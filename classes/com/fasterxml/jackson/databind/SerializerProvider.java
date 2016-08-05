package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import com.fasterxml.jackson.databind.ser.impl.UnknownSerializer;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SerializerProvider
  extends DatabindContext
{
  protected static final boolean CACHE_UNKNOWN_MAPPINGS;
  public static final JsonSerializer<Object> DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
  protected static final JsonSerializer<Object> DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();
  protected transient ContextAttributes _attributes;
  protected final SerializationConfig _config;
  protected DateFormat _dateFormat;
  protected JsonSerializer<Object> _keySerializer;
  protected final ReadOnlyClassToSerializerMap _knownSerializers;
  protected JsonSerializer<Object> _nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
  protected JsonSerializer<Object> _nullValueSerializer = NullSerializer.instance;
  protected final Class<?> _serializationView;
  protected final SerializerCache _serializerCache;
  protected final SerializerFactory _serializerFactory;
  protected final boolean _stdNullValueSerializer;
  protected JsonSerializer<Object> _unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
  
  public SerializerProvider()
  {
    this._config = null;
    this._serializerFactory = null;
    this._serializerCache = new SerializerCache();
    this._knownSerializers = null;
    this._serializationView = null;
    this._attributes = null;
    this._stdNullValueSerializer = true;
  }
  
  protected SerializerProvider(SerializerProvider paramSerializerProvider)
  {
    this._config = null;
    this._serializationView = null;
    this._serializerFactory = null;
    this._knownSerializers = null;
    this._serializerCache = new SerializerCache();
    this._unknownTypeSerializer = paramSerializerProvider._unknownTypeSerializer;
    this._keySerializer = paramSerializerProvider._keySerializer;
    this._nullValueSerializer = paramSerializerProvider._nullValueSerializer;
    this._nullKeySerializer = paramSerializerProvider._nullKeySerializer;
    this._stdNullValueSerializer = paramSerializerProvider._stdNullValueSerializer;
  }
  
  protected SerializerProvider(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
  {
    if (paramSerializationConfig == null) {
      throw new NullPointerException();
    }
    this._serializerFactory = paramSerializerFactory;
    this._config = paramSerializationConfig;
    this._serializerCache = paramSerializerProvider._serializerCache;
    this._unknownTypeSerializer = paramSerializerProvider._unknownTypeSerializer;
    this._keySerializer = paramSerializerProvider._keySerializer;
    this._nullValueSerializer = paramSerializerProvider._nullValueSerializer;
    this._nullKeySerializer = paramSerializerProvider._nullKeySerializer;
    if (this._nullValueSerializer == DEFAULT_NULL_KEY_SERIALIZER) {}
    for (boolean bool = true;; bool = false)
    {
      this._stdNullValueSerializer = bool;
      this._serializationView = paramSerializationConfig.getActiveView();
      this._attributes = paramSerializationConfig.getAttributes();
      this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
      return;
    }
  }
  
  protected JsonSerializer<Object> _createAndCacheUntypedSerializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    try
    {
      JsonSerializer localJsonSerializer = _createUntypedSerializer(paramJavaType);
      if (localJsonSerializer != null) {
        this._serializerCache.addAndResolveNonTypedSerializer(paramJavaType, localJsonSerializer, this);
      }
      return localJsonSerializer;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new JsonMappingException(localIllegalArgumentException.getMessage(), null, localIllegalArgumentException);
    }
  }
  
  protected JsonSerializer<Object> _createAndCacheUntypedSerializer(Class<?> paramClass)
    throws JsonMappingException
  {
    JavaType localJavaType = this._config.constructType(paramClass);
    try
    {
      JsonSerializer localJsonSerializer = _createUntypedSerializer(localJavaType);
      if (localJsonSerializer != null) {
        this._serializerCache.addAndResolveNonTypedSerializer(localJavaType, localJsonSerializer, this);
      }
      return localJsonSerializer;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new JsonMappingException(localIllegalArgumentException.getMessage(), null, localIllegalArgumentException);
    }
  }
  
  protected JsonSerializer<Object> _createUntypedSerializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    synchronized (this._serializerCache)
    {
      JsonSerializer localJsonSerializer = this._serializerFactory.createSerializer(this, paramJavaType);
      return localJsonSerializer;
    }
  }
  
  protected final DateFormat _dateFormat()
  {
    DateFormat localDateFormat;
    if (this._dateFormat != null) {
      localDateFormat = this._dateFormat;
    }
    for (;;)
    {
      return localDateFormat;
      localDateFormat = (DateFormat)this._config.getDateFormat().clone();
      this._dateFormat = localDateFormat;
    }
  }
  
  protected JsonSerializer<Object> _findExplicitUntypedSerializer(Class<?> paramClass)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = this._knownSerializers.untypedValueSerializer(paramClass);
    if (localJsonSerializer == null)
    {
      localJsonSerializer = this._serializerCache.untypedValueSerializer(paramClass);
      if (localJsonSerializer == null) {
        localJsonSerializer = _createAndCacheUntypedSerializer(paramClass);
      }
    }
    if (isUnknownTypeSerializer(localJsonSerializer)) {
      localJsonSerializer = null;
    }
    return localJsonSerializer;
  }
  
  protected JsonSerializer<Object> _handleContextualResolvable(JsonSerializer<?> paramJsonSerializer, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    if ((paramJsonSerializer instanceof ResolvableSerializer)) {
      ((ResolvableSerializer)paramJsonSerializer).resolve(this);
    }
    return handleSecondaryContextualization(paramJsonSerializer, paramBeanProperty);
  }
  
  protected JsonSerializer<Object> _handleResolvable(JsonSerializer<?> paramJsonSerializer)
    throws JsonMappingException
  {
    if ((paramJsonSerializer instanceof ResolvableSerializer)) {
      ((ResolvableSerializer)paramJsonSerializer).resolve(this);
    }
    return paramJsonSerializer;
  }
  
  protected void _reportIncompatibleRootType(Object paramObject, JavaType paramJavaType)
    throws IOException, JsonProcessingException
  {
    if ((paramJavaType.isPrimitive()) && (ClassUtil.wrapperType(paramJavaType.getRawClass()).isAssignableFrom(paramObject.getClass()))) {
      return;
    }
    throw new JsonMappingException("Incompatible types: declared root type (" + paramJavaType + ") vs " + paramObject.getClass().getName());
  }
  
  public void defaultSerializeDateKey(long paramLong, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeFieldName(String.valueOf(paramLong));
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeFieldName(_dateFormat().format(new Date(paramLong)));
    }
  }
  
  public void defaultSerializeDateKey(Date paramDate, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (isEnabled(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeFieldName(String.valueOf(paramDate.getTime()));
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeFieldName(_dateFormat().format(paramDate));
    }
  }
  
  public final void defaultSerializeDateValue(long paramLong, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeNumber(paramLong);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeString(_dateFormat().format(new Date(paramLong)));
    }
  }
  
  public final void defaultSerializeDateValue(Date paramDate, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeNumber(paramDate.getTime());
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeString(_dateFormat().format(paramDate));
    }
  }
  
  public final void defaultSerializeField(String paramString, Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    paramJsonGenerator.writeFieldName(paramString);
    if (paramObject == null) {
      if (this._stdNullValueSerializer) {
        paramJsonGenerator.writeNull();
      }
    }
    for (;;)
    {
      return;
      this._nullValueSerializer.serialize(null, paramJsonGenerator, this);
      continue;
      findTypedValueSerializer(paramObject.getClass(), true, null).serialize(paramObject, paramJsonGenerator, this);
    }
  }
  
  public final void defaultSerializeNull(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (this._stdNullValueSerializer) {
      paramJsonGenerator.writeNull();
    }
    for (;;)
    {
      return;
      this._nullValueSerializer.serialize(null, paramJsonGenerator, this);
    }
  }
  
  public final void defaultSerializeValue(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (paramObject == null) {
      if (this._stdNullValueSerializer) {
        paramJsonGenerator.writeNull();
      }
    }
    for (;;)
    {
      return;
      this._nullValueSerializer.serialize(null, paramJsonGenerator, this);
      continue;
      findTypedValueSerializer(paramObject.getClass(), true, null).serialize(paramObject, paramJsonGenerator, this);
    }
  }
  
  public JsonSerializer<Object> findKeySerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return _handleContextualResolvable(this._serializerFactory.createKeySerializer(this._config, paramJavaType, this._keySerializer), paramBeanProperty);
  }
  
  public JsonSerializer<Object> findKeySerializer(Class<?> paramClass, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return findKeySerializer(this._config.constructType(paramClass), paramBeanProperty);
  }
  
  public JsonSerializer<Object> findNullKeySerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return this._nullKeySerializer;
  }
  
  public JsonSerializer<Object> findNullValueSerializer(BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return this._nullValueSerializer;
  }
  
  public abstract WritableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator);
  
  public JsonSerializer<Object> findPrimaryPropertySerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = this._knownSerializers.untypedValueSerializer(paramJavaType);
    if (localJsonSerializer1 == null)
    {
      localJsonSerializer1 = this._serializerCache.untypedValueSerializer(paramJavaType);
      if (localJsonSerializer1 == null)
      {
        localJsonSerializer1 = _createAndCacheUntypedSerializer(paramJavaType);
        if (localJsonSerializer1 != null) {}
      }
    }
    for (JsonSerializer localJsonSerializer2 = getUnknownTypeSerializer(paramJavaType.getRawClass());; localJsonSerializer2 = handlePrimaryContextualization(localJsonSerializer1, paramBeanProperty)) {
      return localJsonSerializer2;
    }
  }
  
  public JsonSerializer<Object> findPrimaryPropertySerializer(Class<?> paramClass, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = this._knownSerializers.untypedValueSerializer(paramClass);
    if (localJsonSerializer1 == null)
    {
      localJsonSerializer1 = this._serializerCache.untypedValueSerializer(paramClass);
      if (localJsonSerializer1 == null)
      {
        localJsonSerializer1 = this._serializerCache.untypedValueSerializer(this._config.constructType(paramClass));
        if (localJsonSerializer1 == null)
        {
          localJsonSerializer1 = _createAndCacheUntypedSerializer(paramClass);
          if (localJsonSerializer1 != null) {}
        }
      }
    }
    for (JsonSerializer localJsonSerializer2 = getUnknownTypeSerializer(paramClass);; localJsonSerializer2 = handlePrimaryContextualization(localJsonSerializer1, paramBeanProperty)) {
      return localJsonSerializer2;
    }
  }
  
  public TypeSerializer findTypeSerializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    return this._serializerFactory.createTypeSerializer(this._config, paramJavaType);
  }
  
  public JsonSerializer<Object> findTypedValueSerializer(JavaType paramJavaType, boolean paramBoolean, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = this._knownSerializers.typedValueSerializer(paramJavaType);
    Object localObject2;
    if (localJsonSerializer1 != null) {
      localObject2 = localJsonSerializer1;
    }
    for (;;)
    {
      return (JsonSerializer<Object>)localObject2;
      JsonSerializer localJsonSerializer2 = this._serializerCache.typedValueSerializer(paramJavaType);
      if (localJsonSerializer2 != null)
      {
        localObject2 = localJsonSerializer2;
      }
      else
      {
        Object localObject1 = findValueSerializer(paramJavaType, paramBeanProperty);
        TypeSerializer localTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, paramJavaType);
        if (localTypeSerializer != null) {
          localObject1 = new TypeWrappedSerializer(localTypeSerializer.forProperty(paramBeanProperty), (JsonSerializer)localObject1);
        }
        if (paramBoolean) {
          this._serializerCache.addTypedSerializer(paramJavaType, (JsonSerializer)localObject1);
        }
        localObject2 = localObject1;
      }
    }
  }
  
  public JsonSerializer<Object> findTypedValueSerializer(Class<?> paramClass, boolean paramBoolean, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = this._knownSerializers.typedValueSerializer(paramClass);
    Object localObject2;
    if (localJsonSerializer1 != null) {
      localObject2 = localJsonSerializer1;
    }
    for (;;)
    {
      return (JsonSerializer<Object>)localObject2;
      JsonSerializer localJsonSerializer2 = this._serializerCache.typedValueSerializer(paramClass);
      if (localJsonSerializer2 != null)
      {
        localObject2 = localJsonSerializer2;
      }
      else
      {
        Object localObject1 = findValueSerializer(paramClass, paramBeanProperty);
        TypeSerializer localTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType(paramClass));
        if (localTypeSerializer != null) {
          localObject1 = new TypeWrappedSerializer(localTypeSerializer.forProperty(paramBeanProperty), (JsonSerializer)localObject1);
        }
        if (paramBoolean) {
          this._serializerCache.addTypedSerializer(paramClass, (JsonSerializer)localObject1);
        }
        localObject2 = localObject1;
      }
    }
  }
  
  public JsonSerializer<Object> findValueSerializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = this._knownSerializers.untypedValueSerializer(paramJavaType);
    if (localJsonSerializer == null)
    {
      localJsonSerializer = this._serializerCache.untypedValueSerializer(paramJavaType);
      if (localJsonSerializer == null)
      {
        localJsonSerializer = _createAndCacheUntypedSerializer(paramJavaType);
        if (localJsonSerializer == null) {
          localJsonSerializer = getUnknownTypeSerializer(paramJavaType.getRawClass());
        }
      }
    }
    return localJsonSerializer;
  }
  
  public JsonSerializer<Object> findValueSerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = this._knownSerializers.untypedValueSerializer(paramJavaType);
    if (localJsonSerializer1 == null)
    {
      localJsonSerializer1 = this._serializerCache.untypedValueSerializer(paramJavaType);
      if (localJsonSerializer1 == null)
      {
        localJsonSerializer1 = _createAndCacheUntypedSerializer(paramJavaType);
        if (localJsonSerializer1 != null) {}
      }
    }
    for (JsonSerializer localJsonSerializer2 = getUnknownTypeSerializer(paramJavaType.getRawClass());; localJsonSerializer2 = handleSecondaryContextualization(localJsonSerializer1, paramBeanProperty)) {
      return localJsonSerializer2;
    }
  }
  
  public JsonSerializer<Object> findValueSerializer(Class<?> paramClass)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = this._knownSerializers.untypedValueSerializer(paramClass);
    if (localJsonSerializer == null)
    {
      localJsonSerializer = this._serializerCache.untypedValueSerializer(paramClass);
      if (localJsonSerializer == null)
      {
        localJsonSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(paramClass));
        if (localJsonSerializer == null)
        {
          localJsonSerializer = _createAndCacheUntypedSerializer(paramClass);
          if (localJsonSerializer == null) {
            localJsonSerializer = getUnknownTypeSerializer(paramClass);
          }
        }
      }
    }
    return localJsonSerializer;
  }
  
  public JsonSerializer<Object> findValueSerializer(Class<?> paramClass, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = this._knownSerializers.untypedValueSerializer(paramClass);
    if (localJsonSerializer1 == null)
    {
      localJsonSerializer1 = this._serializerCache.untypedValueSerializer(paramClass);
      if (localJsonSerializer1 == null)
      {
        localJsonSerializer1 = this._serializerCache.untypedValueSerializer(this._config.constructType(paramClass));
        if (localJsonSerializer1 == null)
        {
          localJsonSerializer1 = _createAndCacheUntypedSerializer(paramClass);
          if (localJsonSerializer1 != null) {}
        }
      }
    }
    for (JsonSerializer localJsonSerializer2 = getUnknownTypeSerializer(paramClass);; localJsonSerializer2 = handleSecondaryContextualization(localJsonSerializer1, paramBeanProperty)) {
      return localJsonSerializer2;
    }
  }
  
  public final Class<?> getActiveView()
  {
    return this._serializationView;
  }
  
  public final AnnotationIntrospector getAnnotationIntrospector()
  {
    return this._config.getAnnotationIntrospector();
  }
  
  public Object getAttribute(Object paramObject)
  {
    return this._attributes.getAttribute(paramObject);
  }
  
  public final SerializationConfig getConfig()
  {
    return this._config;
  }
  
  public JsonSerializer<Object> getDefaultNullKeySerializer()
  {
    return this._nullKeySerializer;
  }
  
  public JsonSerializer<Object> getDefaultNullValueSerializer()
  {
    return this._nullValueSerializer;
  }
  
  public final FilterProvider getFilterProvider()
  {
    return this._config.getFilterProvider();
  }
  
  public Locale getLocale()
  {
    return this._config.getLocale();
  }
  
  @Deprecated
  public final Class<?> getSerializationView()
  {
    return this._serializationView;
  }
  
  public TimeZone getTimeZone()
  {
    return this._config.getTimeZone();
  }
  
  public final TypeFactory getTypeFactory()
  {
    return this._config.getTypeFactory();
  }
  
  public JsonSerializer<Object> getUnknownTypeSerializer(Class<?> paramClass)
  {
    if (paramClass == Object.class) {}
    for (Object localObject = this._unknownTypeSerializer;; localObject = new UnknownSerializer(paramClass)) {
      return (JsonSerializer<Object>)localObject;
    }
  }
  
  public JsonSerializer<?> handlePrimaryContextualization(JsonSerializer<?> paramJsonSerializer, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    if ((paramJsonSerializer != null) && ((paramJsonSerializer instanceof ContextualSerializer))) {
      paramJsonSerializer = ((ContextualSerializer)paramJsonSerializer).createContextual(this, paramBeanProperty);
    }
    return paramJsonSerializer;
  }
  
  public JsonSerializer<?> handleSecondaryContextualization(JsonSerializer<?> paramJsonSerializer, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    if ((paramJsonSerializer != null) && ((paramJsonSerializer instanceof ContextualSerializer))) {
      paramJsonSerializer = ((ContextualSerializer)paramJsonSerializer).createContextual(this, paramBeanProperty);
    }
    return paramJsonSerializer;
  }
  
  public final boolean hasSerializationFeatures(int paramInt)
  {
    return this._config.hasSerializationFeatures(paramInt);
  }
  
  public final boolean isEnabled(SerializationFeature paramSerializationFeature)
  {
    return this._config.isEnabled(paramSerializationFeature);
  }
  
  public boolean isUnknownTypeSerializer(JsonSerializer<?> paramJsonSerializer)
  {
    boolean bool = true;
    if ((paramJsonSerializer == this._unknownTypeSerializer) || (paramJsonSerializer == null)) {}
    for (;;)
    {
      return bool;
      if ((!isEnabled(SerializationFeature.FAIL_ON_EMPTY_BEANS)) || (paramJsonSerializer.getClass() != UnknownSerializer.class)) {
        bool = false;
      }
    }
  }
  
  public JsonMappingException mappingException(String paramString, Object... paramVarArgs)
  {
    if ((paramVarArgs != null) && (paramVarArgs.length > 0)) {
      paramString = String.format(paramString, paramVarArgs);
    }
    return new JsonMappingException(paramString);
  }
  
  public abstract JsonSerializer<Object> serializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException;
  
  public SerializerProvider setAttribute(Object paramObject1, Object paramObject2)
  {
    this._attributes = this._attributes.withPerCallAttribute(paramObject1, paramObject2);
    return this;
  }
  
  public void setDefaultKeySerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if (paramJsonSerializer == null) {
      throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }
    this._keySerializer = paramJsonSerializer;
  }
  
  public void setNullKeySerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if (paramJsonSerializer == null) {
      throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }
    this._nullKeySerializer = paramJsonSerializer;
  }
  
  public void setNullValueSerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if (paramJsonSerializer == null) {
      throw new IllegalArgumentException("Can not pass null JsonSerializer");
    }
    this._nullValueSerializer = paramJsonSerializer;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/SerializerProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */