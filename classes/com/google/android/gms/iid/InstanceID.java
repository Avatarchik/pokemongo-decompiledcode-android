package com.google.android.gms.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class InstanceID
{
  public static final String ERROR_BACKOFF = "RETRY_LATER";
  public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
  public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
  public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
  public static final String ERROR_TIMEOUT = "TIMEOUT";
  static String zzaDC;
  static Map<String, InstanceID> zzaDw = new HashMap();
  private static zzd zzaDx;
  private static zzc zzaDy;
  Context mContext;
  String zzaDA = "";
  long zzaDB;
  KeyPair zzaDz;
  
  protected InstanceID(Context paramContext, String paramString, Bundle paramBundle)
  {
    this.mContext = paramContext.getApplicationContext();
    this.zzaDA = paramString;
  }
  
  public static InstanceID getInstance(Context paramContext)
  {
    return zza(paramContext, null);
  }
  
  /**
   * @deprecated
   */
  public static InstanceID zza(Context paramContext, Bundle paramBundle)
  {
    if (paramBundle == null) {}
    for (;;)
    {
      try
      {
        localObject2 = "";
      }
      finally {}
      Context localContext = paramContext.getApplicationContext();
      if (zzaDx == null)
      {
        zzaDx = new zzd(localContext);
        zzaDy = new zzc(localContext);
      }
      zzaDC = Integer.toString(zzaC(localContext));
      InstanceID localInstanceID = (InstanceID)zzaDw.get(localObject3);
      if (localInstanceID == null)
      {
        localInstanceID = new InstanceID(localContext, (String)localObject3, paramBundle);
        zzaDw.put(localObject3, localInstanceID);
      }
      return localInstanceID;
      String str = paramBundle.getString("subtype");
      Object localObject2 = str;
      while (localObject2 != null)
      {
        localObject3 = localObject2;
        break;
      }
      Object localObject3 = "";
    }
  }
  
  static String zza(KeyPair paramKeyPair)
  {
    byte[] arrayOfByte1 = paramKeyPair.getPublic().getEncoded();
    try
    {
      byte[] arrayOfByte2 = MessageDigest.getInstance("SHA1").digest(arrayOfByte1);
      arrayOfByte2[0] = ((byte)(0xFF & 112 + (0xF & arrayOfByte2[0])));
      String str2 = Base64.encodeToString(arrayOfByte2, 0, 8, 11);
      str1 = str2;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        Log.w("InstanceID", "Unexpected error, device missing required alghorithms");
        String str1 = null;
      }
    }
    return str1;
  }
  
  static int zzaC(Context paramContext)
  {
    int i = 0;
    try
    {
      i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Log.w("InstanceID", "Never happens: can't find own package " + localNameNotFoundException);
      }
    }
  }
  
  static String zzm(byte[] paramArrayOfByte)
  {
    return Base64.encodeToString(paramArrayOfByte, 11);
  }
  
  public void deleteInstanceID()
    throws IOException
  {
    zzb("*", "*", null);
    zzwn();
  }
  
  public void deleteToken(String paramString1, String paramString2)
    throws IOException
  {
    zzb(paramString1, paramString2, null);
  }
  
  public long getCreationTime()
  {
    if (this.zzaDB == 0L)
    {
      String str = zzaDx.get(this.zzaDA, "cre");
      if (str != null) {
        this.zzaDB = Long.parseLong(str);
      }
    }
    return this.zzaDB;
  }
  
  public String getId()
  {
    return zza(zzwm());
  }
  
  public String getToken(String paramString1, String paramString2)
    throws IOException
  {
    return getToken(paramString1, paramString2, null);
  }
  
  public String getToken(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    int i = 0;
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    int j = 1;
    if (zzwq()) {}
    for (String str = null; str != null; str = zzaDx.zzg(this.zzaDA, paramString1, paramString2)) {
      return str;
    }
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    if (paramBundle.getString("ttl") != null) {
      j = 0;
    }
    if ("jwt".equals(paramBundle.getString("type"))) {}
    for (;;)
    {
      str = zzc(paramString1, paramString2, paramBundle);
      Log.w("InstanceID", "token: " + str);
      if ((str == null) || (i == 0)) {
        break;
      }
      zzaDx.zza(this.zzaDA, paramString1, paramString2, str, zzaDC);
      break;
      i = j;
    }
  }
  
  public void zzb(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    zzaDx.zzh(this.zzaDA, paramString1, paramString2);
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    paramBundle.putString("sender", paramString1);
    if (paramString2 != null) {
      paramBundle.putString("scope", paramString2);
    }
    paramBundle.putString("subscription", paramString1);
    paramBundle.putString("delete", "1");
    paramBundle.putString("X-delete", "1");
    String str;
    if ("".equals(this.zzaDA))
    {
      str = paramString1;
      paramBundle.putString("subtype", str);
      if (!"".equals(this.zzaDA)) {
        break label166;
      }
    }
    for (;;)
    {
      paramBundle.putString("X-subtype", paramString1);
      Intent localIntent = zzaDy.zza(paramBundle, zzwm());
      zzaDy.zzp(localIntent);
      return;
      str = this.zzaDA;
      break;
      label166:
      paramString1 = this.zzaDA;
    }
  }
  
  public String zzc(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (paramString2 != null) {
      paramBundle.putString("scope", paramString2);
    }
    paramBundle.putString("sender", paramString1);
    if ("".equals(this.zzaDA)) {}
    for (String str = paramString1;; str = this.zzaDA)
    {
      if (!paramBundle.containsKey("legacy.register"))
      {
        paramBundle.putString("subscription", paramString1);
        paramBundle.putString("subtype", str);
        paramBundle.putString("X-subscription", paramString1);
        paramBundle.putString("X-subtype", str);
      }
      Intent localIntent = zzaDy.zza(paramBundle, zzwm());
      return zzaDy.zzp(localIntent);
    }
  }
  
  KeyPair zzwm()
  {
    if (this.zzaDz == null) {
      this.zzaDz = zzaDx.zzdq(this.zzaDA);
    }
    if (this.zzaDz == null)
    {
      this.zzaDB = System.currentTimeMillis();
      this.zzaDz = zzaDx.zze(this.zzaDA, this.zzaDB);
    }
    return this.zzaDz;
  }
  
  void zzwn()
  {
    this.zzaDB = 0L;
    zzaDx.zzdr(this.zzaDA);
    this.zzaDz = null;
  }
  
  zzd zzwo()
  {
    return zzaDx;
  }
  
  zzc zzwp()
  {
    return zzaDy;
  }
  
  boolean zzwq()
  {
    boolean bool = true;
    String str1 = zzaDx.get("appVersion");
    if ((str1 == null) || (!str1.equals(zzaDC))) {}
    for (;;)
    {
      return bool;
      String str2 = zzaDx.get("lastToken");
      if (str2 != null)
      {
        Long localLong = Long.valueOf(Long.parseLong(str2));
        if (System.currentTimeMillis() / 1000L - localLong.longValue() <= 604800L) {
          bool = false;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/iid/InstanceID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */