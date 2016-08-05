package com.upsight.android.googlepushservices.internal;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class GooglePushServices_Factory
  implements Factory<GooglePushServices>
{
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!GooglePushServices_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public GooglePushServices_Factory(Provider<UpsightContext> paramProvider)
  {
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<GooglePushServices> create(Provider<UpsightContext> paramProvider)
  {
    return new GooglePushServices_Factory(paramProvider);
  }
  
  public GooglePushServices get()
  {
    return new GooglePushServices((UpsightContext)this.upsightProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/GooglePushServices_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */