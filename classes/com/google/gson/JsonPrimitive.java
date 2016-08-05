package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class JsonPrimitive
  extends JsonElement
{
  private static final Class<?>[] PRIMITIVE_TYPES;
  private Object value;
  
  static
  {
    Class[] arrayOfClass = new Class[16];
    arrayOfClass[0] = Integer.TYPE;
    arrayOfClass[1] = Long.TYPE;
    arrayOfClass[2] = Short.TYPE;
    arrayOfClass[3] = Float.TYPE;
    arrayOfClass[4] = Double.TYPE;
    arrayOfClass[5] = Byte.TYPE;
    arrayOfClass[6] = Boolean.TYPE;
    arrayOfClass[7] = Character.TYPE;
    arrayOfClass[8] = Integer.class;
    arrayOfClass[9] = Long.class;
    arrayOfClass[10] = Short.class;
    arrayOfClass[11] = Float.class;
    arrayOfClass[12] = Double.class;
    arrayOfClass[13] = Byte.class;
    arrayOfClass[14] = Boolean.class;
    arrayOfClass[15] = Character.class;
    PRIMITIVE_TYPES = arrayOfClass;
  }
  
  public JsonPrimitive(Boolean paramBoolean)
  {
    setValue(paramBoolean);
  }
  
  public JsonPrimitive(Character paramCharacter)
  {
    setValue(paramCharacter);
  }
  
  public JsonPrimitive(Number paramNumber)
  {
    setValue(paramNumber);
  }
  
  JsonPrimitive(Object paramObject)
  {
    setValue(paramObject);
  }
  
  public JsonPrimitive(String paramString)
  {
    setValue(paramString);
  }
  
  private static boolean isIntegral(JsonPrimitive paramJsonPrimitive)
  {
    boolean bool = false;
    if ((paramJsonPrimitive.value instanceof Number))
    {
      Number localNumber = (Number)paramJsonPrimitive.value;
      if (((localNumber instanceof BigInteger)) || ((localNumber instanceof Long)) || ((localNumber instanceof Integer)) || ((localNumber instanceof Short)) || ((localNumber instanceof Byte))) {
        bool = true;
      }
    }
    return bool;
  }
  
  private static boolean isPrimitiveOrString(Object paramObject)
  {
    boolean bool = true;
    if ((paramObject instanceof String)) {}
    for (;;)
    {
      return bool;
      Class localClass = paramObject.getClass();
      Class[] arrayOfClass = PRIMITIVE_TYPES;
      int i = arrayOfClass.length;
      for (int j = 0;; j++)
      {
        if (j >= i) {
          break label51;
        }
        if (arrayOfClass[j].isAssignableFrom(localClass)) {
          break;
        }
      }
      label51:
      bool = false;
    }
  }
  
  JsonPrimitive deepCopy()
  {
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (this == paramObject) {}
    for (;;)
    {
      return bool1;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
      {
        bool1 = false;
      }
      else
      {
        JsonPrimitive localJsonPrimitive = (JsonPrimitive)paramObject;
        if (this.value == null)
        {
          if (localJsonPrimitive.value != null) {
            bool1 = false;
          }
        }
        else if ((isIntegral(this)) && (isIntegral(localJsonPrimitive)))
        {
          if (getAsNumber().longValue() != localJsonPrimitive.getAsNumber().longValue()) {
            bool1 = false;
          }
        }
        else if (((this.value instanceof Number)) && ((localJsonPrimitive.value instanceof Number)))
        {
          double d1 = getAsNumber().doubleValue();
          double d2 = localJsonPrimitive.getAsNumber().doubleValue();
          if ((d1 == d2) || ((Double.isNaN(d1)) && (Double.isNaN(d2)))) {
            bool2 = bool1;
          }
          bool1 = bool2;
        }
        else
        {
          bool1 = this.value.equals(localJsonPrimitive.value);
        }
      }
    }
  }
  
  public BigDecimal getAsBigDecimal()
  {
    if ((this.value instanceof BigDecimal)) {}
    for (BigDecimal localBigDecimal = (BigDecimal)this.value;; localBigDecimal = new BigDecimal(this.value.toString())) {
      return localBigDecimal;
    }
  }
  
  public BigInteger getAsBigInteger()
  {
    if ((this.value instanceof BigInteger)) {}
    for (BigInteger localBigInteger = (BigInteger)this.value;; localBigInteger = new BigInteger(this.value.toString())) {
      return localBigInteger;
    }
  }
  
  public boolean getAsBoolean()
  {
    if (isBoolean()) {}
    for (boolean bool = getAsBooleanWrapper().booleanValue();; bool = Boolean.parseBoolean(getAsString())) {
      return bool;
    }
  }
  
  Boolean getAsBooleanWrapper()
  {
    return (Boolean)this.value;
  }
  
  public byte getAsByte()
  {
    if (isNumber()) {}
    for (byte b = getAsNumber().byteValue();; b = Byte.parseByte(getAsString())) {
      return b;
    }
  }
  
  public char getAsCharacter()
  {
    return getAsString().charAt(0);
  }
  
  public double getAsDouble()
  {
    if (isNumber()) {}
    for (double d = getAsNumber().doubleValue();; d = Double.parseDouble(getAsString())) {
      return d;
    }
  }
  
  public float getAsFloat()
  {
    if (isNumber()) {}
    for (float f = getAsNumber().floatValue();; f = Float.parseFloat(getAsString())) {
      return f;
    }
  }
  
  public int getAsInt()
  {
    if (isNumber()) {}
    for (int i = getAsNumber().intValue();; i = Integer.parseInt(getAsString())) {
      return i;
    }
  }
  
  public long getAsLong()
  {
    if (isNumber()) {}
    for (long l = getAsNumber().longValue();; l = Long.parseLong(getAsString())) {
      return l;
    }
  }
  
  public Number getAsNumber()
  {
    if ((this.value instanceof String)) {}
    for (Object localObject = new LazilyParsedNumber((String)this.value);; localObject = (Number)this.value) {
      return (Number)localObject;
    }
  }
  
  public short getAsShort()
  {
    if (isNumber()) {}
    for (short s = getAsNumber().shortValue();; s = Short.parseShort(getAsString())) {
      return s;
    }
  }
  
  public String getAsString()
  {
    String str;
    if (isNumber()) {
      str = getAsNumber().toString();
    }
    for (;;)
    {
      return str;
      if (isBoolean()) {
        str = getAsBooleanWrapper().toString();
      } else {
        str = (String)this.value;
      }
    }
  }
  
  public int hashCode()
  {
    int i;
    if (this.value == null) {
      i = 31;
    }
    for (;;)
    {
      return i;
      if (isIntegral(this))
      {
        long l2 = getAsNumber().longValue();
        i = (int)(l2 ^ l2 >>> 32);
      }
      else if ((this.value instanceof Number))
      {
        long l1 = Double.doubleToLongBits(getAsNumber().doubleValue());
        i = (int)(l1 ^ l1 >>> 32);
      }
      else
      {
        i = this.value.hashCode();
      }
    }
  }
  
  public boolean isBoolean()
  {
    return this.value instanceof Boolean;
  }
  
  public boolean isNumber()
  {
    return this.value instanceof Number;
  }
  
  public boolean isString()
  {
    return this.value instanceof String;
  }
  
  void setValue(Object paramObject)
  {
    if ((paramObject instanceof Character))
    {
      this.value = String.valueOf(((Character)paramObject).charValue());
      return;
    }
    if (((paramObject instanceof Number)) || (isPrimitiveOrString(paramObject))) {}
    for (boolean bool = true;; bool = false)
    {
      .Gson.Preconditions.checkArgument(bool);
      this.value = paramObject;
      break;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/JsonPrimitive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */