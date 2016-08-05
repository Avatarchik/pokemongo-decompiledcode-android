package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class zzd<T extends SafeParcelable>
  extends AbstractDataBuffer<T>
{
  private static final String[] zzadn;
  private final Parcelable.Creator<T> zzado;
  
  static
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "data";
    zzadn = arrayOfString;
  }
  
  public zzd(DataHolder paramDataHolder, Parcelable.Creator<T> paramCreator)
  {
    super(paramDataHolder);
    this.zzado = paramCreator;
  }
  
  public T zzbs(int paramInt)
  {
    byte[] arrayOfByte = this.zzabq.zzg("data", paramInt, this.zzabq.zzbt(paramInt));
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
    localParcel.setDataPosition(0);
    SafeParcelable localSafeParcelable = (SafeParcelable)this.zzado.createFromParcel(localParcel);
    localParcel.recycle();
    return localSafeParcelable;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/data/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */