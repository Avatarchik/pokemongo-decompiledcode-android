package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

public final class bh
{
  public boolean a = false;
  public int b = 10;
  public int c = 3600000;
  public JSONObject d = new JSONObject();
  
  bh() {}
  
  public bh(JSONObject paramJSONObject)
  {
    this.a = paramJSONObject.optBoolean("enabled", false);
    this.b = paramJSONObject.optInt("interval", 10);
    this.c = paramJSONObject.optInt("defaultTimeout", 3600000);
    this.d = paramJSONObject.optJSONObject("transactions");
    if (this.d == null) {
      this.d = new JSONObject();
    }
  }
  
  public static bh a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("com.crittercism.txn.config", 0);
    bh localbh = new bh();
    localbh.a = localSharedPreferences.getBoolean("enabled", false);
    localbh.b = localSharedPreferences.getInt("interval", 10);
    localbh.c = localSharedPreferences.getInt("defaultTimeout", 3600000);
    String str = localSharedPreferences.getString("transactions", null);
    localbh.d = new JSONObject();
    if (str != null) {}
    try
    {
      localbh.d = new JSONObject(str);
      return localbh;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public final long a(String paramString)
  {
    JSONObject localJSONObject = this.d.optJSONObject(paramString);
    if (localJSONObject != null) {}
    for (long l = localJSONObject.optLong("timeout", this.c);; l = this.c) {
      return l;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */