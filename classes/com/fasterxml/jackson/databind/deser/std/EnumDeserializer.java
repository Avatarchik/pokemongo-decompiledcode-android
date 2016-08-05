package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.CompactStringObjectMap;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

@JacksonStdImpl
public class EnumDeserializer
  extends StdScalarDeserializer<Object>
{
  private static final long serialVersionUID = 1L;
  protected final CompactStringObjectMap _enumLookup;
  protected Object[] _enumsByIndex;
  
  public EnumDeserializer(EnumResolver paramEnumResolver)
  {
    super(paramEnumResolver.getEnumClass());
    this._enumLookup = paramEnumResolver.constructLookup();
    this._enumsByIndex = paramEnumResolver.getRawEnums();
  }
  
  private final Object _deserializeAltString(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, String paramString)
    throws IOException
  {
    Object localObject = null;
    String str = paramString.trim();
    if (str.length() == 0) {
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
        break label93;
      }
    }
    label93:
    while (paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
      for (;;)
      {
        return localObject;
        int i = str.charAt(0);
        if ((i >= 48) && (i <= 57)) {
          try
          {
            int j = Integer.parseInt(str);
            _checkFailOnNumber(paramDeserializationContext);
            if ((j >= 0) && (j <= this._enumsByIndex.length)) {
              localObject = this._enumsByIndex[j];
            }
          }
          catch (NumberFormatException localNumberFormatException) {}
        }
      }
    }
    throw paramDeserializationContext.weirdStringException(str, _enumClass(), "value not one of declared Enum instance names: " + this._enumLookup.keys());
  }
  
  public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig paramDeserializationConfig, Class<?> paramClass, AnnotatedMethod paramAnnotatedMethod)
  {
    Class localClass = paramAnnotatedMethod.getRawParameterType(0);
    if (paramDeserializationConfig.canOverrideAccessModifiers()) {
      ClassUtil.checkAndFixAccess(paramAnnotatedMethod.getMember());
    }
    return new FactoryBasedDeserializer(paramClass, paramAnnotatedMethod, localClass);
  }
  
  protected void _checkFailOnNumber(DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
      throw paramDeserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
    }
  }
  
  protected Object _deserializeOther(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    paramJsonParser.getCurrentToken();
    Object localObject;
    if ((paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) && (paramJsonParser.isExpectedStartArrayToken()))
    {
      paramJsonParser.nextToken();
      localObject = deserialize(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + _enumClass().getName() + "' value but there was more than a single value in the array");
      }
    }
    else
    {
      throw paramDeserializationContext.mappingException(_enumClass());
    }
    return localObject;
  }
  
  protected Class<?> _enumClass()
  {
    return handledType();
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject;
    if ((localJsonToken == JsonToken.VALUE_STRING) || (localJsonToken == JsonToken.FIELD_NAME))
    {
      String str = paramJsonParser.getText();
      localObject = this._enumLookup.find(str);
      if (localObject == null) {
        localObject = _deserializeAltString(paramJsonParser, paramDeserializationContext, str);
      }
    }
    for (;;)
    {
      return localObject;
      if (localJsonToken == JsonToken.VALUE_NUMBER_INT)
      {
        _checkFailOnNumber(paramDeserializationContext);
        int i = paramJsonParser.getIntValue();
        if ((i >= 0) && (i <= this._enumsByIndex.length))
        {
          localObject = this._enumsByIndex[i];
        }
        else
        {
          if (!paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            throw paramDeserializationContext.weirdNumberException(Integer.valueOf(i), _enumClass(), "index value outside legal index range [0.." + (-1 + this._enumsByIndex.length) + "]");
          }
          localObject = null;
        }
      }
      else
      {
        localObject = _deserializeOther(paramJsonParser, paramDeserializationContext);
      }
    }
  }
  
  public boolean isCachable()
  {
    return true;
  }
  
  protected static class FactoryBasedDeserializer
    extends StdDeserializer<Object>
    implements ContextualDeserializer
  {
    private static final long serialVersionUID = 1L;
    protected final JsonDeserializer<?> _deser;
    protected final Method _factory;
    protected final Class<?> _inputType;
    
    protected FactoryBasedDeserializer(FactoryBasedDeserializer paramFactoryBasedDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    {
      super();
      this._inputType = paramFactoryBasedDeserializer._inputType;
      this._factory = paramFactoryBasedDeserializer._factory;
      this._deser = paramJsonDeserializer;
    }
    
    public FactoryBasedDeserializer(Class<?> paramClass1, AnnotatedMethod paramAnnotatedMethod, Class<?> paramClass2)
    {
      super();
      this._factory = paramAnnotatedMethod.getAnnotated();
      this._inputType = paramClass2;
      this._deser = null;
    }
    
    public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
      throws JsonMappingException
    {
      if ((this._deser == null) && (this._inputType != String.class)) {
        this = new FactoryBasedDeserializer(this, paramDeserializationContext.findContextualValueDeserializer(paramDeserializationContext.constructType(this._inputType), paramBeanProperty));
      }
      return this;
    }
    
    public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      Object localObject1;
      if (this._deser != null) {
        localObject1 = this._deser.deserialize(paramJsonParser, paramDeserializationContext);
      }
      for (;;)
      {
        try
        {
          Method localMethod = this._factory;
          Class localClass = this._valueClass;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localObject1;
          Object localObject2 = localMethod.invoke(localClass, arrayOfObject);
          return localObject2;
        }
        catch (Exception localException)
        {
          JsonToken localJsonToken;
          Throwable localThrowable = ClassUtil.getRootCause(localException);
          if (!(localThrowable instanceof IOException)) {
            continue;
          }
          throw ((IOException)localThrowable);
          throw paramDeserializationContext.instantiationException(this._valueClass, localThrowable);
        }
        localJsonToken = paramJsonParser.getCurrentToken();
        if ((localJsonToken == JsonToken.VALUE_STRING) || (localJsonToken == JsonToken.FIELD_NAME)) {
          localObject1 = paramJsonParser.getText();
        } else {
          localObject1 = paramJsonParser.getValueAsString();
        }
      }
    }
    
    public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException
    {
      if (this._deser == null) {}
      for (Object localObject = deserialize(paramJsonParser, paramDeserializationContext);; localObject = paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext)) {
        return localObject;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/EnumDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */