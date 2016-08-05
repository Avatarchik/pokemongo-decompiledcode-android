package com.upsight.android.analytics.internal.provider;

import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.provider.UpsightOptOutStatus;
import com.upsight.android.internal.util.PreferencesHelper;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
final class OptOutStatus
  extends UpsightOptOutStatus
{
  private static final String PREFERENCES_KEY_OPT_OUT = "optOut";
  UpsightContext mUpsight;
  
  @Inject
  OptOutStatus(UpsightContext paramUpsightContext)
  {
    this.mUpsight = paramUpsightContext;
  }
  
  public boolean get()
  {
    return PreferencesHelper.getBoolean(this.mUpsight, "optOut", false);
  }
  
  public void set(boolean paramBoolean)
  {
    PreferencesHelper.putBoolean(this.mUpsight, "optOut", paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/OptOutStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */