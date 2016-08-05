package com.nianticlabs.nia.platform;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;
import android.util.Base64;
import com.nianticlabs.nia.contextservice.ContextService;
import java.io.File;
import java.util.Locale;

public class AndroidPlatformContext
  extends ContextService
{
  private final SharedPreferences prefs;
  
  public AndroidPlatformContext(Context paramContext, long paramLong)
  {
    super(paramContext, paramLong);
    this.prefs = paramContext.getSharedPreferences(paramContext.getPackageName() + ".PREFS", 0);
  }
  
  public String concatPath(String paramString1, String paramString2)
  {
    return new File(paramString1, paramString2).getPath();
  }
  
  public boolean deleteFile(String paramString)
  {
    return new File(paramString).delete();
  }
  
  public boolean deleteSetting(String paramString)
  {
    return this.prefs.edit().remove(paramString).commit();
  }
  
  public long fileSize(String paramString)
  {
    return new File(paramString).length();
  }
  
  public String getCacheDirectory()
  {
    return this.context.getCacheDir().getPath();
  }
  
  public String getDeviceCountryCode()
  {
    return Locale.getDefault().getCountry();
  }
  
  public String getDeviceId()
  {
    return Settings.Secure.getString(this.context.getContentResolver(), "android_id");
  }
  
  public String getDeviceLanguageCode()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public byte[] getSetting(String paramString)
  {
    byte[] arrayOfByte = null;
    String str = this.prefs.getString(paramString, null);
    if (str != null) {
      arrayOfByte = Base64.decode(str.getBytes(), 0);
    }
    return arrayOfByte;
  }
  
  public boolean makePathRecursive(String paramString)
  {
    return new File(paramString).mkdirs();
  }
  
  public boolean pathExists(String paramString)
  {
    return new File(paramString).exists();
  }
  
  public boolean setSetting(String paramString, byte[] paramArrayOfByte)
  {
    String str = Base64.encodeToString(paramArrayOfByte, 0);
    return this.prefs.edit().putString(paramString, str).commit();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/platform/AndroidPlatformContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */