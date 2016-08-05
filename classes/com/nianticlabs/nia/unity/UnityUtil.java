package com.nianticlabs.nia.unity;

import android.app.Activity;
import android.util.Log;
import java.lang.reflect.Field;

public class UnityUtil
{
  private static final String TAG = UnityUtil.class.getSimpleName();
  private static volatile Activity activity;
  
  public static Activity getActivity()
  {
    Activity localActivity;
    if (activity != null) {
      localActivity = activity;
    }
    for (;;)
    {
      return localActivity;
      try
      {
        activity = (Activity)Class.forName("com.unity3d.player.UnityPlayer").getField("currentActivity").get(null);
        localActivity = activity;
      }
      catch (Exception localException)
      {
        Log.e(TAG, "Unable to get currentActivity", localException);
        localActivity = null;
      }
    }
  }
  
  public static void init() {}
  
  private static native void nativeInit();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/unity/UnityUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */