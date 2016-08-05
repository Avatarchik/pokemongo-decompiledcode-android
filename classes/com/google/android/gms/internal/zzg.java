package com.google.android.gms.internal;

import android.net.TrafficStats;
import android.os.Build.VERSION;
import java.util.concurrent.BlockingQueue;

public class zzg
  extends Thread
{
  private final zzb zzj;
  private final zzn zzk;
  private volatile boolean zzl = false;
  private final BlockingQueue<zzk<?>> zzy;
  private final zzf zzz;
  
  public zzg(BlockingQueue<zzk<?>> paramBlockingQueue, zzf paramzzf, zzb paramzzb, zzn paramzzn)
  {
    this.zzy = paramBlockingQueue;
    this.zzz = paramzzf;
    this.zzj = paramzzb;
    this.zzk = paramzzn;
  }
  
  private void zzb(zzk<?> paramzzk)
  {
    if (Build.VERSION.SDK_INT >= 14) {
      TrafficStats.setThreadStatsTag(paramzzk.zzg());
    }
  }
  
  private void zzb(zzk<?> paramzzk, zzr paramzzr)
  {
    zzr localzzr = paramzzk.zzb(paramzzr);
    this.zzk.zza(paramzzk, localzzr);
  }
  
  public void quit()
  {
    this.zzl = true;
    interrupt();
  }
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: bipush 10
    //   2: invokestatic 75	android/os/Process:setThreadPriority	(I)V
    //   5: invokestatic 81	android/os/SystemClock:elapsedRealtime	()J
    //   8: lstore_1
    //   9: aload_0
    //   10: getfield 24	com/google/android/gms/internal/zzg:zzy	Ljava/util/concurrent/BlockingQueue;
    //   13: invokeinterface 87 1 0
    //   18: checkcast 40	com/google/android/gms/internal/zzk
    //   21: astore 4
    //   23: aload 4
    //   25: ldc 89
    //   27: invokevirtual 93	com/google/android/gms/internal/zzk:zzc	(Ljava/lang/String;)V
    //   30: aload 4
    //   32: invokevirtual 97	com/google/android/gms/internal/zzk:isCanceled	()Z
    //   35: ifeq +45 -> 80
    //   38: aload 4
    //   40: ldc 99
    //   42: invokevirtual 102	com/google/android/gms/internal/zzk:zzd	(Ljava/lang/String;)V
    //   45: goto -40 -> 5
    //   48: astore 8
    //   50: aload 8
    //   52: invokestatic 81	android/os/SystemClock:elapsedRealtime	()J
    //   55: lload_1
    //   56: lsub
    //   57: invokevirtual 105	com/google/android/gms/internal/zzr:zza	(J)V
    //   60: aload_0
    //   61: aload 4
    //   63: aload 8
    //   65: invokespecial 107	com/google/android/gms/internal/zzg:zzb	(Lcom/google/android/gms/internal/zzk;Lcom/google/android/gms/internal/zzr;)V
    //   68: goto -63 -> 5
    //   71: astore_3
    //   72: aload_0
    //   73: getfield 22	com/google/android/gms/internal/zzg:zzl	Z
    //   76: ifeq -71 -> 5
    //   79: return
    //   80: aload_0
    //   81: aload 4
    //   83: invokespecial 109	com/google/android/gms/internal/zzg:zzb	(Lcom/google/android/gms/internal/zzk;)V
    //   86: aload_0
    //   87: getfield 26	com/google/android/gms/internal/zzg:zzz	Lcom/google/android/gms/internal/zzf;
    //   90: aload 4
    //   92: invokeinterface 114 2 0
    //   97: astore 9
    //   99: aload 4
    //   101: ldc 116
    //   103: invokevirtual 93	com/google/android/gms/internal/zzk:zzc	(Ljava/lang/String;)V
    //   106: aload 9
    //   108: getfield 121	com/google/android/gms/internal/zzi:zzB	Z
    //   111: ifeq +84 -> 195
    //   114: aload 4
    //   116: invokevirtual 124	com/google/android/gms/internal/zzk:zzw	()Z
    //   119: ifeq +76 -> 195
    //   122: aload 4
    //   124: ldc 126
    //   126: invokevirtual 102	com/google/android/gms/internal/zzk:zzd	(Ljava/lang/String;)V
    //   129: goto -124 -> 5
    //   132: astore 5
    //   134: iconst_1
    //   135: anewarray 128	java/lang/Object
    //   138: astore 6
    //   140: aload 6
    //   142: iconst_0
    //   143: aload 5
    //   145: invokevirtual 132	java/lang/Exception:toString	()Ljava/lang/String;
    //   148: aastore
    //   149: aload 5
    //   151: ldc -122
    //   153: aload 6
    //   155: invokestatic 139	com/google/android/gms/internal/zzs:zza	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   158: new 68	com/google/android/gms/internal/zzr
    //   161: dup
    //   162: aload 5
    //   164: invokespecial 142	com/google/android/gms/internal/zzr:<init>	(Ljava/lang/Throwable;)V
    //   167: astore 7
    //   169: aload 7
    //   171: invokestatic 81	android/os/SystemClock:elapsedRealtime	()J
    //   174: lload_1
    //   175: lsub
    //   176: invokevirtual 105	com/google/android/gms/internal/zzr:zza	(J)V
    //   179: aload_0
    //   180: getfield 30	com/google/android/gms/internal/zzg:zzk	Lcom/google/android/gms/internal/zzn;
    //   183: aload 4
    //   185: aload 7
    //   187: invokeinterface 59 3 0
    //   192: goto -187 -> 5
    //   195: aload 4
    //   197: aload 9
    //   199: invokevirtual 145	com/google/android/gms/internal/zzk:zza	(Lcom/google/android/gms/internal/zzi;)Lcom/google/android/gms/internal/zzm;
    //   202: astore 10
    //   204: aload 4
    //   206: ldc -109
    //   208: invokevirtual 93	com/google/android/gms/internal/zzk:zzc	(Ljava/lang/String;)V
    //   211: aload 4
    //   213: invokevirtual 150	com/google/android/gms/internal/zzk:zzr	()Z
    //   216: ifeq +37 -> 253
    //   219: aload 10
    //   221: getfield 156	com/google/android/gms/internal/zzm:zzag	Lcom/google/android/gms/internal/zzb$zza;
    //   224: ifnull +29 -> 253
    //   227: aload_0
    //   228: getfield 28	com/google/android/gms/internal/zzg:zzj	Lcom/google/android/gms/internal/zzb;
    //   231: aload 4
    //   233: invokevirtual 159	com/google/android/gms/internal/zzk:zzh	()Ljava/lang/String;
    //   236: aload 10
    //   238: getfield 156	com/google/android/gms/internal/zzm:zzag	Lcom/google/android/gms/internal/zzb$zza;
    //   241: invokeinterface 164 3 0
    //   246: aload 4
    //   248: ldc -90
    //   250: invokevirtual 93	com/google/android/gms/internal/zzk:zzc	(Ljava/lang/String;)V
    //   253: aload 4
    //   255: invokevirtual 169	com/google/android/gms/internal/zzk:zzv	()V
    //   258: aload_0
    //   259: getfield 30	com/google/android/gms/internal/zzg:zzk	Lcom/google/android/gms/internal/zzn;
    //   262: aload 4
    //   264: aload 10
    //   266: invokeinterface 172 3 0
    //   271: goto -266 -> 5
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	274	0	this	zzg
    //   8	167	1	l	long
    //   71	1	3	localInterruptedException	InterruptedException
    //   21	242	4	localzzk	zzk
    //   132	31	5	localException	Exception
    //   138	16	6	arrayOfObject	Object[]
    //   167	19	7	localzzr1	zzr
    //   48	16	8	localzzr2	zzr
    //   97	101	9	localzzi	zzi
    //   202	63	10	localzzm	zzm
    // Exception table:
    //   from	to	target	type
    //   23	45	48	com/google/android/gms/internal/zzr
    //   80	129	48	com/google/android/gms/internal/zzr
    //   195	271	48	com/google/android/gms/internal/zzr
    //   9	23	71	java/lang/InterruptedException
    //   23	45	132	java/lang/Exception
    //   80	129	132	java/lang/Exception
    //   195	271	132	java/lang/Exception
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */