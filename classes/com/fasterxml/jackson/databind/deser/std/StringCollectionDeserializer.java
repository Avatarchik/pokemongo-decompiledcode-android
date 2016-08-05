package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer
  extends ContainerDeserializerBase<Collection<String>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _collectionType;
  protected final JsonDeserializer<Object> _delegateDeserializer;
  protected final JsonDeserializer<String> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  
  public StringCollectionDeserializer(JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer, ValueInstantiator paramValueInstantiator)
  {
    this(paramJavaType, paramValueInstantiator, null, paramJsonDeserializer);
  }
  
  protected StringCollectionDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2)
  {
    super(paramJavaType);
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer2;
    this._valueInstantiator = paramValueInstantiator;
    this._delegateDeserializer = paramJsonDeserializer1;
  }
  
  private Collection<String> deserializeUsingCustom(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<String> paramCollection, JsonDeserializer<String> paramJsonDeserializer)
    throws IOException
  {
    String str;
    if (paramJsonParser.nextTextValue() == null)
    {
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      if (localJsonToken == JsonToken.END_ARRAY) {
        return paramCollection;
      }
      if (localJsonToken == JsonToken.VALUE_NULL) {
        str = (String)paramJsonDeserializer.getNullValue(paramDeserializationContext);
      }
    }
    for (;;)
    {
      paramCollection.add(str);
      break;
      str = (String)paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      continue;
      str = (String)paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    }
  }
  
  private final Collection<String> handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<String> paramCollection)
    throws IOException
  {
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      throw paramDeserializationContext.mappingException(this._collectionType.getRawClass());
    }
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL)
    {
      if (localJsonDeserializer == null) {}
      for (localObject = null;; localObject = (String)localJsonDeserializer.getNullValue(paramDeserializationContext))
      {
        paramCollection.add(localObject);
        return paramCollection;
      }
    }
    if (localJsonDeserializer == null) {}
    for (Object localObject = _parseString(paramJsonParser, paramDeserializationContext);; localObject = (String)localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext)) {
      break;
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer1 = null;
    if ((this._valueInstantiator != null) && (this._valueInstantiator.getDelegateCreator() != null)) {
      localJsonDeserializer1 = findDeserializer(paramDeserializationContext, this._valueInstantiator.getDelegateType(paramDeserializationContext.getConfig()), paramBeanProperty);
    }
    JsonDeserializer localJsonDeserializer2 = this._valueDeserializer;
    JavaType localJavaType = this._collectionType.getContentType();
    if (localJsonDeserializer2 == null)
    {
      localJsonDeserializer3 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, localJsonDeserializer2);
      if (localJsonDeserializer3 != null) {}
    }
    for (JsonDeserializer localJsonDeserializer3 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);; localJsonDeserializer3 = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer2, paramBeanProperty, localJavaType))
    {
      if (isDefaultDeserializer(localJsonDeserializer3)) {
        localJsonDeserializer3 = null;
      }
      return withResolved(localJsonDeserializer1, localJsonDeserializer3);
    }
  }
  
  public Collection<String> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._delegateDeserializer != null) {}
    for (Collection localCollection = (Collection)this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));; localCollection = deserialize(paramJsonParser, paramDeserializationContext, (Collection)this._valueInstantiator.createUsingDefault(paramDeserializationContext))) {
      return localCollection;
    }
  }
  
  public Collection<String> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<String> paramCollection)
    throws IOException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {}
    for (paramCollection = handleNonArray(paramJsonParser, paramDeserializationContext, paramCollection);; paramCollection = deserializeUsingCustom(paramJsonParser, paramDeserializationContext, paramCollection, this._valueDeserializer))
    {
      return paramCollection;
      if (this._valueDeserializer == null) {
        break;
      }
    }
    for (;;)
    {
      String str;
      try
      {
        str = paramJsonParser.nextTextValue();
        if (str != null)
        {
          paramCollection.add(str);
          continue;
        }
        localJsonToken = paramJsonParser.getCurrentToken();
      }
      catch (Exception localException)
      {
        throw JsonMappingException.wrapWithPath(localException, paramCollection, paramCollection.size());
      }
      JsonToken localJsonToken;
      if (localJsonToken == JsonToken.END_ARRAY) {
        break;
      }
      if (localJsonToken != JsonToken.VALUE_NULL) {
        str = _parseString(paramJsonParser, paramDeserializationContext);
      }
      paramCollection.add(str);
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._collectionType.getContentType();
  }
  
  public boolean isCachable()
  {
    if ((this._valueDeserializer == null) && (this._delegateDeserializer == null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected StringCollectionDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2)
  {
    if ((this._valueDeserializer == paramJsonDeserializer2) && (this._delegateDeserializer == paramJsonDeserializer1)) {}
    for (;;)
    {
      return this;
      this = new StringCollectionDeserializer(this._collectionType, this._valueInstantiator, paramJsonDeserializer1, paramJsonDeserializer2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StringCollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */