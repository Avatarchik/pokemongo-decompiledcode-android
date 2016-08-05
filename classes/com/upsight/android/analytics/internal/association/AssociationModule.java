package com.upsight.android.analytics.internal.association;

import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.session.Clock;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public final class AssociationModule
{
  @Provides
  @Singleton
  public AssociationManager provideAssociationManager(UpsightContext paramUpsightContext, Clock paramClock)
  {
    return new AssociationManagerImpl(paramUpsightContext.getDataStore(), paramClock);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/association/AssociationModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */