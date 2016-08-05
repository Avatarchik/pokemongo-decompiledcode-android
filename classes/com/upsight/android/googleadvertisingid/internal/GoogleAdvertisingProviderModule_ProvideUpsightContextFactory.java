package com.upsight.android.googleadvertisingid.internal;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;

public final class GoogleAdvertisingProviderModule_ProvideUpsightContextFactory
  implements Factory<UpsightContext>
{
  private final GoogleAdvertisingProviderModule module;
  
  static
  {
    if (!GoogleAdvertisingProviderModule_ProvideUpsightContextFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public GoogleAdvertisingProviderModule_ProvideUpsightContextFactory(GoogleAdvertisingProviderModule paramGoogleAdvertisingProviderModule)
  {
    assert (paramGoogleAdvertisingProviderModule != null);
    this.module = paramGoogleAdvertisingProviderModule;
  }
  
  public static Factory<UpsightContext> create(GoogleAdvertisingProviderModule paramGoogleAdvertisingProviderModule)
  {
    return new GoogleAdvertisingProviderModule_ProvideUpsightContextFactory(paramGoogleAdvertisingProviderModule);
  }
  
  public UpsightContext get()
  {
    UpsightContext localUpsightContext = this.module.provideUpsightContext();
    if (localUpsightContext == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightContext;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googleadvertisingid/internal/GoogleAdvertisingProviderModule_ProvideUpsightContextFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */