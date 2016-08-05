package com.google.android.gms.common.api;

import com.google.android.gms.internal.zzlc;
import java.util.ArrayList;
import java.util.List;

public final class Batch
  extends zzlc<BatchResult>
{
  private boolean zzaaA;
  private final PendingResult<?>[] zzaaB;
  private int zzaay;
  private boolean zzaaz;
  private final Object zzpd = new Object();
  
  private Batch(List<PendingResult<?>> paramList, GoogleApiClient paramGoogleApiClient)
  {
    super(paramGoogleApiClient);
    this.zzaay = paramList.size();
    this.zzaaB = new PendingResult[this.zzaay];
    for (int i = 0; i < paramList.size(); i++)
    {
      PendingResult localPendingResult = (PendingResult)paramList.get(i);
      this.zzaaB[i] = localPendingResult;
      localPendingResult.zza(new PendingResult.zza()
      {
        public void zzt(Status paramAnonymousStatus)
        {
          label105:
          Status localStatus;
          synchronized (Batch.zza(Batch.this))
          {
            if (!Batch.this.isCanceled()) {
              if (paramAnonymousStatus.isCanceled())
              {
                Batch.zza(Batch.this, true);
                Batch.zzb(Batch.this);
                if (Batch.zzc(Batch.this) == 0)
                {
                  if (!Batch.zzd(Batch.this)) {
                    break label105;
                  }
                  Batch.zze(Batch.this);
                }
              }
            }
          }
        }
      });
    }
  }
  
  public void cancel()
  {
    super.cancel();
    PendingResult[] arrayOfPendingResult = this.zzaaB;
    int i = arrayOfPendingResult.length;
    for (int j = 0; j < i; j++) {
      arrayOfPendingResult[j].cancel();
    }
  }
  
  public BatchResult createFailedResult(Status paramStatus)
  {
    return new BatchResult(paramStatus, this.zzaaB);
  }
  
  public static final class Builder
  {
    private GoogleApiClient zzVs;
    private List<PendingResult<?>> zzaaD = new ArrayList();
    
    public Builder(GoogleApiClient paramGoogleApiClient)
    {
      this.zzVs = paramGoogleApiClient;
    }
    
    public <R extends Result> BatchResultToken<R> add(PendingResult<R> paramPendingResult)
    {
      BatchResultToken localBatchResultToken = new BatchResultToken(this.zzaaD.size());
      this.zzaaD.add(paramPendingResult);
      return localBatchResultToken;
    }
    
    public Batch build()
    {
      return new Batch(this.zzaaD, this.zzVs, null);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/api/Batch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */