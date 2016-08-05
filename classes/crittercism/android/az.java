package crittercism.android;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.ConditionVariable;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.MessageQueue.IdleHandler;
import android.os.Process;
import com.crittercism.app.CrittercismConfig;
import com.crittercism.app.Transaction;
import com.crittercism.integrations.PluginException;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

public final class az
  implements au, aw, ax, f
{
  static az a;
  public dt A = null;
  int B = 0;
  public boolean C = false;
  private String D = null;
  private bs E;
  private bs F;
  private g G = null;
  private at H;
  private boolean I = false;
  private String J = "";
  public boolean b = false;
  public Context c = null;
  public final ConditionVariable d = new ConditionVariable(false);
  public final ConditionVariable e = new ConditionVariable(false);
  public dw f = new dw();
  bs g;
  bs h;
  bs i;
  bs j;
  bs k;
  bs l;
  bs m;
  bs n;
  bs o;
  cv p = null;
  public dg q = null;
  ExecutorService r = Executors.newCachedThreadPool(new dz());
  public ExecutorService s = Executors.newSingleThreadExecutor(new dz());
  public boolean t = false;
  public bb u;
  protected e v = new e(this.s);
  public dr w;
  dv x = null;
  public bi y;
  public Map z = new HashMap();
  
  public static az A()
  {
    if (a == null) {
      a = new az();
    }
    return a;
  }
  
  private static boolean F()
  {
    boolean bool = false;
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    for (int i1 = 0;; i1++) {
      if (i1 < arrayOfStackTraceElement.length)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i1];
        if ((localStackTraceElement.getMethodName().equals("onCreate")) || (localStackTraceElement.getMethodName().equals("onResume"))) {
          bool = true;
        }
      }
      else
      {
        return bool;
      }
    }
  }
  
  private void G()
  {
    int i1 = Process.myUid();
    int i2 = Process.myPid();
    ActivityManager localActivityManager = (ActivityManager)this.c.getSystemService("activity");
    Iterator localIterator1 = localActivityManager.getRunningAppProcesses().iterator();
    int i3 = 0;
    if (localIterator1.hasNext()) {
      if (((ActivityManager.RunningAppProcessInfo)localIterator1.next()).uid != i1) {
        break label135;
      }
    }
    label135:
    for (int i4 = i3 + 1;; i4 = i3)
    {
      i3 = i4;
      break;
      if (i3 <= 1)
      {
        this.t = false;
        return;
      }
      for (;;)
      {
        Iterator localIterator2 = localActivityManager.getRunningServices(Integer.MAX_VALUE).iterator();
        if (localIterator2.hasNext())
        {
          if (((ActivityManager.RunningServiceInfo)localIterator2.next()).pid != i2) {
            break;
          }
          this.t = true;
        }
      }
    }
  }
  
  private String H()
  {
    try
    {
      if ((this.J == null) || (this.J.equals(""))) {
        this.J = this.c.getPackageName();
      }
      return this.J;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        dx.c("Call to getPackageName() failed.  Please contact us at support@crittercism.com.");
        this.J = new String();
      }
    }
  }
  
  public final boolean B()
  {
    this.d.block();
    return this.f.b();
  }
  
  public final void C()
  {
    if (this.A != null) {
      this.A.d();
    }
  }
  
  public final String D()
  {
    PackageManager localPackageManager = this.c.getPackageManager();
    String str1 = H();
    String str2 = null;
    dn localdn;
    if ((str1 != null) && (str1.length() > 0))
    {
      localdn = dp.a(localPackageManager.getInstallerPackageName(str1));
      if (localdn == null) {
        break label55;
      }
    }
    for (str2 = localdn.a(str1).a();; str2 = this.u.getRateMyAppTestTarget())
    {
      return str2;
      label55:
      dx.c("Could not find app market for this app.  Will try rate-my-app test target in config.");
    }
  }
  
  public final void E()
  {
    if (this.t) {}
    for (;;)
    {
      return;
      di local3 = new di()
      {
        public final void a()
        {
          if (jdField_this.f.b()) {}
          for (;;)
          {
            return;
            cu localcu = new cu(jdField_this);
            JSONObject localJSONObject = jdField_this.x.a();
            localcu.a.put("metadata", localJSONObject);
            new dj(localcu, new dc(new db(az.this.u.b(), "/android_v2/update_user_metadata").a()), new dd(jdField_this.x)).run();
          }
        }
      };
      if (!this.q.a(local3)) {
        this.r.execute(local3);
      }
    }
  }
  
  public final AlertDialog a(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = null;
    int i1 = 0;
    if (this.f.b())
    {
      dx.b("User has opted out of crittercism.  generateRateMyAppAlertDialog returning null.");
      if (i1 != 0) {
        break label92;
      }
    }
    for (;;)
    {
      return (AlertDialog)localObject;
      if (!(paramContext instanceof Activity))
      {
        dx.b("Context object must be an instance of Activity for AlertDialog to form correctly.  generateRateMyAppAlertDialog returning null.");
        break;
      }
      if ((paramString2 == null) || ((paramString2 != null) && (paramString2.length() == 0)))
      {
        dx.b("Message has to be a non-empty string.  generateRateMyAppAlertDialog returning null.");
        break;
      }
      if (Build.VERSION.SDK_INT < 5)
      {
        dx.b("Rate my app not supported below api level 5");
        break;
      }
      i1 = 1;
      break;
      label92:
      final String str = D();
      if (str == null)
      {
        dx.b("Cannot create proper URI to open app market.  Returning null.");
      }
      else
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
        localBuilder.setTitle(paramString1).setMessage(paramString2);
        try
        {
          AlertDialog localAlertDialog = localBuilder.create();
          localObject = localAlertDialog;
          ((AlertDialog)localObject).setButton(-1, "Yes", new DialogInterface.OnClickListener()
          {
            public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              try
              {
                az.A().a(str);
                return;
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  dx.c("YES button failed.  Email support@crittercism.com.");
                }
              }
            }
          });
          ((AlertDialog)localObject).setButton(-2, "No", new DialogInterface.OnClickListener()
          {
            public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              try
              {
                az.A().C();
                return;
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  dx.c("NO button failed.  Email support@crittercism.com.");
                }
              }
            }
          });
          ((AlertDialog)localObject).setButton(-3, "Maybe Later", new DialogInterface.OnClickListener()
          {
            public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              try
              {
                az.A();
                return;
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  dx.c("MAYBE LATER button failed.  Email support@crittercism.com.");
                }
              }
            }
          });
        }
        catch (Exception localException)
        {
          dx.b("Failed to create AlertDialog instance from AlertDialog.Builder.  Did you remember to call Looper.prepare() before calling Crittercism.generateRateMyAppAlertDialog()?");
        }
      }
    }
  }
  
  public final String a()
  {
    String str = this.D;
    if (str == null) {
      str = "";
    }
    return str;
  }
  
  public final String a(String paramString1, String paramString2)
  {
    String str = null;
    SharedPreferences localSharedPreferences = this.c.getSharedPreferences(paramString1, 0);
    if (localSharedPreferences != null) {
      str = localSharedPreferences.getString(paramString2, null);
    }
    return str;
  }
  
  public final void a(Context paramContext, String paramString, CrittercismConfig paramCrittercismConfig)
  {
    dx.a("Initializing Crittercism 5.0.8 for App ID " + paramString);
    bn localbn = new bn(paramString);
    this.D = paramString;
    this.u = new bb(localbn, paramCrittercismConfig);
    this.c = paramContext;
    this.H = new at(this.c, this.u);
    this.J = paramContext.getPackageName();
    this.w = new dr(paramContext);
    G();
    long l1 = 60000000000L;
    if (this.t) {
      l1 = 12000000000L;
    }
    this.p = new cv(l1);
    if (!F()) {
      dx.c("Crittercism should be initialized in onCreate() of MainActivity");
    }
    bx.a(this.H);
    bx.a(this.c);
    bx.a(new cc());
    bx.a(new bf(this.c, this.u));
    for (;;)
    {
      try
      {
        this.v.a(this.u.a());
        this.v.b(this.u.getPreserveQueryStringPatterns());
        this.G = new g(this, new URL(this.u.c() + "/api/apm/network"));
        this.v.a(this.G);
        this.v.a(this);
        new dy(this.G, "OPTMZ").start();
        if (!h.a(this.c).exists())
        {
          boolean bool = this.u.isServiceMonitoringEnabled();
          if (bool) {
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        Thread.UncaughtExceptionHandler localUncaughtExceptionHandler;
        d locald;
        new StringBuilder("Exception in startApm: ").append(localException.getClass().getName());
        dx.b();
        dx.c();
        continue;
        dx.b();
        ((Application)this.c).registerActivityLifecycleCallbacks(new av(this.c, this));
        continue;
        dx.a("API Level is less than 14. Automatic breadcrumbs are not supported.");
        continue;
      }
      this.q = new dg(this.u, paramContext, this, this, this);
      if (!this.t) {
        dx.a(new ec(this, this.s, this.q, this.f));
      }
      localUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
      if (!(localUncaughtExceptionHandler instanceof ay)) {
        Thread.setDefaultUncaughtExceptionHandler(new ay(this, localUncaughtExceptionHandler));
      }
      if (Build.VERSION.SDK_INT < 14) {
        continue;
      }
      if ((this.c instanceof Application)) {
        continue;
      }
      dx.c("Application context not provided. Automatic breadcrumbs will not be recorded.");
      if (!this.t)
      {
        bg.b(this);
        if (Looper.myLooper() == Looper.getMainLooper()) {
          Looper.myQueue().addIdleHandler(new a((byte)0));
        }
      }
      new dy(this.q).start();
      this.b = true;
      return;
      locald = new d(this.c);
      this.I = new i(this.v, locald).a();
      new StringBuilder("installedApm = ").append(this.I);
      dx.b();
    }
  }
  
  public final void a(bh parambh)
  {
    if (this.y == null) {}
    for (;;)
    {
      return;
      bg.a(parambh);
      bg.i();
      if (parambh.a)
      {
        this.y.a(parambh.b, TimeUnit.SECONDS);
        this.y.b();
      }
    }
  }
  
  public final void a(final c paramc)
  {
    di local8 = new di()
    {
      public final void a()
      {
        az.this.l.a(paramc);
      }
    };
    if (!this.q.a(local8)) {
      this.s.execute(local8);
    }
  }
  
  public final void a(final ci paramci)
  {
    if (this.f.b()) {}
    for (;;)
    {
      return;
      di local9 = new di()
      {
        public final void a()
        {
          az.this.m.a(paramci);
        }
      };
      if (!this.q.a(local9)) {
        this.s.execute(local9);
      }
    }
  }
  
  public final void a(h paramh)
  {
    if (this.G == null) {}
    for (;;)
    {
      return;
      if ((paramh.a) && (!paramh.c))
      {
        dx.a("Enabling OPTMZ");
        this.G.a(paramh.d, TimeUnit.SECONDS);
        this.G.a();
      }
    }
  }
  
  public final void a(String paramString)
  {
    if (this.A != null) {
      this.A.d();
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setData(Uri.parse(paramString));
    this.c.startActivity(localIntent);
  }
  
  public final void a(String paramString1, String paramString2, int paramInt)
  {
    SharedPreferences localSharedPreferences = this.c.getSharedPreferences(paramString1, 0);
    if (localSharedPreferences != null)
    {
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      if (localEditor != null)
      {
        localEditor.remove(paramString2);
        localEditor.putInt(paramString2, paramInt);
        localEditor.commit();
      }
    }
  }
  
  public final void a(String paramString1, String paramString2, String paramString3)
  {
    SharedPreferences localSharedPreferences = this.c.getSharedPreferences(paramString1, 0);
    if (localSharedPreferences != null)
    {
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      if (localEditor != null)
      {
        localEditor.remove(paramString2);
        localEditor.putString(paramString2, paramString3);
        localEditor.commit();
      }
    }
  }
  
  public final void a(String paramString, URL paramURL, long paramLong1, long paramLong2, long paramLong3, int paramInt, Exception paramException, long paramLong4)
  {
    if (paramString == null) {
      dx.b("Null HTTP request method provided. Endpoint will not be logged.");
    }
    for (;;)
    {
      return;
      String str = paramString.toUpperCase(Locale.US);
      HashSet localHashSet = new HashSet();
      localHashSet.add("GET");
      localHashSet.add("POST");
      localHashSet.add("HEAD");
      localHashSet.add("PUT");
      localHashSet.add("DELETE");
      localHashSet.add("TRACE");
      localHashSet.add("OPTIONS");
      localHashSet.add("CONNECT");
      localHashSet.add("PATCH");
      if (!localHashSet.contains(str)) {
        dx.c("Logging endpoint with invalid HTTP request method: " + paramString);
      }
      if (paramURL == null)
      {
        dx.b("Null URL provided. Endpoint will not be logged");
      }
      else if ((paramLong2 < 0L) || (paramLong3 < 0L))
      {
        dx.b("Invalid byte values. Bytes need to be non-negative. Endpoint will not be logged.");
      }
      else
      {
        if (paramInt != 0) {
          if ((paramInt < 100) || (paramInt >= 600)) {
            dx.c("Logging endpoint with invalid HTTP response code: " + Integer.toString(paramInt));
          }
        }
        b localb;
        for (;;)
        {
          localb = new d(this.c).a();
          if (paramLong1 >= 0L) {
            break label285;
          }
          dx.b("Invalid latency. Endpoint will not be logged.");
          break;
          if (paramException == null) {
            dx.c("Logging endpoint with null error and response code of 0.");
          }
        }
        label285:
        if (paramLong4 < 0L)
        {
          dx.b("Invalid start time. Endpoint will not be logged.");
        }
        else
        {
          c localc = new c();
          localc.f = str;
          localc.a(paramURL.toExternalForm());
          localc.b(paramLong2);
          localc.d(paramLong3);
          localc.e = paramInt;
          localc.j = localb;
          localc.e(paramLong4);
          localc.f(paramLong4 + paramLong1);
          if (bc.b()) {
            localc.a(bc.a());
          }
          localc.a(paramException);
          this.v.a(localc, c.a.l);
        }
      }
    }
  }
  
  public final void a(Throwable paramThrowable)
  {
    if (this.q == null) {
      dx.b("Unable to handle application crash. Crittercism not yet initialized");
    }
    for (;;)
    {
      return;
      this.q.b();
      dq.a(this.c, true);
      if (!this.f.b()) {
        if (this.t)
        {
          bk localbk1 = new bk(paramThrowable, Thread.currentThread().getId());
          JSONArray localJSONArray = new JSONArray().put(localbk1.b());
          new dj(new cu(this).a(br.e.f(), localJSONArray), new dc(new db(this.u.b(), "/android_v2/handle_crashes").a()), null).run();
        }
        else
        {
          List localList = bg.a(this, paramThrowable instanceof PluginException);
          bk localbk2 = new bk(paramThrowable, Thread.currentThread().getId());
          localbk2.a("crashed_session", this.k);
          if (this.F.b() > 0) {
            localbk2.a("previous_session", this.F);
          }
          localbk2.a(this.l);
          localbk2.b = new bo(this.m).a;
          localbk2.a();
          localbk2.a(localList);
          this.j.a(localbk2);
          df localdf = new df(this.c);
          localdf.a(this.g, new da.a(), this.u.e(), "/v0/appload", null, this, new cs.b());
          localdf.a(this.h, new da.a(), this.u.b(), "/android_v2/handle_exceptions", null, this, new cu.a());
          localdf.a(this.i, new da.a(), this.u.b(), "/android_v2/handle_ndk_crashes", null, this, new cu.a());
          localdf.a(this.j, new da.a(), this.u.b(), "/android_v2/handle_crashes", null, this, new cu.a());
          try
          {
            localdf.a();
          }
          catch (InterruptedException localInterruptedException)
          {
            new StringBuilder("InterruptedException in logCrashException: ").append(localInterruptedException.getMessage());
            dx.b();
            dx.c();
          }
          catch (Throwable localThrowable)
          {
            new StringBuilder("Unexpected throwable in logCrashException: ").append(localThrowable.getMessage());
            dx.b();
            dx.c();
          }
        }
      }
    }
  }
  
  public final void a(final JSONObject paramJSONObject)
  {
    if (this.t) {}
    for (;;)
    {
      return;
      di local2 = new di()
      {
        public final void a()
        {
          if (jdField_this.f.b()) {}
          for (;;)
          {
            return;
            jdField_this.x.a(paramJSONObject);
            if (jdField_this.x.b()) {
              jdField_this.E();
            }
          }
        }
      };
      if (!this.q.a(local2)) {
        this.s.execute(local2);
      }
    }
  }
  
  public final int b(String paramString)
  {
    int i1;
    if (this.t)
    {
      dx.c("Transactions are not supported for services. Returning default value of -1 for " + paramString + ".");
      i1 = -1;
      return i1;
    }
    for (;;)
    {
      synchronized (this.z)
      {
        Transaction localTransaction = (Transaction)this.z.get(paramString);
        if (localTransaction != null) {
          i1 = localTransaction.d();
        }
      }
      i1 = -1;
    }
  }
  
  public final int b(String paramString1, String paramString2)
  {
    int i1 = 0;
    SharedPreferences localSharedPreferences = this.c.getSharedPreferences(paramString1, 0);
    if (localSharedPreferences != null) {
      i1 = localSharedPreferences.getInt(paramString2, 0);
    }
    return i1;
  }
  
  public final String b()
  {
    return this.H.a;
  }
  
  /**
   * @deprecated
   */
  public final void b(final Throwable paramThrowable)
  {
    if (paramThrowable == null) {}
    for (;;)
    {
      try
      {
        dx.c("Calling logHandledException with a null java.lang.Throwable. Nothing will be reported to Crittercism");
        return;
      }
      finally {}
      if (this.t)
      {
        di local5 = new di()
        {
          public final void a()
          {
            if (az.this.f.b()) {}
            for (;;)
            {
              return;
              synchronized (az.this.p)
              {
                if (az.this.B < 10)
                {
                  bk localbk = new bk(paramThrowable, this.b);
                  localbk.a("current_session", az.this.k);
                  localbk.a(az.this.l);
                  localbk.f = "he";
                  if (az.this.p.a())
                  {
                    JSONArray localJSONArray = new JSONArray().put(localbk.b());
                    new dj(new cu(az.a).a(br.b.f(), localJSONArray), new dc(new db(az.this.u.b(), "/android_v2/handle_exceptions").a()), null).run();
                    az localaz = az.this;
                    localaz.B = (1 + localaz.B);
                    az.this.p.b();
                  }
                }
              }
            }
          }
        };
        if (!this.q.a(local5)) {
          this.s.execute(local5);
        }
      }
      else
      {
        di local6 = new di()
        {
          public final void a()
          {
            if (az.this.f.b()) {}
            for (;;)
            {
              return;
              bk localbk = new bk(paramThrowable, this.b);
              localbk.a("current_session", az.this.k);
              localbk.f = "he";
              if (az.this.h.a(localbk))
              {
                az.a.a(new by(localbk.c, localbk.d));
                if (az.this.p.a())
                {
                  df localdf = new df(az.this.c);
                  localdf.a(az.this.h, new da.a(), az.this.u.b(), "/android_v2/handle_exceptions", null, az.a, new cu.a());
                  localdf.a(az.this.q, az.this.r);
                  az.this.p.b();
                }
              }
            }
          }
        };
        if (!this.q.a(local6)) {
          this.s.execute(local6);
        }
      }
    }
  }
  
  public final String c()
  {
    String str = "";
    if (this.w != null) {
      str = this.w.a();
    }
    return str;
  }
  
  public final boolean c(String paramString1, String paramString2)
  {
    boolean bool = false;
    SharedPreferences localSharedPreferences = this.c.getSharedPreferences(paramString1, 0);
    if (localSharedPreferences != null) {
      bool = localSharedPreferences.getBoolean(paramString2, false);
    }
    return bool;
  }
  
  public final String d()
  {
    return "5.0.8";
  }
  
  public final int e()
  {
    int i1 = -1;
    if (this.f != null) {
      i1 = Integer.valueOf(this.f.a().a).intValue();
    }
    return i1;
  }
  
  public final String f()
  {
    return new bx.f().a;
  }
  
  public final int g()
  {
    return new bx.o().a.intValue();
  }
  
  public final int h()
  {
    return new bx.p().a.intValue();
  }
  
  public final String i()
  {
    return "Android";
  }
  
  public final String j()
  {
    return Build.MODEL;
  }
  
  public final String k()
  {
    return Build.VERSION.RELEASE;
  }
  
  public final dw l()
  {
    return this.f;
  }
  
  public final dt m()
  {
    return this.A;
  }
  
  public final bs n()
  {
    return this.g;
  }
  
  public final bs o()
  {
    return this.h;
  }
  
  public final bs p()
  {
    return this.E;
  }
  
  public final bs q()
  {
    return this.i;
  }
  
  public final bs r()
  {
    return this.j;
  }
  
  public final bs s()
  {
    return this.k;
  }
  
  public final bs t()
  {
    return this.l;
  }
  
  public final bs u()
  {
    return this.F;
  }
  
  public final bs v()
  {
    return this.m;
  }
  
  public final bs w()
  {
    return this.n;
  }
  
  public final bs x()
  {
    return this.o;
  }
  
  public final dv y()
  {
    return this.x;
  }
  
  public final void z()
  {
    if (this.t) {}
    for (this.k = new bs(this.c, br.f).a(this.c);; this.k = new bs(this.c, br.f))
    {
      this.F = new bs(this.c, br.h);
      this.l = new bs(this.c, br.g);
      this.m = new bs(this.c, br.k);
      this.g = new bs(this.c, br.a);
      this.h = new bs(this.c, br.b);
      this.E = new bs(this.c, br.c);
      this.i = new bs(this.c, br.d);
      this.j = new bs(this.c, br.e);
      this.n = new bs(this.c, br.i);
      this.o = new bs(this.c, br.j);
      if (!this.t) {
        this.x = new dv(this.c, this.D);
      }
      return;
    }
  }
  
  static class a
    implements MessageQueue.IdleHandler
  {
    private boolean a = false;
    
    public final boolean queueIdle()
    {
      try
      {
        if (!this.a)
        {
          this.a = true;
          bg.g();
        }
        return true;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */