package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InetSocketAddressSerializer
  extends StdScalarSerializer<InetSocketAddress>
{
  public InetSocketAddressSerializer()
  {
    super(InetSocketAddress.class);
  }
  
  public void serialize(InetSocketAddress paramInetSocketAddress, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    InetAddress localInetAddress = paramInetSocketAddress.getAddress();
    String str;
    int i;
    if (localInetAddress == null)
    {
      str = paramInetSocketAddress.getHostName();
      i = str.indexOf('/');
      if (i >= 0)
      {
        if (i != 0) {
          break label131;
        }
        if (!(localInetAddress instanceof Inet6Address)) {
          break label120;
        }
        str = "[" + str.substring(1) + "]";
      }
    }
    for (;;)
    {
      paramJsonGenerator.writeString(str + ":" + paramInetSocketAddress.getPort());
      return;
      str = localInetAddress.toString().trim();
      break;
      label120:
      str = str.substring(1);
      continue;
      label131:
      str = str.substring(0, i);
    }
  }
  
  public void serializeWithType(InetSocketAddress paramInetSocketAddress, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForScalar(paramInetSocketAddress, paramJsonGenerator, InetSocketAddress.class);
    serialize(paramInetSocketAddress, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(paramInetSocketAddress, paramJsonGenerator);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/InetSocketAddressSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */