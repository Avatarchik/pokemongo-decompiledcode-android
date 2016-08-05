package rx.plugins;

import rx.Scheduler;
import rx.functions.Action0;

public class RxJavaSchedulersHook
{
  private static final RxJavaSchedulersHook DEFAULT_INSTANCE = new RxJavaSchedulersHook();
  
  public static RxJavaSchedulersHook getDefaultInstance()
  {
    return DEFAULT_INSTANCE;
  }
  
  public Scheduler getComputationScheduler()
  {
    return null;
  }
  
  public Scheduler getIOScheduler()
  {
    return null;
  }
  
  public Scheduler getNewThreadScheduler()
  {
    return null;
  }
  
  public Action0 onSchedule(Action0 paramAction0)
  {
    return paramAction0;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/plugins/RxJavaSchedulersHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */