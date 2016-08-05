package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface zzft
  extends IInterface
{
  public abstract void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    throws RemoteException;
  
  public abstract void onCreate()
    throws RemoteException;
  
  public abstract void onDestroy()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzft
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
    }
    
    public static zzft zzQ(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzft)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
        if ((localIInterface != null) && ((localIInterface instanceof zzft))) {
          localObject = (zzft)localIInterface;
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
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
      case 1: 
      case 2: 
        for (;;)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
          onCreate();
          paramParcel2.writeNoException();
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
          onDestroy();
          paramParcel2.writeNoException();
          bool = true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
      int i = paramParcel1.readInt();
      int j = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {}
      for (Intent localIntent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; localIntent = null)
      {
        onActivityResult(i, j, localIntent);
        paramParcel2.writeNoException();
        bool = true;
        break;
      }
    }
    
    private static class zza
      implements zzft
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
      
      /* Error */
      public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: iload_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload 4
        //   25: iload_2
        //   26: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   29: aload_3
        //   30: ifnull +48 -> 78
        //   33: aload 4
        //   35: iconst_1
        //   36: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   39: aload_3
        //   40: aload 4
        //   42: iconst_0
        //   43: invokevirtual 46	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/internal/zzft$zza$zza:zznJ	Landroid/os/IBinder;
        //   50: iconst_3
        //   51: aload 4
        //   53: aload 5
        //   55: iconst_0
        //   56: invokeinterface 52 5 0
        //   61: pop
        //   62: aload 5
        //   64: invokevirtual 55	android/os/Parcel:readException	()V
        //   67: aload 5
        //   69: invokevirtual 58	android/os/Parcel:recycle	()V
        //   72: aload 4
        //   74: invokevirtual 58	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aload 4
        //   80: iconst_0
        //   81: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   84: goto -38 -> 46
        //   87: astore 6
        //   89: aload 5
        //   91: invokevirtual 58	android/os/Parcel:recycle	()V
        //   94: aload 4
        //   96: invokevirtual 58	android/os/Parcel:recycle	()V
        //   99: aload 6
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	zza
        //   0	102	1	paramInt1	int
        //   0	102	2	paramInt2	int
        //   0	102	3	paramIntent	Intent
        //   3	92	4	localParcel1	Parcel
        //   8	82	5	localParcel2	Parcel
        //   87	13	6	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   10	67	87	finally
        //   78	84	87	finally
      }
      
      public void onCreate()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
          this.zznJ.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onDestroy()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManager");
          this.zznJ.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */