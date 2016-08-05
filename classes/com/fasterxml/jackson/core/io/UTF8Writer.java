package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public final class UTF8Writer
  extends Writer
{
  static final int SURR1_FIRST = 55296;
  static final int SURR1_LAST = 56319;
  static final int SURR2_FIRST = 56320;
  static final int SURR2_LAST = 57343;
  private final IOContext _context;
  private OutputStream _out;
  private byte[] _outBuffer;
  private final int _outBufferEnd;
  private int _outPtr;
  private int _surrogate = 0;
  
  public UTF8Writer(IOContext paramIOContext, OutputStream paramOutputStream)
  {
    this._context = paramIOContext;
    this._out = paramOutputStream;
    this._outBuffer = paramIOContext.allocWriteEncodingBuffer();
    this._outBufferEnd = (-4 + this._outBuffer.length);
    this._outPtr = 0;
  }
  
  protected static void illegalSurrogate(int paramInt)
    throws IOException
  {
    throw new IOException(illegalSurrogateDesc(paramInt));
  }
  
  protected static String illegalSurrogateDesc(int paramInt)
  {
    String str;
    if (paramInt > 1114111) {
      str = "Illegal character point (0x" + Integer.toHexString(paramInt) + ") to output; max is 0x10FFFF as per RFC 4627";
    }
    for (;;)
    {
      return str;
      if (paramInt >= 55296)
      {
        if (paramInt <= 56319) {
          str = "Unmatched first part of surrogate pair (0x" + Integer.toHexString(paramInt) + ")";
        } else {
          str = "Unmatched second part of surrogate pair (0x" + Integer.toHexString(paramInt) + ")";
        }
      }
      else {
        str = "Illegal character point (0x" + Integer.toHexString(paramInt) + ") to output";
      }
    }
  }
  
  public Writer append(char paramChar)
    throws IOException
  {
    write(paramChar);
    return this;
  }
  
  public void close()
    throws IOException
  {
    if (this._out != null)
    {
      if (this._outPtr > 0)
      {
        this._out.write(this._outBuffer, 0, this._outPtr);
        this._outPtr = 0;
      }
      OutputStream localOutputStream = this._out;
      this._out = null;
      byte[] arrayOfByte = this._outBuffer;
      if (arrayOfByte != null)
      {
        this._outBuffer = null;
        this._context.releaseWriteEncodingBuffer(arrayOfByte);
      }
      localOutputStream.close();
      int i = this._surrogate;
      this._surrogate = 0;
      if (i > 0) {
        illegalSurrogate(i);
      }
    }
  }
  
  protected int convertSurrogate(int paramInt)
    throws IOException
  {
    int i = this._surrogate;
    this._surrogate = 0;
    if ((paramInt < 56320) || (paramInt > 57343)) {
      throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(paramInt) + "; illegal combination");
    }
    return 65536 + (i - 55296 << 10) + (paramInt - 56320);
  }
  
  public void flush()
    throws IOException
  {
    if (this._out != null)
    {
      if (this._outPtr > 0)
      {
        this._out.write(this._outBuffer, 0, this._outPtr);
        this._outPtr = 0;
      }
      this._out.flush();
    }
  }
  
  public void write(int paramInt)
    throws IOException
  {
    if (this._surrogate > 0)
    {
      paramInt = convertSurrogate(paramInt);
      if (this._outPtr >= this._outBufferEnd)
      {
        this._out.write(this._outBuffer, 0, this._outPtr);
        this._outPtr = 0;
      }
      if (paramInt < 128)
      {
        byte[] arrayOfByte10 = this._outBuffer;
        int i5 = this._outPtr;
        this._outPtr = (i5 + 1);
        arrayOfByte10[i5] = ((byte)paramInt);
      }
    }
    else
    {
      for (;;)
      {
        label79:
        return;
        if ((paramInt < 55296) || (paramInt > 57343)) {
          break;
        }
        if (paramInt > 56319) {
          illegalSurrogate(paramInt);
        }
        this._surrogate = paramInt;
      }
    }
    int i = this._outPtr;
    int n;
    if (paramInt < 2048)
    {
      byte[] arrayOfByte8 = this._outBuffer;
      int i4 = i + 1;
      arrayOfByte8[i] = ((byte)(0xC0 | paramInt >> 6));
      byte[] arrayOfByte9 = this._outBuffer;
      n = i4 + 1;
      arrayOfByte9[i4] = ((byte)(0x80 | paramInt & 0x3F));
    }
    for (;;)
    {
      this._outPtr = n;
      break label79;
      break;
      if (paramInt <= 65535)
      {
        byte[] arrayOfByte5 = this._outBuffer;
        int i1 = i + 1;
        arrayOfByte5[i] = ((byte)(0xE0 | paramInt >> 12));
        byte[] arrayOfByte6 = this._outBuffer;
        int i2 = i1 + 1;
        arrayOfByte6[i1] = ((byte)(0x80 | 0x3F & paramInt >> 6));
        byte[] arrayOfByte7 = this._outBuffer;
        int i3 = i2 + 1;
        arrayOfByte7[i2] = ((byte)(0x80 | paramInt & 0x3F));
        n = i3;
      }
      else
      {
        if (paramInt > 1114111) {
          illegalSurrogate(paramInt);
        }
        byte[] arrayOfByte1 = this._outBuffer;
        int j = i + 1;
        arrayOfByte1[i] = ((byte)(0xF0 | paramInt >> 18));
        byte[] arrayOfByte2 = this._outBuffer;
        int k = j + 1;
        arrayOfByte2[j] = ((byte)(0x80 | 0x3F & paramInt >> 12));
        byte[] arrayOfByte3 = this._outBuffer;
        int m = k + 1;
        arrayOfByte3[k] = ((byte)(0x80 | 0x3F & paramInt >> 6));
        byte[] arrayOfByte4 = this._outBuffer;
        n = m + 1;
        arrayOfByte4[m] = ((byte)(0x80 | paramInt & 0x3F));
      }
    }
  }
  
  public void write(String paramString)
    throws IOException
  {
    write(paramString, 0, paramString.length());
  }
  
  public void write(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 < 2)
    {
      if (paramInt2 == 1) {
        write(paramString.charAt(paramInt1));
      }
      return;
    }
    if (this._surrogate > 0)
    {
      int i18 = paramInt1 + 1;
      int i19 = paramString.charAt(paramInt1);
      paramInt2--;
      write(convertSurrogate(i19));
      paramInt1 = i18;
    }
    int i = this._outPtr;
    byte[] arrayOfByte = this._outBuffer;
    int j = this._outBufferEnd;
    int k = paramInt2 + paramInt1;
    int m = paramInt1;
    int n;
    int i2;
    label222:
    int i5;
    for (;;)
    {
      if (m < k)
      {
        if (i >= j)
        {
          this._out.write(arrayOfByte, 0, i);
          i = 0;
        }
        n = m + 1;
        int i1 = paramString.charAt(m);
        if (i1 < 128)
        {
          i2 = i + 1;
          arrayOfByte[i] = ((byte)i1);
          int i13 = k - n;
          int i14 = j - i2;
          if (i13 > i14) {
            i13 = i14;
          }
          int i15 = i13 + n;
          int i16;
          for (m = n;; m = i16)
          {
            if (m >= i15)
            {
              i = i2;
              break;
            }
            i16 = m + 1;
            i1 = paramString.charAt(m);
            if (i1 >= 128)
            {
              m = i16;
              if (i1 >= 2048) {
                break label312;
              }
              int i11 = i2 + 1;
              arrayOfByte[i2] = ((byte)(0xC0 | i1 >> 6));
              int i12 = i11 + 1;
              arrayOfByte[i11] = ((byte)(0x80 | i1 & 0x3F));
              i = i12;
              i5 = m;
              label280:
              m = i5;
              break;
            }
            int i17 = i2 + 1;
            arrayOfByte[i2] = ((byte)i1);
            i2 = i17;
          }
          label312:
          if ((i1 < 55296) || (i1 > 57343))
          {
            int i3 = i2 + 1;
            arrayOfByte[i2] = ((byte)(0xE0 | i1 >> 12));
            int i4 = i3 + 1;
            arrayOfByte[i3] = ((byte)(0x80 | 0x3F & i1 >> 6));
            i = i4 + 1;
            arrayOfByte[i4] = ((byte)(0x80 | i1 & 0x3F));
            continue;
          }
          if (i1 > 56319)
          {
            this._outPtr = i2;
            illegalSurrogate(i1);
          }
          this._surrogate = i1;
          if (m >= k) {
            i = i2;
          }
        }
      }
    }
    for (;;)
    {
      this._outPtr = i;
      break;
      i5 = m + 1;
      int i6 = convertSurrogate(paramString.charAt(m));
      if (i6 > 1114111)
      {
        this._outPtr = i2;
        illegalSurrogate(i6);
      }
      int i7 = i2 + 1;
      arrayOfByte[i2] = ((byte)(0xF0 | i6 >> 18));
      int i8 = i7 + 1;
      arrayOfByte[i7] = ((byte)(0x80 | 0x3F & i6 >> 12));
      int i9 = i8 + 1;
      arrayOfByte[i8] = ((byte)(0x80 | 0x3F & i6 >> 6));
      int i10 = i9 + 1;
      arrayOfByte[i9] = ((byte)(0x80 | i6 & 0x3F));
      i = i10;
      break label280;
      i2 = i;
      m = n;
      break label222;
    }
  }
  
  public void write(char[] paramArrayOfChar)
    throws IOException
  {
    write(paramArrayOfChar, 0, paramArrayOfChar.length);
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 < 2)
    {
      if (paramInt2 == 1) {
        write(paramArrayOfChar[paramInt1]);
      }
      return;
    }
    if (this._surrogate > 0)
    {
      int i18 = paramInt1 + 1;
      int i19 = paramArrayOfChar[paramInt1];
      paramInt2--;
      write(convertSurrogate(i19));
      paramInt1 = i18;
    }
    int i = this._outPtr;
    byte[] arrayOfByte = this._outBuffer;
    int j = this._outBufferEnd;
    int k = paramInt2 + paramInt1;
    int m = paramInt1;
    int n;
    int i2;
    label214:
    int i5;
    for (;;)
    {
      if (m < k)
      {
        if (i >= j)
        {
          this._out.write(arrayOfByte, 0, i);
          i = 0;
        }
        n = m + 1;
        int i1 = paramArrayOfChar[m];
        if (i1 < 128)
        {
          i2 = i + 1;
          arrayOfByte[i] = ((byte)i1);
          int i13 = k - n;
          int i14 = j - i2;
          if (i13 > i14) {
            i13 = i14;
          }
          int i15 = i13 + n;
          int i16;
          for (m = n;; m = i16)
          {
            if (m >= i15)
            {
              i = i2;
              break;
            }
            i16 = m + 1;
            i1 = paramArrayOfChar[m];
            if (i1 >= 128)
            {
              m = i16;
              if (i1 >= 2048) {
                break label304;
              }
              int i11 = i2 + 1;
              arrayOfByte[i2] = ((byte)(0xC0 | i1 >> 6));
              int i12 = i11 + 1;
              arrayOfByte[i11] = ((byte)(0x80 | i1 & 0x3F));
              i = i12;
              i5 = m;
              label272:
              m = i5;
              break;
            }
            int i17 = i2 + 1;
            arrayOfByte[i2] = ((byte)i1);
            i2 = i17;
          }
          label304:
          if ((i1 < 55296) || (i1 > 57343))
          {
            int i3 = i2 + 1;
            arrayOfByte[i2] = ((byte)(0xE0 | i1 >> 12));
            int i4 = i3 + 1;
            arrayOfByte[i3] = ((byte)(0x80 | 0x3F & i1 >> 6));
            i = i4 + 1;
            arrayOfByte[i4] = ((byte)(0x80 | i1 & 0x3F));
            continue;
          }
          if (i1 > 56319)
          {
            this._outPtr = i2;
            illegalSurrogate(i1);
          }
          this._surrogate = i1;
          if (m >= k) {
            i = i2;
          }
        }
      }
    }
    for (;;)
    {
      this._outPtr = i;
      break;
      i5 = m + 1;
      int i6 = convertSurrogate(paramArrayOfChar[m]);
      if (i6 > 1114111)
      {
        this._outPtr = i2;
        illegalSurrogate(i6);
      }
      int i7 = i2 + 1;
      arrayOfByte[i2] = ((byte)(0xF0 | i6 >> 18));
      int i8 = i7 + 1;
      arrayOfByte[i7] = ((byte)(0x80 | 0x3F & i6 >> 12));
      int i9 = i8 + 1;
      arrayOfByte[i8] = ((byte)(0x80 | 0x3F & i6 >> 6));
      int i10 = i9 + 1;
      arrayOfByte[i9] = ((byte)(0x80 | i6 & 0x3F));
      i = i10;
      break label272;
      i2 = i;
      m = n;
      break label214;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/io/UTF8Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */