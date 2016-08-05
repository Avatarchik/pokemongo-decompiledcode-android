package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class PropertyBasedCreator
{
  protected final SettableBeanProperty[] _allProperties;
  protected final int _propertyCount;
  protected final HashMap<String, SettableBeanProperty> _propertyLookup;
  protected final ValueInstantiator _valueInstantiator;
  
  protected PropertyBasedCreator(ValueInstantiator paramValueInstantiator, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
  {
    this._valueInstantiator = paramValueInstantiator;
    this._propertyLookup = new HashMap();
    int i = paramArrayOfSettableBeanProperty.length;
    this._propertyCount = i;
    this._allProperties = new SettableBeanProperty[i];
    for (int j = 0; j < i; j++)
    {
      SettableBeanProperty localSettableBeanProperty = paramArrayOfSettableBeanProperty[j];
      this._allProperties[j] = localSettableBeanProperty;
      this._propertyLookup.put(localSettableBeanProperty.getName(), localSettableBeanProperty);
    }
  }
  
  public static PropertyBasedCreator construct(DeserializationContext paramDeserializationContext, ValueInstantiator paramValueInstantiator, SettableBeanProperty[] paramArrayOfSettableBeanProperty)
    throws JsonMappingException
  {
    int i = paramArrayOfSettableBeanProperty.length;
    SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[i];
    for (int j = 0; j < i; j++)
    {
      SettableBeanProperty localSettableBeanProperty = paramArrayOfSettableBeanProperty[j];
      if (!localSettableBeanProperty.hasValueDeserializer()) {
        localSettableBeanProperty = localSettableBeanProperty.withValueDeserializer(paramDeserializationContext.findContextualValueDeserializer(localSettableBeanProperty.getType(), localSettableBeanProperty));
      }
      arrayOfSettableBeanProperty[j] = localSettableBeanProperty;
    }
    return new PropertyBasedCreator(paramValueInstantiator, arrayOfSettableBeanProperty);
  }
  
  public Object build(DeserializationContext paramDeserializationContext, PropertyValueBuffer paramPropertyValueBuffer)
    throws IOException
  {
    Object localObject = this._valueInstantiator.createFromObjectWith(paramDeserializationContext, paramPropertyValueBuffer.getParameters(this._allProperties));
    if (localObject != null)
    {
      localObject = paramPropertyValueBuffer.handleIdValue(paramDeserializationContext, localObject);
      for (PropertyValue localPropertyValue = paramPropertyValueBuffer.buffered(); localPropertyValue != null; localPropertyValue = localPropertyValue.next) {
        localPropertyValue.assign(localObject);
      }
    }
    return localObject;
  }
  
  public SettableBeanProperty findCreatorProperty(int paramInt)
  {
    Iterator localIterator = this._propertyLookup.values().iterator();
    SettableBeanProperty localSettableBeanProperty;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localSettableBeanProperty = (SettableBeanProperty)localIterator.next();
    } while (localSettableBeanProperty.getPropertyIndex() != paramInt);
    for (;;)
    {
      return localSettableBeanProperty;
      localSettableBeanProperty = null;
    }
  }
  
  public SettableBeanProperty findCreatorProperty(String paramString)
  {
    return (SettableBeanProperty)this._propertyLookup.get(paramString);
  }
  
  public Collection<SettableBeanProperty> properties()
  {
    return this._propertyLookup.values();
  }
  
  public PropertyValueBuffer startBuilding(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, ObjectIdReader paramObjectIdReader)
  {
    return new PropertyValueBuffer(paramJsonParser, paramDeserializationContext, this._propertyCount, paramObjectIdReader);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/PropertyBasedCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */