package com.fasterxml.jackson.core;

public abstract interface FormatFeature
{
  public abstract boolean enabledByDefault();
  
  public abstract boolean enabledIn(int paramInt);
  
  public abstract int getMask();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/FormatFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */