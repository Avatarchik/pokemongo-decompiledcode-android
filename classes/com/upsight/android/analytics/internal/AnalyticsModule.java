package com.upsight.android.analytics.internal;

import com.upsight.android.analytics.internal.association.AssociationModule;
import com.upsight.android.analytics.internal.configuration.ConfigurationModule;
import com.upsight.android.analytics.internal.dispatcher.DispatchModule;
import com.upsight.android.analytics.internal.dispatcher.delivery.DeliveryModule;
import com.upsight.android.analytics.internal.dispatcher.routing.RoutingModule;
import com.upsight.android.analytics.internal.dispatcher.schema.SchemaModule;
import com.upsight.android.analytics.internal.provider.ProviderModule;
import com.upsight.android.analytics.internal.session.LifecycleTrackerModule;
import com.upsight.android.analytics.internal.session.SessionModule;
import dagger.Module;

@Module(includes={AnalyticsApiModule.class, AnalyticsSchedulersModule.class, ConfigObjectMapperModule.class, DispatchModule.class, DeliveryModule.class, RoutingModule.class, SchemaModule.class, ConfigurationModule.class, SessionModule.class, LifecycleTrackerModule.class, ProviderModule.class, AssociationModule.class, BaseAnalyticsModule.class})
public final class AnalyticsModule {}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/AnalyticsModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */