package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract interface zzcu
  extends IInterface
{
  public abstract List<String> getAvailableAssetNames()
    throws RemoteException;
  
  public abstract String getCustomTemplateId()
    throws RemoteException;
  
  public abstract void performClick(String paramString)
    throws RemoteException;
  
  public abstract void recordImpression()
    throws RemoteException;
  
  public abstract String zzU(String paramString)
    throws RemoteException;
  
  public abstract zzcm zzV(String paramString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzcu
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }
    
    public static zzcu zzy(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzcu)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        if ((localIInterface != null) && ((localIInterface instanceof zzcu))) {
          localObject = (zzcu)localIInterface;
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
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        String str2 = zzU(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(str2);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        zzcm localzzcm = zzV(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localzzcm != null) {}
        for (IBinder localIBinder = localzzcm.asBinder();; localIBinder = null)
        {
          paramParcel2.writeStrongBinder(localIBinder);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        List localList = getAvailableAssetNames();
        paramParcel2.writeNoException();
        paramParcel2.writeStringList(localList);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        String str1 = getCustomTemplateId();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str1);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        performClick(paramParcel1.readString());
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        recordImpression();
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class zza
      implements zzcu
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
      
      public List<String> getAvailableAssetNames()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
          this.zznJ.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          ArrayList localArrayList = localParcel2.createStringArrayList();
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getCustomTemplateId()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
          this.zznJ.transact(4, localParcel1, localParcel2, 0);
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
      
      public void performClick(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
          localParcel1.writeString(paramString);
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
      
      public void recordImpression()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
          this.zznJ.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String zzU(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
          localParcel1.writeString(paramString);
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
      
      public zzcm zzV(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
          localParcel1.writeString(paramString);
          this.zznJ.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzcm localzzcm = zzcm.zza.zzt(localParcel2.readStrongBinder());
          return localzzcm;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */