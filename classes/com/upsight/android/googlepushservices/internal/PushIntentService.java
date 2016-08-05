package com.upsight.android.googlepushservices.internal;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.upsight.android.Upsight;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightGooglePushServicesExtension;
import com.upsight.android.analytics.event.content.UpsightContentUnrenderedEvent;
import com.upsight.android.analytics.event.content.UpsightContentUnrenderedEvent.Builder;
import com.upsight.android.googlepushservices.UpsightGooglePushServicesComponent;
import com.upsight.android.logger.UpsightLogger;
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;

public final class PushIntentService
  extends IntentService
{
  private static final String ACTION_ACTIVITY = "activity";
  private static final String ACTION_CONTENT_UNIT = "content_id";
  private static final String ACTION_PLACEMENT = "placement";
  private static final String CONTENT_UNRENDERED_CONTENT_PROVIDER_KEY_NAME = "name";
  private static final String CONTENT_UNRENDERED_CONTENT_PROVIDER_KEY_PARAMETERS = "parameters";
  private static final String CONTENT_UNRENDERED_CONTENT_PROVIDER_PARAMETERS_KEY_CONTENT_ID = "content_id";
  private static final Integer INVALID_MSG_ID = Integer.valueOf(0);
  private static final String LOG_TAG = PushIntentService.class.getSimpleName();
  private static final String SERVICE_NAME = "UpsightGcmPushIntentService";
  private static final String URI_HOST = "com.playhaven.android";
  private static final String URI_SCHEME = "playhaven";
  @Inject
  GoogleCloudMessaging mGcm;
  
  public PushIntentService()
  {
    super("UpsightGcmPushIntentService");
  }
  
  private void interpretPushEvent(Bundle paramBundle)
  {
    Uri localUri = null;
    String str1 = paramBundle.getString(PushParams.uri.name());
    if (!TextUtils.isEmpty(str1)) {
      localUri = Uri.parse(str1);
    }
    UpsightContext localUpsightContext;
    UpsightLogger localUpsightLogger;
    PushIds localPushIds;
    Object localObject;
    if (localUri != null)
    {
      localUpsightContext = Upsight.createContext(this);
      localUpsightLogger = localUpsightContext.getLogger();
      localPushIds = parsePushIds(localUri, paramBundle, localUpsightLogger);
      localObject = null;
      switch (checkUri(localUpsightLogger, localUri))
      {
      }
    }
    for (;;)
    {
      if (localObject != null)
      {
        String str2 = paramBundle.getString(PushParams.title.name());
        String str3 = paramBundle.getString(PushParams.text.name());
        showNotification((Intent)localObject, localPushIds.campaignId, localPushIds.messageId, str2, str3);
      }
      return;
      localObject = new Intent("android.intent.action.VIEW", localUri);
      continue;
      localObject = getPackageManager().getLaunchIntentForPackage(getPackageName());
      continue;
      try
      {
        Intent localIntent = new Intent(this, Class.forName(localUri.getQueryParameter("activity")));
        localObject = localIntent;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        localUpsightLogger.e(LOG_TAG, localClassNotFoundException, "Could not parse class name", new Object[0]);
      }
      continue;
      JSONObject localJSONObject1 = new JSONObject();
      try
      {
        localJSONObject1.put("name", "upsight");
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("content_id", localPushIds.contentId);
        localJSONObject1.put("parameters", localJSONObject2);
        UpsightContentUnrenderedEvent.Builder localBuilder = UpsightContentUnrenderedEvent.createBuilder(localJSONObject1).setScope("com_upsight_push_scope");
        if (localPushIds.campaignId != null) {
          localBuilder.setCampaignId(localPushIds.campaignId);
        }
        localBuilder.record(localUpsightContext);
        localObject = getPackageManager().getLaunchIntentForPackage(getPackageName());
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          localUpsightLogger.e(LOG_TAG, localJSONException, "Could not construct \"content_provider\" bundle in \"upsight.content.unrendered\"", new Object[0]);
        }
      }
    }
  }
  
  private static Integer parseAsInt(String paramString, Integer paramInteger, UpsightLogger paramUpsightLogger)
  {
    Object localObject = paramInteger;
    if ((!TextUtils.isEmpty(paramString)) && (TextUtils.isDigitsOnly(paramString))) {}
    try
    {
      Integer localInteger = Integer.valueOf(Integer.parseInt(paramString));
      localObject = localInteger;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        if (paramInteger == null)
        {
          String str2 = LOG_TAG;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = paramString;
          paramUpsightLogger.e(str2, localNumberFormatException, String.format("Could not parse %s. Setting to null.", arrayOfObject2), new Object[0]);
        }
        else
        {
          String str1 = LOG_TAG;
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = paramString;
          arrayOfObject1[1] = paramInteger;
          paramUpsightLogger.e(str1, localNumberFormatException, String.format("Could not parse %s. Setting to %d.", arrayOfObject1), new Object[0]);
        }
      }
    }
    return (Integer)localObject;
  }
  
  private void showNotification(Intent paramIntent, Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2)
  {
    PendingIntent localPendingIntent = PendingIntent.getService(this, 0, PushClickIntentService.newIntent(this, paramIntent, paramInteger1, paramInteger2), 268435456);
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this).setAutoCancel(true).setSmallIcon(getApplicationInfo().icon).setContentTitle(paramString1).setContentText(paramString2).setContentIntent(localPendingIntent).setStyle(new NotificationCompat.BigTextStyle().bigText(paramString2));
    ((NotificationManager)getSystemService("notification")).notify(paramInteger2.intValue(), localBuilder.build());
  }
  
  public UriTypes checkUri(UpsightLogger paramUpsightLogger, Uri paramUri)
  {
    String str1 = paramUri.getHost();
    String str2 = paramUri.getScheme();
    UriTypes localUriTypes;
    if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2)))
    {
      String str3 = LOG_TAG;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = paramUri;
      paramUpsightLogger.e(str3, String.format("Invalid URI, host or scheme is null or empty: %s.", arrayOfObject1), new Object[0]);
      localUriTypes = UriTypes.INVALID;
    }
    for (;;)
    {
      return localUriTypes;
      if (("playhaven".equals(str2)) && ("com.playhaven.android".equals(str1)))
      {
        if (paramUri.getQueryParameter("activity") != null) {
          localUriTypes = UriTypes.ACTIVITY;
        } else if (paramUri.getQueryParameter("placement") != null) {
          localUriTypes = UriTypes.PLACEMENT;
        } else if (paramUri.getQueryParameter("content_id") != null) {
          localUriTypes = UriTypes.PLACEMENT;
        } else {
          localUriTypes = UriTypes.DEFAULT;
        }
      }
      else
      {
        try
        {
          Intent localIntent = new Intent().setData(paramUri);
          ResolveInfo localResolveInfo = getPackageManager().resolveActivity(localIntent, 0);
          if (localResolveInfo != null) {
            break label232;
          }
          localUriTypes = UriTypes.INVALID;
        }
        catch (Exception localException)
        {
          String str4 = LOG_TAG;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = paramUri;
          paramUpsightLogger.e(str4, String.format("Nothing registered for %s", arrayOfObject2), new Object[0]);
          localUriTypes = UriTypes.INVALID;
        }
        continue;
        label232:
        localUriTypes = UriTypes.CUSTOM;
      }
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    ((UpsightGooglePushServicesComponent)((UpsightGooglePushServicesExtension)Upsight.createContext(this).getUpsightExtension("com.upsight.extension.googlepushservices")).getComponent()).inject(this);
    if ("gcm".equals(this.mGcm.getMessageType(paramIntent)))
    {
      Bundle localBundle = paramIntent.getExtras();
      if ((!localBundle.isEmpty()) && (!TextUtils.isEmpty(localBundle.getString(PushParams.message_id.name())))) {
        interpretPushEvent(localBundle);
      }
    }
    PushBroadcastReceiver.completeWakefulIntent(paramIntent);
  }
  
  PushIds parsePushIds(Uri paramUri, Bundle paramBundle, UpsightLogger paramUpsightLogger)
  {
    return new PushIds(parseAsInt(paramBundle.getString(PushParams.message_id.name()), INVALID_MSG_ID, paramUpsightLogger), parseAsInt(paramBundle.getString(PushParams.msg_campaign_id.name()), null, paramUpsightLogger), parseAsInt(paramUri.getQueryParameter(PushParams.content_id.name()), null, paramUpsightLogger), null);
  }
  
  class PushIds
  {
    final Integer campaignId;
    final Integer contentId;
    final Integer messageId;
    
    private PushIds(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
    {
      this.messageId = paramInteger1;
      this.campaignId = paramInteger2;
      this.contentId = paramInteger3;
    }
  }
  
  public static enum PushParams
  {
    static
    {
      content_id = new PushParams("content_id", 2);
      title = new PushParams("title", 3);
      text = new PushParams("text", 4);
      uri = new PushParams("uri", 5);
      PushParams[] arrayOfPushParams = new PushParams[6];
      arrayOfPushParams[0] = message_id;
      arrayOfPushParams[1] = msg_campaign_id;
      arrayOfPushParams[2] = content_id;
      arrayOfPushParams[3] = title;
      arrayOfPushParams[4] = text;
      arrayOfPushParams[5] = uri;
      $VALUES = arrayOfPushParams;
    }
    
    private PushParams() {}
  }
  
  public static enum UriTypes
  {
    static
    {
      CUSTOM = new UriTypes("CUSTOM", 1);
      INVALID = new UriTypes("INVALID", 2);
      PLACEMENT = new UriTypes("PLACEMENT", 3);
      ACTIVITY = new UriTypes("ACTIVITY", 4);
      UriTypes[] arrayOfUriTypes = new UriTypes[5];
      arrayOfUriTypes[0] = DEFAULT;
      arrayOfUriTypes[1] = CUSTOM;
      arrayOfUriTypes[2] = INVALID;
      arrayOfUriTypes[3] = PLACEMENT;
      arrayOfUriTypes[4] = ACTIVITY;
      $VALUES = arrayOfUriTypes;
    }
    
    private UriTypes() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/PushIntentService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */