package com.nianticlabs.pokemongoplus.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.nianticlabs.pokemongoplus.bridge.ClientBridge;

public class ClientService
  extends Service
{
  private static final String TAG = ClientService.class.getSimpleName();
  static ClientBridge pgpClientBridge = null;
  private BroadcastReceiver sfidaReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      ClientService.this.onHandleIntent(paramAnonymousIntent);
    }
  };
  
  private static void sendBackgroundServiceIntent(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, BackgroundService.class);
    localIntent.setPackage("com.nianticlabs.pokemongoplus.bridge");
    localIntent.putExtra("action", paramString);
    paramContext.startService(localIntent);
  }
  
  private static void sendClientServiceIntent(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, ClientService.class);
    localIntent.setPackage("com.nianticlabs.pokemongoplus.bridge");
    localIntent.putExtra("action", paramString);
    paramContext.startService(localIntent);
  }
  
  public static void startClientService(Context paramContext, ClientBridge paramClientBridge)
  {
    pgpClientBridge = paramClientBridge;
    sendClientServiceIntent(paramContext, "startService");
  }
  
  public static void stopClientService(Context paramContext)
  {
    paramContext.stopService(new Intent(paramContext, ClientService.class));
    pgpClientBridge = null;
  }
  
  protected void confirmBridgeShutdown() {}
  
  @Nullable
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    Log.i(TAG, "ClientService onCreate PROCESS_LOCAL_VALUE = " + BackgroundService.PROCESS_LOCAL_VALUE);
    IntentFilter localIntentFilter = new IntentFilter("com.nianticlabs.pokemongoplus.service.ToClient");
    registerReceiver(this.sfidaReceiver, localIntentFilter);
  }
  
  public void onDestroy()
  {
    unregisterReceiver(this.sfidaReceiver);
    super.onDestroy();
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if ((paramIntent == null) || (pgpClientBridge == null)) {}
    for (;;)
    {
      label10:
      return;
      String str1 = paramIntent.getStringExtra("action");
      if (str1 != null)
      {
        int i = -1;
        switch (str1.hashCode())
        {
        }
        for (;;)
        {
          switch (i)
          {
          case 8: 
          default: 
            Log.e("ClientService", "Can't handle intent message: " + str1);
            break label10;
            if (str1.equals("updateTimestamp"))
            {
              i = 0;
              continue;
              if (str1.equals("sfidaState"))
              {
                i = 1;
                continue;
                if (str1.equals("encounterId"))
                {
                  i = 2;
                  continue;
                  if (str1.equals("pokestop"))
                  {
                    i = 3;
                    continue;
                    if (str1.equals("centralState"))
                    {
                      i = 4;
                      continue;
                      if (str1.equals("scannedSfida"))
                      {
                        i = 5;
                        continue;
                        if (str1.equals("pluginState"))
                        {
                          i = 6;
                          continue;
                          if (str1.equals("batteryLevel"))
                          {
                            i = 7;
                            continue;
                            if (str1.equals("startService"))
                            {
                              i = 8;
                              continue;
                              if (str1.equals("confirmBridgeShutdown")) {
                                i = 9;
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            break;
          }
        }
        long l2 = paramIntent.getLongExtra("timestamp", 0L);
        pgpClientBridge.sendUpdateTimestamp(l2);
        continue;
        int n = paramIntent.getIntExtra("state", 0);
        pgpClientBridge.sendSfidaState(n);
        continue;
        long l1 = paramIntent.getLongExtra("id", 0L);
        pgpClientBridge.sendEncounterId(l1);
        continue;
        String str3 = paramIntent.getStringExtra("id");
        pgpClientBridge.sendPokestopId(str3);
        continue;
        int m = paramIntent.getIntExtra("state", 0);
        pgpClientBridge.sendCentralState(m);
        continue;
        String str2 = paramIntent.getStringExtra("device");
        int k = paramIntent.getIntExtra("button", 0);
        pgpClientBridge.sendScannedSfida(str2, k);
        continue;
        int j = paramIntent.getIntExtra("state", 0);
        pgpClientBridge.sendPluginState(j);
        continue;
        double d = paramIntent.getDoubleExtra("level", 0.0D);
        pgpClientBridge.sendBatteryLevel(d);
        continue;
        confirmBridgeShutdown();
      }
    }
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    onHandleIntent(paramIntent);
    return 1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/service/ClientService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */