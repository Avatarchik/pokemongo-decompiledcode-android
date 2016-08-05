package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnumResolver
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final Class<Enum<?>> _enumClass;
  protected final Enum<?>[] _enums;
  protected final HashMap<String, Enum<?>> _enumsById;
  
  protected EnumResolver(Class<Enum<?>> paramClass, Enum<?>[] paramArrayOfEnum, HashMap<String, Enum<?>> paramHashMap)
  {
    this._enumClass = paramClass;
    this._enums = paramArrayOfEnum;
    this._enumsById = paramHashMap;
  }
  
  public static EnumResolver constructFor(Class<Enum<?>> paramClass, AnnotationIntrospector paramAnnotationIntrospector)
  {
    Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
    if (arrayOfEnum == null) {
      throw new IllegalArgumentException("No enum constants for class " + paramClass.getName());
    }
    HashMap localHashMap = new HashMap();
    int i = arrayOfEnum.length;
    for (int j = 0; j < i; j++)
    {
      Enum localEnum = arrayOfEnum[j];
      localHashMap.put(paramAnnotationIntrospector.findEnumValue(localEnum), localEnum);
    }
    return new EnumResolver(paramClass, arrayOfEnum, localHashMap);
  }
  
  public static EnumResolver constructUnsafe(Class<?> paramClass, AnnotationIntrospector paramAnnotationIntrospector)
  {
    return constructFor(paramClass, paramAnnotationIntrospector);
  }
  
  public static EnumResolver constructUnsafeUsingMethod(Class<?> paramClass, Method paramMethod)
  {
    return constructUsingMethod(paramClass, paramMethod);
  }
  
  public static EnumResolver constructUnsafeUsingToString(Class<?> paramClass)
  {
    return constructUsingToString(paramClass);
  }
  
  public static EnumResolver constructUsingMethod(Class<Enum<?>> paramClass, Method paramMethod)
  {
    Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
    HashMap localHashMap = new HashMap();
    int i = arrayOfEnum.length;
    for (;;)
    {
      i--;
      if (i >= 0)
      {
        Enum localEnum = arrayOfEnum[i];
        try
        {
          Object localObject = paramMethod.invoke(localEnum, new Object[0]);
          if (localObject != null) {
            localHashMap.put(localObject.toString(), localEnum);
          }
        }
        catch (Exception localException)
        {
          throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + localEnum + ": " + localException.getMessage());
        }
      }
    }
    return new EnumResolver(paramClass, arrayOfEnum, localHashMap);
  }
  
  public static EnumResolver constructUsingToString(Class<Enum<?>> paramClass)
  {
    Enum[] arrayOfEnum = (Enum[])paramClass.getEnumConstants();
    HashMap localHashMap = new HashMap();
    int i = arrayOfEnum.length;
    for (;;)
    {
      i--;
      if (i < 0) {
        break;
      }
      Enum localEnum = arrayOfEnum[i];
      localHashMap.put(localEnum.toString(), localEnum);
    }
    return new EnumResolver(paramClass, arrayOfEnum, localHashMap);
  }
  
  public CompactStringObjectMap constructLookup()
  {
    return CompactStringObjectMap.construct(this._enumsById);
  }
  
  public Enum<?> findEnum(String paramString)
  {
    return (Enum)this._enumsById.get(paramString);
  }
  
  public Enum<?> getEnum(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this._enums.length)) {}
    for (Object localObject = null;; localObject = this._enums[paramInt]) {
      return (Enum<?>)localObject;
    }
  }
  
  public Class<Enum<?>> getEnumClass()
  {
    return this._enumClass;
  }
  
  public List<Enum<?>> getEnums()
  {
    ArrayList localArrayList = new ArrayList(this._enums.length);
    Enum[] arrayOfEnum = this._enums;
    int i = arrayOfEnum.length;
    for (int j = 0; j < i; j++) {
      localArrayList.add(arrayOfEnum[j]);
    }
    return localArrayList;
  }
  
  public Enum<?>[] getRawEnums()
  {
    return this._enums;
  }
  
  public int lastValidIndex()
  {
    return -1 + this._enums.length;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/EnumResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */