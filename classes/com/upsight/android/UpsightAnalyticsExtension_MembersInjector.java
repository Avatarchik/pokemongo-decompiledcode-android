package com.upsight.android;

import android.app.Application.ActivityLifecycleCallbacks;
import com.upsight.android.analytics.UpsightAnalyticsApi;
import com.upsight.android.analytics.UpsightAnalyticsComponent;
import com.upsight.android.analytics.internal.association.AssociationManager;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.internal.util.Opt;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UpsightAnalyticsExtension_MembersInjector
  implements MembersInjector<UpsightAnalyticsExtension>
{
  private final Provider<UpsightAnalyticsApi> mAnalyticsProvider;
  private final Provider<AssociationManager> mAssociationManagerProvider;
  private final Provider<Clock> mClockProvider;
  private final Provider<Opt<Thread.UncaughtExceptionHandler>> mUncaughtExceptionHandlerProvider;
  private final Provider<Application.ActivityLifecycleCallbacks> mUpsightLifeCycleCallbacksProvider;
  private final MembersInjector<UpsightExtension<UpsightAnalyticsComponent, UpsightAnalyticsApi>> supertypeInjector;
  
  static
  {
    if (!UpsightAnalyticsExtension_MembersInjector.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UpsightAnalyticsExtension_MembersInjector(MembersInjector<UpsightExtension<UpsightAnalyticsComponent, UpsightAnalyticsApi>> paramMembersInjector, Provider<Opt<Thread.UncaughtExceptionHandler>> paramProvider, Provider<UpsightAnalyticsApi> paramProvider1, Provider<Clock> paramProvider2, Provider<Application.ActivityLifecycleCallbacks> paramProvider3, Provider<AssociationManager> paramProvider4)
  {
    assert (paramMembersInjector != null);
    this.supertypeInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.mUncaughtExceptionHandlerProvider = paramProvider;
    assert (paramProvider1 != null);
    this.mAnalyticsProvider = paramProvider1;
    assert (paramProvider2 != null);
    this.mClockProvider = paramProvider2;
    assert (paramProvider3 != null);
    this.mUpsightLifeCycleCallbacksProvider = paramProvider3;
    assert (paramProvider4 != null);
    this.mAssociationManagerProvider = paramProvider4;
  }
  
  public static MembersInjector<UpsightAnalyticsExtension> create(MembersInjector<UpsightExtension<UpsightAnalyticsComponent, UpsightAnalyticsApi>> paramMembersInjector, Provider<Opt<Thread.UncaughtExceptionHandler>> paramProvider, Provider<UpsightAnalyticsApi> paramProvider1, Provider<Clock> paramProvider2, Provider<Application.ActivityLifecycleCallbacks> paramProvider3, Provider<AssociationManager> paramProvider4)
  {
    return new UpsightAnalyticsExtension_MembersInjector(paramMembersInjector, paramProvider, paramProvider1, paramProvider2, paramProvider3, paramProvider4);
  }
  
  public void injectMembers(UpsightAnalyticsExtension paramUpsightAnalyticsExtension)
  {
    if (paramUpsightAnalyticsExtension == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    this.supertypeInjector.injectMembers(paramUpsightAnalyticsExtension);
    paramUpsightAnalyticsExtension.mUncaughtExceptionHandler = ((Opt)this.mUncaughtExceptionHandlerProvider.get());
    paramUpsightAnalyticsExtension.mAnalytics = ((UpsightAnalyticsApi)this.mAnalyticsProvider.get());
    paramUpsightAnalyticsExtension.mClock = ((Clock)this.mClockProvider.get());
    paramUpsightAnalyticsExtension.mUpsightLifeCycleCallbacks = ((Application.ActivityLifecycleCallbacks)this.mUpsightLifeCycleCallbacksProvider.get());
    paramUpsightAnalyticsExtension.mAssociationManager = ((AssociationManager)this.mAssociationManagerProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightAnalyticsExtension_MembersInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */