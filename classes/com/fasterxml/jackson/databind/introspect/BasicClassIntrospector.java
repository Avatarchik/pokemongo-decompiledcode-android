package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class BasicClassIntrospector
  extends ClassIntrospector
  implements Serializable
{
  protected static final BasicBeanDescription BOOLEAN_DESC;
  protected static final BasicBeanDescription INT_DESC;
  protected static final BasicBeanDescription LONG_DESC;
  protected static final BasicBeanDescription STRING_DESC;
  @Deprecated
  public static final BasicClassIntrospector instance = new BasicClassIntrospector();
  private static final long serialVersionUID = 1L;
  protected final LRUMap<JavaType, BasicBeanDescription> _cachedFCA = new LRUMap(16, 64);
  
  static
  {
    AnnotatedClass localAnnotatedClass1 = AnnotatedClass.constructWithoutSuperTypes(String.class, null, null);
    STRING_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(String.class), localAnnotatedClass1);
    AnnotatedClass localAnnotatedClass2 = AnnotatedClass.constructWithoutSuperTypes(Boolean.TYPE, null, null);
    BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Boolean.TYPE), localAnnotatedClass2);
    AnnotatedClass localAnnotatedClass3 = AnnotatedClass.constructWithoutSuperTypes(Integer.TYPE, null, null);
    INT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Integer.TYPE), localAnnotatedClass3);
    AnnotatedClass localAnnotatedClass4 = AnnotatedClass.constructWithoutSuperTypes(Long.TYPE, null, null);
    LONG_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Long.TYPE), localAnnotatedClass4);
  }
  
  protected BasicBeanDescription _findStdJdkCollectionDesc(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    Object localObject = null;
    if (_isStdJDKCollection(paramJavaType))
    {
      Class localClass = paramJavaType.getRawClass();
      if (paramMapperConfig.isAnnotationProcessingEnabled()) {
        localObject = paramMapperConfig.getAnnotationIntrospector();
      }
      localObject = BasicBeanDescription.forOtherUse(paramMapperConfig, paramJavaType, AnnotatedClass.construct(localClass, (AnnotationIntrospector)localObject, paramMixInResolver));
    }
    return (BasicBeanDescription)localObject;
  }
  
  protected BasicBeanDescription _findStdTypeDesc(JavaType paramJavaType)
  {
    Class localClass = paramJavaType.getRawClass();
    BasicBeanDescription localBasicBeanDescription;
    if (localClass.isPrimitive()) {
      if (localClass == Boolean.TYPE) {
        localBasicBeanDescription = BOOLEAN_DESC;
      }
    }
    for (;;)
    {
      return localBasicBeanDescription;
      if (localClass == Integer.TYPE)
      {
        localBasicBeanDescription = INT_DESC;
      }
      else
      {
        if (localClass == Long.TYPE)
        {
          localBasicBeanDescription = LONG_DESC;
          continue;
          if (localClass == String.class)
          {
            localBasicBeanDescription = STRING_DESC;
            continue;
          }
        }
        localBasicBeanDescription = null;
      }
    }
  }
  
  protected boolean _isStdJDKCollection(JavaType paramJavaType)
  {
    boolean bool = false;
    if ((!paramJavaType.isContainerType()) || (paramJavaType.isArrayType())) {}
    for (;;)
    {
      return bool;
      Class localClass = paramJavaType.getRawClass();
      Package localPackage = localClass.getPackage();
      if (localPackage != null)
      {
        String str = localPackage.getName();
        if (((str.startsWith("java.lang")) || (str.startsWith("java.util"))) && ((Collection.class.isAssignableFrom(localClass)) || (Map.class.isAssignableFrom(localClass)))) {
          bool = true;
        }
      }
    }
  }
  
  protected POJOPropertiesCollector collectProperties(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver, boolean paramBoolean, String paramString)
  {
    boolean bool = paramMapperConfig.isAnnotationProcessingEnabled();
    Class localClass = paramJavaType.getRawClass();
    if (bool) {}
    for (AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();; localAnnotationIntrospector = null) {
      return constructPropertyCollector(paramMapperConfig, AnnotatedClass.construct(localClass, localAnnotationIntrospector, paramMixInResolver), paramJavaType, paramBoolean, paramString);
    }
  }
  
  protected POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver, boolean paramBoolean)
  {
    AnnotationIntrospector localAnnotationIntrospector;
    AnnotatedClass localAnnotatedClass;
    JsonPOJOBuilder.Value localValue;
    if (paramMapperConfig.isAnnotationProcessingEnabled())
    {
      localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
      localAnnotatedClass = AnnotatedClass.construct(paramJavaType.getRawClass(), localAnnotationIntrospector, paramMixInResolver);
      if (localAnnotationIntrospector != null) {
        break label61;
      }
      localValue = null;
      label33:
      if (localValue != null) {
        break label73;
      }
    }
    label61:
    label73:
    for (String str = "with";; str = localValue.withPrefix)
    {
      return constructPropertyCollector(paramMapperConfig, localAnnotatedClass, paramJavaType, paramBoolean, str);
      localAnnotationIntrospector = null;
      break;
      localValue = localAnnotationIntrospector.findPOJOBuilderConfig(localAnnotatedClass);
      break label33;
    }
  }
  
  protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType, boolean paramBoolean, String paramString)
  {
    return new POJOPropertiesCollector(paramMapperConfig, paramBoolean, paramJavaType, paramAnnotatedClass, paramString);
  }
  
  public BasicBeanDescription forClassAnnotations(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    BasicBeanDescription localBasicBeanDescription = _findStdTypeDesc(paramJavaType);
    Class localClass;
    if (localBasicBeanDescription == null)
    {
      localBasicBeanDescription = (BasicBeanDescription)this._cachedFCA.get(paramJavaType);
      if (localBasicBeanDescription == null)
      {
        boolean bool = paramMapperConfig.isAnnotationProcessingEnabled();
        localClass = paramJavaType.getRawClass();
        if (!bool) {
          break label82;
        }
      }
    }
    label82:
    for (AnnotationIntrospector localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();; localAnnotationIntrospector = null)
    {
      localBasicBeanDescription = BasicBeanDescription.forOtherUse(paramMapperConfig, paramJavaType, AnnotatedClass.construct(localClass, localAnnotationIntrospector, paramMixInResolver));
      this._cachedFCA.put(paramJavaType, localBasicBeanDescription);
      return localBasicBeanDescription;
    }
  }
  
  public BasicBeanDescription forCreation(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    BasicBeanDescription localBasicBeanDescription = _findStdTypeDesc(paramJavaType);
    if (localBasicBeanDescription == null)
    {
      localBasicBeanDescription = _findStdJdkCollectionDesc(paramDeserializationConfig, paramJavaType, paramMixInResolver);
      if (localBasicBeanDescription == null) {
        localBasicBeanDescription = BasicBeanDescription.forDeserialization(collectProperties(paramDeserializationConfig, paramJavaType, paramMixInResolver, false, "set"));
      }
    }
    return localBasicBeanDescription;
  }
  
  public BasicBeanDescription forDeserialization(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    BasicBeanDescription localBasicBeanDescription = _findStdTypeDesc(paramJavaType);
    if (localBasicBeanDescription == null)
    {
      localBasicBeanDescription = _findStdJdkCollectionDesc(paramDeserializationConfig, paramJavaType, paramMixInResolver);
      if (localBasicBeanDescription == null) {
        localBasicBeanDescription = BasicBeanDescription.forDeserialization(collectProperties(paramDeserializationConfig, paramJavaType, paramMixInResolver, false, "set"));
      }
      this._cachedFCA.putIfAbsent(paramJavaType, localBasicBeanDescription);
    }
    return localBasicBeanDescription;
  }
  
  public BasicBeanDescription forDeserializationWithBuilder(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    BasicBeanDescription localBasicBeanDescription = BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder(paramDeserializationConfig, paramJavaType, paramMixInResolver, false));
    this._cachedFCA.putIfAbsent(paramJavaType, localBasicBeanDescription);
    return localBasicBeanDescription;
  }
  
  public BasicBeanDescription forDirectClassAnnotations(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    BasicBeanDescription localBasicBeanDescription = _findStdTypeDesc(paramJavaType);
    AnnotationIntrospector localAnnotationIntrospector;
    Class localClass;
    if (localBasicBeanDescription == null)
    {
      boolean bool = paramMapperConfig.isAnnotationProcessingEnabled();
      localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
      localClass = paramJavaType.getRawClass();
      if (!bool) {
        break label53;
      }
    }
    for (;;)
    {
      localBasicBeanDescription = BasicBeanDescription.forOtherUse(paramMapperConfig, paramJavaType, AnnotatedClass.constructWithoutSuperTypes(localClass, localAnnotationIntrospector, paramMixInResolver));
      return localBasicBeanDescription;
      label53:
      localAnnotationIntrospector = null;
    }
  }
  
  public BasicBeanDescription forSerialization(SerializationConfig paramSerializationConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver)
  {
    BasicBeanDescription localBasicBeanDescription = _findStdTypeDesc(paramJavaType);
    if (localBasicBeanDescription == null)
    {
      localBasicBeanDescription = _findStdJdkCollectionDesc(paramSerializationConfig, paramJavaType, paramMixInResolver);
      if (localBasicBeanDescription == null) {
        localBasicBeanDescription = BasicBeanDescription.forSerialization(collectProperties(paramSerializationConfig, paramJavaType, paramMixInResolver, true, "set"));
      }
      this._cachedFCA.putIfAbsent(paramJavaType, localBasicBeanDescription);
    }
    return localBasicBeanDescription;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/BasicClassIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */