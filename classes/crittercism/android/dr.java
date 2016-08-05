package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;
import java.util.UUID;

public final class dr
{
  private SharedPreferences a;
  private SharedPreferences b;
  private Context c;
  
  public dr(Context paramContext)
  {
    if (paramContext == null) {
      throw new NullPointerException("context was null");
    }
    this.c = paramContext;
    this.a = paramContext.getSharedPreferences("com.crittercism.usersettings", 0);
    this.b = paramContext.getSharedPreferences("com.crittercism.prefs", 0);
    if (this.a == null) {
      throw new NullPointerException("prefs were null");
    }
    if (this.b == null) {
      throw new NullPointerException("legacy prefs were null");
    }
  }
  
  private boolean a(String paramString)
  {
    SharedPreferences.Editor localEditor = this.a.edit();
    localEditor.putString("hashedDeviceID", paramString);
    return localEditor.commit();
  }
  
  private String b()
  {
    Object localObject = null;
    try
    {
      String str2 = Settings.Secure.getString(this.c.getContentResolver(), "android_id");
      if ((str2 != null) && (str2.length() > 0) && (!str2.equals("9774d56d682e549c")))
      {
        UUID localUUID = UUID.nameUUIDFromBytes(str2.getBytes("utf8"));
        if (localUUID != null)
        {
          String str3 = localUUID.toString();
          localObject = str3;
        }
      }
    }
    catch (ThreadDeath localThreadDeath2)
    {
      throw localThreadDeath2;
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        dx.a(localThrowable1);
      }
    }
    if ((localObject == null) || (((String)localObject).length() == 0)) {}
    try
    {
      String str1 = UUID.randomUUID().toString();
      localObject = str1;
    }
    catch (ThreadDeath localThreadDeath1)
    {
      throw localThreadDeath1;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        dx.a(localThrowable2);
      }
    }
    return (String)localObject;
  }
  
  public final String a()
  {
    String str = this.a.getString("hashedDeviceID", null);
    if (str == null)
    {
      str = this.b.getString("com.crittercism.prefs.did", null);
      if ((str != null) && (a(str)))
      {
        SharedPreferences.Editor localEditor = this.b.edit();
        localEditor.remove("com.crittercism.prefs.did");
        localEditor.commit();
      }
    }
    if (str == null)
    {
      str = b();
      a(str);
    }
    return str;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */