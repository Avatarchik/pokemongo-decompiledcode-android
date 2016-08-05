package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.Instantiatable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public class ObjectWriter
  implements Versioned, Serializable
{
  protected static final PrettyPrinter NULL_PRETTY_PRINTER = new MinimalPrettyPrinter();
  private static final long serialVersionUID = 1L;
  protected final SerializationConfig _config;
  protected final JsonFactory _generatorFactory;
  protected final GeneratorSettings _generatorSettings;
  protected final Prefetch _prefetch;
  protected final SerializerFactory _serializerFactory;
  protected final DefaultSerializerProvider _serializerProvider;
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._generatorFactory = paramObjectMapper._jsonFactory;
    this._generatorSettings = GeneratorSettings.empty;
    this._prefetch = Prefetch.empty;
  }
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig, FormatSchema paramFormatSchema)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._generatorFactory = paramObjectMapper._jsonFactory;
    if (paramFormatSchema == null) {}
    for (GeneratorSettings localGeneratorSettings = GeneratorSettings.empty;; localGeneratorSettings = new GeneratorSettings(null, paramFormatSchema, null, null))
    {
      this._generatorSettings = localGeneratorSettings;
      this._prefetch = Prefetch.empty;
      return;
    }
  }
  
  protected ObjectWriter(ObjectMapper paramObjectMapper, SerializationConfig paramSerializationConfig, JavaType paramJavaType, PrettyPrinter paramPrettyPrinter)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectMapper._serializerProvider;
    this._serializerFactory = paramObjectMapper._serializerFactory;
    this._generatorFactory = paramObjectMapper._jsonFactory;
    GeneratorSettings localGeneratorSettings;
    if (paramPrettyPrinter == null)
    {
      localGeneratorSettings = GeneratorSettings.empty;
      this._generatorSettings = localGeneratorSettings;
      if ((paramJavaType != null) && (!paramJavaType.hasRawClass(Object.class))) {
        break label87;
      }
    }
    label87:
    JavaType localJavaType;
    for (this._prefetch = Prefetch.empty;; this._prefetch = Prefetch.empty.forRootType(this, localJavaType))
    {
      return;
      localGeneratorSettings = new GeneratorSettings(paramPrettyPrinter, null, null, null);
      break;
      localJavaType = paramJavaType.withStaticTyping();
    }
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, JsonFactory paramJsonFactory)
  {
    this._config = paramObjectWriter._config.with(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, paramJsonFactory.requiresPropertyOrdering());
    this._serializerProvider = paramObjectWriter._serializerProvider;
    this._serializerFactory = paramObjectWriter._serializerFactory;
    this._generatorFactory = paramObjectWriter._generatorFactory;
    this._generatorSettings = paramObjectWriter._generatorSettings;
    this._prefetch = paramObjectWriter._prefetch;
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectWriter._serializerProvider;
    this._serializerFactory = paramObjectWriter._serializerFactory;
    this._generatorFactory = paramObjectWriter._generatorFactory;
    this._generatorSettings = paramObjectWriter._generatorSettings;
    this._prefetch = paramObjectWriter._prefetch;
  }
  
  protected ObjectWriter(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig, GeneratorSettings paramGeneratorSettings, Prefetch paramPrefetch)
  {
    this._config = paramSerializationConfig;
    this._serializerProvider = paramObjectWriter._serializerProvider;
    this._serializerFactory = paramObjectWriter._serializerFactory;
    this._generatorFactory = paramObjectWriter._generatorFactory;
    this._generatorSettings = paramGeneratorSettings;
    this._prefetch = paramPrefetch;
  }
  
  /* Error */
  private final void _writeCloseable(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException
  {
    // Byte code:
    //   0: aload_2
    //   1: checkcast 113	java/io/Closeable
    //   4: astore_3
    //   5: aload_0
    //   6: getfield 67	com/fasterxml/jackson/databind/ObjectWriter:_prefetch	Lcom/fasterxml/jackson/databind/ObjectWriter$Prefetch;
    //   9: aload_1
    //   10: aload_2
    //   11: aload_0
    //   12: invokevirtual 116	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	()Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   15: invokevirtual 120	com/fasterxml/jackson/databind/ObjectWriter$Prefetch:serialize	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;)V
    //   18: aload_1
    //   19: astore 8
    //   21: aconst_null
    //   22: astore_1
    //   23: aload 8
    //   25: invokevirtual 125	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   28: aload_3
    //   29: astore 9
    //   31: aconst_null
    //   32: astore_3
    //   33: aload 9
    //   35: invokeinterface 126 1 0
    //   40: iconst_0
    //   41: ifeq +15 -> 56
    //   44: aconst_null
    //   45: getstatic 132	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   48: invokevirtual 136	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   51: pop
    //   52: aconst_null
    //   53: invokevirtual 125	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   56: iconst_0
    //   57: ifeq +9 -> 66
    //   60: aconst_null
    //   61: invokeinterface 126 1 0
    //   66: return
    //   67: astore 4
    //   69: aload_1
    //   70: ifnull +15 -> 85
    //   73: aload_1
    //   74: getstatic 132	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   77: invokevirtual 136	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   80: pop
    //   81: aload_1
    //   82: invokevirtual 125	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   85: aload_3
    //   86: ifnull +9 -> 95
    //   89: aload_3
    //   90: invokeinterface 126 1 0
    //   95: aload 4
    //   97: athrow
    //   98: astore 12
    //   100: goto -44 -> 56
    //   103: astore 10
    //   105: goto -39 -> 66
    //   108: astore 7
    //   110: goto -25 -> 85
    //   113: astore 5
    //   115: goto -20 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	118	0	this	ObjectWriter
    //   0	118	1	paramJsonGenerator	JsonGenerator
    //   0	118	2	paramObject	Object
    //   4	86	3	localCloseable1	java.io.Closeable
    //   67	29	4	localObject	Object
    //   113	1	5	localIOException1	IOException
    //   108	1	7	localIOException2	IOException
    //   19	5	8	localJsonGenerator	JsonGenerator
    //   29	5	9	localCloseable2	java.io.Closeable
    //   103	1	10	localIOException3	IOException
    //   98	1	12	localIOException4	IOException
    // Exception table:
    //   from	to	target	type
    //   5	40	67	finally
    //   52	56	98	java/io/IOException
    //   60	66	103	java/io/IOException
    //   81	85	108	java/io/IOException
    //   89	95	113	java/io/IOException
  }
  
  /* Error */
  protected final void _configAndWriteValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 141	com/fasterxml/jackson/databind/ObjectWriter:_configureGenerator	(Lcom/fasterxml/jackson/core/JsonGenerator;)V
    //   5: aload_0
    //   6: getfield 45	com/fasterxml/jackson/databind/ObjectWriter:_config	Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   9: getstatic 147	com/fasterxml/jackson/databind/SerializationFeature:CLOSE_CLOSEABLE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   12: invokevirtual 151	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   15: ifeq +17 -> 32
    //   18: aload_2
    //   19: instanceof 113
    //   22: ifeq +10 -> 32
    //   25: aload_0
    //   26: aload_1
    //   27: aload_2
    //   28: invokespecial 153	com/fasterxml/jackson/databind/ObjectWriter:_writeCloseable	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;)V
    //   31: return
    //   32: iconst_0
    //   33: istore_3
    //   34: aload_0
    //   35: getfield 67	com/fasterxml/jackson/databind/ObjectWriter:_prefetch	Lcom/fasterxml/jackson/databind/ObjectWriter$Prefetch;
    //   38: aload_1
    //   39: aload_2
    //   40: aload_0
    //   41: invokevirtual 116	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	()Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   44: invokevirtual 120	com/fasterxml/jackson/databind/ObjectWriter$Prefetch:serialize	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;)V
    //   47: iconst_1
    //   48: istore_3
    //   49: aload_1
    //   50: invokevirtual 125	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   53: iload_3
    //   54: ifne -23 -> 31
    //   57: aload_1
    //   58: getstatic 132	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   61: invokevirtual 136	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   64: pop
    //   65: aload_1
    //   66: invokevirtual 125	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   69: goto -38 -> 31
    //   72: astore 8
    //   74: goto -43 -> 31
    //   77: astore 4
    //   79: iload_3
    //   80: ifne +15 -> 95
    //   83: aload_1
    //   84: getstatic 132	com/fasterxml/jackson/core/JsonGenerator$Feature:AUTO_CLOSE_JSON_CONTENT	Lcom/fasterxml/jackson/core/JsonGenerator$Feature;
    //   87: invokevirtual 136	com/fasterxml/jackson/core/JsonGenerator:disable	(Lcom/fasterxml/jackson/core/JsonGenerator$Feature;)Lcom/fasterxml/jackson/core/JsonGenerator;
    //   90: pop
    //   91: aload_1
    //   92: invokevirtual 125	com/fasterxml/jackson/core/JsonGenerator:close	()V
    //   95: aload 4
    //   97: athrow
    //   98: astore 6
    //   100: goto -5 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	ObjectWriter
    //   0	103	1	paramJsonGenerator	JsonGenerator
    //   0	103	2	paramObject	Object
    //   33	47	3	i	int
    //   77	19	4	localObject	Object
    //   98	1	6	localIOException1	IOException
    //   72	1	8	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   65	69	72	java/io/IOException
    //   34	53	77	finally
    //   91	95	98	java/io/IOException
  }
  
  protected final void _configureGenerator(JsonGenerator paramJsonGenerator)
  {
    this._config.initialize(paramJsonGenerator);
    this._generatorSettings.initialize(paramJsonGenerator);
  }
  
  protected ObjectWriter _new(GeneratorSettings paramGeneratorSettings, Prefetch paramPrefetch)
  {
    return new ObjectWriter(this, this._config, paramGeneratorSettings, paramPrefetch);
  }
  
  protected ObjectWriter _new(ObjectWriter paramObjectWriter, JsonFactory paramJsonFactory)
  {
    return new ObjectWriter(paramObjectWriter, paramJsonFactory);
  }
  
  protected ObjectWriter _new(ObjectWriter paramObjectWriter, SerializationConfig paramSerializationConfig)
  {
    return new ObjectWriter(paramObjectWriter, paramSerializationConfig);
  }
  
  protected SequenceWriter _newSequenceWriter(boolean paramBoolean1, JsonGenerator paramJsonGenerator, boolean paramBoolean2)
    throws IOException
  {
    _configureGenerator(paramJsonGenerator);
    return new SequenceWriter(_serializerProvider(), paramJsonGenerator, paramBoolean2, this._prefetch).init(paramBoolean1);
  }
  
  protected DefaultSerializerProvider _serializerProvider()
  {
    return this._serializerProvider.createInstance(this._config, this._serializerFactory);
  }
  
  protected void _verifySchemaType(FormatSchema paramFormatSchema)
  {
    if ((paramFormatSchema != null) && (!this._generatorFactory.canUseSchema(paramFormatSchema))) {
      throw new IllegalArgumentException("Can not use FormatSchema of type " + paramFormatSchema.getClass().getName() + " for format " + this._generatorFactory.getFormatName());
    }
  }
  
  public void acceptJsonFormatVisitor(JavaType paramJavaType, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("type must be provided");
    }
    _serializerProvider().acceptJsonFormatVisitor(paramJavaType, paramJsonFormatVisitorWrapper);
  }
  
  public void acceptJsonFormatVisitor(Class<?> paramClass, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    acceptJsonFormatVisitor(this._config.constructType(paramClass), paramJsonFormatVisitorWrapper);
  }
  
  public boolean canSerialize(Class<?> paramClass)
  {
    return _serializerProvider().hasSerializerFor(paramClass, null);
  }
  
  public boolean canSerialize(Class<?> paramClass, AtomicReference<Throwable> paramAtomicReference)
  {
    return _serializerProvider().hasSerializerFor(paramClass, paramAtomicReference);
  }
  
  public ObjectWriter forType(TypeReference<?> paramTypeReference)
  {
    return forType(this._config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  public ObjectWriter forType(JavaType paramJavaType)
  {
    Prefetch localPrefetch = this._prefetch.forRootType(this, paramJavaType);
    if (localPrefetch == this._prefetch) {}
    for (;;)
    {
      return this;
      this = _new(this._generatorSettings, localPrefetch);
    }
  }
  
  public ObjectWriter forType(Class<?> paramClass)
  {
    if (paramClass == Object.class) {}
    for (ObjectWriter localObjectWriter = forType((JavaType)null);; localObjectWriter = forType(this._config.constructType(paramClass))) {
      return localObjectWriter;
    }
  }
  
  public ContextAttributes getAttributes()
  {
    return this._config.getAttributes();
  }
  
  public SerializationConfig getConfig()
  {
    return this._config;
  }
  
  public JsonFactory getFactory()
  {
    return this._generatorFactory;
  }
  
  public TypeFactory getTypeFactory()
  {
    return this._config.getTypeFactory();
  }
  
  public boolean hasPrefetchedSerializer()
  {
    return this._prefetch.hasSerializer();
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return this._generatorFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return this._config.isEnabled(paramMapperFeature);
  }
  
  public boolean isEnabled(SerializationFeature paramSerializationFeature)
  {
    return this._config.isEnabled(paramSerializationFeature);
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public ObjectWriter with(Base64Variant paramBase64Variant)
  {
    SerializationConfig localSerializationConfig = this._config.with(paramBase64Variant);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter with(FormatSchema paramFormatSchema)
  {
    GeneratorSettings localGeneratorSettings = this._generatorSettings.with(paramFormatSchema);
    if (localGeneratorSettings == this._generatorSettings) {}
    for (;;)
    {
      return this;
      _verifySchemaType(paramFormatSchema);
      this = _new(localGeneratorSettings, this._prefetch);
    }
  }
  
  public ObjectWriter with(JsonFactory paramJsonFactory)
  {
    if (paramJsonFactory == this._generatorFactory) {}
    for (;;)
    {
      return this;
      this = _new(this, paramJsonFactory);
    }
  }
  
  public ObjectWriter with(JsonGenerator.Feature paramFeature)
  {
    SerializationConfig localSerializationConfig = this._config.with(paramFeature);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter with(PrettyPrinter paramPrettyPrinter)
  {
    GeneratorSettings localGeneratorSettings = this._generatorSettings.with(paramPrettyPrinter);
    if (localGeneratorSettings == this._generatorSettings) {}
    for (;;)
    {
      return this;
      this = _new(localGeneratorSettings, this._prefetch);
    }
  }
  
  public ObjectWriter with(CharacterEscapes paramCharacterEscapes)
  {
    GeneratorSettings localGeneratorSettings = this._generatorSettings.with(paramCharacterEscapes);
    if (localGeneratorSettings == this._generatorSettings) {}
    for (;;)
    {
      return this;
      this = _new(localGeneratorSettings, this._prefetch);
    }
  }
  
  public ObjectWriter with(SerializationFeature paramSerializationFeature)
  {
    SerializationConfig localSerializationConfig = this._config.with(paramSerializationFeature);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter with(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    SerializationConfig localSerializationConfig = this._config.with(paramSerializationFeature, paramVarArgs);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter with(ContextAttributes paramContextAttributes)
  {
    SerializationConfig localSerializationConfig = this._config.with(paramContextAttributes);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter with(FilterProvider paramFilterProvider)
  {
    if (paramFilterProvider == this._config.getFilterProvider()) {}
    for (;;)
    {
      return this;
      this = _new(this, this._config.withFilters(paramFilterProvider));
    }
  }
  
  public ObjectWriter with(DateFormat paramDateFormat)
  {
    SerializationConfig localSerializationConfig = this._config.with(paramDateFormat);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter with(Locale paramLocale)
  {
    SerializationConfig localSerializationConfig = this._config.with(paramLocale);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter with(TimeZone paramTimeZone)
  {
    SerializationConfig localSerializationConfig = this._config.with(paramTimeZone);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withAttribute(Object paramObject1, Object paramObject2)
  {
    SerializationConfig localSerializationConfig = (SerializationConfig)this._config.withAttribute(paramObject1, paramObject2);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withAttributes(Map<Object, Object> paramMap)
  {
    SerializationConfig localSerializationConfig = (SerializationConfig)this._config.withAttributes(paramMap);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withDefaultPrettyPrinter()
  {
    return with(this._config.getDefaultPrettyPrinter());
  }
  
  public ObjectWriter withFeatures(JsonGenerator.Feature... paramVarArgs)
  {
    SerializationConfig localSerializationConfig = this._config.withFeatures(paramVarArgs);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withFeatures(SerializationFeature... paramVarArgs)
  {
    SerializationConfig localSerializationConfig = this._config.withFeatures(paramVarArgs);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withRootName(PropertyName paramPropertyName)
  {
    SerializationConfig localSerializationConfig = this._config.withRootName(paramPropertyName);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withRootName(String paramString)
  {
    SerializationConfig localSerializationConfig = (SerializationConfig)this._config.withRootName(paramString);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withRootValueSeparator(SerializableString paramSerializableString)
  {
    GeneratorSettings localGeneratorSettings = this._generatorSettings.withRootValueSeparator(paramSerializableString);
    if (localGeneratorSettings == this._generatorSettings) {}
    for (;;)
    {
      return this;
      this = _new(localGeneratorSettings, this._prefetch);
    }
  }
  
  public ObjectWriter withRootValueSeparator(String paramString)
  {
    GeneratorSettings localGeneratorSettings = this._generatorSettings.withRootValueSeparator(paramString);
    if (localGeneratorSettings == this._generatorSettings) {}
    for (;;)
    {
      return this;
      this = _new(localGeneratorSettings, this._prefetch);
    }
  }
  
  @Deprecated
  public ObjectWriter withSchema(FormatSchema paramFormatSchema)
  {
    return with(paramFormatSchema);
  }
  
  @Deprecated
  public ObjectWriter withType(TypeReference<?> paramTypeReference)
  {
    return forType(paramTypeReference);
  }
  
  @Deprecated
  public ObjectWriter withType(JavaType paramJavaType)
  {
    return forType(paramJavaType);
  }
  
  @Deprecated
  public ObjectWriter withType(Class<?> paramClass)
  {
    return forType(paramClass);
  }
  
  public ObjectWriter withView(Class<?> paramClass)
  {
    SerializationConfig localSerializationConfig = this._config.withView(paramClass);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter without(JsonGenerator.Feature paramFeature)
  {
    SerializationConfig localSerializationConfig = this._config.without(paramFeature);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter without(SerializationFeature paramSerializationFeature)
  {
    SerializationConfig localSerializationConfig = this._config.without(paramSerializationFeature);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter without(SerializationFeature paramSerializationFeature, SerializationFeature... paramVarArgs)
  {
    SerializationConfig localSerializationConfig = this._config.without(paramSerializationFeature, paramVarArgs);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withoutAttribute(Object paramObject)
  {
    SerializationConfig localSerializationConfig = (SerializationConfig)this._config.withoutAttribute(paramObject);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withoutFeatures(JsonGenerator.Feature... paramVarArgs)
  {
    SerializationConfig localSerializationConfig = this._config.withoutFeatures(paramVarArgs);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withoutFeatures(SerializationFeature... paramVarArgs)
  {
    SerializationConfig localSerializationConfig = this._config.withoutFeatures(paramVarArgs);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  public ObjectWriter withoutRootName()
  {
    SerializationConfig localSerializationConfig = this._config.withRootName(PropertyName.NO_NAME);
    if (localSerializationConfig == this._config) {}
    for (;;)
    {
      return this;
      this = _new(this, localSerializationConfig);
    }
  }
  
  /* Error */
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 141	com/fasterxml/jackson/databind/ObjectWriter:_configureGenerator	(Lcom/fasterxml/jackson/core/JsonGenerator;)V
    //   5: aload_0
    //   6: getfield 45	com/fasterxml/jackson/databind/ObjectWriter:_config	Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   9: getstatic 147	com/fasterxml/jackson/databind/SerializationFeature:CLOSE_CLOSEABLE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   12: invokevirtual 151	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   15: ifeq +83 -> 98
    //   18: aload_2
    //   19: instanceof 113
    //   22: ifeq +76 -> 98
    //   25: aload_2
    //   26: checkcast 113	java/io/Closeable
    //   29: astore_3
    //   30: aload_0
    //   31: getfield 67	com/fasterxml/jackson/databind/ObjectWriter:_prefetch	Lcom/fasterxml/jackson/databind/ObjectWriter$Prefetch;
    //   34: aload_1
    //   35: aload_2
    //   36: aload_0
    //   37: invokevirtual 116	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	()Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   40: invokevirtual 120	com/fasterxml/jackson/databind/ObjectWriter$Prefetch:serialize	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;)V
    //   43: aload_0
    //   44: getfield 45	com/fasterxml/jackson/databind/ObjectWriter:_config	Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   47: getstatic 437	com/fasterxml/jackson/databind/SerializationFeature:FLUSH_AFTER_WRITE_VALUE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   50: invokevirtual 151	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   53: ifeq +7 -> 60
    //   56: aload_1
    //   57: invokevirtual 440	com/fasterxml/jackson/core/JsonGenerator:flush	()V
    //   60: aload_3
    //   61: astore 6
    //   63: aconst_null
    //   64: astore_3
    //   65: aload 6
    //   67: invokeinterface 126 1 0
    //   72: iconst_0
    //   73: ifeq +9 -> 82
    //   76: aconst_null
    //   77: invokeinterface 126 1 0
    //   82: return
    //   83: astore 4
    //   85: aload_3
    //   86: ifnull +9 -> 95
    //   89: aload_3
    //   90: invokeinterface 126 1 0
    //   95: aload 4
    //   97: athrow
    //   98: aload_0
    //   99: getfield 67	com/fasterxml/jackson/databind/ObjectWriter:_prefetch	Lcom/fasterxml/jackson/databind/ObjectWriter$Prefetch;
    //   102: aload_1
    //   103: aload_2
    //   104: aload_0
    //   105: invokevirtual 116	com/fasterxml/jackson/databind/ObjectWriter:_serializerProvider	()Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   108: invokevirtual 120	com/fasterxml/jackson/databind/ObjectWriter$Prefetch:serialize	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;)V
    //   111: aload_0
    //   112: getfield 45	com/fasterxml/jackson/databind/ObjectWriter:_config	Lcom/fasterxml/jackson/databind/SerializationConfig;
    //   115: getstatic 437	com/fasterxml/jackson/databind/SerializationFeature:FLUSH_AFTER_WRITE_VALUE	Lcom/fasterxml/jackson/databind/SerializationFeature;
    //   118: invokevirtual 151	com/fasterxml/jackson/databind/SerializationConfig:isEnabled	(Lcom/fasterxml/jackson/databind/SerializationFeature;)Z
    //   121: ifeq -39 -> 82
    //   124: aload_1
    //   125: invokevirtual 440	com/fasterxml/jackson/core/JsonGenerator:flush	()V
    //   128: goto -46 -> 82
    //   131: astore 7
    //   133: goto -51 -> 82
    //   136: astore 5
    //   138: goto -43 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	this	ObjectWriter
    //   0	141	1	paramJsonGenerator	JsonGenerator
    //   0	141	2	paramObject	Object
    //   29	61	3	localCloseable1	java.io.Closeable
    //   83	13	4	localObject	Object
    //   136	1	5	localIOException1	IOException
    //   61	5	6	localCloseable2	java.io.Closeable
    //   131	1	7	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   30	72	83	finally
    //   76	82	131	java/io/IOException
    //   89	95	136	java/io/IOException
  }
  
  public void writeValue(File paramFile, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._generatorFactory.createGenerator(paramFile, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(OutputStream paramOutputStream, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._generatorFactory.createGenerator(paramOutputStream, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(Writer paramWriter, Object paramObject)
    throws IOException, JsonGenerationException, JsonMappingException
  {
    _configAndWriteValue(this._generatorFactory.createGenerator(paramWriter), paramObject);
  }
  
  public byte[] writeValueAsBytes(Object paramObject)
    throws JsonProcessingException
  {
    ByteArrayBuilder localByteArrayBuilder = new ByteArrayBuilder(this._generatorFactory._getBufferRecycler());
    try
    {
      _configAndWriteValue(this._generatorFactory.createGenerator(localByteArrayBuilder, JsonEncoding.UTF8), paramObject);
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
    SegmentedStringWriter localSegmentedStringWriter = new SegmentedStringWriter(this._generatorFactory._getBufferRecycler());
    try
    {
      _configAndWriteValue(this._generatorFactory.createGenerator(localSegmentedStringWriter), paramObject);
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
  
  public SequenceWriter writeValues(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    _configureGenerator(paramJsonGenerator);
    return _newSequenceWriter(false, paramJsonGenerator, false);
  }
  
  public SequenceWriter writeValues(File paramFile)
    throws IOException
  {
    return _newSequenceWriter(false, this._generatorFactory.createGenerator(paramFile, JsonEncoding.UTF8), true);
  }
  
  public SequenceWriter writeValues(OutputStream paramOutputStream)
    throws IOException
  {
    return _newSequenceWriter(false, this._generatorFactory.createGenerator(paramOutputStream, JsonEncoding.UTF8), true);
  }
  
  public SequenceWriter writeValues(Writer paramWriter)
    throws IOException
  {
    return _newSequenceWriter(false, this._generatorFactory.createGenerator(paramWriter), true);
  }
  
  public SequenceWriter writeValuesAsArray(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    return _newSequenceWriter(true, paramJsonGenerator, false);
  }
  
  public SequenceWriter writeValuesAsArray(File paramFile)
    throws IOException
  {
    return _newSequenceWriter(true, this._generatorFactory.createGenerator(paramFile, JsonEncoding.UTF8), true);
  }
  
  public SequenceWriter writeValuesAsArray(OutputStream paramOutputStream)
    throws IOException
  {
    return _newSequenceWriter(true, this._generatorFactory.createGenerator(paramOutputStream, JsonEncoding.UTF8), true);
  }
  
  public SequenceWriter writeValuesAsArray(Writer paramWriter)
    throws IOException
  {
    return _newSequenceWriter(true, this._generatorFactory.createGenerator(paramWriter), true);
  }
  
  public static final class Prefetch
    implements Serializable
  {
    public static final Prefetch empty = new Prefetch(null, null, null);
    private static final long serialVersionUID = 1L;
    private final JavaType rootType;
    private final TypeSerializer typeSerializer;
    private final JsonSerializer<Object> valueSerializer;
    
    private Prefetch(JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer, TypeSerializer paramTypeSerializer)
    {
      this.rootType = paramJavaType;
      this.valueSerializer = paramJsonSerializer;
      this.typeSerializer = paramTypeSerializer;
    }
    
    public Prefetch forRootType(ObjectWriter paramObjectWriter, JavaType paramJavaType)
    {
      int i = 1;
      if ((paramJavaType == null) || (paramJavaType.isJavaLangObject()))
      {
        if (i == 0) {
          break label55;
        }
        if ((this.rootType != null) && (this.valueSerializer != null)) {
          break label38;
        }
      }
      for (;;)
      {
        return this;
        i = 0;
        break;
        label38:
        this = new Prefetch(null, null, this.typeSerializer);
        continue;
        label55:
        if (!paramJavaType.equals(this.rootType)) {
          if (paramObjectWriter.isEnabled(SerializationFeature.EAGER_SERIALIZER_FETCH))
          {
            DefaultSerializerProvider localDefaultSerializerProvider = paramObjectWriter._serializerProvider();
            try
            {
              JsonSerializer localJsonSerializer = localDefaultSerializerProvider.findTypedValueSerializer(paramJavaType, true, null);
              if ((localJsonSerializer instanceof TypeWrappedSerializer))
              {
                this = new Prefetch(paramJavaType, null, ((TypeWrappedSerializer)localJsonSerializer).typeSerializer());
                continue;
              }
              Prefetch localPrefetch = new Prefetch(paramJavaType, localJsonSerializer, null);
              this = localPrefetch;
            }
            catch (JsonProcessingException localJsonProcessingException) {}
          }
          else
          {
            this = new Prefetch(null, null, this.typeSerializer);
          }
        }
      }
    }
    
    public final TypeSerializer getTypeSerializer()
    {
      return this.typeSerializer;
    }
    
    public final JsonSerializer<Object> getValueSerializer()
    {
      return this.valueSerializer;
    }
    
    public boolean hasSerializer()
    {
      if ((this.valueSerializer != null) || (this.typeSerializer != null)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public void serialize(JsonGenerator paramJsonGenerator, Object paramObject, DefaultSerializerProvider paramDefaultSerializerProvider)
      throws IOException
    {
      if (this.typeSerializer != null) {
        paramDefaultSerializerProvider.serializePolymorphic(paramJsonGenerator, paramObject, this.rootType, this.valueSerializer, this.typeSerializer);
      }
      for (;;)
      {
        return;
        if (this.valueSerializer != null) {
          paramDefaultSerializerProvider.serializeValue(paramJsonGenerator, paramObject, this.rootType, this.valueSerializer);
        } else {
          paramDefaultSerializerProvider.serializeValue(paramJsonGenerator, paramObject);
        }
      }
    }
  }
  
  public static final class GeneratorSettings
    implements Serializable
  {
    public static final GeneratorSettings empty = new GeneratorSettings(null, null, null, null);
    private static final long serialVersionUID = 1L;
    public final CharacterEscapes characterEscapes;
    public final PrettyPrinter prettyPrinter;
    public final SerializableString rootValueSeparator;
    public final FormatSchema schema;
    
    public GeneratorSettings(PrettyPrinter paramPrettyPrinter, FormatSchema paramFormatSchema, CharacterEscapes paramCharacterEscapes, SerializableString paramSerializableString)
    {
      this.prettyPrinter = paramPrettyPrinter;
      this.schema = paramFormatSchema;
      this.characterEscapes = paramCharacterEscapes;
      this.rootValueSeparator = paramSerializableString;
    }
    
    public void initialize(JsonGenerator paramJsonGenerator)
    {
      PrettyPrinter localPrettyPrinter = this.prettyPrinter;
      if (this.prettyPrinter != null)
      {
        if (localPrettyPrinter != ObjectWriter.NULL_PRETTY_PRINTER) {
          break label73;
        }
        paramJsonGenerator.setPrettyPrinter(null);
      }
      for (;;)
      {
        if (this.characterEscapes != null) {
          paramJsonGenerator.setCharacterEscapes(this.characterEscapes);
        }
        if (this.schema != null) {
          paramJsonGenerator.setSchema(this.schema);
        }
        if (this.rootValueSeparator != null) {
          paramJsonGenerator.setRootValueSeparator(this.rootValueSeparator);
        }
        return;
        label73:
        if ((localPrettyPrinter instanceof Instantiatable)) {
          localPrettyPrinter = (PrettyPrinter)((Instantiatable)localPrettyPrinter).createInstance();
        }
        paramJsonGenerator.setPrettyPrinter(localPrettyPrinter);
      }
    }
    
    public GeneratorSettings with(FormatSchema paramFormatSchema)
    {
      if (this.schema == paramFormatSchema) {}
      for (;;)
      {
        return this;
        this = new GeneratorSettings(this.prettyPrinter, paramFormatSchema, this.characterEscapes, this.rootValueSeparator);
      }
    }
    
    public GeneratorSettings with(PrettyPrinter paramPrettyPrinter)
    {
      if (paramPrettyPrinter == null) {
        paramPrettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
      }
      if (paramPrettyPrinter == this.prettyPrinter) {}
      for (;;)
      {
        return this;
        this = new GeneratorSettings(paramPrettyPrinter, this.schema, this.characterEscapes, this.rootValueSeparator);
      }
    }
    
    public GeneratorSettings with(CharacterEscapes paramCharacterEscapes)
    {
      if (this.characterEscapes == paramCharacterEscapes) {}
      for (;;)
      {
        return this;
        this = new GeneratorSettings(this.prettyPrinter, this.schema, paramCharacterEscapes, this.rootValueSeparator);
      }
    }
    
    public GeneratorSettings withRootValueSeparator(SerializableString paramSerializableString)
    {
      if (paramSerializableString == null) {
        if (this.rootValueSeparator != null) {
          break label41;
        }
      }
      for (;;)
      {
        return this;
        if ((this.rootValueSeparator == null) || (!paramSerializableString.getValue().equals(this.rootValueSeparator.getValue()))) {
          label41:
          this = new GeneratorSettings(this.prettyPrinter, this.schema, this.characterEscapes, paramSerializableString);
        }
      }
    }
    
    public GeneratorSettings withRootValueSeparator(String paramString)
    {
      if (paramString == null)
      {
        if (this.rootValueSeparator != null) {}
      }
      else {
        while (paramString.equals(this.rootValueSeparator)) {
          return this;
        }
      }
      PrettyPrinter localPrettyPrinter = this.prettyPrinter;
      FormatSchema localFormatSchema = this.schema;
      CharacterEscapes localCharacterEscapes = this.characterEscapes;
      if (paramString == null) {}
      for (Object localObject = null;; localObject = new SerializedString(paramString))
      {
        this = new GeneratorSettings(localPrettyPrinter, localFormatSchema, localCharacterEscapes, (SerializableString)localObject);
        break;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ObjectWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */