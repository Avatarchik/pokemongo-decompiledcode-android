package crittercism.android;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cu
  implements cw
{
  public Map a = new HashMap();
  
  public cu(au paramau)
  {
    this.a.put("app_id", paramau.a());
    this.a.put("hashed_device_id", paramau.c());
    this.a.put("library_version", "5.0.8");
  }
  
  public final cu a(String paramString1, String paramString2)
  {
    this.a.put(paramString1, paramString2);
    return this;
  }
  
  public final cu a(String paramString, JSONArray paramJSONArray)
  {
    this.a.put(paramString, paramJSONArray);
    return this;
  }
  
  public final void a(OutputStream paramOutputStream)
  {
    dx.b();
    paramOutputStream.write(new JSONObject(this.a).toString().getBytes("UTF8"));
  }
  
  public final String toString()
  {
    Object localObject = null;
    try
    {
      String str = new JSONObject(this.a).toString(4);
      localObject = str;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        dx.a();
      }
    }
    return (String)localObject;
  }
  
  public static class a
    implements cx
  {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/cu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */