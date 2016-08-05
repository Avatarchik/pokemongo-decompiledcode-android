package com.upsight.android.internal;

import com.upsight.android.internal.logger.LoggerModule;
import com.upsight.android.internal.persistence.PersistenceModule;
import com.upsight.android.internal.persistence.storable.StorableModule;
import dagger.Module;

@Module(includes={UpsightContextModule.class, ContextModule.class, PropertiesModule.class, ObjectMapperModule.class, SchedulersModule.class, StorableModule.class, PersistenceModule.class, LoggerModule.class})
public final class CoreModule {}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/CoreModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */