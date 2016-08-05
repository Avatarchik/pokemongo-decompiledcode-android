package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class MethodProperty
  extends SettableBeanProperty
{
  private static final long serialVersionUID = 1L;
  protected final AnnotatedMethod _annotated;
  protected final transient Method _setter;
  
  protected MethodProperty(MethodProperty paramMethodProperty, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(paramMethodProperty, paramJsonDeserializer);
    this._annotated = paramMethodProperty._annotated;
    this._setter = paramMethodProperty._setter;
  }
  
  protected MethodProperty(MethodProperty paramMethodProperty, PropertyName paramPropertyName)
  {
    super(paramMethodProperty, paramPropertyName);
    this._annotated = paramMethodProperty._annotated;
    this._setter = paramMethodProperty._setter;
  }
  
  protected MethodProperty(MethodProperty paramMethodProperty, Method paramMethod)
  {
    super(paramMethodProperty);
    this._annotated = paramMethodProperty._annotated;
    this._setter = paramMethod;
  }
  
  public MethodProperty(BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, AnnotatedMethod paramAnnotatedMethod)
  {
    super(paramBeanPropertyDefinition, paramJavaType, paramTypeDeserializer, paramAnnotations);
    this._annotated = paramAnnotatedMethod;
    this._setter = paramAnnotatedMethod.getAnnotated();
  }
  
  public void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    Object localObject = deserialize(paramJsonParser, paramDeserializationContext);
    try
    {
      Method localMethod = this._setter;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localObject;
      localMethod.invoke(paramObject, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        _throwAsIOE(localException, localObject);
      }
    }
  }
  
  public Object deserializeSetAndReturn(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    localObject1 = deserialize(paramJsonParser, paramDeserializationContext);
    for (;;)
    {
      try
      {
        Method localMethod = this._setter;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localObject1;
        localObject2 = localMethod.invoke(paramObject, arrayOfObject);
        if (localObject2 != null) {
          continue;
        }
      }
      catch (Exception localException)
      {
        Object localObject2;
        _throwAsIOE(localException, localObject1);
        paramObject = null;
        continue;
      }
      return paramObject;
      paramObject = localObject2;
    }
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    if (this._annotated == null) {}
    for (Object localObject = null;; localObject = this._annotated.getAnnotation(paramClass)) {
      return (A)localObject;
    }
  }
  
  public AnnotatedMember getMember()
  {
    return this._annotated;
  }
  
  Object readResolve()
  {
    return new MethodProperty(this, this._annotated.getAnnotated());
  }
  
  public final void set(Object paramObject1, Object paramObject2)
    throws IOException
  {
    try
    {
      Method localMethod = this._setter;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramObject2;
      localMethod.invoke(paramObject1, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        _throwAsIOE(localException, paramObject2);
      }
    }
  }
  
  public Object setAndReturn(Object paramObject1, Object paramObject2)
    throws IOException
  {
    for (;;)
    {
      try
      {
        Method localMethod = this._setter;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramObject2;
        localObject = localMethod.invoke(paramObject1, arrayOfObject);
        if (localObject != null) {
          continue;
        }
      }
      catch (Exception localException)
      {
        Object localObject;
        _throwAsIOE(localException, paramObject2);
        paramObject1 = null;
        continue;
      }
      return paramObject1;
      paramObject1 = localObject;
    }
  }
  
  public MethodProperty withName(PropertyName paramPropertyName)
  {
    return new MethodProperty(this, paramPropertyName);
  }
  
  public MethodProperty withValueDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    return new MethodProperty(this, paramJsonDeserializer);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/MethodProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */