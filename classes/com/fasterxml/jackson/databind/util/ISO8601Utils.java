package com.fasterxml.jackson.databind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class ISO8601Utils
{
  private static final String GMT_ID = "GMT";
  private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone("GMT");
  private static final TimeZone TIMEZONE_Z = TIMEZONE_GMT;
  
  private static boolean checkOffset(String paramString, int paramInt, char paramChar)
  {
    if ((paramInt < paramString.length()) && (paramString.charAt(paramInt) == paramChar)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static String format(Date paramDate)
  {
    return format(paramDate, false, TIMEZONE_GMT);
  }
  
  public static String format(Date paramDate, boolean paramBoolean)
  {
    return format(paramDate, paramBoolean, TIMEZONE_GMT);
  }
  
  public static String format(Date paramDate, boolean paramBoolean, TimeZone paramTimeZone)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramTimeZone, Locale.US);
    localGregorianCalendar.setTime(paramDate);
    int i = "yyyy-MM-ddThh:mm:ss".length();
    int j;
    int m;
    label56:
    StringBuilder localStringBuilder;
    char c;
    if (paramBoolean)
    {
      j = ".sss".length();
      int k = i + j;
      if (paramTimeZone.getRawOffset() != 0) {
        break label335;
      }
      m = "Z".length();
      localStringBuilder = new StringBuilder(k + m);
      padInt(localStringBuilder, localGregorianCalendar.get(1), "yyyy".length());
      localStringBuilder.append('-');
      padInt(localStringBuilder, 1 + localGregorianCalendar.get(2), "MM".length());
      localStringBuilder.append('-');
      padInt(localStringBuilder, localGregorianCalendar.get(5), "dd".length());
      localStringBuilder.append('T');
      padInt(localStringBuilder, localGregorianCalendar.get(11), "hh".length());
      localStringBuilder.append(':');
      padInt(localStringBuilder, localGregorianCalendar.get(12), "mm".length());
      localStringBuilder.append(':');
      padInt(localStringBuilder, localGregorianCalendar.get(13), "ss".length());
      if (paramBoolean)
      {
        localStringBuilder.append('.');
        padInt(localStringBuilder, localGregorianCalendar.get(14), "sss".length());
      }
      int n = paramTimeZone.getOffset(localGregorianCalendar.getTimeInMillis());
      if (n == 0) {
        break label352;
      }
      int i1 = Math.abs(n / 60000 / 60);
      int i2 = Math.abs(n / 60000 % 60);
      if (n >= 0) {
        break label345;
      }
      c = '-';
      label283:
      localStringBuilder.append(c);
      padInt(localStringBuilder, i1, "hh".length());
      localStringBuilder.append(':');
      padInt(localStringBuilder, i2, "mm".length());
    }
    for (;;)
    {
      return localStringBuilder.toString();
      j = 0;
      break;
      label335:
      m = "+hh:mm".length();
      break label56;
      label345:
      c = '+';
      break label283;
      label352:
      localStringBuilder.append('Z');
    }
  }
  
  private static void padInt(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    String str = Integer.toString(paramInt1);
    for (int i = paramInt2 - str.length(); i > 0; i--) {
      paramStringBuilder.append('0');
    }
    paramStringBuilder.append(str);
  }
  
  public static Date parse(String paramString, ParsePosition paramParsePosition)
    throws ParseException
  {
    try
    {
      int i = paramParsePosition.getIndex();
      int j = i + 4;
      k = parseInt(paramString, i, j);
      if (checkOffset(paramString, j, '-')) {
        j++;
      }
      m = j + 2;
      n = parseInt(paramString, j, m);
      if (checkOffset(paramString, m, '-'))
      {
        i1 = m + 1;
        i2 = i1 + 2;
        i3 = parseInt(paramString, i1, i2);
        i4 = 0;
        i5 = 0;
        i6 = 0;
        i7 = 0;
        boolean bool = checkOffset(paramString, i2, 'T');
        if ((!bool) && (paramString.length() <= i2))
        {
          GregorianCalendar localGregorianCalendar2 = new GregorianCalendar(k, n - 1, i3);
          paramParsePosition.setIndex(i2);
          localDate = localGregorianCalendar2.getTime();
          break label814;
        }
        if (bool)
        {
          int i9 = i2 + 1;
          int i10 = i9 + 2;
          i4 = parseInt(paramString, i9, i10);
          if (checkOffset(paramString, i10, ':')) {
            i10++;
          }
          i11 = i10 + 2;
          i5 = parseInt(paramString, i10, i11);
          if (!checkOffset(paramString, i11, ':')) {
            break label800;
          }
          i12 = i11 + 1;
          if (paramString.length() <= i12) {
            break label793;
          }
          int i13 = paramString.charAt(i12);
          if ((i13 == 90) || (i13 == 43) || (i13 == 45)) {
            break label793;
          }
          i2 = i12 + 2;
          i6 = parseInt(paramString, i12, i2);
          if (checkOffset(paramString, i2, '.'))
          {
            int i14 = i2 + 1;
            int i15 = i14 + 3;
            i7 = parseInt(paramString, i14, i15);
            i2 = i15;
          }
        }
        if (paramString.length() <= i2) {
          throw new IllegalArgumentException("No time zone indicator");
        }
      }
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      int k;
      int n;
      int i3;
      int i4;
      int i5;
      int i6;
      int i7;
      localObject = localIndexOutOfBoundsException;
      if (paramString == null)
      {
        str1 = null;
        String str2 = ((Exception)localObject).getMessage();
        if ((str2 == null) || (str2.isEmpty())) {
          str2 = "(" + localObject.getClass().getName() + ")";
        }
        ParseException localParseException = new ParseException("Failed to parse date [" + str1 + "]: " + str2, paramParsePosition.getIndex());
        localParseException.initCause((Throwable)localObject);
        throw localParseException;
        c = paramString.charAt(i2);
        if (c != 'Z') {
          break label817;
        }
        TimeZone localTimeZone = TIMEZONE_Z;
        int i8 = i2 + 1;
        String str4;
        String str5;
        do
        {
          String str3;
          for (;;)
          {
            GregorianCalendar localGregorianCalendar1 = new GregorianCalendar(localTimeZone);
            localGregorianCalendar1.setLenient(false);
            localGregorianCalendar1.set(1, k);
            localGregorianCalendar1.set(2, n - 1);
            localGregorianCalendar1.set(5, i3);
            localGregorianCalendar1.set(11, i4);
            localGregorianCalendar1.set(12, i5);
            localGregorianCalendar1.set(13, i6);
            localGregorianCalendar1.set(14, i7);
            paramParsePosition.setIndex(i8);
            localDate = localGregorianCalendar1.getTime();
            break label814;
            str3 = paramString.substring(i2);
            i8 = i2 + str3.length();
            if ((!"+0000".equals(str3)) && (!"+00:00".equals(str3))) {
              break;
            }
            localTimeZone = TIMEZONE_Z;
          }
          str4 = "GMT" + str3;
          localTimeZone = TimeZone.getTimeZone(str4);
          str5 = localTimeZone.getID();
        } while ((str5.equals(str4)) || (str5.replace(":", "").equals(str4)));
        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str4 + " given, resolves to " + localTimeZone.getID());
        throw new IndexOutOfBoundsException("Invalid time zone indicator '" + c + "'");
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        int m;
        Date localDate;
        int i11;
        char c;
        localObject = localIllegalArgumentException;
        continue;
        String str1 = '"' + paramString + "'";
        continue;
        int i2 = i12;
        continue;
        int i12 = i11;
        continue;
        int i1 = m;
        continue;
        return localDate;
        if (c != '+') {
          if (c != '-') {}
        }
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        label793:
        label800:
        label814:
        label817:
        Object localObject = localNumberFormatException;
      }
    }
  }
  
  private static int parseInt(String paramString, int paramInt1, int paramInt2)
    throws NumberFormatException
  {
    if ((paramInt1 < 0) || (paramInt2 > paramString.length()) || (paramInt1 > paramInt2)) {
      throw new NumberFormatException(paramString);
    }
    int i = 0;
    int j;
    if (paramInt1 < paramInt2)
    {
      j = paramInt1 + 1;
      int n = Character.digit(paramString.charAt(paramInt1), 10);
      if (n < 0) {
        throw new NumberFormatException("Invalid number: " + paramString.substring(paramInt1, paramInt2));
      }
      i = -n;
    }
    for (;;)
    {
      if (j < paramInt2)
      {
        int k = j + 1;
        int m = Character.digit(paramString.charAt(j), 10);
        if (m < 0) {
          throw new NumberFormatException("Invalid number: " + paramString.substring(paramInt1, paramInt2));
        }
        i = i * 10 - m;
        j = k;
      }
      else
      {
        return -i;
        j = paramInt1;
      }
    }
  }
  
  @Deprecated
  public static TimeZone timeZoneGMT()
  {
    return TIMEZONE_GMT;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/ISO8601Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */