package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExternalTypeHandler
{
  private final HashMap<String, Integer> _nameToPropertyIndex;
  private final ExtTypedProperty[] _properties;
  private final TokenBuffer[] _tokens;
  private final String[] _typeIds;
  
  protected ExternalTypeHandler(ExternalTypeHandler paramExternalTypeHandler)
  {
    this._properties = paramExternalTypeHandler._properties;
    this._nameToPropertyIndex = paramExternalTypeHandler._nameToPropertyIndex;
    int i = this._properties.length;
    this._typeIds = new String[i];
    this._tokens = new TokenBuffer[i];
  }
  
  protected ExternalTypeHandler(ExtTypedProperty[] paramArrayOfExtTypedProperty, HashMap<String, Integer> paramHashMap, String[] paramArrayOfString, TokenBuffer[] paramArrayOfTokenBuffer)
  {
    this._properties = paramArrayOfExtTypedProperty;
    this._nameToPropertyIndex = paramHashMap;
    this._typeIds = paramArrayOfString;
    this._tokens = paramArrayOfTokenBuffer;
  }
  
  protected final Object _deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, int paramInt, String paramString)
    throws IOException
  {
    JsonParser localJsonParser1 = this._tokens[paramInt].asParser(paramJsonParser);
    if (localJsonParser1.nextToken() == JsonToken.VALUE_NULL) {}
    JsonParser localJsonParser2;
    for (Object localObject = null;; localObject = this._properties[paramInt].getProperty().deserialize(localJsonParser2, paramDeserializationContext))
    {
      return localObject;
      TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
      localTokenBuffer.writeStartArray();
      localTokenBuffer.writeString(paramString);
      localTokenBuffer.copyCurrentStructure(localJsonParser1);
      localTokenBuffer.writeEndArray();
      localJsonParser2 = localTokenBuffer.asParser(paramJsonParser);
      localJsonParser2.nextToken();
    }
  }
  
  protected final void _deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, int paramInt, String paramString)
    throws IOException
  {
    JsonParser localJsonParser1 = this._tokens[paramInt].asParser(paramJsonParser);
    if (localJsonParser1.nextToken() == JsonToken.VALUE_NULL) {
      this._properties[paramInt].getProperty().set(paramObject, null);
    }
    for (;;)
    {
      return;
      TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
      localTokenBuffer.writeStartArray();
      localTokenBuffer.writeString(paramString);
      localTokenBuffer.copyCurrentStructure(localJsonParser1);
      localTokenBuffer.writeEndArray();
      JsonParser localJsonParser2 = localTokenBuffer.asParser(paramJsonParser);
      localJsonParser2.nextToken();
      this._properties[paramInt].getProperty().deserializeAndSet(localJsonParser2, paramDeserializationContext, paramObject);
    }
  }
  
  public Object complete(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, PropertyValueBuffer paramPropertyValueBuffer, PropertyBasedCreator paramPropertyBasedCreator)
    throws IOException
  {
    int i = this._properties.length;
    Object[] arrayOfObject1 = new Object[i];
    int j = 0;
    while (j < i)
    {
      String str = this._typeIds[j];
      if (str == null)
      {
        if (this._tokens[j] == null)
        {
          j++;
        }
        else
        {
          if (!this._properties[j].hasDefaultType())
          {
            arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = this._properties[j].getTypePropertyName();
            throw paramDeserializationContext.mappingException("Missing external type id property '%s'", arrayOfObject3);
          }
          str = this._properties[j].getDefaultTypeId();
        }
      }
      else
      {
        while (this._tokens[j] != null) {
          for (;;)
          {
            Object[] arrayOfObject3;
            arrayOfObject1[j] = _deserialize(paramJsonParser, paramDeserializationContext, j, str);
          }
        }
        SettableBeanProperty localSettableBeanProperty3 = this._properties[j].getProperty();
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = localSettableBeanProperty3.getName();
        arrayOfObject2[1] = this._properties[j].getTypePropertyName();
        throw paramDeserializationContext.mappingException("Missing property '%s' for external type id '%s'", arrayOfObject2);
      }
    }
    for (int k = 0; k < i; k++)
    {
      SettableBeanProperty localSettableBeanProperty2 = this._properties[k].getProperty();
      if (paramPropertyBasedCreator.findCreatorProperty(localSettableBeanProperty2.getName()) != null) {
        paramPropertyValueBuffer.assignParameter(localSettableBeanProperty2, arrayOfObject1[k]);
      }
    }
    Object localObject = paramPropertyBasedCreator.build(paramDeserializationContext, paramPropertyValueBuffer);
    for (int m = 0; m < i; m++)
    {
      SettableBeanProperty localSettableBeanProperty1 = this._properties[m].getProperty();
      if (paramPropertyBasedCreator.findCreatorProperty(localSettableBeanProperty1.getName()) == null) {
        localSettableBeanProperty1.set(localObject, arrayOfObject1[m]);
      }
    }
    return localObject;
  }
  
  public Object complete(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    int i = 0;
    int j = this._properties.length;
    if (i < j)
    {
      String str = this._typeIds[i];
      if (str == null)
      {
        localTokenBuffer = this._tokens[i];
        if (localTokenBuffer == null) {}
        for (;;)
        {
          i++;
          break;
          localJsonToken = localTokenBuffer.firstToken();
          if ((localJsonToken == null) || (!localJsonToken.isScalarValue())) {
            break label180;
          }
          localJsonParser = localTokenBuffer.asParser(paramJsonParser);
          localJsonParser.nextToken();
          localSettableBeanProperty2 = this._properties[i].getProperty();
          localObject = TypeDeserializer.deserializeIfNatural(localJsonParser, paramDeserializationContext, localSettableBeanProperty2.getType());
          if (localObject == null) {
            break label126;
          }
          localSettableBeanProperty2.set(paramObject, localObject);
        }
        label126:
        if (!this._properties[i].hasDefaultType())
        {
          arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = this._properties[i].getTypePropertyName();
          throw paramDeserializationContext.mappingException("Missing external type id property '%s'", arrayOfObject2);
        }
        str = this._properties[i].getDefaultTypeId();
      }
      label180:
      while (this._tokens[i] != null) {
        for (;;)
        {
          TokenBuffer localTokenBuffer;
          JsonToken localJsonToken;
          JsonParser localJsonParser;
          SettableBeanProperty localSettableBeanProperty2;
          Object localObject;
          Object[] arrayOfObject2;
          _deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, i, str);
        }
      }
      SettableBeanProperty localSettableBeanProperty1 = this._properties[i].getProperty();
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = localSettableBeanProperty1.getName();
      arrayOfObject1[1] = this._properties[i].getTypePropertyName();
      throw paramDeserializationContext.mappingException("Missing property '%s' for external type id '%s'", arrayOfObject1);
    }
    return paramObject;
  }
  
  public boolean handlePropertyValue(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, String paramString, Object paramObject)
    throws IOException
  {
    Integer localInteger = (Integer)this._nameToPropertyIndex.get(paramString);
    boolean bool;
    if (localInteger == null)
    {
      bool = false;
      return bool;
    }
    int i = localInteger.intValue();
    if (this._properties[i].hasTypePropertyName(paramString))
    {
      this._typeIds[i] = paramJsonParser.getText();
      paramJsonParser.skipChildren();
      if ((paramObject != null) && (this._tokens[i] != null)) {}
      for (j = 1;; j = 0)
      {
        if (j != 0)
        {
          String str = this._typeIds[i];
          this._typeIds[i] = null;
          _deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, i, str);
          this._tokens[i] = null;
        }
        bool = true;
        break;
      }
    }
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
    localTokenBuffer.copyCurrentStructure(paramJsonParser);
    this._tokens[i] = localTokenBuffer;
    if ((paramObject != null) && (this._typeIds[i] != null)) {}
    for (int j = 1;; j = 0) {
      break;
    }
  }
  
  public boolean handleTypePropertyValue(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, String paramString, Object paramObject)
    throws IOException
  {
    boolean bool = false;
    Integer localInteger = (Integer)this._nameToPropertyIndex.get(paramString);
    if (localInteger == null) {}
    int i;
    do
    {
      return bool;
      i = localInteger.intValue();
    } while (!this._properties[i].hasTypePropertyName(paramString));
    String str = paramJsonParser.getText();
    int j;
    if ((paramObject != null) && (this._tokens[i] != null))
    {
      j = 1;
      label69:
      if (j == 0) {
        break label106;
      }
      _deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, i, str);
      this._tokens[i] = null;
    }
    for (;;)
    {
      bool = true;
      break;
      j = 0;
      break label69;
      label106:
      this._typeIds[i] = str;
    }
  }
  
  public ExternalTypeHandler start()
  {
    return new ExternalTypeHandler(this);
  }
  
  private static final class ExtTypedProperty
  {
    private final SettableBeanProperty _property;
    private final TypeDeserializer _typeDeserializer;
    private final String _typePropertyName;
    
    public ExtTypedProperty(SettableBeanProperty paramSettableBeanProperty, TypeDeserializer paramTypeDeserializer)
    {
      this._property = paramSettableBeanProperty;
      this._typeDeserializer = paramTypeDeserializer;
      this._typePropertyName = paramTypeDeserializer.getPropertyName();
    }
    
    public String getDefaultTypeId()
    {
      String str = null;
      Class localClass = this._typeDeserializer.getDefaultImpl();
      if (localClass == null) {}
      for (;;)
      {
        return str;
        str = this._typeDeserializer.getTypeIdResolver().idFromValueAndType(null, localClass);
      }
    }
    
    public SettableBeanProperty getProperty()
    {
      return this._property;
    }
    
    public String getTypePropertyName()
    {
      return this._typePropertyName;
    }
    
    public boolean hasDefaultType()
    {
      if (this._typeDeserializer.getDefaultImpl() != null) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean hasTypePropertyName(String paramString)
    {
      return paramString.equals(this._typePropertyName);
    }
  }
  
  public static class Builder
  {
    private final HashMap<String, Integer> _nameToPropertyIndex = new HashMap();
    private final ArrayList<ExternalTypeHandler.ExtTypedProperty> _properties = new ArrayList();
    
    public void addExternal(SettableBeanProperty paramSettableBeanProperty, TypeDeserializer paramTypeDeserializer)
    {
      Integer localInteger = Integer.valueOf(this._properties.size());
      this._properties.add(new ExternalTypeHandler.ExtTypedProperty(paramSettableBeanProperty, paramTypeDeserializer));
      this._nameToPropertyIndex.put(paramSettableBeanProperty.getName(), localInteger);
      this._nameToPropertyIndex.put(paramTypeDeserializer.getPropertyName(), localInteger);
    }
    
    public ExternalTypeHandler build()
    {
      return new ExternalTypeHandler((ExternalTypeHandler.ExtTypedProperty[])this._properties.toArray(new ExternalTypeHandler.ExtTypedProperty[this._properties.size()]), this._nameToPropertyIndex, null, null);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/ExternalTypeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */