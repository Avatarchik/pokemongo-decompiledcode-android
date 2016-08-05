package com.google.android.gms.internal;

@zzgr
public final class zzeg
  extends zzeo.zza
{
  private final Object zzpd = new Object();
  private zzei.zza zzzh;
  private zzef zzzi;
  
  public void onAdClicked()
  {
    synchronized (this.zzpd)
    {
      if (this.zzzi != null) {
        this.zzzi.zzaX();
      }
      return;
    }
  }
  
  public void onAdClosed()
  {
    synchronized (this.zzpd)
    {
      if (this.zzzi != null) {
        this.zzzi.zzaY();
      }
      return;
    }
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    for (;;)
    {
      synchronized (this.zzpd)
      {
        if (this.zzzh != null)
        {
          if (paramInt == 3)
          {
            i = 1;
            this.zzzh.zzq(i);
            this.zzzh = null;
          }
        }
        else {
          return;
        }
      }
      int i = 2;
    }
  }
  
  public void onAdLeftApplication()
  {
    synchronized (this.zzpd)
    {
      if (this.zzzi != null) {
        this.zzzi.zzaZ();
      }
      return;
    }
  }
  
  public void onAdLoaded()
  {
    synchronized (this.zzpd)
    {
      if (this.zzzh != null)
      {
        this.zzzh.zzq(0);
        this.zzzh = null;
      }
      else if (this.zzzi != null)
      {
        this.zzzi.zzbb();
      }
    }
  }
  
  public void onAdOpened()
  {
    synchronized (this.zzpd)
    {
      if (this.zzzi != null) {
        this.zzzi.zzba();
      }
      return;
    }
  }
  
  public void zza(zzef paramzzef)
  {
    synchronized (this.zzpd)
    {
      this.zzzi = paramzzef;
      return;
    }
  }
  
  public void zza(zzei.zza paramzza)
  {
    synchronized (this.zzpd)
    {
      this.zzzh = paramzza;
      return;
    }
  }
  
  public void zza(zzep paramzzep)
  {
    synchronized (this.zzpd)
    {
      if (this.zzzh != null)
      {
        this.zzzh.zza(0, paramzzep);
        this.zzzh = null;
      }
      else if (this.zzzi != null)
      {
        this.zzzi.zzbb();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzeg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */