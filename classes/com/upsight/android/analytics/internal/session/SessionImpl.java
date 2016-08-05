package com.upsight.android.analytics.internal.session;

import android.content.Context;
import com.upsight.android.internal.util.PreferencesHelper;

public class SessionImpl
  implements Session
{
  private static final String PREFERENCES_KEY_CURRENT_SESSION_DURATION = "current_session_duration";
  private static final String PREFERENCES_KEY_PAST_SESSION_TIME = "past_session_time";
  private static final String PREFERENCES_KEY_SESSION_NUM = "session_num";
  private static final String PREFERENCES_KEY_SESSION_START_TS = "session_start_ts";
  private static final int SESSION_NUM_BASE_OFFSET;
  private final Integer mCampaignId;
  private final long mInitialSessionStartTs;
  private long mLastKnownSessionTs;
  private final Integer mMessageId;
  private final long mPastSessionTime;
  private final int mSessionNum;
  
  private SessionImpl(Integer paramInteger1, Integer paramInteger2, int paramInt, long paramLong1, long paramLong2)
  {
    this.mCampaignId = paramInteger1;
    this.mMessageId = paramInteger2;
    this.mSessionNum = paramInt;
    this.mInitialSessionStartTs = paramLong1;
    this.mPastSessionTime = paramLong2;
  }
  
  public static Session create(Context paramContext, Clock paramClock, Integer paramInteger1, Integer paramInteger2)
  {
    int i = PreferencesHelper.getInt(paramContext, "session_num", Integer.MIN_VALUE);
    long l = PreferencesHelper.getLong(paramContext, "session_start_ts", Long.MIN_VALUE);
    if ((i == Integer.MIN_VALUE) || (l == Long.MIN_VALUE)) {}
    for (Object localObject = incrementAndCreate(paramContext, paramClock, paramInteger1, paramInteger2);; localObject = new SessionImpl(paramInteger1, paramInteger2, i, l, PreferencesHelper.getLong(paramContext, "past_session_time", 0L))) {
      return (Session)localObject;
    }
  }
  
  public static Session incrementAndCreate(Context paramContext, Clock paramClock, Integer paramInteger1, Integer paramInteger2)
  {
    int i = 1 + PreferencesHelper.getInt(paramContext, "session_num", 0);
    long l1 = paramClock.currentTimeSeconds();
    PreferencesHelper.putInt(paramContext, "session_num", i);
    PreferencesHelper.putLong(paramContext, "session_start_ts", l1);
    long l2 = PreferencesHelper.getLong(paramContext, "current_session_duration", 0L) + PreferencesHelper.getLong(paramContext, "past_session_time", 0L);
    PreferencesHelper.putLong(paramContext, "current_session_duration", 0L);
    PreferencesHelper.putLong(paramContext, "past_session_time", l2);
    return new SessionImpl(paramInteger1, paramInteger2, i, l1, l2);
  }
  
  /**
   * @deprecated
   */
  public Integer getCampaignID()
  {
    try
    {
      Integer localInteger = this.mCampaignId;
      return localInteger;
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
  public Integer getMessageID()
  {
    try
    {
      Integer localInteger = this.mMessageId;
      return localInteger;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long getPreviousTos()
  {
    return this.mPastSessionTime;
  }
  
  /**
   * @deprecated
   */
  public int getSessionNumber()
  {
    try
    {
      int i = this.mSessionNum;
      return i;
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
  public long getTimeStamp()
  {
    try
    {
      long l = this.mInitialSessionStartTs;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void updateDuration(Context paramContext, long paramLong)
  {
    PreferencesHelper.putLong(paramContext, "current_session_duration", paramLong - this.mInitialSessionStartTs);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/SessionImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */