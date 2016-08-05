package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DateTimeSerializerBase<T>
  extends StdScalarSerializer<T>
  implements ContextualSerializer
{
  protected final DateFormat _customFormat;
  protected final Boolean _useTimestamp;
  
  protected DateTimeSerializerBase(Class<T> paramClass, Boolean paramBoolean, DateFormat paramDateFormat)
  {
    super(paramClass);
    this._useTimestamp = paramBoolean;
    this._customFormat = paramDateFormat;
  }
  
  protected void _acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType, boolean paramBoolean)
    throws JsonMappingException
  {
    if (paramBoolean)
    {
      JsonIntegerFormatVisitor localJsonIntegerFormatVisitor = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (localJsonIntegerFormatVisitor != null)
      {
        localJsonIntegerFormatVisitor.numberType(JsonParser.NumberType.LONG);
        localJsonIntegerFormatVisitor.format(JsonValueFormat.UTC_MILLISEC);
      }
    }
    for (;;)
    {
      return;
      JsonStringFormatVisitor localJsonStringFormatVisitor = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
      if (localJsonStringFormatVisitor != null) {
        localJsonStringFormatVisitor.format(JsonValueFormat.DATE_TIME);
      }
    }
  }
  
  protected boolean _asTimestamp(SerializerProvider paramSerializerProvider)
  {
    boolean bool;
    if (this._useTimestamp != null) {
      bool = this._useTimestamp.booleanValue();
    }
    for (;;)
    {
      return bool;
      if (this._customFormat == null)
      {
        if (paramSerializerProvider != null) {
          bool = paramSerializerProvider.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        } else {
          throw new IllegalArgumentException("Null SerializerProvider passed for " + handledType().getName());
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  protected abstract long _timestamp(T paramT);
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    _acceptJsonFormatVisitor(paramJsonFormatVisitorWrapper, paramJavaType, _asTimestamp(paramJsonFormatVisitorWrapper.getProvider()));
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    Boolean localBoolean = null;
    JsonFormat.Value localValue;
    if (paramBeanProperty != null)
    {
      localValue = paramSerializerProvider.getAnnotationIntrospector().findFormat(paramBeanProperty.getMember());
      if (localValue != null)
      {
        if (!localValue.getShape().isNumeric()) {
          break label48;
        }
        this = withFormat(Boolean.TRUE, null);
      }
    }
    label48:
    TimeZone localTimeZone;
    do
    {
      return this;
      if (localValue.getShape() == JsonFormat.Shape.STRING) {
        localBoolean = Boolean.FALSE;
      }
      localTimeZone = localValue.getTimeZone();
      if (localValue.hasPattern())
      {
        String str = localValue.getPattern();
        if (localValue.hasLocale()) {}
        for (Locale localLocale2 = localValue.getLocale();; localLocale2 = paramSerializerProvider.getLocale())
        {
          SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(str, localLocale2);
          if (localTimeZone == null) {
            localTimeZone = paramSerializerProvider.getTimeZone();
          }
          localSimpleDateFormat.setTimeZone(localTimeZone);
          this = withFormat(localBoolean, localSimpleDateFormat);
          break;
        }
      }
    } while (localTimeZone == null);
    DateFormat localDateFormat1 = paramSerializerProvider.getConfig().getDateFormat();
    Locale localLocale1;
    label190:
    DateFormat localDateFormat2;
    if (localDateFormat1.getClass() == StdDateFormat.class) {
      if (localValue.hasLocale())
      {
        localLocale1 = localValue.getLocale();
        localDateFormat2 = StdDateFormat.getISO8601Format(localTimeZone, localLocale1);
      }
    }
    for (;;)
    {
      this = withFormat(localBoolean, localDateFormat2);
      break;
      localLocale1 = paramSerializerProvider.getLocale();
      break label190;
      localDateFormat2 = (DateFormat)localDateFormat1.clone();
      localDateFormat2.setTimeZone(localTimeZone);
    }
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (_asTimestamp(paramSerializerProvider)) {}
    for (String str = "number";; str = "string") {
      return createSchemaNode(str, true);
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, T paramT)
  {
    if ((paramT == null) || (_timestamp(paramT) == 0L)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Deprecated
  public boolean isEmpty(T paramT)
  {
    if ((paramT == null) || (_timestamp(paramT) == 0L)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public abstract void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException;
  
  public abstract DateTimeSerializerBase<T> withFormat(Boolean paramBoolean, DateFormat paramDateFormat);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/DateTimeSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */