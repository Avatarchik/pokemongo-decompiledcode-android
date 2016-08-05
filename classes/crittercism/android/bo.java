package crittercism.android;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

public final class bo
{
  public JSONArray a = new JSONArray();
  
  public bo(bs parambs)
  {
    Iterator localIterator = parambs.c().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = ((bq)localIterator.next()).a();
      if (localObject != null) {
        this.a.put(localObject);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */