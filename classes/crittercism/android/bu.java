package crittercism.android;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class bu
  implements bv
{
  private Map a = new HashMap();
  
  public final bu a(bw parambw)
  {
    if (parambw.b() != null) {
      this.a.put(parambw.a(), parambw.b());
    }
    return this;
  }
  
  public final JSONObject a()
  {
    return new JSONObject(this.a);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */