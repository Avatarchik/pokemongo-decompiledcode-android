package com.nianticlabs.pokemongoplus.bridge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BackgroundBridge
{
  public static final String BACKGROUND_BROADCAST_DOMAIN = "com.nianticlabs.pokemongoplus.service.ToServer";
  private static final String TAG = BackgroundBridge.class.getSimpleName();
  public static Context currentContext;
  private long nativeHandle;
  
  static
  {
    System.loadLibrary("pgpplugin");
  }
  
  protected BackgroundBridge()
  {
    initialize();
    Log.w(TAG, "Initialize();");
  }
  
  public static BackgroundBridge createBridge(Context paramContext)
  {
    currentContext = paramContext;
    Log.w(TAG, BackgroundBridge.class.toString());
    nativeInit();
    Log.w(TAG, "BackgroundBridge createBridge");
    BackgroundBridge localBackgroundBridge = new BackgroundBridge();
    Log.w(TAG, "new BackgroundBridge");
    return localBackgroundBridge;
  }
  
  private static Intent createIntentWithAction(String paramString)
  {
    Intent localIntent = new Intent("com.nianticlabs.pokemongoplus.service.ToClient");
    localIntent.putExtra("action", paramString);
    Log.i(TAG, "createIntentWithAction: " + paramString);
    return localIntent;
  }
  
  private native void initialize();
  
  public static native void nativeInit();
  
  public static void sendBatteryLevel(double paramDouble)
  {
    Intent localIntent = createIntentWithAction("batteryLevel");
    localIntent.putExtra("level", paramDouble);
    Log.i(TAG, "sendBatteryLevel: " + paramDouble);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void sendCentralState(int paramInt)
  {
    Intent localIntent = createIntentWithAction("centralState");
    localIntent.putExtra("state", paramInt);
    Log.i(TAG, "centralState: " + paramInt);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void sendEncounterId(long paramLong)
  {
    Intent localIntent = createIntentWithAction("encounterId");
    localIntent.putExtra("id", paramLong);
    Log.i(TAG, "sendEncounterId: " + paramLong);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void sendNotification(String paramString)
  {
    Intent localIntent = createIntentWithAction("sendNotification");
    localIntent.putExtra("message", paramString);
    Log.i(TAG, "sendNotification: " + paramString);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void sendPluginState(int paramInt)
  {
    Intent localIntent = createIntentWithAction("pluginState");
    localIntent.putExtra("state", paramInt);
    Log.i(TAG, "sendPluginState: " + paramInt);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void sendPokestopId(String paramString)
  {
    Intent localIntent = createIntentWithAction("pokestop");
    localIntent.putExtra("id", paramString);
    Log.i(TAG, "sendPokestopId: " + paramString);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void sendScannedSfida(String paramString, int paramInt)
  {
    Intent localIntent = createIntentWithAction("scannedSfida");
    localIntent.putExtra("device", paramString);
    localIntent.putExtra("button", paramInt);
    Log.i(TAG, "sendScannedSfida: " + paramString);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void sendSfidaState(int paramInt)
  {
    Intent localIntent = createIntentWithAction("sfidaState");
    localIntent.putExtra("state", paramInt);
    Log.i(TAG, "sfidaState: " + paramInt);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void sendUpdateTimestamp(long paramLong)
  {
    Intent localIntent = createIntentWithAction("updateTimestamp");
    localIntent.putExtra("timestamp", paramLong);
    currentContext.sendBroadcast(localIntent);
  }
  
  public static void stopNotification()
  {
    Intent localIntent = createIntentWithAction("stopNotification");
    Log.i(TAG, "stopNotification");
    currentContext.sendBroadcast(localIntent);
  }
  
  public void destroyBridge()
  {
    dispose();
  }
  
  public native void dispose();
  
  public native void pause();
  
  public native void resume();
  
  public native void start();
  
  public native void startScanning();
  
  public native void startSession(String paramString1, String paramString2, byte[] paramArrayOfByte, long paramLong);
  
  public native void stop();
  
  public native void stopScanning();
  
  public native void stopSession();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/bridge/BackgroundBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */