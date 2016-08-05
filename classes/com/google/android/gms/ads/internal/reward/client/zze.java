package com.google.android.gms.ads.internal.reward.client;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.reward.RewardItem;

public class zze
  implements RewardItem
{
  private final zza zzHc;
  
  public zze(zza paramzza)
  {
    this.zzHc = paramzza;
  }
  
  public int getAmount()
  {
    int i = 0;
    if (this.zzHc == null) {}
    for (;;)
    {
      return i;
      try
      {
        int j = this.zzHc.getAmount();
        i = j;
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not forward getAmount to RewardItem", localRemoteException);
      }
    }
  }
  
  public String getType()
  {
    Object localObject = null;
    if (this.zzHc == null) {}
    for (;;)
    {
      return (String)localObject;
      try
      {
        String str = this.zzHc.getType();
        localObject = str;
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzd("Could not forward getType to RewardItem", localRemoteException);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/reward/client/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */