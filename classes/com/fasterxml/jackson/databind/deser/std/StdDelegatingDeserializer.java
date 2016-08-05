package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;

public class StdDelegatingDeserializer<T>
  extends StdDeserializer<T>
  implements ContextualDeserializer, ResolvableDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final Converter<Object, T> _converter;
  protected final JsonDeserializer<Object> _delegateDeserializer;
  protected final JavaType _delegateType;
  
  protected StdDelegatingDeserializer(StdDelegatingDeserializer<T> paramStdDelegatingDeserializer)
  {
    super(paramStdDelegatingDeserializer);
    this._converter = paramStdDelegatingDeserializer._converter;
    this._delegateType = paramStdDelegatingDeserializer._delegateType;
    this._delegateDeserializer = paramStdDelegatingDeserializer._delegateDeserializer;
  }
  
  public StdDelegatingDeserializer(Converter<?, T> paramConverter)
  {
    super(Object.class);
    this._converter = paramConverter;
    this._delegateType = null;
    this._delegateDeserializer = null;
  }
  
  public StdDelegatingDeserializer(Converter<Object, T> paramConverter, JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(paramJavaType);
    this._converter = paramConverter;
    this._delegateType = paramJavaType;
    this._delegateDeserializer = paramJsonDeserializer;
  }
  
  protected Object _handleIncompatibleUpdateValue(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    String str = "Can not update object of type %s (using deserializer for type %s)" + paramObject.getClass().getName();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this._delegateType;
    throw new UnsupportedOperationException(String.format(str, arrayOfObject));
  }
  
  protected T convertValue(Object paramObject)
  {
    return (T)this._converter.convert(paramObject);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer;
    if (this._delegateDeserializer != null)
    {
      localJsonDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._delegateDeserializer, paramBeanProperty, this._delegateType);
      if (localJsonDeserializer == this._delegateDeserializer) {}
    }
    JavaType localJavaType;
    for (this = withDelegate(this._converter, this._delegateType, localJsonDeserializer);; this = withDelegate(this._converter, localJavaType, paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty)))
    {
      return this;
      localJavaType = this._converter.getInputType(paramDeserializationContext.getTypeFactory());
    }
  }
  
  public T deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1 = this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    if (localObject1 == null) {}
    for (Object localObject2 = null;; localObject2 = convertValue(localObject1)) {
      return (T)localObject2;
    }
  }
  
  public T deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (this._delegateType.getRawClass().isAssignableFrom(paramObject.getClass())) {}
    for (Object localObject = this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext, paramObject);; localObject = _handleIncompatibleUpdateValue(paramJsonParser, paramDeserializationContext, paramObject)) {
      return (T)localObject;
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    Object localObject1 = this._delegateDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, paramTypeDeserializer);
    if (localObject1 == null) {}
    for (Object localObject2 = null;; localObject2 = convertValue(localObject1)) {
      return localObject2;
    }
  }
  
  public JsonDeserializer<?> getDelegatee()
  {
    return this._delegateDeserializer;
  }
  
  public Class<?> handledType()
  {
    return this._delegateDeserializer.handledType();
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    if ((this._delegateDeserializer != null) && ((this._delegateDeserializer instanceof ResolvableDeserializer))) {
      ((ResolvableDeserializer)this._delegateDeserializer).resolve(paramDeserializationContext);
    }
  }
  
  protected StdDelegatingDeserializer<T> withDelegate(Converter<Object, T> paramConverter, JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer)
  {
    if (getClass() != StdDelegatingDeserializer.class) {
      throw new IllegalStateException("Sub-class " + getClass().getName() + " must override 'withDelegate'");
    }
    return new StdDelegatingDeserializer(paramConverter, paramJavaType, paramJsonDeserializer);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StdDelegatingDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */