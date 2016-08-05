package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzfv
  extends IInterface
{
  public abstract void finishPurchase()
    throws RemoteException;
  
  public abstract String getProductId()
    throws RemoteException;
  
  public abstract Intent getPurchaseData()
    throws RemoteException;
  
  public abstract int getResultCode()
    throws RemoteException;
  
  public abstract boolean isVerified()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzfv
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
    }
    
    public static zzfv zzS(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzfv)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
        if ((localIInterface != null) && ((localIInterface instanceof zzfv))) {
          localObject = (zzfv)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int i = 0;
      int j = 1;
      switch (paramInt1)
      {
      default: 
        j = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return j;
        paramParcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
        String str = getProductId();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
        Intent localIntent = getPurchaseData();
        paramParcel2.writeNoException();
        if (localIntent != null)
        {
          paramParcel2.writeInt(j);
          localIntent.writeToParcel(paramParcel2, j);
        }
        else
        {
          paramParcel2.writeInt(0);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
          int k = getResultCode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(k);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
          boolean bool = isVerified();
          paramParcel2.writeNoException();
          if (bool) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
          finishPurchase();
          paramParcel2.writeNoException();
        }
      }
    }
    
    private static class zza
      implements zzfv
    {
      private IBinder zznJ;
      
      zza(IBinder paramIBinder)
      {
        this.zznJ = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.zznJ;
      }
      
      public void finishPurchase()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
          this.zznJ.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getProductId()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
          this.zznJ.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Intent getPurchaseData()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_1
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_1
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/internal/zzfv$zza$zza:zznJ	Landroid/os/IBinder;
        //   18: iconst_2
        //   19: aload_1
        //   20: aload_2
        //   21: iconst_0
        //   22: invokeinterface 41 5 0
        //   27: pop
        //   28: aload_2
        //   29: invokevirtual 44	android/os/Parcel:readException	()V
        //   32: aload_2
        //   33: invokevirtual 58	android/os/Parcel:readInt	()I
        //   36: ifeq +28 -> 64
        //   39: getstatic 64	android/content/Intent:CREATOR	Landroid/os/Parcelable$Creator;
        //   42: aload_2
        //   43: invokeinterface 70 2 0
        //   48: checkcast 60	android/content/Intent
        //   51: astore 5
        //   53: aload_2
        //   54: invokevirtual 47	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: invokevirtual 47	android/os/Parcel:recycle	()V
        //   61: aload 5
        //   63: areturn
        //   64: aconst_null
        //   65: astore 5
        //   67: goto -14 -> 53
        //   70: astore_3
        //   71: aload_2
        //   72: invokevirtual 47	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: invokevirtual 47	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	zza
        //   3	73	1	localParcel1	Parcel
        //   7	65	2	localParcel2	Parcel
        //   70	10	3	localObject	Object
        //   51	15	5	localIntent	Intent
        // Exception table:
        //   from	to	target	type
        //   8	53	70	finally
      }
      
      public int getResultCode()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
          this.zznJ.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isVerified()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseResult");
          this.zznJ.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */