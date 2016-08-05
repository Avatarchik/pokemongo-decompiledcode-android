package com.nianticlabs.pokemongoplus.ble;

import com.nianticlabs.pokemongoplus.ble.callback.CompletionCallback;

public abstract class Characteristic
{
  public abstract void enableNotify(CompletionCallback paramCompletionCallback);
  
  public abstract long getLongValue();
  
  public abstract String getUuid();
  
  public abstract byte[] getValue();
  
  public abstract void readValue(CompletionCallback paramCompletionCallback);
  
  public abstract void writeByteArray(byte[] paramArrayOfByte, CompletionCallback paramCompletionCallback);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/ble/Characteristic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */