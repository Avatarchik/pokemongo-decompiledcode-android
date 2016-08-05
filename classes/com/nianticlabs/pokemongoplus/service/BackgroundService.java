package com.nianticlabs.pokemongoplus.service;

import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import com.nianticlabs.pokemongoplus.R.drawable;
import com.nianticlabs.pokemongoplus.bridge.BackgroundBridge;
import java.util.Random;

public class BackgroundService
  extends Service
{
  public static int PROCESS_LOCAL_VALUE = new Random().nextInt();
  private static final String TAG = BackgroundService.class.getSimpleName();
  private Handler handler;
  private HandlerThread handlerThread;
  private BackgroundBridge pgpBackgroundBridge = null;
  private BroadcastReceiver sfidaReceiver = new BroadcastReceiver()
  {
    void onHandleBroadcastIntent(Intent paramAnonymousIntent)
    {
      String str1 = paramAnonymousIntent.getStringExtra("action");
      int i = -1;
      switch (str1.hashCode())
      {
      default: 
        switch (i)
        {
        }
        break;
      }
      for (;;)
      {
        return;
        if (!str1.equals("sendNotification")) {
          break;
        }
        i = 0;
        break;
        if (!str1.equals("stopNotification")) {
          break;
        }
        i = 1;
        break;
        if (!str1.equals("batteryLevel")) {
          break;
        }
        i = 2;
        break;
        String str2 = paramAnonymousIntent.getStringExtra("message");
        if (str2 != null)
        {
          BackgroundService.this.createPlayerNotification(str2);
          continue;
          BackgroundService.this.stopPlayerNotification();
          continue;
          double d = paramAnonymousIntent.getDoubleExtra("level", 0.0D);
          BackgroundService.this.updateBatteryLevel(d);
        }
      }
    }
    
    public void onReceive(Context paramAnonymousContext, final Intent paramAnonymousIntent)
    {
      BackgroundService.this.handler.post(new Runnable()
      {
        public void run()
        {
          BackgroundService.3.this.onHandleBroadcastIntent(paramAnonymousIntent);
        }
      });
    }
  };
  
  public static Class<?> GetLauncherActivity(Context paramContext)
  {
    String str1 = paramContext.getPackageName();
    str2 = paramContext.getPackageManager().getLaunchIntentForPackage(str1).getComponent().getClassName();
    try
    {
      Class localClass2 = Class.forName(str2);
      localClass1 = localClass2;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        Log.e(TAG, "Launcher class not found: " + str2);
        Class localClass1 = null;
      }
    }
    return localClass1;
  }
  
  private void createPlayerNotification(String paramString)
  {
    Log.i(TAG, "BackgroundService createPlayerNotification message = " + paramString);
    Class localClass = GetLauncherActivity(this);
    Intent localIntent = new Intent(this, localClass);
    localIntent.setFlags(67108864);
    Bitmap localBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sfida_icon);
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_swap_horiz_white_24dp).setLargeIcon(localBitmap).setContentTitle("Pokémon GO Plus").setContentText(paramString);
    TaskStackBuilder localTaskStackBuilder = TaskStackBuilder.create(this);
    localTaskStackBuilder.addParentStack(localClass);
    localTaskStackBuilder.addNextIntent(localIntent);
    localBuilder.setContentIntent(localTaskStackBuilder.getPendingIntent(1516, 134217728));
    startForeground(1515, localBuilder.build());
  }
  
  private void handleStartSession(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("hostPort");
    String str2 = paramIntent.getStringExtra("device");
    byte[] arrayOfByte = paramIntent.getByteArrayExtra("authToken");
    long l = paramIntent.getLongExtra("pokemonId", 0L);
    String str3 = TAG;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = str1;
    arrayOfObject[1] = str2;
    arrayOfObject[2] = Long.valueOf(l);
    Log.i(str3, String.format("Start session: %s %s %d", arrayOfObject));
    this.pgpBackgroundBridge.startSession(str1, str2, arrayOfByte, l);
  }
  
  private static void sendClientIntent(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, ClientService.class);
    localIntent.setPackage("com.nianticlabs.pokemongoplus.bridge");
    localIntent.putExtra("action", paramString);
    paramContext.startService(localIntent);
  }
  
  private void stopPlayerNotification()
  {
    stopForeground(true);
  }
  
  private void updateBatteryLevel(double paramDouble) {}
  
  public void finishShutdownBackgroundBridge()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        Log.i(BackgroundService.TAG, "BackgroundService stopSelf ");
        BackgroundService.this.stopSelf();
      }
    });
  }
  
  public void initBackgroundBridge()
  {
    Log.i(TAG, "BackgroundService onCreate PROCESS_LOCAL_VALUE = " + PROCESS_LOCAL_VALUE);
    this.pgpBackgroundBridge = BackgroundBridge.createBridge(this);
    createPlayerNotification("Background bridge initialized");
  }
  
  @Nullable
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    Log.i(TAG, "BackgroundService onCreate() PROCESS_LOCAL_VALUE = " + PROCESS_LOCAL_VALUE);
    this.handlerThread = new HandlerThread("BackgroundService");
    this.handlerThread.start();
    this.handler = new Handler(this.handlerThread.getLooper());
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("com.nianticlabs.pokemongoplus.service.ToClient");
    registerReceiver(this.sfidaReceiver, localIntentFilter);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    Log.i(TAG, "BackgroundService onDestroy IF NOT REALLY DONE WITH SERVICE, THIS IS A BIG PROBLEM! PROCESS_LOCAL_VALUE = " + PROCESS_LOCAL_VALUE);
    unregisterReceiver(this.sfidaReceiver);
    this.handlerThread.quitSafely();
    this.handler = null;
    this.handlerThread = null;
  }
  
  protected void onHandleBridgedIntent(String paramString, Intent paramIntent)
  {
    Log.i(TAG, paramString + " :: " + PROCESS_LOCAL_VALUE);
    if ((this.pgpBackgroundBridge == null) && (!paramString.equals("stop")))
    {
      Log.i(TAG, "BackgroundService onHandleBridgedIntent (pgpBackgroundBridge == null)");
      initBackgroundBridge();
      if (!paramString.equals("start"))
      {
        Log.e(TAG, "Background servic iunintialized when received \"" + paramString + "\", PROCESS_LOCAL_VALUE = " + PROCESS_LOCAL_VALUE);
        paramString = "start";
      }
    }
    Log.i(TAG, "BackgroundService onHandleBridgedIntent action = " + paramString);
    int i = -1;
    switch (paramString.hashCode())
    {
    default: 
      switch (i)
      {
      default: 
        Log.e(TAG, "Can't handle intent message: " + paramString);
      }
      break;
    }
    for (;;)
    {
      return;
      if (!paramString.equals("start")) {
        break;
      }
      i = 0;
      break;
      if (!paramString.equals("resume")) {
        break;
      }
      i = 1;
      break;
      if (!paramString.equals("pause")) {
        break;
      }
      i = 2;
      break;
      if (!paramString.equals("stop")) {
        break;
      }
      i = 3;
      break;
      if (!paramString.equals("startScanning")) {
        break;
      }
      i = 4;
      break;
      if (!paramString.equals("stopScanning")) {
        break;
      }
      i = 5;
      break;
      if (!paramString.equals("startSession")) {
        break;
      }
      i = 6;
      break;
      if (!paramString.equals("stopSession")) {
        break;
      }
      i = 7;
      break;
      this.pgpBackgroundBridge.start();
      continue;
      this.pgpBackgroundBridge.resume();
      continue;
      this.pgpBackgroundBridge.pause();
      continue;
      if (this.pgpBackgroundBridge != null) {
        this.pgpBackgroundBridge.stop();
      }
      shutdownBackgroundBridge();
      continue;
      this.pgpBackgroundBridge.startScanning();
      continue;
      this.pgpBackgroundBridge.stopScanning();
      continue;
      handleStartSession(paramIntent);
      continue;
      this.pgpBackgroundBridge.stopSession();
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent == null) {
      Log.i(TAG, "BackgroundService onHandleIntent (intent == null)");
    }
    for (;;)
    {
      label14:
      return;
      String str = paramIntent.getStringExtra("action");
      if (str == null)
      {
        Log.i(TAG, "BackgroundService onHandleIntent (action == null)");
        Log.e(TAG, "Missing action  PROCESS_LOCAL_VALUE = " + PROCESS_LOCAL_VALUE);
      }
      else
      {
        int i = -1;
        switch (str.hashCode())
        {
        }
        for (;;)
        {
          switch (i)
          {
          default: 
            onHandleBridgedIntent(str, paramIntent);
            break label14;
            if (str.equals("finishShutdown")) {
              i = 0;
            }
            break;
          }
        }
        finishShutdownBackgroundBridge();
      }
    }
  }
  
  public int onStartCommand(final Intent paramIntent, int paramInt1, int paramInt2)
  {
    Log.i(TAG, "BackgroundService onStartCommand() PROCESS_LOCAL_VALUE = " + PROCESS_LOCAL_VALUE);
    this.handler.post(new Runnable()
    {
      public void run()
      {
        BackgroundService.this.onHandleIntent(paramIntent);
      }
    });
    return 1;
  }
  
  public void shutdownBackgroundBridge()
  {
    Log.i(TAG, "BackgroundService shutdownBackgroundBridge() PROCESS_LOCAL_VALUE = " + PROCESS_LOCAL_VALUE);
    createPlayerNotification("Background bridge shutting down");
    sendClientIntent(this, "confirmBridgeShutdown");
    if (this.pgpBackgroundBridge != null)
    {
      Log.i(TAG, "BackgroundService destroy the bridge ");
      this.pgpBackgroundBridge.destroyBridge();
      this.pgpBackgroundBridge = null;
    }
    Intent localIntent = new Intent(this, BackgroundService.class);
    localIntent.setPackage("com.nianticlabs.pokemongoplus.bridge");
    localIntent.putExtra("action", "finishShutdown");
    startService(localIntent);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/service/BackgroundService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */