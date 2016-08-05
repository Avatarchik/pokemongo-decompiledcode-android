package com.upsight.android.managedvariables.internal;

import dagger.internal.Factory;

public final class ResourceModule_ProvideUxmSchemaResourceFactory
  implements Factory<Integer>
{
  private final ResourceModule module;
  
  static
  {
    if (!ResourceModule_ProvideUxmSchemaResourceFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ResourceModule_ProvideUxmSchemaResourceFactory(ResourceModule paramResourceModule)
  {
    assert (paramResourceModule != null);
    this.module = paramResourceModule;
  }
  
  public static Factory<Integer> create(ResourceModule paramResourceModule)
  {
    return new ResourceModule_ProvideUxmSchemaResourceFactory(paramResourceModule);
  }
  
  public Integer get()
  {
    Integer localInteger = this.module.provideUxmSchemaResource();
    if (localInteger == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localInteger;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/ResourceModule_ProvideUxmSchemaResourceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */