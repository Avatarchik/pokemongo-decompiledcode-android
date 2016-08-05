package org.apache.commons.io;

import java.io.IOException;
import java.io.OutputStream;

public class HexDump
{
  public static final String EOL = System.getProperty("line.separator");
  private static final char[] _hexcodes;
  private static final int[] _shifts;
  
  static
  {
    char[] arrayOfChar = new char[16];
    arrayOfChar[0] = 48;
    arrayOfChar[1] = 49;
    arrayOfChar[2] = 50;
    arrayOfChar[3] = 51;
    arrayOfChar[4] = 52;
    arrayOfChar[5] = 53;
    arrayOfChar[6] = 54;
    arrayOfChar[7] = 55;
    arrayOfChar[8] = 56;
    arrayOfChar[9] = 57;
    arrayOfChar[10] = 65;
    arrayOfChar[11] = 66;
    arrayOfChar[12] = 67;
    arrayOfChar[13] = 68;
    arrayOfChar[14] = 69;
    arrayOfChar[15] = 70;
    _hexcodes = arrayOfChar;
    int[] arrayOfInt = new int[8];
    arrayOfInt[0] = 28;
    arrayOfInt[1] = 24;
    arrayOfInt[2] = 20;
    arrayOfInt[3] = 16;
    arrayOfInt[4] = 12;
    arrayOfInt[5] = 8;
    arrayOfInt[6] = 4;
    arrayOfInt[7] = 0;
    _shifts = arrayOfInt;
  }
  
  private static StringBuilder dump(StringBuilder paramStringBuilder, byte paramByte)
  {
    for (int i = 0; i < 2; i++) {
      paramStringBuilder.append(_hexcodes[(0xF & paramByte >> _shifts[(i + 6)])]);
    }
    return paramStringBuilder;
  }
  
  private static StringBuilder dump(StringBuilder paramStringBuilder, long paramLong)
  {
    for (int i = 0; i < 8; i++) {
      paramStringBuilder.append(_hexcodes[(0xF & (int)(paramLong >> _shifts[i]))]);
    }
    return paramStringBuilder;
  }
  
  public static void dump(byte[] paramArrayOfByte, long paramLong, OutputStream paramOutputStream, int paramInt)
    throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException
  {
    if ((paramInt < 0) || (paramInt >= paramArrayOfByte.length)) {
      throw new ArrayIndexOutOfBoundsException("illegal index: " + paramInt + " into array of length " + paramArrayOfByte.length);
    }
    if (paramOutputStream == null) {
      throw new IllegalArgumentException("cannot write to nullstream");
    }
    long l = paramLong + paramInt;
    StringBuilder localStringBuilder = new StringBuilder(74);
    for (int i = paramInt; i < paramArrayOfByte.length; i += 16)
    {
      int j = paramArrayOfByte.length - i;
      if (j > 16) {
        j = 16;
      }
      dump(localStringBuilder, l).append(' ');
      int k = 0;
      if (k < 16)
      {
        if (k < j) {
          dump(localStringBuilder, paramArrayOfByte[(k + i)]);
        }
        for (;;)
        {
          localStringBuilder.append(' ');
          k++;
          break;
          localStringBuilder.append("  ");
        }
      }
      int m = 0;
      if (m < j)
      {
        if ((paramArrayOfByte[(m + i)] >= 32) && (paramArrayOfByte[(m + i)] < Byte.MAX_VALUE)) {
          localStringBuilder.append((char)paramArrayOfByte[(m + i)]);
        }
        for (;;)
        {
          m++;
          break;
          localStringBuilder.append('.');
        }
      }
      localStringBuilder.append(EOL);
      paramOutputStream.write(localStringBuilder.toString().getBytes());
      paramOutputStream.flush();
      localStringBuilder.setLength(0);
      l += j;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/HexDump.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */