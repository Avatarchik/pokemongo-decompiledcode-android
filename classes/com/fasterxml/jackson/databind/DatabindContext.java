package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DatabindContext
{
  public final boolean canOverrideAccessModifiers()
  {
    return getConfig().canOverrideAccessModifiers();
  }
  
  public JavaType constructSpecializedType(JavaType paramJavaType, Class<?> paramClass)
  {
    if (paramJavaType.getRawClass() == paramClass) {}
    for (;;)
    {
      return paramJavaType;
      paramJavaType = getConfig().constructSpecializedType(paramJavaType, paramClass);
    }
  }
  
  public JavaType constructType(Type paramType)
  {
    return getTypeFactory().constructType(paramType);
  }
  
  public Converter<Object, Object> converterInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    Converter localConverter = null;
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    Class localClass;
    for (;;)
    {
      return (Converter<Object, Object>)localObject;
      if ((paramObject instanceof Converter))
      {
        localObject = (Converter)paramObject;
      }
      else
      {
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + paramObject.getClass().getName() + "; expected type Converter or Class<Converter> instead");
        }
        localClass = (Class)paramObject;
        if ((localClass != Converter.None.class) && (!ClassUtil.isBogusClass(localClass))) {
          break;
        }
        localObject = null;
      }
    }
    if (!Converter.class.isAssignableFrom(localClass)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<Converter>");
    }
    MapperConfig localMapperConfig = getConfig();
    HandlerInstantiator localHandlerInstantiator = localMapperConfig.getHandlerInstantiator();
    if (localHandlerInstantiator == null) {}
    for (;;)
    {
      if (localConverter == null) {
        localConverter = (Converter)ClassUtil.createInstance(localClass, localMapperConfig.canOverrideAccessModifiers());
      }
      localObject = localConverter;
      break;
      localConverter = localHandlerInstantiator.converterInstance(localMapperConfig, paramAnnotated, localClass);
    }
  }
  
  public abstract Class<?> getActiveView();
  
  public abstract AnnotationIntrospector getAnnotationIntrospector();
  
  public abstract Object getAttribute(Object paramObject);
  
  public abstract MapperConfig<?> getConfig();
  
  public abstract Locale getLocale();
  
  public abstract TimeZone getTimeZone();
  
  public abstract TypeFactory getTypeFactory();
  
  public final boolean isEnabled(MapperFeature paramMapperFeature)
  {
    return getConfig().isEnabled(paramMapperFeature);
  }
  
  public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
    throws JsonMappingException
  {
    Class localClass = paramObjectIdInfo.getGeneratorType();
    MapperConfig localMapperConfig = getConfig();
    HandlerInstantiator localHandlerInstantiator = localMapperConfig.getHandlerInstantiator();
    if (localHandlerInstantiator == null) {}
    for (ObjectIdGenerator localObjectIdGenerator = null;; localObjectIdGenerator = localHandlerInstantiator.objectIdGeneratorInstance(localMapperConfig, paramAnnotated, localClass))
    {
      if (localObjectIdGenerator == null) {
        localObjectIdGenerator = (ObjectIdGenerator)ClassUtil.createInstance(localClass, localMapperConfig.canOverrideAccessModifiers());
      }
      return localObjectIdGenerator.forScope(paramObjectIdInfo.getScope());
    }
  }
  
  public ObjectIdResolver objectIdResolverInstance(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
  {
    Class localClass = paramObjectIdInfo.getResolverType();
    MapperConfig localMapperConfig = getConfig();
    HandlerInstantiator localHandlerInstantiator = localMapperConfig.getHandlerInstantiator();
    if (localHandlerInstantiator == null) {}
    for (ObjectIdResolver localObjectIdResolver = null;; localObjectIdResolver = localHandlerInstantiator.resolverIdGeneratorInstance(localMapperConfig, paramAnnotated, localClass))
    {
      if (localObjectIdResolver == null) {
        localObjectIdResolver = (ObjectIdResolver)ClassUtil.createInstance(localClass, localMapperConfig.canOverrideAccessModifiers());
      }
      return localObjectIdResolver;
    }
  }
  
  public abstract DatabindContext setAttribute(Object paramObject1, Object paramObject2);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/DatabindContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */