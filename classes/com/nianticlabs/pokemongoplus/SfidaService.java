package com.nianticlabs.pokemongoplus;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.nianticlabs.pokemongoplus.ble.Service;
import com.nianticlabs.pokemongoplus.ble.callback.CompletionCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class SfidaService
  extends Service
{
  private ArrayList<SfidaCharacteristic> characteristicRef = new ArrayList();
  private long nativeHandle;
  private BluetoothGattService service;
  
  public SfidaService(BluetoothGattService paramBluetoothGattService, BluetoothGatt paramBluetoothGatt)
  {
    this.service = paramBluetoothGattService;
    Iterator localIterator = paramBluetoothGattService.getCharacteristics().iterator();
    while (localIterator.hasNext())
    {
      SfidaCharacteristic localSfidaCharacteristic = new SfidaCharacteristic((BluetoothGattCharacteristic)localIterator.next(), paramBluetoothGatt);
      this.characteristicRef.add(localSfidaCharacteristic);
    }
  }
  
  public void discoverCharacteristics(CompletionCallback paramCompletionCallback) {}
  
  /* Error */
  /**
   * @deprecated
   */
  public SfidaCharacteristic getCharacteristic(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 64	com/nianticlabs/pokemongoplus/SfidaService:getCharacteristicCount	()I
    //   6: istore_3
    //   7: iload_1
    //   8: iload_3
    //   9: iconst_1
    //   10: isub
    //   11: if_icmple +11 -> 22
    //   14: aconst_null
    //   15: astore 4
    //   17: aload_0
    //   18: monitorexit
    //   19: aload 4
    //   21: areturn
    //   22: aload_0
    //   23: getfield 21	com/nianticlabs/pokemongoplus/SfidaService:characteristicRef	Ljava/util/ArrayList;
    //   26: iload_1
    //   27: invokevirtual 68	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   30: checkcast 43	com/nianticlabs/pokemongoplus/SfidaCharacteristic
    //   33: astore 4
    //   35: goto -18 -> 17
    //   38: astore_2
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_2
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	SfidaService
    //   0	43	1	paramInt	int
    //   38	4	2	localObject	Object
    //   6	5	3	i	int
    //   15	19	4	localSfidaCharacteristic	SfidaCharacteristic
    // Exception table:
    //   from	to	target	type
    //   2	7	38	finally
    //   22	35	38	finally
  }
  
  public SfidaCharacteristic getCharacteristic(String paramString)
  {
    int i = getCharacteristicCount();
    int j = 0;
    SfidaCharacteristic localSfidaCharacteristic;
    if (j < i)
    {
      localSfidaCharacteristic = (SfidaCharacteristic)this.characteristicRef.get(j);
      if (!localSfidaCharacteristic.getUuid().equalsIgnoreCase(paramString)) {}
    }
    for (;;)
    {
      return localSfidaCharacteristic;
      j++;
      break;
      localSfidaCharacteristic = null;
    }
  }
  
  /**
   * @deprecated
   */
  public int getCharacteristicCount()
  {
    try
    {
      int i = this.characteristicRef.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getUuid()
  {
    return this.service.getUuid().toString();
  }
  
  public boolean isPrimary()
  {
    return false;
  }
  
  /**
   * @deprecated
   */
  public void onCharacteristicChanged(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic)
  {
    try
    {
      String str = paramBluetoothGattCharacteristic.getUuid().toString();
      Iterator localIterator = this.characteristicRef.iterator();
      while (localIterator.hasNext())
      {
        SfidaCharacteristic localSfidaCharacteristic = (SfidaCharacteristic)localIterator.next();
        if (localSfidaCharacteristic.getUuid().equals(str)) {
          localSfidaCharacteristic.onCharacteristicChanged();
        }
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void onCharacteristicRead(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
  {
    try
    {
      String str = paramBluetoothGattCharacteristic.getUuid().toString();
      Iterator localIterator = this.characteristicRef.iterator();
      while (localIterator.hasNext())
      {
        SfidaCharacteristic localSfidaCharacteristic = (SfidaCharacteristic)localIterator.next();
        if (localSfidaCharacteristic.getUuid().equals(str)) {
          localSfidaCharacteristic.onCharacteristicRead(paramInt);
        }
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void onCharacteristicWrite(BluetoothGatt paramBluetoothGatt, BluetoothGattCharacteristic paramBluetoothGattCharacteristic, int paramInt)
  {
    try
    {
      String str = paramBluetoothGattCharacteristic.getUuid().toString();
      Iterator localIterator = this.characteristicRef.iterator();
      while (localIterator.hasNext())
      {
        SfidaCharacteristic localSfidaCharacteristic = (SfidaCharacteristic)localIterator.next();
        if (localSfidaCharacteristic.getUuid().equals(str)) {
          localSfidaCharacteristic.onCharacteristicWrite(paramInt);
        }
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void onDescriptorWrite(BluetoothGatt paramBluetoothGatt, BluetoothGattDescriptor paramBluetoothGattDescriptor, int paramInt)
  {
    try
    {
      String str = paramBluetoothGattDescriptor.getCharacteristic().getUuid().toString();
      Iterator localIterator = this.characteristicRef.iterator();
      while (localIterator.hasNext())
      {
        SfidaCharacteristic localSfidaCharacteristic = (SfidaCharacteristic)localIterator.next();
        if (localSfidaCharacteristic.getUuid().equals(str)) {
          localSfidaCharacteristic.onDescriptorWrite(paramBluetoothGattDescriptor, paramInt);
        }
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/SfidaService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */