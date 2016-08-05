package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.GestureRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationRequestCreator;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzb;
import com.google.android.gms.location.zzd;
import com.google.android.gms.location.zzd.zza;
import java.util.ArrayList;
import java.util.List;

public abstract interface zzi
  extends IInterface
{
  public abstract Status zza(GestureRequest paramGestureRequest, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(PendingIntent paramPendingIntent, zzh paramzzh, String paramString)
    throws RemoteException;
  
  public abstract void zza(Location paramLocation, int paramInt)
    throws RemoteException;
  
  public abstract void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zzh paramzzh)
    throws RemoteException;
  
  public abstract void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(LocationRequest paramLocationRequest, zzd paramzzd)
    throws RemoteException;
  
  public abstract void zza(LocationRequest paramLocationRequest, zzd paramzzd, String paramString)
    throws RemoteException;
  
  public abstract void zza(LocationSettingsRequest paramLocationSettingsRequest, zzj paramzzj, String paramString)
    throws RemoteException;
  
  public abstract void zza(LocationRequestInternal paramLocationRequestInternal, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(LocationRequestInternal paramLocationRequestInternal, zzd paramzzd)
    throws RemoteException;
  
  public abstract void zza(LocationRequestUpdateData paramLocationRequestUpdateData)
    throws RemoteException;
  
  public abstract void zza(zzh paramzzh, String paramString)
    throws RemoteException;
  
  public abstract void zza(zzd paramzzd)
    throws RemoteException;
  
  public abstract void zza(List<ParcelableGeofence> paramList, PendingIntent paramPendingIntent, zzh paramzzh, String paramString)
    throws RemoteException;
  
  public abstract void zza(String[] paramArrayOfString, zzh paramzzh, String paramString)
    throws RemoteException;
  
  public abstract void zzah(boolean paramBoolean)
    throws RemoteException;
  
  public abstract Status zzb(PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract Status zzc(PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zzc(Location paramLocation)
    throws RemoteException;
  
  public abstract Status zzd(PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract ActivityRecognitionResult zzdu(String paramString)
    throws RemoteException;
  
  public abstract Location zzdv(String paramString)
    throws RemoteException;
  
  public abstract LocationAvailability zzdw(String paramString)
    throws RemoteException;
  
  public abstract void zze(PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract Location zzwC()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzi
  {
    public static zzi zzcb(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzi)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if ((localIInterface != null) && ((localIInterface instanceof zzi))) {
          localObject = (zzi)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool = false;
      Object localObject = null;
      int i = 1;
      switch (paramInt1)
      {
      default: 
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
      case 1: 
      case 57: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 64: 
      case 65: 
      case 66: 
      case 60: 
      case 61: 
      case 7: 
      case 8: 
      case 20: 
      case 9: 
      case 52: 
      case 53: 
      case 10: 
      case 11: 
      case 59: 
      case 12: 
      case 13: 
      case 21: 
      case 26: 
      case 34: 
        for (;;)
        {
          return i;
          paramParcel2.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          ArrayList localArrayList = paramParcel1.createTypedArrayList(ParcelableGeofence.CREATOR);
          if (paramParcel1.readInt() != 0) {}
          for (PendingIntent localPendingIntent12 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent12 = null)
          {
            zza(localArrayList, localPendingIntent12, zzh.zza.zzca(paramParcel1.readStrongBinder()), paramParcel1.readString());
            paramParcel2.writeNoException();
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          GeofencingRequest localGeofencingRequest;
          if (paramParcel1.readInt() != 0)
          {
            localGeofencingRequest = (GeofencingRequest)GeofencingRequest.CREATOR.createFromParcel(paramParcel1);
            label362:
            if (paramParcel1.readInt() == 0) {
              break label411;
            }
          }
          label411:
          for (PendingIntent localPendingIntent11 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent11 = null)
          {
            zza(localGeofencingRequest, localPendingIntent11, zzh.zza.zzca(paramParcel1.readStrongBinder()));
            paramParcel2.writeNoException();
            break;
            localGeofencingRequest = null;
            break label362;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0) {}
          for (PendingIntent localPendingIntent10 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent10 = null)
          {
            zza(localPendingIntent10, zzh.zza.zzca(paramParcel1.readStrongBinder()), paramParcel1.readString());
            paramParcel2.writeNoException();
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          zza(paramParcel1.createStringArray(), zzh.zza.zzca(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          zza(zzh.zza.zzca(paramParcel1.readStrongBinder()), paramParcel1.readString());
          paramParcel2.writeNoException();
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          long l = paramParcel1.readLong();
          if (paramParcel1.readInt() != 0) {
            bool = i;
          }
          if (paramParcel1.readInt() != 0) {}
          for (PendingIntent localPendingIntent9 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent9 = null)
          {
            zza(l, bool, localPendingIntent9);
            paramParcel2.writeNoException();
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramParcel1.readInt() != 0) {}
          for (PendingIntent localPendingIntent8 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent8 = null)
          {
            zza(localPendingIntent8);
            paramParcel2.writeNoException();
            break;
          }
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          ActivityRecognitionResult localActivityRecognitionResult = zzdu(paramParcel1.readString());
          paramParcel2.writeNoException();
          if (localActivityRecognitionResult != null)
          {
            paramParcel2.writeInt(i);
            localActivityRecognitionResult.writeToParcel(paramParcel2, i);
          }
          else
          {
            paramParcel2.writeInt(0);
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramParcel1.readInt() != 0) {}
            for (PendingIntent localPendingIntent7 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent7 = null)
            {
              Status localStatus4 = zzb(localPendingIntent7);
              paramParcel2.writeNoException();
              if (localStatus4 == null) {
                break label764;
              }
              paramParcel2.writeInt(i);
              localStatus4.writeToParcel(paramParcel2, i);
              break;
            }
            label764:
            paramParcel2.writeInt(0);
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramParcel1.readInt() != 0) {}
            for (PendingIntent localPendingIntent6 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent6 = null)
            {
              Status localStatus3 = zzc(localPendingIntent6);
              paramParcel2.writeNoException();
              if (localStatus3 == null) {
                break label839;
              }
              paramParcel2.writeInt(i);
              localStatus3.writeToParcel(paramParcel2, i);
              break;
            }
            label839:
            paramParcel2.writeInt(0);
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            GestureRequest localGestureRequest;
            if (paramParcel1.readInt() != 0)
            {
              localGestureRequest = GestureRequest.CREATOR.zzeA(paramParcel1);
              label869:
              if (paramParcel1.readInt() == 0) {
                break label932;
              }
            }
            label932:
            for (PendingIntent localPendingIntent5 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent5 = null)
            {
              Status localStatus2 = zza(localGestureRequest, localPendingIntent5);
              paramParcel2.writeNoException();
              if (localStatus2 == null) {
                break label938;
              }
              paramParcel2.writeInt(i);
              localStatus2.writeToParcel(paramParcel2, i);
              break;
              localGestureRequest = null;
              break label869;
            }
            label938:
            paramParcel2.writeInt(0);
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramParcel1.readInt() != 0) {}
            for (PendingIntent localPendingIntent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent4 = null)
            {
              Status localStatus1 = zzd(localPendingIntent4);
              paramParcel2.writeNoException();
              if (localStatus1 == null) {
                break label1013;
              }
              paramParcel2.writeInt(i);
              localStatus1.writeToParcel(paramParcel2, i);
              break;
            }
            label1013:
            paramParcel2.writeInt(0);
            continue;
            paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            Location localLocation4 = zzwC();
            paramParcel2.writeNoException();
            if (localLocation4 != null)
            {
              paramParcel2.writeInt(i);
              localLocation4.writeToParcel(paramParcel2, i);
            }
            else
            {
              paramParcel2.writeInt(0);
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              if (paramParcel1.readInt() != 0) {
                localObject = LocationRequest.CREATOR.createFromParcel(paramParcel1);
              }
              zza((LocationRequest)localObject, zzd.zza.zzbX(paramParcel1.readStrongBinder()));
              paramParcel2.writeNoException();
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              if (paramParcel1.readInt() != 0) {
                localObject = LocationRequest.CREATOR.createFromParcel(paramParcel1);
              }
              zza((LocationRequest)localObject, zzd.zza.zzbX(paramParcel1.readStrongBinder()), paramParcel1.readString());
              paramParcel2.writeNoException();
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              LocationRequest localLocationRequest;
              if (paramParcel1.readInt() != 0)
              {
                localLocationRequest = LocationRequest.CREATOR.createFromParcel(paramParcel1);
                label1177:
                if (paramParcel1.readInt() == 0) {
                  break label1219;
                }
              }
              label1219:
              for (PendingIntent localPendingIntent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent3 = null)
              {
                zza(localLocationRequest, localPendingIntent3);
                paramParcel2.writeNoException();
                break;
                localLocationRequest = null;
                break label1177;
              }
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              if (paramParcel1.readInt() != 0) {
                localObject = LocationRequestInternal.CREATOR.zzeH(paramParcel1);
              }
              zza((LocationRequestInternal)localObject, zzd.zza.zzbX(paramParcel1.readStrongBinder()));
              paramParcel2.writeNoException();
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              LocationRequestInternal localLocationRequestInternal;
              if (paramParcel1.readInt() != 0)
              {
                localLocationRequestInternal = LocationRequestInternal.CREATOR.zzeH(paramParcel1);
                label1289:
                if (paramParcel1.readInt() == 0) {
                  break label1331;
                }
              }
              label1331:
              for (PendingIntent localPendingIntent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent2 = null)
              {
                zza(localLocationRequestInternal, localPendingIntent2);
                paramParcel2.writeNoException();
                break;
                localLocationRequestInternal = null;
                break label1289;
              }
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              zza(zzd.zza.zzbX(paramParcel1.readStrongBinder()));
              paramParcel2.writeNoException();
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              if (paramParcel1.readInt() != 0) {}
              for (PendingIntent localPendingIntent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent1 = null)
              {
                zze(localPendingIntent1);
                paramParcel2.writeNoException();
                break;
              }
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              if (paramParcel1.readInt() != 0) {
                localObject = LocationRequestUpdateData.CREATOR.zzeI(paramParcel1);
              }
              zza((LocationRequestUpdateData)localObject);
              paramParcel2.writeNoException();
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              if (paramParcel1.readInt() != 0) {
                bool = i;
              }
              zzah(bool);
              paramParcel2.writeNoException();
              continue;
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              if (paramParcel1.readInt() != 0) {}
              for (Location localLocation3 = (Location)Location.CREATOR.createFromParcel(paramParcel1);; localLocation3 = null)
              {
                zzc(localLocation3);
                paramParcel2.writeNoException();
                break;
              }
              paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
              Location localLocation2 = zzdv(paramParcel1.readString());
              paramParcel2.writeNoException();
              if (localLocation2 != null)
              {
                paramParcel2.writeInt(i);
                localLocation2.writeToParcel(paramParcel2, i);
              }
              else
              {
                paramParcel2.writeInt(0);
                continue;
                paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                if (paramParcel1.readInt() != 0) {}
                for (Location localLocation1 = (Location)Location.CREATOR.createFromParcel(paramParcel1);; localLocation1 = null)
                {
                  zza(localLocation1, paramParcel1.readInt());
                  paramParcel2.writeNoException();
                  break;
                }
                paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                LocationAvailability localLocationAvailability = zzdw(paramParcel1.readString());
                paramParcel2.writeNoException();
                if (localLocationAvailability != null)
                {
                  paramParcel2.writeInt(i);
                  localLocationAvailability.writeToParcel(paramParcel2, i);
                }
                else
                {
                  paramParcel2.writeInt(0);
                }
              }
            }
          }
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
      if (paramParcel1.readInt() != 0) {}
      for (LocationSettingsRequest localLocationSettingsRequest = (LocationSettingsRequest)LocationSettingsRequest.CREATOR.createFromParcel(paramParcel1);; localLocationSettingsRequest = null)
      {
        zza(localLocationSettingsRequest, zzj.zza.zzcc(paramParcel1.readStrongBinder()), paramParcel1.readString());
        paramParcel2.writeNoException();
        break;
      }
    }
    
    private static class zza
      implements zzi
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
      
      public Status zza(GestureRequest paramGestureRequest, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramGestureRequest != null)
            {
              localParcel1.writeInt(1);
              paramGestureRequest.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zznJ.transact(60, localParcel1, localParcel2, 0);
                localParcel2.readException();
                if (localParcel2.readInt() == 0) {
                  break label131;
                }
                localStatus = (Status)Status.CREATOR.createFromParcel(localParcel2);
                return localStatus;
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
          label131:
          Status localStatus = null;
        }
      }
      
      /* Error */
      public void zza(long paramLong, boolean paramBoolean, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 5
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 31
        //   17: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 6
        //   22: lload_1
        //   23: invokevirtual 81	android/os/Parcel:writeLong	(J)V
        //   26: iload_3
        //   27: ifeq +61 -> 88
        //   30: aload 6
        //   32: iload 5
        //   34: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   37: aload 4
        //   39: ifnull +55 -> 94
        //   42: aload 6
        //   44: iconst_1
        //   45: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   48: aload 4
        //   50: aload 6
        //   52: iconst_0
        //   53: invokevirtual 48	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   56: aload_0
        //   57: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   60: iconst_5
        //   61: aload 6
        //   63: aload 7
        //   65: iconst_0
        //   66: invokeinterface 54 5 0
        //   71: pop
        //   72: aload 7
        //   74: invokevirtual 57	android/os/Parcel:readException	()V
        //   77: aload 7
        //   79: invokevirtual 76	android/os/Parcel:recycle	()V
        //   82: aload 6
        //   84: invokevirtual 76	android/os/Parcel:recycle	()V
        //   87: return
        //   88: iconst_0
        //   89: istore 5
        //   91: goto -61 -> 30
        //   94: aload 6
        //   96: iconst_0
        //   97: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   100: goto -44 -> 56
        //   103: astore 8
        //   105: aload 7
        //   107: invokevirtual 76	android/os/Parcel:recycle	()V
        //   110: aload 6
        //   112: invokevirtual 76	android/os/Parcel:recycle	()V
        //   115: aload 8
        //   117: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	118	0	this	zza
        //   0	118	1	paramLong	long
        //   0	118	3	paramBoolean	boolean
        //   0	118	4	paramPendingIntent	PendingIntent
        //   1	89	5	i	int
        //   6	105	6	localParcel1	Parcel
        //   11	95	7	localParcel2	Parcel
        //   103	13	8	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   13	77	103	finally
        //   94	100	103	finally
      }
      
      /* Error */
      public void zza(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 48	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: bipush 6
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 54 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 57	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 76	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 76	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 76	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 76	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramPendingIntent	PendingIntent
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	48	65	finally
        //   57	62	65	finally
      }
      
      public void zza(PendingIntent paramPendingIntent, zzh paramzzh, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramPendingIntent != null)
            {
              localParcel1.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel1, 0);
              if (paramzzh != null)
              {
                localIBinder = paramzzh.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                localParcel1.writeString(paramString);
                this.zznJ.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zza(Location paramLocation, int paramInt)
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
        //   16: ifnull +50 -> 66
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   24: aload_1
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 97	android/location/Location:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_3
        //   31: iload_2
        //   32: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   39: bipush 26
        //   41: aload_3
        //   42: aload 4
        //   44: iconst_0
        //   45: invokeinterface 54 5 0
        //   50: pop
        //   51: aload 4
        //   53: invokevirtual 57	android/os/Parcel:readException	()V
        //   56: aload 4
        //   58: invokevirtual 76	android/os/Parcel:recycle	()V
        //   61: aload_3
        //   62: invokevirtual 76	android/os/Parcel:recycle	()V
        //   65: return
        //   66: aload_3
        //   67: iconst_0
        //   68: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   71: goto -41 -> 30
        //   74: astore 5
        //   76: aload 4
        //   78: invokevirtual 76	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 76	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	zza
        //   0	88	1	paramLocation	Location
        //   0	88	2	paramInt	int
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        //   74	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   9	56	74	finally
        //   66	71	74	finally
      }
      
      public void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zzh paramzzh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramGeofencingRequest != null)
            {
              localParcel1.writeInt(1);
              paramGeofencingRequest.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzh == null) {
                  break label136;
                }
                localIBinder = paramzzh.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(57, localParcel1, localParcel2, 0);
                localParcel2.readException();
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
          label136:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest != null)
            {
              localParcel1.writeInt(1);
              paramLocationRequest.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zznJ.transact(9, localParcel1, localParcel2, 0);
                localParcel2.readException();
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
        }
      }
      
      public void zza(LocationRequest paramLocationRequest, zzd paramzzd)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest != null)
            {
              localParcel1.writeInt(1);
              paramLocationRequest.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                localIBinder = paramzzd.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(8, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(LocationRequest paramLocationRequest, zzd paramzzd, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequest != null)
            {
              localParcel1.writeInt(1);
              paramLocationRequest.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                localIBinder = paramzzd.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                localParcel1.writeString(paramString);
                this.zznJ.transact(20, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(LocationSettingsRequest paramLocationSettingsRequest, zzj paramzzj, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationSettingsRequest != null)
            {
              localParcel1.writeInt(1);
              paramLocationSettingsRequest.writeToParcel(localParcel1, 0);
              if (paramzzj != null)
              {
                localIBinder = paramzzj.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                localParcel1.writeString(paramString);
                this.zznJ.transact(63, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(LocationRequestInternal paramLocationRequestInternal, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequestInternal != null)
            {
              localParcel1.writeInt(1);
              paramLocationRequestInternal.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zznJ.transact(53, localParcel1, localParcel2, 0);
                localParcel2.readException();
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
        }
      }
      
      public void zza(LocationRequestInternal paramLocationRequestInternal, zzd paramzzd)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramLocationRequestInternal != null)
            {
              localParcel1.writeInt(1);
              paramLocationRequestInternal.writeToParcel(localParcel1, 0);
              if (paramzzd != null)
              {
                localIBinder = paramzzd.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(52, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zza(LocationRequestUpdateData paramLocationRequestUpdateData)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 126	com/google/android/gms/location/internal/LocationRequestUpdateData:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: bipush 59
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 54 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 57	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 76	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 76	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 76	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 76	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramLocationRequestUpdateData	LocationRequestUpdateData
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	48	65	finally
        //   57	62	65	finally
      }
      
      /* Error */
      public void zza(zzh paramzzh, String paramString)
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
        //   20: invokeinterface 87 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: aload_2
        //   35: invokevirtual 93	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   38: aload_0
        //   39: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   42: iconst_4
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 54 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 57	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 76	android/os/Parcel:recycle	()V
        //   63: aload_3
        //   64: invokevirtual 76	android/os/Parcel:recycle	()V
        //   67: return
        //   68: aconst_null
        //   69: astore 6
        //   71: goto -44 -> 27
        //   74: astore 5
        //   76: aload 4
        //   78: invokevirtual 76	android/os/Parcel:recycle	()V
        //   81: aload_3
        //   82: invokevirtual 76	android/os/Parcel:recycle	()V
        //   85: aload 5
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	zza
        //   0	88	1	paramzzh	zzh
        //   0	88	2	paramString	String
        //   3	79	3	localParcel1	Parcel
        //   7	70	4	localParcel2	Parcel
        //   74	12	5	localObject	Object
        //   25	45	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	58	74	finally
      }
      
      /* Error */
      public void zza(zzd paramzzd)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 109 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: bipush 10
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 54 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 57	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 76	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 76	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 76	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 76	android/os/Parcel:recycle	()V
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
      
      public void zza(List<ParcelableGeofence> paramList, PendingIntent paramPendingIntent, zzh paramzzh, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            localParcel1.writeTypedList(paramList);
            if (paramPendingIntent != null)
            {
              localParcel1.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel1, 0);
              if (paramzzh != null)
              {
                localIBinder = paramzzh.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                localParcel1.writeString(paramString);
                this.zznJ.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zza(String[] paramArrayOfString, zzh paramzzh, String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 31
        //   14: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 4
        //   19: aload_1
        //   20: invokevirtual 138	android/os/Parcel:writeStringArray	([Ljava/lang/String;)V
        //   23: aload_2
        //   24: ifnull +56 -> 80
        //   27: aload_2
        //   28: invokeinterface 87 1 0
        //   33: astore 7
        //   35: aload 4
        //   37: aload 7
        //   39: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   42: aload 4
        //   44: aload_3
        //   45: invokevirtual 93	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   48: aload_0
        //   49: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   52: iconst_3
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 54 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 57	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 76	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: invokevirtual 76	android/os/Parcel:recycle	()V
        //   79: return
        //   80: aconst_null
        //   81: astore 7
        //   83: goto -48 -> 35
        //   86: astore 6
        //   88: aload 5
        //   90: invokevirtual 76	android/os/Parcel:recycle	()V
        //   93: aload 4
        //   95: invokevirtual 76	android/os/Parcel:recycle	()V
        //   98: aload 6
        //   100: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	101	0	this	zza
        //   0	101	1	paramArrayOfString	String[]
        //   0	101	2	paramzzh	zzh
        //   0	101	3	paramString	String
        //   3	91	4	localParcel1	Parcel
        //   8	81	5	localParcel2	Parcel
        //   86	13	6	localObject	Object
        //   33	49	7	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   10	69	86	finally
      }
      
      public void zzah(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.zznJ.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public Status zzb(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramPendingIntent != null)
            {
              localParcel1.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel1, 0);
              this.zznJ.transact(65, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localStatus = (Status)Status.CREATOR.createFromParcel(localParcel2);
                return localStatus;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Status localStatus = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public Status zzc(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramPendingIntent != null)
            {
              localParcel1.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel1, 0);
              this.zznJ.transact(66, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localStatus = (Status)Status.CREATOR.createFromParcel(localParcel2);
                return localStatus;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Status localStatus = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zzc(Location paramLocation)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 97	android/location/Location:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: bipush 13
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 54 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 57	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 76	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 76	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 76	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 76	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramLocation	Location
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	48	65	finally
        //   57	62	65	finally
      }
      
      public Status zzd(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.internal.IGoogleLocationManagerService");
            if (paramPendingIntent != null)
            {
              localParcel1.writeInt(1);
              paramPendingIntent.writeToParcel(localParcel1, 0);
              this.zznJ.transact(61, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localStatus = (Status)Status.CREATOR.createFromParcel(localParcel2);
                return localStatus;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Status localStatus = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public ActivityRecognitionResult zzdu(String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 93	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   23: bipush 64
        //   25: aload_2
        //   26: aload_3
        //   27: iconst_0
        //   28: invokeinterface 54 5 0
        //   33: pop
        //   34: aload_3
        //   35: invokevirtual 57	android/os/Parcel:readException	()V
        //   38: aload_3
        //   39: invokevirtual 61	android/os/Parcel:readInt	()I
        //   42: ifeq +27 -> 69
        //   45: getstatic 152	com/google/android/gms/location/ActivityRecognitionResult:CREATOR	Lcom/google/android/gms/location/ActivityRecognitionResultCreator;
        //   48: aload_3
        //   49: invokevirtual 157	com/google/android/gms/location/ActivityRecognitionResultCreator:createFromParcel	(Landroid/os/Parcel;)Lcom/google/android/gms/location/ActivityRecognitionResult;
        //   52: astore 7
        //   54: aload 7
        //   56: astore 6
        //   58: aload_3
        //   59: invokevirtual 76	android/os/Parcel:recycle	()V
        //   62: aload_2
        //   63: invokevirtual 76	android/os/Parcel:recycle	()V
        //   66: aload 6
        //   68: areturn
        //   69: aconst_null
        //   70: astore 6
        //   72: goto -14 -> 58
        //   75: astore 4
        //   77: aload_3
        //   78: invokevirtual 76	android/os/Parcel:recycle	()V
        //   81: aload_2
        //   82: invokevirtual 76	android/os/Parcel:recycle	()V
        //   85: aload 4
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	zza
        //   0	88	1	paramString	String
        //   3	79	2	localParcel1	Parcel
        //   7	71	3	localParcel2	Parcel
        //   75	11	4	localObject	Object
        //   56	15	6	localActivityRecognitionResult1	ActivityRecognitionResult
        //   52	3	7	localActivityRecognitionResult2	ActivityRecognitionResult
        // Exception table:
        //   from	to	target	type
        //   8	54	75	finally
      }
      
      /* Error */
      public Location zzdv(String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 93	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   23: bipush 21
        //   25: aload_2
        //   26: aload_3
        //   27: iconst_0
        //   28: invokeinterface 54 5 0
        //   33: pop
        //   34: aload_3
        //   35: invokevirtual 57	android/os/Parcel:readException	()V
        //   38: aload_3
        //   39: invokevirtual 61	android/os/Parcel:readInt	()I
        //   42: ifeq +28 -> 70
        //   45: getstatic 160	android/location/Location:CREATOR	Landroid/os/Parcelable$Creator;
        //   48: aload_3
        //   49: invokeinterface 73 2 0
        //   54: checkcast 96	android/location/Location
        //   57: astore 6
        //   59: aload_3
        //   60: invokevirtual 76	android/os/Parcel:recycle	()V
        //   63: aload_2
        //   64: invokevirtual 76	android/os/Parcel:recycle	()V
        //   67: aload 6
        //   69: areturn
        //   70: aconst_null
        //   71: astore 6
        //   73: goto -14 -> 59
        //   76: astore 4
        //   78: aload_3
        //   79: invokevirtual 76	android/os/Parcel:recycle	()V
        //   82: aload_2
        //   83: invokevirtual 76	android/os/Parcel:recycle	()V
        //   86: aload 4
        //   88: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	89	0	this	zza
        //   0	89	1	paramString	String
        //   3	80	2	localParcel1	Parcel
        //   7	72	3	localParcel2	Parcel
        //   76	11	4	localObject	Object
        //   57	15	6	localLocation	Location
        // Exception table:
        //   from	to	target	type
        //   8	59	76	finally
      }
      
      /* Error */
      public LocationAvailability zzdw(String paramString)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_2
        //   15: aload_1
        //   16: invokevirtual 93	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   19: aload_0
        //   20: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   23: bipush 34
        //   25: aload_2
        //   26: aload_3
        //   27: iconst_0
        //   28: invokeinterface 54 5 0
        //   33: pop
        //   34: aload_3
        //   35: invokevirtual 57	android/os/Parcel:readException	()V
        //   38: aload_3
        //   39: invokevirtual 61	android/os/Parcel:readInt	()I
        //   42: ifeq +27 -> 69
        //   45: getstatic 167	com/google/android/gms/location/LocationAvailability:CREATOR	Lcom/google/android/gms/location/LocationAvailabilityCreator;
        //   48: aload_3
        //   49: invokevirtual 172	com/google/android/gms/location/LocationAvailabilityCreator:createFromParcel	(Landroid/os/Parcel;)Lcom/google/android/gms/location/LocationAvailability;
        //   52: astore 7
        //   54: aload 7
        //   56: astore 6
        //   58: aload_3
        //   59: invokevirtual 76	android/os/Parcel:recycle	()V
        //   62: aload_2
        //   63: invokevirtual 76	android/os/Parcel:recycle	()V
        //   66: aload 6
        //   68: areturn
        //   69: aconst_null
        //   70: astore 6
        //   72: goto -14 -> 58
        //   75: astore 4
        //   77: aload_3
        //   78: invokevirtual 76	android/os/Parcel:recycle	()V
        //   81: aload_2
        //   82: invokevirtual 76	android/os/Parcel:recycle	()V
        //   85: aload 4
        //   87: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	88	0	this	zza
        //   0	88	1	paramString	String
        //   3	79	2	localParcel1	Parcel
        //   7	71	3	localParcel2	Parcel
        //   75	11	4	localObject	Object
        //   56	15	6	localLocationAvailability1	LocationAvailability
        //   52	3	7	localLocationAvailability2	LocationAvailability
        // Exception table:
        //   from	to	target	type
        //   8	54	75	finally
      }
      
      /* Error */
      public void zze(PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 48	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: bipush 11
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 54 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 57	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 76	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 76	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 76	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 76	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramPendingIntent	PendingIntent
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	48	65	finally
        //   57	62	65	finally
      }
      
      /* Error */
      public Location zzwC()
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
        //   15: getfield 18	com/google/android/gms/location/internal/zzi$zza$zza:zznJ	Landroid/os/IBinder;
        //   18: bipush 7
        //   20: aload_1
        //   21: aload_2
        //   22: iconst_0
        //   23: invokeinterface 54 5 0
        //   28: pop
        //   29: aload_2
        //   30: invokevirtual 57	android/os/Parcel:readException	()V
        //   33: aload_2
        //   34: invokevirtual 61	android/os/Parcel:readInt	()I
        //   37: ifeq +28 -> 65
        //   40: getstatic 160	android/location/Location:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_2
        //   44: invokeinterface 73 2 0
        //   49: checkcast 96	android/location/Location
        //   52: astore 5
        //   54: aload_2
        //   55: invokevirtual 76	android/os/Parcel:recycle	()V
        //   58: aload_1
        //   59: invokevirtual 76	android/os/Parcel:recycle	()V
        //   62: aload 5
        //   64: areturn
        //   65: aconst_null
        //   66: astore 5
        //   68: goto -14 -> 54
        //   71: astore_3
        //   72: aload_2
        //   73: invokevirtual 76	android/os/Parcel:recycle	()V
        //   76: aload_1
        //   77: invokevirtual 76	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	zza
        //   3	74	1	localParcel1	Parcel
        //   7	66	2	localParcel2	Parcel
        //   71	10	3	localObject	Object
        //   52	15	5	localLocation	Location
        // Exception table:
        //   from	to	target	type
        //   8	54	71	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */