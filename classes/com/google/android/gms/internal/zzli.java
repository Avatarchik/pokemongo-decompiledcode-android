package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zzd;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zza;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzk.zza;
import com.google.android.gms.common.internal.zzx;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class zzli
  extends GoogleApiClient
{
  private final Context mContext;
  private final int zzaaM;
  private final Looper zzaaO;
  private final GoogleApiAvailability zzaaP;
  final Api.zza<? extends zzqw, zzqx> zzaaQ;
  final zzf zzabI;
  final Map<Api<?>, Integer> zzabJ;
  private final Condition zzabY;
  final zzk zzabZ;
  private final Lock zzabt = new ReentrantLock();
  final Queue<zzf<?>> zzaca = new LinkedList();
  private volatile boolean zzacb;
  private long zzacc = 120000L;
  private long zzacd = 5000L;
  private final zza zzace;
  zzd zzacf;
  final Map<Api.zzc<?>, Api.zzb> zzacg = new HashMap();
  final Map<Api.zzc<?>, ConnectionResult> zzach = new HashMap();
  Set<Scope> zzaci = new HashSet();
  private volatile zzlj zzacj;
  private ConnectionResult zzack = null;
  private final Set<zzlm<?>> zzacl = Collections.newSetFromMap(new WeakHashMap());
  final Set<zzf<?>> zzacm = Collections.newSetFromMap(new ConcurrentHashMap(16, 0.75F, 2));
  private zza zzacn;
  private final zze zzaco = new zze()
  {
    public void zzc(zzli.zzf<?> paramAnonymouszzf)
    {
      zzli.this.zzacm.remove(paramAnonymouszzf);
      if ((paramAnonymouszzf.zznF() != null) && (zzli.zza(zzli.this) != null)) {
        zzli.zza(zzli.this).remove(paramAnonymouszzf.zznF().intValue());
      }
    }
  };
  private final GoogleApiClient.ConnectionCallbacks zzacp = new GoogleApiClient.ConnectionCallbacks()
  {
    public void onConnected(Bundle paramAnonymousBundle)
    {
      zzli.zzb(zzli.this).lock();
      try
      {
        zzli.zzc(zzli.this).onConnected(paramAnonymousBundle);
        return;
      }
      finally
      {
        zzli.zzb(zzli.this).unlock();
      }
    }
    
    public void onConnectionSuspended(int paramAnonymousInt)
    {
      zzli.zzb(zzli.this).lock();
      try
      {
        zzli.zzc(zzli.this).onConnectionSuspended(paramAnonymousInt);
        return;
      }
      finally
      {
        zzli.zzb(zzli.this).unlock();
      }
    }
  };
  private final zzk.zza zzacq = new zzk.zza()
  {
    public boolean isConnected()
    {
      return zzli.this.isConnected();
    }
    
    public Bundle zzmS()
    {
      return null;
    }
  };
  
  public zzli(Context paramContext, Looper paramLooper, zzf paramzzf, GoogleApiAvailability paramGoogleApiAvailability, Api.zza<? extends zzqw, zzqx> paramzza, Map<Api<?>, Api.ApiOptions> paramMap, ArrayList<GoogleApiClient.ConnectionCallbacks> paramArrayList, ArrayList<GoogleApiClient.OnConnectionFailedListener> paramArrayList1, int paramInt)
  {
    this.mContext = paramContext;
    this.zzabZ = new zzk(paramLooper, this.zzacq);
    this.zzaaO = paramLooper;
    this.zzace = new zza(paramLooper);
    this.zzaaP = paramGoogleApiAvailability;
    this.zzaaM = paramInt;
    this.zzabJ = new HashMap();
    this.zzabY = this.zzabt.newCondition();
    this.zzacj = new zzlh(this);
    Iterator localIterator1 = paramArrayList.iterator();
    while (localIterator1.hasNext())
    {
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)localIterator1.next();
      this.zzabZ.registerConnectionCallbacks(localConnectionCallbacks);
    }
    Iterator localIterator2 = paramArrayList1.iterator();
    while (localIterator2.hasNext())
    {
      GoogleApiClient.OnConnectionFailedListener localOnConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener)localIterator2.next();
      this.zzabZ.registerConnectionFailedListener(localOnConnectionFailedListener);
    }
    Map localMap = paramzzf.zzoM();
    Iterator localIterator3 = paramMap.keySet().iterator();
    Api localApi;
    Object localObject1;
    int j;
    if (localIterator3.hasNext())
    {
      localApi = (Api)localIterator3.next();
      localObject1 = paramMap.get(localApi);
      if (localMap.get(localApi) == null) {
        break label526;
      }
      if (((zzf.zza)localMap.get(localApi)).zzafk) {
        j = 1;
      }
    }
    label402:
    label526:
    for (int i = j;; i = 0)
    {
      this.zzabJ.put(localApi, Integer.valueOf(i));
      if (localApi.zzny()) {}
      for (Object localObject2 = zza(localApi.zznw(), localObject1, paramContext, paramLooper, paramzzf, this.zzacp, zza(localApi, i));; localObject2 = zza(localApi.zznv(), localObject1, paramContext, paramLooper, paramzzf, this.zzacp, zza(localApi, i)))
      {
        this.zzacg.put(localApi.zznx(), localObject2);
        break;
        j = 2;
        break label402;
      }
      this.zzabI = paramzzf;
      this.zzaaQ = paramzza;
      return;
    }
  }
  
  private void resume()
  {
    this.zzabt.lock();
    try
    {
      if (zzoc()) {
        connect();
      }
      return;
    }
    finally
    {
      this.zzabt.unlock();
    }
  }
  
  private static <C extends Api.zzb, O> C zza(Api.zza<C, O> paramzza, Object paramObject, Context paramContext, Looper paramLooper, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return paramzza.zza(paramContext, paramLooper, paramzzf, paramObject, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  private GoogleApiClient.OnConnectionFailedListener zza(final Api<?> paramApi, final int paramInt)
  {
    new GoogleApiClient.OnConnectionFailedListener()
    {
      public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
      {
        zzli.zzb(zzli.this).lock();
        try
        {
          zzli.zzc(zzli.this).zza(paramAnonymousConnectionResult, paramApi, paramInt);
          return;
        }
        finally
        {
          zzli.zzb(zzli.this).unlock();
        }
      }
    };
  }
  
  private static <C extends Api.zzd, O> zzac zza(Api.zze<C, O> paramzze, Object paramObject, Context paramContext, Looper paramLooper, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return new zzac(paramContext, paramLooper, paramzze.zznA(), paramConnectionCallbacks, paramOnConnectionFailedListener, paramzzf, paramzze.zzn(paramObject));
  }
  
  private void zza(final GoogleApiClient paramGoogleApiClient, final zzlo paramzzlo, final boolean paramBoolean)
  {
    zzlx.zzagw.zzb(paramGoogleApiClient).setResultCallback(new ResultCallback()
    {
      public void zzo(Status paramAnonymousStatus)
      {
        if ((paramAnonymousStatus.isSuccess()) && (zzli.this.isConnected())) {
          zzli.this.reconnect();
        }
        paramzzlo.zzb(paramAnonymousStatus);
        if (paramBoolean) {
          paramGoogleApiClient.disconnect();
        }
      }
    });
  }
  
  private static void zza(zzf<?> paramzzf, zza paramzza, IBinder paramIBinder)
  {
    if (paramzzf.isReady()) {
      paramzzf.zza(new zzc(paramzzf, paramzza, paramIBinder, null));
    }
    for (;;)
    {
      return;
      if ((paramIBinder != null) && (paramIBinder.isBinderAlive()))
      {
        zzc localzzc = new zzc(paramzzf, paramzza, paramIBinder, null);
        paramzzf.zza(localzzc);
        try
        {
          paramIBinder.linkToDeath(localzzc, 0);
        }
        catch (RemoteException localRemoteException)
        {
          paramzzf.cancel();
          paramzza.remove(paramzzf.zznF().intValue());
        }
      }
      else
      {
        paramzzf.zza(null);
        paramzzf.cancel();
        paramzza.remove(paramzzf.zznF().intValue());
      }
    }
  }
  
  private void zzod()
  {
    this.zzabt.lock();
    try
    {
      if (zzof()) {
        connect();
      }
      return;
    }
    finally
    {
      this.zzabt.unlock();
    }
  }
  
  /* Error */
  public ConnectionResult blockingConnect()
  {
    // Byte code:
    //   0: invokestatic 410	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   3: invokestatic 413	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   6: if_acmpeq +78 -> 84
    //   9: iconst_1
    //   10: istore_1
    //   11: iload_1
    //   12: ldc_w 415
    //   15: invokestatic 420	com/google/android/gms/common/internal/zzx:zza	(ZLjava/lang/Object;)V
    //   18: aload_0
    //   19: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   22: invokeinterface 293 1 0
    //   27: aload_0
    //   28: invokevirtual 299	com/google/android/gms/internal/zzli:connect	()V
    //   31: aload_0
    //   32: invokevirtual 423	com/google/android/gms/internal/zzli:isConnecting	()Z
    //   35: istore_3
    //   36: iload_3
    //   37: ifeq +52 -> 89
    //   40: aload_0
    //   41: getfield 192	com/google/android/gms/internal/zzli:zzabY	Ljava/util/concurrent/locks/Condition;
    //   44: invokeinterface 428 1 0
    //   49: goto -18 -> 31
    //   52: astore 5
    //   54: invokestatic 434	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   57: invokevirtual 437	java/lang/Thread:interrupt	()V
    //   60: new 439	com/google/android/gms/common/ConnectionResult
    //   63: dup
    //   64: bipush 15
    //   66: aconst_null
    //   67: invokespecial 442	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   70: astore 4
    //   72: aload_0
    //   73: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   76: invokeinterface 302 1 0
    //   81: aload 4
    //   83: areturn
    //   84: iconst_0
    //   85: istore_1
    //   86: goto -75 -> 11
    //   89: aload_0
    //   90: invokevirtual 445	com/google/android/gms/internal/zzli:isConnected	()Z
    //   93: ifeq +20 -> 113
    //   96: getstatic 448	com/google/android/gms/common/ConnectionResult:zzZY	Lcom/google/android/gms/common/ConnectionResult;
    //   99: astore 4
    //   101: aload_0
    //   102: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   105: invokeinterface 302 1 0
    //   110: goto -29 -> 81
    //   113: aload_0
    //   114: getfield 132	com/google/android/gms/internal/zzli:zzack	Lcom/google/android/gms/common/ConnectionResult;
    //   117: ifnull +21 -> 138
    //   120: aload_0
    //   121: getfield 132	com/google/android/gms/internal/zzli:zzack	Lcom/google/android/gms/common/ConnectionResult;
    //   124: astore 4
    //   126: aload_0
    //   127: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   130: invokeinterface 302 1 0
    //   135: goto -54 -> 81
    //   138: new 439	com/google/android/gms/common/ConnectionResult
    //   141: dup
    //   142: bipush 13
    //   144: aconst_null
    //   145: invokespecial 442	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   148: astore 4
    //   150: aload_0
    //   151: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   154: invokeinterface 302 1 0
    //   159: goto -78 -> 81
    //   162: astore_2
    //   163: aload_0
    //   164: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   167: invokeinterface 302 1 0
    //   172: aload_2
    //   173: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	this	zzli
    //   10	76	1	bool1	boolean
    //   162	11	2	localObject	Object
    //   35	2	3	bool2	boolean
    //   70	79	4	localConnectionResult	ConnectionResult
    //   52	1	5	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   40	49	52	java/lang/InterruptedException
    //   27	36	162	finally
    //   40	49	162	finally
    //   54	72	162	finally
    //   89	101	162	finally
    //   113	126	162	finally
    //   138	150	162	finally
  }
  
  /* Error */
  public ConnectionResult blockingConnect(long paramLong, java.util.concurrent.TimeUnit paramTimeUnit)
  {
    // Byte code:
    //   0: invokestatic 410	android/os/Looper:myLooper	()Landroid/os/Looper;
    //   3: invokestatic 413	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   6: if_acmpeq +97 -> 103
    //   9: iconst_1
    //   10: istore 4
    //   12: iload 4
    //   14: ldc_w 415
    //   17: invokestatic 420	com/google/android/gms/common/internal/zzx:zza	(ZLjava/lang/Object;)V
    //   20: aload_3
    //   21: ldc_w 451
    //   24: invokestatic 453	com/google/android/gms/common/internal/zzx:zzb	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   27: pop
    //   28: aload_0
    //   29: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   32: invokeinterface 293 1 0
    //   37: aload_0
    //   38: invokevirtual 299	com/google/android/gms/internal/zzli:connect	()V
    //   41: aload_3
    //   42: lload_1
    //   43: invokevirtual 459	java/util/concurrent/TimeUnit:toNanos	(J)J
    //   46: lstore 7
    //   48: aload_0
    //   49: invokevirtual 423	com/google/android/gms/internal/zzli:isConnecting	()Z
    //   52: istore 9
    //   54: iload 9
    //   56: ifeq +85 -> 141
    //   59: aload_0
    //   60: getfield 192	com/google/android/gms/internal/zzli:zzabY	Ljava/util/concurrent/locks/Condition;
    //   63: lload 7
    //   65: invokeinterface 462 3 0
    //   70: lstore 7
    //   72: lload 7
    //   74: lconst_0
    //   75: lcmp
    //   76: ifgt -28 -> 48
    //   79: new 439	com/google/android/gms/common/ConnectionResult
    //   82: dup
    //   83: bipush 14
    //   85: aconst_null
    //   86: invokespecial 442	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   89: astore 10
    //   91: aload_0
    //   92: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   95: invokeinterface 302 1 0
    //   100: aload 10
    //   102: areturn
    //   103: iconst_0
    //   104: istore 4
    //   106: goto -94 -> 12
    //   109: astore 11
    //   111: invokestatic 434	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   114: invokevirtual 437	java/lang/Thread:interrupt	()V
    //   117: new 439	com/google/android/gms/common/ConnectionResult
    //   120: dup
    //   121: bipush 15
    //   123: aconst_null
    //   124: invokespecial 442	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   127: astore 10
    //   129: aload_0
    //   130: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   133: invokeinterface 302 1 0
    //   138: goto -38 -> 100
    //   141: aload_0
    //   142: invokevirtual 445	com/google/android/gms/internal/zzli:isConnected	()Z
    //   145: ifeq +20 -> 165
    //   148: getstatic 448	com/google/android/gms/common/ConnectionResult:zzZY	Lcom/google/android/gms/common/ConnectionResult;
    //   151: astore 10
    //   153: aload_0
    //   154: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   157: invokeinterface 302 1 0
    //   162: goto -62 -> 100
    //   165: aload_0
    //   166: getfield 132	com/google/android/gms/internal/zzli:zzack	Lcom/google/android/gms/common/ConnectionResult;
    //   169: ifnull +21 -> 190
    //   172: aload_0
    //   173: getfield 132	com/google/android/gms/internal/zzli:zzack	Lcom/google/android/gms/common/ConnectionResult;
    //   176: astore 10
    //   178: aload_0
    //   179: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   182: invokeinterface 302 1 0
    //   187: goto -87 -> 100
    //   190: new 439	com/google/android/gms/common/ConnectionResult
    //   193: dup
    //   194: bipush 13
    //   196: aconst_null
    //   197: invokespecial 442	com/google/android/gms/common/ConnectionResult:<init>	(ILandroid/app/PendingIntent;)V
    //   200: astore 10
    //   202: aload_0
    //   203: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   206: invokeinterface 302 1 0
    //   211: goto -111 -> 100
    //   214: astore 6
    //   216: aload_0
    //   217: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   220: invokeinterface 302 1 0
    //   225: aload 6
    //   227: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	this	zzli
    //   0	228	1	paramLong	long
    //   0	228	3	paramTimeUnit	java.util.concurrent.TimeUnit
    //   10	95	4	bool1	boolean
    //   214	12	6	localObject	Object
    //   46	27	7	l	long
    //   52	3	9	bool2	boolean
    //   89	112	10	localConnectionResult	ConnectionResult
    //   109	1	11	localInterruptedException	InterruptedException
    // Exception table:
    //   from	to	target	type
    //   59	91	109	java/lang/InterruptedException
    //   37	54	214	finally
    //   59	91	214	finally
    //   111	129	214	finally
    //   141	153	214	finally
    //   165	178	214	finally
    //   190	202	214	finally
  }
  
  public PendingResult<Status> clearDefaultAccountAndReconnect()
  {
    zzx.zza(isConnected(), "GoogleApiClient is not connected yet.");
    final zzlo localzzlo = new zzlo(this);
    if (this.zzacg.containsKey(zzlx.zzRk)) {
      zza(this, localzzlo, false);
    }
    for (;;)
    {
      return localzzlo;
      final AtomicReference localAtomicReference = new AtomicReference();
      GoogleApiClient.ConnectionCallbacks local5 = new GoogleApiClient.ConnectionCallbacks()
      {
        public void onConnected(Bundle paramAnonymousBundle)
        {
          zzli.zza(zzli.this, (GoogleApiClient)localAtomicReference.get(), localzzlo, true);
        }
        
        public void onConnectionSuspended(int paramAnonymousInt) {}
      };
      GoogleApiClient.OnConnectionFailedListener local6 = new GoogleApiClient.OnConnectionFailedListener()
      {
        public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
        {
          localzzlo.zzb(new Status(8));
        }
      };
      GoogleApiClient localGoogleApiClient = new GoogleApiClient.Builder(this.mContext).addApi(zzlx.API).addConnectionCallbacks(local5).addOnConnectionFailedListener(local6).setHandler(this.zzace).build();
      localAtomicReference.set(localGoogleApiClient);
      localGoogleApiClient.connect();
    }
  }
  
  public void connect()
  {
    this.zzabt.lock();
    try
    {
      this.zzacj.connect();
      return;
    }
    finally
    {
      this.zzabt.unlock();
    }
  }
  
  public void disconnect()
  {
    this.zzabt.lock();
    try
    {
      zzof();
      this.zzacj.disconnect();
      return;
    }
    finally
    {
      this.zzabt.unlock();
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.append(paramString).append("mState=").append(this.zzacj.getName());
    paramPrintWriter.append(" mResuming=").print(this.zzacb);
    paramPrintWriter.append(" mWorkQueue.size()=").print(this.zzaca.size());
    paramPrintWriter.append(" mUnconsumedRunners.size()=").println(this.zzacm.size());
    String str = paramString + "  ";
    Iterator localIterator = this.zzabJ.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api localApi = (Api)localIterator.next();
      paramPrintWriter.append(paramString).append(localApi.getName()).println(":");
      ((Api.zzb)this.zzacg.get(localApi.zznx())).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public ConnectionResult getConnectionResult(Api<?> paramApi)
  {
    Api.zzc localzzc = paramApi.zznx();
    this.zzabt.lock();
    try
    {
      if ((!isConnected()) && (!zzoc())) {
        throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
      }
    }
    finally
    {
      this.zzabt.unlock();
    }
    if (this.zzacg.containsKey(localzzc))
    {
      ConnectionResult localConnectionResult;
      if (((Api.zzb)this.zzacg.get(localzzc)).isConnected())
      {
        localConnectionResult = ConnectionResult.zzZY;
        this.zzabt.unlock();
      }
      for (;;)
      {
        return localConnectionResult;
        if (this.zzach.containsKey(localzzc))
        {
          localConnectionResult = (ConnectionResult)this.zzach.get(localzzc);
          this.zzabt.unlock();
        }
        else
        {
          Log.i("GoogleApiClientImpl", zzog());
          Log.wtf("GoogleApiClientImpl", paramApi.getName() + " requested in getConnectionResult" + " is not connected but is not present in the failed connections map", new Exception());
          localConnectionResult = new ConnectionResult(8, null);
          this.zzabt.unlock();
        }
      }
    }
    this.zzabt.unlock();
    throw new IllegalArgumentException(paramApi.getName() + " was never registered with GoogleApiClient");
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public Looper getLooper()
  {
    return this.zzaaO;
  }
  
  public int getSessionId()
  {
    return System.identityHashCode(this);
  }
  
  public boolean hasConnectedApi(Api<?> paramApi)
  {
    Api.zzb localzzb = (Api.zzb)this.zzacg.get(paramApi.zznx());
    if ((localzzb != null) && (localzzb.isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isConnected()
  {
    return this.zzacj instanceof zzlf;
  }
  
  public boolean isConnecting()
  {
    return this.zzacj instanceof zzlg;
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.zzabZ.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.zzabZ.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public void reconnect()
  {
    disconnect();
    connect();
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zzabZ.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zzabZ.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void stopAutoManage(final FragmentActivity paramFragmentActivity)
  {
    if (this.zzaaM >= 0)
    {
      zzlp localzzlp = zzlp.zza(paramFragmentActivity);
      if (localzzlp == null) {
        new Handler(this.mContext.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            if ((paramFragmentActivity.isFinishing()) || (paramFragmentActivity.getSupportFragmentManager().isDestroyed())) {}
            for (;;)
            {
              return;
              zzlp.zzb(paramFragmentActivity).zzbp(zzli.zzf(zzli.this));
            }
          }
        });
      }
      for (;;)
      {
        return;
        localzzlp.zzbp(this.zzaaM);
      }
    }
    throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zzabZ.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zzabZ.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public <C extends Api.zzb> C zza(Api.zzc<C> paramzzc)
  {
    Api.zzb localzzb = (Api.zzb)this.zzacg.get(paramzzc);
    zzx.zzb(localzzb, "Appropriate Api was not requested.");
    return localzzb;
  }
  
  /* Error */
  public <A extends Api.zzb, R extends Result, T extends zzlb.zza<R, A>> T zza(T paramT)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 688	com/google/android/gms/internal/zzlb$zza:zznx	()Lcom/google/android/gms/common/api/Api$zzc;
    //   4: ifnull +64 -> 68
    //   7: iconst_1
    //   8: istore_2
    //   9: iload_2
    //   10: ldc_w 690
    //   13: invokestatic 692	com/google/android/gms/common/internal/zzx:zzb	(ZLjava/lang/Object;)V
    //   16: aload_0
    //   17: getfield 123	com/google/android/gms/internal/zzli:zzacg	Ljava/util/Map;
    //   20: aload_1
    //   21: invokevirtual 688	com/google/android/gms/internal/zzlb$zza:zznx	()Lcom/google/android/gms/common/api/Api$zzc;
    //   24: invokeinterface 479 2 0
    //   29: ldc_w 694
    //   32: invokestatic 692	com/google/android/gms/common/internal/zzx:zzb	(ZLjava/lang/Object;)V
    //   35: aload_0
    //   36: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   39: invokeinterface 293 1 0
    //   44: aload_0
    //   45: getfield 197	com/google/android/gms/internal/zzli:zzacj	Lcom/google/android/gms/internal/zzlj;
    //   48: aload_1
    //   49: invokeinterface 696 2 0
    //   54: astore 4
    //   56: aload_0
    //   57: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   60: invokeinterface 302 1 0
    //   65: aload 4
    //   67: areturn
    //   68: iconst_0
    //   69: istore_2
    //   70: goto -61 -> 9
    //   73: astore_3
    //   74: aload_0
    //   75: getfield 105	com/google/android/gms/internal/zzli:zzabt	Ljava/util/concurrent/locks/Lock;
    //   78: invokeinterface 302 1 0
    //   83: aload_3
    //   84: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	this	zzli
    //   0	85	1	paramT	T
    //   8	62	2	bool	boolean
    //   73	11	3	localObject	Object
    //   54	12	4	localzza	zzlb.zza
    // Exception table:
    //   from	to	target	type
    //   44	56	73	finally
  }
  
  void zza(zzb paramzzb)
  {
    Message localMessage = this.zzace.obtainMessage(3, paramzzb);
    this.zzace.sendMessage(localMessage);
  }
  
  void zza(RuntimeException paramRuntimeException)
  {
    Message localMessage = this.zzace.obtainMessage(4, paramRuntimeException);
    this.zzace.sendMessage(localMessage);
  }
  
  public boolean zza(Api<?> paramApi)
  {
    return this.zzacg.containsKey(paramApi.zznx());
  }
  
  public <A extends Api.zzb, T extends zzlb.zza<? extends Result, A>> T zzb(T paramT)
  {
    boolean bool;
    if (paramT.zznx() != null) {
      bool = true;
    }
    for (;;)
    {
      zzx.zzb(bool, "This task can not be executed (it's probably a Batch or malformed)");
      this.zzabt.lock();
      try
      {
        if (!zzoc()) {
          break label116;
        }
        this.zzaca.add(paramT);
        while (!this.zzaca.isEmpty())
        {
          zzf localzzf = (zzf)this.zzaca.remove();
          zzb(localzzf);
          localzzf.zzv(Status.zzabd);
        }
        bool = false;
      }
      finally
      {
        this.zzabt.unlock();
      }
    }
    this.zzabt.unlock();
    for (;;)
    {
      return paramT;
      label116:
      zzlb.zza localzza = this.zzacj.zzb(paramT);
      paramT = localzza;
      this.zzabt.unlock();
    }
  }
  
  <A extends Api.zzb> void zzb(zzf<A> paramzzf)
  {
    this.zzacm.add(paramzzf);
    paramzzf.zza(this.zzaco);
  }
  
  void zzg(ConnectionResult paramConnectionResult)
  {
    this.zzabt.lock();
    try
    {
      this.zzack = paramConnectionResult;
      this.zzacj = new zzlh(this);
      this.zzacj.begin();
      this.zzabY.signalAll();
      return;
    }
    finally
    {
      this.zzabt.unlock();
    }
  }
  
  void zznY()
  {
    Iterator localIterator1 = this.zzacm.iterator();
    while (localIterator1.hasNext())
    {
      zzf localzzf = (zzf)localIterator1.next();
      localzzf.zza(null);
      if (localzzf.zznF() == null)
      {
        localzzf.cancel();
      }
      else
      {
        localzzf.zznJ();
        IBinder localIBinder = zza(localzzf.zznx()).zznz();
        zza(localzzf, this.zzacn, localIBinder);
      }
    }
    this.zzacm.clear();
    Iterator localIterator2 = this.zzacl.iterator();
    while (localIterator2.hasNext()) {
      ((zzlm)localIterator2.next()).clear();
    }
    this.zzacl.clear();
  }
  
  void zznZ()
  {
    Iterator localIterator = this.zzacg.values().iterator();
    while (localIterator.hasNext()) {
      ((Api.zzb)localIterator.next()).disconnect();
    }
  }
  
  public <L> zzlm<L> zzo(L paramL)
  {
    zzx.zzb(paramL, "Listener must not be null");
    this.zzabt.lock();
    try
    {
      zzlm localzzlm = new zzlm(this.zzaaO, paramL);
      this.zzacl.add(localzzlm);
      return localzzlm;
    }
    finally
    {
      this.zzabt.unlock();
    }
  }
  
  void zzoa()
  {
    this.zzabt.lock();
    try
    {
      this.zzacj = new zzlg(this, this.zzabI, this.zzabJ, this.zzaaP, this.zzaaQ, this.zzabt, this.mContext);
      this.zzacj.begin();
      this.zzabY.signalAll();
      return;
    }
    finally
    {
      this.zzabt.unlock();
    }
  }
  
  void zzob()
  {
    this.zzabt.lock();
    try
    {
      zzof();
      this.zzacj = new zzlf(this);
      this.zzacj.begin();
      this.zzabY.signalAll();
      return;
    }
    finally
    {
      this.zzabt.unlock();
    }
  }
  
  boolean zzoc()
  {
    return this.zzacb;
  }
  
  void zzoe()
  {
    if (zzoc()) {}
    for (;;)
    {
      return;
      this.zzacb = true;
      if (this.zzacf == null) {
        this.zzacf = ((zzd)zzll.zza(this.mContext.getApplicationContext(), new zzd(this), this.zzaaP));
      }
      this.zzace.sendMessageDelayed(this.zzace.obtainMessage(1), this.zzacc);
      this.zzace.sendMessageDelayed(this.zzace.obtainMessage(2), this.zzacd);
    }
  }
  
  boolean zzof()
  {
    boolean bool = false;
    if (!zzoc()) {}
    for (;;)
    {
      return bool;
      this.zzacb = false;
      this.zzace.removeMessages(2);
      this.zzace.removeMessages(1);
      if (this.zzacf != null)
      {
        this.zzacf.unregister();
        this.zzacf = null;
      }
      bool = true;
    }
  }
  
  String zzog()
  {
    StringWriter localStringWriter = new StringWriter();
    dump("", null, new PrintWriter(localStringWriter), null);
    return localStringWriter.toString();
  }
  
  static abstract class zzb
  {
    private final zzlj zzacy;
    
    protected zzb(zzlj paramzzlj)
    {
      this.zzacy = paramzzlj;
    }
    
    /* Error */
    public final void zzg(zzli paramzzli)
    {
      // Byte code:
      //   0: aload_1
      //   1: invokestatic 21	com/google/android/gms/internal/zzli:zzb	(Lcom/google/android/gms/internal/zzli;)Ljava/util/concurrent/locks/Lock;
      //   4: invokeinterface 26 1 0
      //   9: aload_1
      //   10: invokestatic 30	com/google/android/gms/internal/zzli:zzc	(Lcom/google/android/gms/internal/zzli;)Lcom/google/android/gms/internal/zzlj;
      //   13: astore_3
      //   14: aload_0
      //   15: getfield 16	com/google/android/gms/internal/zzli$zzb:zzacy	Lcom/google/android/gms/internal/zzlj;
      //   18: astore 4
      //   20: aload_3
      //   21: aload 4
      //   23: if_acmpeq +13 -> 36
      //   26: aload_1
      //   27: invokestatic 21	com/google/android/gms/internal/zzli:zzb	(Lcom/google/android/gms/internal/zzli;)Ljava/util/concurrent/locks/Lock;
      //   30: invokeinterface 33 1 0
      //   35: return
      //   36: aload_0
      //   37: invokevirtual 36	com/google/android/gms/internal/zzli$zzb:zznO	()V
      //   40: aload_1
      //   41: invokestatic 21	com/google/android/gms/internal/zzli:zzb	(Lcom/google/android/gms/internal/zzli;)Ljava/util/concurrent/locks/Lock;
      //   44: invokeinterface 33 1 0
      //   49: goto -14 -> 35
      //   52: astore_2
      //   53: aload_1
      //   54: invokestatic 21	com/google/android/gms/internal/zzli:zzb	(Lcom/google/android/gms/internal/zzli;)Ljava/util/concurrent/locks/Lock;
      //   57: invokeinterface 33 1 0
      //   62: aload_2
      //   63: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	64	0	this	zzb
      //   0	64	1	paramzzli	zzli
      //   52	11	2	localObject	Object
      //   13	8	3	localzzlj1	zzlj
      //   18	4	4	localzzlj2	zzlj
      // Exception table:
      //   from	to	target	type
      //   9	20	52	finally
      //   36	40	52	finally
    }
    
    protected abstract void zznO();
  }
  
  final class zza
    extends Handler
  {
    zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        Log.w("GoogleApiClientImpl", "Unknown message id: " + paramMessage.what);
      case 1: 
      case 2: 
      case 3: 
        for (;;)
        {
          return;
          zzli.zze(zzli.this);
          continue;
          zzli.zzd(zzli.this);
          continue;
          ((zzli.zzb)paramMessage.obj).zzg(zzli.this);
        }
      }
      throw ((RuntimeException)paramMessage.obj);
    }
  }
  
  static class zzd
    extends zzll
  {
    private WeakReference<zzli> zzacC;
    
    zzd(zzli paramzzli)
    {
      this.zzacC = new WeakReference(paramzzli);
    }
    
    public void zzoi()
    {
      zzli localzzli = (zzli)this.zzacC.get();
      if (localzzli == null) {}
      for (;;)
      {
        return;
        zzli.zzd(localzzli);
      }
    }
  }
  
  private static class zzc
    implements IBinder.DeathRecipient, zzli.zze
  {
    private final WeakReference<zza> zzacA;
    private final WeakReference<IBinder> zzacB;
    private final WeakReference<zzli.zzf<?>> zzacz;
    
    private zzc(zzli.zzf paramzzf, zza paramzza, IBinder paramIBinder)
    {
      this.zzacA = new WeakReference(paramzza);
      this.zzacz = new WeakReference(paramzzf);
      this.zzacB = new WeakReference(paramIBinder);
    }
    
    private void zzoh()
    {
      zzli.zzf localzzf = (zzli.zzf)this.zzacz.get();
      zza localzza = (zza)this.zzacA.get();
      if ((localzza != null) && (localzzf != null)) {
        localzza.remove(localzzf.zznF().intValue());
      }
      IBinder localIBinder = (IBinder)this.zzacB.get();
      if (this.zzacB != null) {
        localIBinder.unlinkToDeath(this, 0);
      }
    }
    
    public void binderDied()
    {
      zzoh();
    }
    
    public void zzc(zzli.zzf<?> paramzzf)
    {
      zzoh();
    }
  }
  
  static abstract interface zzf<A extends Api.zzb>
  {
    public abstract void cancel();
    
    public abstract boolean isReady();
    
    public abstract void zza(zzli.zze paramzze);
    
    public abstract void zzb(A paramA)
      throws DeadObjectException;
    
    public abstract Integer zznF();
    
    public abstract void zznJ();
    
    public abstract int zznK();
    
    public abstract Api.zzc<A> zznx();
    
    public abstract void zzv(Status paramStatus);
    
    public abstract void zzw(Status paramStatus);
  }
  
  static abstract interface zze
  {
    public abstract void zzc(zzli.zzf<?> paramzzf);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzli.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */