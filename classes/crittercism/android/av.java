package crittercism.android;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;

public final class av
  implements Application.ActivityLifecycleCallbacks
{
  private int a = 0;
  private boolean b = false;
  private boolean c = false;
  private boolean d = false;
  private Context e;
  private az f;
  private bd g;
  
  public av(Context paramContext, az paramaz)
  {
    this.e = paramContext;
    this.f = paramaz;
    this.g = new bd(paramContext, paramaz);
  }
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle) {}
  
  public final void onActivityDestroyed(Activity paramActivity) {}
  
  public final void onActivityPaused(Activity paramActivity)
  {
    if (paramActivity != null) {}
    try
    {
      if (this.c)
      {
        paramActivity.unregisterReceiver(this.g);
        this.c = false;
      }
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
        dx.a(localThrowable);
      }
    }
  }
  
  public final void onActivityResumed(Activity paramActivity)
  {
    if (paramActivity != null) {
      for (;;)
      {
        try
        {
          if (this.b)
          {
            dx.b();
            this.b = false;
            this.a = (1 + this.a);
            IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            paramActivity.registerReceiver(this.g, localIntentFilter);
            this.c = true;
            break;
          }
          if (this.a == 0)
          {
            this.f.a(new bl(bl.a.a));
            bg.f();
            if (this.d) {
              continue;
            }
            this.d = true;
            b localb = new d(this.e).a();
            if (localb == b.c) {
              continue;
            }
            if (localb == b.d)
            {
              this.f.a(new ce(ce.a.b));
              continue;
            }
          }
        }
        catch (ThreadDeath localThreadDeath)
        {
          throw localThreadDeath;
          this.f.a(new ce(ce.a.a));
          continue;
        }
        catch (Throwable localThrowable)
        {
          dx.a(localThrowable);
        }
        this.f.a(new bj(bj.a.a, paramActivity.getClass().getName()));
      }
    }
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public final void onActivityStarted(Activity paramActivity) {}
  
  public final void onActivityStopped(Activity paramActivity)
  {
    if (paramActivity != null) {
      try
      {
        this.a = (-1 + this.a);
        if (paramActivity.isChangingConfigurations())
        {
          dx.b();
          this.b = true;
        }
        else if (this.a == 0)
        {
          this.f.a(new bl(bl.a.b));
          bg.a(this.f);
        }
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
        this.f.a(new bj(bj.a.b, paramActivity.getClass().getName()));
      }
      catch (Throwable localThrowable)
      {
        dx.a(localThrowable);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/av.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */