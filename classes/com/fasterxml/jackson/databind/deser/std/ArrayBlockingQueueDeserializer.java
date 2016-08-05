package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDeserializer
  extends CollectionDeserializer
{
  private static final long serialVersionUID = 1L;
  
  public ArrayBlockingQueueDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer1, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator, JsonDeserializer<Object> paramJsonDeserializer2)
  {
    super(paramJavaType, paramJsonDeserializer1, paramTypeDeserializer, paramValueInstantiator, paramJsonDeserializer2);
  }
  
  protected ArrayBlockingQueueDeserializer(ArrayBlockingQueueDeserializer paramArrayBlockingQueueDeserializer)
  {
    super(paramArrayBlockingQueueDeserializer);
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Collection localCollection;
    if (this._delegateDeserializer != null) {
      localCollection = (Collection)this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    for (;;)
    {
      return localCollection;
      if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING)
      {
        String str = paramJsonParser.getText();
        if (str.length() == 0)
        {
          localCollection = (Collection)this._valueInstantiator.createFromString(paramDeserializationContext, str);
          continue;
        }
      }
      localCollection = deserialize(paramJsonParser, paramDeserializationContext, null);
    }
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      paramCollection = handleNonArray(paramJsonParser, paramDeserializationContext, new ArrayBlockingQueue(1));
    }
    for (;;)
    {
      return paramCollection;
      ArrayList localArrayList = new ArrayList();
      JsonDeserializer localJsonDeserializer = this._valueDeserializer;
      TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
      for (;;)
      {
        try
        {
          JsonToken localJsonToken = paramJsonParser.nextToken();
          if (localJsonToken == JsonToken.END_ARRAY) {
            break;
          }
          if (localJsonToken == JsonToken.VALUE_NULL)
          {
            localObject2 = localJsonDeserializer.getNullValue(paramDeserializationContext);
            localArrayList.add(localObject2);
            continue;
          }
          if (localTypeDeserializer != null) {
            break label118;
          }
        }
        catch (Exception localException)
        {
          throw JsonMappingException.wrapWithPath(localException, localArrayList, localArrayList.size());
        }
        Object localObject2 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        continue;
        label118:
        Object localObject1 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
        localObject2 = localObject1;
      }
      if (paramCollection != null) {
        paramCollection.addAll(localArrayList);
      } else {
        paramCollection = new ArrayBlockingQueue(localArrayList.size(), false, localArrayList);
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  protected ArrayBlockingQueueDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, TypeDeserializer paramTypeDeserializer)
  {
    if ((paramJsonDeserializer1 == this._delegateDeserializer) && (paramJsonDeserializer2 == this._valueDeserializer) && (paramTypeDeserializer == this._valueTypeDeserializer)) {}
    for (;;)
    {
      return this;
      this = new ArrayBlockingQueueDeserializer(this._collectionType, paramJsonDeserializer2, paramTypeDeserializer, this._valueInstantiator, paramJsonDeserializer1);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/ArrayBlockingQueueDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */