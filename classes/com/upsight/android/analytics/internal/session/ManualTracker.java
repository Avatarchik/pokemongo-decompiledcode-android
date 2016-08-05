package com.upsight.android.analytics.internal.session;

import android.app.Activity;
import android.content.Intent;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.UpsightLifeCycleTracker;
import com.upsight.android.analytics.UpsightLifeCycleTracker.ActivityState;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightDataStoreListener;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ManualTracker
  implements UpsightLifeCycleTracker
{
  private Set<WeakReference<Activity>> mActivitySet;
  private UpsightDataStore mDataStore;
  private SessionManager mSessionManager;
  
  @Inject
  public ManualTracker(SessionManager paramSessionManager, UpsightContext paramUpsightContext)
  {
    this.mSessionManager = paramSessionManager;
    this.mDataStore = paramUpsightContext.getDataStore();
    this.mActivitySet = new HashSet();
  }
  
  private static boolean isPurgeable(Activity paramActivity)
  {
    if (paramActivity == null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static void removeAndPurge(Set<WeakReference<Activity>> paramSet, Activity paramActivity)
  {
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      Activity localActivity = (Activity)((WeakReference)localIterator.next()).get();
      if ((localActivity == paramActivity) || (isPurgeable(localActivity))) {
        localIterator.remove();
      }
    }
  }
  
  public void track(Activity paramActivity, UpsightLifeCycleTracker.ActivityState paramActivityState, SessionInitializer paramSessionInitializer)
  {
    switch (paramActivityState)
    {
    }
    for (;;)
    {
      return;
      if (this.mActivitySet.isEmpty())
      {
        this.mDataStore.fetch(ApplicationStatus.class, new UpsightDataStoreListener()
        {
          public void onFailure(UpsightException paramAnonymousUpsightException) {}
          
          public void onSuccess(Set<ApplicationStatus> paramAnonymousSet)
          {
            if (paramAnonymousSet.isEmpty()) {
              ManualTracker.this.mDataStore.store(new ApplicationStatus(ApplicationStatus.State.FOREGROUND));
            }
            for (;;)
            {
              return;
              Iterator localIterator = paramAnonymousSet.iterator();
              int i = 0;
              while (localIterator.hasNext())
              {
                ApplicationStatus localApplicationStatus = (ApplicationStatus)localIterator.next();
                if (i == 0)
                {
                  localApplicationStatus.state = ApplicationStatus.State.FOREGROUND;
                  ManualTracker.this.mDataStore.store(localApplicationStatus);
                  i = 1;
                }
                else
                {
                  ManualTracker.this.mDataStore.remove(localApplicationStatus);
                }
              }
            }
          }
        });
        Intent localIntent = paramActivity.getIntent();
        if ((localIntent == null) || (!localIntent.hasExtra("pushMessage"))) {
          this.mSessionManager.startSession(paramSessionInitializer);
        }
      }
      this.mActivitySet.add(new WeakReference(paramActivity));
      continue;
      removeAndPurge(this.mActivitySet, paramActivity);
      if ((!paramActivity.isChangingConfigurations()) && (this.mActivitySet.isEmpty()))
      {
        this.mDataStore.fetch(ApplicationStatus.class, new UpsightDataStoreListener()
        {
          public void onFailure(UpsightException paramAnonymousUpsightException) {}
          
          public void onSuccess(Set<ApplicationStatus> paramAnonymousSet)
          {
            if (paramAnonymousSet.isEmpty()) {
              ManualTracker.this.mDataStore.store(new ApplicationStatus(ApplicationStatus.State.BACKGROUND));
            }
            for (;;)
            {
              return;
              Iterator localIterator = paramAnonymousSet.iterator();
              int i = 0;
              while (localIterator.hasNext())
              {
                ApplicationStatus localApplicationStatus = (ApplicationStatus)localIterator.next();
                if (i == 0)
                {
                  localApplicationStatus.state = ApplicationStatus.State.BACKGROUND;
                  ManualTracker.this.mDataStore.store(localApplicationStatus);
                  i = 1;
                }
                else
                {
                  ManualTracker.this.mDataStore.remove(localApplicationStatus);
                  localIterator.remove();
                }
              }
            }
          }
        });
        this.mSessionManager.stopSession();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/ManualTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */