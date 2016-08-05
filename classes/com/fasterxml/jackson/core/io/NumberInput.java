package com.fasterxml.jackson.core.io;

import java.math.BigDecimal;

public final class NumberInput
{
  static final long L_BILLION = 1000000000L;
  static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);
  static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
  public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
  
  private static NumberFormatException _badBD(String paramString)
  {
    return new NumberFormatException("Value \"" + paramString + "\" can not be represented as BigDecimal");
  }
  
  public static boolean inLongRange(String paramString, boolean paramBoolean)
  {
    boolean bool = true;
    String str;
    int i;
    int j;
    if (paramBoolean)
    {
      str = MIN_LONG_STR_NO_SIGN;
      i = str.length();
      j = paramString.length();
      if (j >= i) {
        break label38;
      }
    }
    label38:
    label94:
    for (;;)
    {
      return bool;
      str = MAX_LONG_STR;
      break;
      if (j > i) {
        bool = false;
      } else {
        for (int k = 0;; k++)
        {
          if (k >= i) {
            break label94;
          }
          int m = paramString.charAt(k) - str.charAt(k);
          if (m != 0)
          {
            if (m < 0) {
              break;
            }
            bool = false;
            break;
          }
        }
      }
    }
  }
  
  public static boolean inLongRange(char[] paramArrayOfChar, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    boolean bool = true;
    String str;
    int i;
    if (paramBoolean)
    {
      str = MIN_LONG_STR_NO_SIGN;
      i = str.length();
      if (paramInt2 >= i) {
        break label36;
      }
    }
    label36:
    label94:
    for (;;)
    {
      return bool;
      str = MAX_LONG_STR;
      break;
      if (paramInt2 > i) {
        bool = false;
      } else {
        for (int j = 0;; j++)
        {
          if (j >= i) {
            break label94;
          }
          int k = paramArrayOfChar[(paramInt1 + j)] - str.charAt(j);
          if (k != 0)
          {
            if (k < 0) {
              break;
            }
            bool = false;
            break;
          }
        }
      }
    }
  }
  
  public static double parseAsDouble(String paramString, double paramDouble)
  {
    if (paramString == null) {}
    for (;;)
    {
      return paramDouble;
      String str = paramString.trim();
      if (str.length() != 0) {
        try
        {
          double d = parseDouble(str);
          paramDouble = d;
        }
        catch (NumberFormatException localNumberFormatException) {}
      }
    }
  }
  
  public static int parseAsInt(String paramString, int paramInt)
  {
    if (paramString == null) {}
    for (;;)
    {
      return paramInt;
      String str = paramString.trim();
      int i = str.length();
      if (i != 0)
      {
        int j = 0;
        int n;
        if (i < 0)
        {
          n = str.charAt(0);
          if (n != 43) {
            break label93;
          }
          str = str.substring(1);
          i = str.length();
        }
        for (;;)
        {
          if (j >= i) {
            break label119;
          }
          int m = str.charAt(j);
          if ((m > 57) || (m < 48))
          {
            label93:
            try
            {
              double d = parseDouble(str);
              paramInt = (int)d;
            }
            catch (NumberFormatException localNumberFormatException2) {}
            if (n != 45) {
              continue;
            }
            j = 0 + 1;
            continue;
            break;
          }
          j++;
        }
        try
        {
          label119:
          int k = Integer.parseInt(str);
          paramInt = k;
        }
        catch (NumberFormatException localNumberFormatException1) {}
      }
    }
  }
  
  public static long parseAsLong(String paramString, long paramLong)
  {
    if (paramString == null) {}
    for (;;)
    {
      return paramLong;
      String str = paramString.trim();
      int i = str.length();
      if (i != 0)
      {
        int j = 0;
        int m;
        if (i < 0)
        {
          m = str.charAt(0);
          if (m != 43) {
            break label98;
          }
          str = str.substring(1);
          i = str.length();
        }
        for (;;)
        {
          if (j >= i) {
            break label124;
          }
          int k = str.charAt(j);
          if ((k > 57) || (k < 48))
          {
            label98:
            try
            {
              double d = parseDouble(str);
              paramLong = d;
            }
            catch (NumberFormatException localNumberFormatException2) {}
            if (m != 45) {
              continue;
            }
            j = 0 + 1;
            continue;
            break;
          }
          j++;
        }
        try
        {
          label124:
          long l = Long.parseLong(str);
          paramLong = l;
        }
        catch (NumberFormatException localNumberFormatException1) {}
      }
    }
  }
  
  public static BigDecimal parseBigDecimal(String paramString)
    throws NumberFormatException
  {
    try
    {
      BigDecimal localBigDecimal = new BigDecimal(paramString);
      return localBigDecimal;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw _badBD(paramString);
    }
  }
  
  public static BigDecimal parseBigDecimal(char[] paramArrayOfChar)
    throws NumberFormatException
  {
    return parseBigDecimal(paramArrayOfChar, 0, paramArrayOfChar.length);
  }
  
  public static BigDecimal parseBigDecimal(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws NumberFormatException
  {
    try
    {
      BigDecimal localBigDecimal = new BigDecimal(paramArrayOfChar, paramInt1, paramInt2);
      return localBigDecimal;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw _badBD(new String(paramArrayOfChar, paramInt1, paramInt2));
    }
  }
  
  public static double parseDouble(String paramString)
    throws NumberFormatException
  {
    if ("2.2250738585072012e-308".equals(paramString)) {}
    for (double d = Double.MIN_VALUE;; d = Double.parseDouble(paramString)) {
      return d;
    }
  }
  
  public static int parseInt(String paramString)
  {
    int i = 0;
    int j = paramString.charAt(0);
    int k = paramString.length();
    if (j == 45) {
      i = 1;
    }
    int n;
    int m;
    if (i != 0)
    {
      if ((k == 1) || (k > 10))
      {
        n = Integer.parseInt(paramString);
        return n;
      }
      m = 1 + 1;
      j = paramString.charAt(1);
    }
    for (;;)
    {
      if ((j > 57) || (j < 48))
      {
        n = Integer.parseInt(paramString);
        break;
        if (k <= 9) {
          break label300;
        }
        n = Integer.parseInt(paramString);
        break;
      }
      n = j + -48;
      if (m < k)
      {
        int i1 = m + 1;
        int i2 = paramString.charAt(m);
        if ((i2 > 57) || (i2 < 48))
        {
          n = Integer.parseInt(paramString);
          break;
        }
        n = n * 10 + (i2 + -48);
        if (i1 >= k) {
          break label288;
        }
        m = i1 + 1;
        int i3 = paramString.charAt(i1);
        if ((i3 > 57) || (i3 < 48))
        {
          n = Integer.parseInt(paramString);
          break;
        }
        n = n * 10 + (i3 + -48);
        if (m < k) {
          do
          {
            int i4 = m;
            m = i4 + 1;
            int i5 = paramString.charAt(i4);
            if ((i5 > 57) || (i5 < 48))
            {
              n = Integer.parseInt(paramString);
              break;
            }
            n = n * 10 + (i5 + -48);
          } while (m < k);
        }
      }
      label288:
      if (i == 0) {
        break;
      }
      n = -n;
      break;
      label300:
      m = 1;
    }
  }
  
  public static int parseInt(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = '￐' + paramArrayOfChar[paramInt1];
    int i13;
    int i14;
    if (paramInt2 > 4)
    {
      int i2 = i * 10;
      int i3 = paramInt1 + 1;
      int i4 = 10 * (i2 + ('￐' + paramArrayOfChar[i3]));
      int i5 = i3 + 1;
      int i6 = 10 * (i4 + ('￐' + paramArrayOfChar[i5]));
      int i7 = i5 + 1;
      int i8 = 10 * (i6 + ('￐' + paramArrayOfChar[i7]));
      paramInt1 = i7 + 1;
      i = i8 + ('￐' + paramArrayOfChar[paramInt1]);
      paramInt2 -= 4;
      if (paramInt2 > 4)
      {
        int i9 = i * 10;
        int i10 = paramInt1 + 1;
        int i11 = 10 * (i9 + ('￐' + paramArrayOfChar[i10]));
        int i12 = i10 + 1;
        i13 = 10 * (i11 + ('￐' + paramArrayOfChar[i12]));
        i14 = i12 + 1;
      }
    }
    for (int j = 10 * (i13 + ('￐' + paramArrayOfChar[i14])) + ('￐' + paramArrayOfChar[(i14 + 1)]);; j = i)
    {
      return j;
      if (paramInt2 > 1)
      {
        int k = i * 10;
        int m = paramInt1 + 1;
        i = k + ('￐' + paramArrayOfChar[m]);
        if (paramInt2 > 2)
        {
          int n = i * 10;
          int i1 = m + 1;
          i = n + ('￐' + paramArrayOfChar[i1]);
          if (paramInt2 > 3) {
            i = i * 10 + ('￐' + paramArrayOfChar[(i1 + 1)]);
          }
        }
      }
    }
  }
  
  public static long parseLong(String paramString)
  {
    if (paramString.length() <= 9) {}
    for (long l = parseInt(paramString);; l = Long.parseLong(paramString)) {
      return l;
    }
  }
  
  public static long parseLong(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = paramInt2 - 9;
    return 1000000000L * parseInt(paramArrayOfChar, paramInt1, i) + parseInt(paramArrayOfChar, paramInt1 + i, 9);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/io/NumberInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */