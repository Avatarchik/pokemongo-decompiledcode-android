package rx.schedulers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import rx.internal.schedulers.NewThreadWorker;
import rx.internal.util.RxThreadFactory;

final class GenericScheduledExecutorService
{
  private static final GenericScheduledExecutorService INSTANCE = new GenericScheduledExecutorService();
  private static final RxThreadFactory THREAD_FACTORY = new RxThreadFactory("RxScheduledExecutorPool-");
  private static final String THREAD_NAME_PREFIX = "RxScheduledExecutorPool-";
  private final ScheduledExecutorService executor;
  
  private GenericScheduledExecutorService()
  {
    int i = Runtime.getRuntime().availableProcessors();
    if (i > 4) {
      i /= 2;
    }
    if (i > 8) {
      i = 8;
    }
    ScheduledExecutorService localScheduledExecutorService = Executors.newScheduledThreadPool(i, THREAD_FACTORY);
    if ((!NewThreadWorker.tryEnableCancelPolicy(localScheduledExecutorService)) && ((localScheduledExecutorService instanceof ScheduledThreadPoolExecutor))) {
      NewThreadWorker.registerExecutor((ScheduledThreadPoolExecutor)localScheduledExecutorService);
    }
    this.executor = localScheduledExecutorService;
  }
  
  public static ScheduledExecutorService getInstance()
  {
    return INSTANCE.executor;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/schedulers/GenericScheduledExecutorService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */