package com.upsight.android.internal.persistence;

public final class Storable
{
  String id;
  String type;
  String value;
  
  Storable(String paramString1, String paramString2, String paramString3)
  {
    this.id = paramString1;
    this.type = paramString2;
    this.value = paramString3;
  }
  
  public static Storable create(String paramString1, String paramString2, String paramString3)
  {
    return new Storable(paramString1, paramString2, paramString3);
  }
  
  public String getID()
  {
    return this.id;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public String getValue()
  {
    return this.value;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/Storable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */