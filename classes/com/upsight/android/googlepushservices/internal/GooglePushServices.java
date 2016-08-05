package com.upsight.android.googlepushservices.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightCoreComponent;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.event.comm.UpsightCommRegisterEvent;
import com.upsight.android.analytics.event.comm.UpsightCommRegisterEvent.Builder;
import com.upsight.android.analytics.event.comm.UpsightCommUnregisterEvent;
import com.upsight.android.analytics.event.comm.UpsightCommUnregisterEvent.Builder;
import com.upsight.android.googlepushservices.UpsightGooglePushServices.OnRegisterListener;
import com.upsight.android.googlepushservices.UpsightGooglePushServices.OnUnregisterListener;
import com.upsight.android.googlepushservices.UpsightGooglePushServicesApi;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.marketing.UpsightBillboard;
import com.upsight.android.marketing.UpsightBillboard.Handler;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.HandlerScheduler;

@Singleton
public class GooglePushServices
  implements UpsightGooglePushServicesApi
{
  private static final String KEY_GCM = "com.upsight.gcm";
  private static final String LOG_TAG = GooglePushServices.class.getName();
  private static final String PREFERENCES_NAME = "com.upsight.android.googleadvertisingid.internal.registration";
  private static final String PROPERTY_APP_VERSION = "gcmApplicationVersion";
  private static final String PROPERTY_REG_ID = "gcmRegistrationId";
  static final String PUSH_SCOPE = "com_upsight_push_scope";
  private final Scheduler mComputationScheduler;
  private UpsightLogger mLogger;
  private final Set<UpsightGooglePushServices.OnRegisterListener> mPendingRegisterListeners;
  private final Set<UpsightGooglePushServices.OnUnregisterListener> mPendingUnregisterListeners;
  private SharedPreferences mPrefs;
  private UpsightBillboard mPushBillboard;
  private boolean mRegistrationIsInProgress;
  private final Handler mUiThreadHandler;
  private boolean mUnregistrationIsInProgress;
  private UpsightContext mUpsight;
  
  @Inject
  GooglePushServices(UpsightContext paramUpsightContext)
  {
    this.mUpsight = paramUpsightContext;
    this.mLogger = paramUpsightContext.getLogger();
    if (Looper.myLooper() != null) {}
    for (this.mUiThreadHandler = new Handler(Looper.myLooper());; this.mUiThreadHandler = new Handler(Looper.getMainLooper()))
    {
      this.mComputationScheduler = paramUpsightContext.getCoreComponent().subscribeOnScheduler();
      this.mRegistrationIsInProgress = false;
      this.mUnregistrationIsInProgress = false;
      this.mPendingRegisterListeners = new HashSet();
      this.mPendingUnregisterListeners = new HashSet();
      this.mPrefs = this.mUpsight.getSharedPreferences("com.upsight.android.googleadvertisingid.internal.registration", 0);
      return;
    }
  }
  
  private int getAppVersion()
  {
    try
    {
      int i = this.mUpsight.getPackageManager().getPackageInfo(this.mUpsight.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException("Could not get package name: " + localNameNotFoundException);
    }
  }
  
  private String getRegistrationId()
  {
    String str = this.mPrefs.getString("gcmRegistrationId", null);
    if (TextUtils.isEmpty(str)) {
      str = null;
    }
    for (;;)
    {
      return str;
      if (this.mPrefs.getInt("gcmApplicationVersion", Integer.MIN_VALUE) != getAppVersion()) {
        str = null;
      }
    }
  }
  
  private boolean hasPlayServices()
  {
    boolean bool = false;
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.mUpsight);
    if (i != 0)
    {
      UpsightLogger localUpsightLogger = this.mLogger;
      String str = LOG_TAG;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[bool] = GooglePlayServicesUtil.getErrorString(i);
      localUpsightLogger.e(str, "Google play service is not available: ", arrayOfObject);
    }
    for (;;)
    {
      return bool;
      bool = true;
    }
  }
  
  private boolean isRegistered()
  {
    if (getRegistrationId() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void registerInBackground(final String paramString)
  {
    this.mRegistrationIsInProgress = true;
    Observable.create(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super String> paramAnonymousSubscriber)
      {
        try
        {
          GoogleCloudMessaging localGoogleCloudMessaging = GoogleCloudMessaging.getInstance(GooglePushServices.this.mUpsight);
          String[] arrayOfString = new String[1];
          arrayOfString[0] = paramString;
          paramAnonymousSubscriber.onNext(localGoogleCloudMessaging.register(arrayOfString));
          paramAnonymousSubscriber.onCompleted();
          return;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            paramAnonymousSubscriber.onError(localIOException);
          }
        }
      }
    }).subscribeOn(this.mComputationScheduler).observeOn(HandlerScheduler.from(this.mUiThreadHandler)).subscribe(new Observer()
    {
      public void onCompleted() {}
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        synchronized (GooglePushServices.this)
        {
          HashSet localHashSet = new HashSet(GooglePushServices.this.mPendingRegisterListeners);
          GooglePushServices.this.mPendingRegisterListeners.clear();
          GooglePushServices.access$202(GooglePushServices.this, false);
          Iterator localIterator = localHashSet.iterator();
          if (localIterator.hasNext()) {
            ((UpsightGooglePushServices.OnRegisterListener)localIterator.next()).onFailure(new UpsightException(paramAnonymousThrowable));
          }
        }
      }
      
      public void onNext(String paramAnonymousString)
      {
        synchronized (GooglePushServices.this)
        {
          GooglePushServices.this.storeRegistrationId(paramAnonymousString);
          UpsightCommRegisterEvent.createBuilder().setToken(paramAnonymousString).record(GooglePushServices.this.mUpsight);
          HashSet localHashSet = new HashSet(GooglePushServices.this.mPendingRegisterListeners);
          GooglePushServices.this.mPendingRegisterListeners.clear();
          GooglePushServices.access$202(GooglePushServices.this, false);
          Iterator localIterator = localHashSet.iterator();
          if (localIterator.hasNext()) {
            ((UpsightGooglePushServices.OnRegisterListener)localIterator.next()).onSuccess(paramAnonymousString);
          }
        }
      }
    });
  }
  
  private void storeRegistrationId(String paramString)
  {
    int i = getAppVersion();
    SharedPreferences.Editor localEditor = this.mPrefs.edit();
    localEditor.putString("gcmRegistrationId", paramString);
    localEditor.putInt("gcmApplicationVersion", i);
    localEditor.apply();
  }
  
  private void unregisterInBackground()
  {
    this.mUnregistrationIsInProgress = true;
    Observable.create(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super String> paramAnonymousSubscriber)
      {
        try
        {
          GoogleCloudMessaging.getInstance(GooglePushServices.this.mUpsight).unregister();
          paramAnonymousSubscriber.onCompleted();
          return;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            paramAnonymousSubscriber.onError(localIOException);
          }
        }
      }
    }).subscribeOn(this.mComputationScheduler).observeOn(HandlerScheduler.from(this.mUiThreadHandler)).subscribe(new Observer()
    {
      public void onCompleted()
      {
        synchronized (GooglePushServices.this)
        {
          UpsightCommUnregisterEvent.createBuilder().record(GooglePushServices.this.mUpsight);
          HashSet localHashSet = new HashSet(GooglePushServices.this.mPendingUnregisterListeners);
          GooglePushServices.this.mPendingUnregisterListeners.clear();
          GooglePushServices.access$502(GooglePushServices.this, false);
          Iterator localIterator = localHashSet.iterator();
          if (localIterator.hasNext()) {
            ((UpsightGooglePushServices.OnUnregisterListener)localIterator.next()).onSuccess();
          }
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        synchronized (GooglePushServices.this)
        {
          HashSet localHashSet = new HashSet(GooglePushServices.this.mPendingUnregisterListeners);
          GooglePushServices.this.mPendingUnregisterListeners.clear();
          GooglePushServices.access$502(GooglePushServices.this, false);
          Iterator localIterator = localHashSet.iterator();
          if (localIterator.hasNext()) {
            ((UpsightGooglePushServices.OnUnregisterListener)localIterator.next()).onFailure(new UpsightException(paramAnonymousThrowable));
          }
        }
      }
      
      public void onNext(String paramAnonymousString) {}
    });
  }
  
  /**
   * @deprecated
   */
  public UpsightBillboard createPushBillboard(UpsightContext paramUpsightContext, UpsightBillboard.Handler paramHandler)
    throws IllegalArgumentException, IllegalStateException
  {
    try
    {
      if (this.mPushBillboard != null)
      {
        this.mPushBillboard.destroy();
        this.mPushBillboard = null;
      }
      this.mPushBillboard = UpsightBillboard.create(paramUpsightContext, "com_upsight_push_scope", paramHandler);
      UpsightBillboard localUpsightBillboard = this.mPushBillboard;
      return localUpsightBillboard;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void register(UpsightGooglePushServices.OnRegisterListener paramOnRegisterListener)
  {
    if (paramOnRegisterListener == null) {
      try
      {
        throw new IllegalArgumentException("Listener could not be null");
      }
      finally {}
    }
    if (!hasPlayServices()) {
      paramOnRegisterListener.onFailure(new UpsightException("Google Play Services are not available", new Object[0]));
    }
    for (;;)
    {
      return;
      if (this.mUnregistrationIsInProgress)
      {
        paramOnRegisterListener.onFailure(new UpsightException("Unregistration is in progress, try later", new Object[0]));
      }
      else
      {
        String str1 = null;
        Object localObject1 = null;
        try
        {
          Bundle localBundle = this.mUpsight.getPackageManager().getApplicationInfo(this.mUpsight.getPackageName(), 128).metaData;
          if (localBundle != null)
          {
            String str2 = localBundle.getString("com.upsight.gcm");
            if (!TextUtils.isEmpty(str2))
            {
              str1 = str2.substring(0, str2.lastIndexOf('.'));
              String str3 = str2.substring(1 + str2.lastIndexOf('.'));
              localObject1 = str3;
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            UpsightLogger localUpsightLogger = this.mLogger;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = localNameNotFoundException;
            localUpsightLogger.e("Upsight", "Unexpected error: Package name missing!?", arrayOfObject);
          }
          this.mPendingRegisterListeners.add(paramOnRegisterListener);
        }
        if ((!this.mUpsight.getPackageName().equals(str1)) || (TextUtils.isEmpty((CharSequence)localObject1)))
        {
          this.mLogger.e(LOG_TAG, "Registration aborted, wrong or no value for com.upsight.gcm was defined", new Object[0]);
          if (!this.mUpsight.getPackageName().equals(str1)) {
            this.mLogger.e(LOG_TAG, "Check that the package name of your application is specified correctly", new Object[0]);
          }
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            this.mLogger.e(LOG_TAG, "Check that your GCM sender id is specified correctly", new Object[0]);
          }
          paramOnRegisterListener.onFailure(new UpsightException("GCM properties must be set in the Android Manifest with <meta-data android:name=\"com.upsight.gcm\" android:value=\"" + this.mUpsight.getPackageName() + ".GCM_SENDER_ID\" />", new Object[0]));
        }
        else if (!this.mRegistrationIsInProgress)
        {
          registerInBackground((String)localObject1);
        }
      }
    }
  }
  
  /**
   * @deprecated
   */
  public void unregister(UpsightGooglePushServices.OnUnregisterListener paramOnUnregisterListener)
  {
    if (paramOnUnregisterListener == null) {
      try
      {
        throw new IllegalArgumentException("Listener could not be null");
      }
      finally {}
    }
    if (!isRegistered()) {
      paramOnUnregisterListener.onFailure(new UpsightException("Application is not registered to pushes yet", new Object[0]));
    }
    for (;;)
    {
      return;
      if (this.mRegistrationIsInProgress)
      {
        paramOnUnregisterListener.onFailure(new UpsightException("Registration is in progress, try later", new Object[0]));
      }
      else
      {
        this.mPendingUnregisterListeners.add(paramOnUnregisterListener);
        if (!this.mUnregistrationIsInProgress) {
          unregisterInBackground();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/GooglePushServices.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */