package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutocompleteFilter
  implements SafeParcelable
{
  public static final zzc CREATOR = new zzc();
  final int mVersionCode;
  final boolean zzaFW;
  final List<Integer> zzaFX;
  private final Set<Integer> zzaFY;
  
  AutocompleteFilter(int paramInt, boolean paramBoolean, Collection<Integer> paramCollection)
  {
    this.mVersionCode = paramInt;
    this.zzaFW = paramBoolean;
    Object localObject;
    if (paramCollection == null)
    {
      localObject = Collections.emptyList();
      this.zzaFX = ((List)localObject);
      if (!this.zzaFX.isEmpty()) {
        break label62;
      }
    }
    label62:
    for (this.zzaFY = Collections.emptySet();; this.zzaFY = Collections.unmodifiableSet(new HashSet(this.zzaFX)))
    {
      return;
      localObject = new ArrayList(paramCollection);
      break;
    }
  }
  
  public static AutocompleteFilter create(Collection<Integer> paramCollection)
  {
    return zza(true, paramCollection);
  }
  
  public static AutocompleteFilter zza(boolean paramBoolean, Collection<Integer> paramCollection)
  {
    return new AutocompleteFilter(0, paramBoolean, paramCollection);
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
      if (!(paramObject instanceof AutocompleteFilter))
      {
        bool = false;
      }
      else
      {
        AutocompleteFilter localAutocompleteFilter = (AutocompleteFilter)paramObject;
        if ((!this.zzaFY.equals(localAutocompleteFilter.zzaFY)) || (this.zzaFW != localAutocompleteFilter.zzaFW)) {
          bool = false;
        }
      }
    }
  }
  
  public Set<Integer> getPlaceTypes()
  {
    return this.zzaFY;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Boolean.valueOf(this.zzaFW);
    arrayOfObject[1] = this.zzaFY;
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzv(this);
    if (!this.zzaFW) {
      localzza.zzg("restrictedToPlaces", Boolean.valueOf(this.zzaFW));
    }
    localzza.zzg("placeTypes", this.zzaFY);
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzwM()
  {
    return this.zzaFW;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/AutocompleteFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */