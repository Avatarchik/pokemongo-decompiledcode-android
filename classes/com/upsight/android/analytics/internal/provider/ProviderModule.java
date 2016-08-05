package com.upsight.android.analytics.internal.provider;

import com.upsight.android.analytics.provider.UpsightLocationTracker;
import com.upsight.android.analytics.provider.UpsightOptOutStatus;
import com.upsight.android.analytics.provider.UpsightUserAttributes;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public final class ProviderModule
{
  @Provides
  @Singleton
  public UpsightOptOutStatus providesOptOutStatus(OptOutStatus paramOptOutStatus)
  {
    return paramOptOutStatus;
  }
  
  @Provides
  @Singleton
  public UpsightLocationTracker providesUpsightLocationTracker(LocationTracker paramLocationTracker)
  {
    return paramLocationTracker;
  }
  
  @Provides
  @Singleton
  public UpsightUserAttributes providesUpsightUserAttributes(UserAttributes paramUserAttributes)
  {
    return paramUserAttributes;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/ProviderModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */