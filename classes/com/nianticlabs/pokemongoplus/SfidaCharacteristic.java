package com.nianticlabs.pokemongoplus;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.AsyncTask;
import android.util.Log;
import com.nianticlabs.pokemongoplus.ble.Characteristic;
import com.nianticlabs.pokemongoplus.ble.SfidaConstant;
import com.nianticlabs.pokemongoplus.ble.SfidaConstant.BluetoothError;
import com.nianticlabs.pokemongoplus.ble.callback.CompletionCallback;
import com.nianticlabs.pokemongoplus.ble.callback.ValueChangeCallback;
import java.util.ArrayDeque;
import java.util.UUID;

public class SfidaCharacteristic
  extends Characteristic
{
  private static final String TAG = SfidaCharacteristic.class.getSimpleName();
  private BluetoothGattCharacteristic characteristic;
  private BluetoothGatt gatt;
  private long nativeHandle;
  private CompletionCallback onEnableNotifyCallback;
  private CompletionCallback onReadCallback;
  private ValueChangeCallback onValueChangedCallback;
  private CompletionCallback onWriteCallback;
  private volatile ArrayDeque<byte[]> queue = new ArrayDeque();
  
  public SfidaCharacteristic(BluetoothGattCharacteristic paramBluetoothGattCharacteristic, BluetoothGatt paramBluetoothGatt)
  {
    this.gatt = paramBluetoothGatt;
    this.characteristic = paramBluetoothGattCharacteristic;
  }
  
  private native void nativeEnableNotifyCallback(boolean paramBoolean, int paramInt);
  
  private native void nativeReadCompleteCallback(boolean paramBoolean, int paramInt);
  
  private native void nativeSaveValueChangedCallback(byte[] paramArrayOfByte);
  
  private native void nativeValueChangedCallback(boolean paramBoolean1, boolean paramBoolean2, int paramInt);
  
  private native void nativeWriteCompleteCallback(boolean paramBoolean, int paramInt);
  
  public void enableNotify()
  {
    enableNotify(new CompletionCallback()
    {
      public void onCompletion(boolean paramAnonymousBoolean, SfidaConstant.BluetoothError paramAnonymousBluetoothError)
      {
        String str = SfidaCharacteristic.TAG;
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = Boolean.valueOf(paramAnonymousBoolean);
        arrayOfObject[1] = Integer.valueOf(paramAnonymousBluetoothError.getInt());
        arrayOfObject[2] = paramAnonymousBluetoothError.toString();
        arrayOfObject[3] = SfidaCharacteristic.this.getUuid();
        Log.d(str, String.format("enableNotify callback success: %b error[%d]:%s UUID:%s", arrayOfObject));
        SfidaCharacteristic.this.nativeEnableNotifyCallback(paramAnonymousBoolean, paramAnonymousBluetoothError.getInt());
      }
    });
  }
  
  public void enableNotify(CompletionCallback paramCompletionCallback)
  {
    this.onEnableNotifyCallback = paramCompletionCallback;
    BluetoothGattDescriptor localBluetoothGattDescriptor = this.characteristic.getDescriptor(SfidaConstant.UUID_CLIENT_CHARACTERISTIC_CONFIG);
    String str1 = TAG;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = getUuid();
    arrayOfObject1[1] = localBluetoothGattDescriptor;
    Log.d(str1, String.format("Config characteristic:%s descriptor:%s", arrayOfObject1));
    if (localBluetoothGattDescriptor != null)
    {
      localBluetoothGattDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
      boolean bool = this.gatt.writeDescriptor(localBluetoothGattDescriptor);
      String str2 = TAG;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Boolean.valueOf(bool);
      Log.d(str2, String.format("Write description success:%b", arrayOfObject2));
      if (!bool) {
        this.onEnableNotifyCallback.onCompletion(false, SfidaConstant.BluetoothError.Unknown);
      }
    }
  }
  
  public long getLongValue()
  {
    return 0L;
  }
  
  public String getUuid()
  {
    return this.characteristic.getUuid().toString();
  }
  
  public byte[] getValue()
  {
    return (byte[])this.queue.pollFirst();
  }
  
  public void notifyValueChanged()
  {
    this.onValueChangedCallback = new ValueChangeCallback()
    {
      public void OnValueChange(boolean paramAnonymousBoolean1, boolean paramAnonymousBoolean2, SfidaConstant.BluetoothError paramAnonymousBluetoothError)
      {
        SfidaCharacteristic.this.nativeValueChangedCallback(paramAnonymousBoolean1, paramAnonymousBoolean2, paramAnonymousBluetoothError.getInt());
      }
    };
    this.gatt.setCharacteristicNotification(this.characteristic, true);
  }
  
  public void onCharacteristicChanged()
  {
    AsyncTask.execute(new Runnable()
    {
      public void run()
      {
        String str = SfidaCharacteristic.TAG;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = SfidaCharacteristic.this.characteristic.getUuid().toString();
        Log.d(str, String.format("onCharacteristicChanged: %s", arrayOfObject));
        byte[] arrayOfByte = SfidaCharacteristic.this.characteristic.getValue();
        SfidaCharacteristic.this.nativeSaveValueChangedCallback(arrayOfByte);
        if (SfidaCharacteristic.this.onValueChangedCallback != null)
        {
          SfidaCharacteristic.this.queue.add(arrayOfByte);
          SfidaCharacteristic.this.onValueChangedCallback.OnValueChange(true, true, SfidaConstant.BluetoothError.Unknown);
        }
      }
    });
  }
  
  public void onCharacteristicRead(final int paramInt)
  {
    AsyncTask.execute(new Runnable()
    {
      public void run()
      {
        if (paramInt == 0)
        {
          String str = SfidaCharacteristic.TAG;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = SfidaCharacteristic.this.characteristic.getUuid().toString();
          Log.d(str, String.format("onCharacteristicRead: %s", arrayOfObject));
          SfidaCharacteristic.this.nativeSaveValueChangedCallback(SfidaCharacteristic.this.characteristic.getValue());
          SfidaCharacteristic.this.onReadCallback.onCompletion(true, SfidaConstant.BluetoothError.Unknown);
        }
        for (;;)
        {
          return;
          SfidaCharacteristic.this.onReadCallback.onCompletion(false, SfidaConstant.BluetoothError.Unknown);
        }
      }
    });
  }
  
  public void onCharacteristicWrite(final int paramInt)
  {
    AsyncTask.execute(new Runnable()
    {
      public void run()
      {
        if (SfidaCharacteristic.this.onWriteCallback != null)
        {
          if (paramInt != 0) {
            break label34;
          }
          SfidaCharacteristic.this.onWriteCallback.onCompletion(true, SfidaConstant.BluetoothError.Unknown);
        }
        for (;;)
        {
          return;
          label34:
          SfidaCharacteristic.this.onWriteCallback.onCompletion(false, SfidaConstant.BluetoothError.Unknown);
        }
      }
    });
  }
  
  public void onDescriptorWrite(BluetoothGattDescriptor paramBluetoothGattDescriptor, final int paramInt)
  {
    String str = TAG;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    Log.d(str, String.format("onDescriptorWrite status:%d", arrayOfObject));
    AsyncTask.execute(new Runnable()
    {
      public void run()
      {
        if (SfidaCharacteristic.this.onEnableNotifyCallback != null)
        {
          if (paramInt != 0) {
            break label34;
          }
          SfidaCharacteristic.this.onEnableNotifyCallback.onCompletion(true, SfidaConstant.BluetoothError.Unknown);
        }
        for (;;)
        {
          return;
          label34:
          SfidaCharacteristic.this.onEnableNotifyCallback.onCompletion(false, SfidaConstant.BluetoothError.Unknown);
        }
      }
    });
  }
  
  public void readValue()
  {
    readValue(new CompletionCallback()
    {
      public void onCompletion(boolean paramAnonymousBoolean, SfidaConstant.BluetoothError paramAnonymousBluetoothError)
      {
        SfidaCharacteristic.this.nativeReadCompleteCallback(paramAnonymousBoolean, paramAnonymousBluetoothError.getInt());
      }
    });
  }
  
  public void readValue(CompletionCallback paramCompletionCallback)
  {
    this.onReadCallback = paramCompletionCallback;
    if (!this.gatt.readCharacteristic(this.characteristic)) {
      this.onReadCallback.onCompletion(false, SfidaConstant.BluetoothError.Unknown);
    }
  }
  
  public void writeByteArray(byte[] paramArrayOfByte)
  {
    writeByteArray(paramArrayOfByte, new CompletionCallback()
    {
      public void onCompletion(boolean paramAnonymousBoolean, SfidaConstant.BluetoothError paramAnonymousBluetoothError)
      {
        SfidaCharacteristic.this.nativeWriteCompleteCallback(paramAnonymousBoolean, paramAnonymousBluetoothError.getInt());
      }
    });
  }
  
  public void writeByteArray(byte[] paramArrayOfByte, CompletionCallback paramCompletionCallback)
  {
    this.onWriteCallback = paramCompletionCallback;
    this.characteristic.setValue(paramArrayOfByte);
    if (!this.gatt.writeCharacteristic(this.characteristic))
    {
      this.onWriteCallback.onCompletion(false, SfidaConstant.BluetoothError.Unknown);
      this.onWriteCallback = null;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/SfidaCharacteristic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */