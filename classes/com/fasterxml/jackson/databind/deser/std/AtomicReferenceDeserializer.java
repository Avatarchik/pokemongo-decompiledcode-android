package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDeserializer
  extends StdDeserializer<AtomicReference<?>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _referencedType;
  protected final JsonDeserializer<?> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public AtomicReferenceDeserializer(JavaType paramJavaType)
  {
    this(paramJavaType, null, null);
  }
  
  public AtomicReferenceDeserializer(JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(AtomicReference.class);
    this._referencedType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    if (localJsonDeserializer == null) {
      localJsonDeserializer = paramDeserializationContext.findContextualValueDeserializer(this._referencedType, paramBeanProperty);
    }
    if (localTypeDeserializer != null) {
      localTypeDeserializer = localTypeDeserializer.forProperty(paramBeanProperty);
    }
    if ((localJsonDeserializer == this._valueDeserializer) && (localTypeDeserializer == this._valueTypeDeserializer)) {}
    for (;;)
    {
      return this;
      this = withResolved(localTypeDeserializer, localJsonDeserializer);
    }
  }
  
  public AtomicReference<?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._valueTypeDeserializer != null) {}
    for (AtomicReference localAtomicReference = new AtomicReference(this._valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._valueTypeDeserializer));; localAtomicReference = new AtomicReference(this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext))) {
      return localAtomicReference;
    }
  }
  
  public Object[] deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return (Object[])paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  @Deprecated
  public AtomicReference<?> getNullValue()
  {
    return new AtomicReference();
  }
  
  public AtomicReference<?> getNullValue(DeserializationContext paramDeserializationContext)
  {
    return new AtomicReference();
  }
  
  public AtomicReferenceDeserializer withResolved(TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    return new AtomicReferenceDeserializer(this._referencedType, paramTypeDeserializer, paramJsonDeserializer);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/AtomicReferenceDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */