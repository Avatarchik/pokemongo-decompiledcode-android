package com.upsight.android.internal.persistence.storable;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StorableModule_ProvideStorableInfoCacheFactory
  implements Factory<StorableInfoCache>
{
  private final StorableModule module;
  private final Provider<ObjectMapper> objectMapperProvider;
  
  static
  {
    if (!StorableModule_ProvideStorableInfoCacheFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public StorableModule_ProvideStorableInfoCacheFactory(StorableModule paramStorableModule, Provider<ObjectMapper> paramProvider)
  {
    assert (paramStorableModule != null);
    this.module = paramStorableModule;
    assert (paramProvider != null);
    this.objectMapperProvider = paramProvider;
  }
  
  public static Factory<StorableInfoCache> create(StorableModule paramStorableModule, Provider<ObjectMapper> paramProvider)
  {
    return new StorableModule_ProvideStorableInfoCacheFactory(paramStorableModule, paramProvider);
  }
  
  public StorableInfoCache get()
  {
    StorableInfoCache localStorableInfoCache = this.module.provideStorableInfoCache((ObjectMapper)this.objectMapperProvider.get());
    if (localStorableInfoCache == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localStorableInfoCache;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/StorableModule_ProvideStorableInfoCacheFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */