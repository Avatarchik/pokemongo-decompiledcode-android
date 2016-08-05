package com.nianticlabs.pokemongoplus;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.nianticlabs.pokemongoplus.ble.Peripheral;
import com.nianticlabs.pokemongoplus.ble.Service;
import com.nianticlabs.pokemongoplus.ble.SfidaConstant.BluetoothError;
import com.nianticlabs.pokemongoplus.ble.SfidaConstant.PeripheralState;
import com.nianticlabs.pokemongoplus.ble.callback.CompletionCallback;
import com.nianticlabs.pokemongoplus.ble.callback.ConnectCallback;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SfidaPeripheral
  extends Peripheral
{
  private static final String TAG = SfidaPeripheral.class.getSimpleName();
  private BluetoothAdapter bluetoothAdapter;
  private BluetoothDevice bluetoothDevice;
  private final BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback()
  {
    public void onCharacteristicChanged(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic)
    {
      Log.d(SfidaPeripheral.TAG, "onCharacteristicChanged");
      synchronized (SfidaPeripheral.this.serviceRef)
      {
        Iterator localIterator = SfidaPeripheral.this.serviceRef.iterator();
        if (localIterator.hasNext()) {
          ((SfidaService)localIterator.next()).onCharacteristicChanged(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattCharacteristic);
        }
      }
    }
    
    public void onCharacteristicRead(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic, int paramAnonymousInt)
    {
      Log.d(SfidaPeripheral.TAG, "onCharacteristicRead");
      synchronized (SfidaPeripheral.this.serviceRef)
      {
        Iterator localIterator = SfidaPeripheral.this.serviceRef.iterator();
        if (localIterator.hasNext()) {
          ((SfidaService)localIterator.next()).onCharacteristicRead(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattCharacteristic, paramAnonymousInt);
        }
      }
    }
    
    public void onCharacteristicWrite(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic, int paramAnonymousInt)
    {
      Log.d(SfidaPeripheral.TAG, "onCharacteristicWrite");
      synchronized (SfidaPeripheral.this.serviceRef)
      {
        Iterator localIterator = SfidaPeripheral.this.serviceRef.iterator();
        if (localIterator.hasNext()) {
          ((SfidaService)localIterator.next()).onCharacteristicWrite(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattCharacteristic, paramAnonymousInt);
        }
      }
    }
    
    public void onConnectionStateChange(BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      Log.d(SfidaPeripheral.TAG, "onConnectionStateChange");
      SfidaPeripheral.this.onConnectionStateChange(paramAnonymousBluetoothGatt, paramAnonymousInt1, paramAnonymousInt2);
    }
    
    public void onDescriptorWrite(BluetoothGatt paramAnonymousBluetoothGatt, BluetoothGattDescriptor paramAnonymousBluetoothGattDescriptor, int paramAnonymousInt)
    {
      Log.d(SfidaPeripheral.TAG, "onDescriptorWrite");
      synchronized (SfidaPeripheral.this.serviceRef)
      {
        Iterator localIterator = SfidaPeripheral.this.serviceRef.iterator();
        if (localIterator.hasNext()) {
          ((SfidaService)localIterator.next()).onDescriptorWrite(paramAnonymousBluetoothGatt, paramAnonymousBluetoothGattDescriptor, paramAnonymousInt);
        }
      }
    }
    
    public void onServicesDiscovered(BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt)
    {
      Log.d(SfidaPeripheral.TAG, "onServicesDiscovered");
      SfidaPeripheral.this.onServicesDiscovered(paramAnonymousBluetoothGatt, paramAnonymousInt);
    }
  };
  private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver()
  {
    private void onHandleBluetoothIntent(Intent paramAnonymousIntent)
    {
      String str = paramAnonymousIntent.getAction();
      if (str == null) {
        Log.d(SfidaPeripheral.TAG, "onReceived() action was null");
      }
      for (;;)
      {
        label18:
        return;
        int i = -1;
        switch (str.hashCode())
        {
        }
        for (;;)
        {
          switch (i)
          {
          case 0: 
          default: 
            Log.d(SfidaPeripheral.TAG, "onReceive() : " + str);
            break label18;
            if (str.equals("android.bluetooth.device.action.BOND_STATE_CHANGED"))
            {
              i = 0;
              continue;
              if (str.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
                i = 1;
              }
            }
            break;
          }
        }
        BluetoothDevice localBluetoothDevice = (BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        SfidaPeripheral.this.onPairingRequest(localBluetoothDevice);
      }
    }
    
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      onHandleBluetoothIntent(paramAnonymousIntent);
      Log.d(SfidaPeripheral.TAG, "onReceive() : " + paramAnonymousIntent.getAction());
    }
  };
  private ConnectCallback connectCallback;
  private Context context;
  private ConnectCallback disconnectCallback;
  private CompletionCallback discoverServicesCallback;
  private BluetoothGatt gatt;
  private long nativeHandle;
  private byte[] scanRecord;
  private final ArrayList<SfidaService> serviceRef = new ArrayList();
  private SfidaConstant.PeripheralState state;
  
  public SfidaPeripheral(Context paramContext, BluetoothDevice paramBluetoothDevice, byte[] paramArrayOfByte)
  {
    this.context = paramContext;
    this.bluetoothDevice = paramBluetoothDevice;
    this.state = SfidaConstant.PeripheralState.Disconnected;
    this.bluetoothAdapter = SfidaUtils.getBluetoothManager(paramContext).getAdapter();
    this.scanRecord = paramArrayOfByte;
  }
  
  private void bondingCanceled(BluetoothDevice paramBluetoothDevice)
  {
    Log.d(TAG, "bondingCanceled()");
    SfidaUtils.createBond(paramBluetoothDevice);
  }
  
  private byte[] byteArrayFromHexString(String paramString)
  {
    int i = paramString.length();
    byte[] arrayOfByte = new byte[i / 2];
    for (int j = 0; j < i; j += 2) {
      arrayOfByte[(j / 2)] = ((byte)((Character.digit(paramString.charAt(j), 16) << 4) + Character.digit(paramString.charAt(j + 1), 16)));
    }
    return arrayOfByte;
  }
  
  private void disconnectFromBonding(BluetoothDevice paramBluetoothDevice)
  {
    Log.d(TAG, "disconnectFromBonding()");
    unpairDevice();
  }
  
  private Boolean isBoundDevice(BluetoothDevice paramBluetoothDevice)
  {
    Set localSet = this.bluetoothAdapter.getBondedDevices();
    if ((localSet != null) && (localSet.size() != 0))
    {
      Iterator localIterator = localSet.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!((BluetoothDevice)localIterator.next()).getAddress().equals(paramBluetoothDevice.getAddress()));
    }
    for (Boolean localBoolean = Boolean.valueOf(true);; localBoolean = Boolean.valueOf(false)) {
      return localBoolean;
    }
  }
  
  private native void nativeConnectCallback(boolean paramBoolean, int paramInt);
  
  private native void nativeDisconnectCallback(boolean paramBoolean, int paramInt);
  
  private native void nativeDiscoverService(SfidaService paramSfidaService);
  
  private native void nativeDiscoverServicesCallback(boolean paramBoolean, int paramInt);
  
  private void onBondStateChanged(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
    int j = paramIntent.getIntExtra("android.bluetooth.device.extra.PREVIOUS_BOND_STATE", Integer.MIN_VALUE);
    Log.d(TAG, "[BLE] ACTION_BOND_STATE_CHANGED oldState : " + SfidaUtils.getBondStateName(j) + " → newState : " + SfidaUtils.getBondStateName(i));
    BluetoothDevice localBluetoothDevice = (BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
    if (localBluetoothDevice != null) {
      switch (i)
      {
      }
    }
    for (;;)
    {
      return;
      if (!tryCompleteConnect())
      {
        reconnnectFromBonding(localBluetoothDevice);
        continue;
        if (j == 12) {
          disconnectFromBonding(localBluetoothDevice);
        } else if (j == 11) {
          bondingCanceled(localBluetoothDevice);
        } else {
          Log.d(TAG, "Unhandled oldState : " + j);
        }
      }
    }
  }
  
  private void onPairingRequest(BluetoothDevice paramBluetoothDevice)
  {
    Log.d(TAG, "onPairingRequest()");
    try
    {
      Class localClass = paramBluetoothDevice.getClass();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Boolean.TYPE;
      Method localMethod = localClass.getMethod("setPairingConfirmation", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(true);
      localMethod.invoke(paramBluetoothDevice, arrayOfObject);
      paramBluetoothDevice.getClass().getMethod("cancelPairingUserInput", new Class[0]).invoke(paramBluetoothDevice, new Object[0]);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        localIllegalAccessException.printStackTrace();
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        localNoSuchMethodException.printStackTrace();
      }
    }
  }
  
  private void reconnnectFromBonding(BluetoothDevice paramBluetoothDevice)
  {
    Log.d(TAG, "reconnnectFromBonding()");
    retryConnect();
  }
  
  private void releaseServices() {}
  
  private void retryConnect()
  {
    String str = this.bluetoothDevice.getAddress();
    if ((this.bluetoothAdapter == null) || (str == null))
    {
      Log.w(TAG, "[BLE] BluetoothAdapter not initialized or unspecified address.");
      this.state = SfidaConstant.PeripheralState.Disconnected;
    }
    for (;;)
    {
      return;
      if ((str.equals(this.bluetoothDevice.getAddress())) && (this.gatt != null))
      {
        Log.d(TAG, "[BLE] Trying to use an existing bluetoothGatt for connection.");
        this.gatt.connect();
      }
      else
      {
        this.gatt = this.bluetoothDevice.connectGatt(this.context, false, this.bluetoothGattCallback);
        Log.d(TAG, "Trying to create a new connection.");
      }
    }
  }
  
  private void unpairDevice()
  {
    Log.d(TAG, "unpairDevice()");
    try
    {
      this.bluetoothDevice.getClass().getMethod("removeBond", (Class[])null).invoke(this.bluetoothDevice, (Object[])null);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(TAG, localException.getMessage());
      }
    }
  }
  
  public void closeBluetoothGatt()
  {
    if (this.gatt == null) {}
    for (;;)
    {
      return;
      this.gatt.close();
      this.gatt = null;
    }
  }
  
  public void connect()
  {
    connect(new ConnectCallback()
    {
      public void onConnectionStateChanged(boolean paramAnonymousBoolean, SfidaConstant.BluetoothError paramAnonymousBluetoothError)
      {
        SfidaPeripheral.this.nativeConnectCallback(paramAnonymousBoolean, paramAnonymousBluetoothError.getInt());
      }
    });
  }
  
  public void connect(ConnectCallback paramConnectCallback)
  {
    this.connectCallback = paramConnectCallback;
    this.state = SfidaConstant.PeripheralState.Connecting;
    retryConnect();
  }
  
  public void disconnect()
  {
    discoverServices(new CompletionCallback()
    {
      public void onCompletion(boolean paramAnonymousBoolean, SfidaConstant.BluetoothError paramAnonymousBluetoothError)
      {
        SfidaPeripheral.this.nativeDisconnectCallback(paramAnonymousBoolean, paramAnonymousBluetoothError.getInt());
      }
    });
  }
  
  public void disconnect(ConnectCallback paramConnectCallback)
  {
    this.disconnectCallback = paramConnectCallback;
    if ((this.bluetoothAdapter == null) || (this.gatt == null)) {
      Log.w(TAG, "[BLE] BluetoothAdapter not initialized");
    }
    for (;;)
    {
      return;
      this.gatt.disconnect();
    }
  }
  
  public void discoverServices()
  {
    discoverServices(new CompletionCallback()
    {
      public void onCompletion(boolean paramAnonymousBoolean, SfidaConstant.BluetoothError paramAnonymousBluetoothError)
      {
        Log.d(SfidaPeripheral.TAG, "discoverServices success:" + paramAnonymousBoolean + " error:" + paramAnonymousBluetoothError);
        SfidaPeripheral.this.nativeDiscoverServicesCallback(paramAnonymousBoolean, paramAnonymousBluetoothError.getInt());
      }
    });
  }
  
  public void discoverServices(CompletionCallback paramCompletionCallback)
  {
    Log.d(TAG, "discoverServices(" + paramCompletionCallback.toString() + ")");
    try
    {
      Thread.sleep(300L);
      if (this.gatt != null)
      {
        this.discoverServicesCallback = paramCompletionCallback;
        boolean bool = this.gatt.discoverServices();
        Log.d(TAG, "discoverSerivice:" + bool);
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
        continue;
        Log.e(TAG, "gatt is null");
        paramCompletionCallback.onCompletion(false, SfidaConstant.BluetoothError.NotConnected);
      }
    }
  }
  
  public long getAdvertisingServiceDataLongValue(String paramString)
  {
    for (;;)
    {
      int j;
      int m;
      long l;
      try
      {
        byte[] arrayOfByte = byteArrayFromHexString(paramString);
        int i = arrayOfByte.length;
        j = 0;
        if (j < this.scanRecord.length - i)
        {
          int k = 1;
          m = 0;
          if (m < i)
          {
            if (this.scanRecord[(j + m)] == arrayOfByte[(i - 1 - m)]) {
              break label105;
            }
            k = 0;
          }
          if (k == 0) {
            break label111;
          }
          l = this.scanRecord[(j + i)];
        }
        else
        {
          l = 0L;
        }
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
      return l;
      label105:
      m++;
      continue;
      label111:
      j++;
    }
  }
  
  public String getIdentifier()
  {
    return this.bluetoothDevice.getAddress();
  }
  
  public String getName()
  {
    return this.bluetoothDevice.getName();
  }
  
  public Service getService(int paramInt)
  {
    if (paramInt > -1 + getServiceCount()) {}
    for (Service localService = null;; localService = (Service)this.serviceRef.get(paramInt)) {
      return localService;
    }
  }
  
  public Service getService(String paramString)
  {
    int j;
    Service localService;
    if (paramString != null)
    {
      int i = getServiceCount();
      j = 0;
      if (j < i)
      {
        localService = getService(j);
        if (!paramString.equals(localService.getUuid())) {}
      }
    }
    for (;;)
    {
      return localService;
      j++;
      break;
      localService = null;
    }
  }
  
  public int getServiceCount()
  {
    return this.serviceRef.size();
  }
  
  public SfidaConstant.PeripheralState getState()
  {
    return this.state;
  }
  
  public int getStateInt()
  {
    return getState().getInt();
  }
  
  public void onConnectionStateChange(BluetoothGatt paramBluetoothGatt, int paramInt1, int paramInt2)
  {
    boolean bool = false;
    switch (paramInt2)
    {
    case 1: 
    default: 
      Log.e(TAG, "onConnectionStateChange() UnhandledState status : " + paramInt1 + " " + "newState : " + paramInt2);
    }
    for (;;)
    {
      return;
      Log.d(TAG, "Connected with GATT server.");
      if (paramInt1 == 0)
      {
        this.state = SfidaConstant.PeripheralState.Connected;
        tryCompleteConnect();
      }
      else if (this.connectCallback != null)
      {
        this.connectCallback.onConnectionStateChanged(false, SfidaConstant.BluetoothError.Unknown);
        continue;
        Log.d(TAG, "Disconnected from GATT server., state = " + this.state.toString());
        SfidaUtils.refreshDeviceCache(paramBluetoothGatt);
        closeBluetoothGatt();
        releaseServices();
        if ((this.state == SfidaConstant.PeripheralState.Connecting) || (this.state == SfidaConstant.PeripheralState.Connected))
        {
          this.state = SfidaConstant.PeripheralState.Connecting;
          retryConnect();
          Log.d(TAG, "Reconnecting., state now " + this.state.toString());
        }
        else
        {
          this.state = SfidaConstant.PeripheralState.Disconnected;
          if (this.disconnectCallback != null)
          {
            ConnectCallback localConnectCallback = this.disconnectCallback;
            if (paramInt1 == 0) {
              bool = true;
            }
            localConnectCallback.onConnectionStateChanged(bool, SfidaConstant.BluetoothError.Unknown);
          }
          Log.d(TAG, "Disconnected., state now " + this.state.toString());
        }
      }
    }
  }
  
  public void onCreate()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
    localIntentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
    this.context.registerReceiver(this.bluetoothReceiver, localIntentFilter);
  }
  
  public void onDestroy()
  {
    this.context.unregisterReceiver(this.bluetoothReceiver);
  }
  
  public void onServicesDiscovered(BluetoothGatt paramBluetoothGatt, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      this.state = SfidaConstant.PeripheralState.Disconnected;
      this.discoverServicesCallback.onCompletion(false, SfidaConstant.BluetoothError.Unknown);
      Log.e(TAG, "[BLE] onServicesDiscovered received error: " + paramInt);
      return;
    }
    List localList = paramBluetoothGatt.getServices();
    String str;
    Object[] arrayOfObject;
    synchronized (this.serviceRef)
    {
      Log.e(TAG, "onServicesDiscovered thread:" + Thread.currentThread().getId());
      this.serviceRef.clear();
      Iterator localIterator = localList.iterator();
      if (localIterator.hasNext())
      {
        SfidaService localSfidaService = new SfidaService((BluetoothGattService)localIterator.next(), paramBluetoothGatt);
        nativeDiscoverService(localSfidaService);
        this.serviceRef.add(localSfidaService);
      }
    }
  }
  
  public void setScanRecord(byte[] paramArrayOfByte)
  {
    try
    {
      this.scanRecord = paramArrayOfByte;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  boolean tryCompleteConnect()
  {
    boolean bool = true;
    if (this.connectCallback != null)
    {
      Log.d(TAG, "calling onConnectionStateChanged");
      this.connectCallback.onConnectionStateChanged(bool, SfidaConstant.BluetoothError.Unknown);
    }
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/SfidaPeripheral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */