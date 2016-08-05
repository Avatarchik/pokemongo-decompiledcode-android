package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;

public class RootNameLookup
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected transient LRUMap<ClassKey, PropertyName> _rootNames = new LRUMap(20, 200);
  
  public PropertyName findRootName(JavaType paramJavaType, MapperConfig<?> paramMapperConfig)
  {
    return findRootName(paramJavaType.getRawClass(), paramMapperConfig);
  }
  
  public PropertyName findRootName(Class<?> paramClass, MapperConfig<?> paramMapperConfig)
  {
    ClassKey localClassKey = new ClassKey(paramClass);
    PropertyName localPropertyName1 = (PropertyName)this._rootNames.get(localClassKey);
    if (localPropertyName1 != null) {}
    PropertyName localPropertyName2;
    for (Object localObject = localPropertyName1;; localObject = localPropertyName2)
    {
      return (PropertyName)localObject;
      BeanDescription localBeanDescription = paramMapperConfig.introspectClassAnnotations(paramClass);
      localPropertyName2 = paramMapperConfig.getAnnotationIntrospector().findRootName(localBeanDescription.getClassInfo());
      if ((localPropertyName2 == null) || (!localPropertyName2.hasSimpleName())) {
        localPropertyName2 = PropertyName.construct(paramClass.getSimpleName());
      }
      this._rootNames.put(localClassKey, localPropertyName2);
    }
  }
  
  protected Object readResolve()
  {
    return new RootNameLookup();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/RootNameLookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */