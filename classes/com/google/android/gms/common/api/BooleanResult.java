package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzx;

public class BooleanResult
  implements Result
{
  private final Status zzSC;
  private final boolean zzaaE;
  
  public BooleanResult(Status paramStatus, boolean paramBoolean)
  {
    this.zzSC = ((Status)zzx.zzb(paramStatus, "Status must not be null"));
    this.zzaaE = paramBoolean;
  }
  
  public final boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof BooleanResult))
      {
        bool = false;
      }
      else
      {
        BooleanResult localBooleanResult = (BooleanResult)paramObject;
        if ((!this.zzSC.equals(localBooleanResult.zzSC)) || (this.zzaaE != localBooleanResult.zzaaE)) {
          bool = false;
        }
      }
    }
  }
  
  public Status getStatus()
  {
    return this.zzSC;
  }
  
  public boolean getValue()
  {
    return this.zzaaE;
  }
  
  public final int hashCode()
  {
    int i = 31 * (527 + this.zzSC.hashCode());
    if (this.zzaaE) {}
    for (int j = 1;; j = 0) {
      return j + i;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/api/BooleanResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */