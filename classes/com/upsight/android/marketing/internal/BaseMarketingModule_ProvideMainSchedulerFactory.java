package com.upsight.android.marketing.internal;

import dagger.internal.Factory;
import rx.Scheduler;

public final class BaseMarketingModule_ProvideMainSchedulerFactory
  implements Factory<Scheduler>
{
  private final BaseMarketingModule module;
  
  static
  {
    if (!BaseMarketingModule_ProvideMainSchedulerFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public BaseMarketingModule_ProvideMainSchedulerFactory(BaseMarketingModule paramBaseMarketingModule)
  {
    assert (paramBaseMarketingModule != null);
    this.module = paramBaseMarketingModule;
  }
  
  public static Factory<Scheduler> create(BaseMarketingModule paramBaseMarketingModule)
  {
    return new BaseMarketingModule_ProvideMainSchedulerFactory(paramBaseMarketingModule);
  }
  
  public Scheduler get()
  {
    Scheduler localScheduler = this.module.provideMainScheduler();
    if (localScheduler == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localScheduler;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/BaseMarketingModule_ProvideMainSchedulerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */