package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeBindings
{
  private static final JavaType[] NO_TYPES = new JavaType[0];
  public static final JavaType UNBOUND = new SimpleType(Object.class);
  protected Map<String, JavaType> _bindings;
  protected final Class<?> _contextClass;
  protected final JavaType _contextType;
  private final TypeBindings _parentBindings;
  protected HashSet<String> _placeholders;
  protected final TypeFactory _typeFactory;
  
  public TypeBindings(TypeFactory paramTypeFactory, JavaType paramJavaType)
  {
    this(paramTypeFactory, null, paramJavaType.getRawClass(), paramJavaType);
  }
  
  private TypeBindings(TypeFactory paramTypeFactory, TypeBindings paramTypeBindings, Class<?> paramClass, JavaType paramJavaType)
  {
    this._typeFactory = paramTypeFactory;
    this._parentBindings = paramTypeBindings;
    this._contextClass = paramClass;
    this._contextType = paramJavaType;
  }
  
  public TypeBindings(TypeFactory paramTypeFactory, Class<?> paramClass)
  {
    this(paramTypeFactory, null, paramClass, null);
  }
  
  public void _addPlaceholder(String paramString)
  {
    if (this._placeholders == null) {
      this._placeholders = new HashSet();
    }
    this._placeholders.add(paramString);
  }
  
  protected void _resolve()
  {
    _resolveBindings(this._contextClass);
    if (this._contextType != null)
    {
      int i = this._contextType.containedTypeCount();
      if (i > 0) {
        for (int j = 0; j < i; j++) {
          addBinding(this._contextType.containedTypeName(j), this._contextType.containedType(j));
        }
      }
    }
    if (this._bindings == null) {
      this._bindings = Collections.emptyMap();
    }
  }
  
  protected void _resolveBindings(Type paramType)
  {
    if (paramType == null) {
      return;
    }
    label169:
    Class localClass1;
    if ((paramType instanceof ParameterizedType))
    {
      ParameterizedType localParameterizedType = (ParameterizedType)paramType;
      Type[] arrayOfType2 = localParameterizedType.getActualTypeArguments();
      if ((arrayOfType2 != null) && (arrayOfType2.length > 0))
      {
        Class localClass3 = (Class)localParameterizedType.getRawType();
        TypeVariable[] arrayOfTypeVariable2 = localClass3.getTypeParameters();
        if (arrayOfTypeVariable2.length != arrayOfType2.length) {
          throw new IllegalArgumentException("Strange parametrized type (in class " + localClass3.getName() + "): number of type arguments != number of type parameters (" + arrayOfType2.length + " vs " + arrayOfTypeVariable2.length + ")");
        }
        int m = 0;
        int n = arrayOfType2.length;
        if (m < n)
        {
          String str2 = arrayOfTypeVariable2[m].getName();
          if (this._bindings == null)
          {
            this._bindings = new LinkedHashMap();
            _addPlaceholder(str2);
            this._bindings.put(str2, this._typeFactory._constructType(arrayOfType2[m], this));
          }
          for (;;)
          {
            m++;
            break;
            if (!this._bindings.containsKey(str2)) {
              break label169;
            }
          }
        }
      }
      localClass1 = (Class)localParameterizedType.getRawType();
    }
    TypeVariable[] arrayOfTypeVariable1;
    do
    {
      _resolveBindings(localClass1.getGenericSuperclass());
      Type[] arrayOfType1 = localClass1.getGenericInterfaces();
      int j = arrayOfType1.length;
      for (int k = 0; k < j; k++) {
        _resolveBindings(arrayOfType1[k]);
      }
      break;
      if (!(paramType instanceof Class)) {
        break;
      }
      localClass1 = (Class)paramType;
      Class localClass2 = localClass1.getDeclaringClass();
      if ((localClass2 != null) && (!localClass2.isAssignableFrom(localClass1))) {
        _resolveBindings(localClass1.getDeclaringClass());
      }
      arrayOfTypeVariable1 = localClass1.getTypeParameters();
    } while ((arrayOfTypeVariable1 == null) || (arrayOfTypeVariable1.length <= 0));
    JavaType[] arrayOfJavaType = null;
    if ((this._contextType != null) && (localClass1.isAssignableFrom(this._contextType.getRawClass()))) {
      arrayOfJavaType = this._typeFactory.findTypeParameters(this._contextType, localClass1);
    }
    int i = 0;
    label373:
    String str1;
    Type localType;
    if (i < arrayOfTypeVariable1.length)
    {
      TypeVariable localTypeVariable = arrayOfTypeVariable1[i];
      str1 = localTypeVariable.getName();
      localType = localTypeVariable.getBounds()[0];
      if (localType != null)
      {
        if (this._bindings != null) {
          break label473;
        }
        this._bindings = new LinkedHashMap();
        label431:
        _addPlaceholder(str1);
        if ((arrayOfJavaType == null) || (arrayOfJavaType.length <= i)) {
          break label490;
        }
        this._bindings.put(str1, arrayOfJavaType[i]);
      }
    }
    for (;;)
    {
      i++;
      break label373;
      break;
      label473:
      if (!this._bindings.containsKey(str1)) {
        break label431;
      }
      continue;
      label490:
      this._bindings.put(str1, this._typeFactory._constructType(localType, this));
    }
  }
  
  public void addBinding(String paramString, JavaType paramJavaType)
  {
    if ((this._bindings == null) || (this._bindings.size() == 0)) {
      this._bindings = new LinkedHashMap();
    }
    this._bindings.put(paramString, paramJavaType);
  }
  
  public TypeBindings childInstance()
  {
    return new TypeBindings(this._typeFactory, this, this._contextClass, this._contextType);
  }
  
  @Deprecated
  public JavaType findType(String paramString)
  {
    return findType(paramString, true);
  }
  
  public JavaType findType(String paramString, boolean paramBoolean)
  {
    if (this._bindings == null) {
      _resolve();
    }
    JavaType localJavaType = (JavaType)this._bindings.get(paramString);
    if (localJavaType != null) {}
    for (;;)
    {
      return localJavaType;
      if ((this._placeholders != null) && (this._placeholders.contains(paramString)))
      {
        localJavaType = UNBOUND;
      }
      else if (this._parentBindings != null)
      {
        localJavaType = this._parentBindings.findType(paramString);
      }
      else if ((this._contextClass != null) && (this._contextClass.getEnclosingClass() != null) && (!Modifier.isStatic(this._contextClass.getModifiers())))
      {
        localJavaType = UNBOUND;
      }
      else
      {
        if (paramBoolean) {
          break;
        }
        localJavaType = null;
      }
    }
    String str;
    if (this._contextClass != null) {
      str = this._contextClass.getName();
    }
    for (;;)
    {
      throw new IllegalArgumentException("Type variable '" + paramString + "' can not be resolved (with context of class " + str + ")");
      if (this._contextType != null) {
        str = this._contextType.toString();
      } else {
        str = "UNKNOWN";
      }
    }
  }
  
  public int getBindingCount()
  {
    if (this._bindings == null) {
      _resolve();
    }
    return this._bindings.size();
  }
  
  public JavaType resolveType(Class<?> paramClass)
  {
    return this._typeFactory._constructType(paramClass, this);
  }
  
  public JavaType resolveType(Type paramType)
  {
    return this._typeFactory._constructType(paramType, this);
  }
  
  public String toString()
  {
    if (this._bindings == null) {
      _resolve();
    }
    StringBuilder localStringBuilder = new StringBuilder("[TypeBindings for ");
    if (this._contextType != null) {
      localStringBuilder.append(this._contextType.toString());
    }
    for (;;)
    {
      localStringBuilder.append(": ").append(this._bindings).append("]");
      return localStringBuilder.toString();
      localStringBuilder.append(this._contextClass.getName());
    }
  }
  
  public JavaType[] typesAsArray()
  {
    if (this._bindings == null) {
      _resolve();
    }
    if (this._bindings.size() == 0) {}
    for (JavaType[] arrayOfJavaType = NO_TYPES;; arrayOfJavaType = (JavaType[])this._bindings.values().toArray(new JavaType[this._bindings.size()])) {
      return arrayOfJavaType;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/TypeBindings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */