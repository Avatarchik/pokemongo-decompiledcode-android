package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class AbstractDeserializer
  extends JsonDeserializer<Object>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final boolean _acceptBoolean;
  protected final boolean _acceptDouble;
  protected final boolean _acceptInt;
  protected final boolean _acceptString;
  protected final Map<String, SettableBeanProperty> _backRefProperties;
  protected final JavaType _baseType;
  protected final ObjectIdReader _objectIdReader;
  
  protected AbstractDeserializer(BeanDescription paramBeanDescription)
  {
    this._baseType = paramBeanDescription.getType();
    this._objectIdReader = null;
    this._backRefProperties = null;
    Class localClass = this._baseType.getRawClass();
    this._acceptString = localClass.isAssignableFrom(String.class);
    boolean bool2;
    if ((localClass == Boolean.TYPE) || (localClass.isAssignableFrom(Boolean.class)))
    {
      bool2 = true;
      this._acceptBoolean = bool2;
      if ((localClass != Integer.TYPE) && (!localClass.isAssignableFrom(Integer.class))) {
        break label122;
      }
    }
    label122:
    for (boolean bool3 = true;; bool3 = false)
    {
      this._acceptInt = bool3;
      if ((localClass == Double.TYPE) || (localClass.isAssignableFrom(Double.class))) {
        bool1 = true;
      }
      this._acceptDouble = bool1;
      return;
      bool2 = false;
      break;
    }
  }
  
  public AbstractDeserializer(BeanDeserializerBuilder paramBeanDeserializerBuilder, BeanDescription paramBeanDescription, Map<String, SettableBeanProperty> paramMap)
  {
    this._baseType = paramBeanDescription.getType();
    this._objectIdReader = paramBeanDeserializerBuilder.getObjectIdReader();
    this._backRefProperties = paramMap;
    Class localClass = this._baseType.getRawClass();
    this._acceptString = localClass.isAssignableFrom(String.class);
    boolean bool2;
    if ((localClass == Boolean.TYPE) || (localClass.isAssignableFrom(Boolean.class)))
    {
      bool2 = true;
      this._acceptBoolean = bool2;
      if ((localClass != Integer.TYPE) && (!localClass.isAssignableFrom(Integer.class))) {
        break label136;
      }
    }
    label136:
    for (boolean bool3 = true;; bool3 = false)
    {
      this._acceptInt = bool3;
      if ((localClass == Double.TYPE) || (localClass.isAssignableFrom(Double.class))) {
        bool1 = true;
      }
      this._acceptDouble = bool1;
      return;
      bool2 = false;
      break;
    }
  }
  
  public static AbstractDeserializer constructForNonPOJO(BeanDescription paramBeanDescription)
  {
    return new AbstractDeserializer(paramBeanDescription);
  }
  
  protected Object _deserializeFromObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1 = this._objectIdReader.readObjectReference(paramJsonParser, paramDeserializationContext);
    ReadableObjectId localReadableObjectId = paramDeserializationContext.findObjectId(localObject1, this._objectIdReader.generator, this._objectIdReader.resolver);
    Object localObject2 = localReadableObjectId.resolve();
    if (localObject2 == null) {
      throw new UnresolvedForwardReference("Could not resolve Object Id [" + localObject1 + "] -- unresolved forward-reference?", paramJsonParser.getCurrentLocation(), localReadableObjectId);
    }
    return localObject2;
  }
  
  protected Object _deserializeIfNatural(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    switch (paramJsonParser.getCurrentTokenId())
    {
    default: 
      localObject = null;
    }
    for (;;)
    {
      return localObject;
      if (!this._acceptString) {
        break;
      }
      localObject = paramJsonParser.getText();
      continue;
      if (!this._acceptInt) {
        break;
      }
      localObject = Integer.valueOf(paramJsonParser.getIntValue());
      continue;
      if (!this._acceptDouble) {
        break;
      }
      localObject = Double.valueOf(paramJsonParser.getDoubleValue());
      continue;
      if (!this._acceptBoolean) {
        break;
      }
      localObject = Boolean.TRUE;
      continue;
      if (!this._acceptBoolean) {
        break;
      }
      localObject = Boolean.FALSE;
    }
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    throw paramDeserializationContext.instantiationException(this._baseType.getRawClass(), "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    Object localObject;
    if (this._objectIdReader != null)
    {
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      if ((localJsonToken != null) && (localJsonToken.isScalarValue())) {
        localObject = _deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
      }
    }
    for (;;)
    {
      return localObject;
      localObject = _deserializeIfNatural(paramJsonParser, paramDeserializationContext);
      if (localObject == null) {
        localObject = paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
      }
    }
  }
  
  public SettableBeanProperty findBackReference(String paramString)
  {
    if (this._backRefProperties == null) {}
    for (SettableBeanProperty localSettableBeanProperty = null;; localSettableBeanProperty = (SettableBeanProperty)this._backRefProperties.get(paramString)) {
      return localSettableBeanProperty;
    }
  }
  
  public ObjectIdReader getObjectIdReader()
  {
    return this._objectIdReader;
  }
  
  public Class<?> handledType()
  {
    return this._baseType.getRawClass();
  }
  
  public boolean isCachable()
  {
    return true;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/AbstractDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */