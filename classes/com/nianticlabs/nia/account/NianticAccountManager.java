package com.nianticlabs.nia.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.nianticlabs.nia.contextservice.ContextService;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class NianticAccountManager
  extends ContextService
{
  private static final String KEY_ACCOUNT_NAME = "accountName";
  private static final String TAG = "NianticAccountManager";
  private static WeakReference<NianticAccountManager> instance = null;
  private final SharedPreferences prefs;
  
  public NianticAccountManager(Context paramContext, long paramLong)
  {
    super(paramContext, paramLong);
    instance = new WeakReference(this);
    this.prefs = paramContext.getSharedPreferences(paramContext.getPackageName() + ".PREFS", 0);
  }
  
  /**
   * @deprecated
   */
  private void clearAccount()
  {
    try
    {
      this.prefs.edit().remove("accountName").apply();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static WeakReference<NianticAccountManager> getInstance()
  {
    return instance;
  }
  
  private native void nativeAuthTokenCallback(int paramInt, String paramString);
  
  public void getAccount(final String paramString)
  {
    int i = 0;
    int j = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.context);
    if (j != 0)
    {
      Log.e("NianticAccountManager", "Google Play Services not available. Error code: " + j);
      setAuthToken(Status.NON_RECOVERABLE_ERROR, "");
    }
    for (;;)
    {
      return;
      try
      {
        String str1 = getAccountName();
        if (str1 != null)
        {
          Log.d("NianticAccountManager", "Authenticating with account: " + str1);
          String str2 = "audience:server:client_id:" + paramString;
          String str3 = GoogleAuthUtil.getToken(this.context, str1, str2);
          setAuthToken(Status.OK, str3);
        }
        while (i != 0)
        {
          runOnUiThread(new Runnable()
          {
            public void run()
            {
              Intent localIntent = new Intent(NianticAccountManager.this.context, AccountsActivity.class);
              localIntent.putExtra(AccountsActivity.EXTRA_OAUTH_CLIENT_ID, paramString);
              NianticAccountManager.this.context.startActivity(localIntent);
            }
          });
          break;
          i = 1;
        }
      }
      catch (UserRecoverableAuthException localUserRecoverableAuthException)
      {
        for (;;)
        {
          i = 1;
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          Log.e("NianticAccountManager", "Unable to get authToken at this time.", localIOException);
          setAuthToken(Status.NON_RECOVERABLE_ERROR, "");
        }
      }
      catch (GoogleAuthException localGoogleAuthException)
      {
        for (;;)
        {
          Log.e("NianticAccountManager", "User cannot be authenticated.", localGoogleAuthException);
          setAuthToken(Status.NON_RECOVERABLE_ERROR, "");
        }
      }
    }
  }
  
  /**
   * @deprecated
   */
  public String getAccountName()
  {
    try
    {
      String str = this.prefs.getString("accountName", null);
      return str;
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
  public void setAccountName(String paramString)
  {
    try
    {
      this.prefs.edit().putString("accountName", paramString).apply();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public void setAuthToken(Status paramStatus, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 167	com/nianticlabs/nia/account/NianticAccountManager:callbackLock	Ljava/lang/Object;
    //   6: astore 4
    //   8: aload 4
    //   10: monitorenter
    //   11: aload_0
    //   12: aload_1
    //   13: invokevirtual 171	com/nianticlabs/nia/account/NianticAccountManager$Status:ordinal	()I
    //   16: aload_2
    //   17: invokespecial 173	com/nianticlabs/nia/account/NianticAccountManager:nativeAuthTokenCallback	(ILjava/lang/String;)V
    //   20: aload 4
    //   22: monitorexit
    //   23: aload_0
    //   24: monitorexit
    //   25: return
    //   26: astore 5
    //   28: aload 4
    //   30: monitorexit
    //   31: aload 5
    //   33: athrow
    //   34: astore_3
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_3
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	this	NianticAccountManager
    //   0	39	1	paramStatus	Status
    //   0	39	2	paramString	String
    //   34	4	3	localObject1	Object
    //   26	6	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   11	23	26	finally
    //   28	31	26	finally
    //   2	11	34	finally
    //   31	34	34	finally
  }
  
  public static enum Status
  {
    public final int id;
    
    static
    {
      OK = new Status("OK", 1, 1);
      NON_RECOVERABLE_ERROR = new Status("NON_RECOVERABLE_ERROR", 2, 2);
      SIGNING_OUT = new Status("SIGNING_OUT", 3, 3);
      USER_CANCELED_LOGIN = new Status("USER_CANCELED_LOGIN", 4, 4);
      Status[] arrayOfStatus = new Status[5];
      arrayOfStatus[0] = UNDEFINED;
      arrayOfStatus[1] = OK;
      arrayOfStatus[2] = NON_RECOVERABLE_ERROR;
      arrayOfStatus[3] = SIGNING_OUT;
      arrayOfStatus[4] = USER_CANCELED_LOGIN;
      $VALUES = arrayOfStatus;
    }
    
    private Status(int paramInt)
    {
      this.id = paramInt;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/account/NianticAccountManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */