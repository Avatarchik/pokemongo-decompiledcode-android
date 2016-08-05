package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class ParserMinimalBase
  extends JsonParser
{
  protected static final int INT_BACKSLASH = 92;
  protected static final int INT_COLON = 58;
  protected static final int INT_COMMA = 44;
  protected static final int INT_CR = 13;
  protected static final int INT_E = 69;
  protected static final int INT_HASH = 35;
  protected static final int INT_LBRACKET = 91;
  protected static final int INT_LCURLY = 123;
  protected static final int INT_LF = 10;
  protected static final int INT_PERIOD = 46;
  protected static final int INT_QUOTE = 34;
  protected static final int INT_RBRACKET = 93;
  protected static final int INT_RCURLY = 125;
  protected static final int INT_SLASH = 47;
  protected static final int INT_SPACE = 32;
  protected static final int INT_TAB = 9;
  protected static final int INT_e = 101;
  protected JsonToken _currToken;
  protected JsonToken _lastClearedToken;
  
  protected ParserMinimalBase() {}
  
  protected ParserMinimalBase(int paramInt)
  {
    super(paramInt);
  }
  
  protected static String _ascii(byte[] paramArrayOfByte)
  {
    try
    {
      String str = new String(paramArrayOfByte, "US-ASCII");
      return str;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  protected static byte[] _asciiBytes(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length()];
    int i = 0;
    int j = paramString.length();
    while (i < j)
    {
      arrayOfByte[i] = ((byte)paramString.charAt(i));
      i++;
    }
    return arrayOfByte;
  }
  
  protected static final String _getCharDesc(int paramInt)
  {
    char c = (char)paramInt;
    String str;
    if (Character.isISOControl(c)) {
      str = "(CTRL-CHAR, code " + paramInt + ")";
    }
    for (;;)
    {
      return str;
      if (paramInt > 255) {
        str = "'" + c + "' (code " + paramInt + " / 0x" + Integer.toHexString(paramInt) + ")";
      } else {
        str = "'" + c + "' (code " + paramInt + ")";
      }
    }
  }
  
  protected final JsonParseException _constructError(String paramString, Throwable paramThrowable)
  {
    return new JsonParseException(paramString, getCurrentLocation(), paramThrowable);
  }
  
  protected void _decodeBase64(String paramString, ByteArrayBuilder paramByteArrayBuilder, Base64Variant paramBase64Variant)
    throws IOException
  {
    try
    {
      paramBase64Variant.decode(paramString, paramByteArrayBuilder);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        _reportError(localIllegalArgumentException.getMessage());
      }
    }
  }
  
  protected abstract void _handleEOF()
    throws JsonParseException;
  
  protected char _handleUnrecognizedCharacterEscape(char paramChar)
    throws JsonProcessingException
  {
    if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {}
    for (;;)
    {
      return paramChar;
      if ((paramChar != '\'') || (!isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
        _reportError("Unrecognized character escape " + _getCharDesc(paramChar));
      }
    }
  }
  
  protected boolean _hasTextualNull(String paramString)
  {
    return "null".equals(paramString);
  }
  
  protected final void _reportError(String paramString)
    throws JsonParseException
  {
    throw _constructError(paramString);
  }
  
  protected void _reportInvalidEOF()
    throws JsonParseException
  {
    _reportInvalidEOF(" in " + this._currToken);
  }
  
  protected void _reportInvalidEOF(String paramString)
    throws JsonParseException
  {
    _reportError("Unexpected end-of-input" + paramString);
  }
  
  protected void _reportInvalidEOFInValue()
    throws JsonParseException
  {
    _reportInvalidEOF(" in a value");
  }
  
  protected void _reportMissingRootWS(int paramInt)
    throws JsonParseException
  {
    _reportUnexpectedChar(paramInt, "Expected space separating root-level values");
  }
  
  protected void _reportUnexpectedChar(int paramInt, String paramString)
    throws JsonParseException
  {
    if (paramInt < 0) {
      _reportInvalidEOF();
    }
    String str = "Unexpected character (" + _getCharDesc(paramInt) + ")";
    if (paramString != null) {
      str = str + ": " + paramString;
    }
    _reportError(str);
  }
  
  protected final void _throwInternal() {}
  
  protected void _throwInvalidSpace(int paramInt)
    throws JsonParseException
  {
    int i = (char)paramInt;
    _reportError("Illegal character (" + _getCharDesc(i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
  }
  
  protected void _throwUnquotedSpace(int paramInt, String paramString)
    throws JsonParseException
  {
    if ((!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS)) || (paramInt > 32))
    {
      int i = (char)paramInt;
      _reportError("Illegal unquoted character (" + _getCharDesc(i) + "): has to be escaped using backslash to be included in " + paramString);
    }
  }
  
  protected final void _wrapError(String paramString, Throwable paramThrowable)
    throws JsonParseException
  {
    throw _constructError(paramString, paramThrowable);
  }
  
  public void clearCurrentToken()
  {
    if (this._currToken != null)
    {
      this._lastClearedToken = this._currToken;
      this._currToken = null;
    }
  }
  
  public abstract void close()
    throws IOException;
  
  public abstract byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException;
  
  public abstract String getCurrentName()
    throws IOException;
  
  public JsonToken getCurrentToken()
  {
    return this._currToken;
  }
  
  public int getCurrentTokenId()
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == null) {}
    for (int i = 0;; i = localJsonToken.id()) {
      return i;
    }
  }
  
  public JsonToken getLastClearedToken()
  {
    return this._lastClearedToken;
  }
  
  public abstract JsonStreamContext getParsingContext();
  
  public abstract String getText()
    throws IOException;
  
  public abstract char[] getTextCharacters()
    throws IOException;
  
  public abstract int getTextLength()
    throws IOException;
  
  public abstract int getTextOffset()
    throws IOException;
  
  public boolean getValueAsBoolean(boolean paramBoolean)
    throws IOException
  {
    boolean bool = true;
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken != null) {}
    switch (localJsonToken.id())
    {
    case 8: 
    default: 
      bool = paramBoolean;
    }
    for (;;)
    {
      return bool;
      String str = getText().trim();
      if (!"true".equals(str)) {
        if ("false".equals(str))
        {
          bool = false;
        }
        else
        {
          if (!_hasTextualNull(str)) {
            break;
          }
          bool = false;
          continue;
          if (getIntValue() == 0)
          {
            bool = false;
            continue;
            bool = false;
            continue;
            Object localObject = getEmbeddedObject();
            if (!(localObject instanceof Boolean)) {
              break;
            }
            bool = ((Boolean)localObject).booleanValue();
          }
        }
      }
    }
  }
  
  public double getValueAsDouble(double paramDouble)
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken != null) {
      switch (localJsonToken.id())
      {
      }
    }
    for (;;)
    {
      return paramDouble;
      String str = getText();
      if (_hasTextualNull(str))
      {
        paramDouble = 0.0D;
      }
      else
      {
        paramDouble = NumberInput.parseAsDouble(str, paramDouble);
        continue;
        paramDouble = getDoubleValue();
        continue;
        paramDouble = 1.0D;
        continue;
        paramDouble = 0.0D;
        continue;
        Object localObject = getEmbeddedObject();
        if ((localObject instanceof Number)) {
          paramDouble = ((Number)localObject).doubleValue();
        }
      }
    }
  }
  
  public int getValueAsInt()
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    int i;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      i = getIntValue();
    }
    for (;;)
    {
      return i;
      if (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
        i = getIntValue();
      } else {
        i = getValueAsInt(0);
      }
    }
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      paramInt = getIntValue();
    }
    for (;;)
    {
      return paramInt;
      if (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
        paramInt = getIntValue();
      } else if (localJsonToken != null) {
        switch (localJsonToken.id())
        {
        case 7: 
        case 8: 
        default: 
          break;
        case 6: 
          String str = getText();
          if (_hasTextualNull(str)) {
            paramInt = 0;
          } else {
            paramInt = NumberInput.parseAsInt(str, paramInt);
          }
          break;
        case 9: 
          paramInt = 1;
          break;
        case 10: 
          paramInt = 0;
          break;
        case 11: 
          paramInt = 0;
          break;
        case 12: 
          Object localObject = getEmbeddedObject();
          if ((localObject instanceof Number)) {
            paramInt = ((Number)localObject).intValue();
          }
          break;
        }
      }
    }
  }
  
  public long getValueAsLong()
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    long l;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      l = getLongValue();
    }
    for (;;)
    {
      return l;
      if (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
        l = getLongValue();
      } else {
        l = getValueAsLong(0L);
      }
    }
  }
  
  public long getValueAsLong(long paramLong)
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      paramLong = getLongValue();
    }
    for (;;)
    {
      return paramLong;
      if (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
        paramLong = getLongValue();
      } else if (localJsonToken != null) {
        switch (localJsonToken.id())
        {
        case 7: 
        case 8: 
        default: 
          break;
        case 6: 
          String str = getText();
          if (_hasTextualNull(str)) {
            paramLong = 0L;
          } else {
            paramLong = NumberInput.parseAsLong(str, paramLong);
          }
          break;
        case 9: 
          paramLong = 1L;
          break;
        case 10: 
        case 11: 
          paramLong = 0L;
          break;
        case 12: 
          Object localObject = getEmbeddedObject();
          if ((localObject instanceof Number)) {
            paramLong = ((Number)localObject).longValue();
          }
          break;
        }
      }
    }
  }
  
  public String getValueAsString()
    throws IOException
  {
    String str;
    if (this._currToken == JsonToken.VALUE_STRING) {
      str = getText();
    }
    for (;;)
    {
      return str;
      if (this._currToken == JsonToken.FIELD_NAME) {
        str = getCurrentName();
      } else {
        str = getValueAsString(null);
      }
    }
  }
  
  public String getValueAsString(String paramString)
    throws IOException
  {
    if (this._currToken == JsonToken.VALUE_STRING) {
      paramString = getText();
    }
    for (;;)
    {
      return paramString;
      if (this._currToken == JsonToken.FIELD_NAME) {
        paramString = getCurrentName();
      } else if ((this._currToken != null) && (this._currToken != JsonToken.VALUE_NULL) && (this._currToken.isScalarValue())) {
        paramString = getText();
      }
    }
  }
  
  public boolean hasCurrentToken()
  {
    if (this._currToken != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public abstract boolean hasTextCharacters();
  
  public boolean hasToken(JsonToken paramJsonToken)
  {
    if (this._currToken == paramJsonToken) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasTokenId(int paramInt)
  {
    boolean bool = true;
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == null) {
      if (paramInt != 0) {}
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      if (localJsonToken.id() != paramInt) {
        bool = false;
      }
    }
  }
  
  public abstract boolean isClosed();
  
  public boolean isExpectedStartArrayToken()
  {
    if (this._currToken == JsonToken.START_ARRAY) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isExpectedStartObjectToken()
  {
    if (this._currToken == JsonToken.START_OBJECT) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public abstract JsonToken nextToken()
    throws IOException;
  
  public JsonToken nextValue()
    throws IOException
  {
    JsonToken localJsonToken = nextToken();
    if (localJsonToken == JsonToken.FIELD_NAME) {
      localJsonToken = nextToken();
    }
    return localJsonToken;
  }
  
  public abstract void overrideCurrentName(String paramString);
  
  public JsonParser skipChildren()
    throws IOException
  {
    if ((this._currToken != JsonToken.START_OBJECT) && (this._currToken != JsonToken.START_ARRAY)) {}
    for (;;)
    {
      return this;
      int i = 1;
      label53:
      do
      {
        JsonToken localJsonToken;
        do
        {
          for (;;)
          {
            localJsonToken = nextToken();
            if (localJsonToken == null)
            {
              _handleEOF();
              break;
            }
            if (!localJsonToken.isStructStart()) {
              break label53;
            }
            i++;
          }
        } while (!localJsonToken.isStructEnd());
        i--;
      } while (i != 0);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/base/ParserMinimalBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */