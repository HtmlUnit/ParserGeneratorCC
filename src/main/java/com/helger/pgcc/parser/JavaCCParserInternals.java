/**
 * Copyright (c) 2006, Sun Microsystems, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Sun Microsystems, Inc. nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 2011 Google Inc. All Rights Reserved.
 * Author: sreeni@google.com (Sreeni Viswanadha)
 *
 * Copyright 2017-2018 Philip Helger, pgcc@helger.com
 */
package com.helger.pgcc.parser;

import java.util.HashMap;
import java.util.List;

/**
 * Utilities.
 */
public abstract class JavaCCParserInternals extends JavaCCGlobals
{
  protected static void initialize ()
  {
    final Integer i = Integer.valueOf (0);
    s_lexstate_S2I.put ("DEFAULT", i);
    s_lexstate_I2S.put (i, "DEFAULT");
    s_simple_tokens_table.put ("DEFAULT", new HashMap <> ());
  }

  protected static void addcuname (final String id)
  {
    s_cu_name = id;
  }

  protected static void compare (final Token t, final String id1, final String id2)
  {
    if (!id2.equals (id1))
    {
      JavaCCErrors.parse_error (t, "Name " + id2 + " must be the same as that used at PARSER_BEGIN (" + id1 + ")");
    }
  }

  private static List <Token> add_cu_token_here = s_cu_to_insertion_point_1;
  private static Token first_cu_token;
  private static boolean insertionpoint1set = false;
  private static boolean insertionpoint2set = false;

  protected static void setinsertionpoint (final Token t, final int no)
  {
    do
    {
      add_cu_token_here.add (first_cu_token);
      first_cu_token = first_cu_token.next;
    } while (first_cu_token != t);
    if (no == 1)
    {
      if (insertionpoint1set)
      {
        JavaCCErrors.parse_error (t, "Multiple declaration of parser class.");
      }
      else
      {
        insertionpoint1set = true;
        add_cu_token_here = s_cu_to_insertion_point_2;
      }
    }
    else
    {
      add_cu_token_here = s_cu_from_insertion_point_2;
      insertionpoint2set = true;
    }
    first_cu_token = t;
  }

  protected static void insertionpointerrors (final Token t)
  {
    while (first_cu_token != t)
    {
      add_cu_token_here.add (first_cu_token);
      first_cu_token = first_cu_token.next;
    }
    if (!insertionpoint1set || !insertionpoint2set)
    {
      JavaCCErrors.parse_error (t, "Parser class has not been defined between PARSER_BEGIN and PARSER_END.");
    }
  }

  protected static void set_initial_cu_token (final Token t)
  {
    first_cu_token = t;
  }

  protected static void addProduction (final NormalProduction p)
  {
    s_bnfproductions.add (p);
  }

  protected static void productionAddExpansion (final BNFProduction p, final Expansion e)
  {
    e.m_parent = p;
    p.setExpansion (e);
  }

  private static int nextFreeLexState = 1;

  protected static void addregexpr (final TokenProduction p)
  {
    s_rexprlist.add (p);
    if (Options.isUserTokenManager ())
    {
      if (p.m_lexStates == null || p.m_lexStates.length != 1 || !p.m_lexStates[0].equals ("DEFAULT"))
      {
        JavaCCErrors.warning (p,
                              "Ignoring lexical state specifications since option " +
                                 "USER_TOKEN_MANAGER has been set to true.");
      }
    }
    if (p.m_lexStates == null)
    {
      return;
    }
    for (int i = 0; i < p.m_lexStates.length; i++)
    {
      for (int j = 0; j < i; j++)
      {
        if (p.m_lexStates[i].equals (p.m_lexStates[j]))
        {
          JavaCCErrors.parse_error (p, "Multiple occurrence of \"" + p.m_lexStates[i] + "\" in lexical state list.");
        }
      }
      if (s_lexstate_S2I.get (p.m_lexStates[i]) == null)
      {
        final Integer ii = Integer.valueOf (nextFreeLexState++);
        s_lexstate_S2I.put (p.m_lexStates[i], ii);
        s_lexstate_I2S.put (ii, p.m_lexStates[i]);
        s_simple_tokens_table.put (p.m_lexStates[i], new HashMap <> ());
      }
    }
  }

  protected static void add_token_manager_decls (final Token t, final List <Token> decls)
  {
    if (s_token_mgr_decls != null)
    {
      JavaCCErrors.parse_error (t, "Multiple occurrence of \"TOKEN_MGR_DECLS\".");
    }
    else
    {
      s_token_mgr_decls = decls;
      if (Options.isUserTokenManager ())
      {
        JavaCCErrors.warning (t,
                              "Ignoring declarations in \"TOKEN_MGR_DECLS\" since option " +
                                 "USER_TOKEN_MANAGER has been set to true.");
      }
    }
  }

  protected static void add_inline_regexpr (final RegularExpression r)
  {
    if (!(r instanceof REndOfFile))
    {
      final TokenProduction p = new TokenProduction ();
      p.m_isExplicit = false;
      p.m_lexStates = new String [] { "DEFAULT" };
      p.m_kind = TokenProduction.TOKEN;
      final RegExprSpec res = new RegExprSpec ();
      res.rexp = r;
      res.rexp.tpContext = p;
      res.act = new Action ();
      res.nextState = null;
      res.nsTok = null;
      p.m_respecs.add (res);
      s_rexprlist.add (p);
    }
  }

  protected static boolean hexchar (final char ch)
  {
    if (ch >= '0' && ch <= '9')
      return true;
    if (ch >= 'A' && ch <= 'F')
      return true;
    if (ch >= 'a' && ch <= 'f')
      return true;
    return false;
  }

  protected static int hexval (final char ch)
  {
    if (ch >= '0' && ch <= '9')
      return (ch) - ('0');
    if (ch >= 'A' && ch <= 'F')
      return (ch) - ('A') + 10;
    return (ch) - ('a') + 10;
  }

  protected static String remove_escapes_and_quotes (final Token t, final String str)
  {
    String retval = "";
    int index = 1;
    char ch, ch1;
    int ordinal;
    while (index < str.length () - 1)
    {
      if (str.charAt (index) != '\\')
      {
        retval += str.charAt (index);
        index++;
        continue;
      }
      index++;
      ch = str.charAt (index);
      if (ch == 'b')
      {
        retval += '\b';
        index++;
        continue;
      }
      if (ch == 't')
      {
        retval += '\t';
        index++;
        continue;
      }
      if (ch == 'n')
      {
        retval += '\n';
        index++;
        continue;
      }
      if (ch == 'f')
      {
        retval += '\f';
        index++;
        continue;
      }
      if (ch == 'r')
      {
        retval += '\r';
        index++;
        continue;
      }
      if (ch == '"')
      {
        retval += '\"';
        index++;
        continue;
      }
      if (ch == '\'')
      {
        retval += '\'';
        index++;
        continue;
      }
      if (ch == '\\')
      {
        retval += '\\';
        index++;
        continue;
      }
      if (ch >= '0' && ch <= '7')
      {
        ordinal = (ch) - ('0');
        index++;
        ch1 = str.charAt (index);
        if (ch1 >= '0' && ch1 <= '7')
        {
          ordinal = ordinal * 8 + (ch1) - ('0');
          index++;
          ch1 = str.charAt (index);
          if (ch <= '3' && ch1 >= '0' && ch1 <= '7')
          {
            ordinal = ordinal * 8 + (ch1) - ('0');
            index++;
          }
        }
        retval += (char) ordinal;
        continue;
      }
      if (ch == 'u')
      {
        index++;
        ch = str.charAt (index);
        if (hexchar (ch))
        {
          ordinal = hexval (ch);
          index++;
          ch = str.charAt (index);
          if (hexchar (ch))
          {
            ordinal = ordinal * 16 + hexval (ch);
            index++;
            ch = str.charAt (index);
            if (hexchar (ch))
            {
              ordinal = ordinal * 16 + hexval (ch);
              index++;
              ch = str.charAt (index);
              if (hexchar (ch))
              {
                ordinal = ordinal * 16 + hexval (ch);
                index++;
                continue;
              }
            }
          }
        }
        JavaCCErrors.parse_error (t,
                                  "Encountered non-hex character '" +
                                     ch +
                                     "' at position " +
                                     index +
                                     " of string " +
                                     "- Unicode escape must have 4 hex digits after it.");
        return retval;
      }
      JavaCCErrors.parse_error (t, "Illegal escape sequence '\\" + ch + "' at position " + index + " of string.");
      return retval;
    }
    return retval;
  }

  protected static char character_descriptor_assign (final Token t, final String s)
  {
    if (s.length () != 1)
    {
      JavaCCErrors.parse_error (t, "String in character list may contain only one character.");
      return ' ';
    }
    return s.charAt (0);
  }

  protected static char character_descriptor_assign (final Token t, final String s, final String left)
  {
    if (s.length () != 1)
    {
      JavaCCErrors.parse_error (t, "String in character list may contain only one character.");
      return ' ';
    }
    if (left.charAt (0) > s.charAt (0))
    {
      JavaCCErrors.parse_error (t,
                                "Right end of character range \'" +
                                   s +
                                   "\' has a lower ordinal value than the left end of character range \'" +
                                   left +
                                   "\'.");
      return left.charAt (0);
    }
    return s.charAt (0);
  }

  protected static void makeTryBlock (final Token tryLoc,
                                      final Container result,
                                      final Container nestedExp,
                                      final List <List <Token>> types,
                                      final List <Token> ids,
                                      final List <List <Token>> catchblks,
                                      final List <Token> finallyblk)
  {
    if (catchblks.size () == 0 && finallyblk == null)
    {
      JavaCCErrors.parse_error (tryLoc, "Try block must contain at least one catch or finally block.");
      return;
    }
    final TryBlock tblk = new TryBlock ();
    tblk.setLine (tryLoc.beginLine);
    tblk.setColumn (tryLoc.beginColumn);
    tblk.exp = (Expansion) (nestedExp.member);
    tblk.exp.m_parent = tblk;
    tblk.exp.m_ordinal = 0;
    tblk.types = types;
    tblk.m_ids = ids;
    tblk.m_catchblks = catchblks;
    tblk.m_finallyblk = finallyblk;
    result.member = tblk;
  }

  public static void reInit ()
  {
    add_cu_token_here = s_cu_to_insertion_point_1;
    first_cu_token = null;
    insertionpoint1set = false;
    insertionpoint2set = false;
    nextFreeLexState = 1;
  }

}
