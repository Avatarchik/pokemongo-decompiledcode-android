package com.upsight.android.unity;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractUpsightPlugin
{
  protected static final String MANAGER_NAME = "UpsightManager";
  protected static final String TAG = "Upsight";
  private Field mUnityPlayerActivityField;
  private Class<?> mUnityPlayerClass;
  private Method mUnitySendMessageMethod;
  
  public AbstractUpsightPlugin()
  {
    try
    {
      this.mUnityPlayerClass = Class.forName("com.unity3d.player.UnityPlayer");
      this.mUnityPlayerActivityField = this.mUnityPlayerClass.getField("currentActivity");
      Class localClass = this.mUnityPlayerClass;
      Class[] arrayOfClass = new Class[3];
      arrayOfClass[0] = String.class;
      arrayOfClass[1] = String.class;
      arrayOfClass[2] = String.class;
      this.mUnitySendMessageMethod = localClass.getMethod("UnitySendMessage", arrayOfClass);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        Log.i("Upsight", "could not find UnityPlayer class: " + localClassNotFoundException.getMessage());
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        Log.i("Upsight", "could not find currentActivity field: " + localNoSuchFieldException.getMessage());
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.i("Upsight", "unkown exception occurred locating getActivity(): " + localException.getMessage());
      }
    }
  }
  
  public void UnitySendMessage(final String paramString1, String paramString2)
  {
    final String str;
    if (paramString2 != null)
    {
      str = paramString2;
      if (this.mUnitySendMessageMethod == null) {
        break label159;
      }
    }
    for (;;)
    {
      try
      {
        Method localMethod = this.mUnitySendMessageMethod;
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = "UpsightManager";
        arrayOfObject[1] = paramString1;
        arrayOfObject[2] = str;
        localMethod.invoke(null, arrayOfObject);
        return;
        str = "";
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        Log.i("Upsight", "could not find UnitySendMessage method: " + localIllegalArgumentException.getMessage());
        continue;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        Log.i("Upsight", "could not find UnitySendMessage method: " + localIllegalAccessException.getMessage());
        continue;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        Log.i("Upsight", "could not find UnitySendMessage method: " + localInvocationTargetException.getMessage());
        continue;
      }
      label159:
      Log.i("Upsight", "UnitySendMessage: UpsightManager, " + paramString1 + ", " + str);
      runSafelyOnUiThread(new Runnable()
      {
        public void run()
        {
          Activity localActivity = AbstractUpsightPlugin.this.getActivity();
          if (localActivity != null) {
            Toast.makeText(localActivity, "UnitySendMessage:\n" + paramString1 + "\n" + str, 1).show();
          }
        }
      });
    }
  }
  
  protected Activity getActivity()
  {
    if (this.mUnityPlayerActivityField != null) {}
    for (;;)
    {
      try
      {
        localActivity = (Activity)this.mUnityPlayerActivityField.get(this.mUnityPlayerClass);
        if (localActivity == null) {
          Log.e("Upsight", "Something has gone terribly wrong. The Unity Activity does not exist. This could be due to a low memory situation");
        }
        return localActivity;
      }
      catch (Exception localException)
      {
        Log.i("Upsight", "error getting currentActivity: " + localException.getMessage());
      }
      Activity localActivity = null;
    }
  }
  
  protected void runSafelyOnUiThread(final Runnable paramRunnable)
  {
    Activity localActivity = getActivity();
    if (localActivity != null) {
      localActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          try
          {
            paramRunnable.run();
            return;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              Log.e("Upsight", "Exception running command on UI thread: " + localException.getMessage());
            }
          }
        }
      });
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/unity/AbstractUpsightPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */