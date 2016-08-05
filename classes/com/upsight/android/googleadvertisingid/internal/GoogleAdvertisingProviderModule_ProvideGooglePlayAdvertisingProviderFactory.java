package com.upsight.android.googleadvertisingid.internal;

import dagger.internal.Factory;

public final class GoogleAdvertisingProviderModule_ProvideGooglePlayAdvertisingProviderFactory
  implements Factory<GooglePlayAdvertisingProvider>
{
  private final GoogleAdvertisingProviderModule module;
  
  static
  {
    if (!GoogleAdvertisingProviderModule_ProvideGooglePlayAdvertisingProviderFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public GoogleAdvertisingProviderModule_ProvideGooglePlayAdvertisingProviderFactory(GoogleAdvertisingProviderModule paramGoogleAdvertisingProviderModule)
  {
    assert (paramGoogleAdvertisingProviderModule != null);
    this.module = paramGoogleAdvertisingProviderModule;
  }
  
  public static Factory<GooglePlayAdvertisingProvider> create(GoogleAdvertisingProviderModule paramGoogleAdvertisingProviderModule)
  {
    return new GoogleAdvertisingProviderModule_ProvideGooglePlayAdvertisingProviderFactory(paramGoogleAdvertisingProviderModule);
  }
  
  public GooglePlayAdvertisingProvider get()
  {
    GooglePlayAdvertisingProvider localGooglePlayAdvertisingProvider = this.module.provideGooglePlayAdvertisingProvider();
    if (localGooglePlayAdvertisingProvider == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localGooglePlayAdvertisingProvider;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googleadvertisingid/internal/GoogleAdvertisingProviderModule_ProvideGooglePlayAdvertisingProviderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */