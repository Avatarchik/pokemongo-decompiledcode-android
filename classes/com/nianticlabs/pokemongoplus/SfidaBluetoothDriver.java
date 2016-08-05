package com.nianticlabs.pokemongoplus;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.nianticlabs.pokemongoplus.ble.BluetoothDriver;
import com.nianticlabs.pokemongoplus.ble.Peripheral;
import com.nianticlabs.pokemongoplus.ble.SfidaConstant.CentralState;
import com.nianticlabs.pokemongoplus.ble.callback.CentralStateCallback;
import com.nianticlabs.pokemongoplus.ble.callback.ScanCallback;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class SfidaBluetoothDriver
  extends BluetoothDriver
{
  private static final String TAG = SfidaBluetoothDriver.class.getSimpleName();
  private BluetoothAdapter bluetoothAdapter;
  private Context context;
  private boolean isScanning;
  private long nativeHandle;
  private Peripheral peripheral;
  private Map<String, SfidaPeripheral> peripheralMap = new HashMap();
  private ScanCallback scanCallback;
  private HandlerExecutor serialExecutor = new HandlerExecutor("SfidaBluetoothDriver");
  private SfidaScanCallback sfidaScanCallback;
  
  public SfidaBluetoothDriver(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private native void nativeScanCallback(Peripheral paramPeripheral);
  
  private native void nativeStartCallback(int paramInt);
  
  public boolean IsScanning()
  {
    return this.isScanning;
  }
  
  public boolean isEnabledBluetoothLe()
  {
    if ((this.bluetoothAdapter == null) || (!this.bluetoothAdapter.isEnabled())) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public int start(final CentralStateCallback paramCentralStateCallback)
  {
    this.serialExecutor.execute(new Runnable()
    {
      public void run()
      {
        BluetoothManager localBluetoothManager = SfidaUtils.getBluetoothManager(SfidaBluetoothDriver.this.context);
        SfidaConstant.CentralState localCentralState;
        if (localBluetoothManager != null)
        {
          SfidaBluetoothDriver.access$102(SfidaBluetoothDriver.this, localBluetoothManager.getAdapter());
          CentralStateCallback localCentralStateCallback = paramCentralStateCallback;
          if (SfidaBluetoothDriver.this.bluetoothAdapter.isEnabled())
          {
            localCentralState = SfidaConstant.CentralState.PoweredOn;
            localCentralStateCallback.OnStateChanged(localCentralState);
          }
        }
        for (;;)
        {
          return;
          localCentralState = SfidaConstant.CentralState.PoweredOff;
          break;
          Log.e(SfidaBluetoothDriver.TAG, "start(CentralStateCallback): Could not find bluetooth manager.");
          paramCentralStateCallback.OnStateChanged(SfidaConstant.CentralState.Unknown);
        }
      }
    });
    return 0;
  }
  
  public void startDriver()
  {
    start(new CentralStateCallback()
    {
      public void OnStateChanged(SfidaConstant.CentralState paramAnonymousCentralState)
      {
        SfidaBluetoothDriver.this.serialExecutor.assertOnThread();
        SfidaBluetoothDriver.this.nativeStartCallback(paramAnonymousCentralState.getInt());
      }
    });
  }
  
  public void startScanning(String paramString)
  {
    String str = TAG;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    Log.d(str, String.format("startScanning(%s)", arrayOfObject));
    startScanning(paramString, new ScanCallback()
    {
      public void onScan(Peripheral paramAnonymousPeripheral)
      {
        SfidaBluetoothDriver.this.nativeScanCallback(paramAnonymousPeripheral);
      }
    });
  }
  
  public void startScanning(final String paramString, final ScanCallback paramScanCallback)
  {
    this.serialExecutor.execute(new Runnable()
    {
      public void run()
      {
        SfidaBluetoothDriver.access$502(SfidaBluetoothDriver.this, paramScanCallback);
        if (SfidaBluetoothDriver.this.bluetoothAdapter.isEnabled())
        {
          SfidaBluetoothDriver.access$602(SfidaBluetoothDriver.this, new SfidaBluetoothDriver.SfidaScanCallback(SfidaBluetoothDriver.this, paramString));
          SfidaBluetoothDriver.this.bluetoothAdapter.startLeScan(SfidaBluetoothDriver.this.sfidaScanCallback);
          SfidaBluetoothDriver.access$702(SfidaBluetoothDriver.this, true);
        }
      }
    });
  }
  
  public void stop(int paramInt)
  {
    Log.e(TAG, "stop()");
  }
  
  public void stopDriver()
  {
    stop(0);
  }
  
  public void stopScanning(String paramString)
  {
    String str = TAG;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramString;
    Log.d(str, String.format("stopScanning(%s)", arrayOfObject));
    if (this.sfidaScanCallback != null)
    {
      this.bluetoothAdapter.stopLeScan(this.sfidaScanCallback);
      this.isScanning = false;
    }
  }
  
  private class SfidaScanCallback
    implements BluetoothAdapter.LeScanCallback
  {
    private String peripheralName;
    
    public SfidaScanCallback(String paramString)
    {
      this.peripheralName = paramString;
    }
    
    public void onLeScan(final BluetoothDevice paramBluetoothDevice, int paramInt, final byte[] paramArrayOfByte)
    {
      final String str = paramBluetoothDevice.getAddress();
      SfidaBluetoothDriver.this.serialExecutor.execute(new Runnable()
      {
        public void run()
        {
          if (!SfidaBluetoothDriver.this.IsScanning()) {}
          String str;
          do
          {
            return;
            str = paramBluetoothDevice.getName();
          } while ((str == null) || (!str.contains(SfidaBluetoothDriver.SfidaScanCallback.this.peripheralName)) || (SfidaBluetoothDriver.this.scanCallback == null));
          SfidaPeripheral localSfidaPeripheral;
          if (!SfidaBluetoothDriver.this.peripheralMap.containsKey(str))
          {
            localSfidaPeripheral = new SfidaPeripheral(SfidaBluetoothDriver.this.context, paramBluetoothDevice, paramArrayOfByte);
            SfidaBluetoothDriver.this.peripheralMap.put(str, localSfidaPeripheral);
          }
          for (;;)
          {
            SfidaBluetoothDriver.this.scanCallback.onScan(localSfidaPeripheral);
            break;
            localSfidaPeripheral = (SfidaPeripheral)SfidaBluetoothDriver.this.peripheralMap.get(str);
            localSfidaPeripheral.setScanRecord(paramArrayOfByte);
          }
        }
      });
    }
  }
  
  private static class HandlerExecutor
    implements Executor
  {
    private final Handler handler;
    private final HandlerThread handlerThread;
    
    HandlerExecutor(String paramString)
    {
      this.handlerThread = new HandlerThread(paramString);
      this.handlerThread.start();
      this.handler = new Handler(this.handlerThread.getLooper());
    }
    
    public void assertOnThread()
    {
      if (!onThread()) {
        throw new RuntimeException("Must be on the worker thread");
      }
    }
    
    public void execute(Runnable paramRunnable)
    {
      this.handler.post(paramRunnable);
    }
    
    public boolean onThread()
    {
      if (Looper.myLooper() == this.handlerThread.getLooper()) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/SfidaBluetoothDriver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */