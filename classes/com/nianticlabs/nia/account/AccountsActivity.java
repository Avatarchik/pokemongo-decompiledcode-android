package com.nianticlabs.nia.account;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class AccountsActivity
  extends Activity
{
  static final String AUTH_TOKEN_SCOPE_PREFIX = "audience:server:client_id:";
  static String EXTRA_OAUTH_CLIENT_ID = "oauthClientId";
  private static final int REQUEST_CHOOSE_ACCOUNT = 1;
  private static final int REQUEST_GET_AUTH = 2;
  private static String TAG = "AccountsActivity";
  private NianticAccountManager accountManager;
  private boolean authInProgress = false;
  
  private void askUserToRecover(final UserRecoverableAuthException paramUserRecoverableAuthException)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        AccountsActivity.this.startActivityForResult(paramUserRecoverableAuthException.getIntent(), 2);
      }
    });
  }
  
  private void failAuth(NianticAccountManager.Status paramStatus, String paramString)
  {
    Log.e(TAG, paramString);
    this.accountManager.setAuthToken(paramStatus, "");
    finish();
  }
  
  private void getAuth(final String paramString)
  {
    new AsyncTask()
    {
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        AccountsActivity.getAuthTokenBlocking(AccountsActivity.this, paramString);
        return null;
      }
    }.execute(new Void[0]);
  }
  
  private void getAuthOrAccount()
  {
    String str = this.accountManager.getAccountName();
    if (str != null) {
      getAuth(str);
    }
    for (;;)
    {
      return;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "com.google";
      startActivityForResult(AccountPicker.newChooseAccountIntent(null, null, arrayOfString, false, null, null, null, null), 1);
    }
  }
  
  private static void getAuthTokenBlocking(AccountsActivity paramAccountsActivity, String paramString)
  {
    try
    {
      Log.d(TAG, "Authenticating with account: " + paramString);
      String str1 = paramAccountsActivity.getIntent().getStringExtra(EXTRA_OAUTH_CLIENT_ID);
      Log.i(TAG, "Authenticating with client id: " + str1);
      String str2 = "audience:server:client_id:" + str1;
      Log.i(TAG, "Authenticating with scope: " + str2);
      String str3 = GoogleAuthUtil.getToken(paramAccountsActivity, paramString, str2);
      paramAccountsActivity.accountManager.setAuthToken(NianticAccountManager.Status.OK, str3);
      paramAccountsActivity.postFinish();
      return;
    }
    catch (UserRecoverableAuthException localUserRecoverableAuthException)
    {
      for (;;)
      {
        paramAccountsActivity.askUserToRecover(localUserRecoverableAuthException);
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Log.e(TAG, "Unable to get authToken at this time.", localIOException);
        paramAccountsActivity.accountManager.setAuthToken(NianticAccountManager.Status.NON_RECOVERABLE_ERROR, "");
        paramAccountsActivity.postFinish();
      }
    }
    catch (GoogleAuthException localGoogleAuthException)
    {
      for (;;)
      {
        Log.e(TAG, "User cannot be authenticated.", localGoogleAuthException);
        paramAccountsActivity.accountManager.setAuthToken(NianticAccountManager.Status.NON_RECOVERABLE_ERROR, "");
        paramAccountsActivity.postFinish();
      }
    }
  }
  
  private void postFinish()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        AccountsActivity.this.finish();
      }
    });
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    String str1 = "Unexpected requestCode " + paramInt1;
    switch (paramInt1)
    {
    default: 
      Log.e(TAG, str1);
    }
    for (;;)
    {
      return;
      if (paramInt2 == 0)
      {
        failAuth(NianticAccountManager.Status.USER_CANCELED_LOGIN, "User decided to cancel account selection.");
      }
      else if (paramIntent == null)
      {
        failAuth(NianticAccountManager.Status.NON_RECOVERABLE_ERROR, "Attempt to choose null account, resultCode: " + paramInt2);
      }
      else
      {
        String str2 = paramIntent.getStringExtra("authAccount");
        if ((str2 == null) || ("".equals(str2)))
        {
          failAuth(NianticAccountManager.Status.NON_RECOVERABLE_ERROR, "Attempt to choose unnamed account, resultCode: " + paramInt2);
        }
        else
        {
          this.accountManager.setAccountName(str2);
          getAuth(str2);
          continue;
          getAuthOrAccount();
        }
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(getResources().getIdentifier("accounts_activity", "layout", getPackageName()));
    this.accountManager = null;
    WeakReference localWeakReference = NianticAccountManager.getInstance();
    if (localWeakReference != null) {
      this.accountManager = ((NianticAccountManager)localWeakReference.get());
    }
    if (this.accountManager == null) {
      throw new RuntimeException("Unable to locate NianticAccountManager");
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i != 0)
    {
      Log.e(TAG, "Google Play Services not available, need to do something. Error code: " + i);
      this.accountManager.setAuthToken(NianticAccountManager.Status.NON_RECOVERABLE_ERROR, "");
      finish();
    }
    for (;;)
    {
      return;
      if (!this.authInProgress)
      {
        this.authInProgress = true;
        getAuthOrAccount();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/account/AccountsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */