package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

@JacksonStdImpl
public class JsonValueSerializer
  extends StdSerializer<Object>
  implements ContextualSerializer, JsonFormatVisitable, SchemaAware
{
  protected final Method _accessorMethod;
  protected final boolean _forceTypeInformation;
  protected final BeanProperty _property;
  protected final JsonSerializer<Object> _valueSerializer;
  
  public JsonValueSerializer(JsonValueSerializer paramJsonValueSerializer, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer, boolean paramBoolean)
  {
    super(_notNullClass(paramJsonValueSerializer.handledType()));
    this._accessorMethod = paramJsonValueSerializer._accessorMethod;
    this._valueSerializer = paramJsonSerializer;
    this._property = paramBeanProperty;
    this._forceTypeInformation = paramBoolean;
  }
  
  public JsonValueSerializer(Method paramMethod, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramMethod.getReturnType(), false);
    this._accessorMethod = paramMethod;
    this._valueSerializer = paramJsonSerializer;
    this._property = null;
    this._forceTypeInformation = true;
  }
  
  private static final Class<Object> _notNullClass(Class<?> paramClass)
  {
    if (paramClass == null) {
      paramClass = Object.class;
    }
    return paramClass;
  }
  
  protected boolean _acceptJsonFormatVisitorForEnum(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType, Class<?> paramClass)
    throws JsonMappingException
  {
    JsonStringFormatVisitor localJsonStringFormatVisitor = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
    if (localJsonStringFormatVisitor != null)
    {
      LinkedHashSet localLinkedHashSet = new LinkedHashSet();
      Object[] arrayOfObject = paramClass.getEnumConstants();
      int i = arrayOfObject.length;
      int j = 0;
      while (j < i)
      {
        Object localObject1 = arrayOfObject[j];
        try
        {
          localLinkedHashSet.add(String.valueOf(this._accessorMethod.invoke(localObject1, new Object[0])));
          j++;
        }
        catch (Exception localException)
        {
          for (Object localObject2 = localException; ((localObject2 instanceof InvocationTargetException)) && (((Throwable)localObject2).getCause() != null); localObject2 = ((Throwable)localObject2).getCause()) {}
          if ((localObject2 instanceof Error)) {
            throw ((Error)localObject2);
          }
          throw JsonMappingException.wrapWithPath((Throwable)localObject2, localObject1, this._accessorMethod.getName() + "()");
        }
      }
      localJsonStringFormatVisitor.enumTypes(localLinkedHashSet);
    }
    return true;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    Class localClass;
    if (paramJavaType == null)
    {
      localClass = null;
      if (localClass == null) {
        localClass = this._accessorMethod.getDeclaringClass();
      }
      if ((localClass == null) || (!localClass.isEnum()) || (!_acceptJsonFormatVisitorForEnum(paramJsonFormatVisitorWrapper, paramJavaType, localClass))) {
        break label48;
      }
    }
    for (;;)
    {
      return;
      localClass = paramJavaType.getRawClass();
      break;
      label48:
      JsonSerializer localJsonSerializer = this._valueSerializer;
      if (localJsonSerializer == null)
      {
        if (paramJavaType == null)
        {
          if (this._property != null) {
            paramJavaType = this._property.getType();
          }
          if (paramJavaType == null) {
            paramJavaType = paramJsonFormatVisitorWrapper.getProvider().constructType(this._handledType);
          }
        }
        localJsonSerializer = paramJsonFormatVisitorWrapper.getProvider().findTypedValueSerializer(paramJavaType, false, this._property);
        if (localJsonSerializer == null)
        {
          paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
          continue;
        }
      }
      localJsonSerializer.acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, null);
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = this._valueSerializer;
    JavaType localJavaType;
    JsonSerializer localJsonSerializer2;
    if (localJsonSerializer1 == null) {
      if ((paramSerializerProvider.isEnabled(MapperFeature.USE_STATIC_TYPING)) || (Modifier.isFinal(this._accessorMethod.getReturnType().getModifiers())))
      {
        localJavaType = paramSerializerProvider.constructType(this._accessorMethod.getGenericReturnType());
        localJsonSerializer2 = paramSerializerProvider.findPrimaryPropertySerializer(localJavaType, paramBeanProperty);
      }
    }
    for (this = withResolved(paramBeanProperty, localJsonSerializer2, isNaturalTypeWithStdHandling(localJavaType.getRawClass(), localJsonSerializer2));; this = withResolved(paramBeanProperty, paramSerializerProvider.handlePrimaryContextualization(localJsonSerializer1, paramBeanProperty), this._forceTypeInformation)) {
      return this;
    }
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    if ((this._valueSerializer instanceof SchemaAware)) {}
    for (JsonNode localJsonNode = ((SchemaAware)this._valueSerializer).getSchema(paramSerializerProvider, null);; localJsonNode = JsonSchema.getDefaultSchemaNode()) {
      return localJsonNode;
    }
  }
  
  protected boolean isNaturalTypeWithStdHandling(Class<?> paramClass, JsonSerializer<?> paramJsonSerializer)
  {
    boolean bool = false;
    if (paramClass.isPrimitive()) {
      if ((paramClass == Integer.TYPE) || (paramClass == Boolean.TYPE) || (paramClass == Double.TYPE)) {
        break label56;
      }
    }
    for (;;)
    {
      return bool;
      if ((paramClass == String.class) || (paramClass == Integer.class) || (paramClass == Boolean.class) || (paramClass == Double.class)) {
        label56:
        bool = isDefaultSerializer(paramJsonSerializer);
      }
    }
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    try
    {
      Object localObject2 = this._accessorMethod.invoke(paramObject, new Object[0]);
      if (localObject2 == null)
      {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      else
      {
        JsonSerializer localJsonSerializer = this._valueSerializer;
        if (localJsonSerializer == null) {
          localJsonSerializer = paramSerializerProvider.findTypedValueSerializer(localObject2.getClass(), true, this._property);
        }
        localJsonSerializer.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
      }
    }
    catch (IOException localIOException)
    {
      throw localIOException;
    }
    catch (Exception localException)
    {
      for (Object localObject1 = localException; ((localObject1 instanceof InvocationTargetException)) && (((Throwable)localObject1).getCause() != null); localObject1 = ((Throwable)localObject1).getCause()) {}
      if ((localObject1 instanceof Error)) {
        throw ((Error)localObject1);
      }
      throw JsonMappingException.wrapWithPath((Throwable)localObject1, paramObject, this._accessorMethod.getName() + "()");
    }
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    try
    {
      localObject2 = this._accessorMethod.invoke(paramObject, new Object[0]);
      if (localObject2 == null)
      {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      else
      {
        localJsonSerializer = this._valueSerializer;
        if (localJsonSerializer == null)
        {
          localJsonSerializer = paramSerializerProvider.findValueSerializer(localObject2.getClass(), this._property);
          localJsonSerializer.serializeWithType(localObject2, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
        }
      }
    }
    catch (IOException localIOException)
    {
      Object localObject2;
      JsonSerializer localJsonSerializer;
      do
      {
        throw localIOException;
      } while (!this._forceTypeInformation);
      paramTypeSerializer.writeTypePrefixForScalar(paramObject, paramJsonGenerator);
      localJsonSerializer.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
      paramTypeSerializer.writeTypeSuffixForScalar(paramObject, paramJsonGenerator);
    }
    catch (Exception localException)
    {
      for (Object localObject1 = localException; ((localObject1 instanceof InvocationTargetException)) && (((Throwable)localObject1).getCause() != null); localObject1 = ((Throwable)localObject1).getCause()) {}
      if ((localObject1 instanceof Error)) {
        throw ((Error)localObject1);
      }
      throw JsonMappingException.wrapWithPath((Throwable)localObject1, paramObject, this._accessorMethod.getName() + "()");
    }
  }
  
  public String toString()
  {
    return "(@JsonValue serializer for method " + this._accessorMethod.getDeclaringClass() + "#" + this._accessorMethod.getName() + ")";
  }
  
  public JsonValueSerializer withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer, boolean paramBoolean)
  {
    if ((this._property == paramBeanProperty) && (this._valueSerializer == paramJsonSerializer) && (paramBoolean == this._forceTypeInformation)) {}
    for (;;)
    {
      return this;
      this = new JsonValueSerializer(this, paramBeanProperty, paramJsonSerializer, paramBoolean);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/JsonValueSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */