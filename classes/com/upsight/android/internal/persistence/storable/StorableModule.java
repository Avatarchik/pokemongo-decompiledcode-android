package com.upsight.android.internal.persistence.storable;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public final class StorableModule
{
  @Provides
  @Singleton
  public StorableInfoCache provideStorableInfoCache(ObjectMapper paramObjectMapper)
  {
    return new StorableInfoCache(paramObjectMapper);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/StorableModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */