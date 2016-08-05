package com.fasterxml.jackson.core.util;

public class BufferRecycler
{
  public static final int BYTE_BASE64_CODEC_BUFFER = 3;
  private static final int[] BYTE_BUFFER_LENGTHS;
  public static final int BYTE_READ_IO_BUFFER = 0;
  public static final int BYTE_WRITE_CONCAT_BUFFER = 2;
  public static final int BYTE_WRITE_ENCODING_BUFFER = 1;
  private static final int[] CHAR_BUFFER_LENGTHS;
  public static final int CHAR_CONCAT_BUFFER = 1;
  public static final int CHAR_NAME_COPY_BUFFER = 3;
  public static final int CHAR_TEXT_BUFFER = 2;
  public static final int CHAR_TOKEN_BUFFER;
  protected final byte[][] _byteBuffers;
  protected final char[][] _charBuffers;
  
  static
  {
    int[] arrayOfInt1 = new int[4];
    arrayOfInt1[0] = 8000;
    arrayOfInt1[1] = 8000;
    arrayOfInt1[2] = 2000;
    arrayOfInt1[3] = 2000;
    BYTE_BUFFER_LENGTHS = arrayOfInt1;
    int[] arrayOfInt2 = new int[4];
    arrayOfInt2[0] = 4000;
    arrayOfInt2[1] = 4000;
    arrayOfInt2[2] = 200;
    arrayOfInt2[3] = 200;
    CHAR_BUFFER_LENGTHS = arrayOfInt2;
  }
  
  public BufferRecycler()
  {
    this(4, 4);
  }
  
  protected BufferRecycler(int paramInt1, int paramInt2)
  {
    this._byteBuffers = new byte[paramInt1][];
    this._charBuffers = new char[paramInt2][];
  }
  
  public final byte[] allocByteBuffer(int paramInt)
  {
    return allocByteBuffer(paramInt, 0);
  }
  
  public byte[] allocByteBuffer(int paramInt1, int paramInt2)
  {
    int i = byteBufferLength(paramInt1);
    if (paramInt2 < i) {
      paramInt2 = i;
    }
    byte[] arrayOfByte = this._byteBuffers[paramInt1];
    if ((arrayOfByte == null) || (arrayOfByte.length < paramInt2)) {
      arrayOfByte = balloc(paramInt2);
    }
    for (;;)
    {
      return arrayOfByte;
      this._byteBuffers[paramInt1] = null;
    }
  }
  
  public final char[] allocCharBuffer(int paramInt)
  {
    return allocCharBuffer(paramInt, 0);
  }
  
  public char[] allocCharBuffer(int paramInt1, int paramInt2)
  {
    int i = charBufferLength(paramInt1);
    if (paramInt2 < i) {
      paramInt2 = i;
    }
    char[] arrayOfChar = this._charBuffers[paramInt1];
    if ((arrayOfChar == null) || (arrayOfChar.length < paramInt2)) {
      arrayOfChar = calloc(paramInt2);
    }
    for (;;)
    {
      return arrayOfChar;
      this._charBuffers[paramInt1] = null;
    }
  }
  
  protected byte[] balloc(int paramInt)
  {
    return new byte[paramInt];
  }
  
  protected int byteBufferLength(int paramInt)
  {
    return BYTE_BUFFER_LENGTHS[paramInt];
  }
  
  protected char[] calloc(int paramInt)
  {
    return new char[paramInt];
  }
  
  protected int charBufferLength(int paramInt)
  {
    return CHAR_BUFFER_LENGTHS[paramInt];
  }
  
  public final void releaseByteBuffer(int paramInt, byte[] paramArrayOfByte)
  {
    this._byteBuffers[paramInt] = paramArrayOfByte;
  }
  
  public void releaseCharBuffer(int paramInt, char[] paramArrayOfChar)
  {
    this._charBuffers[paramInt] = paramArrayOfChar;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/util/BufferRecycler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */