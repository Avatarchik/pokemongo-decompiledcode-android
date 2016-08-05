package com.google.gson.reflect;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal..Gson.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken<T>
{
  final int hashCode;
  final Class<? super T> rawType;
  final Type type;
  
  protected TypeToken()
  {
    this.type = getSuperclassTypeParameter(getClass());
    this.rawType = .Gson.Types.getRawType(this.type);
    this.hashCode = this.type.hashCode();
  }
  
  TypeToken(Type paramType)
  {
    this.type = .Gson.Types.canonicalize((Type).Gson.Preconditions.checkNotNull(paramType));
    this.rawType = .Gson.Types.getRawType(this.type);
    this.hashCode = this.type.hashCode();
  }
  
  private static AssertionError buildUnexpectedTypeError(Type paramType, Class<?>... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder("Unexpected type. Expected one of: ");
    int i = paramVarArgs.length;
    for (int j = 0; j < i; j++) {
      localStringBuilder.append(paramVarArgs[j].getName()).append(", ");
    }
    localStringBuilder.append("but got: ").append(paramType.getClass().getName()).append(", for type token: ").append(paramType.toString()).append('.');
    return new AssertionError(localStringBuilder.toString());
  }
  
  public static <T> TypeToken<T> get(Class<T> paramClass)
  {
    return new TypeToken(paramClass);
  }
  
  public static TypeToken<?> get(Type paramType)
  {
    return new TypeToken(paramType);
  }
  
  static Type getSuperclassTypeParameter(Class<?> paramClass)
  {
    Type localType = paramClass.getGenericSuperclass();
    if ((localType instanceof Class)) {
      throw new RuntimeException("Missing type parameter.");
    }
    return .Gson.Types.canonicalize(((ParameterizedType)localType).getActualTypeArguments()[0]);
  }
  
  private static boolean isAssignableFrom(Type paramType, GenericArrayType paramGenericArrayType)
  {
    Type localType = paramGenericArrayType.getGenericComponentType();
    Object localObject;
    if ((localType instanceof ParameterizedType))
    {
      localObject = paramType;
      if ((paramType instanceof GenericArrayType)) {
        localObject = ((GenericArrayType)paramType).getGenericComponentType();
      }
    }
    for (boolean bool = isAssignableFrom((Type)localObject, (ParameterizedType)localType, new HashMap());; bool = true)
    {
      return bool;
      if (!(paramType instanceof Class)) {
        break;
      }
      for (Class localClass = (Class)paramType; localClass.isArray(); localClass = localClass.getComponentType()) {}
      localObject = localClass;
      break;
    }
  }
  
  private static boolean isAssignableFrom(Type paramType, ParameterizedType paramParameterizedType, Map<String, Type> paramMap)
  {
    boolean bool;
    if (paramType == null) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      if (paramParameterizedType.equals(paramType))
      {
        bool = true;
      }
      else
      {
        Class localClass = .Gson.Types.getRawType(paramType);
        ParameterizedType localParameterizedType = null;
        if ((paramType instanceof ParameterizedType)) {
          localParameterizedType = (ParameterizedType)paramType;
        }
        if (localParameterizedType != null)
        {
          Type[] arrayOfType2 = localParameterizedType.getActualTypeArguments();
          TypeVariable[] arrayOfTypeVariable = localClass.getTypeParameters();
          for (int k = 0; k < arrayOfType2.length; k++)
          {
            Type localType = arrayOfType2[k];
            TypeVariable localTypeVariable = arrayOfTypeVariable[k];
            while ((localType instanceof TypeVariable)) {
              localType = (Type)paramMap.get(((TypeVariable)localType).getName());
            }
            paramMap.put(localTypeVariable.getName(), localType);
          }
          if (typeEquals(localParameterizedType, paramParameterizedType, paramMap))
          {
            bool = true;
            continue;
          }
        }
        Type[] arrayOfType1 = localClass.getGenericInterfaces();
        int i = arrayOfType1.length;
        for (int j = 0;; j++)
        {
          if (j >= i) {
            break label213;
          }
          if (isAssignableFrom(arrayOfType1[j], paramParameterizedType, new HashMap(paramMap)))
          {
            bool = true;
            break;
          }
        }
        label213:
        bool = isAssignableFrom(localClass.getGenericSuperclass(), paramParameterizedType, new HashMap(paramMap));
      }
    }
  }
  
  private static boolean matches(Type paramType1, Type paramType2, Map<String, Type> paramMap)
  {
    if ((paramType2.equals(paramType1)) || (((paramType1 instanceof TypeVariable)) && (paramType2.equals(paramMap.get(((TypeVariable)paramType1).getName()))))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean typeEquals(ParameterizedType paramParameterizedType1, ParameterizedType paramParameterizedType2, Map<String, Type> paramMap)
  {
    boolean bool = false;
    int i;
    if (paramParameterizedType1.getRawType().equals(paramParameterizedType2.getRawType()))
    {
      Type[] arrayOfType1 = paramParameterizedType1.getActualTypeArguments();
      Type[] arrayOfType2 = paramParameterizedType2.getActualTypeArguments();
      i = 0;
      if (i >= arrayOfType1.length) {
        break label72;
      }
      if (matches(arrayOfType1[i], arrayOfType2[i], paramMap)) {
        break label66;
      }
    }
    for (;;)
    {
      return bool;
      label66:
      i++;
      break;
      label72:
      bool = true;
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (((paramObject instanceof TypeToken)) && (.Gson.Types.equals(this.type, ((TypeToken)paramObject).type))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final Class<? super T> getRawType()
  {
    return this.rawType;
  }
  
  public final Type getType()
  {
    return this.type;
  }
  
  public final int hashCode()
  {
    return this.hashCode;
  }
  
  @Deprecated
  public boolean isAssignableFrom(TypeToken<?> paramTypeToken)
  {
    return isAssignableFrom(paramTypeToken.getType());
  }
  
  @Deprecated
  public boolean isAssignableFrom(Class<?> paramClass)
  {
    return isAssignableFrom(paramClass);
  }
  
  @Deprecated
  public boolean isAssignableFrom(Type paramType)
  {
    boolean bool1 = false;
    if (paramType == null) {}
    for (;;)
    {
      return bool1;
      if (this.type.equals(paramType))
      {
        bool1 = true;
      }
      else if ((this.type instanceof Class))
      {
        bool1 = this.rawType.isAssignableFrom(.Gson.Types.getRawType(paramType));
      }
      else
      {
        if (!(this.type instanceof ParameterizedType)) {
          break;
        }
        bool1 = isAssignableFrom(paramType, (ParameterizedType)this.type, new HashMap());
      }
    }
    if ((this.type instanceof GenericArrayType))
    {
      if ((this.rawType.isAssignableFrom(.Gson.Types.getRawType(paramType))) && (isAssignableFrom(paramType, (GenericArrayType)this.type))) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        bool1 = bool2;
        break;
      }
    }
    Type localType = this.type;
    Class[] arrayOfClass = new Class[3];
    arrayOfClass[bool1] = Class.class;
    arrayOfClass[1] = ParameterizedType.class;
    arrayOfClass[2] = GenericArrayType.class;
    throw buildUnexpectedTypeError(localType, arrayOfClass);
  }
  
  public final String toString()
  {
    return .Gson.Types.typeToString(this.type);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/reflect/TypeToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */