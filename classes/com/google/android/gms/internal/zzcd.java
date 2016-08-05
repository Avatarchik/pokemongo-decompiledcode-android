package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Map;

@zzgr
public abstract class zzcd
{
  @zzgr
  public static final zzcd zzvK = new zzcd()
  {
    public String zzd(String paramAnonymousString1, String paramAnonymousString2)
    {
      return paramAnonymousString2;
    }
  };
  @zzgr
  public static final zzcd zzvL = new zzcd()
  {
    public String zzd(String paramAnonymousString1, String paramAnonymousString2)
    {
      if (paramAnonymousString1 != null) {}
      for (;;)
      {
        return paramAnonymousString1;
        paramAnonymousString1 = paramAnonymousString2;
      }
    }
  };
  @zzgr
  public static final zzcd zzvM = new zzcd()
  {
    private String zzS(String paramAnonymousString)
    {
      if (TextUtils.isEmpty(paramAnonymousString)) {}
      for (;;)
      {
        return paramAnonymousString;
        int i = 0;
        int j = paramAnonymousString.length();
        while ((i < paramAnonymousString.length()) && (paramAnonymousString.charAt(i) == ',')) {
          i++;
        }
        while ((j > 0) && (paramAnonymousString.charAt(j - 1) == ',')) {
          j--;
        }
        if ((i != 0) || (j != paramAnonymousString.length())) {
          paramAnonymousString = paramAnonymousString.substring(i, j);
        }
      }
    }
    
    public String zzd(String paramAnonymousString1, String paramAnonymousString2)
    {
      String str1 = zzS(paramAnonymousString1);
      String str2 = zzS(paramAnonymousString2);
      if (TextUtils.isEmpty(str1)) {}
      for (;;)
      {
        return str2;
        if (TextUtils.isEmpty(str2)) {
          str2 = str1;
        } else {
          str2 = str1 + "," + str2;
        }
      }
    }
  };
  
  public final void zza(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    paramMap.put(paramString1, zzd((String)paramMap.get(paramString1), paramString2));
  }
  
  public abstract String zzd(String paramString1, String paramString2);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */