package com.upsight.android.googlepushservices.internal;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.upsight.android.Upsight;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightGooglePushServicesExtension;
import com.upsight.android.analytics.event.comm.UpsightCommClickEvent;
import com.upsight.android.analytics.event.comm.UpsightCommClickEvent.Builder;
import com.upsight.android.analytics.internal.session.ApplicationStatus;
import com.upsight.android.analytics.internal.session.ApplicationStatus.State;
import com.upsight.android.analytics.internal.session.SessionInitializerImpl;
import com.upsight.android.analytics.internal.session.SessionManager;
import com.upsight.android.googlepushservices.UpsightGooglePushServicesComponent;
import com.upsight.android.persistence.UpsightDataStore;
import javax.inject.Inject;
import rx.Observable;
import rx.observables.BlockingObservable;

public class PushClickIntentService
  extends IntentService
{
  private static final String BUNDLE_KEY_MESSAGE_INTENT = "messageIntent";
  private static final Integer NO_CMP_ID = Integer.valueOf(Integer.MIN_VALUE);
  private static final Integer NO_MSG_ID = Integer.valueOf(Integer.MIN_VALUE);
  private static final String SERVICE_NAME = "UpsightGcmPushClickIntentService";
  @Inject
  SessionManager mSessionManager;
  
  public PushClickIntentService()
  {
    super("UpsightGcmPushClickIntentService");
  }
  
  protected static Intent appendMessageIntentBundle(Intent paramIntent, Integer paramInteger1, Integer paramInteger2)
  {
    Bundle localBundle = new Bundle();
    if (paramInteger1 != null) {
      localBundle.putInt("campaign_id", paramInteger1.intValue());
    }
    if (paramInteger2 != null) {
      localBundle.putInt("message_id", paramInteger2.intValue());
    }
    paramIntent.putExtra("pushMessage", true);
    paramIntent.addFlags(268435456);
    return paramIntent.putExtra("session_extra", localBundle);
  }
  
  static Intent newIntent(Context paramContext, Intent paramIntent, Integer paramInteger1, Integer paramInteger2)
  {
    return new Intent(paramContext.getApplicationContext(), PushClickIntentService.class).putExtra("messageIntent", appendMessageIntentBundle(paramIntent, paramInteger1, paramInteger2));
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    UpsightContext localUpsightContext = Upsight.createContext(this);
    ((UpsightGooglePushServicesComponent)((UpsightGooglePushServicesExtension)localUpsightContext.getUpsightExtension("com.upsight.extension.googlepushservices")).getComponent()).inject(this);
    Intent localIntent = (Intent)paramIntent.getParcelableExtra("messageIntent");
    Bundle localBundle = localIntent.getBundleExtra("session_extra");
    SessionManager localSessionManager = this.mSessionManager;
    if (ApplicationStatus.State.BACKGROUND.name().equals(((ApplicationStatus)localUpsightContext.getDataStore().fetchObservable(ApplicationStatus.class).toBlocking().first()).getState().name())) {
      localSessionManager.startSession(SessionInitializerImpl.fromPush(localBundle));
    }
    UpsightCommClickEvent.Builder localBuilder = UpsightCommClickEvent.createBuilder(Integer.valueOf(localBundle.getInt("message_id", NO_MSG_ID.intValue())));
    Integer localInteger = Integer.valueOf(localBundle.getInt("campaign_id", NO_CMP_ID.intValue()));
    if (!localInteger.equals(NO_CMP_ID)) {
      localBuilder.setMsgCampaignId(localInteger);
    }
    localBuilder.record(Upsight.createContext(this));
    startActivity(localIntent);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/PushClickIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */