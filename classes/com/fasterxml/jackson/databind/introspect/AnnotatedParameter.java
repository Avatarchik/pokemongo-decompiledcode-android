package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedParameter
  extends AnnotatedMember
{
  private static final long serialVersionUID = 1L;
  protected final int _index;
  protected final AnnotatedWithParams _owner;
  protected final Type _type;
  
  public AnnotatedParameter(AnnotatedWithParams paramAnnotatedWithParams, Type paramType, AnnotationMap paramAnnotationMap, int paramInt) {}
  
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
        AnnotatedParameter localAnnotatedParameter = (AnnotatedParameter)paramObject;
        if ((!localAnnotatedParameter._owner.equals(this._owner)) || (localAnnotatedParameter._index != this._index)) {
          bool = false;
        }
      }
    }
  }
  
  public AnnotatedElement getAnnotated()
  {
    return null;
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    if (this._annotations == null) {}
    for (Object localObject = null;; localObject = this._annotations.get(paramClass)) {
      return (A)localObject;
    }
  }
  
  public Class<?> getDeclaringClass()
  {
    return this._owner.getDeclaringClass();
  }
  
  public Type getGenericType()
  {
    return this._type;
  }
  
  public int getIndex()
  {
    return this._index;
  }
  
  public Member getMember()
  {
    return this._owner.getMember();
  }
  
  public int getModifiers()
  {
    return this._owner.getModifiers();
  }
  
  public String getName()
  {
    return "";
  }
  
  public AnnotatedWithParams getOwner()
  {
    return this._owner;
  }
  
  public Type getParameterType()
  {
    return this._type;
  }
  
  public Class<?> getRawType()
  {
    if ((this._type instanceof Class)) {}
    for (Class localClass = (Class)this._type;; localClass = TypeFactory.defaultInstance().constructType(this._type).getRawClass()) {
      return localClass;
    }
  }
  
  public Object getValue(Object paramObject)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Cannot call getValue() on constructor parameter of " + getDeclaringClass().getName());
  }
  
  public int hashCode()
  {
    return this._owner.hashCode() + this._index;
  }
  
  public void setValue(Object paramObject1, Object paramObject2)
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Cannot call setValue() on constructor parameter of " + getDeclaringClass().getName());
  }
  
  public String toString()
  {
    return "[parameter #" + getIndex() + ", annotations: " + this._annotations + "]";
  }
  
  public AnnotatedParameter withAnnotations(AnnotationMap paramAnnotationMap)
  {
    if (paramAnnotationMap == this._annotations) {}
    for (;;)
    {
      return this;
      this = this._owner.replaceParameterAnnotations(this._index, paramAnnotationMap);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotatedParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */