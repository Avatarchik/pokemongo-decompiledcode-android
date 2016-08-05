package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

public final class JsonStringEncoder
{
  private static final byte[] HB = CharTypes.copyHexBytes();
  private static final char[] HC = ;
  private static final int SURR1_FIRST = 55296;
  private static final int SURR1_LAST = 56319;
  private static final int SURR2_FIRST = 56320;
  private static final int SURR2_LAST = 57343;
  protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal();
  protected ByteArrayBuilder _bytes;
  protected final char[] _qbuf = new char[6];
  protected TextBuffer _text;
  
  public JsonStringEncoder()
  {
    this._qbuf[0] = '\\';
    this._qbuf[2] = '0';
    this._qbuf[3] = '0';
  }
  
  private int _appendByte(int paramInt1, int paramInt2, ByteArrayBuilder paramByteArrayBuilder, int paramInt3)
  {
    paramByteArrayBuilder.setCurrentSegmentLength(paramInt3);
    paramByteArrayBuilder.append(92);
    if (paramInt2 < 0)
    {
      paramByteArrayBuilder.append(117);
      if (paramInt1 > 255)
      {
        int i = paramInt1 >> 8;
        paramByteArrayBuilder.append(HB[(i >> 4)]);
        paramByteArrayBuilder.append(HB[(i & 0xF)]);
        paramInt1 &= 0xFF;
        paramByteArrayBuilder.append(HB[(paramInt1 >> 4)]);
        paramByteArrayBuilder.append(HB[(paramInt1 & 0xF)]);
      }
    }
    for (;;)
    {
      return paramByteArrayBuilder.getCurrentSegmentLength();
      paramByteArrayBuilder.append(48);
      paramByteArrayBuilder.append(48);
      break;
      paramByteArrayBuilder.append((byte)paramInt2);
    }
  }
  
  private int _appendNamed(int paramInt, char[] paramArrayOfChar)
  {
    paramArrayOfChar[1] = ((char)paramInt);
    return 2;
  }
  
  private int _appendNumeric(int paramInt, char[] paramArrayOfChar)
  {
    paramArrayOfChar[1] = 'u';
    paramArrayOfChar[4] = HC[(paramInt >> 4)];
    paramArrayOfChar[5] = HC[(paramInt & 0xF)];
    return 6;
  }
  
  private static int _convert(int paramInt1, int paramInt2)
  {
    if ((paramInt2 < 56320) || (paramInt2 > 57343)) {
      throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(paramInt1) + ", second 0x" + Integer.toHexString(paramInt2) + "; illegal combination");
    }
    return 65536 + (paramInt1 - 55296 << 10) + (paramInt2 - 56320);
  }
  
  private static void _illegal(int paramInt)
  {
    throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(paramInt));
  }
  
  public static JsonStringEncoder getInstance()
  {
    SoftReference localSoftReference = (SoftReference)_threadEncoder.get();
    if (localSoftReference == null) {}
    for (JsonStringEncoder localJsonStringEncoder = null;; localJsonStringEncoder = (JsonStringEncoder)localSoftReference.get())
    {
      if (localJsonStringEncoder == null)
      {
        localJsonStringEncoder = new JsonStringEncoder();
        _threadEncoder.set(new SoftReference(localJsonStringEncoder));
      }
      return localJsonStringEncoder;
    }
  }
  
  public byte[] encodeAsUTF8(String paramString)
  {
    ByteArrayBuilder localByteArrayBuilder = this._bytes;
    if (localByteArrayBuilder == null)
    {
      localByteArrayBuilder = new ByteArrayBuilder(null);
      this._bytes = localByteArrayBuilder;
    }
    int i = paramString.length();
    int j = 0;
    byte[] arrayOfByte = localByteArrayBuilder.resetAndGetFirstSegment();
    int k = arrayOfByte.length;
    int m = 0;
    int i1;
    int i2;
    label69:
    int i13;
    if (m < i)
    {
      int n = m + 1;
      i1 = paramString.charAt(m);
      i2 = n;
      if (i1 <= 127)
      {
        if (j >= k)
        {
          arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
          k = arrayOfByte.length;
          j = 0;
        }
        i13 = j + 1;
        arrayOfByte[j] = ((byte)i1);
        if (i2 >= i) {
          j = i13;
        }
      }
    }
    for (;;)
    {
      return this._bytes.completeAndCoalesce(j);
      int i14 = i2 + 1;
      i1 = paramString.charAt(i2);
      j = i13;
      i2 = i14;
      break label69;
      if (j >= k)
      {
        arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
        k = arrayOfByte.length;
      }
      for (int i3 = 0;; i3 = j)
      {
        int i6;
        if (i1 < 2048)
        {
          i6 = i3 + 1;
          arrayOfByte[i3] = ((byte)(0xC0 | i1 >> 6));
        }
        for (int i7 = i2;; i7 = i2)
        {
          if (i6 >= k)
          {
            arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
            k = arrayOfByte.length;
            i6 = 0;
          }
          int i8 = i6 + 1;
          arrayOfByte[i6] = ((byte)(0x80 | i1 & 0x3F));
          j = i8;
          m = i7;
          break;
          if ((i1 >= 55296) && (i1 <= 57343)) {
            break label357;
          }
          int i4 = i3 + 1;
          arrayOfByte[i3] = ((byte)(0xE0 | i1 >> 12));
          if (i4 >= k)
          {
            arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
            k = arrayOfByte.length;
            i4 = 0;
          }
          int i5 = i4 + 1;
          arrayOfByte[i4] = ((byte)(0x80 | 0x3F & i1 >> 6));
          i6 = i5;
        }
        label357:
        if (i1 > 56319) {
          _illegal(i1);
        }
        if (i2 >= i) {
          _illegal(i1);
        }
        i7 = i2 + 1;
        i1 = _convert(i1, paramString.charAt(i2));
        if (i1 > 1114111) {
          _illegal(i1);
        }
        int i9 = i3 + 1;
        arrayOfByte[i3] = ((byte)(0xF0 | i1 >> 18));
        if (i9 >= k)
        {
          arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
          k = arrayOfByte.length;
          i9 = 0;
        }
        int i10 = i9 + 1;
        arrayOfByte[i9] = ((byte)(0x80 | 0x3F & i1 >> 12));
        if (i10 >= k)
        {
          arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
          k = arrayOfByte.length;
        }
        for (int i11 = 0;; i11 = i10)
        {
          int i12 = i11 + 1;
          arrayOfByte[i11] = ((byte)(0x80 | 0x3F & i1 >> 6));
          i6 = i12;
          break;
        }
      }
    }
  }
  
  public char[] quoteAsString(String paramString)
  {
    TextBuffer localTextBuffer = this._text;
    if (localTextBuffer == null)
    {
      localTextBuffer = new TextBuffer(null);
      this._text = localTextBuffer;
    }
    char[] arrayOfChar = localTextBuffer.emptyAndGetCurrentSegment();
    int[] arrayOfInt = CharTypes.get7BitOutputEscapes();
    int i = arrayOfInt.length;
    int j = 0;
    int k = paramString.length();
    int m = 0;
    label57:
    int n;
    int i2;
    int i4;
    int i5;
    if (j < k)
    {
      n = paramString.charAt(j);
      if ((n < i) && (arrayOfInt[n] != 0))
      {
        i2 = j + 1;
        int i3 = paramString.charAt(j);
        i4 = arrayOfInt[i3];
        if (i4 >= 0) {
          break label248;
        }
        i5 = _appendNumeric(i3, this._qbuf);
        label118:
        if (m + i5 <= arrayOfChar.length) {
          break label263;
        }
        int i6 = arrayOfChar.length - m;
        if (i6 > 0) {
          System.arraycopy(this._qbuf, 0, arrayOfChar, m, i6);
        }
        arrayOfChar = localTextBuffer.finishCurrentSegment();
        int i7 = i5 - i6;
        System.arraycopy(this._qbuf, i6, arrayOfChar, 0, i7);
        m = i7;
      }
    }
    for (;;)
    {
      j = i2;
      break;
      if (m >= arrayOfChar.length)
      {
        arrayOfChar = localTextBuffer.finishCurrentSegment();
        m = 0;
      }
      int i1 = m + 1;
      arrayOfChar[m] = n;
      j++;
      if (j >= k)
      {
        m = i1;
        localTextBuffer.setCurrentLength(m);
        return localTextBuffer.contentsAsArray();
      }
      m = i1;
      break label57;
      label248:
      i5 = _appendNamed(i4, this._qbuf);
      break label118;
      label263:
      System.arraycopy(this._qbuf, 0, arrayOfChar, m, i5);
      m += i5;
    }
  }
  
  public byte[] quoteAsUTF8(String paramString)
  {
    ByteArrayBuilder localByteArrayBuilder = this._bytes;
    if (localByteArrayBuilder == null)
    {
      localByteArrayBuilder = new ByteArrayBuilder(null);
      this._bytes = localByteArrayBuilder;
    }
    int i = 0;
    int j = paramString.length();
    int k = 0;
    byte[] arrayOfByte = localByteArrayBuilder.resetAndGetFirstSegment();
    int n;
    int i1;
    label198:
    int i5;
    int i6;
    for (;;)
    {
      int[] arrayOfInt;
      if (i < j) {
        arrayOfInt = CharTypes.get7BitOutputEscapes();
      }
      for (;;)
      {
        int m = paramString.charAt(i);
        if ((m > 127) || (arrayOfInt[m] != 0))
        {
          if (k >= arrayOfByte.length)
          {
            arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
            k = 0;
          }
          n = i + 1;
          i1 = paramString.charAt(i);
          if (i1 > 127) {
            break label198;
          }
          k = _appendByte(i1, arrayOfInt[i1], localByteArrayBuilder, k);
          arrayOfByte = localByteArrayBuilder.getCurrentSegment();
          i = n;
          break;
        }
        if (k >= arrayOfByte.length)
        {
          arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
          k = 0;
        }
        int i15 = k + 1;
        arrayOfByte[k] = ((byte)m);
        i++;
        if (i >= j)
        {
          k = i15;
          return this._bytes.completeAndCoalesce(k);
        }
        k = i15;
      }
      if (i1 > 2047) {
        break;
      }
      int i14 = k + 1;
      arrayOfByte[k] = ((byte)(0xC0 | i1 >> 6));
      i5 = 0x80 | i1 & 0x3F;
      i6 = i14;
      i = n;
      if (i6 >= arrayOfByte.length)
      {
        arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
        i6 = 0;
      }
      int i7 = i6 + 1;
      arrayOfByte[i6] = ((byte)i5);
      k = i7;
    }
    int i2;
    if ((i1 < 55296) || (i1 > 57343))
    {
      i2 = k + 1;
      arrayOfByte[k] = ((byte)(0xE0 | i1 >> 12));
      if (i2 < arrayOfByte.length) {
        break label569;
      }
      arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
    }
    label569:
    for (int i3 = 0;; i3 = i2)
    {
      int i4 = i3 + 1;
      arrayOfByte[i3] = ((byte)(0x80 | 0x3F & i1 >> 6));
      i5 = 0x80 | i1 & 0x3F;
      i6 = i4;
      i = n;
      break;
      if (i1 > 56319) {
        _illegal(i1);
      }
      if (n >= j) {
        _illegal(i1);
      }
      i = n + 1;
      int i8 = _convert(i1, paramString.charAt(n));
      if (i8 > 1114111) {
        _illegal(i8);
      }
      int i9 = k + 1;
      arrayOfByte[k] = ((byte)(0xF0 | i8 >> 18));
      if (i9 >= arrayOfByte.length) {
        arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
      }
      for (int i10 = 0;; i10 = i9)
      {
        int i11 = i10 + 1;
        arrayOfByte[i10] = ((byte)(0x80 | 0x3F & i8 >> 12));
        if (i11 >= arrayOfByte.length) {
          arrayOfByte = localByteArrayBuilder.finishCurrentSegment();
        }
        for (int i12 = 0;; i12 = i11)
        {
          int i13 = i12 + 1;
          arrayOfByte[i12] = ((byte)(0x80 | 0x3F & i8 >> 6));
          i5 = 0x80 | i8 & 0x3F;
          i6 = i13;
          break;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/io/JsonStringEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */