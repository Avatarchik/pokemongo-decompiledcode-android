package com.upsight.android.analytics.internal.dispatcher.schema;

import android.location.Location;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import com.upsight.android.analytics.provider.UpsightLocationTracker.Data;
import com.upsight.android.persistence.UpsightDataStore;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import rx.Observable;
import rx.observables.BlockingObservable;

public class LocationBlockProvider
  extends UpsightDataProvider
{
  public static final String LATITUDE_KEY = "location.lat";
  public static final String LONGITUDE_KEY = "location.lon";
  public static final String TIME_ZONE_KEY = "location.tz";
  private UpsightContext mUpsight;
  
  LocationBlockProvider(UpsightContext paramUpsightContext)
  {
    this.mUpsight = paramUpsightContext;
  }
  
  private UpsightLocationTracker.Data fetchLatestLocation()
  {
    return (UpsightLocationTracker.Data)this.mUpsight.getDataStore().fetchObservable(UpsightLocationTracker.Data.class).lastOrDefault(null).toBlocking().first();
  }
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "location.tz";
    arrayOfString[1] = "location.lat";
    arrayOfString[2] = "location.lon";
    return new HashSet(Arrays.asList(arrayOfString));
  }
  
  /**
   * @deprecated
   */
  public Object get(String paramString)
  {
    int i = 0;
    for (;;)
    {
      UpsightLocationTracker.Data localData;
      try
      {
        localData = fetchLatestLocation();
        if (localData == null)
        {
          localObject2 = null;
          return localObject2;
        }
        switch (paramString.hashCode())
        {
        case 552272735: 
          localObject2 = super.get(paramString);
          continue;
          if (!paramString.equals("location.tz")) {
            break label157;
          }
        }
      }
      finally {}
      if (!paramString.equals("location.lat")) {
        break;
      }
      i = 1;
      break label160;
      if (!paramString.equals("location.lon")) {
        break;
      }
      i = 2;
      break label160;
      Object localObject2 = localData.getTimeZone();
      continue;
      localObject2 = Location.convert(localData.getLatitude(), 0);
      continue;
      String str = Location.convert(localData.getLongitude(), 0);
      localObject2 = str;
    }
    label157:
    i = -1;
    label160:
    switch (i)
    {
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/LocationBlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */