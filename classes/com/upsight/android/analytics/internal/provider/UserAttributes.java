package com.upsight.android.analytics.internal.provider;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.provider.UpsightUserAttributes;
import com.upsight.android.analytics.provider.UpsightUserAttributes.Entry;
import com.upsight.android.internal.util.PreferencesHelper;
import com.upsight.android.logger.UpsightLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
final class UserAttributes
  extends UpsightUserAttributes
{
  private UpsightLogger mLogger;
  private UpsightContext mUpsight;
  private Map<String, UpsightUserAttributes.Entry> mUserAttributes = new HashMap();
  private Set<UpsightUserAttributes.Entry> mUserAttributesSet = new HashSet();
  
  @Inject
  UserAttributes(UpsightContext paramUpsightContext)
  {
    this.mUpsight = paramUpsightContext;
    this.mLogger = paramUpsightContext.getLogger();
    loadDefaultAttributes();
  }
  
  private void loadDefaultAttributes()
  {
    try
    {
      Bundle localBundle = this.mUpsight.getPackageManager().getApplicationInfo(this.mUpsight.getPackageName(), 128).metaData;
      if (localBundle != null)
      {
        Iterator localIterator = localBundle.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          if ((!TextUtils.isEmpty(str1)) && (str1.startsWith("com.upsight.user_attribute.")))
          {
            String str2 = str1.substring(1 + str1.lastIndexOf('.'));
            UpsightUserAttributes.Entry localEntry = new UpsightUserAttributes.Entry(str2, localBundle.get(str1));
            this.mUserAttributes.put(str2, localEntry);
            this.mUserAttributesSet.add(localEntry);
          }
        }
      }
      UpsightLogger localUpsightLogger;
      Object[] arrayOfObject;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localUpsightLogger = this.mLogger;
      arrayOfObject = new Object[1];
      arrayOfObject[0] = localNameNotFoundException;
      localUpsightLogger.e("Upsight", "Unexpected error: Package name missing!?", arrayOfObject);
    }
  }
  
  public Boolean getBoolean(String paramString)
  {
    if (!this.mUserAttributes.containsKey(paramString))
    {
      UpsightLogger localUpsightLogger2 = this.mLogger;
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = "com.upsight.user_attribute.";
      arrayOfObject3[1] = paramString;
      localUpsightLogger2.w("Upsight", String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject3), new Object[0]);
      Object[] arrayOfObject4 = new Object[2];
      arrayOfObject4[0] = "com.upsight.user_attribute.";
      arrayOfObject4[1] = paramString;
      throw new IllegalArgumentException(String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject4));
    }
    if (!Boolean.class.equals(((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType()))
    {
      UpsightLogger localUpsightLogger1 = this.mLogger;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
      localUpsightLogger1.w("Upsight", String.format("The user attribute %s must be of type: %s", arrayOfObject1), new Object[0]);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
      throw new IllegalArgumentException(String.format("The user attribute %s must be of type: %s", arrayOfObject2));
    }
    return Boolean.valueOf(PreferencesHelper.getBoolean(this.mUpsight, "com.upsight.user_attribute." + paramString, ((Boolean)((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getDefaultValue()).booleanValue()));
  }
  
  public Set<UpsightUserAttributes.Entry> getDefault()
  {
    return this.mUserAttributesSet;
  }
  
  public Float getFloat(String paramString)
  {
    if (!this.mUserAttributes.containsKey(paramString))
    {
      UpsightLogger localUpsightLogger2 = this.mLogger;
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = "com.upsight.user_attribute.";
      arrayOfObject3[1] = paramString;
      localUpsightLogger2.w("Upsight", String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject3), new Object[0]);
      Object[] arrayOfObject4 = new Object[2];
      arrayOfObject4[0] = "com.upsight.user_attribute.";
      arrayOfObject4[1] = paramString;
      throw new IllegalArgumentException(String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject4));
    }
    if (!Float.class.equals(((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType()))
    {
      UpsightLogger localUpsightLogger1 = this.mLogger;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
      localUpsightLogger1.w("Upsight", String.format("The user attribute %s must be of type: %s", arrayOfObject1), new Object[0]);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
      throw new IllegalArgumentException(String.format("The user attribute %s must be of type: %s", arrayOfObject2));
    }
    return Float.valueOf(PreferencesHelper.getFloat(this.mUpsight, "com.upsight.user_attribute." + paramString, ((Float)((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getDefaultValue()).floatValue()));
  }
  
  public Integer getInt(String paramString)
  {
    if (!this.mUserAttributes.containsKey(paramString))
    {
      UpsightLogger localUpsightLogger2 = this.mLogger;
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = "com.upsight.user_attribute.";
      arrayOfObject3[1] = paramString;
      localUpsightLogger2.w("Upsight", String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject3), new Object[0]);
      Object[] arrayOfObject4 = new Object[2];
      arrayOfObject4[0] = "com.upsight.user_attribute.";
      arrayOfObject4[1] = paramString;
      throw new IllegalArgumentException(String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject4));
    }
    if (!Integer.class.equals(((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType()))
    {
      UpsightLogger localUpsightLogger1 = this.mLogger;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
      localUpsightLogger1.w("Upsight", String.format("The user attribute %s must be of type: %s", arrayOfObject1), new Object[0]);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
      throw new IllegalArgumentException(String.format("The user attribute %s must be of type: %s", arrayOfObject2));
    }
    return Integer.valueOf(PreferencesHelper.getInt(this.mUpsight, "com.upsight.user_attribute." + paramString, ((Integer)((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getDefaultValue()).intValue()));
  }
  
  public String getString(String paramString)
  {
    if (!this.mUserAttributes.containsKey(paramString))
    {
      UpsightLogger localUpsightLogger2 = this.mLogger;
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = "com.upsight.user_attribute.";
      arrayOfObject3[1] = paramString;
      localUpsightLogger2.w("Upsight", String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject3), new Object[0]);
      Object[] arrayOfObject4 = new Object[2];
      arrayOfObject4[0] = "com.upsight.user_attribute.";
      arrayOfObject4[1] = paramString;
      throw new IllegalArgumentException(String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject4));
    }
    if (!String.class.equals(((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType()))
    {
      UpsightLogger localUpsightLogger1 = this.mLogger;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
      localUpsightLogger1.w("Upsight", String.format("The user attribute %s must be of type: %s", arrayOfObject1), new Object[0]);
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
      throw new IllegalArgumentException(String.format("The user attribute %s must be of type: %s", arrayOfObject2));
    }
    return PreferencesHelper.getString(this.mUpsight, "com.upsight.user_attribute." + paramString, (String)((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getDefaultValue());
  }
  
  public void put(String paramString, Boolean paramBoolean)
  {
    if (paramBoolean == null) {
      PreferencesHelper.clear(this.mUpsight, paramString);
    }
    if (this.mUserAttributes.containsKey(paramString))
    {
      if (!Boolean.class.equals(((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType()))
      {
        UpsightLogger localUpsightLogger2 = this.mLogger;
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = paramString;
        arrayOfObject3[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
        localUpsightLogger2.w("Upsight", String.format("The user attribute %s must be of type: %s", arrayOfObject3), new Object[0]);
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = paramString;
        arrayOfObject4[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
        throw new IllegalArgumentException(String.format("The user attribute %s must be of type: %s", arrayOfObject4));
      }
      PreferencesHelper.putBoolean(this.mUpsight, "com.upsight.user_attribute." + paramString, paramBoolean.booleanValue());
      return;
    }
    UpsightLogger localUpsightLogger1 = this.mLogger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = "com.upsight.user_attribute.";
    arrayOfObject1[1] = paramString;
    localUpsightLogger1.w("Upsight", String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject1), new Object[0]);
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = "com.upsight.user_attribute.";
    arrayOfObject2[1] = paramString;
    throw new IllegalArgumentException(String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject2));
  }
  
  public void put(String paramString, Float paramFloat)
  {
    if (paramFloat == null) {
      PreferencesHelper.clear(this.mUpsight, paramString);
    }
    if (this.mUserAttributes.containsKey(paramString))
    {
      if (!Float.class.equals(((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType()))
      {
        UpsightLogger localUpsightLogger2 = this.mLogger;
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = paramString;
        arrayOfObject3[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
        localUpsightLogger2.w("Upsight", String.format("The user attribute %s must be of type: %s", arrayOfObject3), new Object[0]);
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = paramString;
        arrayOfObject4[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
        throw new IllegalArgumentException(String.format("The user attribute %s must be of type: %s", arrayOfObject4));
      }
      PreferencesHelper.putFloat(this.mUpsight, "com.upsight.user_attribute." + paramString, paramFloat.floatValue());
      return;
    }
    UpsightLogger localUpsightLogger1 = this.mLogger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = "com.upsight.user_attribute.";
    arrayOfObject1[1] = paramString;
    localUpsightLogger1.w("Upsight", String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject1), new Object[0]);
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = "com.upsight.user_attribute.";
    arrayOfObject2[1] = paramString;
    throw new IllegalArgumentException(String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject2));
  }
  
  public void put(String paramString, Integer paramInteger)
  {
    if (paramInteger == null) {
      PreferencesHelper.clear(this.mUpsight, paramString);
    }
    if (this.mUserAttributes.containsKey(paramString))
    {
      if (!Integer.class.equals(((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType()))
      {
        UpsightLogger localUpsightLogger2 = this.mLogger;
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = paramString;
        arrayOfObject3[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
        localUpsightLogger2.w("Upsight", String.format("The user attribute %s must be of type: %s", arrayOfObject3), new Object[0]);
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = paramString;
        arrayOfObject4[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString)).getType();
        throw new IllegalArgumentException(String.format("The user attribute %s must be of type: %s", arrayOfObject4));
      }
      PreferencesHelper.putInt(this.mUpsight, "com.upsight.user_attribute." + paramString, paramInteger.intValue());
      return;
    }
    UpsightLogger localUpsightLogger1 = this.mLogger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = "com.upsight.user_attribute.";
    arrayOfObject1[1] = paramString;
    localUpsightLogger1.w("Upsight", String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject1), new Object[0]);
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = "com.upsight.user_attribute.";
    arrayOfObject2[1] = paramString;
    throw new IllegalArgumentException(String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject2));
  }
  
  public void put(String paramString1, String paramString2)
    throws IllegalArgumentException
  {
    if (paramString2 == null) {
      PreferencesHelper.clear(this.mUpsight, paramString1);
    }
    if (this.mUserAttributes.containsKey(paramString1))
    {
      if (!String.class.equals(((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString1)).getType()))
      {
        UpsightLogger localUpsightLogger2 = this.mLogger;
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = paramString1;
        arrayOfObject3[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString1)).getType();
        localUpsightLogger2.w("Upsight", String.format("The user attribute %s must be of type: %s", arrayOfObject3), new Object[0]);
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = paramString1;
        arrayOfObject4[1] = ((UpsightUserAttributes.Entry)this.mUserAttributes.get(paramString1)).getType();
        throw new IllegalArgumentException(String.format("The user attribute %s must be of type: %s", arrayOfObject4));
      }
      PreferencesHelper.putString(this.mUpsight, "com.upsight.user_attribute." + paramString1, paramString2);
      return;
    }
    UpsightLogger localUpsightLogger1 = this.mLogger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = "com.upsight.user_attribute.";
    arrayOfObject1[1] = paramString1;
    localUpsightLogger1.w("Upsight", String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject1), new Object[0]);
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = "com.upsight.user_attribute.";
    arrayOfObject2[1] = paramString1;
    throw new IllegalArgumentException(String.format("No metadata found with android:name %s%s in the Android Manifest", arrayOfObject2));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/UserAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */