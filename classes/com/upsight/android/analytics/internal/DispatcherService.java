package com.upsight.android.analytics.internal;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import com.upsight.android.Upsight;
import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.UpsightAnalyticsComponent;
import com.upsight.android.analytics.internal.configuration.ConfigurationManager;
import com.upsight.android.analytics.internal.dispatcher.Dispatcher;
import com.upsight.android.analytics.internal.session.ApplicationStatus;
import com.upsight.android.analytics.internal.session.ApplicationStatus.State;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightSubscription;
import com.upsight.android.persistence.annotation.Created;
import com.upsight.android.persistence.annotation.Updated;
import javax.inject.Inject;

public class DispatcherService
  extends Service
{
  private static final long STATUS_CHECK_INTERVAL = 25000L;
  private static final int STOP_AFTER_DEAD_INTERVALS = 4;
  @Inject
  ConfigurationManager mConfigurationManager;
  private UpsightSubscription mDataStoreSubscription;
  private int mDeadIntervalsInARow;
  @Inject
  Dispatcher mDispatcher;
  private Handler mHandler;
  private Runnable mSelfStopTask = new Runnable()
  {
    public void run()
    {
      if (DispatcherService.this.mDispatcher.hasPendingRecords())
      {
        DispatcherService.access$002(DispatcherService.this, 0);
        DispatcherService.this.mHandler.postDelayed(DispatcherService.this.mSelfStopTask, 25000L);
      }
      for (;;)
      {
        return;
        if (DispatcherService.this.mDeadIntervalsInARow == 4)
        {
          DispatcherService.this.stopSelf();
        }
        else
        {
          DispatcherService.access$008(DispatcherService.this);
          DispatcherService.this.mHandler.postDelayed(DispatcherService.this.mSelfStopTask, 25000L);
        }
      }
    }
  };
  
  @Created
  @Updated
  public void onApplicationStatus(ApplicationStatus paramApplicationStatus)
  {
    if (paramApplicationStatus.getState() == ApplicationStatus.State.BACKGROUND)
    {
      this.mDeadIntervalsInARow = 0;
      this.mHandler.postDelayed(this.mSelfStopTask, 25000L);
    }
    for (;;)
    {
      return;
      this.mHandler.removeCallbacks(this.mSelfStopTask);
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    UpsightContext localUpsightContext = Upsight.createContext(this);
    ((UpsightAnalyticsComponent)((UpsightAnalyticsExtension)localUpsightContext.getUpsightExtension("com.upsight.extension.analytics")).getComponent()).inject(this);
    this.mHandler = new Handler();
    this.mDataStoreSubscription = localUpsightContext.getDataStore().subscribe(this);
    this.mDispatcher.launch();
    this.mConfigurationManager.launch();
  }
  
  public void onDestroy()
  {
    this.mHandler.removeCallbacks(this.mSelfStopTask);
    this.mDataStoreSubscription.unsubscribe();
    this.mConfigurationManager.terminate();
    this.mDispatcher.terminate();
    super.onDestroy();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/DispatcherService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */