package com.upsight.android.analytics.internal.dispatcher.schema;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SchemaModule_ProvideSchemaSelectorBuilderFactory
  implements Factory<SchemaSelectorBuilder>
{
  private final SchemaModule module;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!SchemaModule_ProvideSchemaSelectorBuilderFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public SchemaModule_ProvideSchemaSelectorBuilderFactory(SchemaModule paramSchemaModule, Provider<UpsightContext> paramProvider)
  {
    assert (paramSchemaModule != null);
    this.module = paramSchemaModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<SchemaSelectorBuilder> create(SchemaModule paramSchemaModule, Provider<UpsightContext> paramProvider)
  {
    return new SchemaModule_ProvideSchemaSelectorBuilderFactory(paramSchemaModule, paramProvider);
  }
  
  public SchemaSelectorBuilder get()
  {
    SchemaSelectorBuilder localSchemaSelectorBuilder = this.module.provideSchemaSelectorBuilder((UpsightContext)this.upsightProvider.get());
    if (localSchemaSelectorBuilder == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localSchemaSelectorBuilder;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/SchemaModule_ProvideSchemaSelectorBuilderFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */