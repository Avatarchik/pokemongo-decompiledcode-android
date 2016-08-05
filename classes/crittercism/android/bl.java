package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class bl
  extends ci
{
  private String a = cg.a.a();
  private String b = ed.a.a();
  private a c;
  
  public bl(a parama)
  {
    this.c = parama;
  }
  
  public final JSONArray a()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("event", this.c.a());
    return new JSONArray().put(this.b).put(3).put(new JSONObject(localHashMap));
  }
  
  public final String e()
  {
    return this.a;
  }
  
  public static enum a
  {
    private String c;
    
    static
    {
      a[] arrayOfa = new a[2];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      d = arrayOfa;
    }
    
    private a(String paramString1)
    {
      this.c = paramString1;
    }
    
    public final String a()
    {
      return this.c;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */