package com.unity3d.player;

import android.content.Context;
import android.hardware.GeomagneticField;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import java.util.Iterator;
import java.util.List;

final class r
  implements LocationListener
{
  private final Context a;
  private final UnityPlayer b;
  private Location c;
  private float d = 0.0F;
  private boolean e = false;
  private int f = 0;
  private boolean g = false;
  private int h = 0;
  
  protected r(Context paramContext, UnityPlayer paramUnityPlayer)
  {
    this.a = paramContext;
    this.b = paramUnityPlayer;
  }
  
  private void a(int paramInt)
  {
    this.h = paramInt;
    this.b.nativeSetLocationStatus(paramInt);
  }
  
  private void a(Location paramLocation)
  {
    if (paramLocation == null) {}
    for (;;)
    {
      return;
      if (a(paramLocation, this.c))
      {
        this.c = paramLocation;
        GeomagneticField localGeomagneticField = new GeomagneticField((float)this.c.getLatitude(), (float)this.c.getLongitude(), (float)this.c.getAltitude(), this.c.getTime());
        this.b.nativeSetLocation((float)paramLocation.getLatitude(), (float)paramLocation.getLongitude(), (float)paramLocation.getAltitude(), paramLocation.getAccuracy(), paramLocation.getTime() / 1000.0D, localGeomagneticField.getDeclination());
      }
    }
  }
  
  private static boolean a(Location paramLocation1, Location paramLocation2)
  {
    boolean bool1 = true;
    if (paramLocation2 == null) {}
    label29:
    label40:
    boolean bool4;
    label70:
    label76:
    label80:
    for (;;)
    {
      return bool1;
      long l = paramLocation1.getTime() - paramLocation2.getTime();
      boolean bool2;
      boolean bool3;
      if (l > 120000L)
      {
        bool2 = bool1;
        if (l >= -120000L) {
          break label70;
        }
        bool3 = bool1;
        if (l <= 0L) {
          break label76;
        }
      }
      for (bool4 = bool1;; bool4 = false)
      {
        if (bool2) {
          break label80;
        }
        if (!bool3) {
          break label82;
        }
        bool1 = false;
        break;
        bool2 = false;
        break label29;
        bool3 = false;
        break label40;
      }
    }
    label82:
    int i = (int)(paramLocation1.getAccuracy() - paramLocation2.getAccuracy());
    boolean bool5;
    label102:
    boolean bool6;
    label110:
    boolean bool7;
    if (i > 0)
    {
      bool5 = bool1;
      if (i >= 0) {
        break label194;
      }
      bool6 = bool1;
      if (i <= 200) {
        break label200;
      }
      bool7 = bool1;
      label121:
      if (paramLocation1.getAccuracy() != 0.0F) {
        break label206;
      }
    }
    label194:
    label200:
    label206:
    for (boolean bool8 = bool1;; bool8 = false)
    {
      int j = bool7 | bool8;
      boolean bool9 = a(paramLocation1.getProvider(), paramLocation2.getProvider());
      if ((bool6) || ((bool4) && (!bool5)) || ((bool4) && (j == 0) && (bool9))) {
        break;
      }
      bool1 = false;
      break;
      bool5 = false;
      break label102;
      bool6 = false;
      break label110;
      bool7 = false;
      break label121;
    }
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    boolean bool;
    if (paramString1 == null) {
      if (paramString2 == null) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = paramString1.equals(paramString2);
    }
  }
  
  public final void a(float paramFloat)
  {
    this.d = paramFloat;
  }
  
  public final boolean a()
  {
    if (!((LocationManager)this.a.getSystemService("location")).getProviders(new Criteria(), true).isEmpty()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void b()
  {
    this.g = false;
    if (this.e) {
      m.Log(5, "Location_StartUpdatingLocation already started!");
    }
    LocationManager localLocationManager;
    List localList;
    for (;;)
    {
      return;
      if (!a())
      {
        a(3);
      }
      else
      {
        localLocationManager = (LocationManager)this.a.getSystemService("location");
        a(1);
        localList = localLocationManager.getProviders(true);
        if (!localList.isEmpty()) {
          break;
        }
        a(3);
      }
    }
    LocationProvider localLocationProvider2;
    if (this.f == 2)
    {
      Iterator localIterator2 = localList.iterator();
      do
      {
        if (!localIterator2.hasNext()) {
          break;
        }
        localLocationProvider2 = localLocationManager.getProvider((String)localIterator2.next());
      } while (localLocationProvider2.getAccuracy() != 2);
    }
    for (LocationProvider localLocationProvider1 = localLocationProvider2;; localLocationProvider1 = null)
    {
      Iterator localIterator1 = localList.iterator();
      while (localIterator1.hasNext())
      {
        String str = (String)localIterator1.next();
        if ((localLocationProvider1 == null) || (localLocationManager.getProvider(str).getAccuracy() != 1))
        {
          a(localLocationManager.getLastKnownLocation(str));
          localLocationManager.requestLocationUpdates(str, 0L, this.d, this, this.a.getMainLooper());
          this.e = true;
        }
      }
      break;
    }
  }
  
  public final void b(float paramFloat)
  {
    if (paramFloat < 100.0F) {
      this.f = 1;
    }
    for (;;)
    {
      return;
      if (paramFloat < 500.0F) {
        this.f = 1;
      } else {
        this.f = 2;
      }
    }
  }
  
  public final void c()
  {
    ((LocationManager)this.a.getSystemService("location")).removeUpdates(this);
    this.e = false;
    this.c = null;
    a(0);
  }
  
  public final void d()
  {
    if ((this.h == 1) || (this.h == 2))
    {
      this.g = true;
      c();
    }
  }
  
  public final void e()
  {
    if (this.g) {
      b();
    }
  }
  
  public final void onLocationChanged(Location paramLocation)
  {
    a(2);
    a(paramLocation);
  }
  
  public final void onProviderDisabled(String paramString)
  {
    this.c = null;
  }
  
  public final void onProviderEnabled(String paramString) {}
  
  public final void onStatusChanged(String paramString, int paramInt, Bundle paramBundle) {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */