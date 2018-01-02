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
// Copyright 2011 Google Inc. All Rights Reserved.
// Author: sreeni@google.com (Sreeni Viswanadha)

/* Copyright (c) 2006, Sun Microsystems, Inc.
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
 */

package com.helger.pgcc.jjtree;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.helger.commons.string.StringHelper;
import com.helger.pgcc.PGVersion;
import com.helger.pgcc.parser.Options;
import com.helger.pgcc.parser.OtherFilesGenCPP;
import com.helger.pgcc.parser.OutputFile;
import com.helger.pgcc.utils.OutputFileGenerator;

final class NodeFilesCpp
{
  private NodeFilesCpp ()
  {}

  private static List <String> headersForJJTreeH = new ArrayList <> ();
  /**
   * ID of the latest version (of JJTree) in which one of the Node classes was
   * modified.
   */
  static final String nodeVersion = PGVersion.majorDotMinor;

  static Set <String> nodesToGenerate = new HashSet <> ();

  static void addType (final String type)
  {
    if (!type.equals ("Node") && !type.equals ("SimpleNode"))
    {
      nodesToGenerate.add (type);
    }
  }

  public static String nodeIncludeFile ()
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (), "Node.h").getAbsolutePath ();
  }

  public static String simpleNodeIncludeFile ()
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (), "SimpleNode.h").getAbsolutePath ();
  }

  public static String simpleNodeCodeFile ()
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (), "SimpleNode.cc").getAbsolutePath ();
  }

  public static String jjtreeIncludeFile ()
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (),
                     JJTreeGlobals.s_parserName + "Tree.h").getAbsolutePath ();
  }

  public static String jjtreeImplFile ()
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (),
                     JJTreeGlobals.s_parserName + "Tree.cc").getAbsolutePath ();
  }

  public static String jjtreeIncludeFile (final String s)
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (), s + ".h").getAbsolutePath ();
  }

  public static String jjtreeImplFile (final String s)
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (), s + ".cc").getAbsolutePath ();
  }

  public static String jjtreeASTIncludeFile (final String ASTNode)
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (), ASTNode + ".h").getAbsolutePath ();
  }

  public static String jjtreeASTCodeFile (final String ASTNode)
  {
    return new File (JJTreeOptions.getJJTreeOutputDirectory (), ASTNode + ".cc").getAbsolutePath ();
  }

  private static String _getVisitorIncludeFile ()
  {
    final String name = getVisitorClass ();
    return new File (JJTreeOptions.getJJTreeOutputDirectory (), name + ".h").getAbsolutePath ();
  }

  static void generateTreeClasses ()
  {
    _generateNodeHeader ();
    _generateSimpleNodeHeader ();
    _generateSimpleNodeCode ();
    _generateMultiTreeInterface ();
    _generateMultiTreeImpl ();
    _generateOneTreeInterface ();
    if (false)
      _generateOneTreeImpl ();
  }

  private static void _generateNodeHeader ()
  {
    final File file = new File (nodeIncludeFile ());

    final String [] options = new String [] { "MULTI",
                                              "NODE_USES_PARSER",
                                              "VISITOR",
                                              "TRACK_TOKENS",
                                              "NODE_PREFIX",
                                              "NODE_EXTENDS",
                                              "NODE_FACTORY",
                                              Options.USEROPTION__SUPPORT_CLASS_VISIBILITY_PUBLIC };

    try (final OutputFile outputFile = new OutputFile (file, nodeVersion, options))
    {
      outputFile.setToolName ("JJTree");

      if (file.exists () && !outputFile.needToWrite)
      {
        return;
      }

      final Map <String, Object> optionMap = new HashMap <> (Options.getOptions ());
      optionMap.put (Options.NONUSER_OPTION__PARSER_NAME, JJTreeGlobals.s_parserName);
      optionMap.put ("VISITOR_RETURN_TYPE", _getVisitorReturnType ());
      optionMap.put ("VISITOR_DATA_TYPE", _getVisitorArgumentType ());
      optionMap.put ("VISITOR_RETURN_TYPE_VOID", Boolean.valueOf (_getVisitorReturnType ().equals ("void")));

      generateFile (outputFile, "/templates/cpp/Node.h.template", optionMap, false);
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  private static void _generateSimpleNodeHeader ()
  {
    final File file = new File (simpleNodeIncludeFile ());

    final String [] options = new String [] { "MULTI",
                                              "NODE_USES_PARSER",
                                              "VISITOR",
                                              "TRACK_TOKENS",
                                              "NODE_PREFIX",
                                              "NODE_EXTENDS",
                                              "NODE_FACTORY",
                                              Options.USEROPTION__SUPPORT_CLASS_VISIBILITY_PUBLIC };

    try (final OutputFile outputFile = new OutputFile (file, nodeVersion, options))
    {
      outputFile.setToolName ("JJTree");

      if (file.exists () && !outputFile.needToWrite)
        return;

      final Map <String, Object> optionMap = new HashMap <> (Options.getOptions ());
      optionMap.put (Options.NONUSER_OPTION__PARSER_NAME, JJTreeGlobals.s_parserName);
      optionMap.put ("VISITOR_RETURN_TYPE", _getVisitorReturnType ());
      optionMap.put ("VISITOR_DATA_TYPE", _getVisitorArgumentType ());
      optionMap.put ("VISITOR_RETURN_TYPE_VOID", Boolean.valueOf (_getVisitorReturnType ().equals ("void")));

      generateFile (outputFile, "/templates/cpp/SimpleNode.h.template", optionMap, false);
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  private static void _generateSimpleNodeCode ()
  {
    final File file = new File (simpleNodeCodeFile ());

    final String [] options = new String [] { "MULTI",
                                              "NODE_USES_PARSER",
                                              "VISITOR",
                                              "TRACK_TOKENS",
                                              "NODE_PREFIX",
                                              "NODE_EXTENDS",
                                              "NODE_FACTORY",
                                              Options.USEROPTION__SUPPORT_CLASS_VISIBILITY_PUBLIC };

    try (final OutputFile outputFile = new OutputFile (file, nodeVersion, options))
    {
      outputFile.setToolName ("JJTree");

      if (file.exists () && !outputFile.needToWrite)
        return;

      final Map <String, Object> optionMap = new HashMap <> (Options.getOptions ());
      optionMap.put (Options.NONUSER_OPTION__PARSER_NAME, JJTreeGlobals.s_parserName);
      optionMap.put ("VISITOR_RETURN_TYPE", _getVisitorReturnType ());
      optionMap.put ("VISITOR_DATA_TYPE", _getVisitorArgumentType ());
      optionMap.put ("VISITOR_RETURN_TYPE_VOID", Boolean.valueOf (_getVisitorReturnType ().equals ("void")));

      generateFile (outputFile, "/templates/cpp/SimpleNode.cc.template", optionMap, false);
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  private static void _generateMultiTreeInterface ()
  {
    final String [] options = new String [] { "MULTI",
                                              "NODE_USES_PARSER",
                                              "VISITOR",
                                              "TRACK_TOKENS",
                                              "NODE_PREFIX",
                                              "NODE_EXTENDS",
                                              "NODE_FACTORY",
                                              Options.USEROPTION__SUPPORT_CLASS_VISIBILITY_PUBLIC };
    try
    {
      for (final String aString : nodesToGenerate)
      {
        final String node = aString;
        final File file = new File (jjtreeIncludeFile (node));
        try (final OutputFile outputFile = new OutputFile (file, nodeVersion, options))
        {
          outputFile.setToolName ("JJTree");

          if (file.exists () && !outputFile.needToWrite)
          {
            return;
          }

          final Map <String, Object> optionMap = new HashMap <> (Options.getOptions ());
          optionMap.put (Options.NONUSER_OPTION__PARSER_NAME, JJTreeGlobals.s_parserName);
          optionMap.put ("VISITOR_RETURN_TYPE", _getVisitorReturnType ());
          optionMap.put ("VISITOR_DATA_TYPE", _getVisitorArgumentType ());
          optionMap.put ("VISITOR_RETURN_TYPE_VOID", Boolean.valueOf (_getVisitorReturnType ().equals ("void")));
          optionMap.put ("NODE_TYPE", node);

          generateFile (outputFile, "/templates/cpp/MultiNodeInterface.template", optionMap, false);
        }
      }
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  private static void _generateMultiTreeImpl ()
  {
    final String [] options = new String [] { "MULTI",
                                              "NODE_USES_PARSER",
                                              "VISITOR",
                                              "TRACK_TOKENS",
                                              "NODE_PREFIX",
                                              "NODE_EXTENDS",
                                              "NODE_FACTORY",
                                              Options.USEROPTION__SUPPORT_CLASS_VISIBILITY_PUBLIC };

    try
    {
      for (final String aString : nodesToGenerate)
      {
        final String node = aString;
        final File file = new File (jjtreeImplFile (node));
        try (final OutputFile outputFile = new OutputFile (file, nodeVersion, options))
        {
          outputFile.setToolName ("JJTree");

          if (file.exists () && !outputFile.needToWrite)
          {
            return;
          }

          final Map <String, Object> optionMap = new HashMap <> (Options.getOptions ());
          optionMap.put (Options.NONUSER_OPTION__PARSER_NAME, JJTreeGlobals.s_parserName);
          optionMap.put ("VISITOR_RETURN_TYPE", _getVisitorReturnType ());
          optionMap.put ("VISITOR_DATA_TYPE", _getVisitorArgumentType ());
          optionMap.put ("VISITOR_RETURN_TYPE_VOID", Boolean.valueOf (_getVisitorReturnType ().equals ("void")));
          optionMap.put ("NODE_TYPE", node);

          generateFile (outputFile, "/templates/cpp/MultiNodeImpl.template", optionMap, false);
        }
      }
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  private static void _generateOneTreeInterface ()
  {
    final File file = new File (jjtreeIncludeFile ());

    try
    {
      final String [] options = new String [] { "MULTI",
                                                "NODE_USES_PARSER",
                                                "VISITOR",
                                                "TRACK_TOKENS",
                                                "NODE_PREFIX",
                                                "NODE_EXTENDS",
                                                "NODE_FACTORY",
                                                Options.USEROPTION__SUPPORT_CLASS_VISIBILITY_PUBLIC };
      try (OutputFile outputFile = new OutputFile (file, nodeVersion, options))
      {
        outputFile.setToolName ("JJTree");

        if (file.exists () && !outputFile.needToWrite)
        {
          return;
        }

        final Map <String, Object> optionMap = new HashMap <> (Options.getOptions ());
        optionMap.put (Options.NONUSER_OPTION__PARSER_NAME, JJTreeGlobals.s_parserName);
        optionMap.put ("VISITOR_RETURN_TYPE", _getVisitorReturnType ());
        optionMap.put ("VISITOR_DATA_TYPE", _getVisitorArgumentType ());
        optionMap.put ("VISITOR_RETURN_TYPE_VOID", Boolean.valueOf (_getVisitorReturnType ().equals ("void")));

        try (final PrintWriter ostr = outputFile.getPrintWriter ())
        {
          final String includeName = file.getName ().replace ('.', '_').toUpperCase (Locale.US);
          ostr.println ("#ifndef " + includeName);
          ostr.println ("#define " + includeName);
          ostr.println ("#include \"SimpleNode.h\"");
          for (final String aString : nodesToGenerate)
          {
            final String s = aString;
            ostr.println ("#include \"" + s + ".h\"");
          }
          ostr.println ("#endif");
        }
      }
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  private static void _generateOneTreeImpl ()
  {
    final File file = new File (jjtreeImplFile ());

    final String [] options = new String [] { "MULTI",
                                              "NODE_USES_PARSER",
                                              "VISITOR",
                                              "TRACK_TOKENS",
                                              "NODE_PREFIX",
                                              "NODE_EXTENDS",
                                              "NODE_FACTORY",
                                              Options.USEROPTION__SUPPORT_CLASS_VISIBILITY_PUBLIC };

    try (final OutputFile outputFile = new OutputFile (file, nodeVersion, options))
    {
      outputFile.setToolName ("JJTree");

      if (file.exists () && !outputFile.needToWrite)
      {
        return;
      }

      final Map <String, Object> optionMap = new HashMap <> (Options.getOptions ());
      optionMap.put (Options.NONUSER_OPTION__PARSER_NAME, JJTreeGlobals.s_parserName);
      optionMap.put ("VISITOR_RETURN_TYPE", _getVisitorReturnType ());
      optionMap.put ("VISITOR_DATA_TYPE", _getVisitorArgumentType ());
      optionMap.put ("VISITOR_RETURN_TYPE_VOID", Boolean.valueOf (_getVisitorReturnType ().equals ("void")));
      generateFile (outputFile, "/templates/cpp/TreeImplHeader.template", optionMap, false);

      final boolean hasNamespace = Options.stringValue (Options.USEROPTION__CPP_NAMESPACE).length () > 0;
      if (hasNamespace)
      {
        outputFile.getPrintWriter ().println ("namespace " + Options.stringValue ("NAMESPACE_OPEN"));
      }

      for (final String aString : nodesToGenerate)
      {
        final String s = aString;
        optionMap.put ("NODE_TYPE", s);
        generateFile (outputFile, "/templates/cpp/MultiNodeImpl.template", optionMap, false);
      }

      if (hasNamespace)
      {
        outputFile.getPrintWriter ().println (Options.stringValue ("NAMESPACE_CLOSE"));
      }
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  static void generatePrologue ()
  {
    // Output the node's namespace name?
  }

  static String nodeConstants ()
  {
    return JJTreeGlobals.s_parserName + "TreeConstants";
  }

  static void generateTreeConstants ()
  {
    final String name = nodeConstants ();
    final File file = new File (JJTreeOptions.getJJTreeOutputDirectory (), name + ".h");
    headersForJJTreeH.add (file.getName ());

    try (final OutputFile outputFile = new OutputFile (file))
    {
      final PrintWriter ostr = outputFile.getPrintWriter ();

      final List <String> nodeIds = ASTNodeDescriptor.getNodeIds ();
      final List <String> nodeNames = ASTNodeDescriptor.getNodeNames ();

      generatePrologue ();
      ostr.println ("#ifndef " + file.getName ().replace ('.', '_').toUpperCase ());
      ostr.println ("#define " + file.getName ().replace ('.', '_').toUpperCase ());

      ostr.println ("\n#include \"JavaCC.h\"");
      final boolean hasNamespace = Options.stringValue (Options.USEROPTION__CPP_NAMESPACE).length () > 0;
      if (hasNamespace)
      {
        ostr.println ("namespace " + Options.stringValue ("NAMESPACE_OPEN"));
      }
      ostr.println ("enum {");
      for (int i = 0; i < nodeIds.size (); ++i)
      {
        final String n = nodeIds.get (i);
        ostr.println ("  " + n + " = " + i + ",");
      }

      ostr.println ("};");
      ostr.println ();

      for (int i = 0; i < nodeNames.size (); ++i)
      {
        ostr.println ("  static JJChar jjtNodeName_arr_" + i + "[] = ");
        final String n = nodeNames.get (i);
        // ostr.println(" (JJChar*)\"" + n + "\",");
        OtherFilesGenCPP.printCharArray (ostr, n);
        ostr.println (";");
      }
      ostr.println ("  static JJString jjtNodeName[] = {");
      for (int i = 0; i < nodeNames.size (); i++)
      {
        ostr.println ("jjtNodeName_arr_" + i + ", ");
      }
      ostr.println ("  };");

      if (hasNamespace)
      {
        ostr.println (Options.stringValue ("NAMESPACE_CLOSE"));
      }

      ostr.println ("#endif");
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }

  static String getVisitorClass ()
  {
    return JJTreeGlobals.s_parserName + "Visitor";
  }

  private static String _getVisitMethodName (final String className)
  {
    final StringBuilder sb = new StringBuilder ("visit");
    if (Options.booleanValue ("VISITOR_METHOD_NAME_INCLUDES_TYPE_NAME"))
    {
      sb.append (Character.toUpperCase (className.charAt (0)));
      for (int i = 1; i < className.length (); i++)
      {
        sb.append (className.charAt (i));
      }
    }

    return sb.toString ();
  }

  private static String _getVisitorArgumentType ()
  {
    final String ret = Options.stringValue ("VISITOR_DATA_TYPE");
    return ret == null || ret.length () == 0 || ret.equals ("Object") ? "void *" : ret;
  }

  private static String _getVisitorReturnType ()
  {
    final String ret = Options.stringValue ("VISITOR_RETURN_TYPE");
    return ret == null || ret.length () == 0 || ret.equals ("Object") ? "void " : ret;
  }

  static void generateVisitors ()
  {
    if (!JJTreeOptions.getVisitor ())
      return;

    final File file = new File (_getVisitorIncludeFile ());
    try (final OutputFile outputFile = new OutputFile (file); final PrintWriter ostr = outputFile.getPrintWriter ())
    {
      generatePrologue ();
      ostr.println ("#ifndef " + file.getName ().replace ('.', '_').toUpperCase ());
      ostr.println ("#define " + file.getName ().replace ('.', '_').toUpperCase ());
      ostr.println ("\n#include \"JavaCC.h\"");
      ostr.println ("#include \"" + JJTreeGlobals.s_parserName + "Tree.h" + "\"");

      final boolean hasNamespace = Options.stringValue (Options.USEROPTION__CPP_NAMESPACE).length () > 0;
      if (hasNamespace)
      {
        ostr.println ("namespace " + Options.stringValue ("NAMESPACE_OPEN"));
      }

      _generateVisitorInterface (ostr);
      _generateDefaultVisitor (ostr);

      if (hasNamespace)
      {
        ostr.println (Options.stringValue ("NAMESPACE_CLOSE"));
      }

      ostr.println ("#endif");
    }
    catch (final IOException ioe)
    {
      throw new UncheckedIOException (ioe);
    }
  }

  private static void _generateVisitorInterface (final PrintWriter ostr)
  {
    final String name = getVisitorClass ();
    final List <String> nodeNames = ASTNodeDescriptor.getNodeNames ();

    ostr.println ("class " + name);
    ostr.println ("{");

    String argumentType = _getVisitorArgumentType ();
    final String returnType = _getVisitorReturnType ();
    if (StringHelper.hasText (JJTreeOptions.getVisitorDataType ()))
      argumentType = JJTreeOptions.getVisitorDataType ();

    ostr.println ("  public:");

    ostr.println ("  virtual " + returnType + " visit(const SimpleNode *node, " + argumentType + " data) = 0;");
    if (JJTreeOptions.getMulti ())
    {
      for (int i = 0; i < nodeNames.size (); ++i)
      {
        final String n = nodeNames.get (i);
        if (n.equals ("void"))
        {
          continue;
        }
        final String nodeType = JJTreeOptions.getNodePrefix () + n;
        ostr.println ("  virtual " +
                      returnType +
                      " " +
                      _getVisitMethodName (nodeType) +
                      "(const " +
                      nodeType +
                      " *node, " +
                      argumentType +
                      " data) = 0;");
      }
    }

    ostr.println ("  virtual ~" + name + "() { }");
    ostr.println ("};");
  }

  static String defaultVisitorClass ()
  {
    return JJTreeGlobals.s_parserName + "DefaultVisitor";
  }

  private static void _generateDefaultVisitor (final PrintWriter ostr)
  {
    final String className = defaultVisitorClass ();
    final List <String> nodeNames = ASTNodeDescriptor.getNodeNames ();

    ostr.println ("class " + className + " : public " + getVisitorClass () + " {");

    final String argumentType = _getVisitorArgumentType ();
    final String ret = _getVisitorReturnType ();

    ostr.println ("public:");
    ostr.println ("  virtual " + ret + " defaultVisit(const SimpleNode *node, " + argumentType + " data) = 0;");
    // ostr.println(" node->childrenAccept(this, data);");
    // ostr.println(" return" + (ret.trim().equals("void") ? "" : " data") +
    // ";");
    // ostr.println(" }");

    ostr.println ("  virtual " + ret + " visit(const SimpleNode *node, " + argumentType + " data) {");
    ostr.println ("    " + (ret.trim ().equals ("void") ? "" : "return ") + "defaultVisit(node, data);");
    ostr.println ("}");

    if (JJTreeOptions.getMulti ())
    {
      for (int i = 0; i < nodeNames.size (); ++i)
      {
        final String n = nodeNames.get (i);
        if (n.equals ("void"))
        {
          continue;
        }
        final String nodeType = JJTreeOptions.getNodePrefix () + n;
        ostr.println ("  virtual " +
                      ret +
                      " " +
                      _getVisitMethodName (nodeType) +
                      "(const " +
                      nodeType +
                      " *node, " +
                      argumentType +
                      " data) {");
        ostr.println ("    " + (ret.trim ().equals ("void") ? "" : "return ") + "defaultVisit(node, data);");
        ostr.println ("  }");
      }
    }
    ostr.println ("  ~" + className + "() { }");
    ostr.println ("};");
  }

  public static void generateFile (final OutputFile outputFile,
                                   final String template,
                                   final Map <String, Object> options,
                                   final boolean close) throws IOException
  {
    @SuppressWarnings ("resource")
    final PrintWriter ostr = outputFile.getPrintWriter ();
    generatePrologue ();

    final OutputFileGenerator generator = new OutputFileGenerator (template, options);
    generator.generate (ostr);
    if (close)
      ostr.close ();
  }
}
