package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ByteBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.FloatBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.IntBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.LongBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ShortBuilder;
import java.io.IOException;

public abstract class PrimitiveArrayDeserializers<T>
  extends StdDeserializer<T>
{
  protected PrimitiveArrayDeserializers(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  public static JsonDeserializer<?> forType(Class<?> paramClass)
  {
    Object localObject;
    if (paramClass == Integer.TYPE) {
      localObject = IntDeser.instance;
    }
    for (;;)
    {
      return (JsonDeserializer<?>)localObject;
      if (paramClass == Long.TYPE)
      {
        localObject = LongDeser.instance;
      }
      else if (paramClass == Byte.TYPE)
      {
        localObject = new ByteDeser();
      }
      else if (paramClass == Short.TYPE)
      {
        localObject = new ShortDeser();
      }
      else if (paramClass == Float.TYPE)
      {
        localObject = new FloatDeser();
      }
      else if (paramClass == Double.TYPE)
      {
        localObject = new DoubleDeser();
      }
      else if (paramClass == Boolean.TYPE)
      {
        localObject = new BooleanDeser();
      }
      else
      {
        if (paramClass != Character.TYPE) {
          break;
        }
        localObject = new CharDeser();
      }
    }
    throw new IllegalStateException();
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  @JacksonStdImpl
  static final class DoubleDeser
    extends PrimitiveArrayDeserializers<double[]>
  {
    private static final long serialVersionUID = 1L;
    
    public DoubleDeser()
    {
      super();
    }
    
    private final double[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      double[] arrayOfDouble;
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        arrayOfDouble = null;
      }
      for (;;)
      {
        return arrayOfDouble;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
          throw paramDeserializationContext.mappingException(this._valueClass);
        }
        arrayOfDouble = new double[1];
        arrayOfDouble[0] = _parseDoublePrimitive(paramJsonParser, paramDeserializationContext);
      }
    }
    
    public double[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      double[] arrayOfDouble2;
      if (!paramJsonParser.isExpectedStartArrayToken())
      {
        arrayOfDouble2 = handleNonArray(paramJsonParser, paramDeserializationContext);
        return arrayOfDouble2;
      }
      ArrayBuilders.DoubleBuilder localDoubleBuilder = paramDeserializationContext.getArrayBuilders().getDoubleBuilder();
      double[] arrayOfDouble1 = (double[])localDoubleBuilder.resetAndStart();
      int i = 0;
      for (;;)
      {
        try
        {
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
          {
            double d = _parseDoublePrimitive(paramJsonParser, paramDeserializationContext);
            if (i < arrayOfDouble1.length) {
              break label129;
            }
            arrayOfDouble1 = (double[])localDoubleBuilder.appendCompletedChunk(arrayOfDouble1, i);
            j = 0;
            i = j + 1;
            arrayOfDouble1[j] = d;
            continue;
          }
          arrayOfDouble2 = (double[])localDoubleBuilder.completeAndClearBuffer(arrayOfDouble1, i);
        }
        catch (Exception localException)
        {
          throw JsonMappingException.wrapWithPath(localException, arrayOfDouble1, i + localDoubleBuilder.bufferedSize());
        }
        break;
        label129:
        int j = i;
      }
    }
  }
  
  @JacksonStdImpl
  static final class FloatDeser
    extends PrimitiveArrayDeserializers<float[]>
  {
    private static final long serialVersionUID = 1L;
    
    public FloatDeser()
    {
      super();
    }
    
    private final float[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      float[] arrayOfFloat;
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        arrayOfFloat = null;
      }
      for (;;)
      {
        return arrayOfFloat;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
          throw paramDeserializationContext.mappingException(this._valueClass);
        }
        arrayOfFloat = new float[1];
        arrayOfFloat[0] = _parseFloatPrimitive(paramJsonParser, paramDeserializationContext);
      }
    }
    
    public float[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      float[] arrayOfFloat2;
      if (!paramJsonParser.isExpectedStartArrayToken())
      {
        arrayOfFloat2 = handleNonArray(paramJsonParser, paramDeserializationContext);
        return arrayOfFloat2;
      }
      ArrayBuilders.FloatBuilder localFloatBuilder = paramDeserializationContext.getArrayBuilders().getFloatBuilder();
      float[] arrayOfFloat1 = (float[])localFloatBuilder.resetAndStart();
      int i = 0;
      for (;;)
      {
        try
        {
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
          {
            float f = _parseFloatPrimitive(paramJsonParser, paramDeserializationContext);
            if (i < arrayOfFloat1.length) {
              break label129;
            }
            arrayOfFloat1 = (float[])localFloatBuilder.appendCompletedChunk(arrayOfFloat1, i);
            j = 0;
            i = j + 1;
            arrayOfFloat1[j] = f;
            continue;
          }
          arrayOfFloat2 = (float[])localFloatBuilder.completeAndClearBuffer(arrayOfFloat1, i);
        }
        catch (Exception localException)
        {
          throw JsonMappingException.wrapWithPath(localException, arrayOfFloat1, i + localFloatBuilder.bufferedSize());
        }
        break;
        label129:
        int j = i;
      }
    }
  }
  
  @JacksonStdImpl
  static final class LongDeser
    extends PrimitiveArrayDeserializers<long[]>
  {
    public static final LongDeser instance = new LongDeser();
    private static final long serialVersionUID = 1L;
    
    public LongDeser()
    {
      super();
    }
    
    private final long[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      long[] arrayOfLong;
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        arrayOfLong = null;
      }
      for (;;)
      {
        return arrayOfLong;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
          throw paramDeserializationContext.mappingException(this._valueClass);
        }
        arrayOfLong = new long[1];
        arrayOfLong[0] = _parseLongPrimitive(paramJsonParser, paramDeserializationContext);
      }
    }
    
    public long[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      long[] arrayOfLong2;
      if (!paramJsonParser.isExpectedStartArrayToken())
      {
        arrayOfLong2 = handleNonArray(paramJsonParser, paramDeserializationContext);
        return arrayOfLong2;
      }
      ArrayBuilders.LongBuilder localLongBuilder = paramDeserializationContext.getArrayBuilders().getLongBuilder();
      long[] arrayOfLong1 = (long[])localLongBuilder.resetAndStart();
      int i = 0;
      for (;;)
      {
        try
        {
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
          {
            long l = _parseLongPrimitive(paramJsonParser, paramDeserializationContext);
            if (i < arrayOfLong1.length) {
              break label129;
            }
            arrayOfLong1 = (long[])localLongBuilder.appendCompletedChunk(arrayOfLong1, i);
            j = 0;
            i = j + 1;
            arrayOfLong1[j] = l;
            continue;
          }
          arrayOfLong2 = (long[])localLongBuilder.completeAndClearBuffer(arrayOfLong1, i);
        }
        catch (Exception localException)
        {
          throw JsonMappingException.wrapWithPath(localException, arrayOfLong1, i + localLongBuilder.bufferedSize());
        }
        break;
        label129:
        int j = i;
      }
    }
  }
  
  @JacksonStdImpl
  static final class IntDeser
    extends PrimitiveArrayDeserializers<int[]>
  {
    public static final IntDeser instance = new IntDeser();
    private static final long serialVersionUID = 1L;
    
    public IntDeser()
    {
      super();
    }
    
    private final int[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      int[] arrayOfInt;
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        arrayOfInt = null;
      }
      for (;;)
      {
        return arrayOfInt;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
          throw paramDeserializationContext.mappingException(this._valueClass);
        }
        arrayOfInt = new int[1];
        arrayOfInt[0] = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
      }
    }
    
    public int[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      int[] arrayOfInt2;
      if (!paramJsonParser.isExpectedStartArrayToken())
      {
        arrayOfInt2 = handleNonArray(paramJsonParser, paramDeserializationContext);
        return arrayOfInt2;
      }
      ArrayBuilders.IntBuilder localIntBuilder = paramDeserializationContext.getArrayBuilders().getIntBuilder();
      int[] arrayOfInt1 = (int[])localIntBuilder.resetAndStart();
      int i = 0;
      for (;;)
      {
        try
        {
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
          {
            int j = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
            if (i < arrayOfInt1.length) {
              break label129;
            }
            arrayOfInt1 = (int[])localIntBuilder.appendCompletedChunk(arrayOfInt1, i);
            k = 0;
            i = k + 1;
            arrayOfInt1[k] = j;
            continue;
          }
          arrayOfInt2 = (int[])localIntBuilder.completeAndClearBuffer(arrayOfInt1, i);
        }
        catch (Exception localException)
        {
          throw JsonMappingException.wrapWithPath(localException, arrayOfInt1, i + localIntBuilder.bufferedSize());
        }
        break;
        label129:
        int k = i;
      }
    }
  }
  
  @JacksonStdImpl
  static final class ShortDeser
    extends PrimitiveArrayDeserializers<short[]>
  {
    private static final long serialVersionUID = 1L;
    
    public ShortDeser()
    {
      super();
    }
    
    private final short[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      short[] arrayOfShort;
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        arrayOfShort = null;
      }
      for (;;)
      {
        return arrayOfShort;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
          throw paramDeserializationContext.mappingException(this._valueClass);
        }
        arrayOfShort = new short[1];
        arrayOfShort[0] = _parseShortPrimitive(paramJsonParser, paramDeserializationContext);
      }
    }
    
    public short[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      short[] arrayOfShort2;
      if (!paramJsonParser.isExpectedStartArrayToken())
      {
        arrayOfShort2 = handleNonArray(paramJsonParser, paramDeserializationContext);
        return arrayOfShort2;
      }
      ArrayBuilders.ShortBuilder localShortBuilder = paramDeserializationContext.getArrayBuilders().getShortBuilder();
      short[] arrayOfShort1 = (short[])localShortBuilder.resetAndStart();
      int i = 0;
      for (;;)
      {
        try
        {
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
          {
            int j = _parseShortPrimitive(paramJsonParser, paramDeserializationContext);
            if (i < arrayOfShort1.length) {
              break label129;
            }
            arrayOfShort1 = (short[])localShortBuilder.appendCompletedChunk(arrayOfShort1, i);
            k = 0;
            i = k + 1;
            arrayOfShort1[k] = j;
            continue;
          }
          arrayOfShort2 = (short[])localShortBuilder.completeAndClearBuffer(arrayOfShort1, i);
        }
        catch (Exception localException)
        {
          throw JsonMappingException.wrapWithPath(localException, arrayOfShort1, i + localShortBuilder.bufferedSize());
        }
        break;
        label129:
        int k = i;
      }
    }
  }
  
  @JacksonStdImpl
  static final class ByteDeser
    extends PrimitiveArrayDeserializers<byte[]>
  {
    private static final long serialVersionUID = 1L;
    
    public ByteDeser()
    {
      super();
    }
    
    private final byte[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      byte[] arrayOfByte;
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0))
      {
        arrayOfByte = null;
        return arrayOfByte;
      }
      if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        throw paramDeserializationContext.mappingException(this._valueClass);
      }
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      if ((localJsonToken == JsonToken.VALUE_NUMBER_INT) || (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT)) {}
      for (int i = paramJsonParser.getByteValue();; i = 0)
      {
        arrayOfByte = new byte[1];
        arrayOfByte[0] = i;
        break;
        if (localJsonToken != JsonToken.VALUE_NULL) {
          throw paramDeserializationContext.mappingException(this._valueClass.getComponentType());
        }
      }
    }
    
    public byte[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
      byte[] arrayOfByte2;
      if (localJsonToken1 == JsonToken.VALUE_STRING) {
        arrayOfByte2 = paramJsonParser.getBinaryValue(paramDeserializationContext.getBase64Variant());
      }
      for (;;)
      {
        return arrayOfByte2;
        if (localJsonToken1 == JsonToken.VALUE_EMBEDDED_OBJECT)
        {
          Object localObject = paramJsonParser.getEmbeddedObject();
          if (localObject == null)
          {
            arrayOfByte2 = null;
            continue;
          }
          if ((localObject instanceof byte[]))
          {
            arrayOfByte2 = (byte[])localObject;
            continue;
          }
        }
        if (paramJsonParser.isExpectedStartArrayToken()) {
          break;
        }
        arrayOfByte2 = handleNonArray(paramJsonParser, paramDeserializationContext);
      }
      ArrayBuilders.ByteBuilder localByteBuilder = paramDeserializationContext.getArrayBuilders().getByteBuilder();
      byte[] arrayOfByte1 = (byte[])localByteBuilder.resetAndStart();
      int i = 0;
      for (;;)
      {
        try
        {
          JsonToken localJsonToken2 = paramJsonParser.nextToken();
          if (localJsonToken2 == JsonToken.END_ARRAY) {
            break label231;
          }
          if ((localJsonToken2 == JsonToken.VALUE_NUMBER_INT) || (localJsonToken2 == JsonToken.VALUE_NUMBER_FLOAT))
          {
            j = paramJsonParser.getByteValue();
            if (i < arrayOfByte1.length) {
              break label248;
            }
            arrayOfByte1 = (byte[])localByteBuilder.appendCompletedChunk(arrayOfByte1, i);
            k = 0;
            i = k + 1;
            arrayOfByte1[k] = j;
            continue;
          }
          if (localJsonToken2 == JsonToken.VALUE_NULL) {
            break label225;
          }
        }
        catch (Exception localException)
        {
          throw JsonMappingException.wrapWithPath(localException, arrayOfByte1, i + localByteBuilder.bufferedSize());
        }
        throw paramDeserializationContext.mappingException(this._valueClass.getComponentType());
        label225:
        int j = 0;
        continue;
        label231:
        arrayOfByte2 = (byte[])localByteBuilder.completeAndClearBuffer(arrayOfByte1, i);
        break;
        label248:
        int k = i;
      }
    }
  }
  
  @JacksonStdImpl
  static final class BooleanDeser
    extends PrimitiveArrayDeserializers<boolean[]>
  {
    private static final long serialVersionUID = 1L;
    
    public BooleanDeser()
    {
      super();
    }
    
    private final boolean[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      boolean[] arrayOfBoolean;
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        arrayOfBoolean = null;
      }
      for (;;)
      {
        return arrayOfBoolean;
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
          throw paramDeserializationContext.mappingException(this._valueClass);
        }
        arrayOfBoolean = new boolean[1];
        arrayOfBoolean[0] = _parseBooleanPrimitive(paramJsonParser, paramDeserializationContext);
      }
    }
    
    public boolean[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException, JsonProcessingException
    {
      boolean[] arrayOfBoolean2;
      if (!paramJsonParser.isExpectedStartArrayToken())
      {
        arrayOfBoolean2 = handleNonArray(paramJsonParser, paramDeserializationContext);
        return arrayOfBoolean2;
      }
      ArrayBuilders.BooleanBuilder localBooleanBuilder = paramDeserializationContext.getArrayBuilders().getBooleanBuilder();
      boolean[] arrayOfBoolean1 = (boolean[])localBooleanBuilder.resetAndStart();
      int i = 0;
      for (;;)
      {
        try
        {
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
          {
            boolean bool = _parseBooleanPrimitive(paramJsonParser, paramDeserializationContext);
            if (i < arrayOfBoolean1.length) {
              break label129;
            }
            arrayOfBoolean1 = (boolean[])localBooleanBuilder.appendCompletedChunk(arrayOfBoolean1, i);
            j = 0;
            i = j + 1;
            arrayOfBoolean1[j] = bool;
            continue;
          }
          arrayOfBoolean2 = (boolean[])localBooleanBuilder.completeAndClearBuffer(arrayOfBoolean1, i);
        }
        catch (Exception localException)
        {
          throw JsonMappingException.wrapWithPath(localException, arrayOfBoolean1, i + localBooleanBuilder.bufferedSize());
        }
        break;
        label129:
        int j = i;
      }
    }
  }
  
  @JacksonStdImpl
  static final class CharDeser
    extends PrimitiveArrayDeserializers<char[]>
  {
    private static final long serialVersionUID = 1L;
    
    public CharDeser()
    {
      super();
    }
    
    public char[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
      char[] arrayOfChar1;
      if (localJsonToken1 == JsonToken.VALUE_STRING)
      {
        char[] arrayOfChar2 = paramJsonParser.getTextCharacters();
        int i = paramJsonParser.getTextOffset();
        int j = paramJsonParser.getTextLength();
        arrayOfChar1 = new char[j];
        System.arraycopy(arrayOfChar2, i, arrayOfChar1, 0, j);
      }
      for (;;)
      {
        return arrayOfChar1;
        if (paramJsonParser.isExpectedStartArrayToken())
        {
          StringBuilder localStringBuilder = new StringBuilder(64);
          for (;;)
          {
            JsonToken localJsonToken2 = paramJsonParser.nextToken();
            if (localJsonToken2 == JsonToken.END_ARRAY) {
              break;
            }
            if (localJsonToken2 != JsonToken.VALUE_STRING) {
              throw paramDeserializationContext.mappingException(Character.TYPE);
            }
            String str = paramJsonParser.getText();
            if (str.length() != 1) {
              throw JsonMappingException.from(paramJsonParser, "Can not convert a JSON String of length " + str.length() + " into a char element of char array");
            }
            localStringBuilder.append(str.charAt(0));
          }
          arrayOfChar1 = localStringBuilder.toString().toCharArray();
        }
        else
        {
          if (localJsonToken1 != JsonToken.VALUE_EMBEDDED_OBJECT) {
            break;
          }
          Object localObject = paramJsonParser.getEmbeddedObject();
          if (localObject == null)
          {
            arrayOfChar1 = null;
          }
          else if ((localObject instanceof char[]))
          {
            arrayOfChar1 = (char[])localObject;
          }
          else if ((localObject instanceof String))
          {
            arrayOfChar1 = ((String)localObject).toCharArray();
          }
          else
          {
            if (!(localObject instanceof byte[])) {
              break;
            }
            arrayOfChar1 = Base64Variants.getDefaultVariant().encode((byte[])localObject, false).toCharArray();
          }
        }
      }
      throw paramDeserializationContext.mappingException(this._valueClass);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/PrimitiveArrayDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */