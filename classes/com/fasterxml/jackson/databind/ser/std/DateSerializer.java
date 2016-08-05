package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@JacksonStdImpl
public class DateSerializer
  extends DateTimeSerializerBase<Date>
{
  public static final DateSerializer instance = new DateSerializer();
  
  public DateSerializer()
  {
    this(null, null);
  }
  
  public DateSerializer(Boolean paramBoolean, DateFormat paramDateFormat)
  {
    super(Date.class, paramBoolean, paramDateFormat);
  }
  
  protected long _timestamp(Date paramDate)
  {
    if (paramDate == null) {}
    for (long l = 0L;; l = paramDate.getTime()) {
      return l;
    }
  }
  
  public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (_asTimestamp(paramSerializerProvider)) {
      paramJsonGenerator.writeNumber(_timestamp(paramDate));
    }
    for (;;)
    {
      return;
      if (this._customFormat != null) {
        synchronized (this._customFormat)
        {
          paramJsonGenerator.writeString(this._customFormat.format(paramDate));
        }
      }
      paramSerializerProvider.defaultSerializeDateValue(paramDate, paramJsonGenerator);
    }
  }
  
  public DateSerializer withFormat(Boolean paramBoolean, DateFormat paramDateFormat)
  {
    return new DateSerializer(paramBoolean, paramDateFormat);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/DateSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */