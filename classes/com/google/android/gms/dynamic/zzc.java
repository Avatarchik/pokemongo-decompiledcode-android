package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface zzc
  extends IInterface
{
  public abstract Bundle getArguments()
    throws RemoteException;
  
  public abstract int getId()
    throws RemoteException;
  
  public abstract boolean getRetainInstance()
    throws RemoteException;
  
  public abstract String getTag()
    throws RemoteException;
  
  public abstract int getTargetRequestCode()
    throws RemoteException;
  
  public abstract boolean getUserVisibleHint()
    throws RemoteException;
  
  public abstract zzd getView()
    throws RemoteException;
  
  public abstract boolean isAdded()
    throws RemoteException;
  
  public abstract boolean isDetached()
    throws RemoteException;
  
  public abstract boolean isHidden()
    throws RemoteException;
  
  public abstract boolean isInLayout()
    throws RemoteException;
  
  public abstract boolean isRemoving()
    throws RemoteException;
  
  public abstract boolean isResumed()
    throws RemoteException;
  
  public abstract boolean isVisible()
    throws RemoteException;
  
  public abstract void setHasOptionsMenu(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setMenuVisibility(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setRetainInstance(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void setUserVisibleHint(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void startActivity(Intent paramIntent)
    throws RemoteException;
  
  public abstract void startActivityForResult(Intent paramIntent, int paramInt)
    throws RemoteException;
  
  public abstract void zzn(zzd paramzzd)
    throws RemoteException;
  
  public abstract void zzo(zzd paramzzd)
    throws RemoteException;
  
  public abstract zzd zzsa()
    throws RemoteException;
  
  public abstract zzc zzsb()
    throws RemoteException;
  
  public abstract zzd zzsc()
    throws RemoteException;
  
  public abstract zzc zzsd()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzc
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.dynamic.IFragmentWrapper");
    }
    
    public static zzc zzbj(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzc)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
        if ((localIInterface != null) && ((localIInterface instanceof zzc))) {
          localObject = (zzc)localIInterface;
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
      Object localObject = null;
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
        paramParcel2.writeString("com.google.android.gms.dynamic.IFragmentWrapper");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
        zzd localzzd3 = zzsa();
        paramParcel2.writeNoException();
        if (localzzd3 != null) {
          localObject = localzzd3.asBinder();
        }
        paramParcel2.writeStrongBinder((IBinder)localObject);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
        Bundle localBundle = getArguments();
        paramParcel2.writeNoException();
        if (localBundle != null)
        {
          paramParcel2.writeInt(j);
          localBundle.writeToParcel(paramParcel2, j);
        }
        else
        {
          paramParcel2.writeInt(0);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          int i1 = getId();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(i1);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          zzc localzzc2 = zzsb();
          paramParcel2.writeNoException();
          if (localzzc2 != null) {
            localObject = localzzc2.asBinder();
          }
          paramParcel2.writeStrongBinder((IBinder)localObject);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          zzd localzzd2 = zzsc();
          paramParcel2.writeNoException();
          if (localzzd2 != null) {
            localObject = localzzd2.asBinder();
          }
          paramParcel2.writeStrongBinder((IBinder)localObject);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool9 = getRetainInstance();
          paramParcel2.writeNoException();
          if (bool9) {}
          int n;
          for (int m = j;; n = 0)
          {
            paramParcel2.writeInt(m);
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          String str = getTag();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          zzc localzzc1 = zzsd();
          paramParcel2.writeNoException();
          if (localzzc1 != null) {
            localObject = localzzc1.asBinder();
          }
          paramParcel2.writeStrongBinder((IBinder)localObject);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          int k = getTargetRequestCode();
          paramParcel2.writeNoException();
          paramParcel2.writeInt(k);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool8 = getUserVisibleHint();
          paramParcel2.writeNoException();
          if (bool8) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          zzd localzzd1 = getView();
          paramParcel2.writeNoException();
          if (localzzd1 != null) {
            localObject = localzzd1.asBinder();
          }
          paramParcel2.writeStrongBinder((IBinder)localObject);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool7 = isAdded();
          paramParcel2.writeNoException();
          if (bool7) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool6 = isDetached();
          paramParcel2.writeNoException();
          if (bool6) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool5 = isHidden();
          paramParcel2.writeNoException();
          if (bool5) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool4 = isInLayout();
          paramParcel2.writeNoException();
          if (bool4) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool3 = isRemoving();
          paramParcel2.writeNoException();
          if (bool3) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool2 = isResumed();
          paramParcel2.writeNoException();
          if (bool2) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          boolean bool1 = isVisible();
          paramParcel2.writeNoException();
          if (bool1) {
            i = j;
          }
          paramParcel2.writeInt(i);
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          zzn(zzd.zza.zzbk(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramParcel1.readInt() != 0) {
            i = j;
          }
          setHasOptionsMenu(i);
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramParcel1.readInt() != 0) {
            i = j;
          }
          setMenuVisibility(i);
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramParcel1.readInt() != 0) {
            i = j;
          }
          setRetainInstance(i);
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramParcel1.readInt() != 0) {
            i = j;
          }
          setUserVisibleHint(i);
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramParcel1.readInt() != 0) {
            localObject = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
          }
          startActivity((Intent)localObject);
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramParcel1.readInt() != 0) {
            localObject = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
          }
          startActivityForResult((Intent)localObject, paramParcel1.readInt());
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
          zzo(zzd.zza.zzbk(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
        }
      }
    }
    
    private static class zza
      implements zzc
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
      public Bundle getArguments()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_1
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_1
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/dynamic/zzc$zza$zza:zznJ	Landroid/os/IBinder;
        //   18: iconst_3
        //   19: aload_1
        //   20: aload_2
        //   21: iconst_0
        //   22: invokeinterface 42 5 0
        //   27: pop
        //   28: aload_2
        //   29: invokevirtual 45	android/os/Parcel:readException	()V
        //   32: aload_2
        //   33: invokevirtual 49	android/os/Parcel:readInt	()I
        //   36: ifeq +28 -> 64
        //   39: getstatic 55	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   42: aload_2
        //   43: invokeinterface 61 2 0
        //   48: checkcast 51	android/os/Bundle
        //   51: astore 5
        //   53: aload_2
        //   54: invokevirtual 64	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: invokevirtual 64	android/os/Parcel:recycle	()V
        //   61: aload 5
        //   63: areturn
        //   64: aconst_null
        //   65: astore 5
        //   67: goto -14 -> 53
        //   70: astore_3
        //   71: aload_2
        //   72: invokevirtual 64	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: invokevirtual 64	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	zza
        //   3	73	1	localParcel1	Parcel
        //   7	65	2	localParcel2	Parcel
        //   70	10	3	localObject	Object
        //   51	15	5	localBundle	Bundle
        // Exception table:
        //   from	to	target	type
        //   8	53	70	finally
      }
      
      public int getId()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(4, localParcel1, localParcel2, 0);
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
      
      public boolean getRetainInstance()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(7, localParcel1, localParcel2, 0);
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
      
      public String getTag()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(8, localParcel1, localParcel2, 0);
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
      
      public int getTargetRequestCode()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(10, localParcel1, localParcel2, 0);
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
      
      public boolean getUserVisibleHint()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(11, localParcel1, localParcel2, 0);
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
      
      public zzd getView()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzd localzzd = zzd.zza.zzbk(localParcel2.readStrongBinder());
          return localzzd;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isAdded()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(13, localParcel1, localParcel2, 0);
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
      
      public boolean isDetached()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(14, localParcel1, localParcel2, 0);
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
      
      public boolean isHidden()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(15, localParcel1, localParcel2, 0);
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
      
      public boolean isInLayout()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(16, localParcel1, localParcel2, 0);
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
      
      public boolean isRemoving()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(17, localParcel1, localParcel2, 0);
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
      
      public boolean isResumed()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(18, localParcel1, localParcel2, 0);
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
      
      public boolean isVisible()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(19, localParcel1, localParcel2, 0);
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
      
      public void setHasOptionsMenu(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.zznJ.transact(21, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setMenuVisibility(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.zznJ.transact(22, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setRetainInstance(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.zznJ.transact(23, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void setUserVisibleHint(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.zznJ.transact(24, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void startActivity(Intent paramIntent)
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
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 98	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 109	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/dynamic/zzc$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: bipush 25
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 42 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 45	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 64	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 64	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 98	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 64	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 64	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramIntent	Intent
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	48	65	finally
        //   57	62	65	finally
      }
      
      /* Error */
      public void startActivityForResult(Intent paramIntent, int paramInt)
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
        //   15: aload_1
        //   16: ifnull +50 -> 66
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 98	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 109	android/content/Intent:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: iload_2
        //   32: invokevirtual 98	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/dynamic/zzc$zza$zza:zznJ	Landroid/os/IBinder;
        //   39: bipush 26
        //   41: aload_3
        //   42: aload 4
        //   44: iconst_0
        //   45: invokeinterface 42 5 0
        //   50: pop
        //   51: aload 4
        //   53: invokevirtual 45	android/os/Parcel:readException	()V
        //   56: aload 4
        //   58: invokevirtual 64	android/os/Parcel:recycle	()V
        //   61: aload_3
        //   62: invokevirtual 64	android/os/Parcel:recycle	()V
        //   65: return
        //   66: aload_3
        //   67: iconst_0
        //   68: invokevirtual 98	android/os/Parcel:writeInt	(I)V
        //   71: goto -41 -> 30
        //   74: astore 5
        //   76: aload 4
        //   78: invokevirtual 64	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 64	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	zza
        //   0	88	1	paramIntent	Intent
        //   0	88	2	paramInt	int
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        //   74	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   9	56	74	finally
        //   66	71	74	finally
      }
      
      /* Error */
      public void zzn(zzd paramzzd)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 117 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 120	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/dynamic/zzc$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: bipush 20
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 42 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 45	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 64	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 64	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 64	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 64	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	zza
        //   0	79	1	paramzzd	zzd
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	51	66	finally
      }
      
      /* Error */
      public void zzo(zzd paramzzd)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 117 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 120	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/dynamic/zzc$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: bipush 27
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 42 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 45	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 64	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 64	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 64	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 64	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	zza
        //   0	79	1	paramzzd	zzd
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	51	66	finally
      }
      
      public zzd zzsa()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzd localzzd = zzd.zza.zzbk(localParcel2.readStrongBinder());
          return localzzd;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public zzc zzsb()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzc localzzc = zzc.zza.zzbj(localParcel2.readStrongBinder());
          return localzzc;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public zzd zzsc()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzd localzzd = zzd.zza.zzbk(localParcel2.readStrongBinder());
          return localzzd;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public zzc zzsd()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
          this.zznJ.transact(9, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzc localzzc = zzc.zza.zzbj(localParcel2.readStrongBinder());
          return localzzc;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/dynamic/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */