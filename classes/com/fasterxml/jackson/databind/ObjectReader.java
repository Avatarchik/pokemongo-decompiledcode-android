package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.JsonPointerBasedFilter;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectReader
  extends ObjectCodec
  implements Versioned, Serializable
{
  private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
  private static final long serialVersionUID = 1L;
  protected final DeserializationConfig _config;
  protected final DefaultDeserializationContext _context;
  protected final DataFormatReaders _dataFormatReaders;
  private final TokenFilter _filter;
  protected final InjectableValues _injectableValues;
  protected final JsonFactory _parserFactory;
  protected final JsonDeserializer<Object> _rootDeserializer;
  protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
  protected final FormatSchema _schema;
  protected final boolean _unwrapRoot;
  protected final Object _valueToUpdate;
  protected final JavaType _valueType;
  
  protected ObjectReader(ObjectMapper paramObjectMapper, DeserializationConfig paramDeserializationConfig)
  {
    this(paramObjectMapper, paramDeserializationConfig, null, null, null, null);
  }
  
  protected ObjectReader(ObjectMapper paramObjectMapper, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues)
  {
    this._config = paramDeserializationConfig;
    this._context = paramObjectMapper._deserializationContext;
    this._rootDeserializers = paramObjectMapper._rootDeserializers;
    this._parserFactory = paramObjectMapper._jsonFactory;
    this._valueType = paramJavaType;
    this._valueToUpdate = paramObject;
    if ((paramObject != null) && (paramJavaType.isArrayType())) {
      throw new IllegalArgumentException("Can not update an array value");
    }
    this._schema = paramFormatSchema;
    this._injectableValues = paramInjectableValues;
    this._unwrapRoot = paramDeserializationConfig.useRootWrapping();
    this._rootDeserializer = _prefetchRootDeserializer(paramJavaType);
    this._dataFormatReaders = null;
    this._filter = null;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, JsonFactory paramJsonFactory)
  {
    this._config = paramObjectReader._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, paramJsonFactory.requiresPropertyOrdering());
    this._context = paramObjectReader._context;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._parserFactory = paramJsonFactory;
    this._valueType = paramObjectReader._valueType;
    this._rootDeserializer = paramObjectReader._rootDeserializer;
    this._valueToUpdate = paramObjectReader._valueToUpdate;
    this._schema = paramObjectReader._schema;
    this._injectableValues = paramObjectReader._injectableValues;
    this._unwrapRoot = paramObjectReader._unwrapRoot;
    this._dataFormatReaders = paramObjectReader._dataFormatReaders;
    this._filter = paramObjectReader._filter;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, TokenFilter paramTokenFilter)
  {
    this._config = paramObjectReader._config;
    this._context = paramObjectReader._context;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._parserFactory = paramObjectReader._parserFactory;
    this._valueType = paramObjectReader._valueType;
    this._rootDeserializer = paramObjectReader._rootDeserializer;
    this._valueToUpdate = paramObjectReader._valueToUpdate;
    this._schema = paramObjectReader._schema;
    this._injectableValues = paramObjectReader._injectableValues;
    this._unwrapRoot = paramObjectReader._unwrapRoot;
    this._dataFormatReaders = paramObjectReader._dataFormatReaders;
    this._filter = paramTokenFilter;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig)
  {
    this._config = paramDeserializationConfig;
    this._context = paramObjectReader._context;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._parserFactory = paramObjectReader._parserFactory;
    this._valueType = paramObjectReader._valueType;
    this._rootDeserializer = paramObjectReader._rootDeserializer;
    this._valueToUpdate = paramObjectReader._valueToUpdate;
    this._schema = paramObjectReader._schema;
    this._injectableValues = paramObjectReader._injectableValues;
    this._unwrapRoot = paramDeserializationConfig.useRootWrapping();
    this._dataFormatReaders = paramObjectReader._dataFormatReaders;
    this._filter = paramObjectReader._filter;
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues, DataFormatReaders paramDataFormatReaders)
  {
    this._config = paramDeserializationConfig;
    this._context = paramObjectReader._context;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._parserFactory = paramObjectReader._parserFactory;
    this._valueType = paramJavaType;
    this._rootDeserializer = paramJsonDeserializer;
    this._valueToUpdate = paramObject;
    if ((paramObject != null) && (paramJavaType.isArrayType())) {
      throw new IllegalArgumentException("Can not update an array value");
    }
    this._schema = paramFormatSchema;
    this._injectableValues = paramInjectableValues;
    this._unwrapRoot = paramDeserializationConfig.useRootWrapping();
    this._dataFormatReaders = paramDataFormatReaders;
    this._filter = paramObjectReader._filter;
  }
  
  protected Object _bind(JsonParser paramJsonParser, Object paramObject)
    throws IOException
  {
    JsonToken localJsonToken = _initForReading(paramJsonParser);
    Object localObject;
    if (localJsonToken == JsonToken.VALUE_NULL) {
      if (paramObject == null)
      {
        DefaultDeserializationContext localDefaultDeserializationContext2 = createDeserializationContext(paramJsonParser);
        localObject = _findRootDeserializer(localDefaultDeserializationContext2).getNullValue(localDefaultDeserializationContext2);
      }
    }
    for (;;)
    {
      paramJsonParser.clearCurrentToken();
      return localObject;
      localObject = paramObject;
      continue;
      if ((localJsonToken == JsonToken.END_ARRAY) || (localJsonToken == JsonToken.END_OBJECT))
      {
        localObject = paramObject;
      }
      else
      {
        DefaultDeserializationContext localDefaultDeserializationContext1 = createDeserializationContext(paramJsonParser);
        JsonDeserializer localJsonDeserializer = _findRootDeserializer(localDefaultDeserializationContext1);
        if (this._unwrapRoot)
        {
          localObject = _unwrapAndDeserialize(paramJsonParser, localDefaultDeserializationContext1, this._valueType, localJsonDeserializer);
        }
        else if (paramObject == null)
        {
          localObject = localJsonDeserializer.deserialize(paramJsonParser, localDefaultDeserializationContext1);
        }
        else
        {
          localJsonDeserializer.deserialize(paramJsonParser, localDefaultDeserializationContext1, paramObject);
          localObject = paramObject;
        }
      }
    }
  }
  
  /* Error */
  protected Object _bindAndClose(JsonParser paramJsonParser)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 140	com/fasterxml/jackson/databind/ObjectReader:_initForReading	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/core/JsonToken;
    //   5: astore 4
    //   7: aload 4
    //   9: getstatic 146	com/fasterxml/jackson/core/JsonToken:VALUE_NULL	Lcom/fasterxml/jackson/core/JsonToken;
    //   12: if_acmpne +50 -> 62
    //   15: aload_0
    //   16: getfield 79	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   19: ifnonnull +34 -> 53
    //   22: aload_0
    //   23: aload_1
    //   24: invokevirtual 150	com/fasterxml/jackson/databind/ObjectReader:createDeserializationContext	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/databind/deser/DefaultDeserializationContext;
    //   27: astore 10
    //   29: aload_0
    //   30: aload 10
    //   32: invokevirtual 154	com/fasterxml/jackson/databind/ObjectReader:_findRootDeserializer	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   35: aload 10
    //   37: invokevirtual 160	com/fasterxml/jackson/databind/JsonDeserializer:getNullValue	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   40: astore 11
    //   42: aload 11
    //   44: astore 5
    //   46: aload_1
    //   47: invokevirtual 187	com/fasterxml/jackson/core/JsonParser:close	()V
    //   50: aload 5
    //   52: areturn
    //   53: aload_0
    //   54: getfield 79	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   57: astore 5
    //   59: goto -13 -> 46
    //   62: aload 4
    //   64: getstatic 168	com/fasterxml/jackson/core/JsonToken:END_ARRAY	Lcom/fasterxml/jackson/core/JsonToken;
    //   67: if_acmpeq +11 -> 78
    //   70: aload 4
    //   72: getstatic 171	com/fasterxml/jackson/core/JsonToken:END_OBJECT	Lcom/fasterxml/jackson/core/JsonToken;
    //   75: if_acmpne +12 -> 87
    //   78: aload_0
    //   79: getfield 79	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   82: astore 5
    //   84: goto -38 -> 46
    //   87: aload_0
    //   88: aload_1
    //   89: invokevirtual 150	com/fasterxml/jackson/databind/ObjectReader:createDeserializationContext	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/databind/deser/DefaultDeserializationContext;
    //   92: astore 7
    //   94: aload_0
    //   95: aload 7
    //   97: invokevirtual 154	com/fasterxml/jackson/databind/ObjectReader:_findRootDeserializer	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   100: astore 8
    //   102: aload_0
    //   103: getfield 103	com/fasterxml/jackson/databind/ObjectReader:_unwrapRoot	Z
    //   106: ifeq +21 -> 127
    //   109: aload_0
    //   110: aload_1
    //   111: aload 7
    //   113: aload_0
    //   114: getfield 77	com/fasterxml/jackson/databind/ObjectReader:_valueType	Lcom/fasterxml/jackson/databind/JavaType;
    //   117: aload 8
    //   119: invokevirtual 175	com/fasterxml/jackson/databind/ObjectReader:_unwrapAndDeserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/databind/JsonDeserializer;)Ljava/lang/Object;
    //   122: astore 5
    //   124: goto -78 -> 46
    //   127: aload_0
    //   128: getfield 79	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   131: ifnonnull +16 -> 147
    //   134: aload 8
    //   136: aload_1
    //   137: aload 7
    //   139: invokevirtual 179	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   142: astore 5
    //   144: goto -98 -> 46
    //   147: aload 8
    //   149: aload_1
    //   150: aload 7
    //   152: aload_0
    //   153: getfield 79	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   156: invokevirtual 182	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Ljava/lang/Object;)Ljava/lang/Object;
    //   159: pop
    //   160: aload_0
    //   161: getfield 79	com/fasterxml/jackson/databind/ObjectReader:_valueToUpdate	Ljava/lang/Object;
    //   164: astore 5
    //   166: goto -120 -> 46
    //   169: astore_2
    //   170: aload_1
    //   171: invokevirtual 187	com/fasterxml/jackson/core/JsonParser:close	()V
    //   174: aload_2
    //   175: athrow
    //   176: astore 6
    //   178: goto -128 -> 50
    //   181: astore_3
    //   182: goto -8 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	185	0	this	ObjectReader
    //   0	185	1	paramJsonParser	JsonParser
    //   169	6	2	localObject1	Object
    //   181	1	3	localIOException1	IOException
    //   5	66	4	localJsonToken	JsonToken
    //   44	121	5	localObject2	Object
    //   176	1	6	localIOException2	IOException
    //   92	59	7	localDefaultDeserializationContext1	DefaultDeserializationContext
    //   100	48	8	localJsonDeserializer	JsonDeserializer
    //   27	9	10	localDefaultDeserializationContext2	DefaultDeserializationContext
    //   40	3	11	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	42	169	finally
    //   53	166	169	finally
    //   46	50	176	java/io/IOException
    //   170	174	181	java/io/IOException
  }
  
  /* Error */
  protected JsonNode _bindAndCloseAsTree(JsonParser paramJsonParser)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 192	com/fasterxml/jackson/databind/ObjectReader:_bindAsTree	(Lcom/fasterxml/jackson/core/JsonParser;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   5: astore 4
    //   7: aload_1
    //   8: invokevirtual 187	com/fasterxml/jackson/core/JsonParser:close	()V
    //   11: aload 4
    //   13: areturn
    //   14: astore_2
    //   15: aload_1
    //   16: invokevirtual 187	com/fasterxml/jackson/core/JsonParser:close	()V
    //   19: aload_2
    //   20: athrow
    //   21: astore 5
    //   23: goto -12 -> 11
    //   26: astore_3
    //   27: goto -8 -> 19
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	30	0	this	ObjectReader
    //   0	30	1	paramJsonParser	JsonParser
    //   14	6	2	localObject	Object
    //   26	1	3	localIOException1	IOException
    //   5	7	4	localJsonNode	JsonNode
    //   21	1	5	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   0	7	14	finally
    //   7	11	21	java/io/IOException
    //   15	19	26	java/io/IOException
  }
  
  protected <T> MappingIterator<T> _bindAndReadValues(JsonParser paramJsonParser)
    throws IOException
  {
    _initForMultiRead(paramJsonParser);
    paramJsonParser.nextToken();
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramJsonParser);
    return _newIterator(paramJsonParser, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext), true);
  }
  
  protected JsonNode _bindAsTree(JsonParser paramJsonParser)
    throws IOException
  {
    JsonToken localJsonToken = _initForReading(paramJsonParser);
    Object localObject;
    if ((localJsonToken == JsonToken.VALUE_NULL) || (localJsonToken == JsonToken.END_ARRAY) || (localJsonToken == JsonToken.END_OBJECT)) {
      localObject = NullNode.instance;
    }
    for (;;)
    {
      paramJsonParser.clearCurrentToken();
      return (JsonNode)localObject;
      DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramJsonParser);
      JsonDeserializer localJsonDeserializer = _findTreeDeserializer(localDefaultDeserializationContext);
      if (this._unwrapRoot) {
        localObject = (JsonNode)_unwrapAndDeserialize(paramJsonParser, localDefaultDeserializationContext, JSON_NODE_TYPE, localJsonDeserializer);
      } else {
        localObject = (JsonNode)localJsonDeserializer.deserialize(paramJsonParser, localDefaultDeserializationContext);
      }
    }
  }
  
  protected JsonParser _considerFilter(JsonParser paramJsonParser)
  {
    if ((this._filter == null) || (FilteringParserDelegate.class.isInstance(paramJsonParser))) {}
    for (;;)
    {
      return paramJsonParser;
      paramJsonParser = new FilteringParserDelegate(paramJsonParser, this._filter, false, false);
    }
  }
  
  protected Object _detectBindAndClose(DataFormatReaders.Match paramMatch, boolean paramBoolean)
    throws IOException
  {
    if (!paramMatch.hasMatch()) {
      _reportUnkownFormat(this._dataFormatReaders, paramMatch);
    }
    JsonParser localJsonParser = paramMatch.createParserWithMatch();
    if (paramBoolean) {
      localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    }
    return paramMatch.getReader()._bindAndClose(localJsonParser);
  }
  
  protected Object _detectBindAndClose(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    DataFormatReaders.Match localMatch = this._dataFormatReaders.findFormat(paramArrayOfByte, paramInt1, paramInt2);
    if (!localMatch.hasMatch()) {
      _reportUnkownFormat(this._dataFormatReaders, localMatch);
    }
    JsonParser localJsonParser = localMatch.createParserWithMatch();
    return localMatch.getReader()._bindAndClose(localJsonParser);
  }
  
  protected JsonNode _detectBindAndCloseAsTree(InputStream paramInputStream)
    throws IOException
  {
    DataFormatReaders.Match localMatch = this._dataFormatReaders.findFormat(paramInputStream);
    if (!localMatch.hasMatch()) {
      _reportUnkownFormat(this._dataFormatReaders, localMatch);
    }
    JsonParser localJsonParser = localMatch.createParserWithMatch();
    localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    return localMatch.getReader()._bindAndCloseAsTree(localJsonParser);
  }
  
  protected <T> MappingIterator<T> _detectBindAndReadValues(DataFormatReaders.Match paramMatch, boolean paramBoolean)
    throws IOException, JsonProcessingException
  {
    if (!paramMatch.hasMatch()) {
      _reportUnkownFormat(this._dataFormatReaders, paramMatch);
    }
    JsonParser localJsonParser = paramMatch.createParserWithMatch();
    if (paramBoolean) {
      localJsonParser.enable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    }
    return paramMatch.getReader()._bindAndReadValues(localJsonParser);
  }
  
  protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer;
    if (this._rootDeserializer != null) {
      localJsonDeserializer = this._rootDeserializer;
    }
    for (;;)
    {
      return localJsonDeserializer;
      JavaType localJavaType = this._valueType;
      if (localJavaType == null) {
        throw new JsonMappingException("No value type configured for ObjectReader");
      }
      localJsonDeserializer = (JsonDeserializer)this._rootDeserializers.get(localJavaType);
      if (localJsonDeserializer == null)
      {
        localJsonDeserializer = paramDeserializationContext.findRootValueDeserializer(localJavaType);
        if (localJsonDeserializer == null) {
          throw new JsonMappingException("Can not find a deserializer for type " + localJavaType);
        }
        this._rootDeserializers.put(localJavaType, localJsonDeserializer);
      }
    }
  }
  
  protected JsonDeserializer<Object> _findTreeDeserializer(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)this._rootDeserializers.get(JSON_NODE_TYPE);
    if (localJsonDeserializer == null)
    {
      localJsonDeserializer = paramDeserializationContext.findRootValueDeserializer(JSON_NODE_TYPE);
      if (localJsonDeserializer == null) {
        throw new JsonMappingException("Can not find a deserializer for type " + JSON_NODE_TYPE);
      }
      this._rootDeserializers.put(JSON_NODE_TYPE, localJsonDeserializer);
    }
    return localJsonDeserializer;
  }
  
  protected void _initForMultiRead(JsonParser paramJsonParser)
    throws IOException
  {
    if (this._schema != null) {
      paramJsonParser.setSchema(this._schema);
    }
    this._config.initialize(paramJsonParser);
  }
  
  protected JsonToken _initForReading(JsonParser paramJsonParser)
    throws IOException
  {
    if (this._schema != null) {
      paramJsonParser.setSchema(this._schema);
    }
    this._config.initialize(paramJsonParser);
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == null)
    {
      localJsonToken = paramJsonParser.nextToken();
      if (localJsonToken == null) {
        throw JsonMappingException.from(paramJsonParser, "No content to map due to end-of-input");
      }
    }
    return localJsonToken;
  }
  
  protected InputStream _inputStream(File paramFile)
    throws IOException
  {
    return new FileInputStream(paramFile);
  }
  
  protected InputStream _inputStream(URL paramURL)
    throws IOException
  {
    return paramURL.openStream();
  }
  
  protected ObjectReader _new(ObjectReader paramObjectReader, JsonFactory paramJsonFactory)
  {
    return new ObjectReader(paramObjectReader, paramJsonFactory);
  }
  
  protected ObjectReader _new(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig)
  {
    return new ObjectReader(paramObjectReader, paramDeserializationConfig);
  }
  
  protected ObjectReader _new(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues, DataFormatReaders paramDataFormatReaders)
  {
    return new ObjectReader(paramObjectReader, paramDeserializationConfig, paramJavaType, paramJsonDeserializer, paramObject, paramFormatSchema, paramInjectableValues, paramDataFormatReaders);
  }
  
  protected <T> MappingIterator<T> _newIterator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonDeserializer<?> paramJsonDeserializer, boolean paramBoolean)
  {
    return new MappingIterator(this._valueType, paramJsonParser, paramDeserializationContext, paramJsonDeserializer, paramBoolean, this._valueToUpdate);
  }
  
  protected JsonDeserializer<Object> _prefetchRootDeserializer(JavaType paramJavaType)
  {
    Object localObject = null;
    if ((paramJavaType == null) || (!this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH))) {}
    for (;;)
    {
      return (JsonDeserializer<Object>)localObject;
      localObject = (JsonDeserializer)this._rootDeserializers.get(paramJavaType);
      if (localObject == null) {
        try
        {
          localObject = createDeserializationContext(null).findRootValueDeserializer(paramJavaType);
          if (localObject != null) {
            this._rootDeserializers.put(paramJavaType, localObject);
          }
        }
        catch (JsonProcessingException localJsonProcessingException) {}
      }
    }
  }
  
  protected void _reportUndetectableSource(Object paramObject)
    throws JsonProcessingException
  {
    throw new JsonParseException("Can not use source of type " + paramObject.getClass().getName() + " with format auto-detection: must be byte- not char-based", JsonLocation.NA);
  }
  
  protected void _reportUnkownFormat(DataFormatReaders paramDataFormatReaders, DataFormatReaders.Match paramMatch)
    throws JsonProcessingException
  {
    throw new JsonParseException("Can not detect format from input, does not look like any of detectable formats " + paramDataFormatReaders.toString(), JsonLocation.NA);
  }
  
  protected Object _unwrapAndDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer)
    throws IOException
  {
    String str1 = this._config.findRootName(paramJavaType).getSimpleName();
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      throw JsonMappingException.from(paramJsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
    }
    if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME) {
      throw JsonMappingException.from(paramJsonParser, "Current token not FIELD_NAME (to contain expected root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
    }
    String str2 = paramJsonParser.getCurrentName();
    if (!str1.equals(str2)) {
      throw JsonMappingException.from(paramJsonParser, "Root name '" + str2 + "' does not match expected ('" + str1 + "') for type " + paramJavaType);
    }
    paramJsonParser.nextToken();
    if (this._valueToUpdate == null) {}
    for (Object localObject = paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext); paramJsonParser.nextToken() != JsonToken.END_OBJECT; localObject = this._valueToUpdate)
    {
      throw JsonMappingException.from(paramJsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
      paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext, this._valueToUpdate);
    }
    return localObject;
  }
  
  protected void _verifySchemaType(FormatSchema paramFormatSchema)
  {
    if ((paramFormatSchema != null) && (!this._parserFactory.canUseSchema(paramFormatSchema))) {
      throw new IllegalArgumentException("Can not use FormatSchema of type " + paramFormatSchema.getClass().getName() + " for format " + this._parserFactory.getFormatName());
    }
  }
  
  protected ObjectReader _with(DeserializationConfig paramDeserializationConfig)
  {
    if (paramDeserializationConfig == this._config) {}
    for (;;)
    {
      return this;
      ObjectReader localObjectReader = _new(this, paramDeserializationConfig);
      if (this._dataFormatReaders != null) {
        localObjectReader = localObjectReader.withFormatDetection(this._dataFormatReaders.with(paramDeserializationConfig));
      }
      this = localObjectReader;
    }
  }
  
  public ObjectReader at(JsonPointer paramJsonPointer)
  {
    return new ObjectReader(this, new JsonPointerBasedFilter(paramJsonPointer));
  }
  
  public ObjectReader at(String paramString)
  {
    return new ObjectReader(this, new JsonPointerBasedFilter(paramString));
  }
  
  public JsonNode createArrayNode()
  {
    return this._config.getNodeFactory().arrayNode();
  }
  
  protected DefaultDeserializationContext createDeserializationContext(JsonParser paramJsonParser)
  {
    return this._context.createInstance(this._config, paramJsonParser, this._injectableValues);
  }
  
  public JsonNode createObjectNode()
  {
    return this._config.getNodeFactory().objectNode();
  }
  
  public ObjectReader forType(TypeReference<?> paramTypeReference)
  {
    return forType(this._config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  public ObjectReader forType(JavaType paramJavaType)
  {
    if ((paramJavaType != null) && (paramJavaType.equals(this._valueType))) {}
    for (;;)
    {
      return this;
      JsonDeserializer localJsonDeserializer = _prefetchRootDeserializer(paramJavaType);
      DataFormatReaders localDataFormatReaders = this._dataFormatReaders;
      if (localDataFormatReaders != null) {
        localDataFormatReaders = localDataFormatReaders.withType(paramJavaType);
      }
      DeserializationConfig localDeserializationConfig = this._config;
      Object localObject = this._valueToUpdate;
      FormatSchema localFormatSchema = this._schema;
      InjectableValues localInjectableValues = this._injectableValues;
      this = _new(this, localDeserializationConfig, paramJavaType, localJsonDeserializer, localObject, localFormatSchema, localInjectableValues, localDataFormatReaders);
    }
  }
  
  public ObjectReader forType(Class<?> paramClass)
  {
    return forType(this._config.constructType(paramClass));
  }
  
  public ContextAttributes getAttributes()
  {
    return this._config.getAttributes();
  }
  
  public DeserializationConfig getConfig()
  {
    return this._config;
  }
  
  public JsonFactory getFactory()
  {
    return this._parserFactory;
  }
  
  public InjectableValues getInjectableValues()
  {
    return this._injectableValues;
  }
  
  public TypeFactory getTypeFactory()
  {
    return this._config.getTypeFactory();
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return this._parserFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(DeserializationFeature paramDeserializationFeature)
  {
    return this._config.isEnabled(paramDeserializationFeature);
  }
  
  public boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return this._config.isEnabled(paramMapperFeature);
  }
  
  public <T extends TreeNode> T readTree(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    return _bindAsTree(paramJsonParser);
  }
  
  public JsonNode readTree(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (JsonNode localJsonNode = _detectBindAndCloseAsTree(paramInputStream);; localJsonNode = _bindAndCloseAsTree(_considerFilter(this._parserFactory.createParser(paramInputStream)))) {
      return localJsonNode;
    }
  }
  
  public JsonNode readTree(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    return _bindAndCloseAsTree(_considerFilter(this._parserFactory.createParser(paramReader)));
  }
  
  public JsonNode readTree(String paramString)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    return _bindAndCloseAsTree(_considerFilter(this._parserFactory.createParser(paramString)));
  }
  
  public <T> T readValue(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    return (T)_bind(paramJsonParser, this._valueToUpdate);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException
  {
    return (T)forType((JavaType)paramResolvedType).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException
  {
    return (T)forType(paramTypeReference).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    return (T)forType(paramJavaType).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException
  {
    return (T)forType(paramClass).readValue(paramJsonParser);
  }
  
  public <T> T readValue(JsonNode paramJsonNode)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramJsonNode);
    }
    return (T)_bindAndClose(_considerFilter(treeAsTokens(paramJsonNode)));
  }
  
  public <T> T readValue(File paramFile)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (Object localObject = _detectBindAndClose(this._dataFormatReaders.findFormat(_inputStream(paramFile)), true);; localObject = _bindAndClose(_considerFilter(this._parserFactory.createParser(paramFile)))) {
      return (T)localObject;
    }
  }
  
  public <T> T readValue(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (Object localObject = _detectBindAndClose(this._dataFormatReaders.findFormat(paramInputStream), false);; localObject = _bindAndClose(_considerFilter(this._parserFactory.createParser(paramInputStream)))) {
      return (T)localObject;
    }
  }
  
  public <T> T readValue(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramReader)));
  }
  
  public <T> T readValue(String paramString)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    return (T)_bindAndClose(_considerFilter(this._parserFactory.createParser(paramString)));
  }
  
  public <T> T readValue(URL paramURL)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (Object localObject = _detectBindAndClose(this._dataFormatReaders.findFormat(_inputStream(paramURL)), true);; localObject = _bindAndClose(_considerFilter(this._parserFactory.createParser(paramURL)))) {
      return (T)localObject;
    }
  }
  
  public <T> T readValue(byte[] paramArrayOfByte)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (Object localObject = _detectBindAndClose(paramArrayOfByte, 0, paramArrayOfByte.length);; localObject = _bindAndClose(_considerFilter(this._parserFactory.createParser(paramArrayOfByte)))) {
      return (T)localObject;
    }
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (Object localObject = _detectBindAndClose(paramArrayOfByte, paramInt1, paramInt2);; localObject = _bindAndClose(_considerFilter(this._parserFactory.createParser(paramArrayOfByte, paramInt1, paramInt2)))) {
      return (T)localObject;
    }
  }
  
  public <T> MappingIterator<T> readValues(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramJsonParser);
    return _newIterator(paramJsonParser, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext), false);
  }
  
  public <T> MappingIterator<T> readValues(File paramFile)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (MappingIterator localMappingIterator = _detectBindAndReadValues(this._dataFormatReaders.findFormat(_inputStream(paramFile)), false);; localMappingIterator = _bindAndReadValues(_considerFilter(this._parserFactory.createParser(paramFile)))) {
      return localMappingIterator;
    }
  }
  
  public <T> MappingIterator<T> readValues(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (MappingIterator localMappingIterator = _detectBindAndReadValues(this._dataFormatReaders.findFormat(paramInputStream), false);; localMappingIterator = _bindAndReadValues(_considerFilter(this._parserFactory.createParser(paramInputStream)))) {
      return localMappingIterator;
    }
  }
  
  public <T> MappingIterator<T> readValues(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramReader);
    }
    JsonParser localJsonParser = _considerFilter(this._parserFactory.createParser(paramReader));
    _initForMultiRead(localJsonParser);
    localJsonParser.nextToken();
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(localJsonParser);
    return _newIterator(localJsonParser, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext), true);
  }
  
  public <T> MappingIterator<T> readValues(String paramString)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {
      _reportUndetectableSource(paramString);
    }
    JsonParser localJsonParser = _considerFilter(this._parserFactory.createParser(paramString));
    _initForMultiRead(localJsonParser);
    localJsonParser.nextToken();
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(localJsonParser);
    return _newIterator(localJsonParser, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext), true);
  }
  
  public <T> MappingIterator<T> readValues(URL paramURL)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (MappingIterator localMappingIterator = _detectBindAndReadValues(this._dataFormatReaders.findFormat(_inputStream(paramURL)), true);; localMappingIterator = _bindAndReadValues(_considerFilter(this._parserFactory.createParser(paramURL)))) {
      return localMappingIterator;
    }
  }
  
  public final <T> MappingIterator<T> readValues(byte[] paramArrayOfByte)
    throws IOException, JsonProcessingException
  {
    return readValues(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public <T> MappingIterator<T> readValues(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonProcessingException
  {
    if (this._dataFormatReaders != null) {}
    for (MappingIterator localMappingIterator = _detectBindAndReadValues(this._dataFormatReaders.findFormat(paramArrayOfByte, paramInt1, paramInt2), false);; localMappingIterator = _bindAndReadValues(_considerFilter(this._parserFactory.createParser(paramArrayOfByte)))) {
      return localMappingIterator;
    }
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException
  {
    return readValues(paramJsonParser, (JavaType)paramResolvedType);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException
  {
    return forType(paramTypeReference).readValues(paramJsonParser);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    return forType(paramJavaType).readValues(paramJsonParser);
  }
  
  public <T> Iterator<T> readValues(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException
  {
    return forType(paramClass).readValues(paramJsonParser);
  }
  
  public JsonParser treeAsTokens(TreeNode paramTreeNode)
  {
    return new TreeTraversingParser((JsonNode)paramTreeNode, this);
  }
  
  public <T> T treeToValue(TreeNode paramTreeNode, Class<T> paramClass)
    throws JsonProcessingException
  {
    try
    {
      Object localObject = readValue(treeAsTokens(paramTreeNode), paramClass);
      return (T)localObject;
    }
    catch (JsonProcessingException localJsonProcessingException)
    {
      throw localJsonProcessingException;
    }
    catch (IOException localIOException)
    {
      throw new IllegalArgumentException(localIOException.getMessage(), localIOException);
    }
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public ObjectReader with(Base64Variant paramBase64Variant)
  {
    return _with(this._config.with(paramBase64Variant));
  }
  
  public ObjectReader with(FormatSchema paramFormatSchema)
  {
    if (this._schema == paramFormatSchema) {}
    for (;;)
    {
      return this;
      _verifySchemaType(paramFormatSchema);
      DeserializationConfig localDeserializationConfig = this._config;
      JavaType localJavaType = this._valueType;
      JsonDeserializer localJsonDeserializer = this._rootDeserializer;
      Object localObject = this._valueToUpdate;
      InjectableValues localInjectableValues = this._injectableValues;
      DataFormatReaders localDataFormatReaders = this._dataFormatReaders;
      this = _new(this, localDeserializationConfig, localJavaType, localJsonDeserializer, localObject, paramFormatSchema, localInjectableValues, localDataFormatReaders);
    }
  }
  
  public ObjectReader with(JsonFactory paramJsonFactory)
  {
    if (paramJsonFactory == this._parserFactory) {}
    for (;;)
    {
      return this;
      ObjectReader localObjectReader = _new(this, paramJsonFactory);
      if (paramJsonFactory.getCodec() == null) {
        paramJsonFactory.setCodec(localObjectReader);
      }
      this = localObjectReader;
    }
  }
  
  public ObjectReader with(JsonParser.Feature paramFeature)
  {
    return _with(this._config.with(paramFeature));
  }
  
  public ObjectReader with(DeserializationConfig paramDeserializationConfig)
  {
    return _with(paramDeserializationConfig);
  }
  
  public ObjectReader with(DeserializationFeature paramDeserializationFeature)
  {
    return _with(this._config.with(paramDeserializationFeature));
  }
  
  public ObjectReader with(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    return _with(this._config.with(paramDeserializationFeature, paramVarArgs));
  }
  
  public ObjectReader with(InjectableValues paramInjectableValues)
  {
    if (this._injectableValues == paramInjectableValues) {}
    for (;;)
    {
      return this;
      DeserializationConfig localDeserializationConfig = this._config;
      JavaType localJavaType = this._valueType;
      JsonDeserializer localJsonDeserializer = this._rootDeserializer;
      Object localObject = this._valueToUpdate;
      FormatSchema localFormatSchema = this._schema;
      DataFormatReaders localDataFormatReaders = this._dataFormatReaders;
      this = _new(this, localDeserializationConfig, localJavaType, localJsonDeserializer, localObject, localFormatSchema, paramInjectableValues, localDataFormatReaders);
    }
  }
  
  public ObjectReader with(ContextAttributes paramContextAttributes)
  {
    return _with(this._config.with(paramContextAttributes));
  }
  
  public ObjectReader with(JsonNodeFactory paramJsonNodeFactory)
  {
    return _with(this._config.with(paramJsonNodeFactory));
  }
  
  public ObjectReader with(Locale paramLocale)
  {
    return _with(this._config.with(paramLocale));
  }
  
  public ObjectReader with(TimeZone paramTimeZone)
  {
    return _with(this._config.with(paramTimeZone));
  }
  
  public ObjectReader withAttribute(Object paramObject1, Object paramObject2)
  {
    return _with((DeserializationConfig)this._config.withAttribute(paramObject1, paramObject2));
  }
  
  public ObjectReader withAttributes(Map<Object, Object> paramMap)
  {
    return _with((DeserializationConfig)this._config.withAttributes(paramMap));
  }
  
  public ObjectReader withFeatures(JsonParser.Feature... paramVarArgs)
  {
    return _with(this._config.withFeatures(paramVarArgs));
  }
  
  public ObjectReader withFeatures(DeserializationFeature... paramVarArgs)
  {
    return _with(this._config.withFeatures(paramVarArgs));
  }
  
  public ObjectReader withFormatDetection(DataFormatReaders paramDataFormatReaders)
  {
    return _new(this, this._config, this._valueType, this._rootDeserializer, this._valueToUpdate, this._schema, this._injectableValues, paramDataFormatReaders);
  }
  
  public ObjectReader withFormatDetection(ObjectReader... paramVarArgs)
  {
    return withFormatDetection(new DataFormatReaders(paramVarArgs));
  }
  
  public ObjectReader withHandler(DeserializationProblemHandler paramDeserializationProblemHandler)
  {
    return _with(this._config.withHandler(paramDeserializationProblemHandler));
  }
  
  public ObjectReader withRootName(PropertyName paramPropertyName)
  {
    return _with(this._config.withRootName(paramPropertyName));
  }
  
  public ObjectReader withRootName(String paramString)
  {
    return _with((DeserializationConfig)this._config.withRootName(paramString));
  }
  
  @Deprecated
  public ObjectReader withType(TypeReference<?> paramTypeReference)
  {
    return forType(this._config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  @Deprecated
  public ObjectReader withType(JavaType paramJavaType)
  {
    return forType(paramJavaType);
  }
  
  @Deprecated
  public ObjectReader withType(Class<?> paramClass)
  {
    return forType(this._config.constructType(paramClass));
  }
  
  @Deprecated
  public ObjectReader withType(Type paramType)
  {
    return forType(this._config.getTypeFactory().constructType(paramType));
  }
  
  public ObjectReader withValueToUpdate(Object paramObject)
  {
    if (paramObject == this._valueToUpdate) {
      return this;
    }
    if (paramObject == null) {
      throw new IllegalArgumentException("cat not update null value");
    }
    if (this._valueType == null) {}
    for (JavaType localJavaType = this._config.constructType(paramObject.getClass());; localJavaType = this._valueType)
    {
      DeserializationConfig localDeserializationConfig = this._config;
      JsonDeserializer localJsonDeserializer = this._rootDeserializer;
      FormatSchema localFormatSchema = this._schema;
      InjectableValues localInjectableValues = this._injectableValues;
      DataFormatReaders localDataFormatReaders = this._dataFormatReaders;
      this = _new(this, localDeserializationConfig, localJavaType, localJsonDeserializer, paramObject, localFormatSchema, localInjectableValues, localDataFormatReaders);
      break;
    }
  }
  
  public ObjectReader withView(Class<?> paramClass)
  {
    return _with(this._config.withView(paramClass));
  }
  
  public ObjectReader without(JsonParser.Feature paramFeature)
  {
    return _with(this._config.without(paramFeature));
  }
  
  public ObjectReader without(DeserializationFeature paramDeserializationFeature)
  {
    return _with(this._config.without(paramDeserializationFeature));
  }
  
  public ObjectReader without(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    return _with(this._config.without(paramDeserializationFeature, paramVarArgs));
  }
  
  public ObjectReader withoutAttribute(Object paramObject)
  {
    return _with((DeserializationConfig)this._config.withoutAttribute(paramObject));
  }
  
  public ObjectReader withoutFeatures(JsonParser.Feature... paramVarArgs)
  {
    return _with(this._config.withoutFeatures(paramVarArgs));
  }
  
  public ObjectReader withoutFeatures(DeserializationFeature... paramVarArgs)
  {
    return _with(this._config.withoutFeatures(paramVarArgs));
  }
  
  public ObjectReader withoutRootName()
  {
    return _with(this._config.withRootName(PropertyName.NO_NAME));
  }
  
  public void writeTree(JsonGenerator paramJsonGenerator, TreeNode paramTreeNode)
  {
    throw new UnsupportedOperationException();
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonProcessingException
  {
    throw new UnsupportedOperationException("Not implemented for ObjectReader");
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ObjectReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */