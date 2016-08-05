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
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class WriterBasedJsonGenerator
  extends JsonGeneratorImpl
{
  protected static final char[] HEX_CHARS = ;
  protected static final int SHORT_WRITE = 32;
  protected SerializableString _currentEscape;
  protected char[] _entityBuffer;
  protected char[] _outputBuffer;
  protected int _outputEnd;
  protected int _outputHead = 0;
  protected int _outputTail = 0;
  protected final Writer _writer;
  
  public WriterBasedJsonGenerator(IOContext paramIOContext, int paramInt, ObjectCodec paramObjectCodec, Writer paramWriter)
  {
    super(paramIOContext, paramInt, paramObjectCodec);
    this._writer = paramWriter;
    this._outputBuffer = paramIOContext.allocConcatBuffer();
    this._outputEnd = this._outputBuffer.length;
  }
  
  private char[] _allocateEntityBuffer()
  {
    char[] arrayOfChar = new char[14];
    arrayOfChar[0] = '\\';
    arrayOfChar[2] = '\\';
    arrayOfChar[3] = 'u';
    arrayOfChar[4] = '0';
    arrayOfChar[5] = '0';
    arrayOfChar[8] = '\\';
    arrayOfChar[9] = 'u';
    this._entityBuffer = arrayOfChar;
    return arrayOfChar;
  }
  
  private void _appendCharacterEscape(char paramChar, int paramInt)
    throws IOException, JsonGenerationException
  {
    if (paramInt >= 0)
    {
      if (2 + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      char[] arrayOfChar2 = this._outputBuffer;
      int i6 = this._outputTail;
      this._outputTail = (i6 + 1);
      arrayOfChar2[i6] = '\\';
      char[] arrayOfChar3 = this._outputBuffer;
      int i7 = this._outputTail;
      this._outputTail = (i7 + 1);
      arrayOfChar3[i7] = ((char)paramInt);
    }
    for (;;)
    {
      return;
      if (paramInt != -2)
      {
        if (5 + this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        int j = this._outputTail;
        char[] arrayOfChar1 = this._outputBuffer;
        int k = j + 1;
        arrayOfChar1[j] = '\\';
        int m = k + 1;
        arrayOfChar1[k] = 'u';
        int i1;
        if (paramChar > 'ÿ')
        {
          int i4 = 0xFF & paramChar >> '\b';
          int i5 = m + 1;
          arrayOfChar1[m] = HEX_CHARS[(i4 >> 4)];
          i1 = i5 + 1;
          arrayOfChar1[i5] = HEX_CHARS[(i4 & 0xF)];
          paramChar &= 0xFF;
        }
        for (;;)
        {
          int i2 = i1 + 1;
          arrayOfChar1[i1] = HEX_CHARS[(paramChar >> '\004')];
          int i3 = i2 + 1;
          arrayOfChar1[i2] = HEX_CHARS[(paramChar & 0xF)];
          this._outputTail = i3;
          break;
          int n = m + 1;
          arrayOfChar1[m] = '0';
          i1 = n + 1;
          arrayOfChar1[n] = '0';
        }
      }
      String str;
      if (this._currentEscape == null) {
        str = this._characterEscapes.getEscapeSequence(paramChar).getValue();
      }
      int i;
      for (;;)
      {
        i = str.length();
        if (i + this._outputTail <= this._outputEnd) {
          break label357;
        }
        _flushBuffer();
        if (i <= this._outputEnd) {
          break label357;
        }
        this._writer.write(str);
        break;
        str = this._currentEscape.getValue();
        this._currentEscape = null;
      }
      label357:
      str.getChars(0, i, this._outputBuffer, this._outputTail);
      this._outputTail = (i + this._outputTail);
    }
  }
  
  private int _prependOrWriteCharacterEscape(char[] paramArrayOfChar, int paramInt1, int paramInt2, char paramChar, int paramInt3)
    throws IOException, JsonGenerationException
  {
    int j;
    if (paramInt3 >= 0)
    {
      if ((paramInt1 > 1) && (paramInt1 < paramInt2))
      {
        paramInt1 -= 2;
        paramArrayOfChar[paramInt1] = '\\';
        paramArrayOfChar[(paramInt1 + 1)] = ((char)paramInt3);
      }
      for (;;)
      {
        j = paramInt1;
        return j;
        char[] arrayOfChar2 = this._entityBuffer;
        if (arrayOfChar2 == null) {
          arrayOfChar2 = _allocateEntityBuffer();
        }
        arrayOfChar2[1] = ((char)paramInt3);
        this._writer.write(arrayOfChar2, 0, 2);
      }
    }
    if (paramInt3 != -2)
    {
      int i2;
      int i4;
      if ((paramInt1 > 5) && (paramInt1 < paramInt2))
      {
        int n = paramInt1 - 6;
        int i1 = n + 1;
        paramArrayOfChar[n] = '\\';
        i2 = i1 + 1;
        paramArrayOfChar[i1] = 'u';
        if (paramChar > 'ÿ')
        {
          int i6 = 0xFF & paramChar >> '\b';
          int i7 = i2 + 1;
          paramArrayOfChar[i2] = HEX_CHARS[(i6 >> 4)];
          i4 = i7 + 1;
          paramArrayOfChar[i7] = HEX_CHARS[(i6 & 0xF)];
          paramChar &= 0xFF;
          label186:
          int i5 = i4 + 1;
          paramArrayOfChar[i4] = HEX_CHARS[(paramChar >> '\004')];
          paramArrayOfChar[i5] = HEX_CHARS[(paramChar & 0xF)];
          paramInt1 = i5 - 5;
        }
      }
      for (;;)
      {
        j = paramInt1;
        break;
        int i3 = i2 + 1;
        paramArrayOfChar[i2] = '0';
        i4 = i3 + 1;
        paramArrayOfChar[i3] = '0';
        break label186;
        char[] arrayOfChar1 = this._entityBuffer;
        if (arrayOfChar1 == null) {
          arrayOfChar1 = _allocateEntityBuffer();
        }
        this._outputHead = this._outputTail;
        if (paramChar > 'ÿ')
        {
          int k = 0xFF & paramChar >> '\b';
          int m = paramChar & 0xFF;
          arrayOfChar1[10] = HEX_CHARS[(k >> 4)];
          arrayOfChar1[11] = HEX_CHARS[(k & 0xF)];
          arrayOfChar1[12] = HEX_CHARS[(m >> 4)];
          arrayOfChar1[13] = HEX_CHARS[(m & 0xF)];
          this._writer.write(arrayOfChar1, 8, 6);
        }
        else
        {
          arrayOfChar1[6] = HEX_CHARS[(paramChar >> '\004')];
          arrayOfChar1[7] = HEX_CHARS[(paramChar & 0xF)];
          this._writer.write(arrayOfChar1, 2, 6);
        }
      }
    }
    String str;
    if (this._currentEscape == null)
    {
      str = this._characterEscapes.getEscapeSequence(paramChar).getValue();
      label442:
      int i = str.length();
      if ((paramInt1 < i) || (paramInt1 >= paramInt2)) {
        break label500;
      }
      paramInt1 -= i;
      str.getChars(0, i, paramArrayOfChar, paramInt1);
    }
    for (;;)
    {
      j = paramInt1;
      break;
      str = this._currentEscape.getValue();
      this._currentEscape = null;
      break label442;
      label500:
      this._writer.write(str);
    }
  }
  
  private void _prependOrWriteCharacterEscape(char paramChar, int paramInt)
    throws IOException, JsonGenerationException
  {
    if (paramInt >= 0) {
      if (this._outputTail >= 2)
      {
        int i7 = -2 + this._outputTail;
        this._outputHead = i7;
        char[] arrayOfChar4 = this._outputBuffer;
        int i8 = i7 + 1;
        arrayOfChar4[i7] = '\\';
        this._outputBuffer[i8] = ((char)paramInt);
      }
    }
    for (;;)
    {
      return;
      char[] arrayOfChar3 = this._entityBuffer;
      if (arrayOfChar3 == null) {
        arrayOfChar3 = _allocateEntityBuffer();
      }
      this._outputHead = this._outputTail;
      arrayOfChar3[1] = ((char)paramInt);
      this._writer.write(arrayOfChar3, 0, 2);
      continue;
      if (paramInt != -2)
      {
        if (this._outputTail >= 6)
        {
          char[] arrayOfChar2 = this._outputBuffer;
          int n = -6 + this._outputTail;
          this._outputHead = n;
          arrayOfChar2[n] = '\\';
          int i1 = n + 1;
          arrayOfChar2[i1] = 'u';
          int i3;
          if (paramChar > 'ÿ')
          {
            int i5 = 0xFF & paramChar >> '\b';
            int i6 = i1 + 1;
            arrayOfChar2[i6] = HEX_CHARS[(i5 >> 4)];
            i3 = i6 + 1;
            arrayOfChar2[i3] = HEX_CHARS[(i5 & 0xF)];
            paramChar &= 0xFF;
          }
          for (;;)
          {
            int i4 = i3 + 1;
            arrayOfChar2[i4] = HEX_CHARS[(paramChar >> '\004')];
            arrayOfChar2[(i4 + 1)] = HEX_CHARS[(paramChar & 0xF)];
            break;
            int i2 = i1 + 1;
            arrayOfChar2[i2] = '0';
            i3 = i2 + 1;
            arrayOfChar2[i3] = '0';
          }
        }
        char[] arrayOfChar1 = this._entityBuffer;
        if (arrayOfChar1 == null) {
          arrayOfChar1 = _allocateEntityBuffer();
        }
        this._outputHead = this._outputTail;
        if (paramChar > 'ÿ')
        {
          int k = 0xFF & paramChar >> '\b';
          int m = paramChar & 0xFF;
          arrayOfChar1[10] = HEX_CHARS[(k >> 4)];
          arrayOfChar1[11] = HEX_CHARS[(k & 0xF)];
          arrayOfChar1[12] = HEX_CHARS[(m >> 4)];
          arrayOfChar1[13] = HEX_CHARS[(m & 0xF)];
          this._writer.write(arrayOfChar1, 8, 6);
        }
        else
        {
          arrayOfChar1[6] = HEX_CHARS[(paramChar >> '\004')];
          arrayOfChar1[7] = HEX_CHARS[(paramChar & 0xF)];
          this._writer.write(arrayOfChar1, 2, 6);
        }
      }
      else
      {
        String str;
        if (this._currentEscape == null) {
          str = this._characterEscapes.getEscapeSequence(paramChar).getValue();
        }
        for (;;)
        {
          int i = str.length();
          if (this._outputTail < i) {
            break label528;
          }
          int j = this._outputTail - i;
          this._outputHead = j;
          str.getChars(0, i, this._outputBuffer, j);
          break;
          str = this._currentEscape.getValue();
          this._currentEscape = null;
        }
        label528:
        this._outputHead = this._outputTail;
        this._writer.write(str);
      }
    }
  }
  
  private int _readMore(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
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
  
  private void _writeLongString(String paramString)
    throws IOException
  {
    _flushBuffer();
    int i = paramString.length();
    int j = 0;
    int k = this._outputEnd;
    int m;
    if (j + k > i)
    {
      m = i - j;
      label30:
      paramString.getChars(j, j + m, this._outputBuffer, 0);
      if (this._characterEscapes == null) {
        break label75;
      }
      _writeSegmentCustom(m);
    }
    for (;;)
    {
      j += m;
      if (j < i) {
        break;
      }
      return;
      m = k;
      break label30;
      label75:
      if (this._maximumNonEscapedChar != 0) {
        _writeSegmentASCII(m, this._maximumNonEscapedChar);
      } else {
        _writeSegment(m);
      }
    }
  }
  
  private final void _writeNull()
    throws IOException
  {
    if (4 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    char[] arrayOfChar = this._outputBuffer;
    arrayOfChar[i] = 'n';
    int j = i + 1;
    arrayOfChar[j] = 'u';
    int k = j + 1;
    arrayOfChar[k] = 'l';
    int m = k + 1;
    arrayOfChar[m] = 'l';
    this._outputTail = (m + 1);
  }
  
  private void _writeQuotedInt(int paramInt)
    throws IOException
  {
    if (13 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar1[i] = '"';
    this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
    char[] arrayOfChar2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfChar2[j] = '"';
  }
  
  private void _writeQuotedLong(long paramLong)
    throws IOException
  {
    if (23 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar1[i] = '"';
    this._outputTail = NumberOutput.outputLong(paramLong, this._outputBuffer, this._outputTail);
    char[] arrayOfChar2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfChar2[j] = '"';
  }
  
  private void _writeQuotedRaw(String paramString)
    throws IOException
  {
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar1[i] = '"';
    writeRaw(paramString);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfChar2[j] = '"';
  }
  
  private void _writeQuotedShort(short paramShort)
    throws IOException
  {
    if (8 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar1[i] = '"';
    this._outputTail = NumberOutput.outputInt(paramShort, this._outputBuffer, this._outputTail);
    char[] arrayOfChar2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfChar2[j] = '"';
  }
  
  private void _writeSegment(int paramInt)
    throws IOException
  {
    int[] arrayOfInt = this._outputEscapes;
    int i = arrayOfInt.length;
    int j = 0;
    int m;
    for (int k = 0;; k = _prependOrWriteCharacterEscape(this._outputBuffer, j, paramInt, m, arrayOfInt[m]))
    {
      if (j < paramInt)
      {
        m = this._outputBuffer[j];
        if ((m >= i) || (arrayOfInt[m] == 0)) {
          break label76;
        }
      }
      for (;;)
      {
        int n = j - k;
        if (n <= 0) {
          break label88;
        }
        this._writer.write(this._outputBuffer, k, n);
        if (j < paramInt) {
          break label88;
        }
        return;
        label76:
        j++;
        if (j < paramInt) {
          break;
        }
      }
      label88:
      j++;
    }
  }
  
  private void _writeSegmentASCII(int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int[] arrayOfInt = this._outputEscapes;
    int i = Math.min(arrayOfInt.length, paramInt2 + 1);
    int j = 0;
    int k = 0;
    int n;
    for (int m = 0;; m = _prependOrWriteCharacterEscape(this._outputBuffer, j, paramInt1, n, k))
    {
      if (j < paramInt1)
      {
        n = this._outputBuffer[j];
        if (n >= i) {
          break label91;
        }
        k = arrayOfInt[n];
        if (k == 0) {
          break label104;
        }
      }
      for (;;)
      {
        int i1 = j - m;
        if (i1 <= 0) {
          break label116;
        }
        this._writer.write(this._outputBuffer, m, i1);
        if (j < paramInt1) {
          break label116;
        }
        return;
        label91:
        if (n > paramInt2)
        {
          k = -1;
        }
        else
        {
          label104:
          j++;
          if (j < paramInt1) {
            break;
          }
        }
      }
      label116:
      j++;
    }
  }
  
  private void _writeSegmentCustom(int paramInt)
    throws IOException, JsonGenerationException
  {
    int[] arrayOfInt = this._outputEscapes;
    int i;
    int j;
    CharacterEscapes localCharacterEscapes;
    int k;
    int m;
    if (this._maximumNonEscapedChar < 1)
    {
      i = 65535;
      j = Math.min(arrayOfInt.length, i + 1);
      localCharacterEscapes = this._characterEscapes;
      k = 0;
      m = 0;
    }
    label47:
    int i1;
    for (int n = 0;; n = _prependOrWriteCharacterEscape(this._outputBuffer, k, paramInt, i1, m))
    {
      if (k < paramInt)
      {
        i1 = this._outputBuffer[k];
        if (i1 >= j) {
          break label116;
        }
        m = arrayOfInt[i1];
        if (m == 0) {
          break label156;
        }
      }
      for (;;)
      {
        int i2 = k - n;
        if (i2 <= 0) {
          break label168;
        }
        this._writer.write(this._outputBuffer, n, i2);
        if (k < paramInt) {
          break label168;
        }
        return;
        i = this._maximumNonEscapedChar;
        break;
        label116:
        if (i1 > i)
        {
          m = -1;
        }
        else
        {
          SerializableString localSerializableString = localCharacterEscapes.getEscapeSequence(i1);
          this._currentEscape = localSerializableString;
          if (localSerializableString != null)
          {
            m = -2;
          }
          else
          {
            label156:
            k++;
            if (k < paramInt) {
              break label47;
            }
          }
        }
      }
      label168:
      k++;
    }
  }
  
  private void _writeString(String paramString)
    throws IOException
  {
    int i = paramString.length();
    if (i > this._outputEnd) {
      _writeLongString(paramString);
    }
    for (;;)
    {
      return;
      if (i + this._outputTail > this._outputEnd) {
        _flushBuffer();
      }
      paramString.getChars(0, i, this._outputBuffer, this._outputTail);
      if (this._characterEscapes != null) {
        _writeStringCustom(i);
      } else if (this._maximumNonEscapedChar != 0) {
        _writeStringASCII(i, this._maximumNonEscapedChar);
      } else {
        _writeString2(i);
      }
    }
  }
  
  private void _writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._characterEscapes != null) {
      _writeStringCustom(paramArrayOfChar, paramInt1, paramInt2);
    }
    label61:
    label84:
    label176:
    label195:
    label218:
    for (;;)
    {
      return;
      if (this._maximumNonEscapedChar != 0)
      {
        _writeStringASCII(paramArrayOfChar, paramInt1, paramInt2, this._maximumNonEscapedChar);
      }
      else
      {
        int i = paramInt2 + paramInt1;
        int[] arrayOfInt = this._outputEscapes;
        int j = arrayOfInt.length;
        for (;;)
        {
          if (paramInt1 >= i) {
            break label218;
          }
          int k = paramInt1;
          int m = paramArrayOfChar[paramInt1];
          int n;
          int i1;
          if ((m < j) && (arrayOfInt[m] != 0))
          {
            n = paramInt1;
            i1 = n - k;
            if (i1 >= 32) {
              break label176;
            }
            if (i1 + this._outputTail > this._outputEnd) {
              _flushBuffer();
            }
            if (i1 > 0)
            {
              System.arraycopy(paramArrayOfChar, k, this._outputBuffer, this._outputTail, i1);
              this._outputTail = (i1 + this._outputTail);
            }
          }
          for (;;)
          {
            if (n < i) {
              break label195;
            }
            break;
            paramInt1++;
            if (paramInt1 < i) {
              break label61;
            }
            n = paramInt1;
            break label84;
            _flushBuffer();
            this._writer.write(paramArrayOfChar, k, i1);
          }
          paramInt1 = n + 1;
          char c = paramArrayOfChar[n];
          _appendCharacterEscape(c, arrayOfInt[c]);
        }
      }
    }
  }
  
  private void _writeString2(int paramInt)
    throws IOException
  {
    int i = paramInt + this._outputTail;
    int[] arrayOfInt = this._outputEscapes;
    int j = arrayOfInt.length;
    if (this._outputTail < i)
    {
      int m;
      do
      {
        int k = this._outputBuffer[this._outputTail];
        if ((k < j) && (arrayOfInt[k] != 0))
        {
          int n = this._outputTail - this._outputHead;
          if (n > 0) {
            this._writer.write(this._outputBuffer, this._outputHead, n);
          }
          char[] arrayOfChar = this._outputBuffer;
          int i1 = this._outputTail;
          this._outputTail = (i1 + 1);
          char c = arrayOfChar[i1];
          _prependOrWriteCharacterEscape(c, arrayOfInt[c]);
          break;
        }
        m = 1 + this._outputTail;
        this._outputTail = m;
      } while (m < i);
    }
  }
  
  private void _writeStringASCII(int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int i = paramInt1 + this._outputTail;
    int[] arrayOfInt = this._outputEscapes;
    int j = Math.min(arrayOfInt.length, paramInt2 + 1);
    if (this._outputTail < i)
    {
      label129:
      int m;
      do
      {
        int k = this._outputBuffer[this._outputTail];
        int n;
        if (k < j)
        {
          n = arrayOfInt[k];
          if (n == 0) {}
        }
        else
        {
          for (;;)
          {
            int i1 = this._outputTail - this._outputHead;
            if (i1 > 0) {
              this._writer.write(this._outputBuffer, this._outputHead, i1);
            }
            this._outputTail = (1 + this._outputTail);
            _prependOrWriteCharacterEscape(k, n);
            break;
            if (k <= paramInt2) {
              break label129;
            }
            n = -1;
          }
        }
        m = 1 + this._outputTail;
        this._outputTail = m;
      } while (m < i);
    }
  }
  
  private void _writeStringASCII(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
    throws IOException, JsonGenerationException
  {
    int i = paramInt2 + paramInt1;
    int[] arrayOfInt = this._outputEscapes;
    int j = Math.min(arrayOfInt.length, paramInt3 + 1);
    int k = 0;
    for (;;)
    {
      int m;
      int n;
      label59:
      int i1;
      if (paramInt1 < i)
      {
        m = paramInt1;
        n = paramArrayOfChar[paramInt1];
        if (n >= j) {
          break label129;
        }
        k = arrayOfInt[n];
        if (k == 0) {
          break label143;
        }
        i1 = paramInt1 - m;
        if (i1 >= 32) {
          break label155;
        }
        if (i1 + this._outputTail > this._outputEnd) {
          _flushBuffer();
        }
        if (i1 > 0)
        {
          System.arraycopy(paramArrayOfChar, m, this._outputBuffer, this._outputTail, i1);
          this._outputTail = (i1 + this._outputTail);
        }
      }
      for (;;)
      {
        if (paramInt1 < i) {
          break label174;
        }
        return;
        label129:
        if (n > paramInt3)
        {
          k = -1;
          break label59;
        }
        label143:
        paramInt1++;
        if (paramInt1 < i) {
          break;
        }
        break label59;
        label155:
        _flushBuffer();
        this._writer.write(paramArrayOfChar, m, i1);
      }
      label174:
      paramInt1++;
      _appendCharacterEscape(n, k);
    }
  }
  
  private void _writeStringCustom(int paramInt)
    throws IOException, JsonGenerationException
  {
    int i = paramInt + this._outputTail;
    int[] arrayOfInt = this._outputEscapes;
    int j;
    int k;
    CharacterEscapes localCharacterEscapes;
    if (this._maximumNonEscapedChar < 1)
    {
      j = 65535;
      k = Math.min(arrayOfInt.length, j + 1);
      localCharacterEscapes = this._characterEscapes;
      label41:
      if (this._outputTail >= i) {}
    }
    else
    {
      label182:
      int n;
      do
      {
        int m = this._outputBuffer[this._outputTail];
        int i1;
        if (m < k)
        {
          i1 = arrayOfInt[m];
          if (i1 == 0) {}
        }
        else
        {
          for (;;)
          {
            int i2 = this._outputTail - this._outputHead;
            if (i2 > 0) {
              this._writer.write(this._outputBuffer, this._outputHead, i2);
            }
            this._outputTail = (1 + this._outputTail);
            _prependOrWriteCharacterEscape(m, i1);
            break label41;
            j = this._maximumNonEscapedChar;
            break;
            if (m > j)
            {
              i1 = -1;
            }
            else
            {
              SerializableString localSerializableString = localCharacterEscapes.getEscapeSequence(m);
              this._currentEscape = localSerializableString;
              if (localSerializableString == null) {
                break label182;
              }
              i1 = -2;
            }
          }
        }
        n = 1 + this._outputTail;
        this._outputTail = n;
      } while (n < i);
    }
  }
  
  private void _writeStringCustom(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int i = paramInt2 + paramInt1;
    int[] arrayOfInt = this._outputEscapes;
    int j;
    int k;
    CharacterEscapes localCharacterEscapes;
    int m;
    if (this._maximumNonEscapedChar < 1)
    {
      j = 65535;
      k = Math.min(arrayOfInt.length, j + 1);
      localCharacterEscapes = this._characterEscapes;
      m = 0;
    }
    for (;;)
    {
      int n;
      label53:
      int i1;
      label77:
      int i2;
      if (paramInt1 < i)
      {
        n = paramInt1;
        i1 = paramArrayOfChar[paramInt1];
        if (i1 >= k) {
          break label156;
        }
        m = arrayOfInt[i1];
        if (m == 0) {
          break label197;
        }
        i2 = paramInt1 - n;
        if (i2 >= 32) {
          break label209;
        }
        if (i2 + this._outputTail > this._outputEnd) {
          _flushBuffer();
        }
        if (i2 > 0)
        {
          System.arraycopy(paramArrayOfChar, n, this._outputBuffer, this._outputTail, i2);
          this._outputTail = (i2 + this._outputTail);
        }
      }
      for (;;)
      {
        if (paramInt1 < i) {
          break label228;
        }
        return;
        j = this._maximumNonEscapedChar;
        break;
        label156:
        if (i1 > j)
        {
          m = -1;
          break label77;
        }
        SerializableString localSerializableString = localCharacterEscapes.getEscapeSequence(i1);
        this._currentEscape = localSerializableString;
        if (localSerializableString != null)
        {
          m = -2;
          break label77;
        }
        label197:
        paramInt1++;
        if (paramInt1 < i) {
          break label53;
        }
        break label77;
        label209:
        _flushBuffer();
        this._writer.write(paramArrayOfChar, n, i2);
      }
      label228:
      paramInt1++;
      _appendCharacterEscape(i1, m);
    }
  }
  
  private void writeRawLong(String paramString)
    throws IOException
  {
    int i = this._outputEnd - this._outputTail;
    paramString.getChars(0, i, this._outputBuffer, this._outputTail);
    this._outputTail = (i + this._outputTail);
    _flushBuffer();
    int j = i;
    int k = paramString.length() - i;
    while (k > this._outputEnd)
    {
      int m = this._outputEnd;
      paramString.getChars(j, j + m, this._outputBuffer, 0);
      this._outputHead = 0;
      this._outputTail = m;
      _flushBuffer();
      j += m;
      k -= m;
    }
    paramString.getChars(j, j + k, this._outputBuffer, 0);
    this._outputHead = 0;
    this._outputTail = k;
  }
  
  protected void _flushBuffer()
    throws IOException
  {
    int i = this._outputTail - this._outputHead;
    if (i > 0)
    {
      int j = this._outputHead;
      this._outputHead = 0;
      this._outputTail = 0;
      this._writer.write(this._outputBuffer, j, i);
    }
  }
  
  protected void _releaseBuffers()
  {
    char[] arrayOfChar = this._outputBuffer;
    if (arrayOfChar != null)
    {
      this._outputBuffer = null;
      this._ioContext.releaseConcatBuffer(arrayOfChar);
    }
  }
  
  protected void _verifyPrettyValueWrite(String paramString)
    throws IOException
  {
    int i = this._writeContext.writeValue();
    if (i == 5) {
      _reportError("Can not " + paramString + ", expecting field name");
    }
    switch (i)
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
  
  protected void _verifyValueWrite(String paramString)
    throws IOException
  {
    if (this._cfgPrettyPrinter != null) {
      _verifyPrettyValueWrite(paramString);
    }
    for (;;)
    {
      return;
      int i = this._writeContext.writeValue();
      if (i == 5) {
        _reportError("Can not " + paramString + ", expecting field name");
      }
      switch (i)
      {
      default: 
        break;
      case 1: 
      case 2: 
        for (int j = 44;; j = 58)
        {
          if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
          }
          this._outputBuffer[this._outputTail] = j;
          this._outputTail = (1 + this._outputTail);
          break;
        }
      case 3: 
        if (this._rootValueSeparator != null) {
          writeRaw(this._rootValueSeparator.getValue());
        }
        break;
      }
    }
  }
  
  protected int _writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, byte[] paramArrayOfByte)
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
        char[] arrayOfChar1 = this._outputBuffer;
        int i8 = this._outputTail;
        this._outputTail = (i8 + 1);
        arrayOfChar1[i8] = '\\';
        char[] arrayOfChar2 = this._outputBuffer;
        int i9 = this._outputTail;
        this._outputTail = (i9 + 1);
        arrayOfChar2[i9] = 'n';
        i1 = paramBase64Variant.getMaxLineLength() >> 2;
      }
      i = i6;
    }
  }
  
  protected int _writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt)
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
        char[] arrayOfChar1 = this._outputBuffer;
        int i11 = this._outputTail;
        this._outputTail = (i11 + 1);
        arrayOfChar1[i11] = '\\';
        char[] arrayOfChar2 = this._outputBuffer;
        int i12 = this._outputTail;
        this._outputTail = (i12 + 1);
        arrayOfChar2[i12] = 'n';
        n = paramBase64Variant.getMaxLineLength() >> 2;
      }
      i = i9;
      break;
    }
  }
  
  protected void _writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
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
        char[] arrayOfChar1 = this._outputBuffer;
        int i8 = this._outputTail;
        this._outputTail = (i8 + 1);
        arrayOfChar1[i8] = '\\';
        char[] arrayOfChar2 = this._outputBuffer;
        int i9 = this._outputTail;
        this._outputTail = (i9 + 1);
        arrayOfChar2[i9] = 'n';
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
  
  protected void _writeFieldName(SerializableString paramSerializableString, boolean paramBoolean)
    throws IOException
  {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(paramSerializableString, paramBoolean);
    }
    for (;;)
    {
      return;
      if (1 + this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      if (paramBoolean)
      {
        char[] arrayOfChar5 = this._outputBuffer;
        int n = this._outputTail;
        this._outputTail = (n + 1);
        arrayOfChar5[n] = ',';
      }
      char[] arrayOfChar1 = paramSerializableString.asQuotedChars();
      if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES))
      {
        writeRaw(arrayOfChar1, 0, arrayOfChar1.length);
      }
      else
      {
        char[] arrayOfChar2 = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = (i + 1);
        arrayOfChar2[i] = '"';
        int j = arrayOfChar1.length;
        if (1 + (j + this._outputTail) >= this._outputEnd)
        {
          writeRaw(arrayOfChar1, 0, j);
          if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
          }
          char[] arrayOfChar4 = this._outputBuffer;
          int m = this._outputTail;
          this._outputTail = (m + 1);
          arrayOfChar4[m] = '"';
        }
        else
        {
          System.arraycopy(arrayOfChar1, 0, this._outputBuffer, this._outputTail, j);
          this._outputTail = (j + this._outputTail);
          char[] arrayOfChar3 = this._outputBuffer;
          int k = this._outputTail;
          this._outputTail = (k + 1);
          arrayOfChar3[k] = '"';
        }
      }
    }
  }
  
  protected void _writeFieldName(String paramString, boolean paramBoolean)
    throws IOException
  {
    if (this._cfgPrettyPrinter != null) {
      _writePPFieldName(paramString, paramBoolean);
    }
    for (;;)
    {
      return;
      if (1 + this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      if (paramBoolean)
      {
        char[] arrayOfChar3 = this._outputBuffer;
        int k = this._outputTail;
        this._outputTail = (k + 1);
        arrayOfChar3[k] = ',';
      }
      if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES))
      {
        _writeString(paramString);
      }
      else
      {
        char[] arrayOfChar1 = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = (i + 1);
        arrayOfChar1[i] = '"';
        _writeString(paramString);
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        char[] arrayOfChar2 = this._outputBuffer;
        int j = this._outputTail;
        this._outputTail = (j + 1);
        arrayOfChar2[j] = '"';
      }
    }
  }
  
  protected void _writePPFieldName(SerializableString paramSerializableString, boolean paramBoolean)
    throws IOException, JsonGenerationException
  {
    char[] arrayOfChar1;
    if (paramBoolean)
    {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
      arrayOfChar1 = paramSerializableString.asQuotedChars();
      if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
        break label137;
      }
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      char[] arrayOfChar2 = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfChar2[i] = '"';
      writeRaw(arrayOfChar1, 0, arrayOfChar1.length);
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      char[] arrayOfChar3 = this._outputBuffer;
      int j = this._outputTail;
      this._outputTail = (j + 1);
      arrayOfChar3[j] = '"';
    }
    for (;;)
    {
      return;
      this._cfgPrettyPrinter.beforeObjectEntries(this);
      break;
      label137:
      writeRaw(arrayOfChar1, 0, arrayOfChar1.length);
    }
  }
  
  protected void _writePPFieldName(String paramString, boolean paramBoolean)
    throws IOException, JsonGenerationException
  {
    if (paramBoolean)
    {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
      if (!isEnabled(JsonGenerator.Feature.QUOTE_FIELD_NAMES)) {
        break label125;
      }
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      char[] arrayOfChar1 = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfChar1[i] = '"';
      _writeString(paramString);
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      char[] arrayOfChar2 = this._outputBuffer;
      int j = this._outputTail;
      this._outputTail = (j + 1);
      arrayOfChar2[j] = '"';
    }
    for (;;)
    {
      return;
      this._cfgPrettyPrinter.beforeObjectEntries(this);
      break;
      label125:
      _writeString(paramString);
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
    this._outputHead = 0;
    this._outputTail = 0;
    if (this._writer != null)
    {
      if ((!this._ioContext.isResourceManaged()) && (!isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET))) {
        break label107;
      }
      this._writer.close();
    }
    for (;;)
    {
      _releaseBuffers();
      return;
      label107:
      if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
        this._writer.flush();
      }
    }
  }
  
  public void flush()
    throws IOException
  {
    _flushBuffer();
    if ((this._writer != null) && (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))) {
      this._writer.flush();
    }
  }
  
  public int getOutputBuffered()
  {
    return Math.max(0, this._outputTail - this._outputHead);
  }
  
  public Object getOutputTarget()
  {
    return this._writer;
  }
  
  public int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a binary value");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar1[i] = '"';
    arrayOfByte = this._ioContext.allocBase64Buffer();
    if (paramInt < 0) {}
    for (;;)
    {
      try
      {
        int n = _writeBinary(paramBase64Variant, paramInputStream, arrayOfByte);
        k = n;
        this._ioContext.releaseBase64Buffer(arrayOfByte);
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        char[] arrayOfChar2 = this._outputBuffer;
        int m = this._outputTail;
        this._outputTail = (m + 1);
        arrayOfChar2[m] = '"';
        return k;
      }
      finally
      {
        int k;
        int j;
        this._ioContext.releaseBase64Buffer(arrayOfByte);
      }
      j = _writeBinary(paramBase64Variant, paramInputStream, arrayOfByte, paramInt);
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
    char[] arrayOfChar1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar1[i] = '"';
    _writeBinary(paramBase64Variant, paramArrayOfByte, paramInt1, paramInt1 + paramInt2);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfChar2[j] = '"';
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException
  {
    _verifyValueWrite("write a boolean value");
    if (5 + this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    char[] arrayOfChar = this._outputBuffer;
    int n;
    if (paramBoolean)
    {
      arrayOfChar[i] = 't';
      int i1 = i + 1;
      arrayOfChar[i1] = 'r';
      int i2 = i1 + 1;
      arrayOfChar[i2] = 'u';
      n = i2 + 1;
      arrayOfChar[n] = 'e';
    }
    for (;;)
    {
      this._outputTail = (n + 1);
      return;
      arrayOfChar[i] = 'f';
      int j = i + 1;
      arrayOfChar[j] = 'a';
      int k = j + 1;
      arrayOfChar[k] = 'l';
      int m = k + 1;
      arrayOfChar[m] = 's';
      n = m + 1;
      arrayOfChar[n] = 'e';
    }
  }
  
  public void writeEndArray()
    throws IOException, JsonGenerationException
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
      char[] arrayOfChar = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfChar[i] = ']';
    }
  }
  
  public void writeEndObject()
    throws IOException, JsonGenerationException
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
      char[] arrayOfChar = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfChar[i] = '}';
    }
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    int i = 1;
    int j = this._writeContext.writeFieldName(paramSerializableString.getValue());
    if (j == 4) {
      _reportError("Can not write a field name, expecting a value");
    }
    if (j == i) {}
    for (;;)
    {
      _writeFieldName(paramSerializableString, i);
      return;
      i = 0;
    }
  }
  
  public void writeFieldName(String paramString)
    throws IOException
  {
    int i = 1;
    int j = this._writeContext.writeFieldName(paramString);
    if (j == 4) {
      _reportError("Can not write a field name, expecting a value");
    }
    if (j == i) {}
    for (;;)
    {
      _writeFieldName(paramString, i);
      return;
      i = 0;
    }
  }
  
  public void writeNull()
    throws IOException
  {
    _verifyValueWrite("write a null");
    _writeNull();
  }
  
  public void writeNumber(double paramDouble)
    throws IOException
  {
    if ((this._cfgNumbersAsStrings) || ((isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS)) && ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))))) {
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
    throws IOException
  {
    if ((this._cfgNumbersAsStrings) || ((isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS)) && ((Float.isNaN(paramFloat)) || (Float.isInfinite(paramFloat))))) {
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
    throws IOException
  {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedInt(paramInt);
    }
    for (;;)
    {
      return;
      if (11 + this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
    }
  }
  
  public void writeNumber(long paramLong)
    throws IOException
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
    throws IOException
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
    throws IOException
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
    throws IOException
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
    throws IOException
  {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings) {
      _writeQuotedShort(paramShort);
    }
    for (;;)
    {
      return;
      if (6 + this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      this._outputTail = NumberOutput.outputInt(paramShort, this._outputBuffer, this._outputTail);
    }
  }
  
  public void writeRaw(char paramChar)
    throws IOException
  {
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar[i] = paramChar;
  }
  
  public void writeRaw(SerializableString paramSerializableString)
    throws IOException
  {
    writeRaw(paramSerializableString.getValue());
  }
  
  public void writeRaw(String paramString)
    throws IOException
  {
    int i = paramString.length();
    int j = this._outputEnd - this._outputTail;
    if (j == 0)
    {
      _flushBuffer();
      j = this._outputEnd - this._outputTail;
    }
    if (j >= i)
    {
      paramString.getChars(0, i, this._outputBuffer, this._outputTail);
      this._outputTail = (i + this._outputTail);
    }
    for (;;)
    {
      return;
      writeRawLong(paramString);
    }
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = this._outputEnd - this._outputTail;
    if (i < paramInt2)
    {
      _flushBuffer();
      i = this._outputEnd - this._outputTail;
    }
    if (i >= paramInt2)
    {
      paramString.getChars(paramInt1, paramInt1 + paramInt2, this._outputBuffer, this._outputTail);
      this._outputTail = (paramInt2 + this._outputTail);
    }
    for (;;)
    {
      return;
      writeRawLong(paramString.substring(paramInt1, paramInt1 + paramInt2));
    }
  }
  
  public void writeRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 < 32)
    {
      if (paramInt2 > this._outputEnd - this._outputTail) {
        _flushBuffer();
      }
      System.arraycopy(paramArrayOfChar, paramInt1, this._outputBuffer, this._outputTail, paramInt2);
      this._outputTail = (paramInt2 + this._outputTail);
    }
    for (;;)
    {
      return;
      _flushBuffer();
      this._writer.write(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeStartArray()
    throws IOException, JsonGenerationException
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
      char[] arrayOfChar = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfChar[i] = '[';
    }
  }
  
  public void writeStartObject()
    throws IOException, JsonGenerationException
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
      char[] arrayOfChar = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfChar[i] = '{';
    }
  }
  
  public void writeString(SerializableString paramSerializableString)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar1[i] = '"';
    char[] arrayOfChar2 = paramSerializableString.asQuotedChars();
    int j = arrayOfChar2.length;
    if (j < 32)
    {
      if (j > this._outputEnd - this._outputTail) {
        _flushBuffer();
      }
      System.arraycopy(arrayOfChar2, 0, this._outputBuffer, this._outputTail, j);
      this._outputTail = (j + this._outputTail);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      char[] arrayOfChar3 = this._outputBuffer;
      int k = this._outputTail;
      this._outputTail = (k + 1);
      arrayOfChar3[k] = '"';
      return;
      _flushBuffer();
      this._writer.write(arrayOfChar2, 0, j);
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
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      char[] arrayOfChar1 = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfChar1[i] = '"';
      _writeString(paramString);
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      char[] arrayOfChar2 = this._outputBuffer;
      int j = this._outputTail;
      this._outputTail = (j + 1);
      arrayOfChar2[j] = '"';
    }
  }
  
  public void writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar1 = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfChar1[i] = '"';
    _writeString(paramArrayOfChar, paramInt1, paramInt2);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    char[] arrayOfChar2 = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfChar2[j] = '"';
  }
  
  public void writeUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/json/WriterBasedJsonGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */