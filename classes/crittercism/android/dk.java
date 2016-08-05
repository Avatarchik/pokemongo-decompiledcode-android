package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONException;
import org.json.JSONObject;

public final class dk
  extends di
{
  private ax a;
  private final boolean b;
  private Context c;
  
  public dk(Context paramContext, ax paramax, boolean paramBoolean)
  {
    this.a = paramax;
    this.b = paramBoolean;
    this.c = paramContext;
  }
  
  public final void a()
  {
    new StringBuilder("Setting opt out status to ").append(this.b).append(".  This will take effect in the next user session.");
    dx.b();
    boolean bool = this.b;
    ax localax = this.a;
    String str1 = cq.i.a();
    String str2 = cq.i.b();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("optOutStatus", bool).put("optOutStatusSet", true);
      localax.a(str1, str2, localJSONObject.toString());
      if (this.b)
      {
        SharedPreferences.Editor localEditor = this.c.getSharedPreferences("com.crittercism.optmz.config", 0).edit();
        localEditor.clear();
        localEditor.commit();
        h.b(this.c);
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */