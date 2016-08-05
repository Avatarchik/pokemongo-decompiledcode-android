package com.google.android.gms.location.places;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.internal.zzs;

public class PlaceBuffer
  extends AbstractDataBuffer<Place>
  implements Result
{
  private final Context mContext;
  private final Status zzSC;
  private final String zzaGk;
  
  public PlaceBuffer(DataHolder paramDataHolder, Context paramContext)
  {
    super(paramDataHolder);
    this.mContext = paramContext;
    this.zzSC = PlacesStatusCodes.zzhp(paramDataHolder.getStatusCode());
    if ((paramDataHolder != null) && (paramDataHolder.zzor() != null)) {}
    for (this.zzaGk = paramDataHolder.zzor().getString("com.google.android.gms.location.places.PlaceBuffer.ATTRIBUTIONS_EXTRA_KEY");; this.zzaGk = null) {
      return;
    }
  }
  
  public Place get(int paramInt)
  {
    return new zzs(this.zzabq, paramInt, this.mContext);
  }
  
  public CharSequence getAttributions()
  {
    return this.zzaGk;
  }
  
  public Status getStatus()
  {
    return this.zzSC;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/PlaceBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */