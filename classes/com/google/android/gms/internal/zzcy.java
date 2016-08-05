package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzcy
  extends IInterface
{
  public abstract void zza(zzcu paramzzcu, String paramString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzcy
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }
    
    public static zzcy zzB(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzcy)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        if ((localIInterface != null) && ((localIInterface instanceof zzcy))) {
          localObject = (zzcy)localIInterface;
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
      boolean bool = true;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
        zza(zzcu.zza.zzy(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
      }
    }
    
    private static class zza
      implements zzcy
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
      public void zza(zzcu paramzzcu, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 31
        //   12: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +52 -> 68
        //   19: aload_1
        //   20: invokeinterface 39 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 18	com/google/android/gms/internal/zzcy$zza$zza:zznJ	Landroid/os/IBinder;
        //   42: iconst_1
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 51 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 54	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 57	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 57	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore 6
        //   71: goto -44 -> 27
        //   74: astore 5
        //   76: aload 4
        //   78: invokevirtual 57	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 57	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	zza
        //   0	88	1	paramzzcu	zzcu
        //   0	88	2	paramString	String
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        //   74	12	5	localObject	Object
        //   25	45	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	58	74	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */