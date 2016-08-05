package com.upsight.android.managedvariables.internal.type;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UxmModule_ProvideUxmSchemaMapperFactory
  implements Factory<ObjectMapper>
{
  private final UxmModule module;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!UxmModule_ProvideUxmSchemaMapperFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UxmModule_ProvideUxmSchemaMapperFactory(UxmModule paramUxmModule, Provider<UpsightContext> paramProvider)
  {
    assert (paramUxmModule != null);
    this.module = paramUxmModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<ObjectMapper> create(UxmModule paramUxmModule, Provider<UpsightContext> paramProvider)
  {
    return new UxmModule_ProvideUxmSchemaMapperFactory(paramUxmModule, paramProvider);
  }
  
  public ObjectMapper get()
  {
    ObjectMapper localObjectMapper = this.module.provideUxmSchemaMapper((UpsightContext)this.upsightProvider.get());
    if (localObjectMapper == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localObjectMapper;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/UxmModule_ProvideUxmSchemaMapperFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */