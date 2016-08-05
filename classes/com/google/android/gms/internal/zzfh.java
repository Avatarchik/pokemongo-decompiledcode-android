package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzfh
{
  private final String zzAK;
  private final zziz zzoM;
  
  public zzfh(zziz paramzziz)
  {
    this(paramzziz, "");
  }
  
  public zzfh(zziz paramzziz, String paramString)
  {
    this.zzoM = paramzziz;
    this.zzAK = paramString;
  }
  
  public void zza(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, int paramInt5)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("width", paramInt1).put("height", paramInt2).put("maxSizeWidth", paramInt3).put("maxSizeHeight", paramInt4).put("density", paramFloat).put("rotation", paramInt5);
      this.zzoM.zzb("onScreenInfoChanged", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzb("Error occured while obtaining screen information.", localJSONException);
      }
    }
  }
  
  public void zzak(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("message", paramString).put("action", this.zzAK);
      this.zzoM.zzb("onError", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzb("Error occurred while dispatching error event.", localJSONException);
      }
    }
  }
  
  public void zzal(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("js", paramString);
      this.zzoM.zzb("onReadyEventReceived", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzb("Error occured while dispatching ready Event.", localJSONException);
      }
    }
  }
  
  public void zzam(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("state", paramString);
      this.zzoM.zzb("onStateChanged", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzb("Error occured while dispatching state change.", localJSONException);
      }
    }
  }
  
  public void zzb(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("x", paramInt1).put("y", paramInt2).put("width", paramInt3).put("height", paramInt4);
      this.zzoM.zzb("onSizeChanged", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzb("Error occured while dispatching size change.", localJSONException);
      }
    }
  }
  
  public void zzc(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("x", paramInt1).put("y", paramInt2).put("width", paramInt3).put("height", paramInt4);
      this.zzoM.zzb("onDefaultPositionReceived", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzb("Error occured while dispatching default position.", localJSONException);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */