package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class TypeNameIdResolver
  extends TypeIdResolverBase
{
  protected final MapperConfig<?> _config;
  protected final HashMap<String, JavaType> _idToType;
  protected final HashMap<String, String> _typeToId;
  
  protected TypeNameIdResolver(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, HashMap<String, String> paramHashMap, HashMap<String, JavaType> paramHashMap1)
  {
    super(paramJavaType, paramMapperConfig.getTypeFactory());
    this._config = paramMapperConfig;
    this._typeToId = paramHashMap;
    this._idToType = paramHashMap1;
  }
  
  protected static String _defaultTypeId(Class<?> paramClass)
  {
    String str = paramClass.getName();
    int i = str.lastIndexOf('.');
    if (i < 0) {}
    for (;;)
    {
      return str;
      str = str.substring(i + 1);
    }
  }
  
  public static TypeNameIdResolver construct(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, Collection<NamedType> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1 == paramBoolean2) {
      throw new IllegalArgumentException();
    }
    HashMap localHashMap1 = null;
    HashMap localHashMap2 = null;
    if (paramBoolean1) {
      localHashMap1 = new HashMap();
    }
    if (paramBoolean2) {
      localHashMap2 = new HashMap();
    }
    if (paramCollection != null)
    {
      Iterator localIterator = paramCollection.iterator();
      if (localIterator.hasNext())
      {
        NamedType localNamedType = (NamedType)localIterator.next();
        Class localClass = localNamedType.getType();
        if (localNamedType.hasName()) {}
        for (String str = localNamedType.getName();; str = _defaultTypeId(localClass))
        {
          if (paramBoolean1) {
            localHashMap1.put(localClass.getName(), str);
          }
          if (!paramBoolean2) {
            break;
          }
          JavaType localJavaType = (JavaType)localHashMap2.get(str);
          if ((localJavaType != null) && (localClass.isAssignableFrom(localJavaType.getRawClass()))) {
            break;
          }
          localHashMap2.put(str, paramMapperConfig.constructType(localClass));
          break;
        }
      }
    }
    return new TypeNameIdResolver(paramMapperConfig, paramJavaType, localHashMap1, localHashMap2);
  }
  
  protected JavaType _typeFromId(String paramString)
  {
    return (JavaType)this._idToType.get(paramString);
  }
  
  public String getDescForKnownTypeIds()
  {
    return new TreeSet(this._idToType.keySet()).toString();
  }
  
  public JsonTypeInfo.Id getMechanism()
  {
    return JsonTypeInfo.Id.NAME;
  }
  
  protected String idFromClass(Class<?> paramClass)
  {
    String str2;
    if (paramClass == null) {
      str2 = null;
    }
    for (;;)
    {
      return str2;
      Class localClass = this._typeFactory.constructType(paramClass).getRawClass();
      String str1 = localClass.getName();
      synchronized (this._typeToId)
      {
        str2 = (String)this._typeToId.get(str1);
        if (str2 == null)
        {
          if (this._config.isAnnotationProcessingEnabled())
          {
            BeanDescription localBeanDescription = this._config.introspectClassAnnotations(localClass);
            str2 = this._config.getAnnotationIntrospector().findTypeName(localBeanDescription.getClassInfo());
          }
          if (str2 == null) {
            str2 = _defaultTypeId(localClass);
          }
          this._typeToId.put(str1, str2);
        }
      }
    }
  }
  
  public String idFromValue(Object paramObject)
  {
    return idFromClass(paramObject.getClass());
  }
  
  public String idFromValueAndType(Object paramObject, Class<?> paramClass)
  {
    if (paramObject == null) {}
    for (String str = idFromClass(paramClass);; str = idFromValue(paramObject)) {
      return str;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[').append(getClass().getName());
    localStringBuilder.append("; id-to-type=").append(this._idToType);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public JavaType typeFromId(DatabindContext paramDatabindContext, String paramString)
  {
    return _typeFromId(paramString);
  }
  
  @Deprecated
  public JavaType typeFromId(String paramString)
  {
    return _typeFromId(paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/TypeNameIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */