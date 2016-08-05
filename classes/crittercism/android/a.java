package crittercism.android;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a
{
  JSONObject a = new JSONObject();
  
  private a(au paramau, List paramList)
  {
    paramList.size();
    JSONArray localJSONArray1 = new JSONArray();
    JSONArray localJSONArray2 = new JSONArray();
    localJSONArray2.put(paramau.a());
    localJSONArray2.put(paramau.b());
    localJSONArray2.put(paramau.c());
    localJSONArray2.put("5.0.8");
    localJSONArray2.put(paramau.e());
    localJSONArray1.put(localJSONArray2);
    JSONArray localJSONArray3 = new JSONArray();
    localJSONArray3.put(ed.a.a());
    localJSONArray3.put(paramau.f());
    localJSONArray3.put(paramau.j());
    localJSONArray3.put(paramau.i());
    localJSONArray3.put(paramau.k());
    localJSONArray3.put(paramau.g());
    localJSONArray3.put(paramau.h());
    localJSONArray1.put(localJSONArray3);
    JSONArray localJSONArray4 = new JSONArray();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      localJSONArray4.put(((c)localIterator.next()).d());
    }
    localJSONArray1.put(localJSONArray4);
    this.a.put("d", localJSONArray1);
  }
  
  public static a a(au paramau, List paramList)
  {
    try
    {
      locala = new a(paramau, paramList);
      return locala;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        dx.b("Unable to generate APM request's JSON: " + localJSONException);
        a locala = null;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */