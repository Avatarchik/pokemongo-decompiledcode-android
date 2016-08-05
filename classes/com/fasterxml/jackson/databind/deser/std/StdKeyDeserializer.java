package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@JacksonStdImpl
public class StdKeyDeserializer
  extends KeyDeserializer
  implements Serializable
{
  public static final int TYPE_BOOLEAN = 1;
  public static final int TYPE_BYTE = 2;
  public static final int TYPE_CALENDAR = 11;
  public static final int TYPE_CHAR = 4;
  public static final int TYPE_CLASS = 15;
  public static final int TYPE_CURRENCY = 16;
  public static final int TYPE_DATE = 10;
  public static final int TYPE_DOUBLE = 8;
  public static final int TYPE_FLOAT = 7;
  public static final int TYPE_INT = 5;
  public static final int TYPE_LOCALE = 9;
  public static final int TYPE_LONG = 6;
  public static final int TYPE_SHORT = 3;
  public static final int TYPE_URI = 13;
  public static final int TYPE_URL = 14;
  public static final int TYPE_UUID = 12;
  private static final long serialVersionUID = 1L;
  protected final FromStringDeserializer<?> _deser;
  protected final Class<?> _keyClass;
  protected final int _kind;
  
  protected StdKeyDeserializer(int paramInt, Class<?> paramClass)
  {
    this(paramInt, paramClass, null);
  }
  
  protected StdKeyDeserializer(int paramInt, Class<?> paramClass, FromStringDeserializer<?> paramFromStringDeserializer)
  {
    this._kind = paramInt;
    this._keyClass = paramClass;
    this._deser = paramFromStringDeserializer;
  }
  
  public static StdKeyDeserializer forType(Class<?> paramClass)
  {
    Object localObject;
    if ((paramClass == String.class) || (paramClass == Object.class)) {
      localObject = StringKD.forType(paramClass);
    }
    for (;;)
    {
      return (StdKeyDeserializer)localObject;
      int i;
      if (paramClass == UUID.class) {
        i = 12;
      }
      for (;;)
      {
        localObject = new StdKeyDeserializer(i, paramClass);
        break;
        if (paramClass == Integer.class)
        {
          i = 5;
        }
        else if (paramClass == Long.class)
        {
          i = 6;
        }
        else if (paramClass == Date.class)
        {
          i = 10;
        }
        else if (paramClass == Calendar.class)
        {
          i = 11;
        }
        else if (paramClass == Boolean.class)
        {
          i = 1;
        }
        else if (paramClass == Byte.class)
        {
          i = 2;
        }
        else if (paramClass == Character.class)
        {
          i = 4;
        }
        else if (paramClass == Short.class)
        {
          i = 3;
        }
        else if (paramClass == Float.class)
        {
          i = 7;
        }
        else if (paramClass == Double.class)
        {
          i = 8;
        }
        else if (paramClass == URI.class)
        {
          i = 13;
        }
        else if (paramClass == URL.class)
        {
          i = 14;
        }
        else
        {
          if (paramClass != Class.class) {
            break label192;
          }
          i = 15;
        }
      }
      label192:
      if (paramClass == Locale.class) {
        localObject = new StdKeyDeserializer(9, paramClass, FromStringDeserializer.findDeserializer(Locale.class));
      } else if (paramClass == Currency.class) {
        localObject = new StdKeyDeserializer(16, paramClass, FromStringDeserializer.findDeserializer(Currency.class));
      } else {
        localObject = null;
      }
    }
  }
  
  protected Object _parse(String paramString, DeserializationContext paramDeserializationContext)
    throws Exception
  {
    Object localObject1 = null;
    switch (this._kind)
    {
    }
    for (;;)
    {
      return localObject1;
      if ("true".equals(paramString))
      {
        localObject1 = Boolean.TRUE;
        continue;
      }
      if ("false".equals(paramString))
      {
        localObject1 = Boolean.FALSE;
        continue;
      }
      throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "value not 'true' or 'false'");
      int j = _parseInt(paramString);
      if ((j < -128) || (j > 255)) {
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "overflow, value can not be represented as 8-bit value");
      }
      localObject1 = Byte.valueOf((byte)j);
      continue;
      int i = _parseInt(paramString);
      if ((i < 32768) || (i > 32767)) {
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "overflow, value can not be represented as 16-bit value");
      }
      localObject1 = Short.valueOf((short)i);
      continue;
      if (paramString.length() == 1)
      {
        localObject1 = Character.valueOf(paramString.charAt(0));
        continue;
      }
      throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "can only convert 1-character Strings");
      localObject1 = Integer.valueOf(_parseInt(paramString));
      continue;
      localObject1 = Long.valueOf(_parseLong(paramString));
      continue;
      localObject1 = Float.valueOf((float)_parseDouble(paramString));
      continue;
      localObject1 = Double.valueOf(_parseDouble(paramString));
      continue;
      try
      {
        Object localObject3 = this._deser._deserialize(paramString, paramDeserializationContext);
        localObject1 = localObject3;
      }
      catch (IOException localIOException2)
      {
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "unable to parse key as locale");
      }
      try
      {
        Object localObject2 = this._deser._deserialize(paramString, paramDeserializationContext);
        localObject1 = localObject2;
      }
      catch (IOException localIOException1)
      {
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "unable to parse key as currency");
      }
      localObject1 = paramDeserializationContext.parseDate(paramString);
      continue;
      Date localDate = paramDeserializationContext.parseDate(paramString);
      if (localDate == null) {
        continue;
      }
      localObject1 = paramDeserializationContext.constructCalendar(localDate);
      continue;
      localObject1 = UUID.fromString(paramString);
      continue;
      localObject1 = URI.create(paramString);
      continue;
      localObject1 = new URL(paramString);
      continue;
      try
      {
        Class localClass = paramDeserializationContext.findClass(paramString);
        localObject1 = localClass;
      }
      catch (Exception localException)
      {
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "unable to parse key as Class");
      }
    }
  }
  
  protected double _parseDouble(String paramString)
    throws IllegalArgumentException
  {
    return NumberInput.parseDouble(paramString);
  }
  
  protected int _parseInt(String paramString)
    throws IllegalArgumentException
  {
    return Integer.parseInt(paramString);
  }
  
  protected long _parseLong(String paramString)
    throws IllegalArgumentException
  {
    return Long.parseLong(paramString);
  }
  
  public Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject2;
    if (paramString == null) {
      localObject2 = null;
    }
    for (;;)
    {
      return localObject2;
      try
      {
        Object localObject1 = _parse(paramString, paramDeserializationContext);
        localObject2 = localObject1;
        if (localObject2 == null) {
          if ((this._keyClass.isEnum()) && (paramDeserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL))) {
            localObject2 = null;
          }
        }
      }
      catch (Exception localException)
      {
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation: " + localException.getMessage());
      }
    }
    throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation");
  }
  
  public Class<?> getKeyClass()
  {
    return this._keyClass;
  }
  
  static final class StringFactoryKeyDeserializer
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    final Method _factoryMethod;
    
    public StringFactoryKeyDeserializer(Method paramMethod)
    {
      super(paramMethod.getDeclaringClass());
      this._factoryMethod = paramMethod;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws Exception
    {
      Method localMethod = this._factoryMethod;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramString;
      return localMethod.invoke(null, arrayOfObject);
    }
  }
  
  static final class StringCtorKeyDeserializer
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected final Constructor<?> _ctor;
    
    public StringCtorKeyDeserializer(Constructor<?> paramConstructor)
    {
      super(paramConstructor.getDeclaringClass());
      this._ctor = paramConstructor;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws Exception
    {
      Constructor localConstructor = this._ctor;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramString;
      return localConstructor.newInstance(arrayOfObject);
    }
  }
  
  @JacksonStdImpl
  static final class EnumKD
    extends StdKeyDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected final AnnotatedMethod _factory;
    protected final EnumResolver _resolver;
    
    protected EnumKD(EnumResolver paramEnumResolver, AnnotatedMethod paramAnnotatedMethod)
    {
      super(paramEnumResolver.getEnumClass());
      this._resolver = paramEnumResolver;
      this._factory = paramAnnotatedMethod;
    }
    
    public Object _parse(String paramString, DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      if (this._factory != null) {}
      Object localObject1;
      do
      {
        try
        {
          Object localObject2 = this._factory.call1(paramString);
          localObject1 = localObject2;
          return localObject1;
        }
        catch (Exception localException)
        {
          ClassUtil.unwrapAndThrowAsIAE(localException);
        }
        localObject1 = this._resolver.findEnum(paramString);
      } while ((localObject1 != null) || (paramDeserializationContext.getConfig().isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)));
      throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not one of values for Enum class");
    }
  }
  
  static final class DelegatingKD
    extends KeyDeserializer
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected final JsonDeserializer<?> _delegate;
    protected final Class<?> _keyClass;
    
    protected DelegatingKD(Class<?> paramClass, JsonDeserializer<?> paramJsonDeserializer)
    {
      this._keyClass = paramClass;
      this._delegate = paramJsonDeserializer;
    }
    
    public final Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      Object localObject2;
      if (paramString == null) {
        localObject2 = null;
      }
      for (;;)
      {
        return localObject2;
        try
        {
          Object localObject1 = this._delegate.deserialize(paramDeserializationContext.getParser(), paramDeserializationContext);
          localObject2 = localObject1;
          if (localObject2 != null) {
            continue;
          }
          throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation");
        }
        catch (Exception localException)
        {
          throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation: " + localException.getMessage());
        }
      }
    }
    
    public Class<?> getKeyClass()
    {
      return this._keyClass;
    }
  }
  
  @JacksonStdImpl
  static final class StringKD
    extends StdKeyDeserializer
  {
    private static final StringKD sObject = new StringKD(Object.class);
    private static final StringKD sString = new StringKD(String.class);
    private static final long serialVersionUID = 1L;
    
    private StringKD(Class<?> paramClass)
    {
      super(paramClass);
    }
    
    public static StringKD forType(Class<?> paramClass)
    {
      StringKD localStringKD;
      if (paramClass == String.class) {
        localStringKD = sString;
      }
      for (;;)
      {
        return localStringKD;
        if (paramClass == Object.class) {
          localStringKD = sObject;
        } else {
          localStringKD = new StringKD(paramClass);
        }
      }
    }
    
    public Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      return paramString;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StdKeyDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */