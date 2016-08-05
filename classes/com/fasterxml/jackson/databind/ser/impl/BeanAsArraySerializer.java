package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public class BeanAsArraySerializer
  extends BeanSerializerBase
{
  private static final long serialVersionUID = 1L;
  protected final BeanSerializerBase _defaultSerializer;
  
  public BeanAsArraySerializer(BeanSerializerBase paramBeanSerializerBase)
  {
    super(paramBeanSerializerBase, (ObjectIdWriter)null);
    this._defaultSerializer = paramBeanSerializerBase;
  }
  
  protected BeanAsArraySerializer(BeanSerializerBase paramBeanSerializerBase, ObjectIdWriter paramObjectIdWriter, Object paramObject)
  {
    super(paramBeanSerializerBase, paramObjectIdWriter, paramObject);
    this._defaultSerializer = paramBeanSerializerBase;
  }
  
  protected BeanAsArraySerializer(BeanSerializerBase paramBeanSerializerBase, String[] paramArrayOfString)
  {
    super(paramBeanSerializerBase, paramArrayOfString);
    this._defaultSerializer = paramBeanSerializerBase;
  }
  
  private boolean hasSingleElement(SerializerProvider paramSerializerProvider)
  {
    int i = 1;
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    if ((this._filteredProps != null) && (paramSerializerProvider.getActiveView() != null))
    {
      arrayOfBeanPropertyWriter = this._filteredProps;
      if (arrayOfBeanPropertyWriter.length != i) {
        break label37;
      }
    }
    for (;;)
    {
      return i;
      arrayOfBeanPropertyWriter = this._props;
      break;
      label37:
      int j = 0;
    }
  }
  
  protected BeanSerializerBase asArraySerializer()
  {
    return this;
  }
  
  public boolean isUnwrappingSerializer()
  {
    return false;
  }
  
  public final void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && (hasSingleElement(paramSerializerProvider))) {
      serializeAsArray(paramObject, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray();
      paramJsonGenerator.setCurrentValue(paramObject);
      serializeAsArray(paramObject, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  protected final void serializeAsArray(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    if ((this._filteredProps != null) && (paramSerializerProvider.getActiveView() != null)) {
      arrayOfBeanPropertyWriter = this._filteredProps;
    }
    int i;
    label28:
    JsonMappingException localJsonMappingException;
    for (;;)
    {
      i = 0;
      try
      {
        int j = arrayOfBeanPropertyWriter.length;
        if (i < j)
        {
          BeanPropertyWriter localBeanPropertyWriter = arrayOfBeanPropertyWriter[i];
          if (localBeanPropertyWriter == null) {
            paramJsonGenerator.writeNull();
          }
          for (;;)
          {
            i++;
            break label28;
            arrayOfBeanPropertyWriter = this._props;
            break;
            localBeanPropertyWriter.serializeAsElement(paramObject, paramJsonGenerator, paramSerializerProvider);
          }
        }
        String str2;
        str1 = "[anySetter]";
      }
      catch (Exception localException)
      {
        if (i == arrayOfBeanPropertyWriter.length) {}
        for (str2 = "[anySetter]";; str2 = arrayOfBeanPropertyWriter[i].getName())
        {
          wrapAndThrow(paramSerializerProvider, localException, paramObject, str2);
          return;
        }
      }
      catch (StackOverflowError localStackOverflowError)
      {
        localJsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)", localStackOverflowError);
        if (i != arrayOfBeanPropertyWriter.length) {}
      }
    }
    for (;;)
    {
      localJsonMappingException.prependPath(new JsonMappingException.Reference(paramObject, str1));
      throw localJsonMappingException;
      String str1 = arrayOfBeanPropertyWriter[i].getName();
    }
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if (this._objectIdWriter != null) {
      _serializeWithObjectId(paramObject, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
    }
    for (;;)
    {
      return;
      String str;
      if (this._typeId == null)
      {
        str = null;
        label27:
        if (str != null) {
          break label71;
        }
        paramTypeSerializer.writeTypePrefixForArray(paramObject, paramJsonGenerator);
      }
      for (;;)
      {
        serializeAsArray(paramObject, paramJsonGenerator, paramSerializerProvider);
        if (str != null) {
          break label83;
        }
        paramTypeSerializer.writeTypeSuffixForArray(paramObject, paramJsonGenerator);
        break;
        str = _customTypeId(paramObject);
        break label27;
        label71:
        paramTypeSerializer.writeCustomTypePrefixForArray(paramObject, paramJsonGenerator, str);
      }
      label83:
      paramTypeSerializer.writeCustomTypeSuffixForArray(paramObject, paramJsonGenerator, str);
    }
  }
  
  public String toString()
  {
    return "BeanAsArraySerializer for " + handledType().getName();
  }
  
  public JsonSerializer<Object> unwrappingSerializer(NameTransformer paramNameTransformer)
  {
    return this._defaultSerializer.unwrappingSerializer(paramNameTransformer);
  }
  
  public BeanSerializerBase withFilterId(Object paramObject)
  {
    return new BeanAsArraySerializer(this, this._objectIdWriter, paramObject);
  }
  
  protected BeanAsArraySerializer withIgnorals(String[] paramArrayOfString)
  {
    return new BeanAsArraySerializer(this, paramArrayOfString);
  }
  
  public BeanSerializerBase withObjectIdWriter(ObjectIdWriter paramObjectIdWriter)
  {
    return this._defaultSerializer.withObjectIdWriter(paramObjectIdWriter);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/BeanAsArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */