package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public final class dq
{
  public static boolean a = false;
  
  public static Boolean a(Context paramContext)
  {
    return Boolean.valueOf(paramContext.getSharedPreferences("com.crittercism.usersettings", 0).getBoolean("crashedOnLastLoad", false));
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("com.crittercism.usersettings", 0).edit();
    localEditor.putBoolean("crashedOnLastLoad", paramBoolean);
    localEditor.commit();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */