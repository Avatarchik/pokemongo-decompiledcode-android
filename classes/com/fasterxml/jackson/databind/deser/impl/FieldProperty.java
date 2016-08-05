package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class FieldProperty
  extends SettableBeanProperty
{
  private static final long serialVersionUID = 1L;
  protected final AnnotatedField _annotated;
  protected final transient Field _field;
  
  protected FieldProperty(FieldProperty paramFieldProperty)
  {
    super(paramFieldProperty);
    this._annotated = paramFieldProperty._annotated;
    Field localField = this._annotated.getAnnotated();
    if (localField == null) {
      throw new IllegalArgumentException("Missing field (broken JDK (de)serialization?)");
    }
    this._field = localField;
  }
  
  protected FieldProperty(FieldProperty paramFieldProperty, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(paramFieldProperty, paramJsonDeserializer);
    this._annotated = paramFieldProperty._annotated;
    this._field = paramFieldProperty._field;
  }
  
  protected FieldProperty(FieldProperty paramFieldProperty, PropertyName paramPropertyName)
  {
    super(paramFieldProperty, paramPropertyName);
    this._annotated = paramFieldProperty._annotated;
    this._field = paramFieldProperty._field;
  }
  
  public FieldProperty(BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, AnnotatedField paramAnnotatedField)
  {
    super(paramBeanPropertyDefinition, paramJavaType, paramTypeDeserializer, paramAnnotations);
    this._annotated = paramAnnotatedField;
    this._field = paramAnnotatedField.getAnnotated();
  }
  
  public void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    Object localObject = deserialize(paramJsonParser, paramDeserializationContext);
    try
    {
      this._field.set(paramObject, localObject);
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
    Object localObject = deserialize(paramJsonParser, paramDeserializationContext);
    try
    {
      this._field.set(paramObject, localObject);
      return paramObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        _throwAsIOE(localException, localObject);
      }
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
    return new FieldProperty(this);
  }
  
  public final void set(Object paramObject1, Object paramObject2)
    throws IOException
  {
    try
    {
      this._field.set(paramObject1, paramObject2);
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
    try
    {
      this._field.set(paramObject1, paramObject2);
      return paramObject1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        _throwAsIOE(localException, paramObject2);
      }
    }
  }
  
  public FieldProperty withName(PropertyName paramPropertyName)
  {
    return new FieldProperty(this, paramPropertyName);
  }
  
  public FieldProperty withValueDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    return new FieldProperty(this, paramJsonDeserializer);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/FieldProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */