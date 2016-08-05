package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import org.json.JSONObject;

@zzgr
public class zzfp
  extends Handler
{
  private final zzfo zzCq;
  
  public zzfp(Context paramContext)
  {
    this(new zzfq(paramContext));
  }
  
  public zzfp(zzfo paramzzfo)
  {
    this.zzCq = paramzzfo;
  }
  
  private void zzc(JSONObject paramJSONObject)
  {
    try
    {
      this.zzCq.zza(paramJSONObject.getString("request_id"), paramJSONObject.getString("base_url"), paramJSONObject.getString("html"));
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void handleMessage(Message paramMessage)
  {
    try
    {
      Bundle localBundle = paramMessage.getData();
      if (localBundle != null)
      {
        JSONObject localJSONObject = new JSONObject(localBundle.getString("data"));
        if ("fetch_html".equals(localJSONObject.getString("message_name"))) {
          zzc(localJSONObject);
        }
      }
    }
    catch (Exception localException) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */