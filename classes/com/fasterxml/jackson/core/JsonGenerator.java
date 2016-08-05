package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class JsonGenerator
  implements Closeable, Flushable, Versioned
{
  protected PrettyPrinter _cfgPrettyPrinter;
  
  protected void _reportError(String paramString)
    throws JsonGenerationException
  {
    throw new JsonGenerationException(paramString);
  }
  
  protected void _reportUnsupportedOperation()
  {
    throw new UnsupportedOperationException("Operation not supported by generator of type " + getClass().getName());
  }
  
  protected final void _throwInternal() {}
  
  protected void _writeSimpleObject(Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      if ((paramObject instanceof String))
      {
        writeString((String)paramObject);
      }
      else if ((paramObject instanceof Number))
      {
        Number localNumber = (Number)paramObject;
        if ((localNumber instanceof Integer))
        {
          writeNumber(localNumber.intValue());
        }
        else if ((localNumber instanceof Long))
        {
          writeNumber(localNumber.longValue());
        }
        else if ((localNumber instanceof Double))
        {
          writeNumber(localNumber.doubleValue());
        }
        else if ((localNumber instanceof Float))
        {
          writeNumber(localNumber.floatValue());
        }
        else if ((localNumber instanceof Short))
        {
          writeNumber(localNumber.shortValue());
        }
        else if ((localNumber instanceof Byte))
        {
          writeNumber((short)localNumber.byteValue());
        }
        else if ((localNumber instanceof BigInteger))
        {
          writeNumber((BigInteger)localNumber);
        }
        else if ((localNumber instanceof BigDecimal))
        {
          writeNumber((BigDecimal)localNumber);
        }
        else if ((localNumber instanceof AtomicInteger))
        {
          writeNumber(((AtomicInteger)localNumber).get());
        }
        else
        {
          if (!(localNumber instanceof AtomicLong)) {
            break;
          }
          writeNumber(((AtomicLong)localNumber).get());
        }
      }
      else if ((paramObject instanceof byte[]))
      {
        writeBinary((byte[])paramObject);
      }
      else if ((paramObject instanceof Boolean))
      {
        writeBoolean(((Boolean)paramObject).booleanValue());
      }
      else
      {
        if (!(paramObject instanceof AtomicBoolean)) {
          break;
        }
        writeBoolean(((AtomicBoolean)paramObject).get());
      }
    }
    throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + paramObject.getClass().getName() + ")");
  }
  
  public boolean canOmitFields()
  {
    return true;
  }
  
  public boolean canUseSchema(FormatSchema paramFormatSchema)
  {
    return false;
  }
  
  public boolean canWriteBinaryNatively()
  {
    return false;
  }
  
  public boolean canWriteObjectId()
  {
    return false;
  }
  
  public boolean canWriteTypeId()
  {
    return false;
  }
  
  public abstract void close()
    throws IOException;
  
  public final JsonGenerator configure(Feature paramFeature, boolean paramBoolean)
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
  
  public void copyCurrentEvent(JsonParser paramJsonParser)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == null) {
      _reportError("No current event to copy");
    }
    switch (localJsonToken.id())
    {
    case 0: 
    default: 
      _throwInternal();
    }
    for (;;)
    {
      return;
      _reportError("No current event to copy");
      writeStartObject();
      continue;
      writeEndObject();
      continue;
      writeStartArray();
      continue;
      writeEndArray();
      continue;
      writeFieldName(paramJsonParser.getCurrentName());
      continue;
      if (paramJsonParser.hasTextCharacters())
      {
        writeString(paramJsonParser.getTextCharacters(), paramJsonParser.getTextOffset(), paramJsonParser.getTextLength());
      }
      else
      {
        writeString(paramJsonParser.getText());
        continue;
        JsonParser.NumberType localNumberType2 = paramJsonParser.getNumberType();
        if (localNumberType2 == JsonParser.NumberType.INT)
        {
          writeNumber(paramJsonParser.getIntValue());
        }
        else if (localNumberType2 == JsonParser.NumberType.BIG_INTEGER)
        {
          writeNumber(paramJsonParser.getBigIntegerValue());
        }
        else
        {
          writeNumber(paramJsonParser.getLongValue());
          continue;
          JsonParser.NumberType localNumberType1 = paramJsonParser.getNumberType();
          if (localNumberType1 == JsonParser.NumberType.BIG_DECIMAL)
          {
            writeNumber(paramJsonParser.getDecimalValue());
          }
          else if (localNumberType1 == JsonParser.NumberType.FLOAT)
          {
            writeNumber(paramJsonParser.getFloatValue());
          }
          else
          {
            writeNumber(paramJsonParser.getDoubleValue());
            continue;
            writeBoolean(true);
            continue;
            writeBoolean(false);
            continue;
            writeNull();
            continue;
            writeObject(paramJsonParser.getEmbeddedObject());
          }
        }
      }
    }
  }
  
  public void copyCurrentStructure(JsonParser paramJsonParser)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == null) {
      _reportError("No current event to copy");
    }
    int i = localJsonToken.id();
    if (i == 5)
    {
      writeFieldName(paramJsonParser.getCurrentName());
      i = paramJsonParser.nextToken().id();
    }
    switch (i)
    {
    case 2: 
    default: 
      copyCurrentEvent(paramJsonParser);
    }
    for (;;)
    {
      return;
      writeStartObject();
      while (paramJsonParser.nextToken() != JsonToken.END_OBJECT) {
        copyCurrentStructure(paramJsonParser);
      }
      writeEndObject();
      continue;
      writeStartArray();
      while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        copyCurrentStructure(paramJsonParser);
      }
      writeEndArray();
    }
  }
  
  public abstract JsonGenerator disable(Feature paramFeature);
  
  public abstract JsonGenerator enable(Feature paramFeature);
  
  public abstract void flush()
    throws IOException;
  
  public CharacterEscapes getCharacterEscapes()
  {
    return null;
  }
  
  public abstract ObjectCodec getCodec();
  
  public Object getCurrentValue()
  {
    JsonStreamContext localJsonStreamContext = getOutputContext();
    if (localJsonStreamContext == null) {}
    for (Object localObject = null;; localObject = localJsonStreamContext.getCurrentValue()) {
      return localObject;
    }
  }
  
  public abstract int getFeatureMask();
  
  public int getFormatFeatures()
  {
    return 0;
  }
  
  public int getHighestEscapedChar()
  {
    return 0;
  }
  
  public int getOutputBuffered()
  {
    return -1;
  }
  
  public abstract JsonStreamContext getOutputContext();
  
  public Object getOutputTarget()
  {
    return null;
  }
  
  public PrettyPrinter getPrettyPrinter()
  {
    return this._cfgPrettyPrinter;
  }
  
  public FormatSchema getSchema()
  {
    return null;
  }
  
  public abstract boolean isClosed();
  
  public abstract boolean isEnabled(Feature paramFeature);
  
  public JsonGenerator overrideFormatFeatures(int paramInt1, int paramInt2)
  {
    throw new IllegalArgumentException("No FormatFeatures defined for generator of type " + getClass().getName());
  }
  
  public JsonGenerator overrideStdFeatures(int paramInt1, int paramInt2)
  {
    return setFeatureMask(getFeatureMask() & (paramInt2 ^ 0xFFFFFFFF) | paramInt1 & paramInt2);
  }
  
  public JsonGenerator setCharacterEscapes(CharacterEscapes paramCharacterEscapes)
  {
    return this;
  }
  
  public abstract JsonGenerator setCodec(ObjectCodec paramObjectCodec);
  
  public void setCurrentValue(Object paramObject)
  {
    JsonStreamContext localJsonStreamContext = getOutputContext();
    if (localJsonStreamContext != null) {
      localJsonStreamContext.setCurrentValue(paramObject);
    }
  }
  
  public abstract JsonGenerator setFeatureMask(int paramInt);
  
  public JsonGenerator setHighestNonEscapedChar(int paramInt)
  {
    return this;
  }
  
  public JsonGenerator setPrettyPrinter(PrettyPrinter paramPrettyPrinter)
  {
    this._cfgPrettyPrinter = paramPrettyPrinter;
    return this;
  }
  
  public JsonGenerator setRootValueSeparator(SerializableString paramSerializableString)
  {
    throw new UnsupportedOperationException();
  }
  
  public void setSchema(FormatSchema paramFormatSchema)
  {
    throw new UnsupportedOperationException("Generator of type " + getClass().getName() + " does not support schema of type '" + paramFormatSchema.getSchemaType() + "'");
  }
  
  public abstract JsonGenerator useDefaultPrettyPrinter();
  
  public abstract Version version();
  
  public final void writeArrayFieldStart(String paramString)
    throws IOException
  {
    writeFieldName(paramString);
    writeStartArray();
  }
  
  public abstract int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
    throws IOException;
  
  public int writeBinary(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    return writeBinary(Base64Variants.getDefaultVariant(), paramInputStream, paramInt);
  }
  
  public abstract void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public void writeBinary(byte[] paramArrayOfByte)
    throws IOException
  {
    writeBinary(Base64Variants.getDefaultVariant(), paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public void writeBinary(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    writeBinary(Base64Variants.getDefaultVariant(), paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public final void writeBinaryField(String paramString, byte[] paramArrayOfByte)
    throws IOException
  {
    writeFieldName(paramString);
    writeBinary(paramArrayOfByte);
  }
  
  public abstract void writeBoolean(boolean paramBoolean)
    throws IOException;
  
  public final void writeBooleanField(String paramString, boolean paramBoolean)
    throws IOException
  {
    writeFieldName(paramString);
    writeBoolean(paramBoolean);
  }
  
  public abstract void writeEndArray()
    throws IOException;
  
  public abstract void writeEndObject()
    throws IOException;
  
  public abstract void writeFieldName(SerializableString paramSerializableString)
    throws IOException;
  
  public abstract void writeFieldName(String paramString)
    throws IOException;
  
  public abstract void writeNull()
    throws IOException;
  
  public final void writeNullField(String paramString)
    throws IOException
  {
    writeFieldName(paramString);
    writeNull();
  }
  
  public abstract void writeNumber(double paramDouble)
    throws IOException;
  
  public abstract void writeNumber(float paramFloat)
    throws IOException;
  
  public abstract void writeNumber(int paramInt)
    throws IOException;
  
  public abstract void writeNumber(long paramLong)
    throws IOException;
  
  public abstract void writeNumber(String paramString)
    throws IOException;
  
  public abstract void writeNumber(BigDecimal paramBigDecimal)
    throws IOException;
  
  public abstract void writeNumber(BigInteger paramBigInteger)
    throws IOException;
  
  public void writeNumber(short paramShort)
    throws IOException
  {
    writeNumber(paramShort);
  }
  
  public final void writeNumberField(String paramString, double paramDouble)
    throws IOException
  {
    writeFieldName(paramString);
    writeNumber(paramDouble);
  }
  
  public final void writeNumberField(String paramString, float paramFloat)
    throws IOException
  {
    writeFieldName(paramString);
    writeNumber(paramFloat);
  }
  
  public final void writeNumberField(String paramString, int paramInt)
    throws IOException
  {
    writeFieldName(paramString);
    writeNumber(paramInt);
  }
  
  public final void writeNumberField(String paramString, long paramLong)
    throws IOException
  {
    writeFieldName(paramString);
    writeNumber(paramLong);
  }
  
  public final void writeNumberField(String paramString, BigDecimal paramBigDecimal)
    throws IOException
  {
    writeFieldName(paramString);
    writeNumber(paramBigDecimal);
  }
  
  public abstract void writeObject(Object paramObject)
    throws IOException;
  
  public final void writeObjectField(String paramString, Object paramObject)
    throws IOException
  {
    writeFieldName(paramString);
    writeObject(paramObject);
  }
  
  public final void writeObjectFieldStart(String paramString)
    throws IOException
  {
    writeFieldName(paramString);
    writeStartObject();
  }
  
  public void writeObjectId(Object paramObject)
    throws IOException
  {
    throw new JsonGenerationException("No native support for writing Object Ids");
  }
  
  public void writeObjectRef(Object paramObject)
    throws IOException
  {
    throw new JsonGenerationException("No native support for writing Object Ids");
  }
  
  public void writeOmittedField(String paramString)
    throws IOException
  {}
  
  public abstract void writeRaw(char paramChar)
    throws IOException;
  
  public void writeRaw(SerializableString paramSerializableString)
    throws IOException
  {
    writeRaw(paramSerializableString.getValue());
  }
  
  public abstract void writeRaw(String paramString)
    throws IOException;
  
  public abstract void writeRaw(String paramString, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void writeRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void writeRawUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public void writeRawValue(SerializableString paramSerializableString)
    throws IOException
  {
    writeRawValue(paramSerializableString.getValue());
  }
  
  public abstract void writeRawValue(String paramString)
    throws IOException;
  
  public abstract void writeRawValue(String paramString, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void writeRawValue(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void writeStartArray()
    throws IOException;
  
  public void writeStartArray(int paramInt)
    throws IOException
  {
    writeStartArray();
  }
  
  public abstract void writeStartObject()
    throws IOException;
  
  public abstract void writeString(SerializableString paramSerializableString)
    throws IOException;
  
  public abstract void writeString(String paramString)
    throws IOException;
  
  public abstract void writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException;
  
  public void writeStringField(String paramString1, String paramString2)
    throws IOException
  {
    writeFieldName(paramString1);
    writeString(paramString2);
  }
  
  public abstract void writeTree(TreeNode paramTreeNode)
    throws IOException;
  
  public void writeTypeId(Object paramObject)
    throws IOException
  {
    throw new JsonGenerationException("No native support for writing Type Ids");
  }
  
  public abstract void writeUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException;
  
  public static enum Feature
  {
    private final boolean _defaultState;
    private final int _mask;
    
    static
    {
      AUTO_CLOSE_JSON_CONTENT = new Feature("AUTO_CLOSE_JSON_CONTENT", 1, true);
      FLUSH_PASSED_TO_STREAM = new Feature("FLUSH_PASSED_TO_STREAM", 2, true);
      QUOTE_FIELD_NAMES = new Feature("QUOTE_FIELD_NAMES", 3, true);
      QUOTE_NON_NUMERIC_NUMBERS = new Feature("QUOTE_NON_NUMERIC_NUMBERS", 4, true);
      WRITE_NUMBERS_AS_STRINGS = new Feature("WRITE_NUMBERS_AS_STRINGS", 5, false);
      WRITE_BIGDECIMAL_AS_PLAIN = new Feature("WRITE_BIGDECIMAL_AS_PLAIN", 6, false);
      ESCAPE_NON_ASCII = new Feature("ESCAPE_NON_ASCII", 7, false);
      STRICT_DUPLICATE_DETECTION = new Feature("STRICT_DUPLICATE_DETECTION", 8, false);
      IGNORE_UNKNOWN = new Feature("IGNORE_UNKNOWN", 9, false);
      Feature[] arrayOfFeature = new Feature[10];
      arrayOfFeature[0] = AUTO_CLOSE_TARGET;
      arrayOfFeature[1] = AUTO_CLOSE_JSON_CONTENT;
      arrayOfFeature[2] = FLUSH_PASSED_TO_STREAM;
      arrayOfFeature[3] = QUOTE_FIELD_NAMES;
      arrayOfFeature[4] = QUOTE_NON_NUMERIC_NUMBERS;
      arrayOfFeature[5] = WRITE_NUMBERS_AS_STRINGS;
      arrayOfFeature[6] = WRITE_BIGDECIMAL_AS_PLAIN;
      arrayOfFeature[7] = ESCAPE_NON_ASCII;
      arrayOfFeature[8] = STRICT_DUPLICATE_DETECTION;
      arrayOfFeature[9] = IGNORE_UNKNOWN;
      $VALUES = arrayOfFeature;
    }
    
    private Feature(boolean paramBoolean)
    {
      this._defaultState = paramBoolean;
      this._mask = (1 << ordinal());
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
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */