package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.formats.zzh.zza;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzn;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzgm
  implements Callable<zzhs>
{
  private static final long zzDE = TimeUnit.SECONDS.toMillis(60L);
  private final Context mContext;
  private final zzih zzDF;
  private final zzn zzDG;
  private final zzbc zzDH;
  private boolean zzDI;
  private List<String> zzDJ;
  private final zzhs.zza zzDe;
  private int zzDv;
  private final Object zzpd = new Object();
  private final zzan zzwL;
  
  public zzgm(Context paramContext, zzn paramzzn, zzbc paramzzbc, zzih paramzzih, zzan paramzzan, zzhs.zza paramzza)
  {
    this.mContext = paramContext;
    this.zzDG = paramzzn;
    this.zzDF = paramzzih;
    this.zzDH = paramzzbc;
    this.zzDe = paramzza;
    this.zzwL = paramzzan;
    this.zzDI = false;
    this.zzDv = -2;
    this.zzDJ = null;
  }
  
  private zzh.zza zza(zzbb paramzzbb, zza paramzza, JSONObject paramJSONObject)
    throws ExecutionException, InterruptedException, JSONException
  {
    Object localObject = null;
    if (zzfD()) {}
    for (;;)
    {
      return (zzh.zza)localObject;
      String[] arrayOfString = zzc(paramJSONObject.getJSONObject("tracking_urls_and_actions"), "impression_tracking_urls");
      if (arrayOfString == null) {}
      zzh.zza localzza;
      for (List localList = null;; localList = Arrays.asList(arrayOfString))
      {
        this.zzDJ = localList;
        localzza = paramzza.zza(this, paramJSONObject);
        if (localzza != null) {
          break label74;
        }
        zzb.e("Failed to retrieve ad assets.");
        break;
      }
      label74:
      localzza.zza(new zzh(this.mContext, this.zzDG, paramzzbb, this.zzwL, paramJSONObject, localzza, this.zzDe.zzHC.zzqj));
      localObject = localzza;
    }
  }
  
  private zzhs zza(zzh.zza paramzza)
  {
    for (;;)
    {
      synchronized (this.zzpd)
      {
        int i = this.zzDv;
        if ((paramzza == null) && (this.zzDv == -2)) {
          i = 0;
        }
        if (i != -2)
        {
          localzza = null;
          return new zzhs(this.zzDe.zzHC.zzEn, null, this.zzDe.zzHD.zzyY, i, this.zzDe.zzHD.zzyZ, this.zzDJ, this.zzDe.zzHD.orientation, this.zzDe.zzHD.zzzc, this.zzDe.zzHC.zzEq, false, null, null, null, null, null, 0L, this.zzDe.zzqn, this.zzDe.zzHD.zzEJ, this.zzDe.zzHz, this.zzDe.zzHA, this.zzDe.zzHD.zzEP, this.zzDe.zzHw, localzza);
        }
      }
      zzh.zza localzza = paramzza;
    }
  }
  
  private zziq<zzc> zza(JSONObject paramJSONObject, final boolean paramBoolean1, boolean paramBoolean2)
    throws JSONException
  {
    String str;
    final double d;
    Object localObject;
    if (paramBoolean1)
    {
      str = paramJSONObject.getString("url");
      d = paramJSONObject.optDouble("scale", 1.0D);
      if (!TextUtils.isEmpty(str)) {
        break label59;
      }
      zza(0, paramBoolean1);
      localObject = new zzio(null);
    }
    for (;;)
    {
      return (zziq<zzc>)localObject;
      str = paramJSONObject.optString("url");
      break;
      label59:
      if (paramBoolean2) {
        localObject = new zzio(new zzc(null, Uri.parse(str), d));
      } else {
        localObject = this.zzDF.zza(str, new zzih.zza()
        {
          public zzc zzfE()
          {
            zzgm.this.zza(2, paramBoolean1);
            return null;
          }
          
          public zzc zzg(InputStream paramAnonymousInputStream)
          {
            localzzc = null;
            try
            {
              byte[] arrayOfByte2 = zzmt.zzk(paramAnonymousInputStream);
              arrayOfByte1 = arrayOfByte2;
            }
            catch (IOException localIOException)
            {
              for (;;)
              {
                byte[] arrayOfByte1 = null;
                continue;
                Bitmap localBitmap = BitmapFactory.decodeByteArray(arrayOfByte1, 0, arrayOfByte1.length);
                if (localBitmap == null)
                {
                  zzgm.this.zza(2, paramBoolean1);
                }
                else
                {
                  localBitmap.setDensity((int)(160.0D * d));
                  localzzc = new zzc(new BitmapDrawable(Resources.getSystem(), localBitmap), Uri.parse(this.zzAs), d);
                }
              }
            }
            if (arrayOfByte1 == null)
            {
              zzgm.this.zza(2, paramBoolean1);
              return localzzc;
            }
          }
        });
      }
    }
  }
  
  private void zza(zzh.zza paramzza, zzbb paramzzbb)
  {
    if (!(paramzza instanceof zzf)) {}
    for (;;)
    {
      return;
      final zzf localzzf = (zzf)paramzza;
      zzb localzzb = new zzb();
      zzdk local3 = new zzdk()
      {
        public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
        {
          String str = (String)paramAnonymousMap.get("asset");
          zzgm.zza(zzgm.this, localzzf, str);
        }
      };
      localzzb.zzDZ = local3;
      paramzzbb.zza("/nativeAdCustomClick", local3);
    }
  }
  
  private Integer zzb(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject(paramString);
      Integer localInteger2 = Integer.valueOf(Color.rgb(localJSONObject.getInt("r"), localJSONObject.getInt("g"), localJSONObject.getInt("b")));
      localInteger1 = localInteger2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Integer localInteger1 = null;
      }
    }
    return localInteger1;
  }
  
  private JSONObject zzb(final zzbb paramzzbb)
    throws TimeoutException, JSONException
  {
    if (zzfD()) {}
    final zzin localzzin;
    for (JSONObject localJSONObject = null;; localJSONObject = (JSONObject)localzzin.get(zzDE, TimeUnit.MILLISECONDS))
    {
      return localJSONObject;
      localzzin = new zzin();
      final zzb localzzb = new zzb();
      zzdk local1 = new zzdk()
      {
        public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
        {
          paramzzbb.zzb("/nativeAdPreProcess", localzzb.zzDZ);
          try
          {
            String str = (String)paramAnonymousMap.get("success");
            if (!TextUtils.isEmpty(str))
            {
              localzzin.zzf(new JSONObject(str).getJSONArray("ads").getJSONObject(0));
              return;
            }
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              zzb.zzb("Malformed native JSON response.", localJSONException);
              zzgm.this.zzC(0);
              zzx.zza(zzgm.this.zzfD(), "Unable to set the ad state error!");
              localzzin.zzf(null);
            }
          }
        }
      };
      localzzb.zzDZ = local1;
      paramzzbb.zza("/nativeAdPreProcess", local1);
      paramzzbb.zza("google.afma.nativeAds.preProcessJsonGmsg", new JSONObject(this.zzDe.zzHD.body));
    }
  }
  
  private void zzb(zzcu paramzzcu, String paramString)
  {
    try
    {
      zzcy localzzcy = this.zzDG.zzr(paramzzcu.getCustomTemplateId());
      if (localzzcy != null) {
        localzzcy.zza(paramzzcu, paramString);
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        zzb.zzd("Failed to call onCustomClick for asset " + paramString + ".", localRemoteException);
      }
    }
  }
  
  private String[] zzc(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray(paramString);
    if (localJSONArray == null) {}
    String[] arrayOfString;
    for (Object localObject = null;; localObject = arrayOfString)
    {
      return (String[])localObject;
      arrayOfString = new String[localJSONArray.length()];
      for (int i = 0; i < localJSONArray.length(); i++) {
        arrayOfString[i] = localJSONArray.getString(i);
      }
    }
  }
  
  private static List<Drawable> zzd(List<zzc> paramList)
    throws RemoteException
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add((Drawable)zze.zzp(((zzc)localIterator.next()).zzdv()));
    }
    return localArrayList;
  }
  
  private zzbb zzfC()
    throws CancellationException, ExecutionException, InterruptedException, TimeoutException
  {
    Object localObject = null;
    if (zzfD()) {
      return (zzbb)localObject;
    }
    String str1 = (String)zzby.zzvj.get();
    if (this.zzDe.zzHD.zzBF.indexOf("https") == 0) {}
    for (String str2 = "https:";; str2 = "http:")
    {
      String str3 = str2 + str1;
      zzbb localzzbb = (zzbb)this.zzDH.zza(this.mContext, this.zzDe.zzHC.zzqj, str3, this.zzwL).get(zzDE, TimeUnit.MILLISECONDS);
      localzzbb.zza(this.zzDG, this.zzDG, this.zzDG, this.zzDG, false, null, null, null, null);
      localObject = localzzbb;
      break;
    }
  }
  
  public void zzC(int paramInt)
  {
    synchronized (this.zzpd)
    {
      this.zzDI = true;
      this.zzDv = paramInt;
      return;
    }
  }
  
  public zziq<zzc> zza(JSONObject paramJSONObject, String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws JSONException
  {
    if (paramBoolean1) {}
    for (JSONObject localJSONObject = paramJSONObject.getJSONObject(paramString);; localJSONObject = paramJSONObject.optJSONObject(paramString))
    {
      if (localJSONObject == null) {
        localJSONObject = new JSONObject();
      }
      return zza(localJSONObject, paramBoolean1, paramBoolean2);
    }
  }
  
  public List<zziq<zzc>> zza(JSONObject paramJSONObject, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    throws JSONException
  {
    JSONArray localJSONArray;
    ArrayList localArrayList1;
    if (paramBoolean1)
    {
      localJSONArray = paramJSONObject.getJSONArray(paramString);
      localArrayList1 = new ArrayList();
      if ((localJSONArray != null) && (localJSONArray.length() != 0)) {
        break label56;
      }
      zza(0, paramBoolean1);
    }
    for (ArrayList localArrayList2 = localArrayList1;; localArrayList2 = localArrayList1)
    {
      return localArrayList2;
      localJSONArray = paramJSONObject.optJSONArray(paramString);
      break;
      label56:
      if (paramBoolean3) {}
      for (int i = localJSONArray.length();; i = 1) {
        for (int j = 0; j < i; j++)
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(j);
          if (localJSONObject == null) {
            localJSONObject = new JSONObject();
          }
          localArrayList1.add(zza(localJSONObject, paramBoolean1, paramBoolean2));
        }
      }
    }
  }
  
  public Future<zzc> zza(JSONObject paramJSONObject, String paramString, boolean paramBoolean)
    throws JSONException
  {
    JSONObject localJSONObject = paramJSONObject.getJSONObject(paramString);
    boolean bool = localJSONObject.optBoolean("require", true);
    if (localJSONObject == null) {
      localJSONObject = new JSONObject();
    }
    return zza(localJSONObject, bool, paramBoolean);
  }
  
  public void zza(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean) {
      zzC(paramInt);
    }
  }
  
  protected zza zzd(JSONObject paramJSONObject)
    throws JSONException, TimeoutException
  {
    Object localObject;
    if (zzfD()) {
      localObject = null;
    }
    for (;;)
    {
      return (zza)localObject;
      String str1 = paramJSONObject.getString("template_id");
      boolean bool1;
      if (this.zzDe.zzHC.zzqB != null)
      {
        bool1 = this.zzDe.zzHC.zzqB.zzwR;
        label48:
        if (this.zzDe.zzHC.zzqB == null) {
          break label106;
        }
      }
      label106:
      for (boolean bool2 = this.zzDe.zzHC.zzqB.zzwT;; bool2 = false)
      {
        if (!"2".equals(str1)) {
          break label112;
        }
        localObject = new zzgn(bool1, bool2);
        break;
        bool1 = false;
        break label48;
      }
      label112:
      if ("1".equals(str1))
      {
        localObject = new zzgo(bool1, bool2);
      }
      else
      {
        if (!"3".equals(str1)) {
          break label246;
        }
        final String str2 = paramJSONObject.getString("custom_template_id");
        final zzin localzzin = new zzin();
        zzid.zzIE.post(new Runnable()
        {
          public void run()
          {
            localzzin.zzf(zzgm.zza(zzgm.this).zzbo().get(str2));
          }
        });
        if (localzzin.get(zzDE, TimeUnit.MILLISECONDS) == null) {
          break;
        }
        localObject = new zzgp(bool1);
      }
    }
    zzb.e("No handler for custom template: " + paramJSONObject.getString("custom_template_id"));
    for (;;)
    {
      localObject = null;
      break;
      label246:
      zzC(0);
    }
  }
  
  public zziq<zza> zze(JSONObject paramJSONObject)
    throws JSONException
  {
    JSONObject localJSONObject = paramJSONObject.optJSONObject("attribution");
    Object localObject1;
    if (localJSONObject == null)
    {
      localObject1 = new zzio(null);
      return (zziq<zza>)localObject1;
    }
    final String str = localJSONObject.optString("text");
    final int i = localJSONObject.optInt("text_size", -1);
    final Integer localInteger1 = zzb(localJSONObject, "text_color");
    final Integer localInteger2 = zzb(localJSONObject, "bg_color");
    final int j = localJSONObject.optInt("animation_ms", 1000);
    final int k = localJSONObject.optInt("presentation_ms", 4000);
    Object localObject2 = new ArrayList();
    if (localJSONObject.optJSONArray("images") != null) {
      localObject2 = zza(localJSONObject, "images", false, false, true);
    }
    for (;;)
    {
      localObject1 = zzip.zza(zzip.zzh((List)localObject2), new zzip.zza()
      {
        public zza zzf(List<zzc> paramAnonymousList)
        {
          Object localObject = null;
          if (paramAnonymousList != null) {
            for (;;)
            {
              try
              {
                if (paramAnonymousList.isEmpty()) {
                  break;
                }
                String str = str;
                List localList = zzgm.zze(paramAnonymousList);
                Integer localInteger1 = localInteger2;
                Integer localInteger2 = localInteger1;
                if (i > 0)
                {
                  localInteger3 = Integer.valueOf(i);
                  localzza = new zza(str, localList, localInteger1, localInteger2, localInteger3, k + j);
                }
              }
              catch (RemoteException localRemoteException)
              {
                zzb.zzb("Could not get attribution icon", localRemoteException);
                return (zza)localObject;
              }
              Integer localInteger3 = null;
            }
          }
          zza localzza = null;
          localObject = localzza;
          return (zza)localObject;
        }
      });
      break;
      ((List)localObject2).add(zza(localJSONObject, "image", false, false));
    }
  }
  
  public zzhs zzfB()
  {
    try
    {
      zzbb localzzbb = zzfC();
      JSONObject localJSONObject = zzb(localzzbb);
      zzh.zza localzza = zza(localzzbb, zzd(localJSONObject), localJSONObject);
      zza(localzza, localzzbb);
      zzhs localzzhs2 = zza(localzza);
      localzzhs1 = localzzhs2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzd("Malformed native JSON response.", localJSONException);
        if (!this.zzDI) {
          zzC(0);
        }
        zzhs localzzhs1 = zza(null);
      }
    }
    catch (TimeoutException localTimeoutException)
    {
      for (;;)
      {
        zzb.zzd("Timeout when loading native ad.", localTimeoutException);
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;) {}
    }
    catch (CancellationException localCancellationException)
    {
      for (;;) {}
    }
    return localzzhs1;
  }
  
  public boolean zzfD()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzDI;
      return bool;
    }
  }
  
  class zzb
  {
    public zzdk zzDZ;
    
    zzb() {}
  }
  
  public static abstract interface zza<T extends zzh.zza>
  {
    public abstract T zza(zzgm paramzzgm, JSONObject paramJSONObject)
      throws JSONException, InterruptedException, ExecutionException;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */