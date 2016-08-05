package com.upsight.android;

import android.content.Context;
import android.content.ContextWrapper;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Named;

public class UpsightContext
  extends ContextWrapper
{
  private final String mAppToken;
  private UpsightCoreComponent mCoreComponent;
  private final UpsightDataStore mDataStore;
  private final Map<String, UpsightExtension> mExtensionsMap = new ConcurrentHashMap();
  private final UpsightLogger mLogger;
  private final String mPublicKey;
  private final String mSdkPlugin;
  private final String mSid;
  
  public UpsightContext(Context paramContext, @Named("com.upsight.sdk_plugin") String paramString1, @Named("com.upsight.app_token") String paramString2, @Named("com.upsight.public_key") String paramString3, String paramString4, UpsightDataStore paramUpsightDataStore, UpsightLogger paramUpsightLogger)
  {
    super(paramContext);
    this.mSdkPlugin = paramString1;
    this.mAppToken = paramString2;
    this.mPublicKey = paramString3;
    this.mSid = paramString4;
    this.mDataStore = paramUpsightDataStore;
    this.mLogger = paramUpsightLogger;
  }
  
  public String getApplicationToken()
  {
    return this.mAppToken;
  }
  
  public UpsightCoreComponent getCoreComponent()
  {
    return this.mCoreComponent;
  }
  
  public UpsightDataStore getDataStore()
  {
    return this.mDataStore;
  }
  
  public UpsightLogger getLogger()
  {
    return this.mLogger;
  }
  
  public String getPublicKey()
  {
    return this.mPublicKey;
  }
  
  public String getSdkBuild()
  {
    return getString(R.string.upsight_sdk_build);
  }
  
  public String getSdkPlugin()
  {
    return this.mSdkPlugin;
  }
  
  public String getSdkVersion()
  {
    return getString(R.string.upsight_sdk_version);
  }
  
  public String getSid()
  {
    return this.mSid;
  }
  
  public UpsightExtension<?, ?> getUpsightExtension(String paramString)
  {
    return (UpsightExtension)this.mExtensionsMap.get(paramString);
  }
  
  void onCreate(UpsightCoreComponent paramUpsightCoreComponent, Map<String, UpsightExtension> paramMap)
  {
    this.mCoreComponent = paramUpsightCoreComponent;
    Iterator localIterator1 = paramMap.entrySet().iterator();
    while (localIterator1.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator1.next();
      UpsightExtension localUpsightExtension2 = (UpsightExtension)localEntry.getValue();
      localUpsightExtension2.setComponent(localUpsightExtension2.onResolve(paramUpsightCoreComponent.upsightContext()));
      this.mExtensionsMap.put(localEntry.getKey(), localUpsightExtension2);
    }
    Iterator localIterator2 = paramMap.values().iterator();
    while (localIterator2.hasNext())
    {
      UpsightExtension localUpsightExtension1 = (UpsightExtension)localIterator2.next();
      localUpsightExtension1.getComponent().inject(localUpsightExtension1);
    }
    Iterator localIterator3 = paramMap.values().iterator();
    while (localIterator3.hasNext()) {
      ((UpsightExtension)localIterator3.next()).onCreate(this);
    }
    Iterator localIterator4 = paramMap.values().iterator();
    while (localIterator4.hasNext()) {
      ((UpsightExtension)localIterator4.next()).onPostCreate(this);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */