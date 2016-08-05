package com.google.android.gms.location.places;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class zza
{
  static <E> List<E> zzf(Collection<E> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {}
    for (Object localObject = Collections.emptyList();; localObject = new ArrayList(paramCollection)) {
      return (List<E>)localObject;
    }
  }
  
  static <E> Set<E> zzs(List<E> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {}
    for (Set localSet = Collections.emptySet();; localSet = Collections.unmodifiableSet(new HashSet(paramList))) {
      return localSet;
    }
  }
  
  public abstract Set<String> getPlaceIds();
  
  public boolean isRestrictedToPlacesOpenNow()
  {
    return false;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */