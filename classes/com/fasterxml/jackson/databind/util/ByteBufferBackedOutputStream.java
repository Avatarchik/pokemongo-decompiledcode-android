package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedOutputStream
  extends OutputStream
{
  protected final ByteBuffer _b;
  
  public ByteBufferBackedOutputStream(ByteBuffer paramByteBuffer)
  {
    this._b = paramByteBuffer;
  }
  
  public void write(int paramInt)
    throws IOException
  {
    this._b.put((byte)paramInt);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this._b.put(paramArrayOfByte, paramInt1, paramInt2);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/ByteBufferBackedOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */