package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedConstructor
  extends AnnotatedWithParams
{
  private static final long serialVersionUID = 1L;
  protected final Constructor<?> _constructor;
  protected Serialization _serialization;
  
  public AnnotatedConstructor(AnnotatedClass paramAnnotatedClass, Constructor<?> paramConstructor, AnnotationMap paramAnnotationMap, AnnotationMap[] paramArrayOfAnnotationMap)
  {
    super(paramAnnotatedClass, paramAnnotationMap, paramArrayOfAnnotationMap);
    if (paramConstructor == null) {
      throw new IllegalArgumentException("Null constructor not allowed");
    }
    this._constructor = paramConstructor;
  }
  
  protected AnnotatedConstructor(Serialization paramSerialization)
  {
    super(null, null, null);
    this._constructor = null;
    this._serialization = paramSerialization;
  }
  
  public final Object call()
    throws Exception
  {
    return this._constructor.newInstance(new Object[0]);
  }
  
  public final Object call(Object[] paramArrayOfObject)
    throws Exception
  {
    return this._constructor.newInstance(paramArrayOfObject);
  }
  
  public final Object call1(Object paramObject)
    throws Exception
  {
    Constructor localConstructor = this._constructor;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramObject;
    return localConstructor.newInstance(arrayOfObject);
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
      } else if (((AnnotatedConstructor)paramObject)._constructor != this._constructor) {
        bool = false;
      }
    }
  }
  
  public Constructor<?> getAnnotated()
  {
    return this._constructor;
  }
  
  public Class<?> getDeclaringClass()
  {
    return this._constructor.getDeclaringClass();
  }
  
  public Type getGenericParameterType(int paramInt)
  {
    Type[] arrayOfType = this._constructor.getGenericParameterTypes();
    if (paramInt >= arrayOfType.length) {}
    for (Type localType = null;; localType = arrayOfType[paramInt]) {
      return localType;
    }
  }
  
  public Type getGenericType()
  {
    return getRawType();
  }
  
  public Member getMember()
  {
    return this._constructor;
  }
  
  public int getModifiers()
  {
    return this._constructor.getModifiers();
  }
  
  public String getName()
  {
    return this._constructor.getName();
  }
  
  public int getParameterCount()
  {
    return this._constructor.getParameterTypes().length;
  }
  
  public Class<?> getRawParameterType(int paramInt)
  {
    Class[] arrayOfClass = this._constructor.getParameterTypes();
    if (paramInt >= arrayOfClass.length) {}
    for (Object localObject = null;; localObject = arrayOfClass[paramInt]) {
      return (Class<?>)localObject;
    }
  }
  
  public Class<?> getRawType()
  {
    return this._constructor.getDeclaringClass();
  }
  
  public JavaType getType(TypeBindings paramTypeBindings)
  {
    return getType(paramTypeBindings, this._constructor.getTypeParameters());
  }
  
  public Object getValue(Object paramObject)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Cannot call getValue() on constructor of " + getDeclaringClass().getName());
  }
  
  public int hashCode()
  {
    return this._constructor.getName().hashCode();
  }
  
  Object readResolve()
  {
    Class localClass = this._serialization.clazz;
    try
    {
      Constructor localConstructor = localClass.getDeclaredConstructor(this._serialization.args);
      if (!localConstructor.isAccessible()) {
        ClassUtil.checkAndFixAccess(localConstructor);
      }
      AnnotatedConstructor localAnnotatedConstructor = new AnnotatedConstructor(null, localConstructor, null, null);
      return localAnnotatedConstructor;
    }
    catch (Exception localException)
    {
      throw new IllegalArgumentException("Could not find constructor with " + this._serialization.args.length + " args from Class '" + localClass.getName());
    }
  }
  
  public void setValue(Object paramObject1, Object paramObject2)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Cannot call setValue() on constructor of " + getDeclaringClass().getName());
  }
  
  public String toString()
  {
    return "[constructor for " + getName() + ", annotations: " + this._annotations + "]";
  }
  
  public AnnotatedConstructor withAnnotations(AnnotationMap paramAnnotationMap)
  {
    return new AnnotatedConstructor(this._context, this._constructor, paramAnnotationMap, this._paramAnnotations);
  }
  
  Object writeReplace()
  {
    return new AnnotatedConstructor(new Serialization(this._constructor));
  }
  
  private static final class Serialization
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected Class<?>[] args;
    protected Class<?> clazz;
    
    public Serialization(Constructor<?> paramConstructor)
    {
      this.clazz = paramConstructor.getDeclaringClass();
      this.args = paramConstructor.getParameterTypes();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotatedConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */