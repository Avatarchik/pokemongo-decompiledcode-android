package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class ReaderBasedJsonParser
  extends ParserBase
{
  protected static final int[] _icLatin1 = ;
  protected boolean _bufferRecyclable;
  protected final int _hashSeed;
  protected char[] _inputBuffer;
  protected ObjectCodec _objectCodec;
  protected Reader _reader;
  protected final CharsToNameCanonicalizer _symbols;
  protected boolean _tokenIncomplete = false;
  
  public ReaderBasedJsonParser(IOContext paramIOContext, int paramInt, Reader paramReader, ObjectCodec paramObjectCodec, CharsToNameCanonicalizer paramCharsToNameCanonicalizer)
  {
    super(paramIOContext, paramInt);
    this._reader = paramReader;
    this._inputBuffer = paramIOContext.allocTokenBuffer();
    this._inputPtr = 0;
    this._inputEnd = 0;
    this._objectCodec = paramObjectCodec;
    this._symbols = paramCharsToNameCanonicalizer;
    this._hashSeed = paramCharsToNameCanonicalizer.hashSeed();
    this._bufferRecyclable = true;
  }
  
  public ReaderBasedJsonParser(IOContext paramIOContext, int paramInt1, Reader paramReader, ObjectCodec paramObjectCodec, CharsToNameCanonicalizer paramCharsToNameCanonicalizer, char[] paramArrayOfChar, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    super(paramIOContext, paramInt1);
    this._reader = paramReader;
    this._inputBuffer = paramArrayOfChar;
    this._inputPtr = paramInt2;
    this._inputEnd = paramInt3;
    this._objectCodec = paramObjectCodec;
    this._symbols = paramCharsToNameCanonicalizer;
    this._hashSeed = paramCharsToNameCanonicalizer.hashSeed();
    this._bufferRecyclable = paramBoolean;
  }
  
  private String _handleOddName2(int paramInt1, int paramInt2, int[] paramArrayOfInt)
    throws IOException
  {
    this._textBuffer.resetWithShared(this._inputBuffer, paramInt1, this._inputPtr - paramInt1);
    char[] arrayOfChar1 = this._textBuffer.getCurrentSegment();
    int i = this._textBuffer.getCurrentSegmentSize();
    int j = paramArrayOfInt.length;
    for (;;)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
      int i1;
      for (;;)
      {
        this._textBuffer.setCurrentLength(i);
        TextBuffer localTextBuffer = this._textBuffer;
        char[] arrayOfChar2 = localTextBuffer.getTextBuffer();
        int m = localTextBuffer.getTextOffset();
        int n = localTextBuffer.size();
        return this._symbols.findSymbol(arrayOfChar2, m, n, paramInt2);
        int k = this._inputBuffer[this._inputPtr];
        if (k <= j)
        {
          if (paramArrayOfInt[k] != 0) {}
        }
        else {
          while (Character.isJavaIdentifierPart(k))
          {
            this._inputPtr = (1 + this._inputPtr);
            paramInt2 = k + paramInt2 * 33;
            i1 = i + 1;
            arrayOfChar1[i] = k;
            if (i1 < arrayOfChar1.length) {
              break label199;
            }
            arrayOfChar1 = this._textBuffer.finishCurrentSegment();
            i = 0;
            break;
          }
        }
      }
      label199:
      i = i1;
    }
  }
  
  private final void _matchFalse()
    throws IOException
  {
    int i = this._inputPtr;
    if (i + 4 < this._inputEnd)
    {
      char[] arrayOfChar = this._inputBuffer;
      if (arrayOfChar[i] == 'a')
      {
        int j = i + 1;
        if (arrayOfChar[j] == 'l')
        {
          int k = j + 1;
          if (arrayOfChar[k] == 's')
          {
            int m = k + 1;
            if (arrayOfChar[m] == 'e')
            {
              int n = m + 1;
              int i1 = arrayOfChar[n];
              if ((i1 < 48) || (i1 == 93) || (i1 == 125)) {
                this._inputPtr = n;
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      return;
      _matchToken("false", 1);
    }
  }
  
  private final void _matchNull()
    throws IOException
  {
    int i = this._inputPtr;
    if (i + 3 < this._inputEnd)
    {
      char[] arrayOfChar = this._inputBuffer;
      if (arrayOfChar[i] == 'u')
      {
        int j = i + 1;
        if (arrayOfChar[j] == 'l')
        {
          int k = j + 1;
          if (arrayOfChar[k] == 'l')
          {
            int m = k + 1;
            int n = arrayOfChar[m];
            if ((n < 48) || (n == 93) || (n == 125)) {
              this._inputPtr = m;
            }
          }
        }
      }
    }
    for (;;)
    {
      return;
      _matchToken("null", 1);
    }
  }
  
  private final void _matchTrue()
    throws IOException
  {
    int i = this._inputPtr;
    if (i + 3 < this._inputEnd)
    {
      char[] arrayOfChar = this._inputBuffer;
      if (arrayOfChar[i] == 'r')
      {
        int j = i + 1;
        if (arrayOfChar[j] == 'u')
        {
          int k = j + 1;
          if (arrayOfChar[k] == 'e')
          {
            int m = k + 1;
            int n = arrayOfChar[m];
            if ((n < 48) || (n == 93) || (n == 125)) {
              this._inputPtr = m;
            }
          }
        }
      }
    }
    for (;;)
    {
      return;
      _matchToken("true", 1);
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
        localJsonToken = _handleOddValue(paramInt);
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
  
  private final JsonToken _parseFloat(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4)
    throws IOException
  {
    int i = this._inputEnd;
    int j = 0;
    int i4;
    JsonToken localJsonToken;
    if (paramInt1 == 46)
    {
      i4 = paramInt3;
      if (i4 >= i) {
        localJsonToken = _parseNumber2(paramBoolean, paramInt2);
      }
    }
    int m;
    int n;
    for (;;)
    {
      return localJsonToken;
      char[] arrayOfChar4 = this._inputBuffer;
      paramInt3 = i4 + 1;
      paramInt1 = arrayOfChar4[i4];
      if ((paramInt1 < 48) || (paramInt1 > 57))
      {
        if (j == 0) {
          reportUnexpectedNumberChar(paramInt1, "Decimal point not followed by a digit");
        }
        k = paramInt3;
        m = 0;
        if ((paramInt1 != 101) && (paramInt1 != 69)) {
          break label286;
        }
        if (k >= i)
        {
          this._inputPtr = paramInt2;
          localJsonToken = _parseNumber2(paramBoolean, paramInt2);
        }
      }
      else
      {
        j++;
        i4 = paramInt3;
        break;
      }
      char[] arrayOfChar1 = this._inputBuffer;
      n = k + 1;
      paramInt1 = arrayOfChar1[k];
      if ((paramInt1 != 45) && (paramInt1 != 43)) {
        break label351;
      }
      if (n < i) {
        break label189;
      }
      this._inputPtr = paramInt2;
      localJsonToken = _parseNumber2(paramBoolean, paramInt2);
    }
    label189:
    char[] arrayOfChar2 = this._inputBuffer;
    int k = n + 1;
    paramInt1 = arrayOfChar2[n];
    for (;;)
    {
      if ((paramInt1 <= 57) && (paramInt1 >= 48))
      {
        m++;
        if (k >= i)
        {
          this._inputPtr = paramInt2;
          localJsonToken = _parseNumber2(paramBoolean, paramInt2);
          break;
        }
        char[] arrayOfChar3 = this._inputBuffer;
        int i3 = k + 1;
        paramInt1 = arrayOfChar3[k];
        k = i3;
        continue;
      }
      if (m == 0) {
        reportUnexpectedNumberChar(paramInt1, "Exponent indicator not followed by a digit");
      }
      label286:
      int i1 = -1 + k;
      this._inputPtr = i1;
      if (this._parsingContext.inRoot()) {
        _verifyRootSpace(paramInt1);
      }
      int i2 = i1 - paramInt2;
      this._textBuffer.resetWithShared(this._inputBuffer, paramInt2, i2);
      localJsonToken = resetFloat(paramBoolean, paramInt4, j, m);
      break;
      label351:
      k = n;
    }
  }
  
  private String _parseName2(int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    this._textBuffer.resetWithShared(this._inputBuffer, paramInt1, this._inputPtr - paramInt1);
    char[] arrayOfChar1 = this._textBuffer.getCurrentSegment();
    int i = this._textBuffer.getCurrentSegmentSize();
    for (;;)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidEOF(": was expecting closing '" + paramInt3 + "' for name");
      }
      char[] arrayOfChar2 = this._inputBuffer;
      int j = this._inputPtr;
      this._inputPtr = (j + 1);
      int k = arrayOfChar2[j];
      int m = k;
      if (m <= 92)
      {
        if (m != 92) {
          break label177;
        }
        k = _decodeEscaped();
      }
      int n;
      for (;;)
      {
        paramInt2 = k + paramInt2 * 33;
        n = i + 1;
        arrayOfChar1[i] = k;
        if (n < arrayOfChar1.length) {
          break label258;
        }
        arrayOfChar1 = this._textBuffer.finishCurrentSegment();
        i = 0;
        break;
        label177:
        if (m <= paramInt3)
        {
          if (m == paramInt3)
          {
            this._textBuffer.setCurrentLength(i);
            TextBuffer localTextBuffer = this._textBuffer;
            char[] arrayOfChar3 = localTextBuffer.getTextBuffer();
            int i1 = localTextBuffer.getTextOffset();
            int i2 = localTextBuffer.size();
            return this._symbols.findSymbol(arrayOfChar3, i1, i2, paramInt2);
          }
          if (m < 32) {
            _throwUnquotedSpace(m, "name");
          }
        }
      }
      label258:
      i = n;
    }
  }
  
  private final JsonToken _parseNumber2(boolean paramBoolean, int paramInt)
    throws IOException
  {
    if (paramBoolean) {
      paramInt++;
    }
    this._inputPtr = paramInt;
    char[] arrayOfChar1 = this._textBuffer.emptyAndGetCurrentSegment();
    int i = 0;
    if (paramBoolean)
    {
      int i15 = 0 + 1;
      arrayOfChar1[i] = '-';
      i = i15;
    }
    int j = 0;
    int k;
    int m;
    label99:
    int n;
    if (this._inputPtr < this._inputEnd)
    {
      char[] arrayOfChar7 = this._inputBuffer;
      int i14 = this._inputPtr;
      this._inputPtr = (i14 + 1);
      k = arrayOfChar7[i14];
      if (k == 48) {
        k = _verifyNoLeadingZeroes();
      }
      m = 0;
      if ((k < 48) || (k > 57)) {
        break label765;
      }
      j++;
      if (i >= arrayOfChar1.length)
      {
        arrayOfChar1 = this._textBuffer.finishCurrentSegment();
        i = 0;
      }
      n = i + 1;
      arrayOfChar1[i] = k;
      if ((this._inputPtr < this._inputEnd) || (loadMore())) {
        break label202;
      }
      k = 0;
      m = 1;
    }
    for (;;)
    {
      JsonToken localJsonToken;
      if (j == 0)
      {
        localJsonToken = _handleInvalidNumberStart(k, paramBoolean);
        return localJsonToken;
        k = getNextChar("No digit following minus sign");
        break;
        label202:
        char[] arrayOfChar6 = this._inputBuffer;
        int i13 = this._inputPtr;
        this._inputPtr = (i13 + 1);
        k = arrayOfChar6[i13];
        i = n;
        break label99;
      }
      int i1 = 0;
      int i2;
      if (k == 46)
      {
        i2 = n + 1;
        arrayOfChar1[n] = k;
        label258:
        if ((this._inputPtr >= this._inputEnd) && (!loadMore()))
        {
          m = 1;
          label279:
          if (i1 == 0) {
            reportUnexpectedNumberChar(k, "Decimal point not followed by a digit");
          }
        }
      }
      for (;;)
      {
        int i3 = 0;
        int i4;
        label377:
        int i5;
        if ((k == 101) || (k == 69))
        {
          if (i2 >= arrayOfChar1.length)
          {
            arrayOfChar1 = this._textBuffer.finishCurrentSegment();
            i2 = 0;
          }
          i4 = i2 + 1;
          arrayOfChar1[i2] = k;
          if (this._inputPtr >= this._inputEnd) {
            break label686;
          }
          char[] arrayOfChar4 = this._inputBuffer;
          int i10 = this._inputPtr;
          this._inputPtr = (i10 + 1);
          k = arrayOfChar4[i10];
          if ((k != 45) && (k != 43)) {
            break label751;
          }
          if (i4 < arrayOfChar1.length) {
            break label744;
          }
          arrayOfChar1 = this._textBuffer.finishCurrentSegment();
          i5 = 0;
          label409:
          int i6 = i5 + 1;
          arrayOfChar1[i5] = k;
          if (this._inputPtr >= this._inputEnd) {
            break label698;
          }
          char[] arrayOfChar3 = this._inputBuffer;
          int i9 = this._inputPtr;
          this._inputPtr = (i9 + 1);
          k = arrayOfChar3[i9];
          label459:
          i2 = i6;
        }
        for (;;)
        {
          int i7;
          if ((k <= 57) && (k >= 48))
          {
            i3++;
            if (i2 >= arrayOfChar1.length)
            {
              arrayOfChar1 = this._textBuffer.finishCurrentSegment();
              i2 = 0;
            }
            i7 = i2 + 1;
            arrayOfChar1[i2] = k;
            if ((this._inputPtr >= this._inputEnd) && (!loadMore()))
            {
              m = 1;
              i2 = i7;
            }
          }
          else
          {
            if (i3 == 0) {
              reportUnexpectedNumberChar(k, "Exponent indicator not followed by a digit");
            }
            if (m == 0)
            {
              this._inputPtr = (-1 + this._inputPtr);
              if (this._parsingContext.inRoot()) {
                _verifyRootSpace(k);
              }
            }
            this._textBuffer.setCurrentLength(i2);
            localJsonToken = reset(paramBoolean, j, i1, i3);
            break;
            char[] arrayOfChar5 = this._inputBuffer;
            int i11 = this._inputPtr;
            this._inputPtr = (i11 + 1);
            k = arrayOfChar5[i11];
            if ((k < 48) || (k > 57)) {
              break label279;
            }
            i1++;
            if (i2 >= arrayOfChar1.length)
            {
              arrayOfChar1 = this._textBuffer.finishCurrentSegment();
              i2 = 0;
            }
            int i12 = i2 + 1;
            arrayOfChar1[i2] = k;
            i2 = i12;
            break label258;
            label686:
            k = getNextChar("expected a digit for number exponent");
            break label377;
            label698:
            k = getNextChar("expected a digit for number exponent");
            break label459;
          }
          char[] arrayOfChar2 = this._inputBuffer;
          int i8 = this._inputPtr;
          this._inputPtr = (i8 + 1);
          k = arrayOfChar2[i8];
          i2 = i7;
          continue;
          label744:
          i5 = i4;
          break label409;
          label751:
          i2 = i4;
        }
        i2 = n;
      }
      label765:
      n = i;
    }
  }
  
  private final int _skipAfterComma2()
    throws IOException
  {
    while ((this._inputPtr < this._inputEnd) || (loadMore()))
    {
      char[] arrayOfChar = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      int j = arrayOfChar[i];
      if (j > 32)
      {
        if (j == 47) {
          _skipComment();
        } else if ((j != 35) || (!_skipYAMLComment())) {
          return j;
        }
      }
      else if (j < 32) {
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
  
  private void _skipCComment()
    throws IOException
  {
    for (;;)
    {
      int j;
      if ((this._inputPtr < this._inputEnd) || (loadMore()))
      {
        char[] arrayOfChar = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = (i + 1);
        j = arrayOfChar[i];
        if (j > 42) {
          continue;
        }
        if (j != 42) {
          break label104;
        }
        if ((this._inputPtr < this._inputEnd) || (loadMore())) {}
      }
      else
      {
        _reportInvalidEOF(" in a comment");
      }
      for (;;)
      {
        return;
        if (this._inputBuffer[this._inputPtr] != '/') {
          break;
        }
        this._inputPtr = (1 + this._inputPtr);
      }
      label104:
      if (j < 32) {
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
        char[] arrayOfChar4 = this._inputBuffer;
        int i1 = 1 + this._inputPtr;
        this._inputPtr = i1;
        k = arrayOfChar4[i1];
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
            char[] arrayOfChar5 = this._inputBuffer;
            int i2 = 1 + this._inputPtr;
            this._inputPtr = i2;
            k = arrayOfChar5[i2];
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
          char[] arrayOfChar1 = this._inputBuffer;
          int j = 1 + this._inputPtr;
          this._inputPtr = j;
          i = arrayOfChar1[j];
        }
        if (i == 58)
        {
          char[] arrayOfChar2 = this._inputBuffer;
          int m = 1 + this._inputPtr;
          this._inputPtr = m;
          k = arrayOfChar2[m];
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
              char[] arrayOfChar3 = this._inputBuffer;
              int n = 1 + this._inputPtr;
              this._inputPtr = n;
              k = arrayOfChar3[n];
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
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      char[] arrayOfChar = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      int j = arrayOfChar[i];
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
      else if (j < 32) {
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
  }
  
  private final int _skipComma(int paramInt)
    throws IOException
  {
    if (paramInt != 44) {
      _reportUnexpectedChar(paramInt, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
    }
    int k;
    int i;
    if (this._inputPtr < this._inputEnd)
    {
      char[] arrayOfChar = this._inputBuffer;
      int j = this._inputPtr;
      this._inputPtr = (j + 1);
      k = arrayOfChar[j];
      if (k > 32) {
        if ((k == 47) || (k == 35))
        {
          this._inputPtr = (-1 + this._inputPtr);
          i = _skipAfterComma2();
        }
      }
    }
    for (;;)
    {
      return i;
      i = k;
      continue;
      if (k >= 32) {
        break;
      }
      if (k == 10)
      {
        this._currInputRow = (1 + this._currInputRow);
        this._currInputRowStart = this._inputPtr;
        break;
      }
      if (k == 13)
      {
        _skipCR();
        break;
      }
      if (k == 9) {
        break;
      }
      _throwInvalidSpace(k);
      break;
      i = _skipAfterComma2();
    }
  }
  
  private void _skipComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
    }
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in a comment");
    }
    char[] arrayOfChar = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    int j = arrayOfChar[i];
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
  
  private void _skipLine()
    throws IOException
  {
    for (;;)
    {
      int j;
      if ((this._inputPtr < this._inputEnd) || (loadMore()))
      {
        char[] arrayOfChar = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = (i + 1);
        j = arrayOfChar[i];
        if (j >= 32) {
          continue;
        }
        if (j != 10) {
          break label70;
        }
        this._currInputRow = (1 + this._currInputRow);
        this._currInputRowStart = this._inputPtr;
      }
      for (;;)
      {
        return;
        label70:
        if (j != 13) {
          break;
        }
        _skipCR();
      }
      if (j != 9) {
        _throwInvalidSpace(j);
      }
    }
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
      char[] arrayOfChar1 = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      j = arrayOfChar1[i];
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
            break label187;
          }
          this._currInputRow = (1 + this._currInputRow);
          this._currInputRowStart = this._inputPtr;
        }
        for (;;)
        {
          if (this._inputPtr >= this._inputEnd) {
            break label274;
          }
          char[] arrayOfChar2 = this._inputBuffer;
          int k = this._inputPtr;
          this._inputPtr = (k + 1);
          j = arrayOfChar2[k];
          if (j > 32)
          {
            if ((j != 47) && (j != 35)) {
              break;
            }
            this._inputPtr = (-1 + this._inputPtr);
            j = _skipWSOrEnd2();
            break;
            label187:
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
        label274:
        j = _skipWSOrEnd2();
      }
    }
  }
  
  private int _skipWSOrEnd2()
    throws IOException
  {
    for (;;)
    {
      int j;
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        j = _eofAsNextChar();
      }
      do
      {
        return j;
        char[] arrayOfChar = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = (i + 1);
        j = arrayOfChar[i];
        if (j <= 32) {
          break label81;
        }
        if (j == 47)
        {
          _skipComment();
          break;
        }
      } while ((j != 35) || (!_skipYAMLComment()));
      continue;
      label81:
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
  }
  
  private boolean _skipYAMLComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_YAML_COMMENTS)) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      _skipLine();
    }
  }
  
  private char _verifyNLZ2()
    throws IOException
  {
    char c;
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      c = '0';
    }
    for (;;)
    {
      return c;
      c = this._inputBuffer[this._inputPtr];
      if ((c < '0') || (c > '9'))
      {
        c = '0';
      }
      else
      {
        if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
          reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr = (1 + this._inputPtr);
        if (c == '0') {
          if ((this._inputPtr < this._inputEnd) || (loadMore()))
          {
            c = this._inputBuffer[this._inputPtr];
            if ((c < '0') || (c > '9'))
            {
              c = '0';
            }
            else
            {
              this._inputPtr = (1 + this._inputPtr);
              if (c == '0') {
                break;
              }
            }
          }
        }
      }
    }
  }
  
  private final char _verifyNoLeadingZeroes()
    throws IOException
  {
    char c1 = '0';
    if (this._inputPtr < this._inputEnd)
    {
      char c2 = this._inputBuffer[this._inputPtr];
      if ((c2 >= c1) && (c2 <= '9')) {}
    }
    for (;;)
    {
      return c1;
      c1 = _verifyNLZ2();
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
  
  protected void _closeInput()
    throws IOException
  {
    if (this._reader != null)
    {
      if ((this._ioContext.isResourceManaged()) || (isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))) {
        this._reader.close();
      }
      this._reader = null;
    }
  }
  
  protected byte[] _decodeBase64(Base64Variant paramBase64Variant)
    throws IOException
  {
    ByteArrayBuilder localByteArrayBuilder = _getByteArrayBuilder();
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      char[] arrayOfChar1 = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      char c1 = arrayOfChar1[i];
      if (c1 > ' ')
      {
        int j = paramBase64Variant.decodeBase64Char(c1);
        byte[] arrayOfByte;
        if (j < 0) {
          if (c1 == '"') {
            arrayOfByte = localByteArrayBuilder.toByteArray();
          }
        }
        int i4;
        char c4;
        for (;;)
        {
          return arrayOfByte;
          j = _decodeBase64Escape(paramBase64Variant, c1, 0);
          if (j < 0) {
            break;
          }
          int k = j;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          char[] arrayOfChar2 = this._inputBuffer;
          int m = this._inputPtr;
          this._inputPtr = (m + 1);
          char c2 = arrayOfChar2[m];
          int n = paramBase64Variant.decodeBase64Char(c2);
          if (n < 0) {
            n = _decodeBase64Escape(paramBase64Variant, c2, 1);
          }
          int i1 = n | k << 6;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          char[] arrayOfChar3 = this._inputBuffer;
          int i2 = this._inputPtr;
          this._inputPtr = (i2 + 1);
          char c3 = arrayOfChar3[i2];
          int i3 = paramBase64Variant.decodeBase64Char(c3);
          if (i3 < 0)
          {
            if (i3 != -2)
            {
              if ((c3 == '"') && (!paramBase64Variant.usesPadding()))
              {
                localByteArrayBuilder.append(i1 >> 4);
                arrayOfByte = localByteArrayBuilder.toByteArray();
                continue;
              }
              i3 = _decodeBase64Escape(paramBase64Variant, c3, 2);
            }
            if (i3 == -2)
            {
              if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
              }
              char[] arrayOfChar5 = this._inputBuffer;
              int i7 = this._inputPtr;
              this._inputPtr = (i7 + 1);
              char c5 = arrayOfChar5[i7];
              if (!paramBase64Variant.usesPaddingChar(c5)) {
                throw reportInvalidBase64Char(paramBase64Variant, c5, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
              }
              localByteArrayBuilder.append(i1 >> 4);
              break;
            }
          }
          i4 = i3 | i1 << 6;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          char[] arrayOfChar4 = this._inputBuffer;
          int i5 = this._inputPtr;
          this._inputPtr = (i5 + 1);
          c4 = arrayOfChar4[i5];
          i6 = paramBase64Variant.decodeBase64Char(c4);
          if (i6 >= 0) {
            break label516;
          }
          if (i6 == -2) {
            break label498;
          }
          if ((c4 != '"') || (paramBase64Variant.usesPadding())) {
            break label488;
          }
          localByteArrayBuilder.appendTwoBytes(i4 >> 2);
          arrayOfByte = localByteArrayBuilder.toByteArray();
        }
        label488:
        int i6 = _decodeBase64Escape(paramBase64Variant, c4, 3);
        label498:
        if (i6 == -2) {
          localByteArrayBuilder.appendTwoBytes(i4 >> 2);
        } else {
          label516:
          localByteArrayBuilder.appendThreeBytes(i6 | i4 << 6);
        }
      }
    }
  }
  
  protected char _decodeEscaped()
    throws IOException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in character escape sequence");
    }
    char[] arrayOfChar1 = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    char c = arrayOfChar1[i];
    switch (c)
    {
    default: 
      c = _handleUnrecognizedCharacterEscape(c);
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
      int j = 0;
      for (int k = 0; k < 4; k++)
      {
        if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
          _reportInvalidEOF(" in character escape sequence");
        }
        char[] arrayOfChar2 = this._inputBuffer;
        int m = this._inputPtr;
        this._inputPtr = (m + 1);
        int n = arrayOfChar2[m];
        int i1 = CharTypes.charToHex(n);
        if (i1 < 0) {
          _reportUnexpectedChar(n, "expected a hex-digit for character escape sequence");
        }
        j = i1 | j << 4;
      }
      c = (char)j;
    }
  }
  
  protected final void _finishString()
    throws IOException
  {
    int i = this._inputPtr;
    int j = this._inputEnd;
    if (i < j)
    {
      int[] arrayOfInt = _icLatin1;
      int k = arrayOfInt.length;
      int m = this._inputBuffer[i];
      if ((m < k) && (arrayOfInt[m] != 0))
      {
        if (m != 34) {
          break label89;
        }
        this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, i - this._inputPtr);
        this._inputPtr = (i + 1);
      }
    }
    for (;;)
    {
      return;
      i++;
      if (i < j) {
        break;
      }
      label89:
      this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, i - this._inputPtr);
      this._inputPtr = i;
      _finishString2();
    }
  }
  
  protected void _finishString2()
    throws IOException
  {
    char[] arrayOfChar1 = this._textBuffer.getCurrentSegment();
    int i = this._textBuffer.getCurrentSegmentSize();
    int[] arrayOfInt = _icLatin1;
    int j = arrayOfInt.length;
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing quote for a string value");
    }
    char[] arrayOfChar2 = this._inputBuffer;
    int k = this._inputPtr;
    this._inputPtr = (k + 1);
    int m = arrayOfChar2[k];
    int n = m;
    if ((n < j) && (arrayOfInt[n] != 0))
    {
      if (n == 34)
      {
        this._textBuffer.setCurrentLength(i);
        return;
      }
      if (n != 92) {
        break label155;
      }
      m = _decodeEscaped();
    }
    for (;;)
    {
      if (i >= arrayOfChar1.length)
      {
        arrayOfChar1 = this._textBuffer.finishCurrentSegment();
        i = 0;
      }
      int i1 = i + 1;
      arrayOfChar1[i] = m;
      i = i1;
      break;
      label155:
      if (n < 32) {
        _throwUnquotedSpace(n, "string value");
      }
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
    char[] arrayOfChar1 = this._textBuffer.emptyAndGetCurrentSegment();
    int i = this._textBuffer.getCurrentSegmentSize();
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing quote for a string value");
    }
    char[] arrayOfChar2 = this._inputBuffer;
    int j = this._inputPtr;
    this._inputPtr = (j + 1);
    int k = arrayOfChar2[j];
    int m = k;
    if (m <= 92)
    {
      if (m != 92) {
        break label122;
      }
      k = _decodeEscaped();
    }
    for (;;)
    {
      if (i >= arrayOfChar1.length)
      {
        arrayOfChar1 = this._textBuffer.finishCurrentSegment();
        i = 0;
      }
      int n = i + 1;
      arrayOfChar1[i] = k;
      i = n;
      break;
      label122:
      if (m <= 39)
      {
        if (m == 39)
        {
          this._textBuffer.setCurrentLength(i);
          return JsonToken.VALUE_STRING;
        }
        if (m < 32) {
          _throwUnquotedSpace(m, "string value");
        }
      }
    }
  }
  
  protected JsonToken _handleInvalidNumberStart(int paramInt, boolean paramBoolean)
    throws IOException
  {
    double d = Double.NEGATIVE_INFINITY;
    JsonToken localJsonToken;
    if (paramInt == 73)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidEOFInValue();
      }
      char[] arrayOfChar = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      paramInt = arrayOfChar[i];
      if (paramInt != 78) {
        break label166;
      }
      String str2;
      if (paramBoolean)
      {
        str2 = "-INF";
        _matchToken(str2, 3);
        if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
          break label121;
        }
        if (!paramBoolean) {
          break label114;
        }
      }
      for (;;)
      {
        localJsonToken = resetAsNaN(str2, d);
        return localJsonToken;
        str2 = "+INF";
        break;
        label114:
        d = Double.POSITIVE_INFINITY;
      }
      label121:
      _reportError("Non-standard token '" + str2 + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
    }
    for (;;)
    {
      reportUnexpectedNumberChar(paramInt, "expected digit (0-9) to follow minus sign, for valid numeric value");
      localJsonToken = null;
      break;
      label166:
      if (paramInt == 110)
      {
        String str1;
        if (paramBoolean)
        {
          str1 = "-Infinity";
          label181:
          _matchToken(str1, 3);
          if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            break label229;
          }
          if (!paramBoolean) {
            break label222;
          }
        }
        for (;;)
        {
          localJsonToken = resetAsNaN(str1, d);
          break;
          str1 = "+Infinity";
          break label181;
          label222:
          d = Double.POSITIVE_INFINITY;
        }
        label229:
        _reportError("Non-standard token '" + str1 + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
      }
    }
  }
  
  protected String _handleOddName(int paramInt)
    throws IOException
  {
    String str;
    if ((paramInt == 39) && (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
      str = _parseAposName();
    }
    for (;;)
    {
      return str;
      if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
        _reportUnexpectedChar(paramInt, "was expecting double-quote to start field name");
      }
      int[] arrayOfInt = CharTypes.getInputCodeLatin1JsNames();
      int i = arrayOfInt.length;
      boolean bool;
      label64:
      int j;
      int k;
      int m;
      if (paramInt < i) {
        if (arrayOfInt[paramInt] == 0)
        {
          bool = true;
          if (!bool) {
            _reportUnexpectedChar(paramInt, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
          }
          j = this._inputPtr;
          k = this._hashSeed;
          m = this._inputEnd;
          if (j >= m) {
            break label249;
          }
        }
      }
      label229:
      do
      {
        int i1 = this._inputBuffer[j];
        if (i1 < i)
        {
          if (arrayOfInt[i1] == 0) {
            break label229;
          }
          int i3 = -1 + this._inputPtr;
          this._inputPtr = j;
          str = this._symbols.findSymbol(this._inputBuffer, i3, j - i3, k);
          break;
          bool = false;
          break label64;
          bool = Character.isJavaIdentifierPart((char)paramInt);
          break label64;
        }
        if (!Character.isJavaIdentifierPart((char)i1))
        {
          int i2 = -1 + this._inputPtr;
          this._inputPtr = j;
          str = this._symbols.findSymbol(this._inputBuffer, i2, j - i2, k);
          break;
        }
        k = i1 + k * 33;
        j++;
      } while (j < m);
      label249:
      int n = -1 + this._inputPtr;
      this._inputPtr = j;
      str = _handleOddName2(n, k, arrayOfInt);
    }
  }
  
  protected JsonToken _handleOddValue(int paramInt)
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
          char[] arrayOfChar = this._inputBuffer;
          int i = this._inputPtr;
          this._inputPtr = (i + 1);
          localJsonToken = _handleInvalidNumberStart(arrayOfChar[i], false);
        }
      }
    }
  }
  
  protected final void _matchToken(String paramString, int paramInt)
    throws IOException
  {
    int i = paramString.length();
    do
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
      if (this._inputBuffer[this._inputPtr] != paramString.charAt(paramInt)) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
      this._inputPtr = (1 + this._inputPtr);
      paramInt++;
    } while (paramInt < i);
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
    for (;;)
    {
      return;
      char c = this._inputBuffer[this._inputPtr];
      if ((c >= '0') && (c != ']') && (c != '}') && (Character.isJavaIdentifierPart(c))) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
    }
  }
  
  protected String _parseAposName()
    throws IOException
  {
    int i = this._inputPtr;
    int j = this._hashSeed;
    int k = this._inputEnd;
    int i1;
    String str;
    if (i < k)
    {
      int[] arrayOfInt = _icLatin1;
      int n = arrayOfInt.length;
      i1 = this._inputBuffer[i];
      if (i1 == 39)
      {
        int i2 = this._inputPtr;
        this._inputPtr = (i + 1);
        str = this._symbols.findSymbol(this._inputBuffer, i2, i - i2, j);
        label78:
        return str;
      }
      if ((i1 >= n) || (arrayOfInt[i1] == 0)) {
        break label121;
      }
    }
    for (;;)
    {
      int m = this._inputPtr;
      this._inputPtr = i;
      str = _parseName2(m, j, 39);
      break label78;
      label121:
      j = i1 + j * 33;
      i++;
      if (i < k) {
        break;
      }
    }
  }
  
  protected final String _parseName()
    throws IOException
  {
    int i = this._inputPtr;
    int j = this._hashSeed;
    int[] arrayOfInt = _icLatin1;
    int m;
    int n;
    if (i < this._inputEnd)
    {
      m = this._inputBuffer[i];
      if ((m < arrayOfInt.length) && (arrayOfInt[m] != 0))
      {
        if (m != 34) {
          break label101;
        }
        n = this._inputPtr;
        this._inputPtr = (i + 1);
      }
    }
    label101:
    int k;
    for (String str = this._symbols.findSymbol(this._inputBuffer, n, i - n, j);; str = _parseName2(k, j, 34))
    {
      return str;
      j = m + j * 33;
      i++;
      break;
      k = this._inputPtr;
      this._inputPtr = i;
    }
  }
  
  protected final JsonToken _parseNegNumber()
    throws IOException
  {
    int i = this._inputPtr;
    int j = i - 1;
    int k = this._inputEnd;
    JsonToken localJsonToken;
    if (i >= k) {
      localJsonToken = _parseNumber2(true, j);
    }
    for (;;)
    {
      return localJsonToken;
      char[] arrayOfChar1 = this._inputBuffer;
      int m = i + 1;
      int n = arrayOfChar1[i];
      if ((n > 57) || (n < 48))
      {
        this._inputPtr = m;
        localJsonToken = _handleInvalidNumberStart(n, true);
      }
      else if (n == 48)
      {
        localJsonToken = _parseNumber2(true, j);
      }
      else
      {
        int i1 = 1;
        int i2;
        int i3;
        for (;;)
        {
          if (m >= k)
          {
            localJsonToken = _parseNumber2(true, j);
            break;
          }
          char[] arrayOfChar2 = this._inputBuffer;
          i2 = m + 1;
          i3 = arrayOfChar2[m];
          if ((i3 < 48) || (i3 > 57))
          {
            if ((i3 != 46) && (i3 != 101) && (i3 != 69)) {
              break label213;
            }
            this._inputPtr = i2;
            localJsonToken = _parseFloat(i3, j, i2, true, i1);
            break;
          }
          i1++;
          m = i2;
        }
        label213:
        int i4 = i2 - 1;
        this._inputPtr = i4;
        if (this._parsingContext.inRoot()) {
          _verifyRootSpace(i3);
        }
        int i5 = i4 - j;
        this._textBuffer.resetWithShared(this._inputBuffer, j, i5);
        localJsonToken = resetInt(true, i1);
      }
    }
  }
  
  protected final JsonToken _parsePosNumber(int paramInt)
    throws IOException
  {
    int i = this._inputPtr;
    int j = i - 1;
    int k = this._inputEnd;
    JsonToken localJsonToken;
    if (paramInt == 48) {
      localJsonToken = _parseNumber2(false, j);
    }
    for (;;)
    {
      return localJsonToken;
      int m = 1;
      int i1;
      int i2;
      for (int n = i;; n = i1)
      {
        if (n >= k)
        {
          this._inputPtr = j;
          localJsonToken = _parseNumber2(false, j);
          break;
        }
        char[] arrayOfChar = this._inputBuffer;
        i1 = n + 1;
        i2 = arrayOfChar[n];
        if ((i2 < 48) || (i2 > 57))
        {
          if ((i2 != 46) && (i2 != 101) && (i2 != 69)) {
            break label151;
          }
          this._inputPtr = i1;
          localJsonToken = _parseFloat(i2, j, i1, false, m);
          break;
        }
        m++;
      }
      label151:
      int i3 = i1 - 1;
      this._inputPtr = i3;
      if (this._parsingContext.inRoot()) {
        _verifyRootSpace(i2);
      }
      int i4 = i3 - j;
      this._textBuffer.resetWithShared(this._inputBuffer, j, i4);
      localJsonToken = resetInt(false, m);
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
      char[] arrayOfChar1 = this._inputBuffer;
      int m = this._inputPtr;
      this._inputPtr = (m + 1);
      char c1 = arrayOfChar1[m];
      if (c1 > ' ')
      {
        int n = paramBase64Variant.decodeBase64Char(c1);
        if (n < 0) {
          if (c1 != '"') {}
        }
        int i7;
        char c4;
        for (;;)
        {
          this._tokenIncomplete = false;
          if (i > 0)
          {
            k += i;
            paramOutputStream.write(paramArrayOfByte, 0, i);
          }
          return k;
          n = _decodeBase64Escape(paramBase64Variant, c1, 0);
          if (n < 0) {
            break;
          }
          if (i > j)
          {
            k += i;
            paramOutputStream.write(paramArrayOfByte, 0, i);
            i = 0;
          }
          int i1 = n;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          char[] arrayOfChar2 = this._inputBuffer;
          int i2 = this._inputPtr;
          this._inputPtr = (i2 + 1);
          char c2 = arrayOfChar2[i2];
          int i3 = paramBase64Variant.decodeBase64Char(c2);
          if (i3 < 0) {
            i3 = _decodeBase64Escape(paramBase64Variant, c2, 1);
          }
          int i4 = i3 | i1 << 6;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          char[] arrayOfChar3 = this._inputBuffer;
          int i5 = this._inputPtr;
          this._inputPtr = (i5 + 1);
          char c3 = arrayOfChar3[i5];
          int i6 = paramBase64Variant.decodeBase64Char(c3);
          if (i6 < 0)
          {
            if (i6 != -2)
            {
              if ((c3 == '"') && (!paramBase64Variant.usesPadding()))
              {
                int i21 = i4 >> 4;
                int i22 = i + 1;
                paramArrayOfByte[i] = ((byte)i21);
                i = i22;
                continue;
              }
              i6 = _decodeBase64Escape(paramBase64Variant, c3, 2);
            }
            if (i6 == -2)
            {
              if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
              }
              char[] arrayOfChar5 = this._inputBuffer;
              int i18 = this._inputPtr;
              this._inputPtr = (i18 + 1);
              char c5 = arrayOfChar5[i18];
              if (!paramBase64Variant.usesPaddingChar(c5)) {
                throw reportInvalidBase64Char(paramBase64Variant, c5, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
              }
              int i19 = i4 >> 4;
              int i20 = i + 1;
              paramArrayOfByte[i] = ((byte)i19);
              i = i20;
              break;
            }
          }
          i7 = i6 | i4 << 6;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          char[] arrayOfChar4 = this._inputBuffer;
          int i8 = this._inputPtr;
          this._inputPtr = (i8 + 1);
          c4 = arrayOfChar4[i8];
          i9 = paramBase64Variant.decodeBase64Char(c4);
          if (i9 >= 0) {
            break label642;
          }
          if (i9 == -2) {
            break label597;
          }
          if ((c4 != '"') || (paramBase64Variant.usesPadding())) {
            break label587;
          }
          int i16 = i7 >> 2;
          int i17 = i + 1;
          paramArrayOfByte[i] = ((byte)(i16 >> 8));
          i = i17 + 1;
          paramArrayOfByte[i17] = ((byte)i16);
        }
        label587:
        int i9 = _decodeBase64Escape(paramBase64Variant, c4, 3);
        label597:
        if (i9 == -2)
        {
          int i14 = i7 >> 2;
          int i15 = i + 1;
          paramArrayOfByte[i] = ((byte)(i14 >> 8));
          i = i15 + 1;
          paramArrayOfByte[i15] = ((byte)i14);
        }
        else
        {
          label642:
          int i10 = i9 | i7 << 6;
          int i11 = i + 1;
          paramArrayOfByte[i] = ((byte)(i10 >> 16));
          int i12 = i11 + 1;
          paramArrayOfByte[i11] = ((byte)(i10 >> 8));
          int i13 = i12 + 1;
          paramArrayOfByte[i12] = ((byte)i10);
          i = i13;
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
      char[] arrayOfChar = this._inputBuffer;
      if (arrayOfChar != null)
      {
        this._inputBuffer = null;
        this._ioContext.releaseTokenBuffer(arrayOfChar);
      }
    }
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
        c = this._inputBuffer[this._inputPtr];
      } while (!Character.isJavaIdentifierPart(c));
      this._inputPtr = (1 + this._inputPtr);
      localStringBuilder.append(c);
    }
  }
  
  protected final void _skipCR()
    throws IOException
  {
    if (((this._inputPtr < this._inputEnd) || (loadMore())) && (this._inputBuffer[this._inputPtr] == '\n')) {
      this._inputPtr = (1 + this._inputPtr);
    }
    this._currInputRow = (1 + this._currInputRow);
    this._currInputRowStart = this._inputPtr;
  }
  
  protected final void _skipString()
    throws IOException
  {
    this._tokenIncomplete = false;
    int i = this._inputPtr;
    int j = this._inputEnd;
    char[] arrayOfChar = this._inputBuffer;
    for (;;)
    {
      if (i >= j)
      {
        this._inputPtr = i;
        if (!loadMore()) {
          _reportInvalidEOF(": was expecting closing quote for a string value");
        }
        i = this._inputPtr;
        j = this._inputEnd;
      }
      int k = i + 1;
      int m = arrayOfChar[i];
      if (m <= 92)
      {
        if (m == 92)
        {
          this._inputPtr = k;
          _decodeEscaped();
          i = this._inputPtr;
          j = this._inputEnd;
          continue;
        }
        if (m <= 34)
        {
          if (m == 34)
          {
            this._inputPtr = k;
            return;
          }
          if (m < 32)
          {
            this._inputPtr = k;
            _throwUnquotedSpace(m, "string value");
          }
        }
      }
      i = k;
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
  
  public Object getInputSource()
  {
    return this._reader;
  }
  
  protected char getNextChar(String paramString)
    throws IOException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(paramString);
    }
    char[] arrayOfChar = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    return arrayOfChar[i];
  }
  
  public final String getText()
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == JsonToken.VALUE_STRING) {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
    }
    for (String str = this._textBuffer.contentsAsString();; str = _getText2(localJsonToken)) {
      return str;
    }
  }
  
  public final char[] getTextCharacters()
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
  
  public final int getTextLength()
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
  
  public final int getTextOffset()
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
  
  public final String getValueAsString()
    throws IOException
  {
    String str;
    if (this._currToken == JsonToken.VALUE_STRING)
    {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      str = this._textBuffer.contentsAsString();
    }
    for (;;)
    {
      return str;
      if (this._currToken == JsonToken.FIELD_NAME) {
        str = getCurrentName();
      } else {
        str = super.getValueAsString(null);
      }
    }
  }
  
  public final String getValueAsString(String paramString)
    throws IOException
  {
    String str;
    if (this._currToken == JsonToken.VALUE_STRING)
    {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      str = this._textBuffer.contentsAsString();
    }
    for (;;)
    {
      return str;
      if (this._currToken == JsonToken.FIELD_NAME) {
        str = getCurrentName();
      } else {
        str = super.getValueAsString(paramString);
      }
    }
  }
  
  protected boolean loadMore()
    throws IOException
  {
    boolean bool = false;
    this._currInputProcessed += this._inputEnd;
    this._currInputRowStart -= this._inputEnd;
    int i;
    if (this._reader != null)
    {
      i = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
      if (i <= 0) {
        break label72;
      }
      this._inputPtr = 0;
      this._inputEnd = i;
      bool = true;
    }
    label72:
    do
    {
      return bool;
      _closeInput();
    } while (i != 0);
    throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
  }
  
  public final Boolean nextBooleanValue()
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
        if (localJsonToken1 != null)
        {
          int i = localJsonToken1.id();
          if (i == 9) {
            localBoolean = Boolean.TRUE;
          } else if (i == 10) {
            localBoolean = Boolean.FALSE;
          }
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
    int i;
    for (;;)
    {
      return str;
      if (this._tokenIncomplete) {
        _skipString();
      }
      i = _skipWSOrEnd();
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
          if (this._parsingContext.expectComma()) {
            i = _skipComma(i);
          }
          if (this._parsingContext.inObject()) {
            break;
          }
          _nextTokenNotInObject(i);
        }
      }
    }
    if (i == 34) {}
    int j;
    for (str = _parseName();; str = _handleOddName(i))
    {
      this._parsingContext.setCurrentName(str);
      this._currToken = JsonToken.FIELD_NAME;
      j = _skipColon();
      if (j != 34) {
        break label285;
      }
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      break;
    }
    label285:
    JsonToken localJsonToken;
    switch (j)
    {
    default: 
      localJsonToken = _handleOddValue(j);
    }
    for (;;)
    {
      this._nextToken = localJsonToken;
      break;
      localJsonToken = _parseNegNumber();
      continue;
      localJsonToken = _parsePosNumber(j);
      continue;
      _matchFalse();
      localJsonToken = JsonToken.VALUE_FALSE;
      continue;
      _matchNull();
      localJsonToken = JsonToken.VALUE_NULL;
      continue;
      _matchTrue();
      localJsonToken = JsonToken.VALUE_TRUE;
      continue;
      localJsonToken = JsonToken.START_ARRAY;
      continue;
      localJsonToken = JsonToken.START_OBJECT;
    }
  }
  
  public final int nextIntValue(int paramInt)
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
  
  public final long nextLongValue(long paramLong)
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
  
  public final String nextTextValue()
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
      if (localJsonToken == JsonToken.VALUE_STRING)
      {
        if (this._tokenIncomplete)
        {
          this._tokenIncomplete = false;
          _finishString();
        }
        str = this._textBuffer.contentsAsString();
      }
    }
    for (;;)
    {
      return str;
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
  
  public final JsonToken nextToken()
    throws IOException
  {
    JsonToken localJsonToken = null;
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME) {
      localJsonToken = _nextAfterName();
    }
    for (;;)
    {
      return localJsonToken;
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
          localJsonToken = JsonToken.END_ARRAY;
          this._currToken = localJsonToken;
        }
        else if (i == 125)
        {
          if (!this._parsingContext.inObject()) {
            _reportMismatchedEndMarker(i, ']');
          }
          this._parsingContext = this._parsingContext.getParent();
          localJsonToken = JsonToken.END_OBJECT;
          this._currToken = localJsonToken;
        }
        else
        {
          if (this._parsingContext.expectComma()) {
            i = _skipComma(i);
          }
          boolean bool = this._parsingContext.inObject();
          String str;
          if (bool)
          {
            if (i == 34)
            {
              str = _parseName();
              label233:
              this._parsingContext.setCurrentName(str);
              this._currToken = JsonToken.FIELD_NAME;
              i = _skipColon();
            }
          }
          else {
            switch (i)
            {
            default: 
              localJsonToken = _handleOddValue(i);
            }
          }
          for (;;)
          {
            if (!bool) {
              break label579;
            }
            this._nextToken = localJsonToken;
            localJsonToken = this._currToken;
            break;
            str = _handleOddName(i);
            break label233;
            this._tokenIncomplete = true;
            localJsonToken = JsonToken.VALUE_STRING;
            continue;
            if (!bool) {
              this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            }
            localJsonToken = JsonToken.START_ARRAY;
            continue;
            if (!bool) {
              this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            localJsonToken = JsonToken.START_OBJECT;
            continue;
            _reportUnexpectedChar(i, "expected a value");
            _matchTrue();
            localJsonToken = JsonToken.VALUE_TRUE;
            continue;
            _matchFalse();
            localJsonToken = JsonToken.VALUE_FALSE;
            continue;
            _matchNull();
            localJsonToken = JsonToken.VALUE_NULL;
            continue;
            localJsonToken = _parseNegNumber();
            continue;
            localJsonToken = _parsePosNumber(i);
          }
          label579:
          this._currToken = localJsonToken;
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
    //   1: getfield 36	com/fasterxml/jackson/core/json/ReaderBasedJsonParser:_tokenIncomplete	Z
    //   4: ifeq +13 -> 17
    //   7: aload_0
    //   8: getfield 163	com/fasterxml/jackson/core/json/ReaderBasedJsonParser:_currToken	Lcom/fasterxml/jackson/core/JsonToken;
    //   11: getstatic 174	com/fasterxml/jackson/core/JsonToken:VALUE_STRING	Lcom/fasterxml/jackson/core/JsonToken;
    //   14: if_acmpeq +21 -> 35
    //   17: aload_0
    //   18: aload_1
    //   19: invokevirtual 763	com/fasterxml/jackson/core/json/ReaderBasedJsonParser:getBinaryValue	(Lcom/fasterxml/jackson/core/Base64Variant;)[B
    //   22: astore_3
    //   23: aload_2
    //   24: aload_3
    //   25: invokevirtual 766	java/io/OutputStream:write	([B)V
    //   28: aload_3
    //   29: arraylength
    //   30: istore 4
    //   32: iload 4
    //   34: ireturn
    //   35: aload_0
    //   36: getfield 371	com/fasterxml/jackson/core/json/ReaderBasedJsonParser:_ioContext	Lcom/fasterxml/jackson/core/io/IOContext;
    //   39: invokevirtual 769	com/fasterxml/jackson/core/io/IOContext:allocBase64Buffer	()[B
    //   42: astore 5
    //   44: aload_0
    //   45: aload_1
    //   46: aload_2
    //   47: aload 5
    //   49: invokevirtual 771	com/fasterxml/jackson/core/json/ReaderBasedJsonParser:_readBinary	(Lcom/fasterxml/jackson/core/Base64Variant;Ljava/io/OutputStream;[B)I
    //   52: istore 7
    //   54: iload 7
    //   56: istore 4
    //   58: aload_0
    //   59: getfield 371	com/fasterxml/jackson/core/json/ReaderBasedJsonParser:_ioContext	Lcom/fasterxml/jackson/core/io/IOContext;
    //   62: aload 5
    //   64: invokevirtual 774	com/fasterxml/jackson/core/io/IOContext:releaseBase64Buffer	([B)V
    //   67: goto -35 -> 32
    //   70: astore 6
    //   72: aload_0
    //   73: getfield 371	com/fasterxml/jackson/core/json/ReaderBasedJsonParser:_ioContext	Lcom/fasterxml/jackson/core/io/IOContext;
    //   76: aload 5
    //   78: invokevirtual 774	com/fasterxml/jackson/core/io/IOContext:releaseBase64Buffer	([B)V
    //   81: aload 6
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	ReaderBasedJsonParser
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
  
  public int releaseBuffered(Writer paramWriter)
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
      paramWriter.write(this._inputBuffer, j, i);
    }
  }
  
  public void setCodec(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/json/ReaderBasedJsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */