package com.nianticlabs.pokemongoplus.ble;

import com.nianticlabs.pokemongoplus.ble.callback.CompletionCallback;

public abstract class Service
{
  public abstract void discoverCharacteristics(CompletionCallback paramCompletionCallback);
  
  public abstract int getCharacteristicCount();
  
  public abstract String getUuid();
  
  public abstract boolean isPrimary();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/ble/Service.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */