package com.fasterxml.jackson.databind.introspect;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public class VirtualAnnotatedMember
  extends AnnotatedMember
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _declaringClass;
  protected final String _name;
  protected final Class<?> _rawType;
  
  public VirtualAnnotatedMember(AnnotatedClass paramAnnotatedClass, Class<?> paramClass1, String paramString, Class<?> paramClass2)
  {
    super(paramAnnotatedClass, null);
    this._declaringClass = paramClass1;
    this._rawType = paramClass2;
    this._name = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if ((paramObject == null) || (paramObject.getClass() != getClass()))
      {
        bool = false;
      }
      else
      {
        VirtualAnnotatedMember localVirtualAnnotatedMember = (VirtualAnnotatedMember)paramObject;
        if ((localVirtualAnnotatedMember._declaringClass != this._declaringClass) || (!localVirtualAnnotatedMember._name.equals(this._name))) {
          bool = false;
        }
      }
    }
  }
  
  public Field getAnnotated()
  {
    return null;
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    return null;
  }
  
  public int getAnnotationCount()
  {
    return 0;
  }
  
  public Class<?> getDeclaringClass()
  {
    return this._declaringClass;
  }
  
  public String getFullName()
  {
    return getDeclaringClass().getName() + "#" + getName();
  }
  
  public Type getGenericType()
  {
    return this._rawType;
  }
  
  public Member getMember()
  {
    return null;
  }
  
  public int getModifiers()
  {
    return 0;
  }
  
  public String getName()
  {
    return this._name;
  }
  
  public Class<?> getRawType()
  {
    return this._rawType;
  }
  
  public Object getValue(Object paramObject)
    throws IllegalArgumentException
  {
    throw new IllegalArgumentException("Can not get virtual property '" + this._name + "'");
  }
  
  public int hashCode()
  {
    return this._name.hashCode();
  }
  
  public void setValue(Object paramObject1, Object paramObject2)
    throws IllegalArgumentException
  {
    throw new IllegalArgumentException("Can not set virtual property '" + this._name + "'");
  }
  
  public String toString()
  {
    return "[field " + getFullName() + "]";
  }
  
  public Annotated withAnnotations(AnnotationMap paramAnnotationMap)
  {
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/VirtualAnnotatedMember.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */