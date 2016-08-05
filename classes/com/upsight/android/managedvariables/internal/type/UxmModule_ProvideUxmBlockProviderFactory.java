package com.upsight.android.managedvariables.internal.type;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UxmModule_ProvideUxmBlockProviderFactory
  implements Factory<UxmBlockProvider>
{
  private final UxmModule module;
  private final Provider<UpsightContext> upsightProvider;
  private final Provider<UxmSchema> uxmSchemaProvider;
  private final Provider<String> uxmSchemaRawStringProvider;
  
  static
  {
    if (!UxmModule_ProvideUxmBlockProviderFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UxmModule_ProvideUxmBlockProviderFactory(UxmModule paramUxmModule, Provider<UpsightContext> paramProvider, Provider<String> paramProvider1, Provider<UxmSchema> paramProvider2)
  {
    assert (paramUxmModule != null);
    this.module = paramUxmModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
    assert (paramProvider1 != null);
    this.uxmSchemaRawStringProvider = paramProvider1;
    assert (paramProvider2 != null);
    this.uxmSchemaProvider = paramProvider2;
  }
  
  public static Factory<UxmBlockProvider> create(UxmModule paramUxmModule, Provider<UpsightContext> paramProvider, Provider<String> paramProvider1, Provider<UxmSchema> paramProvider2)
  {
    return new UxmModule_ProvideUxmBlockProviderFactory(paramUxmModule, paramProvider, paramProvider1, paramProvider2);
  }
  
  public UxmBlockProvider get()
  {
    UxmBlockProvider localUxmBlockProvider = this.module.provideUxmBlockProvider((UpsightContext)this.upsightProvider.get(), (String)this.uxmSchemaRawStringProvider.get(), (UxmSchema)this.uxmSchemaProvider.get());
    if (localUxmBlockProvider == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUxmBlockProvider;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/UxmModule_ProvideUxmBlockProviderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */