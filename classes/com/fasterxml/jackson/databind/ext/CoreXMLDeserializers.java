package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLDeserializers
  extends Deserializers.Base
{
  protected static final int TYPE_DURATION = 1;
  protected static final int TYPE_G_CALENDAR = 2;
  protected static final int TYPE_QNAME = 3;
  static final DatatypeFactory _dataTypeFactory;
  
  static
  {
    try
    {
      _dataTypeFactory = DatatypeFactory.newInstance();
      return;
    }
    catch (DatatypeConfigurationException localDatatypeConfigurationException)
    {
      throw new RuntimeException(localDatatypeConfigurationException);
    }
  }
  
  public JsonDeserializer<?> findBeanDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
  {
    Class localClass = paramJavaType.getRawClass();
    Std localStd;
    if (localClass == QName.class) {
      localStd = new Std(localClass, 3);
    }
    for (;;)
    {
      return localStd;
      if (localClass == XMLGregorianCalendar.class) {
        localStd = new Std(localClass, 2);
      } else if (localClass == Duration.class) {
        localStd = new Std(localClass, 1);
      } else {
        localStd = null;
      }
    }
  }
  
  public static class Std
    extends FromStringDeserializer<Object>
  {
    private static final long serialVersionUID = 1L;
    protected final int _kind;
    
    public Std(Class<?> paramClass, int paramInt)
    {
      super();
      this._kind = paramInt;
    }
    
    protected Object _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IllegalArgumentException
    {
      switch (this._kind)
      {
      case 2: 
      default: 
        throw new IllegalStateException();
      }
      for (Object localObject = CoreXMLDeserializers._dataTypeFactory.newDuration(paramString);; localObject = QName.valueOf(paramString)) {
        return localObject;
      }
    }
    
    public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      Date localDate;
      Object localObject;
      if (this._kind == 2)
      {
        localDate = _parseDate(paramJsonParser, paramDeserializationContext);
        if (localDate == null) {
          localObject = null;
        }
      }
      for (;;)
      {
        return localObject;
        GregorianCalendar localGregorianCalendar = new GregorianCalendar();
        localGregorianCalendar.setTime(localDate);
        TimeZone localTimeZone = paramDeserializationContext.getTimeZone();
        if (localTimeZone != null) {
          localGregorianCalendar.setTimeZone(localTimeZone);
        }
        localObject = CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(localGregorianCalendar);
        continue;
        localObject = super.deserialize(paramJsonParser, paramDeserializationContext);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ext/CoreXMLDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */