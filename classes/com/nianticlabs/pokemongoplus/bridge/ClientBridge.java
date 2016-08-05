package com.nianticlabs.pokemongoplus.bridge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.nianticlabs.pokemongoplus.service.BackgroundService;

public class ClientBridge
{
  public static final String CLIENT_BROADCAST_DOMAIN = "com.nianticlabs.pokemongoplus.service.ToClient";
  private static final String TAG = ClientBridge.class.getSimpleName();
  public static Context currentContext;
  LoginDelegate loginDelegate;
  private long nativeHandle;
  SfidaRegisterDelegate sfidaRegisterDelegate;
  
  protected ClientBridge()
  {
    initialize();
    Log.w(TAG, "Initialize();");
  }
  
  public static ClientBridge createBridge(Context paramContext)
  {
    currentContext = paramContext;
    Log.w(TAG, ClientBridge.class.toString());
    nativeInit();
    ClientBridge localClientBridge = new ClientBridge();
    Log.w(TAG, "new ClientBridge");
    return localClientBridge;
  }
  
  private static Intent createIntentWithAction(String paramString)
  {
    Intent localIntent = new Intent(currentContext, BackgroundService.class);
    localIntent.setPackage("com.nianticlabs.holoholo.dev");
    localIntent.putExtra("action", paramString);
    return localIntent;
  }
  
  public static void initBackgroundBridge()
  {
    Log.i(TAG, "Init background bridge PROCESS_LOCAL_VALUE = " + BackgroundService.PROCESS_LOCAL_VALUE);
    Intent localIntent = new Intent(currentContext, BackgroundService.class);
    localIntent.setPackage("com.nianticlabs.pokemongoplus.bridge");
    localIntent.putExtra("action", "init");
    currentContext.startService(localIntent);
  }
  
  private native void initialize();
  
  public static native void nativeInit();
  
  public static void sendPause()
  {
    Intent localIntent = createIntentWithAction("pause");
    Log.i(TAG, "send pause intent");
    currentContext.startService(localIntent);
  }
  
  public static void sendResume()
  {
    Intent localIntent = createIntentWithAction("resume");
    Log.i(TAG, "send resume intent");
    currentContext.startService(localIntent);
  }
  
  public static void sendStart()
  {
    Log.i(TAG, "sendStart PROCESS_LOCAL_VALUE = " + BackgroundService.PROCESS_LOCAL_VALUE);
    Intent localIntent = new Intent(currentContext, BackgroundService.class);
    localIntent.setPackage("com.nianticlabs.pokemongoplus.bridge");
    localIntent.putExtra("action", "start");
    currentContext.startService(localIntent);
  }
  
  public static void sendStartScanning()
  {
    Intent localIntent = createIntentWithAction("startScanning");
    Log.i(TAG, "send startScanning intent");
    currentContext.startService(localIntent);
  }
  
  public static void sendStartSession(String paramString1, String paramString2, byte[] paramArrayOfByte, long paramLong)
  {
    Intent localIntent = createIntentWithAction("startSession");
    localIntent.putExtra("hostPort", paramString1);
    localIntent.putExtra("device", paramString2);
    localIntent.putExtra("authToken", paramArrayOfByte);
    localIntent.putExtra("pokemonId", paramLong);
    String str = TAG;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = paramString2;
    arrayOfObject[2] = paramArrayOfByte;
    arrayOfObject[3] = Long.valueOf(paramLong);
    Log.i(str, String.format("send startSession intent %s %s %s %d", arrayOfObject));
    currentContext.startService(localIntent);
  }
  
  public static void sendStop()
  {
    Intent localIntent = createIntentWithAction("stop");
    Log.i(TAG, "send stop intent");
    currentContext.startService(localIntent);
  }
  
  public static void sendStopScanning()
  {
    Intent localIntent = createIntentWithAction("stopScanning");
    Log.i(TAG, "send stopScanning intent");
    currentContext.startService(localIntent);
  }
  
  public static void sendStopSession()
  {
    Intent localIntent = createIntentWithAction("stopSession");
    Log.i(TAG, "send stopSession intent");
    currentContext.startService(localIntent);
  }
  
  public static void shutdownBackgroundBridge()
  {
    Log.i(TAG, "shutdown background bridge PROCESS_LOCAL_VALUE = " + BackgroundService.PROCESS_LOCAL_VALUE);
    Intent localIntent = new Intent(currentContext, BackgroundService.class);
    localIntent.setPackage("com.nianticlabs.pokemongoplus.bridge");
    localIntent.putExtra("action", "shutdown");
    currentContext.startService(localIntent);
  }
  
  public native void connectDevice(String paramString);
  
  public native void disconnectDevice();
  
  public native void dispose();
  
  public native void login();
  
  public native void logout();
  
  public void onLogin(boolean paramBoolean)
  {
    if (this.loginDelegate != null)
    {
      this.loginDelegate.onLogin(paramBoolean);
      this.loginDelegate = null;
    }
  }
  
  public void onSfidaRegistered(boolean paramBoolean, String paramString)
  {
    if (this.sfidaRegisterDelegate != null)
    {
      this.sfidaRegisterDelegate.onSfidaRegistered(paramBoolean, paramString);
      this.sfidaRegisterDelegate = null;
    }
  }
  
  public native void pausePlugin();
  
  public native void registerDevice(String paramString);
  
  public native void resumePlugin();
  
  public native void sendBatteryLevel(double paramDouble);
  
  public native void sendCentralState(int paramInt);
  
  public native void sendEncounterId(long paramLong);
  
  public native void sendPluginState(int paramInt);
  
  public native void sendPokestopId(String paramString);
  
  public native void sendScannedSfida(String paramString, int paramInt);
  
  public native void sendSfidaState(int paramInt);
  
  public native void sendUpdateTimestamp(long paramLong);
  
  public native void standaloneInit(long paramLong);
  
  public void standaloneLogin(LoginDelegate paramLoginDelegate)
  {
    this.loginDelegate = paramLoginDelegate;
    login();
  }
  
  public void standaloneSfidaRegister(String paramString, SfidaRegisterDelegate paramSfidaRegisterDelegate)
  {
    this.sfidaRegisterDelegate = paramSfidaRegisterDelegate;
    registerDevice(paramString);
  }
  
  public native void standaloneUpdate();
  
  public native void startPlugin();
  
  public native void startScanning();
  
  public native void stopPlugin();
  
  public native void stopScanning();
  
  public static abstract interface SfidaRegisterDelegate
  {
    public abstract void onSfidaRegistered(boolean paramBoolean, String paramString);
  }
  
  public static abstract interface LoginDelegate
  {
    public abstract void onLogin(boolean paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/bridge/ClientBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */