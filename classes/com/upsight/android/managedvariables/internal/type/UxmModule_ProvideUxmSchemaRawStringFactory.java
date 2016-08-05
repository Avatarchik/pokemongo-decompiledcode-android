package com.upsight.android.managedvariables.internal.type;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UxmModule_ProvideUxmSchemaRawStringFactory
  implements Factory<String>
{
  private final UxmModule module;
  private final Provider<UpsightContext> upsightProvider;
  private final Provider<Integer> uxmSchemaResProvider;
  
  static
  {
    if (!UxmModule_ProvideUxmSchemaRawStringFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UxmModule_ProvideUxmSchemaRawStringFactory(UxmModule paramUxmModule, Provider<UpsightContext> paramProvider, Provider<Integer> paramProvider1)
  {
    assert (paramUxmModule != null);
    this.module = paramUxmModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
    assert (paramProvider1 != null);
    this.uxmSchemaResProvider = paramProvider1;
  }
  
  public static Factory<String> create(UxmModule paramUxmModule, Provider<UpsightContext> paramProvider, Provider<Integer> paramProvider1)
  {
    return new UxmModule_ProvideUxmSchemaRawStringFactory(paramUxmModule, paramProvider, paramProvider1);
  }
  
  public String get()
  {
    String str = this.module.provideUxmSchemaRawString((UpsightContext)this.upsightProvider.get(), (Integer)this.uxmSchemaResProvider.get());
    if (str == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return str;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/UxmModule_ProvideUxmSchemaRawStringFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */