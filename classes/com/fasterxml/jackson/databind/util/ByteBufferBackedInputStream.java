package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedInputStream
  extends InputStream
{
  protected final ByteBuffer _b;
  
  public ByteBufferBackedInputStream(ByteBuffer paramByteBuffer)
  {
    this._b = paramByteBuffer;
  }
  
  public int available()
  {
    return this._b.remaining();
  }
  
  public int read()
    throws IOException
  {
    if (this._b.hasRemaining()) {}
    for (int i = 0xFF & this._b.get();; i = -1) {
      return i;
    }
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (!this._b.hasRemaining()) {}
    int i;
    for (int j = -1;; j = i)
    {
      return j;
      i = Math.min(paramInt2, this._b.remaining());
      this._b.get(paramArrayOfByte, paramInt1, i);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/ByteBufferBackedInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */