package crittercism.android;

import java.util.HashMap;
import java.util.Map;

public final class bn
{
  private static final Map a;
  private String b;
  private String c;
  private String d;
  private String e;
  
  static
  {
    HashMap localHashMap = new HashMap();
    a = localHashMap;
    localHashMap.put("00555300", "crittercism.com");
    a.put("00555304", "crit-ci.com");
    a.put("00555305", "crit-staging.com");
    a.put("00444503", "eu.crittercism.com");
  }
  
  public bn(String paramString)
  {
    if (paramString == null) {
      throw new a("Given null appId");
    }
    if (!paramString.matches("[0-9a-fA-F]+")) {
      throw new a("Invalid appId: '" + paramString + "'. AppId must be hexadecimal characters");
    }
    if ((paramString.length() != 24) && (paramString.length() != 40)) {
      throw new a("Invalid appId: '" + paramString + "'. AppId must be either 24 or 40 characters");
    }
    String str1 = null;
    if (paramString.length() == 24) {
      str1 = "00555300";
    }
    String str2;
    for (;;)
    {
      str2 = (String)a.get(str1);
      if (str2 != null) {
        break;
      }
      throw new a("Invalid appId: '" + paramString + "'. Invalid app locator code");
      if (paramString.length() == 40) {
        str1 = paramString.substring(-8 + paramString.length());
      }
    }
    this.b = System.getProperty("com.crittercism.apmUrl", "https://apm." + str2);
    this.c = System.getProperty("com.crittercism.apiUrl", "https://api." + str2);
    this.d = System.getProperty("com.crittercism.txnUrl", "https://txn.ingest." + str2);
    this.e = System.getProperty("com.crittercism.appLoadUrl", "https://appload.ingest." + str2);
  }
  
  public final String a()
  {
    return this.c;
  }
  
  public final String b()
  {
    return this.b;
  }
  
  public final String c()
  {
    return this.e;
  }
  
  public final String d()
  {
    return this.d;
  }
  
  public static class a
    extends Exception
  {
    public a(String paramString)
    {
      super();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */