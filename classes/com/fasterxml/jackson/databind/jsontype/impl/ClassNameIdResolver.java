package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.util.EnumMap;
import java.util.EnumSet;

public class ClassNameIdResolver
  extends TypeIdResolverBase
{
  public ClassNameIdResolver(JavaType paramJavaType, TypeFactory paramTypeFactory)
  {
    super(paramJavaType, paramTypeFactory);
  }
  
  protected final String _idFrom(Object paramObject, Class<?> paramClass)
  {
    if ((Enum.class.isAssignableFrom(paramClass)) && (!paramClass.isEnum())) {
      paramClass = paramClass.getSuperclass();
    }
    String str1 = paramClass.getName();
    if (str1.startsWith("java.util")) {
      if ((paramObject instanceof EnumSet))
      {
        Class localClass2 = ClassUtil.findEnumType((EnumSet)paramObject);
        str1 = TypeFactory.defaultInstance().constructCollectionType(EnumSet.class, localClass2).toCanonical();
      }
    }
    for (;;)
    {
      return str1;
      if ((paramObject instanceof EnumMap))
      {
        Class localClass1 = ClassUtil.findEnumType((EnumMap)paramObject);
        str1 = TypeFactory.defaultInstance().constructMapType(EnumMap.class, localClass1, Object.class).toCanonical();
      }
      else
      {
        String str2 = str1.substring(9);
        if (((str2.startsWith(".Arrays$")) || (str2.startsWith(".Collections$"))) && (str1.indexOf("List") >= 0))
        {
          str1 = "java.util.ArrayList";
          continue;
          if ((str1.indexOf('$') >= 0) && (ClassUtil.getOuterClass(paramClass) != null) && (ClassUtil.getOuterClass(this._baseType.getRawClass()) == null)) {
            str1 = this._baseType.getRawClass().getName();
          }
        }
      }
    }
  }
  
  protected JavaType _typeFromId(String paramString, TypeFactory paramTypeFactory)
  {
    Object localObject;
    if (paramString.indexOf('<') > 0) {
      localObject = paramTypeFactory.constructFromCanonical(paramString);
    }
    for (;;)
    {
      return (JavaType)localObject;
      try
      {
        Class localClass = paramTypeFactory.findClass(paramString);
        JavaType localJavaType = paramTypeFactory.constructSpecializedType(this._baseType, localClass);
        localObject = localJavaType;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new IllegalArgumentException("Invalid type id '" + paramString + "' (for id type 'Id.class'): no such class found");
      }
      catch (Exception localException)
      {
        throw new IllegalArgumentException("Invalid type id '" + paramString + "' (for id type 'Id.class'): " + localException.getMessage(), localException);
      }
    }
  }
  
  public String getDescForKnownTypeIds()
  {
    return "class name used as type id";
  }
  
  public JsonTypeInfo.Id getMechanism()
  {
    return JsonTypeInfo.Id.CLASS;
  }
  
  public String idFromValue(Object paramObject)
  {
    return _idFrom(paramObject, paramObject.getClass());
  }
  
  public String idFromValueAndType(Object paramObject, Class<?> paramClass)
  {
    return _idFrom(paramObject, paramClass);
  }
  
  public void registerSubtype(Class<?> paramClass, String paramString) {}
  
  public JavaType typeFromId(DatabindContext paramDatabindContext, String paramString)
  {
    return _typeFromId(paramString, paramDatabindContext.getTypeFactory());
  }
  
  @Deprecated
  public JavaType typeFromId(String paramString)
  {
    return _typeFromId(paramString, this._typeFactory);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/ClassNameIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */