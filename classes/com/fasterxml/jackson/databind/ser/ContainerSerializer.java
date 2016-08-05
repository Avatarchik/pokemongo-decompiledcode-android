package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class ContainerSerializer<T>
  extends StdSerializer<T>
{
  protected ContainerSerializer(JavaType paramJavaType)
  {
    super(paramJavaType);
  }
  
  protected ContainerSerializer(ContainerSerializer<?> paramContainerSerializer)
  {
    super(paramContainerSerializer._handledType, false);
  }
  
  protected ContainerSerializer(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  protected ContainerSerializer(Class<?> paramClass, boolean paramBoolean)
  {
    super(paramClass, paramBoolean);
  }
  
  protected abstract ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer);
  
  public abstract JsonSerializer<?> getContentSerializer();
  
  public abstract JavaType getContentType();
  
  protected boolean hasContentTypeAnnotation(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
  {
    if (paramBeanProperty != null)
    {
      AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
      AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
      if ((localAnnotatedMember == null) || (localAnnotationIntrospector == null) || (localAnnotationIntrospector.findSerializationContentType(localAnnotatedMember, paramBeanProperty.getType()) == null)) {}
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public abstract boolean hasSingleElement(T paramT);
  
  @Deprecated
  public boolean isEmpty(T paramT)
  {
    return isEmpty(null, paramT);
  }
  
  public ContainerSerializer<?> withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    if (paramTypeSerializer == null) {}
    for (;;)
    {
      return this;
      this = _withValueTypeSerializer(paramTypeSerializer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/ContainerSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */