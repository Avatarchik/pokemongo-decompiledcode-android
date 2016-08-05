package com.upsight.android.unity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import com.upsight.android.Upsight;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.UpsightGooglePlayHelper;
import com.upsight.android.analytics.event.UpsightCustomEvent;
import com.upsight.android.analytics.event.UpsightCustomEvent.Builder;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.event.milestone.UpsightMilestoneEvent;
import com.upsight.android.analytics.event.milestone.UpsightMilestoneEvent.Builder;
import com.upsight.android.analytics.event.monetization.UpsightMonetizationEvent;
import com.upsight.android.analytics.event.monetization.UpsightMonetizationEvent.Builder;
import com.upsight.android.analytics.provider.UpsightLocationTracker;
import com.upsight.android.analytics.provider.UpsightLocationTracker.Data;
import com.upsight.android.analytics.provider.UpsightOptOutStatus;
import com.upsight.android.analytics.provider.UpsightUserAttributes;
import com.upsight.android.googlepushservices.UpsightGooglePushServices;
import com.upsight.android.googlepushservices.UpsightGooglePushServices.OnRegisterListener;
import com.upsight.android.googlepushservices.UpsightGooglePushServices.OnUnregisterListener;
import com.upsight.android.googlepushservices.UpsightPushBillboard;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.logger.UpsightLogger.Level;
import com.upsight.android.managedvariables.type.UpsightManagedBoolean;
import com.upsight.android.managedvariables.type.UpsightManagedFloat;
import com.upsight.android.managedvariables.type.UpsightManagedInt;
import com.upsight.android.managedvariables.type.UpsightManagedString;
import com.upsight.android.marketing.UpsightBillboard;
import com.upsight.android.marketing.UpsightMarketingContentStore;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class UpsightPlugin
  extends AbstractUpsightPlugin
  implements Application.ActivityLifecycleCallbacks
{
  private static UpsightPlugin sInstance;
  private BillboardHandler mBillboardHandler;
  private Map<String, UpsightBillboard> mBillboardMap = new HashMap();
  private boolean mHasActiveBillboard = false;
  private List<String> mJettisonedBillboardScopes;
  private UpsightBillboard mPushBillboard;
  private boolean mShouldSynchronizeManagedVariables = true;
  private UpsightContext mUpsight;
  
  /**
   * @deprecated
   */
  @SuppressLint({"NewApi"})
  public static UpsightPlugin instance()
  {
    try
    {
      if (sInstance == null)
      {
        sInstance = new UpsightPlugin();
        Activity localActivity = sInstance.getActivity();
        if (localActivity != null)
        {
          sInstance.mUpsight = Upsight.createContext(localActivity);
          sInstance.mBillboardHandler = new BillboardHandler(localActivity, sInstance);
          Log.i("Upsight", "creating UpsightPushBillboard");
          sInstance.mPushBillboard = UpsightPushBillboard.create(sInstance.mUpsight, sInstance.mBillboardHandler);
          if (Build.VERSION.SDK_INT >= 14)
          {
            Log.i("Upsight", "wiring up an ActivityLifecycleCallback listener since we are on API 14+");
            localActivity.getApplication().registerActivityLifecycleCallbacks(sInstance);
          }
        }
      }
      UpsightPlugin localUpsightPlugin = sInstance;
      return localUpsightPlugin;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private static UpsightPublisherData publisherDataFromJsonString(String paramString)
  {
    UpsightPublisherData.Builder localBuilder = new UpsightPublisherData.Builder();
    if ((paramString != null) && (paramString.length() > 0)) {}
    for (;;)
    {
      String str;
      Object localObject;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        Iterator localIterator = localJSONObject.keys();
        if (localIterator.hasNext())
        {
          str = (String)localIterator.next();
          try
          {
            localObject = localJSONObject.get(str);
            if (!(localObject instanceof String)) {
              break label107;
            }
            localBuilder.put(str, (String)localObject);
          }
          catch (JSONException localJSONException2)
          {
            localJSONException2.printStackTrace();
          }
          continue;
        }
        return localBuilder.build();
      }
      catch (JSONException localJSONException1)
      {
        localJSONException1.printStackTrace();
      }
      label107:
      if ((localObject instanceof Float)) {
        localBuilder.put(str, ((Float)localObject).floatValue());
      } else if ((localObject instanceof Double)) {
        localBuilder.put(str, ((Double)localObject).doubleValue());
      } else if ((localObject instanceof Long)) {
        localBuilder.put(str, ((Long)localObject).longValue());
      } else if ((localObject instanceof Boolean)) {
        localBuilder.put(str, ((Boolean)localObject).booleanValue());
      }
    }
  }
  
  public void destroyBillboard(String paramString)
  {
    if ((this.mBillboardMap.containsKey(paramString)) && (!getHasActiveBillboard()))
    {
      Log.i("Upsight", "Destroying billboard for scope: " + paramString);
      ((UpsightBillboard)this.mBillboardMap.get(paramString)).destroy();
      this.mBillboardMap.remove(paramString);
    }
  }
  
  public String getAppToken()
  {
    return this.mUpsight.getApplicationToken();
  }
  
  boolean getHasActiveBillboard()
  {
    return this.mHasActiveBillboard;
  }
  
  public boolean getManagedBool(String paramString)
  {
    boolean bool = false;
    try
    {
      UpsightManagedBoolean localUpsightManagedBoolean = UpsightManagedBoolean.fetch(this.mUpsight, paramString);
      if (localUpsightManagedBoolean != null) {
        bool = ((Boolean)localUpsightManagedBoolean.get()).booleanValue();
      } else {
        Log.e("Upsight", "Unknown tag " + paramString + " for managed bool, please check your UXM schema");
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return bool;
  }
  
  public float getManagedFloat(String paramString)
  {
    float f = 0.0F;
    try
    {
      UpsightManagedFloat localUpsightManagedFloat = UpsightManagedFloat.fetch(this.mUpsight, paramString);
      if (localUpsightManagedFloat != null) {
        f = ((Float)localUpsightManagedFloat.get()).floatValue();
      } else {
        Log.e("Upsight", "Unknown tag " + paramString + " for managed float, please check your UXM schema");
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return f;
  }
  
  public int getManagedInt(String paramString)
  {
    int i = 0;
    try
    {
      UpsightManagedInt localUpsightManagedInt = UpsightManagedInt.fetch(this.mUpsight, paramString);
      if (localUpsightManagedInt != null) {
        i = ((Integer)localUpsightManagedInt.get()).intValue();
      } else {
        Log.e("Upsight", "Unknown tag " + paramString + " for managed int, please check your UXM schema");
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return i;
  }
  
  public String getManagedString(String paramString)
  {
    String str = null;
    try
    {
      UpsightManagedString localUpsightManagedString = UpsightManagedString.fetch(this.mUpsight, paramString);
      if (localUpsightManagedString != null) {
        str = (String)localUpsightManagedString.get();
      } else {
        Log.e("Upsight", "Unknown tag " + paramString + " for managed string, please check your UXM schema");
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str;
  }
  
  public boolean getOptOutStatus()
  {
    return UpsightOptOutStatus.get(this.mUpsight);
  }
  
  public String getPluginVersion()
  {
    return this.mUpsight.getSdkPlugin();
  }
  
  public String getPublicKey()
  {
    return this.mUpsight.getPublicKey();
  }
  
  public boolean getShouldSynchronizeManagedVariables()
  {
    return this.mShouldSynchronizeManagedVariables;
  }
  
  public String getSid()
  {
    return this.mUpsight.getSid();
  }
  
  public boolean getUserAttributesBool(String paramString)
  {
    try
    {
      boolean bool2 = UpsightUserAttributes.getBoolean(this.mUpsight, paramString).booleanValue();
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  public float getUserAttributesFloat(String paramString)
  {
    try
    {
      float f2 = UpsightUserAttributes.getFloat(this.mUpsight, paramString).floatValue();
      f1 = f2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        float f1 = 0.0F;
      }
    }
    return f1;
  }
  
  public int getUserAttributesInt(String paramString)
  {
    try
    {
      int j = UpsightUserAttributes.getInteger(this.mUpsight, paramString).intValue();
      i = j;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        int i = 0;
      }
    }
    return i;
  }
  
  public String getUserAttributesString(String paramString)
  {
    try
    {
      String str2 = UpsightUserAttributes.getString(this.mUpsight, paramString);
      str1 = str2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        String str1 = null;
      }
    }
    return str1;
  }
  
  public boolean isContentReadyForBillboardWithScope(String paramString)
  {
    return UpsightMarketingContentStore.isContentReady(this.mUpsight, paramString);
  }
  
  public void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityDestroyed(Activity paramActivity) {}
  
  public void onActivityPaused(Activity paramActivity)
  {
    this.mPushBillboard.destroy();
    this.mPushBillboard = null;
    this.mJettisonedBillboardScopes = new ArrayList();
    Iterator localIterator = this.mBillboardMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      this.mJettisonedBillboardScopes.add(str);
      ((UpsightBillboard)this.mBillboardMap.get(str)).destroy();
    }
    this.mBillboardMap.clear();
    Log.i("Upsight", "tombstoned " + this.mJettisonedBillboardScopes.size() + " scopes when pausing");
  }
  
  public void onActivityResumed(Activity paramActivity)
  {
    Log.i("Upsight", "resurrecting " + this.mJettisonedBillboardScopes.size() + " scopes when resuming and push billboard");
    if (this.mPushBillboard == null) {
      this.mPushBillboard = UpsightPushBillboard.create(this.mUpsight, this.mBillboardHandler);
    }
    Iterator localIterator = this.mJettisonedBillboardScopes.iterator();
    while (localIterator.hasNext()) {
      prepareBillboard((String)localIterator.next());
    }
    this.mJettisonedBillboardScopes = null;
  }
  
  public void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public void onActivityStarted(Activity paramActivity) {}
  
  public void onActivityStopped(Activity paramActivity) {}
  
  public void prepareBillboard(String paramString)
  {
    if ((this.mBillboardMap.containsKey(paramString)) || (getHasActiveBillboard())) {}
    for (;;)
    {
      return;
      if (this.mBillboardMap.size() > 0)
      {
        Iterator localIterator = this.mBillboardMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          Log.i("Upsight", "clearing out cached billboard [" + str + "] to make room for the new billboard: " + paramString);
          ((UpsightBillboard)this.mBillboardMap.get(str)).destroy();
        }
        this.mBillboardMap.clear();
      }
      UpsightBillboard localUpsightBillboard = UpsightBillboard.create(this.mUpsight, paramString, this.mBillboardHandler);
      this.mBillboardMap.put(paramString, localUpsightBillboard);
    }
  }
  
  public void purgeLocation()
  {
    UpsightLocationTracker.purge(this.mUpsight);
  }
  
  public void recordAnalyticsEvent(String paramString1, String paramString2)
  {
    UpsightCustomEvent.Builder localBuilder = UpsightCustomEvent.createBuilder(paramString1);
    localBuilder.put(publisherDataFromJsonString(paramString2));
    localBuilder.record(this.mUpsight);
  }
  
  public void recordGooglePlayPurchase(int paramInt1, String paramString1, double paramDouble1, double paramDouble2, String paramString2, int paramInt2, String paramString3, String paramString4, String paramString5)
  {
    UpsightPublisherData.Builder localBuilder = new UpsightPublisherData.Builder();
    localBuilder.put(publisherDataFromJsonString(paramString5));
    try
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("RESPONSE_CODE", paramInt2);
      localIntent.putExtra("INAPP_PURCHASE_DATA", paramString3);
      localIntent.putExtra("INAPP_DATA_SIGNATURE", paramString4);
      UpsightGooglePlayHelper.trackPurchase(this.mUpsight, paramInt1, paramString1, paramDouble1, paramDouble2, paramString2, localIntent, localBuilder.build());
      return;
    }
    catch (UpsightException localUpsightException)
    {
      for (;;)
      {
        Log.i("Upsight", "Failed to recordGooglePlayPurchase: " + localUpsightException.getMessage());
        localUpsightException.printStackTrace();
      }
    }
  }
  
  public void recordMilestoneEvent(String paramString1, String paramString2)
  {
    UpsightMilestoneEvent.Builder localBuilder = UpsightMilestoneEvent.createBuilder(paramString1);
    localBuilder.put(publisherDataFromJsonString(paramString2));
    localBuilder.record(this.mUpsight);
  }
  
  public void recordMonetizationEvent(double paramDouble1, String paramString1, String paramString2, double paramDouble2, String paramString3, int paramInt, String paramString4)
  {
    UpsightMonetizationEvent.Builder localBuilder = UpsightMonetizationEvent.createBuilder(Double.valueOf(paramDouble1), paramString1);
    localBuilder.put(publisherDataFromJsonString(paramString4));
    if (paramString2 != null) {
      localBuilder.setProduct(paramString2);
    }
    if (paramDouble2 >= 0.0D) {
      localBuilder.setPrice(Double.valueOf(paramDouble2));
    }
    if (paramString3 != null) {
      localBuilder.setResolution(paramString3);
    }
    if (paramInt > 0) {
      localBuilder.setQuantity(Integer.valueOf(paramInt));
    }
    localBuilder.record(this.mUpsight);
  }
  
  public void registerForPushNotifications()
  {
    Log.i("Upsight", "registering for push notifications");
    UpsightGooglePushServices.register(this.mUpsight, new UpsightGooglePushServices.OnRegisterListener()
    {
      public void onFailure(UpsightException paramAnonymousUpsightException)
      {
        Log.e("Upsight", "registration failed: " + paramAnonymousUpsightException);
      }
      
      public void onSuccess(String paramAnonymousString)
      {
        Log.e("Upsight", "registration succeeded");
      }
    });
  }
  
  public void removeBillboardFromMap(String paramString)
  {
    if (this.mBillboardMap.containsKey(paramString))
    {
      Log.i("Upsight", "Removing used billboard from internal map for scope: " + paramString);
      this.mBillboardMap.remove(paramString);
    }
  }
  
  void setHasActiveBillboard(boolean paramBoolean)
  {
    this.mHasActiveBillboard = paramBoolean;
  }
  
  public void setLocation(double paramDouble1, double paramDouble2, String paramString)
  {
    UpsightLocationTracker.Data localData = UpsightLocationTracker.Data.create(paramDouble1, paramDouble2);
    if ((paramString != null) && (paramString.length() > 0)) {
      localData.setTimeZone(paramString);
    }
    UpsightLocationTracker.track(this.mUpsight, localData);
  }
  
  public void setLoggerLevel(String paramString)
  {
    if (paramString.toLowerCase().equals("verbose"))
    {
      Log.i("Upsight", "enabling verbose logs");
      this.mUpsight.getLogger().setLogLevel("Upsight", EnumSet.allOf(UpsightLogger.Level.class));
    }
    for (;;)
    {
      return;
      EnumSet localEnumSet = EnumSet.of(UpsightLogger.Level.valueOf(paramString));
      this.mUpsight.getLogger().setLogLevel("Upsight", localEnumSet);
    }
  }
  
  public void setOptOutStatus(boolean paramBoolean)
  {
    UpsightOptOutStatus.set(this.mUpsight, paramBoolean);
  }
  
  public void setShouldSynchronizeManagedVariables(boolean paramBoolean)
  {
    this.mShouldSynchronizeManagedVariables = paramBoolean;
  }
  
  public void setUserAttributesBool(String paramString, boolean paramBoolean)
  {
    try
    {
      UpsightUserAttributes.put(this.mUpsight, paramString, Boolean.valueOf(paramBoolean));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void setUserAttributesFloat(String paramString, float paramFloat)
  {
    try
    {
      UpsightUserAttributes.put(this.mUpsight, paramString, Float.valueOf(paramFloat));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void setUserAttributesInt(String paramString, int paramInt)
  {
    try
    {
      UpsightUserAttributes.put(this.mUpsight, paramString, Integer.valueOf(paramInt));
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void setUserAttributesString(String paramString1, String paramString2)
  {
    try
    {
      UpsightUserAttributes.put(this.mUpsight, paramString1, paramString2);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void unregisterForPushNotifications()
  {
    Log.i("Upsight", "unregistering for push notifications");
    UpsightGooglePushServices.unregister(this.mUpsight, new UpsightGooglePushServices.OnUnregisterListener()
    {
      public void onFailure(UpsightException paramAnonymousUpsightException)
      {
        Log.e("Upsight", "unregistration failed: " + paramAnonymousUpsightException);
      }
      
      public void onSuccess()
      {
        Log.e("Upsight", "unregistration succeeded");
      }
    });
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/unity/UpsightPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */