package crittercism.android;

import java.util.HashMap;
import java.util.Map;

public final class dp
{
  private static Map a;
  
  static
  {
    HashMap localHashMap = new HashMap();
    a = localHashMap;
    localHashMap.put("com.amazon.venezia", new do.a.a());
    a.put("com.android.vending", new do.b.a());
  }
  
  public static dn a(String paramString)
  {
    if ((paramString != null) && (a.containsKey(paramString))) {}
    for (dn localdn = (dn)a.get(paramString);; localdn = null) {
      return localdn;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */