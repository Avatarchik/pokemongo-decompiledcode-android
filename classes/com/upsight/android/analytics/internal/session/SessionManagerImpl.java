package com.upsight.android.analytics.internal.session;

import android.content.Context;
import android.content.Intent;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.configuration.UpsightConfiguration;
import com.upsight.android.analytics.event.session.UpsightSessionPauseEvent;
import com.upsight.android.analytics.event.session.UpsightSessionPauseEvent.Builder;
import com.upsight.android.analytics.event.session.UpsightSessionResumeEvent;
import com.upsight.android.analytics.event.session.UpsightSessionResumeEvent.Builder;
import com.upsight.android.analytics.event.session.UpsightSessionStartEvent;
import com.upsight.android.analytics.event.session.UpsightSessionStartEvent.Builder;
import com.upsight.android.analytics.internal.DispatcherService;
import com.upsight.android.analytics.provider.UpsightLocationTracker;
import com.upsight.android.analytics.session.UpsightSessionCallbacks;
import com.upsight.android.internal.util.PreferencesHelper;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.annotation.Created;
import com.upsight.android.persistence.annotation.Updated;
import java.io.IOException;

public class SessionManagerImpl
  implements SessionManager
{
  private static final String KEY_SESSION = "com.upsight.session_callbacks";
  private static final String LOG_TAG = SessionManagerImpl.class.getSimpleName();
  private static final String PREFERENCES_KEY_JSON_CONFIG = "session_manager_json_config";
  private static final String PREFERENCES_KEY_LAST_KNOWN_SESSION_TIME = "last_known_session_time";
  private Context mAppContext;
  private final Clock mClock;
  private ConfigParser mConfigParser;
  private Config mCurrentConfig;
  private long mLastKnownSessionTs;
  private UpsightLogger mLogger;
  private Session mSession;
  private long mStopRequestedTs;
  private UpsightContext mUpsight;
  private UpsightSessionCallbacks mUpsightSessionCallbacks;
  
  SessionManagerImpl(Context paramContext, UpsightContext paramUpsightContext, UpsightDataStore paramUpsightDataStore, UpsightLogger paramUpsightLogger, ConfigParser paramConfigParser, Clock paramClock)
  {
    this.mLogger = paramUpsightLogger;
    this.mConfigParser = paramConfigParser;
    this.mAppContext = paramContext;
    this.mUpsight = paramUpsightContext;
    this.mClock = paramClock;
    this.mLastKnownSessionTs = PreferencesHelper.getLong(paramContext, "last_known_session_time", 0L);
    loadSessionHook();
    paramUpsightDataStore.subscribe(this);
    applyConfiguration(fetchCurrentConfig());
  }
  
  private boolean applyConfiguration(String paramString)
  {
    boolean bool = true;
    Config localConfig;
    try
    {
      localConfig = this.mConfigParser.parseConfig(paramString);
      if ((localConfig == null) || (!localConfig.isValid()))
      {
        this.mLogger.w(LOG_TAG, "New config is invalid", new Object[0]);
        bool = false;
      }
      else if (localConfig.equals(this.mCurrentConfig))
      {
        this.mLogger.w(LOG_TAG, "New config ignored because it is equal to current config", new Object[0]);
      }
    }
    catch (IOException localIOException)
    {
      UpsightLogger localUpsightLogger = this.mLogger;
      String str = LOG_TAG;
      Object[] arrayOfObject = new Object[bool];
      arrayOfObject[0] = localIOException;
      localUpsightLogger.e(str, "Failed to apply new config", arrayOfObject);
      bool = false;
    }
    PreferencesHelper.putString(this.mAppContext, "session_manager_json_config", paramString);
    this.mCurrentConfig = localConfig;
    return bool;
  }
  
  private String fetchCurrentConfig()
  {
    return PreferencesHelper.getString(this.mAppContext, "session_manager_json_config", "{\"session_gap\": 120}");
  }
  
  private boolean isExpired()
  {
    if (((this.mStopRequestedTs != 0L) && (this.mClock.currentTimeSeconds() - this.mStopRequestedTs > this.mCurrentConfig.timeToNewSession)) || ((this.mSession == null) && (this.mClock.currentTimeSeconds() - this.mLastKnownSessionTs > this.mCurrentConfig.timeToNewSession))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private boolean isSessionNull()
  {
    if (this.mSession == null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  /* Error */
  private void loadSessionHook()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 61	com/upsight/android/analytics/internal/session/SessionManagerImpl:mUpsight	Lcom/upsight/android/UpsightContext;
    //   4: invokevirtual 159	com/upsight/android/UpsightContext:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: aload_0
    //   8: getfield 61	com/upsight/android/analytics/internal/session/SessionManagerImpl:mUpsight	Lcom/upsight/android/UpsightContext;
    //   11: invokevirtual 162	com/upsight/android/UpsightContext:getPackageName	()Ljava/lang/String;
    //   14: sipush 128
    //   17: invokevirtual 168	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   20: getfield 174	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   23: astore 4
    //   25: aload 4
    //   27: ifnull +132 -> 159
    //   30: aload 4
    //   32: ldc 13
    //   34: invokevirtual 179	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   37: ifeq +122 -> 159
    //   40: aload 4
    //   42: ldc 13
    //   44: invokevirtual 182	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   47: astore 5
    //   49: aload 5
    //   51: invokestatic 186	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   54: astore 21
    //   56: ldc -68
    //   58: aload 21
    //   60: invokevirtual 192	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   63: ifne +97 -> 160
    //   66: iconst_2
    //   67: anewarray 4	java/lang/Object
    //   70: astore 22
    //   72: aload 22
    //   74: iconst_0
    //   75: aload 21
    //   77: invokevirtual 195	java/lang/Class:getName	()Ljava/lang/String;
    //   80: aastore
    //   81: aload 22
    //   83: iconst_1
    //   84: ldc -68
    //   86: invokevirtual 195	java/lang/Class:getName	()Ljava/lang/String;
    //   89: aastore
    //   90: new 197	java/lang/IllegalStateException
    //   93: dup
    //   94: ldc -57
    //   96: aload 22
    //   98: invokestatic 205	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   101: invokespecial 208	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   104: athrow
    //   105: astore 16
    //   107: aload_0
    //   108: getfield 55	com/upsight/android/analytics/internal/session/SessionManagerImpl:mLogger	Lcom/upsight/android/logger/UpsightLogger;
    //   111: astore 17
    //   113: iconst_1
    //   114: anewarray 4	java/lang/Object
    //   117: astore 18
    //   119: aload 18
    //   121: iconst_0
    //   122: aload 5
    //   124: aastore
    //   125: ldc -46
    //   127: aload 18
    //   129: invokestatic 205	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   132: astore 19
    //   134: iconst_1
    //   135: anewarray 4	java/lang/Object
    //   138: astore 20
    //   140: aload 20
    //   142: iconst_0
    //   143: aload 16
    //   145: aastore
    //   146: aload 17
    //   148: ldc -44
    //   150: aload 19
    //   152: aload 20
    //   154: invokeinterface 120 4 0
    //   159: return
    //   160: aload_0
    //   161: aload 21
    //   163: invokevirtual 216	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   166: checkcast 188	com/upsight/android/analytics/session/UpsightSessionCallbacks
    //   169: putfield 218	com/upsight/android/analytics/internal/session/SessionManagerImpl:mUpsightSessionCallbacks	Lcom/upsight/android/analytics/session/UpsightSessionCallbacks;
    //   172: goto -13 -> 159
    //   175: astore 11
    //   177: aload_0
    //   178: getfield 55	com/upsight/android/analytics/internal/session/SessionManagerImpl:mLogger	Lcom/upsight/android/logger/UpsightLogger;
    //   181: astore 12
    //   183: iconst_1
    //   184: anewarray 4	java/lang/Object
    //   187: astore 13
    //   189: aload 13
    //   191: iconst_0
    //   192: aload 5
    //   194: aastore
    //   195: ldc -36
    //   197: aload 13
    //   199: invokestatic 205	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   202: astore 14
    //   204: iconst_1
    //   205: anewarray 4	java/lang/Object
    //   208: astore 15
    //   210: aload 15
    //   212: iconst_0
    //   213: aload 11
    //   215: aastore
    //   216: aload 12
    //   218: ldc -44
    //   220: aload 14
    //   222: aload 15
    //   224: invokeinterface 120 4 0
    //   229: goto -70 -> 159
    //   232: astore_1
    //   233: aload_0
    //   234: getfield 55	com/upsight/android/analytics/internal/session/SessionManagerImpl:mLogger	Lcom/upsight/android/logger/UpsightLogger;
    //   237: astore_2
    //   238: iconst_1
    //   239: anewarray 4	java/lang/Object
    //   242: astore_3
    //   243: aload_3
    //   244: iconst_0
    //   245: aload_1
    //   246: aastore
    //   247: aload_2
    //   248: ldc -44
    //   250: ldc -34
    //   252: aload_3
    //   253: invokeinterface 120 4 0
    //   258: goto -99 -> 159
    //   261: astore 6
    //   263: aload_0
    //   264: getfield 55	com/upsight/android/analytics/internal/session/SessionManagerImpl:mLogger	Lcom/upsight/android/logger/UpsightLogger;
    //   267: astore 7
    //   269: iconst_1
    //   270: anewarray 4	java/lang/Object
    //   273: astore 8
    //   275: aload 8
    //   277: iconst_0
    //   278: aload 5
    //   280: aastore
    //   281: ldc -32
    //   283: aload 8
    //   285: invokestatic 205	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   288: astore 9
    //   290: iconst_1
    //   291: anewarray 4	java/lang/Object
    //   294: astore 10
    //   296: aload 10
    //   298: iconst_0
    //   299: aload 6
    //   301: aastore
    //   302: aload 7
    //   304: ldc -44
    //   306: aload 9
    //   308: aload 10
    //   310: invokeinterface 120 4 0
    //   315: goto -156 -> 159
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	this	SessionManagerImpl
    //   232	14	1	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   237	11	2	localUpsightLogger1	UpsightLogger
    //   242	11	3	arrayOfObject1	Object[]
    //   23	18	4	localBundle	android.os.Bundle
    //   47	232	5	str1	String
    //   261	39	6	localInstantiationException	InstantiationException
    //   267	36	7	localUpsightLogger2	UpsightLogger
    //   273	11	8	arrayOfObject2	Object[]
    //   288	19	9	str2	String
    //   294	15	10	arrayOfObject3	Object[]
    //   175	39	11	localIllegalAccessException	IllegalAccessException
    //   181	36	12	localUpsightLogger3	UpsightLogger
    //   187	11	13	arrayOfObject4	Object[]
    //   202	19	14	str3	String
    //   208	15	15	arrayOfObject5	Object[]
    //   105	39	16	localClassNotFoundException	ClassNotFoundException
    //   111	36	17	localUpsightLogger4	UpsightLogger
    //   117	11	18	arrayOfObject6	Object[]
    //   132	19	19	str4	String
    //   138	15	20	arrayOfObject7	Object[]
    //   54	108	21	localClass	Class
    //   70	27	22	arrayOfObject8	Object[]
    // Exception table:
    //   from	to	target	type
    //   49	105	105	java/lang/ClassNotFoundException
    //   160	172	105	java/lang/ClassNotFoundException
    //   49	105	175	java/lang/IllegalAccessException
    //   160	172	175	java/lang/IllegalAccessException
    //   0	49	232	android/content/pm/PackageManager$NameNotFoundException
    //   49	105	232	android/content/pm/PackageManager$NameNotFoundException
    //   107	159	232	android/content/pm/PackageManager$NameNotFoundException
    //   160	172	232	android/content/pm/PackageManager$NameNotFoundException
    //   177	229	232	android/content/pm/PackageManager$NameNotFoundException
    //   263	315	232	android/content/pm/PackageManager$NameNotFoundException
    //   49	105	261	java/lang/InstantiationException
    //   160	172	261	java/lang/InstantiationException
  }
  
  private Session startSession(boolean paramBoolean1, boolean paramBoolean2, SessionInitializer paramSessionInitializer)
  {
    this.mUpsight.startService(new Intent(this.mUpsight, DispatcherService.class));
    Integer localInteger1 = null;
    Integer localInteger2 = null;
    int i;
    int j;
    if (paramSessionInitializer != null)
    {
      i = 1;
      if (this.mStopRequestedTs == 0L) {
        break label175;
      }
      j = 1;
      label46:
      this.mStopRequestedTs = 0L;
      if ((i == 0) && (!paramBoolean2)) {
        break label181;
      }
      UpsightLocationTracker.purge(this.mUpsight);
      if (this.mUpsightSessionCallbacks != null) {
        this.mUpsightSessionCallbacks.onStart(this.mUpsight);
      }
      if (i != 0)
      {
        localInteger1 = paramSessionInitializer.getCampaignID();
        localInteger2 = paramSessionInitializer.getMessageID();
      }
      this.mSession = SessionImpl.incrementAndCreate(this.mAppContext, this.mClock, localInteger1, localInteger2);
      UpsightSessionStartEvent.createBuilder().record(this.mUpsight);
    }
    for (;;)
    {
      this.mLastKnownSessionTs = this.mClock.currentTimeSeconds();
      PreferencesHelper.putLong(this.mAppContext, "last_known_session_time", this.mLastKnownSessionTs);
      return this.mSession;
      i = 0;
      break;
      label175:
      j = 0;
      break label46;
      label181:
      if (paramBoolean1)
      {
        UpsightLocationTracker.purge(this.mUpsight);
        if (this.mUpsightSessionCallbacks != null) {
          this.mUpsightSessionCallbacks.onStart(this.mUpsight);
        }
        this.mSession = SessionImpl.create(this.mAppContext, this.mClock, null, null);
        UpsightSessionResumeEvent.createBuilder().record(this.mUpsight);
      }
      else if (j != 0)
      {
        UpsightSessionResumeEvent.createBuilder().record(this.mUpsight);
      }
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public Session getCurrentSession()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 292	com/upsight/android/analytics/internal/session/SessionManagerImpl:isSessionNull	()Z
    //   6: istore_2
    //   7: aload_0
    //   8: invokespecial 294	com/upsight/android/analytics/internal/session/SessionManagerImpl:isExpired	()Z
    //   11: istore_3
    //   12: iload_2
    //   13: ifne +7 -> 20
    //   16: iload_3
    //   17: ifeq +21 -> 38
    //   20: aload_0
    //   21: iload_2
    //   22: iload_3
    //   23: aconst_null
    //   24: invokespecial 296	com/upsight/android/analytics/internal/session/SessionManagerImpl:startSession	(ZZLcom/upsight/android/analytics/internal/session/SessionInitializer;)Lcom/upsight/android/analytics/internal/session/Session;
    //   27: astore 4
    //   29: aload 4
    //   31: astore 5
    //   33: aload_0
    //   34: monitorexit
    //   35: aload 5
    //   37: areturn
    //   38: aload_0
    //   39: getfield 144	com/upsight/android/analytics/internal/session/SessionManagerImpl:mSession	Lcom/upsight/android/analytics/internal/session/Session;
    //   42: astore 5
    //   44: goto -11 -> 33
    //   47: astore_1
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	SessionManagerImpl
    //   47	4	1	localObject	Object
    //   6	16	2	bool1	boolean
    //   11	12	3	bool2	boolean
    //   27	3	4	localSession1	Session
    //   31	12	5	localSession2	Session
    // Exception table:
    //   from	to	target	type
    //   2	29	47	finally
    //   38	44	47	finally
  }
  
  /**
   * @deprecated
   */
  @Updated
  public void onApplicationStatusUpdated(ApplicationStatus paramApplicationStatus)
  {
    try
    {
      if (ApplicationStatus.State.BACKGROUND.equals(paramApplicationStatus.getState()))
      {
        this.mLastKnownSessionTs = this.mClock.currentTimeSeconds();
        PreferencesHelper.putLong(this.mAppContext, "last_known_session_time", this.mLastKnownSessionTs);
        UpsightSessionPauseEvent.createBuilder().record(this.mUpsight);
      }
      return;
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
  @Created
  public void onConfigurationCreated(UpsightConfiguration paramUpsightConfiguration)
  {
    try
    {
      if ("upsight.configuration.session_manager".equals(paramUpsightConfiguration.getScope())) {
        applyConfiguration(paramUpsightConfiguration.getConfiguration());
      }
      return;
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
  public Session startSession(SessionInitializer paramSessionInitializer)
  {
    try
    {
      Session localSession = startSession(isSessionNull(), isExpired(), paramSessionInitializer);
      return localSession;
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
  public void stopSession()
  {
    try
    {
      Session localSession = getCurrentSession();
      this.mStopRequestedTs = this.mClock.currentTimeSeconds();
      localSession.updateDuration(this.mAppContext, this.mStopRequestedTs);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static final class Config
  {
    public final long timeToNewSession;
    
    Config(long paramLong)
    {
      this.timeToNewSession = paramLong;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (this == paramObject) {}
      for (;;)
      {
        return bool;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          bool = false;
        } else if (((Config)paramObject).timeToNewSession != this.timeToNewSession) {
          bool = false;
        }
      }
    }
    
    public boolean isValid()
    {
      if (this.timeToNewSession > 0L) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/SessionManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */