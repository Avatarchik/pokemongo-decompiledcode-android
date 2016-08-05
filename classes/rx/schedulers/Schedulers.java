package rx.schedulers;

import java.util.concurrent.Executor;
import rx.Scheduler;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;

public final class Schedulers
{
  private static final Schedulers INSTANCE = new Schedulers();
  private final Scheduler computationScheduler;
  private final Scheduler ioScheduler;
  private final Scheduler newThreadScheduler;
  
  private Schedulers()
  {
    Scheduler localScheduler1 = RxJavaPlugins.getInstance().getSchedulersHook().getComputationScheduler();
    label42:
    Scheduler localScheduler3;
    if (localScheduler1 != null)
    {
      this.computationScheduler = localScheduler1;
      Scheduler localScheduler2 = RxJavaPlugins.getInstance().getSchedulersHook().getIOScheduler();
      if (localScheduler2 == null) {
        break label76;
      }
      this.ioScheduler = localScheduler2;
      localScheduler3 = RxJavaPlugins.getInstance().getSchedulersHook().getNewThreadScheduler();
      if (localScheduler3 == null) {
        break label90;
      }
    }
    label76:
    label90:
    for (this.newThreadScheduler = localScheduler3;; this.newThreadScheduler = NewThreadScheduler.instance())
    {
      return;
      this.computationScheduler = new EventLoopsScheduler();
      break;
      this.ioScheduler = new CachedThreadScheduler();
      break label42;
    }
  }
  
  public static Scheduler computation()
  {
    return INSTANCE.computationScheduler;
  }
  
  public static Scheduler from(Executor paramExecutor)
  {
    return new ExecutorScheduler(paramExecutor);
  }
  
  public static Scheduler immediate()
  {
    return ImmediateScheduler.instance();
  }
  
  public static Scheduler io()
  {
    return INSTANCE.ioScheduler;
  }
  
  public static Scheduler newThread()
  {
    return INSTANCE.newThreadScheduler;
  }
  
  public static TestScheduler test()
  {
    return new TestScheduler();
  }
  
  public static Scheduler trampoline()
  {
    return TrampolineScheduler.instance();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/schedulers/Schedulers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */