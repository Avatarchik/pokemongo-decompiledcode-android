package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.zza;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzj<T extends IInterface>
  implements Api.zzb, zzk.zza
{
  public static final String[] zzafI;
  private final Context mContext;
  final Handler mHandler;
  private final Account zzQd;
  private final Set<Scope> zzTm;
  private final Looper zzaaO;
  private final GoogleApiAvailability zzaaP;
  private final zzf zzabI;
  private T zzafA;
  private final ArrayList<zzj<T>.zzc<?>> zzafB = new ArrayList();
  private zzj<T>.zze zzafC;
  private int zzafD = 1;
  private final GoogleApiClient.ConnectionCallbacks zzafE;
  private final GoogleApiClient.OnConnectionFailedListener zzafF;
  private final int zzafG;
  protected AtomicInteger zzafH = new AtomicInteger(0);
  private final zzl zzafx;
  private zzs zzafy;
  private GoogleApiClient.zza zzafz;
  private final Object zzpd = new Object();
  
  static
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "service_esmobile";
    arrayOfString[1] = "service_googleme";
    zzafI = arrayOfString;
  }
  
  protected zzj(Context paramContext, Looper paramLooper, int paramInt, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramLooper, zzl.zzal(paramContext), GoogleApiAvailability.getInstance(), paramInt, paramzzf, (GoogleApiClient.ConnectionCallbacks)zzx.zzw(paramConnectionCallbacks), (GoogleApiClient.OnConnectionFailedListener)zzx.zzw(paramOnConnectionFailedListener));
  }
  
  protected zzj(Context paramContext, Looper paramLooper, zzl paramzzl, GoogleApiAvailability paramGoogleApiAvailability, int paramInt, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.mContext = ((Context)zzx.zzb(paramContext, "Context must not be null"));
    this.zzaaO = ((Looper)zzx.zzb(paramLooper, "Looper must not be null"));
    this.zzafx = ((zzl)zzx.zzb(paramzzl, "Supervisor must not be null"));
    this.zzaaP = ((GoogleApiAvailability)zzx.zzb(paramGoogleApiAvailability, "API availability must not be null"));
    this.mHandler = new zzb(paramLooper);
    this.zzafG = paramInt;
    this.zzabI = ((zzf)zzx.zzw(paramzzf));
    this.zzQd = paramzzf.getAccount();
    this.zzTm = zza(paramzzf.zzoL());
    this.zzafE = paramConnectionCallbacks;
    this.zzafF = paramOnConnectionFailedListener;
  }
  
  private Set<Scope> zza(Set<Scope> paramSet)
  {
    Set localSet1 = zzb(paramSet);
    if (localSet1 == null) {}
    for (Set localSet2 = localSet1;; localSet2 = localSet1)
    {
      return localSet2;
      Iterator localIterator = localSet1.iterator();
      while (localIterator.hasNext()) {
        if (!paramSet.contains((Scope)localIterator.next())) {
          throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
        }
      }
    }
  }
  
  private boolean zza(int paramInt1, int paramInt2, T paramT)
  {
    boolean bool;
    synchronized (this.zzpd)
    {
      if (this.zzafD != paramInt1)
      {
        bool = false;
      }
      else
      {
        zzb(paramInt2, paramT);
        bool = true;
      }
    }
    return bool;
  }
  
  private void zzb(int paramInt, T paramT)
  {
    boolean bool1 = true;
    boolean bool2;
    boolean bool3;
    if (paramInt == 3)
    {
      bool2 = bool1;
      if (paramT == null) {
        break label119;
      }
      bool3 = bool1;
      if (bool2 != bool3) {
        break label125;
      }
      zzx.zzaa(bool1);
    }
    label119:
    label125:
    synchronized (this.zzpd)
    {
      this.zzafD = paramInt;
      this.zzafA = paramT;
      zzc(paramInt, paramT);
      switch (paramInt)
      {
      default: 
        
      case 2: 
        zzoX();
      }
    }
  }
  
  private void zzoX()
  {
    if (this.zzafC != null)
    {
      Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + zzfK());
      this.zzafx.zzb(zzfK(), this.zzafC, zzoV());
      this.zzafH.incrementAndGet();
    }
    this.zzafC = new zze(this.zzafH.get());
    if (!this.zzafx.zza(zzfK(), this.zzafC, zzoV()))
    {
      Log.e("GmsClient", "unable to connect to service: " + zzfK());
      this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzafH.get(), 9));
    }
  }
  
  private void zzoY()
  {
    if (this.zzafC != null)
    {
      this.zzafx.zzb(zzfK(), this.zzafC, zzoV());
      this.zzafC = null;
    }
  }
  
  public void disconnect()
  {
    this.zzafH.incrementAndGet();
    synchronized (this.zzafB)
    {
      int i = this.zzafB.size();
      for (int j = 0; j < i; j++) {
        ((zzc)this.zzafB.get(j)).zzpi();
      }
      this.zzafB.clear();
      zzb(1, null);
      return;
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    for (;;)
    {
      IInterface localIInterface;
      synchronized (this.zzpd)
      {
        int i = this.zzafD;
        localIInterface = this.zzafA;
        paramPrintWriter.append(paramString).append("mConnectState=");
        switch (i)
        {
        default: 
          paramPrintWriter.print("UNKNOWN");
          paramPrintWriter.append(" mService=");
          if (localIInterface != null) {
            break label144;
          }
          paramPrintWriter.println("null");
          return;
        }
      }
      paramPrintWriter.print("CONNECTING");
      continue;
      paramPrintWriter.print("CONNECTED");
      continue;
      paramPrintWriter.print("DISCONNECTING");
      continue;
      paramPrintWriter.print("DISCONNECTED");
      continue;
      label144:
      paramPrintWriter.append(zzfL()).append("@").println(Integer.toHexString(System.identityHashCode(localIInterface.asBinder())));
    }
  }
  
  public final Context getContext()
  {
    return this.mContext;
  }
  
  public final Looper getLooper()
  {
    return this.zzaaO;
  }
  
  public boolean isConnected()
  {
    for (;;)
    {
      synchronized (this.zzpd)
      {
        if (this.zzafD == 3)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public boolean isConnecting()
  {
    for (;;)
    {
      synchronized (this.zzpd)
      {
        if (this.zzafD == 2)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  protected void onConnectionFailed(ConnectionResult paramConnectionResult) {}
  
  protected void onConnectionSuspended(int paramInt) {}
  
  protected abstract T zzW(IBinder paramIBinder);
  
  protected void zza(int paramInt1, Bundle paramBundle, int paramInt2)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(5, paramInt2, -1, new zzi(paramInt1, paramBundle)));
  }
  
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramInt2, -1, new zzg(paramInt1, paramIBinder, paramBundle)));
  }
  
  public void zza(GoogleApiClient.zza paramzza)
  {
    this.zzafz = ((GoogleApiClient.zza)zzx.zzb(paramzza, "Connection progress callbacks cannot be null."));
    zzb(2, null);
  }
  
  public void zza(zzp paramzzp)
  {
    Bundle localBundle = zzpd();
    ValidateAccountRequest localValidateAccountRequest = new ValidateAccountRequest(paramzzp, (Scope[])this.zzTm.toArray(new Scope[this.zzTm.size()]), this.mContext.getPackageName(), localBundle);
    try
    {
      this.zzafy.zza(new zzd(this, this.zzafH.get()), localValidateAccountRequest);
      return;
    }
    catch (DeadObjectException localDeadObjectException)
    {
      for (;;)
      {
        Log.w("GmsClient", "service died");
        zzbE(1);
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.w("GmsClient", "Remote exception occurred", localRemoteException);
      }
    }
  }
  
  public void zza(zzp paramzzp, Set<Scope> paramSet)
  {
    try
    {
      Bundle localBundle = zzly();
      GetServiceRequest localGetServiceRequest = new GetServiceRequest(this.zzafG).zzcl(this.mContext.getPackageName()).zzg(localBundle);
      if (paramSet != null) {
        localGetServiceRequest.zzd(paramSet);
      }
      if (zzlN()) {
        localGetServiceRequest.zzc(zzoI()).zzc(paramzzp);
      }
      for (;;)
      {
        this.zzafy.zza(new zzd(this, this.zzafH.get()), localGetServiceRequest);
        break;
        if (zzpe()) {
          localGetServiceRequest.zzc(this.zzQd);
        }
      }
      return;
    }
    catch (DeadObjectException localDeadObjectException)
    {
      Log.w("GmsClient", "service died");
      zzbE(1);
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("GmsClient", "Remote exception occurred", localRemoteException);
    }
  }
  
  protected Set<Scope> zzb(Set<Scope> paramSet)
  {
    return paramSet;
  }
  
  public void zzbE(int paramInt)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzafH.get(), paramInt));
  }
  
  protected void zzbF(int paramInt)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(6, paramInt, -1, new zzh()));
  }
  
  protected void zzc(int paramInt, T paramT) {}
  
  protected abstract String zzfK();
  
  protected abstract String zzfL();
  
  public boolean zzlN()
  {
    return false;
  }
  
  protected Bundle zzly()
  {
    return new Bundle();
  }
  
  public Bundle zzmS()
  {
    return null;
  }
  
  public IBinder zznz()
  {
    if (this.zzafy == null) {}
    for (IBinder localIBinder = null;; localIBinder = this.zzafy.asBinder()) {
      return localIBinder;
    }
  }
  
  public final Account zzoI()
  {
    if (this.zzQd != null) {}
    for (Account localAccount = this.zzQd;; localAccount = new Account("<<default account>>", "com.google")) {
      return localAccount;
    }
  }
  
  protected final String zzoV()
  {
    return this.zzabI.zzoO();
  }
  
  protected void zzoW() {}
  
  public void zzoZ()
  {
    int i = this.zzaaP.isGooglePlayServicesAvailable(this.mContext);
    if (i != 0)
    {
      zzb(1, null);
      this.zzafz = new zzf();
      this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzafH.get(), i));
    }
    for (;;)
    {
      return;
      zza(new zzf());
    }
  }
  
  protected final zzf zzpa()
  {
    return this.zzabI;
  }
  
  protected final void zzpb()
  {
    if (!isConnected()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  public final T zzpc()
    throws DeadObjectException
  {
    boolean bool;
    IInterface localIInterface;
    synchronized (this.zzpd)
    {
      if (this.zzafD == 4) {
        throw new DeadObjectException();
      }
    }
  }
  
  protected Bundle zzpd()
  {
    return null;
  }
  
  public boolean zzpe()
  {
    return false;
  }
  
  public static final class zzd
    extends zzr.zza
  {
    private zzj zzafM;
    private final int zzafN;
    
    public zzd(zzj paramzzj, int paramInt)
    {
      this.zzafM = paramzzj;
      this.zzafN = paramInt;
    }
    
    private void zzpj()
    {
      this.zzafM = null;
    }
    
    public void zza(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      zzx.zzb(this.zzafM, "onPostInitComplete can be called only once per call to getRemoteService");
      this.zzafM.zza(paramInt, paramIBinder, paramBundle, this.zzafN);
      zzpj();
    }
    
    public void zzb(int paramInt, Bundle paramBundle)
    {
      zzx.zzb(this.zzafM, "onAccountValidationComplete can be called only once per call to validateAccount");
      this.zzafM.zza(paramInt, paramBundle, this.zzafN);
      zzpj();
    }
  }
  
  private abstract class zza
    extends zzj<T>.zzc<Boolean>
  {
    public final int statusCode;
    public final Bundle zzafJ;
    
    protected zza(int paramInt, Bundle paramBundle)
    {
      super(Boolean.valueOf(true));
      this.statusCode = paramInt;
      this.zzafJ = paramBundle;
    }
    
    protected void zzc(Boolean paramBoolean)
    {
      PendingIntent localPendingIntent = null;
      if (paramBoolean == null) {
        zzj.zza(zzj.this, 1, null);
      }
      for (;;)
      {
        return;
        switch (this.statusCode)
        {
        default: 
          zzj.zza(zzj.this, 1, null);
          if (this.zzafJ != null) {
            localPendingIntent = (PendingIntent)this.zzafJ.getParcelable("pendingIntent");
          }
          zzh(new ConnectionResult(this.statusCode, localPendingIntent));
          break;
        case 0: 
          if (!zzpf())
          {
            zzj.zza(zzj.this, 1, null);
            zzh(new ConnectionResult(8, null));
          }
          break;
        }
      }
      zzj.zza(zzj.this, 1, null);
      throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
    }
    
    protected abstract void zzh(ConnectionResult paramConnectionResult);
    
    protected abstract boolean zzpf();
    
    protected void zzpg() {}
  }
  
  protected final class zzg
    extends zzj<T>.zza
  {
    public final IBinder zzafO;
    
    public zzg(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      super(paramInt, paramBundle);
      this.zzafO = paramIBinder;
    }
    
    protected void zzh(ConnectionResult paramConnectionResult)
    {
      if (zzj.zze(zzj.this) != null) {
        zzj.zze(zzj.this).onConnectionFailed(paramConnectionResult);
      }
      zzj.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzpf()
    {
      bool = false;
      try
      {
        String str = this.zzafO.getInterfaceDescriptor();
        if (zzj.this.zzfL().equals(str)) {
          break label83;
        }
        Log.e("GmsClient", "service descriptor mismatch: " + zzj.this.zzfL() + " vs. " + str);
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          Log.w("GmsClient", "service probably died");
          continue;
          IInterface localIInterface = zzj.this.zzW(this.zzafO);
          if ((localIInterface != null) && (zzj.zza(zzj.this, 2, 3, localIInterface)))
          {
            Bundle localBundle = zzj.this.zzmS();
            if (zzj.zzb(zzj.this) != null) {
              zzj.zzb(zzj.this).onConnected(localBundle);
            }
            bool = true;
          }
        }
      }
      return bool;
    }
  }
  
  protected final class zzi
    extends zzj<T>.zza
  {
    public zzi(int paramInt, Bundle paramBundle)
    {
      super(paramInt, paramBundle);
    }
    
    protected void zzh(ConnectionResult paramConnectionResult)
    {
      zzj.zza(zzj.this).zzb(paramConnectionResult);
      zzj.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzpf()
    {
      zzj.zza(zzj.this).zzb(ConnectionResult.zzZY);
      return true;
    }
  }
  
  protected final class zzh
    extends zzj<T>.zza
  {
    public zzh()
    {
      super(0, null);
    }
    
    protected void zzh(ConnectionResult paramConnectionResult)
    {
      zzj.zza(zzj.this).zza(paramConnectionResult);
      zzj.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzpf()
    {
      zzj.zza(zzj.this).zza(ConnectionResult.zzZY);
      return true;
    }
  }
  
  protected class zzf
    implements GoogleApiClient.zza
  {
    public zzf() {}
    
    public void zza(ConnectionResult paramConnectionResult)
    {
      if (paramConnectionResult.isSuccess()) {
        zzj.this.zza(null, zzj.zzd(zzj.this));
      }
      for (;;)
      {
        return;
        if (zzj.zze(zzj.this) != null) {
          zzj.zze(zzj.this).onConnectionFailed(paramConnectionResult);
        }
      }
    }
    
    public void zzb(ConnectionResult paramConnectionResult)
    {
      throw new IllegalStateException("Legacy GmsClient received onReportAccountValidation callback.");
    }
  }
  
  protected abstract class zzc<TListener>
  {
    private TListener mListener;
    private boolean zzafL;
    
    public zzc()
    {
      Object localObject;
      this.mListener = localObject;
      this.zzafL = false;
    }
    
    public void unregister()
    {
      zzpi();
      synchronized (zzj.zzc(zzj.this))
      {
        zzj.zzc(zzj.this).remove(this);
        return;
      }
    }
    
    protected abstract void zzpg();
    
    /* Error */
    public void zzph()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 24	com/google/android/gms/common/internal/zzj$zzc:mListener	Ljava/lang/Object;
      //   6: astore_2
      //   7: aload_0
      //   8: getfield 26	com/google/android/gms/common/internal/zzj$zzc:zzafL	Z
      //   11: ifeq +33 -> 44
      //   14: ldc 45
      //   16: new 47	java/lang/StringBuilder
      //   19: dup
      //   20: invokespecial 48	java/lang/StringBuilder:<init>	()V
      //   23: ldc 50
      //   25: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   28: aload_0
      //   29: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   32: ldc 59
      //   34: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   37: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   40: invokestatic 69	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   43: pop
      //   44: aload_0
      //   45: monitorexit
      //   46: aload_2
      //   47: ifnull +36 -> 83
      //   50: aload_0
      //   51: aload_2
      //   52: invokevirtual 73	com/google/android/gms/common/internal/zzj$zzc:zzt	(Ljava/lang/Object;)V
      //   55: aload_0
      //   56: monitorenter
      //   57: aload_0
      //   58: iconst_1
      //   59: putfield 26	com/google/android/gms/common/internal/zzj$zzc:zzafL	Z
      //   62: aload_0
      //   63: monitorexit
      //   64: aload_0
      //   65: invokevirtual 75	com/google/android/gms/common/internal/zzj$zzc:unregister	()V
      //   68: return
      //   69: astore_1
      //   70: aload_0
      //   71: monitorexit
      //   72: aload_1
      //   73: athrow
      //   74: astore 4
      //   76: aload_0
      //   77: invokevirtual 77	com/google/android/gms/common/internal/zzj$zzc:zzpg	()V
      //   80: aload 4
      //   82: athrow
      //   83: aload_0
      //   84: invokevirtual 77	com/google/android/gms/common/internal/zzj$zzc:zzpg	()V
      //   87: goto -32 -> 55
      //   90: astore_3
      //   91: aload_0
      //   92: monitorexit
      //   93: aload_3
      //   94: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	95	0	this	zzc
      //   69	4	1	localObject1	Object
      //   6	46	2	localObject2	Object
      //   90	4	3	localObject3	Object
      //   74	7	4	localRuntimeException	RuntimeException
      // Exception table:
      //   from	to	target	type
      //   2	46	69	finally
      //   70	72	69	finally
      //   50	55	74	java/lang/RuntimeException
      //   57	64	90	finally
      //   91	93	90	finally
    }
    
    public void zzpi()
    {
      try
      {
        this.mListener = null;
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    protected abstract void zzt(TListener paramTListener);
  }
  
  final class zzb
    extends Handler
  {
    public zzb(Looper paramLooper)
    {
      super();
    }
    
    private void zza(Message paramMessage)
    {
      zzj.zzc localzzc = (zzj.zzc)paramMessage.obj;
      localzzc.zzpg();
      localzzc.unregister();
    }
    
    private boolean zzb(Message paramMessage)
    {
      int i = 1;
      if ((paramMessage.what == 2) || (paramMessage.what == i) || (paramMessage.what == 5) || (paramMessage.what == 6)) {}
      for (;;)
      {
        return i;
        int j = 0;
      }
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (zzj.this.zzafH.get() != paramMessage.arg1) {
        if (zzb(paramMessage)) {
          zza(paramMessage);
        }
      }
      for (;;)
      {
        return;
        if (((paramMessage.what == 1) || (paramMessage.what == 5) || (paramMessage.what == 6)) && (!zzj.this.isConnecting()))
        {
          zza(paramMessage);
        }
        else if (paramMessage.what == 3)
        {
          ConnectionResult localConnectionResult = new ConnectionResult(paramMessage.arg2, null);
          zzj.zza(zzj.this).zza(localConnectionResult);
          zzj.this.onConnectionFailed(localConnectionResult);
        }
        else if (paramMessage.what == 4)
        {
          zzj.zza(zzj.this, 4, null);
          if (zzj.zzb(zzj.this) != null) {
            zzj.zzb(zzj.this).onConnectionSuspended(paramMessage.arg2);
          }
          zzj.this.onConnectionSuspended(paramMessage.arg2);
          zzj.zza(zzj.this, 4, 1, null);
        }
        else if ((paramMessage.what == 2) && (!zzj.this.isConnected()))
        {
          zza(paramMessage);
        }
        else if (zzb(paramMessage))
        {
          ((zzj.zzc)paramMessage.obj).zzph();
        }
        else
        {
          Log.wtf("GmsClient", "Don't know how to handle message: " + paramMessage.what, new Exception());
        }
      }
    }
  }
  
  public final class zze
    implements ServiceConnection
  {
    private final int zzafN;
    
    public zze(int paramInt)
    {
      this.zzafN = paramInt;
    }
    
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      zzx.zzb(paramIBinder, "Expecting a valid IBinder");
      zzj.zza(zzj.this, zzs.zza.zzaK(paramIBinder));
      zzj.this.zzbF(this.zzafN);
    }
    
    public void onServiceDisconnected(ComponentName paramComponentName)
    {
      zzj.this.mHandler.sendMessage(zzj.this.mHandler.obtainMessage(4, this.zzafN, 1));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */