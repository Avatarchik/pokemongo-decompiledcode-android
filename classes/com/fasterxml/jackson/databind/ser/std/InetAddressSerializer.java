package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.net.InetAddress;

public class InetAddressSerializer
  extends StdScalarSerializer<InetAddress>
{
  public InetAddressSerializer()
  {
    super(InetAddress.class);
  }
  
  public void serialize(InetAddress paramInetAddress, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    String str = paramInetAddress.toString().trim();
    int i = str.indexOf('/');
    if (i >= 0) {
      if (i != 0) {
        break label43;
      }
    }
    label43:
    for (str = str.substring(1);; str = str.substring(0, i))
    {
      paramJsonGenerator.writeString(str);
      return;
    }
  }
  
  public void serializeWithType(InetAddress paramInetAddress, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForScalar(paramInetAddress, paramJsonGenerator, InetAddress.class);
    serialize(paramInetAddress, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(paramInetAddress, paramJsonGenerator);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/InetAddressSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */