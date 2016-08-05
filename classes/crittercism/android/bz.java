package crittercism.android;

import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;

public final class bz
  extends bq
{
  private bz(File paramFile)
  {
    super(paramFile);
  }
  
  public final Object a()
  {
    try
    {
      JSONArray localJSONArray1 = new JSONArray((String)super.a());
      localJSONArray2 = localJSONArray1;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        JSONArray localJSONArray2 = null;
      }
    }
    return localJSONArray2;
  }
  
  public static class a
    extends cj
  {
    public final bq a(File paramFile)
    {
      return new bz(paramFile, (byte)0);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */