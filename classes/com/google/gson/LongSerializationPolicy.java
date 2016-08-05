package com.google.gson;

public enum LongSerializationPolicy
{
  static
  {
    LongSerializationPolicy[] arrayOfLongSerializationPolicy = new LongSerializationPolicy[2];
    arrayOfLongSerializationPolicy[0] = DEFAULT;
    arrayOfLongSerializationPolicy[1] = STRING;
    $VALUES = arrayOfLongSerializationPolicy;
  }
  
  private LongSerializationPolicy() {}
  
  public abstract JsonElement serialize(Long paramLong);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/LongSerializationPolicy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */