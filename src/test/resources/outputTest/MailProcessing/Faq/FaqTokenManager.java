/* FaqTokenManager.java */
/* Generated by: ParserGeneratorCC: Do not edit this line. FaqTokenManager.java */
import java.io.*;

/** Token Manager. */
@SuppressWarnings ("unused")
public class FaqTokenManager implements FaqConstants {
private int jjMoveStringLiteralDfa0_0()
{
   return jjMoveNfa_0(0, 0);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 18;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x2400L & l) != 0x0L)
                     { jjCheckNAdd(15); }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 1:
                  if (curChar == 42)
                     { jjAddStates(0, 1); }
                  break;
               case 2:
                  if ((0x2400L & l) != 0x0L && kind > 4)
                     kind = 4;
                  break;
               case 3:
                  if (curChar == 10 && kind > 4)
                     kind = 4;
                  break;
               case 4:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 5:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 6:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 7:
                  if (curChar == 32)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 12:
                  if (curChar == 32)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 13:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 12;
                  break;
               case 14:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 16:
                  if (curChar == 10)
                     { jjCheckNAdd(15); }
                  break;
               case 17:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 8:
                  if (curChar == 72)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 9:
                  if (curChar == 79)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 10:
                  if (curChar == 79)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 11:
                  if (curChar == 69)
                     jjstateSet[jjnewStateCnt++] = 10;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 18 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(final java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_1(int pos, long active0){
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_1(int pos, long active0){
   return jjMoveNfa_1(jjStopStringLiteralDfa_1(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_1(){
   switch(curChar)
   {
      case 'D':
         return jjMoveStringLiteralDfa1_1(0x200L);
      case 'F':
         return jjMoveStringLiteralDfa1_1(0x100L);
      case 'S':
         return jjMoveStringLiteralDfa1_1(0x80L);
      default :
         return jjMoveNfa_1(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_1(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 'a':
         return jjMoveStringLiteralDfa2_1(active0, 0x200L);
      case 'r':
         return jjMoveStringLiteralDfa2_1(active0, 0x100L);
      case 'u':
         return jjMoveStringLiteralDfa2_1(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_1(0, active0);
}
private int jjMoveStringLiteralDfa2_1(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 'b':
         return jjMoveStringLiteralDfa3_1(active0, 0x80L);
      case 'o':
         return jjMoveStringLiteralDfa3_1(active0, 0x100L);
      case 't':
         return jjMoveStringLiteralDfa3_1(active0, 0x200L);
      default :
         break;
   }
   return jjStartNfa_1(1, active0);
}
private int jjMoveStringLiteralDfa3_1(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 'e':
         return jjMoveStringLiteralDfa4_1(active0, 0x200L);
      case 'j':
         return jjMoveStringLiteralDfa4_1(active0, 0x80L);
      case 'm':
         return jjMoveStringLiteralDfa4_1(active0, 0x100L);
      default :
         break;
   }
   return jjStartNfa_1(2, active0);
}
private int jjMoveStringLiteralDfa4_1(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case ':':
         return jjMoveStringLiteralDfa5_1(active0, 0x300L);
      case 'e':
         return jjMoveStringLiteralDfa5_1(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_1(3, active0);
}
private int jjMoveStringLiteralDfa5_1(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case ' ':
         if ((active0 & 0x100L) != 0x0L)
            return jjStopAtPos(5, 8);
         else if ((active0 & 0x200L) != 0x0L)
            return jjStopAtPos(5, 9);
         break;
      case 'c':
         return jjMoveStringLiteralDfa6_1(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_1(4, active0);
}
private int jjMoveStringLiteralDfa6_1(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 't':
         return jjMoveStringLiteralDfa7_1(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_1(5, active0);
}
private int jjMoveStringLiteralDfa7_1(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case ':':
         return jjMoveStringLiteralDfa8_1(active0, 0x80L);
      default :
         break;
   }
   return jjStartNfa_1(6, active0);
}
private int jjMoveStringLiteralDfa8_1(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_1(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_1(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case ' ':
         if ((active0 & 0x80L) != 0x0L)
            return jjStopAtPos(8, 7);
         break;
      default :
         break;
   }
   return jjStartNfa_1(7, active0);
}
private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 8;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (curChar == 13)
                     { jjAddStates(2, 3); }
                  else if (curChar == 10)
                     { jjCheckNAddTwoStates(1, 3); }
                  break;
               case 1:
                  if ((0x2400L & l) != 0x0L && kind > 6)
                     kind = 6;
                  break;
               case 2:
               case 7:
                  if (curChar == 10 && kind > 6)
                     kind = 6;
                  break;
               case 3:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 4:
                  if (curChar == 13)
                     { jjAddStates(2, 3); }
                  break;
               case 5:
                  if (curChar == 10)
                     { jjCheckNAddTwoStates(1, 3); }
                  break;
               case 6:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 8 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(final java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_2()
{
   return jjMoveNfa_2(1, 0);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_2(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((0xffffffffffffdbffL & l) != 0x0L)
                  {
                     if (kind > 11)
                        kind = 11;
                     { jjCheckNAdd(0); }
                  }
                  else if ((0x2400L & l) != 0x0L)
                  {
                     if (kind > 12)
                        kind = 12;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if ((0xffffffffffffdbffL & l) == 0x0L)
                     break;
                  kind = 11;
                  { jjCheckNAdd(0); }
                  break;
               case 2:
                  if (curChar == 10 && kind > 12)
                     kind = 12;
                  break;
               case 3:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
               case 0:
                  kind = 11;
                  { jjCheckNAdd(0); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
               case 0:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 11)
                     kind = 11;
                  { jjCheckNAdd(0); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 4 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(final java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_3()
{
   return jjMoveNfa_3(1, 0);
}
private int jjMoveNfa_3(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((0xffffffffffffdbffL & l) != 0x0L)
                  {
                     if (kind > 13)
                        kind = 13;
                     { jjCheckNAdd(0); }
                  }
                  else if ((0x2400L & l) != 0x0L)
                  {
                     if (kind > 14)
                        kind = 14;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if ((0xffffffffffffdbffL & l) == 0x0L)
                     break;
                  kind = 13;
                  { jjCheckNAdd(0); }
                  break;
               case 2:
                  if (curChar == 10 && kind > 14)
                     kind = 14;
                  break;
               case 3:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
               case 0:
                  kind = 13;
                  { jjCheckNAdd(0); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
               case 0:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 13)
                     kind = 13;
                  { jjCheckNAdd(0); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 4 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(final java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_4()
{
   return jjMoveNfa_4(1, 0);
}
private int jjMoveNfa_4(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((0xffffffffffffdbffL & l) != 0x0L)
                  {
                     if (kind > 15)
                        kind = 15;
                     { jjCheckNAdd(0); }
                  }
                  else if ((0x2400L & l) != 0x0L)
                  {
                     if (kind > 16)
                        kind = 16;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if ((0xffffffffffffdbffL & l) == 0x0L)
                     break;
                  kind = 15;
                  { jjCheckNAdd(0); }
                  break;
               case 2:
                  if (curChar == 10 && kind > 16)
                     kind = 16;
                  break;
               case 3:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
               case 0:
                  kind = 15;
                  { jjCheckNAdd(0); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
               case 0:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 15)
                     kind = 15;
                  { jjCheckNAdd(0); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 4 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(final java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_5(int pos, long active0){
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_5(int pos, long active0){
   return jjMoveNfa_5(jjStopStringLiteralDfa_5(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_5(){
   switch(curChar)
   {
      case 31:
         return jjStopAtPos(0, 18);
      default :
         return jjMoveNfa_5(4, 0);
   }
}
private int jjMoveNfa_5(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 4;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if ((0xffffffff7fffdbffL & l) != 0x0L)
                     { jjCheckNAddStates(4, 6); }
                  else if ((0x2400L & l) != 0x0L)
                  {
                     if (kind > 17)
                        kind = 17;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 0:
                  if ((0xffffffff7fffdbffL & l) != 0x0L)
                     { jjCheckNAddStates(4, 6); }
                  break;
               case 1:
                  if ((0x2400L & l) != 0x0L && kind > 17)
                     kind = 17;
                  break;
               case 2:
                  if (curChar == 10 && kind > 17)
                     kind = 17;
                  break;
               case 3:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 2;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 0:
                  { jjCheckNAddStates(4, 6); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
               case 0:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjCheckNAddStates(4, 6); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      i = jjnewStateCnt;
      jjnewStateCnt = startsAt;
      startsAt = 4 - jjnewStateCnt;
      if (i == startsAt)
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(final java.io.IOException e) { return curPos; }
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, "\37", };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = im == null ? input_stream.getImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   t.image = curTokenImage;

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}
static final int[] jjnextStates = {
   2, 4, 5, 6, 0, 1, 3, 
};

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop:
  for (;;)
  {
   try
   {
      curChar = input_stream.beginToken();
   }
   catch(final Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   switch(curLexState)
   {
     case 0:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_0();
       if (jjmatchedPos == 0 && jjmatchedKind > 5)
       {
          jjmatchedKind = 5;
       }
       break;
     case 1:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_1();
       if (jjmatchedPos == 0 && jjmatchedKind > 10)
       {
          jjmatchedKind = 10;
       }
       break;
     case 2:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_2();
       break;
     case 3:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_3();
       break;
     case 4:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_4();
       break;
     case 5:
       jjmatchedKind = 0x7fffffff;
       jjmatchedPos = 0;
       curPos = jjMoveStringLiteralDfa0_5();
       break;
   }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try {
       input_stream.readChar();
       input_stream.backup(1);
     }
     catch (final java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.getImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.getImage();
     }
     throw new TokenMgrException(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrException.LEXICAL_ERROR);
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public FaqTokenManager(SimpleCharStream stream){
    input_stream = stream;
  }

  /** Constructor. */
  public FaqTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  
  public void ReInit(SimpleCharStream stream)
  {


    jjmatchedPos =
    jjnewStateCnt =
    0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 18; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream, int lexState)
  {
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 6 || lexState < 0)
      throw new TokenMgrException("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrException.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }


/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "MAILHEADER",
   "MAILSUBJECT",
   "MAILFROM",
   "MAILDATE",
   "MAILBODY",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, 1, -1, 5, 2, 3, 4, -1, -1, 1, -1, 1, -1, 1, -1, 0, 
};
static final long[] jjtoToken = {
   0x6a801L, 
};
static final long[] jjtoSkip = {
   0x157f0L, 
};
static final long[] jjtoSpecial = {
   0x0L, 
};
static final long[] jjtoMore = {
   0x0L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[18];
    private final int[] jjstateSet = new int[2 * 18];
    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    protected int curChar;
}
