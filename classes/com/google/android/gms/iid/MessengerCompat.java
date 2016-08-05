package com.google.android.gms.iid;

import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public class MessengerCompat
  implements Parcelable
{
  public static final Parcelable.Creator<MessengerCompat> CREATOR = new Parcelable.Creator()
  {
    public MessengerCompat zzey(Parcel paramAnonymousParcel)
    {
      IBinder localIBinder = paramAnonymousParcel.readStrongBinder();
      if (localIBinder != null) {}
      for (MessengerCompat localMessengerCompat = new MessengerCompat(localIBinder);; localMessengerCompat = null) {
        return localMessengerCompat;
      }
    }
    
    public MessengerCompat[] zzgJ(int paramAnonymousInt)
    {
      return new MessengerCompat[paramAnonymousInt];
    }
  };
  Messenger zzaDK;
  zzb zzaDL;
  
  public MessengerCompat(Handler paramHandler)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      this.zzaDK = new Messenger(paramHandler);
    }
    for (;;)
    {
      return;
      this.zzaDL = new zza(paramHandler);
    }
  }
  
  public MessengerCompat(IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      this.zzaDK = new Messenger(paramIBinder);
    }
    for (;;)
    {
      return;
      this.zzaDL = zzb.zza.zzbV(paramIBinder);
    }
  }
  
  public static int zzc(Message paramMessage)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (int i = zzd(paramMessage);; i = paramMessage.arg2) {
      return i;
    }
  }
  
  private static int zzd(Message paramMessage)
  {
    return paramMessage.sendingUid;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject == null) {}
    for (;;)
    {
      return bool1;
      try
      {
        boolean bool2 = getBinder().equals(((MessengerCompat)paramObject).getBinder());
        bool1 = bool2;
      }
      catch (ClassCastException localClassCastException) {}
    }
  }
  
  public IBinder getBinder()
  {
    if (this.zzaDK != null) {}
    for (IBinder localIBinder = this.zzaDK.getBinder();; localIBinder = this.zzaDL.asBinder()) {
      return localIBinder;
    }
  }
  
  public int hashCode()
  {
    return getBinder().hashCode();
  }
  
  public void send(Message paramMessage)
    throws RemoteException
  {
    if (this.zzaDK != null) {
      this.zzaDK.send(paramMessage);
    }
    for (;;)
    {
      return;
      this.zzaDL.send(paramMessage);
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.zzaDK != null) {
      paramParcel.writeStrongBinder(this.zzaDK.getBinder());
    }
    for (;;)
    {
      return;
      paramParcel.writeStrongBinder(this.zzaDL.asBinder());
    }
  }
  
  private final class zza
    extends zzb.zza
  {
    Handler handler;
    
    zza(Handler paramHandler)
    {
      this.handler = paramHandler;
    }
    
    public void send(Message paramMessage)
      throws RemoteException
    {
      paramMessage.arg2 = Binder.getCallingUid();
      this.handler.dispatchMessage(paramMessage);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/iid/MessengerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */