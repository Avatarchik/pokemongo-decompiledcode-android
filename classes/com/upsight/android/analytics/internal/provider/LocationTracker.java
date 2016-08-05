package com.upsight.android.analytics.internal.provider;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.provider.UpsightLocationTracker;
import com.upsight.android.analytics.provider.UpsightLocationTracker.Data;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightDataStoreListener;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
final class LocationTracker
  extends UpsightLocationTracker
{
  private static final String LOG_TAG = LocationTracker.class.getSimpleName();
  private UpsightDataStore mDataStore;
  private UpsightLogger mLogger;
  
  @Inject
  LocationTracker(UpsightContext paramUpsightContext)
  {
    this.mDataStore = paramUpsightContext.getDataStore();
    this.mLogger = paramUpsightContext.getLogger();
  }
  
  public void purge()
  {
    this.mDataStore.fetch(UpsightLocationTracker.Data.class, new UpsightDataStoreListener()
    {
      public void onFailure(UpsightException paramAnonymousUpsightException)
      {
        UpsightLogger localUpsightLogger = LocationTracker.this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramAnonymousUpsightException;
        localUpsightLogger.e("Upsight", "Failed to remove stale location data.", arrayOfObject);
      }
      
      public void onSuccess(Set<UpsightLocationTracker.Data> paramAnonymousSet)
      {
        Iterator localIterator = paramAnonymousSet.iterator();
        while (localIterator.hasNext())
        {
          UpsightLocationTracker.Data localData = (UpsightLocationTracker.Data)localIterator.next();
          LocationTracker.this.mDataStore.remove(localData);
        }
      }
    });
  }
  
  public void track(final UpsightLocationTracker.Data paramData)
  {
    this.mDataStore.fetch(UpsightLocationTracker.Data.class, new UpsightDataStoreListener()
    {
      public void onFailure(UpsightException paramAnonymousUpsightException)
      {
        LocationTracker.this.mLogger.e(LocationTracker.LOG_TAG, paramAnonymousUpsightException, "Failed to fetch location data.", new Object[0]);
      }
      
      public void onSuccess(Set<UpsightLocationTracker.Data> paramAnonymousSet)
      {
        UpsightLocationTracker.Data localData = null;
        Iterator localIterator = paramAnonymousSet.iterator();
        if (localIterator.hasNext())
        {
          localData = (UpsightLocationTracker.Data)localIterator.next();
          localData.setLatitude(paramData.getLatitude());
          localData.setLongitude(paramData.getLongitude());
          localData.setTimeZone(paramData.getTimeZone());
        }
        if (localData == null) {
          localData = paramData;
        }
        LocationTracker.this.mDataStore.store(localData);
      }
    });
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/LocationTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */