package com.upsight.android.internal;

import com.upsight.android.UpsightCoreComponent;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules={CoreModule.class})
@Singleton
public abstract interface CoreComponent
  extends UpsightCoreComponent
{}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/CoreComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */