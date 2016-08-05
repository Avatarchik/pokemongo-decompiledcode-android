package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T>
  extends JsonDeserializer<T>
  implements Serializable
{
  protected static final int F_MASK_INT_COERCIONS = DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask();
  private static final long serialVersionUID = 1L;
  protected final Class<?> _valueClass;
  
  protected StdDeserializer(JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (Class localClass = null;; localClass = paramJavaType.getRawClass())
    {
      this._valueClass = localClass;
      return;
    }
  }
  
  protected StdDeserializer(StdDeserializer<?> paramStdDeserializer)
  {
    this._valueClass = paramStdDeserializer._valueClass;
  }
  
  protected StdDeserializer(Class<?> paramClass)
  {
    this._valueClass = paramClass;
  }
  
  protected static final double parseDouble(String paramString)
    throws NumberFormatException
  {
    if ("2.2250738585072012e-308".equals(paramString)) {}
    for (double d = Double.MIN_VALUE;; d = Double.parseDouble(paramString)) {
      return d;
    }
  }
  
  protected Object _coerceIntegral(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    int i = paramDeserializationContext.getDeserializationFeatures();
    Object localObject;
    if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(i)) {
      localObject = paramJsonParser.getBigIntegerValue();
    }
    for (;;)
    {
      return localObject;
      if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(i)) {
        localObject = Long.valueOf(paramJsonParser.getLongValue());
      } else {
        localObject = paramJsonParser.getBigIntegerValue();
      }
    }
  }
  
  protected T _deserializeFromEmpty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.START_ARRAY)
    {
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
        break label76;
      }
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {}
    }
    while ((localJsonToken == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().trim().isEmpty()))
    {
      return null;
      throw paramDeserializationContext.mappingException(handledType(), JsonToken.START_ARRAY);
    }
    label76:
    throw paramDeserializationContext.mappingException(handledType());
  }
  
  protected void _failDoubleToIntCoercion(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, String paramString)
    throws IOException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramJsonParser.getValueAsString();
    arrayOfObject[1] = paramString;
    throw paramDeserializationContext.mappingException("Can not coerce a floating-point value ('%s') into %s; enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow", arrayOfObject);
  }
  
  protected boolean _hasTextualNull(String paramString)
  {
    return "null".equals(paramString);
  }
  
  protected final boolean _isIntNumber(String paramString)
  {
    boolean bool = false;
    int i = paramString.length();
    int k;
    if (i > 0)
    {
      int j = paramString.charAt(0);
      if ((j != 45) && (j != 43)) {
        break label65;
      }
      k = 1;
      if (k >= i) {
        break label80;
      }
      int m = paramString.charAt(k);
      if ((m <= 57) && (m >= 48)) {
        break label71;
      }
    }
    for (;;)
    {
      return bool;
      label65:
      k = 0;
      break;
      label71:
      k += 1;
      break;
      label80:
      bool = true;
    }
  }
  
  protected final boolean _isNaN(String paramString)
  {
    return "NaN".equals(paramString);
  }
  
  protected final boolean _isNegInf(String paramString)
  {
    if (("-Infinity".equals(paramString)) || ("-INF".equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected final boolean _isPosInf(String paramString)
  {
    if (("Infinity".equals(paramString)) || ("INF".equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected final Boolean _parseBoolean(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject;
    if (localJsonToken == JsonToken.VALUE_TRUE) {
      localObject = Boolean.TRUE;
    }
    for (;;)
    {
      return (Boolean)localObject;
      if (localJsonToken == JsonToken.VALUE_FALSE)
      {
        localObject = Boolean.FALSE;
      }
      else if (localJsonToken == JsonToken.VALUE_NUMBER_INT)
      {
        if (paramJsonParser.getNumberType() == JsonParser.NumberType.INT)
        {
          if (paramJsonParser.getIntValue() == 0) {
            localObject = Boolean.FALSE;
          } else {
            localObject = Boolean.TRUE;
          }
        }
        else {
          localObject = Boolean.valueOf(_parseBooleanFromNumber(paramJsonParser, paramDeserializationContext));
        }
      }
      else if (localJsonToken == JsonToken.VALUE_NULL)
      {
        localObject = (Boolean)getNullValue(paramDeserializationContext);
      }
      else if (localJsonToken == JsonToken.VALUE_STRING)
      {
        String str = paramJsonParser.getText().trim();
        if (("true".equals(str)) || ("True".equals(str))) {
          localObject = Boolean.TRUE;
        } else if (("false".equals(str)) || ("False".equals(str))) {
          localObject = Boolean.FALSE;
        } else if (str.length() == 0) {
          localObject = (Boolean)getEmptyValue(paramDeserializationContext);
        } else if (_hasTextualNull(str)) {
          localObject = (Boolean)getNullValue(paramDeserializationContext);
        } else {
          throw paramDeserializationContext.weirdStringException(str, this._valueClass, "only \"true\" or \"false\" recognized");
        }
      }
      else
      {
        if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
          break;
        }
        paramJsonParser.nextToken();
        Boolean localBoolean = _parseBoolean(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Boolean' value but there was more than a single value in the array");
        }
        localObject = localBoolean;
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected final boolean _parseBooleanFromNumber(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Boolean localBoolean;
    boolean bool;
    if (paramJsonParser.getNumberType() == JsonParser.NumberType.LONG) {
      if (paramJsonParser.getLongValue() == 0L)
      {
        localBoolean = Boolean.FALSE;
        bool = localBoolean.booleanValue();
      }
    }
    for (;;)
    {
      return bool;
      localBoolean = Boolean.TRUE;
      break;
      String str = paramJsonParser.getText();
      if (("0.0".equals(str)) || ("0".equals(str))) {
        bool = Boolean.FALSE.booleanValue();
      } else {
        bool = Boolean.TRUE.booleanValue();
      }
    }
  }
  
  protected final boolean _parseBooleanPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    boolean bool1 = true;
    boolean bool2 = false;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.VALUE_TRUE) {
      bool2 = bool1;
    }
    for (;;)
    {
      return bool2;
      if ((localJsonToken != JsonToken.VALUE_FALSE) && (localJsonToken != JsonToken.VALUE_NULL)) {
        if (localJsonToken == JsonToken.VALUE_NUMBER_INT)
        {
          if (paramJsonParser.getNumberType() == JsonParser.NumberType.INT)
          {
            if (paramJsonParser.getIntValue() != 0) {}
            for (;;)
            {
              bool2 = bool1;
              break;
              bool1 = false;
            }
          }
          bool2 = _parseBooleanFromNumber(paramJsonParser, paramDeserializationContext);
        }
        else if (localJsonToken == JsonToken.VALUE_STRING)
        {
          String str = paramJsonParser.getText().trim();
          if (("true".equals(str)) || ("True".equals(str))) {
            bool2 = bool1;
          } else if ((!"false".equals(str)) && (!"False".equals(str)) && (str.length() != 0) && (!_hasTextualNull(str))) {
            throw paramDeserializationContext.weirdStringException(str, this._valueClass, "only \"true\" or \"false\" recognized");
          }
        }
        else
        {
          if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
            break;
          }
          paramJsonParser.nextToken();
          boolean bool3 = _parseBooleanPrimitive(paramJsonParser, paramDeserializationContext);
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
            throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'boolean' value but there was more than a single value in the array");
          }
          bool2 = bool3;
        }
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected Byte _parseByte(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      localObject = Byte.valueOf(paramJsonParser.getByteValue());
    }
    for (;;)
    {
      return (Byte)localObject;
      if (localJsonToken == JsonToken.VALUE_STRING)
      {
        String str = paramJsonParser.getText().trim();
        if (_hasTextualNull(str))
        {
          localObject = (Byte)getNullValue(paramDeserializationContext);
        }
        else
        {
          int i;
          try
          {
            if (str.length() == 0)
            {
              localObject = (Byte)getEmptyValue(paramDeserializationContext);
              continue;
            }
            i = NumberInput.parseInt(str);
            if ((i < -128) || (i > 255)) {
              throw paramDeserializationContext.weirdStringException(str, this._valueClass, "overflow, value can not be represented as 8-bit value");
            }
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid Byte value");
          }
          localObject = Byte.valueOf((byte)i);
        }
      }
      else if (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      {
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
          _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "Byte");
        }
        localObject = Byte.valueOf(paramJsonParser.getByteValue());
      }
      else if (localJsonToken == JsonToken.VALUE_NULL)
      {
        localObject = (Byte)getNullValue(paramDeserializationContext);
      }
      else
      {
        if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
          break;
        }
        paramJsonParser.nextToken();
        Byte localByte = _parseByte(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
        }
        localObject = localByte;
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected Date _parseDate(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      localObject = new Date(paramJsonParser.getLongValue());
    }
    for (;;)
    {
      return (Date)localObject;
      if (localJsonToken == JsonToken.VALUE_NULL)
      {
        localObject = (Date)getNullValue(paramDeserializationContext);
      }
      else if (localJsonToken == JsonToken.VALUE_STRING)
      {
        String str = null;
        try
        {
          str = paramJsonParser.getText().trim();
          if (str.length() == 0)
          {
            localObject = (Date)getEmptyValue(paramDeserializationContext);
            continue;
          }
          if (_hasTextualNull(str))
          {
            localObject = (Date)getNullValue(paramDeserializationContext);
            continue;
          }
          Date localDate2 = paramDeserializationContext.parseDate(str);
          localObject = localDate2;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid representation (error: " + localIllegalArgumentException.getMessage() + ")");
        }
      }
      else
      {
        if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
          break;
        }
        paramJsonParser.nextToken();
        Date localDate1 = _parseDate(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'java.util.Date' value but there was more than a single value in the array");
        }
        localObject = localDate1;
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected final Double _parseDouble(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      localObject = Double.valueOf(paramJsonParser.getDoubleValue());
    }
    for (;;)
    {
      return (Double)localObject;
      String str;
      if (localJsonToken == JsonToken.VALUE_STRING)
      {
        str = paramJsonParser.getText().trim();
        if (str.length() == 0)
        {
          localObject = (Double)getEmptyValue(paramDeserializationContext);
        }
        else if (_hasTextualNull(str))
        {
          localObject = (Double)getNullValue(paramDeserializationContext);
        }
        else
        {
          switch (str.charAt(0))
          {
          }
          do
          {
            do
            {
              do
              {
                try
                {
                  Double localDouble2 = Double.valueOf(parseDouble(str));
                  localObject = localDouble2;
                }
                catch (IllegalArgumentException localIllegalArgumentException)
                {
                  throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid Double value");
                }
              } while (!_isPosInf(str));
              localObject = Double.valueOf(Double.POSITIVE_INFINITY);
              break;
            } while (!_isNaN(str));
            localObject = Double.valueOf(NaN.0D);
            break;
          } while (!_isNegInf(str));
          localObject = Double.valueOf(Double.NEGATIVE_INFINITY);
        }
      }
      else if (localJsonToken == JsonToken.VALUE_NULL)
      {
        localObject = (Double)getNullValue(paramDeserializationContext);
      }
      else
      {
        if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
          break;
        }
        paramJsonParser.nextToken();
        Double localDouble1 = _parseDouble(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Double' value but there was more than a single value in the array");
        }
        localObject = localDouble1;
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected final double _parseDoublePrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    double d1 = 0.0D;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      d1 = paramJsonParser.getDoubleValue();
    }
    do
    {
      String str;
      do
      {
        for (;;)
        {
          return d1;
          if (localJsonToken != JsonToken.VALUE_STRING) {
            break;
          }
          str = paramJsonParser.getText().trim();
          if ((str.length() != 0) && (!_hasTextualNull(str)))
          {
            switch (str.charAt(0))
            {
            }
            do
            {
              do
              {
                do
                {
                  try
                  {
                    double d2 = parseDouble(str);
                    d1 = d2;
                  }
                  catch (IllegalArgumentException localIllegalArgumentException)
                  {
                    throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid double value");
                  }
                } while (!_isPosInf(str));
                d1 = Double.POSITIVE_INFINITY;
                break;
              } while (!_isNaN(str));
              d1 = NaN.0D;
              break;
            } while (!_isNegInf(str));
            d1 = Double.NEGATIVE_INFINITY;
          }
        }
      } while (localJsonToken == JsonToken.VALUE_NULL);
      if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
        break;
      }
      paramJsonParser.nextToken();
      d1 = _parseDoublePrimitive(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected final Float _parseFloat(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject;
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      localObject = Float.valueOf(paramJsonParser.getFloatValue());
    }
    for (;;)
    {
      return (Float)localObject;
      String str;
      if (localJsonToken == JsonToken.VALUE_STRING)
      {
        str = paramJsonParser.getText().trim();
        if (str.length() == 0)
        {
          localObject = (Float)getEmptyValue(paramDeserializationContext);
        }
        else if (_hasTextualNull(str))
        {
          localObject = (Float)getNullValue(paramDeserializationContext);
        }
        else
        {
          switch (str.charAt(0))
          {
          }
          do
          {
            do
            {
              do
              {
                try
                {
                  Float localFloat2 = Float.valueOf(Float.parseFloat(str));
                  localObject = localFloat2;
                }
                catch (IllegalArgumentException localIllegalArgumentException)
                {
                  throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid Float value");
                }
              } while (!_isPosInf(str));
              localObject = Float.valueOf(Float.POSITIVE_INFINITY);
              break;
            } while (!_isNaN(str));
            localObject = Float.valueOf(NaN.0F);
            break;
          } while (!_isNegInf(str));
          localObject = Float.valueOf(Float.NEGATIVE_INFINITY);
        }
      }
      else if (localJsonToken == JsonToken.VALUE_NULL)
      {
        localObject = (Float)getNullValue(paramDeserializationContext);
      }
      else
      {
        if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
          break;
        }
        paramJsonParser.nextToken();
        Float localFloat1 = _parseFloat(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Byte' value but there was more than a single value in the array");
        }
        localObject = localFloat1;
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected final float _parseFloatPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    float f1 = 0.0F;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {
      f1 = paramJsonParser.getFloatValue();
    }
    do
    {
      String str;
      do
      {
        for (;;)
        {
          return f1;
          if (localJsonToken != JsonToken.VALUE_STRING) {
            break;
          }
          str = paramJsonParser.getText().trim();
          if ((str.length() != 0) && (!_hasTextualNull(str)))
          {
            switch (str.charAt(0))
            {
            }
            do
            {
              do
              {
                do
                {
                  try
                  {
                    float f2 = Float.parseFloat(str);
                    f1 = f2;
                  }
                  catch (IllegalArgumentException localIllegalArgumentException)
                  {
                    throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid float value");
                  }
                } while (!_isPosInf(str));
                f1 = Float.POSITIVE_INFINITY;
                break;
              } while (!_isNaN(str));
              f1 = NaN.0F;
              break;
            } while (!_isNegInf(str));
            f1 = Float.NEGATIVE_INFINITY;
          }
        }
      } while (localJsonToken == JsonToken.VALUE_NULL);
      if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
        break;
      }
      paramJsonParser.nextToken();
      f1 = _parseFloatPrimitive(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'float' value but there was more than a single value in the array");
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected final int _parseIntPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    int i = 0;
    if (paramJsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
      i = paramJsonParser.getIntValue();
    }
    JsonToken localJsonToken;
    label175:
    do
    {
      do
      {
        for (;;)
        {
          return i;
          localJsonToken = paramJsonParser.getCurrentToken();
          if (localJsonToken == JsonToken.VALUE_STRING)
          {
            String str = paramJsonParser.getText().trim();
            if (!_hasTextualNull(str))
            {
              int j;
              long l;
              try
              {
                j = str.length();
                if (j <= 9) {
                  break label175;
                }
                l = Long.parseLong(str);
                if ((l < -2147483648L) || (l > 2147483647L)) {
                  throw paramDeserializationContext.weirdStringException(str, this._valueClass, "Overflow: numeric value (" + str + ") out of range of int (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
                }
              }
              catch (IllegalArgumentException localIllegalArgumentException)
              {
                throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid int value");
              }
              i = (int)l;
              continue;
              if (j != 0)
              {
                int k = NumberInput.parseInt(str);
                i = k;
              }
            }
          }
          else
          {
            if (localJsonToken != JsonToken.VALUE_NUMBER_FLOAT) {
              break;
            }
            if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
              _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "int");
            }
            i = paramJsonParser.getValueAsInt();
          }
        }
      } while (localJsonToken == JsonToken.VALUE_NULL);
      if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
        break;
      }
      paramJsonParser.nextToken();
      i = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'int' value but there was more than a single value in the array");
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected final Integer _parseInteger(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 4: 
    case 5: 
    case 9: 
    case 10: 
    default: 
      throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
    case 7: 
      localObject = Integer.valueOf(paramJsonParser.getIntValue());
    }
    for (;;)
    {
      return (Integer)localObject;
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
        _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "Integer");
      }
      localObject = Integer.valueOf(paramJsonParser.getValueAsInt());
      continue;
      String str = paramJsonParser.getText().trim();
      int i;
      long l;
      try
      {
        i = str.length();
        if (_hasTextualNull(str))
        {
          localObject = (Integer)getNullValue(paramDeserializationContext);
          continue;
        }
        if (i <= 9) {
          break label275;
        }
        l = Long.parseLong(str);
        if ((l < -2147483648L) || (l > 2147483647L)) {
          throw paramDeserializationContext.weirdStringException(str, this._valueClass, "Overflow: numeric value (" + str + ") out of range of Integer (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid Integer value");
      }
      int j = (int)l;
      localObject = Integer.valueOf(j);
      continue;
      label275:
      if (i == 0)
      {
        localObject = (Integer)getEmptyValue(paramDeserializationContext);
      }
      else
      {
        Integer localInteger2 = Integer.valueOf(NumberInput.parseInt(str));
        localObject = localInteger2;
        continue;
        localObject = (Integer)getNullValue(paramDeserializationContext);
        continue;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          break;
        }
        paramJsonParser.nextToken();
        Integer localInteger1 = _parseInteger(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Integer' value but there was more than a single value in the array");
        }
        localObject = localInteger1;
      }
    }
  }
  
  protected final Long _parseLong(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 4: 
    case 5: 
    case 9: 
    case 10: 
    default: 
      throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
    case 7: 
      localObject = Long.valueOf(paramJsonParser.getLongValue());
    }
    for (;;)
    {
      return (Long)localObject;
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
        _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "Long");
      }
      localObject = Long.valueOf(paramJsonParser.getValueAsLong());
      continue;
      String str = paramJsonParser.getText().trim();
      if (str.length() == 0)
      {
        localObject = (Long)getEmptyValue(paramDeserializationContext);
      }
      else if (_hasTextualNull(str))
      {
        localObject = (Long)getNullValue(paramDeserializationContext);
      }
      else
      {
        try
        {
          Long localLong2 = Long.valueOf(NumberInput.parseLong(str));
          localObject = localLong2;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid Long value");
        }
        localObject = (Long)getNullValue(paramDeserializationContext);
        continue;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
          break;
        }
        paramJsonParser.nextToken();
        Long localLong1 = _parseLong(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Long' value but there was more than a single value in the array");
        }
        localObject = localLong1;
      }
    }
  }
  
  protected final long _parseLongPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    long l1 = 0L;
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 4: 
    case 5: 
    case 9: 
    case 10: 
    default: 
      throw paramDeserializationContext.mappingException(this._valueClass, paramJsonParser.getCurrentToken());
    case 7: 
      l1 = paramJsonParser.getLongValue();
    }
    do
    {
      for (;;)
      {
        return l1;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
          _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "long");
        }
        l1 = paramJsonParser.getValueAsLong();
        continue;
        String str = paramJsonParser.getText().trim();
        if ((str.length() != 0) && (!_hasTextualNull(str))) {
          try
          {
            long l2 = NumberInput.parseLong(str);
            l1 = l2;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid long value");
          }
        }
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
        break;
      }
      paramJsonParser.nextToken();
      l1 = _parseLongPrimitive(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'long' value but there was more than a single value in the array");
  }
  
  protected Short _parseShort(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      localObject = Short.valueOf(paramJsonParser.getShortValue());
    }
    for (;;)
    {
      return (Short)localObject;
      if (localJsonToken == JsonToken.VALUE_STRING)
      {
        String str = paramJsonParser.getText().trim();
        int i;
        try
        {
          if (str.length() == 0)
          {
            localObject = (Short)getEmptyValue(paramDeserializationContext);
            continue;
          }
          if (_hasTextualNull(str))
          {
            localObject = (Short)getNullValue(paramDeserializationContext);
            continue;
          }
          i = NumberInput.parseInt(str);
          if ((i < 32768) || (i > 32767)) {
            throw paramDeserializationContext.weirdStringException(str, this._valueClass, "overflow, value can not be represented as 16-bit value");
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw paramDeserializationContext.weirdStringException(str, this._valueClass, "not a valid Short value");
        }
        localObject = Short.valueOf((short)i);
      }
      else if (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      {
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
          _failDoubleToIntCoercion(paramJsonParser, paramDeserializationContext, "Short");
        }
        localObject = Short.valueOf(paramJsonParser.getShortValue());
      }
      else if (localJsonToken == JsonToken.VALUE_NULL)
      {
        localObject = (Short)getNullValue(paramDeserializationContext);
      }
      else
      {
        if ((localJsonToken != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
          break;
        }
        paramJsonParser.nextToken();
        Short localShort = _parseShort(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'Short' value but there was more than a single value in the array");
        }
        localObject = localShort;
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  protected final short _parseShortPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    int i = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
    if ((i < 32768) || (i > 32767)) {
      throw paramDeserializationContext.weirdStringException(String.valueOf(i), this._valueClass, "overflow, value can not be represented as 16-bit value");
    }
    return (short)i;
  }
  
  protected final String _parseString(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject;
    if (localJsonToken == JsonToken.VALUE_STRING) {
      localObject = paramJsonParser.getText();
    }
    for (;;)
    {
      return (String)localObject;
      if ((localJsonToken == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
      {
        paramJsonParser.nextToken();
        localObject = _parseString(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'String' value but there was more than a single value in the array");
        }
      }
      else
      {
        String str = paramJsonParser.getValueAsString();
        if (str == null) {
          break;
        }
        localObject = str;
      }
    }
    throw paramDeserializationContext.mappingException(String.class, paramJsonParser.getCurrentToken());
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  protected JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    Converter localConverter;
    JavaType localJavaType;
    if ((localAnnotationIntrospector != null) && (paramBeanProperty != null))
    {
      AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
      if (localAnnotatedMember != null)
      {
        Object localObject2 = localAnnotationIntrospector.findDeserializationContentConverter(localAnnotatedMember);
        if (localObject2 != null)
        {
          localConverter = paramDeserializationContext.converterInstance(paramBeanProperty.getMember(), localObject2);
          localJavaType = localConverter.getInputType(paramDeserializationContext.getTypeFactory());
          if (paramJsonDeserializer == null) {
            paramJsonDeserializer = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);
          }
        }
      }
    }
    for (Object localObject1 = new StdDelegatingDeserializer(localConverter, localJavaType, paramJsonDeserializer);; localObject1 = paramJsonDeserializer) {
      return (JsonDeserializer<?>)localObject1;
    }
  }
  
  protected JsonDeserializer<Object> findDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    return paramDeserializationContext.findContextualValueDeserializer(paramJavaType, paramBeanProperty);
  }
  
  @Deprecated
  public final Class<?> getValueClass()
  {
    return this._valueClass;
  }
  
  public JavaType getValueType()
  {
    return null;
  }
  
  protected void handleUnknownProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException
  {
    if (paramObject == null) {
      paramObject = handledType();
    }
    if (paramDeserializationContext.handleUnknownProperty(paramJsonParser, this, paramObject, paramString)) {}
    for (;;)
    {
      return;
      paramDeserializationContext.reportUnknownProperty(paramObject, paramString, this);
      paramJsonParser.skipChildren();
    }
  }
  
  public Class<?> handledType()
  {
    return this._valueClass;
  }
  
  protected boolean isDefaultDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    return ClassUtil.isJacksonStdImpl(paramJsonDeserializer);
  }
  
  protected boolean isDefaultKeyDeserializer(KeyDeserializer paramKeyDeserializer)
  {
    return ClassUtil.isJacksonStdImpl(paramKeyDeserializer);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StdDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */