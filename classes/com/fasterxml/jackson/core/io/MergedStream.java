package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.InputStream;

public final class MergedStream
  extends InputStream
{
  private byte[] _b;
  private final IOContext _ctxt;
  private final int _end;
  private final InputStream _in;
  private int _ptr;
  
  public MergedStream(IOContext paramIOContext, InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this._ctxt = paramIOContext;
    this._in = paramInputStream;
    this._b = paramArrayOfByte;
    this._ptr = paramInt1;
    this._end = paramInt2;
  }
  
  private void _free()
  {
    byte[] arrayOfByte = this._b;
    if (arrayOfByte != null)
    {
      this._b = null;
      if (this._ctxt != null) {
        this._ctxt.releaseReadIOBuffer(arrayOfByte);
      }
    }
  }
  
  public int available()
    throws IOException
  {
    if (this._b != null) {}
    for (int i = this._end - this._ptr;; i = this._in.available()) {
      return i;
    }
  }
  
  public void close()
    throws IOException
  {
    _free();
    this._in.close();
  }
  
  public void mark(int paramInt)
  {
    if (this._b == null) {
      this._in.mark(paramInt);
    }
  }
  
  public boolean markSupported()
  {
    if ((this._b == null) && (this._in.markSupported())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int read()
    throws IOException
  {
    int i;
    if (this._b != null)
    {
      byte[] arrayOfByte = this._b;
      int j = this._ptr;
      this._ptr = (j + 1);
      i = 0xFF & arrayOfByte[j];
      if (this._ptr >= this._end) {
        _free();
      }
    }
    for (;;)
    {
      return i;
      i = this._in.read();
    }
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._b != null)
    {
      int j = this._end - this._ptr;
      if (paramInt2 > j) {
        paramInt2 = j;
      }
      System.arraycopy(this._b, this._ptr, paramArrayOfByte, paramInt1, paramInt2);
      this._ptr = (paramInt2 + this._ptr);
      if (this._ptr >= this._end) {
        _free();
      }
    }
    for (int i = paramInt2;; i = this._in.read(paramArrayOfByte, paramInt1, paramInt2)) {
      return i;
    }
  }
  
  public void reset()
    throws IOException
  {
    if (this._b == null) {
      this._in.reset();
    }
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    long l1 = 0L;
    int i;
    if (this._b != null)
    {
      i = this._end - this._ptr;
      if (i > paramLong) {
        this._ptr += (int)paramLong;
      }
    }
    for (long l2 = paramLong;; l2 = l1)
    {
      return l2;
      _free();
      l1 += i;
      paramLong -= i;
      if (paramLong > 0L) {
        l1 += this._in.skip(paramLong);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/io/MergedStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */