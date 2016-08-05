package com.google.android.gms.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;

@zzgr
public class zzgd
  implements Runnable
{
  private final Handler zzDk;
  private final long zzDl;
  private long zzDm;
  private zzja.zza zzDn;
  protected boolean zzDo;
  protected boolean zzDp;
  private final int zznQ;
  private final int zznR;
  protected final zziz zzoM;
  
  public zzgd(zzja.zza paramzza, zziz paramzziz, int paramInt1, int paramInt2)
  {
    this(paramzza, paramzziz, paramInt1, paramInt2, 200L, 50L);
  }
  
  public zzgd(zzja.zza paramzza, zziz paramzziz, int paramInt1, int paramInt2, long paramLong1, long paramLong2)
  {
    this.zzDl = paramLong1;
    this.zzDm = paramLong2;
    this.zzDk = new Handler(Looper.getMainLooper());
    this.zzoM = paramzziz;
    this.zzDn = paramzza;
    this.zzDo = false;
    this.zzDp = false;
    this.zznR = paramInt2;
    this.zznQ = paramInt1;
  }
  
  public void run()
  {
    if ((this.zzoM == null) || (zzfx())) {
      this.zzDn.zza(this.zzoM, true);
    }
    for (;;)
    {
      return;
      new zza(this.zzoM.getWebView()).execute(new Void[0]);
    }
  }
  
  public void zza(AdResponseParcel paramAdResponseParcel)
  {
    zza(paramAdResponseParcel, new zzji(this, this.zzoM, paramAdResponseParcel.zzER));
  }
  
  public void zza(AdResponseParcel paramAdResponseParcel, zzji paramzzji)
  {
    this.zzoM.setWebViewClient(paramzzji);
    zziz localzziz = this.zzoM;
    if (TextUtils.isEmpty(paramAdResponseParcel.zzBF)) {}
    for (String str = null;; str = zzp.zzbv().zzaz(paramAdResponseParcel.zzBF))
    {
      localzziz.loadDataWithBaseURL(str, paramAdResponseParcel.body, "text/html", "UTF-8", null);
      return;
    }
  }
  
  public void zzfv()
  {
    this.zzDk.postDelayed(this, this.zzDl);
  }
  
  /**
   * @deprecated
   */
  public void zzfw()
  {
    try
    {
      this.zzDo = true;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean zzfx()
  {
    try
    {
      boolean bool = this.zzDo;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean zzfy()
  {
    return this.zzDp;
  }
  
  protected final class zza
    extends AsyncTask<Void, Void, Boolean>
  {
    private final WebView zzDq;
    private Bitmap zzDr;
    
    public zza(WebView paramWebView)
    {
      this.zzDq = paramWebView;
    }
    
    /**
     * @deprecated
     */
    protected void onPreExecute()
    {
      try
      {
        this.zzDr = Bitmap.createBitmap(zzgd.zza(zzgd.this), zzgd.zzb(zzgd.this), Bitmap.Config.ARGB_8888);
        this.zzDq.setVisibility(0);
        this.zzDq.measure(View.MeasureSpec.makeMeasureSpec(zzgd.zza(zzgd.this), 0), View.MeasureSpec.makeMeasureSpec(zzgd.zzb(zzgd.this), 0));
        this.zzDq.layout(0, 0, zzgd.zza(zzgd.this), zzgd.zzb(zzgd.this));
        Canvas localCanvas = new Canvas(this.zzDr);
        this.zzDq.draw(localCanvas);
        this.zzDq.invalidate();
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    /**
     * @deprecated
     */
    protected Boolean zza(Void... paramVarArgs)
    {
      for (;;)
      {
        int k;
        try
        {
          int i = this.zzDr.getWidth();
          int j = this.zzDr.getHeight();
          Object localObject2;
          if ((i == 0) || (j == 0))
          {
            Boolean localBoolean1 = Boolean.valueOf(false);
            localObject2 = localBoolean1;
            return (Boolean)localObject2;
          }
          k = 0;
          int m = 0;
          int n;
          if (k < i)
          {
            n = 0;
            if (n >= j) {
              break label139;
            }
            if (this.zzDr.getPixel(k, n) != 0) {
              m++;
            }
          }
          else
          {
            if (m / (i * j / 100.0D) > 0.1D)
            {
              bool = true;
              Boolean localBoolean2 = Boolean.valueOf(bool);
              localObject2 = localBoolean2;
              continue;
            }
            boolean bool = false;
            continue;
          }
          n += 10;
        }
        finally {}
        continue;
        label139:
        k += 10;
      }
    }
    
    protected void zza(Boolean paramBoolean)
    {
      zzgd.zzc(zzgd.this);
      if ((paramBoolean.booleanValue()) || (zzgd.this.zzfx()) || (zzgd.zzd(zzgd.this) <= 0L))
      {
        zzgd.this.zzDp = paramBoolean.booleanValue();
        zzgd.zze(zzgd.this).zza(zzgd.this.zzoM, true);
      }
      for (;;)
      {
        return;
        if (zzgd.zzd(zzgd.this) > 0L)
        {
          if (zzb.zzN(2)) {
            zzb.zzaF("Ad not detected, scheduling another run.");
          }
          zzgd.zzg(zzgd.this).postDelayed(zzgd.this, zzgd.zzf(zzgd.this));
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */