package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.List;

public class HereContent
  implements SafeParcelable
{
  public static final zzb CREATOR = new zzb();
  final int mVersionCode;
  private final String zzaHW;
  private final List<Action> zzaHX;
  
  HereContent(int paramInt, String paramString, List<Action> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzaHW = paramString;
    this.zzaHX = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof HereContent))
      {
        bool = false;
      }
      else
      {
        HereContent localHereContent = (HereContent)paramObject;
        if ((!zzw.equal(this.zzaHW, localHereContent.zzaHW)) || (!zzw.equal(this.zzaHX, localHereContent.zzaHX))) {
          bool = false;
        }
      }
    }
  }
  
  public List<Action> getActions()
  {
    return this.zzaHX;
  }
  
  public String getData()
  {
    return this.zzaHW;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.zzaHW;
    arrayOfObject[1] = this.zzaHX;
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("data", this.zzaHW).zzg("actions", this.zzaHX).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public static final class Action
    implements SafeParcelable
  {
    public static final zza CREATOR = new zza();
    final int mVersionCode;
    private final String zzQg;
    private final String zzajf;
    
    Action(int paramInt, String paramString1, String paramString2)
    {
      this.mVersionCode = paramInt;
      this.zzajf = paramString1;
      this.zzQg = paramString2;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (this == paramObject) {}
      for (;;)
      {
        return bool;
        if (!(paramObject instanceof Action))
        {
          bool = false;
        }
        else
        {
          Action localAction = (Action)paramObject;
          if ((!zzw.equal(this.zzajf, localAction.zzajf)) || (!zzw.equal(this.zzQg, localAction.zzQg))) {
            bool = false;
          }
        }
      }
    }
    
    public String getTitle()
    {
      return this.zzajf;
    }
    
    public String getUri()
    {
      return this.zzQg;
    }
    
    public int hashCode()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.zzajf;
      arrayOfObject[1] = this.zzQg;
      return zzw.hashCode(arrayOfObject);
    }
    
    public String toString()
    {
      return zzw.zzv(this).zzg("title", this.zzajf).zzg("uri", this.zzQg).toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zza.zza(this, paramParcel, paramInt);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/personalized/HereContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */