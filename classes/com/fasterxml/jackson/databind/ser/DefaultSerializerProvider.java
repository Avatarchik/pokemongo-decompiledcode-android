package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DefaultSerializerProvider
  extends SerializerProvider
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected transient ArrayList<ObjectIdGenerator<?>> _objectIdGenerators;
  protected transient Map<Object, WritableObjectId> _seenObjectIds;
  
  protected DefaultSerializerProvider() {}
  
  protected DefaultSerializerProvider(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
  {
    super(paramSerializerProvider, paramSerializationConfig, paramSerializerFactory);
  }
  
  protected DefaultSerializerProvider(DefaultSerializerProvider paramDefaultSerializerProvider)
  {
    super(paramDefaultSerializerProvider);
  }
  
  protected Map<Object, WritableObjectId> _createObjectIdMap()
  {
    if (isEnabled(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID)) {}
    for (Object localObject = new HashMap();; localObject = new IdentityHashMap()) {
      return (Map<Object, WritableObjectId>)localObject;
    }
  }
  
  protected void _serializeNull(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    JsonSerializer localJsonSerializer = getDefaultNullValueSerializer();
    try
    {
      localJsonSerializer.serialize(null, paramJsonGenerator, this);
      return;
    }
    catch (IOException localIOException)
    {
      throw localIOException;
    }
    catch (Exception localException)
    {
      String str = localException.getMessage();
      if (str == null) {
        str = "[no message for " + localException.getClass().getName() + "]";
      }
      throw new JsonMappingException(str, localException);
    }
  }
  
  public void acceptJsonFormatVisitor(JavaType paramJavaType, JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper)
    throws JsonMappingException
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("A class must be provided");
    }
    paramJsonFormatVisitorWrapper.setProvider(this);
    findValueSerializer(paramJavaType, null).acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, paramJavaType);
  }
  
  public int cachedSerializersCount()
  {
    return this._serializerCache.size();
  }
  
  public DefaultSerializerProvider copy()
  {
    throw new IllegalStateException("DefaultSerializerProvider sub-class not overriding copy()");
  }
  
  public abstract DefaultSerializerProvider createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory);
  
  public WritableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator)
  {
    Object localObject;
    if (this._seenObjectIds == null)
    {
      this._seenObjectIds = _createObjectIdMap();
      localObject = null;
      if (this._objectIdGenerators != null) {
        break label105;
      }
      this._objectIdGenerators = new ArrayList(8);
    }
    label105:
    label158:
    for (;;)
    {
      if (localObject == null)
      {
        localObject = paramObjectIdGenerator.newForSerialization(this);
        this._objectIdGenerators.add(localObject);
      }
      WritableObjectId localWritableObjectId = new WritableObjectId((ObjectIdGenerator)localObject);
      this._seenObjectIds.put(paramObject, localWritableObjectId);
      for (;;)
      {
        return localWritableObjectId;
        localWritableObjectId = (WritableObjectId)this._seenObjectIds.get(paramObject);
        if (localWritableObjectId == null) {
          break;
        }
      }
      int i = 0;
      int j = this._objectIdGenerators.size();
      for (;;)
      {
        if (i >= j) {
          break label158;
        }
        ObjectIdGenerator localObjectIdGenerator = (ObjectIdGenerator)this._objectIdGenerators.get(i);
        if (localObjectIdGenerator.canUseFor(paramObjectIdGenerator))
        {
          localObject = localObjectIdGenerator;
          break;
        }
        i++;
      }
    }
  }
  
  public void flushCachedSerializers()
  {
    this._serializerCache.flush();
  }
  
  @Deprecated
  public JsonSchema generateJsonSchema(Class<?> paramClass)
    throws JsonMappingException
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("A class must be provided");
    }
    JsonSerializer localJsonSerializer = findValueSerializer(paramClass, null);
    if ((localJsonSerializer instanceof SchemaAware)) {}
    for (JsonNode localJsonNode = ((SchemaAware)localJsonSerializer).getSchema(this, null); !(localJsonNode instanceof ObjectNode); localJsonNode = JsonSchema.getDefaultSchemaNode()) {
      throw new IllegalArgumentException("Class " + paramClass.getName() + " would not be serialized as a JSON object and therefore has no schema");
    }
    return new JsonSchema((ObjectNode)localJsonNode);
  }
  
  public boolean hasSerializerFor(Class<?> paramClass, AtomicReference<Throwable> paramAtomicReference)
  {
    boolean bool = false;
    try
    {
      JsonSerializer localJsonSerializer = _findExplicitUntypedSerializer(paramClass);
      if (localJsonSerializer != null) {
        bool = true;
      }
    }
    catch (JsonMappingException localJsonMappingException)
    {
      for (;;)
      {
        if (paramAtomicReference != null) {
          paramAtomicReference.set(localJsonMappingException);
        }
      }
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        if (paramAtomicReference == null) {
          throw localRuntimeException;
        }
        paramAtomicReference.set(localRuntimeException);
      }
    }
    return bool;
  }
  
  public void serializePolymorphic(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if ((paramJavaType != null) && (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass()))) {
        _reportIncompatibleRootType(paramObject, paramJavaType);
      }
      PropertyName localPropertyName;
      boolean bool;
      if (paramJsonSerializer == null)
      {
        if ((paramJavaType != null) && (paramJavaType.isContainerType())) {
          paramJsonSerializer = findValueSerializer(paramJavaType, null);
        }
      }
      else
      {
        localPropertyName = this._config.getFullRootName();
        if (localPropertyName != null) {
          break label156;
        }
        bool = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        if (bool)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(this._config.findRootName(paramObject.getClass()).simpleAsEncoded(this._config));
        }
      }
      try
      {
        paramJsonSerializer.serializeWithType(paramObject, paramJsonGenerator, this, paramTypeSerializer);
        if (!bool) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          throw localIOException;
          paramJsonSerializer = findValueSerializer(paramObject.getClass(), null);
          break;
          if (localPropertyName.isEmpty())
          {
            bool = false;
          }
          else
          {
            bool = true;
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(localPropertyName.getSimpleName());
          }
        }
      }
      catch (Exception localException)
      {
        label156:
        String str = localException.getMessage();
        if (str == null) {
          str = "[no message for " + localException.getClass().getName() + "]";
        }
        throw new JsonMappingException(str, localException);
      }
    }
  }
  
  @Deprecated
  public void serializePolymorphic(JsonGenerator paramJsonGenerator, Object paramObject, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if (paramObject == null) {}
    for (JavaType localJavaType = null;; localJavaType = this._config.constructType(paramObject.getClass()))
    {
      serializePolymorphic(paramJsonGenerator, paramObject, localJavaType, null, paramTypeSerializer);
      return;
    }
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      JsonSerializer localJsonSerializer = findTypedValueSerializer(paramObject.getClass(), true, null);
      PropertyName localPropertyName = this._config.getFullRootName();
      boolean bool;
      if (localPropertyName == null)
      {
        bool = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        if (bool)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(this._config.findRootName(paramObject.getClass()).simpleAsEncoded(this._config));
        }
      }
      try
      {
        localJsonSerializer.serialize(paramObject, paramJsonGenerator, this);
        if (!bool) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          throw localIOException;
          if (localPropertyName.isEmpty())
          {
            bool = false;
          }
          else
          {
            bool = true;
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(localPropertyName.getSimpleName());
          }
        }
      }
      catch (Exception localException)
      {
        String str = localException.getMessage();
        if (str == null) {
          str = "[no message for " + localException.getClass().getName() + "]";
        }
        throw new JsonMappingException(str, localException);
      }
    }
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType)
    throws IOException
  {
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass())) {
        _reportIncompatibleRootType(paramObject, paramJavaType);
      }
      JsonSerializer localJsonSerializer = findTypedValueSerializer(paramJavaType, true, null);
      PropertyName localPropertyName = this._config.getFullRootName();
      boolean bool;
      if (localPropertyName == null)
      {
        bool = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        if (bool)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(this._config.findRootName(paramObject.getClass()).simpleAsEncoded(this._config));
        }
      }
      try
      {
        localJsonSerializer.serialize(paramObject, paramJsonGenerator, this);
        if (!bool) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          throw localIOException;
          if (localPropertyName.isEmpty())
          {
            bool = false;
          }
          else
          {
            bool = true;
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(localPropertyName.getSimpleName());
          }
        }
      }
      catch (Exception localException)
      {
        String str = localException.getMessage();
        if (str == null) {
          str = "[no message for " + localException.getClass().getName() + "]";
        }
        throw new JsonMappingException(str, localException);
      }
    }
  }
  
  public void serializeValue(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    if (paramObject == null) {
      _serializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if ((paramJavaType != null) && (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass()))) {
        _reportIncompatibleRootType(paramObject, paramJavaType);
      }
      if (paramJsonSerializer == null) {
        paramJsonSerializer = findTypedValueSerializer(paramJavaType, true, null);
      }
      PropertyName localPropertyName1 = this._config.getFullRootName();
      boolean bool;
      PropertyName localPropertyName2;
      if (localPropertyName1 == null)
      {
        bool = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        if (bool)
        {
          paramJsonGenerator.writeStartObject();
          if (paramJavaType != null) {
            break label138;
          }
          localPropertyName2 = this._config.findRootName(paramObject.getClass());
          paramJsonGenerator.writeFieldName(localPropertyName2.simpleAsEncoded(this._config));
        }
      }
      try
      {
        paramJsonSerializer.serialize(paramObject, paramJsonGenerator, this);
        if (!bool) {
          continue;
        }
        paramJsonGenerator.writeEndObject();
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          throw localIOException;
          localPropertyName2 = this._config.findRootName(paramJavaType);
          break;
          if (localPropertyName1.isEmpty())
          {
            bool = false;
          }
          else
          {
            bool = true;
            paramJsonGenerator.writeStartObject();
            paramJsonGenerator.writeFieldName(localPropertyName1.getSimpleName());
          }
        }
      }
      catch (Exception localException)
      {
        label138:
        String str = localException.getMessage();
        if (str == null) {
          str = "[no message for " + localException.getClass().getName() + "]";
        }
        throw new JsonMappingException(str, localException);
      }
    }
  }
  
  public JsonSerializer<Object> serializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    Object localObject = null;
    if (paramObject == null) {
      return (JsonSerializer<Object>)localObject;
    }
    JsonSerializer localJsonSerializer;
    if ((paramObject instanceof JsonSerializer)) {
      localJsonSerializer = (JsonSerializer)paramObject;
    }
    label205:
    for (;;)
    {
      localObject = _handleResolvable(localJsonSerializer);
      break;
      if (!(paramObject instanceof Class)) {
        throw new IllegalStateException("AnnotationIntrospector returned serializer definition of type " + paramObject.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead");
      }
      Class localClass = (Class)paramObject;
      if ((localClass == JsonSerializer.None.class) || (ClassUtil.isBogusClass(localClass))) {
        break;
      }
      if (!JsonSerializer.class.isAssignableFrom(localClass)) {
        throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<JsonSerializer>");
      }
      HandlerInstantiator localHandlerInstantiator = this._config.getHandlerInstantiator();
      if (localHandlerInstantiator == null) {}
      for (localJsonSerializer = null;; localJsonSerializer = localHandlerInstantiator.serializerInstance(this._config, paramAnnotated, localClass))
      {
        if (localJsonSerializer != null) {
          break label205;
        }
        localJsonSerializer = (JsonSerializer)ClassUtil.createInstance(localClass, this._config.canOverrideAccessModifiers());
        break;
      }
    }
  }
  
  public static final class Impl
    extends DefaultSerializerProvider
  {
    private static final long serialVersionUID = 1L;
    
    public Impl() {}
    
    protected Impl(SerializerProvider paramSerializerProvider, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
    {
      super(paramSerializationConfig, paramSerializerFactory);
    }
    
    public Impl(Impl paramImpl)
    {
      super();
    }
    
    public DefaultSerializerProvider copy()
    {
      if (getClass() != Impl.class) {}
      for (Object localObject = super.copy();; localObject = new Impl(this)) {
        return (DefaultSerializerProvider)localObject;
      }
    }
    
    public Impl createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory)
    {
      return new Impl(this, paramSerializationConfig, paramSerializerFactory);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/DefaultSerializerProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */