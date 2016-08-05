package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeofencingEvent
{
  private final int zzDv;
  private final int zzaEq;
  private final List<Geofence> zzaEr;
  private final Location zzaEs;
  
  private GeofencingEvent(int paramInt1, int paramInt2, List<Geofence> paramList, Location paramLocation)
  {
    this.zzDv = paramInt1;
    this.zzaEq = paramInt2;
    this.zzaEr = paramList;
    this.zzaEs = paramLocation;
  }
  
  public static GeofencingEvent fromIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (GeofencingEvent localGeofencingEvent = null;; localGeofencingEvent = new GeofencingEvent(paramIntent.getIntExtra("gms_error_code", -1), zzs(paramIntent), zzt(paramIntent), (Location)paramIntent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"))) {
      return localGeofencingEvent;
    }
  }
  
  private static int zzs(Intent paramIntent)
  {
    int i = -1;
    int j = paramIntent.getIntExtra("com.google.android.location.intent.extra.transition", i);
    if (j == i) {}
    for (;;)
    {
      return i;
      if ((j == 1) || (j == 2) || (j == 4)) {
        i = j;
      }
    }
  }
  
  private static List<Geofence> zzt(Intent paramIntent)
  {
    ArrayList localArrayList1 = (ArrayList)paramIntent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
    if (localArrayList1 == null) {}
    ArrayList localArrayList2;
    for (Object localObject = null;; localObject = localArrayList2)
    {
      return (List<Geofence>)localObject;
      localArrayList2 = new ArrayList(localArrayList1.size());
      Iterator localIterator = localArrayList1.iterator();
      while (localIterator.hasNext()) {
        localArrayList2.add(ParcelableGeofence.zzn((byte[])localIterator.next()));
      }
    }
  }
  
  public int getErrorCode()
  {
    return this.zzDv;
  }
  
  public int getGeofenceTransition()
  {
    return this.zzaEq;
  }
  
  public List<Geofence> getTriggeringGeofences()
  {
    return this.zzaEr;
  }
  
  public Location getTriggeringLocation()
  {
    return this.zzaEs;
  }
  
  public boolean hasError()
  {
    if (this.zzDv != -1) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/GeofencingEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */