package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzfr.zza;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzhv;
import com.google.android.gms.internal.zzid;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

@zzgr
public class zzd
  extends zzfr.zza
{
  private Context mContext;
  private String zzCJ;
  private ArrayList<String> zzCK;
  private String zzqV;
  
  public zzd(String paramString1, ArrayList<String> paramArrayList, Context paramContext, String paramString2)
  {
    this.zzCJ = paramString1;
    this.zzCK = paramArrayList;
    this.zzqV = paramString2;
    this.mContext = paramContext;
  }
  
  public String getProductId()
  {
    return this.zzCJ;
  }
  
  public void recordPlayBillingResolution(int paramInt)
  {
    if (paramInt == 0) {
      zzfn();
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("google_play_status", String.valueOf(paramInt));
    localHashMap.put("sku", this.zzCJ);
    localHashMap.put("status", String.valueOf(zzy(paramInt)));
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.zzCK.iterator();
    while (localIterator.hasNext()) {
      localLinkedList.add(zza((String)localIterator.next(), localHashMap));
    }
    zzp.zzbv().zza(this.mContext, this.zzqV, localLinkedList);
  }
  
  public void recordResolution(int paramInt)
  {
    if (paramInt == 1) {
      zzfn();
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("status", String.valueOf(paramInt));
    localHashMap.put("sku", this.zzCJ);
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = this.zzCK.iterator();
    while (localIterator.hasNext()) {
      localLinkedList.add(zza((String)localIterator.next(), localHashMap));
    }
    zzp.zzbv().zza(this.mContext, this.zzqV, localLinkedList);
  }
  
  protected String zza(String paramString, HashMap<String, String> paramHashMap)
  {
    str1 = this.mContext.getPackageName();
    try
    {
      String str18 = this.mContext.getPackageManager().getPackageInfo(str1, 0).versionName;
      str2 = str18;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      String str2;
      long l2;
      for (;;)
      {
        long l1;
        Iterator localIterator;
        zzb.zzd("Error to retrieve app version", localNameNotFoundException);
        str2 = "";
      }
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = "sessionid";
      String str3 = String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", arrayOfObject1);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = zzp.zzby().getSessionId();
      String str4 = paramString.replaceAll(str3, String.format("$1%s$2", arrayOfObject2));
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = "appid";
      String str5 = String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", arrayOfObject3);
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = str1;
      String str6 = str4.replaceAll(str5, String.format("$1%s$2", arrayOfObject4));
      Object[] arrayOfObject5 = new Object[1];
      arrayOfObject5[0] = "osversion";
      String str7 = String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", arrayOfObject5);
      Object[] arrayOfObject6 = new Object[1];
      arrayOfObject6[0] = String.valueOf(Build.VERSION.SDK_INT);
      String str8 = str6.replaceAll(str7, String.format("$1%s$2", arrayOfObject6));
      Object[] arrayOfObject7 = new Object[1];
      arrayOfObject7[0] = "sdkversion";
      String str9 = String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", arrayOfObject7);
      Object[] arrayOfObject8 = new Object[1];
      arrayOfObject8[0] = this.zzqV;
      String str10 = str8.replaceAll(str9, String.format("$1%s$2", arrayOfObject8));
      Object[] arrayOfObject9 = new Object[1];
      arrayOfObject9[0] = "appversion";
      String str11 = String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", arrayOfObject9);
      Object[] arrayOfObject10 = new Object[1];
      arrayOfObject10[0] = str2;
      String str12 = str10.replaceAll(str11, String.format("$1%s$2", arrayOfObject10));
      Object[] arrayOfObject11 = new Object[1];
      arrayOfObject11[0] = "timestamp";
      String str13 = String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", arrayOfObject11);
      Object[] arrayOfObject12 = new Object[1];
      arrayOfObject12[0] = String.valueOf(l2);
      String str14 = str12.replaceAll(str13, String.format("$1%s$2", arrayOfObject12));
      Object[] arrayOfObject13 = new Object[1];
      arrayOfObject13[0] = "[^@]+";
      String str15 = String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", arrayOfObject13);
      Object[] arrayOfObject14 = new Object[1];
      arrayOfObject14[0] = "";
      return str14.replaceAll(str15, String.format("$1%s$2", arrayOfObject14)).replaceAll("@@", "@");
    }
    l1 = zzp.zzby().zzgn().zzgx();
    l2 = SystemClock.elapsedRealtime() - l1;
    localIterator = paramHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str16 = (String)localIterator.next();
      Object[] arrayOfObject15 = new Object[1];
      arrayOfObject15[0] = str16;
      String str17 = String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", arrayOfObject15);
      Object[] arrayOfObject16 = new Object[1];
      arrayOfObject16[0] = paramHashMap.get(str16);
      paramString = paramString.replaceAll(str17, String.format("$1%s$2", arrayOfObject16));
    }
  }
  
  void zzfn()
  {
    try
    {
      Class localClass = this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter");
      Class[] arrayOfClass = new Class[4];
      arrayOfClass[0] = Context.class;
      arrayOfClass[1] = String.class;
      arrayOfClass[2] = String.class;
      arrayOfClass[3] = Boolean.TYPE;
      Method localMethod = localClass.getDeclaredMethod("reportWithProductId", arrayOfClass);
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = this.mContext;
      arrayOfObject[1] = this.zzCJ;
      arrayOfObject[2] = "";
      arrayOfObject[3] = Boolean.valueOf(true);
      localMethod.invoke(null, arrayOfObject);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        zzb.zzaH("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        zzb.zzaH("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        zzb.zzd("Fail to report a conversion.", localException);
      }
    }
  }
  
  protected int zzy(int paramInt)
  {
    int i = 1;
    if (paramInt == 0) {}
    for (;;)
    {
      return i;
      if (paramInt == i) {
        i = 2;
      } else if (paramInt == 4) {
        i = 3;
      } else {
        i = 0;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/purchase/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */