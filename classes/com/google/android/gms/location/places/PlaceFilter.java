package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter
  extends zza
  implements SafeParcelable
{
  public static final zzg CREATOR = new zzg();
  final int mVersionCode;
  final List<Integer> zzaFX;
  private final Set<Integer> zzaFY;
  final List<String> zzaFZ;
  final List<UserDataType> zzaGa;
  private final Set<String> zzaGb;
  private final Set<UserDataType> zzaGc;
  final boolean zzaGl;
  
  public PlaceFilter()
  {
    this(false, null);
  }
  
  PlaceFilter(int paramInt, List<Integer> paramList, boolean paramBoolean, List<String> paramList1, List<UserDataType> paramList2)
  {
    this.mVersionCode = paramInt;
    List localList1;
    List localList2;
    if (paramList == null)
    {
      localList1 = Collections.emptyList();
      this.zzaFX = localList1;
      this.zzaGl = paramBoolean;
      if (paramList2 != null) {
        break label104;
      }
      localList2 = Collections.emptyList();
      label39:
      this.zzaGa = localList2;
      if (paramList1 != null) {
        break label114;
      }
    }
    label104:
    label114:
    for (List localList3 = Collections.emptyList();; localList3 = Collections.unmodifiableList(paramList1))
    {
      this.zzaFZ = localList3;
      this.zzaFY = zzs(this.zzaFX);
      this.zzaGc = zzs(this.zzaGa);
      this.zzaGb = zzs(this.zzaFZ);
      return;
      localList1 = Collections.unmodifiableList(paramList);
      break;
      localList2 = Collections.unmodifiableList(paramList2);
      break label39;
    }
  }
  
  public PlaceFilter(Collection<Integer> paramCollection, boolean paramBoolean, Collection<String> paramCollection1, Collection<UserDataType> paramCollection2)
  {
    this(0, zzf(paramCollection), paramBoolean, zzf(paramCollection1), zzf(paramCollection2));
  }
  
  public PlaceFilter(boolean paramBoolean, Collection<String> paramCollection)
  {
    this(null, paramBoolean, paramCollection, null);
  }
  
  @Deprecated
  public static PlaceFilter zzwT()
  {
    return new zza(null).zzwU();
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
      if (!(paramObject instanceof PlaceFilter))
      {
        bool = false;
      }
      else
      {
        PlaceFilter localPlaceFilter = (PlaceFilter)paramObject;
        if ((!this.zzaFY.equals(localPlaceFilter.zzaFY)) || (this.zzaGl != localPlaceFilter.zzaGl) || (!this.zzaGc.equals(localPlaceFilter.zzaGc)) || (!this.zzaGb.equals(localPlaceFilter.zzaGb))) {
          bool = false;
        }
      }
    }
  }
  
  public Set<String> getPlaceIds()
  {
    return this.zzaGb;
  }
  
  public Set<Integer> getPlaceTypes()
  {
    return this.zzaFY;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = this.zzaFY;
    arrayOfObject[1] = Boolean.valueOf(this.zzaGl);
    arrayOfObject[2] = this.zzaGc;
    arrayOfObject[3] = this.zzaGb;
    return zzw.hashCode(arrayOfObject);
  }
  
  public boolean isRestrictedToPlacesOpenNow()
  {
    return this.zzaGl;
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzv(this);
    if (!this.zzaFY.isEmpty()) {
      localzza.zzg("types", this.zzaFY);
    }
    localzza.zzg("requireOpenNow", Boolean.valueOf(this.zzaGl));
    if (!this.zzaGb.isEmpty()) {
      localzza.zzg("placeIds", this.zzaGb);
    }
    if (!this.zzaGc.isEmpty()) {
      localzza.zzg("requestedUserDataTypes", this.zzaGc);
    }
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
  
  public Set<UserDataType> zzwS()
  {
    return this.zzaGc;
  }
  
  @Deprecated
  public static final class zza
  {
    private boolean zzaGl = false;
    private Collection<Integer> zzaGm = null;
    private Collection<UserDataType> zzaGn = null;
    private String[] zzaGo = null;
    
    public PlaceFilter zzwU()
    {
      List localList = null;
      ArrayList localArrayList1;
      if (this.zzaGm != null)
      {
        localArrayList1 = new ArrayList(this.zzaGm);
        if (this.zzaGn == null) {
          break label75;
        }
      }
      label75:
      for (ArrayList localArrayList2 = new ArrayList(this.zzaGn);; localArrayList2 = null)
      {
        if (this.zzaGo != null) {
          localList = Arrays.asList(this.zzaGo);
        }
        return new PlaceFilter(localArrayList1, this.zzaGl, localList, localArrayList2);
        localArrayList1 = null;
        break;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/PlaceFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */