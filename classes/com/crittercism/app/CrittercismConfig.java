package com.crittercism.app;

import android.os.Build.VERSION;
import crittercism.android.dx;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

public class CrittercismConfig
{
  public static final String API_VERSION = "5.0.8";
  protected String a = "com.crittercism/dumps";
  private String b = null;
  private boolean c = false;
  private boolean d = false;
  private boolean e = true;
  private boolean f = false;
  private boolean g = b();
  private String h = "Developer Reply";
  private String i = null;
  private List j = new LinkedList();
  private List k = new LinkedList();
  
  public CrittercismConfig() {}
  
  public CrittercismConfig(CrittercismConfig paramCrittercismConfig)
  {
    this.b = paramCrittercismConfig.b;
    this.c = paramCrittercismConfig.c;
    this.d = paramCrittercismConfig.d;
    this.e = paramCrittercismConfig.e;
    this.f = paramCrittercismConfig.f;
    this.g = paramCrittercismConfig.g;
    this.a = paramCrittercismConfig.a;
    this.h = paramCrittercismConfig.h;
    setURLBlacklistPatterns(paramCrittercismConfig.j);
    setPreserveQueryStringPatterns(paramCrittercismConfig.k);
    this.i = paramCrittercismConfig.i;
  }
  
  @Deprecated
  public CrittercismConfig(JSONObject paramJSONObject)
  {
    this.b = a(paramJSONObject, "customVersionName", this.b);
    this.d = a(paramJSONObject, "includeVersionCode", this.d);
    this.e = a(paramJSONObject, "installNdk", this.e);
    this.c = a(paramJSONObject, "delaySendingAppLoad", this.c);
    this.f = a(paramJSONObject, "shouldCollectLogcat", this.f);
    this.a = a(paramJSONObject, "nativeDumpPath", this.a);
    this.h = a(paramJSONObject, "notificationTitle", this.h);
    this.g = a(paramJSONObject, "installApm", this.g);
  }
  
  private static int a(String paramString)
  {
    int m = 0;
    if (paramString != null) {
      m = paramString.hashCode();
    }
    return m;
  }
  
  private static String a(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    if (paramJSONObject.has(paramString1)) {}
    try
    {
      String str = paramJSONObject.getString(paramString1);
      paramString2 = str;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return paramString2;
  }
  
  protected static boolean a(String paramString1, String paramString2)
  {
    boolean bool;
    if (paramString1 == null) {
      if (paramString2 == null) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = paramString1.equals(paramString2);
    }
  }
  
  private static boolean a(JSONObject paramJSONObject, String paramString, boolean paramBoolean)
  {
    if (paramJSONObject.has(paramString)) {}
    try
    {
      boolean bool = paramJSONObject.getBoolean(paramString);
      paramBoolean = bool;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return paramBoolean;
  }
  
  private static final boolean b()
  {
    if ((Build.VERSION.SDK_INT >= 10) && (Build.VERSION.SDK_INT <= 21)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public List a()
  {
    return getURLBlacklistPatterns();
  }
  
  public final boolean delaySendingAppLoad()
  {
    return this.c;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof CrittercismConfig)) {}
    for (;;)
    {
      return bool;
      CrittercismConfig localCrittercismConfig = (CrittercismConfig)paramObject;
      if ((this.c == localCrittercismConfig.c) && (this.f == localCrittercismConfig.f) && (isNdkCrashReportingEnabled() == localCrittercismConfig.isNdkCrashReportingEnabled()) && (isOptmzEnabled() == localCrittercismConfig.isOptmzEnabled()) && (isVersionCodeToBeIncludedInVersionString() == localCrittercismConfig.isVersionCodeToBeIncludedInVersionString()) && (a(this.b, localCrittercismConfig.b)) && (a(this.h, localCrittercismConfig.h)) && (a(this.a, localCrittercismConfig.a)) && (this.j.equals(localCrittercismConfig.j)) && (this.k.equals(localCrittercismConfig.k)) && (a(this.i, localCrittercismConfig.i))) {
        bool = true;
      }
    }
  }
  
  public final String getCustomVersionName()
  {
    return this.b;
  }
  
  public List getPreserveQueryStringPatterns()
  {
    return new LinkedList(this.k);
  }
  
  public final String getRateMyAppTestTarget()
  {
    return this.i;
  }
  
  public List getURLBlacklistPatterns()
  {
    return new LinkedList(this.j);
  }
  
  public int hashCode()
  {
    int m = 1;
    int n = 31 * (31 * (31 * (31 * (31 * (0 + a(this.b)) + a(this.h)) + a(this.a)) + a(this.i)) + this.j.hashCode()) + this.k.hashCode();
    int i1;
    int i3;
    label97:
    int i5;
    label116:
    int i7;
    label135:
    int i8;
    if (this.c)
    {
      i1 = m;
      int i2 = i1 + 0 << 1;
      if (!this.f) {
        break label176;
      }
      i3 = m;
      int i4 = i3 + i2 << 1;
      if (!isNdkCrashReportingEnabled()) {
        break label182;
      }
      i5 = m;
      int i6 = i5 + i4 << 1;
      if (!isOptmzEnabled()) {
        break label188;
      }
      i7 = m;
      i8 = i7 + i6 << 1;
      if (!isVersionCodeToBeIncludedInVersionString()) {
        break label194;
      }
    }
    for (;;)
    {
      int i9 = i8 + m;
      return n * 31 + Integer.valueOf(i9).hashCode();
      i1 = 0;
      break;
      label176:
      i3 = 0;
      break label97;
      label182:
      i5 = 0;
      break label116;
      label188:
      i7 = 0;
      break label135;
      label194:
      m = 0;
    }
  }
  
  public final boolean isLogcatReportingEnabled()
  {
    return this.f;
  }
  
  public final boolean isNdkCrashReportingEnabled()
  {
    return this.e;
  }
  
  @Deprecated
  public final boolean isOptmzEnabled()
  {
    return this.g;
  }
  
  public final boolean isServiceMonitoringEnabled()
  {
    return isOptmzEnabled();
  }
  
  public final boolean isVersionCodeToBeIncludedInVersionString()
  {
    return this.d;
  }
  
  public final void setCustomVersionName(String paramString)
  {
    this.b = paramString;
  }
  
  public final void setDelaySendingAppLoad(boolean paramBoolean)
  {
    this.c = paramBoolean;
  }
  
  public final void setLogcatReportingEnabled(boolean paramBoolean)
  {
    this.f = paramBoolean;
  }
  
  public final void setNdkCrashReportingEnabled(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }
  
  @Deprecated
  public final void setOptmzEnabled(boolean paramBoolean)
  {
    if ((!b()) && (paramBoolean)) {
      dx.a("OPTMZ is currently only allowed for api levels 10 to 21.  APM will not be installed");
    }
    for (;;)
    {
      return;
      this.g = paramBoolean;
    }
  }
  
  public void setPreserveQueryStringPatterns(List paramList)
  {
    this.k.clear();
    if (paramList != null) {
      this.k.addAll(paramList);
    }
  }
  
  public final void setRateMyAppTestTarget(String paramString)
  {
    this.i = paramString;
  }
  
  public final void setServiceMonitoringEnabled(boolean paramBoolean)
  {
    setOptmzEnabled(paramBoolean);
  }
  
  public void setURLBlacklistPatterns(List paramList)
  {
    this.j.clear();
    if (paramList != null) {
      this.j.addAll(paramList);
    }
  }
  
  public final void setVersionCodeToBeIncludedInVersionString(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/crittercism/app/CrittercismConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */