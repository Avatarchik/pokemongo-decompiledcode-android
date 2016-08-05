package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferSerializer
  extends StdScalarSerializer<ByteBuffer>
{
  public ByteBufferSerializer()
  {
    super(ByteBuffer.class);
  }
  
  public void serialize(ByteBuffer paramByteBuffer, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (paramByteBuffer.hasArray()) {
      paramJsonGenerator.writeBinary(paramByteBuffer.array(), 0, paramByteBuffer.limit());
    }
    for (;;)
    {
      return;
      ByteBuffer localByteBuffer = paramByteBuffer.asReadOnlyBuffer();
      if (localByteBuffer.position() > 0) {
        localByteBuffer.rewind();
      }
      ByteBufferBackedInputStream localByteBufferBackedInputStream = new ByteBufferBackedInputStream(localByteBuffer);
      paramJsonGenerator.writeBinary(localByteBufferBackedInputStream, localByteBuffer.remaining());
      localByteBufferBackedInputStream.close();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/ByteBufferSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */