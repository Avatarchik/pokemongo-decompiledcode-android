package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SimpleDeserializers
  implements Deserializers, Serializable
{
  private static final long serialVersionUID = -3006673354353448880L;
  protected HashMap<ClassKey, JsonDeserializer<?>> _classMappings = null;
  protected boolean _hasEnumDeserializer = false;
  
  public SimpleDeserializers() {}
  
  public SimpleDeserializers(Map<Class<?>, JsonDeserializer<?>> paramMap)
  {
    addDeserializers(paramMap);
  }
  
  public <T> void addDeserializer(Class<T> paramClass, JsonDeserializer<? extends T> paramJsonDeserializer)
  {
    ClassKey localClassKey = new ClassKey(paramClass);
    if (this._classMappings == null) {
      this._classMappings = new HashMap();
    }
    this._classMappings.put(localClassKey, paramJsonDeserializer);
    if (paramClass == Enum.class) {
      this._hasEnumDeserializer = true;
    }
  }
  
  public void addDeserializers(Map<Class<?>, JsonDeserializer<?>> paramMap)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      addDeserializer((Class)localEntry.getKey(), (JsonDeserializer)localEntry.getValue());
    }
  }
  
  public JsonDeserializer<?> findArrayDeserializer(ArrayType paramArrayType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    if (this._classMappings == null) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(paramArrayType.getRawClass()))) {
      return (JsonDeserializer<?>)localObject;
    }
  }
  
  public JsonDeserializer<?> findBeanDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if (this._classMappings == null) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(paramJavaType.getRawClass()))) {
      return (JsonDeserializer<?>)localObject;
    }
  }
  
  public JsonDeserializer<?> findCollectionDeserializer(CollectionType paramCollectionType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    if (this._classMappings == null) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(paramCollectionType.getRawClass()))) {
      return (JsonDeserializer<?>)localObject;
    }
  }
  
  public JsonDeserializer<?> findCollectionLikeDeserializer(CollectionLikeType paramCollectionLikeType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    if (this._classMappings == null) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(paramCollectionLikeType.getRawClass()))) {
      return (JsonDeserializer<?>)localObject;
    }
  }
  
  public JsonDeserializer<?> findEnumDeserializer(Class<?> paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if (this._classMappings == null) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(Enum.class))) {
      do
      {
        return (JsonDeserializer<?>)localObject;
        localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(paramClass));
      } while ((localObject != null) || (!this._hasEnumDeserializer) || (!paramClass.isEnum()));
    }
  }
  
  public JsonDeserializer<?> findMapDeserializer(MapType paramMapType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    if (this._classMappings == null) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(paramMapType.getRawClass()))) {
      return (JsonDeserializer<?>)localObject;
    }
  }
  
  public JsonDeserializer<?> findMapLikeDeserializer(MapLikeType paramMapLikeType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    if (this._classMappings == null) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(paramMapLikeType.getRawClass()))) {
      return (JsonDeserializer<?>)localObject;
    }
  }
  
  public JsonDeserializer<?> findTreeNodeDeserializer(Class<? extends JsonNode> paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if (this._classMappings == null) {}
    for (Object localObject = null;; localObject = (JsonDeserializer)this._classMappings.get(new ClassKey(paramClass))) {
      return (JsonDeserializer<?>)localObject;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/module/SimpleDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */