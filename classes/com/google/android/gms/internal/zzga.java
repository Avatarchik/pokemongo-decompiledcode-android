package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchase;

@zzgr
public class zzga
  implements InAppPurchase
{
  private final zzfr zzCM;
  
  public zzga(zzfr paramzzfr)
  {
    this.zzCM = paramzzfr;
  }
  
  public String getProductId()
  {
    try
    {
      String str2 = this.zzCM.getProductId();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward getProductId to InAppPurchase", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public void recordPlayBillingResolution(int paramInt)
  {
    try
    {
      this.zzCM.recordPlayBillingResolution(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward recordPlayBillingResolution to InAppPurchase", localRemoteException);
      }
    }
  }
  
  public void recordResolution(int paramInt)
  {
    try
    {
      this.zzCM.recordResolution(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward recordResolution to InAppPurchase", localRemoteException);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzga.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */