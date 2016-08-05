package com.fasterxml.jackson.core.io;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class UTF32Reader
  extends Reader
{
  protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
  protected static final char NC;
  protected final boolean _bigEndian;
  protected byte[] _buffer;
  protected int _byteCount = 0;
  protected int _charCount = 0;
  protected final IOContext _context;
  protected InputStream _in;
  protected int _length;
  protected final boolean _managedBuffers;
  protected int _ptr;
  protected char _surrogate = '\000';
  protected char[] _tmpBuf = null;
  
  public UTF32Reader(IOContext paramIOContext, InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this._context = paramIOContext;
    this._in = paramInputStream;
    this._buffer = paramArrayOfByte;
    this._ptr = paramInt1;
    this._length = paramInt2;
    this._bigEndian = paramBoolean;
    if (paramInputStream != null) {
      bool = true;
    }
    this._managedBuffers = bool;
  }
  
  private void freeBuffers()
  {
    byte[] arrayOfByte = this._buffer;
    if (arrayOfByte != null)
    {
      this._buffer = null;
      this._context.releaseReadIOBuffer(arrayOfByte);
    }
  }
  
  private boolean loadMore(int paramInt)
    throws IOException
  {
    boolean bool = false;
    this._byteCount += this._length - paramInt;
    int j;
    if (paramInt > 0)
    {
      if (this._ptr > 0)
      {
        System.arraycopy(this._buffer, this._ptr, this._buffer, 0, paramInt);
        this._ptr = 0;
      }
      for (this._length = paramInt;; this._length = (j + this._length))
      {
        if (this._length >= 4) {
          break label222;
        }
        if (this._in != null) {
          break;
        }
        j = -1;
        label74:
        if (j < 1)
        {
          if (j < 0)
          {
            if (this._managedBuffers) {
              freeBuffers();
            }
            reportUnexpectedEOF(this._length, 4);
          }
          reportStrangeStream();
        }
      }
    }
    this._ptr = 0;
    int i;
    if (this._in == null)
    {
      i = -1;
      label138:
      if (i >= 1) {
        break label184;
      }
      this._length = 0;
      if (i >= 0) {
        break label180;
      }
      if (this._managedBuffers) {
        freeBuffers();
      }
    }
    for (;;)
    {
      return bool;
      i = this._in.read(this._buffer);
      break label138;
      label180:
      reportStrangeStream();
      label184:
      this._length = i;
      break;
      j = this._in.read(this._buffer, this._length, this._buffer.length - this._length);
      break label74;
      label222:
      bool = true;
    }
  }
  
  private void reportBounds(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    throw new ArrayIndexOutOfBoundsException("read(buf," + paramInt1 + "," + paramInt2 + "), cbuf[" + paramArrayOfChar.length + "]");
  }
  
  private void reportInvalid(int paramInt1, int paramInt2, String paramString)
    throws IOException
  {
    int i = -1 + (this._byteCount + this._ptr);
    int j = paramInt2 + this._charCount;
    throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(paramInt1) + paramString + " at char #" + j + ", byte #" + i + ")");
  }
  
  private void reportStrangeStream()
    throws IOException
  {
    throw new IOException("Strange I/O stream, returned 0 bytes on read");
  }
  
  private void reportUnexpectedEOF(int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt1 + this._byteCount;
    int j = this._charCount;
    throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + paramInt1 + ", needed " + paramInt2 + ", at char #" + j + ", byte #" + i + ")");
  }
  
  public void close()
    throws IOException
  {
    InputStream localInputStream = this._in;
    if (localInputStream != null)
    {
      this._in = null;
      freeBuffers();
      localInputStream.close();
    }
  }
  
  public int read()
    throws IOException
  {
    if (this._tmpBuf == null) {
      this._tmpBuf = new char[1];
    }
    if (read(this._tmpBuf, 0, 1) < 1) {}
    for (int i = -1;; i = this._tmpBuf[0]) {
      return i;
    }
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = -1;
    if (this._buffer == null) {}
    for (;;)
    {
      return i;
      if (paramInt2 >= 1) {
        break;
      }
      i = paramInt2;
    }
    if ((paramInt1 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length)) {
      reportBounds(paramArrayOfChar, paramInt1, paramInt2);
    }
    int j = paramInt2 + paramInt1;
    int m;
    label73:
    int i2;
    int i3;
    label153:
    int n;
    if (this._surrogate != 0)
    {
      m = paramInt1 + 1;
      paramArrayOfChar[paramInt1] = this._surrogate;
      this._surrogate = '\000';
      if (m >= j) {
        break label420;
      }
      i2 = this._ptr;
      if (!this._bigEndian) {
        break label322;
      }
      i3 = this._buffer[i2] << 24 | (0xFF & this._buffer[(i2 + 1)]) << 16 | (0xFF & this._buffer[(i2 + 2)]) << 8 | 0xFF & this._buffer[(i2 + 3)];
      this._ptr = (4 + this._ptr);
      if (i3 <= 65535) {
        break label385;
      }
      if (i3 > 1114111) {
        reportInvalid(i3, m - paramInt1, "(above " + Integer.toHexString(1114111) + ") ");
      }
      int i4 = i3 - 65536;
      n = m + 1;
      paramArrayOfChar[m] = ((char)(55296 + (i4 >> 10)));
      i3 = 0xDC00 | i4 & 0x3FF;
      if (n < j) {
        break label389;
      }
      this._surrogate = ((char)i3);
    }
    for (;;)
    {
      int i1 = n - paramInt1;
      this._charCount = (i1 + this._charCount);
      i = i1;
      break;
      int k = this._length - this._ptr;
      if ((k < 4) && (!loadMore(k))) {
        break;
      }
      m = paramInt1;
      break label73;
      label322:
      i3 = 0xFF & this._buffer[i2] | (0xFF & this._buffer[(i2 + 1)]) << 8 | (0xFF & this._buffer[(i2 + 2)]) << 16 | this._buffer[(i2 + 3)] << 24;
      break label153;
      label385:
      n = m;
      label389:
      m = n + 1;
      paramArrayOfChar[n] = ((char)i3);
      if (this._ptr < this._length) {
        break label73;
      }
      n = m;
      continue;
      label420:
      n = m;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/io/UTF32Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */