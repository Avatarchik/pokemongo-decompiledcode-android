package com.google.android.gms.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class zzd
{
  Context context;
  SharedPreferences zzaEa;
  
  public zzd(Context paramContext)
  {
    this(paramContext, "com.google.android.gms.appid");
  }
  
  public zzd(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.zzaEa = paramContext.getSharedPreferences(paramString, 4);
    zzdo(paramString + "-no-backup");
  }
  
  private void zzdo(String paramString)
  {
    File localFile = new File(new ContextCompat().getNoBackupFilesDir(this.context), paramString);
    if (localFile.exists()) {}
    for (;;)
    {
      return;
      try
      {
        if ((!localFile.createNewFile()) || (isEmpty())) {
          continue;
        }
        Log.i("InstanceID/Store", "App restored, clearing state");
        InstanceIDListenerService.zza(this.context, this);
      }
      catch (IOException localIOException) {}
      if (Log.isLoggable("InstanceID/Store", 3)) {
        Log.d("InstanceID/Store", "Error creating file in no backup dir: " + localIOException.getMessage());
      }
    }
  }
  
  private String zzf(String paramString1, String paramString2, String paramString3)
  {
    return paramString1 + "|T|" + paramString2 + "|" + paramString3;
  }
  
  /**
   * @deprecated
   */
  String get(String paramString)
  {
    try
    {
      String str = this.zzaEa.getString(paramString, null);
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  String get(String paramString1, String paramString2)
  {
    try
    {
      String str = this.zzaEa.getString(paramString1 + "|S|" + paramString2, null);
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  boolean isEmpty()
  {
    return this.zzaEa.getAll().isEmpty();
  }
  
  /**
   * @deprecated
   */
  void zza(SharedPreferences.Editor paramEditor, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramEditor.putString(paramString1 + "|S|" + paramString2, paramString3);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void zza(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    try
    {
      String str = zzf(paramString1, paramString2, paramString3);
      SharedPreferences.Editor localEditor = this.zzaEa.edit();
      localEditor.putString(str, paramString4);
      localEditor.putString("appVersion", paramString5);
      localEditor.putString("lastToken", Long.toString(System.currentTimeMillis() / 1000L));
      localEditor.commit();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void zzdp(String paramString)
  {
    try
    {
      SharedPreferences.Editor localEditor = this.zzaEa.edit();
      Iterator localIterator = this.zzaEa.getAll().keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (str.startsWith(paramString)) {
          localEditor.remove(str);
        }
      }
      localEditor.commit();
    }
    finally {}
  }
  
  public KeyPair zzdq(String paramString)
  {
    return zzdt(paramString);
  }
  
  void zzdr(String paramString)
  {
    zzdp(paramString + "|");
  }
  
  public void zzds(String paramString)
  {
    zzdp(paramString + "|T|");
  }
  
  KeyPair zzdt(String paramString)
  {
    String str1 = get(paramString, "|P|");
    String str2 = get(paramString, "|K|");
    KeyPair localKeyPair;
    if (str2 == null) {
      localKeyPair = null;
    }
    for (;;)
    {
      return localKeyPair;
      try
      {
        byte[] arrayOfByte1 = Base64.decode(str1, 8);
        byte[] arrayOfByte2 = Base64.decode(str2, 8);
        KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
        localKeyPair = new KeyPair(localKeyFactory.generatePublic(new X509EncodedKeySpec(arrayOfByte1)), localKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(arrayOfByte2)));
      }
      catch (InvalidKeySpecException localInvalidKeySpecException)
      {
        Log.w("InstanceID/Store", "Invalid key stored " + localInvalidKeySpecException);
        InstanceIDListenerService.zza(this.context, this);
        localKeyPair = null;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        for (;;) {}
      }
    }
  }
  
  /**
   * @deprecated
   */
  KeyPair zze(String paramString, long paramLong)
  {
    try
    {
      KeyPair localKeyPair = zza.zzwl();
      SharedPreferences.Editor localEditor = this.zzaEa.edit();
      zza(localEditor, paramString, "|P|", InstanceID.zzm(localKeyPair.getPublic().getEncoded()));
      zza(localEditor, paramString, "|K|", InstanceID.zzm(localKeyPair.getPrivate().getEncoded()));
      zza(localEditor, paramString, "cre", Long.toString(paramLong));
      localEditor.commit();
      return localKeyPair;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public String zzg(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      String str1 = zzf(paramString1, paramString2, paramString3);
      String str2 = this.zzaEa.getString(str1, null);
      return str2;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void zzh(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      String str = zzf(paramString1, paramString2, paramString3);
      SharedPreferences.Editor localEditor = this.zzaEa.edit();
      localEditor.remove(str);
      localEditor.commit();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void zzwt()
  {
    try
    {
      this.zzaEa.edit().clear().commit();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/iid/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */