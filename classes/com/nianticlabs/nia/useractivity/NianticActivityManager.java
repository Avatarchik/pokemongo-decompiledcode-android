package com.nianticlabs.nia.useractivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionApi;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.nianticlabs.nia.contextservice.ContextService;
import com.nianticlabs.nia.contextservice.GoogleApiManager;
import com.nianticlabs.nia.contextservice.GoogleApiManager.Listener;
import com.nianticlabs.nia.contextservice.ServiceStatus;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NianticActivityManager
  extends ContextService
{
  private static final long ACTIVITY_DETECTION_INTERVAL_MS = 5000L;
  private static final String TAG = "NianticActivityManager";
  private static WeakReference<NianticActivityManager> instance = null;
  private static Object instanceLock = new Object();
  private final PendingIntent activityRecognitionIntent;
  private AppState appState = AppState.STOP;
  GoogleApiManager.Listener googleApiListener = new GoogleApiManager.Listener()
  {
    public void onConnected()
    {
      if (NianticActivityManager.this.appState == NianticActivityManager.AppState.RESUME) {
        NianticActivityManager.this.requestActivityUpdates();
      }
    }
    
    public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
    {
      NianticActivityManager.access$202(NianticActivityManager.this, NianticActivityManager.GoogleApiState.STOPPED);
      switch (paramAnonymousConnectionResult.getErrorCode())
      {
      default: 
        NianticActivityManager.access$302(NianticActivityManager.this, ServiceStatus.FAILED);
      }
      for (;;)
      {
        if (NianticActivityManager.this.status != ServiceStatus.INITIALIZED) {
          Log.e("NianticActivityManager", "Connection to activity recognition failed: " + paramAnonymousConnectionResult.getErrorMessage());
        }
        Log.d("NianticActivityManager", "Connection failed, updating status.");
        NianticActivityManager.this.safeUpdateActivity(null, NianticActivityManager.this.status.ordinal());
        return;
        NianticActivityManager.access$302(NianticActivityManager.this, ServiceStatus.INITIALIZED);
        continue;
        NianticActivityManager.access$302(NianticActivityManager.this, ServiceStatus.PERMISSION_DENIED);
      }
    }
    
    public void onDisconnected()
    {
      NianticActivityManager.access$202(NianticActivityManager.this, NianticActivityManager.GoogleApiState.STOPPED);
    }
  };
  private final GoogleApiManager googleApiManager;
  private GoogleApiState googleApiState = GoogleApiState.STOPPED;
  private ServiceStatus status;
  
  public NianticActivityManager(Context paramContext, long paramLong)
  {
    super(paramContext, paramLong);
    this.googleApiManager = new GoogleApiManager(paramContext);
    this.googleApiManager.setListener(this.googleApiListener);
    this.googleApiManager.builder().addApi(ActivityRecognition.API);
    this.googleApiManager.build();
    this.activityRecognitionIntent = createPendingIntent(paramContext);
    synchronized (instanceLock)
    {
      instance = new WeakReference(this);
      return;
    }
  }
  
  public static PendingIntent createPendingIntent(Context paramContext)
  {
    return PendingIntent.getService(paramContext, 0, new Intent(paramContext, ActivityRecognitionService.class), 134217728);
  }
  
  public static NianticActivityManager getInstance()
  {
    NianticActivityManager localNianticActivityManager;
    synchronized (instanceLock)
    {
      if (instance != null) {
        localNianticActivityManager = (NianticActivityManager)instance.get();
      } else {
        localNianticActivityManager = null;
      }
    }
    return localNianticActivityManager;
  }
  
  public static Object getInstanceLock()
  {
    return instanceLock;
  }
  
  private native void nativeUpdateActivity(@Nullable long[] paramArrayOfLong, int paramInt);
  
  private void requestActivityUpdates()
  {
    try
    {
      Status localStatus = (Status)ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(this.googleApiManager.getClient(), 5000L, this.activityRecognitionIntent).await(0L, TimeUnit.MILLISECONDS);
      if (localStatus.isSuccess()) {
        this.status = ServiceStatus.RUNNING;
      }
      for (;;)
      {
        safeUpdateActivity(null, this.status.ordinal());
        return;
        this.status = ServiceStatus.FAILED;
        Log.d("NianticActivityManager", "Request updates failed " + localStatus.getStatusMessage());
        Log.d("NianticActivityManager", "Request status has resolution " + localStatus.hasResolution());
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e("NianticActivityManager", "requestActivityUpdates throws" + localException);
        localException.printStackTrace();
        this.status = ServiceStatus.FAILED;
      }
    }
  }
  
  private void safeUpdateActivity(@Nullable long[] paramArrayOfLong, int paramInt)
  {
    synchronized (this.callbackLock)
    {
      nativeUpdateActivity(paramArrayOfLong, paramInt);
      return;
    }
  }
  
  private void stopActivityUpdates()
  {
    ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(this.googleApiManager.getClient(), this.activityRecognitionIntent);
    this.status = ServiceStatus.STOPPED;
    safeUpdateActivity(null, this.status.ordinal());
  }
  
  public void onPause()
  {
    this.appState = AppState.PAUSE;
    if (this.googleApiState == GoogleApiState.STARTED) {
      stopActivityUpdates();
    }
    this.googleApiManager.onPause();
  }
  
  public void onResume()
  {
    this.appState = AppState.RESUME;
    if (this.googleApiState == GoogleApiState.STARTED) {
      requestActivityUpdates();
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
  
  public void receiveUpdateActivity(final ActivityRecognitionResult paramActivityRecognitionResult)
  {
    ContextService.runOnServiceHandler(new Runnable()
    {
      public void run()
      {
        if (NianticActivityManager.this.appState == NianticActivityManager.AppState.RESUME)
        {
          NianticActivityManager.access$302(NianticActivityManager.this, ServiceStatus.RUNNING);
          long[] arrayOfLong = new long[1 + 2 * paramActivityRecognitionResult.getProbableActivities().size()];
          arrayOfLong[0] = paramActivityRecognitionResult.getTime();
          int i = 1;
          Iterator localIterator = paramActivityRecognitionResult.getProbableActivities().iterator();
          while (localIterator.hasNext())
          {
            DetectedActivity localDetectedActivity = (DetectedActivity)localIterator.next();
            int j = i + 1;
            arrayOfLong[i] = localDetectedActivity.getType();
            i = j + 1;
            arrayOfLong[j] = localDetectedActivity.getConfidence();
          }
          NianticActivityManager.this.safeUpdateActivity(arrayOfLong, NianticActivityManager.this.status.ordinal());
        }
      }
    });
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/useractivity/NianticActivityManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */