package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

@JacksonStdImpl
public class CalendarSerializer
  extends DateTimeSerializerBase<Calendar>
{
  public static final CalendarSerializer instance = new CalendarSerializer();
  
  public CalendarSerializer()
  {
    this(null, null);
  }
  
  public CalendarSerializer(Boolean paramBoolean, DateFormat paramDateFormat)
  {
    super(Calendar.class, paramBoolean, paramDateFormat);
  }
  
  protected long _timestamp(Calendar paramCalendar)
  {
    if (paramCalendar == null) {}
    for (long l = 0L;; l = paramCalendar.getTimeInMillis()) {
      return l;
    }
  }
  
  public void serialize(Calendar paramCalendar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (_asTimestamp(paramSerializerProvider)) {
      paramJsonGenerator.writeNumber(_timestamp(paramCalendar));
    }
    for (;;)
    {
      return;
      if (this._customFormat != null) {
        synchronized (this._customFormat)
        {
          paramJsonGenerator.writeString(this._customFormat.format(paramCalendar.getTime()));
        }
      }
      paramSerializerProvider.defaultSerializeDateValue(paramCalendar.getTime(), paramJsonGenerator);
    }
  }
  
  public CalendarSerializer withFormat(Boolean paramBoolean, DateFormat paramDateFormat)
  {
    return new CalendarSerializer(paramBoolean, paramDateFormat);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/CalendarSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */