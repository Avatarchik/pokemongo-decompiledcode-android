package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface zzoz
  extends IInterface
{
  public abstract void zzh(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzoz
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.identity.intents.internal.IAddressCallbacks");
    }
    
    public static zzoz zzbT(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzoz)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof zzoz))) {
          localObject = (zzoz)localIInterface;
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
      case 1598968902: 
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.identity.intents.internal.IAddressCallbacks");
      int i = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {}
      for (Bundle localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; localBundle = null)
      {
        zzh(i, localBundle);
        paramParcel2.writeNoException();
        bool = true;
        break;
      }
    }
    
    private static class zza
      implements zzoz
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
      public void zzh(int paramInt, Bundle paramBundle)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 32
        //   12: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_3
        //   16: iload_1
        //   17: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   20: aload_2
        //   21: ifnull +44 -> 65
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 46	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/internal/zzoz$zza$zza:zznJ	Landroid/os/IBinder;
        //   39: iconst_2
        //   40: aload_3
        //   41: aload 4
        //   43: iconst_0
        //   44: invokeinterface 52 5 0
        //   49: pop
        //   50: aload 4
        //   52: invokevirtual 55	android/os/Parcel:readException	()V
        //   55: aload 4
        //   57: invokevirtual 58	android/os/Parcel:recycle	()V
        //   60: aload_3
        //   61: invokevirtual 58	android/os/Parcel:recycle	()V
        //   64: return
        //   65: aload_3
        //   66: iconst_0
        //   67: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   70: goto -35 -> 35
        //   73: astore 5
        //   75: aload 4
        //   77: invokevirtual 58	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: invokevirtual 58	android/os/Parcel:recycle	()V
        //   84: aload 5
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	zza
        //   0	87	1	paramInt	int
        //   0	87	2	paramBundle	Bundle
        //   3	78	3	localParcel1	Parcel
        //   7	69	4	localParcel2	Parcel
        //   73	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   9	55	73	finally
        //   65	70	73	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzoz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */