package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public final class AnnotatedField
  extends AnnotatedMember
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final transient Field _field;
  protected Serialization _serialization;
  
  public AnnotatedField(AnnotatedClass paramAnnotatedClass, Field paramField, AnnotationMap paramAnnotationMap)
  {
    super(paramAnnotatedClass, paramAnnotationMap);
    this._field = paramField;
  }
  
  protected AnnotatedField(Serialization paramSerialization)
  {
    super(null, null);
    this._field = null;
    this._serialization = paramSerialization;
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
      } else if (((AnnotatedField)paramObject)._field != this._field) {
        bool = false;
      }
    }
  }
  
  public Field getAnnotated()
  {
    return this._field;
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    if (this._annotations == null) {}
    for (Object localObject = null;; localObject = this._annotations.get(paramClass)) {
      return (A)localObject;
    }
  }
  
  public int getAnnotationCount()
  {
    return this._annotations.size();
  }
  
  public Class<?> getDeclaringClass()
  {
    return this._field.getDeclaringClass();
  }
  
  public String getFullName()
  {
    return getDeclaringClass().getName() + "#" + getName();
  }
  
  public Type getGenericType()
  {
    return this._field.getGenericType();
  }
  
  public Member getMember()
  {
    return this._field;
  }
  
  public int getModifiers()
  {
    return this._field.getModifiers();
  }
  
  public String getName()
  {
    return this._field.getName();
  }
  
  public Class<?> getRawType()
  {
    return this._field.getType();
  }
  
  public Object getValue(Object paramObject)
    throws IllegalArgumentException
  {
    try
    {
      Object localObject = this._field.get(paramObject);
      return localObject;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalArgumentException("Failed to getValue() for field " + getFullName() + ": " + localIllegalAccessException.getMessage(), localIllegalAccessException);
    }
  }
  
  public int hashCode()
  {
    return this._field.getName().hashCode();
  }
  
  public boolean isTransient()
  {
    return Modifier.isTransient(getModifiers());
  }
  
  Object readResolve()
  {
    Class localClass = this._serialization.clazz;
    try
    {
      Field localField = localClass.getDeclaredField(this._serialization.name);
      if (!localField.isAccessible()) {
        ClassUtil.checkAndFixAccess(localField);
      }
      AnnotatedField localAnnotatedField = new AnnotatedField(null, localField, null);
      return localAnnotatedField;
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
      this._field.set(paramObject1, paramObject2);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalArgumentException("Failed to setValue() for field " + getFullName() + ": " + localIllegalAccessException.getMessage(), localIllegalAccessException);
    }
  }
  
  public String toString()
  {
    return "[field " + getFullName() + "]";
  }
  
  public AnnotatedField withAnnotations(AnnotationMap paramAnnotationMap)
  {
    return new AnnotatedField(this._context, this._field, paramAnnotationMap);
  }
  
  Object writeReplace()
  {
    return new AnnotatedField(new Serialization(this._field));
  }
  
  private static final class Serialization
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected Class<?> clazz;
    protected String name;
    
    public Serialization(Field paramField)
    {
      this.clazz = paramField.getDeclaringClass();
      this.name = paramField.getName();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotatedField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */