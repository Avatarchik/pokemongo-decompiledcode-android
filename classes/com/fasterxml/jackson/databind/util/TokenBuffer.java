package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.TreeMap;

public class TokenBuffer
  extends JsonGenerator
{
  protected static final int DEFAULT_GENERATOR_FEATURES = ;
  protected int _appendAt;
  protected boolean _closed;
  protected Segment _first;
  protected boolean _forceBigDecimal;
  protected int _generatorFeatures;
  protected boolean _hasNativeId = false;
  protected boolean _hasNativeObjectIds;
  protected boolean _hasNativeTypeIds;
  protected Segment _last;
  protected boolean _mayHaveNativeIds;
  protected ObjectCodec _objectCodec;
  protected Object _objectId;
  protected Object _typeId;
  protected JsonWriteContext _writeContext;
  
  public TokenBuffer(JsonParser paramJsonParser)
  {
    this(paramJsonParser, null);
  }
  
  public TokenBuffer(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
  {
    this._objectCodec = paramJsonParser.getCodec();
    this._generatorFeatures = DEFAULT_GENERATOR_FEATURES;
    this._writeContext = JsonWriteContext.createRootContext(null);
    Segment localSegment = new Segment();
    this._last = localSegment;
    this._first = localSegment;
    this._appendAt = 0;
    this._hasNativeTypeIds = paramJsonParser.canReadTypeId();
    this._hasNativeObjectIds = paramJsonParser.canReadObjectId();
    this._mayHaveNativeIds = (this._hasNativeTypeIds | this._hasNativeObjectIds);
    if (paramDeserializationContext == null) {}
    for (;;)
    {
      this._forceBigDecimal = bool;
      return;
      bool = paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    }
  }
  
  @Deprecated
  public TokenBuffer(ObjectCodec paramObjectCodec)
  {
    this(paramObjectCodec, false);
  }
  
  public TokenBuffer(ObjectCodec paramObjectCodec, boolean paramBoolean)
  {
    this._objectCodec = paramObjectCodec;
    this._generatorFeatures = DEFAULT_GENERATOR_FEATURES;
    this._writeContext = JsonWriteContext.createRootContext(null);
    Segment localSegment = new Segment();
    this._last = localSegment;
    this._first = localSegment;
    this._appendAt = 0;
    this._hasNativeTypeIds = paramBoolean;
    this._hasNativeObjectIds = paramBoolean;
    this._mayHaveNativeIds = (this._hasNativeTypeIds | this._hasNativeObjectIds);
  }
  
  private final void _appendNativeIds(StringBuilder paramStringBuilder)
  {
    Object localObject1 = this._last.findObjectId(-1 + this._appendAt);
    if (localObject1 != null) {
      paramStringBuilder.append("[objectId=").append(String.valueOf(localObject1)).append(']');
    }
    Object localObject2 = this._last.findTypeId(-1 + this._appendAt);
    if (localObject2 != null) {
      paramStringBuilder.append("[typeId=").append(String.valueOf(localObject2)).append(']');
    }
  }
  
  private final void _checkNativeIds(JsonParser paramJsonParser)
    throws IOException
  {
    Object localObject1 = paramJsonParser.getTypeId();
    this._typeId = localObject1;
    if (localObject1 != null) {
      this._hasNativeId = true;
    }
    Object localObject2 = paramJsonParser.getObjectId();
    this._objectId = localObject2;
    if (localObject2 != null) {
      this._hasNativeId = true;
    }
  }
  
  protected final void _append(JsonToken paramJsonToken)
  {
    Segment localSegment;
    if (this._hasNativeId)
    {
      localSegment = this._last.append(this._appendAt, paramJsonToken, this._objectId, this._typeId);
      if (localSegment != null) {
        break label59;
      }
    }
    for (this._appendAt = (1 + this._appendAt);; this._appendAt = 1)
    {
      return;
      localSegment = this._last.append(this._appendAt, paramJsonToken);
      break;
      label59:
      this._last = localSegment;
    }
  }
  
  protected final void _append(JsonToken paramJsonToken, Object paramObject)
  {
    Segment localSegment;
    if (this._hasNativeId)
    {
      localSegment = this._last.append(this._appendAt, paramJsonToken, paramObject, this._objectId, this._typeId);
      if (localSegment != null) {
        break label61;
      }
    }
    for (this._appendAt = (1 + this._appendAt);; this._appendAt = 1)
    {
      return;
      localSegment = this._last.append(this._appendAt, paramJsonToken, paramObject);
      break;
      label61:
      this._last = localSegment;
    }
  }
  
  protected final void _appendRaw(int paramInt, Object paramObject)
  {
    Segment localSegment;
    if (this._hasNativeId)
    {
      localSegment = this._last.appendRaw(this._appendAt, paramInt, paramObject, this._objectId, this._typeId);
      if (localSegment != null) {
        break label61;
      }
    }
    for (this._appendAt = (1 + this._appendAt);; this._appendAt = 1)
    {
      return;
      localSegment = this._last.appendRaw(this._appendAt, paramInt, paramObject);
      break;
      label61:
      this._last = localSegment;
    }
  }
  
  protected void _reportUnsupportedOperation()
  {
    throw new UnsupportedOperationException("Called operation not supported for TokenBuffer");
  }
  
  public TokenBuffer append(TokenBuffer paramTokenBuffer)
    throws IOException
  {
    if (!this._hasNativeTypeIds) {
      this._hasNativeTypeIds = paramTokenBuffer.canWriteTypeId();
    }
    if (!this._hasNativeObjectIds) {
      this._hasNativeObjectIds = paramTokenBuffer.canWriteObjectId();
    }
    this._mayHaveNativeIds = (this._hasNativeTypeIds | this._hasNativeObjectIds);
    JsonParser localJsonParser = paramTokenBuffer.asParser();
    while (localJsonParser.nextToken() != null) {
      copyCurrentStructure(localJsonParser);
    }
    return this;
  }
  
  public JsonParser asParser()
  {
    return asParser(this._objectCodec);
  }
  
  public JsonParser asParser(JsonParser paramJsonParser)
  {
    Parser localParser = new Parser(this._first, paramJsonParser.getCodec(), this._hasNativeTypeIds, this._hasNativeObjectIds);
    localParser.setLocation(paramJsonParser.getTokenLocation());
    return localParser;
  }
  
  public JsonParser asParser(ObjectCodec paramObjectCodec)
  {
    return new Parser(this._first, paramObjectCodec, this._hasNativeTypeIds, this._hasNativeObjectIds);
  }
  
  public boolean canWriteBinaryNatively()
  {
    return true;
  }
  
  public boolean canWriteObjectId()
  {
    return this._hasNativeObjectIds;
  }
  
  public boolean canWriteTypeId()
  {
    return this._hasNativeTypeIds;
  }
  
  public void close()
    throws IOException
  {
    this._closed = true;
  }
  
  public void copyCurrentEvent(JsonParser paramJsonParser)
    throws IOException
  {
    if (this._mayHaveNativeIds) {
      _checkNativeIds(paramJsonParser);
    }
    switch (1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[paramJsonParser.getCurrentToken().ordinal()])
    {
    default: 
      throw new RuntimeException("Internal error: should never end up through this code path");
    case 1: 
      writeStartObject();
    }
    for (;;)
    {
      return;
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
        switch (paramJsonParser.getNumberType())
        {
        default: 
          writeNumber(paramJsonParser.getLongValue());
          break;
        case ???: 
          writeNumber(paramJsonParser.getIntValue());
          break;
        case ???: 
          writeNumber(paramJsonParser.getBigIntegerValue());
          continue;
          if (this._forceBigDecimal) {
            writeNumber(paramJsonParser.getDecimalValue());
          } else {
            switch (paramJsonParser.getNumberType())
            {
            default: 
              writeNumber(paramJsonParser.getDoubleValue());
              break;
            case ???: 
              writeNumber(paramJsonParser.getDecimalValue());
              break;
            case ???: 
              writeNumber(paramJsonParser.getFloatValue());
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
          break;
        }
      }
    }
  }
  
  public void copyCurrentStructure(JsonParser paramJsonParser)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.FIELD_NAME)
    {
      if (this._mayHaveNativeIds) {
        _checkNativeIds(paramJsonParser);
      }
      writeFieldName(paramJsonParser.getCurrentName());
      localJsonToken = paramJsonParser.nextToken();
    }
    if (this._mayHaveNativeIds) {
      _checkNativeIds(paramJsonParser);
    }
    switch (1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[localJsonToken.ordinal()])
    {
    case 2: 
    default: 
      copyCurrentEvent(paramJsonParser);
    }
    for (;;)
    {
      return;
      writeStartArray();
      while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        copyCurrentStructure(paramJsonParser);
      }
      writeEndArray();
      continue;
      writeStartObject();
      while (paramJsonParser.nextToken() != JsonToken.END_OBJECT) {
        copyCurrentStructure(paramJsonParser);
      }
      writeEndObject();
    }
  }
  
  public TokenBuffer deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.getCurrentTokenId() != JsonToken.FIELD_NAME.id()) {
      copyCurrentStructure(paramJsonParser);
    }
    for (;;)
    {
      return this;
      writeStartObject();
      JsonToken localJsonToken;
      do
      {
        copyCurrentStructure(paramJsonParser);
        localJsonToken = paramJsonParser.nextToken();
      } while (localJsonToken == JsonToken.FIELD_NAME);
      if (localJsonToken != JsonToken.END_OBJECT) {
        throw paramDeserializationContext.mappingException("Expected END_OBJECT after copying contents of a JsonParser into TokenBuffer, got " + localJsonToken);
      }
      writeEndObject();
    }
  }
  
  public JsonGenerator disable(JsonGenerator.Feature paramFeature)
  {
    this._generatorFeatures &= (0xFFFFFFFF ^ paramFeature.getMask());
    return this;
  }
  
  public JsonGenerator enable(JsonGenerator.Feature paramFeature)
  {
    this._generatorFeatures |= paramFeature.getMask();
    return this;
  }
  
  public JsonToken firstToken()
  {
    if (this._first != null) {}
    for (JsonToken localJsonToken = this._first.type(0);; localJsonToken = null) {
      return localJsonToken;
    }
  }
  
  public void flush()
    throws IOException
  {}
  
  public TokenBuffer forceUseOfBigDecimal(boolean paramBoolean)
  {
    this._forceBigDecimal = paramBoolean;
    return this;
  }
  
  public ObjectCodec getCodec()
  {
    return this._objectCodec;
  }
  
  public int getFeatureMask()
  {
    return this._generatorFeatures;
  }
  
  public final JsonWriteContext getOutputContext()
  {
    return this._writeContext;
  }
  
  public boolean isClosed()
  {
    return this._closed;
  }
  
  public boolean isEnabled(JsonGenerator.Feature paramFeature)
  {
    if ((this._generatorFeatures & paramFeature.getMask()) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void serialize(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    Segment localSegment = this._first;
    int i = -1;
    boolean bool = this._mayHaveNativeIds;
    int j;
    if ((bool) && (localSegment.hasIds())) {
      j = 1;
    }
    for (;;)
    {
      i++;
      if (i >= 16)
      {
        i = 0;
        localSegment = localSegment.next();
        if (localSegment == null)
        {
          return;
          j = 0;
          continue;
        }
        if ((!bool) || (!localSegment.hasIds())) {
          break label206;
        }
      }
      label206:
      for (j = 1;; j = 0)
      {
        JsonToken localJsonToken = localSegment.type(i);
        if (localJsonToken == null) {
          break;
        }
        if (j != 0)
        {
          Object localObject6 = localSegment.findObjectId(i);
          if (localObject6 != null) {
            paramJsonGenerator.writeObjectId(localObject6);
          }
          Object localObject7 = localSegment.findTypeId(i);
          if (localObject7 != null) {
            paramJsonGenerator.writeTypeId(localObject7);
          }
        }
        switch (1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[localJsonToken.ordinal()])
        {
        default: 
          throw new RuntimeException("Internal error: should never end up through this code path");
        }
      }
      paramJsonGenerator.writeStartObject();
      continue;
      paramJsonGenerator.writeEndObject();
      continue;
      paramJsonGenerator.writeStartArray();
      continue;
      paramJsonGenerator.writeEndArray();
      continue;
      Object localObject5 = localSegment.get(i);
      if ((localObject5 instanceof SerializableString))
      {
        paramJsonGenerator.writeFieldName((SerializableString)localObject5);
      }
      else
      {
        paramJsonGenerator.writeFieldName((String)localObject5);
        continue;
        Object localObject4 = localSegment.get(i);
        if ((localObject4 instanceof SerializableString))
        {
          paramJsonGenerator.writeString((SerializableString)localObject4);
        }
        else
        {
          paramJsonGenerator.writeString((String)localObject4);
          continue;
          Object localObject3 = localSegment.get(i);
          if ((localObject3 instanceof Integer))
          {
            paramJsonGenerator.writeNumber(((Integer)localObject3).intValue());
          }
          else if ((localObject3 instanceof BigInteger))
          {
            paramJsonGenerator.writeNumber((BigInteger)localObject3);
          }
          else if ((localObject3 instanceof Long))
          {
            paramJsonGenerator.writeNumber(((Long)localObject3).longValue());
          }
          else if ((localObject3 instanceof Short))
          {
            paramJsonGenerator.writeNumber(((Short)localObject3).shortValue());
          }
          else
          {
            paramJsonGenerator.writeNumber(((Number)localObject3).intValue());
            continue;
            Object localObject2 = localSegment.get(i);
            if ((localObject2 instanceof Double))
            {
              paramJsonGenerator.writeNumber(((Double)localObject2).doubleValue());
            }
            else if ((localObject2 instanceof BigDecimal))
            {
              paramJsonGenerator.writeNumber((BigDecimal)localObject2);
            }
            else if ((localObject2 instanceof Float))
            {
              paramJsonGenerator.writeNumber(((Float)localObject2).floatValue());
            }
            else if (localObject2 == null)
            {
              paramJsonGenerator.writeNull();
            }
            else if ((localObject2 instanceof String))
            {
              paramJsonGenerator.writeNumber((String)localObject2);
            }
            else
            {
              throw new JsonGenerationException("Unrecognized value type for VALUE_NUMBER_FLOAT: " + localObject2.getClass().getName() + ", can not serialize");
              paramJsonGenerator.writeBoolean(true);
              continue;
              paramJsonGenerator.writeBoolean(false);
              continue;
              paramJsonGenerator.writeNull();
              continue;
              Object localObject1 = localSegment.get(i);
              if ((localObject1 instanceof RawValue)) {
                ((RawValue)localObject1).serialize(paramJsonGenerator);
              } else {
                paramJsonGenerator.writeObject(localObject1);
              }
            }
          }
        }
      }
    }
  }
  
  public JsonGenerator setCodec(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
    return this;
  }
  
  public JsonGenerator setFeatureMask(int paramInt)
  {
    this._generatorFeatures = paramInt;
    return this;
  }
  
  /* Error */
  public String toString()
  {
    // Byte code:
    //   0: new 119	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 368	java/lang/StringBuilder:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: ldc_w 508
    //   12: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload_0
    //   17: invokevirtual 194	com/fasterxml/jackson/databind/util/TokenBuffer:asParser	()Lcom/fasterxml/jackson/core/JsonParser;
    //   20: astore_3
    //   21: iconst_0
    //   22: istore 4
    //   24: aload_0
    //   25: getfield 83	com/fasterxml/jackson/databind/util/TokenBuffer:_hasNativeTypeIds	Z
    //   28: ifne +10 -> 38
    //   31: aload_0
    //   32: getfield 88	com/fasterxml/jackson/databind/util/TokenBuffer:_hasNativeObjectIds	Z
    //   35: ifeq +58 -> 93
    //   38: iconst_1
    //   39: istore 5
    //   41: aload_3
    //   42: invokevirtual 198	com/fasterxml/jackson/core/JsonParser:nextToken	()Lcom/fasterxml/jackson/core/JsonToken;
    //   45: astore 7
    //   47: aload 7
    //   49: ifnonnull +50 -> 99
    //   52: iload 4
    //   54: bipush 100
    //   56: if_icmplt +25 -> 81
    //   59: aload_1
    //   60: ldc_w 510
    //   63: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: iload 4
    //   68: bipush 100
    //   70: isub
    //   71: invokevirtual 513	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   74: ldc_w 515
    //   77: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: pop
    //   81: aload_1
    //   82: bipush 93
    //   84: invokevirtual 132	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: aload_1
    //   89: invokevirtual 376	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: areturn
    //   93: iconst_0
    //   94: istore 5
    //   96: goto -55 -> 41
    //   99: iload 5
    //   101: ifeq +8 -> 109
    //   104: aload_0
    //   105: aload_1
    //   106: invokespecial 517	com/fasterxml/jackson/databind/util/TokenBuffer:_appendNativeIds	(Ljava/lang/StringBuilder;)V
    //   109: iload 4
    //   111: bipush 100
    //   113: if_icmpge +57 -> 170
    //   116: iload 4
    //   118: ifle +11 -> 129
    //   121: aload_1
    //   122: ldc_w 519
    //   125: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload_1
    //   130: aload 7
    //   132: invokevirtual 520	com/fasterxml/jackson/core/JsonToken:toString	()Ljava/lang/String;
    //   135: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: aload 7
    //   141: getstatic 351	com/fasterxml/jackson/core/JsonToken:FIELD_NAME	Lcom/fasterxml/jackson/core/JsonToken;
    //   144: if_acmpne +26 -> 170
    //   147: aload_1
    //   148: bipush 40
    //   150: invokevirtual 132	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload_1
    //   155: aload_3
    //   156: invokevirtual 256	com/fasterxml/jackson/core/JsonParser:getCurrentName	()Ljava/lang/String;
    //   159: invokevirtual 123	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: pop
    //   163: aload_1
    //   164: bipush 41
    //   166: invokevirtual 132	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   169: pop
    //   170: iinc 4 1
    //   173: goto -132 -> 41
    //   176: astore 6
    //   178: new 522	java/lang/IllegalStateException
    //   181: dup
    //   182: aload 6
    //   184: invokespecial 525	java/lang/IllegalStateException:<init>	(Ljava/lang/Throwable;)V
    //   187: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	this	TokenBuffer
    //   7	157	1	localStringBuilder	StringBuilder
    //   20	136	3	localJsonParser	JsonParser
    //   22	149	4	i	int
    //   39	61	5	j	int
    //   176	7	6	localIOException	IOException
    //   45	95	7	localJsonToken	JsonToken
    // Exception table:
    //   from	to	target	type
    //   41	47	176	java/io/IOException
    //   104	170	176	java/io/IOException
  }
  
  public JsonGenerator useDefaultPrettyPrinter()
  {
    return this;
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    writeObject(arrayOfByte);
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {}
    for (JsonToken localJsonToken = JsonToken.VALUE_TRUE;; localJsonToken = JsonToken.VALUE_FALSE)
    {
      _append(localJsonToken);
      return;
    }
  }
  
  public final void writeEndArray()
    throws IOException
  {
    _append(JsonToken.END_ARRAY);
    JsonWriteContext localJsonWriteContext = this._writeContext.getParent();
    if (localJsonWriteContext != null) {
      this._writeContext = localJsonWriteContext;
    }
  }
  
  public final void writeEndObject()
    throws IOException
  {
    _append(JsonToken.END_OBJECT);
    JsonWriteContext localJsonWriteContext = this._writeContext.getParent();
    if (localJsonWriteContext != null) {
      this._writeContext = localJsonWriteContext;
    }
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    _append(JsonToken.FIELD_NAME, paramSerializableString);
    this._writeContext.writeFieldName(paramSerializableString.getValue());
  }
  
  public final void writeFieldName(String paramString)
    throws IOException
  {
    _append(JsonToken.FIELD_NAME, paramString);
    this._writeContext.writeFieldName(paramString);
  }
  
  public void writeNull()
    throws IOException
  {
    _append(JsonToken.VALUE_NULL);
  }
  
  public void writeNumber(double paramDouble)
    throws IOException
  {
    _append(JsonToken.VALUE_NUMBER_FLOAT, Double.valueOf(paramDouble));
  }
  
  public void writeNumber(float paramFloat)
    throws IOException
  {
    _append(JsonToken.VALUE_NUMBER_FLOAT, Float.valueOf(paramFloat));
  }
  
  public void writeNumber(int paramInt)
    throws IOException
  {
    _append(JsonToken.VALUE_NUMBER_INT, Integer.valueOf(paramInt));
  }
  
  public void writeNumber(long paramLong)
    throws IOException
  {
    _append(JsonToken.VALUE_NUMBER_INT, Long.valueOf(paramLong));
  }
  
  public void writeNumber(String paramString)
    throws IOException
  {
    _append(JsonToken.VALUE_NUMBER_FLOAT, paramString);
  }
  
  public void writeNumber(BigDecimal paramBigDecimal)
    throws IOException
  {
    if (paramBigDecimal == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      _append(JsonToken.VALUE_NUMBER_FLOAT, paramBigDecimal);
    }
  }
  
  public void writeNumber(BigInteger paramBigInteger)
    throws IOException
  {
    if (paramBigInteger == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      _append(JsonToken.VALUE_NUMBER_INT, paramBigInteger);
    }
  }
  
  public void writeNumber(short paramShort)
    throws IOException
  {
    _append(JsonToken.VALUE_NUMBER_INT, Short.valueOf(paramShort));
  }
  
  public void writeObject(Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      if ((paramObject.getClass() == byte[].class) || ((paramObject instanceof RawValue))) {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, paramObject);
      } else if (this._objectCodec == null) {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, paramObject);
      } else {
        this._objectCodec.writeValue(this, paramObject);
      }
    }
  }
  
  public void writeObjectId(Object paramObject)
  {
    this._objectId = paramObject;
    this._hasNativeId = true;
  }
  
  public void writeRaw(char paramChar)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(SerializableString paramSerializableString)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(String paramString)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
  
  public void writeRawValue(String paramString)
    throws IOException
  {
    _append(JsonToken.VALUE_EMBEDDED_OBJECT, new RawValue(paramString));
  }
  
  public void writeRawValue(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if ((paramInt1 > 0) || (paramInt2 != paramString.length())) {
      paramString = paramString.substring(paramInt1, paramInt1 + paramInt2);
    }
    _append(JsonToken.VALUE_EMBEDDED_OBJECT, new RawValue(paramString));
  }
  
  public void writeRawValue(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    _append(JsonToken.VALUE_EMBEDDED_OBJECT, new String(paramArrayOfChar, paramInt1, paramInt2));
  }
  
  public final void writeStartArray()
    throws IOException
  {
    _append(JsonToken.START_ARRAY);
    this._writeContext = this._writeContext.createChildArrayContext();
  }
  
  public final void writeStartObject()
    throws IOException
  {
    _append(JsonToken.START_OBJECT);
    this._writeContext = this._writeContext.createChildObjectContext();
  }
  
  public void writeString(SerializableString paramSerializableString)
    throws IOException
  {
    if (paramSerializableString == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      _append(JsonToken.VALUE_STRING, paramSerializableString);
    }
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    if (paramString == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      _append(JsonToken.VALUE_STRING, paramString);
    }
  }
  
  public void writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    writeString(new String(paramArrayOfChar, paramInt1, paramInt2));
  }
  
  public void writeTree(TreeNode paramTreeNode)
    throws IOException
  {
    if (paramTreeNode == null) {
      writeNull();
    }
    for (;;)
    {
      return;
      if (this._objectCodec == null) {
        _append(JsonToken.VALUE_EMBEDDED_OBJECT, paramTreeNode);
      } else {
        this._objectCodec.writeTree(this, paramTreeNode);
      }
    }
  }
  
  public void writeTypeId(Object paramObject)
  {
    this._typeId = paramObject;
    this._hasNativeId = true;
  }
  
  public void writeUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    _reportUnsupportedOperation();
  }
  
  protected static final class Segment
  {
    public static final int TOKENS_PER_SEGMENT = 16;
    private static final JsonToken[] TOKEN_TYPES_BY_INDEX = new JsonToken[16];
    protected TreeMap<Integer, Object> _nativeIds;
    protected Segment _next;
    protected long _tokenTypes;
    protected final Object[] _tokens = new Object[16];
    
    static
    {
      JsonToken[] arrayOfJsonToken = JsonToken.values();
      System.arraycopy(arrayOfJsonToken, 1, TOKEN_TYPES_BY_INDEX, 1, Math.min(15, -1 + arrayOfJsonToken.length));
    }
    
    private final int _objectIdIndex(int paramInt)
    {
      return 1 + (paramInt + paramInt);
    }
    
    private final int _typeIdIndex(int paramInt)
    {
      return paramInt + paramInt;
    }
    
    private final void assignNativeIds(int paramInt, Object paramObject1, Object paramObject2)
    {
      if (this._nativeIds == null) {
        this._nativeIds = new TreeMap();
      }
      if (paramObject1 != null) {
        this._nativeIds.put(Integer.valueOf(_objectIdIndex(paramInt)), paramObject1);
      }
      if (paramObject2 != null) {
        this._nativeIds.put(Integer.valueOf(_typeIdIndex(paramInt)), paramObject2);
      }
    }
    
    private void set(int paramInt1, int paramInt2, Object paramObject)
    {
      this._tokens[paramInt1] = paramObject;
      long l = paramInt2;
      if (paramInt1 > 0) {
        l <<= paramInt1 << 2;
      }
      this._tokenTypes = (l | this._tokenTypes);
    }
    
    private void set(int paramInt1, int paramInt2, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      this._tokens[paramInt1] = paramObject1;
      long l = paramInt2;
      if (paramInt1 > 0) {
        l <<= paramInt1 << 2;
      }
      this._tokenTypes = (l | this._tokenTypes);
      assignNativeIds(paramInt1, paramObject2, paramObject3);
    }
    
    private void set(int paramInt, JsonToken paramJsonToken)
    {
      long l = paramJsonToken.ordinal();
      if (paramInt > 0) {
        l <<= paramInt << 2;
      }
      this._tokenTypes = (l | this._tokenTypes);
    }
    
    private void set(int paramInt, JsonToken paramJsonToken, Object paramObject)
    {
      this._tokens[paramInt] = paramObject;
      long l = paramJsonToken.ordinal();
      if (paramInt > 0) {
        l <<= paramInt << 2;
      }
      this._tokenTypes = (l | this._tokenTypes);
    }
    
    private void set(int paramInt, JsonToken paramJsonToken, Object paramObject1, Object paramObject2)
    {
      long l = paramJsonToken.ordinal();
      if (paramInt > 0) {
        l <<= paramInt << 2;
      }
      this._tokenTypes = (l | this._tokenTypes);
      assignNativeIds(paramInt, paramObject1, paramObject2);
    }
    
    private void set(int paramInt, JsonToken paramJsonToken, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      this._tokens[paramInt] = paramObject1;
      long l = paramJsonToken.ordinal();
      if (paramInt > 0) {
        l <<= paramInt << 2;
      }
      this._tokenTypes = (l | this._tokenTypes);
      assignNativeIds(paramInt, paramObject2, paramObject3);
    }
    
    public Segment append(int paramInt, JsonToken paramJsonToken)
    {
      if (paramInt < 16) {
        set(paramInt, paramJsonToken);
      }
      for (Segment localSegment = null;; localSegment = this._next)
      {
        return localSegment;
        this._next = new Segment();
        this._next.set(0, paramJsonToken);
      }
    }
    
    public Segment append(int paramInt, JsonToken paramJsonToken, Object paramObject)
    {
      if (paramInt < 16) {
        set(paramInt, paramJsonToken, paramObject);
      }
      for (Segment localSegment = null;; localSegment = this._next)
      {
        return localSegment;
        this._next = new Segment();
        this._next.set(0, paramJsonToken, paramObject);
      }
    }
    
    public Segment append(int paramInt, JsonToken paramJsonToken, Object paramObject1, Object paramObject2)
    {
      if (paramInt < 16) {
        set(paramInt, paramJsonToken, paramObject1, paramObject2);
      }
      for (Segment localSegment = null;; localSegment = this._next)
      {
        return localSegment;
        this._next = new Segment();
        this._next.set(0, paramJsonToken, paramObject1, paramObject2);
      }
    }
    
    public Segment append(int paramInt, JsonToken paramJsonToken, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      if (paramInt < 16) {
        set(paramInt, paramJsonToken, paramObject1, paramObject2, paramObject3);
      }
      for (Segment localSegment = null;; localSegment = this._next)
      {
        return localSegment;
        this._next = new Segment();
        this._next.set(0, paramJsonToken, paramObject1, paramObject2, paramObject3);
      }
    }
    
    public Segment appendRaw(int paramInt1, int paramInt2, Object paramObject)
    {
      if (paramInt1 < 16) {
        set(paramInt1, paramInt2, paramObject);
      }
      for (Segment localSegment = null;; localSegment = this._next)
      {
        return localSegment;
        this._next = new Segment();
        this._next.set(0, paramInt2, paramObject);
      }
    }
    
    public Segment appendRaw(int paramInt1, int paramInt2, Object paramObject1, Object paramObject2, Object paramObject3)
    {
      if (paramInt1 < 16) {
        set(paramInt1, paramInt2, paramObject1, paramObject2, paramObject3);
      }
      for (Segment localSegment = null;; localSegment = this._next)
      {
        return localSegment;
        this._next = new Segment();
        this._next.set(0, paramInt2, paramObject1, paramObject2, paramObject3);
      }
    }
    
    public Object findObjectId(int paramInt)
    {
      if (this._nativeIds == null) {}
      for (Object localObject = null;; localObject = this._nativeIds.get(Integer.valueOf(_objectIdIndex(paramInt)))) {
        return localObject;
      }
    }
    
    public Object findTypeId(int paramInt)
    {
      if (this._nativeIds == null) {}
      for (Object localObject = null;; localObject = this._nativeIds.get(Integer.valueOf(_typeIdIndex(paramInt)))) {
        return localObject;
      }
    }
    
    public Object get(int paramInt)
    {
      return this._tokens[paramInt];
    }
    
    public boolean hasIds()
    {
      if (this._nativeIds != null) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Segment next()
    {
      return this._next;
    }
    
    public int rawType(int paramInt)
    {
      long l = this._tokenTypes;
      if (paramInt > 0) {
        l >>= paramInt << 2;
      }
      return 0xF & (int)l;
    }
    
    public JsonToken type(int paramInt)
    {
      long l = this._tokenTypes;
      if (paramInt > 0) {
        l >>= paramInt << 2;
      }
      int i = 0xF & (int)l;
      return TOKEN_TYPES_BY_INDEX[i];
    }
  }
  
  protected static final class Parser
    extends ParserMinimalBase
  {
    protected transient ByteArrayBuilder _byteBuilder;
    protected boolean _closed;
    protected ObjectCodec _codec;
    protected final boolean _hasNativeIds;
    protected final boolean _hasNativeObjectIds;
    protected final boolean _hasNativeTypeIds;
    protected JsonLocation _location = null;
    protected JsonReadContext _parsingContext;
    protected TokenBuffer.Segment _segment;
    protected int _segmentPtr;
    
    public Parser(TokenBuffer.Segment paramSegment, ObjectCodec paramObjectCodec, boolean paramBoolean1, boolean paramBoolean2)
    {
      super();
      this._segment = paramSegment;
      this._segmentPtr = -1;
      this._codec = paramObjectCodec;
      this._parsingContext = JsonReadContext.createRootContext(null);
      this._hasNativeTypeIds = paramBoolean1;
      this._hasNativeObjectIds = paramBoolean2;
      this._hasNativeIds = (paramBoolean1 | paramBoolean2);
    }
    
    protected final void _checkIsNumber()
      throws JsonParseException
    {
      if ((this._currToken == null) || (!this._currToken.isNumeric())) {
        throw _constructError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
      }
    }
    
    protected final Object _currentObject()
    {
      return this._segment.get(this._segmentPtr);
    }
    
    protected void _handleEOF()
      throws JsonParseException
    {
      _throwInternal();
    }
    
    public boolean canReadObjectId()
    {
      return this._hasNativeObjectIds;
    }
    
    public boolean canReadTypeId()
    {
      return this._hasNativeTypeIds;
    }
    
    public void close()
      throws IOException
    {
      if (!this._closed) {
        this._closed = true;
      }
    }
    
    public BigInteger getBigIntegerValue()
      throws IOException
    {
      Number localNumber = getNumberValue();
      BigInteger localBigInteger;
      if ((localNumber instanceof BigInteger)) {
        localBigInteger = (BigInteger)localNumber;
      }
      for (;;)
      {
        return localBigInteger;
        if (getNumberType() == JsonParser.NumberType.BIG_DECIMAL) {
          localBigInteger = ((BigDecimal)localNumber).toBigInteger();
        } else {
          localBigInteger = BigInteger.valueOf(localNumber.longValue());
        }
      }
    }
    
    public byte[] getBinaryValue(Base64Variant paramBase64Variant)
      throws IOException, JsonParseException
    {
      Object localObject;
      if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT)
      {
        localObject = _currentObject();
        if (!(localObject instanceof byte[])) {}
      }
      String str;
      for (byte[] arrayOfByte = (byte[])localObject;; arrayOfByte = null)
      {
        return arrayOfByte;
        if (this._currToken != JsonToken.VALUE_STRING) {
          throw _constructError("Current token (" + this._currToken + ") not VALUE_STRING (or VALUE_EMBEDDED_OBJECT with byte[]), can not access as binary");
        }
        str = getText();
        if (str != null) {
          break;
        }
      }
      ByteArrayBuilder localByteArrayBuilder = this._byteBuilder;
      if (localByteArrayBuilder == null)
      {
        localByteArrayBuilder = new ByteArrayBuilder(100);
        this._byteBuilder = localByteArrayBuilder;
      }
      for (;;)
      {
        _decodeBase64(str, localByteArrayBuilder, paramBase64Variant);
        arrayOfByte = localByteArrayBuilder.toByteArray();
        break;
        this._byteBuilder.reset();
      }
    }
    
    public ObjectCodec getCodec()
    {
      return this._codec;
    }
    
    public JsonLocation getCurrentLocation()
    {
      if (this._location == null) {}
      for (JsonLocation localJsonLocation = JsonLocation.NA;; localJsonLocation = this._location) {
        return localJsonLocation;
      }
    }
    
    public String getCurrentName()
    {
      if ((this._currToken == JsonToken.START_OBJECT) || (this._currToken == JsonToken.START_ARRAY)) {}
      for (String str = this._parsingContext.getParent().getCurrentName();; str = this._parsingContext.getCurrentName()) {
        return str;
      }
    }
    
    public BigDecimal getDecimalValue()
      throws IOException
    {
      Number localNumber = getNumberValue();
      BigDecimal localBigDecimal;
      if ((localNumber instanceof BigDecimal)) {
        localBigDecimal = (BigDecimal)localNumber;
      }
      for (;;)
      {
        return localBigDecimal;
        switch (TokenBuffer.1.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[getNumberType().ordinal()])
        {
        case 3: 
        case 4: 
        default: 
          localBigDecimal = BigDecimal.valueOf(localNumber.doubleValue());
          break;
        case 1: 
        case 5: 
          localBigDecimal = BigDecimal.valueOf(localNumber.longValue());
          break;
        case 2: 
          localBigDecimal = new BigDecimal((BigInteger)localNumber);
        }
      }
    }
    
    public double getDoubleValue()
      throws IOException
    {
      return getNumberValue().doubleValue();
    }
    
    public Object getEmbeddedObject()
    {
      if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {}
      for (Object localObject = _currentObject();; localObject = null) {
        return localObject;
      }
    }
    
    public float getFloatValue()
      throws IOException
    {
      return getNumberValue().floatValue();
    }
    
    public int getIntValue()
      throws IOException
    {
      if (this._currToken == JsonToken.VALUE_NUMBER_INT) {}
      for (int i = ((Number)_currentObject()).intValue();; i = getNumberValue().intValue()) {
        return i;
      }
    }
    
    public long getLongValue()
      throws IOException
    {
      return getNumberValue().longValue();
    }
    
    public JsonParser.NumberType getNumberType()
      throws IOException
    {
      Number localNumber = getNumberValue();
      JsonParser.NumberType localNumberType;
      if ((localNumber instanceof Integer)) {
        localNumberType = JsonParser.NumberType.INT;
      }
      for (;;)
      {
        return localNumberType;
        if ((localNumber instanceof Long)) {
          localNumberType = JsonParser.NumberType.LONG;
        } else if ((localNumber instanceof Double)) {
          localNumberType = JsonParser.NumberType.DOUBLE;
        } else if ((localNumber instanceof BigDecimal)) {
          localNumberType = JsonParser.NumberType.BIG_DECIMAL;
        } else if ((localNumber instanceof BigInteger)) {
          localNumberType = JsonParser.NumberType.BIG_INTEGER;
        } else if ((localNumber instanceof Float)) {
          localNumberType = JsonParser.NumberType.FLOAT;
        } else if ((localNumber instanceof Short)) {
          localNumberType = JsonParser.NumberType.INT;
        } else {
          localNumberType = null;
        }
      }
    }
    
    public final Number getNumberValue()
      throws IOException
    {
      _checkIsNumber();
      Object localObject1 = _currentObject();
      Object localObject2;
      if ((localObject1 instanceof Number)) {
        localObject2 = (Number)localObject1;
      }
      for (;;)
      {
        return (Number)localObject2;
        if ((localObject1 instanceof String))
        {
          String str = (String)localObject1;
          if (str.indexOf('.') >= 0) {
            localObject2 = Double.valueOf(Double.parseDouble(str));
          } else {
            localObject2 = Long.valueOf(Long.parseLong(str));
          }
        }
        else
        {
          if (localObject1 != null) {
            break;
          }
          localObject2 = null;
        }
      }
      throw new IllegalStateException("Internal error: entry should be a Number, but is of type " + localObject1.getClass().getName());
    }
    
    public Object getObjectId()
    {
      return this._segment.findObjectId(this._segmentPtr);
    }
    
    public JsonStreamContext getParsingContext()
    {
      return this._parsingContext;
    }
    
    public String getText()
    {
      String str = null;
      Object localObject1;
      if ((this._currToken == JsonToken.VALUE_STRING) || (this._currToken == JsonToken.FIELD_NAME))
      {
        localObject1 = _currentObject();
        if ((localObject1 instanceof String)) {
          str = (String)localObject1;
        }
      }
      for (;;)
      {
        return str;
        if (localObject1 != null)
        {
          str = localObject1.toString();
          continue;
          if (this._currToken != null) {
            switch (TokenBuffer.1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[this._currToken.ordinal()])
            {
            default: 
              str = this._currToken.asString();
              break;
            case 7: 
            case 8: 
              Object localObject2 = _currentObject();
              if (localObject2 != null) {
                str = localObject2.toString();
              }
              break;
            }
          }
        }
      }
    }
    
    public char[] getTextCharacters()
    {
      String str = getText();
      if (str == null) {}
      for (char[] arrayOfChar = null;; arrayOfChar = str.toCharArray()) {
        return arrayOfChar;
      }
    }
    
    public int getTextLength()
    {
      String str = getText();
      if (str == null) {}
      for (int i = 0;; i = str.length()) {
        return i;
      }
    }
    
    public int getTextOffset()
    {
      return 0;
    }
    
    public JsonLocation getTokenLocation()
    {
      return getCurrentLocation();
    }
    
    public Object getTypeId()
    {
      return this._segment.findTypeId(this._segmentPtr);
    }
    
    public boolean hasTextCharacters()
    {
      return false;
    }
    
    public boolean isClosed()
    {
      return this._closed;
    }
    
    public String nextFieldName()
      throws IOException
    {
      Object localObject1 = null;
      if ((this._closed) || (this._segment == null)) {}
      for (;;)
      {
        return (String)localObject1;
        int i = 1 + this._segmentPtr;
        if ((i < 16) && (this._segment.type(i) == JsonToken.FIELD_NAME))
        {
          this._segmentPtr = i;
          Object localObject2 = this._segment.get(i);
          if ((localObject2 instanceof String)) {}
          for (String str = (String)localObject2;; str = localObject2.toString())
          {
            this._parsingContext.setCurrentName(str);
            localObject1 = str;
            break;
          }
        }
        if (nextToken() == JsonToken.FIELD_NAME) {
          localObject1 = getCurrentName();
        }
      }
    }
    
    public JsonToken nextToken()
      throws IOException
    {
      JsonToken localJsonToken = null;
      if ((this._closed) || (this._segment == null)) {}
      do
      {
        return localJsonToken;
        int i = 1 + this._segmentPtr;
        this._segmentPtr = i;
        if (i < 16) {
          break;
        }
        this._segmentPtr = 0;
        this._segment = this._segment.next();
      } while (this._segment == null);
      this._currToken = this._segment.type(this._segmentPtr);
      Object localObject;
      String str;
      if (this._currToken == JsonToken.FIELD_NAME)
      {
        localObject = _currentObject();
        if ((localObject instanceof String))
        {
          str = (String)localObject;
          label102:
          this._parsingContext.setCurrentName(str);
        }
      }
      for (;;)
      {
        localJsonToken = this._currToken;
        break;
        str = localObject.toString();
        break label102;
        if (this._currToken == JsonToken.START_OBJECT)
        {
          this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
        }
        else if (this._currToken == JsonToken.START_ARRAY)
        {
          this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
        }
        else if ((this._currToken == JsonToken.END_OBJECT) || (this._currToken == JsonToken.END_ARRAY))
        {
          this._parsingContext = this._parsingContext.getParent();
          if (this._parsingContext == null) {
            this._parsingContext = JsonReadContext.createRootContext(null);
          }
        }
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
        throw new RuntimeException(localIOException);
      }
    }
    
    public JsonToken peekNextToken()
      throws IOException
    {
      JsonToken localJsonToken = null;
      if (this._closed) {}
      label50:
      label56:
      for (;;)
      {
        return localJsonToken;
        TokenBuffer.Segment localSegment = this._segment;
        int i = 1 + this._segmentPtr;
        if (i >= 16)
        {
          i = 0;
          if (localSegment != null) {
            break label50;
          }
        }
        for (localSegment = null;; localSegment = localSegment.next())
        {
          if (localSegment == null) {
            break label56;
          }
          localJsonToken = localSegment.type(i);
          break;
        }
      }
    }
    
    public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
      throws IOException
    {
      int i = 0;
      byte[] arrayOfByte = getBinaryValue(paramBase64Variant);
      if (arrayOfByte != null)
      {
        paramOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
        i = arrayOfByte.length;
      }
      return i;
    }
    
    public void setCodec(ObjectCodec paramObjectCodec)
    {
      this._codec = paramObjectCodec;
    }
    
    public void setLocation(JsonLocation paramJsonLocation)
    {
      this._location = paramJsonLocation;
    }
    
    public Version version()
    {
      return PackageVersion.VERSION;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/TokenBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */