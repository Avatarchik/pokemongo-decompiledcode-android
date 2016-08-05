package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public final class ct
  extends da
{
  private au a;
  private Context b;
  private String c;
  private JSONObject d;
  private JSONObject e;
  private boolean f;
  
  public ct(bs parambs1, bs parambs2, String paramString, Context paramContext, au paramau)
  {
    super(parambs1, parambs2);
    this.c = paramString;
    this.b = paramContext;
    this.a = paramau;
  }
  
  public final void a(boolean paramBoolean, int paramInt, JSONObject paramJSONObject)
  {
    super.a(paramBoolean, paramInt, paramJSONObject);
    if (paramJSONObject != null)
    {
      if (!paramJSONObject.optBoolean("internalExceptionReporting", false)) {
        break label427;
      }
      dx.a = dx.a.b;
      i.d();
    }
    for (;;)
    {
      dt localdt = this.a.m();
      JSONObject localJSONObject;
      if (localdt != null)
      {
        localJSONObject = paramJSONObject.optJSONObject("rateMyApp");
        if (localJSONObject == null) {
          localdt.a(false);
        }
      }
      else if (paramJSONObject.optInt("needPkg", 0) != 1) {}
      try
      {
        new dj(new cu(this.a).a("device_name", this.a.i()).a("pkg", this.b.getPackageName()), new dc(new db(this.c, "/android_v2/update_package_name").a()), null).run();
        this.f = true;
        this.d = paramJSONObject.optJSONObject("apm");
        if (this.d != null)
        {
          h localh = new h(this.d);
          localContext = this.b;
          if (localh.c)
          {
            h.b(localContext);
            localEditor1 = localContext.getSharedPreferences("com.crittercism.optmz.config", 0).edit();
            if (!localh.b) {
              break label669;
            }
            localEditor1.putBoolean("enabled", localh.a);
            localEditor1.putBoolean("kill", localh.c);
            localEditor1.putBoolean("persist", localh.b);
            localEditor1.putInt("interval", localh.d);
            localEditor1.commit();
            az.A().a(localh);
          }
        }
        else
        {
          this.e = paramJSONObject.optJSONObject("txnConfig");
          if (this.e != null)
          {
            bh localbh = new bh(this.e);
            SharedPreferences.Editor localEditor2 = this.b.getSharedPreferences("com.crittercism.txn.config", 0).edit();
            localEditor2.putBoolean("enabled", localbh.a);
            localEditor2.putInt("interval", localbh.b);
            localEditor2.putInt("defaultTimeout", localbh.c);
            localEditor2.putString("transactions", localbh.d.toString());
            localEditor2.commit();
            az.A().a(localbh);
          }
          return;
          label427:
          dx.a = dx.a.c;
          continue;
          try
          {
            int i = localJSONObject.getInt("rateAfterLoadNum");
            if (i < 0) {
              i = 0;
            }
            localdt.a.edit().putInt("rateAfterNumLoads", i).commit();
            int j = localJSONObject.getInt("remindAfterLoadNum");
            if (j <= 0) {
              j = 1;
            }
            localdt.a.edit().putInt("remindAfterNumLoads", j).commit();
            String str1 = localJSONObject.getString("message");
            localdt.a.edit().putString("rateAppMessage", str1).commit();
            String str2 = localJSONObject.getString("title");
            localdt.a.edit().putString("rateAppTitle", str2).commit();
            localdt.a(true);
          }
          catch (JSONException localJSONException)
          {
            localdt.a(false);
          }
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          Context localContext;
          SharedPreferences.Editor localEditor1;
          new StringBuilder("IOException in handleResponse(): ").append(localIOException.getMessage());
          dx.b();
          dx.c();
          continue;
          File localFile = h.a(localContext);
          if ((!localFile.delete()) && (localFile.exists()))
          {
            dx.b("Unable to reenable OPTMZ instrumentation");
            continue;
            label669:
            localEditor1.clear();
          }
        }
      }
    }
  }
  
  public static class a
    implements cz
  {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */