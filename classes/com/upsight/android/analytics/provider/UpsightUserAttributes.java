package com.upsight.android.analytics.provider;

import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.UpsightAnalyticsApi;
import com.upsight.android.logger.UpsightLogger;
import java.util.HashSet;
import java.util.Set;

public abstract class UpsightUserAttributes
{
  public static final String USER_ATTRIBUTES_PREFIX = "com.upsight.user_attribute.";
  
  public static Boolean getBoolean(UpsightContext paramUpsightContext, String paramString)
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {}
    for (Boolean localBoolean = localUpsightAnalyticsExtension.getApi().getBooleanUserAttribute(paramString);; localBoolean = null)
    {
      return localBoolean;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static Set<Entry> getDefault(UpsightContext paramUpsightContext)
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {}
    for (Object localObject = localUpsightAnalyticsExtension.getApi().getDefaultUserAttributes();; localObject = new HashSet())
    {
      return (Set<Entry>)localObject;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static Float getFloat(UpsightContext paramUpsightContext, String paramString)
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {}
    for (Float localFloat = localUpsightAnalyticsExtension.getApi().getFloatUserAttribute(paramString);; localFloat = null)
    {
      return localFloat;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static Integer getInteger(UpsightContext paramUpsightContext, String paramString)
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {}
    for (Integer localInteger = localUpsightAnalyticsExtension.getApi().getIntUserAttribute(paramString);; localInteger = null)
    {
      return localInteger;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static String getString(UpsightContext paramUpsightContext, String paramString)
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {}
    for (String str = localUpsightAnalyticsExtension.getApi().getStringUserAttribute(paramString);; str = null)
    {
      return str;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static void put(UpsightContext paramUpsightContext, String paramString, Boolean paramBoolean)
    throws IllegalArgumentException
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {
      localUpsightAnalyticsExtension.getApi().putUserAttribute(paramString, paramBoolean);
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static void put(UpsightContext paramUpsightContext, String paramString, Float paramFloat)
    throws IllegalArgumentException
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {
      localUpsightAnalyticsExtension.getApi().putUserAttribute(paramString, paramFloat);
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static void put(UpsightContext paramUpsightContext, String paramString, Integer paramInteger)
    throws IllegalArgumentException
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {
      localUpsightAnalyticsExtension.getApi().putUserAttribute(paramString, paramInteger);
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static void put(UpsightContext paramUpsightContext, String paramString1, String paramString2)
    throws IllegalArgumentException
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {
      localUpsightAnalyticsExtension.getApi().putUserAttribute(paramString1, paramString2);
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public abstract Boolean getBoolean(String paramString);
  
  public abstract Set<Entry> getDefault();
  
  public abstract Float getFloat(String paramString);
  
  public abstract Integer getInt(String paramString);
  
  public abstract String getString(String paramString);
  
  public abstract void put(String paramString, Boolean paramBoolean);
  
  public abstract void put(String paramString, Float paramFloat);
  
  public abstract void put(String paramString, Integer paramInteger);
  
  public abstract void put(String paramString1, String paramString2);
  
  public static class Entry
  {
    private Object mDefaultValue;
    private String mKey;
    
    public Entry(String paramString, Object paramObject)
    {
      this.mKey = paramString;
      this.mDefaultValue = paramObject;
    }
    
    public Object getDefaultValue()
    {
      return this.mDefaultValue;
    }
    
    public String getKey()
    {
      return this.mKey;
    }
    
    public Class getType()
    {
      return this.mDefaultValue.getClass();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/provider/UpsightUserAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */