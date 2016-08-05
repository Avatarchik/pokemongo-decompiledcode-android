package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ParserBase
  extends ParserMinimalBase
{
  static final BigDecimal BD_MAX_INT = new BigDecimal(BI_MAX_INT);
  static final BigDecimal BD_MAX_LONG;
  static final BigDecimal BD_MIN_INT;
  static final BigDecimal BD_MIN_LONG;
  static final BigInteger BI_MAX_INT;
  static final BigInteger BI_MAX_LONG;
  static final BigInteger BI_MIN_INT = BigInteger.valueOf(-2147483648L);
  static final BigInteger BI_MIN_LONG;
  protected static final char CHAR_NULL = '\000';
  protected static final int INT_0 = 48;
  protected static final int INT_9 = 57;
  protected static final int INT_MINUS = 45;
  protected static final int INT_PLUS = 43;
  static final double MAX_INT_D = 2.147483647E9D;
  static final long MAX_INT_L = 2147483647L;
  static final double MAX_LONG_D = 9.223372036854776E18D;
  static final double MIN_INT_D = -2.147483648E9D;
  static final long MIN_INT_L = -2147483648L;
  static final double MIN_LONG_D = -9.223372036854776E18D;
  protected static final int NR_BIGDECIMAL = 16;
  protected static final int NR_BIGINT = 4;
  protected static final int NR_DOUBLE = 8;
  protected static final int NR_INT = 1;
  protected static final int NR_LONG = 2;
  protected static final int NR_UNKNOWN;
  protected byte[] _binaryValue;
  protected ByteArrayBuilder _byteArrayBuilder = null;
  protected boolean _closed;
  protected long _currInputProcessed = 0L;
  protected int _currInputRow = 1;
  protected int _currInputRowStart = 0;
  protected int _expLength;
  protected int _fractLength;
  protected int _inputEnd = 0;
  protected int _inputPtr = 0;
  protected int _intLength;
  protected final IOContext _ioContext;
  protected boolean _nameCopied = false;
  protected char[] _nameCopyBuffer = null;
  protected JsonToken _nextToken;
  protected int _numTypesValid = 0;
  protected BigDecimal _numberBigDecimal;
  protected BigInteger _numberBigInt;
  protected double _numberDouble;
  protected int _numberInt;
  protected long _numberLong;
  protected boolean _numberNegative;
  protected JsonReadContext _parsingContext;
  protected final TextBuffer _textBuffer;
  protected int _tokenInputCol = 0;
  protected int _tokenInputRow = 1;
  protected long _tokenInputTotal = 0L;
  
  static
  {
    BI_MAX_INT = BigInteger.valueOf(2147483647L);
    BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
    BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
    BD_MIN_INT = new BigDecimal(BI_MIN_INT);
  }
  
  protected ParserBase(IOContext paramIOContext, int paramInt)
  {
    super(paramInt);
    this._ioContext = paramIOContext;
    this._textBuffer = paramIOContext.constructTextBuffer();
    if (JsonParser.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(paramInt)) {
      localDupDetector = DupDetector.rootDetector(this);
    }
    this._parsingContext = JsonReadContext.createRootContext(localDupDetector);
  }
  
  private void _parseSlowFloat(int paramInt)
    throws IOException
  {
    if (paramInt == 16) {}
    try
    {
      this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
      this._numTypesValid = 16;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      _wrapError("Malformed numeric value '" + this._textBuffer.contentsAsString() + "'", localNumberFormatException);
    }
    this._numberDouble = this._textBuffer.contentsAsDouble();
    this._numTypesValid = 8;
  }
  
  private void _parseSlowInt(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
    throws IOException
  {
    String str = this._textBuffer.contentsAsString();
    try
    {
      if (NumberInput.inLongRange(paramArrayOfChar, paramInt2, paramInt3, this._numberNegative))
      {
        this._numberLong = Long.parseLong(str);
        this._numTypesValid = 2;
      }
      else
      {
        this._numberBigInt = new BigInteger(str);
        this._numTypesValid = 4;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      _wrapError("Malformed numeric value '" + str + "'", localNumberFormatException);
    }
  }
  
  protected abstract void _closeInput()
    throws IOException;
  
  protected final int _decodeBase64Escape(Base64Variant paramBase64Variant, char paramChar, int paramInt)
    throws IOException
  {
    if (paramChar != '\\') {
      throw reportInvalidBase64Char(paramBase64Variant, paramChar, paramInt);
    }
    char c = _decodeEscaped();
    int i;
    if ((c <= ' ') && (paramInt == 0)) {
      i = -1;
    }
    do
    {
      return i;
      i = paramBase64Variant.decodeBase64Char(c);
    } while (i >= 0);
    throw reportInvalidBase64Char(paramBase64Variant, c, paramInt);
  }
  
  protected final int _decodeBase64Escape(Base64Variant paramBase64Variant, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt1 != 92) {
      throw reportInvalidBase64Char(paramBase64Variant, paramInt1, paramInt2);
    }
    int i = _decodeEscaped();
    int j;
    if ((i <= 32) && (paramInt2 == 0)) {
      j = -1;
    }
    do
    {
      return j;
      j = paramBase64Variant.decodeBase64Char(i);
    } while (j >= 0);
    throw reportInvalidBase64Char(paramBase64Variant, i, paramInt2);
  }
  
  protected char _decodeEscaped()
    throws IOException
  {
    throw new UnsupportedOperationException();
  }
  
  protected final int _eofAsNextChar()
    throws JsonParseException
  {
    _handleEOF();
    return -1;
  }
  
  protected abstract void _finishString()
    throws IOException;
  
  public ByteArrayBuilder _getByteArrayBuilder()
  {
    if (this._byteArrayBuilder == null) {
      this._byteArrayBuilder = new ByteArrayBuilder();
    }
    for (;;)
    {
      return this._byteArrayBuilder;
      this._byteArrayBuilder.reset();
    }
  }
  
  protected void _handleEOF()
    throws JsonParseException
  {
    if (!this._parsingContext.inRoot()) {
      _reportInvalidEOF(": expected close marker for " + this._parsingContext.getTypeDesc() + " (from " + this._parsingContext.getStartLocation(this._ioContext.getSourceReference()) + ")");
    }
  }
  
  protected int _parseIntValue()
    throws IOException
  {
    int i;
    if (this._currToken == JsonToken.VALUE_NUMBER_INT)
    {
      char[] arrayOfChar = this._textBuffer.getTextBuffer();
      int j = this._textBuffer.getTextOffset();
      int k = this._intLength;
      if (this._numberNegative) {
        j++;
      }
      if (k <= 9)
      {
        i = NumberInput.parseInt(arrayOfChar, j, k);
        if (this._numberNegative) {
          i = -i;
        }
        this._numberInt = i;
        this._numTypesValid = 1;
      }
    }
    for (;;)
    {
      return i;
      _parseNumericValue(1);
      if ((0x1 & this._numTypesValid) == 0) {
        convertNumberToInt();
      }
      i = this._numberInt;
    }
  }
  
  protected void _parseNumericValue(int paramInt)
    throws IOException
  {
    char[] arrayOfChar;
    int i;
    int j;
    if (this._currToken == JsonToken.VALUE_NUMBER_INT)
    {
      arrayOfChar = this._textBuffer.getTextBuffer();
      i = this._textBuffer.getTextOffset();
      j = this._intLength;
      if (this._numberNegative) {
        i++;
      }
      if (j <= 9)
      {
        int k = NumberInput.parseInt(arrayOfChar, i, j);
        if (this._numberNegative) {
          k = -k;
        }
        this._numberInt = k;
        this._numTypesValid = 1;
      }
    }
    for (;;)
    {
      return;
      if (j <= 18)
      {
        long l = NumberInput.parseLong(arrayOfChar, i, j);
        if (this._numberNegative) {
          l = -l;
        }
        if (j == 10) {
          if (this._numberNegative)
          {
            if (l >= -2147483648L)
            {
              this._numberInt = ((int)l);
              this._numTypesValid = 1;
            }
          }
          else if (l <= 2147483647L)
          {
            this._numberInt = ((int)l);
            this._numTypesValid = 1;
            continue;
          }
        }
        this._numberLong = l;
        this._numTypesValid = 2;
      }
      else
      {
        _parseSlowInt(paramInt, arrayOfChar, i, j);
        continue;
        if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
          _parseSlowFloat(paramInt);
        } else {
          _reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
        }
      }
    }
  }
  
  protected void _releaseBuffers()
    throws IOException
  {
    this._textBuffer.releaseBuffers();
    char[] arrayOfChar = this._nameCopyBuffer;
    if (arrayOfChar != null)
    {
      this._nameCopyBuffer = null;
      this._ioContext.releaseNameCopyBuffer(arrayOfChar);
    }
  }
  
  protected void _reportMismatchedEndMarker(int paramInt, char paramChar)
    throws JsonParseException
  {
    String str = "" + this._parsingContext.getStartLocation(this._ioContext.getSourceReference());
    _reportError("Unexpected close marker '" + (char)paramInt + "': expected '" + paramChar + "' (for " + this._parsingContext.getTypeDesc() + " starting at " + str + ")");
  }
  
  public void close()
    throws IOException
  {
    if (!this._closed) {
      this._closed = true;
    }
    try
    {
      _closeInput();
      return;
    }
    finally
    {
      _releaseBuffers();
    }
  }
  
  protected void convertNumberToBigDecimal()
    throws IOException
  {
    if ((0x8 & this._numTypesValid) != 0) {
      this._numberBigDecimal = NumberInput.parseBigDecimal(getText());
    }
    for (;;)
    {
      this._numTypesValid = (0x10 | this._numTypesValid);
      return;
      if ((0x4 & this._numTypesValid) != 0) {
        this._numberBigDecimal = new BigDecimal(this._numberBigInt);
      } else if ((0x2 & this._numTypesValid) != 0) {
        this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
      } else if ((0x1 & this._numTypesValid) != 0) {
        this._numberBigDecimal = BigDecimal.valueOf(this._numberInt);
      } else {
        _throwInternal();
      }
    }
  }
  
  protected void convertNumberToBigInteger()
    throws IOException
  {
    if ((0x10 & this._numTypesValid) != 0) {
      this._numberBigInt = this._numberBigDecimal.toBigInteger();
    }
    for (;;)
    {
      this._numTypesValid = (0x4 | this._numTypesValid);
      return;
      if ((0x2 & this._numTypesValid) != 0) {
        this._numberBigInt = BigInteger.valueOf(this._numberLong);
      } else if ((0x1 & this._numTypesValid) != 0) {
        this._numberBigInt = BigInteger.valueOf(this._numberInt);
      } else if ((0x8 & this._numTypesValid) != 0) {
        this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
      } else {
        _throwInternal();
      }
    }
  }
  
  protected void convertNumberToDouble()
    throws IOException
  {
    if ((0x10 & this._numTypesValid) != 0) {
      this._numberDouble = this._numberBigDecimal.doubleValue();
    }
    for (;;)
    {
      this._numTypesValid = (0x8 | this._numTypesValid);
      return;
      if ((0x4 & this._numTypesValid) != 0) {
        this._numberDouble = this._numberBigInt.doubleValue();
      } else if ((0x2 & this._numTypesValid) != 0) {
        this._numberDouble = this._numberLong;
      } else if ((0x1 & this._numTypesValid) != 0) {
        this._numberDouble = this._numberInt;
      } else {
        _throwInternal();
      }
    }
  }
  
  protected void convertNumberToInt()
    throws IOException
  {
    if ((0x2 & this._numTypesValid) != 0)
    {
      int i = (int)this._numberLong;
      if (i != this._numberLong) {
        _reportError("Numeric value (" + getText() + ") out of range of int");
      }
      this._numberInt = i;
    }
    for (;;)
    {
      this._numTypesValid = (0x1 | this._numTypesValid);
      return;
      if ((0x4 & this._numTypesValid) != 0)
      {
        if ((BI_MIN_INT.compareTo(this._numberBigInt) > 0) || (BI_MAX_INT.compareTo(this._numberBigInt) < 0)) {
          reportOverflowInt();
        }
        this._numberInt = this._numberBigInt.intValue();
      }
      else if ((0x8 & this._numTypesValid) != 0)
      {
        if ((this._numberDouble < -2.147483648E9D) || (this._numberDouble > 2.147483647E9D)) {
          reportOverflowInt();
        }
        this._numberInt = ((int)this._numberDouble);
      }
      else if ((0x10 & this._numTypesValid) != 0)
      {
        if ((BD_MIN_INT.compareTo(this._numberBigDecimal) > 0) || (BD_MAX_INT.compareTo(this._numberBigDecimal) < 0)) {
          reportOverflowInt();
        }
        this._numberInt = this._numberBigDecimal.intValue();
      }
      else
      {
        _throwInternal();
      }
    }
  }
  
  protected void convertNumberToLong()
    throws IOException
  {
    if ((0x1 & this._numTypesValid) != 0) {
      this._numberLong = this._numberInt;
    }
    for (;;)
    {
      this._numTypesValid = (0x2 | this._numTypesValid);
      return;
      if ((0x4 & this._numTypesValid) != 0)
      {
        if ((BI_MIN_LONG.compareTo(this._numberBigInt) > 0) || (BI_MAX_LONG.compareTo(this._numberBigInt) < 0)) {
          reportOverflowLong();
        }
        this._numberLong = this._numberBigInt.longValue();
      }
      else if ((0x8 & this._numTypesValid) != 0)
      {
        if ((this._numberDouble < -9.223372036854776E18D) || (this._numberDouble > 9.223372036854776E18D)) {
          reportOverflowLong();
        }
        this._numberLong = (this._numberDouble);
      }
      else if ((0x10 & this._numTypesValid) != 0)
      {
        if ((BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0) || (BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0)) {
          reportOverflowLong();
        }
        this._numberLong = this._numberBigDecimal.longValue();
      }
      else
      {
        _throwInternal();
      }
    }
  }
  
  public JsonParser disable(JsonParser.Feature paramFeature)
  {
    this._features &= (0xFFFFFFFF ^ paramFeature.getMask());
    if (paramFeature == JsonParser.Feature.STRICT_DUPLICATE_DETECTION) {
      this._parsingContext = this._parsingContext.withDupDetector(null);
    }
    return this;
  }
  
  public JsonParser enable(JsonParser.Feature paramFeature)
  {
    this._features |= paramFeature.getMask();
    if ((paramFeature == JsonParser.Feature.STRICT_DUPLICATE_DETECTION) && (this._parsingContext.getDupDetector() == null)) {
      this._parsingContext = this._parsingContext.withDupDetector(DupDetector.rootDetector(this));
    }
    return this;
  }
  
  public BigInteger getBigIntegerValue()
    throws IOException
  {
    if ((0x4 & this._numTypesValid) == 0)
    {
      if (this._numTypesValid == 0) {
        _parseNumericValue(4);
      }
      if ((0x4 & this._numTypesValid) == 0) {
        convertNumberToBigInteger();
      }
    }
    return this._numberBigInt;
  }
  
  public JsonLocation getCurrentLocation()
  {
    int i = 1 + (this._inputPtr - this._currInputRowStart);
    return new JsonLocation(this._ioContext.getSourceReference(), -1L, this._currInputProcessed + this._inputPtr, this._currInputRow, i);
  }
  
  public String getCurrentName()
    throws IOException
  {
    if ((this._currToken == JsonToken.START_OBJECT) || (this._currToken == JsonToken.START_ARRAY)) {}
    for (String str = this._parsingContext.getParent().getCurrentName();; str = this._parsingContext.getCurrentName()) {
      return str;
    }
  }
  
  public Object getCurrentValue()
  {
    return this._parsingContext.getCurrentValue();
  }
  
  public BigDecimal getDecimalValue()
    throws IOException
  {
    if ((0x10 & this._numTypesValid) == 0)
    {
      if (this._numTypesValid == 0) {
        _parseNumericValue(16);
      }
      if ((0x10 & this._numTypesValid) == 0) {
        convertNumberToBigDecimal();
      }
    }
    return this._numberBigDecimal;
  }
  
  public double getDoubleValue()
    throws IOException
  {
    if ((0x8 & this._numTypesValid) == 0)
    {
      if (this._numTypesValid == 0) {
        _parseNumericValue(8);
      }
      if ((0x8 & this._numTypesValid) == 0) {
        convertNumberToDouble();
      }
    }
    return this._numberDouble;
  }
  
  public Object getEmbeddedObject()
    throws IOException
  {
    return null;
  }
  
  public float getFloatValue()
    throws IOException
  {
    return (float)getDoubleValue();
  }
  
  public int getIntValue()
    throws IOException
  {
    if ((0x1 & this._numTypesValid) == 0) {
      if (this._numTypesValid != 0) {}
    }
    for (int i = _parseIntValue();; i = this._numberInt)
    {
      return i;
      if ((0x1 & this._numTypesValid) == 0) {
        convertNumberToInt();
      }
    }
  }
  
  public long getLongValue()
    throws IOException
  {
    if ((0x2 & this._numTypesValid) == 0)
    {
      if (this._numTypesValid == 0) {
        _parseNumericValue(2);
      }
      if ((0x2 & this._numTypesValid) == 0) {
        convertNumberToLong();
      }
    }
    return this._numberLong;
  }
  
  public JsonParser.NumberType getNumberType()
    throws IOException
  {
    if (this._numTypesValid == 0) {
      _parseNumericValue(0);
    }
    JsonParser.NumberType localNumberType;
    if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
      if ((0x1 & this._numTypesValid) != 0) {
        localNumberType = JsonParser.NumberType.INT;
      }
    }
    for (;;)
    {
      return localNumberType;
      if ((0x2 & this._numTypesValid) != 0)
      {
        localNumberType = JsonParser.NumberType.LONG;
      }
      else
      {
        localNumberType = JsonParser.NumberType.BIG_INTEGER;
        continue;
        if ((0x10 & this._numTypesValid) != 0) {
          localNumberType = JsonParser.NumberType.BIG_DECIMAL;
        } else {
          localNumberType = JsonParser.NumberType.DOUBLE;
        }
      }
    }
  }
  
  public Number getNumberValue()
    throws IOException
  {
    if (this._numTypesValid == 0) {
      _parseNumericValue(0);
    }
    Object localObject;
    if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
      if ((0x1 & this._numTypesValid) != 0) {
        localObject = Integer.valueOf(this._numberInt);
      }
    }
    for (;;)
    {
      return (Number)localObject;
      if ((0x2 & this._numTypesValid) != 0)
      {
        localObject = Long.valueOf(this._numberLong);
      }
      else if ((0x4 & this._numTypesValid) != 0)
      {
        localObject = this._numberBigInt;
      }
      else
      {
        localObject = this._numberBigDecimal;
        continue;
        if ((0x10 & this._numTypesValid) != 0)
        {
          localObject = this._numberBigDecimal;
        }
        else
        {
          if ((0x8 & this._numTypesValid) == 0) {
            _throwInternal();
          }
          localObject = Double.valueOf(this._numberDouble);
        }
      }
    }
  }
  
  public JsonReadContext getParsingContext()
  {
    return this._parsingContext;
  }
  
  public long getTokenCharacterOffset()
  {
    return this._tokenInputTotal;
  }
  
  public int getTokenColumnNr()
  {
    int i = this._tokenInputCol;
    if (i < 0) {}
    for (;;)
    {
      return i;
      i++;
    }
  }
  
  public int getTokenLineNr()
  {
    return this._tokenInputRow;
  }
  
  public JsonLocation getTokenLocation()
  {
    return new JsonLocation(this._ioContext.getSourceReference(), -1L, getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
  }
  
  public boolean hasTextCharacters()
  {
    boolean bool;
    if (this._currToken == JsonToken.VALUE_STRING) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      if (this._currToken == JsonToken.FIELD_NAME) {
        bool = this._nameCopied;
      } else {
        bool = false;
      }
    }
  }
  
  public boolean isClosed()
  {
    return this._closed;
  }
  
  protected abstract boolean loadMore()
    throws IOException;
  
  protected final void loadMoreGuaranteed()
    throws IOException
  {
    if (!loadMore()) {
      _reportInvalidEOF();
    }
  }
  
  public void overrideCurrentName(String paramString)
  {
    JsonReadContext localJsonReadContext = this._parsingContext;
    if ((this._currToken == JsonToken.START_OBJECT) || (this._currToken == JsonToken.START_ARRAY)) {
      localJsonReadContext = localJsonReadContext.getParent();
    }
    try
    {
      localJsonReadContext.setCurrentName(paramString);
      return;
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }
  
  protected IllegalArgumentException reportInvalidBase64Char(Base64Variant paramBase64Variant, int paramInt1, int paramInt2)
    throws IllegalArgumentException
  {
    return reportInvalidBase64Char(paramBase64Variant, paramInt1, paramInt2, null);
  }
  
  protected IllegalArgumentException reportInvalidBase64Char(Base64Variant paramBase64Variant, int paramInt1, int paramInt2, String paramString)
    throws IllegalArgumentException
  {
    String str;
    if (paramInt1 <= 32) {
      str = "Illegal white space character (code 0x" + Integer.toHexString(paramInt1) + ") as character #" + (paramInt2 + 1) + " of 4-char base64 unit: can only used between units";
    }
    for (;;)
    {
      if (paramString != null) {
        str = str + ": " + paramString;
      }
      return new IllegalArgumentException(str);
      if (paramBase64Variant.usesPaddingChar(paramInt1)) {
        str = "Unexpected padding character ('" + paramBase64Variant.getPaddingChar() + "') as character #" + (paramInt2 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
      } else if ((!Character.isDefined(paramInt1)) || (Character.isISOControl(paramInt1))) {
        str = "Illegal character (code 0x" + Integer.toHexString(paramInt1) + ") in base64 content";
      } else {
        str = "Illegal character '" + (char)paramInt1 + "' (code 0x" + Integer.toHexString(paramInt1) + ") in base64 content";
      }
    }
  }
  
  protected void reportInvalidNumber(String paramString)
    throws JsonParseException
  {
    _reportError("Invalid numeric value: " + paramString);
  }
  
  protected void reportOverflowInt()
    throws IOException
  {
    _reportError("Numeric value (" + getText() + ") out of range of int (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
  }
  
  protected void reportOverflowLong()
    throws IOException
  {
    _reportError("Numeric value (" + getText() + ") out of range of long (" + Long.MIN_VALUE + " - " + Long.MAX_VALUE + ")");
  }
  
  protected void reportUnexpectedNumberChar(int paramInt, String paramString)
    throws JsonParseException
  {
    String str = "Unexpected character (" + _getCharDesc(paramInt) + ") in numeric value";
    if (paramString != null) {
      str = str + ": " + paramString;
    }
    _reportError(str);
  }
  
  protected final JsonToken reset(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt2 < 1) && (paramInt3 < 1)) {}
    for (JsonToken localJsonToken = resetInt(paramBoolean, paramInt1);; localJsonToken = resetFloat(paramBoolean, paramInt1, paramInt2, paramInt3)) {
      return localJsonToken;
    }
  }
  
  protected final JsonToken resetAsNaN(String paramString, double paramDouble)
  {
    this._textBuffer.resetWithString(paramString);
    this._numberDouble = paramDouble;
    this._numTypesValid = 8;
    return JsonToken.VALUE_NUMBER_FLOAT;
  }
  
  protected final JsonToken resetFloat(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    this._numberNegative = paramBoolean;
    this._intLength = paramInt1;
    this._fractLength = paramInt2;
    this._expLength = paramInt3;
    this._numTypesValid = 0;
    return JsonToken.VALUE_NUMBER_FLOAT;
  }
  
  protected final JsonToken resetInt(boolean paramBoolean, int paramInt)
  {
    this._numberNegative = paramBoolean;
    this._intLength = paramInt;
    this._fractLength = 0;
    this._expLength = 0;
    this._numTypesValid = 0;
    return JsonToken.VALUE_NUMBER_INT;
  }
  
  public void setCurrentValue(Object paramObject)
  {
    this._parsingContext.setCurrentValue(paramObject);
  }
  
  public JsonParser setFeatureMask(int paramInt)
  {
    if ((paramInt ^ this._features) != 0)
    {
      this._features = paramInt;
      if (!JsonParser.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(paramInt)) {
        break label51;
      }
      if (this._parsingContext.getDupDetector() != null) {}
    }
    label51:
    for (this._parsingContext = this._parsingContext.withDupDetector(DupDetector.rootDetector(this));; this._parsingContext = this._parsingContext.withDupDetector(null)) {
      return this;
    }
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/base/ParserBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */