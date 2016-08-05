package com.nianticlabs.nia.contextservice;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class GoogleApiManager
{
  private static final boolean ENABLE_VERBOSE_LOGS = true;
  private static final String TAG = "GoogleApiManager";
  private AppState appState = AppState.STOP;
  private final GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks()
  {
    public void onConnected(Bundle paramAnonymousBundle)
    {
      ContextService.assertOnServiceThread();
      Log.v("GoogleApiManager", "onConnected");
      GoogleApiManager.this.requestStateStarted();
    }
    
    public void onConnectionSuspended(int paramAnonymousInt)
    {
      ContextService.assertOnServiceThread();
      Log.v("GoogleApiManager", "onConnectionSuspended");
      StringBuilder localStringBuilder;
      if (Log.isLoggable("GoogleApiManager", 3))
      {
        localStringBuilder = new StringBuilder("Connection to Google Play Services suspended. ");
        switch (paramAnonymousInt)
        {
        default: 
          localStringBuilder.append("Unknown (");
          localStringBuilder.append(paramAnonymousInt);
          localStringBuilder.append(")");
        }
      }
      for (;;)
      {
        Log.d("GoogleApiManager", localStringBuilder.toString());
        Log.v("GoogleApiManager", "State " + GoogleApiManager.this.state.name() + " -> STOPPED");
        GoogleApiManager.access$102(GoogleApiManager.this, GoogleApiManager.State.STOPPING);
        GoogleApiManager.this.requestStateStopped();
        return;
        localStringBuilder.append("CAUSE_NETWORK_LOST");
        continue;
        localStringBuilder.append("CAUSE_SERVICE_DISCONNECTED");
      }
    }
  };
  private final GoogleApiClient.OnConnectionFailedListener connectionFailedListener = new GoogleApiClient.OnConnectionFailedListener()
  {
    public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
    {
      ContextService.assertOnServiceThread();
      GoogleApiManager.this.requestStateStartFailed(paramAnonymousConnectionResult);
    }
  };
  private final GoogleApiClient.Builder googleApiBuilder;
  private GoogleApiClient googleApiClient = null;
  private Listener listener = null;
  private State state = State.STOPPED;
  
  public GoogleApiManager(Context paramContext)
  {
    this.googleApiBuilder = new GoogleApiClient.Builder(paramContext).addConnectionCallbacks(this.connectionCallbacks).addOnConnectionFailedListener(this.connectionFailedListener).setHandler(ContextService.getServiceHandler());
  }
  
  private void requestStateStartFailed(ConnectionResult paramConnectionResult)
  {
    Log.v("GoogleApiManager", "requestStateStartFailed " + this.appState.name() + " " + this.state.name());
    if (this.state == State.STARTING)
    {
      Log.v("GoogleApiManager", "State " + this.state.name() + " -> START_FAILED");
      this.state = State.START_FAILED;
      if (this.listener != null) {
        this.listener.onConnectionFailed(paramConnectionResult);
      }
    }
  }
  
  private void requestStateStarted()
  {
    Log.v("GoogleApiManager", "requestStateStarted " + this.appState.name() + " " + this.state.name());
    if (this.state == State.STARTING)
    {
      Log.v("GoogleApiManager", "State " + this.state.name() + " -> STARTED");
      this.state = State.STARTED;
      if (this.listener != null) {
        this.listener.onConnected();
      }
      switch (3.$SwitchMap$com$nianticlabs$nia$contextservice$GoogleApiManager$AppState[this.appState.ordinal()])
      {
      }
    }
    for (;;)
    {
      return;
      requestStateStopping();
    }
  }
  
  private void requestStateStarting()
  {
    Log.v("GoogleApiManager", "requestStateStarting " + this.appState.name() + " " + this.state.name());
    if (this.appState != AppState.STOP) {
      switch (3.$SwitchMap$com$nianticlabs$nia$contextservice$GoogleApiManager$State[this.state.ordinal()])
      {
      }
    }
    for (;;)
    {
      return;
      Log.v("GoogleApiManager", "State " + this.state.name() + " -> STARTING");
      this.state = State.STARTING;
      this.googleApiClient.connect();
    }
  }
  
  private void requestStateStopped()
  {
    Log.v("GoogleApiManager", "requestStateStopped " + this.appState.name() + " " + this.state.name());
    if (this.state == State.STOPPING)
    {
      Log.v("GoogleApiManager", "State " + this.state.name() + " -> STOPPED");
      this.state = State.STOPPED;
      if (this.listener != null) {
        this.listener.onDisconnected();
      }
    }
  }
  
  private void requestStateStopping()
  {
    Log.v("GoogleApiManager", "requestStateStopping " + this.appState.name() + " " + this.state.name());
    switch (3.$SwitchMap$com$nianticlabs$nia$contextservice$GoogleApiManager$State[this.state.ordinal()])
    {
    }
    for (;;)
    {
      return;
      Log.v("GoogleApiManager", "State " + this.state.name() + " -> STOPPING");
      this.state = State.STOPPING;
      this.googleApiClient.disconnect();
      requestStateStopped();
    }
  }
  
  public void build()
  {
    if (this.googleApiClient != null) {
      throw new IllegalStateException("Calling build() after already built");
    }
    this.googleApiClient = this.googleApiBuilder.build();
  }
  
  public GoogleApiClient.Builder builder()
  {
    if (this.googleApiClient != null) {
      throw new IllegalStateException("Calling builder() after already built");
    }
    return this.googleApiBuilder;
  }
  
  public GoogleApiClient getClient()
  {
    return this.googleApiClient;
  }
  
  public void onPause()
  {
    Log.v("GoogleApiManager", "onPause " + this.appState.name() + " " + this.state.name());
    ContextService.assertOnServiceThread();
    this.appState = AppState.PAUSE;
  }
  
  public void onResume()
  {
    Log.v("GoogleApiManager", "onResume " + this.appState.name() + " " + this.state.name());
    ContextService.assertOnServiceThread();
    this.appState = AppState.RESUME;
  }
  
  public void onStart()
  {
    Log.v("GoogleApiManager", "onStart " + this.appState.name() + " " + this.state.name());
    ContextService.assertOnServiceThread();
    this.appState = AppState.START;
    requestStateStarting();
  }
  
  public void onStop()
  {
    Log.v("GoogleApiManager", "onStop " + this.appState.name() + " " + this.state.name());
    ContextService.assertOnServiceThread();
    this.appState = AppState.STOP;
    requestStateStopping();
  }
  
  public void setListener(Listener paramListener)
  {
    this.listener = paramListener;
  }
  
  private static enum State
  {
    static
    {
      STARTED = new State("STARTED", 2);
      STOPPING = new State("STOPPING", 3);
      STOP_FAILED = new State("STOP_FAILED", 4);
      STOPPED = new State("STOPPED", 5);
      State[] arrayOfState = new State[6];
      arrayOfState[0] = STARTING;
      arrayOfState[1] = START_FAILED;
      arrayOfState[2] = STARTED;
      arrayOfState[3] = STOPPING;
      arrayOfState[4] = STOP_FAILED;
      arrayOfState[5] = STOPPED;
      $VALUES = arrayOfState;
    }
    
    private State() {}
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
  
  public static abstract interface Listener
  {
    public abstract void onConnected();
    
    public abstract void onConnectionFailed(ConnectionResult paramConnectionResult);
    
    public abstract void onDisconnected();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/contextservice/GoogleApiManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */