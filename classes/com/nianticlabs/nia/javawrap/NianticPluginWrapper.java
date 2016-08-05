package com.nianticlabs.nia.javawrap;

public class NianticPluginWrapper
{
  private long nativeHandle;
  
  private native void nativeDispose();
  
  private native long nativeGetApi();
  
  private native void nativeInitialize();
  
  public void dispose()
  {
    nativeDispose();
  }
  
  public long getApi()
  {
    return nativeGetApi();
  }
  
  public void initialize()
  {
    nativeInitialize();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/javawrap/NianticPluginWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */