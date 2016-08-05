package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

public final class TypeFactory
  implements Serializable
{
  protected static final SimpleType CORE_TYPE_BOOL = new SimpleType(Boolean.TYPE);
  protected static final SimpleType CORE_TYPE_INT = new SimpleType(Integer.TYPE);
  protected static final SimpleType CORE_TYPE_LONG = new SimpleType(Long.TYPE);
  protected static final SimpleType CORE_TYPE_STRING;
  private static final JavaType[] NO_TYPES = new JavaType[0];
  protected static final TypeFactory instance = new TypeFactory();
  private static final long serialVersionUID = 1L;
  protected transient HierarchicType _cachedArrayListType;
  protected transient HierarchicType _cachedHashMapType;
  protected final ClassLoader _classLoader;
  protected final TypeModifier[] _modifiers;
  protected final TypeParser _parser;
  protected final LRUMap<ClassKey, JavaType> _typeCache = new LRUMap(16, 100);
  
  static
  {
    CORE_TYPE_STRING = new SimpleType(String.class);
  }
  
  private TypeFactory()
  {
    this._parser = new TypeParser(this);
    this._modifiers = null;
    this._classLoader = null;
  }
  
  protected TypeFactory(TypeParser paramTypeParser, TypeModifier[] paramArrayOfTypeModifier)
  {
    this(paramTypeParser, paramArrayOfTypeModifier, null);
  }
  
  protected TypeFactory(TypeParser paramTypeParser, TypeModifier[] paramArrayOfTypeModifier, ClassLoader paramClassLoader)
  {
    this._parser = paramTypeParser.withFactory(this);
    this._modifiers = paramArrayOfTypeModifier;
    this._classLoader = paramClassLoader;
  }
  
  private JavaType _collectionType(Class<?> paramClass)
  {
    JavaType[] arrayOfJavaType = findTypeParameters(paramClass, Collection.class);
    if (arrayOfJavaType == null) {}
    for (CollectionType localCollectionType = CollectionType.construct(paramClass, _unknownType());; localCollectionType = CollectionType.construct(paramClass, arrayOfJavaType[0]))
    {
      return localCollectionType;
      if (arrayOfJavaType.length != 1) {
        throw new IllegalArgumentException("Strange Collection type " + paramClass.getName() + ": can not determine type parameters");
      }
    }
  }
  
  private JavaType _mapType(Class<?> paramClass)
  {
    MapType localMapType;
    if (paramClass == Properties.class) {
      localMapType = MapType.construct(paramClass, CORE_TYPE_STRING, CORE_TYPE_STRING);
    }
    for (;;)
    {
      return localMapType;
      JavaType[] arrayOfJavaType = findTypeParameters(paramClass, Map.class);
      if (arrayOfJavaType == null)
      {
        localMapType = MapType.construct(paramClass, _unknownType(), _unknownType());
      }
      else
      {
        if (arrayOfJavaType.length != 2) {
          throw new IllegalArgumentException("Strange Map type " + paramClass.getName() + ": can not determine type parameters");
        }
        localMapType = MapType.construct(paramClass, arrayOfJavaType[0], arrayOfJavaType[1]);
      }
    }
  }
  
  public static TypeFactory defaultInstance()
  {
    return instance;
  }
  
  public static Class<?> rawClass(Type paramType)
  {
    if ((paramType instanceof Class)) {}
    for (Class localClass = (Class)paramType;; localClass = defaultInstance().constructType(paramType).getRawClass()) {
      return localClass;
    }
  }
  
  public static JavaType unknownType()
  {
    return defaultInstance()._unknownType();
  }
  
  /**
   * @deprecated
   */
  protected HierarchicType _arrayListSuperInterfaceChain(HierarchicType paramHierarchicType)
  {
    try
    {
      if (this._cachedArrayListType == null)
      {
        HierarchicType localHierarchicType2 = paramHierarchicType.deepCloneWithoutSubtype();
        _doFindSuperInterfaceChain(localHierarchicType2, List.class);
        this._cachedArrayListType = localHierarchicType2.getSuperType();
      }
      HierarchicType localHierarchicType1 = this._cachedArrayListType.deepCloneWithoutSubtype();
      paramHierarchicType.setSuperType(localHierarchicType1);
      localHierarchicType1.setSubType(paramHierarchicType);
      return paramHierarchicType;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected JavaType _constructType(Type paramType, TypeBindings paramTypeBindings)
  {
    JavaType localJavaType1;
    if ((paramType instanceof Class)) {
      localJavaType1 = _fromClass((Class)paramType, paramTypeBindings);
    }
    for (;;)
    {
      if ((this._modifiers != null) && (!localJavaType1.isContainerType()))
      {
        TypeModifier[] arrayOfTypeModifier = this._modifiers;
        int i = arrayOfTypeModifier.length;
        int j = 0;
        for (;;)
        {
          if (j < i)
          {
            localJavaType1 = arrayOfTypeModifier[j].modifyType(localJavaType1, paramType, paramTypeBindings, this);
            j++;
            continue;
            if ((paramType instanceof ParameterizedType))
            {
              localJavaType1 = _fromParamType((ParameterizedType)paramType, paramTypeBindings);
              break;
            }
            if (!(paramType instanceof JavaType)) {}
          }
        }
      }
    }
    for (JavaType localJavaType2 = (JavaType)paramType;; localJavaType2 = localJavaType1)
    {
      return localJavaType2;
      if ((paramType instanceof GenericArrayType))
      {
        localJavaType1 = _fromArrayType((GenericArrayType)paramType, paramTypeBindings);
        break;
      }
      if ((paramType instanceof TypeVariable))
      {
        localJavaType1 = _fromVariable((TypeVariable)paramType, paramTypeBindings);
        break;
      }
      if ((paramType instanceof WildcardType))
      {
        localJavaType1 = _fromWildcard((WildcardType)paramType, paramTypeBindings);
        break;
      }
      StringBuilder localStringBuilder = new StringBuilder().append("Unrecognized Type: ");
      if (paramType == null) {}
      for (String str = "[null]";; str = paramType.toString()) {
        throw new IllegalArgumentException(str);
      }
    }
  }
  
  protected HierarchicType _doFindSuperInterfaceChain(HierarchicType paramHierarchicType, Class<?> paramClass)
  {
    Class localClass = paramHierarchicType.getRawClass();
    Type[] arrayOfType = localClass.getGenericInterfaces();
    int j;
    if (arrayOfType != null)
    {
      int i = arrayOfType.length;
      j = 0;
      if (j < i)
      {
        HierarchicType localHierarchicType2 = _findSuperInterfaceChain(arrayOfType[j], paramClass);
        if (localHierarchicType2 != null)
        {
          localHierarchicType2.setSubType(paramHierarchicType);
          paramHierarchicType.setSuperType(localHierarchicType2);
        }
      }
    }
    for (;;)
    {
      return paramHierarchicType;
      j++;
      break;
      Type localType = localClass.getGenericSuperclass();
      if (localType != null)
      {
        HierarchicType localHierarchicType1 = _findSuperInterfaceChain(localType, paramClass);
        if (localHierarchicType1 != null)
        {
          localHierarchicType1.setSubType(paramHierarchicType);
          paramHierarchicType.setSuperType(localHierarchicType1);
          continue;
        }
      }
      paramHierarchicType = null;
    }
  }
  
  protected Class<?> _findPrimitive(String paramString)
  {
    Class localClass;
    if ("int".equals(paramString)) {
      localClass = Integer.TYPE;
    }
    for (;;)
    {
      return localClass;
      if ("long".equals(paramString)) {
        localClass = Long.TYPE;
      } else if ("float".equals(paramString)) {
        localClass = Float.TYPE;
      } else if ("double".equals(paramString)) {
        localClass = Double.TYPE;
      } else if ("boolean".equals(paramString)) {
        localClass = Boolean.TYPE;
      } else if ("byte".equals(paramString)) {
        localClass = Byte.TYPE;
      } else if ("char".equals(paramString)) {
        localClass = Character.TYPE;
      } else if ("short".equals(paramString)) {
        localClass = Short.TYPE;
      } else if ("void".equals(paramString)) {
        localClass = Void.TYPE;
      } else {
        localClass = null;
      }
    }
  }
  
  protected HierarchicType _findSuperClassChain(Type paramType, Class<?> paramClass)
  {
    HierarchicType localHierarchicType1 = new HierarchicType(paramType);
    Class localClass = localHierarchicType1.getRawClass();
    if (localClass == paramClass) {}
    for (;;)
    {
      return localHierarchicType1;
      Type localType = localClass.getGenericSuperclass();
      if (localType != null)
      {
        HierarchicType localHierarchicType2 = _findSuperClassChain(localType, paramClass);
        if (localHierarchicType2 != null)
        {
          localHierarchicType2.setSubType(localHierarchicType1);
          localHierarchicType1.setSuperType(localHierarchicType2);
          continue;
        }
      }
      localHierarchicType1 = null;
    }
  }
  
  protected HierarchicType _findSuperInterfaceChain(Type paramType, Class<?> paramClass)
  {
    HierarchicType localHierarchicType1 = new HierarchicType(paramType);
    Class localClass = localHierarchicType1.getRawClass();
    HierarchicType localHierarchicType2;
    if (localClass == paramClass) {
      localHierarchicType2 = new HierarchicType(paramType);
    }
    for (;;)
    {
      return localHierarchicType2;
      if ((localClass == HashMap.class) && (paramClass == Map.class)) {
        localHierarchicType2 = _hashMapSuperInterfaceChain(localHierarchicType1);
      } else if ((localClass == ArrayList.class) && (paramClass == List.class)) {
        localHierarchicType2 = _arrayListSuperInterfaceChain(localHierarchicType1);
      } else {
        localHierarchicType2 = _doFindSuperInterfaceChain(localHierarchicType1, paramClass);
      }
    }
  }
  
  protected HierarchicType _findSuperTypeChain(Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2.isInterface()) {}
    for (HierarchicType localHierarchicType = _findSuperInterfaceChain(paramClass1, paramClass2);; localHierarchicType = _findSuperClassChain(paramClass1, paramClass2)) {
      return localHierarchicType;
    }
  }
  
  protected JavaType _fromArrayType(GenericArrayType paramGenericArrayType, TypeBindings paramTypeBindings)
  {
    return ArrayType.construct(_constructType(paramGenericArrayType.getGenericComponentType(), paramTypeBindings), null, null);
  }
  
  protected JavaType _fromClass(Class<?> paramClass, TypeBindings paramTypeBindings)
  {
    Object localObject;
    if (paramClass == String.class) {
      localObject = CORE_TYPE_STRING;
    }
    ClassKey localClassKey;
    do
    {
      for (;;)
      {
        return (JavaType)localObject;
        if (paramClass == Boolean.TYPE)
        {
          localObject = CORE_TYPE_BOOL;
        }
        else if (paramClass == Integer.TYPE)
        {
          localObject = CORE_TYPE_INT;
        }
        else
        {
          if (paramClass != Long.TYPE) {
            break;
          }
          localObject = CORE_TYPE_LONG;
        }
      }
      localClassKey = new ClassKey(paramClass);
      localObject = (JavaType)this._typeCache.get(localClassKey);
    } while (localObject != null);
    if (paramClass.isArray()) {
      localObject = ArrayType.construct(_constructType(paramClass.getComponentType(), null), null, null);
    }
    for (;;)
    {
      this._typeCache.put(localClassKey, localObject);
      break;
      if (paramClass.isEnum())
      {
        localObject = new SimpleType(paramClass);
      }
      else if (Map.class.isAssignableFrom(paramClass))
      {
        localObject = _mapType(paramClass);
      }
      else if (Collection.class.isAssignableFrom(paramClass))
      {
        localObject = _collectionType(paramClass);
      }
      else
      {
        if (AtomicReference.class.isAssignableFrom(paramClass))
        {
          JavaType[] arrayOfJavaType3 = findTypeParameters(paramClass, AtomicReference.class);
          if ((arrayOfJavaType3 == null) || (arrayOfJavaType3.length != 1)) {}
          for (JavaType localJavaType3 = unknownType();; localJavaType3 = arrayOfJavaType3[0])
          {
            localObject = constructReferenceType(paramClass, localJavaType3);
            break;
          }
        }
        if (Map.Entry.class.isAssignableFrom(paramClass))
        {
          JavaType[] arrayOfJavaType1 = findTypeParameters(paramClass, Map.Entry.class);
          JavaType localJavaType1;
          JavaType localJavaType2;
          if ((arrayOfJavaType1 == null) || (arrayOfJavaType1.length != 2))
          {
            localJavaType1 = unknownType();
            localJavaType2 = localJavaType1;
          }
          for (;;)
          {
            JavaType[] arrayOfJavaType2 = new JavaType[2];
            arrayOfJavaType2[0] = localJavaType2;
            arrayOfJavaType2[1] = localJavaType1;
            localObject = constructSimpleType(paramClass, Map.Entry.class, arrayOfJavaType2);
            break;
            localJavaType2 = arrayOfJavaType1[0];
            localJavaType1 = arrayOfJavaType1[1];
          }
        }
        localObject = new SimpleType(paramClass);
      }
    }
  }
  
  protected JavaType _fromParamType(ParameterizedType paramParameterizedType, TypeBindings paramTypeBindings)
  {
    Class localClass = (Class)paramParameterizedType.getRawType();
    Type[] arrayOfType = paramParameterizedType.getActualTypeArguments();
    int i;
    JavaType[] arrayOfJavaType1;
    if (arrayOfType == null)
    {
      i = 0;
      if (i != 0) {
        break label125;
      }
      arrayOfJavaType1 = NO_TYPES;
    }
    label125:
    Object localObject;
    for (;;)
    {
      if (Map.class.isAssignableFrom(localClass))
      {
        JavaType[] arrayOfJavaType6 = findTypeParameters(constructSimpleType(localClass, localClass, arrayOfJavaType1), Map.class);
        if (arrayOfJavaType6.length != 2)
        {
          throw new IllegalArgumentException("Could not find 2 type parameters for Map class " + localClass.getName() + " (found " + arrayOfJavaType6.length + ")");
          i = arrayOfType.length;
          break;
          arrayOfJavaType1 = new JavaType[i];
          for (int j = 0; j < i; j++) {
            arrayOfJavaType1[j] = _constructType(arrayOfType[j], paramTypeBindings);
          }
          continue;
        }
        localObject = MapType.construct(localClass, arrayOfJavaType6[0], arrayOfJavaType6[1]);
      }
    }
    for (;;)
    {
      return (JavaType)localObject;
      if (Collection.class.isAssignableFrom(localClass))
      {
        JavaType[] arrayOfJavaType5 = findTypeParameters(constructSimpleType(localClass, localClass, arrayOfJavaType1), Collection.class);
        if (arrayOfJavaType5.length != 1) {
          throw new IllegalArgumentException("Could not find 1 type parameter for Collection class " + localClass.getName() + " (found " + arrayOfJavaType5.length + ")");
        }
        localObject = CollectionType.construct(localClass, arrayOfJavaType5[0]);
      }
      else
      {
        if (AtomicReference.class.isAssignableFrom(localClass))
        {
          JavaType localJavaType3 = null;
          if (localClass == AtomicReference.class) {
            if (i != 1) {}
          }
          JavaType[] arrayOfJavaType4;
          for (localJavaType3 = arrayOfJavaType1[0];; localJavaType3 = arrayOfJavaType4[0]) {
            do
            {
              if (localJavaType3 == null) {
                localJavaType3 = unknownType();
              }
              localObject = constructReferenceType(localClass, localJavaType3);
              break;
              arrayOfJavaType4 = findTypeParameters(constructSimpleType(localClass, localClass, arrayOfJavaType1), AtomicReference.class, paramTypeBindings);
            } while ((arrayOfJavaType4 == null) || (arrayOfJavaType4.length != 1));
          }
        }
        if (Map.Entry.class.isAssignableFrom(localClass))
        {
          JavaType localJavaType1 = null;
          JavaType localJavaType2 = null;
          if (localClass == Map.Entry.class) {
            if (i == 2) {
              localJavaType1 = arrayOfJavaType1[0];
            }
          }
          JavaType[] arrayOfJavaType2;
          for (localJavaType2 = arrayOfJavaType1[1];; localJavaType2 = arrayOfJavaType2[1])
          {
            do
            {
              JavaType[] arrayOfJavaType3 = new JavaType[2];
              if (localJavaType1 == null) {
                localJavaType1 = unknownType();
              }
              arrayOfJavaType3[0] = localJavaType1;
              if (localJavaType2 == null) {
                localJavaType2 = unknownType();
              }
              arrayOfJavaType3[1] = localJavaType2;
              localObject = constructSimpleType(localClass, Map.Entry.class, arrayOfJavaType3);
              break;
              arrayOfJavaType2 = findTypeParameters(constructSimpleType(localClass, localClass, arrayOfJavaType1), Map.Entry.class, paramTypeBindings);
            } while ((arrayOfJavaType2 == null) || (arrayOfJavaType2.length != 2));
            localJavaType1 = arrayOfJavaType2[0];
          }
        }
        if (i == 0) {
          localObject = new SimpleType(localClass);
        } else {
          localObject = constructSimpleType(localClass, arrayOfJavaType1);
        }
      }
    }
  }
  
  protected JavaType _fromParameterizedClass(Class<?> paramClass, List<JavaType> paramList)
  {
    Object localObject;
    if (paramClass.isArray()) {
      localObject = ArrayType.construct(_constructType(paramClass.getComponentType(), null), null, null);
    }
    for (;;)
    {
      return (JavaType)localObject;
      if (paramClass.isEnum())
      {
        localObject = new SimpleType(paramClass);
      }
      else if (Map.class.isAssignableFrom(paramClass))
      {
        if (paramList.size() > 0)
        {
          JavaType localJavaType1 = (JavaType)paramList.get(0);
          if (paramList.size() >= 2) {}
          for (JavaType localJavaType2 = (JavaType)paramList.get(1);; localJavaType2 = _unknownType())
          {
            localObject = MapType.construct(paramClass, localJavaType1, localJavaType2);
            break;
          }
        }
        localObject = _mapType(paramClass);
      }
      else if (Collection.class.isAssignableFrom(paramClass))
      {
        if (paramList.size() >= 1) {
          localObject = CollectionType.construct(paramClass, (JavaType)paramList.get(0));
        } else {
          localObject = _collectionType(paramClass);
        }
      }
      else if (paramList.size() == 0)
      {
        localObject = new SimpleType(paramClass);
      }
      else
      {
        localObject = constructSimpleType(paramClass, paramClass, (JavaType[])paramList.toArray(new JavaType[paramList.size()]));
      }
    }
  }
  
  protected JavaType _fromVariable(TypeVariable<?> paramTypeVariable, TypeBindings paramTypeBindings)
  {
    String str = paramTypeVariable.getName();
    JavaType localJavaType;
    if (paramTypeBindings == null)
    {
      paramTypeBindings = new TypeBindings(this, (Class)null);
      Type[] arrayOfType = paramTypeVariable.getBounds();
      paramTypeBindings._addPlaceholder(str);
      localJavaType = _constructType(arrayOfType[0], paramTypeBindings);
    }
    for (;;)
    {
      return localJavaType;
      localJavaType = paramTypeBindings.findType(str, false);
      if (localJavaType == null) {
        break;
      }
    }
  }
  
  protected JavaType _fromWildcard(WildcardType paramWildcardType, TypeBindings paramTypeBindings)
  {
    return _constructType(paramWildcardType.getUpperBounds()[0], paramTypeBindings);
  }
  
  /**
   * @deprecated
   */
  protected HierarchicType _hashMapSuperInterfaceChain(HierarchicType paramHierarchicType)
  {
    try
    {
      if (this._cachedHashMapType == null)
      {
        HierarchicType localHierarchicType2 = paramHierarchicType.deepCloneWithoutSubtype();
        _doFindSuperInterfaceChain(localHierarchicType2, Map.class);
        this._cachedHashMapType = localHierarchicType2.getSuperType();
      }
      HierarchicType localHierarchicType1 = this._cachedHashMapType.deepCloneWithoutSubtype();
      paramHierarchicType.setSuperType(localHierarchicType1);
      localHierarchicType1.setSubType(paramHierarchicType);
      return paramHierarchicType;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected JavaType _resolveVariableViaSubTypes(HierarchicType paramHierarchicType, String paramString, TypeBindings paramTypeBindings)
  {
    int i;
    Type localType;
    JavaType localJavaType;
    if ((paramHierarchicType != null) && (paramHierarchicType.isGeneric()))
    {
      TypeVariable[] arrayOfTypeVariable = paramHierarchicType.getRawClass().getTypeParameters();
      i = 0;
      int j = arrayOfTypeVariable.length;
      if (i < j) {
        if (paramString.equals(arrayOfTypeVariable[i].getName()))
        {
          localType = paramHierarchicType.asGeneric().getActualTypeArguments()[i];
          if ((localType instanceof TypeVariable)) {
            localJavaType = _resolveVariableViaSubTypes(paramHierarchicType.getSubType(), ((TypeVariable)localType).getName(), paramTypeBindings);
          }
        }
      }
    }
    for (;;)
    {
      return localJavaType;
      localJavaType = _constructType(localType, paramTypeBindings);
      continue;
      i++;
      break;
      localJavaType = _unknownType();
    }
  }
  
  protected JavaType _unknownType()
  {
    return new SimpleType(Object.class);
  }
  
  protected Class<?> classForName(String paramString)
    throws ClassNotFoundException
  {
    return Class.forName(paramString);
  }
  
  protected Class<?> classForName(String paramString, boolean paramBoolean, ClassLoader paramClassLoader)
    throws ClassNotFoundException
  {
    return Class.forName(paramString, true, paramClassLoader);
  }
  
  public void clearCache()
  {
    this._typeCache.clear();
  }
  
  public ArrayType constructArrayType(JavaType paramJavaType)
  {
    return ArrayType.construct(paramJavaType, null, null);
  }
  
  public ArrayType constructArrayType(Class<?> paramClass)
  {
    return ArrayType.construct(_constructType(paramClass, null), null, null);
  }
  
  public CollectionLikeType constructCollectionLikeType(Class<?> paramClass, JavaType paramJavaType)
  {
    return CollectionLikeType.construct(paramClass, paramJavaType);
  }
  
  public CollectionLikeType constructCollectionLikeType(Class<?> paramClass1, Class<?> paramClass2)
  {
    return CollectionLikeType.construct(paramClass1, constructType(paramClass2));
  }
  
  public CollectionType constructCollectionType(Class<? extends Collection> paramClass, JavaType paramJavaType)
  {
    return CollectionType.construct(paramClass, paramJavaType);
  }
  
  public CollectionType constructCollectionType(Class<? extends Collection> paramClass, Class<?> paramClass1)
  {
    return CollectionType.construct(paramClass, constructType(paramClass1));
  }
  
  public JavaType constructFromCanonical(String paramString)
    throws IllegalArgumentException
  {
    return this._parser.parse(paramString);
  }
  
  public MapLikeType constructMapLikeType(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    return MapLikeType.construct(paramClass, paramJavaType1, paramJavaType2);
  }
  
  public MapLikeType constructMapLikeType(Class<?> paramClass1, Class<?> paramClass2, Class<?> paramClass3)
  {
    return MapType.construct(paramClass1, constructType(paramClass2), constructType(paramClass3));
  }
  
  public MapType constructMapType(Class<? extends Map> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    return MapType.construct(paramClass, paramJavaType1, paramJavaType2);
  }
  
  public MapType constructMapType(Class<? extends Map> paramClass, Class<?> paramClass1, Class<?> paramClass2)
  {
    return MapType.construct(paramClass, constructType(paramClass1), constructType(paramClass2));
  }
  
  @Deprecated
  public JavaType constructParametricType(Class<?> paramClass, JavaType... paramVarArgs)
  {
    return constructParametrizedType(paramClass, paramClass, paramVarArgs);
  }
  
  @Deprecated
  public JavaType constructParametricType(Class<?> paramClass, Class<?>... paramVarArgs)
  {
    return constructParametrizedType(paramClass, paramClass, paramVarArgs);
  }
  
  public JavaType constructParametrizedType(Class<?> paramClass1, Class<?> paramClass2, JavaType... paramVarArgs)
  {
    Object localObject;
    if (paramClass1.isArray())
    {
      if (paramVarArgs.length != 1) {
        throw new IllegalArgumentException("Need exactly 1 parameter type for arrays (" + paramClass1.getName() + ")");
      }
      localObject = constructArrayType(paramVarArgs[0]);
    }
    for (;;)
    {
      return (JavaType)localObject;
      if (Map.class.isAssignableFrom(paramClass1))
      {
        if (paramVarArgs.length != 2) {
          throw new IllegalArgumentException("Need exactly 2 parameter types for Map types (" + paramClass1.getName() + ")");
        }
        localObject = constructMapType(paramClass1, paramVarArgs[0], paramVarArgs[1]);
      }
      else if (Collection.class.isAssignableFrom(paramClass1))
      {
        if (paramVarArgs.length != 1) {
          throw new IllegalArgumentException("Need exactly 1 parameter type for Collection types (" + paramClass1.getName() + ")");
        }
        localObject = constructCollectionType(paramClass1, paramVarArgs[0]);
      }
      else
      {
        localObject = constructSimpleType(paramClass1, paramClass2, paramVarArgs);
      }
    }
  }
  
  public JavaType constructParametrizedType(Class<?> paramClass1, Class<?> paramClass2, Class<?>... paramVarArgs)
  {
    int i = paramVarArgs.length;
    JavaType[] arrayOfJavaType = new JavaType[i];
    for (int j = 0; j < i; j++) {
      arrayOfJavaType[j] = _fromClass(paramVarArgs[j], null);
    }
    return constructParametrizedType(paramClass1, paramClass2, arrayOfJavaType);
  }
  
  public CollectionLikeType constructRawCollectionLikeType(Class<?> paramClass)
  {
    return CollectionLikeType.construct(paramClass, unknownType());
  }
  
  public CollectionType constructRawCollectionType(Class<? extends Collection> paramClass)
  {
    return CollectionType.construct(paramClass, unknownType());
  }
  
  public MapLikeType constructRawMapLikeType(Class<?> paramClass)
  {
    return MapLikeType.construct(paramClass, unknownType(), unknownType());
  }
  
  public MapType constructRawMapType(Class<? extends Map> paramClass)
  {
    return MapType.construct(paramClass, unknownType(), unknownType());
  }
  
  public JavaType constructReferenceType(Class<?> paramClass, JavaType paramJavaType)
  {
    return new ReferenceType(paramClass, paramJavaType, null, null, false);
  }
  
  public JavaType constructSimpleType(Class<?> paramClass1, Class<?> paramClass2, JavaType[] paramArrayOfJavaType)
  {
    TypeVariable[] arrayOfTypeVariable = paramClass2.getTypeParameters();
    if (arrayOfTypeVariable.length != paramArrayOfJavaType.length) {
      throw new IllegalArgumentException("Parameter type mismatch for " + paramClass1.getName() + " (and target " + paramClass2.getName() + "): expected " + arrayOfTypeVariable.length + " parameters, was given " + paramArrayOfJavaType.length);
    }
    String[] arrayOfString = new String[arrayOfTypeVariable.length];
    int i = 0;
    int j = arrayOfTypeVariable.length;
    while (i < j)
    {
      arrayOfString[i] = arrayOfTypeVariable[i].getName();
      i++;
    }
    return new SimpleType(paramClass1, arrayOfString, paramArrayOfJavaType, null, null, false, paramClass2);
  }
  
  @Deprecated
  public JavaType constructSimpleType(Class<?> paramClass, JavaType[] paramArrayOfJavaType)
  {
    return constructSimpleType(paramClass, paramClass, paramArrayOfJavaType);
  }
  
  public JavaType constructSpecializedType(JavaType paramJavaType, Class<?> paramClass)
  {
    if (paramJavaType.getRawClass() == paramClass) {}
    for (;;)
    {
      return paramJavaType;
      if (((paramJavaType instanceof SimpleType)) && ((paramClass.isArray()) || (Map.class.isAssignableFrom(paramClass)) || (Collection.class.isAssignableFrom(paramClass))))
      {
        if (!paramJavaType.getRawClass().isAssignableFrom(paramClass)) {
          throw new IllegalArgumentException("Class " + paramClass.getClass().getName() + " not subtype of " + paramJavaType);
        }
        JavaType localJavaType = _fromClass(paramClass, new TypeBindings(this, paramJavaType.getRawClass()));
        Object localObject1 = paramJavaType.getValueHandler();
        if (localObject1 != null) {
          localJavaType = localJavaType.withValueHandler(localObject1);
        }
        Object localObject2 = paramJavaType.getTypeHandler();
        if (localObject2 != null) {
          localJavaType = localJavaType.withTypeHandler(localObject2);
        }
        paramJavaType = localJavaType;
      }
      else
      {
        paramJavaType = paramJavaType.narrowBy(paramClass);
      }
    }
  }
  
  public JavaType constructType(TypeReference<?> paramTypeReference)
  {
    return _constructType(paramTypeReference.getType(), null);
  }
  
  public JavaType constructType(Type paramType)
  {
    return _constructType(paramType, null);
  }
  
  public JavaType constructType(Type paramType, JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (TypeBindings localTypeBindings = null;; localTypeBindings = new TypeBindings(this, paramJavaType)) {
      return _constructType(paramType, localTypeBindings);
    }
  }
  
  public JavaType constructType(Type paramType, TypeBindings paramTypeBindings)
  {
    return _constructType(paramType, paramTypeBindings);
  }
  
  public JavaType constructType(Type paramType, Class<?> paramClass)
  {
    if (paramClass == null) {}
    for (TypeBindings localTypeBindings = null;; localTypeBindings = new TypeBindings(this, paramClass)) {
      return _constructType(paramType, localTypeBindings);
    }
  }
  
  public Class<?> findClass(String paramString)
    throws ClassNotFoundException
  {
    Object localObject;
    if (paramString.indexOf('.') < 0)
    {
      localObject = _findPrimitive(paramString);
      if (localObject == null) {}
    }
    for (;;)
    {
      return (Class<?>)localObject;
      Throwable localThrowable = null;
      ClassLoader localClassLoader = getClassLoader();
      if (localClassLoader == null) {
        localClassLoader = Thread.currentThread().getContextClassLoader();
      }
      if (localClassLoader != null) {
        try
        {
          Class localClass2 = classForName(paramString, true, localClassLoader);
          localObject = localClass2;
        }
        catch (Exception localException2)
        {
          localThrowable = ClassUtil.getRootCause(localException2);
        }
      }
      try
      {
        Class localClass1 = classForName(paramString);
        localObject = localClass1;
      }
      catch (Exception localException1)
      {
        if (localThrowable == null) {
          localThrowable = ClassUtil.getRootCause(localException1);
        }
        if ((localThrowable instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable);
        }
        throw new ClassNotFoundException(localThrowable.getMessage(), localThrowable);
      }
    }
  }
  
  public JavaType[] findTypeParameters(JavaType paramJavaType, Class<?> paramClass)
  {
    int i;
    JavaType[] arrayOfJavaType;
    if (paramClass == paramJavaType.getParameterSource())
    {
      i = paramJavaType.containedTypeCount();
      if (i == 0) {
        arrayOfJavaType = null;
      }
    }
    for (;;)
    {
      return arrayOfJavaType;
      arrayOfJavaType = new JavaType[i];
      for (int j = 0; j < i; j++) {
        arrayOfJavaType[j] = paramJavaType.containedType(j);
      }
      continue;
      arrayOfJavaType = findTypeParameters(paramJavaType.getRawClass(), paramClass, new TypeBindings(this, paramJavaType));
    }
  }
  
  public JavaType[] findTypeParameters(JavaType paramJavaType, Class<?> paramClass, TypeBindings paramTypeBindings)
  {
    int i;
    JavaType[] arrayOfJavaType;
    if (paramClass == paramJavaType.getParameterSource())
    {
      i = paramJavaType.containedTypeCount();
      if (i == 0) {
        arrayOfJavaType = null;
      }
    }
    for (;;)
    {
      return arrayOfJavaType;
      arrayOfJavaType = new JavaType[i];
      for (int j = 0; j < i; j++) {
        arrayOfJavaType[j] = paramJavaType.containedType(j);
      }
      continue;
      arrayOfJavaType = findTypeParameters(paramJavaType.getRawClass(), paramClass, paramTypeBindings);
    }
  }
  
  public JavaType[] findTypeParameters(Class<?> paramClass1, Class<?> paramClass2)
  {
    return findTypeParameters(paramClass1, paramClass2, new TypeBindings(this, paramClass1));
  }
  
  public JavaType[] findTypeParameters(Class<?> paramClass1, Class<?> paramClass2, TypeBindings paramTypeBindings)
  {
    HierarchicType localHierarchicType1 = _findSuperTypeChain(paramClass1, paramClass2);
    if (localHierarchicType1 == null) {
      throw new IllegalArgumentException("Class " + paramClass1.getName() + " is not a subtype of " + paramClass2.getName());
    }
    HierarchicType localHierarchicType2 = localHierarchicType1;
    while (localHierarchicType2.getSuperType() != null)
    {
      localHierarchicType2 = localHierarchicType2.getSuperType();
      Class localClass = localHierarchicType2.getRawClass();
      TypeBindings localTypeBindings = new TypeBindings(this, localClass);
      if (localHierarchicType2.isGeneric())
      {
        Type[] arrayOfType = localHierarchicType2.asGeneric().getActualTypeArguments();
        TypeVariable[] arrayOfTypeVariable = localClass.getTypeParameters();
        int i = arrayOfType.length;
        for (int j = 0; j < i; j++) {
          localTypeBindings.addBinding(arrayOfTypeVariable[j].getName(), _constructType(arrayOfType[j], paramTypeBindings));
        }
      }
      paramTypeBindings = localTypeBindings;
    }
    if (!localHierarchicType2.isGeneric()) {}
    for (JavaType[] arrayOfJavaType = null;; arrayOfJavaType = paramTypeBindings.typesAsArray()) {
      return arrayOfJavaType;
    }
  }
  
  public ClassLoader getClassLoader()
  {
    return this._classLoader;
  }
  
  public JavaType moreSpecificType(JavaType paramJavaType1, JavaType paramJavaType2)
  {
    if (paramJavaType1 == null) {
      paramJavaType1 = paramJavaType2;
    }
    for (;;)
    {
      return paramJavaType1;
      if (paramJavaType2 != null)
      {
        Class localClass1 = paramJavaType1.getRawClass();
        Class localClass2 = paramJavaType2.getRawClass();
        if ((localClass1 != localClass2) && (localClass1.isAssignableFrom(localClass2))) {
          paramJavaType1 = paramJavaType2;
        }
      }
    }
  }
  
  public JavaType uncheckedSimpleType(Class<?> paramClass)
  {
    return new SimpleType(paramClass);
  }
  
  public TypeFactory withClassLoader(ClassLoader paramClassLoader)
  {
    return new TypeFactory(this._parser, this._modifiers, paramClassLoader);
  }
  
  public TypeFactory withModifier(TypeModifier paramTypeModifier)
  {
    TypeFactory localTypeFactory;
    if (paramTypeModifier == null) {
      localTypeFactory = new TypeFactory(this._parser, this._modifiers, this._classLoader);
    }
    for (;;)
    {
      return localTypeFactory;
      if (this._modifiers == null)
      {
        TypeParser localTypeParser = this._parser;
        TypeModifier[] arrayOfTypeModifier = new TypeModifier[1];
        arrayOfTypeModifier[0] = paramTypeModifier;
        localTypeFactory = new TypeFactory(localTypeParser, arrayOfTypeModifier, this._classLoader);
      }
      else
      {
        localTypeFactory = new TypeFactory(this._parser, (TypeModifier[])ArrayBuilders.insertInListNoDup(this._modifiers, paramTypeModifier), this._classLoader);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/TypeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */