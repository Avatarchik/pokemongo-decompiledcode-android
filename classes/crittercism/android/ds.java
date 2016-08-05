package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;

public final class ds
{
  private boolean a;
  private boolean b;
  
  public ds(boolean paramBoolean)
  {
    this.a = paramBoolean;
    this.b = true;
  }
  
  /**
   * @deprecated
   */
  public final boolean a()
  {
    try
    {
      boolean bool = this.a;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static class a
  {
    public static ds a(ax paramax)
    {
      Object localObject = null;
      String str = paramax.a(cq.i.a(), cq.i.b());
      if (str != null) {}
      for (;;)
      {
        try
        {
          localJSONObject = new JSONObject(str);
          localObject = localJSONObject;
        }
        catch (JSONException localJSONException)
        {
          dx.b();
          continue;
          boolean bool2 = paramax.c(cq.l.a(), cq.l.b());
          continue;
          boolean bool1 = false;
          continue;
        }
        if (localObject != null)
        {
          bool1 = ((JSONObject)localObject).optBoolean("optOutStatusSet", false);
          if (bool1)
          {
            bool2 = ((JSONObject)localObject).optBoolean("optOutStatus", false);
            return new ds(bool2);
          }
        }
        JSONObject localJSONObject = null;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */