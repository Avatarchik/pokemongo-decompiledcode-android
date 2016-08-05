package crittercism.android;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class cs
  implements cw
{
  private Map a = new HashMap();
  
  private JSONArray a()
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = this.a.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      JSONObject localJSONObject = new JSONObject((Map)localEntry.getKey());
      a locala = (a)localEntry.getValue();
      try
      {
        localJSONArray.put(new JSONObject().put("appLoads", localJSONObject).put("count", locala.b).put("current", locala.a));
      }
      catch (JSONException localJSONException) {}
    }
    return localJSONArray;
  }
  
  public final void a(OutputStream paramOutputStream)
  {
    paramOutputStream.write(a().toString().getBytes("UTF8"));
  }
  
  public final String toString()
  {
    Object localObject = null;
    try
    {
      String str = a().toString(4);
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
  
  public static class b
    implements cx
  {}
  
  static class a
  {
    boolean a = false;
    int b = 0;
    
    public a()
    {
      this((byte)0);
    }
    
    private a(byte paramByte) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/cs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */