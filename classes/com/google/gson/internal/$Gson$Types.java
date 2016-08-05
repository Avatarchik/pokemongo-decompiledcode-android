package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class $Gson$Types
{
  static final Type[] EMPTY_TYPE_ARRAY = new Type[0];
  
  public static GenericArrayType arrayOf(Type paramType)
  {
    return new GenericArrayTypeImpl(paramType);
  }
  
  public static Type canonicalize(Type paramType)
  {
    Class localClass;
    Object localObject2;
    Object localObject1;
    if ((paramType instanceof Class))
    {
      localClass = (Class)paramType;
      if (localClass.isArray())
      {
        localObject2 = new GenericArrayTypeImpl(canonicalize(localClass.getComponentType()));
        localObject1 = (Type)localObject2;
      }
    }
    for (;;)
    {
      return (Type)localObject1;
      localObject2 = localClass;
      break;
      if ((paramType instanceof ParameterizedType))
      {
        ParameterizedType localParameterizedType = (ParameterizedType)paramType;
        localObject1 = new ParameterizedTypeImpl(localParameterizedType.getOwnerType(), localParameterizedType.getRawType(), localParameterizedType.getActualTypeArguments());
      }
      else if ((paramType instanceof GenericArrayType))
      {
        localObject1 = new GenericArrayTypeImpl(((GenericArrayType)paramType).getGenericComponentType());
      }
      else if ((paramType instanceof WildcardType))
      {
        WildcardType localWildcardType = (WildcardType)paramType;
        localObject1 = new WildcardTypeImpl(localWildcardType.getUpperBounds(), localWildcardType.getLowerBounds());
      }
      else
      {
        localObject1 = paramType;
      }
    }
  }
  
  private static void checkNotPrimitive(Type paramType)
  {
    if ((!(paramType instanceof Class)) || (!((Class)paramType).isPrimitive())) {}
    for (boolean bool = true;; bool = false)
    {
      .Gson.Preconditions.checkArgument(bool);
      return;
    }
  }
  
  private static Class<?> declaringClassOf(TypeVariable<?> paramTypeVariable)
  {
    GenericDeclaration localGenericDeclaration = paramTypeVariable.getGenericDeclaration();
    if ((localGenericDeclaration instanceof Class)) {}
    for (Class localClass = (Class)localGenericDeclaration;; localClass = null) {
      return localClass;
    }
  }
  
  static boolean equal(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean equals(Type paramType1, Type paramType2)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramType1 == paramType2) {
      bool2 = bool1;
    }
    do
    {
      do
      {
        for (;;)
        {
          return bool2;
          if ((paramType1 instanceof Class))
          {
            bool2 = paramType1.equals(paramType2);
          }
          else if ((paramType1 instanceof ParameterizedType))
          {
            if ((paramType2 instanceof ParameterizedType))
            {
              ParameterizedType localParameterizedType1 = (ParameterizedType)paramType1;
              ParameterizedType localParameterizedType2 = (ParameterizedType)paramType2;
              if ((equal(localParameterizedType1.getOwnerType(), localParameterizedType2.getOwnerType())) && (localParameterizedType1.getRawType().equals(localParameterizedType2.getRawType())) && (Arrays.equals(localParameterizedType1.getActualTypeArguments(), localParameterizedType2.getActualTypeArguments()))) {}
              for (;;)
              {
                bool2 = bool1;
                break;
                bool1 = false;
              }
            }
          }
          else
          {
            if (!(paramType1 instanceof GenericArrayType)) {
              break;
            }
            if ((paramType2 instanceof GenericArrayType))
            {
              GenericArrayType localGenericArrayType1 = (GenericArrayType)paramType1;
              GenericArrayType localGenericArrayType2 = (GenericArrayType)paramType2;
              bool2 = equals(localGenericArrayType1.getGenericComponentType(), localGenericArrayType2.getGenericComponentType());
            }
          }
        }
        if (!(paramType1 instanceof WildcardType)) {
          break;
        }
      } while (!(paramType2 instanceof WildcardType));
      WildcardType localWildcardType1 = (WildcardType)paramType1;
      WildcardType localWildcardType2 = (WildcardType)paramType2;
      if ((Arrays.equals(localWildcardType1.getUpperBounds(), localWildcardType2.getUpperBounds())) && (Arrays.equals(localWildcardType1.getLowerBounds(), localWildcardType2.getLowerBounds()))) {}
      for (;;)
      {
        bool2 = bool1;
        break;
        bool1 = false;
      }
    } while ((!(paramType1 instanceof TypeVariable)) || (!(paramType2 instanceof TypeVariable)));
    TypeVariable localTypeVariable1 = (TypeVariable)paramType1;
    TypeVariable localTypeVariable2 = (TypeVariable)paramType2;
    if ((localTypeVariable1.getGenericDeclaration() == localTypeVariable2.getGenericDeclaration()) && (localTypeVariable1.getName().equals(localTypeVariable2.getName()))) {}
    for (;;)
    {
      bool2 = bool1;
      break;
      bool1 = false;
    }
  }
  
  public static Type getArrayComponentType(Type paramType)
  {
    if ((paramType instanceof GenericArrayType)) {}
    for (Object localObject = ((GenericArrayType)paramType).getGenericComponentType();; localObject = ((Class)paramType).getComponentType()) {
      return (Type)localObject;
    }
  }
  
  public static Type getCollectionElementType(Type paramType, Class<?> paramClass)
  {
    Type localType = getSupertype(paramType, paramClass, Collection.class);
    if ((localType instanceof WildcardType)) {
      localType = ((WildcardType)localType).getUpperBounds()[0];
    }
    if ((localType instanceof ParameterizedType)) {}
    for (Object localObject = ((ParameterizedType)localType).getActualTypeArguments()[0];; localObject = Object.class) {
      return (Type)localObject;
    }
  }
  
  static Type getGenericSupertype(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2 == paramClass1) {}
    for (;;)
    {
      return paramType;
      if (paramClass2.isInterface())
      {
        Class[] arrayOfClass = paramClass1.getInterfaces();
        int i = 0;
        int j = arrayOfClass.length;
        for (;;)
        {
          if (i >= j) {
            break label93;
          }
          if (arrayOfClass[i] == paramClass2)
          {
            paramType = paramClass1.getGenericInterfaces()[i];
            break;
          }
          if (paramClass2.isAssignableFrom(arrayOfClass[i]))
          {
            paramType = getGenericSupertype(paramClass1.getGenericInterfaces()[i], arrayOfClass[i], paramClass2);
            break;
          }
          i++;
        }
      }
      label93:
      if (!paramClass1.isInterface()) {
        for (;;)
        {
          if (paramClass1 == Object.class) {
            break label150;
          }
          Class localClass = paramClass1.getSuperclass();
          if (localClass == paramClass2)
          {
            paramType = paramClass1.getGenericSuperclass();
            break;
          }
          if (paramClass2.isAssignableFrom(localClass))
          {
            paramType = getGenericSupertype(paramClass1.getGenericSuperclass(), localClass, paramClass2);
            break;
          }
          paramClass1 = localClass;
        }
      }
      label150:
      paramType = paramClass2;
    }
  }
  
  public static Type[] getMapKeyAndValueTypes(Type paramType, Class<?> paramClass)
  {
    Type[] arrayOfType;
    if (paramType == Properties.class)
    {
      arrayOfType = new Type[2];
      arrayOfType[0] = String.class;
      arrayOfType[1] = String.class;
    }
    for (;;)
    {
      return arrayOfType;
      Type localType = getSupertype(paramType, paramClass, Map.class);
      if ((localType instanceof ParameterizedType))
      {
        arrayOfType = ((ParameterizedType)localType).getActualTypeArguments();
      }
      else
      {
        arrayOfType = new Type[2];
        arrayOfType[0] = Object.class;
        arrayOfType[1] = Object.class;
      }
    }
  }
  
  public static Class<?> getRawType(Type paramType)
  {
    Class localClass;
    if ((paramType instanceof Class)) {
      localClass = (Class)paramType;
    }
    for (;;)
    {
      return localClass;
      if ((paramType instanceof ParameterizedType))
      {
        Type localType = ((ParameterizedType)paramType).getRawType();
        .Gson.Preconditions.checkArgument(localType instanceof Class);
        localClass = (Class)localType;
      }
      else if ((paramType instanceof GenericArrayType))
      {
        localClass = Array.newInstance(getRawType(((GenericArrayType)paramType).getGenericComponentType()), 0).getClass();
      }
      else if ((paramType instanceof TypeVariable))
      {
        localClass = Object.class;
      }
      else
      {
        if (!(paramType instanceof WildcardType)) {
          break;
        }
        localClass = getRawType(((WildcardType)paramType).getUpperBounds()[0]);
      }
    }
    if (paramType == null) {}
    for (String str = "null";; str = paramType.getClass().getName()) {
      throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + paramType + "> is of type " + str);
    }
  }
  
  static Type getSupertype(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    .Gson.Preconditions.checkArgument(paramClass2.isAssignableFrom(paramClass1));
    return resolve(paramType, paramClass1, getGenericSupertype(paramType, paramClass1, paramClass2));
  }
  
  private static int hashCodeOrZero(Object paramObject)
  {
    if (paramObject != null) {}
    for (int i = paramObject.hashCode();; i = 0) {
      return i;
    }
  }
  
  private static int indexOf(Object[] paramArrayOfObject, Object paramObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++) {
      if (paramObject.equals(paramArrayOfObject[i])) {
        return i;
      }
    }
    throw new NoSuchElementException();
  }
  
  public static ParameterizedType newParameterizedTypeWithOwner(Type paramType1, Type paramType2, Type... paramVarArgs)
  {
    return new ParameterizedTypeImpl(paramType1, paramType2, paramVarArgs);
  }
  
  public static Type resolve(Type paramType1, Class<?> paramClass, Type paramType2)
  {
    Object localObject1;
    while ((paramType2 instanceof TypeVariable))
    {
      TypeVariable localTypeVariable = (TypeVariable)paramType2;
      paramType2 = resolveTypeVariable(paramType1, paramClass, localTypeVariable);
      if (paramType2 == localTypeVariable) {
        localObject1 = paramType2;
      }
    }
    for (;;)
    {
      return (Type)localObject1;
      if (((paramType2 instanceof Class)) && (((Class)paramType2).isArray()))
      {
        Object localObject2 = (Class)paramType2;
        Class localClass = ((Class)localObject2).getComponentType();
        Type localType8 = resolve(paramType1, paramClass, localClass);
        if (localClass == localType8) {}
        for (;;)
        {
          localObject1 = localObject2;
          break;
          localObject2 = arrayOf(localType8);
        }
      }
      if ((paramType2 instanceof GenericArrayType))
      {
        localObject1 = (GenericArrayType)paramType2;
        Type localType6 = ((GenericArrayType)localObject1).getGenericComponentType();
        Type localType7 = resolve(paramType1, paramClass, localType6);
        if (localType6 != localType7) {
          localObject1 = arrayOf(localType7);
        }
      }
      else if ((paramType2 instanceof ParameterizedType))
      {
        localObject1 = (ParameterizedType)paramType2;
        Type localType3 = ((ParameterizedType)localObject1).getOwnerType();
        Type localType4 = resolve(paramType1, paramClass, localType3);
        if (localType4 != localType3) {}
        Type[] arrayOfType3;
        for (int i = 1;; i = 0)
        {
          arrayOfType3 = ((ParameterizedType)localObject1).getActualTypeArguments();
          int j = 0;
          int k = arrayOfType3.length;
          while (j < k)
          {
            Type localType5 = resolve(paramType1, paramClass, arrayOfType3[j]);
            if (localType5 != arrayOfType3[j])
            {
              if (i == 0)
              {
                arrayOfType3 = (Type[])arrayOfType3.clone();
                i = 1;
              }
              arrayOfType3[j] = localType5;
            }
            j++;
          }
        }
        if (i != 0) {
          localObject1 = newParameterizedTypeWithOwner(localType4, ((ParameterizedType)localObject1).getRawType(), arrayOfType3);
        }
      }
      else if ((paramType2 instanceof WildcardType))
      {
        localObject1 = (WildcardType)paramType2;
        Type[] arrayOfType1 = ((WildcardType)localObject1).getLowerBounds();
        Type[] arrayOfType2 = ((WildcardType)localObject1).getUpperBounds();
        if (arrayOfType1.length == 1)
        {
          Type localType2 = resolve(paramType1, paramClass, arrayOfType1[0]);
          if (localType2 != arrayOfType1[0]) {
            localObject1 = supertypeOf(localType2);
          }
        }
        else if (arrayOfType2.length == 1)
        {
          Type localType1 = resolve(paramType1, paramClass, arrayOfType2[0]);
          if (localType1 != arrayOfType2[0]) {
            localObject1 = subtypeOf(localType1);
          }
        }
      }
      else
      {
        localObject1 = paramType2;
      }
    }
  }
  
  static Type resolveTypeVariable(Type paramType, Class<?> paramClass, TypeVariable<?> paramTypeVariable)
  {
    Class localClass = declaringClassOf(paramTypeVariable);
    if (localClass == null) {}
    for (;;)
    {
      return paramTypeVariable;
      Type localType = getGenericSupertype(paramType, paramClass, localClass);
      if ((localType instanceof ParameterizedType))
      {
        int i = indexOf(localClass.getTypeParameters(), paramTypeVariable);
        paramTypeVariable = ((ParameterizedType)localType).getActualTypeArguments()[i];
      }
    }
  }
  
  public static WildcardType subtypeOf(Type paramType)
  {
    Type[] arrayOfType = new Type[1];
    arrayOfType[0] = paramType;
    return new WildcardTypeImpl(arrayOfType, EMPTY_TYPE_ARRAY);
  }
  
  public static WildcardType supertypeOf(Type paramType)
  {
    Type[] arrayOfType1 = new Type[1];
    arrayOfType1[0] = Object.class;
    Type[] arrayOfType2 = new Type[1];
    arrayOfType2[0] = paramType;
    return new WildcardTypeImpl(arrayOfType1, arrayOfType2);
  }
  
  public static String typeToString(Type paramType)
  {
    if ((paramType instanceof Class)) {}
    for (String str = ((Class)paramType).getName();; str = paramType.toString()) {
      return str;
    }
  }
  
  private static final class WildcardTypeImpl
    implements WildcardType, Serializable
  {
    private static final long serialVersionUID;
    private final Type lowerBound;
    private final Type upperBound;
    
    public WildcardTypeImpl(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
    {
      if (paramArrayOfType2.length <= i)
      {
        int k = i;
        .Gson.Preconditions.checkArgument(k);
        if (paramArrayOfType1.length != i) {
          break label88;
        }
        int n = i;
        label29:
        .Gson.Preconditions.checkArgument(n);
        if (paramArrayOfType2.length != i) {
          break label99;
        }
        .Gson.Preconditions.checkNotNull(paramArrayOfType2[0]);
        .Gson.Types.checkNotPrimitive(paramArrayOfType2[0]);
        if (paramArrayOfType1[0] != Object.class) {
          break label94;
        }
        label61:
        .Gson.Preconditions.checkArgument(i);
        this.lowerBound = .Gson.Types.canonicalize(paramArrayOfType2[0]);
      }
      for (this.upperBound = Object.class;; this.upperBound = .Gson.Types.canonicalize(paramArrayOfType1[0]))
      {
        return;
        int m = 0;
        break;
        label88:
        int i1 = 0;
        break label29;
        label94:
        int j = 0;
        break label61;
        label99:
        .Gson.Preconditions.checkNotNull(paramArrayOfType1[0]);
        .Gson.Types.checkNotPrimitive(paramArrayOfType1[0]);
        this.lowerBound = null;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (((paramObject instanceof WildcardType)) && (.Gson.Types.equals(this, (WildcardType)paramObject))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Type[] getLowerBounds()
    {
      Type[] arrayOfType;
      if (this.lowerBound != null)
      {
        arrayOfType = new Type[1];
        arrayOfType[0] = this.lowerBound;
      }
      for (;;)
      {
        return arrayOfType;
        arrayOfType = .Gson.Types.EMPTY_TYPE_ARRAY;
      }
    }
    
    public Type[] getUpperBounds()
    {
      Type[] arrayOfType = new Type[1];
      arrayOfType[0] = this.upperBound;
      return arrayOfType;
    }
    
    public int hashCode()
    {
      if (this.lowerBound != null) {}
      for (int i = 31 + this.lowerBound.hashCode();; i = 1) {
        return i ^ 31 + this.upperBound.hashCode();
      }
    }
    
    public String toString()
    {
      String str;
      if (this.lowerBound != null) {
        str = "? super " + .Gson.Types.typeToString(this.lowerBound);
      }
      for (;;)
      {
        return str;
        if (this.upperBound == Object.class) {
          str = "?";
        } else {
          str = "? extends " + .Gson.Types.typeToString(this.upperBound);
        }
      }
    }
  }
  
  private static final class GenericArrayTypeImpl
    implements GenericArrayType, Serializable
  {
    private static final long serialVersionUID;
    private final Type componentType;
    
    public GenericArrayTypeImpl(Type paramType)
    {
      this.componentType = .Gson.Types.canonicalize(paramType);
    }
    
    public boolean equals(Object paramObject)
    {
      if (((paramObject instanceof GenericArrayType)) && (.Gson.Types.equals(this, (GenericArrayType)paramObject))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Type getGenericComponentType()
    {
      return this.componentType;
    }
    
    public int hashCode()
    {
      return this.componentType.hashCode();
    }
    
    public String toString()
    {
      return .Gson.Types.typeToString(this.componentType) + "[]";
    }
  }
  
  private static final class ParameterizedTypeImpl
    implements ParameterizedType, Serializable
  {
    private static final long serialVersionUID;
    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;
    
    public ParameterizedTypeImpl(Type paramType1, Type paramType2, Type... paramVarArgs)
    {
      boolean bool2;
      if ((paramType2 instanceof Class))
      {
        Class localClass = (Class)paramType2;
        if ((paramType1 != null) || (localClass.getEnclosingClass() == null))
        {
          bool2 = true;
          .Gson.Preconditions.checkArgument(bool2);
          if ((paramType1 == null) || (localClass.getEnclosingClass() != null)) {
            bool1 = true;
          }
          .Gson.Preconditions.checkArgument(bool1);
        }
      }
      else
      {
        if (paramType1 != null) {
          break label155;
        }
      }
      label155:
      for (Type localType = null;; localType = .Gson.Types.canonicalize(paramType1))
      {
        this.ownerType = localType;
        this.rawType = .Gson.Types.canonicalize(paramType2);
        this.typeArguments = ((Type[])paramVarArgs.clone());
        for (int i = 0; i < this.typeArguments.length; i++)
        {
          .Gson.Preconditions.checkNotNull(this.typeArguments[i]);
          .Gson.Types.checkNotPrimitive(this.typeArguments[i]);
          this.typeArguments[i] = .Gson.Types.canonicalize(this.typeArguments[i]);
        }
        bool2 = false;
        break;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      if (((paramObject instanceof ParameterizedType)) && (.Gson.Types.equals(this, (ParameterizedType)paramObject))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Type[] getActualTypeArguments()
    {
      return (Type[])this.typeArguments.clone();
    }
    
    public Type getOwnerType()
    {
      return this.ownerType;
    }
    
    public Type getRawType()
    {
      return this.rawType;
    }
    
    public int hashCode()
    {
      return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ .Gson.Types.hashCodeOrZero(this.ownerType);
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(30 * (1 + this.typeArguments.length));
      localStringBuilder.append(.Gson.Types.typeToString(this.rawType));
      if (this.typeArguments.length == 0) {}
      for (String str = localStringBuilder.toString();; str = ">")
      {
        return str;
        localStringBuilder.append("<").append(.Gson.Types.typeToString(this.typeArguments[0]));
        for (int i = 1; i < this.typeArguments.length; i++) {
          localStringBuilder.append(", ").append(.Gson.Types.typeToString(this.typeArguments[i]));
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/internal/$Gson$Types.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */