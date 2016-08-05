package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber
  extends Number
{
  private final String value;
  
  public LazilyParsedNumber(String paramString)
  {
    this.value = paramString;
  }
  
  private Object writeReplace()
    throws ObjectStreamException
  {
    return new BigDecimal(this.value);
  }
  
  public double doubleValue()
  {
    return Double.parseDouble(this.value);
  }
  
  public float floatValue()
  {
    return Float.parseFloat(this.value);
  }
  
  public int intValue()
  {
    try
    {
      int j = Integer.parseInt(this.value);
      i = j;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      for (;;)
      {
        try
        {
          long l = Long.parseLong(this.value);
          i = (int)l;
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          int i = new BigDecimal(this.value).intValue();
        }
      }
    }
    return i;
  }
  
  public long longValue()
  {
    try
    {
      long l2 = Long.parseLong(this.value);
      l1 = l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        long l1 = new BigDecimal(this.value).longValue();
      }
    }
    return l1;
  }
  
  public String toString()
  {
    return this.value;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/internal/LazilyParsedNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */