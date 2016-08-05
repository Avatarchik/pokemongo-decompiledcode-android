package com.nianticlabs.pokemongoplus.ble.callback;

import com.nianticlabs.pokemongoplus.ble.SfidaConstant.BluetoothError;

public abstract interface ValueChangeCallback
{
  public abstract void OnValueChange(boolean paramBoolean1, boolean paramBoolean2, SfidaConstant.BluetoothError paramBluetoothError);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/ble/callback/ValueChangeCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */