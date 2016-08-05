package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class StdKeyDeserializers
  implements KeyDeserializers, Serializable
{
  private static final long serialVersionUID = 1L;
  
  public static KeyDeserializer constructDelegatingKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer)
  {
    return new StdKeyDeserializer.DelegatingKD(paramJavaType.getRawClass(), paramJsonDeserializer);
  }
  
  public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver paramEnumResolver)
  {
    return new StdKeyDeserializer.EnumKD(paramEnumResolver, null);
  }
  
  public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver paramEnumResolver, AnnotatedMethod paramAnnotatedMethod)
  {
    return new StdKeyDeserializer.EnumKD(paramEnumResolver, paramAnnotatedMethod);
  }
  
  public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    BeanDescription localBeanDescription = paramDeserializationConfig.introspect(paramJavaType);
    Class[] arrayOfClass1 = new Class[1];
    arrayOfClass1[0] = String.class;
    Constructor localConstructor = localBeanDescription.findSingleArgConstructor(arrayOfClass1);
    Object localObject;
    if (localConstructor != null)
    {
      if (paramDeserializationConfig.canOverrideAccessModifiers()) {
        ClassUtil.checkAndFixAccess(localConstructor);
      }
      localObject = new StdKeyDeserializer.StringCtorKeyDeserializer(localConstructor);
    }
    for (;;)
    {
      return (KeyDeserializer)localObject;
      Class[] arrayOfClass2 = new Class[1];
      arrayOfClass2[0] = String.class;
      Method localMethod = localBeanDescription.findFactoryMethod(arrayOfClass2);
      if (localMethod != null)
      {
        if (paramDeserializationConfig.canOverrideAccessModifiers()) {
          ClassUtil.checkAndFixAccess(localMethod);
        }
        localObject = new StdKeyDeserializer.StringFactoryKeyDeserializer(localMethod);
      }
      else
      {
        localObject = null;
      }
    }
  }
  
  public KeyDeserializer findKeyDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Class localClass = paramJavaType.getRawClass();
    if (localClass.isPrimitive()) {
      localClass = ClassUtil.wrapperType(localClass);
    }
    return StdKeyDeserializer.forType(localClass);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StdKeyDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */