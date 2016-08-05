package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzdl
  implements zzdk
{
  private final Context mContext;
  private final VersionInfoParcel zzpb;
  
  public zzdl(Context paramContext, VersionInfoParcel paramVersionInfoParcel)
  {
    this.mContext = paramContext;
    this.zzpb = paramVersionInfoParcel;
  }
  
  /* Error */
  public JSONObject zzX(String paramString)
  {
    // Byte code:
    //   0: new 42	org/json/JSONObject
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 45	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   8: astore_2
    //   9: new 42	org/json/JSONObject
    //   12: dup
    //   13: invokespecial 46	org/json/JSONObject:<init>	()V
    //   16: astore_3
    //   17: ldc 48
    //   19: astore 4
    //   21: aload_2
    //   22: ldc 50
    //   24: invokevirtual 54	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   27: astore 4
    //   29: aload_0
    //   30: aload_0
    //   31: aload_2
    //   32: invokevirtual 57	com/google/android/gms/internal/zzdl:zzb	(Lorg/json/JSONObject;)Lcom/google/android/gms/internal/zzdl$zzb;
    //   35: invokevirtual 60	com/google/android/gms/internal/zzdl:zza	(Lcom/google/android/gms/internal/zzdl$zzb;)Lcom/google/android/gms/internal/zzdl$zzc;
    //   38: astore 10
    //   40: aload 10
    //   42: invokevirtual 64	com/google/android/gms/internal/zzdl$zzc:isSuccess	()Z
    //   45: ifeq +70 -> 115
    //   48: aload_3
    //   49: ldc 66
    //   51: aload_0
    //   52: aload 10
    //   54: invokevirtual 70	com/google/android/gms/internal/zzdl$zzc:zzdI	()Lcom/google/android/gms/internal/zzdl$zzd;
    //   57: invokevirtual 73	com/google/android/gms/internal/zzdl:zza	(Lcom/google/android/gms/internal/zzdl$zzd;)Lorg/json/JSONObject;
    //   60: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   63: pop
    //   64: aload_3
    //   65: ldc 79
    //   67: iconst_1
    //   68: invokevirtual 82	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   71: pop
    //   72: aload_3
    //   73: areturn
    //   74: astore 16
    //   76: ldc 84
    //   78: invokestatic 89	com/google/android/gms/ads/internal/util/client/zzb:e	(Ljava/lang/String;)V
    //   81: new 42	org/json/JSONObject
    //   84: dup
    //   85: invokespecial 46	org/json/JSONObject:<init>	()V
    //   88: ldc 79
    //   90: iconst_0
    //   91: invokevirtual 82	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   94: astore 18
    //   96: aload 18
    //   98: astore_3
    //   99: goto -27 -> 72
    //   102: astore 17
    //   104: new 42	org/json/JSONObject
    //   107: dup
    //   108: invokespecial 46	org/json/JSONObject:<init>	()V
    //   111: astore_3
    //   112: goto -40 -> 72
    //   115: aload_3
    //   116: ldc 66
    //   118: new 42	org/json/JSONObject
    //   121: dup
    //   122: invokespecial 46	org/json/JSONObject:<init>	()V
    //   125: ldc 50
    //   127: aload 4
    //   129: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   132: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   135: pop
    //   136: aload_3
    //   137: ldc 79
    //   139: iconst_0
    //   140: invokevirtual 82	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   143: pop
    //   144: aload_3
    //   145: ldc 91
    //   147: aload 10
    //   149: invokevirtual 95	com/google/android/gms/internal/zzdl$zzc:getReason	()Ljava/lang/String;
    //   152: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   155: pop
    //   156: goto -84 -> 72
    //   159: astore 5
    //   161: aload_3
    //   162: ldc 66
    //   164: new 42	org/json/JSONObject
    //   167: dup
    //   168: invokespecial 46	org/json/JSONObject:<init>	()V
    //   171: ldc 50
    //   173: aload 4
    //   175: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   178: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   181: pop
    //   182: aload_3
    //   183: ldc 79
    //   185: iconst_0
    //   186: invokevirtual 82	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   189: pop
    //   190: aload_3
    //   191: ldc 91
    //   193: aload 5
    //   195: invokevirtual 98	java/lang/Exception:toString	()Ljava/lang/String;
    //   198: invokevirtual 77	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   201: pop
    //   202: goto -130 -> 72
    //   205: astore 6
    //   207: goto -135 -> 72
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	210	0	this	zzdl
    //   0	210	1	paramString	String
    //   8	24	2	localJSONObject1	JSONObject
    //   16	175	3	localObject	Object
    //   19	155	4	str	String
    //   159	35	5	localException	Exception
    //   205	1	6	localJSONException1	JSONException
    //   38	110	10	localzzc	zzc
    //   74	1	16	localJSONException2	JSONException
    //   102	1	17	localJSONException3	JSONException
    //   94	3	18	localJSONObject2	JSONObject
    // Exception table:
    //   from	to	target	type
    //   0	9	74	org/json/JSONException
    //   81	96	102	org/json/JSONException
    //   21	72	159	java/lang/Exception
    //   115	156	159	java/lang/Exception
    //   161	202	205	org/json/JSONException
  }
  
  protected zzc zza(zzb paramzzb)
  {
    HttpURLConnection localHttpURLConnection;
    zzc localzzc;
    try
    {
      localHttpURLConnection = (HttpURLConnection)paramzzb.zzdF().openConnection();
      zzp.zzbv().zza(this.mContext, this.zzpb.zzJu, false, localHttpURLConnection);
      Iterator localIterator1 = paramzzb.zzdG().iterator();
      while (localIterator1.hasNext())
      {
        zza localzza = (zza)localIterator1.next();
        localHttpURLConnection.addRequestProperty(localzza.getKey(), localzza.getValue());
        continue;
        return localzzc;
      }
    }
    catch (Exception localException)
    {
      localzzc = new zzc(false, null, localException.toString());
    }
    for (;;)
    {
      if (!TextUtils.isEmpty(paramzzb.zzdH()))
      {
        localHttpURLConnection.setDoOutput(true);
        byte[] arrayOfByte = paramzzb.zzdH().getBytes();
        localHttpURLConnection.setFixedLengthStreamingMode(arrayOfByte.length);
        BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localHttpURLConnection.getOutputStream());
        localBufferedOutputStream.write(arrayOfByte);
        localBufferedOutputStream.close();
      }
      ArrayList localArrayList = new ArrayList();
      if (localHttpURLConnection.getHeaderFields() != null)
      {
        Iterator localIterator2 = localHttpURLConnection.getHeaderFields().entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator2.next();
          Iterator localIterator3 = ((List)localEntry.getValue()).iterator();
          while (localIterator3.hasNext())
          {
            String str = (String)localIterator3.next();
            localArrayList.add(new zza((String)localEntry.getKey(), str));
          }
        }
      }
      localzzc = new zzc(true, new zzd(paramzzb.zzdE(), localHttpURLConnection.getResponseCode(), localArrayList, zzp.zzbv().zza(new InputStreamReader(localHttpURLConnection.getInputStream()))), null);
    }
  }
  
  protected JSONObject zza(zzd paramzzd)
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray;
    try
    {
      localJSONObject.put("http_request_id", paramzzd.zzdE());
      if (paramzzd.getBody() != null) {
        localJSONObject.put("body", paramzzd.getBody());
      }
      localJSONArray = new JSONArray();
      Iterator localIterator = paramzzd.zzdJ().iterator();
      while (localIterator.hasNext())
      {
        zza localzza = (zza)localIterator.next();
        localJSONArray.put(new JSONObject().put("key", localzza.getKey()).put("value", localzza.getValue()));
        continue;
        return localJSONObject;
      }
    }
    catch (JSONException localJSONException)
    {
      zzb.zzb("Error constructing JSON for http response.", localJSONException);
    }
    for (;;)
    {
      localJSONObject.put("headers", localJSONArray);
      localJSONObject.put("response_code", paramzzd.getResponseCode());
    }
  }
  
  public void zza(final zziz paramzziz, final Map<String, String> paramMap)
  {
    zzic.zza(new Runnable()
    {
      public void run()
      {
        zzb.zzaF("Received Http request.");
        String str = (String)paramMap.get("http_request");
        final JSONObject localJSONObject = zzdl.this.zzX(str);
        if (localJSONObject == null) {
          zzb.e("Response should not be null.");
        }
        for (;;)
        {
          return;
          zzid.zzIE.post(new Runnable()
          {
            public void run()
            {
              zzdl.1.this.zzxB.zzb("fetchHttpRequestCompleted", localJSONObject);
              zzb.zzaF("Dispatched http response.");
            }
          });
        }
      }
    });
  }
  
  protected zzb zzb(JSONObject paramJSONObject)
  {
    String str1 = paramJSONObject.optString("http_request_id");
    String str2 = paramJSONObject.optString("url");
    String str3 = paramJSONObject.optString("post_body", null);
    URL localURL;
    ArrayList localArrayList;
    try
    {
      localURL = new URL(str2);
      localArrayList = new ArrayList();
      JSONArray localJSONArray = paramJSONObject.optJSONArray("headers");
      if (localJSONArray == null) {
        localJSONArray = new JSONArray();
      }
      for (int i = 0;; i++)
      {
        if (i >= localJSONArray.length()) {
          break label148;
        }
        localJSONObject = localJSONArray.optJSONObject(i);
        if (localJSONObject != null) {
          break;
        }
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      for (;;)
      {
        JSONObject localJSONObject;
        zzb.zzb("Error constructing http request.", localMalformedURLException);
        localURL = null;
        continue;
        localArrayList.add(new zza(localJSONObject.optString("key"), localJSONObject.optString("value")));
      }
    }
    label148:
    return new zzb(str1, localURL, localArrayList, str3);
  }
  
  @zzgr
  class zzc
  {
    private final zzdl.zzd zzxJ;
    private final boolean zzxK;
    private final String zzxL;
    
    public zzc(boolean paramBoolean, zzdl.zzd paramzzd, String paramString)
    {
      this.zzxK = paramBoolean;
      this.zzxJ = paramzzd;
      this.zzxL = paramString;
    }
    
    public String getReason()
    {
      return this.zzxL;
    }
    
    public boolean isSuccess()
    {
      return this.zzxK;
    }
    
    public zzdl.zzd zzdI()
    {
      return this.zzxJ;
    }
  }
  
  @zzgr
  static class zzd
  {
    private final String zzwq;
    private final String zzxF;
    private final int zzxM;
    private final List<zzdl.zza> zzxN;
    
    public zzd(String paramString1, int paramInt, List<zzdl.zza> paramList, String paramString2)
    {
      this.zzxF = paramString1;
      this.zzxM = paramInt;
      if (paramList == null) {}
      for (this.zzxN = new ArrayList();; this.zzxN = paramList)
      {
        this.zzwq = paramString2;
        return;
      }
    }
    
    public String getBody()
    {
      return this.zzwq;
    }
    
    public int getResponseCode()
    {
      return this.zzxM;
    }
    
    public String zzdE()
    {
      return this.zzxF;
    }
    
    public Iterable<zzdl.zza> zzdJ()
    {
      return this.zzxN;
    }
  }
  
  @zzgr
  static class zzb
  {
    private final String zzxF;
    private final URL zzxG;
    private final ArrayList<zzdl.zza> zzxH;
    private final String zzxI;
    
    public zzb(String paramString1, URL paramURL, ArrayList<zzdl.zza> paramArrayList, String paramString2)
    {
      this.zzxF = paramString1;
      this.zzxG = paramURL;
      if (paramArrayList == null) {}
      for (this.zzxH = new ArrayList();; this.zzxH = paramArrayList)
      {
        this.zzxI = paramString2;
        return;
      }
    }
    
    public String zzdE()
    {
      return this.zzxF;
    }
    
    public URL zzdF()
    {
      return this.zzxG;
    }
    
    public ArrayList<zzdl.zza> zzdG()
    {
      return this.zzxH;
    }
    
    public String zzdH()
    {
      return this.zzxI;
    }
  }
  
  @zzgr
  static class zza
  {
    private final String mValue;
    private final String zzue;
    
    public zza(String paramString1, String paramString2)
    {
      this.zzue = paramString1;
      this.mValue = paramString2;
    }
    
    public String getKey()
    {
      return this.zzue;
    }
    
    public String getValue()
    {
      return this.mValue;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */