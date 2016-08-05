package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bj
  extends ci
{
  private String a = cg.a.a();
  private String b = ed.a.a();
  private a c;
  private String d;
  
  public bj(a parama, String paramString)
  {
    this.c = parama;
    this.d = paramString;
  }
  
  public final JSONArray a()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("event", Integer.valueOf(this.c.ordinal()));
    localHashMap.put("viewName", this.d);
    return new JSONArray().put(this.b).put(5).put(new JSONObject(localHashMap));
  }
  
  public final String e()
  {
    return this.a;
  }
  
  public static enum a
  {
    static
    {
      a[] arrayOfa = new a[2];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      c = arrayOfa;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */