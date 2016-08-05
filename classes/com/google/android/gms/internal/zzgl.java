package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.zzn;
import java.util.concurrent.Future;

@zzgr
public class zzgl
  extends zzhz
{
  private final zzgm zzDB;
  private Future<zzhs> zzDC;
  private final zzgg.zza zzDd;
  private final zzhs.zza zzDe;
  private final AdResponseParcel zzDf;
  private final Object zzpd = new Object();
  
  public zzgl(Context paramContext, zzn paramzzn, zzbc paramzzbc, zzhs.zza paramzza, zzan paramzzan, zzgg.zza paramzza1)
  {
    this(paramzza, paramzza1, new zzgm(paramContext, paramzzn, paramzzbc, new zzih(paramContext), paramzzan, paramzza));
  }
  
  zzgl(zzhs.zza paramzza, zzgg.zza paramzza1, zzgm paramzzgm)
  {
    this.zzDe = paramzza;
    this.zzDf = paramzza.zzHD;
    this.zzDd = paramzza1;
    this.zzDB = paramzzgm;
  }
  
  private zzhs zzB(int paramInt)
  {
    return new zzhs(this.zzDe.zzHC.zzEn, null, null, paramInt, null, null, this.zzDf.orientation, this.zzDf.zzzc, this.zzDe.zzHC.zzEq, false, null, null, null, null, null, this.zzDf.zzEL, this.zzDe.zzqn, this.zzDf.zzEJ, this.zzDe.zzHz, this.zzDf.zzEO, this.zzDf.zzEP, this.zzDe.zzHw, null);
  }
  
  public void onStop()
  {
    synchronized (this.zzpd)
    {
      if (this.zzDC != null) {
        this.zzDC.cancel(true);
      }
      return;
    }
  }
  
  /* Error */
  public void zzbn()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 43	com/google/android/gms/internal/zzgl:zzpd	Ljava/lang/Object;
    //   4: astore 9
    //   6: aload 9
    //   8: monitorenter
    //   9: aload_0
    //   10: aload_0
    //   11: getfield 56	com/google/android/gms/internal/zzgl:zzDB	Lcom/google/android/gms/internal/zzgm;
    //   14: invokestatic 135	com/google/android/gms/internal/zzic:zza	(Ljava/util/concurrent/Callable;)Lcom/google/android/gms/internal/zziq;
    //   17: putfield 115	com/google/android/gms/internal/zzgl:zzDC	Ljava/util/concurrent/Future;
    //   20: aload 9
    //   22: monitorexit
    //   23: aload_0
    //   24: getfield 115	com/google/android/gms/internal/zzgl:zzDC	Ljava/util/concurrent/Future;
    //   27: ldc2_w 136
    //   30: getstatic 143	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   33: invokeinterface 147 4 0
    //   38: checkcast 60	com/google/android/gms/internal/zzhs
    //   41: astore_2
    //   42: bipush -2
    //   44: istore_3
    //   45: aload_2
    //   46: ifnull +81 -> 127
    //   49: getstatic 153	com/google/android/gms/internal/zzid:zzIE	Landroid/os/Handler;
    //   52: new 6	com/google/android/gms/internal/zzgl$1
    //   55: dup
    //   56: aload_0
    //   57: aload_2
    //   58: invokespecial 156	com/google/android/gms/internal/zzgl$1:<init>	(Lcom/google/android/gms/internal/zzgl;Lcom/google/android/gms/internal/zzhs;)V
    //   61: invokevirtual 162	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   64: pop
    //   65: return
    //   66: astore 10
    //   68: aload 9
    //   70: monitorexit
    //   71: aload 10
    //   73: athrow
    //   74: astore 7
    //   76: ldc -92
    //   78: invokestatic 170	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   81: aload_0
    //   82: getfield 115	com/google/android/gms/internal/zzgl:zzDC	Ljava/util/concurrent/Future;
    //   85: iconst_1
    //   86: invokeinterface 121 2 0
    //   91: pop
    //   92: iconst_2
    //   93: istore_3
    //   94: aconst_null
    //   95: astore_2
    //   96: goto -51 -> 45
    //   99: astore 6
    //   101: iconst_0
    //   102: istore_3
    //   103: aconst_null
    //   104: astore_2
    //   105: goto -60 -> 45
    //   108: astore 5
    //   110: aconst_null
    //   111: astore_2
    //   112: bipush -1
    //   114: istore_3
    //   115: goto -70 -> 45
    //   118: astore_1
    //   119: aconst_null
    //   120: astore_2
    //   121: bipush -1
    //   123: istore_3
    //   124: goto -79 -> 45
    //   127: aload_0
    //   128: iload_3
    //   129: invokespecial 172	com/google/android/gms/internal/zzgl:zzB	(I)Lcom/google/android/gms/internal/zzhs;
    //   132: astore_2
    //   133: goto -84 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	this	zzgl
    //   118	1	1	localCancellationException	java.util.concurrent.CancellationException
    //   41	92	2	localzzhs	zzhs
    //   44	85	3	i	int
    //   108	1	5	localInterruptedException	InterruptedException
    //   99	1	6	localExecutionException	java.util.concurrent.ExecutionException
    //   74	1	7	localTimeoutException	java.util.concurrent.TimeoutException
    //   66	6	10	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   9	23	66	finally
    //   68	71	66	finally
    //   0	9	74	java/util/concurrent/TimeoutException
    //   23	42	74	java/util/concurrent/TimeoutException
    //   71	74	74	java/util/concurrent/TimeoutException
    //   0	9	99	java/util/concurrent/ExecutionException
    //   23	42	99	java/util/concurrent/ExecutionException
    //   71	74	99	java/util/concurrent/ExecutionException
    //   0	9	108	java/lang/InterruptedException
    //   23	42	108	java/lang/InterruptedException
    //   71	74	108	java/lang/InterruptedException
    //   0	9	118	java/util/concurrent/CancellationException
    //   23	42	118	java/util/concurrent/CancellationException
    //   71	74	118	java/util/concurrent/CancellationException
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */