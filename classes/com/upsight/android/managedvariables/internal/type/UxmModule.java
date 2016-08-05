package com.upsight.android.managedvariables.internal.type;

import android.content.res.Resources;
import android.text.TextUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightCoreComponent;
import com.upsight.android.analytics.UpsightAnalyticsComponent;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.managedvariables.experience.UpsightUserExperience;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.inject.Singleton;
import org.apache.commons.io.IOUtils;
import rx.Scheduler;

@Module
public class UxmModule
{
  public static final String MAPPER_UXM_SCHEMA = "mapperUxmSchema";
  public static final String STRING_RAW_UXM_SCHEMA = "stringRawUxmSchema";
  
  @Provides
  @Singleton
  ManagedVariableManager provideManagedVariableManager(UpsightContext paramUpsightContext, @Named("main") Scheduler paramScheduler, UxmSchema paramUxmSchema)
  {
    return new ManagedVariableManager(paramScheduler, paramUpsightContext.getDataStore(), paramUxmSchema);
  }
  
  @Provides
  @Singleton
  UxmBlockProvider provideUxmBlockProvider(UpsightContext paramUpsightContext, @Named("stringRawUxmSchema") String paramString, UxmSchema paramUxmSchema)
  {
    return new UxmBlockProvider(paramUpsightContext, paramString, paramUxmSchema);
  }
  
  @Provides
  @Singleton
  UxmContentFactory provideUxmContentFactory(UpsightContext paramUpsightContext, @Named("main") Scheduler paramScheduler, UpsightUserExperience paramUpsightUserExperience)
  {
    UpsightCoreComponent localUpsightCoreComponent = paramUpsightContext.getCoreComponent();
    Bus localBus = localUpsightCoreComponent.bus();
    ObjectMapper localObjectMapper = localUpsightCoreComponent.objectMapper();
    UpsightLogger localUpsightLogger = paramUpsightContext.getLogger();
    return new UxmContentFactory(new UxmContentActions.UxmContentActionContext(paramUpsightContext, localBus, localObjectMapper, ((UpsightAnalyticsComponent)((UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics")).getComponent()).clock(), paramScheduler.createWorker(), localUpsightLogger), paramUpsightUserExperience);
  }
  
  @Provides
  @Singleton
  UxmSchema provideUxmSchema(UpsightContext paramUpsightContext, @Named("mapperUxmSchema") ObjectMapper paramObjectMapper, @Named("stringRawUxmSchema") String paramString)
  {
    localUpsightLogger = paramUpsightContext.getLogger();
    Object localObject = null;
    if (!TextUtils.isEmpty(paramString)) {}
    try
    {
      UxmSchema localUxmSchema = UxmSchema.create(paramString, paramObjectMapper, localUpsightLogger);
      localObject = localUxmSchema;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        localUpsightLogger.e("Upsight", localIllegalArgumentException, "Failed to parse UXM schema", new Object[0]);
      }
    }
    if (localObject == null)
    {
      localObject = new UxmSchema(localUpsightLogger);
      localUpsightLogger.d("Upsight", "Empty UXM schema used", new Object[0]);
    }
    return (UxmSchema)localObject;
  }
  
  @Provides
  @Named("mapperUxmSchema")
  @Singleton
  ObjectMapper provideUxmSchemaMapper(UpsightContext paramUpsightContext)
  {
    return paramUpsightContext.getCoreComponent().objectMapper().copy().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }
  
  @Provides
  @Named("stringRawUxmSchema")
  @Singleton
  String provideUxmSchemaRawString(UpsightContext paramUpsightContext, @Named("resUxmSchema") Integer paramInteger)
  {
    UpsightLogger localUpsightLogger = paramUpsightContext.getLogger();
    String str = "";
    try
    {
      InputStream localInputStream = paramUpsightContext.getResources().openRawResource(paramInteger.intValue());
      if (localInputStream != null) {
        str = IOUtils.toString(localInputStream);
      } else {
        localUpsightLogger.e("Upsight", "Failed to find UXM schema file", new Object[0]);
      }
    }
    catch (IOException localIOException)
    {
      localUpsightLogger.e("Upsight", localIOException, "Failed to read UXM schema file", new Object[0]);
    }
    return str;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/UxmModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */