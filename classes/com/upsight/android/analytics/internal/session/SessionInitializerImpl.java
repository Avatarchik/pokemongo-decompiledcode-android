package com.upsight.android.analytics.internal.session;

import android.os.Bundle;

public class SessionInitializerImpl
  implements SessionInitializer
{
  private static final int NO_CMP_ID = Integer.MIN_VALUE;
  private static final int NO_MSG_ID = Integer.MIN_VALUE;
  private static final String SESSION_CAMPAIGN_ID = "campaign_id";
  private static final String SESSION_MESSAGE_ID = "message_id";
  private int mCampaignId;
  private int mMessageId;
  
  private SessionInitializerImpl(int paramInt1, int paramInt2)
  {
    this.mCampaignId = paramInt1;
    this.mMessageId = paramInt2;
  }
  
  public static SessionInitializer fromPush(Bundle paramBundle)
  {
    return new SessionInitializerImpl(paramBundle.getInt("campaign_id", Integer.MIN_VALUE), paramBundle.getInt("message_id", Integer.MIN_VALUE));
  }
  
  public Integer getCampaignID()
  {
    if (this.mCampaignId == Integer.MIN_VALUE) {}
    for (Integer localInteger = null;; localInteger = Integer.valueOf(this.mCampaignId)) {
      return localInteger;
    }
  }
  
  public Integer getMessageID()
  {
    if (this.mMessageId == Integer.MIN_VALUE) {}
    for (Integer localInteger = null;; localInteger = Integer.valueOf(this.mMessageId)) {
      return localInteger;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/SessionInitializerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */