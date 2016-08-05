package com.nianticlabs.pokemongoplus.ble;

import com.nianticlabs.pokemongoplus.ble.callback.CentralStateCallback;
import com.nianticlabs.pokemongoplus.ble.callback.ScanCallback;

public abstract class BluetoothDriver
{
  private SfidaConstant.CentralState currentState = SfidaConstant.CentralState.Unknown;
  
  public abstract boolean IsScanning();
  
  public abstract int start(CentralStateCallback paramCentralStateCallback);
  
  public abstract void startScanning(String paramString, ScanCallback paramScanCallback);
  
  public abstract void stop(int paramInt);
  
  public abstract void stopScanning(String paramString);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/ble/BluetoothDriver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */