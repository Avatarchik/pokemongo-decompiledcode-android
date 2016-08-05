package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.util.BitSet;

public class PropertyValueBuffer
{
  protected PropertyValue _buffered;
  protected final DeserializationContext _context;
  protected final Object[] _creatorParameters;
  protected Object _idValue;
  protected final ObjectIdReader _objectIdReader;
  protected int _paramsNeeded;
  protected int _paramsSeen;
  protected final BitSet _paramsSeenBig;
  protected final JsonParser _parser;
  
  public PropertyValueBuffer(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, int paramInt, ObjectIdReader paramObjectIdReader)
  {
    this._parser = paramJsonParser;
    this._context = paramDeserializationContext;
    this._paramsNeeded = paramInt;
    this._objectIdReader = paramObjectIdReader;
    this._creatorParameters = new Object[paramInt];
    if (paramInt < 32) {}
    for (this._paramsSeenBig = null;; this._paramsSeenBig = new BitSet()) {
      return;
    }
  }
  
  protected Object _findMissing(SettableBeanProperty paramSettableBeanProperty)
    throws JsonMappingException
  {
    if (paramSettableBeanProperty.getInjectableValueId() != null) {}
    for (Object localObject = this._context.findInjectableValue(paramSettableBeanProperty.getInjectableValueId(), paramSettableBeanProperty, null);; localObject = paramSettableBeanProperty.getValueDeserializer().getNullValue(this._context))
    {
      return localObject;
      if (paramSettableBeanProperty.isRequired())
      {
        DeserializationContext localDeserializationContext2 = this._context;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = paramSettableBeanProperty.getName();
        arrayOfObject2[1] = Integer.valueOf(paramSettableBeanProperty.getCreatorIndex());
        throw localDeserializationContext2.mappingException("Missing required creator property '%s' (index %d)", arrayOfObject2);
      }
      if (this._context.isEnabled(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES))
      {
        DeserializationContext localDeserializationContext1 = this._context;
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = paramSettableBeanProperty.getName();
        arrayOfObject1[1] = Integer.valueOf(paramSettableBeanProperty.getCreatorIndex());
        throw localDeserializationContext1.mappingException("Missing creator property '%s' (index %d); DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES enabled", arrayOfObject1);
      }
    }
  }
  
  @Deprecated
  public boolean assignParameter(int paramInt, Object paramObject)
  {
    this._creatorParameters[paramInt] = paramObject;
    return false;
  }
  
  public boolean assignParameter(SettableBeanProperty paramSettableBeanProperty, Object paramObject)
  {
    int i = 1;
    int j = paramSettableBeanProperty.getCreatorIndex();
    this._creatorParameters[j] = paramObject;
    if (this._paramsSeenBig == null)
    {
      int m = this._paramsSeen;
      int n = m | i << j;
      if (m == n) {
        break label114;
      }
      this._paramsSeen = n;
      int i1 = -1 + this._paramsNeeded;
      this._paramsNeeded = i1;
      if (i1 > 0) {
        break label114;
      }
    }
    for (;;)
    {
      return i;
      if (!this._paramsSeenBig.get(j))
      {
        int k = -1 + this._paramsNeeded;
        this._paramsNeeded = k;
        if (k > 0) {
          this._paramsSeenBig.set(j);
        }
      }
      else
      {
        label114:
        i = 0;
      }
    }
  }
  
  public void bufferAnyProperty(SettableAnyProperty paramSettableAnyProperty, String paramString, Object paramObject)
  {
    this._buffered = new PropertyValue.Any(this._buffered, paramObject, paramSettableAnyProperty, paramString);
  }
  
  public void bufferMapProperty(Object paramObject1, Object paramObject2)
  {
    this._buffered = new PropertyValue.Map(this._buffered, paramObject2, paramObject1);
  }
  
  public void bufferProperty(SettableBeanProperty paramSettableBeanProperty, Object paramObject)
  {
    this._buffered = new PropertyValue.Regular(this._buffered, paramObject, paramSettableBeanProperty);
  }
  
  protected PropertyValue buffered()
  {
    return this._buffered;
  }
  
  protected Object[] getParameters(SettableBeanProperty[] paramArrayOfSettableBeanProperty)
    throws JsonMappingException
  {
    if (this._paramsNeeded > 0)
    {
      if (this._paramsSeenBig == null)
      {
        int m = this._paramsSeen;
        int n = 0;
        int i1 = this._creatorParameters.length;
        while (n < i1)
        {
          if ((m & 0x1) == 0) {
            this._creatorParameters[n] = _findMissing(paramArrayOfSettableBeanProperty[n]);
          }
          n++;
          m >>= 1;
        }
      }
      int i = this._creatorParameters.length;
      int k;
      for (int j = 0;; j = k + 1)
      {
        k = this._paramsSeenBig.nextClearBit(j);
        if (k >= i) {
          break;
        }
        this._creatorParameters[k] = _findMissing(paramArrayOfSettableBeanProperty[k]);
      }
    }
    return this._creatorParameters;
  }
  
  public Object handleIdValue(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (this._objectIdReader != null)
    {
      if (this._idValue == null) {
        break label67;
      }
      paramDeserializationContext.findObjectId(this._idValue, this._objectIdReader.generator, this._objectIdReader.resolver).bindItem(paramObject);
      SettableBeanProperty localSettableBeanProperty = this._objectIdReader.idProperty;
      if (localSettableBeanProperty != null) {
        paramObject = localSettableBeanProperty.setAndReturn(paramObject, this._idValue);
      }
    }
    return paramObject;
    label67:
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramObject.getClass().getName();
    throw paramDeserializationContext.mappingException("No _idValue when handleIdValue called, on instance of %s", arrayOfObject);
  }
  
  public boolean isComplete()
  {
    if (this._paramsNeeded <= 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean readIdProperty(String paramString)
    throws IOException
  {
    if ((this._objectIdReader != null) && (paramString.equals(this._objectIdReader.propertyName.getSimpleName()))) {
      this._idValue = this._objectIdReader.readObjectReference(this._parser, this._context);
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/PropertyValueBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */