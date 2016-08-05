package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Environment;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zzgr
public class zzca
{
  final Context mContext;
  final String zzqV;
  String zzvB;
  BlockingQueue<zzcg> zzvD;
  ExecutorService zzvE;
  LinkedHashMap<String, String> zzvF = new LinkedHashMap();
  Map<String, zzcd> zzvG = new HashMap();
  private AtomicBoolean zzvH;
  private File zzvI;
  
  public zzca(Context paramContext, String paramString1, String paramString2, Map<String, String> paramMap)
  {
    this.mContext = paramContext;
    this.zzqV = paramString1;
    this.zzvB = paramString2;
    this.zzvH = new AtomicBoolean(false);
    this.zzvH.set(((Boolean)zzby.zzuS.get()).booleanValue());
    if (this.zzvH.get())
    {
      File localFile = Environment.getExternalStorageDirectory();
      if (localFile != null) {
        this.zzvI = new File(localFile, "sdk_csi_data.txt");
      }
    }
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      this.zzvF.put(localEntry.getKey(), localEntry.getValue());
    }
    this.zzvD = new ArrayBlockingQueue(30);
    this.zzvE = Executors.newSingleThreadExecutor();
    this.zzvE.execute(new Runnable()
    {
      public void run()
      {
        zzca.zza(zzca.this);
      }
    });
    this.zzvG.put("action", zzcd.zzvL);
    this.zzvG.put("ad_format", zzcd.zzvL);
    this.zzvG.put("e", zzcd.zzvM);
  }
  
  /* Error */
  private void zza(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +109 -> 110
    //   4: new 173	java/io/FileOutputStream
    //   7: dup
    //   8: aload_1
    //   9: iconst_1
    //   10: invokespecial 176	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   13: astore_3
    //   14: aload_3
    //   15: aload_2
    //   16: invokevirtual 182	java/lang/String:getBytes	()[B
    //   19: invokevirtual 186	java/io/FileOutputStream:write	([B)V
    //   22: aload_3
    //   23: bipush 10
    //   25: invokevirtual 188	java/io/FileOutputStream:write	(I)V
    //   28: aload_3
    //   29: ifnull +7 -> 36
    //   32: aload_3
    //   33: invokevirtual 191	java/io/FileOutputStream:close	()V
    //   36: return
    //   37: astore 8
    //   39: ldc -63
    //   41: aload 8
    //   43: invokestatic 199	com/google/android/gms/ads/internal/util/client/zzb:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   46: goto -10 -> 36
    //   49: astore 4
    //   51: aconst_null
    //   52: astore_3
    //   53: ldc -55
    //   55: aload 4
    //   57: invokestatic 199	com/google/android/gms/ads/internal/util/client/zzb:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   60: aload_3
    //   61: ifnull -25 -> 36
    //   64: aload_3
    //   65: invokevirtual 191	java/io/FileOutputStream:close	()V
    //   68: goto -32 -> 36
    //   71: astore 7
    //   73: ldc -63
    //   75: aload 7
    //   77: invokestatic 199	com/google/android/gms/ads/internal/util/client/zzb:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   80: goto -44 -> 36
    //   83: astore 5
    //   85: aconst_null
    //   86: astore_3
    //   87: aload_3
    //   88: ifnull +7 -> 95
    //   91: aload_3
    //   92: invokevirtual 191	java/io/FileOutputStream:close	()V
    //   95: aload 5
    //   97: athrow
    //   98: astore 6
    //   100: ldc -63
    //   102: aload 6
    //   104: invokestatic 199	com/google/android/gms/ads/internal/util/client/zzb:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   107: goto -12 -> 95
    //   110: ldc -53
    //   112: invokestatic 207	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   115: goto -79 -> 36
    //   118: astore 5
    //   120: goto -33 -> 87
    //   123: astore 4
    //   125: goto -72 -> 53
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	this	zzca
    //   0	128	1	paramFile	File
    //   0	128	2	paramString	String
    //   13	79	3	localFileOutputStream	java.io.FileOutputStream
    //   49	7	4	localIOException1	java.io.IOException
    //   123	1	4	localIOException2	java.io.IOException
    //   83	13	5	localObject1	Object
    //   118	1	5	localObject2	Object
    //   98	5	6	localIOException3	java.io.IOException
    //   71	5	7	localIOException4	java.io.IOException
    //   37	5	8	localIOException5	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   32	36	37	java/io/IOException
    //   4	14	49	java/io/IOException
    //   64	68	71	java/io/IOException
    //   4	14	83	finally
    //   91	95	98	java/io/IOException
    //   14	28	118	finally
    //   53	60	118	finally
    //   14	28	123	java/io/IOException
  }
  
  private void zzc(Map<String, String> paramMap, String paramString)
  {
    String str = zza(this.zzvB, paramMap, paramString);
    if (this.zzvH.get()) {
      zza(this.zzvI, str);
    }
    for (;;)
    {
      return;
      zzp.zzbv().zzc(this.mContext, this.zzqV, str);
    }
  }
  
  private void zzdj()
  {
    try
    {
      for (;;)
      {
        zzcg localzzcg = (zzcg)this.zzvD.take();
        String str = localzzcg.zzdp();
        if (!TextUtils.isEmpty(str)) {
          zzc(zza(this.zzvF, localzzcg.zzn()), str);
        }
      }
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      zzb.zzd("CsiReporter:reporter interrupted", localInterruptedException);
    }
  }
  
  public zzcd zzR(String paramString)
  {
    zzcd localzzcd = (zzcd)this.zzvG.get(paramString);
    if (localzzcd != null) {}
    for (;;)
    {
      return localzzcd;
      localzzcd = zzcd.zzvK;
    }
  }
  
  String zza(String paramString1, Map<String, String> paramMap, String paramString2)
  {
    Uri.Builder localBuilder = Uri.parse(paramString1).buildUpon();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localBuilder.appendQueryParameter((String)localEntry.getKey(), (String)localEntry.getValue());
    }
    StringBuilder localStringBuilder = new StringBuilder(localBuilder.build().toString());
    localStringBuilder.append("&").append("it").append("=").append(paramString2);
    return localStringBuilder.toString();
  }
  
  Map<String, String> zza(Map<String, String> paramMap1, Map<String, String> paramMap2)
  {
    LinkedHashMap localLinkedHashMap1 = new LinkedHashMap(paramMap1);
    if (paramMap2 == null) {}
    for (LinkedHashMap localLinkedHashMap2 = localLinkedHashMap1;; localLinkedHashMap2 = localLinkedHashMap1)
    {
      return localLinkedHashMap2;
      Iterator localIterator = paramMap2.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str1 = (String)localEntry.getKey();
        String str2 = (String)localEntry.getValue();
        String str3 = (String)localLinkedHashMap1.get(str1);
        localLinkedHashMap1.put(str1, zzR(str1).zzd(str3, str2));
      }
    }
  }
  
  public boolean zza(zzcg paramzzcg)
  {
    return this.zzvD.offer(paramzzcg);
  }
  
  public void zzb(List<String> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      this.zzvF.put("e", TextUtils.join(",", paramList));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */