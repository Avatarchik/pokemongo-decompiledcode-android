package com.upsight.android.analytics.internal;

import android.content.Context;
import com.upsight.android.internal.util.PreferencesHelper;

public final class EventSequenceId
{
  private static final long INITIAL_SEQUENCE_ID = 1L;
  private static final String PREFERENCES_KEY_SEQ_ID = "seq_id";
  
  /**
   * @deprecated
   */
  public static long getAndIncrement(Context paramContext)
  {
    try
    {
      long l = PreferencesHelper.getLong(paramContext, "seq_id", 1L);
      PreferencesHelper.putLong(paramContext, "seq_id", l + 1L);
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/EventSequenceId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */