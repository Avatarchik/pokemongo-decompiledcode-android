package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnwrappedPropertyHandler
{
  protected final List<SettableBeanProperty> _properties;
  
  public UnwrappedPropertyHandler()
  {
    this._properties = new ArrayList();
  }
  
  protected UnwrappedPropertyHandler(List<SettableBeanProperty> paramList)
  {
    this._properties = paramList;
  }
  
  public void addProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    this._properties.add(paramSettableBeanProperty);
  }
  
  public Object processUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer)
    throws IOException, JsonProcessingException
  {
    int i = 0;
    int j = this._properties.size();
    while (i < j)
    {
      SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._properties.get(i);
      JsonParser localJsonParser = paramTokenBuffer.asParser();
      localJsonParser.nextToken();
      localSettableBeanProperty.deserializeAndSet(localJsonParser, paramDeserializationContext, paramObject);
      i++;
    }
    return paramObject;
  }
  
  public UnwrappedPropertyHandler renameAll(NameTransformer paramNameTransformer)
  {
    ArrayList localArrayList = new ArrayList(this._properties.size());
    Iterator localIterator = this._properties.iterator();
    while (localIterator.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty1 = (SettableBeanProperty)localIterator.next();
      SettableBeanProperty localSettableBeanProperty2 = localSettableBeanProperty1.withSimpleName(paramNameTransformer.transform(localSettableBeanProperty1.getName()));
      JsonDeserializer localJsonDeserializer1 = localSettableBeanProperty2.getValueDeserializer();
      if (localJsonDeserializer1 != null)
      {
        JsonDeserializer localJsonDeserializer2 = localJsonDeserializer1.unwrappingDeserializer(paramNameTransformer);
        if (localJsonDeserializer2 != localJsonDeserializer1) {
          localSettableBeanProperty2 = localSettableBeanProperty2.withValueDeserializer(localJsonDeserializer2);
        }
      }
      localArrayList.add(localSettableBeanProperty2);
    }
    return new UnwrappedPropertyHandler(localArrayList);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/UnwrappedPropertyHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */