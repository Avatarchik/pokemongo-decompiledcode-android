package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class zza
  extends zzp.zza
{
  private Context mContext;
  private Account zzQd;
  int zzaeG;
  
  public static Account zzb(zzp paramzzp)
  {
    Object localObject1 = null;
    if (paramzzp != null) {
      l = Binder.clearCallingIdentity();
    }
    try
    {
      Account localAccount = paramzzp.getAccount();
      localObject1 = localAccount;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.w("AccountAccessor", "Remote account accessor probably died");
        Binder.restoreCallingIdentity(l);
      }
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
    return (Account)localObject1;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (this == paramObject) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof zza)) {
        bool = false;
      } else {
        bool = this.zzQd.equals(((zza)paramObject).zzQd);
      }
    }
  }
  
  public Account getAccount()
  {
    int i = Binder.getCallingUid();
    if (i == this.zzaeG) {}
    for (Account localAccount = this.zzQd;; localAccount = this.zzQd)
    {
      return localAccount;
      if (!GooglePlayServicesUtil.zze(this.mContext, i)) {
        break;
      }
      this.zzaeG = i;
    }
    throw new SecurityException("Caller is not GooglePlayServices");
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */