package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;

public class SettableAnyProperty
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final BeanProperty _property;
  protected final AnnotatedMethod _setter;
  protected final JavaType _type;
  protected JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public SettableAnyProperty(BeanProperty paramBeanProperty, AnnotatedMethod paramAnnotatedMethod, JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    this._property = paramBeanProperty;
    this._setter = paramAnnotatedMethod;
    this._type = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  protected SettableAnyProperty(SettableAnyProperty paramSettableAnyProperty)
  {
    this._property = paramSettableAnyProperty._property;
    this._setter = paramSettableAnyProperty._setter;
    this._type = paramSettableAnyProperty._type;
    this._valueDeserializer = paramSettableAnyProperty._valueDeserializer;
    this._valueTypeDeserializer = paramSettableAnyProperty._valueTypeDeserializer;
  }
  
  private String getClassName()
  {
    return this._setter.getDeclaringClass().getName();
  }
  
  protected void _throwAsIOE(Exception paramException, String paramString, Object paramObject)
    throws IOException
  {
    if ((paramException instanceof IllegalArgumentException))
    {
      String str1;
      StringBuilder localStringBuilder;
      if (paramObject == null)
      {
        str1 = "[NULL]";
        localStringBuilder = new StringBuilder("Problem deserializing \"any\" property '").append(paramString);
        localStringBuilder.append("' of class " + getClassName() + " (expected type: ").append(this._type);
        localStringBuilder.append("; actual type: ").append(str1).append(")");
        String str2 = paramException.getMessage();
        if (str2 == null) {
          break label139;
        }
        localStringBuilder.append(", problem: ").append(str2);
      }
      for (;;)
      {
        throw new JsonMappingException(localStringBuilder.toString(), null, paramException);
        str1 = paramObject.getClass().getName();
        break;
        label139:
        localStringBuilder.append(" (no error message provided)");
      }
    }
    if ((paramException instanceof IOException)) {
      throw ((IOException)paramException);
    }
    if ((paramException instanceof RuntimeException)) {
      throw ((RuntimeException)paramException);
    }
    for (Object localObject = paramException; ((Throwable)localObject).getCause() != null; localObject = ((Throwable)localObject).getCause()) {}
    throw new JsonMappingException(((Throwable)localObject).getMessage(), null, (Throwable)localObject);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      localObject = null;
    }
    for (;;)
    {
      return localObject;
      if (this._valueTypeDeserializer != null) {
        localObject = this._valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._valueTypeDeserializer);
      } else {
        localObject = this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
    }
  }
  
  public final void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException
  {
    try
    {
      set(paramObject, paramString, deserialize(paramJsonParser, paramDeserializationContext));
      return;
    }
    catch (UnresolvedForwardReference localUnresolvedForwardReference)
    {
      for (;;)
      {
        if (this._valueDeserializer.getObjectIdReader() == null) {
          throw JsonMappingException.from(paramJsonParser, "Unresolved forward reference but no identity info.", localUnresolvedForwardReference);
        }
        AnySetterReferring localAnySetterReferring = new AnySetterReferring(this, localUnresolvedForwardReference, this._type.getRawClass(), paramObject, paramString);
        localUnresolvedForwardReference.getRoid().appendReferring(localAnySetterReferring);
      }
    }
  }
  
  public BeanProperty getProperty()
  {
    return this._property;
  }
  
  public JavaType getType()
  {
    return this._type;
  }
  
  public boolean hasValueDeserializer()
  {
    if (this._valueDeserializer != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  Object readResolve()
  {
    if ((this._setter == null) || (this._setter.getAnnotated() == null)) {
      throw new IllegalArgumentException("Missing method (broken JDK (de)serialization?)");
    }
    return this;
  }
  
  public void set(Object paramObject1, String paramString, Object paramObject2)
    throws IOException
  {
    try
    {
      Method localMethod = this._setter.getAnnotated();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramString;
      arrayOfObject[1] = paramObject2;
      localMethod.invoke(paramObject1, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        _throwAsIOE(localException, paramString, paramObject2);
      }
    }
  }
  
  public String toString()
  {
    return "[any property on class " + getClassName() + "]";
  }
  
  public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> paramJsonDeserializer)
  {
    return new SettableAnyProperty(this._property, this._setter, this._type, paramJsonDeserializer, this._valueTypeDeserializer);
  }
  
  private static class AnySetterReferring
    extends ReadableObjectId.Referring
  {
    private final SettableAnyProperty _parent;
    private final Object _pojo;
    private final String _propName;
    
    public AnySetterReferring(SettableAnyProperty paramSettableAnyProperty, UnresolvedForwardReference paramUnresolvedForwardReference, Class<?> paramClass, Object paramObject, String paramString)
    {
      super(paramClass);
      this._parent = paramSettableAnyProperty;
      this._pojo = paramObject;
      this._propName = paramString;
    }
    
    public void handleResolvedForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      if (!hasId(paramObject1)) {
        throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + paramObject1.toString() + "] that wasn't previously registered.");
      }
      this._parent.set(this._pojo, this._propName, paramObject2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/SettableAnyProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */