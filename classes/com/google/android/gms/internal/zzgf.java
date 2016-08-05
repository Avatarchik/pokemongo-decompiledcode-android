package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzgr
public abstract class zzgf
  extends zzhz
{
  protected final Context mContext;
  protected final zzgg.zza zzDd;
  protected final zzhs.zza zzDe;
  protected AdResponseParcel zzDf;
  protected final Object zzDh = new Object();
  protected final Object zzpd = new Object();
  
  protected zzgf(Context paramContext, zzhs.zza paramzza, zzgg.zza paramzza1)
  {
    super(true);
    this.mContext = paramContext;
    this.zzDe = paramzza;
    this.zzDf = paramzza.zzHD;
    this.zzDd = paramzza1;
  }
  
  public void onStop() {}
  
  protected abstract zzhs zzA(int paramInt);
  
  public void zzbn()
  {
    for (;;)
    {
      int j;
      synchronized (this.zzpd)
      {
        zzb.zzaF("AdRendererBackgroundTask started.");
        int i = this.zzDe.errorCode;
        try
        {
          zzh(SystemClock.elapsedRealtime());
          final zzhs localzzhs = zzA(i);
          zzid.zzIE.post(new Runnable()
          {
            public void run()
            {
              synchronized (zzgf.this.zzpd)
              {
                zzgf.this.zzi(localzzhs);
                return;
              }
            }
          });
          return;
        }
        catch (zza localzza)
        {
          j = localzza.getErrorCode();
          if (j == 3) {
            continue;
          }
        }
        if (j == -1)
        {
          zzb.zzaG(localzza.getMessage());
          if (this.zzDf == null)
          {
            this.zzDf = new AdResponseParcel(j);
            zzid.zzIE.post(new Runnable()
            {
              public void run()
              {
                zzgf.this.onStop();
              }
            });
            i = j;
          }
        }
        else
        {
          zzb.zzaH(localzza.getMessage());
        }
      }
      this.zzDf = new AdResponseParcel(j, this.zzDf.zzzc);
    }
  }
  
  protected abstract void zzh(long paramLong)
    throws zzgf.zza;
  
  protected void zzi(zzhs paramzzhs)
  {
    this.zzDd.zzb(paramzzhs);
  }
  
  protected static final class zza
    extends Exception
  {
    private final int zzDv;
    
    public zza(String paramString, int paramInt)
    {
      super();
      this.zzDv = paramInt;
    }
    
    public int getErrorCode()
    {
      return this.zzDv;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */