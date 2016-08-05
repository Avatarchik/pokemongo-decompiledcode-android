package com.fasterxml.jackson.core.io;

public final class NumberOutput
{
  private static int BILLION;
  static final char[] FULL_3;
  static final byte[] FULL_TRIPLETS_B;
  static final char[] LEAD_3;
  private static long MAX_INT_AS_LONG;
  private static int MILLION = 1000000;
  private static long MIN_INT_AS_LONG;
  private static final char NC;
  static final String SMALLEST_LONG;
  private static long TEN_BILLION_L;
  private static long THOUSAND_L;
  static final String[] sSmallIntStrs;
  static final String[] sSmallIntStrs2;
  
  static
  {
    BILLION = 1000000000;
    TEN_BILLION_L = 10000000000L;
    THOUSAND_L = 1000L;
    MIN_INT_AS_LONG = -2147483648L;
    MAX_INT_AS_LONG = 2147483647L;
    SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    LEAD_3 = new char['ྠ'];
    FULL_3 = new char['ྠ'];
    int i = 0;
    label210:
    for (int j = 0; j < 10; j++)
    {
      int m = (char)(j + 48);
      int n;
      if (j == 0) {
        n = 0;
      }
      for (int i1 = 0;; i1++)
      {
        if (i1 >= 10) {
          break label210;
        }
        int i2 = (char)(i1 + 48);
        if ((j == 0) && (i1 == 0)) {}
        for (int i3 = 0;; i3 = i2)
        {
          for (int i4 = 0; i4 < 10; i4++)
          {
            int i5 = (char)(i4 + 48);
            LEAD_3[i] = n;
            LEAD_3[(i + 1)] = i3;
            LEAD_3[(i + 2)] = i5;
            FULL_3[i] = m;
            FULL_3[(i + 1)] = i2;
            FULL_3[(i + 2)] = i5;
            i += 4;
          }
          n = m;
          break;
        }
      }
    }
    FULL_TRIPLETS_B = new byte['ྠ'];
    for (int k = 0; k < 4000; k++) {
      FULL_TRIPLETS_B[k] = ((byte)FULL_3[k]);
    }
    String[] arrayOfString1 = new String[11];
    arrayOfString1[0] = "0";
    arrayOfString1[1] = "1";
    arrayOfString1[2] = "2";
    arrayOfString1[3] = "3";
    arrayOfString1[4] = "4";
    arrayOfString1[5] = "5";
    arrayOfString1[6] = "6";
    arrayOfString1[7] = "7";
    arrayOfString1[8] = "8";
    arrayOfString1[9] = "9";
    arrayOfString1[10] = "10";
    sSmallIntStrs = arrayOfString1;
    String[] arrayOfString2 = new String[10];
    arrayOfString2[0] = "-1";
    arrayOfString2[1] = "-2";
    arrayOfString2[2] = "-3";
    arrayOfString2[3] = "-4";
    arrayOfString2[4] = "-5";
    arrayOfString2[5] = "-6";
    arrayOfString2[6] = "-7";
    arrayOfString2[7] = "-8";
    arrayOfString2[8] = "-9";
    arrayOfString2[9] = "-10";
    sSmallIntStrs2 = arrayOfString2;
  }
  
  private static int calcLongStrLength(long paramLong)
  {
    int i = 10;
    for (long l = TEN_BILLION_L;; l = (l << 3) + (l << 1))
    {
      if ((paramLong < l) || (i == 19)) {
        return i;
      }
      i++;
    }
  }
  
  private static int full3(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    int i = paramInt1 << 2;
    int j = paramInt2 + 1;
    byte[] arrayOfByte1 = FULL_TRIPLETS_B;
    int k = i + 1;
    paramArrayOfByte[paramInt2] = arrayOfByte1[i];
    int m = j + 1;
    byte[] arrayOfByte2 = FULL_TRIPLETS_B;
    int n = k + 1;
    paramArrayOfByte[j] = arrayOfByte2[k];
    int i1 = m + 1;
    paramArrayOfByte[m] = FULL_TRIPLETS_B[n];
    return i1;
  }
  
  private static int full3(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    int i = paramInt1 << 2;
    int j = paramInt2 + 1;
    char[] arrayOfChar1 = FULL_3;
    int k = i + 1;
    paramArrayOfChar[paramInt2] = arrayOfChar1[i];
    int m = j + 1;
    char[] arrayOfChar2 = FULL_3;
    int n = k + 1;
    paramArrayOfChar[j] = arrayOfChar2[k];
    int i1 = m + 1;
    paramArrayOfChar[m] = FULL_3[n];
    return i1;
  }
  
  private static int leading3(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    int i = paramInt1 << 2;
    char[] arrayOfChar1 = LEAD_3;
    int j = i + 1;
    int k = arrayOfChar1[i];
    if (k != 0)
    {
      int i3 = paramInt2 + 1;
      paramArrayOfByte[paramInt2] = ((byte)k);
      paramInt2 = i3;
    }
    char[] arrayOfChar2 = LEAD_3;
    int m = j + 1;
    int n = arrayOfChar2[j];
    if (n != 0)
    {
      int i2 = paramInt2 + 1;
      paramArrayOfByte[paramInt2] = ((byte)n);
      paramInt2 = i2;
    }
    int i1 = paramInt2 + 1;
    paramArrayOfByte[paramInt2] = ((byte)LEAD_3[m]);
    return i1;
  }
  
  private static int leading3(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    int i = paramInt1 << 2;
    char[] arrayOfChar1 = LEAD_3;
    int j = i + 1;
    int k = arrayOfChar1[i];
    if (k != 0)
    {
      int i3 = paramInt2 + 1;
      paramArrayOfChar[paramInt2] = k;
      paramInt2 = i3;
    }
    char[] arrayOfChar2 = LEAD_3;
    int m = j + 1;
    int n = arrayOfChar2[j];
    if (n != 0)
    {
      int i2 = paramInt2 + 1;
      paramArrayOfChar[paramInt2] = n;
      paramInt2 = i2;
    }
    int i1 = paramInt2 + 1;
    paramArrayOfChar[paramInt2] = LEAD_3[m];
    return i1;
  }
  
  public static int outputInt(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    int i2;
    if (paramInt1 < 0)
    {
      if (paramInt1 == Integer.MIN_VALUE)
      {
        i2 = outputLong(paramInt1, paramArrayOfByte, paramInt2);
        return i2;
      }
      int i8 = paramInt2 + 1;
      paramArrayOfByte[paramInt2] = 45;
      paramInt1 = -paramInt1;
      paramInt2 = i8;
    }
    if (paramInt1 < MILLION)
    {
      int i6;
      if (paramInt1 < 1000) {
        if (paramInt1 < 10)
        {
          int i7 = paramInt2 + 1;
          paramArrayOfByte[paramInt2] = ((byte)(paramInt1 + 48));
          i6 = i7;
        }
      }
      for (;;)
      {
        i2 = i6;
        break;
        i6 = leading3(paramInt1, paramArrayOfByte, paramInt2);
        continue;
        int i5 = paramInt1 / 1000;
        i6 = full3(paramInt1 - i5 * 1000, paramArrayOfByte, leading3(i5, paramArrayOfByte, paramInt2));
      }
    }
    int i;
    label133:
    label169:
    int k;
    int m;
    int n;
    if (paramInt1 >= BILLION)
    {
      i = 1;
      if (i != 0)
      {
        paramInt1 -= BILLION;
        if (paramInt1 < BILLION) {
          break label242;
        }
        paramInt1 -= BILLION;
        int i4 = paramInt2 + 1;
        paramArrayOfByte[paramInt2] = 50;
        paramInt2 = i4;
      }
      int j = paramInt1 / 1000;
      k = paramInt1 - j * 1000;
      m = j / 1000;
      n = j - m * 1000;
      if (i == 0) {
        break label258;
      }
    }
    label242:
    label258:
    for (int i1 = full3(m, paramArrayOfByte, paramInt2);; i1 = leading3(m, paramArrayOfByte, paramInt2))
    {
      i2 = full3(k, paramArrayOfByte, full3(n, paramArrayOfByte, i1));
      break;
      i = 0;
      break label133;
      int i3 = paramInt2 + 1;
      paramArrayOfByte[paramInt2] = 49;
      paramInt2 = i3;
      break label169;
    }
  }
  
  public static int outputInt(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    int i2;
    if (paramInt1 < 0)
    {
      if (paramInt1 == Integer.MIN_VALUE)
      {
        i2 = outputLong(paramInt1, paramArrayOfChar, paramInt2);
        return i2;
      }
      int i8 = paramInt2 + 1;
      paramArrayOfChar[paramInt2] = '-';
      paramInt1 = -paramInt1;
      paramInt2 = i8;
    }
    if (paramInt1 < MILLION)
    {
      int i6;
      if (paramInt1 < 1000) {
        if (paramInt1 < 10)
        {
          int i7 = paramInt2 + 1;
          paramArrayOfChar[paramInt2] = ((char)(paramInt1 + 48));
          i6 = i7;
        }
      }
      for (;;)
      {
        i2 = i6;
        break;
        i6 = leading3(paramInt1, paramArrayOfChar, paramInt2);
        continue;
        int i5 = paramInt1 / 1000;
        i6 = full3(paramInt1 - i5 * 1000, paramArrayOfChar, leading3(i5, paramArrayOfChar, paramInt2));
      }
    }
    int i;
    label133:
    label169:
    int k;
    int m;
    int n;
    if (paramInt1 >= BILLION)
    {
      i = 1;
      if (i != 0)
      {
        paramInt1 -= BILLION;
        if (paramInt1 < BILLION) {
          break label242;
        }
        paramInt1 -= BILLION;
        int i4 = paramInt2 + 1;
        paramArrayOfChar[paramInt2] = '2';
        paramInt2 = i4;
      }
      int j = paramInt1 / 1000;
      k = paramInt1 - j * 1000;
      m = j / 1000;
      n = j - m * 1000;
      if (i == 0) {
        break label258;
      }
    }
    label242:
    label258:
    for (int i1 = full3(m, paramArrayOfChar, paramInt2);; i1 = leading3(m, paramArrayOfChar, paramInt2))
    {
      i2 = full3(k, paramArrayOfChar, full3(n, paramArrayOfChar, i1));
      break;
      i = 0;
      break label133;
      int i3 = paramInt2 + 1;
      paramArrayOfChar[paramInt2] = '1';
      paramInt2 = i3;
      break label169;
    }
  }
  
  public static int outputLong(long paramLong, byte[] paramArrayOfByte, int paramInt)
  {
    int i;
    if (paramLong < 0L) {
      if (paramLong > MIN_INT_AS_LONG) {
        i = outputInt((int)paramLong, paramArrayOfByte, paramInt);
      }
    }
    for (;;)
    {
      return i;
      if (paramLong == Long.MIN_VALUE)
      {
        int i3 = SMALLEST_LONG.length();
        int i4 = 0;
        int i5;
        for (i = paramInt; i4 < i3; i = i5)
        {
          i5 = i + 1;
          paramArrayOfByte[i] = ((byte)SMALLEST_LONG.charAt(i4));
          i4++;
        }
      }
      else
      {
        int i2 = paramInt + 1;
        paramArrayOfByte[paramInt] = 45;
        paramLong = -paramLong;
        paramInt = i2;
        int j;
        int k;
        int m;
        do
        {
          j = paramInt;
          k = paramInt + calcLongStrLength(paramLong);
          m = k;
          while (paramLong > MAX_INT_AS_LONG)
          {
            m -= 3;
            long l = paramLong / THOUSAND_L;
            full3((int)(paramLong - l * THOUSAND_L), paramArrayOfByte, m);
            paramLong = l;
          }
        } while (paramLong > MAX_INT_AS_LONG);
        i = outputInt((int)paramLong, paramArrayOfByte, paramInt);
        continue;
        int i1;
        for (int n = (int)paramLong; n >= 1000; n = i1)
        {
          m -= 3;
          i1 = n / 1000;
          full3(n - i1 * 1000, paramArrayOfByte, m);
        }
        leading3(n, paramArrayOfByte, j);
        i = k;
      }
    }
  }
  
  public static int outputLong(long paramLong, char[] paramArrayOfChar, int paramInt)
  {
    int i;
    if (paramLong < 0L) {
      if (paramLong > MIN_INT_AS_LONG) {
        i = outputInt((int)paramLong, paramArrayOfChar, paramInt);
      }
    }
    for (;;)
    {
      return i;
      if (paramLong == Long.MIN_VALUE)
      {
        int i3 = SMALLEST_LONG.length();
        SMALLEST_LONG.getChars(0, i3, paramArrayOfChar, paramInt);
        i = paramInt + i3;
      }
      else
      {
        int i2 = paramInt + 1;
        paramArrayOfChar[paramInt] = '-';
        paramLong = -paramLong;
        paramInt = i2;
        int j;
        int k;
        int m;
        do
        {
          j = paramInt;
          k = paramInt + calcLongStrLength(paramLong);
          m = k;
          while (paramLong > MAX_INT_AS_LONG)
          {
            m -= 3;
            long l = paramLong / THOUSAND_L;
            full3((int)(paramLong - l * THOUSAND_L), paramArrayOfChar, m);
            paramLong = l;
          }
        } while (paramLong > MAX_INT_AS_LONG);
        i = outputInt((int)paramLong, paramArrayOfChar, paramInt);
        continue;
        int i1;
        for (int n = (int)paramLong; n >= 1000; n = i1)
        {
          m -= 3;
          i1 = n / 1000;
          full3(n - i1 * 1000, paramArrayOfChar, m);
        }
        leading3(n, paramArrayOfChar, j);
        i = k;
      }
    }
  }
  
  public static String toString(double paramDouble)
  {
    return Double.toString(paramDouble);
  }
  
  public static String toString(float paramFloat)
  {
    return Float.toString(paramFloat);
  }
  
  public static String toString(int paramInt)
  {
    String str;
    if (paramInt < sSmallIntStrs.length) {
      if (paramInt >= 0) {
        str = sSmallIntStrs[paramInt];
      }
    }
    for (;;)
    {
      return str;
      int i = -1 + -paramInt;
      if (i < sSmallIntStrs2.length) {
        str = sSmallIntStrs2[i];
      } else {
        str = Integer.toString(paramInt);
      }
    }
  }
  
  public static String toString(long paramLong)
  {
    if ((paramLong <= 2147483647L) && (paramLong >= -2147483648L)) {}
    for (String str = toString((int)paramLong);; str = Long.toString(paramLong)) {
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/io/NumberOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */