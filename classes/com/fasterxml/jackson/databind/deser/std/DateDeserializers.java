package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

public class DateDeserializers
{
  private static final HashSet<String> _classNames = new HashSet();
  
  static
  {
    Class[] arrayOfClass = new Class[5];
    arrayOfClass[0] = Calendar.class;
    arrayOfClass[1] = GregorianCalendar.class;
    arrayOfClass[2] = java.sql.Date.class;
    arrayOfClass[3] = java.util.Date.class;
    arrayOfClass[4] = Timestamp.class;
    int i = arrayOfClass.length;
    for (int j = 0; j < i; j++)
    {
      Class localClass = arrayOfClass[j];
      _classNames.add(localClass.getName());
    }
  }
  
  public static JsonDeserializer<?> find(Class<?> paramClass, String paramString)
  {
    Object localObject;
    if (_classNames.contains(paramString)) {
      if (paramClass == Calendar.class) {
        localObject = new CalendarDeserializer();
      }
    }
    for (;;)
    {
      return (JsonDeserializer<?>)localObject;
      if (paramClass == java.util.Date.class) {
        localObject = DateDeserializer.instance;
      } else if (paramClass == java.sql.Date.class) {
        localObject = new SqlDateDeserializer();
      } else if (paramClass == Timestamp.class) {
        localObject = new TimestampDeserializer();
      } else if (paramClass == GregorianCalendar.class) {
        localObject = new CalendarDeserializer(GregorianCalendar.class);
      } else {
        localObject = null;
      }
    }
  }
  
  public static class TimestampDeserializer
    extends DateDeserializers.DateBasedDeserializer<Timestamp>
  {
    public TimestampDeserializer()
    {
      super();
    }
    
    public TimestampDeserializer(TimestampDeserializer paramTimestampDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super(paramDateFormat, paramString);
    }
    
    public Timestamp deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      return new Timestamp(_parseDate(paramJsonParser, paramDeserializationContext).getTime());
    }
    
    protected TimestampDeserializer withDateFormat(DateFormat paramDateFormat, String paramString)
    {
      return new TimestampDeserializer(this, paramDateFormat, paramString);
    }
  }
  
  public static class SqlDateDeserializer
    extends DateDeserializers.DateBasedDeserializer<java.sql.Date>
  {
    public SqlDateDeserializer()
    {
      super();
    }
    
    public SqlDateDeserializer(SqlDateDeserializer paramSqlDateDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super(paramDateFormat, paramString);
    }
    
    public java.sql.Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      java.util.Date localDate = _parseDate(paramJsonParser, paramDeserializationContext);
      if (localDate == null) {}
      for (java.sql.Date localDate1 = null;; localDate1 = new java.sql.Date(localDate.getTime())) {
        return localDate1;
      }
    }
    
    protected SqlDateDeserializer withDateFormat(DateFormat paramDateFormat, String paramString)
    {
      return new SqlDateDeserializer(this, paramDateFormat, paramString);
    }
  }
  
  public static class DateDeserializer
    extends DateDeserializers.DateBasedDeserializer<java.util.Date>
  {
    public static final DateDeserializer instance = new DateDeserializer();
    
    public DateDeserializer()
    {
      super();
    }
    
    public DateDeserializer(DateDeserializer paramDateDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super(paramDateFormat, paramString);
    }
    
    public java.util.Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      return _parseDate(paramJsonParser, paramDeserializationContext);
    }
    
    protected DateDeserializer withDateFormat(DateFormat paramDateFormat, String paramString)
    {
      return new DateDeserializer(this, paramDateFormat, paramString);
    }
  }
  
  @JacksonStdImpl
  public static class CalendarDeserializer
    extends DateDeserializers.DateBasedDeserializer<Calendar>
  {
    protected final Class<? extends Calendar> _calendarClass;
    
    public CalendarDeserializer()
    {
      super();
      this._calendarClass = null;
    }
    
    public CalendarDeserializer(CalendarDeserializer paramCalendarDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super(paramDateFormat, paramString);
      this._calendarClass = paramCalendarDeserializer._calendarClass;
    }
    
    public CalendarDeserializer(Class<? extends Calendar> paramClass)
    {
      super();
      this._calendarClass = paramClass;
    }
    
    public Calendar deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      java.util.Date localDate = _parseDate(paramJsonParser, paramDeserializationContext);
      Calendar localCalendar;
      if (localDate == null) {
        localCalendar = null;
      }
      for (;;)
      {
        return localCalendar;
        if (this._calendarClass == null)
        {
          localCalendar = paramDeserializationContext.constructCalendar(localDate);
          continue;
        }
        try
        {
          localCalendar = (Calendar)this._calendarClass.newInstance();
          localCalendar.setTimeInMillis(localDate.getTime());
          TimeZone localTimeZone = paramDeserializationContext.getTimeZone();
          if (localTimeZone == null) {
            continue;
          }
          localCalendar.setTimeZone(localTimeZone);
        }
        catch (Exception localException)
        {
          throw paramDeserializationContext.instantiationException(this._calendarClass, localException);
        }
      }
    }
    
    protected CalendarDeserializer withDateFormat(DateFormat paramDateFormat, String paramString)
    {
      return new CalendarDeserializer(this, paramDateFormat, paramString);
    }
  }
  
  protected static abstract class DateBasedDeserializer<T>
    extends StdScalarDeserializer<T>
    implements ContextualDeserializer
  {
    protected final DateFormat _customFormat;
    protected final String _formatString;
    
    protected DateBasedDeserializer(DateBasedDeserializer<T> paramDateBasedDeserializer, DateFormat paramDateFormat, String paramString)
    {
      super();
      this._customFormat = paramDateFormat;
      this._formatString = paramString;
    }
    
    protected DateBasedDeserializer(Class<?> paramClass)
    {
      super();
      this._customFormat = null;
      this._formatString = null;
    }
    
    protected java.util.Date _parseDate(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      JsonToken localJsonToken;
      String str;
      Object localObject1;
      if (this._customFormat != null)
      {
        localJsonToken = paramJsonParser.getCurrentToken();
        if (localJsonToken == JsonToken.VALUE_STRING)
        {
          str = paramJsonParser.getText().trim();
          if (str.length() == 0) {
            localObject1 = (java.util.Date)getEmptyValue(paramDeserializationContext);
          }
        }
      }
      for (;;)
      {
        return (java.util.Date)localObject1;
        try
        {
          synchronized (this._customFormat)
          {
            java.util.Date localDate2 = this._customFormat.parse(str);
            localObject1 = localDate2;
          }
          if (localJsonToken != JsonToken.START_ARRAY) {
            break label199;
          }
        }
        catch (ParseException localParseException)
        {
          throw new IllegalArgumentException("Failed to parse Date value '" + str + "' (format: \"" + this._formatString + "\"): " + localParseException.getMessage());
        }
        if (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))
        {
          paramJsonParser.nextToken();
          java.util.Date localDate1 = _parseDate(paramJsonParser, paramDeserializationContext);
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
            throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'java.util.Date' value but there was more than a single value in the array");
          }
          localObject1 = localDate1;
        }
        else
        {
          label199:
          localObject1 = super._parseDate(paramJsonParser, paramDeserializationContext);
        }
      }
    }
    
    public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
      throws JsonMappingException
    {
      JsonFormat.Value localValue;
      TimeZone localTimeZone;
      if (paramBeanProperty != null)
      {
        localValue = paramDeserializationContext.getAnnotationIntrospector().findFormat(paramBeanProperty.getMember());
        if (localValue != null)
        {
          localTimeZone = localValue.getTimeZone();
          if (!localValue.hasPattern()) {
            break label105;
          }
          str = localValue.getPattern();
          if (!localValue.hasLocale()) {
            break label96;
          }
          localLocale2 = localValue.getLocale();
          localSimpleDateFormat = new SimpleDateFormat(str, localLocale2);
          if (localTimeZone == null) {
            localTimeZone = paramDeserializationContext.getTimeZone();
          }
          localSimpleDateFormat.setTimeZone(localTimeZone);
          this = withDateFormat(localSimpleDateFormat, str);
        }
      }
      label96:
      label105:
      while (localTimeZone == null) {
        for (;;)
        {
          String str;
          SimpleDateFormat localSimpleDateFormat;
          return this;
          Locale localLocale2 = paramDeserializationContext.getLocale();
        }
      }
      DateFormat localDateFormat = paramDeserializationContext.getConfig().getDateFormat();
      Locale localLocale1;
      label142:
      Object localObject;
      if (localDateFormat.getClass() == StdDateFormat.class) {
        if (localValue.hasLocale())
        {
          localLocale1 = localValue.getLocale();
          localObject = ((StdDateFormat)localDateFormat).withTimeZone(localTimeZone).withLocale(localLocale1);
        }
      }
      for (;;)
      {
        this = withDateFormat((DateFormat)localObject, this._formatString);
        break;
        localLocale1 = paramDeserializationContext.getLocale();
        break label142;
        localObject = (DateFormat)localDateFormat.clone();
        ((DateFormat)localObject).setTimeZone(localTimeZone);
      }
    }
    
    protected abstract DateBasedDeserializer<T> withDateFormat(DateFormat paramDateFormat, String paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/DateDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */