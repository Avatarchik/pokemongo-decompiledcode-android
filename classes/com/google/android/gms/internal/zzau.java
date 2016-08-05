package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public abstract interface zzau
  extends IInterface
{
  public abstract Bundle zza(Account paramAccount)
    throws RemoteException;
  
  public abstract Bundle zza(Account paramAccount, String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract Bundle zza(Bundle paramBundle)
    throws RemoteException;
  
  public abstract Bundle zza(String paramString, Bundle paramBundle)
    throws RemoteException;
  
  public abstract Bundle zza(String paramString1, String paramString2, Bundle paramBundle)
    throws RemoteException;
  
  public abstract AccountChangeEventsResponse zza(AccountChangeEventsRequest paramAccountChangeEventsRequest)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzau
  {
    public static zzau zza(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzau)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        if ((localIInterface != null) && ((localIInterface instanceof zzau))) {
          localObject = (zzau)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
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
          paramParcel2.writeString("com.google.android.auth.IAuthManagerService");
        }
      case 1: 
        paramParcel1.enforceInterface("com.google.android.auth.IAuthManagerService");
        String str3 = paramParcel1.readString();
        String str4 = paramParcel1.readString();
        Bundle localBundle8;
        if (paramParcel1.readInt() != 0)
        {
          localBundle8 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          Bundle localBundle9 = zza(str3, str4, localBundle8);
          paramParcel2.writeNoException();
          if (localBundle9 == null) {
            break label178;
          }
          paramParcel2.writeInt(1);
          localBundle9.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          localBundle8 = null;
          break label133;
          paramParcel2.writeInt(0);
        }
      case 2: 
        paramParcel1.enforceInterface("com.google.android.auth.IAuthManagerService");
        String str2 = paramParcel1.readString();
        Bundle localBundle6;
        if (paramParcel1.readInt() != 0)
        {
          localBundle6 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          Bundle localBundle7 = zza(str2, localBundle6);
          paramParcel2.writeNoException();
          if (localBundle7 == null) {
            break label262;
          }
          paramParcel2.writeInt(1);
          localBundle7.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          localBundle6 = null;
          break label219;
          paramParcel2.writeInt(0);
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.auth.IAuthManagerService");
        AccountChangeEventsRequest localAccountChangeEventsRequest;
        if (paramParcel1.readInt() != 0)
        {
          localAccountChangeEventsRequest = (AccountChangeEventsRequest)AccountChangeEventsRequest.CREATOR.createFromParcel(paramParcel1);
          AccountChangeEventsResponse localAccountChangeEventsResponse = zza(localAccountChangeEventsRequest);
          paramParcel2.writeNoException();
          if (localAccountChangeEventsResponse == null) {
            break label338;
          }
          paramParcel2.writeInt(1);
          localAccountChangeEventsResponse.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          localAccountChangeEventsRequest = null;
          break label297;
          paramParcel2.writeInt(0);
        }
      case 5: 
        paramParcel1.enforceInterface("com.google.android.auth.IAuthManagerService");
        Account localAccount2;
        Bundle localBundle4;
        if (paramParcel1.readInt() != 0)
        {
          localAccount2 = (Account)Account.CREATOR.createFromParcel(paramParcel1);
          String str1 = paramParcel1.readString();
          if (paramParcel1.readInt() == 0) {
            break label445;
          }
          localBundle4 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          Bundle localBundle5 = zza(localAccount2, str1, localBundle4);
          paramParcel2.writeNoException();
          if (localBundle5 == null) {
            break label451;
          }
          paramParcel2.writeInt(1);
          localBundle5.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          localAccount2 = null;
          break label373;
          localBundle4 = null;
          break label400;
          paramParcel2.writeInt(0);
        }
      case 6: 
        label133:
        label178:
        label219:
        label262:
        label297:
        label338:
        label373:
        label400:
        label445:
        label451:
        paramParcel1.enforceInterface("com.google.android.auth.IAuthManagerService");
        Bundle localBundle2;
        if (paramParcel1.readInt() != 0)
        {
          localBundle2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          label486:
          Bundle localBundle3 = zza(localBundle2);
          paramParcel2.writeNoException();
          if (localBundle3 == null) {
            break label527;
          }
          paramParcel2.writeInt(1);
          localBundle3.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          localBundle2 = null;
          break label486;
          label527:
          paramParcel2.writeInt(0);
        }
      }
      paramParcel1.enforceInterface("com.google.android.auth.IAuthManagerService");
      Account localAccount1;
      if (paramParcel1.readInt() != 0)
      {
        localAccount1 = (Account)Account.CREATOR.createFromParcel(paramParcel1);
        label562:
        Bundle localBundle1 = zza(localAccount1);
        paramParcel2.writeNoException();
        if (localBundle1 == null) {
          break label603;
        }
        paramParcel2.writeInt(1);
        localBundle1.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        bool = true;
        break;
        localAccount1 = null;
        break label562;
        label603:
        paramParcel2.writeInt(0);
      }
    }
    
    private static class zza
      implements zzau
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
      
      public Bundle zza(Account paramAccount)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
            if (paramAccount != null)
            {
              localParcel1.writeInt(1);
              paramAccount.writeToParcel(localParcel1, 0);
              this.zznJ.transact(7, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
                return localBundle;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Bundle localBundle = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Bundle zza(Account paramAccount, String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
            if (paramAccount != null)
            {
              localParcel1.writeInt(1);
              paramAccount.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString);
              if (paramBundle != null)
              {
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                this.zznJ.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
                if (localParcel2.readInt() == 0) {
                  break label147;
                }
                localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
                return localBundle;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label147:
          Bundle localBundle = null;
        }
      }
      
      public Bundle zza(Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              this.zznJ.transact(6, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
                return localBundle;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Bundle localBundle = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Bundle zza(String paramString, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
            localParcel1.writeString(paramString);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              this.zznJ.transact(2, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
                return localBundle;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Bundle localBundle = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Bundle zza(String paramString1, String paramString2, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              this.zznJ.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localBundle = (Bundle)Bundle.CREATOR.createFromParcel(localParcel2);
                return localBundle;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Bundle localBundle = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public AccountChangeEventsResponse zza(AccountChangeEventsRequest paramAccountChangeEventsRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.auth.IAuthManagerService");
            if (paramAccountChangeEventsRequest != null)
            {
              localParcel1.writeInt(1);
              paramAccountChangeEventsRequest.writeToParcel(localParcel1, 0);
              this.zznJ.transact(3, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localAccountChangeEventsResponse = (AccountChangeEventsResponse)AccountChangeEventsResponse.CREATOR.createFromParcel(localParcel2);
                return localAccountChangeEventsResponse;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            AccountChangeEventsResponse localAccountChangeEventsResponse = null;
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
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */