package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.Impl;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker.Std;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ObjectMapper
  extends ObjectCodec
  implements Versioned, Serializable
{
  protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR;
  protected static final BaseSettings DEFAULT_BASE = new BaseSettings(null, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), null, Base64Variants.getDefaultVariant());
  private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
  protected static final VisibilityChecker<?> STD_VISIBILITY_CHECKER;
  @Deprecated
  protected static final PrettyPrinter _defaultPrettyPrinter;
  private static final long serialVersionUID = 1L;
  protected DeserializationConfig _deserializationConfig;
  protected DefaultDeserializationContext _deserializationContext;
  protected InjectableValues _injectableValues;
  protected final JsonFactory _jsonFactory;
  protected SimpleMixInResolver _mixIns;
  protected Set<Object> _registeredModuleTypes;
  protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers = new ConcurrentHashMap(64, 0.6F, 2);
  protected SerializationConfig _serializationConfig;
  protected SerializerFactory _serializerFactory;
  protected DefaultSerializerProvider _serializerProvider;
  protected SubtypeResolver _subtypeResolver;
  protected TypeFactory _typeFactory;
  
  static
  {
    DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
    STD_VISIBILITY_CHECKER = VisibilityChecker.Std.defaultInstance();
    _defaultPrettyPrinter = new DefaultPrettyPrinter();
  }
  
  public ObjectMapper()
  {
    this(null, null, null);
  }
  
  public ObjectMapper(JsonFactory paramJsonFactory)
  {
    this(paramJsonFactory, null, null);
  }
  
  public ObjectMapper(JsonFactory paramJsonFactory, DefaultSerializerProvider paramDefaultSerializerProvider, DefaultDeserializationContext paramDefaultDeserializationContext)
  {
    if (paramJsonFactory == null) {
      this._jsonFactory = new MappingJsonFactory(this);
    }
    for (;;)
    {
      this._subtypeResolver = new StdSubtypeResolver();
      RootNameLookup localRootNameLookup = new RootNameLookup();
      this._typeFactory = TypeFactory.defaultInstance();
      SimpleMixInResolver localSimpleMixInResolver = new SimpleMixInResolver(null);
      this._mixIns = localSimpleMixInResolver;
      BaseSettings localBaseSettings = DEFAULT_BASE.withClassIntrospector(defaultClassIntrospector());
      this._serializationConfig = new SerializationConfig(localBaseSettings, this._subtypeResolver, localSimpleMixInResolver, localRootNameLookup);
      this._deserializationConfig = new DeserializationConfig(localBaseSettings, this._subtypeResolver, localSimpleMixInResolver, localRootNameLookup);
      boolean bool = this._jsonFactory.requiresPropertyOrdering();
      if ((bool ^ this._serializationConfig.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY))) {
        configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, bool);
      }
      if (paramDefaultSerializerProvider == null) {
        paramDefaultSerializerProvider = new DefaultSerializerProvider.Impl();
      }
      this._serializerProvider = paramDefaultSerializerProvider;
      if (paramDefaultDeserializationContext == null) {
        paramDefaultDeserializationContext = new DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance);
      }
      this._deserializationContext = paramDefaultDeserializationContext;
      this._serializerFactory = BeanSerializerFactory.instance;
      return;
      this._jsonFactory = paramJsonFactory;
      if (paramJsonFactory.getCodec() == null) {
        this._jsonFactory.setCodec(this);
      }
    }
  }
  
  protected ObjectMapper(ObjectMapper paramObjectMapper)
  {
    this._jsonFactory = paramObjectMapper._jsonFactory.copy();
    this._jsonFactory.setCodec(this);
    this._subtypeResolver = paramObjectMapper._subtypeResolver;
    this._typeFactory = paramObjectMapper._typeFactory;
    this._injectableValues = paramObjectMapper._injectableValues;
    SimpleMixInResolver localSimpleMixInResolver = paramObjectMapper._mixIns.copy();
    this._mixIns = localSimpleMixInResolver;
    RootNameLookup localRootNameLookup = new RootNameLookup();
    this._serializationConfig = new SerializationConfig(paramObjectMapper._serializationConfig, localSimpleMixInResolver, localRootNameLookup);
    this._deserializationConfig = new DeserializationConfig(paramObjectMapper._deserializationConfig, localSimpleMixInResolver, localRootNameLookup);
    this._serializerProvider = paramObjectMapper._serializerProvider.copy();
    this._deserializationContext = paramObjectMapper._deserializationContext.copy();
    this._serializerFactory = paramObjectMapper._serializerFactory;
    Set localSet = this._registeredModuleTypes;
    if (localSet == null) {}
    for (this._registeredModuleTypes = null;; this._registeredModuleTypes = new LinkedHashSet(localSet)) {
      return;
    }
  }
  
  /* Error */
  private final void _configAndWriteCloseable(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    // Byte code:
    //   0: aload_2
    //   1: checkcast 273	java/io/Closeable
    //   4: astore 4
    //   6: aload_0
    //   7: aload_3
    //   8: invokevirtual 276	com/fasterxml/jackson/databind/ObjectMapper:_serializerProvider	(Lcom/fasterxml/jackson/databind/SerializationConfig;)Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   11: aload_1
    //   12: aload_2
    //   13: invokevirtual 280	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;)V
    //   16: aload_1
    //   17: astore 9
    //   19: aconst_null
    //   20: astore_1
    //   21: aload 9
    //   23: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   26: aload 4
    //   28: astore 10
    //   30: aconst_null
    //   31: astore 4
    //   33: aload 10
    //   35: invokeinterface 286 1 0
    //   40: iconst_0
    //   41: ifeq +15 -> 56
    //   44: aconst_null
    //   45: getstatic 292	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   48: invokevirtual 296	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   51: pop
    //   52: aconst_null
    //   53: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   56: iconst_0
    //   57: ifeq +9 -> 66
    //   60: aconst_null
    //   61: invokeinterface 286 1 0
    //   66: return
    //   67: astore 5
    //   69: aload_1
    //   70: ifnull +15 -> 85
    //   73: aload_1
    //   74: getstatic 292	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   77: invokevirtual 296	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   80: pop
    //   81: aload_1
    //   82: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   85: aload 4
    //   87: ifnull +10 -> 97
    //   90: aload 4
    //   92: invokeinterface 286 1 0
    //   97: aload 5
    //   99: athrow
    //   100: astore 13
    //   102: goto -46 -> 56
    //   105: astore 11
    //   107: goto -41 -> 66
    //   110: astore 8
    //   112: goto -27 -> 85
    //   115: astore 6
    //   117: goto -20 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	this	ObjectMapper
    //   0	120	1	paramJsonGenerator	JsonGenerator
    //   0	120	2	paramObject	Object
    //   0	120	3	paramSerializationConfig	SerializationConfig
    //   4	87	4	localCloseable1	Closeable
    //   67	31	5	localObject	Object
    //   115	1	6	localIOException1	IOException
    //   110	1	8	localIOException2	IOException
    //   17	5	9	localJsonGenerator	JsonGenerator
    //   28	6	10	localCloseable2	Closeable
    //   105	1	11	localIOException3	IOException
    //   100	1	13	localIOException4	IOException
    // Exception table:
    //   from	to	target	type
    //   6	40	67	finally
    //   52	56	100	java/io/IOException
    //   60	66	105	java/io/IOException
    //   81	85	110	java/io/IOException
    //   90	97	115	java/io/IOException
  }
  
  /* Error */
  private final void _writeCloseableValue(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    // Byte code:
    //   0: aload_2
    //   1: checkcast 273	java/io/Closeable
    //   4: astore 4
    //   6: aload_0
    //   7: aload_3
    //   8: invokevirtual 276	com/fasterxml/jackson/databind/ObjectMapper:_serializerProvider	(Lcom/fasterxml/jackson/databind/SerializationConfig;)Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   11: aload_1
    //   12: aload_2
    //   13: invokevirtual 280	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;)V
    //   16: aload_3
    //   17: getstatic 303	com/fasterxml/jackson/databind/SerializationFeature:FLUSH_AFTER_WRITE_VALUE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   20: invokevirtual 306	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   23: ifeq +7 -> 30
    //   26: aload_1
    //   27: invokevirtual 309	com/fasterxml/jackson/core/JsonGenerator:flush	()V
    //   30: aload 4
    //   32: astore 7
    //   34: aconst_null
    //   35: astore 4
    //   37: aload 7
    //   39: invokeinterface 286 1 0
    //   44: iconst_0
    //   45: ifeq +9 -> 54
    //   48: aconst_null
    //   49: invokeinterface 286 1 0
    //   54: return
    //   55: astore 5
    //   57: aload 4
    //   59: ifnull +10 -> 69
    //   62: aload 4
    //   64: invokeinterface 286 1 0
    //   69: aload 5
    //   71: athrow
    //   72: astore 8
    //   74: goto -20 -> 54
    //   77: astore 6
    //   79: goto -10 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	82	0	this	ObjectMapper
    //   0	82	1	paramJsonGenerator	JsonGenerator
    //   0	82	2	paramObject	Object
    //   0	82	3	paramSerializationConfig	SerializationConfig
    //   4	59	4	localCloseable1	Closeable
    //   55	15	5	localObject	Object
    //   77	1	6	localIOException1	IOException
    //   32	6	7	localCloseable2	Closeable
    //   72	1	8	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   6	44	55	finally
    //   48	54	72	java/io/IOException
    //   62	69	77	java/io/IOException
  }
  
  public static List<Module> findModules()
  {
    return findModules(null);
  }
  
  public static List<Module> findModules(ClassLoader paramClassLoader)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramClassLoader == null) {}
    for (ServiceLoader localServiceLoader = ServiceLoader.load(Module.class);; localServiceLoader = ServiceLoader.load(Module.class, paramClassLoader))
    {
      Iterator localIterator = localServiceLoader.iterator();
      while (localIterator.hasNext()) {
        localArrayList.add((Module)localIterator.next());
      }
    }
    return localArrayList;
  }
  
  protected void _checkInvalidCopy(Class<?> paramClass)
  {
    if (getClass() != paramClass) {
      throw new IllegalStateException("Failed copy(): " + getClass().getName() + " (version: " + version() + ") does not override copy(); it has to");
    }
  }
  
  /* Error */
  protected final void _configAndWriteValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 392	com/fasterxml/jackson/databind/ObjectMapper:getSerializationConfig	()Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   4: astore_3
    //   5: aload_3
    //   6: aload_1
    //   7: invokevirtual 396	com/fasterxml/jackson/databind/SerializationConfig:initialize	(Lcom/fasterxml/jackson/core/JsonGenerator;)V
    //   10: aload_3
    //   11: getstatic 399	com/fasterxml/jackson/databind/SerializationFeature:CLOSE_CLOSEABLE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   14: invokevirtual 306	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   17: ifeq +18 -> 35
    //   20: aload_2
    //   21: instanceof 273
    //   24: ifeq +11 -> 35
    //   27: aload_0
    //   28: aload_1
    //   29: aload_2
    //   30: aload_3
    //   31: invokespecial 401	com/fasterxml/jackson/databind/ObjectMapper:_configAndWriteCloseable	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/SerializationConfig;)V
    //   34: return
    //   35: iconst_0
    //   36: istore 4
    //   38: aload_0
    //   39: aload_3
    //   40: invokevirtual 276	com/fasterxml/jackson/databind/ObjectMapper:_serializerProvider	(Lcom/fasterxml/jackson/databind/SerializationConfig;)Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   43: aload_1
    //   44: aload_2
    //   45: invokevirtual 280	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;)V
    //   48: iconst_1
    //   49: istore 4
    //   51: aload_1
    //   52: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   55: iload 4
    //   57: ifne -23 -> 34
    //   60: aload_1
    //   61: getstatic 292	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   64: invokevirtual 296	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   67: pop
    //   68: aload_1
    //   69: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   72: goto -38 -> 34
    //   75: astore 9
    //   77: goto -43 -> 34
    //   80: astore 5
    //   82: iload 4
    //   84: ifne +15 -> 99
    //   87: aload_1
    //   88: getstatic 292	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   91: invokevirtual 296	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   94: pop
    //   95: aload_1
    //   96: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   99: aload 5
    //   101: athrow
    //   102: astore 7
    //   104: goto -5 -> 99
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	ObjectMapper
    //   0	107	1	paramJsonGenerator	JsonGenerator
    //   0	107	2	paramObject	Object
    //   4	36	3	localSerializationConfig	SerializationConfig
    //   36	47	4	i	int
    //   80	20	5	localObject	Object
    //   102	1	7	localIOException1	IOException
    //   75	1	9	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   68	72	75	java/io/IOException
    //   38	55	80	finally
    //   95	99	102	java/io/IOException
  }
  
  /* Error */
  protected final void _configAndWriteValue(JsonGenerator paramJsonGenerator, Object paramObject, Class<?> paramClass)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 392	com/fasterxml/jackson/databind/ObjectMapper:getSerializationConfig	()Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   4: aload_3
    //   5: invokevirtual 406	com/fasterxml/jackson/databind/SerializationConfig:withView	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   8: astore 4
    //   10: aload 4
    //   12: aload_1
    //   13: invokevirtual 396	com/fasterxml/jackson/databind/SerializationConfig:initialize	(Lcom/fasterxml/jackson/core/JsonGenerator;)V
    //   16: aload 4
    //   18: getstatic 399	com/fasterxml/jackson/databind/SerializationFeature:CLOSE_CLOSEABLE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   21: invokevirtual 306	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   24: ifeq +19 -> 43
    //   27: aload_2
    //   28: instanceof 273
    //   31: ifeq +12 -> 43
    //   34: aload_0
    //   35: aload_1
    //   36: aload_2
    //   37: aload 4
    //   39: invokespecial 401	com/fasterxml/jackson/databind/ObjectMapper:_configAndWriteCloseable	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/SerializationConfig;)V
    //   42: return
    //   43: iconst_0
    //   44: istore 5
    //   46: aload_0
    //   47: aload 4
    //   49: invokevirtual 276	com/fasterxml/jackson/databind/ObjectMapper:_serializerProvider	(Lcom/fasterxml/jackson/databind/SerializationConfig;)Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   52: aload_1
    //   53: aload_2
    //   54: invokevirtual 280	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;)V
    //   57: iconst_1
    //   58: istore 5
    //   60: aload_1
    //   61: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   64: iload 5
    //   66: ifne -24 -> 42
    //   69: aload_1
    //   70: getstatic 292	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   73: invokevirtual 296	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   76: pop
    //   77: aload_1
    //   78: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   81: goto -39 -> 42
    //   84: astore 10
    //   86: goto -44 -> 42
    //   89: astore 6
    //   91: iload 5
    //   93: ifne +15 -> 108
    //   96: aload_1
    //   97: getstatic 292	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   100: invokevirtual 296	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   103: pop
    //   104: aload_1
    //   105: invokevirtual 285	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   108: aload 6
    //   110: athrow
    //   111: astore 8
    //   113: goto -5 -> 108
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	this	ObjectMapper
    //   0	116	1	paramJsonGenerator	JsonGenerator
    //   0	116	2	paramObject	Object
    //   0	116	3	paramClass	Class<?>
    //   8	40	4	localSerializationConfig	SerializationConfig
    //   44	48	5	i	int
    //   89	20	6	localObject	Object
    //   111	1	8	localIOException1	IOException
    //   84	1	10	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   77	81	84	java/io/IOException
    //   46	64	89	finally
    //   104	108	111	java/io/IOException
  }
  
  protected Object _convert(Object paramObject, JavaType paramJavaType)
    throws IllegalArgumentException
  {
    Class localClass = paramJavaType.getRawClass();
    if ((localClass != Object.class) && (!paramJavaType.hasGenericTypes()) && (localClass.isAssignableFrom(paramObject.getClass()))) {
      return paramObject;
    }
    TokenBuffer localTokenBuffer = new TokenBuffer(this, false);
    if (isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
      localTokenBuffer = localTokenBuffer.forceUseOfBigDecimal(true);
    }
    for (;;)
    {
      try
      {
        _serializerProvider(getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE)).serializeValue(localTokenBuffer, paramObject);
        JsonParser localJsonParser = localTokenBuffer.asParser();
        DeserializationConfig localDeserializationConfig = getDeserializationConfig();
        JsonToken localJsonToken = _initForReading(localJsonParser);
        Object localObject1;
        if (localJsonToken == JsonToken.VALUE_NULL)
        {
          DefaultDeserializationContext localDefaultDeserializationContext2 = createDeserializationContext(localJsonParser, localDeserializationConfig);
          localObject1 = _findRootDeserializer(localDefaultDeserializationContext2, paramJavaType).getNullValue(localDefaultDeserializationContext2);
          localJsonParser.close();
          paramObject = localObject1;
          break;
        }
        if ((localJsonToken != JsonToken.END_ARRAY) && (localJsonToken != JsonToken.END_OBJECT))
        {
          DefaultDeserializationContext localDefaultDeserializationContext1 = createDeserializationContext(localJsonParser, localDeserializationConfig);
          Object localObject2 = _findRootDeserializer(localDefaultDeserializationContext1, paramJavaType).deserialize(localJsonParser, localDefaultDeserializationContext1);
          localObject1 = localObject2;
        }
        else
        {
          localObject1 = null;
        }
      }
      catch (IOException localIOException)
      {
        throw new IllegalArgumentException(localIOException.getMessage(), localIOException);
      }
    }
  }
  
  @Deprecated
  protected PrettyPrinter _defaultPrettyPrinter()
  {
    return this._serializationConfig.constructDefaultPrettyPrinter();
  }
  
  protected JsonDeserializer<Object> _findRootDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer1 = (JsonDeserializer)this._rootDeserializers.get(paramJavaType);
    if (localJsonDeserializer1 != null) {}
    JsonDeserializer localJsonDeserializer2;
    for (Object localObject = localJsonDeserializer1;; localObject = localJsonDeserializer2)
    {
      return (JsonDeserializer<Object>)localObject;
      localJsonDeserializer2 = paramDeserializationContext.findRootValueDeserializer(paramJavaType);
      if (localJsonDeserializer2 == null) {
        throw new JsonMappingException("Can not find a deserializer for type " + paramJavaType);
      }
      this._rootDeserializers.put(paramJavaType, localJsonDeserializer2);
    }
  }
  
  protected JsonToken _initForReading(JsonParser paramJsonParser)
    throws IOException
  {
    this._deserializationConfig.initialize(paramJsonParser);
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
  
  protected ObjectReader _newReader(DeserializationConfig paramDeserializationConfig)
  {
    return new ObjectReader(this, paramDeserializationConfig);
  }
  
  protected ObjectReader _newReader(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues)
  {
    return new ObjectReader(this, paramDeserializationConfig, paramJavaType, paramObject, paramFormatSchema, paramInjectableValues);
  }
  
  protected ObjectWriter _newWriter(SerializationConfig paramSerializationConfig)
  {
    return new ObjectWriter(this, paramSerializationConfig);
  }
  
  protected ObjectWriter _newWriter(SerializationConfig paramSerializationConfig, FormatSchema paramFormatSchema)
  {
    return new ObjectWriter(this, paramSerializationConfig, paramFormatSchema);
  }
  
  protected ObjectWriter _newWriter(SerializationConfig paramSerializationConfig, JavaType paramJavaType, PrettyPrinter paramPrettyPrinter)
  {
    return new ObjectWriter(this, paramSerializationConfig, paramJavaType, paramPrettyPrinter);
  }
  
  protected Object _readMapAndClose(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    try
    {
      localJsonToken = _initForReading(paramJsonParser);
      if (localJsonToken == JsonToken.VALUE_NULL)
      {
        DefaultDeserializationContext localDefaultDeserializationContext2 = createDeserializationContext(paramJsonParser, getDeserializationConfig());
        localObject2 = _findRootDeserializer(localDefaultDeserializationContext2, paramJavaType).getNullValue(localDefaultDeserializationContext2);
        paramJsonParser.clearCurrentToken();
      }
    }
    finally
    {
      for (;;)
      {
        try
        {
          JsonToken localJsonToken;
          paramJsonParser.close();
          return localObject2;
          if ((localJsonToken != JsonToken.END_ARRAY) && (localJsonToken != JsonToken.END_OBJECT))
          {
            DeserializationConfig localDeserializationConfig = getDeserializationConfig();
            localDefaultDeserializationContext1 = createDeserializationContext(paramJsonParser, localDeserializationConfig);
            localJsonDeserializer = _findRootDeserializer(localDefaultDeserializationContext1, paramJavaType);
            if (localDeserializationConfig.useRootWrapping())
            {
              localObject2 = _unwrapAndDeserialize(paramJsonParser, localDefaultDeserializationContext1, localDeserializationConfig, paramJavaType, localJsonDeserializer);
              localDefaultDeserializationContext1.checkUnresolvedObjectId();
              continue;
              localObject1 = finally;
            }
          }
        }
        catch (IOException localIOException2)
        {
          try
          {
            DefaultDeserializationContext localDefaultDeserializationContext1;
            JsonDeserializer localJsonDeserializer;
            paramJsonParser.close();
            throw ((Throwable)localObject1);
            Object localObject3 = localJsonDeserializer.deserialize(paramJsonParser, localDefaultDeserializationContext1);
            localObject2 = localObject3;
            continue;
            localIOException2 = localIOException2;
          }
          catch (IOException localIOException1)
          {
            continue;
          }
          Object localObject2 = null;
        }
      }
    }
  }
  
  protected Object _readValue(DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    JsonToken localJsonToken = _initForReading(paramJsonParser);
    Object localObject;
    if (localJsonToken == JsonToken.VALUE_NULL)
    {
      DefaultDeserializationContext localDefaultDeserializationContext2 = createDeserializationContext(paramJsonParser, paramDeserializationConfig);
      localObject = _findRootDeserializer(localDefaultDeserializationContext2, paramJavaType).getNullValue(localDefaultDeserializationContext2);
    }
    for (;;)
    {
      paramJsonParser.clearCurrentToken();
      return localObject;
      if ((localJsonToken == JsonToken.END_ARRAY) || (localJsonToken == JsonToken.END_OBJECT))
      {
        localObject = null;
      }
      else
      {
        DefaultDeserializationContext localDefaultDeserializationContext1 = createDeserializationContext(paramJsonParser, paramDeserializationConfig);
        JsonDeserializer localJsonDeserializer = _findRootDeserializer(localDefaultDeserializationContext1, paramJavaType);
        if (paramDeserializationConfig.useRootWrapping()) {
          localObject = _unwrapAndDeserialize(paramJsonParser, localDefaultDeserializationContext1, paramDeserializationConfig, paramJavaType, localJsonDeserializer);
        } else {
          localObject = localJsonDeserializer.deserialize(paramJsonParser, localDefaultDeserializationContext1);
        }
      }
    }
  }
  
  protected DefaultSerializerProvider _serializerProvider(SerializationConfig paramSerializationConfig)
  {
    return this._serializerProvider.createInstance(paramSerializationConfig, this._serializerFactory);
  }
  
  protected Object _unwrapAndDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer)
    throws IOException
  {
    String str1 = paramDeserializationConfig.findRootName(paramJavaType).getSimpleName();
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
    Object localObject = paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() != JsonToken.END_OBJECT) {
      throw JsonMappingException.from(paramJsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + str1 + "'), but " + paramJsonParser.getCurrentToken());
    }
    return localObject;
  }
  
  protected void _verifySchemaType(FormatSchema paramFormatSchema)
  {
    if ((paramFormatSchema != null) && (!this._jsonFactory.canUseSchema(paramFormatSchema))) {
      throw new IllegalArgumentException("Can not use FormatSchema of type " + paramFormatSchema.getClass().getName() + " for format " + this._jsonFactory.getFormatName());
    }
  }
  
  public void acceptJsonFormatVisitor(JavaType paramJavaType, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("type must be provided");
    }
    _serializerProvider(getSerializationConfig()).acceptJsonFormatVisitor(paramJavaType, paramJsonFormatVisitorWrapper);
  }
  
  public void acceptJsonFormatVisitor(Class<?> paramClass, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    acceptJsonFormatVisitor(this._typeFactory.constructType(paramClass), paramJsonFormatVisitorWrapper);
  }
  
  public ObjectMapper addHandler(DeserializationProblemHandler paramDeserializationProblemHandler)
  {
    this._deserializationConfig = this._deserializationConfig.withHandler(paramDeserializationProblemHandler);
    return this;
  }
  
  public ObjectMapper addMixIn(Class<?> paramClass1, Class<?> paramClass2)
  {
    this._mixIns.addLocalDefinition(paramClass1, paramClass2);
    return this;
  }
  
  @Deprecated
  public final void addMixInAnnotations(Class<?> paramClass1, Class<?> paramClass2)
  {
    addMixIn(paramClass1, paramClass2);
  }
  
  public boolean canDeserialize(JavaType paramJavaType)
  {
    return createDeserializationContext(null, getDeserializationConfig()).hasValueDeserializerFor(paramJavaType, null);
  }
  
  public boolean canDeserialize(JavaType paramJavaType, AtomicReference<Throwable> paramAtomicReference)
  {
    return createDeserializationContext(null, getDeserializationConfig()).hasValueDeserializerFor(paramJavaType, paramAtomicReference);
  }
  
  public boolean canSerialize(Class<?> paramClass)
  {
    return _serializerProvider(getSerializationConfig()).hasSerializerFor(paramClass, null);
  }
  
  public boolean canSerialize(Class<?> paramClass, AtomicReference<Throwable> paramAtomicReference)
  {
    return _serializerProvider(getSerializationConfig()).hasSerializerFor(paramClass, paramAtomicReference);
  }
  
  public ObjectMapper clearProblemHandlers()
  {
    this._deserializationConfig = this._deserializationConfig.withNoProblemHandlers();
    return this;
  }
  
  public ObjectMapper configure(JsonGenerator.Feature paramFeature, boolean paramBoolean)
  {
    this._jsonFactory.configure(paramFeature, paramBoolean);
    return this;
  }
  
  public ObjectMapper configure(JsonParser.Feature paramFeature, boolean paramBoolean)
  {
    this._jsonFactory.configure(paramFeature, paramBoolean);
    return this;
  }
  
  public ObjectMapper configure(DeserializationFeature paramDeserializationFeature, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (DeserializationConfig localDeserializationConfig = this._deserializationConfig.with(paramDeserializationFeature);; localDeserializationConfig = this._deserializationConfig.without(paramDeserializationFeature))
    {
      this._deserializationConfig = localDeserializationConfig;
      return this;
    }
  }
  
  public ObjectMapper configure(MapperFeature paramMapperFeature, boolean paramBoolean)
  {
    SerializationConfig localSerializationConfig2;
    DeserializationConfig localDeserializationConfig3;
    MapperFeature[] arrayOfMapperFeature3;
    if (paramBoolean)
    {
      SerializationConfig localSerializationConfig3 = this._serializationConfig;
      MapperFeature[] arrayOfMapperFeature4 = new MapperFeature[1];
      arrayOfMapperFeature4[0] = paramMapperFeature;
      localSerializationConfig2 = localSerializationConfig3.with(arrayOfMapperFeature4);
      this._serializationConfig = localSerializationConfig2;
      if (!paramBoolean) {
        break label101;
      }
      localDeserializationConfig3 = this._deserializationConfig;
      arrayOfMapperFeature3 = new MapperFeature[1];
      arrayOfMapperFeature3[0] = paramMapperFeature;
    }
    label101:
    DeserializationConfig localDeserializationConfig1;
    MapperFeature[] arrayOfMapperFeature2;
    for (DeserializationConfig localDeserializationConfig2 = localDeserializationConfig3.with(arrayOfMapperFeature3);; localDeserializationConfig2 = localDeserializationConfig1.without(arrayOfMapperFeature2))
    {
      this._deserializationConfig = localDeserializationConfig2;
      return this;
      SerializationConfig localSerializationConfig1 = this._serializationConfig;
      MapperFeature[] arrayOfMapperFeature1 = new MapperFeature[1];
      arrayOfMapperFeature1[0] = paramMapperFeature;
      localSerializationConfig2 = localSerializationConfig1.without(arrayOfMapperFeature1);
      break;
      localDeserializationConfig1 = this._deserializationConfig;
      arrayOfMapperFeature2 = new MapperFeature[1];
      arrayOfMapperFeature2[0] = paramMapperFeature;
    }
  }
  
  public ObjectMapper configure(SerializationFeature paramSerializationFeature, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (SerializationConfig localSerializationConfig = this._serializationConfig.with(paramSerializationFeature);; localSerializationConfig = this._serializationConfig.without(paramSerializationFeature))
    {
      this._serializationConfig = localSerializationConfig;
      return this;
    }
  }
  
  public JavaType constructType(Type paramType)
  {
    return this._typeFactory.constructType(paramType);
  }
  
  public <T> T convertValue(Object paramObject, TypeReference<?> paramTypeReference)
    throws IllegalArgumentException
  {
    return (T)convertValue(paramObject, this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T convertValue(Object paramObject, JavaType paramJavaType)
    throws IllegalArgumentException
  {
    if (paramObject == null) {}
    for (Object localObject = null;; localObject = _convert(paramObject, paramJavaType)) {
      return (T)localObject;
    }
  }
  
  public <T> T convertValue(Object paramObject, Class<T> paramClass)
    throws IllegalArgumentException
  {
    if (paramObject == null) {}
    for (Object localObject = null;; localObject = _convert(paramObject, this._typeFactory.constructType(paramClass))) {
      return (T)localObject;
    }
  }
  
  public ObjectMapper copy()
  {
    _checkInvalidCopy(ObjectMapper.class);
    return new ObjectMapper(this);
  }
  
  public ArrayNode createArrayNode()
  {
    return this._deserializationConfig.getNodeFactory().arrayNode();
  }
  
  protected DefaultDeserializationContext createDeserializationContext(JsonParser paramJsonParser, DeserializationConfig paramDeserializationConfig)
  {
    return this._deserializationContext.createInstance(paramDeserializationConfig, paramJsonParser, this._injectableValues);
  }
  
  public ObjectNode createObjectNode()
  {
    return this._deserializationConfig.getNodeFactory().objectNode();
  }
  
  protected ClassIntrospector defaultClassIntrospector()
  {
    return new BasicClassIntrospector();
  }
  
  public ObjectMapper disable(DeserializationFeature paramDeserializationFeature)
  {
    this._deserializationConfig = this._deserializationConfig.without(paramDeserializationFeature);
    return this;
  }
  
  public ObjectMapper disable(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    this._deserializationConfig = this._deserializationConfig.without(paramDeserializationFeature, paramVarArgs);
    return this;
  }
  
  public ObjectMapper disable(SerializationFeature paramSerializationFeature)
  {
    this._serializationConfig = this._serializationConfig.without(paramSerializationFeature);
    return this;
  }
  
  public ObjectMapper disable(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    this._serializationConfig = this._serializationConfig.without(paramSerializationFeature, paramVarArgs);
    return this;
  }
  
  public ObjectMapper disable(JsonGenerator.Feature... paramVarArgs)
  {
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      JsonGenerator.Feature localFeature = paramVarArgs[j];
      this._jsonFactory.disable(localFeature);
    }
    return this;
  }
  
  public ObjectMapper disable(JsonParser.Feature... paramVarArgs)
  {
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      JsonParser.Feature localFeature = paramVarArgs[j];
      this._jsonFactory.disable(localFeature);
    }
    return this;
  }
  
  public ObjectMapper disable(MapperFeature... paramVarArgs)
  {
    this._deserializationConfig = this._deserializationConfig.without(paramVarArgs);
    this._serializationConfig = this._serializationConfig.without(paramVarArgs);
    return this;
  }
  
  public ObjectMapper disableDefaultTyping()
  {
    return setDefaultTyping(null);
  }
  
  public ObjectMapper enable(DeserializationFeature paramDeserializationFeature)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramDeserializationFeature);
    return this;
  }
  
  public ObjectMapper enable(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramDeserializationFeature, paramVarArgs);
    return this;
  }
  
  public ObjectMapper enable(SerializationFeature paramSerializationFeature)
  {
    this._serializationConfig = this._serializationConfig.with(paramSerializationFeature);
    return this;
  }
  
  public ObjectMapper enable(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    this._serializationConfig = this._serializationConfig.with(paramSerializationFeature, paramVarArgs);
    return this;
  }
  
  public ObjectMapper enable(JsonGenerator.Feature... paramVarArgs)
  {
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      JsonGenerator.Feature localFeature = paramVarArgs[j];
      this._jsonFactory.enable(localFeature);
    }
    return this;
  }
  
  public ObjectMapper enable(JsonParser.Feature... paramVarArgs)
  {
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++)
    {
      JsonParser.Feature localFeature = paramVarArgs[j];
      this._jsonFactory.enable(localFeature);
    }
    return this;
  }
  
  public ObjectMapper enable(MapperFeature... paramVarArgs)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramVarArgs);
    this._serializationConfig = this._serializationConfig.with(paramVarArgs);
    return this;
  }
  
  public ObjectMapper enableDefaultTyping()
  {
    return enableDefaultTyping(DefaultTyping.OBJECT_AND_NON_CONCRETE);
  }
  
  public ObjectMapper enableDefaultTyping(DefaultTyping paramDefaultTyping)
  {
    return enableDefaultTyping(paramDefaultTyping, JsonTypeInfo.As.WRAPPER_ARRAY);
  }
  
  public ObjectMapper enableDefaultTyping(DefaultTyping paramDefaultTyping, JsonTypeInfo.As paramAs)
  {
    if (paramAs == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
      throw new IllegalArgumentException("Can not use includeAs of " + paramAs);
    }
    return setDefaultTyping(new DefaultTypeResolverBuilder(paramDefaultTyping).init(JsonTypeInfo.Id.CLASS, null).inclusion(paramAs));
  }
  
  public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping paramDefaultTyping, String paramString)
  {
    return setDefaultTyping(new DefaultTypeResolverBuilder(paramDefaultTyping).init(JsonTypeInfo.Id.CLASS, null).inclusion(JsonTypeInfo.As.PROPERTY).typeProperty(paramString));
  }
  
  public ObjectMapper findAndRegisterModules()
  {
    return registerModules(findModules());
  }
  
  public Class<?> findMixInClassFor(Class<?> paramClass)
  {
    return this._mixIns.findMixInClassFor(paramClass);
  }
  
  @Deprecated
  public JsonSchema generateJsonSchema(Class<?> paramClass)
    throws JsonMappingException
  {
    return _serializerProvider(getSerializationConfig()).generateJsonSchema(paramClass);
  }
  
  public DateFormat getDateFormat()
  {
    return this._serializationConfig.getDateFormat();
  }
  
  public DeserializationConfig getDeserializationConfig()
  {
    return this._deserializationConfig;
  }
  
  public DeserializationContext getDeserializationContext()
  {
    return this._deserializationContext;
  }
  
  public JsonFactory getFactory()
  {
    return this._jsonFactory;
  }
  
  public InjectableValues getInjectableValues()
  {
    return this._injectableValues;
  }
  
  @Deprecated
  public JsonFactory getJsonFactory()
  {
    return getFactory();
  }
  
  public JsonNodeFactory getNodeFactory()
  {
    return this._deserializationConfig.getNodeFactory();
  }
  
  public PropertyNamingStrategy getPropertyNamingStrategy()
  {
    return this._serializationConfig.getPropertyNamingStrategy();
  }
  
  public SerializationConfig getSerializationConfig()
  {
    return this._serializationConfig;
  }
  
  public SerializerFactory getSerializerFactory()
  {
    return this._serializerFactory;
  }
  
  public SerializerProvider getSerializerProvider()
  {
    return this._serializerProvider;
  }
  
  public SubtypeResolver getSubtypeResolver()
  {
    return this._subtypeResolver;
  }
  
  public TypeFactory getTypeFactory()
  {
    return this._typeFactory;
  }
  
  public VisibilityChecker<?> getVisibilityChecker()
  {
    return this._serializationConfig.getDefaultVisibilityChecker();
  }
  
  public boolean isEnabled(JsonFactory.Feature paramFeature)
  {
    return this._jsonFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(JsonGenerator.Feature paramFeature)
  {
    return this._serializationConfig.isEnabled(paramFeature, this._jsonFactory);
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return this._deserializationConfig.isEnabled(paramFeature, this._jsonFactory);
  }
  
  public boolean isEnabled(DeserializationFeature paramDeserializationFeature)
  {
    return this._deserializationConfig.isEnabled(paramDeserializationFeature);
  }
  
  public boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return this._serializationConfig.isEnabled(paramMapperFeature);
  }
  
  public boolean isEnabled(SerializationFeature paramSerializationFeature)
  {
    return this._serializationConfig.isEnabled(paramSerializationFeature);
  }
  
  public int mixInCount()
  {
    return this._mixIns.localSize();
  }
  
  public <T extends TreeNode> T readTree(JsonParser paramJsonParser)
    throws IOException, JsonProcessingException
  {
    DeserializationConfig localDeserializationConfig = getDeserializationConfig();
    if ((paramJsonParser.getCurrentToken() == null) && (paramJsonParser.nextToken() == null)) {}
    Object localObject1;
    for (Object localObject2 = null;; localObject2 = localObject1)
    {
      return (T)localObject2;
      localObject1 = (JsonNode)_readValue(localDeserializationConfig, paramJsonParser, JSON_NODE_TYPE);
      if (localObject1 == null) {
        localObject1 = getNodeFactory().nullNode();
      }
    }
  }
  
  public JsonNode readTree(File paramFile)
    throws IOException, JsonProcessingException
  {
    Object localObject = (JsonNode)_readMapAndClose(this._jsonFactory.createParser(paramFile), JSON_NODE_TYPE);
    if (localObject == null) {
      localObject = NullNode.instance;
    }
    return (JsonNode)localObject;
  }
  
  public JsonNode readTree(InputStream paramInputStream)
    throws IOException, JsonProcessingException
  {
    Object localObject = (JsonNode)_readMapAndClose(this._jsonFactory.createParser(paramInputStream), JSON_NODE_TYPE);
    if (localObject == null) {
      localObject = NullNode.instance;
    }
    return (JsonNode)localObject;
  }
  
  public JsonNode readTree(Reader paramReader)
    throws IOException, JsonProcessingException
  {
    Object localObject = (JsonNode)_readMapAndClose(this._jsonFactory.createParser(paramReader), JSON_NODE_TYPE);
    if (localObject == null) {
      localObject = NullNode.instance;
    }
    return (JsonNode)localObject;
  }
  
  public JsonNode readTree(String paramString)
    throws IOException, JsonProcessingException
  {
    Object localObject = (JsonNode)_readMapAndClose(this._jsonFactory.createParser(paramString), JSON_NODE_TYPE);
    if (localObject == null) {
      localObject = NullNode.instance;
    }
    return (JsonNode)localObject;
  }
  
  public JsonNode readTree(URL paramURL)
    throws IOException, JsonProcessingException
  {
    Object localObject = (JsonNode)_readMapAndClose(this._jsonFactory.createParser(paramURL), JSON_NODE_TYPE);
    if (localObject == null) {
      localObject = NullNode.instance;
    }
    return (JsonNode)localObject;
  }
  
  public JsonNode readTree(byte[] paramArrayOfByte)
    throws IOException, JsonProcessingException
  {
    Object localObject = (JsonNode)_readMapAndClose(this._jsonFactory.createParser(paramArrayOfByte), JSON_NODE_TYPE);
    if (localObject == null) {
      localObject = NullNode.instance;
    }
    return (JsonNode)localObject;
  }
  
  public final <T> T readValue(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readValue(getDeserializationConfig(), paramJsonParser, (JavaType)paramResolvedType);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readValue(getDeserializationConfig(), paramJsonParser, this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T readValue(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readValue(getDeserializationConfig(), paramJsonParser, paramJavaType);
  }
  
  public <T> T readValue(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readValue(getDeserializationConfig(), paramJsonParser, this._typeFactory.constructType(paramClass));
  }
  
  public <T> T readValue(File paramFile, TypeReference paramTypeReference)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramFile), this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T readValue(File paramFile, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramFile), paramJavaType);
  }
  
  public <T> T readValue(File paramFile, Class<T> paramClass)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramFile), this._typeFactory.constructType(paramClass));
  }
  
  public <T> T readValue(InputStream paramInputStream, TypeReference paramTypeReference)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramInputStream), this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T readValue(InputStream paramInputStream, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramInputStream), paramJavaType);
  }
  
  public <T> T readValue(InputStream paramInputStream, Class<T> paramClass)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramInputStream), this._typeFactory.constructType(paramClass));
  }
  
  public <T> T readValue(Reader paramReader, TypeReference paramTypeReference)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramReader), this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T readValue(Reader paramReader, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramReader), paramJavaType);
  }
  
  public <T> T readValue(Reader paramReader, Class<T> paramClass)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramReader), this._typeFactory.constructType(paramClass));
  }
  
  public <T> T readValue(String paramString, TypeReference paramTypeReference)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramString), this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T readValue(String paramString, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramString), paramJavaType);
  }
  
  public <T> T readValue(String paramString, Class<T> paramClass)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramString), this._typeFactory.constructType(paramClass));
  }
  
  public <T> T readValue(URL paramURL, TypeReference paramTypeReference)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramURL), this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T readValue(URL paramURL, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramURL), paramJavaType);
  }
  
  public <T> T readValue(URL paramURL, Class<T> paramClass)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramURL), this._typeFactory.constructType(paramClass));
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2, TypeReference paramTypeReference)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramArrayOfByte, paramInt1, paramInt2), this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramArrayOfByte, paramInt1, paramInt2), paramJavaType);
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Class<T> paramClass)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramArrayOfByte, paramInt1, paramInt2), this._typeFactory.constructType(paramClass));
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, TypeReference paramTypeReference)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramArrayOfByte), this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, JavaType paramJavaType)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramArrayOfByte), paramJavaType);
  }
  
  public <T> T readValue(byte[] paramArrayOfByte, Class<T> paramClass)
    throws IOException, JsonParseException, JsonMappingException
  {
    return (T)_readMapAndClose(this._jsonFactory.createParser(paramArrayOfByte), this._typeFactory.constructType(paramClass));
  }
  
  public <T> MappingIterator<T> readValues(JsonParser paramJsonParser, ResolvedType paramResolvedType)
    throws IOException, JsonProcessingException
  {
    return readValues(paramJsonParser, (JavaType)paramResolvedType);
  }
  
  public <T> MappingIterator<T> readValues(JsonParser paramJsonParser, TypeReference<?> paramTypeReference)
    throws IOException, JsonProcessingException
  {
    return readValues(paramJsonParser, this._typeFactory.constructType(paramTypeReference));
  }
  
  public <T> MappingIterator<T> readValues(JsonParser paramJsonParser, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    DefaultDeserializationContext localDefaultDeserializationContext = createDeserializationContext(paramJsonParser, getDeserializationConfig());
    return new MappingIterator(paramJavaType, paramJsonParser, localDefaultDeserializationContext, _findRootDeserializer(localDefaultDeserializationContext, paramJavaType), false, null);
  }
  
  public <T> MappingIterator<T> readValues(JsonParser paramJsonParser, Class<T> paramClass)
    throws IOException, JsonProcessingException
  {
    return readValues(paramJsonParser, this._typeFactory.constructType(paramClass));
  }
  
  public ObjectReader reader()
  {
    return _newReader(getDeserializationConfig()).with(this._injectableValues);
  }
  
  public ObjectReader reader(Base64Variant paramBase64Variant)
  {
    return _newReader(getDeserializationConfig().with(paramBase64Variant));
  }
  
  public ObjectReader reader(FormatSchema paramFormatSchema)
  {
    _verifySchemaType(paramFormatSchema);
    return _newReader(getDeserializationConfig(), null, null, paramFormatSchema, this._injectableValues);
  }
  
  @Deprecated
  public ObjectReader reader(TypeReference<?> paramTypeReference)
  {
    return _newReader(getDeserializationConfig(), this._typeFactory.constructType(paramTypeReference), null, null, this._injectableValues);
  }
  
  public ObjectReader reader(DeserializationFeature paramDeserializationFeature)
  {
    return _newReader(getDeserializationConfig().with(paramDeserializationFeature));
  }
  
  public ObjectReader reader(DeserializationFeature paramDeserializationFeature, DeserializationFeature... paramVarArgs)
  {
    return _newReader(getDeserializationConfig().with(paramDeserializationFeature, paramVarArgs));
  }
  
  public ObjectReader reader(InjectableValues paramInjectableValues)
  {
    return _newReader(getDeserializationConfig(), null, null, null, paramInjectableValues);
  }
  
  @Deprecated
  public ObjectReader reader(JavaType paramJavaType)
  {
    return _newReader(getDeserializationConfig(), paramJavaType, null, null, this._injectableValues);
  }
  
  public ObjectReader reader(ContextAttributes paramContextAttributes)
  {
    return _newReader(getDeserializationConfig().with(paramContextAttributes));
  }
  
  public ObjectReader reader(JsonNodeFactory paramJsonNodeFactory)
  {
    return _newReader(getDeserializationConfig()).with(paramJsonNodeFactory);
  }
  
  @Deprecated
  public ObjectReader reader(Class<?> paramClass)
  {
    return _newReader(getDeserializationConfig(), this._typeFactory.constructType(paramClass), null, null, this._injectableValues);
  }
  
  public ObjectReader readerFor(TypeReference<?> paramTypeReference)
  {
    return _newReader(getDeserializationConfig(), this._typeFactory.constructType(paramTypeReference), null, null, this._injectableValues);
  }
  
  public ObjectReader readerFor(JavaType paramJavaType)
  {
    return _newReader(getDeserializationConfig(), paramJavaType, null, null, this._injectableValues);
  }
  
  public ObjectReader readerFor(Class<?> paramClass)
  {
    return _newReader(getDeserializationConfig(), this._typeFactory.constructType(paramClass), null, null, this._injectableValues);
  }
  
  public ObjectReader readerForUpdating(Object paramObject)
  {
    JavaType localJavaType = this._typeFactory.constructType(paramObject.getClass());
    return _newReader(getDeserializationConfig(), localJavaType, paramObject, null, this._injectableValues);
  }
  
  public ObjectReader readerWithView(Class<?> paramClass)
  {
    return _newReader(getDeserializationConfig().withView(paramClass));
  }
  
  public ObjectMapper registerModule(Module paramModule)
  {
    if (isEnabled(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS))
    {
      Object localObject = paramModule.getTypeId();
      if (localObject != null)
      {
        if (this._registeredModuleTypes == null) {
          this._registeredModuleTypes = new LinkedHashSet();
        }
        if (this._registeredModuleTypes.add(localObject)) {}
      }
    }
    for (;;)
    {
      return this;
      if (paramModule.getModuleName() == null) {
        throw new IllegalArgumentException("Module without defined name");
      }
      if (paramModule.version() == null) {
        throw new IllegalArgumentException("Module without defined version");
      }
      paramModule.setupModule(new Module.SetupContext()
      {
        public void addAbstractTypeResolver(AbstractTypeResolver paramAnonymousAbstractTypeResolver)
        {
          DeserializerFactory localDeserializerFactory = jdField_this._deserializationContext._factory.withAbstractTypeResolver(paramAnonymousAbstractTypeResolver);
          jdField_this._deserializationContext = jdField_this._deserializationContext.with(localDeserializerFactory);
        }
        
        public void addBeanDeserializerModifier(BeanDeserializerModifier paramAnonymousBeanDeserializerModifier)
        {
          DeserializerFactory localDeserializerFactory = jdField_this._deserializationContext._factory.withDeserializerModifier(paramAnonymousBeanDeserializerModifier);
          jdField_this._deserializationContext = jdField_this._deserializationContext.with(localDeserializerFactory);
        }
        
        public void addBeanSerializerModifier(BeanSerializerModifier paramAnonymousBeanSerializerModifier)
        {
          jdField_this._serializerFactory = jdField_this._serializerFactory.withSerializerModifier(paramAnonymousBeanSerializerModifier);
        }
        
        public void addDeserializationProblemHandler(DeserializationProblemHandler paramAnonymousDeserializationProblemHandler)
        {
          jdField_this.addHandler(paramAnonymousDeserializationProblemHandler);
        }
        
        public void addDeserializers(Deserializers paramAnonymousDeserializers)
        {
          DeserializerFactory localDeserializerFactory = jdField_this._deserializationContext._factory.withAdditionalDeserializers(paramAnonymousDeserializers);
          jdField_this._deserializationContext = jdField_this._deserializationContext.with(localDeserializerFactory);
        }
        
        public void addKeyDeserializers(KeyDeserializers paramAnonymousKeyDeserializers)
        {
          DeserializerFactory localDeserializerFactory = jdField_this._deserializationContext._factory.withAdditionalKeyDeserializers(paramAnonymousKeyDeserializers);
          jdField_this._deserializationContext = jdField_this._deserializationContext.with(localDeserializerFactory);
        }
        
        public void addKeySerializers(Serializers paramAnonymousSerializers)
        {
          jdField_this._serializerFactory = jdField_this._serializerFactory.withAdditionalKeySerializers(paramAnonymousSerializers);
        }
        
        public void addSerializers(Serializers paramAnonymousSerializers)
        {
          jdField_this._serializerFactory = jdField_this._serializerFactory.withAdditionalSerializers(paramAnonymousSerializers);
        }
        
        public void addTypeModifier(TypeModifier paramAnonymousTypeModifier)
        {
          TypeFactory localTypeFactory = jdField_this._typeFactory.withModifier(paramAnonymousTypeModifier);
          jdField_this.setTypeFactory(localTypeFactory);
        }
        
        public void addValueInstantiators(ValueInstantiators paramAnonymousValueInstantiators)
        {
          DeserializerFactory localDeserializerFactory = jdField_this._deserializationContext._factory.withValueInstantiators(paramAnonymousValueInstantiators);
          jdField_this._deserializationContext = jdField_this._deserializationContext.with(localDeserializerFactory);
        }
        
        public void appendAnnotationIntrospector(AnnotationIntrospector paramAnonymousAnnotationIntrospector)
        {
          jdField_this._deserializationConfig = jdField_this._deserializationConfig.withAppendedAnnotationIntrospector(paramAnonymousAnnotationIntrospector);
          jdField_this._serializationConfig = jdField_this._serializationConfig.withAppendedAnnotationIntrospector(paramAnonymousAnnotationIntrospector);
        }
        
        public Version getMapperVersion()
        {
          return ObjectMapper.this.version();
        }
        
        public <C extends ObjectCodec> C getOwner()
        {
          return jdField_this;
        }
        
        public TypeFactory getTypeFactory()
        {
          return ObjectMapper.this._typeFactory;
        }
        
        public void insertAnnotationIntrospector(AnnotationIntrospector paramAnonymousAnnotationIntrospector)
        {
          jdField_this._deserializationConfig = jdField_this._deserializationConfig.withInsertedAnnotationIntrospector(paramAnonymousAnnotationIntrospector);
          jdField_this._serializationConfig = jdField_this._serializationConfig.withInsertedAnnotationIntrospector(paramAnonymousAnnotationIntrospector);
        }
        
        public boolean isEnabled(JsonFactory.Feature paramAnonymousFeature)
        {
          return jdField_this.isEnabled(paramAnonymousFeature);
        }
        
        public boolean isEnabled(JsonGenerator.Feature paramAnonymousFeature)
        {
          return jdField_this.isEnabled(paramAnonymousFeature);
        }
        
        public boolean isEnabled(JsonParser.Feature paramAnonymousFeature)
        {
          return jdField_this.isEnabled(paramAnonymousFeature);
        }
        
        public boolean isEnabled(DeserializationFeature paramAnonymousDeserializationFeature)
        {
          return jdField_this.isEnabled(paramAnonymousDeserializationFeature);
        }
        
        public boolean isEnabled(MapperFeature paramAnonymousMapperFeature)
        {
          return jdField_this.isEnabled(paramAnonymousMapperFeature);
        }
        
        public boolean isEnabled(SerializationFeature paramAnonymousSerializationFeature)
        {
          return jdField_this.isEnabled(paramAnonymousSerializationFeature);
        }
        
        public void registerSubtypes(NamedType... paramAnonymousVarArgs)
        {
          jdField_this.registerSubtypes(paramAnonymousVarArgs);
        }
        
        public void registerSubtypes(Class<?>... paramAnonymousVarArgs)
        {
          jdField_this.registerSubtypes(paramAnonymousVarArgs);
        }
        
        public void setClassIntrospector(ClassIntrospector paramAnonymousClassIntrospector)
        {
          jdField_this._deserializationConfig = jdField_this._deserializationConfig.with(paramAnonymousClassIntrospector);
          jdField_this._serializationConfig = jdField_this._serializationConfig.with(paramAnonymousClassIntrospector);
        }
        
        public void setMixInAnnotations(Class<?> paramAnonymousClass1, Class<?> paramAnonymousClass2)
        {
          jdField_this.addMixIn(paramAnonymousClass1, paramAnonymousClass2);
        }
        
        public void setNamingStrategy(PropertyNamingStrategy paramAnonymousPropertyNamingStrategy)
        {
          jdField_this.setPropertyNamingStrategy(paramAnonymousPropertyNamingStrategy);
        }
      });
    }
  }
  
  public ObjectMapper registerModules(Iterable<Module> paramIterable)
  {
    Iterator localIterator = paramIterable.iterator();
    while (localIterator.hasNext()) {
      registerModule((Module)localIterator.next());
    }
    return this;
  }
  
  public ObjectMapper registerModules(Module... paramVarArgs)
  {
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++) {
      registerModule(paramVarArgs[j]);
    }
    return this;
  }
  
  public void registerSubtypes(NamedType... paramVarArgs)
  {
    getSubtypeResolver().registerSubtypes(paramVarArgs);
  }
  
  public void registerSubtypes(Class<?>... paramVarArgs)
  {
    getSubtypeResolver().registerSubtypes(paramVarArgs);
  }
  
  public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector)
  {
    this._serializationConfig = this._serializationConfig.with(paramAnnotationIntrospector);
    this._deserializationConfig = this._deserializationConfig.with(paramAnnotationIntrospector);
    return this;
  }
  
  public ObjectMapper setAnnotationIntrospectors(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2)
  {
    this._serializationConfig = this._serializationConfig.with(paramAnnotationIntrospector1);
    this._deserializationConfig = this._deserializationConfig.with(paramAnnotationIntrospector2);
    return this;
  }
  
  public ObjectMapper setBase64Variant(Base64Variant paramBase64Variant)
  {
    this._serializationConfig = this._serializationConfig.with(paramBase64Variant);
    this._deserializationConfig = this._deserializationConfig.with(paramBase64Variant);
    return this;
  }
  
  public ObjectMapper setConfig(DeserializationConfig paramDeserializationConfig)
  {
    this._deserializationConfig = paramDeserializationConfig;
    return this;
  }
  
  public ObjectMapper setConfig(SerializationConfig paramSerializationConfig)
  {
    this._serializationConfig = paramSerializationConfig;
    return this;
  }
  
  public ObjectMapper setDateFormat(DateFormat paramDateFormat)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramDateFormat);
    this._serializationConfig = this._serializationConfig.with(paramDateFormat);
    return this;
  }
  
  public ObjectMapper setDefaultPrettyPrinter(PrettyPrinter paramPrettyPrinter)
  {
    this._serializationConfig = this._serializationConfig.withDefaultPrettyPrinter(paramPrettyPrinter);
    return this;
  }
  
  public ObjectMapper setDefaultTyping(TypeResolverBuilder<?> paramTypeResolverBuilder)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramTypeResolverBuilder);
    this._serializationConfig = this._serializationConfig.with(paramTypeResolverBuilder);
    return this;
  }
  
  public ObjectMapper setFilterProvider(FilterProvider paramFilterProvider)
  {
    this._serializationConfig = this._serializationConfig.withFilters(paramFilterProvider);
    return this;
  }
  
  @Deprecated
  public void setFilters(FilterProvider paramFilterProvider)
  {
    this._serializationConfig = this._serializationConfig.withFilters(paramFilterProvider);
  }
  
  public Object setHandlerInstantiator(HandlerInstantiator paramHandlerInstantiator)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramHandlerInstantiator);
    this._serializationConfig = this._serializationConfig.with(paramHandlerInstantiator);
    return this;
  }
  
  public ObjectMapper setInjectableValues(InjectableValues paramInjectableValues)
  {
    this._injectableValues = paramInjectableValues;
    return this;
  }
  
  public ObjectMapper setLocale(Locale paramLocale)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramLocale);
    this._serializationConfig = this._serializationConfig.with(paramLocale);
    return this;
  }
  
  @Deprecated
  public void setMixInAnnotations(Map<Class<?>, Class<?>> paramMap)
  {
    setMixIns(paramMap);
  }
  
  public ObjectMapper setMixInResolver(ClassIntrospector.MixInResolver paramMixInResolver)
  {
    SimpleMixInResolver localSimpleMixInResolver = this._mixIns.withOverrides(paramMixInResolver);
    if (localSimpleMixInResolver != this._mixIns)
    {
      this._mixIns = localSimpleMixInResolver;
      this._deserializationConfig = new DeserializationConfig(this._deserializationConfig, localSimpleMixInResolver);
      this._serializationConfig = new SerializationConfig(this._serializationConfig, localSimpleMixInResolver);
    }
    return this;
  }
  
  public ObjectMapper setMixIns(Map<Class<?>, Class<?>> paramMap)
  {
    this._mixIns.setLocalDefinitions(paramMap);
    return this;
  }
  
  public ObjectMapper setNodeFactory(JsonNodeFactory paramJsonNodeFactory)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramJsonNodeFactory);
    return this;
  }
  
  public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy paramPropertyNamingStrategy)
  {
    this._serializationConfig = this._serializationConfig.with(paramPropertyNamingStrategy);
    this._deserializationConfig = this._deserializationConfig.with(paramPropertyNamingStrategy);
    return this;
  }
  
  public ObjectMapper setSerializationInclusion(JsonInclude.Include paramInclude)
  {
    this._serializationConfig = this._serializationConfig.withSerializationInclusion(paramInclude);
    return this;
  }
  
  public ObjectMapper setSerializerFactory(SerializerFactory paramSerializerFactory)
  {
    this._serializerFactory = paramSerializerFactory;
    return this;
  }
  
  public ObjectMapper setSerializerProvider(DefaultSerializerProvider paramDefaultSerializerProvider)
  {
    this._serializerProvider = paramDefaultSerializerProvider;
    return this;
  }
  
  public ObjectMapper setSubtypeResolver(SubtypeResolver paramSubtypeResolver)
  {
    this._subtypeResolver = paramSubtypeResolver;
    this._deserializationConfig = this._deserializationConfig.with(paramSubtypeResolver);
    this._serializationConfig = this._serializationConfig.with(paramSubtypeResolver);
    return this;
  }
  
  public ObjectMapper setTimeZone(TimeZone paramTimeZone)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramTimeZone);
    this._serializationConfig = this._serializationConfig.with(paramTimeZone);
    return this;
  }
  
  public ObjectMapper setTypeFactory(TypeFactory paramTypeFactory)
  {
    this._typeFactory = paramTypeFactory;
    this._deserializationConfig = this._deserializationConfig.with(paramTypeFactory);
    this._serializationConfig = this._serializationConfig.with(paramTypeFactory);
    return this;
  }
  
  public ObjectMapper setVisibility(PropertyAccessor paramPropertyAccessor, JsonAutoDetect.Visibility paramVisibility)
  {
    this._deserializationConfig = this._deserializationConfig.withVisibility(paramPropertyAccessor, paramVisibility);
    this._serializationConfig = this._serializationConfig.withVisibility(paramPropertyAccessor, paramVisibility);
    return this;
  }
  
  public ObjectMapper setVisibility(VisibilityChecker<?> paramVisibilityChecker)
  {
    this._deserializationConfig = this._deserializationConfig.with(paramVisibilityChecker);
    this._serializationConfig = this._serializationConfig.with(paramVisibilityChecker);
    return this;
  }
  
  @Deprecated
  public void setVisibilityChecker(VisibilityChecker<?> paramVisibilityChecker)
  {
    setVisibility(paramVisibilityChecker);
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
      if ((paramClass == Object.class) || (!paramClass.isAssignableFrom(paramTreeNode.getClass())))
      {
        Object localObject = readValue(treeAsTokens(paramTreeNode), paramClass);
        paramTreeNode = (TreeNode)localObject;
      }
    }
    catch (JsonProcessingException localJsonProcessingException)
    {
      throw localJsonProcessingException;
    }
    catch (IOException localIOException)
    {
      throw new IllegalArgumentException(localIOException.getMessage(), localIOException);
    }
    return paramTreeNode;
  }
  
  public <T extends JsonNode> T valueToTree(Object paramObject)
    throws IllegalArgumentException
  {
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    for (;;)
    {
      return (T)localObject;
      TokenBuffer localTokenBuffer = new TokenBuffer(this, false);
      if (isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
        localTokenBuffer = localTokenBuffer.forceUseOfBigDecimal(true);
      }
      try
      {
        writeValue(localTokenBuffer, paramObject);
        JsonParser localJsonParser = localTokenBuffer.asParser();
        localObject = (JsonNode)readTree(localJsonParser);
        localJsonParser.close();
      }
      catch (IOException localIOException)
      {
        throw new IllegalArgumentException(localIOException.getMessage(), localIOException);
      }
    }
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public void writeTree(JsonGenerator paramJsonGenerator, TreeNode paramTreeNode)
    throws IOException, JsonProcessingException
  {
    SerializationConfig localSerializationConfig = getSerializationConfig();
    _serializerProvider(localSerializationConfig).serializeValue(paramJsonGenerator, paramTreeNode);
    if (localSerializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
      paramJsonGenerator.flush();
    }
  }
  
  public void writeTree(JsonGenerator paramJsonGenerator, JsonNode paramJsonNode)
    throws IOException, JsonProcessingException
  {
    SerializationConfig localSerializationConfig = getSerializationConfig();
    _serializerProvider(localSerializationConfig).serializeValue(paramJsonGenerator, paramJsonNode);
    if (localSerializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
      paramJsonGenerator.flush();
    }
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    SerializationConfig localSerializationConfig = getSerializationConfig();
    if ((localSerializationConfig.isEnabled(SerializationFeature.INDENT_OUTPUT)) && (paramJsonGenerator.getPrettyPrinter() == null)) {
      paramJsonGenerator.setPrettyPrinter(localSerializationConfig.constructDefaultPrettyPrinter());
    }
    if ((localSerializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE)) && ((paramObject instanceof Closeable))) {
      _writeCloseableValue(paramJsonGenerator, paramObject, localSerializationConfig);
    }
    for (;;)
    {
      return;
      _serializerProvider(localSerializationConfig).serializeValue(paramJsonGenerator, paramObject);
      if (localSerializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
        paramJsonGenerator.flush();
      }
    }
  }
  
  public void writeValue(File paramFile, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._jsonFactory.createGenerator(paramFile, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(OutputStream paramOutputStream, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._jsonFactory.createGenerator(paramOutputStream, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(Writer paramWriter, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._jsonFactory.createGenerator(paramWriter), paramObject);
  }
  
  public byte[] writeValueAsBytes(Object paramObject)
    throws JsonProcessingException
  {
    ByteArrayBuilder localByteArrayBuilder = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
    try
    {
      _configAndWriteValue(this._jsonFactory.createGenerator(localByteArrayBuilder, JsonEncoding.UTF8), paramObject);
      byte[] arrayOfByte = localByteArrayBuilder.toByteArray();
      localByteArrayBuilder.release();
      return arrayOfByte;
    }
    catch (JsonProcessingException localJsonProcessingException)
    {
      throw localJsonProcessingException;
    }
    catch (IOException localIOException)
    {
      throw JsonMappingException.fromUnexpectedIOE(localIOException);
    }
  }
  
  public String writeValueAsString(Object paramObject)
    throws JsonProcessingException
  {
    SegmentedStringWriter localSegmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
    try
    {
      _configAndWriteValue(this._jsonFactory.createGenerator(localSegmentedStringWriter), paramObject);
      return localSegmentedStringWriter.getAndClear();
    }
    catch (JsonProcessingException localJsonProcessingException)
    {
      throw localJsonProcessingException;
    }
    catch (IOException localIOException)
    {
      throw JsonMappingException.fromUnexpectedIOE(localIOException);
    }
  }
  
  public ObjectWriter writer()
  {
    return _newWriter(getSerializationConfig());
  }
  
  public ObjectWriter writer(Base64Variant paramBase64Variant)
  {
    return _newWriter(getSerializationConfig().with(paramBase64Variant));
  }
  
  public ObjectWriter writer(FormatSchema paramFormatSchema)
  {
    _verifySchemaType(paramFormatSchema);
    return _newWriter(getSerializationConfig(), paramFormatSchema);
  }
  
  public ObjectWriter writer(PrettyPrinter paramPrettyPrinter)
  {
    if (paramPrettyPrinter == null) {
      paramPrettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
    }
    return _newWriter(getSerializationConfig(), null, paramPrettyPrinter);
  }
  
  public ObjectWriter writer(CharacterEscapes paramCharacterEscapes)
  {
    return _newWriter(getSerializationConfig()).with(paramCharacterEscapes);
  }
  
  public ObjectWriter writer(SerializationFeature paramSerializationFeature)
  {
    return _newWriter(getSerializationConfig().with(paramSerializationFeature));
  }
  
  public ObjectWriter writer(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    return _newWriter(getSerializationConfig().with(paramSerializationFeature, paramVarArgs));
  }
  
  public ObjectWriter writer(ContextAttributes paramContextAttributes)
  {
    return _newWriter(getSerializationConfig().with(paramContextAttributes));
  }
  
  public ObjectWriter writer(FilterProvider paramFilterProvider)
  {
    return _newWriter(getSerializationConfig().withFilters(paramFilterProvider));
  }
  
  public ObjectWriter writer(DateFormat paramDateFormat)
  {
    return _newWriter(getSerializationConfig().with(paramDateFormat));
  }
  
  public ObjectWriter writerFor(TypeReference<?> paramTypeReference)
  {
    SerializationConfig localSerializationConfig = getSerializationConfig();
    if (paramTypeReference == null) {}
    for (JavaType localJavaType = null;; localJavaType = this._typeFactory.constructType(paramTypeReference)) {
      return _newWriter(localSerializationConfig, localJavaType, null);
    }
  }
  
  public ObjectWriter writerFor(JavaType paramJavaType)
  {
    return _newWriter(getSerializationConfig(), paramJavaType, null);
  }
  
  public ObjectWriter writerFor(Class<?> paramClass)
  {
    SerializationConfig localSerializationConfig = getSerializationConfig();
    if (paramClass == null) {}
    for (JavaType localJavaType = null;; localJavaType = this._typeFactory.constructType(paramClass)) {
      return _newWriter(localSerializationConfig, localJavaType, null);
    }
  }
  
  public ObjectWriter writerWithDefaultPrettyPrinter()
  {
    SerializationConfig localSerializationConfig = getSerializationConfig();
    return _newWriter(localSerializationConfig, null, localSerializationConfig.getDefaultPrettyPrinter());
  }
  
  @Deprecated
  public ObjectWriter writerWithType(TypeReference<?> paramTypeReference)
  {
    SerializationConfig localSerializationConfig = getSerializationConfig();
    if (paramTypeReference == null) {}
    for (JavaType localJavaType = null;; localJavaType = this._typeFactory.constructType(paramTypeReference)) {
      return _newWriter(localSerializationConfig, localJavaType, null);
    }
  }
  
  @Deprecated
  public ObjectWriter writerWithType(JavaType paramJavaType)
  {
    return _newWriter(getSerializationConfig(), paramJavaType, null);
  }
  
  @Deprecated
  public ObjectWriter writerWithType(Class<?> paramClass)
  {
    SerializationConfig localSerializationConfig = getSerializationConfig();
    if (paramClass == null) {}
    for (JavaType localJavaType = null;; localJavaType = this._typeFactory.constructType(paramClass)) {
      return _newWriter(localSerializationConfig, localJavaType, null);
    }
  }
  
  public ObjectWriter writerWithView(Class<?> paramClass)
  {
    return _newWriter(getSerializationConfig().withView(paramClass));
  }
  
  public static class DefaultTypeResolverBuilder
    extends StdTypeResolverBuilder
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected final ObjectMapper.DefaultTyping _appliesFor;
    
    public DefaultTypeResolverBuilder(ObjectMapper.DefaultTyping paramDefaultTyping)
    {
      this._appliesFor = paramDefaultTyping;
    }
    
    public TypeDeserializer buildTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection)
    {
      if (useForType(paramJavaType)) {}
      for (TypeDeserializer localTypeDeserializer = super.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramCollection);; localTypeDeserializer = null) {
        return localTypeDeserializer;
      }
    }
    
    public TypeSerializer buildTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection)
    {
      if (useForType(paramJavaType)) {}
      for (TypeSerializer localTypeSerializer = super.buildTypeSerializer(paramSerializationConfig, paramJavaType, paramCollection);; localTypeSerializer = null) {
        return localTypeSerializer;
      }
    }
    
    public boolean useForType(JavaType paramJavaType)
    {
      boolean bool1 = true;
      boolean bool2 = false;
      switch (ObjectMapper.2.$SwitchMap$com$fasterxml$jackson$databind$ObjectMapper$DefaultTyping[this._appliesFor.ordinal()])
      {
      default: 
      case 1: 
      case 2: 
        for (bool2 = paramJavaType.isJavaLangObject();; bool2 = bool1) {
          do
          {
            return bool2;
            while (paramJavaType.isArrayType()) {
              paramJavaType = paramJavaType.getContentType();
            }
          } while ((!paramJavaType.isJavaLangObject()) && ((paramJavaType.isConcrete()) || (TreeNode.class.isAssignableFrom(paramJavaType.getRawClass()))));
        }
      }
      while (paramJavaType.isArrayType()) {
        paramJavaType = paramJavaType.getContentType();
      }
      if ((!paramJavaType.isFinal()) && (!TreeNode.class.isAssignableFrom(paramJavaType.getRawClass()))) {}
      for (;;)
      {
        bool2 = bool1;
        break;
        bool1 = false;
      }
    }
  }
  
  public static enum DefaultTyping
  {
    static
    {
      NON_CONCRETE_AND_ARRAYS = new DefaultTyping("NON_CONCRETE_AND_ARRAYS", 2);
      NON_FINAL = new DefaultTyping("NON_FINAL", 3);
      DefaultTyping[] arrayOfDefaultTyping = new DefaultTyping[4];
      arrayOfDefaultTyping[0] = JAVA_LANG_OBJECT;
      arrayOfDefaultTyping[1] = OBJECT_AND_NON_CONCRETE;
      arrayOfDefaultTyping[2] = NON_CONCRETE_AND_ARRAYS;
      arrayOfDefaultTyping[3] = NON_FINAL;
      $VALUES = arrayOfDefaultTyping;
    }
    
    private DefaultTyping() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ObjectMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */