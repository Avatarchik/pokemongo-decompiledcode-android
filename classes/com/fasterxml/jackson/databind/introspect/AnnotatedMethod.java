package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public final class AnnotatedMethod
  extends AnnotatedWithParams
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final transient Method _method;
  protected Class<?>[] _paramClasses;
  protected Serialization _serialization;
  
  public AnnotatedMethod(AnnotatedClass paramAnnotatedClass, Method paramMethod, AnnotationMap paramAnnotationMap, AnnotationMap[] paramArrayOfAnnotationMap)
  {
    super(paramAnnotatedClass, paramAnnotationMap, paramArrayOfAnnotationMap);
    if (paramMethod == null) {
      throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
    }
    this._method = paramMethod;
  }
  
  protected AnnotatedMethod(Serialization paramSerialization)
  {
    super(null, null, null);
    this._method = null;
    this._serialization = paramSerialization;
  }
  
  public final Object call()
    throws Exception
  {
    return this._method.invoke(null, new Object[0]);
  }
  
  public final Object call(Object[] paramArrayOfObject)
    throws Exception
  {
    return this._method.invoke(null, paramArrayOfObject);
  }
  
  public final Object call1(Object paramObject)
    throws Exception
  {
    Method localMethod = this._method;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramObject;
    return localMethod.invoke(null, arrayOfObject);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject == null) || (paramObject.getClass() != getClass())) {
        bool = false;
      } else if (((AnnotatedMethod)paramObject)._method != this._method) {
        bool = false;
      }
    }
  }
  
  public Method getAnnotated()
  {
    return this._method;
  }
  
  public Class<?> getDeclaringClass()
  {
    return this._method.getDeclaringClass();
  }
  
  public String getFullName()
  {
    return getDeclaringClass().getName() + "#" + getName() + "(" + getParameterCount() + " params)";
  }
  
  public Type getGenericParameterType(int paramInt)
  {
    Type[] arrayOfType = this._method.getGenericParameterTypes();
    if (paramInt >= arrayOfType.length) {}
    for (Type localType = null;; localType = arrayOfType[paramInt]) {
      return localType;
    }
  }
  
  public Type[] getGenericParameterTypes()
  {
    return this._method.getGenericParameterTypes();
  }
  
  public Type getGenericReturnType()
  {
    return this._method.getGenericReturnType();
  }
  
  public Type getGenericType()
  {
    return this._method.getGenericReturnType();
  }
  
  public Method getMember()
  {
    return this._method;
  }
  
  public int getModifiers()
  {
    return this._method.getModifiers();
  }
  
  public String getName()
  {
    return this._method.getName();
  }
  
  public int getParameterCount()
  {
    return getRawParameterTypes().length;
  }
  
  public Class<?> getRawParameterType(int paramInt)
  {
    Class[] arrayOfClass = getRawParameterTypes();
    if (paramInt >= arrayOfClass.length) {}
    for (Object localObject = null;; localObject = arrayOfClass[paramInt]) {
      return (Class<?>)localObject;
    }
  }
  
  public Class<?>[] getRawParameterTypes()
  {
    if (this._paramClasses == null) {
      this._paramClasses = this._method.getParameterTypes();
    }
    return this._paramClasses;
  }
  
  public Class<?> getRawReturnType()
  {
    return this._method.getReturnType();
  }
  
  public Class<?> getRawType()
  {
    return this._method.getReturnType();
  }
  
  public JavaType getType(TypeBindings paramTypeBindings)
  {
    return getType(paramTypeBindings, this._method.getTypeParameters());
  }
  
  public Object getValue(Object paramObject)
    throws IllegalArgumentException
  {
    try
    {
      Object localObject = this._method.invoke(paramObject, new Object[0]);
      return localObject;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalArgumentException("Failed to getValue() with method " + getFullName() + ": " + localIllegalAccessException.getMessage(), localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new IllegalArgumentException("Failed to getValue() with method " + getFullName() + ": " + localInvocationTargetException.getMessage(), localInvocationTargetException);
    }
  }
  
  public boolean hasReturnType()
  {
    Class localClass = getRawReturnType();
    if ((localClass != Void.TYPE) && (localClass != Void.class)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return this._method.getName().hashCode();
  }
  
  Object readResolve()
  {
    Class localClass = this._serialization.clazz;
    try
    {
      Method localMethod = localClass.getDeclaredMethod(this._serialization.name, this._serialization.args);
      if (!localMethod.isAccessible()) {
        ClassUtil.checkAndFixAccess(localMethod);
      }
      AnnotatedMethod localAnnotatedMethod = new AnnotatedMethod(null, localMethod, null, null);
      return localAnnotatedMethod;
    }
    catch (Exception localException)
    {
      throw new IllegalArgumentException("Could not find method '" + this._serialization.name + "' from Class '" + localClass.getName());
    }
  }
  
  public void setValue(Object paramObject1, Object paramObject2)
    throws IllegalArgumentException
  {
    try
    {
      Method localMethod = this._method;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramObject2;
      localMethod.invoke(paramObject1, arrayOfObject);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + localIllegalAccessException.getMessage(), localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + localInvocationTargetException.getMessage(), localInvocationTargetException);
    }
  }
  
  public String toString()
  {
    return "[method " + getFullName() + "]";
  }
  
  public AnnotatedMethod withAnnotations(AnnotationMap paramAnnotationMap)
  {
    return new AnnotatedMethod(this._context, this._method, paramAnnotationMap, this._paramAnnotations);
  }
  
  public AnnotatedMethod withMethod(Method paramMethod)
  {
    return new AnnotatedMethod(this._context, paramMethod, this._annotations, this._paramAnnotations);
  }
  
  Object writeReplace()
  {
    return new AnnotatedMethod(new Serialization(this._method));
  }
  
  private static final class Serialization
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected Class<?>[] args;
    protected Class<?> clazz;
    protected String name;
    
    public Serialization(Method paramMethod)
    {
      this.clazz = paramMethod.getDeclaringClass();
      this.name = paramMethod.getName();
      this.args = paramMethod.getParameterTypes();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotatedMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */