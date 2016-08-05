package com.upsight.android.analytics.internal;

import com.upsight.android.analytics.UpsightAnalyticsComponent;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules={AnalyticsModule.class})
@Singleton
public abstract interface AnalyticsComponent
  extends UpsightAnalyticsComponent
{}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/AnalyticsComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */