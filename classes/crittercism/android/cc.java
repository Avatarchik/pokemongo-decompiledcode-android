package crittercism.android;

import android.os.Build.VERSION;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONArray;

public final class cc
  implements cb
{
  private static JSONArray a(InputStream paramInputStream)
  {
    JSONArray localJSONArray = new JSONArray();
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    int i = 0;
    for (;;)
    {
      try
      {
        String str = localBufferedReader.readLine();
        if (str != null)
        {
          localJSONArray.put(str);
          i++;
          if (i <= 200) {
            continue;
          }
        }
      }
      catch (IOException localIOException2)
      {
        localIOException2 = localIOException2;
        dx.a();
        try
        {
          localBufferedReader.close();
        }
        catch (IOException localIOException3)
        {
          dx.a();
        }
        continue;
      }
      finally {}
      try
      {
        localBufferedReader.close();
        return localJSONArray;
      }
      catch (IOException localIOException4)
      {
        dx.a();
      }
    }
    try
    {
      localBufferedReader.close();
      throw ((Throwable)localObject);
    }
    catch (IOException localIOException1)
    {
      for (;;)
      {
        dx.a();
      }
    }
  }
  
  public final JSONArray a()
  {
    Object localObject1 = new JSONArray();
    Process localProcess = null;
    if (Build.VERSION.SDK_INT < 10)
    {
      ((JSONArray)localObject1).put("Logcat data is not collected for Android APIs before 10 (Gingerbread)");
      ((JSONArray)localObject1).put("API level is " + Build.VERSION.SDK_INT + "(" + Build.VERSION.CODENAME + ")");
    }
    for (;;)
    {
      return (JSONArray)localObject1;
      try
      {
        String str2 = Integer.toString(100);
        String[] arrayOfString = new String[5];
        arrayOfString[0] = "logcat";
        arrayOfString[1] = "-t";
        arrayOfString[2] = str2;
        arrayOfString[3] = "-v";
        arrayOfString[4] = "time";
        localProcess = new ProcessBuilder(arrayOfString).redirectErrorStream(true).start();
        JSONArray localJSONArray = a(localProcess.getInputStream());
        localObject1 = localJSONArray;
        if (localProcess == null) {
          continue;
        }
        localProcess.destroy();
      }
      catch (Exception localException)
      {
        dx.b("Unable to collect logcat data", localException);
        ((JSONArray)localObject1).put("Unable to collect logcat data due to a(n) " + localException.getClass().getName());
        String str1 = localException.getMessage();
        if (str1 != null) {
          ((JSONArray)localObject1).put(str1);
        }
        if (localProcess == null) {
          continue;
        }
        localProcess.destroy();
      }
      finally
      {
        if (localProcess != null) {
          localProcess.destroy();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/cc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */