package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.internal.zzgr;
import java.lang.reflect.Method;

@zzgr
public class zzb
{
  private final Context mContext;
  private final boolean zzCA;
  Object zzCz;
  
  public zzb(Context paramContext)
  {
    this(paramContext, true);
  }
  
  public zzb(Context paramContext, boolean paramBoolean)
  {
    this.mContext = paramContext;
    this.zzCA = paramBoolean;
  }
  
  public void destroy()
  {
    this.zzCz = null;
  }
  
  public void zzN(IBinder paramIBinder)
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub");
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = IBinder.class;
      Method localMethod = localClass.getDeclaredMethod("asInterface", arrayOfClass);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramIBinder;
      this.zzCz = localMethod.invoke(null, arrayOfObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (this.zzCA) {
          com.google.android.gms.ads.internal.util.client.zzb.zzaH("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
        }
      }
    }
  }
  
  public int zza(int paramInt, String paramString1, String paramString2)
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
      Class[] arrayOfClass = new Class[3];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = String.class;
      arrayOfClass[2] = String.class;
      Method localMethod = localClass.getDeclaredMethod("isBillingSupported", arrayOfClass);
      Object localObject = localClass.cast(this.zzCz);
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = paramString2;
      int j = ((Integer)localMethod.invoke(localObject, arrayOfObject)).intValue();
      i = j;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (this.zzCA) {
          com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", localException);
        }
        int i = 5;
      }
    }
    return i;
  }
  
  public Bundle zzb(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
      Class[] arrayOfClass = new Class[5];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = String.class;
      arrayOfClass[2] = String.class;
      arrayOfClass[3] = String.class;
      arrayOfClass[4] = String.class;
      Method localMethod = localClass.getDeclaredMethod("getBuyIntent", arrayOfClass);
      Object localObject = localClass.cast(this.zzCz);
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = Integer.valueOf(3);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = paramString2;
      arrayOfObject[3] = "inapp";
      arrayOfObject[4] = paramString3;
      localBundle = (Bundle)localMethod.invoke(localObject, arrayOfObject);
      return localBundle;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (this.zzCA) {
          com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", localException);
        }
        Bundle localBundle = null;
      }
    }
  }
  
  public int zzi(String paramString1, String paramString2)
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
      Class[] arrayOfClass = new Class[3];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = String.class;
      arrayOfClass[2] = String.class;
      Method localMethod = localClass.getDeclaredMethod("consumePurchase", arrayOfClass);
      Object localObject = localClass.cast(this.zzCz);
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(3);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = paramString2;
      int j = ((Integer)localMethod.invoke(localObject, arrayOfObject)).intValue();
      i = j;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (this.zzCA) {
          com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", localException);
        }
        int i = 5;
      }
    }
    return i;
  }
  
  public Bundle zzj(String paramString1, String paramString2)
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
      Class[] arrayOfClass = new Class[4];
      arrayOfClass[0] = Integer.TYPE;
      arrayOfClass[1] = String.class;
      arrayOfClass[2] = String.class;
      arrayOfClass[3] = String.class;
      Method localMethod = localClass.getDeclaredMethod("getPurchases", arrayOfClass);
      Object localObject = localClass.cast(this.zzCz);
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Integer.valueOf(3);
      arrayOfObject[1] = paramString1;
      arrayOfObject[2] = "inapp";
      arrayOfObject[3] = paramString2;
      localBundle = (Bundle)localMethod.invoke(localObject, arrayOfObject);
      return localBundle;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (this.zzCA) {
          com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", localException);
        }
        Bundle localBundle = null;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/purchase/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */