package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.zzj.zza;
import com.google.android.gms.ads.internal.request.zzk;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public final class zzgt
  extends zzj.zza
{
  private static zzgt zzFA;
  private static final Object zzpy = new Object();
  private final Context mContext;
  private final zzgs zzFB;
  private final zzbr zzFC;
  private final zzdz zzrf;
  
  zzgt(Context paramContext, zzbr paramzzbr, zzgs paramzzgs)
  {
    this.mContext = paramContext;
    this.zzFB = paramzzgs;
    this.zzFC = paramzzbr;
    if (paramContext.getApplicationContext() != null) {}
    for (Context localContext = paramContext.getApplicationContext();; localContext = paramContext)
    {
      this.zzrf = new zzdz(localContext, new VersionInfoParcel(8115000, 8115000, true), paramzzbr.zzdc(), new zzdz.zzb()new zzdz.zzc
      {
        public void zza(zzbb paramAnonymouszzbb)
        {
          paramAnonymouszzbb.zza("/log", zzdj.zzxv);
        }
      }, new zzdz.zzc());
      return;
    }
  }
  
  private static AdResponseParcel zza(Context paramContext, zzdz paramzzdz, final zzbr paramzzbr, zzgs paramzzgs, final AdRequestInfoParcel paramAdRequestInfoParcel)
  {
    zzb.zzaF("Starting ad request from service.");
    zzby.initialize(paramContext);
    final zzcg localzzcg = new zzcg(((Boolean)zzby.zzuQ.get()).booleanValue(), "load_ad", paramAdRequestInfoParcel.zzqn.zzte);
    if ((paramAdRequestInfoParcel.versionCode > 10) && (paramAdRequestInfoParcel.zzEF != -1L))
    {
      zzce localzzce4 = localzzcg.zzb(paramAdRequestInfoParcel.zzEF);
      String[] arrayOfString4 = new String[1];
      arrayOfString4[0] = "cts";
      localzzcg.zza(localzzce4, arrayOfString4);
    }
    zzce localzzce1 = localzzcg.zzdn();
    paramzzgs.zzFv.init();
    zzgy localzzgy = zzp.zzbB().zzC(paramContext);
    AdResponseParcel localAdResponseParcel;
    if (localzzgy.zzGE == -1)
    {
      zzb.zzaF("Device is offline.");
      localAdResponseParcel = new AdResponseParcel(2);
    }
    for (;;)
    {
      return localAdResponseParcel;
      if (paramAdRequestInfoParcel.versionCode >= 7) {}
      final zzgv localzzgv;
      for (String str1 = paramAdRequestInfoParcel.zzEC;; str1 = UUID.randomUUID().toString())
      {
        localzzgv = new zzgv(str1, paramAdRequestInfoParcel.applicationInfo.packageName);
        if (paramAdRequestInfoParcel.zzEn.extras == null) {
          break label240;
        }
        String str6 = paramAdRequestInfoParcel.zzEn.extras.getString("_ad");
        if (str6 == null) {
          break label240;
        }
        localAdResponseParcel = zzgu.zza(paramContext, paramAdRequestInfoParcel, str6);
        break;
      }
      label240:
      Location localLocation = paramzzgs.zzFv.zzd(250L);
      String str2 = paramzzgs.zzFw.zzc(paramContext, paramAdRequestInfoParcel.zzEo.packageName);
      List localList = paramzzgs.zzFu.zza(paramAdRequestInfoParcel);
      String str3 = paramzzgs.zzFx.zzax(paramAdRequestInfoParcel.zzEp);
      JSONObject localJSONObject = zzgu.zza(paramContext, paramAdRequestInfoParcel, localzzgy, paramzzgs.zzFy.zzD(paramContext), localLocation, paramzzbr, str2, str3, localList);
      if (paramAdRequestInfoParcel.versionCode < 7) {}
      try
      {
        localJSONObject.put("request_id", str1);
        if (localJSONObject == null)
        {
          localAdResponseParcel = new AdResponseParcel(0);
          continue;
        }
        final String str4 = localJSONObject.toString();
        String[] arrayOfString1 = new String[1];
        arrayOfString1[0] = "arc";
        localzzcg.zza(localzzce1, arrayOfString1);
        final zzce localzzce2 = localzzcg.zzdn();
        if (((Boolean)zzby.zzum.get()).booleanValue()) {
          zzid.zzIE.post(new Runnable()
          {
            public void run()
            {
              zzdz.zzd localzzd = zzgt.this.zzdO();
              localzzgv.zzb(localzzd);
              zzcg localzzcg = localzzcg;
              zzce localzzce = localzzce2;
              String[] arrayOfString = new String[1];
              arrayOfString[0] = "rwc";
              localzzcg.zza(localzzce, arrayOfString);
              localzzd.zza(new zzis.zzc()new zzis.zza
              {
                public void zzb(zzbe paramAnonymous2zzbe)
                {
                  zzcg localzzcg = zzgt.1.this.zzoD;
                  zzce localzzce = this.zzFH;
                  String[] arrayOfString = new String[1];
                  arrayOfString[0] = "jsf";
                  localzzcg.zza(localzzce, arrayOfString);
                  zzgt.1.this.zzoD.zzdo();
                  paramAnonymous2zzbe.zza("/invalidRequest", zzgt.1.this.zzFE.zzFR);
                  paramAnonymous2zzbe.zza("/loadAdURL", zzgt.1.this.zzFE.zzFS);
                  try
                  {
                    paramAnonymous2zzbe.zza("AFMA_buildAdURL", zzgt.1.this.zzFG);
                    return;
                  }
                  catch (Exception localException)
                  {
                    for (;;)
                    {
                      zzb.zzb("Error requesting an ad url", localException);
                    }
                  }
                }
              }, new zzis.zza()
              {
                public void run() {}
              });
            }
          });
        }
        try
        {
          for (;;)
          {
            localzzgx = (zzgx)localzzgv.zzfS().get(10L, TimeUnit.SECONDS);
            if (localzzgx != null) {
              break label561;
            }
            localAdResponseParcel = new AdResponseParcel(0);
            zzid.zzIE.post(new Runnable()
            {
              public void run()
              {
                zzgt.this.zzfT();
                if (zzgt.this.zzfR() != null) {
                  zzgt.this.zzfR().release();
                }
              }
            });
            break;
            zzid.zzIE.post(new Runnable()
            {
              public void run()
              {
                zziz localzziz = zzp.zzbw().zza(zzgt.this, new AdSizeParcel(), false, false, null, paramAdRequestInfoParcel.zzqj);
                if (zzp.zzby().zzgu()) {
                  localzziz.clearCache(true);
                }
                localzziz.getWebView().setWillNotDraw(true);
                localzzgv.zze(localzziz);
                zzcg localzzcg = localzzcg;
                zzce localzzce1 = localzzce2;
                String[] arrayOfString = new String[1];
                arrayOfString[0] = "rwc";
                localzzcg.zza(localzzce1, arrayOfString);
                zzce localzzce2 = localzzcg.zzdn();
                zzja.zza localzza = zzgt.zzb(str4, localzzcg, localzzce2);
                zzja localzzja = localzziz.zzhe();
                localzzja.zza("/invalidRequest", localzzgv.zzFR);
                localzzja.zza("/loadAdURL", localzzgv.zzFS);
                localzzja.zza("/log", zzdj.zzxv);
                localzzja.zza(localzza);
                zzb.zzaF("Loading the JS library.");
                localzziz.loadUrl(paramzzbr.zzdc());
              }
            });
          }
        }
        catch (Exception localException)
        {
          zzgx localzzgx;
          localAdResponseParcel = new AdResponseParcel(0);
          zzid.zzIE.post(new Runnable()
          {
            public void run()
            {
              zzgt.this.zzfT();
              if (zzgt.this.zzfR() != null) {
                zzgt.this.zzfR().release();
              }
            }
          });
          continue;
          label561:
          if (localzzgx.getErrorCode() != -2)
          {
            localAdResponseParcel = new AdResponseParcel(localzzgx.getErrorCode());
            zzid.zzIE.post(new Runnable()
            {
              public void run()
              {
                zzgt.this.zzfT();
                if (zzgt.this.zzfR() != null) {
                  zzgt.this.zzfR().release();
                }
              }
            });
            continue;
          }
          if (localzzcg.zzdq() != null)
          {
            zzce localzzce3 = localzzcg.zzdq();
            String[] arrayOfString3 = new String[1];
            arrayOfString3[0] = "rur";
            localzzcg.zza(localzzce3, arrayOfString3);
          }
          String str5 = null;
          if (localzzgx.zzfW()) {
            str5 = paramzzgs.zzFt.zzaw(paramAdRequestInfoParcel.zzEo.packageName);
          }
          localAdResponseParcel = zza(paramAdRequestInfoParcel, paramContext, paramAdRequestInfoParcel.zzqj.zzJu, localzzgx.getUrl(), str5, str2, localzzgx, localzzcg, paramzzgs);
          if (localAdResponseParcel.zzEW == 1) {
            paramzzgs.zzFw.clearToken(paramContext, paramAdRequestInfoParcel.zzEo.packageName);
          }
          String[] arrayOfString2 = new String[1];
          arrayOfString2[0] = "tts";
          localzzcg.zza(localzzce1, arrayOfString2);
          localAdResponseParcel.zzEY = localzzcg.zzdp();
          zzid.zzIE.post(new Runnable()
          {
            public void run()
            {
              zzgt.this.zzfT();
              if (zzgt.this.zzfR() != null) {
                zzgt.this.zzfR().release();
              }
            }
          });
        }
        finally
        {
          zzid.zzIE.post(new Runnable()
          {
            public void run()
            {
              zzgt.this.zzfT();
              if (zzgt.this.zzfR() != null) {
                zzgt.this.zzfR().release();
              }
            }
          });
        }
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
  }
  
  /* Error */
  public static AdResponseParcel zza(AdRequestInfoParcel paramAdRequestInfoParcel, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, zzgx paramzzgx, zzcg paramzzcg, zzgs paramzzgs)
  {
    // Byte code:
    //   0: aload 7
    //   2: ifnull +379 -> 381
    //   5: aload 7
    //   7: invokevirtual 157	com/google/android/gms/internal/zzcg:zzdn	()Lcom/google/android/gms/internal/zzce;
    //   10: astore 9
    //   12: new 405	com/google/android/gms/internal/zzgw
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 408	com/google/android/gms/internal/zzgw:<init>	(Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)V
    //   20: astore 10
    //   22: new 410	java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial 411	java/lang/StringBuilder:<init>	()V
    //   29: ldc_w 413
    //   32: invokevirtual 417	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: aload_3
    //   36: invokevirtual 417	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 418	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokestatic 91	com/google/android/gms/ads/internal/util/client/zzb:zzaF	(Ljava/lang/String;)V
    //   45: new 420	java/net/URL
    //   48: dup
    //   49: aload_3
    //   50: invokespecial 422	java/net/URL:<init>	(Ljava/lang/String;)V
    //   53: astore 13
    //   55: invokestatic 426	com/google/android/gms/ads/internal/zzp:zzbz	()Lcom/google/android/gms/internal/zzmn;
    //   58: invokeinterface 432 1 0
    //   63: lstore 14
    //   65: iconst_0
    //   66: istore 16
    //   68: aload 13
    //   70: astore 17
    //   72: aload 8
    //   74: ifnull +13 -> 87
    //   77: aload 8
    //   79: getfield 436	com/google/android/gms/internal/zzgs:zzFz	Lcom/google/android/gms/internal/zzha;
    //   82: invokeinterface 441 1 0
    //   87: aload 17
    //   89: invokevirtual 445	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   92: checkcast 447	java/net/HttpURLConnection
    //   95: astore 18
    //   97: invokestatic 451	com/google/android/gms/ads/internal/zzp:zzbv	()Lcom/google/android/gms/internal/zzid;
    //   100: aload_1
    //   101: aload_2
    //   102: iconst_0
    //   103: aload 18
    //   105: invokevirtual 454	com/google/android/gms/internal/zzid:zza	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
    //   108: aload 4
    //   110: invokestatic 460	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   113: ifne +13 -> 126
    //   116: aload 18
    //   118: ldc_w 462
    //   121: aload 4
    //   123: invokevirtual 465	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   126: aload 5
    //   128: invokestatic 460	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   131: ifne +32 -> 163
    //   134: aload 18
    //   136: ldc_w 467
    //   139: new 410	java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial 411	java/lang/StringBuilder:<init>	()V
    //   146: ldc_w 469
    //   149: invokevirtual 417	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload 5
    //   154: invokevirtual 417	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: invokevirtual 418	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   160: invokevirtual 465	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   163: aload 6
    //   165: ifnull +64 -> 229
    //   168: aload 6
    //   170: invokevirtual 472	com/google/android/gms/internal/zzgx:zzfV	()Ljava/lang/String;
    //   173: invokestatic 460	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   176: ifne +53 -> 229
    //   179: aload 18
    //   181: iconst_1
    //   182: invokevirtual 476	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   185: aload 6
    //   187: invokevirtual 472	com/google/android/gms/internal/zzgx:zzfV	()Ljava/lang/String;
    //   190: invokevirtual 480	java/lang/String:getBytes	()[B
    //   193: astore 35
    //   195: aload 18
    //   197: aload 35
    //   199: arraylength
    //   200: invokevirtual 483	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   203: new 485	java/io/BufferedOutputStream
    //   206: dup
    //   207: aload 18
    //   209: invokevirtual 489	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   212: invokespecial 492	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   215: astore 36
    //   217: aload 36
    //   219: aload 35
    //   221: invokevirtual 496	java/io/BufferedOutputStream:write	([B)V
    //   224: aload 36
    //   226: invokestatic 501	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
    //   229: aload 18
    //   231: invokevirtual 504	java/net/HttpURLConnection:getResponseCode	()I
    //   234: istore 20
    //   236: aload 18
    //   238: invokevirtual 508	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   241: astore 21
    //   243: iload 20
    //   245: sipush 200
    //   248: if_icmplt +232 -> 480
    //   251: iload 20
    //   253: sipush 300
    //   256: if_icmpge +224 -> 480
    //   259: aload 17
    //   261: invokevirtual 509	java/net/URL:toString	()Ljava/lang/String;
    //   264: astore 28
    //   266: new 511	java/io/InputStreamReader
    //   269: dup
    //   270: aload 18
    //   272: invokevirtual 515	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   275: invokespecial 518	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   278: astore 29
    //   280: invokestatic 451	com/google/android/gms/ads/internal/zzp:zzbv	()Lcom/google/android/gms/internal/zzid;
    //   283: aload 29
    //   285: invokevirtual 521	com/google/android/gms/internal/zzid:zza	(Ljava/io/InputStreamReader;)Ljava/lang/String;
    //   288: astore 31
    //   290: aload 29
    //   292: invokestatic 501	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
    //   295: aload 28
    //   297: aload 21
    //   299: aload 31
    //   301: iload 20
    //   303: invokestatic 524	com/google/android/gms/internal/zzgt:zza	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
    //   306: aload 10
    //   308: aload 28
    //   310: aload 21
    //   312: aload 31
    //   314: invokevirtual 527	com/google/android/gms/internal/zzgw:zzb	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V
    //   317: aload 7
    //   319: ifnull +26 -> 345
    //   322: iconst_1
    //   323: anewarray 148	java/lang/String
    //   326: astore 33
    //   328: aload 33
    //   330: iconst_0
    //   331: ldc_w 529
    //   334: aastore
    //   335: aload 7
    //   337: aload 9
    //   339: aload 33
    //   341: invokevirtual 153	com/google/android/gms/internal/zzcg:zza	(Lcom/google/android/gms/internal/zzce;[Ljava/lang/String;)Z
    //   344: pop
    //   345: aload 10
    //   347: lload 14
    //   349: invokevirtual 533	com/google/android/gms/internal/zzgw:zzj	(J)Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
    //   352: astore 32
    //   354: aload 18
    //   356: invokevirtual 536	java/net/HttpURLConnection:disconnect	()V
    //   359: aload 8
    //   361: ifnull +13 -> 374
    //   364: aload 8
    //   366: getfield 436	com/google/android/gms/internal/zzgs:zzFz	Lcom/google/android/gms/internal/zzha;
    //   369: invokeinterface 539 1 0
    //   374: aload 32
    //   376: astore 12
    //   378: aload 12
    //   380: areturn
    //   381: aconst_null
    //   382: astore 9
    //   384: goto -372 -> 12
    //   387: astore 37
    //   389: aconst_null
    //   390: astore 36
    //   392: aload 36
    //   394: invokestatic 501	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
    //   397: aload 37
    //   399: athrow
    //   400: astore 19
    //   402: aload 18
    //   404: invokevirtual 536	java/net/HttpURLConnection:disconnect	()V
    //   407: aload 8
    //   409: ifnull +13 -> 422
    //   412: aload 8
    //   414: getfield 436	com/google/android/gms/internal/zzgs:zzFz	Lcom/google/android/gms/internal/zzha;
    //   417: invokeinterface 539 1 0
    //   422: aload 19
    //   424: athrow
    //   425: astore 11
    //   427: new 410	java/lang/StringBuilder
    //   430: dup
    //   431: invokespecial 411	java/lang/StringBuilder:<init>	()V
    //   434: ldc_w 541
    //   437: invokevirtual 417	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: aload 11
    //   442: invokevirtual 544	java/io/IOException:getMessage	()Ljava/lang/String;
    //   445: invokevirtual 417	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: invokevirtual 418	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   451: invokestatic 547	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   454: new 189	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   457: dup
    //   458: iconst_2
    //   459: invokespecial 192	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   462: astore 12
    //   464: goto -86 -> 378
    //   467: astore 30
    //   469: aconst_null
    //   470: astore 29
    //   472: aload 29
    //   474: invokestatic 501	com/google/android/gms/internal/zzmt:zzb	(Ljava/io/Closeable;)V
    //   477: aload 30
    //   479: athrow
    //   480: aload 17
    //   482: invokevirtual 509	java/net/URL:toString	()Ljava/lang/String;
    //   485: aload 21
    //   487: aconst_null
    //   488: iload 20
    //   490: invokestatic 524	com/google/android/gms/internal/zzgt:zza	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
    //   493: iload 20
    //   495: sipush 300
    //   498: if_icmplt +138 -> 636
    //   501: iload 20
    //   503: sipush 400
    //   506: if_icmpge +130 -> 636
    //   509: aload 18
    //   511: ldc_w 549
    //   514: invokevirtual 552	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   517: astore 23
    //   519: aload 23
    //   521: invokestatic 460	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   524: ifeq +46 -> 570
    //   527: ldc_w 554
    //   530: invokestatic 547	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   533: new 189	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   536: dup
    //   537: iconst_0
    //   538: invokespecial 192	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   541: astore 27
    //   543: aload 18
    //   545: invokevirtual 536	java/net/HttpURLConnection:disconnect	()V
    //   548: aload 8
    //   550: ifnull +13 -> 563
    //   553: aload 8
    //   555: getfield 436	com/google/android/gms/internal/zzgs:zzFz	Lcom/google/android/gms/internal/zzha;
    //   558: invokeinterface 539 1 0
    //   563: aload 27
    //   565: astore 12
    //   567: goto -189 -> 378
    //   570: new 420	java/net/URL
    //   573: dup
    //   574: aload 23
    //   576: invokespecial 422	java/net/URL:<init>	(Ljava/lang/String;)V
    //   579: astore 24
    //   581: iload 16
    //   583: iconst_1
    //   584: iadd
    //   585: istore 25
    //   587: iload 25
    //   589: iconst_5
    //   590: if_icmple +107 -> 697
    //   593: ldc_w 556
    //   596: invokestatic 547	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   599: new 189	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   602: dup
    //   603: iconst_0
    //   604: invokespecial 192	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   607: astore 26
    //   609: aload 18
    //   611: invokevirtual 536	java/net/HttpURLConnection:disconnect	()V
    //   614: aload 8
    //   616: ifnull +13 -> 629
    //   619: aload 8
    //   621: getfield 436	com/google/android/gms/internal/zzgs:zzFz	Lcom/google/android/gms/internal/zzha;
    //   624: invokeinterface 539 1 0
    //   629: aload 26
    //   631: astore 12
    //   633: goto -255 -> 378
    //   636: new 410	java/lang/StringBuilder
    //   639: dup
    //   640: invokespecial 411	java/lang/StringBuilder:<init>	()V
    //   643: ldc_w 558
    //   646: invokevirtual 417	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: iload 20
    //   651: invokevirtual 561	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   654: invokevirtual 418	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   657: invokestatic 547	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   660: new 189	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   663: dup
    //   664: iconst_0
    //   665: invokespecial 192	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   668: astore 22
    //   670: aload 18
    //   672: invokevirtual 536	java/net/HttpURLConnection:disconnect	()V
    //   675: aload 8
    //   677: ifnull +13 -> 690
    //   680: aload 8
    //   682: getfield 436	com/google/android/gms/internal/zzgs:zzFz	Lcom/google/android/gms/internal/zzha;
    //   685: invokeinterface 539 1 0
    //   690: aload 22
    //   692: astore 12
    //   694: goto -316 -> 378
    //   697: aload 10
    //   699: aload 21
    //   701: invokevirtual 565	com/google/android/gms/internal/zzgw:zzh	(Ljava/util/Map;)V
    //   704: aload 18
    //   706: invokevirtual 536	java/net/HttpURLConnection:disconnect	()V
    //   709: aload 8
    //   711: ifnull +13 -> 724
    //   714: aload 8
    //   716: getfield 436	com/google/android/gms/internal/zzgs:zzFz	Lcom/google/android/gms/internal/zzha;
    //   719: invokeinterface 539 1 0
    //   724: iload 25
    //   726: istore 16
    //   728: aload 24
    //   730: astore 17
    //   732: goto -660 -> 72
    //   735: astore 30
    //   737: goto -265 -> 472
    //   740: astore 37
    //   742: goto -350 -> 392
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	745	0	paramAdRequestInfoParcel	AdRequestInfoParcel
    //   0	745	1	paramContext	Context
    //   0	745	2	paramString1	String
    //   0	745	3	paramString2	String
    //   0	745	4	paramString3	String
    //   0	745	5	paramString4	String
    //   0	745	6	paramzzgx	zzgx
    //   0	745	7	paramzzcg	zzcg
    //   0	745	8	paramzzgs	zzgs
    //   10	373	9	localzzce	zzce
    //   20	678	10	localzzgw	zzgw
    //   425	16	11	localIOException	java.io.IOException
    //   376	317	12	localObject1	Object
    //   53	16	13	localURL1	java.net.URL
    //   63	285	14	l	long
    //   66	661	16	i	int
    //   70	661	17	localObject2	Object
    //   95	610	18	localHttpURLConnection	java.net.HttpURLConnection
    //   400	23	19	localObject3	Object
    //   234	416	20	j	int
    //   241	459	21	localMap	Map
    //   668	23	22	localAdResponseParcel1	AdResponseParcel
    //   517	58	23	str1	String
    //   579	150	24	localURL2	java.net.URL
    //   585	140	25	k	int
    //   607	23	26	localAdResponseParcel2	AdResponseParcel
    //   541	23	27	localAdResponseParcel3	AdResponseParcel
    //   264	45	28	str2	String
    //   278	195	29	localInputStreamReader	java.io.InputStreamReader
    //   467	11	30	localObject4	Object
    //   735	1	30	localObject5	Object
    //   288	25	31	str3	String
    //   352	23	32	localAdResponseParcel4	AdResponseParcel
    //   326	14	33	arrayOfString	String[]
    //   193	27	35	arrayOfByte	byte[]
    //   215	178	36	localBufferedOutputStream	java.io.BufferedOutputStream
    //   387	11	37	localObject6	Object
    //   740	1	37	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   203	217	387	finally
    //   97	203	400	finally
    //   224	266	400	finally
    //   290	354	400	finally
    //   392	400	400	finally
    //   472	543	400	finally
    //   570	609	400	finally
    //   636	670	400	finally
    //   697	704	400	finally
    //   12	97	425	java/io/IOException
    //   354	374	425	java/io/IOException
    //   402	425	425	java/io/IOException
    //   543	563	425	java/io/IOException
    //   609	629	425	java/io/IOException
    //   670	690	425	java/io/IOException
    //   704	724	425	java/io/IOException
    //   266	280	467	finally
    //   280	290	735	finally
    //   217	224	740	finally
  }
  
  public static zzgt zza(Context paramContext, zzbr paramzzbr, zzgs paramzzgs)
  {
    synchronized (zzpy)
    {
      if (zzFA == null)
      {
        if (paramContext.getApplicationContext() != null) {
          paramContext = paramContext.getApplicationContext();
        }
        zzFA = new zzgt(paramContext, paramzzbr, paramzzgs);
      }
      zzgt localzzgt = zzFA;
      return localzzgt;
    }
  }
  
  private static zzja.zza zza(final String paramString, zzcg paramzzcg, final zzce paramzzce)
  {
    new zzja.zza()
    {
      public void zza(zziz paramAnonymouszziz, boolean paramAnonymousBoolean)
      {
        zzcg localzzcg = zzgt.this;
        zzce localzzce = paramzzce;
        String[] arrayOfString = new String[1];
        arrayOfString[0] = "jsf";
        localzzcg.zza(localzzce, arrayOfString);
        zzgt.this.zzdo();
        paramAnonymouszziz.zza("AFMA_buildAdURL", paramString);
      }
    };
  }
  
  private static void zza(String paramString1, Map<String, List<String>> paramMap, String paramString2, int paramInt)
  {
    if (zzb.zzN(2))
    {
      zzb.v("Http Response: {\n  URL:\n    " + paramString1 + "\n  Headers:");
      if (paramMap != null)
      {
        Iterator localIterator1 = paramMap.keySet().iterator();
        while (localIterator1.hasNext())
        {
          String str1 = (String)localIterator1.next();
          zzb.v("    " + str1 + ":");
          Iterator localIterator2 = ((List)paramMap.get(str1)).iterator();
          while (localIterator2.hasNext())
          {
            String str2 = (String)localIterator2.next();
            zzb.v("      " + str2);
          }
        }
      }
      zzb.v("  Body:");
      if (paramString2 != null) {
        for (int i = 0; i < Math.min(paramString2.length(), 100000); i += 1000) {
          zzb.v(paramString2.substring(i, Math.min(paramString2.length(), i + 1000)));
        }
      }
      zzb.v("    null");
      zzb.v("  Response Code:\n    " + paramInt + "\n}");
    }
  }
  
  public void zza(final AdRequestInfoParcel paramAdRequestInfoParcel, final zzk paramzzk)
  {
    zzp.zzby().zzb(this.mContext, paramAdRequestInfoParcel.zzqj);
    zzid.zzb(new Runnable()
    {
      public void run()
      {
        try
        {
          AdResponseParcel localAdResponseParcel2 = zzgt.this.zze(paramAdRequestInfoParcel);
          localAdResponseParcel1 = localAdResponseParcel2;
        }
        catch (Exception localException)
        {
          try
          {
            paramzzk.zzb(localAdResponseParcel1);
            return;
            localException = localException;
            zzp.zzby().zzc(localException, true);
            zzb.zzd("Could not fetch ad response due to an Exception.", localException);
            AdResponseParcel localAdResponseParcel1 = null;
          }
          catch (RemoteException localRemoteException)
          {
            for (;;)
            {
              zzb.zzd("Fail to forward ad response.", localRemoteException);
            }
          }
        }
        if (localAdResponseParcel1 == null) {
          localAdResponseParcel1 = new AdResponseParcel(0);
        }
      }
    });
  }
  
  public AdResponseParcel zze(AdRequestInfoParcel paramAdRequestInfoParcel)
  {
    return zza(this.mContext, this.zzrf, this.zzFC, this.zzFB, paramAdRequestInfoParcel);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */