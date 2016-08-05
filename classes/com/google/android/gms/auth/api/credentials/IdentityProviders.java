package com.google.android.gms.auth.api.credentials;

import android.accounts.Account;
import com.google.android.gms.common.internal.zzx;

public final class IdentityProviders
{
  public static final String FACEBOOK = "https://www.facebook.com";
  public static final String GOOGLE = "https://accounts.google.com";
  public static final String LINKEDIN = "https://www.linkedin.com";
  public static final String MICROSOFT = "https://login.live.com";
  public static final String PAYPAL = "https://www.paypal.com";
  public static final String TWITTER = "https://twitter.com";
  public static final String YAHOO = "https://login.yahoo.com";
  
  public static final String getIdentityProviderForAccount(Account paramAccount)
  {
    zzx.zzb(paramAccount, "account cannot be null");
    String str;
    if ("com.google".equals(paramAccount.type)) {
      str = "https://accounts.google.com";
    }
    for (;;)
    {
      return str;
      if ("com.facebook.auth.login".equals(paramAccount.type)) {
        str = "https://www.facebook.com";
      } else {
        str = null;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/IdentityProviders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */