package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzln;
import com.google.android.gms.internal.zzlo;

public final class PendingResults
{
  public static PendingResult<Status> canceledPendingResult()
  {
    zzlo localzzlo = new zzlo(Looper.getMainLooper());
    localzzlo.cancel();
    return localzzlo;
  }
  
  public static <R extends Result> PendingResult<R> canceledPendingResult(R paramR)
  {
    zzx.zzb(paramR, "Result must not be null");
    if (paramR.getStatus().getStatusCode() == 16) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Status code must be CommonStatusCodes.CANCELED");
      zza localzza = new zza(paramR);
      localzza.cancel();
      return localzza;
    }
  }
  
  public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R paramR)
  {
    zzx.zzb(paramR, "Result must not be null");
    zzc localzzc = new zzc(null);
    localzzc.zzb(paramR);
    return new zzln(localzzc);
  }
  
  public static PendingResult<Status> immediatePendingResult(Status paramStatus)
  {
    zzx.zzb(paramStatus, "Result must not be null");
    zzlo localzzlo = new zzlo(Looper.getMainLooper());
    localzzlo.zzb(paramStatus);
    return localzzlo;
  }
  
  public static <R extends Result> PendingResult<R> zza(R paramR, GoogleApiClient paramGoogleApiClient)
  {
    zzx.zzb(paramR, "Result must not be null");
    if (!paramR.getStatus().isSuccess()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Status code must not be SUCCESS");
      zzb localzzb = new zzb(paramGoogleApiClient, paramR);
      localzzb.zzb(paramR);
      return localzzb;
    }
  }
  
  public static PendingResult<Status> zza(Status paramStatus, GoogleApiClient paramGoogleApiClient)
  {
    zzx.zzb(paramStatus, "Result must not be null");
    zzlo localzzlo = new zzlo(paramGoogleApiClient);
    localzzlo.zzb(paramStatus);
    return localzzlo;
  }
  
  private static final class zza<R extends Result>
    extends zzlc<R>
  {
    private final R zzaaW;
    
    public zza(R paramR)
    {
      super();
      this.zzaaW = paramR;
    }
    
    protected R zzb(Status paramStatus)
    {
      if (paramStatus.getStatusCode() != this.zzaaW.getStatus().getStatusCode()) {
        throw new UnsupportedOperationException("Creating failed results is not supported");
      }
      return this.zzaaW;
    }
  }
  
  private static final class zzb<R extends Result>
    extends zzlc<R>
  {
    private final R zzaaX;
    
    public zzb(GoogleApiClient paramGoogleApiClient, R paramR)
    {
      super();
      this.zzaaX = paramR;
    }
    
    protected R zzb(Status paramStatus)
    {
      return this.zzaaX;
    }
  }
  
  private static final class zzc<R extends Result>
    extends zzlc<R>
  {
    public zzc(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    protected R zzb(Status paramStatus)
    {
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/api/PendingResults.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */