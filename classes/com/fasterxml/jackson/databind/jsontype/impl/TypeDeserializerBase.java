package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class TypeDeserializerBase
  extends TypeDeserializer
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _baseType;
  protected final JavaType _defaultImpl;
  protected JsonDeserializer<Object> _defaultImplDeserializer;
  protected final Map<String, JsonDeserializer<Object>> _deserializers;
  protected final TypeIdResolver _idResolver;
  protected final BeanProperty _property;
  protected final boolean _typeIdVisible;
  protected final String _typePropertyName;
  
  protected TypeDeserializerBase(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, String paramString, boolean paramBoolean, Class<?> paramClass)
  {
    this._baseType = paramJavaType;
    this._idResolver = paramTypeIdResolver;
    this._typePropertyName = paramString;
    this._typeIdVisible = paramBoolean;
    this._deserializers = new ConcurrentHashMap(16, 0.75F, 4);
    if (paramClass == null) {}
    for (this._defaultImpl = null;; this._defaultImpl = paramJavaType.forcedNarrowBy(paramClass))
    {
      this._property = null;
      return;
    }
  }
  
  protected TypeDeserializerBase(TypeDeserializerBase paramTypeDeserializerBase, BeanProperty paramBeanProperty)
  {
    this._baseType = paramTypeDeserializerBase._baseType;
    this._idResolver = paramTypeDeserializerBase._idResolver;
    this._typePropertyName = paramTypeDeserializerBase._typePropertyName;
    this._typeIdVisible = paramTypeDeserializerBase._typeIdVisible;
    this._deserializers = paramTypeDeserializerBase._deserializers;
    this._defaultImpl = paramTypeDeserializerBase._defaultImpl;
    this._defaultImplDeserializer = paramTypeDeserializerBase._defaultImplDeserializer;
    this._property = paramBeanProperty;
  }
  
  @Deprecated
  protected Object _deserializeWithNativeTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return _deserializeWithNativeTypeId(paramJsonParser, paramDeserializationContext, paramJsonParser.getTypeId());
  }
  
  protected Object _deserializeWithNativeTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    JsonDeserializer localJsonDeserializer;
    if (paramObject == null)
    {
      localJsonDeserializer = _findDefaultImplDeserializer(paramDeserializationContext);
      if (localJsonDeserializer == null) {
        throw paramDeserializationContext.mappingException("No (native) type id found when one was expected for polymorphic type handling");
      }
    }
    else
    {
      if (!(paramObject instanceof String)) {
        break label53;
      }
    }
    label53:
    for (String str = (String)paramObject;; str = String.valueOf(paramObject))
    {
      localJsonDeserializer = _findDeserializer(paramDeserializationContext, str);
      return localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    }
  }
  
  protected final JsonDeserializer<Object> _findDefaultImplDeserializer(DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject2;
    if (this._defaultImpl == null) {
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
        localObject2 = NullifyingDeserializer.instance;
      }
    }
    for (;;)
    {
      return (JsonDeserializer<Object>)localObject2;
      localObject2 = null;
      continue;
      if (ClassUtil.isBogusClass(this._defaultImpl.getRawClass()))
      {
        localObject2 = NullifyingDeserializer.instance;
        continue;
      }
      synchronized (this._defaultImpl)
      {
        if (this._defaultImplDeserializer == null) {
          this._defaultImplDeserializer = paramDeserializationContext.findContextualValueDeserializer(this._defaultImpl, this._property);
        }
        localObject2 = this._defaultImplDeserializer;
      }
    }
  }
  
  protected final JsonDeserializer<Object> _findDeserializer(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException
  {
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)this._deserializers.get(paramString);
    JavaType localJavaType;
    if (localJsonDeserializer == null)
    {
      localJavaType = this._idResolver.typeFromId(paramDeserializationContext, paramString);
      if (localJavaType != null) {
        break label75;
      }
      localJsonDeserializer = _findDefaultImplDeserializer(paramDeserializationContext);
      if (localJsonDeserializer != null) {}
    }
    for (localJsonDeserializer = _handleUnknownTypeId(paramDeserializationContext, paramString, this._idResolver, this._baseType);; localJsonDeserializer = paramDeserializationContext.findContextualValueDeserializer(localJavaType, this._property))
    {
      this._deserializers.put(paramString, localJsonDeserializer);
      return localJsonDeserializer;
      label75:
      if ((this._baseType != null) && (this._baseType.getClass() == localJavaType.getClass())) {
        localJavaType = paramDeserializationContext.getTypeFactory().constructSpecializedType(this._baseType, localJavaType.getRawClass());
      }
    }
  }
  
  protected JsonDeserializer<Object> _handleUnknownTypeId(DeserializationContext paramDeserializationContext, String paramString, TypeIdResolver paramTypeIdResolver, JavaType paramJavaType)
    throws IOException
  {
    String str2;
    String str1;
    if ((paramTypeIdResolver instanceof TypeIdResolverBase))
    {
      str2 = ((TypeIdResolverBase)paramTypeIdResolver).getDescForKnownTypeIds();
      if (str2 == null) {
        str1 = "known type ids are not statically known";
      }
    }
    for (;;)
    {
      throw paramDeserializationContext.unknownTypeException(this._baseType, paramString, str1);
      str1 = "known type ids = " + str2;
      continue;
      str1 = null;
    }
  }
  
  public String baseTypeName()
  {
    return this._baseType.getRawClass().getName();
  }
  
  public abstract TypeDeserializer forProperty(BeanProperty paramBeanProperty);
  
  public Class<?> getDefaultImpl()
  {
    if (this._defaultImpl == null) {}
    for (Object localObject = null;; localObject = this._defaultImpl.getRawClass()) {
      return (Class<?>)localObject;
    }
  }
  
  public final String getPropertyName()
  {
    return this._typePropertyName;
  }
  
  public TypeIdResolver getTypeIdResolver()
  {
    return this._idResolver;
  }
  
  public abstract JsonTypeInfo.As getTypeInclusion();
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[').append(getClass().getName());
    localStringBuilder.append("; base-type:").append(this._baseType);
    localStringBuilder.append("; id-resolver: ").append(this._idResolver);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/TypeDeserializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */