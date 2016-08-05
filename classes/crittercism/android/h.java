package crittercism.android;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public final class h
{
  public boolean a = false;
  public boolean b = false;
  public boolean c = false;
  public int d = 10;
  
  public h(Context paramContext)
  {
    if (a(paramContext).exists()) {
      this.c = true;
    }
  }
  
  public h(JSONObject paramJSONObject)
  {
    if (!paramJSONObject.has("net")) {}
    for (;;)
    {
      return;
      try
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject("net");
        this.a = localJSONObject.optBoolean("enabled", false);
        this.b = localJSONObject.optBoolean("persist", false);
        this.c = localJSONObject.optBoolean("kill", false);
        this.d = localJSONObject.optInt("interval", 10);
      }
      catch (JSONException localJSONException) {}
    }
  }
  
  public static File a(Context paramContext)
  {
    return new File(paramContext.getFilesDir().getAbsolutePath() + "/.crittercism.apm.disabled.");
  }
  
  public static void b(Context paramContext)
  {
    try
    {
      a(paramContext).createNewFile();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        dx.b("Unable to kill APM: " + localIOException.getMessage());
      }
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (paramObject == null)
      {
        bool = false;
      }
      else if (!(paramObject instanceof h))
      {
        bool = false;
      }
      else
      {
        h localh = (h)paramObject;
        if (this.c != localh.c) {
          bool = false;
        } else if (this.a != localh.a) {
          bool = false;
        } else if (this.b != localh.b) {
          bool = false;
        } else if (this.d != localh.d) {
          bool = false;
        }
      }
    }
  }
  
  public final int hashCode()
  {
    int i = 1231;
    int j;
    int m;
    label31:
    int n;
    if (this.c)
    {
      j = i;
      int k = 31 * (j + 31);
      if (!this.a) {
        break label67;
      }
      m = i;
      n = 31 * (m + k);
      if (!this.b) {
        break label75;
      }
    }
    for (;;)
    {
      return 31 * (n + i) + this.d;
      j = 1237;
      break;
      label67:
      m = 1237;
      break label31;
      label75:
      i = 1237;
    }
  }
  
  public final String toString()
  {
    return "OptmzConfiguration [\nisSendTaskEnabled=" + this.a + "\n, shouldPersist=" + this.b + "\n, isKilled=" + this.c + "\n, statisticsSendInterval=" + this.d + "]";
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */