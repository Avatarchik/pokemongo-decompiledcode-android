package com.upsight.android.analytics.internal;

import com.upsight.android.analytics.UpsightAnalyticsApi;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public final class AnalyticsApiModule
{
  @Provides
  @Singleton
  public UpsightAnalyticsApi provideUpsightAnalyticsApi(Analytics paramAnalytics)
  {
    return paramAnalytics;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/AnalyticsApiModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */