package rx.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;

public final class AndroidSchedulers
{
  private static final Scheduler MAIN_THREAD_SCHEDULER = new HandlerScheduler(new Handler(Looper.getMainLooper()));
  
  private AndroidSchedulers()
  {
    throw new AssertionError("No instances");
  }
  
  public static Scheduler mainThread()
  {
    Scheduler localScheduler = RxAndroidPlugins.getInstance().getSchedulersHook().getMainThreadScheduler();
    if (localScheduler != null) {}
    for (;;)
    {
      return localScheduler;
      localScheduler = MAIN_THREAD_SCHEDULER;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/android/schedulers/AndroidSchedulers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */