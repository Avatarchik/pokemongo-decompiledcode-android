package com.google.gson;

public final class JsonNull
  extends JsonElement
{
  public static final JsonNull INSTANCE = new JsonNull();
  
  JsonNull deepCopy()
  {
    return INSTANCE;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((this == paramObject) || ((paramObject instanceof JsonNull))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return JsonNull.class.hashCode();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/JsonNull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */