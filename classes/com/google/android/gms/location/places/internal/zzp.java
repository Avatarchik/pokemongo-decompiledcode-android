package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class zzp
{
  private static final String TAG = zzp.class.getSimpleName();
  private static final long zzaHF = TimeUnit.SECONDS.toMillis(1L);
  private static zzp zzaHG;
  private final Context mContext;
  private final Handler mHandler;
  private final Runnable zzaHH = new zza(null);
  private ArrayList<String> zzaHI = null;
  private ArrayList<String> zzaHJ = null;
  private final Object zzpd = new Object();
  
  private zzp(Context paramContext)
  {
    this((Context)zzx.zzw(paramContext), new Handler(Looper.getMainLooper()));
  }
  
  zzp(Context paramContext, Handler paramHandler)
  {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public static zzp zzaF(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 57	com/google/android/gms/common/internal/zzx:zzw	(Ljava/lang/Object;)Ljava/lang/Object;
    //   7: pop
    //   8: getstatic 99	android/os/Build$VERSION:SDK_INT	I
    //   11: istore_3
    //   12: iload_3
    //   13: bipush 14
    //   15: if_icmpge +12 -> 27
    //   18: aconst_null
    //   19: astore 4
    //   21: ldc 2
    //   23: monitorexit
    //   24: aload 4
    //   26: areturn
    //   27: getstatic 101	com/google/android/gms/location/places/internal/zzp:zzaHG	Lcom/google/android/gms/location/places/internal/zzp;
    //   30: ifnonnull +17 -> 47
    //   33: new 2	com/google/android/gms/location/places/internal/zzp
    //   36: dup
    //   37: aload_0
    //   38: invokevirtual 105	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   41: invokespecial 107	com/google/android/gms/location/places/internal/zzp:<init>	(Landroid/content/Context;)V
    //   44: putstatic 101	com/google/android/gms/location/places/internal/zzp:zzaHG	Lcom/google/android/gms/location/places/internal/zzp;
    //   47: getstatic 101	com/google/android/gms/location/places/internal/zzp:zzaHG	Lcom/google/android/gms/location/places/internal/zzp;
    //   50: astore 4
    //   52: goto -31 -> 21
    //   55: astore_1
    //   56: ldc 2
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	paramContext	Context
    //   55	5	1	localObject	Object
    //   11	5	3	i	int
    //   19	32	4	localzzp	zzp
    // Exception table:
    //   from	to	target	type
    //   3	12	55	finally
    //   27	52	55	finally
  }
  
  public void zzE(String paramString1, String paramString2)
  {
    synchronized (this.zzpd)
    {
      if (this.zzaHI == null)
      {
        this.zzaHI = new ArrayList();
        this.zzaHJ = new ArrayList();
        this.mHandler.postDelayed(this.zzaHH, zzaHF);
      }
      this.zzaHI.add(paramString1);
      this.zzaHJ.add(paramString2);
      if (this.zzaHI.size() >= 10000)
      {
        if (Log.isLoggable(TAG, 5)) {
          Log.w(TAG, "Event buffer full, flushing");
        }
        this.zzaHH.run();
        this.mHandler.removeCallbacks(this.zzaHH);
      }
    }
  }
  
  private class zza
    implements Runnable
  {
    private zza() {}
    
    public void run()
    {
      synchronized (zzp.zzb(zzp.this))
      {
        Intent localIntent = new Intent("com.google.android.location.places.METHOD_CALL");
        localIntent.setPackage("com.google.android.gms");
        localIntent.putStringArrayListExtra("PLACE_IDS", zzp.zzc(zzp.this));
        localIntent.putStringArrayListExtra("METHOD_NAMES", zzp.zzd(zzp.this));
        localIntent.putExtra("PACKAGE_NAME", zzp.zze(zzp.this).getPackageName());
        localIntent.putExtra("CLIENT_VERSION", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        zzp.zze(zzp.this).sendBroadcast(localIntent);
        zzp.zza(zzp.this, null);
        zzp.zzb(zzp.this, null);
        return;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */