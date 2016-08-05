package com.nianticlabs.nia.location;

import android.location.GpsSatellite;

public abstract interface GpsProvider
  extends Provider
{
  public static abstract interface GpsProviderListener
    extends Provider.ProviderListener
  {
    public abstract void onGpsStatusUpdate(int paramInt, GpsSatellite[] paramArrayOfGpsSatellite);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/location/GpsProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */