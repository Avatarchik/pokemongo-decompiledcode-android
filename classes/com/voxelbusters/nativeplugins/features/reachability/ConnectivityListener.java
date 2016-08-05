package com.voxelbusters.nativeplugins.features.reachability;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityListener
  extends BroadcastReceiver
{
  boolean isConnected;
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    updateConnectionStatus(paramContext);
  }
  
  public void updateConnectionStatus(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localNetworkInfo.isConnected())) {}
    for (boolean bool = true;; bool = false)
    {
      NetworkReachabilityHandler.sendWifiReachabilityStatus(bool);
      return;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/features/reachability/ConnectivityListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */