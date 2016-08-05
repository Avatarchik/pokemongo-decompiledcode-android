package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class StdKeySerializers
{
  protected static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
  protected static final JsonSerializer<Object> DEFAULT_STRING_SERIALIZER = new StringKeySerializer();
  
  @Deprecated
  public static JsonSerializer<Object> getDefault()
  {
    return DEFAULT_KEY_SERIALIZER;
  }
  
  public static JsonSerializer<Object> getFallbackKeySerializer(SerializationConfig paramSerializationConfig, Class<?> paramClass)
  {
    Object localObject;
    if (paramClass != null) {
      if (paramClass == Enum.class) {
        localObject = new Dynamic();
      }
    }
    for (;;)
    {
      return (JsonSerializer<Object>)localObject;
      if (paramClass.isEnum()) {
        localObject = new Default(4, paramClass);
      } else {
        localObject = DEFAULT_KEY_SERIALIZER;
      }
    }
  }
  
  @Deprecated
  public static JsonSerializer<Object> getStdKeySerializer(JavaType paramJavaType)
  {
    return getStdKeySerializer(null, paramJavaType.getRawClass(), true);
  }
  
  public static JsonSerializer<Object> getStdKeySerializer(SerializationConfig paramSerializationConfig, Class<?> paramClass, boolean paramBoolean)
  {
    Object localObject;
    if ((paramClass == null) || (paramClass == Object.class)) {
      localObject = new Dynamic();
    }
    for (;;)
    {
      return (JsonSerializer<Object>)localObject;
      if (paramClass == String.class) {
        localObject = DEFAULT_STRING_SERIALIZER;
      } else if ((paramClass.isPrimitive()) || (Number.class.isAssignableFrom(paramClass))) {
        localObject = DEFAULT_KEY_SERIALIZER;
      } else if (paramClass == Class.class) {
        localObject = new Default(3, paramClass);
      } else if (Date.class.isAssignableFrom(paramClass)) {
        localObject = new Default(1, paramClass);
      } else if (Calendar.class.isAssignableFrom(paramClass)) {
        localObject = new Default(2, paramClass);
      } else if (paramClass == UUID.class) {
        localObject = new Default(5, paramClass);
      } else if (paramBoolean) {
        localObject = DEFAULT_KEY_SERIALIZER;
      } else {
        localObject = null;
      }
    }
  }
  
  @Deprecated
  public static class CalendarKeySerializer
    extends StdSerializer<Calendar>
  {
    protected static final JsonSerializer<?> instance = new CalendarKeySerializer();
    
    public CalendarKeySerializer()
    {
      super();
    }
    
    public void serialize(Calendar paramCalendar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException
    {
      paramSerializerProvider.defaultSerializeDateKey(paramCalendar.getTimeInMillis(), paramJsonGenerator);
    }
  }
  
  @Deprecated
  public static class DateKeySerializer
    extends StdSerializer<Date>
  {
    protected static final JsonSerializer<?> instance = new DateKeySerializer();
    
    public DateKeySerializer()
    {
      super();
    }
    
    public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException
    {
      paramSerializerProvider.defaultSerializeDateKey(paramDate, paramJsonGenerator);
    }
  }
  
  public static class StringKeySerializer
    extends StdSerializer<Object>
  {
    public StringKeySerializer()
    {
      super(false);
    }
    
    public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException
    {
      paramJsonGenerator.writeFieldName((String)paramObject);
    }
  }
  
  public static class Dynamic
    extends StdSerializer<Object>
  {
    protected transient PropertySerializerMap _dynamicSerializers = PropertySerializerMap.emptyForProperties();
    
    public Dynamic()
    {
      super(false);
    }
    
    protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
      throws JsonMappingException
    {
      PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = paramPropertySerializerMap.findAndAddKeySerializer(paramClass, paramSerializerProvider, null);
      if (paramPropertySerializerMap != localSerializerAndMapResult.map) {
        this._dynamicSerializers = localSerializerAndMapResult.map;
      }
      return localSerializerAndMapResult.serializer;
    }
    
    Object readResolve()
    {
      this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
      return this;
    }
    
    public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException
    {
      Class localClass = paramObject.getClass();
      PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
      JsonSerializer localJsonSerializer = localPropertySerializerMap.serializerFor(localClass);
      if (localJsonSerializer == null) {
        localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
      }
      localJsonSerializer.serialize(paramObject, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  public static class Default
    extends StdSerializer<Object>
  {
    static final int TYPE_CALENDAR = 2;
    static final int TYPE_CLASS = 3;
    static final int TYPE_DATE = 1;
    static final int TYPE_ENUM = 4;
    static final int TYPE_TO_STRING = 5;
    protected final int _typeId;
    
    public Default(int paramInt, Class<?> paramClass)
    {
      super(false);
      this._typeId = paramInt;
    }
    
    public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException
    {
      switch (this._typeId)
      {
      default: 
        paramJsonGenerator.writeFieldName(paramObject.toString());
      case 1: 
      case 2: 
      case 3: 
        for (;;)
        {
          return;
          paramSerializerProvider.defaultSerializeDateKey((Date)paramObject, paramJsonGenerator);
          continue;
          paramSerializerProvider.defaultSerializeDateKey(((Calendar)paramObject).getTimeInMillis(), paramJsonGenerator);
          continue;
          paramJsonGenerator.writeFieldName(((Class)paramObject).getName());
        }
      }
      if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {}
      for (String str = paramObject.toString();; str = ((Enum)paramObject).name())
      {
        paramJsonGenerator.writeFieldName(str);
        break;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/StdKeySerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */