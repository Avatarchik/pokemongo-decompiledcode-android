package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StdDateFormat
  extends DateFormat
{
  protected static final String[] ALL_FORMATS;
  protected static final DateFormat DATE_FORMAT_ISO8601;
  protected static final DateFormat DATE_FORMAT_ISO8601_Z;
  protected static final DateFormat DATE_FORMAT_PLAIN;
  protected static final DateFormat DATE_FORMAT_RFC1123;
  protected static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
  protected static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
  protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
  private static final Locale DEFAULT_LOCALE;
  private static final TimeZone DEFAULT_TIMEZONE;
  public static final StdDateFormat instance = new StdDateFormat();
  protected transient DateFormat _formatISO8601;
  protected transient DateFormat _formatISO8601_z;
  protected transient DateFormat _formatPlain;
  protected transient DateFormat _formatRFC1123;
  protected final Locale _locale;
  protected transient TimeZone _timezone;
  
  static
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    arrayOfString[1] = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    arrayOfString[2] = "EEE, dd MMM yyyy HH:mm:ss zzz";
    arrayOfString[3] = "yyyy-MM-dd";
    ALL_FORMATS = arrayOfString;
    DEFAULT_TIMEZONE = TimeZone.getTimeZone("GMT");
    DEFAULT_LOCALE = Locale.US;
    DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", DEFAULT_LOCALE);
    DATE_FORMAT_RFC1123.setTimeZone(DEFAULT_TIMEZONE);
    DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", DEFAULT_LOCALE);
    DATE_FORMAT_ISO8601.setTimeZone(DEFAULT_TIMEZONE);
    DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", DEFAULT_LOCALE);
    DATE_FORMAT_ISO8601_Z.setTimeZone(DEFAULT_TIMEZONE);
    DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd", DEFAULT_LOCALE);
    DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
  }
  
  public StdDateFormat()
  {
    this._locale = DEFAULT_LOCALE;
  }
  
  @Deprecated
  public StdDateFormat(TimeZone paramTimeZone)
  {
    this(paramTimeZone, DEFAULT_LOCALE);
  }
  
  public StdDateFormat(TimeZone paramTimeZone, Locale paramLocale)
  {
    this._timezone = paramTimeZone;
    this._locale = paramLocale;
  }
  
  private static final DateFormat _cloneFormat(DateFormat paramDateFormat, String paramString, TimeZone paramTimeZone, Locale paramLocale)
  {
    Object localObject;
    if (!paramLocale.equals(DEFAULT_LOCALE))
    {
      localObject = new SimpleDateFormat(paramString, paramLocale);
      if (paramTimeZone == null) {
        paramTimeZone = DEFAULT_TIMEZONE;
      }
      ((DateFormat)localObject).setTimeZone(paramTimeZone);
    }
    for (;;)
    {
      return (DateFormat)localObject;
      localObject = (DateFormat)paramDateFormat.clone();
      if (paramTimeZone != null) {
        ((DateFormat)localObject).setTimeZone(paramTimeZone);
      }
    }
  }
  
  @Deprecated
  public static DateFormat getBlueprintISO8601Format()
  {
    return DATE_FORMAT_ISO8601;
  }
  
  @Deprecated
  public static DateFormat getBlueprintRFC1123Format()
  {
    return DATE_FORMAT_RFC1123;
  }
  
  public static TimeZone getDefaultTimeZone()
  {
    return DEFAULT_TIMEZONE;
  }
  
  @Deprecated
  public static DateFormat getISO8601Format(TimeZone paramTimeZone)
  {
    return getISO8601Format(paramTimeZone, DEFAULT_LOCALE);
  }
  
  public static DateFormat getISO8601Format(TimeZone paramTimeZone, Locale paramLocale)
  {
    return _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", paramTimeZone, paramLocale);
  }
  
  @Deprecated
  public static DateFormat getRFC1123Format(TimeZone paramTimeZone)
  {
    return getRFC1123Format(paramTimeZone, DEFAULT_LOCALE);
  }
  
  public static DateFormat getRFC1123Format(TimeZone paramTimeZone, Locale paramLocale)
  {
    return _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", paramTimeZone, paramLocale);
  }
  
  private static final boolean hasTimeZone(String paramString)
  {
    boolean bool = true;
    int i = paramString.length();
    if (i >= 6)
    {
      int j = paramString.charAt(i - 6);
      if ((j != 43) && (j != 45)) {}
    }
    for (;;)
    {
      return bool;
      int k = paramString.charAt(i - 5);
      if ((k != 43) && (k != 45))
      {
        int m = paramString.charAt(i - 3);
        if ((m != 43) && (m != 45)) {
          bool = false;
        }
      }
    }
  }
  
  public StdDateFormat clone()
  {
    return new StdDateFormat(this._timezone, this._locale);
  }
  
  public StringBuffer format(Date paramDate, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition)
  {
    if (this._formatISO8601 == null) {
      this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", this._timezone, this._locale);
    }
    return this._formatISO8601.format(paramDate, paramStringBuffer, paramFieldPosition);
  }
  
  public TimeZone getTimeZone()
  {
    return this._timezone;
  }
  
  protected boolean looksLikeISO8601(String paramString)
  {
    boolean bool = false;
    if ((paramString.length() >= 5) && (Character.isDigit(paramString.charAt(0))) && (Character.isDigit(paramString.charAt(3))) && (paramString.charAt(4) == '-')) {
      bool = true;
    }
    return bool;
  }
  
  public Date parse(String paramString)
    throws ParseException
  {
    String str1 = paramString.trim();
    ParsePosition localParsePosition = new ParsePosition(0);
    Date localDate = parse(str1, localParsePosition);
    if (localDate != null) {
      return localDate;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = ALL_FORMATS;
    int i = arrayOfString.length;
    int j = 0;
    if (j < i)
    {
      String str2 = arrayOfString[j];
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append("\", \"");
      }
      for (;;)
      {
        localStringBuilder.append(str2);
        j++;
        break;
        localStringBuilder.append('"');
      }
    }
    localStringBuilder.append('"');
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = str1;
    arrayOfObject[1] = localStringBuilder.toString();
    throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", arrayOfObject), localParsePosition.getErrorIndex());
  }
  
  public Date parse(String paramString, ParsePosition paramParsePosition)
  {
    Date localDate;
    if (looksLikeISO8601(paramString)) {
      localDate = parseAsISO8601(paramString, paramParsePosition);
    }
    for (;;)
    {
      return localDate;
      int i = paramString.length();
      int j;
      do
      {
        i--;
        if (i < 0) {
          break;
        }
        j = paramString.charAt(i);
      } while (((j >= 48) && (j <= 57)) || ((i <= 0) && (j == 45)));
      if ((i < 0) && ((paramString.charAt(0) == '-') || (NumberInput.inLongRange(paramString, false)))) {
        localDate = new Date(Long.parseLong(paramString));
      } else {
        localDate = parseAsRFC1123(paramString, paramParsePosition);
      }
    }
  }
  
  protected Date parseAsISO8601(String paramString, ParsePosition paramParsePosition)
  {
    int i = paramString.length();
    char c = paramString.charAt(i - 1);
    DateFormat localDateFormat;
    if ((i <= 10) && (Character.isDigit(c)))
    {
      localDateFormat = this._formatPlain;
      if (localDateFormat == null)
      {
        localDateFormat = _cloneFormat(DATE_FORMAT_PLAIN, "yyyy-MM-dd", this._timezone, this._locale);
        this._formatPlain = localDateFormat;
      }
    }
    for (;;)
    {
      return localDateFormat.parse(paramString, paramParsePosition);
      if (c != 'Z') {
        break;
      }
      localDateFormat = this._formatISO8601_z;
      if (localDateFormat == null)
      {
        localDateFormat = _cloneFormat(DATE_FORMAT_ISO8601_Z, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", this._timezone, this._locale);
        this._formatISO8601_z = localDateFormat;
      }
      if (paramString.charAt(i - 4) == ':')
      {
        StringBuilder localStringBuilder4 = new StringBuilder(paramString);
        localStringBuilder4.insert(i - 1, ".000");
        paramString = localStringBuilder4.toString();
      }
    }
    if (hasTimeZone(paramString))
    {
      int k = paramString.charAt(i - 3);
      label206:
      int i1;
      StringBuilder localStringBuilder3;
      if (k == 58)
      {
        StringBuilder localStringBuilder2 = new StringBuilder(paramString);
        localStringBuilder2.delete(i - 3, i - 2);
        paramString = localStringBuilder2.toString();
        int m = paramString.length();
        int n = -6 + (m - paramString.lastIndexOf('T'));
        if (n < 12)
        {
          i1 = m - 5;
          localStringBuilder3 = new StringBuilder(paramString);
          switch (n)
          {
          }
        }
      }
      for (;;)
      {
        paramString = localStringBuilder3.toString();
        localDateFormat = this._formatISO8601;
        if (this._formatISO8601 != null) {
          break;
        }
        localDateFormat = _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", this._timezone, this._locale);
        this._formatISO8601 = localDateFormat;
        break;
        if ((k != 43) && (k != 45)) {
          break label206;
        }
        paramString = paramString + "00";
        break label206;
        localStringBuilder3.insert(i1, '0');
        continue;
        localStringBuilder3.insert(i1, "00");
        continue;
        localStringBuilder3.insert(i1, "000");
        continue;
        localStringBuilder3.insert(i1, ".000");
        continue;
        localStringBuilder3.insert(i1, "00.000");
        localStringBuilder3.insert(i1, ":00.000");
      }
    }
    StringBuilder localStringBuilder1 = new StringBuilder(paramString);
    int j = -1 + (i - paramString.lastIndexOf('T'));
    if (j < 12) {
      switch (j)
      {
      default: 
        localStringBuilder1.append(".000");
      }
    }
    for (;;)
    {
      localStringBuilder1.append('Z');
      paramString = localStringBuilder1.toString();
      localDateFormat = this._formatISO8601_z;
      if (localDateFormat != null) {
        break;
      }
      localDateFormat = _cloneFormat(DATE_FORMAT_ISO8601_Z, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", this._timezone, this._locale);
      this._formatISO8601_z = localDateFormat;
      break;
      localStringBuilder1.append('0');
      localStringBuilder1.append('0');
      localStringBuilder1.append('0');
    }
  }
  
  protected Date parseAsRFC1123(String paramString, ParsePosition paramParsePosition)
  {
    if (this._formatRFC1123 == null) {
      this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", this._timezone, this._locale);
    }
    return this._formatRFC1123.parse(paramString, paramParsePosition);
  }
  
  public void setTimeZone(TimeZone paramTimeZone)
  {
    if (!paramTimeZone.equals(this._timezone))
    {
      this._formatRFC1123 = null;
      this._formatISO8601 = null;
      this._formatISO8601_z = null;
      this._formatPlain = null;
      this._timezone = paramTimeZone;
    }
  }
  
  public String toString()
  {
    String str = "DateFormat " + getClass().getName();
    TimeZone localTimeZone = this._timezone;
    if (localTimeZone != null) {
      str = str + " (timezone: " + localTimeZone + ")";
    }
    return str + "(locale: " + this._locale + ")";
  }
  
  public StdDateFormat withLocale(Locale paramLocale)
  {
    if (paramLocale.equals(this._locale)) {}
    for (;;)
    {
      return this;
      this = new StdDateFormat(this._timezone, paramLocale);
    }
  }
  
  public StdDateFormat withTimeZone(TimeZone paramTimeZone)
  {
    if (paramTimeZone == null) {
      paramTimeZone = DEFAULT_TIMEZONE;
    }
    if ((paramTimeZone == this._timezone) || (paramTimeZone.equals(this._timezone))) {}
    for (;;)
    {
      return this;
      this = new StdDateFormat(paramTimeZone, this._locale);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/StdDateFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */