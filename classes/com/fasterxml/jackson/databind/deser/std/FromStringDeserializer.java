package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public abstract class FromStringDeserializer<T>
  extends StdScalarDeserializer<T>
{
  protected FromStringDeserializer(Class<?> paramClass)
  {
    super(paramClass);
  }
  
  public static Std findDeserializer(Class<?> paramClass)
  {
    int i;
    if (paramClass == File.class) {
      i = 1;
    }
    for (Std localStd = new Std(paramClass, i);; localStd = null)
    {
      return localStd;
      if (paramClass == URL.class)
      {
        i = 2;
        break;
      }
      if (paramClass == URI.class)
      {
        i = 3;
        break;
      }
      if (paramClass == Class.class)
      {
        i = 4;
        break;
      }
      if (paramClass == JavaType.class)
      {
        i = 5;
        break;
      }
      if (paramClass == Currency.class)
      {
        i = 6;
        break;
      }
      if (paramClass == Pattern.class)
      {
        i = 7;
        break;
      }
      if (paramClass == Locale.class)
      {
        i = 8;
        break;
      }
      if (paramClass == Charset.class)
      {
        i = 9;
        break;
      }
      if (paramClass == TimeZone.class)
      {
        i = 10;
        break;
      }
      if (paramClass == InetAddress.class)
      {
        i = 11;
        break;
      }
      if (paramClass == InetSocketAddress.class)
      {
        i = 12;
        break;
      }
    }
  }
  
  public static Class<?>[] types()
  {
    Class[] arrayOfClass = new Class[12];
    arrayOfClass[0] = File.class;
    arrayOfClass[1] = URL.class;
    arrayOfClass[2] = URI.class;
    arrayOfClass[3] = Class.class;
    arrayOfClass[4] = JavaType.class;
    arrayOfClass[5] = Currency.class;
    arrayOfClass[6] = Pattern.class;
    arrayOfClass[7] = Locale.class;
    arrayOfClass[8] = Charset.class;
    arrayOfClass[9] = TimeZone.class;
    arrayOfClass[10] = InetAddress.class;
    arrayOfClass[11] = InetSocketAddress.class;
    return arrayOfClass;
  }
  
  protected abstract T _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException;
  
  protected T _deserializeEmbedded(Object paramObject, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramObject.getClass().getName();
    arrayOfObject[1] = this._valueClass.getName();
    throw paramDeserializationContext.mappingException("Don't know how to convert embedded Object of type %s into %s", arrayOfObject);
  }
  
  protected T _deserializeFromEmptyString()
    throws IOException
  {
    return null;
  }
  
  public T deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject2;
    String str2;
    if ((paramJsonParser.getCurrentToken() == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
    {
      paramJsonParser.nextToken();
      localObject2 = deserialize(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
      }
    }
    else
    {
      String str1 = paramJsonParser.getValueAsString();
      if (str1 == null) {
        break label229;
      }
      if (str1.length() != 0)
      {
        str2 = str1.trim();
        if (str2.length() != 0) {
          break label121;
        }
      }
      localObject2 = _deserializeFromEmptyString();
    }
    for (;;)
    {
      return (T)localObject2;
      label121:
      Object localObject3 = null;
      try
      {
        Object localObject4 = _deserialize(str2, paramDeserializationContext);
        if (localObject4 != null) {
          localObject2 = localObject4;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localObject3 = localIllegalArgumentException;
        String str3 = "not a valid textual representation";
        if (localObject3 != null)
        {
          String str4 = ((Exception)localObject3).getMessage();
          if (str4 != null) {
            str3 = str3 + ", problem: " + str4;
          }
        }
        JsonMappingException localJsonMappingException = paramDeserializationContext.weirdStringException(str2, this._valueClass, str3);
        if (localObject3 != null) {
          localJsonMappingException.initCause((Throwable)localObject3);
        }
        throw localJsonMappingException;
      }
      label229:
      if (paramJsonParser.getCurrentToken() != JsonToken.VALUE_EMBEDDED_OBJECT) {
        break;
      }
      Object localObject1 = paramJsonParser.getEmbeddedObject();
      if (localObject1 == null) {
        localObject2 = null;
      } else if (this._valueClass.isAssignableFrom(localObject1.getClass())) {
        localObject2 = localObject1;
      } else {
        localObject2 = _deserializeEmbedded(localObject1, paramDeserializationContext);
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass);
  }
  
  public static class Std
    extends FromStringDeserializer<Object>
  {
    public static final int STD_CHARSET = 9;
    public static final int STD_CLASS = 4;
    public static final int STD_CURRENCY = 6;
    public static final int STD_FILE = 1;
    public static final int STD_INET_ADDRESS = 11;
    public static final int STD_INET_SOCKET_ADDRESS = 12;
    public static final int STD_JAVA_TYPE = 5;
    public static final int STD_LOCALE = 8;
    public static final int STD_PATTERN = 7;
    public static final int STD_TIME_ZONE = 10;
    public static final int STD_URI = 3;
    public static final int STD_URL = 2;
    private static final long serialVersionUID = 1L;
    protected final int _kind;
    
    protected Std(Class<?> paramClass, int paramInt)
    {
      super();
      this._kind = paramInt;
    }
    
    protected Object _deserialize(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      Object localObject;
      switch (this._kind)
      {
      default: 
        throw new IllegalArgumentException();
      case 1: 
        localObject = new File(paramString);
      }
      for (;;)
      {
        return localObject;
        localObject = new URL(paramString);
        continue;
        localObject = URI.create(paramString);
        continue;
        try
        {
          Class localClass = paramDeserializationContext.findClass(paramString);
          localObject = localClass;
        }
        catch (Exception localException)
        {
          throw paramDeserializationContext.instantiationException(this._valueClass, ClassUtil.getRootCause(localException));
        }
        localObject = paramDeserializationContext.getTypeFactory().constructFromCanonical(paramString);
        continue;
        localObject = Currency.getInstance(paramString);
        continue;
        localObject = Pattern.compile(paramString);
        continue;
        int i1 = paramString.indexOf('_');
        if (i1 < 0)
        {
          localObject = new Locale(paramString);
        }
        else
        {
          String str1 = paramString.substring(0, i1);
          String str2 = paramString.substring(i1 + 1);
          int i2 = str2.indexOf('_');
          if (i2 < 0)
          {
            localObject = new Locale(str1, str2);
          }
          else
          {
            localObject = new Locale(str1, str2.substring(0, i2), str2.substring(i2 + 1));
            continue;
            localObject = Charset.forName(paramString);
            continue;
            localObject = TimeZone.getTimeZone(paramString);
            continue;
            localObject = InetAddress.getByName(paramString);
            continue;
            if (paramString.startsWith("["))
            {
              int k = paramString.lastIndexOf(']');
              if (k == -1) {
                throw new InvalidFormatException("Bracketed IPv6 address must contain closing bracket", paramString, InetSocketAddress.class);
              }
              int m = paramString.indexOf(':', k);
              if (m > -1) {}
              for (int n = Integer.parseInt(paramString.substring(m + 1));; n = 0)
              {
                localObject = new InetSocketAddress(paramString.substring(0, k + 1), n);
                break;
              }
            }
            int i = paramString.indexOf(':');
            if ((i >= 0) && (paramString.indexOf(':', i + 1) < 0))
            {
              int j = Integer.parseInt(paramString.substring(i + 1));
              localObject = new InetSocketAddress(paramString.substring(0, i), j);
            }
            else
            {
              localObject = new InetSocketAddress(paramString, 0);
            }
          }
        }
      }
    }
    
    protected Object _deserializeFromEmptyString()
      throws IOException
    {
      if (this._kind == 3) {}
      for (Object localObject = URI.create("");; localObject = super._deserializeFromEmptyString()) {
        return localObject;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/FromStringDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */