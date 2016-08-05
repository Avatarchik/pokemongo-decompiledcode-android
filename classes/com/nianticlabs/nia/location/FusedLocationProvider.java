package com.nianticlabs.nia.location;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.nianticlabs.nia.contextservice.ContextService;
import com.nianticlabs.nia.contextservice.GoogleApiManager;
import com.nianticlabs.nia.contextservice.GoogleApiManager.Listener;
import com.nianticlabs.nia.contextservice.ServiceStatus;

public class FusedLocationProvider
  implements Provider
{
  private static final boolean ENABLE_VERBOSE_LOGS = false;
  private static final String TAG = "FusedLocationProvider";
  private AppState appState = AppState.STOP;
  private LocationCallback fusedListener = new LocationCallback()
  {
    public void onLocationAvailability(LocationAvailability paramAnonymousLocationAvailability)
    {
      if (paramAnonymousLocationAvailability.isLocationAvailable()) {
        FusedLocationProvider.this.updateStatus(ServiceStatus.RUNNING);
      }
      for (;;)
      {
        return;
        FusedLocationProvider.this.updateStatus(ServiceStatus.PERMISSION_DENIED);
      }
    }
    
    public void onLocationResult(LocationResult paramAnonymousLocationResult)
    {
      Location localLocation = paramAnonymousLocationResult.getLastLocation();
      boolean bool = LocationServices.FusedLocationApi.getLocationAvailability(FusedLocationProvider.this.googleApiManager.getClient()).isLocationAvailable();
      if ((localLocation != null) && (bool))
      {
        Provider.ProviderListener localProviderListener = FusedLocationProvider.this.providerListener;
        if (localProviderListener != null) {
          localProviderListener.onProviderLocation(localLocation);
        }
      }
    }
  };
  GoogleApiManager.Listener googleApiListener = new GoogleApiManager.Listener()
  {
    public void onConnected()
    {
      FusedLocationProvider.access$002(FusedLocationProvider.this, FusedLocationProvider.GoogleApiState.STARTED);
      FusedLocationProvider.this.updateStatus(ServiceStatus.INITIALIZED);
      if (FusedLocationProvider.this.appState == FusedLocationProvider.AppState.RESUME) {
        FusedLocationProvider.this.startProvider();
      }
    }
    
    public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
    {
      FusedLocationProvider.access$002(FusedLocationProvider.this, FusedLocationProvider.GoogleApiState.STOPPED);
      if (paramAnonymousConnectionResult != null) {
        switch (paramAnonymousConnectionResult.getErrorCode())
        {
        default: 
          FusedLocationProvider.this.updateStatus(ServiceStatus.FAILED);
        }
      }
      for (;;)
      {
        return;
        FusedLocationProvider.this.updateStatus(ServiceStatus.PERMISSION_DENIED);
        continue;
        FusedLocationProvider.this.updateStatus(ServiceStatus.FAILED);
      }
    }
    
    public void onDisconnected()
    {
      FusedLocationProvider.access$002(FusedLocationProvider.this, FusedLocationProvider.GoogleApiState.STOPPED);
    }
  };
  private final GoogleApiManager googleApiManager;
  private GoogleApiState googleApiState = GoogleApiState.STOPPED;
  private LocationRequest locationRequest = new LocationRequest();
  private Provider.ProviderListener providerListener = null;
  
  public FusedLocationProvider(Context paramContext, int paramInt, float paramFloat)
  {
    this.locationRequest.setInterval(paramInt);
    this.locationRequest.setFastestInterval(paramInt);
    this.locationRequest.setPriority(100);
    this.locationRequest.setSmallestDisplacement(paramFloat);
    this.googleApiManager = new GoogleApiManager(paramContext);
    this.googleApiManager.setListener(this.googleApiListener);
    this.googleApiManager.builder().addApi(LocationServices.API);
    this.googleApiManager.build();
  }
  
  private void startProvider()
  {
    try
    {
      LocationServices.FusedLocationApi.requestLocationUpdates(this.googleApiManager.getClient(), this.locationRequest, this.fusedListener, ContextService.getServiceLooper()).setResultCallback(new ResultCallback()
      {
        public void onResult(Status paramAnonymousStatus)
        {
          if (paramAnonymousStatus.isSuccess()) {
            FusedLocationProvider.this.updateStatus(ServiceStatus.RUNNING);
          }
          for (;;)
          {
            return;
            FusedLocationProvider.this.updateStatus(ServiceStatus.FAILED);
          }
        }
      });
      return;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        updateStatus(ServiceStatus.PERMISSION_DENIED);
      }
    }
  }
  
  private void stopProvider()
  {
    LocationServices.FusedLocationApi.removeLocationUpdates(this.googleApiManager.getClient(), this.fusedListener).setResultCallback(new ResultCallback()
    {
      public void onResult(Status paramAnonymousStatus)
      {
        if (paramAnonymousStatus.isSuccess()) {
          FusedLocationProvider.this.updateStatus(ServiceStatus.STOPPED);
        }
      }
    });
  }
  
  private void updateStatus(ServiceStatus paramServiceStatus)
  {
    Provider.ProviderListener localProviderListener = this.providerListener;
    if (localProviderListener != null) {
      localProviderListener.onProviderStatus(paramServiceStatus);
    }
  }
  
  public void onPause()
  {
    this.appState = AppState.PAUSE;
    if (this.googleApiState == GoogleApiState.STARTED) {
      stopProvider();
    }
    this.googleApiManager.onPause();
  }
  
  public void onResume()
  {
    this.appState = AppState.RESUME;
    if (this.googleApiState == GoogleApiState.STARTED) {
      startProvider();
    }
    this.googleApiManager.onResume();
  }
  
  public void onStart()
  {
    this.appState = AppState.START;
    this.googleApiManager.onStart();
  }
  
  public void onStop()
  {
    this.appState = AppState.STOP;
    this.googleApiManager.onStop();
  }
  
  public void setListener(Provider.ProviderListener paramProviderListener)
  {
    this.providerListener = paramProviderListener;
  }
  
  private static enum GoogleApiState
  {
    static
    {
      GoogleApiState[] arrayOfGoogleApiState = new GoogleApiState[2];
      arrayOfGoogleApiState[0] = STARTED;
      arrayOfGoogleApiState[1] = STOPPED;
      $VALUES = arrayOfGoogleApiState;
    }
    
    private GoogleApiState() {}
  }
  
  private static enum AppState
  {
    static
    {
      PAUSE = new AppState("PAUSE", 2);
      RESUME = new AppState("RESUME", 3);
      AppState[] arrayOfAppState = new AppState[4];
      arrayOfAppState[0] = START;
      arrayOfAppState[1] = STOP;
      arrayOfAppState[2] = PAUSE;
      arrayOfAppState[3] = RESUME;
      $VALUES = arrayOfAppState;
    }
    
    private AppState() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/location/FusedLocationProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */