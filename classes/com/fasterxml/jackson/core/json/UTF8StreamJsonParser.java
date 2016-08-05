package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class UTF8StreamJsonParser
  extends ParserBase
{
  static final byte BYTE_LF = 10;
  protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
  private static final int[] _icUTF8 = ;
  protected boolean _bufferRecyclable;
  protected byte[] _inputBuffer;
  protected InputStream _inputStream;
  protected ObjectCodec _objectCodec;
  private int _quad1;
  protected int[] _quadBuffer = new int[16];
  protected final ByteQuadsCanonicalizer _symbols;
  protected boolean _tokenIncomplete = false;
  
  public UTF8StreamJsonParser(IOContext paramIOContext, int paramInt1, InputStream paramInputStream, ObjectCodec paramObjectCodec, ByteQuadsCanonicalizer paramByteQuadsCanonicalizer, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    super(paramIOContext, paramInt1);
    this._inputStream = paramInputStream;
    this._objectCodec = paramObjectCodec;
    this._symbols = paramByteQuadsCanonicalizer;
    this._inputBuffer = paramArrayOfByte;
    this._inputPtr = paramInt2;
    this._inputEnd = paramInt3;
    this._currInputRowStart = paramInt2;
    this._currInputProcessed = (-paramInt2);
    this._bufferRecyclable = paramBoolean;
  }
  
  private final void _checkMatchEnd(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (Character.isJavaIdentifierPart((char)_decodeCharForError(paramInt2))) {
      _reportInvalidToken(paramString.substring(0, paramInt1));
    }
  }
  
  private final int _decodeUtf8_2(int paramInt)
    throws IOException
  {
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = arrayOfByte[i];
    if ((j & 0xC0) != 128) {
      _reportInvalidOther(j & 0xFF, this._inputPtr);
    }
    return (paramInt & 0x1F) << 6 | j & 0x3F;
  }
  
  private final int _decodeUtf8_3(int paramInt)
    throws IOException
  {
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    int i = paramInt & 0xF;
    byte[] arrayOfByte1 = this._inputBuffer;
    int j = this._inputPtr;
    this._inputPtr = (j + 1);
    int k = arrayOfByte1[j];
    if ((k & 0xC0) != 128) {
      _reportInvalidOther(k & 0xFF, this._inputPtr);
    }
    int m = i << 6 | k & 0x3F;
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte2 = this._inputBuffer;
    int n = this._inputPtr;
    this._inputPtr = (n + 1);
    int i1 = arrayOfByte2[n];
    if ((i1 & 0xC0) != 128) {
      _reportInvalidOther(i1 & 0xFF, this._inputPtr);
    }
    return m << 6 | i1 & 0x3F;
  }
  
  private final int _decodeUtf8_3fast(int paramInt)
    throws IOException
  {
    int i = paramInt & 0xF;
    byte[] arrayOfByte1 = this._inputBuffer;
    int j = this._inputPtr;
    this._inputPtr = (j + 1);
    int k = arrayOfByte1[j];
    if ((k & 0xC0) != 128) {
      _reportInvalidOther(k & 0xFF, this._inputPtr);
    }
    int m = i << 6 | k & 0x3F;
    byte[] arrayOfByte2 = this._inputBuffer;
    int n = this._inputPtr;
    this._inputPtr = (n + 1);
    int i1 = arrayOfByte2[n];
    if ((i1 & 0xC0) != 128) {
      _reportInvalidOther(i1 & 0xFF, this._inputPtr);
    }
    return m << 6 | i1 & 0x3F;
  }
  
  private final int _decodeUtf8_4(int paramInt)
    throws IOException
  {
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte1 = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = arrayOfByte1[i];
    if ((j & 0xC0) != 128) {
      _reportInvalidOther(j & 0xFF, this._inputPtr);
    }
    int k = (paramInt & 0x7) << 6 | j & 0x3F;
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte2 = this._inputBuffer;
    int m = this._inputPtr;
    this._inputPtr = (m + 1);
    int n = arrayOfByte2[m];
    if ((n & 0xC0) != 128) {
      _reportInvalidOther(n & 0xFF, this._inputPtr);
    }
    int i1 = k << 6 | n & 0x3F;
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte3 = this._inputBuffer;
    int i2 = this._inputPtr;
    this._inputPtr = (i2 + 1);
    int i3 = arrayOfByte3[i2];
    if ((i3 & 0xC0) != 128) {
      _reportInvalidOther(i3 & 0xFF, this._inputPtr);
    }
    return (i1 << 6 | i3 & 0x3F) - 65536;
  }
  
  private final void _finishString2(char[] paramArrayOfChar, int paramInt)
    throws IOException
  {
    int[] arrayOfInt = _icUTF8;
    byte[] arrayOfByte = this._inputBuffer;
    int m;
    int i1;
    for (;;)
    {
      int i = this._inputPtr;
      if (i >= this._inputEnd)
      {
        loadMoreGuaranteed();
        i = this._inputPtr;
      }
      if (paramInt >= paramArrayOfChar.length)
      {
        paramArrayOfChar = this._textBuffer.finishCurrentSegment();
        paramInt = 0;
      }
      int j = Math.min(this._inputEnd, i + (paramArrayOfChar.length - paramInt));
      int k = i;
      int i2;
      for (m = paramInt; k < j; m = i2)
      {
        int n = k + 1;
        i1 = 0xFF & arrayOfByte[k];
        if (arrayOfInt[i1] != 0)
        {
          this._inputPtr = n;
          if (i1 != 34) {
            break label164;
          }
          this._textBuffer.setCurrentLength(m);
          return;
        }
        i2 = m + 1;
        paramArrayOfChar[m] = ((char)i1);
        k = n;
      }
      this._inputPtr = k;
      paramInt = m;
    }
    label164:
    int i4;
    switch (arrayOfInt[i1])
    {
    default: 
      if (i1 < 32)
      {
        _throwUnquotedSpace(i1, "string value");
        i4 = m;
      }
      break;
    }
    for (;;)
    {
      if (i4 >= paramArrayOfChar.length)
      {
        paramArrayOfChar = this._textBuffer.finishCurrentSegment();
        i4 = 0;
      }
      int i5 = i4 + 1;
      paramArrayOfChar[i4] = ((char)i1);
      paramInt = i5;
      break;
      i1 = _decodeEscaped();
      i4 = m;
      continue;
      i1 = _decodeUtf8_2(i1);
      i4 = m;
      continue;
      if (this._inputEnd - this._inputPtr >= 2)
      {
        i1 = _decodeUtf8_3fast(i1);
        i4 = m;
      }
      else
      {
        i1 = _decodeUtf8_3(i1);
        i4 = m;
        continue;
        int i3 = _decodeUtf8_4(i1);
        i4 = m + 1;
        paramArrayOfChar[m] = ((char)(0xD800 | i3 >> 10));
        if (i4 >= paramArrayOfChar.length)
        {
          paramArrayOfChar = this._textBuffer.finishCurrentSegment();
          i4 = 0;
        }
        i1 = 0xDC00 | i3 & 0x3FF;
        continue;
        _reportInvalidChar(i1);
        i4 = m;
      }
    }
  }
  
  private final boolean _isNextTokenNameMaybe(int paramInt, SerializableString paramSerializableString)
    throws IOException
  {
    String str = _parseName(paramInt);
    this._parsingContext.setCurrentName(str);
    boolean bool = str.equals(paramSerializableString.getValue());
    this._currToken = JsonToken.FIELD_NAME;
    int i = _skipColon();
    if (i == 34)
    {
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return bool;
    }
    JsonToken localJsonToken;
    switch (i)
    {
    default: 
      localJsonToken = _handleUnexpectedValue(i);
    }
    for (;;)
    {
      this._nextToken = localJsonToken;
      break;
      localJsonToken = JsonToken.START_ARRAY;
      continue;
      localJsonToken = JsonToken.START_OBJECT;
      continue;
      _matchToken("true", 1);
      localJsonToken = JsonToken.VALUE_TRUE;
      continue;
      _matchToken("false", 1);
      localJsonToken = JsonToken.VALUE_FALSE;
      continue;
      _matchToken("null", 1);
      localJsonToken = JsonToken.VALUE_NULL;
      continue;
      localJsonToken = _parseNegNumber();
      continue;
      localJsonToken = _parsePosNumber(i);
    }
  }
  
  private final void _isNextTokenNameYes(int paramInt)
    throws IOException
  {
    this._currToken = JsonToken.FIELD_NAME;
    switch (paramInt)
    {
    default: 
      this._nextToken = _handleUnexpectedValue(paramInt);
    }
    for (;;)
    {
      return;
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      continue;
      this._nextToken = JsonToken.START_ARRAY;
      continue;
      this._nextToken = JsonToken.START_OBJECT;
      continue;
      _matchToken("true", 1);
      this._nextToken = JsonToken.VALUE_TRUE;
      continue;
      _matchToken("false", 1);
      this._nextToken = JsonToken.VALUE_FALSE;
      continue;
      _matchToken("null", 1);
      this._nextToken = JsonToken.VALUE_NULL;
      continue;
      this._nextToken = _parseNegNumber();
      continue;
      this._nextToken = _parsePosNumber(paramInt);
    }
  }
  
  private final void _matchToken2(String paramString, int paramInt)
    throws IOException
  {
    int i = paramString.length();
    do
    {
      if (((this._inputPtr >= this._inputEnd) && (!loadMore())) || (this._inputBuffer[this._inputPtr] != paramString.charAt(paramInt))) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
      this._inputPtr = (1 + this._inputPtr);
      paramInt++;
    } while (paramInt < i);
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
    for (;;)
    {
      return;
      int j = 0xFF & this._inputBuffer[this._inputPtr];
      if ((j >= 48) && (j != 93) && (j != 125)) {
        _checkMatchEnd(paramString, paramInt, j);
      }
    }
  }
  
  private final JsonToken _nextAfterName()
  {
    this._nameCopied = false;
    JsonToken localJsonToken = this._nextToken;
    this._nextToken = null;
    if (localJsonToken == JsonToken.START_ARRAY) {
      this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
    }
    for (;;)
    {
      this._currToken = localJsonToken;
      return localJsonToken;
      if (localJsonToken == JsonToken.START_OBJECT) {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      }
    }
  }
  
  private final JsonToken _nextTokenNotInObject(int paramInt)
    throws IOException
  {
    JsonToken localJsonToken;
    if (paramInt == 34)
    {
      this._tokenIncomplete = true;
      localJsonToken = JsonToken.VALUE_STRING;
      this._currToken = localJsonToken;
    }
    for (;;)
    {
      return localJsonToken;
      switch (paramInt)
      {
      default: 
        localJsonToken = _handleUnexpectedValue(paramInt);
        this._currToken = localJsonToken;
        break;
      case 91: 
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        localJsonToken = JsonToken.START_ARRAY;
        this._currToken = localJsonToken;
        break;
      case 123: 
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        localJsonToken = JsonToken.START_OBJECT;
        this._currToken = localJsonToken;
        break;
      case 116: 
        _matchToken("true", 1);
        localJsonToken = JsonToken.VALUE_TRUE;
        this._currToken = localJsonToken;
        break;
      case 102: 
        _matchToken("false", 1);
        localJsonToken = JsonToken.VALUE_FALSE;
        this._currToken = localJsonToken;
        break;
      case 110: 
        _matchToken("null", 1);
        localJsonToken = JsonToken.VALUE_NULL;
        this._currToken = localJsonToken;
        break;
      case 45: 
        localJsonToken = _parseNegNumber();
        this._currToken = localJsonToken;
        break;
      case 48: 
      case 49: 
      case 50: 
      case 51: 
      case 52: 
      case 53: 
      case 54: 
      case 55: 
      case 56: 
      case 57: 
        localJsonToken = _parsePosNumber(paramInt);
        this._currToken = localJsonToken;
      }
    }
  }
  
  private final JsonToken _parseFloat(char[] paramArrayOfChar, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
    throws IOException
  {
    int i = 0;
    int j = 0;
    if (paramInt2 == 46)
    {
      int i6 = paramInt1 + 1;
      paramArrayOfChar[paramInt1] = ((char)paramInt2);
      paramInt1 = i6;
      if ((this._inputPtr < this._inputEnd) || (loadMore())) {
        break label365;
      }
      j = 1;
      label46:
      if (i == 0) {
        reportUnexpectedNumberChar(paramInt2, "Decimal point not followed by a digit");
      }
    }
    int k = 0;
    int m;
    int i1;
    if ((paramInt2 == 101) || (paramInt2 == 69))
    {
      if (paramInt1 >= paramArrayOfChar.length)
      {
        paramArrayOfChar = this._textBuffer.finishCurrentSegment();
        paramInt1 = 0;
      }
      m = paramInt1 + 1;
      paramArrayOfChar[paramInt1] = ((char)paramInt2);
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      byte[] arrayOfByte1 = this._inputBuffer;
      int n = this._inputPtr;
      this._inputPtr = (n + 1);
      paramInt2 = 0xFF & arrayOfByte1[n];
      if ((paramInt2 != 45) && (paramInt2 != 43)) {
        break label485;
      }
      if (m < paramArrayOfChar.length) {
        break label478;
      }
      paramArrayOfChar = this._textBuffer.finishCurrentSegment();
      i1 = 0;
      label175:
      int i2 = i1 + 1;
      paramArrayOfChar[i1] = ((char)paramInt2);
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      byte[] arrayOfByte2 = this._inputBuffer;
      int i3 = this._inputPtr;
      this._inputPtr = (i3 + 1);
      paramInt2 = 0xFF & arrayOfByte2[i3];
      paramInt1 = i2;
    }
    for (;;)
    {
      int i4;
      if ((paramInt2 <= 57) && (paramInt2 >= 48))
      {
        k++;
        if (paramInt1 >= paramArrayOfChar.length)
        {
          paramArrayOfChar = this._textBuffer.finishCurrentSegment();
          paramInt1 = 0;
        }
        i4 = paramInt1 + 1;
        paramArrayOfChar[paramInt1] = ((char)paramInt2);
        if ((this._inputPtr >= this._inputEnd) && (!loadMore()))
        {
          j = 1;
          paramInt1 = i4;
        }
      }
      else
      {
        if (k == 0) {
          reportUnexpectedNumberChar(paramInt2, "Exponent indicator not followed by a digit");
        }
        if (j == 0)
        {
          this._inputPtr = (-1 + this._inputPtr);
          if (this._parsingContext.inRoot()) {
            _verifyRootSpace(paramInt2);
          }
        }
        this._textBuffer.setCurrentLength(paramInt1);
        return resetFloat(paramBoolean, paramInt3, i, k);
        label365:
        byte[] arrayOfByte4 = this._inputBuffer;
        int i7 = this._inputPtr;
        this._inputPtr = (i7 + 1);
        paramInt2 = 0xFF & arrayOfByte4[i7];
        if ((paramInt2 < 48) || (paramInt2 > 57)) {
          break label46;
        }
        i++;
        if (paramInt1 >= paramArrayOfChar.length)
        {
          paramArrayOfChar = this._textBuffer.finishCurrentSegment();
          paramInt1 = 0;
        }
        int i8 = paramInt1 + 1;
        paramArrayOfChar[paramInt1] = ((char)paramInt2);
        paramInt1 = i8;
        break;
      }
      byte[] arrayOfByte3 = this._inputBuffer;
      int i5 = this._inputPtr;
      this._inputPtr = (i5 + 1);
      paramInt2 = 0xFF & arrayOfByte3[i5];
      paramInt1 = i4;
      continue;
      label478:
      i1 = m;
      break label175;
      label485:
      paramInt1 = m;
    }
  }
  
  private final JsonToken _parseNumber2(char[] paramArrayOfChar, int paramInt1, boolean paramBoolean, int paramInt2)
    throws IOException
  {
    JsonToken localJsonToken;
    if ((this._inputPtr >= this._inputEnd) && (!loadMore()))
    {
      this._textBuffer.setCurrentLength(paramInt1);
      localJsonToken = resetInt(paramBoolean, paramInt2);
    }
    for (;;)
    {
      return localJsonToken;
      byte[] arrayOfByte1 = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      int j = 0xFF & arrayOfByte1[i];
      if ((j > 57) || (j < 48))
      {
        if ((j == 46) || (j == 101) || (j == 69)) {
          localJsonToken = _parseFloat(paramArrayOfChar, paramInt1, j, paramBoolean, paramInt2);
        }
      }
      else
      {
        if (paramInt1 >= paramArrayOfChar.length)
        {
          paramArrayOfChar = this._textBuffer.finishCurrentSegment();
          paramInt1 = 0;
        }
        int m = paramInt1 + 1;
        paramArrayOfChar[paramInt1] = ((char)j);
        paramInt2++;
        paramInt1 = m;
        break;
      }
      this._inputPtr = (-1 + this._inputPtr);
      this._textBuffer.setCurrentLength(paramInt1);
      if (this._parsingContext.inRoot())
      {
        byte[] arrayOfByte2 = this._inputBuffer;
        int k = this._inputPtr;
        this._inputPtr = (k + 1);
        _verifyRootSpace(0xFF & arrayOfByte2[k]);
      }
      localJsonToken = resetInt(paramBoolean, paramInt2);
    }
  }
  
  private final void _skipCComment()
    throws IOException
  {
    int[] arrayOfInt = CharTypes.getInputCodeComment();
    for (;;)
    {
      int j;
      int k;
      if ((this._inputPtr < this._inputEnd) || (loadMore()))
      {
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = (i + 1);
        j = 0xFF & arrayOfByte[i];
        k = arrayOfInt[j];
        if (k == 0) {}
      }
      else
      {
        switch (k)
        {
        default: 
          _reportInvalidChar(j);
          break;
        case 42: 
          if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
            _reportInvalidEOF(" in a comment");
          }
          for (;;)
          {
            return;
            if (this._inputBuffer[this._inputPtr] != 47) {
              break;
            }
            this._inputPtr = (1 + this._inputPtr);
          }
        case 10: 
          this._currInputRow = (1 + this._currInputRow);
          this._currInputRowStart = this._inputPtr;
          break;
        case 13: 
          _skipCR();
          break;
        case 2: 
          _skipUtf8_2(j);
          break;
        case 3: 
          _skipUtf8_3(j);
          break;
        case 4: 
          _skipUtf8_4(j);
        }
      }
    }
  }
  
  private final int _skipColon()
    throws IOException
  {
    int k;
    if (4 + this._inputPtr >= this._inputEnd) {
      k = _skipColon2(false);
    }
    for (;;)
    {
      return k;
      int i = this._inputBuffer[this._inputPtr];
      if (i == 58)
      {
        byte[] arrayOfByte4 = this._inputBuffer;
        int i1 = 1 + this._inputPtr;
        this._inputPtr = i1;
        k = arrayOfByte4[i1];
        if (k > 32)
        {
          if ((k == 47) || (k == 35)) {
            k = _skipColon2(true);
          } else {
            this._inputPtr = (1 + this._inputPtr);
          }
        }
        else
        {
          if ((k == 32) || (k == 9))
          {
            byte[] arrayOfByte5 = this._inputBuffer;
            int i2 = 1 + this._inputPtr;
            this._inputPtr = i2;
            k = arrayOfByte5[i2];
            if (k > 32)
            {
              if ((k == 47) || (k == 35))
              {
                k = _skipColon2(true);
                continue;
              }
              this._inputPtr = (1 + this._inputPtr);
              continue;
            }
          }
          k = _skipColon2(true);
        }
      }
      else
      {
        if ((i == 32) || (i == 9))
        {
          byte[] arrayOfByte1 = this._inputBuffer;
          int j = 1 + this._inputPtr;
          this._inputPtr = j;
          i = arrayOfByte1[j];
        }
        if (i == 58)
        {
          byte[] arrayOfByte2 = this._inputBuffer;
          int m = 1 + this._inputPtr;
          this._inputPtr = m;
          k = arrayOfByte2[m];
          if (k > 32)
          {
            if ((k == 47) || (k == 35)) {
              k = _skipColon2(true);
            } else {
              this._inputPtr = (1 + this._inputPtr);
            }
          }
          else
          {
            if ((k == 32) || (k == 9))
            {
              byte[] arrayOfByte3 = this._inputBuffer;
              int n = 1 + this._inputPtr;
              this._inputPtr = n;
              k = arrayOfByte3[n];
              if (k > 32)
              {
                if ((k == 47) || (k == 35))
                {
                  k = _skipColon2(true);
                  continue;
                }
                this._inputPtr = (1 + this._inputPtr);
                continue;
              }
            }
            k = _skipColon2(true);
          }
        }
        else
        {
          k = _skipColon2(false);
        }
      }
    }
  }
  
  private final int _skipColon2(boolean paramBoolean)
    throws IOException
  {
    while ((this._inputPtr < this._inputEnd) || (loadMore()))
    {
      byte[] arrayOfByte = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      int j = 0xFF & arrayOfByte[i];
      if (j > 32)
      {
        if (j == 47)
        {
          _skipComment();
        }
        else if ((j != 35) || (!_skipYAMLComment()))
        {
          if (paramBoolean) {
            return j;
          }
          if (j != 58)
          {
            if (j < 32) {
              _throwInvalidSpace(j);
            }
            _reportUnexpectedChar(j, "was expecting a colon to separate field name and value");
          }
          paramBoolean = true;
        }
      }
      else if (j != 32) {
        if (j == 10)
        {
          this._currInputRow = (1 + this._currInputRow);
          this._currInputRowStart = this._inputPtr;
        }
        else if (j == 13)
        {
          _skipCR();
        }
        else if (j != 9)
        {
          _throwInvalidSpace(j);
        }
      }
    }
    throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
  }
  
  private final int _skipColonFast(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte1 = this._inputBuffer;
    int i = paramInt + 1;
    int j = arrayOfByte1[paramInt];
    int i4;
    int i5;
    int m;
    if (j == 58)
    {
      byte[] arrayOfByte5 = this._inputBuffer;
      i4 = i + 1;
      i5 = arrayOfByte5[i];
      if (i5 > 32)
      {
        if ((i5 == 47) || (i5 == 35)) {
          break label143;
        }
        this._inputPtr = i4;
        m = i5;
      }
    }
    for (;;)
    {
      return m;
      if ((i5 == 32) || (i5 == 9))
      {
        byte[] arrayOfByte6 = this._inputBuffer;
        int i6 = i4 + 1;
        int i7 = arrayOfByte6[i4];
        if ((i7 > 32) && (i7 != 47) && (i7 != 35))
        {
          this._inputPtr = i6;
          m = i7;
        }
        else
        {
          i4 = i6;
        }
      }
      else
      {
        label143:
        this._inputPtr = (i4 - 1);
        m = _skipColon2(true);
        continue;
        if ((j == 32) || (j == 9))
        {
          byte[] arrayOfByte2 = this._inputBuffer;
          int k = i + 1;
          j = arrayOfByte2[i];
          i = k;
        }
        if (j == 58)
        {
          byte[] arrayOfByte3 = this._inputBuffer;
          int n = i + 1;
          int i1 = arrayOfByte3[i];
          if (i1 > 32)
          {
            if ((i1 != 47) && (i1 != 35))
            {
              this._inputPtr = n;
              m = i1;
            }
          }
          else if ((i1 == 32) || (i1 == 9))
          {
            byte[] arrayOfByte4 = this._inputBuffer;
            int i2 = n + 1;
            int i3 = arrayOfByte4[n];
            if ((i3 > 32) && (i3 != 47) && (i3 != 35))
            {
              this._inputPtr = i2;
              m = i3;
              continue;
            }
            n = i2;
          }
          this._inputPtr = (n - 1);
          m = _skipColon2(true);
        }
        else
        {
          this._inputPtr = (i - 1);
          m = _skipColon2(false);
        }
      }
    }
  }
  
  private final void _skipComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
    }
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in a comment");
    }
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = 0xFF & arrayOfByte[i];
    if (j == 47) {
      _skipLine();
    }
    for (;;)
    {
      return;
      if (j == 42) {
        _skipCComment();
      } else {
        _reportUnexpectedChar(j, "was expecting either '*' or '/' for a comment");
      }
    }
  }
  
  private final void _skipLine()
    throws IOException
  {
    int[] arrayOfInt = CharTypes.getInputCodeComment();
    for (;;)
    {
      int j;
      int k;
      if ((this._inputPtr < this._inputEnd) || (loadMore()))
      {
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = (i + 1);
        j = 0xFF & arrayOfByte[i];
        k = arrayOfInt[j];
        if (k == 0) {}
      }
      else
      {
        switch (k)
        {
        case 42: 
        default: 
          if (k < 0) {
            _reportInvalidChar(j);
          }
          break;
        case 10: 
          this._currInputRow = (1 + this._currInputRow);
          this._currInputRowStart = this._inputPtr;
        case 13: 
          for (;;)
          {
            return;
            _skipCR();
          }
        case 2: 
          _skipUtf8_2(j);
          break;
        case 3: 
          _skipUtf8_3(j);
          break;
        case 4: 
          _skipUtf8_4(j);
        }
      }
    }
  }
  
  private final void _skipUtf8_2(int paramInt)
    throws IOException
  {
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = arrayOfByte[i];
    if ((j & 0xC0) != 128) {
      _reportInvalidOther(j & 0xFF, this._inputPtr);
    }
  }
  
  private final void _skipUtf8_3(int paramInt)
    throws IOException
  {
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte1 = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = arrayOfByte1[i];
    if ((j & 0xC0) != 128) {
      _reportInvalidOther(j & 0xFF, this._inputPtr);
    }
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte2 = this._inputBuffer;
    int k = this._inputPtr;
    this._inputPtr = (k + 1);
    int m = arrayOfByte2[k];
    if ((m & 0xC0) != 128) {
      _reportInvalidOther(m & 0xFF, this._inputPtr);
    }
  }
  
  private final void _skipUtf8_4(int paramInt)
    throws IOException
  {
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte1 = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = arrayOfByte1[i];
    if ((j & 0xC0) != 128) {
      _reportInvalidOther(j & 0xFF, this._inputPtr);
    }
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte2 = this._inputBuffer;
    int k = this._inputPtr;
    this._inputPtr = (k + 1);
    int m = arrayOfByte2[k];
    if ((m & 0xC0) != 128) {
      _reportInvalidOther(m & 0xFF, this._inputPtr);
    }
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte3 = this._inputBuffer;
    int n = this._inputPtr;
    this._inputPtr = (n + 1);
    int i1 = arrayOfByte3[n];
    if ((i1 & 0xC0) != 128) {
      _reportInvalidOther(i1 & 0xFF, this._inputPtr);
    }
  }
  
  private final int _skipWS()
    throws IOException
  {
    if (this._inputPtr < this._inputEnd)
    {
      byte[] arrayOfByte = this._inputBuffer;
      int j = this._inputPtr;
      this._inputPtr = (j + 1);
      i = 0xFF & arrayOfByte[j];
      if (i > 32) {
        if ((i == 47) || (i == 35)) {
          this._inputPtr = (-1 + this._inputPtr);
        }
      }
    }
    for (int i = _skipWS2();; i = _skipWS2())
    {
      return i;
      if (i == 32) {
        break;
      }
      if (i == 10)
      {
        this._currInputRow = (1 + this._currInputRow);
        this._currInputRowStart = this._inputPtr;
        break;
      }
      if (i == 13)
      {
        _skipCR();
        break;
      }
      if (i == 9) {
        break;
      }
      _throwInvalidSpace(i);
      break;
    }
  }
  
  private final int _skipWS2()
    throws IOException
  {
    while ((this._inputPtr < this._inputEnd) || (loadMore()))
    {
      byte[] arrayOfByte = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      int j = 0xFF & arrayOfByte[i];
      if (j > 32)
      {
        if (j == 47) {
          _skipComment();
        } else if ((j != 35) || (!_skipYAMLComment())) {
          return j;
        }
      }
      else if (j != 32) {
        if (j == 10)
        {
          this._currInputRow = (1 + this._currInputRow);
          this._currInputRowStart = this._inputPtr;
        }
        else if (j == 13)
        {
          _skipCR();
        }
        else if (j != 9)
        {
          _throwInvalidSpace(j);
        }
      }
    }
    throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
  }
  
  private final int _skipWSOrEnd()
    throws IOException
  {
    int j;
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      j = _eofAsNextChar();
    }
    for (;;)
    {
      return j;
      byte[] arrayOfByte1 = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      j = 0xFF & arrayOfByte1[i];
      if (j > 32)
      {
        if ((j == 47) || (j == 35))
        {
          this._inputPtr = (-1 + this._inputPtr);
          j = _skipWSOrEnd2();
        }
      }
      else
      {
        if (j != 32)
        {
          if (j != 10) {
            break label195;
          }
          this._currInputRow = (1 + this._currInputRow);
          this._currInputRowStart = this._inputPtr;
        }
        for (;;)
        {
          if (this._inputPtr >= this._inputEnd) {
            break label282;
          }
          byte[] arrayOfByte2 = this._inputBuffer;
          int k = this._inputPtr;
          this._inputPtr = (k + 1);
          j = 0xFF & arrayOfByte2[k];
          if (j > 32)
          {
            if ((j != 47) && (j != 35)) {
              break;
            }
            this._inputPtr = (-1 + this._inputPtr);
            j = _skipWSOrEnd2();
            break;
            label195:
            if (j == 13)
            {
              _skipCR();
              continue;
            }
            if (j == 9) {
              continue;
            }
            _throwInvalidSpace(j);
            continue;
          }
          if (j != 32) {
            if (j == 10)
            {
              this._currInputRow = (1 + this._currInputRow);
              this._currInputRowStart = this._inputPtr;
            }
            else if (j == 13)
            {
              _skipCR();
            }
            else if (j != 9)
            {
              _throwInvalidSpace(j);
            }
          }
        }
        label282:
        j = _skipWSOrEnd2();
      }
    }
  }
  
  private final int _skipWSOrEnd2()
    throws IOException
  {
    int j;
    do
    {
      for (;;)
      {
        if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
          break label137;
        }
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = (i + 1);
        j = 0xFF & arrayOfByte[i];
        if (j <= 32) {
          break label77;
        }
        if (j != 47) {
          break;
        }
        _skipComment();
      }
    } while ((j == 35) && (_skipYAMLComment()));
    for (;;)
    {
      return j;
      label77:
      if (j == 32) {
        break;
      }
      if (j == 10)
      {
        this._currInputRow = (1 + this._currInputRow);
        this._currInputRowStart = this._inputPtr;
        break;
      }
      if (j == 13)
      {
        _skipCR();
        break;
      }
      if (j == 9) {
        break;
      }
      _throwInvalidSpace(j);
      break;
      label137:
      j = _eofAsNextChar();
    }
  }
  
  private final boolean _skipYAMLComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_YAML_COMMENTS)) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      _skipLine();
    }
  }
  
  private final int _verifyNoLeadingZeroes()
    throws IOException
  {
    int i;
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      i = 48;
    }
    for (;;)
    {
      return i;
      i = 0xFF & this._inputBuffer[this._inputPtr];
      if ((i < 48) || (i > 57))
      {
        i = 48;
      }
      else
      {
        if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
          reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr = (1 + this._inputPtr);
        if (i == 48) {
          if ((this._inputPtr < this._inputEnd) || (loadMore()))
          {
            i = 0xFF & this._inputBuffer[this._inputPtr];
            if ((i < 48) || (i > 57))
            {
              i = 48;
            }
            else
            {
              this._inputPtr = (1 + this._inputPtr);
              if (i == 48) {
                break;
              }
            }
          }
        }
      }
    }
  }
  
  private final void _verifyRootSpace(int paramInt)
    throws IOException
  {
    this._inputPtr = (1 + this._inputPtr);
    switch (paramInt)
    {
    default: 
      _reportMissingRootWS(paramInt);
    }
    for (;;)
    {
      return;
      _skipCR();
      continue;
      this._currInputRow = (1 + this._currInputRow);
      this._currInputRowStart = this._inputPtr;
    }
  }
  
  private final String addName(int[] paramArrayOfInt, int paramInt1, int paramInt2)
    throws JsonParseException
  {
    int i = paramInt2 + (-4 + (paramInt1 << 2));
    int j;
    char[] arrayOfChar;
    int m;
    label50:
    int n;
    int i2;
    int i3;
    label110:
    int i1;
    if (paramInt2 < 4)
    {
      j = paramArrayOfInt[(paramInt1 - 1)];
      paramArrayOfInt[(paramInt1 - 1)] = (j << (4 - paramInt2 << 3));
      arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
      int k = 0;
      m = 0;
      if (k >= i) {
        break label463;
      }
      n = 0xFF & paramArrayOfInt[(k >> 2)] >> (3 - (k & 0x3) << 3);
      k++;
      if (n <= 127) {
        break label501;
      }
      if ((n & 0xE0) != 192) {
        break label397;
      }
      i2 = n & 0x1F;
      i3 = 1;
      if (k + i3 > i) {
        _reportInvalidEOF(" in field name");
      }
      int i4 = paramArrayOfInt[(k >> 2)] >> (3 - (k & 0x3) << 3);
      k++;
      if ((i4 & 0xC0) != 128) {
        _reportInvalidOther(i4);
      }
      n = i2 << 6 | i4 & 0x3F;
      if (i3 > 1)
      {
        int i6 = paramArrayOfInt[(k >> 2)] >> (3 - (k & 0x3) << 3);
        k++;
        if ((i6 & 0xC0) != 128) {
          _reportInvalidOther(i6);
        }
        n = n << 6 | i6 & 0x3F;
        if (i3 > 2)
        {
          int i7 = paramArrayOfInt[(k >> 2)] >> (3 - (k & 0x3) << 3);
          k++;
          if ((i7 & 0xC0) != 128) {
            _reportInvalidOther(i7 & 0xFF);
          }
          n = n << 6 | i7 & 0x3F;
        }
      }
      if (i3 <= 2) {
        break label501;
      }
      int i5 = n - 65536;
      if (m >= arrayOfChar.length) {
        arrayOfChar = this._textBuffer.expandCurrentSegment();
      }
      i1 = m + 1;
      arrayOfChar[m] = ((char)(55296 + (i5 >> 10)));
      n = 0xDC00 | i5 & 0x3FF;
    }
    for (;;)
    {
      if (i1 >= arrayOfChar.length) {
        arrayOfChar = this._textBuffer.expandCurrentSegment();
      }
      m = i1 + 1;
      arrayOfChar[i1] = ((char)n);
      break label50;
      j = 0;
      break;
      label397:
      if ((n & 0xF0) == 224)
      {
        i2 = n & 0xF;
        i3 = 2;
        break label110;
      }
      if ((n & 0xF8) == 240)
      {
        i2 = n & 0x7;
        i3 = 3;
        break label110;
      }
      _reportInvalidInitial(n);
      i2 = 1;
      i3 = i2;
      break label110;
      label463:
      String str = new String(arrayOfChar, 0, m);
      if (paramInt2 < 4) {
        paramArrayOfInt[(paramInt1 - 1)] = j;
      }
      return this._symbols.addName(str, paramArrayOfInt, paramInt1);
      label501:
      i1 = m;
    }
  }
  
  private final String findName(int paramInt1, int paramInt2)
    throws JsonParseException
  {
    int i = pad(paramInt1, paramInt2);
    String str = this._symbols.findName(i);
    if (str != null) {}
    for (;;)
    {
      return str;
      this._quadBuffer[0] = i;
      str = addName(this._quadBuffer, 1, paramInt2);
    }
  }
  
  private final String findName(int paramInt1, int paramInt2, int paramInt3)
    throws JsonParseException
  {
    int i = pad(paramInt2, paramInt3);
    String str = this._symbols.findName(paramInt1, i);
    if (str != null) {}
    for (;;)
    {
      return str;
      this._quadBuffer[0] = paramInt1;
      this._quadBuffer[1] = i;
      str = addName(this._quadBuffer, 2, paramInt3);
    }
  }
  
  private final String findName(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws JsonParseException
  {
    int i = pad(paramInt3, paramInt4);
    String str = this._symbols.findName(paramInt1, paramInt2, i);
    if (str != null) {}
    for (;;)
    {
      return str;
      int[] arrayOfInt = this._quadBuffer;
      arrayOfInt[0] = paramInt1;
      arrayOfInt[1] = paramInt2;
      arrayOfInt[2] = pad(i, paramInt4);
      str = addName(arrayOfInt, 3, paramInt4);
    }
  }
  
  private final String findName(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
    throws JsonParseException
  {
    if (paramInt1 >= paramArrayOfInt.length)
    {
      paramArrayOfInt = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
      this._quadBuffer = paramArrayOfInt;
    }
    int i = paramInt1 + 1;
    paramArrayOfInt[paramInt1] = pad(paramInt2, paramInt3);
    String str = this._symbols.findName(paramArrayOfInt, i);
    if (str == null) {
      str = addName(paramArrayOfInt, i, paramInt3);
    }
    return str;
  }
  
  public static int[] growArrayBy(int[] paramArrayOfInt, int paramInt)
  {
    if (paramArrayOfInt == null) {}
    for (int[] arrayOfInt = new int[paramInt];; arrayOfInt = Arrays.copyOf(paramArrayOfInt, paramInt + paramArrayOfInt.length)) {
      return arrayOfInt;
    }
  }
  
  private int nextByte()
    throws IOException
  {
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    return 0xFF & arrayOfByte[i];
  }
  
  private static final int pad(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 4) {}
    for (;;)
    {
      return paramInt1;
      paramInt1 |= -1 << (paramInt2 << 3);
    }
  }
  
  private final String parseName(int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    return parseEscapedName(this._quadBuffer, 0, paramInt1, paramInt2, paramInt3);
  }
  
  private final String parseName(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IOException
  {
    this._quadBuffer[0] = paramInt1;
    return parseEscapedName(this._quadBuffer, 1, paramInt2, paramInt3, paramInt4);
  }
  
  private final String parseName(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    throws IOException
  {
    this._quadBuffer[0] = paramInt1;
    this._quadBuffer[1] = paramInt2;
    return parseEscapedName(this._quadBuffer, 2, paramInt3, paramInt4, paramInt5);
  }
  
  protected void _closeInput()
    throws IOException
  {
    if (this._inputStream != null)
    {
      if ((this._ioContext.isResourceManaged()) || (isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))) {
        this._inputStream.close();
      }
      this._inputStream = null;
    }
  }
  
  protected final byte[] _decodeBase64(Base64Variant paramBase64Variant)
    throws IOException
  {
    ByteArrayBuilder localByteArrayBuilder = _getByteArrayBuilder();
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      byte[] arrayOfByte1 = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      int j = 0xFF & arrayOfByte1[i];
      if (j > 32)
      {
        int k = paramBase64Variant.decodeBase64Char(j);
        byte[] arrayOfByte5;
        if (k < 0) {
          if (j == 34) {
            arrayOfByte5 = localByteArrayBuilder.toByteArray();
          }
        }
        int i7;
        int i9;
        for (;;)
        {
          return arrayOfByte5;
          k = _decodeBase64Escape(paramBase64Variant, j, 0);
          if (k < 0) {
            break;
          }
          int m = k;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          byte[] arrayOfByte2 = this._inputBuffer;
          int n = this._inputPtr;
          this._inputPtr = (n + 1);
          int i1 = 0xFF & arrayOfByte2[n];
          int i2 = paramBase64Variant.decodeBase64Char(i1);
          if (i2 < 0) {
            i2 = _decodeBase64Escape(paramBase64Variant, i1, 1);
          }
          int i3 = i2 | m << 6;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          byte[] arrayOfByte3 = this._inputBuffer;
          int i4 = this._inputPtr;
          this._inputPtr = (i4 + 1);
          int i5 = 0xFF & arrayOfByte3[i4];
          int i6 = paramBase64Variant.decodeBase64Char(i5);
          if (i6 < 0)
          {
            if (i6 != -2)
            {
              if ((i5 == 34) && (!paramBase64Variant.usesPadding()))
              {
                localByteArrayBuilder.append(i3 >> 4);
                arrayOfByte5 = localByteArrayBuilder.toByteArray();
                continue;
              }
              i6 = _decodeBase64Escape(paramBase64Variant, i5, 2);
            }
            if (i6 == -2)
            {
              if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
              }
              byte[] arrayOfByte6 = this._inputBuffer;
              int i11 = this._inputPtr;
              this._inputPtr = (i11 + 1);
              int i12 = 0xFF & arrayOfByte6[i11];
              if (!paramBase64Variant.usesPaddingChar(i12)) {
                throw reportInvalidBase64Char(paramBase64Variant, i12, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
              }
              localByteArrayBuilder.append(i3 >> 4);
              break;
            }
          }
          i7 = i6 | i3 << 6;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          byte[] arrayOfByte4 = this._inputBuffer;
          int i8 = this._inputPtr;
          this._inputPtr = (i8 + 1);
          i9 = 0xFF & arrayOfByte4[i8];
          i10 = paramBase64Variant.decodeBase64Char(i9);
          if (i10 >= 0) {
            break label536;
          }
          if (i10 == -2) {
            break label518;
          }
          if ((i9 != 34) || (paramBase64Variant.usesPadding())) {
            break label508;
          }
          localByteArrayBuilder.appendTwoBytes(i7 >> 2);
          arrayOfByte5 = localByteArrayBuilder.toByteArray();
        }
        label508:
        int i10 = _decodeBase64Escape(paramBase64Variant, i9, 3);
        label518:
        if (i10 == -2) {
          localByteArrayBuilder.appendTwoBytes(i7 >> 2);
        } else {
          label536:
          localByteArrayBuilder.appendThreeBytes(i10 | i7 << 6);
        }
      }
    }
  }
  
  protected int _decodeCharForError(int paramInt)
    throws IOException
  {
    int i = paramInt & 0xFF;
    int j;
    if (i > 127)
    {
      if ((i & 0xE0) != 192) {
        break label159;
      }
      i &= 0x1F;
      j = 1;
    }
    for (;;)
    {
      int k = nextByte();
      if ((k & 0xC0) != 128) {
        _reportInvalidOther(k & 0xFF);
      }
      i = i << 6 | k & 0x3F;
      if (j > 1)
      {
        int m = nextByte();
        if ((m & 0xC0) != 128) {
          _reportInvalidOther(m & 0xFF);
        }
        i = i << 6 | m & 0x3F;
        if (j > 2)
        {
          int n = nextByte();
          if ((n & 0xC0) != 128) {
            _reportInvalidOther(n & 0xFF);
          }
          i = i << 6 | n & 0x3F;
        }
      }
      return i;
      label159:
      if ((i & 0xF0) == 224)
      {
        i &= 0xF;
        j = 2;
      }
      else if ((i & 0xF8) == 240)
      {
        i &= 0x7;
        j = 3;
      }
      else
      {
        _reportInvalidInitial(i & 0xFF);
        j = 1;
      }
    }
  }
  
  protected char _decodeEscaped()
    throws IOException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in character escape sequence");
    }
    byte[] arrayOfByte1 = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = arrayOfByte1[i];
    char c;
    switch (j)
    {
    default: 
      c = _handleUnrecognizedCharacterEscape((char)_decodeCharForError(j));
    }
    for (;;)
    {
      return c;
      c = '\b';
      continue;
      c = '\t';
      continue;
      c = '\n';
      continue;
      c = '\f';
      continue;
      c = '\r';
      continue;
      c = (char)j;
      continue;
      int k = 0;
      for (int m = 0; m < 4; m++)
      {
        if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
          _reportInvalidEOF(" in character escape sequence");
        }
        byte[] arrayOfByte2 = this._inputBuffer;
        int n = this._inputPtr;
        this._inputPtr = (n + 1);
        int i1 = arrayOfByte2[n];
        int i2 = CharTypes.charToHex(i1);
        if (i2 < 0) {
          _reportUnexpectedChar(i1, "expected a hex-digit for character escape sequence");
        }
        k = i2 | k << 4;
      }
      c = (char)k;
    }
  }
  
  protected String _finishAndReturnString()
    throws IOException
  {
    int i = this._inputPtr;
    if (i >= this._inputEnd)
    {
      loadMoreGuaranteed();
      i = this._inputPtr;
    }
    char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
    int[] arrayOfInt = _icUTF8;
    int j = Math.min(this._inputEnd, i + arrayOfChar.length);
    byte[] arrayOfByte = this._inputBuffer;
    int k = 0;
    int m;
    if (i < j)
    {
      m = 0xFF & arrayOfByte[i];
      if (arrayOfInt[m] != 0)
      {
        if (m != 34) {
          break label130;
        }
        this._inputPtr = (i + 1);
      }
    }
    for (String str = this._textBuffer.setCurrentAndReturn(k);; str = this._textBuffer.contentsAsString())
    {
      return str;
      i++;
      int n = k + 1;
      arrayOfChar[k] = ((char)m);
      k = n;
      break;
      label130:
      this._inputPtr = i;
      _finishString2(arrayOfChar, k);
    }
  }
  
  protected void _finishString()
    throws IOException
  {
    int i = this._inputPtr;
    if (i >= this._inputEnd)
    {
      loadMoreGuaranteed();
      i = this._inputPtr;
    }
    char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
    int[] arrayOfInt = _icUTF8;
    int j = Math.min(this._inputEnd, i + arrayOfChar.length);
    byte[] arrayOfByte = this._inputBuffer;
    int k = 0;
    int m;
    if (i < j)
    {
      m = 0xFF & arrayOfByte[i];
      if (arrayOfInt[m] != 0)
      {
        if (m != 34) {
          break label126;
        }
        this._inputPtr = (i + 1);
        this._textBuffer.setCurrentLength(k);
      }
    }
    for (;;)
    {
      return;
      i++;
      int n = k + 1;
      arrayOfChar[k] = ((char)m);
      k = n;
      break;
      label126:
      this._inputPtr = i;
      _finishString2(arrayOfChar, k);
    }
  }
  
  protected final String _getText2(JsonToken paramJsonToken)
  {
    String str;
    if (paramJsonToken == null) {
      str = null;
    }
    for (;;)
    {
      return str;
      switch (paramJsonToken.id())
      {
      default: 
        str = paramJsonToken.asString();
        break;
      case 5: 
        str = this._parsingContext.getCurrentName();
        break;
      case 6: 
      case 7: 
      case 8: 
        str = this._textBuffer.contentsAsString();
      }
    }
  }
  
  protected JsonToken _handleApos()
    throws IOException
  {
    int i = 0;
    char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
    int[] arrayOfInt = _icUTF8;
    byte[] arrayOfByte = this._inputBuffer;
    int n;
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      if (i >= arrayOfChar.length)
      {
        arrayOfChar = this._textBuffer.finishCurrentSegment();
        i = 0;
      }
      int j = this._inputEnd;
      int k = this._inputPtr + (arrayOfChar.length - i);
      if (k < j) {
        j = k;
      }
      while (this._inputPtr < j)
      {
        int m = this._inputPtr;
        this._inputPtr = (m + 1);
        n = 0xFF & arrayOfByte[m];
        if ((n == 39) || (arrayOfInt[n] != 0))
        {
          if (n != 39) {
            break label163;
          }
          this._textBuffer.setCurrentLength(i);
          return JsonToken.VALUE_STRING;
        }
        int i4 = i + 1;
        arrayOfChar[i] = ((char)n);
        i = i4;
      }
    }
    label163:
    switch (arrayOfInt[n])
    {
    default: 
      if (n < 32) {
        _throwUnquotedSpace(n, "string value");
      }
      _reportInvalidChar(n);
    case 1: 
    case 2: 
    case 3: 
      for (;;)
      {
        if (i >= arrayOfChar.length)
        {
          arrayOfChar = this._textBuffer.finishCurrentSegment();
          i = 0;
        }
        int i3 = i + 1;
        arrayOfChar[i] = ((char)n);
        i = i3;
        break;
        if (n != 39)
        {
          n = _decodeEscaped();
          continue;
          n = _decodeUtf8_2(n);
          continue;
          if (this._inputEnd - this._inputPtr >= 2) {
            n = _decodeUtf8_3fast(n);
          } else {
            n = _decodeUtf8_3(n);
          }
        }
      }
    }
    int i1 = _decodeUtf8_4(n);
    int i2 = i + 1;
    arrayOfChar[i] = ((char)(0xD800 | i1 >> 10));
    if (i2 >= arrayOfChar.length) {
      arrayOfChar = this._textBuffer.finishCurrentSegment();
    }
    for (i = 0;; i = i2)
    {
      n = 0xDC00 | i1 & 0x3FF;
      break;
    }
  }
  
  protected JsonToken _handleInvalidNumberStart(int paramInt, boolean paramBoolean)
    throws IOException
  {
    String str;
    label69:
    double d;
    if (paramInt == 73)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidEOFInValue();
      }
      byte[] arrayOfByte = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      paramInt = arrayOfByte[i];
      if (paramInt == 78) {
        if (paramBoolean)
        {
          str = "-INF";
          _matchToken(str, 3);
          if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            break label148;
          }
          if (!paramBoolean) {
            break label140;
          }
          d = Double.NEGATIVE_INFINITY;
        }
      }
    }
    label95:
    for (JsonToken localJsonToken = resetAsNaN(str, d);; localJsonToken = null)
    {
      return localJsonToken;
      str = "+INF";
      break label69;
      if (paramInt == 110)
      {
        if (paramBoolean) {}
        for (str = "-Infinity";; str = "+Infinity") {
          break;
        }
        label140:
        d = Double.POSITIVE_INFINITY;
        break label95;
        label148:
        _reportError("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        break;
      }
      reportUnexpectedNumberChar(paramInt, "expected digit (0-9) to follow minus sign, for valid numeric value");
    }
  }
  
  protected String _handleOddName(int paramInt)
    throws IOException
  {
    String str;
    if ((paramInt == 39) && (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)))
    {
      str = _parseAposName();
      return str;
    }
    if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
      _reportUnexpectedChar((char)_decodeCharForError(paramInt), "was expecting double-quote to start field name");
    }
    int[] arrayOfInt1 = CharTypes.getInputCodeUtf8JsNames();
    if (arrayOfInt1[paramInt] != 0) {
      _reportUnexpectedChar(paramInt, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
    }
    int[] arrayOfInt2 = this._quadBuffer;
    int i = 0;
    int j = 0;
    int m;
    for (int k = 0;; k = m)
    {
      if (j < 4)
      {
        j++;
        i = paramInt | i << 8;
        m = k;
      }
      for (;;)
      {
        if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
          _reportInvalidEOF(" in field name");
        }
        paramInt = 0xFF & this._inputBuffer[this._inputPtr];
        if (arrayOfInt1[paramInt] == 0) {
          break label258;
        }
        if (j > 0)
        {
          if (m >= arrayOfInt2.length)
          {
            arrayOfInt2 = growArrayBy(arrayOfInt2, arrayOfInt2.length);
            this._quadBuffer = arrayOfInt2;
          }
          int n = m + 1;
          arrayOfInt2[m] = i;
          m = n;
        }
        str = this._symbols.findName(arrayOfInt2, m);
        if (str != null) {
          break;
        }
        str = addName(arrayOfInt2, m, j);
        break;
        if (k >= arrayOfInt2.length)
        {
          arrayOfInt2 = growArrayBy(arrayOfInt2, arrayOfInt2.length);
          this._quadBuffer = arrayOfInt2;
        }
        m = k + 1;
        arrayOfInt2[k] = i;
        i = paramInt;
        j = 1;
      }
      label258:
      this._inputPtr = (1 + this._inputPtr);
    }
  }
  
  protected JsonToken _handleUnexpectedValue(int paramInt)
    throws IOException
  {
    JsonToken localJsonToken;
    switch (paramInt)
    {
    default: 
      if (Character.isJavaIdentifierStart(paramInt)) {
        _reportInvalidToken("" + (char)paramInt, "('true', 'false' or 'null')");
      }
      _reportUnexpectedChar(paramInt, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
      localJsonToken = null;
    }
    for (;;)
    {
      return localJsonToken;
      _reportUnexpectedChar(paramInt, "expected a value");
      if (!isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
        break;
      }
      localJsonToken = _handleApos();
      continue;
      _matchToken("NaN", 1);
      if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
      {
        localJsonToken = resetAsNaN("NaN", NaN.0D);
      }
      else
      {
        _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        break;
        _matchToken("Infinity", 1);
        if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS))
        {
          localJsonToken = resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
        }
        else
        {
          _reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
          break;
          if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
            _reportInvalidEOFInValue();
          }
          byte[] arrayOfByte = this._inputBuffer;
          int i = this._inputPtr;
          this._inputPtr = (i + 1);
          localJsonToken = _handleInvalidNumberStart(0xFF & arrayOfByte[i], false);
        }
      }
    }
  }
  
  protected final boolean _loadToHaveAtLeast(int paramInt)
    throws IOException
  {
    boolean bool = false;
    if (this._inputStream == null) {}
    for (;;)
    {
      return bool;
      int i = this._inputEnd - this._inputPtr;
      if ((i > 0) && (this._inputPtr > 0))
      {
        this._currInputProcessed += this._inputPtr;
        this._currInputRowStart -= this._inputPtr;
        System.arraycopy(this._inputBuffer, this._inputPtr, this._inputBuffer, 0, i);
        this._inputEnd = i;
        label81:
        this._inputPtr = 0;
      }
      for (;;)
      {
        if (this._inputEnd >= paramInt) {
          break label192;
        }
        int j = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
        if (j < 1)
        {
          _closeInput();
          if (j != 0) {
            break;
          }
          throw new IOException("InputStream.read() returned 0 characters when trying to read " + i + " bytes");
          this._inputEnd = 0;
          break label81;
        }
        this._inputEnd = (j + this._inputEnd);
      }
      label192:
      bool = true;
    }
  }
  
  protected final void _matchToken(String paramString, int paramInt)
    throws IOException
  {
    int i = paramString.length();
    if (i + this._inputPtr >= this._inputEnd) {
      _matchToken2(paramString, paramInt);
    }
    for (;;)
    {
      return;
      do
      {
        if (this._inputBuffer[this._inputPtr] != paramString.charAt(paramInt)) {
          _reportInvalidToken(paramString.substring(0, paramInt));
        }
        this._inputPtr = (1 + this._inputPtr);
        paramInt++;
      } while (paramInt < i);
      int j = 0xFF & this._inputBuffer[this._inputPtr];
      if ((j >= 48) && (j != 93) && (j != 125)) {
        _checkMatchEnd(paramString, paramInt, j);
      }
    }
  }
  
  protected String _parseAposName()
    throws IOException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing ''' for name");
    }
    byte[] arrayOfByte1 = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = 0xFF & arrayOfByte1[i];
    String str;
    if (j == 39) {
      str = "";
    }
    int[] arrayOfInt1;
    int k;
    int m;
    int[] arrayOfInt2;
    int n;
    int i7;
    for (;;)
    {
      return str;
      arrayOfInt1 = this._quadBuffer;
      k = 0;
      m = 0;
      arrayOfInt2 = _icLatin1;
      n = 0;
      if (j != 39) {
        break;
      }
      if (m <= 0) {
        break label536;
      }
      if (n >= arrayOfInt1.length)
      {
        arrayOfInt1 = growArrayBy(arrayOfInt1, arrayOfInt1.length);
        this._quadBuffer = arrayOfInt1;
      }
      i7 = n + 1;
      arrayOfInt1[n] = pad(k, m);
      str = this._symbols.findName(arrayOfInt1, i7);
      if (str == null) {
        str = addName(arrayOfInt1, i7, m);
      }
    }
    if ((j != 34) && (arrayOfInt2[j] != 0))
    {
      if (j == 92) {
        break label383;
      }
      _throwUnquotedSpace(j, "name");
    }
    int i5;
    label286:
    int i1;
    for (;;)
    {
      if (j > 127)
      {
        if (m >= 4)
        {
          if (n >= arrayOfInt1.length)
          {
            arrayOfInt1 = growArrayBy(arrayOfInt1, arrayOfInt1.length);
            this._quadBuffer = arrayOfInt1;
          }
          int i6 = n + 1;
          arrayOfInt1[n] = k;
          k = 0;
          m = 0;
          n = i6;
        }
        if (j >= 2048) {
          break label391;
        }
        k = k << 8 | 0xC0 | j >> 6;
        m++;
        i5 = n;
        j = 0x80 | j & 0x3F;
        n = i5;
      }
      if (m >= 4) {
        break label490;
      }
      m++;
      k = j | k << 8;
      i1 = n;
      label321:
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidEOF(" in field name");
      }
      byte[] arrayOfByte2 = this._inputBuffer;
      int i2 = this._inputPtr;
      this._inputPtr = (i2 + 1);
      j = 0xFF & arrayOfByte2[i2];
      n = i1;
      break;
      label383:
      j = _decodeEscaped();
    }
    label391:
    int i3 = k << 8 | 0xE0 | j >> 12;
    int i4 = m + 1;
    if (i4 >= 4)
    {
      if (n >= arrayOfInt1.length)
      {
        arrayOfInt1 = growArrayBy(arrayOfInt1, arrayOfInt1.length);
        this._quadBuffer = arrayOfInt1;
      }
      i5 = n + 1;
      arrayOfInt1[n] = i3;
      i3 = 0;
      i4 = 0;
    }
    for (;;)
    {
      k = i3 << 8 | 0x80 | 0x3F & j >> 6;
      m = i4 + 1;
      break label286;
      label490:
      if (n >= arrayOfInt1.length)
      {
        arrayOfInt1 = growArrayBy(arrayOfInt1, arrayOfInt1.length);
        this._quadBuffer = arrayOfInt1;
      }
      i1 = n + 1;
      arrayOfInt1[n] = k;
      k = j;
      m = 1;
      break label321;
      label536:
      i7 = n;
      break;
      i5 = n;
    }
  }
  
  protected final String _parseName(int paramInt)
    throws IOException
  {
    String str;
    if (paramInt != 34) {
      str = _handleOddName(paramInt);
    }
    for (;;)
    {
      return str;
      if (13 + this._inputPtr > this._inputEnd)
      {
        str = slowParseName();
      }
      else
      {
        byte[] arrayOfByte = this._inputBuffer;
        int[] arrayOfInt = _icLatin1;
        int i = this._inputPtr;
        this._inputPtr = (i + 1);
        int j = 0xFF & arrayOfByte[i];
        if (arrayOfInt[j] == 0)
        {
          int k = this._inputPtr;
          this._inputPtr = (k + 1);
          int m = 0xFF & arrayOfByte[k];
          if (arrayOfInt[m] == 0)
          {
            int n = m | j << 8;
            int i1 = this._inputPtr;
            this._inputPtr = (i1 + 1);
            int i2 = 0xFF & arrayOfByte[i1];
            if (arrayOfInt[i2] == 0)
            {
              int i3 = i2 | n << 8;
              int i4 = this._inputPtr;
              this._inputPtr = (i4 + 1);
              int i5 = 0xFF & arrayOfByte[i4];
              if (arrayOfInt[i5] == 0)
              {
                int i6 = i5 | i3 << 8;
                int i7 = this._inputPtr;
                this._inputPtr = (i7 + 1);
                int i8 = 0xFF & arrayOfByte[i7];
                if (arrayOfInt[i8] == 0)
                {
                  this._quad1 = i6;
                  str = parseMediumName(i8);
                }
                else if (i8 == 34)
                {
                  str = findName(i6, 4);
                }
                else
                {
                  str = parseName(i6, i8, 4);
                }
              }
              else if (i5 == 34)
              {
                str = findName(i3, 3);
              }
              else
              {
                str = parseName(i3, i5, 3);
              }
            }
            else if (i2 == 34)
            {
              str = findName(n, 2);
            }
            else
            {
              str = parseName(n, i2, 2);
            }
          }
          else if (m == 34)
          {
            str = findName(j, 1);
          }
          else
          {
            str = parseName(j, m, 1);
          }
        }
        else if (j == 34)
        {
          str = "";
        }
        else
        {
          str = parseName(0, j, 0);
        }
      }
    }
  }
  
  protected JsonToken _parseNegNumber()
    throws IOException
  {
    char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
    int i = 0 + 1;
    arrayOfChar[0] = '-';
    if (this._inputPtr >= this._inputEnd) {
      loadMoreGuaranteed();
    }
    byte[] arrayOfByte1 = this._inputBuffer;
    int j = this._inputPtr;
    this._inputPtr = (j + 1);
    int k = 0xFF & arrayOfByte1[j];
    JsonToken localJsonToken;
    if ((k < 48) || (k > 57)) {
      localJsonToken = _handleInvalidNumberStart(k, true);
    }
    for (;;)
    {
      return localJsonToken;
      if (k == 48) {
        k = _verifyNoLeadingZeroes();
      }
      int m = i + 1;
      arrayOfChar[i] = ((char)k);
      int n = 1;
      int i1 = -2 + (this._inputPtr + arrayOfChar.length);
      if (i1 > this._inputEnd) {
        i1 = this._inputEnd;
      }
      int i3;
      for (;;)
      {
        if (this._inputPtr >= i1)
        {
          localJsonToken = _parseNumber2(arrayOfChar, m, true, n);
          break;
        }
        byte[] arrayOfByte2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = (i2 + 1);
        i3 = 0xFF & arrayOfByte2[i2];
        if ((i3 < 48) || (i3 > 57))
        {
          if ((i3 != 46) && (i3 != 101) && (i3 != 69)) {
            break label271;
          }
          localJsonToken = _parseFloat(arrayOfChar, m, i3, true, n);
          break;
        }
        n++;
        int i4 = m + 1;
        arrayOfChar[m] = ((char)i3);
        m = i4;
      }
      label271:
      this._inputPtr = (-1 + this._inputPtr);
      this._textBuffer.setCurrentLength(m);
      if (this._parsingContext.inRoot()) {
        _verifyRootSpace(i3);
      }
      localJsonToken = resetInt(true, n);
    }
  }
  
  protected JsonToken _parsePosNumber(int paramInt)
    throws IOException
  {
    char[] arrayOfChar = this._textBuffer.emptyAndGetCurrentSegment();
    if (paramInt == 48) {
      paramInt = _verifyNoLeadingZeroes();
    }
    arrayOfChar[0] = ((char)paramInt);
    int i = 1;
    int j = 1;
    int k = -1 + (this._inputPtr + arrayOfChar.length);
    if (k > this._inputEnd) {
      k = this._inputEnd;
    }
    JsonToken localJsonToken;
    if (this._inputPtr >= k) {
      localJsonToken = _parseNumber2(arrayOfChar, j, false, i);
    }
    for (;;)
    {
      return localJsonToken;
      byte[] arrayOfByte = this._inputBuffer;
      int m = this._inputPtr;
      this._inputPtr = (m + 1);
      int n = 0xFF & arrayOfByte[m];
      if ((n < 48) || (n > 57))
      {
        if ((n == 46) || (n == 101) || (n == 69)) {
          localJsonToken = _parseFloat(arrayOfChar, j, n, false, i);
        }
      }
      else
      {
        i++;
        int i1 = j + 1;
        arrayOfChar[j] = ((char)n);
        j = i1;
        break;
      }
      this._inputPtr = (-1 + this._inputPtr);
      this._textBuffer.setCurrentLength(j);
      if (this._parsingContext.inRoot()) {
        _verifyRootSpace(n);
      }
      localJsonToken = resetInt(false, i);
    }
  }
  
  protected int _readBinary(Base64Variant paramBase64Variant, OutputStream paramOutputStream, byte[] paramArrayOfByte)
    throws IOException
  {
    int i = 0;
    int j = -3 + paramArrayOfByte.length;
    int k = 0;
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      byte[] arrayOfByte1 = this._inputBuffer;
      int m = this._inputPtr;
      this._inputPtr = (m + 1);
      int n = 0xFF & arrayOfByte1[m];
      if (n > 32)
      {
        int i1 = paramBase64Variant.decodeBase64Char(n);
        if (i1 < 0) {
          if (n != 34) {}
        }
        int i10;
        int i12;
        for (;;)
        {
          this._tokenIncomplete = false;
          if (i > 0)
          {
            k += i;
            paramOutputStream.write(paramArrayOfByte, 0, i);
          }
          return k;
          i1 = _decodeBase64Escape(paramBase64Variant, n, 0);
          if (i1 < 0) {
            break;
          }
          if (i > j)
          {
            k += i;
            paramOutputStream.write(paramArrayOfByte, 0, i);
            i = 0;
          }
          int i2 = i1;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          byte[] arrayOfByte2 = this._inputBuffer;
          int i3 = this._inputPtr;
          this._inputPtr = (i3 + 1);
          int i4 = 0xFF & arrayOfByte2[i3];
          int i5 = paramBase64Variant.decodeBase64Char(i4);
          if (i5 < 0) {
            i5 = _decodeBase64Escape(paramBase64Variant, i4, 1);
          }
          int i6 = i5 | i2 << 6;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          byte[] arrayOfByte3 = this._inputBuffer;
          int i7 = this._inputPtr;
          this._inputPtr = (i7 + 1);
          int i8 = 0xFF & arrayOfByte3[i7];
          int i9 = paramBase64Variant.decodeBase64Char(i8);
          if (i9 < 0)
          {
            if (i9 != -2)
            {
              if ((i8 == 34) && (!paramBase64Variant.usesPadding()))
              {
                int i26 = i6 >> 4;
                int i27 = i + 1;
                paramArrayOfByte[i] = ((byte)i26);
                i = i27;
                continue;
              }
              i9 = _decodeBase64Escape(paramBase64Variant, i8, 2);
            }
            if (i9 == -2)
            {
              if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
              }
              byte[] arrayOfByte5 = this._inputBuffer;
              int i22 = this._inputPtr;
              this._inputPtr = (i22 + 1);
              int i23 = 0xFF & arrayOfByte5[i22];
              if (!paramBase64Variant.usesPaddingChar(i23)) {
                throw reportInvalidBase64Char(paramBase64Variant, i23, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
              }
              int i24 = i6 >> 4;
              int i25 = i + 1;
              paramArrayOfByte[i] = ((byte)i24);
              i = i25;
              break;
            }
          }
          i10 = i9 | i6 << 6;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          byte[] arrayOfByte4 = this._inputBuffer;
          int i11 = this._inputPtr;
          this._inputPtr = (i11 + 1);
          i12 = 0xFF & arrayOfByte4[i11];
          i13 = paramBase64Variant.decodeBase64Char(i12);
          if (i13 >= 0) {
            break label662;
          }
          if (i13 == -2) {
            break label617;
          }
          if ((i12 != 34) || (paramBase64Variant.usesPadding())) {
            break label607;
          }
          int i20 = i10 >> 2;
          int i21 = i + 1;
          paramArrayOfByte[i] = ((byte)(i20 >> 8));
          i = i21 + 1;
          paramArrayOfByte[i21] = ((byte)i20);
        }
        label607:
        int i13 = _decodeBase64Escape(paramBase64Variant, i12, 3);
        label617:
        if (i13 == -2)
        {
          int i18 = i10 >> 2;
          int i19 = i + 1;
          paramArrayOfByte[i] = ((byte)(i18 >> 8));
          i = i19 + 1;
          paramArrayOfByte[i19] = ((byte)i18);
        }
        else
        {
          label662:
          int i14 = i13 | i10 << 6;
          int i15 = i + 1;
          paramArrayOfByte[i] = ((byte)(i14 >> 16));
          int i16 = i15 + 1;
          paramArrayOfByte[i15] = ((byte)(i14 >> 8));
          int i17 = i16 + 1;
          paramArrayOfByte[i16] = ((byte)i14);
          i = i17;
        }
      }
    }
  }
  
  protected void _releaseBuffers()
    throws IOException
  {
    super._releaseBuffers();
    this._symbols.release();
    if (this._bufferRecyclable)
    {
      byte[] arrayOfByte = this._inputBuffer;
      if (arrayOfByte != null)
      {
        this._inputBuffer = ByteArrayBuilder.NO_BYTES;
        this._ioContext.releaseReadIOBuffer(arrayOfByte);
      }
    }
  }
  
  protected void _reportInvalidChar(int paramInt)
    throws JsonParseException
  {
    if (paramInt < 32) {
      _throwInvalidSpace(paramInt);
    }
    _reportInvalidInitial(paramInt);
  }
  
  protected void _reportInvalidInitial(int paramInt)
    throws JsonParseException
  {
    _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(paramInt));
  }
  
  protected void _reportInvalidOther(int paramInt)
    throws JsonParseException
  {
    _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(paramInt));
  }
  
  protected void _reportInvalidOther(int paramInt1, int paramInt2)
    throws JsonParseException
  {
    this._inputPtr = paramInt2;
    _reportInvalidOther(paramInt1);
  }
  
  protected void _reportInvalidToken(String paramString)
    throws IOException
  {
    _reportInvalidToken(paramString, "'null', 'true', 'false' or NaN");
  }
  
  protected void _reportInvalidToken(String paramString1, String paramString2)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(paramString1);
    for (;;)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
      char c;
      do
      {
        _reportError("Unrecognized token '" + localStringBuilder.toString() + "': was expecting " + paramString2);
        return;
        byte[] arrayOfByte = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = (i + 1);
        c = (char)_decodeCharForError(arrayOfByte[i]);
      } while (!Character.isJavaIdentifierPart(c));
      localStringBuilder.append(c);
    }
  }
  
  protected final void _skipCR()
    throws IOException
  {
    if (((this._inputPtr < this._inputEnd) || (loadMore())) && (this._inputBuffer[this._inputPtr] == 10)) {
      this._inputPtr = (1 + this._inputPtr);
    }
    this._currInputRow = (1 + this._currInputRow);
    this._currInputRowStart = this._inputPtr;
  }
  
  protected void _skipString()
    throws IOException
  {
    this._tokenIncomplete = false;
    int[] arrayOfInt = _icUTF8;
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    int j = this._inputEnd;
    int n;
    if (i >= j)
    {
      loadMoreGuaranteed();
      n = this._inputPtr;
      j = this._inputEnd;
    }
    for (int k = n;; k = i)
    {
      int m;
      if (k < j)
      {
        i = k + 1;
        m = 0xFF & arrayOfByte[k];
        if (arrayOfInt[m] == 0) {
          continue;
        }
        this._inputPtr = i;
        if (m != 34) {}
      }
      else
      {
        this._inputPtr = k;
        break;
      }
      switch (arrayOfInt[m])
      {
      default: 
        if (m < 32) {
          _throwUnquotedSpace(m, "string value");
        }
        break;
      case 1: 
        _decodeEscaped();
        break;
      case 2: 
        _skipUtf8_2(m);
        break;
      case 3: 
        _skipUtf8_3(m);
        break;
      case 4: 
        _skipUtf8_4(m);
        break;
        _reportInvalidChar(m);
        break;
      }
    }
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException
  {
    if ((this._currToken != JsonToken.VALUE_STRING) && ((this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT) || (this._binaryValue == null))) {
      _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
    }
    if (this._tokenIncomplete) {}
    for (;;)
    {
      try
      {
        this._binaryValue = _decodeBase64(paramBase64Variant);
        this._tokenIncomplete = false;
        return this._binaryValue;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw _constructError("Failed to decode VALUE_STRING as base64 (" + paramBase64Variant + "): " + localIllegalArgumentException.getMessage());
      }
      if (this._binaryValue == null)
      {
        ByteArrayBuilder localByteArrayBuilder = _getByteArrayBuilder();
        _decodeBase64(getText(), localByteArrayBuilder, paramBase64Variant);
        this._binaryValue = localByteArrayBuilder.toByteArray();
      }
    }
  }
  
  public ObjectCodec getCodec()
  {
    return this._objectCodec;
  }
  
  public JsonLocation getCurrentLocation()
  {
    int i = 1 + (this._inputPtr - this._currInputRowStart);
    return new JsonLocation(this._ioContext.getSourceReference(), this._currInputProcessed + this._inputPtr, -1L, this._currInputRow, i);
  }
  
  public Object getInputSource()
  {
    return this._inputStream;
  }
  
  public String getText()
    throws IOException
  {
    String str;
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        str = _finishAndReturnString();
      }
    }
    for (;;)
    {
      return str;
      str = this._textBuffer.contentsAsString();
      continue;
      str = _getText2(this._currToken);
    }
  }
  
  public char[] getTextCharacters()
    throws IOException
  {
    char[] arrayOfChar;
    if (this._currToken != null) {
      switch (this._currToken.id())
      {
      default: 
        arrayOfChar = this._currToken.asCharArray();
      }
    }
    for (;;)
    {
      return arrayOfChar;
      String str;
      int i;
      if (!this._nameCopied)
      {
        str = this._parsingContext.getCurrentName();
        i = str.length();
        if (this._nameCopyBuffer != null) {
          break label117;
        }
        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(i);
      }
      for (;;)
      {
        str.getChars(0, i, this._nameCopyBuffer, 0);
        this._nameCopied = true;
        arrayOfChar = this._nameCopyBuffer;
        break;
        label117:
        if (this._nameCopyBuffer.length < i) {
          this._nameCopyBuffer = new char[i];
        }
      }
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      arrayOfChar = this._textBuffer.getTextBuffer();
      continue;
      arrayOfChar = null;
    }
  }
  
  public int getTextLength()
    throws IOException
  {
    int i = 0;
    if (this._currToken != null) {
      switch (this._currToken.id())
      {
      default: 
        i = this._currToken.asCharArray().length;
      }
    }
    for (;;)
    {
      return i;
      i = this._parsingContext.getCurrentName().length();
      continue;
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      i = this._textBuffer.size();
    }
  }
  
  public int getTextOffset()
    throws IOException
  {
    int i = 0;
    if (this._currToken != null) {
      switch (this._currToken.id())
      {
      }
    }
    for (;;)
    {
      return i;
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      i = this._textBuffer.getTextOffset();
    }
  }
  
  public JsonLocation getTokenLocation()
  {
    return new JsonLocation(this._ioContext.getSourceReference(), getTokenCharacterOffset(), -1L, getTokenLineNr(), getTokenColumnNr());
  }
  
  public int getValueAsInt()
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    int i;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      if ((0x1 & this._numTypesValid) == 0) {
        if (this._numTypesValid == 0) {
          i = _parseIntValue();
        }
      }
    }
    for (;;)
    {
      return i;
      if ((0x1 & this._numTypesValid) == 0) {
        convertNumberToInt();
      }
      i = this._numberInt;
      continue;
      i = super.getValueAsInt(0);
    }
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    int i;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      if ((0x1 & this._numTypesValid) == 0) {
        if (this._numTypesValid == 0) {
          i = _parseIntValue();
        }
      }
    }
    for (;;)
    {
      return i;
      if ((0x1 & this._numTypesValid) == 0) {
        convertNumberToInt();
      }
      i = this._numberInt;
      continue;
      i = super.getValueAsInt(paramInt);
    }
  }
  
  public String getValueAsString()
    throws IOException
  {
    String str;
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        str = _finishAndReturnString();
      }
    }
    for (;;)
    {
      return str;
      str = this._textBuffer.contentsAsString();
      continue;
      if (this._currToken == JsonToken.FIELD_NAME) {
        str = getCurrentName();
      } else {
        str = super.getValueAsString(null);
      }
    }
  }
  
  public String getValueAsString(String paramString)
    throws IOException
  {
    String str;
    if (this._currToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        str = _finishAndReturnString();
      }
    }
    for (;;)
    {
      return str;
      str = this._textBuffer.contentsAsString();
      continue;
      if (this._currToken == JsonToken.FIELD_NAME) {
        str = getCurrentName();
      } else {
        str = super.getValueAsString(paramString);
      }
    }
  }
  
  protected final boolean loadMore()
    throws IOException
  {
    boolean bool = false;
    this._currInputProcessed += this._inputEnd;
    this._currInputRowStart -= this._inputEnd;
    int i;
    if (this._inputStream != null)
    {
      i = this._inputBuffer.length;
      if (i != 0) {
        break label48;
      }
    }
    label48:
    int j;
    do
    {
      for (;;)
      {
        return bool;
        j = this._inputStream.read(this._inputBuffer, 0, i);
        if (j <= 0) {
          break;
        }
        this._inputPtr = 0;
        this._inputEnd = j;
        bool = true;
      }
      _closeInput();
    } while (j != 0);
    throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
  }
  
  public Boolean nextBooleanValue()
    throws IOException
  {
    Boolean localBoolean = null;
    JsonToken localJsonToken2;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      this._nameCopied = false;
      localJsonToken2 = this._nextToken;
      this._nextToken = null;
      this._currToken = localJsonToken2;
      if (localJsonToken2 == JsonToken.VALUE_TRUE) {
        localBoolean = Boolean.TRUE;
      }
    }
    for (;;)
    {
      return localBoolean;
      if (localJsonToken2 == JsonToken.VALUE_FALSE)
      {
        localBoolean = Boolean.FALSE;
      }
      else if (localJsonToken2 == JsonToken.START_ARRAY)
      {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      }
      else if (localJsonToken2 == JsonToken.START_OBJECT)
      {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        continue;
        JsonToken localJsonToken1 = nextToken();
        if (localJsonToken1 == JsonToken.VALUE_TRUE) {
          localBoolean = Boolean.TRUE;
        } else if (localJsonToken1 == JsonToken.VALUE_FALSE) {
          localBoolean = Boolean.FALSE;
        }
      }
    }
  }
  
  public String nextFieldName()
    throws IOException
  {
    String str = null;
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME) {
      _nextAfterName();
    }
    int j;
    for (;;)
    {
      return str;
      if (this._tokenIncomplete) {
        _skipString();
      }
      int i = _skipWSOrEnd();
      if (i < 0)
      {
        close();
        this._currToken = null;
      }
      else
      {
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr - 1L);
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (-1 + (this._inputPtr - this._currInputRowStart));
        this._binaryValue = null;
        if (i == 93)
        {
          if (!this._parsingContext.inArray()) {
            _reportMismatchedEndMarker(i, '}');
          }
          this._parsingContext = this._parsingContext.getParent();
          this._currToken = JsonToken.END_ARRAY;
        }
        else if (i == 125)
        {
          if (!this._parsingContext.inObject()) {
            _reportMismatchedEndMarker(i, ']');
          }
          this._parsingContext = this._parsingContext.getParent();
          this._currToken = JsonToken.END_OBJECT;
        }
        else
        {
          if (this._parsingContext.expectComma())
          {
            if (i != 44) {
              _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
            }
            i = _skipWS();
          }
          if (!this._parsingContext.inObject())
          {
            _nextTokenNotInObject(i);
          }
          else
          {
            str = _parseName(i);
            this._parsingContext.setCurrentName(str);
            this._currToken = JsonToken.FIELD_NAME;
            j = _skipColon();
            if (j != 34) {
              break;
            }
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
          }
        }
      }
    }
    JsonToken localJsonToken;
    switch (j)
    {
    default: 
      localJsonToken = _handleUnexpectedValue(j);
    }
    for (;;)
    {
      this._nextToken = localJsonToken;
      break;
      localJsonToken = _parseNegNumber();
      continue;
      localJsonToken = _parsePosNumber(j);
      continue;
      _matchToken("false", 1);
      localJsonToken = JsonToken.VALUE_FALSE;
      continue;
      _matchToken("null", 1);
      localJsonToken = JsonToken.VALUE_NULL;
      continue;
      _matchToken("true", 1);
      localJsonToken = JsonToken.VALUE_TRUE;
      continue;
      localJsonToken = JsonToken.START_ARRAY;
      continue;
      localJsonToken = JsonToken.START_OBJECT;
    }
  }
  
  public boolean nextFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    this._numTypesValid = 0;
    boolean bool;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      _nextAfterName();
      bool = false;
    }
    int i;
    for (;;)
    {
      return bool;
      if (this._tokenIncomplete) {
        _skipString();
      }
      i = _skipWSOrEnd();
      if (i < 0)
      {
        close();
        this._currToken = null;
        bool = false;
      }
      else
      {
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr - 1L);
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (-1 + (this._inputPtr - this._currInputRowStart));
        this._binaryValue = null;
        if (i == 93)
        {
          if (!this._parsingContext.inArray()) {
            _reportMismatchedEndMarker(i, '}');
          }
          this._parsingContext = this._parsingContext.getParent();
          this._currToken = JsonToken.END_ARRAY;
          bool = false;
        }
        else if (i == 125)
        {
          if (!this._parsingContext.inObject()) {
            _reportMismatchedEndMarker(i, ']');
          }
          this._parsingContext = this._parsingContext.getParent();
          this._currToken = JsonToken.END_OBJECT;
          bool = false;
        }
        else
        {
          if (this._parsingContext.expectComma())
          {
            if (i != 44) {
              _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
            }
            i = _skipWS();
          }
          if (this._parsingContext.inObject()) {
            break;
          }
          _nextTokenNotInObject(i);
          bool = false;
        }
      }
    }
    byte[] arrayOfByte;
    int k;
    int m;
    if (i == 34)
    {
      arrayOfByte = paramSerializableString.asQuotedUTF8();
      int j = arrayOfByte.length;
      if (4 + (j + this._inputPtr) < this._inputEnd)
      {
        k = j + this._inputPtr;
        if (this._inputBuffer[k] == 34) {
          m = 0;
        }
      }
    }
    for (int n = this._inputPtr;; n++)
    {
      if (n == k)
      {
        this._parsingContext.setCurrentName(paramSerializableString.getValue());
        _isNextTokenNameYes(_skipColonFast(n + 1));
        bool = true;
        break;
      }
      if (arrayOfByte[m] != this._inputBuffer[n])
      {
        bool = _isNextTokenNameMaybe(i, paramSerializableString);
        break;
      }
      m++;
    }
  }
  
  public int nextIntValue(int paramInt)
    throws IOException
  {
    JsonToken localJsonToken;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      this._nameCopied = false;
      localJsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
        paramInt = getIntValue();
      }
    }
    for (;;)
    {
      return paramInt;
      if (localJsonToken == JsonToken.START_ARRAY)
      {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      }
      else if (localJsonToken == JsonToken.START_OBJECT)
      {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        continue;
        if (nextToken() == JsonToken.VALUE_NUMBER_INT) {
          paramInt = getIntValue();
        }
      }
    }
  }
  
  public long nextLongValue(long paramLong)
    throws IOException
  {
    JsonToken localJsonToken;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      this._nameCopied = false;
      localJsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
        paramLong = getLongValue();
      }
    }
    for (;;)
    {
      return paramLong;
      if (localJsonToken == JsonToken.START_ARRAY)
      {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      }
      else if (localJsonToken == JsonToken.START_OBJECT)
      {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        continue;
        if (nextToken() == JsonToken.VALUE_NUMBER_INT) {
          paramLong = getLongValue();
        }
      }
    }
  }
  
  public String nextTextValue()
    throws IOException
  {
    String str = null;
    JsonToken localJsonToken;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      this._nameCopied = false;
      localJsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_STRING) {
        if (this._tokenIncomplete)
        {
          this._tokenIncomplete = false;
          str = _finishAndReturnString();
        }
      }
    }
    for (;;)
    {
      return str;
      str = this._textBuffer.contentsAsString();
      continue;
      if (localJsonToken == JsonToken.START_ARRAY)
      {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      }
      else if (localJsonToken == JsonToken.START_OBJECT)
      {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        continue;
        if (nextToken() == JsonToken.VALUE_STRING) {
          str = getText();
        }
      }
    }
  }
  
  public JsonToken nextToken()
    throws IOException
  {
    JsonToken localJsonToken1 = null;
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME) {
      localJsonToken1 = _nextAfterName();
    }
    int j;
    for (;;)
    {
      return localJsonToken1;
      if (this._tokenIncomplete) {
        _skipString();
      }
      int i = _skipWSOrEnd();
      if (i < 0)
      {
        close();
        this._currToken = null;
      }
      else
      {
        this._tokenInputTotal = (this._currInputProcessed + this._inputPtr - 1L);
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (-1 + (this._inputPtr - this._currInputRowStart));
        this._binaryValue = null;
        if (i == 93)
        {
          if (!this._parsingContext.inArray()) {
            _reportMismatchedEndMarker(i, '}');
          }
          this._parsingContext = this._parsingContext.getParent();
          localJsonToken1 = JsonToken.END_ARRAY;
          this._currToken = localJsonToken1;
        }
        else if (i == 125)
        {
          if (!this._parsingContext.inObject()) {
            _reportMismatchedEndMarker(i, ']');
          }
          this._parsingContext = this._parsingContext.getParent();
          localJsonToken1 = JsonToken.END_OBJECT;
          this._currToken = localJsonToken1;
        }
        else
        {
          if (this._parsingContext.expectComma())
          {
            if (i != 44) {
              _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
            }
            i = _skipWS();
          }
          if (!this._parsingContext.inObject())
          {
            localJsonToken1 = _nextTokenNotInObject(i);
          }
          else
          {
            String str = _parseName(i);
            this._parsingContext.setCurrentName(str);
            this._currToken = JsonToken.FIELD_NAME;
            j = _skipColon();
            if (j != 34) {
              break;
            }
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            localJsonToken1 = this._currToken;
          }
        }
      }
    }
    JsonToken localJsonToken2;
    switch (j)
    {
    default: 
      localJsonToken2 = _handleUnexpectedValue(j);
    }
    for (;;)
    {
      this._nextToken = localJsonToken2;
      localJsonToken1 = this._currToken;
      break;
      localJsonToken2 = _parseNegNumber();
      continue;
      localJsonToken2 = _parsePosNumber(j);
      continue;
      _matchToken("false", 1);
      localJsonToken2 = JsonToken.VALUE_FALSE;
      continue;
      _matchToken("null", 1);
      localJsonToken2 = JsonToken.VALUE_NULL;
      continue;
      _matchToken("true", 1);
      localJsonToken2 = JsonToken.VALUE_TRUE;
      continue;
      localJsonToken2 = JsonToken.START_ARRAY;
      continue;
      localJsonToken2 = JsonToken.START_OBJECT;
    }
  }
  
  protected final String parseEscapedName(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws IOException
  {
    int[] arrayOfInt = _icLatin1;
    label105:
    int k;
    label150:
    int i1;
    if (arrayOfInt[paramInt3] != 0)
    {
      if (paramInt3 == 34)
      {
        if (paramInt4 > 0)
        {
          if (paramInt1 >= paramArrayOfInt.length)
          {
            paramArrayOfInt = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
            this._quadBuffer = paramArrayOfInt;
          }
          int i2 = paramInt1 + 1;
          paramArrayOfInt[paramInt1] = pad(paramInt2, paramInt4);
          paramInt1 = i2;
        }
        String str = this._symbols.findName(paramArrayOfInt, paramInt1);
        if (str == null) {
          str = addName(paramArrayOfInt, paramInt1, paramInt4);
        }
        return str;
      }
      if (paramInt3 != 92)
      {
        _throwUnquotedSpace(paramInt3, "name");
        if (paramInt3 <= 127) {
          break label427;
        }
        if (paramInt4 < 4) {
          break label421;
        }
        if (paramInt1 >= paramArrayOfInt.length)
        {
          paramArrayOfInt = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
          this._quadBuffer = paramArrayOfInt;
        }
        k = paramInt1 + 1;
        paramArrayOfInt[paramInt1] = paramInt2;
        paramInt2 = 0;
        paramInt4 = 0;
        if (paramInt3 >= 2048) {
          break label283;
        }
        paramInt2 = paramInt2 << 8 | 0xC0 | paramInt3 >> 6;
        paramInt4++;
        i1 = k;
        paramInt3 = 0x80 | paramInt3 & 0x3F;
      }
    }
    label215:
    label283:
    label414:
    label421:
    label427:
    for (int i = i1;; i = paramInt1)
    {
      int m;
      int n;
      if (paramInt4 < 4)
      {
        paramInt4++;
        paramInt2 = paramInt3 | paramInt2 << 8;
        paramInt1 = i;
        if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
          _reportInvalidEOF(" in field name");
        }
        byte[] arrayOfByte = this._inputBuffer;
        int j = this._inputPtr;
        this._inputPtr = (j + 1);
        paramInt3 = 0xFF & arrayOfByte[j];
        break;
        paramInt3 = _decodeEscaped();
        break label105;
        m = paramInt2 << 8 | 0xE0 | paramInt3 >> 12;
        n = paramInt4 + 1;
        if (n < 4) {
          break label414;
        }
        if (k >= paramArrayOfInt.length)
        {
          paramArrayOfInt = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
          this._quadBuffer = paramArrayOfInt;
        }
        i1 = k + 1;
        paramArrayOfInt[k] = m;
        m = 0;
        n = 0;
      }
      for (;;)
      {
        paramInt2 = m << 8 | 0x80 | 0x3F & paramInt3 >> 6;
        paramInt4 = n + 1;
        break;
        if (i >= paramArrayOfInt.length)
        {
          paramArrayOfInt = growArrayBy(paramArrayOfInt, paramArrayOfInt.length);
          this._quadBuffer = paramArrayOfInt;
        }
        paramInt1 = i + 1;
        paramArrayOfInt[i] = paramInt2;
        paramInt2 = paramInt3;
        paramInt4 = 1;
        break label215;
        i1 = k;
      }
      k = paramInt1;
      break label150;
    }
  }
  
  protected final String parseLongName(int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    this._quadBuffer[0] = this._quad1;
    this._quadBuffer[1] = paramInt2;
    this._quadBuffer[2] = paramInt3;
    byte[] arrayOfByte = this._inputBuffer;
    int[] arrayOfInt1 = _icLatin1;
    int i = 3;
    int k;
    String str;
    if (4 + this._inputPtr <= this._inputEnd)
    {
      int j = this._inputPtr;
      this._inputPtr = (j + 1);
      k = 0xFF & arrayOfByte[j];
      if (arrayOfInt1[k] != 0) {
        if (k == 34) {
          str = findName(this._quadBuffer, i, paramInt1, 1);
        }
      }
    }
    for (;;)
    {
      return str;
      str = parseEscapedName(this._quadBuffer, i, paramInt1, k, 1);
      continue;
      int m = k | paramInt1 << 8;
      int n = this._inputPtr;
      this._inputPtr = (n + 1);
      int i1 = 0xFF & arrayOfByte[n];
      if (arrayOfInt1[i1] != 0)
      {
        if (i1 == 34) {
          str = findName(this._quadBuffer, i, m, 2);
        } else {
          str = parseEscapedName(this._quadBuffer, i, m, i1, 2);
        }
      }
      else
      {
        int i2 = i1 | m << 8;
        int i3 = this._inputPtr;
        this._inputPtr = (i3 + 1);
        int i4 = 0xFF & arrayOfByte[i3];
        if (arrayOfInt1[i4] != 0)
        {
          if (i4 == 34) {
            str = findName(this._quadBuffer, i, i2, 3);
          } else {
            str = parseEscapedName(this._quadBuffer, i, i2, i4, 3);
          }
        }
        else
        {
          int i5 = i4 | i2 << 8;
          int i6 = this._inputPtr;
          this._inputPtr = (i6 + 1);
          int i7 = 0xFF & arrayOfByte[i6];
          if (arrayOfInt1[i7] != 0)
          {
            if (i7 == 34) {
              str = findName(this._quadBuffer, i, i5, 4);
            } else {
              str = parseEscapedName(this._quadBuffer, i, i5, i7, 4);
            }
          }
          else
          {
            if (i >= this._quadBuffer.length) {
              this._quadBuffer = growArrayBy(this._quadBuffer, i);
            }
            int[] arrayOfInt2 = this._quadBuffer;
            int i8 = i + 1;
            arrayOfInt2[i] = i5;
            paramInt1 = i7;
            i = i8;
            break;
            str = parseEscapedName(this._quadBuffer, i, 0, paramInt1, 0);
          }
        }
      }
    }
  }
  
  protected final String parseMediumName(int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = this._inputBuffer;
    int[] arrayOfInt = _icLatin1;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = 0xFF & arrayOfByte[i];
    String str;
    if (arrayOfInt[j] != 0) {
      if (j == 34) {
        str = findName(this._quad1, paramInt, 1);
      }
    }
    for (;;)
    {
      return str;
      str = parseName(this._quad1, paramInt, j, 1);
      continue;
      int k = j | paramInt << 8;
      int m = this._inputPtr;
      this._inputPtr = (m + 1);
      int n = 0xFF & arrayOfByte[m];
      if (arrayOfInt[n] != 0)
      {
        if (n == 34) {
          str = findName(this._quad1, k, 2);
        } else {
          str = parseName(this._quad1, k, n, 2);
        }
      }
      else
      {
        int i1 = n | k << 8;
        int i2 = this._inputPtr;
        this._inputPtr = (i2 + 1);
        int i3 = 0xFF & arrayOfByte[i2];
        if (arrayOfInt[i3] != 0)
        {
          if (i3 == 34) {
            str = findName(this._quad1, i1, 3);
          } else {
            str = parseName(this._quad1, i1, i3, 3);
          }
        }
        else
        {
          int i4 = i3 | i1 << 8;
          int i5 = this._inputPtr;
          this._inputPtr = (i5 + 1);
          int i6 = 0xFF & arrayOfByte[i5];
          if (arrayOfInt[i6] != 0)
          {
            if (i6 == 34) {
              str = findName(this._quad1, i4, 4);
            } else {
              str = parseName(this._quad1, i4, i6, 4);
            }
          }
          else {
            str = parseMediumName2(i6, i4);
          }
        }
      }
    }
  }
  
  protected final String parseMediumName2(int paramInt1, int paramInt2)
    throws IOException
  {
    byte[] arrayOfByte = this._inputBuffer;
    int[] arrayOfInt = _icLatin1;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = 0xFF & arrayOfByte[i];
    String str;
    if (arrayOfInt[j] != 0) {
      if (j == 34) {
        str = findName(this._quad1, paramInt2, paramInt1, 1);
      }
    }
    for (;;)
    {
      return str;
      str = parseName(this._quad1, paramInt2, paramInt1, j, 1);
      continue;
      int k = j | paramInt1 << 8;
      int m = this._inputPtr;
      this._inputPtr = (m + 1);
      int n = 0xFF & arrayOfByte[m];
      if (arrayOfInt[n] != 0)
      {
        if (n == 34) {
          str = findName(this._quad1, paramInt2, k, 2);
        } else {
          str = parseName(this._quad1, paramInt2, k, n, 2);
        }
      }
      else
      {
        int i1 = n | k << 8;
        int i2 = this._inputPtr;
        this._inputPtr = (i2 + 1);
        int i3 = 0xFF & arrayOfByte[i2];
        if (arrayOfInt[i3] != 0)
        {
          if (i3 == 34) {
            str = findName(this._quad1, paramInt2, i1, 3);
          } else {
            str = parseName(this._quad1, paramInt2, i1, i3, 3);
          }
        }
        else
        {
          int i4 = i3 | i1 << 8;
          int i5 = this._inputPtr;
          this._inputPtr = (i5 + 1);
          int i6 = 0xFF & arrayOfByte[i5];
          if (arrayOfInt[i6] != 0)
          {
            if (i6 == 34) {
              str = findName(this._quad1, paramInt2, i4, 4);
            } else {
              str = parseName(this._quad1, paramInt2, i4, i6, 4);
            }
          }
          else {
            str = parseLongName(i6, paramInt2, i4);
          }
        }
      }
    }
  }
  
  /* Error */
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 48	com/fasterxml/jackson/core/json/UTF8StreamJsonParser:_tokenIncomplete	Z
    //   4: ifeq +13 -> 17
    //   7: aload_0
    //   8: getfield 186	com/fasterxml/jackson/core/json/UTF8StreamJsonParser:_currToken	Lcom/fasterxml/jackson/core/JsonToken;
    //   11: getstatic 193	com/fasterxml/jackson/core/JsonToken:VALUE_STRING	Lcom/fasterxml/jackson/core/JsonToken;
    //   14: if_acmpeq +21 -> 35
    //   17: aload_0
    //   18: aload_1
    //   19: invokevirtual 934	com/fasterxml/jackson/core/json/UTF8StreamJsonParser:getBinaryValue	(Lcom/fasterxml/jackson/core/Base64Variant;)[B
    //   22: astore_3
    //   23: aload_2
    //   24: aload_3
    //   25: invokevirtual 936	java/io/OutputStream:write	([B)V
    //   28: aload_3
    //   29: arraylength
    //   30: istore 4
    //   32: iload 4
    //   34: ireturn
    //   35: aload_0
    //   36: getfield 466	com/fasterxml/jackson/core/json/UTF8StreamJsonParser:_ioContext	Lcom/fasterxml/jackson/core/io/IOContext;
    //   39: invokevirtual 939	com/fasterxml/jackson/core/io/IOContext:allocBase64Buffer	()[B
    //   42: astore 5
    //   44: aload_0
    //   45: aload_1
    //   46: aload_2
    //   47: aload 5
    //   49: invokevirtual 941	com/fasterxml/jackson/core/json/UTF8StreamJsonParser:_readBinary	(Lcom/fasterxml/jackson/core/Base64Variant;Ljava/io/OutputStream;[B)I
    //   52: istore 7
    //   54: iload 7
    //   56: istore 4
    //   58: aload_0
    //   59: getfield 466	com/fasterxml/jackson/core/json/UTF8StreamJsonParser:_ioContext	Lcom/fasterxml/jackson/core/io/IOContext;
    //   62: aload 5
    //   64: invokevirtual 944	com/fasterxml/jackson/core/io/IOContext:releaseBase64Buffer	([B)V
    //   67: goto -35 -> 32
    //   70: astore 6
    //   72: aload_0
    //   73: getfield 466	com/fasterxml/jackson/core/json/UTF8StreamJsonParser:_ioContext	Lcom/fasterxml/jackson/core/io/IOContext;
    //   76: aload 5
    //   78: invokevirtual 944	com/fasterxml/jackson/core/io/IOContext:releaseBase64Buffer	([B)V
    //   81: aload 6
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	UTF8StreamJsonParser
    //   0	84	1	paramBase64Variant	Base64Variant
    //   0	84	2	paramOutputStream	OutputStream
    //   22	7	3	arrayOfByte1	byte[]
    //   30	27	4	i	int
    //   42	35	5	arrayOfByte2	byte[]
    //   70	12	6	localObject	Object
    //   52	3	7	j	int
    // Exception table:
    //   from	to	target	type
    //   44	54	70	finally
  }
  
  public int releaseBuffered(OutputStream paramOutputStream)
    throws IOException
  {
    int i = this._inputEnd - this._inputPtr;
    if (i < 1) {
      i = 0;
    }
    for (;;)
    {
      return i;
      int j = this._inputPtr;
      paramOutputStream.write(this._inputBuffer, j, i);
    }
  }
  
  public void setCodec(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
  }
  
  protected String slowParseName()
    throws IOException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing '\"' for name");
    }
    byte[] arrayOfByte = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = 0xFF & arrayOfByte[i];
    if (j == 34) {}
    for (String str = "";; str = parseEscapedName(this._quadBuffer, 0, 0, j, 0)) {
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/json/UTF8StreamJsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */