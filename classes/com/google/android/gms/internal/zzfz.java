package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

@zzgr
public class zzfz
  implements InAppPurchaseResult
{
  private final zzfv zzDc;
  
  public zzfz(zzfv paramzzfv)
  {
    this.zzDc = paramzzfv;
  }
  
  public void finishPurchase()
  {
    try
    {
      this.zzDc.finishPurchase();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward finishPurchase to InAppPurchaseResult", localRemoteException);
      }
    }
  }
  
  public String getProductId()
  {
    try
    {
      String str2 = this.zzDc.getProductId();
      str1 = str2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward getProductId to InAppPurchaseResult", localRemoteException);
        String str1 = null;
      }
    }
    return str1;
  }
  
  public Intent getPurchaseData()
  {
    try
    {
      Intent localIntent2 = this.zzDc.getPurchaseData();
      localIntent1 = localIntent2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward getPurchaseData to InAppPurchaseResult", localRemoteException);
        Intent localIntent1 = null;
      }
    }
    return localIntent1;
  }
  
  public int getResultCode()
  {
    try
    {
      int j = this.zzDc.getResultCode();
      i = j;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward getPurchaseData to InAppPurchaseResult", localRemoteException);
        int i = 0;
      }
    }
    return i;
  }
  
  public boolean isVerified()
  {
    try
    {
      boolean bool2 = this.zzDc.isVerified();
      bool1 = bool2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Could not forward isVerified to InAppPurchaseResult", localRemoteException);
        boolean bool1 = false;
      }
    }
    return bool1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */