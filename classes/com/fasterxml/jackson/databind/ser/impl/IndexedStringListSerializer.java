package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import java.io.IOException;
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer
  extends StaticListSerializerBase<List<String>>
{
  public static final IndexedStringListSerializer instance = new IndexedStringListSerializer();
  private static final long serialVersionUID = 1L;
  
  protected IndexedStringListSerializer()
  {
    super(List.class);
  }
  
  public IndexedStringListSerializer(IndexedStringListSerializer paramIndexedStringListSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramIndexedStringListSerializer, paramJsonSerializer, paramBoolean);
  }
  
  private final void _serializeUnwrapped(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._serializer == null) {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider, 1);
    }
    for (;;)
    {
      return;
      serializeUsingCustom(paramList, paramJsonGenerator, paramSerializerProvider, 1);
    }
  }
  
  private final void serializeContents(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, int paramInt)
    throws IOException
  {
    for (int i = 0;; i++) {
      if (i < paramInt) {
        try
        {
          String str = (String)paramList.get(i);
          if (str == null) {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          } else {
            paramJsonGenerator.writeString(str);
          }
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramList, i);
        }
      } else {
        return;
      }
    }
  }
  
  private final void serializeUsingCustom(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, int paramInt)
    throws IOException
  {
    for (int i = 0;; i++)
    {
      try
      {
        JsonSerializer localJsonSerializer = this._serializer;
        i = 0;
        if (i < paramInt)
        {
          String str = (String)paramList.get(i);
          if (str == null) {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          } else {
            localJsonSerializer.serialize(str, paramJsonGenerator, paramSerializerProvider);
          }
        }
      }
      catch (Exception localException)
      {
        wrapAndThrow(paramSerializerProvider, localException, paramList, i);
      }
      return;
    }
  }
  
  public JsonSerializer<?> _withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new IndexedStringListSerializer(this, paramJsonSerializer, paramBoolean);
  }
  
  protected void acceptContentVisitor(JsonArrayFormatVisitor paramJsonArrayFormatVisitor)
    throws JsonMappingException
  {
    paramJsonArrayFormatVisitor.itemsFormat(JsonFormatTypes.STRING);
  }
  
  protected JsonNode contentSchema()
  {
    return createSchemaNode("string", true);
  }
  
  public void serialize(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramList.size();
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE)))
    {
      _serializeUnwrapped(paramList, paramJsonGenerator, paramSerializerProvider);
      return;
    }
    paramJsonGenerator.writeStartArray(i);
    if (this._serializer == null) {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider, i);
    }
    for (;;)
    {
      paramJsonGenerator.writeEndArray();
      break;
      serializeUsingCustom(paramList, paramJsonGenerator, paramSerializerProvider, i);
    }
  }
  
  public void serializeWithType(List<String> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    int i = paramList.size();
    paramTypeSerializer.writeTypePrefixForArray(paramList, paramJsonGenerator);
    if (this._serializer == null) {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider, i);
    }
    for (;;)
    {
      paramTypeSerializer.writeTypeSuffixForArray(paramList, paramJsonGenerator);
      return;
      serializeUsingCustom(paramList, paramJsonGenerator, paramSerializerProvider, i);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/IndexedStringListSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */