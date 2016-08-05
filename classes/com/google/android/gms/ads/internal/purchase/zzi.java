package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzid;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzi
{
  public void zza(Context paramContext, boolean paramBoolean, GInAppPurchaseManagerInfoParcel paramGInAppPurchaseManagerInfoParcel)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramContext, "com.google.android.gms.ads.purchase.InAppPurchaseActivity");
    localIntent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", paramBoolean);
    GInAppPurchaseManagerInfoParcel.zza(localIntent, paramGInAppPurchaseManagerInfoParcel);
    zzp.zzbv().zzb(paramContext, localIntent);
  }
  
  public String zzao(String paramString)
  {
    Object localObject = null;
    if (paramString == null) {}
    for (;;)
    {
      return (String)localObject;
      try
      {
        String str = new JSONObject(paramString).getString("developerPayload");
        localObject = str;
      }
      catch (JSONException localJSONException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to parse purchase data");
      }
    }
  }
  
  public String zzap(String paramString)
  {
    Object localObject = null;
    if (paramString == null) {}
    for (;;)
    {
      return (String)localObject;
      try
      {
        String str = new JSONObject(paramString).getString("purchaseToken");
        localObject = str;
      }
      catch (JSONException localJSONException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to parse purchase data");
      }
    }
  }
  
  public int zzc(Bundle paramBundle)
  {
    Object localObject = paramBundle.get("RESPONSE_CODE");
    int i;
    if (localObject == null)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("Bundle with null response code, assuming OK (known issue)");
      i = 0;
    }
    for (;;)
    {
      return i;
      if ((localObject instanceof Integer))
      {
        i = ((Integer)localObject).intValue();
      }
      else if ((localObject instanceof Long))
      {
        i = (int)((Long)localObject).longValue();
      }
      else
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Unexpected type for intent response code. " + localObject.getClass().getName());
        i = 5;
      }
    }
  }
  
  public int zzd(Intent paramIntent)
  {
    int i;
    if (paramIntent == null) {
      i = 5;
    }
    for (;;)
    {
      return i;
      Object localObject = paramIntent.getExtras().get("RESPONSE_CODE");
      if (localObject == null)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Intent with no response code, assuming OK (known issue)");
        i = 0;
      }
      else if ((localObject instanceof Integer))
      {
        i = ((Integer)localObject).intValue();
      }
      else if ((localObject instanceof Long))
      {
        i = (int)((Long)localObject).longValue();
      }
      else
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Unexpected type for intent response code. " + localObject.getClass().getName());
        i = 5;
      }
    }
  }
  
  public String zze(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (String str = null;; str = paramIntent.getStringExtra("INAPP_PURCHASE_DATA")) {
      return str;
    }
  }
  
  public String zzf(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (String str = null;; str = paramIntent.getStringExtra("INAPP_DATA_SIGNATURE")) {
      return str;
    }
  }
  
  public void zzx(final Context paramContext)
  {
    ServiceConnection local1 = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        boolean bool = false;
        zzb localzzb = new zzb(paramContext.getApplicationContext(), false);
        localzzb.zzN(paramAnonymousIBinder);
        int i = localzzb.zza(3, paramContext.getPackageName(), "inapp");
        zzhu localzzhu = zzp.zzby();
        if (i == 0) {
          bool = true;
        }
        localzzhu.zzB(bool);
        paramContext.unbindService(this);
        localzzb.destroy();
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName) {}
    };
    Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
    localIntent.setPackage("com.android.vending");
    paramContext.bindService(localIntent, local1, 1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/purchase/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */