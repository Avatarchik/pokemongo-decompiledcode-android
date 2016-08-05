package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

public class NumberDeserializers
{
  private static final HashSet<String> _classNames = new HashSet();
  
  static
  {
    Class[] arrayOfClass = new Class[11];
    arrayOfClass[0] = Boolean.class;
    arrayOfClass[1] = Byte.class;
    arrayOfClass[2] = Short.class;
    arrayOfClass[3] = Character.class;
    arrayOfClass[4] = Integer.class;
    arrayOfClass[5] = Long.class;
    arrayOfClass[6] = Float.class;
    arrayOfClass[7] = Double.class;
    arrayOfClass[8] = Number.class;
    arrayOfClass[9] = BigDecimal.class;
    arrayOfClass[10] = BigInteger.class;
    int i = arrayOfClass.length;
    for (int j = 0; j < i; j++)
    {
      Class localClass = arrayOfClass[j];
      _classNames.add(localClass.getName());
    }
  }
  
  public static JsonDeserializer<?> find(Class<?> paramClass, String paramString)
  {
    Object localObject;
    if (paramClass.isPrimitive()) {
      if (paramClass == Integer.TYPE) {
        localObject = IntegerDeserializer.primitiveInstance;
      }
    }
    for (;;)
    {
      return (JsonDeserializer<?>)localObject;
      if (paramClass == Boolean.TYPE)
      {
        localObject = BooleanDeserializer.primitiveInstance;
      }
      else if (paramClass == Long.TYPE)
      {
        localObject = LongDeserializer.primitiveInstance;
      }
      else if (paramClass == Double.TYPE)
      {
        localObject = DoubleDeserializer.primitiveInstance;
      }
      else if (paramClass == Character.TYPE)
      {
        localObject = CharacterDeserializer.primitiveInstance;
      }
      else if (paramClass == Byte.TYPE)
      {
        localObject = ByteDeserializer.primitiveInstance;
      }
      else if (paramClass == Short.TYPE)
      {
        localObject = ShortDeserializer.primitiveInstance;
      }
      else
      {
        if (paramClass != Float.TYPE) {
          break;
        }
        localObject = FloatDeserializer.primitiveInstance;
        continue;
        if (_classNames.contains(paramString))
        {
          if (paramClass == Integer.class)
          {
            localObject = IntegerDeserializer.wrapperInstance;
          }
          else if (paramClass == Boolean.class)
          {
            localObject = BooleanDeserializer.wrapperInstance;
          }
          else if (paramClass == Long.class)
          {
            localObject = LongDeserializer.wrapperInstance;
          }
          else if (paramClass == Double.class)
          {
            localObject = DoubleDeserializer.wrapperInstance;
          }
          else if (paramClass == Character.class)
          {
            localObject = CharacterDeserializer.wrapperInstance;
          }
          else if (paramClass == Byte.class)
          {
            localObject = ByteDeserializer.wrapperInstance;
          }
          else if (paramClass == Short.class)
          {
            localObject = ShortDeserializer.wrapperInstance;
          }
          else if (paramClass == Float.class)
          {
            localObject = FloatDeserializer.wrapperInstance;
          }
          else if (paramClass == Number.class)
          {
            localObject = NumberDeserializer.instance;
          }
          else if (paramClass == BigDecimal.class)
          {
            localObject = BigDecimalDeserializer.instance;
          }
          else
          {
            if (paramClass != BigInteger.class) {
              break;
            }
            localObject = BigIntegerDeserializer.instance;
          }
        }
        else {
          localObject = null;
        }
      }
    }
    throw new IllegalArgumentException("Internal error: can't find deserializer for " + paramClass.getName());
  }
  
  @JacksonStdImpl
  public static class BigDecimalDeserializer
    extends StdScalarDeserializer<BigDecimal>
  {
    public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();
    
    public BigDecimalDeserializer()
    {
      super();
    }
    
    public BigDecimal deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      BigDecimal localBigDecimal;
      switch (paramJsonParser.getCurrentTokenId())
      {
      case 4: 
      case 5: 
      default: 
        throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
      case 7: 
      case 8: 
        localBigDecimal = paramJsonParser.getDecimalValue();
      }
      do
      {
        for (;;)
        {
          return localBigDecimal;
          String str = paramJsonParser.getText().trim();
          if (str.length() == 0) {
            localBigDecimal = null;
          } else {
            try
            {
              localBigDecimal = new BigDecimal(str);
            }
            catch (IllegalArgumentException localIllegalArgumentException)
            {
              throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid representation");
            }
          }
        }
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          break;
        }
        paramJsonParser.nextToken();
        localBigDecimal = deserialize(paramJsonParser, paramDeserializationContext);
      } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'BigDecimal' value but there was more than a single value in the array");
    }
  }
  
  @JacksonStdImpl
  public static class BigIntegerDeserializer
    extends StdScalarDeserializer<BigInteger>
  {
    public static final BigIntegerDeserializer instance = new BigIntegerDeserializer();
    
    public BigIntegerDeserializer()
    {
      super();
    }
    
    public BigInteger deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      BigInteger localBigInteger;
      switch (paramJsonParser.getCurrentTokenId())
      {
      case 4: 
      case 5: 
      default: 
      case 7: 
        for (;;)
        {
          throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
          switch (NumberDeserializers.1.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[paramJsonParser.getNumberType().ordinal()])
          {
          }
        }
        localBigInteger = paramJsonParser.getBigIntegerValue();
      }
      for (;;)
      {
        return localBigInteger;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
          _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "java.math.BigInteger");
        }
        localBigInteger = paramJsonParser.getDecimalValue().toBigInteger();
        continue;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          break;
        }
        paramJsonParser.nextToken();
        localBigInteger = deserialize(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
          continue;
        }
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'BigInteger' value but there was more than a single value in the array");
        String str = paramJsonParser.getText().trim();
        if (str.length() == 0)
        {
          localBigInteger = null;
          continue;
        }
        try
        {
          localBigInteger = new BigInteger(str);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid representation");
        }
      }
    }
  }
  
  @JacksonStdImpl
  public static class NumberDeserializer
    extends StdScalarDeserializer<Object>
  {
    public static final NumberDeserializer instance = new NumberDeserializer();
    
    public NumberDeserializer()
    {
      super();
    }
    
    public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      Object localObject;
      switch (paramJsonParser.getCurrentTokenId())
      {
      case 4: 
      case 5: 
      default: 
        throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
      case 7: 
        if (paramDeserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
          localObject = _coerceIntegral(paramJsonParser, paramDeserializationContext);
        }
        break;
      }
      label286:
      do
      {
        for (;;)
        {
          return localObject;
          localObject = paramJsonParser.getNumberValue();
          continue;
          if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
          {
            localObject = paramJsonParser.getDecimalValue();
          }
          else
          {
            localObject = Double.valueOf(paramJsonParser.getDoubleValue());
            continue;
            String str = paramJsonParser.getText().trim();
            if (str.length() == 0)
            {
              localObject = getEmptyValue(paramDeserializationContext);
            }
            else if (_hasTextualNull(str))
            {
              localObject = getNullValue(paramDeserializationContext);
            }
            else if (_isPosInf(str))
            {
              localObject = Double.valueOf(Double.POSITIVE_INFINITY);
            }
            else if (_isNegInf(str))
            {
              localObject = Double.valueOf(Double.NEGATIVE_INFINITY);
            }
            else if (_isNaN(str))
            {
              localObject = Double.valueOf(NaN.0D);
            }
            else
            {
              try
              {
                if (_isIntNumber(str)) {
                  break label286;
                }
                if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                  localObject = new BigDecimal(str);
                }
              }
              catch (IllegalArgumentException localIllegalArgumentException)
              {
                throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid number");
              }
              localObject = new Double(str);
              continue;
              if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
              {
                localObject = new BigInteger(str);
              }
              else
              {
                long l = Long.parseLong(str);
                if ((!paramDeserializationContext.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS)) && (l <= 2147483647L) && (l >= -2147483648L))
                {
                  localObject = Integer.valueOf((int)l);
                }
                else
                {
                  Long localLong = Long.valueOf(l);
                  localObject = localLong;
                }
              }
            }
          }
        }
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          break;
        }
        paramJsonParser.nextToken();
        localObject = deserialize(paramJsonParser, paramDeserializationContext);
      } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
    }
    
    public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException
    {
      switch (paramJsonParser.getCurrentTokenId())
      {
      }
      for (Object localObject = paramTypeDeserializer.deserializeTypedFromScalar(paramJsonParser, paramDeserializationContext);; localObject = deserialize(paramJsonParser, paramDeserializationContext)) {
        return localObject;
      }
    }
  }
  
  @JacksonStdImpl
  public static class DoubleDeserializer
    extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Double>
  {
    static final DoubleDeserializer primitiveInstance = new DoubleDeserializer(Double.TYPE, Double.valueOf(0.0D));
    private static final long serialVersionUID = 1L;
    static final DoubleDeserializer wrapperInstance = new DoubleDeserializer(Double.class, null);
    
    public DoubleDeserializer(Class<Double> paramClass, Double paramDouble)
    {
      super(paramDouble);
    }
    
    public Double deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      return _parseDouble(paramJsonParser, paramDeserializationContext);
    }
    
    public Double deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException
    {
      return _parseDouble(paramJsonParser, paramDeserializationContext);
    }
  }
  
  @JacksonStdImpl
  public static class FloatDeserializer
    extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Float>
  {
    static final FloatDeserializer primitiveInstance = new FloatDeserializer(Float.TYPE, Float.valueOf(0.0F));
    private static final long serialVersionUID = 1L;
    static final FloatDeserializer wrapperInstance = new FloatDeserializer(Float.class, null);
    
    public FloatDeserializer(Class<Float> paramClass, Float paramFloat)
    {
      super(paramFloat);
    }
    
    public Float deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      return _parseFloat(paramJsonParser, paramDeserializationContext);
    }
  }
  
  @JacksonStdImpl
  public static final class LongDeserializer
    extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Long>
  {
    static final LongDeserializer primitiveInstance = new LongDeserializer(Long.TYPE, Long.valueOf(0L));
    private static final long serialVersionUID = 1L;
    static final LongDeserializer wrapperInstance = new LongDeserializer(Long.class, null);
    
    public LongDeserializer(Class<Long> paramClass, Long paramLong)
    {
      super(paramLong);
    }
    
    public Long deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      if (paramJsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {}
      for (Long localLong = Long.valueOf(paramJsonParser.getLongValue());; localLong = _parseLong(paramJsonParser, paramDeserializationContext)) {
        return localLong;
      }
    }
    
    public boolean isCachable()
    {
      return true;
    }
  }
  
  @JacksonStdImpl
  public static final class IntegerDeserializer
    extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Integer>
  {
    static final IntegerDeserializer primitiveInstance = new IntegerDeserializer(Integer.TYPE, Integer.valueOf(0));
    private static final long serialVersionUID = 1L;
    static final IntegerDeserializer wrapperInstance = new IntegerDeserializer(Integer.class, null);
    
    public IntegerDeserializer(Class<Integer> paramClass, Integer paramInteger)
    {
      super(paramInteger);
    }
    
    public Integer deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      if (paramJsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {}
      for (Integer localInteger = Integer.valueOf(paramJsonParser.getIntValue());; localInteger = _parseInteger(paramJsonParser, paramDeserializationContext)) {
        return localInteger;
      }
    }
    
    public Integer deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException
    {
      if (paramJsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {}
      for (Integer localInteger = Integer.valueOf(paramJsonParser.getIntValue());; localInteger = _parseInteger(paramJsonParser, paramDeserializationContext)) {
        return localInteger;
      }
    }
    
    public boolean isCachable()
    {
      return true;
    }
  }
  
  @JacksonStdImpl
  public static class CharacterDeserializer
    extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Character>
  {
    static final CharacterDeserializer primitiveInstance = new CharacterDeserializer(Character.TYPE, Character.valueOf('\000'));
    private static final long serialVersionUID = 1L;
    static final CharacterDeserializer wrapperInstance = new CharacterDeserializer(Character.class, null);
    
    public CharacterDeserializer(Class<Character> paramClass, Character paramCharacter)
    {
      super(paramCharacter);
    }
    
    public Character deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      Object localObject;
      switch (paramJsonParser.getCurrentTokenId())
      {
      case 4: 
      case 5: 
      default: 
      case 7: 
        int i;
        do
        {
          throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
          i = paramJsonParser.getIntValue();
        } while ((i < 0) || (i > 65535));
        localObject = Character.valueOf((char)i);
      }
      for (;;)
      {
        return (Character)localObject;
        String str = paramJsonParser.getText();
        if (str.length() == 1)
        {
          localObject = Character.valueOf(str.charAt(0));
        }
        else
        {
          if (str.length() != 0) {
            break;
          }
          localObject = (Character)getEmptyValue(paramDeserializationContext);
          continue;
          if (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            break;
          }
          paramJsonParser.nextToken();
          Character localCharacter = deserialize(paramJsonParser, paramDeserializationContext);
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
            throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
          }
          localObject = localCharacter;
        }
      }
    }
  }
  
  @JacksonStdImpl
  public static class ShortDeserializer
    extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Short>
  {
    static final ShortDeserializer primitiveInstance = new ShortDeserializer(Short.TYPE, Short.valueOf((short)0));
    private static final long serialVersionUID = 1L;
    static final ShortDeserializer wrapperInstance = new ShortDeserializer(Short.class, null);
    
    public ShortDeserializer(Class<Short> paramClass, Short paramShort)
    {
      super(paramShort);
    }
    
    public Short deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      return _parseShort(paramJsonParser, paramDeserializationContext);
    }
  }
  
  @JacksonStdImpl
  public static class ByteDeserializer
    extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Byte>
  {
    static final ByteDeserializer primitiveInstance = new ByteDeserializer(Byte.TYPE, Byte.valueOf((byte)0));
    private static final long serialVersionUID = 1L;
    static final ByteDeserializer wrapperInstance = new ByteDeserializer(Byte.class, null);
    
    public ByteDeserializer(Class<Byte> paramClass, Byte paramByte)
    {
      super(paramByte);
    }
    
    public Byte deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      return _parseByte(paramJsonParser, paramDeserializationContext);
    }
  }
  
  @JacksonStdImpl
  public static final class BooleanDeserializer
    extends NumberDeserializers.PrimitiveOrWrapperDeserializer<Boolean>
  {
    static final BooleanDeserializer primitiveInstance = new BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
    private static final long serialVersionUID = 1L;
    static final BooleanDeserializer wrapperInstance = new BooleanDeserializer(Boolean.class, null);
    
    public BooleanDeserializer(Class<Boolean> paramClass, Boolean paramBoolean)
    {
      super(paramBoolean);
    }
    
    public Boolean deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      return _parseBoolean(paramJsonParser, paramDeserializationContext);
    }
    
    public Boolean deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException
    {
      return _parseBoolean(paramJsonParser, paramDeserializationContext);
    }
  }
  
  protected static abstract class PrimitiveOrWrapperDeserializer<T>
    extends StdScalarDeserializer<T>
  {
    private static final long serialVersionUID = 1L;
    protected final T _nullValue;
    protected final boolean _primitive;
    
    protected PrimitiveOrWrapperDeserializer(Class<T> paramClass, T paramT)
    {
      super();
      this._nullValue = paramT;
      this._primitive = paramClass.isPrimitive();
    }
    
    @Deprecated
    public final T getNullValue()
    {
      return (T)this._nullValue;
    }
    
    public final T getNullValue(DeserializationContext paramDeserializationContext)
      throws JsonMappingException
    {
      if ((this._primitive) && (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = handledType().toString();
        throw paramDeserializationContext.mappingException("Can not map JSON null into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", arrayOfObject);
      }
      return (T)this._nullValue;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/NumberDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */