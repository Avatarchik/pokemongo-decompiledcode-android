package com.android.vending.billing;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IInAppBillingService
  extends IInterface
{
  public abstract int consumePurchase(int paramInt, String paramString1, String paramString2)
    throws RemoteException;
  
  public abstract Bundle getBuyIntent(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
    throws RemoteException;
  
  public abstract Bundle getPurchases(int paramInt, String paramString1, String paramString2, String paramString3)
    throws RemoteException;
  
  public abstract Bundle getSkuDetails(int paramInt, String paramString1, String paramString2, Bundle paramBundle)
    throws RemoteException;
  
  public abstract int isBillingSupported(int paramInt, String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IInAppBillingService
  {
    private static final String DESCRIPTOR = "com.android.vending.billing.IInAppBillingService";
    static final int TRANSACTION_consumePurchase = 5;
    static final int TRANSACTION_getBuyIntent = 3;
    static final int TRANSACTION_getPurchases = 4;
    static final int TRANSACTION_getSkuDetails = 2;
    static final int TRANSACTION_isBillingSupported = 1;
    
    public Stub()
    {
      attachInterface(this, "com.android.vending.billing.IInAppBillingService");
    }
    
    public static IInAppBillingService asInterface(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (IInAppBillingService)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.billing.IInAppBillingService");
        if ((localIInterface != null) && ((localIInterface instanceof IInAppBillingService))) {
          localObject = (IInAppBillingService)localIInterface;
        } else {
          localObject = new Proxy(paramIBinder);
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
        paramParcel2.writeString("com.android.vending.billing.IInAppBillingService");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
        int k = isBillingSupported(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(k);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
        int j = paramParcel1.readInt();
        String str1 = paramParcel1.readString();
        String str2 = paramParcel1.readString();
        Bundle localBundle3;
        if (paramParcel1.readInt() != 0)
        {
          localBundle3 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          label171:
          Bundle localBundle4 = getSkuDetails(j, str1, str2, localBundle3);
          paramParcel2.writeNoException();
          if (localBundle4 == null) {
            break label218;
          }
          paramParcel2.writeInt(1);
          localBundle4.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          localBundle3 = null;
          break label171;
          label218:
          paramParcel2.writeInt(0);
        }
        paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
        Bundle localBundle2 = getBuyIntent(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localBundle2 != null)
        {
          paramParcel2.writeInt(1);
          localBundle2.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          paramParcel2.writeInt(0);
        }
        paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
        Bundle localBundle1 = getPurchases(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localBundle1 != null)
        {
          paramParcel2.writeInt(1);
          localBundle1.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          paramParcel2.writeInt(0);
        }
        paramParcel1.enforceInterface("com.android.vending.billing.IInAppBillingService");
        int i = consumePurchase(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i);
        bool = true;
      }
    }
    
    private static class Proxy
      implements IInAppBillingService
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public int consumePurchase(int paramInt, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
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
      
      /* Error */
      public Bundle getBuyIntent(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 6
        //   19: iload_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload 6
        //   25: aload_2
        //   26: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload 6
        //   31: aload_3
        //   32: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   35: aload 6
        //   37: aload 4
        //   39: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 6
        //   44: aload 5
        //   46: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   49: aload_0
        //   50: getfield 18	com/android/vending/billing/IInAppBillingService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   53: iconst_3
        //   54: aload 6
        //   56: aload 7
        //   58: iconst_0
        //   59: invokeinterface 49 5 0
        //   64: pop
        //   65: aload 7
        //   67: invokevirtual 52	android/os/Parcel:readException	()V
        //   70: aload 7
        //   72: invokevirtual 56	android/os/Parcel:readInt	()I
        //   75: ifeq +31 -> 106
        //   78: getstatic 67	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   81: aload 7
        //   83: invokeinterface 73 2 0
        //   88: checkcast 63	android/os/Bundle
        //   91: astore 10
        //   93: aload 7
        //   95: invokevirtual 59	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: invokevirtual 59	android/os/Parcel:recycle	()V
        //   103: aload 10
        //   105: areturn
        //   106: aconst_null
        //   107: astore 10
        //   109: goto -16 -> 93
        //   112: astore 8
        //   114: aload 7
        //   116: invokevirtual 59	android/os/Parcel:recycle	()V
        //   119: aload 6
        //   121: invokevirtual 59	android/os/Parcel:recycle	()V
        //   124: aload 8
        //   126: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	127	0	this	Proxy
        //   0	127	1	paramInt	int
        //   0	127	2	paramString1	String
        //   0	127	3	paramString2	String
        //   0	127	4	paramString3	String
        //   0	127	5	paramString4	String
        //   3	117	6	localParcel1	Parcel
        //   8	107	7	localParcel2	Parcel
        //   112	13	8	localObject	Object
        //   91	17	10	localBundle	Bundle
        // Exception table:
        //   from	to	target	type
        //   10	93	112	finally
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.android.vending.billing.IInAppBillingService";
      }
      
      /* Error */
      public Bundle getPurchases(int paramInt, String paramString1, String paramString2, String paramString3)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: iload_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload 5
        //   25: aload_2
        //   26: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload 5
        //   31: aload_3
        //   32: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   35: aload 5
        //   37: aload 4
        //   39: invokevirtual 43	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload_0
        //   43: getfield 18	com/android/vending/billing/IInAppBillingService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   46: iconst_4
        //   47: aload 5
        //   49: aload 6
        //   51: iconst_0
        //   52: invokeinterface 49 5 0
        //   57: pop
        //   58: aload 6
        //   60: invokevirtual 52	android/os/Parcel:readException	()V
        //   63: aload 6
        //   65: invokevirtual 56	android/os/Parcel:readInt	()I
        //   68: ifeq +31 -> 99
        //   71: getstatic 67	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   74: aload 6
        //   76: invokeinterface 73 2 0
        //   81: checkcast 63	android/os/Bundle
        //   84: astore 9
        //   86: aload 6
        //   88: invokevirtual 59	android/os/Parcel:recycle	()V
        //   91: aload 5
        //   93: invokevirtual 59	android/os/Parcel:recycle	()V
        //   96: aload 9
        //   98: areturn
        //   99: aconst_null
        //   100: astore 9
        //   102: goto -16 -> 86
        //   105: astore 7
        //   107: aload 6
        //   109: invokevirtual 59	android/os/Parcel:recycle	()V
        //   112: aload 5
        //   114: invokevirtual 59	android/os/Parcel:recycle	()V
        //   117: aload 7
        //   119: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	120	0	this	Proxy
        //   0	120	1	paramInt	int
        //   0	120	2	paramString1	String
        //   0	120	3	paramString2	String
        //   0	120	4	paramString3	String
        //   3	110	5	localParcel1	Parcel
        //   8	100	6	localParcel2	Parcel
        //   105	13	7	localObject	Object
        //   84	17	9	localBundle	Bundle
        // Exception table:
        //   from	to	target	type
        //   10	86	105	finally
      }
      
      public Bundle getSkuDetails(int paramInt, String paramString1, String paramString2, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
            localParcel1.writeInt(paramInt);
            localParcel1.writeString(paramString1);
            localParcel1.writeString(paramString2);
            if (paramBundle != null)
            {
              localParcel1.writeInt(1);
              paramBundle.writeToParcel(localParcel1, 0);
              this.mRemote.transact(2, localParcel1, localParcel2, 0);
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
      
      public int isBillingSupported(int paramInt, String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.android.vending.billing.IInAppBillingService");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
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
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/android/vending/billing/IInAppBillingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */