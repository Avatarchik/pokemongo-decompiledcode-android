package crittercism.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.ConditionVariable;
import com.crittercism.app.CrittercismNDK;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class dg
  extends di
{
  public ConditionVariable a = new ConditionVariable();
  public bm b = null;
  private ConditionVariable c = new ConditionVariable();
  private bb d;
  private Context e;
  private aw f;
  private ax g;
  private au h;
  private List i = new ArrayList();
  private boolean j = false;
  private boolean k;
  private Exception l = null;
  
  public dg(bb parambb, Context paramContext, aw paramaw, ax paramax, au paramau)
  {
    this.d = parambb;
    this.e = paramContext;
    this.f = paramaw;
    this.g = paramax;
    this.h = paramau;
    this.k = false;
  }
  
  private void a(File paramFile)
  {
    az localaz = az.A();
    if (localaz.t) {
      return;
    }
    if ((paramFile != null) && (paramFile.exists())) {
      paramFile.isFile();
    }
    bs localbs1 = this.f.s();
    bs localbs2 = this.f.t();
    bs localbs3 = this.f.u();
    bs localbs4 = this.f.v();
    bs localbs5 = this.f.q();
    if (paramFile != null)
    {
      dq.a = true;
      localaz.e.open();
      localbs5.a(new cd(paramFile, localbs1, localbs3, localbs2, localbs4));
      paramFile.delete();
      this.f.w().a();
    }
    for (;;)
    {
      localbs3.a();
      localbs2.a();
      localbs4.a();
      localbs1.a(localbs3);
      break;
      localaz.e.open();
      bg.a(this.f);
    }
  }
  
  /**
   * @deprecated
   */
  private void c()
  {
    try
    {
      this.j = true;
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
  private boolean d()
  {
    try
    {
      boolean bool = this.j;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private File e()
  {
    Object localObject = null;
    int m = 0;
    File localFile1 = new File(this.e.getFilesDir().getAbsolutePath() + "/" + this.d.g());
    if ((!localFile1.exists()) || (!localFile1.isDirectory())) {}
    for (;;)
    {
      return (File)localObject;
      File[] arrayOfFile = localFile1.listFiles();
      if (arrayOfFile != null) {
        if (arrayOfFile.length == 1)
        {
          File localFile3 = arrayOfFile[0];
          localFile3.isFile();
          if (localFile3.isFile()) {
            localObject = localFile3;
          }
        }
        else if (arrayOfFile.length > 1)
        {
          int n = arrayOfFile.length;
          while (m < n)
          {
            File localFile2 = arrayOfFile[m];
            localFile2.isFile();
            localFile2.delete();
            m++;
          }
        }
      }
    }
  }
  
  private void f()
  {
    if (az.A().t) {}
    for (;;)
    {
      return;
      bs localbs1 = this.f.n();
      bs localbs2 = this.f.o();
      bs localbs3 = this.f.p();
      bs localbs4 = this.f.q();
      bs localbs5 = this.f.r();
      dv localdv = this.f.y();
      this.d.b();
      this.b = new bm(this.h);
      if (!this.d.delaySendingAppLoad())
      {
        localbs1.a(this.b);
        df localdf = new df(this.e);
        localdf.a(localbs1, new ct.a(), this.d.e(), "/v0/appload", this.d.b(), this.h, new cs.b());
        localdf.a(localbs2, new da.a(), this.d.b(), "/android_v2/handle_exceptions", null, this.h, new cu.a());
        localdf.a(localbs4, new da.a(), this.d.b(), "/android_v2/handle_ndk_crashes", null, this.h, new cu.a());
        localdf.a(localbs5, new da.a(), this.d.b(), "/android_v2/handle_crashes", null, this.h, new cu.a());
        localdf.a(localbs3, new da.a(), this.d.b(), "/android_v2/handle_exceptions", null, new ba(this.h, this.d), new cu.a());
        localdf.a();
      }
      if (localdv.b()) {
        az.A().E();
      }
    }
  }
  
  public final void a()
  {
    File localFile2;
    try
    {
      dx.b();
      localFile1 = new File(this.e.getFilesDir().getAbsolutePath() + "/com.crittercism/pending");
      if ((localFile1.exists()) && (!localFile1.isDirectory()))
      {
        dx.b();
        az localaz1 = az.A();
        localaz1.w.a();
        dw localdw = this.h.l();
        localaz1.d.open();
        ax localax = this.g;
        localdw.a(localax);
        dq.a = dq.a(this.e).booleanValue();
        dq.a(this.e, false);
        if (!localdw.b())
        {
          dt localdt = new dt(this.e);
          int m = 1 + localdt.a();
          localdt.a.edit().putInt("numAppLoads", m).commit();
          az.A().A = localdt;
          localdw.a().a(this.g, cq.j.a(), cq.j.b());
        }
        this.k = localdw.b();
        localFile2 = e();
        if (!this.k) {
          break label544;
        }
        if (!az.A().t)
        {
          if ((localFile2 != null) && (localFile2.exists())) {
            localFile2.isFile();
          }
          new bs(this.e, br.a).a();
          new bs(this.e, br.b).a();
          new bs(this.e, br.c).a();
          new bs(this.e, br.d).a();
          new bs(this.e, br.e).a();
          new bs(this.e, br.f).a();
          new bs(this.e, br.h).a();
          new bs(this.e, br.g).a();
          new bs(this.e, br.k).a();
          if (localFile2 != null) {
            localFile2.delete();
          }
        }
        h.b(this.e);
        c();
        Iterator localIterator = this.i.iterator();
        while (localIterator.hasNext()) {
          ((Runnable)localIterator.next()).run();
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        File localFile1;
        new StringBuilder("Exception in run(): ").append(localException.getMessage());
        dx.b();
        dx.c();
        this.l = localException;
        label525:
        return;
        eb.a(localFile1);
      }
    }
    finally
    {
      this.c.open();
    }
    label544:
    Context localContext = this.e;
    h localh = new h(localContext);
    SharedPreferences localSharedPreferences = localContext.getSharedPreferences("com.crittercism.optmz.config", 0);
    if (localSharedPreferences.contains("interval"))
    {
      localh.d = localSharedPreferences.getInt("interval", 10);
      if (!localSharedPreferences.contains("kill")) {
        break label1000;
      }
      localh.c = localSharedPreferences.getBoolean("kill", false);
      if (!localSharedPreferences.contains("persist")) {
        break label1006;
      }
      localh.b = localSharedPreferences.getBoolean("persist", false);
      if (!localSharedPreferences.contains("enabled")) {
        break label1012;
      }
      localh.a = localSharedPreferences.getBoolean("enabled", false);
    }
    for (;;)
    {
      if (localh != null) {
        az.A().a(localh);
      }
      this.f.z();
      bh localbh;
      bs localbs1;
      bs localbs2;
      bs localbs3;
      bs localbs4;
      if (!az.A().t)
      {
        localbh = bh.a(this.e);
        localbs1 = this.f.x();
        localbs2 = this.f.s();
        localbs3 = this.f.t();
        localbs4 = this.f.v();
      }
      try
      {
        URL localURL = new URL(this.d.d() + "/api/v1/transactions");
        bi localbi = new bi(this.e, this.h, localbs1, localbs2, localbs3, localbs4, localURL);
        az localaz2 = az.A();
        localaz2.y = localbi;
        new dy(localbi, "TXN Thread").start();
        localaz2.a(localbh);
        a(localFile2);
        this.a.open();
        this.f.s().a(cf.a);
        if ((!az.A().t) && (this.d.isNdkCrashReportingEnabled())) {
          dx.b();
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        try
        {
          CrittercismNDK.installNdkLib(this.e, this.d.g());
          f();
          break;
          localMalformedURLException = localMalformedURLException;
          dx.a();
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            new StringBuilder("Exception installing ndk library: ").append(localThrowable.getClass().getName());
            dx.b();
          }
        }
      }
      this.c.open();
      break label525;
      localh = null;
      continue;
      label1000:
      localh = null;
      continue;
      label1006:
      localh = null;
      continue;
      label1012:
      localh = null;
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public final boolean a(Runnable paramRunnable)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 500	crittercism/android/dg:d	()Z
    //   6: ifne +20 -> 26
    //   9: aload_0
    //   10: getfield 43	crittercism/android/dg:i	Ljava/util/List;
    //   13: aload_1
    //   14: invokeinterface 504 2 0
    //   19: pop
    //   20: iconst_1
    //   21: istore_3
    //   22: aload_0
    //   23: monitorexit
    //   24: iload_3
    //   25: ireturn
    //   26: iconst_0
    //   27: istore_3
    //   28: goto -6 -> 22
    //   31: astore_2
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_2
    //   35: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	36	0	this	dg
    //   0	36	1	paramRunnable	Runnable
    //   31	4	2	localObject	Object
    //   21	7	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	20	31	finally
  }
  
  public final void b()
  {
    this.c.block();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/dg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */