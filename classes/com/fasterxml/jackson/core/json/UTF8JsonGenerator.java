package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UTF8JsonGenerator
  extends JsonGeneratorImpl
{
  private static final byte BYTE_0 = 48;
  private static final byte BYTE_BACKSLASH = 92;
  private static final byte BYTE_COLON = 58;
  private static final byte BYTE_COMMA = 44;
  private static final byte BYTE_LBRACKET = 91;
  private static final byte BYTE_LCURLY = 123;
  private static final byte BYTE_QUOTE = 34;
  private static final byte BYTE_RBRACKET = 93;
  private static final byte BYTE_RCURLY = 125;
  private static final byte BYTE_u = 117;
  private static final byte[] FALSE_BYTES;
  static final byte[] HEX_CHARS = ;
  private static final int MAX_BYTES_TO_BUFFER = 512;
  private static final byte[] NULL_BYTES;
  private static final byte[] TRUE_BYTES;
  protected boolean _bufferRecyclable;
  protected boolean _cfgUnqNames;
  protected char[] _charBuffer;
  protected final int _charBufferLength;
  protected byte[] _entityBuffer;
  protected byte[] _outputBuffer;
  protected final int _outputEnd;
  protected final int _outputMaxContiguous;
  protected final OutputStream _outputStream;
  protected int _outputTail = 0;
  
  static
  {
    byte[] arrayOfByte1 = new byte[4];
    arrayOfByte1[0] = 110;
    arrayOfByte1[1] = 117;
    arrayOfByte1[2] = 108;
    arrayOfByte1[3] = 108;
    NULL_BYTES = arrayOfByte1;
    byte[] arrayOfByte2 = new byte[4];
    arrayOfByte2[0] = 116;
    arrayOfByte2[1] = 114;
    arrayOfByte2[2] = 117;
    arrayOfByte2[3] = 101;
    TRUE_BYTES = arrayOfByte2;
    byte[] arrayOfByte3 = new byte[5];
    arrayOfByte3[0] = 102;
    arrayOfByte3[1] = 97;
    arrayOfByte3[2] = 108;
    arrayOfByte3[3] = 115;
    arrayOfByte3[4] = 101;
    FALSE_BYTES = arrayOfByte3;
  }
  
  public UTF8JsonGenerator(IOContext paramIOContext, int paramInt, ObjectCodec paramObjectCodec, OutputStream paramOutputStream)
  {
    super(paramIOContext, paramInt, paramObjectCodec);
    this._outputStream = paramOutputStream;
    this._bufferRecyclable = bool;
    this._outputBuffer = paramIOContext.allocWriteEncodingBuffer();
    this._outputEnd = this._outputBuffer.length;
    this._outputMaxContiguous = (this._outputEnd >> 3);
    this._charBuffer = paramIOContext.allocConcatBuffer();
    this._charBufferLength = this._charBuffer.length;
    if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
      setHighestNonEscapedChar(127);
    }
    if (!JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(paramInt)) {}
    for (;;)
    {
      this._cfgUnqNames = bool;
      return;
      bool = false;
    }
  }
  
  public UTF8JsonGenerator(IOContext paramIOContext, int paramInt1, ObjectCodec paramObjectCodec, OutputStream paramOutputStream, byte[] paramArrayOfByte, int paramInt2, boolean paramBoolean)
  {
    super(paramIOContext, paramInt1, paramObjectCodec);
    this._outputStream = paramOutputStream;
    this._bufferRecyclable = paramBoolean;
    this._outputTail = paramInt2;
    this._outputBuffer = paramArrayOfByte;
    this._outputEnd = this._outputBuffer.length;
    this._outputMaxContiguous = (this._outputEnd >> 3);
    this._charBuffer = paramIOContext.allocConcatBuffer();
    this._charBufferLength = this._charBuffer.length;
    if (!JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(paramInt1)) {
      bool = true;
    }
    this._cfgUnqNames = bool;
  }
  
  private final int _handleLongCustomEscape(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
    throws IOException, JsonGenerationException
  {
    int i = paramArrayOfByte2.length;
    int k;
    int j;
    if (paramInt1 + i > paramInt2)
    {
      this._outputTail = paramInt1;
      _flushBuffer();
      k = this._outputTail;
      if (i > paramArrayOfByte1.length)
      {
        this._outputStream.write(paramArrayOfByte2, 0, i);
        j = k;
      }
    }
    for (;;)
    {
      return j;
      System.arraycopy(paramArrayOfByte2, 0, paramArrayOfByte1, k, i);
      paramInt1 = k + i;
      if (paramInt1 + paramInt3 * 6 > paramInt2)
      {
        _flushBuffer();
        j = this._outputTail;
      }
      else
      {
        j = paramInt1;
      }
    }
  }
  
  private final int _outputMultiByteChar(int paramInt1, int paramInt2)
    throws IOException
  {
    byte[] arrayOfByte = this._outputBuffer;
    int m;
    if ((paramInt1 >= 55296) && (paramInt1 <= 57343))
    {
      int n = paramInt2 + 1;
      arrayOfByte[paramInt2] = 92;
      int i1 = n + 1;
      arrayOfByte[n] = 117;
      int i2 = i1 + 1;
      arrayOfByte[i1] = HEX_CHARS[(0xF & paramInt1 >> 12)];
      int i3 = i2 + 1;
      arrayOfByte[i2] = HEX_CHARS[(0xF & paramInt1 >> 8)];
      int i4 = i3 + 1;
      arrayOfByte[i3] = HEX_CHARS[(0xF & paramInt1 >> 4)];
      m = i4 + 1;
      arrayOfByte[i4] = HEX_CHARS[(paramInt1 & 0xF)];
    }
    for (;;)
    {
      return m;
      int i = paramInt2 + 1;
      arrayOfByte[paramInt2] = ((byte)(0xE0 | paramInt1 >> 12));
      int j = i + 1;
      arrayOfByte[i] = ((byte)(0x80 | 0x3F & paramInt1 >> 6));
      int k = j + 1;
      arrayOfByte[j] = ((byte)(0x80 | paramInt1 & 0x3F));
      m = k;
    }
  }
  
  private final int _outputRawMultiByteChar(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
    throws IOException
  {
    if ((paramInt1 >= 55296) && (paramInt1 <= 57343))
    {
      if ((paramInt2 >= paramInt3) || (paramArrayOfChar == null)) {
        _reportError("Split surrogate on writeRaw() input (last character)");
      }
      _outputSurrogates(paramInt1, paramArrayOfChar[paramInt2]);
      paramInt2++;
    }
    for (;;)
    {
      return paramInt2;
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = ((byte)(0xE0 | paramInt1 >> 12));
      int j = this._outputTail;
      this._outputTail = (j + 1);
      arrayOfByte[j] = ((byte)(0x80 | 0x3F & paramInt1 >> 6));
      int k = this._outputTail;
      this._outputTail = (k + 1);
      arrayOfByte[k] = ((byte)(0x80 | paramInt1 & 0x3F));
    }
  }
  
  private final int _readMore(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    int i = 0;
    int i4;
    for (int j = paramInt1; j < paramInt2; j = i4)
    {
      int i3 = i + 1;
      i4 = j + 1;
      paramArrayOfByte[i] = paramArrayOfByte[j];
      i = i3;
    }
    int k = i;
    int m = Math.min(paramInt3, paramArrayOfByte.length);
    int n = m - k;
    if (n == 0) {}
    for (;;)
    {
      int i1;
      for (int i2 = k;; i2 = k)
      {
        return i2;
        i1 = paramInputStream.read(paramArrayOfByte, k, n);
        if (i1 >= 0) {
          break;
        }
      }
      k += i1;
      if (k < 3) {
        break;
      }
    }
  }
  
  private final void _writeBytes(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = paramArrayOfByte.length;
    if (i + this._outputTail > this._outputEnd)
    {
      _flushBuffer();
      if (i > 512) {
        this._outputStream.write(paramArrayOfByte, 0, i);
      }
    }
    for (;;)
    {
      return;
      System.arraycopy(paramArrayOfByte, 0, this._outputBuffer, this._outputTail, i);
      this._outputTail = (i + this._outputTail);
    }
  }
  
  private final void _writeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 + this._outputTail > this._outputEnd)
    {
      _flushBuffer();
      if (paramInt2 > 512) {
        this._outputStream.write(paramArrayOfByte, paramInt1, paramInt2);
      }
    }
    for (;;)
    {
      return;
      System.arraycopy(paramArrayOfByte, paramInt1, this._outputBuffer, this._outputTail, paramInt2);
      this._outputTail = (paramInt2 + this._outputTail);
    }
  }
  
  private final int _writeCustomEscape(byte[] paramArrayOfByte, int paramInt1, SerializableString paramSerializableString, int paramInt2)
    throws IOException, JsonGenerationException
  {
    byte[] arrayOfByte = paramSerializableString.asUnquotedUTF8();
    int i = arrayOfByte.length;
    if (i > 6) {}
    for (int j = _handleLongCustomEscape(paramArrayOfByte, paramInt1, this._outputEnd, arrayOfByte, paramInt2);; j = paramInt1 + i)
    {
      return j;
      System.arraycopy(arrayOfByte, 0, paramArrayOfByte, paramInt1, i);
    }
  }
  
  private final void _writeCustomStringSegment2(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + 6 * (paramInt2 - paramInt1) > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j;
    CharacterEscapes localCharacterEscapes;
    int k;
    int m;
    if (this._maximumNonEscapedChar <= 0)
    {
      j = 65535;
      localCharacterEscapes = this._characterEscapes;
      k = i;
      m = paramInt1;
    }
    for (;;)
    {
      if (m < paramInt2)
      {
        int n = m + 1;
        int i1 = paramString.charAt(m);
        if (i1 <= 127)
        {
          if (arrayOfInt[i1] == 0)
          {
            int i7 = k + 1;
            arrayOfByte[k] = ((byte)i1);
            k = i7;
            m = n;
            continue;
            j = this._maximumNonEscapedChar;
            break;
          }
          int i5 = arrayOfInt[i1];
          if (i5 > 0)
          {
            int i6 = k + 1;
            arrayOfByte[k] = 92;
            k = i6 + 1;
            arrayOfByte[i6] = ((byte)i5);
            m = n;
            continue;
          }
          if (i5 == -2)
          {
            SerializableString localSerializableString2 = localCharacterEscapes.getEscapeSequence(i1);
            if (localSerializableString2 == null) {
              _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(i1) + ", although was supposed to have one");
            }
            k = _writeCustomEscape(arrayOfByte, k, localSerializableString2, paramInt2 - n);
            m = n;
            continue;
          }
          k = _writeGenericEscape(i1, k);
          m = n;
          continue;
        }
        if (i1 > j)
        {
          k = _writeGenericEscape(i1, k);
          m = n;
        }
        else
        {
          SerializableString localSerializableString1 = localCharacterEscapes.getEscapeSequence(i1);
          if (localSerializableString1 != null)
          {
            k = _writeCustomEscape(arrayOfByte, k, localSerializableString1, paramInt2 - n);
            m = n;
          }
          else
          {
            int i4;
            if (i1 <= 2047)
            {
              int i3 = k + 1;
              arrayOfByte[k] = ((byte)(0xC0 | i1 >> 6));
              i4 = i3 + 1;
              arrayOfByte[i3] = ((byte)(0x80 | i1 & 0x3F));
            }
            for (int i2 = i4;; i2 = _outputMultiByteChar(i1, k))
            {
              k = i2;
              m = n;
              break;
            }
          }
        }
      }
    }
    this._outputTail = k;
  }
  
  private final void _writeCustomStringSegment2(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + 6 * (paramInt2 - paramInt1) > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j;
    CharacterEscapes localCharacterEscapes;
    int k;
    int m;
    if (this._maximumNonEscapedChar <= 0)
    {
      j = 65535;
      localCharacterEscapes = this._characterEscapes;
      k = i;
      m = paramInt1;
    }
    for (;;)
    {
      if (m < paramInt2)
      {
        int n = m + 1;
        int i1 = paramArrayOfChar[m];
        if (i1 <= 127)
        {
          if (arrayOfInt[i1] == 0)
          {
            int i7 = k + 1;
            arrayOfByte[k] = ((byte)i1);
            k = i7;
            m = n;
            continue;
            j = this._maximumNonEscapedChar;
            break;
          }
          int i5 = arrayOfInt[i1];
          if (i5 > 0)
          {
            int i6 = k + 1;
            arrayOfByte[k] = 92;
            k = i6 + 1;
            arrayOfByte[i6] = ((byte)i5);
            m = n;
            continue;
          }
          if (i5 == -2)
          {
            SerializableString localSerializableString2 = localCharacterEscapes.getEscapeSequence(i1);
            if (localSerializableString2 == null) {
              _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(i1) + ", although was supposed to have one");
            }
            k = _writeCustomEscape(arrayOfByte, k, localSerializableString2, paramInt2 - n);
            m = n;
            continue;
          }
          k = _writeGenericEscape(i1, k);
          m = n;
          continue;
        }
        if (i1 > j)
        {
          k = _writeGenericEscape(i1, k);
          m = n;
        }
        else
        {
          SerializableString localSerializableString1 = localCharacterEscapes.getEscapeSequence(i1);
          if (localSerializableString1 != null)
          {
            k = _writeCustomEscape(arrayOfByte, k, localSerializableString1, paramInt2 - n);
            m = n;
          }
          else
          {
            int i4;
            if (i1 <= 2047)
            {
              int i3 = k + 1;
              arrayOfByte[k] = ((byte)(0xC0 | i1 >> 6));
              i4 = i3 + 1;
              arrayOfByte[i3] = ((byte)(0x80 | i1 & 0x3F));
            }
            for (int i2 = i4;; i2 = _outputMultiByteChar(i1, k))
            {
              k = i2;
              m = n;
              break;
            }
          }
        }
      }
    }
    this._outputTail = k;
  }
  
  private int _writeGenericEscape(int paramInt1, int paramInt2)
    throws IOException
  {
    byte[] arrayOfByte = this._outputBuffer;
    int i = paramInt2 + 1;
    arrayOfByte[paramInt2] = 92;
    int j = i + 1;
    arrayOfByte[i] = 117;
    int m;
    if (paramInt1 > 255)
    {
      int i2 = 0xFF & paramInt1 >> 8;
      int i3 = j + 1;
      arrayOfByte[j] = HEX_CHARS[(i2 >> 4)];
      m = i3 + 1;
      arrayOfByte[i3] = HEX_CHARS[(i2 & 0xF)];
      paramInt1 &= 0xFF;
    }
    for (;;)
    {
      int n = m + 1;
      arrayOfByte[m] = HEX_CHARS[(paramInt1 >> 4)];
      int i1 = n + 1;
      arrayOfByte[n] = HEX_CHARS[(paramInt1 & 0xF)];
      return i1;
      int k = j + 1;
      arrayOfByte[j] = 48;
      m = k + 1;
      arrayOfByte[k] = 48;
    }
  }
  
  private final void _writeNull()
    throws IOException
  {
    if (4 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
    this._outputTail = (4 + this._outputTail);
  }
  
  private final void _writeQuotedInt(int paramInt)
    throws IOException
  {
    if (13 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
    byte[] arrayOfByte2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte2[j] = 34;
  }
  
  private final void _writeQuotedLong(long paramLong)
    throws IOException
  {
    if (23 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    this._outputTail = NumberOutput.outputLong(paramLong, this._outputBuffer, this._outputTail);
    byte[] arrayOfByte2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte2[j] = 34;
  }
  
  private final void _writeQuotedRaw(String paramString)
    throws IOException
  {
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    writeRaw(paramString);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte2[j] = 34;
  }
  
  private final void _writeQuotedShort(short paramShort)
    throws IOException
  {
    if (8 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    this._outputTail = NumberOutput.outputInt(paramShort, this._outputBuffer, this._outputTail);
    byte[] arrayOfByte2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte2[j] = 34;
  }
  
  private final void _writeSegmentedRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int i = this._outputEnd;
    byte[] arrayOfByte = this._outputBuffer;
    for (;;)
    {
      int m;
      int n;
      if (paramInt1 < paramInt2) {
        do
        {
          int j = paramArrayOfChar[paramInt1];
          if (j >= 128)
          {
            if (3 + this._outputTail >= this._outputEnd) {
              _flushBuffer();
            }
            m = paramInt1 + 1;
            n = paramArrayOfChar[paramInt1];
            if (n >= 2048) {
              break label173;
            }
            int i1 = this._outputTail;
            this._outputTail = (i1 + 1);
            arrayOfByte[i1] = ((byte)(0xC0 | n >> 6));
            int i2 = this._outputTail;
            this._outputTail = (i2 + 1);
            arrayOfByte[i2] = ((byte)(0x80 | n & 0x3F));
            paramInt1 = m;
            break;
          }
          if (this._outputTail >= i) {
            _flushBuffer();
          }
          int k = this._outputTail;
          this._outputTail = (k + 1);
          arrayOfByte[k] = ((byte)j);
          paramInt1++;
        } while (paramInt1 < paramInt2);
      }
      return;
      label173:
      paramInt1 = _outputRawMultiByteChar(n, paramArrayOfChar, m, paramInt2);
    }
  }
  
  private final void _writeStringSegment(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt2 + paramInt1;
    int j = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int k = j;
    int m;
    if (paramInt1 < i)
    {
      m = paramString.charAt(paramInt1);
      if ((m <= 127) && (arrayOfInt[m] == 0)) {}
    }
    else
    {
      this._outputTail = k;
      if (paramInt1 < i)
      {
        if (this._characterEscapes == null) {
          break label107;
        }
        _writeCustomStringSegment2(paramString, paramInt1, i);
      }
    }
    for (;;)
    {
      return;
      int n = k + 1;
      arrayOfByte[k] = ((byte)m);
      paramInt1++;
      k = n;
      break;
      label107:
      if (this._maximumNonEscapedChar == 0) {
        _writeStringSegment2(paramString, paramInt1, i);
      } else {
        _writeStringSegmentASCII2(paramString, paramInt1, i);
      }
    }
  }
  
  private final void _writeStringSegment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = paramInt2 + paramInt1;
    int j = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int k = j;
    int m;
    if (paramInt1 < i)
    {
      m = paramArrayOfChar[paramInt1];
      if ((m <= 127) && (arrayOfInt[m] == 0)) {}
    }
    else
    {
      this._outputTail = k;
      if (paramInt1 < i)
      {
        if (this._characterEscapes == null) {
          break label105;
        }
        _writeCustomStringSegment2(paramArrayOfChar, paramInt1, i);
      }
    }
    for (;;)
    {
      return;
      int n = k + 1;
      arrayOfByte[k] = ((byte)m);
      paramInt1++;
      k = n;
      break;
      label105:
      if (this._maximumNonEscapedChar == 0) {
        _writeStringSegment2(paramArrayOfChar, paramInt1, i);
      } else {
        _writeStringSegmentASCII2(paramArrayOfChar, paramInt1, i);
      }
    }
  }
  
  private final void _writeStringSegment2(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + 6 * (paramInt2 - paramInt1) > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j = i;
    int k = paramInt1;
    while (k < paramInt2)
    {
      int m = k + 1;
      int n = paramString.charAt(k);
      if (n <= 127)
      {
        if (arrayOfInt[n] == 0)
        {
          int i6 = j + 1;
          arrayOfByte[j] = ((byte)n);
          j = i6;
          k = m;
        }
        else
        {
          int i4 = arrayOfInt[n];
          if (i4 > 0)
          {
            int i5 = j + 1;
            arrayOfByte[j] = 92;
            j = i5 + 1;
            arrayOfByte[i5] = ((byte)i4);
            k = m;
          }
          else
          {
            j = _writeGenericEscape(n, j);
            k = m;
          }
        }
      }
      else
      {
        int i3;
        if (n <= 2047)
        {
          int i2 = j + 1;
          arrayOfByte[j] = ((byte)(0xC0 | n >> 6));
          i3 = i2 + 1;
          arrayOfByte[i2] = ((byte)(0x80 | n & 0x3F));
        }
        for (int i1 = i3;; i1 = _outputMultiByteChar(n, j))
        {
          j = i1;
          k = m;
          break;
        }
      }
    }
    this._outputTail = j;
  }
  
  private final void _writeStringSegment2(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + 6 * (paramInt2 - paramInt1) > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j = i;
    int k = paramInt1;
    while (k < paramInt2)
    {
      int m = k + 1;
      int n = paramArrayOfChar[k];
      if (n <= 127)
      {
        if (arrayOfInt[n] == 0)
        {
          int i6 = j + 1;
          arrayOfByte[j] = ((byte)n);
          j = i6;
          k = m;
        }
        else
        {
          int i4 = arrayOfInt[n];
          if (i4 > 0)
          {
            int i5 = j + 1;
            arrayOfByte[j] = 92;
            j = i5 + 1;
            arrayOfByte[i5] = ((byte)i4);
            k = m;
          }
          else
          {
            j = _writeGenericEscape(n, j);
            k = m;
          }
        }
      }
      else
      {
        int i3;
        if (n <= 2047)
        {
          int i2 = j + 1;
          arrayOfByte[j] = ((byte)(0xC0 | n >> 6));
          i3 = i2 + 1;
          arrayOfByte[i2] = ((byte)(0x80 | n & 0x3F));
        }
        for (int i1 = i3;; i1 = _outputMultiByteChar(n, j))
        {
          j = i1;
          k = m;
          break;
        }
      }
    }
    this._outputTail = j;
  }
  
  private final void _writeStringSegmentASCII2(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + 6 * (paramInt2 - paramInt1) > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j = this._maximumNonEscapedChar;
    int k = i;
    int m = paramInt1;
    while (m < paramInt2)
    {
      int n = m + 1;
      int i1 = paramString.charAt(m);
      if (i1 <= 127)
      {
        if (arrayOfInt[i1] == 0)
        {
          int i7 = k + 1;
          arrayOfByte[k] = ((byte)i1);
          k = i7;
          m = n;
        }
        else
        {
          int i5 = arrayOfInt[i1];
          if (i5 > 0)
          {
            int i6 = k + 1;
            arrayOfByte[k] = 92;
            k = i6 + 1;
            arrayOfByte[i6] = ((byte)i5);
            m = n;
          }
          else
          {
            k = _writeGenericEscape(i1, k);
            m = n;
          }
        }
      }
      else if (i1 > j)
      {
        k = _writeGenericEscape(i1, k);
        m = n;
      }
      else
      {
        int i4;
        if (i1 <= 2047)
        {
          int i3 = k + 1;
          arrayOfByte[k] = ((byte)(0xC0 | i1 >> 6));
          i4 = i3 + 1;
          arrayOfByte[i3] = ((byte)(0x80 | i1 & 0x3F));
        }
        for (int i2 = i4;; i2 = _outputMultiByteChar(i1, k))
        {
          k = i2;
          m = n;
          break;
        }
      }
    }
    this._outputTail = k;
  }
  
  private final void _writeStringSegmentASCII2(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + 6 * (paramInt2 - paramInt1) > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j = this._maximumNonEscapedChar;
    int k = i;
    int m = paramInt1;
    while (m < paramInt2)
    {
      int n = m + 1;
      int i1 = paramArrayOfChar[m];
      if (i1 <= 127)
      {
        if (arrayOfInt[i1] == 0)
        {
          int i7 = k + 1;
          arrayOfByte[k] = ((byte)i1);
          k = i7;
          m = n;
        }
        else
        {
          int i5 = arrayOfInt[i1];
          if (i5 > 0)
          {
            int i6 = k + 1;
            arrayOfByte[k] = 92;
            k = i6 + 1;
            arrayOfByte[i6] = ((byte)i5);
            m = n;
          }
          else
          {
            k = _writeGenericEscape(i1, k);
            m = n;
          }
        }
      }
      else if (i1 > j)
      {
        k = _writeGenericEscape(i1, k);
        m = n;
      }
      else
      {
        int i4;
        if (i1 <= 2047)
        {
          int i3 = k + 1;
          arrayOfByte[k] = ((byte)(0xC0 | i1 >> 6));
          i4 = i3 + 1;
          arrayOfByte[i3] = ((byte)(0x80 | i1 & 0x3F));
        }
        for (int i2 = i4;; i2 = _outputMultiByteChar(i1, k))
        {
          k = i2;
          m = n;
          break;
        }
      }
    }
    this._outputTail = k;
  }
  
  private final void _writeStringSegments(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    do
    {
      int i = Math.min(this._outputMaxContiguous, paramInt2);
      if (i + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramString, paramInt1, i);
      paramInt1 += i;
      paramInt2 -= i;
    } while (paramInt2 > 0);
  }
  
  private final void _writeStringSegments(String paramString, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte2 = this._outputBuffer;
      int n = this._outputTail;
      this._outputTail = (n + 1);
      arrayOfByte2[n] = 34;
    }
    int i = paramString.length();
    int j = 0;
    while (i > 0)
    {
      int m = Math.min(this._outputMaxContiguous, i);
      if (m + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramString, j, m);
      j += m;
      i -= m;
    }
    if (paramBoolean)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte1 = this._outputBuffer;
      int k = this._outputTail;
      this._outputTail = (k + 1);
      arrayOfByte1[k] = 34;
    }
  }
  
  private final void _writeStringSegments(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    do
    {
      int i = Math.min(this._outputMaxContiguous, paramInt2);
      if (i + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramArrayOfChar, paramInt1, i);
      paramInt1 += i;
      paramInt2 -= i;
    } while (paramInt2 > 0);
  }
  
  private final void _writeUTF8Segment(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int[] arrayOfInt = this._outputEscapes;
    int i = paramInt1 + paramInt2;
    int j = paramInt1;
    int k;
    if (j < i)
    {
      k = j + 1;
      int m = paramArrayOfByte[j];
      if ((m >= 0) && (arrayOfInt[m] != 0)) {
        _writeUTF8Segment2(paramArrayOfByte, paramInt1, paramInt2);
      }
    }
    for (;;)
    {
      return;
      j = k;
      break;
      if (paramInt2 + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      System.arraycopy(paramArrayOfByte, paramInt1, this._outputBuffer, this._outputTail, paramInt2);
      this._outputTail = (paramInt2 + this._outputTail);
    }
  }
  
  private final void _writeUTF8Segment2(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int i = this._outputTail;
    if (i + paramInt2 * 6 > this._outputEnd)
    {
      _flushBuffer();
      i = this._outputTail;
    }
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j = paramInt2 + paramInt1;
    int k = i;
    int m = paramInt1;
    while (m < j)
    {
      int n = m + 1;
      int i1 = paramArrayOfByte[m];
      if ((i1 < 0) || (arrayOfInt[i1] == 0))
      {
        int i2 = k + 1;
        arrayOfByte[k] = i1;
        k = i2;
        m = n;
      }
      else
      {
        int i3 = arrayOfInt[i1];
        int i6;
        if (i3 > 0)
        {
          int i5 = k + 1;
          arrayOfByte[k] = 92;
          i6 = i5 + 1;
          arrayOfByte[i5] = ((byte)i3);
        }
        for (int i4 = i6;; i4 = _writeGenericEscape(i1, k))
        {
          k = i4;
          m = n;
          break;
        }
      }
    }
    this._outputTail = k;
  }
  
  private final void _writeUTF8Segments(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    do
    {
      int i = Math.min(this._outputMaxContiguous, paramInt2);
      _writeUTF8Segment(paramArrayOfByte, paramInt1, i);
      paramInt1 += i;
      paramInt2 -= i;
    } while (paramInt2 > 0);
  }
  
  private final void _writeUnq(SerializableString paramSerializableString)
    throws IOException
  {
    int i = paramSerializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (i < 0) {
      _writeBytes(paramSerializableString.asQuotedUTF8());
    }
    for (;;)
    {
      return;
      this._outputTail = (i + this._outputTail);
    }
  }
  
  protected final void _flushBuffer()
    throws IOException
  {
    int i = this._outputTail;
    if (i > 0)
    {
      this._outputTail = 0;
      this._outputStream.write(this._outputBuffer, 0, i);
    }
  }
  
  protected final void _outputSurrogates(int paramInt1, int paramInt2)
    throws IOException
  {
    int i = _decodeSurrogate(paramInt1, paramInt2);
    if (4 + this._outputTail > this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte[j] = ((byte)(0xF0 | i >> 18));
    int k = this._outputTail;
    this._outputTail = (k + 1);
    arrayOfByte[k] = ((byte)(0x80 | 0x3F & i >> 12));
    int m = this._outputTail;
    this._outputTail = (m + 1);
    arrayOfByte[m] = ((byte)(0x80 | 0x3F & i >> 6));
    int n = this._outputTail;
    this._outputTail = (n + 1);
    arrayOfByte[n] = ((byte)(0x80 | i & 0x3F));
  }
  
  protected void _releaseBuffers()
  {
    byte[] arrayOfByte = this._outputBuffer;
    if ((arrayOfByte != null) && (this._bufferRecyclable))
    {
      this._outputBuffer = null;
      this._ioContext.releaseWriteEncodingBuffer(arrayOfByte);
    }
    char[] arrayOfChar = this._charBuffer;
    if (arrayOfChar != null)
    {
      this._charBuffer = null;
      this._ioContext.releaseConcatBuffer(arrayOfChar);
    }
  }
  
  protected final void _verifyPrettyValueWrite(String paramString, int paramInt)
    throws IOException
  {
    switch (paramInt)
    {
    default: 
      _throwInternal();
    }
    for (;;)
    {
      return;
      this._cfgPrettyPrinter.writeArrayValueSeparator(this);
      continue;
      this._cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
      continue;
      this._cfgPrettyPrinter.writeRootValueSeparator(this);
      continue;
      if (this._writeContext.inArray()) {
        this._cfgPrettyPrinter.beforeArrayValues(this);
      } else if (this._writeContext.inObject()) {
        this._cfgPrettyPrinter.beforeObjectEntries(this);
      }
    }
  }
  
  protected final void _verifyValueWrite(String paramString)
    throws IOException
  {
    int i = this._writeContext.writeValue();
    if (i == 5) {
      _reportError("Can not " + paramString + ", expecting field name");
    }
    if (this._cfgPrettyPrinter == null) {
      switch (i)
      {
      }
    }
    for (;;)
    {
      return;
      for (int j = 44;; j = 58)
      {
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        this._outputBuffer[this._outputTail] = j;
        this._outputTail = (1 + this._outputTail);
        break;
      }
      if (this._rootValueSeparator != null)
      {
        byte[] arrayOfByte = this._rootValueSeparator.asUnquotedUTF8();
        if (arrayOfByte.length > 0)
        {
          _writeBytes(arrayOfByte);
          continue;
          _verifyPrettyValueWrite(paramString, i);
        }
      }
    }
  }
  
  protected final int _writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, byte[] paramArrayOfByte)
    throws IOException, JsonGenerationException
  {
    int i = 0;
    int j = 0;
    int k = -3;
    int m = 0;
    int n = -6 + this._outputEnd;
    int i1 = paramBase64Variant.getMaxLineLength() >> 2;
    for (;;)
    {
      if (i > k)
      {
        j = _readMore(paramInputStream, paramArrayOfByte, i, j, paramArrayOfByte.length);
        i = 0;
        if (j < 3)
        {
          if (j < 0)
          {
            if (this._outputTail > n) {
              _flushBuffer();
            }
            int i10 = 0 + 1;
            int i11 = paramArrayOfByte[0] << 16;
            int i12 = 1;
            if (i10 < j)
            {
              i11 |= (0xFF & paramArrayOfByte[i10]) << 8;
              i12 = 2;
            }
            m += i12;
            this._outputTail = paramBase64Variant.encodeBase64Partial(i11, i12, this._outputBuffer, this._outputTail);
          }
          return m;
        }
        k = j - 3;
      }
      if (this._outputTail > n) {
        _flushBuffer();
      }
      int i2 = i + 1;
      int i3 = paramArrayOfByte[i] << 8;
      int i4 = i2 + 1;
      int i5 = (i3 | 0xFF & paramArrayOfByte[i2]) << 8;
      int i6 = i4 + 1;
      int i7 = i5 | 0xFF & paramArrayOfByte[i4];
      m += 3;
      this._outputTail = paramBase64Variant.encodeBase64Chunk(i7, this._outputBuffer, this._outputTail);
      i1--;
      if (i1 <= 0)
      {
        byte[] arrayOfByte1 = this._outputBuffer;
        int i8 = this._outputTail;
        this._outputTail = (i8 + 1);
        arrayOfByte1[i8] = 92;
        byte[] arrayOfByte2 = this._outputBuffer;
        int i9 = this._outputTail;
        this._outputTail = (i9 + 1);
        arrayOfByte2[i9] = 110;
        i1 = paramBase64Variant.getMaxLineLength() >> 2;
      }
      i = i6;
    }
  }
  
  protected final int _writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt)
    throws IOException, JsonGenerationException
  {
    int i = 0;
    int j = 0;
    int k = -3;
    int m = -6 + this._outputEnd;
    int n = paramBase64Variant.getMaxLineLength() >> 2;
    int i3;
    if (paramInt > 2)
    {
      if (i <= k) {
        break label175;
      }
      j = _readMore(paramInputStream, paramArrayOfByte, i, j, paramInt);
      i = 0;
      if (j >= 3) {}
    }
    else if (paramInt > 0)
    {
      int i1 = _readMore(paramInputStream, paramArrayOfByte, i, j, paramInt);
      if (i1 > 0)
      {
        if (this._outputTail > m) {
          _flushBuffer();
        }
        int i2 = 0 + 1;
        i3 = paramArrayOfByte[0] << 16;
        if (i2 >= i1) {
          break label342;
        }
        i3 |= (0xFF & paramArrayOfByte[i2]) << 8;
      }
    }
    label175:
    label342:
    for (int i4 = 2;; i4 = 1)
    {
      this._outputTail = paramBase64Variant.encodeBase64Partial(i3, i4, this._outputBuffer, this._outputTail);
      paramInt -= i4;
      return paramInt;
      k = j - 3;
      if (this._outputTail > m) {
        _flushBuffer();
      }
      int i5 = i + 1;
      int i6 = paramArrayOfByte[i] << 8;
      int i7 = i5 + 1;
      int i8 = (i6 | 0xFF & paramArrayOfByte[i5]) << 8;
      int i9 = i7 + 1;
      int i10 = i8 | 0xFF & paramArrayOfByte[i7];
      paramInt -= 3;
      this._outputTail = paramBase64Variant.encodeBase64Chunk(i10, this._outputBuffer, this._outputTail);
      n--;
      if (n <= 0)
      {
        byte[] arrayOfByte1 = this._outputBuffer;
        int i11 = this._outputTail;
        this._outputTail = (i11 + 1);
        arrayOfByte1[i11] = 92;
        byte[] arrayOfByte2 = this._outputBuffer;
        int i12 = this._outputTail;
        this._outputTail = (i12 + 1);
        arrayOfByte2[i12] = 110;
        n = paramBase64Variant.getMaxLineLength() >> 2;
      }
      i = i9;
      break;
    }
  }
  
  protected final void _writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int i = paramInt2 - 3;
    int j = -6 + this._outputEnd;
    int k = paramBase64Variant.getMaxLineLength() >> 2;
    int i7;
    for (int m = paramInt1; m <= i; m = i7)
    {
      if (this._outputTail > j) {
        _flushBuffer();
      }
      int i3 = m + 1;
      int i4 = paramArrayOfByte[m] << 8;
      int i5 = i3 + 1;
      int i6 = (i4 | 0xFF & paramArrayOfByte[i3]) << 8;
      i7 = i5 + 1;
      this._outputTail = paramBase64Variant.encodeBase64Chunk(i6 | 0xFF & paramArrayOfByte[i5], this._outputBuffer, this._outputTail);
      k--;
      if (k <= 0)
      {
        byte[] arrayOfByte1 = this._outputBuffer;
        int i8 = this._outputTail;
        this._outputTail = (i8 + 1);
        arrayOfByte1[i8] = 92;
        byte[] arrayOfByte2 = this._outputBuffer;
        int i9 = this._outputTail;
        this._outputTail = (i9 + 1);
        arrayOfByte2[i9] = 110;
        k = paramBase64Variant.getMaxLineLength() >> 2;
      }
    }
    int n = paramInt2 - m;
    if (n > 0)
    {
      if (this._outputTail > j) {
        _flushBuffer();
      }
      int i1 = m + 1;
      int i2 = paramArrayOfByte[m] << 16;
      if (n == 2)
      {
        (i1 + 1);
        i2 |= (0xFF & paramArrayOfByte[i1]) << 8;
      }
      this._outputTail = paramBase64Variant.encodeBase64Partial(i2, n, this._outputBuffer, this._outputTail);
    }
    for (;;)
    {
      return;
    }
  }
  
  protected final void _writePPFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    int i = 1;
    int j = this._writeContext.writeFieldName(paramSerializableString.getValue());
    if (j == 4) {
      _reportError("Can not write a field name, expecting a value");
    }
    if (j == i)
    {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
      if (this._cfgUnqNames) {
        break label166;
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        byte[] arrayOfByte2 = this._outputBuffer;
        int m = this._outputTail;
        this._outputTail = (m + 1);
        arrayOfByte2[m] = 34;
      }
      _writeBytes(paramSerializableString.asQuotedUTF8());
      if (i != 0)
      {
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        byte[] arrayOfByte1 = this._outputBuffer;
        int k = this._outputTail;
        this._outputTail = (k + 1);
        arrayOfByte1[k] = 34;
      }
      return;
      this._cfgPrettyPrinter.beforeObjectEntries(this);
      break;
      label166:
      i = 0;
    }
  }
  
  protected final void _writePPFieldName(String paramString)
    throws IOException
  {
    int i = this._writeContext.writeFieldName(paramString);
    if (i == 4) {
      _reportError("Can not write a field name, expecting a value");
    }
    if (i == 1)
    {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
      if (!this._cfgUnqNames) {
        break label63;
      }
      _writeStringSegments(paramString, false);
    }
    label63:
    int j;
    for (;;)
    {
      return;
      this._cfgPrettyPrinter.beforeObjectEntries(this);
      break;
      j = paramString.length();
      if (j <= this._charBufferLength) {
        break label85;
      }
      _writeStringSegments(paramString, true);
    }
    label85:
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int k = this._outputTail;
    this._outputTail = (k + 1);
    arrayOfByte1[k] = 34;
    paramString.getChars(0, j, this._charBuffer, 0);
    if (j <= this._outputMaxContiguous)
    {
      if (j + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(this._charBuffer, 0, j);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte2 = this._outputBuffer;
      int m = this._outputTail;
      this._outputTail = (m + 1);
      arrayOfByte2[m] = 34;
      break;
      _writeStringSegments(this._charBuffer, 0, j);
    }
  }
  
  public void close()
    throws IOException
  {
    super.close();
    if ((this._outputBuffer != null) && (isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT))) {
      for (;;)
      {
        JsonWriteContext localJsonWriteContext = getOutputContext();
        if (localJsonWriteContext.inArray())
        {
          writeEndArray();
        }
        else
        {
          if (!localJsonWriteContext.inObject()) {
            break;
          }
          writeEndObject();
        }
      }
    }
    _flushBuffer();
    this._outputTail = 0;
    if (this._outputStream != null)
    {
      if ((!this._ioContext.isResourceManaged()) && (!isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET))) {
        break label102;
      }
      this._outputStream.close();
    }
    for (;;)
    {
      _releaseBuffers();
      return;
      label102:
      if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
        this._outputStream.flush();
      }
    }
  }
  
  public void flush()
    throws IOException
  {
    _flushBuffer();
    if ((this._outputStream != null) && (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))) {
      this._outputStream.flush();
    }
  }
  
  public int getOutputBuffered()
  {
    return this._outputTail;
  }
  
  public Object getOutputTarget()
  {
    return this._outputStream;
  }
  
  public int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a binary value");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    arrayOfByte2 = this._ioContext.allocBase64Buffer();
    if (paramInt < 0) {}
    for (;;)
    {
      try
      {
        int n = _writeBinary(paramBase64Variant, paramInputStream, arrayOfByte2);
        k = n;
        this._ioContext.releaseBase64Buffer(arrayOfByte2);
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        byte[] arrayOfByte3 = this._outputBuffer;
        int m = this._outputTail;
        this._outputTail = (m + 1);
        arrayOfByte3[m] = 34;
        return k;
      }
      finally
      {
        int k;
        int j;
        this._ioContext.releaseBase64Buffer(arrayOfByte2);
      }
      j = _writeBinary(paramBase64Variant, paramInputStream, arrayOfByte2, paramInt);
      if (j > 0) {
        _reportError("Too few bytes available: missing " + j + " bytes (out of " + paramInt + ")");
      }
      k = paramInt;
    }
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a binary value");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    _writeBinary(paramBase64Variant, paramArrayOfByte, paramInt1, paramInt1 + paramInt2);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte2[j] = 34;
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a boolean value");
    if (5 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    if (paramBoolean) {}
    for (byte[] arrayOfByte = TRUE_BYTES;; arrayOfByte = FALSE_BYTES)
    {
      int i = arrayOfByte.length;
      System.arraycopy(arrayOfByte, 0, this._outputBuffer, this._outputTail, i);
      this._outputTail = (i + this._outputTail);
      return;
    }
  }
  
  public final void writeEndArray()
    throws IOException
  {
    if (!this._writeContext.inArray()) {
      _reportError("Current context not an ARRAY but " + this._writeContext.getTypeDesc());
    }
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
    }
    for (;;)
    {
      this._writeContext = this._writeContext.getParent();
      return;
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 93;
    }
  }
  
  public final void writeEndObject()
    throws IOException
  {
    if (!this._writeContext.inObject()) {
      _reportError("Current context not an object but " + this._writeContext.getTypeDesc());
    }
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
    }
    for (;;)
    {
      this._writeContext = this._writeContext.getParent();
      return;
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 125;
    }
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(paramSerializableString);
    }
    for (;;)
    {
      return;
      int i = this._writeContext.writeFieldName(paramSerializableString.getValue());
      if (i == 4) {
        _reportError("Can not write a field name, expecting a value");
      }
      if (i == 1)
      {
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        byte[] arrayOfByte3 = this._outputBuffer;
        int n = this._outputTail;
        this._outputTail = (n + 1);
        arrayOfByte3[n] = 44;
      }
      if (!this._cfgUnqNames) {
        break;
      }
      _writeUnq(paramSerializableString);
    }
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte1[j] = 34;
    int k = paramSerializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (k < 0) {
      _writeBytes(paramSerializableString.asQuotedUTF8());
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte2 = this._outputBuffer;
      int m = this._outputTail;
      this._outputTail = (m + 1);
      arrayOfByte2[m] = 34;
      break;
      this._outputTail = (k + this._outputTail);
    }
  }
  
  public void writeFieldName(String paramString)
    throws IOException
  {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(paramString);
    }
    int j;
    for (;;)
    {
      return;
      int i = this._writeContext.writeFieldName(paramString);
      if (i == 4) {
        _reportError("Can not write a field name, expecting a value");
      }
      if (i == 1)
      {
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        byte[] arrayOfByte3 = this._outputBuffer;
        int n = this._outputTail;
        this._outputTail = (n + 1);
        arrayOfByte3[n] = 44;
      }
      if (this._cfgUnqNames)
      {
        _writeStringSegments(paramString, false);
      }
      else
      {
        j = paramString.length();
        if (j <= this._charBufferLength) {
          break;
        }
        _writeStringSegments(paramString, true);
      }
    }
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int k = this._outputTail;
    this._outputTail = (k + 1);
    arrayOfByte1[k] = 34;
    if (j <= this._outputMaxContiguous)
    {
      if (j + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramString, 0, j);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte2 = this._outputBuffer;
      int m = this._outputTail;
      this._outputTail = (m + 1);
      arrayOfByte2[m] = 34;
      break;
      _writeStringSegments(paramString, 0, j);
    }
  }
  
  public void writeNull()
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a null");
    _writeNull();
  }
  
  public void writeNumber(double paramDouble)
    throws IOException, JsonGenerationException
  {
    if ((this._cfgNumbersAsStrings) || (((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) && (isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS)))) {
      writeString(String.valueOf(paramDouble));
    }
    for (;;)
    {
      return;
      _verifyValueWrite("write a number");
      writeRaw(String.valueOf(paramDouble));
    }
  }
  
  public void writeNumber(float paramFloat)
    throws IOException, JsonGenerationException
  {
    if ((this._cfgNumbersAsStrings) || (((Float.isNaN(paramFloat)) || (Float.isInfinite(paramFloat))) && (isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS)))) {
      writeString(String.valueOf(paramFloat));
    }
    for (;;)
    {
      return;
      _verifyValueWrite("write a number");
      writeRaw(String.valueOf(paramFloat));
    }
  }
  
  public void writeNumber(int paramInt)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (11 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    if (this._cfgNumbersAsStrings) {
      _writeQuotedInt(paramInt);
    }
    for (;;)
    {
      return;
      this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
    }
  }
  
  public void writeNumber(long paramLong)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedLong(paramLong);
    }
    for (;;)
    {
      return;
      if (21 + this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      this._outputTail = NumberOutput.outputLong(paramLong, this._outputBuffer, this._outputTail);
    }
  }
  
  public void writeNumber(String paramString)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedRaw(paramString);
    }
    for (;;)
    {
      return;
      writeRaw(paramString);
    }
  }
  
  public void writeNumber(BigDecimal paramBigDecimal)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (paramBigDecimal == null) {
      _writeNull();
    }
    for (;;)
    {
      return;
      if (this._cfgNumbersAsStrings)
      {
        if (isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)) {}
        for (String str = paramBigDecimal.toPlainString();; str = paramBigDecimal.toString())
        {
          _writeQuotedRaw(str);
          break;
        }
      }
      if (isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)) {
        writeRaw(paramBigDecimal.toPlainString());
      } else {
        writeRaw(paramBigDecimal.toString());
      }
    }
  }
  
  public void writeNumber(BigInteger paramBigInteger)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (paramBigInteger == null) {
      _writeNull();
    }
    for (;;)
    {
      return;
      if (this._cfgNumbersAsStrings) {
        _writeQuotedRaw(paramBigInteger.toString());
      } else {
        writeRaw(paramBigInteger.toString());
      }
    }
  }
  
  public void writeNumber(short paramShort)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (6 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    if (this._cfgNumbersAsStrings) {
      _writeQuotedShort(paramShort);
    }
    for (;;)
    {
      return;
      this._outputTail = NumberOutput.outputInt(paramShort, this._outputBuffer, this._outputTail);
    }
  }
  
  public void writeRaw(char paramChar)
    throws IOException, JsonGenerationException
  {
    if (3 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    if (paramChar <= '')
    {
      int k = this._outputTail;
      this._outputTail = (k + 1);
      arrayOfByte[k] = ((byte)paramChar);
    }
    for (;;)
    {
      return;
      if (paramChar < 'ࠀ')
      {
        int i = this._outputTail;
        this._outputTail = (i + 1);
        arrayOfByte[i] = ((byte)(0xC0 | paramChar >> '\006'));
        int j = this._outputTail;
        this._outputTail = (j + 1);
        arrayOfByte[j] = ((byte)(0x80 | paramChar & 0x3F));
      }
      else
      {
        _outputRawMultiByteChar(paramChar, null, 0, 0);
      }
    }
  }
  
  public void writeRaw(SerializableString paramSerializableString)
    throws IOException, JsonGenerationException
  {
    byte[] arrayOfByte = paramSerializableString.asUnquotedUTF8();
    if (arrayOfByte.length > 0) {
      _writeBytes(arrayOfByte);
    }
  }
  
  public void writeRaw(String paramString)
    throws IOException, JsonGenerationException
  {
    int i = 0;
    int j = paramString.length();
    if (j > 0)
    {
      char[] arrayOfChar = this._charBuffer;
      int k = arrayOfChar.length;
      if (j < k) {}
      for (int m = j;; m = k)
      {
        paramString.getChars(i, i + m, arrayOfChar, 0);
        writeRaw(arrayOfChar, 0, m);
        i += m;
        j -= m;
        break;
      }
    }
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    if (paramInt2 > 0)
    {
      char[] arrayOfChar = this._charBuffer;
      int i = arrayOfChar.length;
      if (paramInt2 < i) {}
      for (int j = paramInt2;; j = i)
      {
        paramString.getChars(paramInt1, paramInt1 + j, arrayOfChar, 0);
        writeRaw(arrayOfChar, 0, j);
        paramInt1 += j;
        paramInt2 -= j;
        break;
      }
    }
  }
  
  public final void writeRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int i = paramInt2 + (paramInt2 + paramInt2);
    if (i + this._outputTail > this._outputEnd) {
      if (this._outputEnd < i) {
        _writeSegmentedRaw(paramArrayOfChar, paramInt1, paramInt2);
      }
    }
    label199:
    label212:
    for (;;)
    {
      return;
      _flushBuffer();
      int j = paramInt2 + paramInt1;
      for (;;)
      {
        if (paramInt1 >= j) {
          break label212;
        }
        int n;
        int i1;
        do
        {
          int k = paramArrayOfChar[paramInt1];
          if (k > 127)
          {
            n = paramInt1 + 1;
            i1 = paramArrayOfChar[paramInt1];
            if (i1 >= 2048) {
              break label199;
            }
            byte[] arrayOfByte2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = (i2 + 1);
            arrayOfByte2[i2] = ((byte)(0xC0 | i1 >> 6));
            byte[] arrayOfByte3 = this._outputBuffer;
            int i3 = this._outputTail;
            this._outputTail = (i3 + 1);
            arrayOfByte3[i3] = ((byte)(0x80 | i1 & 0x3F));
            paramInt1 = n;
            break;
          }
          byte[] arrayOfByte1 = this._outputBuffer;
          int m = this._outputTail;
          this._outputTail = (m + 1);
          arrayOfByte1[m] = ((byte)k);
          paramInt1++;
        } while (paramInt1 < j);
        break;
        paramInt1 = _outputRawMultiByteChar(i1, paramArrayOfChar, n, j);
      }
    }
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    _writeBytes(paramArrayOfByte, paramInt1, paramInt2);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte2[j] = 34;
  }
  
  public void writeRawValue(SerializableString paramSerializableString)
    throws IOException
  {
    _verifyValueWrite("write a raw (unencoded) value");
    byte[] arrayOfByte = paramSerializableString.asUnquotedUTF8();
    if (arrayOfByte.length > 0) {
      _writeBytes(arrayOfByte);
    }
  }
  
  public final void writeStartArray()
    throws IOException
  {
    _verifyValueWrite("start an array");
    this._writeContext = this._writeContext.createChildArrayContext();
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartArray(this);
    }
    for (;;)
    {
      return;
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 91;
    }
  }
  
  public final void writeStartObject()
    throws IOException
  {
    _verifyValueWrite("start an object");
    this._writeContext = this._writeContext.createChildObjectContext();
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeStartObject(this);
    }
    for (;;)
    {
      return;
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 123;
    }
  }
  
  public final void writeString(SerializableString paramSerializableString)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    int j = paramSerializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (j < 0) {
      _writeBytes(paramSerializableString.asQuotedUTF8());
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte2 = this._outputBuffer;
      int k = this._outputTail;
      this._outputTail = (k + 1);
      arrayOfByte2[k] = 34;
      return;
      this._outputTail = (j + this._outputTail);
    }
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (paramString == null) {
      _writeNull();
    }
    for (;;)
    {
      return;
      int i = paramString.length();
      if (i > this._outputMaxContiguous)
      {
        _writeStringSegments(paramString, true);
      }
      else
      {
        if (i + this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        byte[] arrayOfByte1 = this._outputBuffer;
        int j = this._outputTail;
        this._outputTail = (j + 1);
        arrayOfByte1[j] = 34;
        _writeStringSegment(paramString, 0, i);
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        byte[] arrayOfByte2 = this._outputBuffer;
        int k = this._outputTail;
        this._outputTail = (k + 1);
        arrayOfByte2[k] = 34;
      }
    }
  }
  
  public void writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    if (paramInt2 <= this._outputMaxContiguous)
    {
      if (paramInt2 + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramArrayOfChar, paramInt1, paramInt2);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte2 = this._outputBuffer;
      int j = this._outputTail;
      this._outputTail = (j + 1);
      arrayOfByte2[j] = 34;
      return;
      _writeStringSegments(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
  
  public void writeUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte1[i] = 34;
    if (paramInt2 <= this._outputMaxContiguous) {
      _writeUTF8Segment(paramArrayOfByte, paramInt1, paramInt2);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte2 = this._outputBuffer;
      int j = this._outputTail;
      this._outputTail = (j + 1);
      arrayOfByte2[j] = 34;
      return;
      _writeUTF8Segments(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/json/UTF8JsonGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */