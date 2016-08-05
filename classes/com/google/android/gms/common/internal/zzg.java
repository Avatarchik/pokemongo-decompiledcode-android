package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.internal.zzmq;

public final class zzg
{
  public static String zzc(Context paramContext, int paramInt, String paramString)
  {
    Resources localResources = paramContext.getResources();
    String str;
    switch (paramInt)
    {
    default: 
      str = localResources.getString(R.string.common_google_play_services_unknown_issue);
    }
    for (;;)
    {
      return str;
      if (zzmq.zzb(localResources))
      {
        int i3 = R.string.common_google_play_services_install_text_tablet;
        Object[] arrayOfObject8 = new Object[1];
        arrayOfObject8[0] = paramString;
        str = localResources.getString(i3, arrayOfObject8);
      }
      else
      {
        int i2 = R.string.common_google_play_services_install_text_phone;
        Object[] arrayOfObject7 = new Object[1];
        arrayOfObject7[0] = paramString;
        str = localResources.getString(i2, arrayOfObject7);
        continue;
        int i1 = R.string.common_google_play_services_enable_text;
        Object[] arrayOfObject6 = new Object[1];
        arrayOfObject6[0] = paramString;
        str = localResources.getString(i1, arrayOfObject6);
        continue;
        int n = R.string.common_google_play_services_updating_text;
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = paramString;
        str = localResources.getString(n, arrayOfObject5);
        continue;
        int m = R.string.common_google_play_services_update_text;
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = paramString;
        str = localResources.getString(m, arrayOfObject4);
        continue;
        int k = R.string.common_android_wear_update_text;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = paramString;
        str = localResources.getString(k, arrayOfObject3);
        continue;
        int j = R.string.common_google_play_services_unsupported_text;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = paramString;
        str = localResources.getString(j, arrayOfObject2);
        continue;
        str = localResources.getString(R.string.common_google_play_services_network_error_text);
        continue;
        str = localResources.getString(R.string.common_google_play_services_invalid_account_text);
        continue;
        int i = R.string.common_google_play_services_api_unavailable_text;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = paramString;
        str = localResources.getString(i, arrayOfObject1);
        continue;
        str = localResources.getString(R.string.common_google_play_services_sign_in_failed_text);
      }
    }
  }
  
  public static String zzd(Context paramContext, int paramInt, String paramString)
  {
    Resources localResources = paramContext.getResources();
    String str;
    switch (paramInt)
    {
    default: 
      str = localResources.getString(R.string.common_google_play_services_unknown_issue);
    }
    for (;;)
    {
      return str;
      if (zzmq.zzb(localResources))
      {
        int i3 = R.string.common_google_play_services_install_text_tablet;
        Object[] arrayOfObject8 = new Object[1];
        arrayOfObject8[0] = paramString;
        str = localResources.getString(i3, arrayOfObject8);
      }
      else
      {
        int i2 = R.string.common_google_play_services_install_text_phone;
        Object[] arrayOfObject7 = new Object[1];
        arrayOfObject7[0] = paramString;
        str = localResources.getString(i2, arrayOfObject7);
        continue;
        int i1 = R.string.common_google_play_services_updating_text;
        Object[] arrayOfObject6 = new Object[1];
        arrayOfObject6[0] = paramString;
        str = localResources.getString(i1, arrayOfObject6);
        continue;
        int n = R.string.common_google_play_services_update_text;
        Object[] arrayOfObject5 = new Object[1];
        arrayOfObject5[0] = paramString;
        str = localResources.getString(n, arrayOfObject5);
        continue;
        int m = R.string.common_android_wear_notification_needs_update_text;
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = paramString;
        str = localResources.getString(m, arrayOfObject4);
        continue;
        int k = R.string.common_google_play_services_enable_text;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = paramString;
        str = localResources.getString(k, arrayOfObject3);
        continue;
        int j = R.string.common_google_play_services_unsupported_text;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = paramString;
        str = localResources.getString(j, arrayOfObject2);
        continue;
        str = localResources.getString(R.string.common_google_play_services_network_error_text);
        continue;
        str = localResources.getString(R.string.common_google_play_services_invalid_account_text);
        continue;
        int i = R.string.common_google_play_services_api_unavailable_text;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = paramString;
        str = localResources.getString(i, arrayOfObject1);
        continue;
        str = localResources.getString(R.string.common_google_play_services_sign_in_failed_text);
      }
    }
  }
  
  public static final String zzg(Context paramContext, int paramInt)
  {
    String str = null;
    Resources localResources = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      Log.e("GoogleApiAvailability", "Unexpected error code " + paramInt);
    }
    for (;;)
    {
      return str;
      str = localResources.getString(R.string.common_google_play_services_install_title);
      continue;
      str = localResources.getString(R.string.common_google_play_services_enable_title);
      continue;
      str = localResources.getString(R.string.common_google_play_services_updating_title);
      continue;
      str = localResources.getString(R.string.common_google_play_services_update_title);
      continue;
      str = localResources.getString(R.string.common_android_wear_update_title);
      continue;
      Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
      str = localResources.getString(R.string.common_google_play_services_unsupported_title);
      continue;
      Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
      str = localResources.getString(R.string.common_google_play_services_network_error_title);
      continue;
      Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
      continue;
      Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
      continue;
      Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
      str = localResources.getString(R.string.common_google_play_services_invalid_account_title);
      continue;
      Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
      continue;
      Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
      continue;
      Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
      str = localResources.getString(R.string.common_google_play_services_sign_in_failed_title);
    }
  }
  
  public static String zzh(Context paramContext, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    String str;
    switch (paramInt)
    {
    default: 
      str = localResources.getString(17039370);
    }
    for (;;)
    {
      return str;
      str = localResources.getString(R.string.common_google_play_services_install_button);
      continue;
      str = localResources.getString(R.string.common_google_play_services_enable_button);
      continue;
      str = localResources.getString(R.string.common_google_play_services_update_button);
    }
  }
  
  public static final String zzi(Context paramContext, int paramInt)
  {
    String str = null;
    Resources localResources = paramContext.getResources();
    switch (paramInt)
    {
    default: 
      Log.e("GoogleApiAvailability", "Unexpected error code " + paramInt);
    }
    for (;;)
    {
      return str;
      str = localResources.getString(R.string.common_google_play_services_install_title);
      continue;
      str = localResources.getString(R.string.common_google_play_services_enable_title);
      continue;
      str = localResources.getString(R.string.common_google_play_services_updating_title);
      continue;
      str = localResources.getString(R.string.common_google_play_services_update_title);
      continue;
      str = localResources.getString(R.string.common_android_wear_update_title);
      continue;
      Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
      str = localResources.getString(R.string.common_google_play_services_unsupported_title);
      continue;
      Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
      str = localResources.getString(R.string.common_google_play_services_network_error_title);
      continue;
      Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
      continue;
      Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
      continue;
      Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
      str = localResources.getString(R.string.common_google_play_services_invalid_account_title);
      continue;
      Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
      continue;
      Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
      continue;
      Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
      str = localResources.getString(R.string.common_google_play_services_sign_in_failed_title);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */