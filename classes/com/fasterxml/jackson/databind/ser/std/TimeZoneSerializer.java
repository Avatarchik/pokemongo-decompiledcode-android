package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.TimeZone;

public class TimeZoneSerializer
  extends StdScalarSerializer<TimeZone>
{
  public TimeZoneSerializer()
  {
    super(TimeZone.class);
  }
  
  public void serialize(TimeZone paramTimeZone, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonGenerator.writeString(paramTimeZone.getID());
  }
  
  public void serializeWithType(TimeZone paramTimeZone, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramTypeSerializer.writeTypePrefixForScalar(paramTimeZone, paramJsonGenerator, TimeZone.class);
    serialize(paramTimeZone, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(paramTimeZone, paramJsonGenerator);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/TimeZoneSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */