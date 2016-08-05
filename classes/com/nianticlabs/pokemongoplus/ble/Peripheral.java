package com.nianticlabs.pokemongoplus.ble;

import com.nianticlabs.pokemongoplus.ble.callback.CompletionCallback;
import com.nianticlabs.pokemongoplus.ble.callback.ConnectCallback;

public abstract class Peripheral
{
  public abstract void connect(ConnectCallback paramConnectCallback);
  
  public abstract void disconnect(ConnectCallback paramConnectCallback);
  
  public abstract void discoverServices(CompletionCallback paramCompletionCallback);
  
  public abstract long getAdvertisingServiceDataLongValue(String paramString);
  
  public abstract String getIdentifier();
  
  public abstract String getName();
  
  public abstract Service getService(int paramInt);
  
  public abstract int getServiceCount();
  
  public abstract SfidaConstant.PeripheralState getState();
  
  public abstract void setScanRecord(byte[] paramArrayOfByte);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/ble/Peripheral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */