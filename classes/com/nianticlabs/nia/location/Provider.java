package com.nianticlabs.nia.location;

import android.location.Location;
import com.nianticlabs.nia.contextservice.ServiceStatus;

public abstract interface Provider
{
  public abstract void onPause();
  
  public abstract void onResume();
  
  public abstract void onStart();
  
  public abstract void onStop();
  
  public abstract void setListener(ProviderListener paramProviderListener);
  
  public static abstract interface ProviderListener
  {
    public abstract void onProviderLocation(Location paramLocation);
    
    public abstract void onProviderStatus(ServiceStatus paramServiceStatus);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/location/Provider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */