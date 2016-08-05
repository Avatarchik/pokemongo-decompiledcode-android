package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzfw
  extends IInterface
{
  public abstract boolean isValidPurchase(String paramString)
    throws RemoteException;
  
  public abstract void zza(zzfv paramzzfv)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzfw
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
    }
    
    public static zzfw zzT(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzfw)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
        if ((localIInterface != null) && ((localIInterface instanceof zzfw))) {
          localObject = (zzfw)localIInterface;
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
      int i = 1;
      switch (paramInt1)
      {
      default: 
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return i;
        paramParcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
        boolean bool = isValidPurchase(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (bool) {}
        int k;
        for (int j = i;; k = 0)
        {
          paramParcel2.writeInt(j);
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IPlayStorePurchaseListener");
        zza(zzfv.zza.zzS(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
      }
    }
    
    private static class zza
      implements zzfw
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
      public boolean isValidPurchase(String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_3
        //   6: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   9: astore 4
        //   11: aload_3
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_3
        //   18: aload_1
        //   19: invokevirtual 39	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   22: aload_0
        //   23: getfield 18	com/google/android/gms/internal/zzfw$zza$zza:zznJ	Landroid/os/IBinder;
        //   26: iconst_1
        //   27: aload_3
        //   28: aload 4
        //   30: iconst_0
        //   31: invokeinterface 45 5 0
        //   36: pop
        //   37: aload 4
        //   39: invokevirtual 48	android/os/Parcel:readException	()V
        //   42: aload 4
        //   44: invokevirtual 52	android/os/Parcel:readInt	()I
        //   47: istore 7
        //   49: iload 7
        //   51: ifeq +14 -> 65
        //   54: aload 4
        //   56: invokevirtual 55	android/os/Parcel:recycle	()V
        //   59: aload_3
        //   60: invokevirtual 55	android/os/Parcel:recycle	()V
        //   63: iload_2
        //   64: ireturn
        //   65: iconst_0
        //   66: istore_2
        //   67: goto -13 -> 54
        //   70: astore 5
        //   72: aload 4
        //   74: invokevirtual 55	android/os/Parcel:recycle	()V
        //   77: aload_3
        //   78: invokevirtual 55	android/os/Parcel:recycle	()V
        //   81: aload 5
        //   83: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	84	0	this	zza
        //   0	84	1	paramString	String
        //   1	66	2	bool	boolean
        //   5	73	3	localParcel1	Parcel
        //   9	64	4	localParcel2	Parcel
        //   70	12	5	localObject	Object
        //   47	3	7	i	int
        // Exception table:
        //   from	to	target	type
        //   11	49	70	finally
      }
      
      /* Error */
      public void zza(zzfv paramzzfv)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 60 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 63	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/internal/zzfw$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_2
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 45 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 48	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 55	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 55	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 55	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 55	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramzzfv	zzfv
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        //   24	37	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	50	65	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */