package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class dv
{
  private SharedPreferences a;
  
  public dv(Context paramContext, String paramString)
  {
    this.a = paramContext.getSharedPreferences("com.crittercism." + paramString + ".usermetadata", 0);
    JSONObject localJSONObject;
    if (!this.a.contains("data")) {
      localJSONObject = new JSONObject();
    }
    try
    {
      localJSONObject.putOpt("username", "anonymous");
      a(localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  private void b(JSONObject paramJSONObject)
  {
    SharedPreferences.Editor localEditor = this.a.edit();
    localEditor.putString("data", paramJSONObject.toString());
    localEditor.commit();
  }
  
  public final JSONObject a()
  {
    String str = this.a.getString("data", "{}");
    try
    {
      localJSONObject = new JSONObject(str);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        JSONObject localJSONObject = new JSONObject();
      }
    }
  }
  
  public final void a(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = a();
    if (localJSONObject.length() == 0)
    {
      if (paramJSONObject.length() > 0)
      {
        b(paramJSONObject);
        a(true);
      }
      return;
    }
    Iterator localIterator = paramJSONObject.keys();
    int i = 0;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        Object localObject1 = paramJSONObject.opt(str1);
        Object localObject2 = localJSONObject.opt(str1);
        int j;
        if (localObject2 == null)
        {
          j = 1;
          label82:
          if (localObject2 != null)
          {
            if ((!(localObject1 instanceof JSONObject)) && (!(localObject1 instanceof JSONArray))) {
              break label158;
            }
            String str2 = localObject1.toString();
            if (localObject2.toString().equals(str2)) {
              break label152;
            }
            j = 1;
          }
        }
        for (;;)
        {
          if (j == 0) {
            break label178;
          }
          try
          {
            localJSONObject.put(str1, localObject1);
            i = 1;
          }
          catch (JSONException localJSONException) {}
          j = 0;
          break label82;
          label152:
          j = 0;
          continue;
          label158:
          if (!localObject2.equals(localObject1)) {
            j = 1;
          } else {
            j = 0;
          }
        }
      }
      else
      {
        label178:
        if (i == 0) {
          break;
        }
        b(localJSONObject);
        a(true);
        break;
      }
    }
  }
  
  public final void a(boolean paramBoolean)
  {
    this.a.edit().putBoolean("dirty", paramBoolean).commit();
  }
  
  public final boolean b()
  {
    return this.a.getBoolean("dirty", false);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */