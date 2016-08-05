package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class DeserializerCache
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers = new ConcurrentHashMap(64, 0.75F, 4);
  protected final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers = new HashMap(8);
  
  private boolean _hasCustomValueHandler(JavaType paramJavaType)
  {
    boolean bool = false;
    if (paramJavaType.isContainerType())
    {
      JavaType localJavaType = paramJavaType.getContentType();
      if ((localJavaType != null) && ((localJavaType.getValueHandler() != null) || (localJavaType.getTypeHandler() != null))) {
        bool = true;
      }
    }
    return bool;
  }
  
  private Class<?> _verifyAsClass(Object paramObject, String paramString, Class<?> paramClass)
  {
    if (paramObject == null) {}
    for (Object localObject = null;; localObject = null) {
      do
      {
        return (Class<?>)localObject;
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector." + paramString + "() returned value of type " + paramObject.getClass().getName() + ": expected type JsonSerializer or Class<JsonSerializer> instead");
        }
        localObject = (Class)paramObject;
      } while ((localObject != paramClass) && (!ClassUtil.isBogusClass((Class)localObject)));
    }
  }
  
  private JavaType modifyTypeByAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated, JavaType paramJavaType)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    Class localClass1 = localAnnotationIntrospector.findDeserializationType(paramAnnotated, paramJavaType);
    if (localClass1 != null) {}
    Class localClass2;
    try
    {
      JavaType localJavaType4 = paramDeserializationContext.getTypeFactory().constructSpecializedType(paramJavaType, localClass1);
      paramJavaType = localJavaType4;
      if (paramJavaType.isContainerType())
      {
        localClass2 = localAnnotationIntrospector.findDeserializationKeyType(paramAnnotated, paramJavaType.getKeyType());
        if (localClass2 != null) {
          if (!(paramJavaType instanceof MapLikeType)) {
            throw new JsonMappingException("Illegal key-type annotation: type " + paramJavaType + " is not a Map(-like) type");
          }
        }
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException3)
    {
      throw new JsonMappingException("Failed to narrow type " + paramJavaType + " with concrete-type annotation (value " + localClass1.getName() + "), method '" + paramAnnotated.getName() + "': " + localIllegalArgumentException3.getMessage(), null, localIllegalArgumentException3);
    }
    for (;;)
    {
      Object localObject2;
      try
      {
        JavaType localJavaType3 = ((MapLikeType)paramJavaType).narrowKey(localClass2);
        paramJavaType = localJavaType3;
        JavaType localJavaType1 = paramJavaType.getKeyType();
        if ((localJavaType1 != null) && (localJavaType1.getValueHandler() == null))
        {
          Object localObject3 = localAnnotationIntrospector.findKeyDeserializer(paramAnnotated);
          if (localObject3 != null)
          {
            KeyDeserializer localKeyDeserializer = paramDeserializationContext.keyDeserializerInstance(paramAnnotated, localObject3);
            if (localKeyDeserializer != null)
            {
              paramJavaType = ((MapLikeType)paramJavaType).withKeyValueHandler(localKeyDeserializer);
              paramJavaType.getKeyType();
            }
          }
        }
        Class localClass3 = localAnnotationIntrospector.findDeserializationContentType(paramAnnotated, paramJavaType.getContentType());
        if (localClass3 != null) {}
        JavaType localJavaType2;
        Object localObject1;
        localClass4 = _verifyAsClass(localObject1, "findContentDeserializer", JsonDeserializer.None.class);
      }
      catch (IllegalArgumentException localIllegalArgumentException2)
      {
        try
        {
          localJavaType2 = paramJavaType.narrowContentsBy(localClass3);
          paramJavaType = localJavaType2;
          if (paramJavaType.getContentType().getValueHandler() == null)
          {
            localObject1 = localAnnotationIntrospector.findContentDeserializer(paramAnnotated);
            if (localObject1 != null)
            {
              localObject2 = null;
              if (!(localObject1 instanceof JsonDeserializer)) {
                break label441;
              }
              ((JsonDeserializer)localObject1);
              if (localObject2 != null) {
                paramJavaType = paramJavaType.withContentValueHandler(localObject2);
              }
            }
          }
          return paramJavaType;
        }
        catch (IllegalArgumentException localIllegalArgumentException1)
        {
          throw new JsonMappingException("Failed to narrow content type " + paramJavaType + " with content-type annotation (" + localClass3.getName() + "): " + localIllegalArgumentException1.getMessage(), null, localIllegalArgumentException1);
        }
        localIllegalArgumentException2 = localIllegalArgumentException2;
        throw new JsonMappingException("Failed to narrow key type " + paramJavaType + " with key-type annotation (" + localClass2.getName() + "): " + localIllegalArgumentException2.getMessage(), null, localIllegalArgumentException2);
      }
      label441:
      Class localClass4;
      if (localClass4 != null) {
        localObject2 = paramDeserializationContext.deserializerInstance(paramAnnotated, localClass4);
      }
    }
  }
  
  protected JsonDeserializer<Object> _createAndCache2(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer2;
    try
    {
      JsonDeserializer localJsonDeserializer1 = _createDeserializer(paramDeserializationContext, paramDeserializerFactory, paramJavaType);
      localJsonDeserializer2 = localJsonDeserializer1;
      if (localJsonDeserializer2 == null)
      {
        localJsonDeserializer2 = null;
        return localJsonDeserializer2;
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new JsonMappingException(localIllegalArgumentException.getMessage(), null, localIllegalArgumentException);
    }
    boolean bool = localJsonDeserializer2 instanceof ResolvableDeserializer;
    if ((!_hasCustomValueHandler(paramJavaType)) && (localJsonDeserializer2.isCachable())) {}
    for (int i = 1;; i = 0)
    {
      if (bool)
      {
        this._incompleteDeserializers.put(paramJavaType, localJsonDeserializer2);
        ((ResolvableDeserializer)localJsonDeserializer2).resolve(paramDeserializationContext);
        this._incompleteDeserializers.remove(paramJavaType);
      }
      if (i == 0) {
        break;
      }
      this._cachedDeserializers.put(paramJavaType, localJsonDeserializer2);
      break;
    }
  }
  
  protected JsonDeserializer<Object> _createAndCacheValueDeserializer(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    Object localObject3;
    int i;
    synchronized (this._incompleteDeserializers)
    {
      JsonDeserializer localJsonDeserializer1 = _findCachedDeserializer(paramJavaType);
      if (localJsonDeserializer1 != null)
      {
        localObject3 = localJsonDeserializer1;
      }
      else
      {
        i = this._incompleteDeserializers.size();
        if (i > 0)
        {
          JsonDeserializer localJsonDeserializer3 = (JsonDeserializer)this._incompleteDeserializers.get(paramJavaType);
          if (localJsonDeserializer3 != null) {
            localObject3 = localJsonDeserializer3;
          }
        }
      }
    }
    label146:
    try
    {
      JsonDeserializer localJsonDeserializer2 = _createAndCache2(paramDeserializationContext, paramDeserializerFactory, paramJavaType);
      localObject3 = localJsonDeserializer2;
      if ((i == 0) && (this._incompleteDeserializers.size() > 0)) {
        this._incompleteDeserializers.clear();
      }
      break label149;
    }
    finally
    {
      if ((i != 0) || (this._incompleteDeserializers.size() <= 0)) {
        break label146;
      }
      this._incompleteDeserializers.clear();
    }
    localObject1 = finally;
    throw ((Throwable)localObject1);
    label149:
    return (JsonDeserializer<Object>)localObject3;
  }
  
  protected JsonDeserializer<Object> _createDeserializer(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    if ((paramJavaType.isAbstract()) || (paramJavaType.isMapLikeType()) || (paramJavaType.isCollectionLikeType())) {
      paramJavaType = paramDeserializerFactory.mapAbstractType(localDeserializationConfig, paramJavaType);
    }
    BeanDescription localBeanDescription = localDeserializationConfig.introspect(paramJavaType);
    Object localObject = findDeserializerFromAnnotation(paramDeserializationContext, localBeanDescription.getClassInfo());
    if (localObject != null) {}
    for (;;)
    {
      return (JsonDeserializer<Object>)localObject;
      JavaType localJavaType1 = modifyTypeByAnnotation(paramDeserializationContext, localBeanDescription.getClassInfo(), paramJavaType);
      if (localJavaType1 != paramJavaType)
      {
        paramJavaType = localJavaType1;
        localBeanDescription = localDeserializationConfig.introspect(localJavaType1);
      }
      Class localClass = localBeanDescription.findPOJOBuilder();
      if (localClass != null)
      {
        localObject = paramDeserializerFactory.createBuilderBasedDeserializer(paramDeserializationContext, paramJavaType, localBeanDescription, localClass);
      }
      else
      {
        Converter localConverter = localBeanDescription.findDeserializationConverter();
        if (localConverter == null)
        {
          localObject = _createDeserializer2(paramDeserializationContext, paramDeserializerFactory, paramJavaType, localBeanDescription);
        }
        else
        {
          JavaType localJavaType2 = localConverter.getInputType(paramDeserializationContext.getTypeFactory());
          if (!localJavaType2.hasRawClass(paramJavaType.getRawClass())) {
            localBeanDescription = localDeserializationConfig.introspect(localJavaType2);
          }
          localObject = new StdDelegatingDeserializer(localConverter, localJavaType2, _createDeserializer2(paramDeserializationContext, paramDeserializerFactory, localJavaType2, localBeanDescription));
        }
      }
    }
  }
  
  protected JsonDeserializer<?> _createDeserializer2(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    JsonDeserializer localJsonDeserializer;
    if (paramJavaType.isEnumType()) {
      localJsonDeserializer = paramDeserializerFactory.createEnumDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
    }
    for (;;)
    {
      return localJsonDeserializer;
      if (paramJavaType.isContainerType())
      {
        if (paramJavaType.isArrayType())
        {
          localJsonDeserializer = paramDeserializerFactory.createArrayDeserializer(paramDeserializationContext, (ArrayType)paramJavaType, paramBeanDescription);
          continue;
        }
        if (paramJavaType.isMapLikeType())
        {
          MapLikeType localMapLikeType = (MapLikeType)paramJavaType;
          if (localMapLikeType.isTrueMapType())
          {
            localJsonDeserializer = paramDeserializerFactory.createMapDeserializer(paramDeserializationContext, (MapType)localMapLikeType, paramBeanDescription);
            continue;
          }
          localJsonDeserializer = paramDeserializerFactory.createMapLikeDeserializer(paramDeserializationContext, localMapLikeType, paramBeanDescription);
          continue;
        }
        if (paramJavaType.isCollectionLikeType())
        {
          JsonFormat.Value localValue = paramBeanDescription.findExpectedFormat(null);
          if ((localValue == null) || (localValue.getShape() != JsonFormat.Shape.OBJECT))
          {
            CollectionLikeType localCollectionLikeType = (CollectionLikeType)paramJavaType;
            if (localCollectionLikeType.isTrueCollectionType())
            {
              localJsonDeserializer = paramDeserializerFactory.createCollectionDeserializer(paramDeserializationContext, (CollectionType)localCollectionLikeType, paramBeanDescription);
              continue;
            }
            localJsonDeserializer = paramDeserializerFactory.createCollectionLikeDeserializer(paramDeserializationContext, localCollectionLikeType, paramBeanDescription);
            continue;
          }
        }
      }
      if (JsonNode.class.isAssignableFrom(paramJavaType.getRawClass())) {
        localJsonDeserializer = paramDeserializerFactory.createTreeDeserializer(localDeserializationConfig, paramJavaType, paramBeanDescription);
      } else {
        localJsonDeserializer = paramDeserializerFactory.createBeanDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
      }
    }
  }
  
  protected JsonDeserializer<Object> _findCachedDeserializer(JavaType paramJavaType)
  {
    if (paramJavaType == null) {
      throw new IllegalArgumentException("Null JavaType passed");
    }
    if (_hasCustomValueHandler(paramJavaType)) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._cachedDeserializers.get(paramJavaType)) {
      return (JsonDeserializer<Object>)localObject;
    }
  }
  
  protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    throw new JsonMappingException("Can not find a (Map) Key deserializer for type " + paramJavaType);
  }
  
  protected JsonDeserializer<Object> _handleUnknownValueDeserializer(JavaType paramJavaType)
    throws JsonMappingException
  {
    if (!ClassUtil.isConcrete(paramJavaType.getRawClass())) {
      throw new JsonMappingException("Can not find a Value deserializer for abstract type " + paramJavaType);
    }
    throw new JsonMappingException("Can not find a Value deserializer for type " + paramJavaType);
  }
  
  public int cachedDeserializersCount()
  {
    return this._cachedDeserializers.size();
  }
  
  protected Converter<Object, Object> findConverter(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject1 = paramDeserializationContext.getAnnotationIntrospector().findDeserializationConverter(paramAnnotated);
    if (localObject1 == null) {}
    for (Object localObject2 = null;; localObject2 = paramDeserializationContext.converterInstance(paramAnnotated, localObject1)) {
      return (Converter<Object, Object>)localObject2;
    }
  }
  
  protected JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext paramDeserializationContext, Annotated paramAnnotated, JsonDeserializer<Object> paramJsonDeserializer)
    throws JsonMappingException
  {
    Converter localConverter = findConverter(paramDeserializationContext, paramAnnotated);
    if (localConverter == null) {}
    for (;;)
    {
      return paramJsonDeserializer;
      paramJsonDeserializer = new StdDelegatingDeserializer(localConverter, localConverter.getInputType(paramDeserializationContext.getTypeFactory()), paramJsonDeserializer);
    }
  }
  
  protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject1 = paramDeserializationContext.getAnnotationIntrospector().findDeserializer(paramAnnotated);
    if (localObject1 == null) {}
    for (Object localObject2 = null;; localObject2 = findConvertingDeserializer(paramDeserializationContext, paramAnnotated, paramDeserializationContext.deserializerInstance(paramAnnotated, localObject1))) {
      return (JsonDeserializer<Object>)localObject2;
    }
  }
  
  public KeyDeserializer findKeyDeserializer(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    KeyDeserializer localKeyDeserializer = paramDeserializerFactory.createKeyDeserializer(paramDeserializationContext, paramJavaType);
    if (localKeyDeserializer == null) {
      localKeyDeserializer = _handleUnknownKeyDeserializer(paramJavaType);
    }
    for (;;)
    {
      return localKeyDeserializer;
      if ((localKeyDeserializer instanceof ResolvableDeserializer)) {
        ((ResolvableDeserializer)localKeyDeserializer).resolve(paramDeserializationContext);
      }
    }
  }
  
  public JsonDeserializer<Object> findValueDeserializer(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer = _findCachedDeserializer(paramJavaType);
    if (localJsonDeserializer == null)
    {
      localJsonDeserializer = _createAndCacheValueDeserializer(paramDeserializationContext, paramDeserializerFactory, paramJavaType);
      if (localJsonDeserializer == null) {
        localJsonDeserializer = _handleUnknownValueDeserializer(paramJavaType);
      }
    }
    return localJsonDeserializer;
  }
  
  public void flushCachedDeserializers()
  {
    this._cachedDeserializers.clear();
  }
  
  public boolean hasValueDeserializerFor(DeserializationContext paramDeserializationContext, DeserializerFactory paramDeserializerFactory, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer = _findCachedDeserializer(paramJavaType);
    if (localJsonDeserializer == null) {
      localJsonDeserializer = _createAndCacheValueDeserializer(paramDeserializationContext, paramDeserializerFactory, paramJavaType);
    }
    if (localJsonDeserializer != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  Object writeReplace()
  {
    this._incompleteDeserializers.clear();
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/DeserializerCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */