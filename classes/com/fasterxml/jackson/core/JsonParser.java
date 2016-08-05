package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

public abstract class JsonParser
  implements Closeable, Versioned
{
  private static final int MAX_BYTE_I = 255;
  private static final int MAX_SHORT_I = 32767;
  private static final int MIN_BYTE_I = -128;
  private static final int MIN_SHORT_I = -32768;
  protected int _features;
  
  protected JsonParser() {}
  
  protected JsonParser(int paramInt)
  {
    this._features = paramInt;
  }
  
  protected ObjectCodec _codec()
  {
    ObjectCodec localObjectCodec = getCodec();
    if (localObjectCodec == null) {
      throw new IllegalStateException("No ObjectCodec defined for parser, needed for deserialization");
    }
    return localObjectCodec;
  }
  
  protected JsonParseException _constructError(String paramString)
  {
    return new JsonParseException(paramString, getCurrentLocation());
  }
  
  protected void _reportUnsupportedOperation()
  {
    throw new UnsupportedOperationException("Operation not supported by parser of type " + getClass().getName());
  }
  
  public boolean canReadObjectId()
  {
    return false;
  }
  
  public boolean canReadTypeId()
  {
    return false;
  }
  
  public boolean canUseSchema(FormatSchema paramFormatSchema)
  {
    return false;
  }
  
  public abstract void clearCurrentToken();
  
  public abstract void close()
    throws IOException;
  
  public JsonParser configure(Feature paramFeature, boolean paramBoolean)
  {
    if (paramBoolean) {
      enable(paramFeature);
    }
    for (;;)
    {
      return this;
      disable(paramFeature);
    }
  }
  
  public JsonParser disable(Feature paramFeature)
  {
    this._features &= (0xFFFFFFFF ^ paramFeature.getMask());
    return this;
  }
  
  public JsonParser enable(Feature paramFeature)
  {
    this._features |= paramFeature.getMask();
    return this;
  }
  
  public abstract BigInteger getBigIntegerValue()
    throws IOException;
  
  public byte[] getBinaryValue()
    throws IOException
  {
    return getBinaryValue(Base64Variants.getDefaultVariant());
  }
  
  public abstract byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException;
  
  public boolean getBooleanValue()
    throws IOException
  {
    JsonToken localJsonToken = getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_TRUE) {}
    for (boolean bool = true;; bool = false)
    {
      return bool;
      if (localJsonToken != JsonToken.VALUE_FALSE) {
        break;
      }
    }
    throw new JsonParseException("Current token (" + localJsonToken + ") not of boolean type", getCurrentLocation());
  }
  
  public byte getByteValue()
    throws IOException
  {
    int i = getIntValue();
    if ((i < -128) || (i > 255)) {
      throw _constructError("Numeric value (" + getText() + ") out of range of Java byte");
    }
    return (byte)i;
  }
  
  public abstract ObjectCodec getCodec();
  
  public abstract JsonLocation getCurrentLocation();
  
  public abstract String getCurrentName()
    throws IOException;
  
  public abstract JsonToken getCurrentToken();
  
  public abstract int getCurrentTokenId();
  
  public Object getCurrentValue()
  {
    JsonStreamContext localJsonStreamContext = getParsingContext();
    if (localJsonStreamContext == null) {}
    for (Object localObject = null;; localObject = localJsonStreamContext.getCurrentValue()) {
      return localObject;
    }
  }
  
  public abstract BigDecimal getDecimalValue()
    throws IOException;
  
  public abstract double getDoubleValue()
    throws IOException;
  
  public abstract Object getEmbeddedObject()
    throws IOException;
  
  public int getFeatureMask()
  {
    return this._features;
  }
  
  public abstract float getFloatValue()
    throws IOException;
  
  public int getFormatFeatures()
  {
    return 0;
  }
  
  public Object getInputSource()
  {
    return null;
  }
  
  public abstract int getIntValue()
    throws IOException;
  
  public abstract JsonToken getLastClearedToken();
  
  public abstract long getLongValue()
    throws IOException;
  
  public abstract NumberType getNumberType()
    throws IOException;
  
  public abstract Number getNumberValue()
    throws IOException;
  
  public Object getObjectId()
    throws IOException
  {
    return null;
  }
  
  public abstract JsonStreamContext getParsingContext();
  
  public FormatSchema getSchema()
  {
    return null;
  }
  
  public short getShortValue()
    throws IOException
  {
    int i = getIntValue();
    if ((i < 32768) || (i > 32767)) {
      throw _constructError("Numeric value (" + getText() + ") out of range of Java short");
    }
    return (short)i;
  }
  
  public abstract String getText()
    throws IOException;
  
  public abstract char[] getTextCharacters()
    throws IOException;
  
  public abstract int getTextLength()
    throws IOException;
  
  public abstract int getTextOffset()
    throws IOException;
  
  public abstract JsonLocation getTokenLocation();
  
  public Object getTypeId()
    throws IOException
  {
    return null;
  }
  
  public boolean getValueAsBoolean()
    throws IOException
  {
    return getValueAsBoolean(false);
  }
  
  public boolean getValueAsBoolean(boolean paramBoolean)
    throws IOException
  {
    return paramBoolean;
  }
  
  public double getValueAsDouble()
    throws IOException
  {
    return getValueAsDouble(0.0D);
  }
  
  public double getValueAsDouble(double paramDouble)
    throws IOException
  {
    return paramDouble;
  }
  
  public int getValueAsInt()
    throws IOException
  {
    return getValueAsInt(0);
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException
  {
    return paramInt;
  }
  
  public long getValueAsLong()
    throws IOException
  {
    return getValueAsLong(0L);
  }
  
  public long getValueAsLong(long paramLong)
    throws IOException
  {
    return paramLong;
  }
  
  public String getValueAsString()
    throws IOException
  {
    return getValueAsString(null);
  }
  
  public abstract String getValueAsString(String paramString)
    throws IOException;
  
  public abstract boolean hasCurrentToken();
  
  public abstract boolean hasTextCharacters();
  
  public abstract boolean hasToken(JsonToken paramJsonToken);
  
  public abstract boolean hasTokenId(int paramInt);
  
  public abstract boolean isClosed();
  
  public boolean isEnabled(Feature paramFeature)
  {
    return paramFeature.enabledIn(this._features);
  }
  
  public boolean isExpectedStartArrayToken()
  {
    if (getCurrentToken() == JsonToken.START_ARRAY) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isExpectedStartObjectToken()
  {
    if (getCurrentToken() == JsonToken.START_OBJECT) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Boolean nextBooleanValue()
    throws IOException, JsonParseException
  {
    JsonToken localJsonToken = nextToken();
    Boolean localBoolean;
    if (localJsonToken == JsonToken.VALUE_TRUE) {
      localBoolean = Boolean.TRUE;
    }
    for (;;)
    {
      return localBoolean;
      if (localJsonToken == JsonToken.VALUE_FALSE) {
        localBoolean = Boolean.FALSE;
      } else {
        localBoolean = null;
      }
    }
  }
  
  public String nextFieldName()
    throws IOException, JsonParseException
  {
    if (nextToken() == JsonToken.FIELD_NAME) {}
    for (String str = getCurrentName();; str = null) {
      return str;
    }
  }
  
  public boolean nextFieldName(SerializableString paramSerializableString)
    throws IOException, JsonParseException
  {
    if ((nextToken() == JsonToken.FIELD_NAME) && (paramSerializableString.getValue().equals(getCurrentName()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int nextIntValue(int paramInt)
    throws IOException, JsonParseException
  {
    if (nextToken() == JsonToken.VALUE_NUMBER_INT) {
      paramInt = getIntValue();
    }
    return paramInt;
  }
  
  public long nextLongValue(long paramLong)
    throws IOException, JsonParseException
  {
    if (nextToken() == JsonToken.VALUE_NUMBER_INT) {
      paramLong = getLongValue();
    }
    return paramLong;
  }
  
  public String nextTextValue()
    throws IOException, JsonParseException
  {
    if (nextToken() == JsonToken.VALUE_STRING) {}
    for (String str = getText();; str = null) {
      return str;
    }
  }
  
  public abstract JsonToken nextToken()
    throws IOException, JsonParseException;
  
  public abstract JsonToken nextValue()
    throws IOException, JsonParseException;
  
  public abstract void overrideCurrentName(String paramString);
  
  public JsonParser overrideFormatFeatures(int paramInt1, int paramInt2)
  {
    throw new IllegalArgumentException("No FormatFeatures defined for parser of type " + getClass().getName());
  }
  
  public JsonParser overrideStdFeatures(int paramInt1, int paramInt2)
  {
    this._features = (this._features & (paramInt2 ^ 0xFFFFFFFF) | paramInt1 & paramInt2);
    return this;
  }
  
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException
  {
    _reportUnsupportedOperation();
    return 0;
  }
  
  public int readBinaryValue(OutputStream paramOutputStream)
    throws IOException
  {
    return readBinaryValue(Base64Variants.getDefaultVariant(), paramOutputStream);
  }
  
  public <T> T readValueAs(TypeReference<?> paramTypeReference)
    throws IOException
  {
    return (T)_codec().readValue(this, paramTypeReference);
  }
  
  public <T> T readValueAs(Class<T> paramClass)
    throws IOException
  {
    return (T)_codec().readValue(this, paramClass);
  }
  
  public <T extends TreeNode> T readValueAsTree()
    throws IOException
  {
    return _codec().readTree(this);
  }
  
  public <T> Iterator<T> readValuesAs(TypeReference<?> paramTypeReference)
    throws IOException
  {
    return _codec().readValues(this, paramTypeReference);
  }
  
  public <T> Iterator<T> readValuesAs(Class<T> paramClass)
    throws IOException
  {
    return _codec().readValues(this, paramClass);
  }
  
  public int releaseBuffered(OutputStream paramOutputStream)
    throws IOException
  {
    return -1;
  }
  
  public int releaseBuffered(Writer paramWriter)
    throws IOException
  {
    return -1;
  }
  
  public boolean requiresCustomCodec()
  {
    return false;
  }
  
  public abstract void setCodec(ObjectCodec paramObjectCodec);
  
  public void setCurrentValue(Object paramObject)
  {
    JsonStreamContext localJsonStreamContext = getParsingContext();
    if (localJsonStreamContext != null) {
      localJsonStreamContext.setCurrentValue(paramObject);
    }
  }
  
  public JsonParser setFeatureMask(int paramInt)
  {
    this._features = paramInt;
    return this;
  }
  
  public void setSchema(FormatSchema paramFormatSchema)
  {
    throw new UnsupportedOperationException("Parser of type " + getClass().getName() + " does not support schema of type '" + paramFormatSchema.getSchemaType() + "'");
  }
  
  public abstract JsonParser skipChildren()
    throws IOException, JsonParseException;
  
  public abstract Version version();
  
  public static enum Feature
  {
    private final boolean _defaultState;
    private final int _mask = 1 << ordinal();
    
    static
    {
      ALLOW_COMMENTS = new Feature("ALLOW_COMMENTS", 1, false);
      ALLOW_YAML_COMMENTS = new Feature("ALLOW_YAML_COMMENTS", 2, false);
      ALLOW_UNQUOTED_FIELD_NAMES = new Feature("ALLOW_UNQUOTED_FIELD_NAMES", 3, false);
      ALLOW_SINGLE_QUOTES = new Feature("ALLOW_SINGLE_QUOTES", 4, false);
      ALLOW_UNQUOTED_CONTROL_CHARS = new Feature("ALLOW_UNQUOTED_CONTROL_CHARS", 5, false);
      ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER = new Feature("ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER", 6, false);
      ALLOW_NUMERIC_LEADING_ZEROS = new Feature("ALLOW_NUMERIC_LEADING_ZEROS", 7, false);
      ALLOW_NON_NUMERIC_NUMBERS = new Feature("ALLOW_NON_NUMERIC_NUMBERS", 8, false);
      STRICT_DUPLICATE_DETECTION = new Feature("STRICT_DUPLICATE_DETECTION", 9, false);
      IGNORE_UNDEFINED = new Feature("IGNORE_UNDEFINED", 10, false);
      Feature[] arrayOfFeature = new Feature[11];
      arrayOfFeature[0] = AUTO_CLOSE_SOURCE;
      arrayOfFeature[1] = ALLOW_COMMENTS;
      arrayOfFeature[2] = ALLOW_YAML_COMMENTS;
      arrayOfFeature[3] = ALLOW_UNQUOTED_FIELD_NAMES;
      arrayOfFeature[4] = ALLOW_SINGLE_QUOTES;
      arrayOfFeature[5] = ALLOW_UNQUOTED_CONTROL_CHARS;
      arrayOfFeature[6] = ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER;
      arrayOfFeature[7] = ALLOW_NUMERIC_LEADING_ZEROS;
      arrayOfFeature[8] = ALLOW_NON_NUMERIC_NUMBERS;
      arrayOfFeature[9] = STRICT_DUPLICATE_DETECTION;
      arrayOfFeature[10] = IGNORE_UNDEFINED;
      $VALUES = arrayOfFeature;
    }
    
    private Feature(boolean paramBoolean)
    {
      this._defaultState = paramBoolean;
    }
    
    public static int collectDefaults()
    {
      int i = 0;
      for (Feature localFeature : values()) {
        if (localFeature.enabledByDefault()) {
          i |= localFeature.getMask();
        }
      }
      return i;
    }
    
    public boolean enabledByDefault()
    {
      return this._defaultState;
    }
    
    public boolean enabledIn(int paramInt)
    {
      if ((paramInt & this._mask) != 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public int getMask()
    {
      return this._mask;
    }
  }
  
  public static enum NumberType
  {
    static
    {
      BIG_INTEGER = new NumberType("BIG_INTEGER", 2);
      FLOAT = new NumberType("FLOAT", 3);
      DOUBLE = new NumberType("DOUBLE", 4);
      BIG_DECIMAL = new NumberType("BIG_DECIMAL", 5);
      NumberType[] arrayOfNumberType = new NumberType[6];
      arrayOfNumberType[0] = INT;
      arrayOfNumberType[1] = LONG;
      arrayOfNumberType[2] = BIG_INTEGER;
      arrayOfNumberType[3] = FLOAT;
      arrayOfNumberType[4] = DOUBLE;
      arrayOfNumberType[5] = BIG_DECIMAL;
      $VALUES = arrayOfNumberType;
    }
    
    private NumberType() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */