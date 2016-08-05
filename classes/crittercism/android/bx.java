package crittercism.android;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bx
{
  private static at a = null;
  private static Context b = null;
  private static bf c = null;
  private static cb d = null;
  
  public static void a(Context paramContext)
  {
    b = paramContext;
  }
  
  public static void a(at paramat)
  {
    a = paramat;
  }
  
  public static void a(bf parambf)
  {
    c = parambf;
  }
  
  public static void a(cb paramcb)
  {
    d = paramcb;
  }
  
  public static class y
    extends bx.g
  {
    public y()
    {
      super();
    }
    
    public final String a()
    {
      return "wifi";
    }
  }
  
  public static class q
    extends bx.g
  {
    public q()
    {
      super();
    }
    
    public final String a()
    {
      return "mobile_network";
    }
  }
  
  static class g
    implements bw
  {
    private JSONObject a = null;
    
    public g(int paramInt)
    {
      bx.b();
      bx.c();
      this.a = a(paramInt);
    }
    
    private static JSONObject a(int paramInt)
    {
      int i = 1;
      JSONObject localJSONObject;
      if (!bx.c().c) {
        localJSONObject = null;
      }
      label138:
      label197:
      for (;;)
      {
        return localJSONObject;
        if (!ConnectivityManager.isNetworkTypeValid(paramInt))
        {
          localJSONObject = null;
        }
        else
        {
          NetworkInfo localNetworkInfo = ((ConnectivityManager)bx.b().getSystemService("connectivity")).getNetworkInfo(paramInt);
          localJSONObject = new JSONObject();
          if (localNetworkInfo != null) {
            for (;;)
            {
              try
              {
                localJSONObject.put("available", localNetworkInfo.isAvailable());
                localJSONObject.put("connected", localNetworkInfo.isConnected());
                if (!localNetworkInfo.isConnected()) {
                  localJSONObject.put("connecting", localNetworkInfo.isConnectedOrConnecting());
                }
                localJSONObject.put("failover", localNetworkInfo.isFailover());
                if (paramInt != 0) {
                  break label138;
                }
                if (i == 0) {
                  break;
                }
                localJSONObject.put("roaming", localNetworkInfo.isRoaming());
              }
              catch (JSONException localJSONException)
              {
                dx.c();
                localJSONObject = null;
              }
              break;
              i = 0;
            }
          }
          localJSONObject.put("available", false);
          localJSONObject.put("connected", false);
          localJSONObject.put("connecting", false);
          localJSONObject.put("failover", false);
          if (paramInt == 0) {}
          for (;;)
          {
            if (i == 0) {
              break label197;
            }
            localJSONObject.put("roaming", false);
            break;
            i = 0;
          }
        }
      }
    }
    
    public String a()
    {
      return null;
    }
    
    public JSONObject c()
    {
      return this.a;
    }
  }
  
  public static class v
    implements bw
  {
    private String a = null;
    
    public v()
    {
      try
      {
        BigInteger.valueOf(-1L);
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        this.a = BigInteger.valueOf(localStatFs.getBlockCount()).multiply(BigInteger.valueOf(localStatFs.getBlockSize())).toString();
        return;
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          this.a = null;
        }
      }
    }
    
    public final String a()
    {
      return "sd_space_total";
    }
  }
  
  public static class u
    implements bw
  {
    private String a = null;
    
    public u()
    {
      try
      {
        BigInteger.valueOf(-1L);
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        this.a = BigInteger.valueOf(localStatFs.getAvailableBlocks()).multiply(BigInteger.valueOf(localStatFs.getBlockSize())).toString();
        return;
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          this.a = null;
        }
      }
    }
    
    public final String a()
    {
      return "sd_space_free";
    }
  }
  
  public static class t
    implements bw
  {
    private Integer a = null;
    
    public t()
    {
      bx.b();
      int i = bx.b().getResources().getConfiguration().orientation;
      Display localDisplay;
      if (i == 0)
      {
        localDisplay = ((WindowManager)bx.b().getSystemService("window")).getDefaultDisplay();
        if (localDisplay.getWidth() != localDisplay.getHeight()) {
          break label69;
        }
        i = 3;
      }
      for (;;)
      {
        this.a = Integer.valueOf(i);
        return;
        label69:
        if (localDisplay.getWidth() > localDisplay.getHeight()) {
          i = 2;
        } else {
          i = 1;
        }
      }
    }
    
    public final String a()
    {
      return "orientation";
    }
  }
  
  public static class m
    implements bw
  {
    private Long a = null;
    
    public final String a()
    {
      return "memory_total";
    }
  }
  
  public static class n
    implements bw
  {
    private Integer a = null;
    
    public n()
    {
      Debug.MemoryInfo localMemoryInfo = new Debug.MemoryInfo();
      Debug.getMemoryInfo(localMemoryInfo);
      this.a = Integer.valueOf(1024 * (localMemoryInfo.dalvikPss + localMemoryInfo.nativePss + localMemoryInfo.otherPss));
    }
    
    public final String a()
    {
      return "memory_usage";
    }
  }
  
  public static class l
    implements bw
  {
    private JSONArray a = null;
    
    public l()
    {
      bx.c();
      bx.d();
      if (bx.c().a) {
        this.a = bx.d().a();
      }
    }
    
    public final String a()
    {
      return "logcat";
    }
  }
  
  public static class j
    implements bw
  {
    private String a = null;
    
    public j()
    {
      try
      {
        BigInteger.valueOf(-1L);
        StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
        this.a = BigInteger.valueOf(localStatFs.getBlockCount()).multiply(BigInteger.valueOf(localStatFs.getBlockSize())).toString();
        return;
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          this.a = null;
        }
      }
    }
    
    public final String a()
    {
      return "disk_space_total";
    }
  }
  
  public static class i
    implements bw
  {
    private String a = null;
    
    public i()
    {
      try
      {
        BigInteger.valueOf(-1L);
        StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
        this.a = BigInteger.valueOf(localStatFs.getAvailableBlocks()).multiply(BigInteger.valueOf(localStatFs.getBlockSize())).toString();
        return;
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          this.a = null;
        }
      }
    }
    
    public final String a()
    {
      return "disk_space_free";
    }
  }
  
  public static class e
    implements bw
  {
    private Double a = null;
    
    public e()
    {
      bx.b();
      double d1 = 1.0D;
      Intent localIntent = bx.b().getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      int i = localIntent.getIntExtra("level", -1);
      double d2 = localIntent.getIntExtra("scale", -1);
      if ((i >= 0) && (d2 > 0.0D)) {
        d1 = i / d2;
      }
      this.a = Double.valueOf(d1);
    }
    
    public final String a()
    {
      return "battery_level";
    }
  }
  
  public static class a
    implements bw
  {
    private String a = null;
    
    public a()
    {
      bx.c();
      bx.b();
      if (bx.c().b) {
        str = ((ActivityManager.RunningTaskInfo)((ActivityManager)bx.b().getSystemService("activity")).getRunningTasks(1).get(0)).topActivity.flattenToShortString().replace("/", "");
      }
      this.a = str;
    }
    
    public final String a()
    {
      return "activity";
    }
  }
  
  public static class x
    implements bw
  {
    public final String a()
    {
      return "system_version";
    }
  }
  
  public static class w
    implements bw
  {
    public final String a()
    {
      return "system";
    }
  }
  
  public static class s
    implements bw
  {
    public final String a()
    {
      return "name";
    }
  }
  
  public static class r
    implements bw
  {
    public final String a()
    {
      return "model";
    }
  }
  
  public static class k
    implements bw
  {
    public String a = null;
    
    public k()
    {
      bx.b();
      this.a = bx.b().getResources().getConfiguration().locale.getLanguage();
      if ((this.a == null) || (this.a.length() == 0)) {
        this.a = "en";
      }
    }
    
    public final String a()
    {
      return "locale";
    }
  }
  
  public static class aa
    implements bw
  {
    private Float a = null;
    
    public aa()
    {
      bx.b();
      this.a = Float.valueOf(bx.b().getResources().getDisplayMetrics().ydpi);
    }
    
    public final String a()
    {
      return "ydpi";
    }
  }
  
  public static class z
    implements bw
  {
    private Float a = null;
    
    public z()
    {
      bx.b();
      this.a = Float.valueOf(bx.b().getResources().getDisplayMetrics().xdpi);
    }
    
    public final String a()
    {
      return "xdpi";
    }
  }
  
  public static class h
    implements bw
  {
    private Float a = null;
    
    public h()
    {
      bx.b();
      this.a = Float.valueOf(bx.b().getResources().getDisplayMetrics().density);
    }
    
    public final String a()
    {
      return "dpi";
    }
  }
  
  public static class p
    implements bw
  {
    public Integer a = Integer.valueOf(0);
    
    public p()
    {
      bx.b();
      try
      {
        String str = ((TelephonyManager)bx.b().getSystemService("phone")).getNetworkOperator();
        if (str != null) {
          this.a = Integer.valueOf(Integer.parseInt(str.substring(3)));
        }
        new StringBuilder("mobileNetworkCode == ").append(this.a);
        dx.b();
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    
    public final String a()
    {
      return "mobile_network_code";
    }
  }
  
  public static class o
    implements bw
  {
    public Integer a = Integer.valueOf(0);
    
    public o()
    {
      bx.b();
      try
      {
        String str = ((TelephonyManager)bx.b().getSystemService("phone")).getNetworkOperator();
        if (str != null) {
          this.a = Integer.valueOf(Integer.parseInt(str.substring(0, 3)));
        }
        new StringBuilder("mobileCountryCode == ").append(this.a);
        dx.b();
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    
    public final String a()
    {
      return "mobile_country_code";
    }
  }
  
  public static class f
    implements bw
  {
    public String a = null;
    
    public f()
    {
      bx.b();
      try
      {
        String str2 = ((TelephonyManager)bx.b().getSystemService("phone")).getNetworkOperatorName();
        str1 = str2;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          String str1 = Build.BRAND;
        }
      }
      this.a = str1;
      new StringBuilder("carrier == ").append(this.a);
      dx.b();
    }
    
    public final String a()
    {
      return "carrier";
    }
  }
  
  public static class d
    implements bw
  {
    public final String a()
    {
      return "arch";
    }
  }
  
  public static class b
    implements bw
  {
    private Integer a = null;
    
    public b()
    {
      bx.a();
      this.a = Integer.valueOf(bx.a().b);
    }
    
    public final String a()
    {
      return "app_version_code";
    }
  }
  
  public static class c
    implements bw
  {
    private String a = null;
    
    public c()
    {
      bx.a();
      this.a = bx.a().a;
    }
    
    public final String a()
    {
      return "app_version";
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/bx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */