package com.nianticlabs.pokemongoplus;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.Method;

public class SfidaUtils
{
  private static final String TAG = SfidaUtils.class.getSimpleName();
  
  public static String byteArrayToBitString(byte[] paramArrayOfByte)
  {
    String str = "";
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfByte[j];
      int m = 0;
      if (m < 8)
      {
        StringBuilder localStringBuilder = new StringBuilder().append(str);
        if ((k & 128 >> m) != 0) {}
        for (int n = 1;; n = 0)
        {
          str = String.valueOf(n);
          m++;
          break;
        }
      }
      str = str + " ";
    }
    return str;
  }
  
  public static String byteArrayToString(byte[] paramArrayOfByte)
  {
    String str = "";
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfByte[j];
      str = str + String.valueOf(k);
    }
    return str;
  }
  
  public static boolean checkBluetoothLeSupported(Context paramContext)
  {
    if (!paramContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  @TargetApi(19)
  public static void createBond(BluetoothDevice paramBluetoothDevice)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      Log.d(TAG, "createBond() Start Pairing...");
      paramBluetoothDevice.createBond();
    }
    for (;;)
    {
      return;
      try
      {
        Log.d(TAG, "createBond() Start Pairing...");
        paramBluetoothDevice.getClass().getMethod("createBond", (Class[])null).invoke(paramBluetoothDevice, (Object[])null);
        Log.d(TAG, "createBond() Pairing finished.");
      }
      catch (Exception localException)
      {
        Log.e(TAG, localException.getMessage());
      }
    }
  }
  
  public static BluetoothManager getBluetoothManager(Context paramContext)
  {
    return (BluetoothManager)paramContext.getSystemService("bluetooth");
  }
  
  public static String getBondStateName(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    default: 
      str = String.valueOf(paramInt);
    }
    for (;;)
    {
      return str;
      str = "BOND_NONE";
      continue;
      str = "BOND_BONDING";
      continue;
      str = "BOND_BONDED";
    }
  }
  
  public static byte[] hexStringToByteArray(String paramString)
  {
    int i = paramString.length();
    byte[] arrayOfByte = new byte[i / 2];
    for (int j = 0; j < i; j += 2) {
      arrayOfByte[(j / 2)] = ((byte)((Character.digit(paramString.charAt(j), 16) << 4) + Character.digit(paramString.charAt(j + 1), 16)));
    }
    return arrayOfByte;
  }
  
  public static boolean refreshDeviceCache(BluetoothGatt paramBluetoothGatt)
  {
    try
    {
      Method localMethod = paramBluetoothGatt.getClass().getMethod("refresh", new Class[0]);
      if (localMethod == null) {
        break label53;
      }
      boolean bool2 = ((Boolean)localMethod.invoke(paramBluetoothGatt, new Object[0])).booleanValue();
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(TAG, "An exception occurred while refreshing device");
        label53:
        boolean bool1 = false;
      }
    }
    return bool1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/SfidaUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */