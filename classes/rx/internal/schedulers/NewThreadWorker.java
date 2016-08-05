package rx.internal.schedulers;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class NewThreadWorker
  extends Scheduler.Worker
  implements Subscription
{
  private static final ConcurrentHashMap<ScheduledThreadPoolExecutor, ScheduledThreadPoolExecutor> EXECUTORS = new ConcurrentHashMap();
  private static final String FREQUENCY_KEY = "rx.scheduler.jdk6.purge-frequency-millis";
  private static final AtomicReference<ScheduledExecutorService> PURGE = new AtomicReference();
  private static final boolean PURGE_FORCE = Boolean.getBoolean("rx.scheduler.jdk6.purge-force");
  private static final String PURGE_FORCE_KEY = "rx.scheduler.jdk6.purge-force";
  public static final int PURGE_FREQUENCY = Integer.getInteger("rx.scheduler.jdk6.purge-frequency-millis", 1000).intValue();
  private static final String PURGE_THREAD_PREFIX = "RxSchedulerPurge-";
  private final ScheduledExecutorService executor;
  volatile boolean isUnsubscribed;
  private final RxJavaSchedulersHook schedulersHook;
  
  public NewThreadWorker(ThreadFactory paramThreadFactory)
  {
    ScheduledExecutorService localScheduledExecutorService = Executors.newScheduledThreadPool(1, paramThreadFactory);
    if ((!tryEnableCancelPolicy(localScheduledExecutorService)) && ((localScheduledExecutorService instanceof ScheduledThreadPoolExecutor))) {
      registerExecutor((ScheduledThreadPoolExecutor)localScheduledExecutorService);
    }
    this.schedulersHook = RxJavaPlugins.getInstance().getSchedulersHook();
    this.executor = localScheduledExecutorService;
  }
  
  public static void deregisterExecutor(ScheduledExecutorService paramScheduledExecutorService)
  {
    EXECUTORS.remove(paramScheduledExecutorService);
  }
  
  static void purgeExecutors()
  {
    for (;;)
    {
      Iterator localIterator;
      try
      {
        localIterator = EXECUTORS.keySet().iterator();
        if (localIterator.hasNext())
        {
          ScheduledThreadPoolExecutor localScheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor)localIterator.next();
          if (!localScheduledThreadPoolExecutor.isShutdown()) {
            localScheduledThreadPoolExecutor.purge();
          }
        }
        else
        {
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwIfFatal(localThrowable);
        RxJavaPlugins.getInstance().getErrorHandler().handleError(localThrowable);
      }
      localIterator.remove();
    }
  }
  
  public static void registerExecutor(ScheduledThreadPoolExecutor paramScheduledThreadPoolExecutor)
  {
    if ((ScheduledExecutorService)PURGE.get() != null) {}
    for (;;)
    {
      EXECUTORS.putIfAbsent(paramScheduledThreadPoolExecutor, paramScheduledThreadPoolExecutor);
      return;
      ScheduledExecutorService localScheduledExecutorService = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge-"));
      if (!PURGE.compareAndSet(null, localScheduledExecutorService)) {
        break;
      }
      localScheduledExecutorService.scheduleAtFixedRate(new Runnable()
      {
        public void run() {}
      }, PURGE_FREQUENCY, PURGE_FREQUENCY, TimeUnit.MILLISECONDS);
    }
  }
  
  public static boolean tryEnableCancelPolicy(ScheduledExecutorService paramScheduledExecutorService)
  {
    int i = 1;
    int m;
    Method localMethod;
    if (!PURGE_FORCE)
    {
      Method[] arrayOfMethod = paramScheduledExecutorService.getClass().getMethods();
      int k = arrayOfMethod.length;
      m = 0;
      if (m < k)
      {
        localMethod = arrayOfMethod[m];
        if ((!localMethod.getName().equals("setRemoveOnCancelPolicy")) || (localMethod.getParameterTypes().length != i) || (localMethod.getParameterTypes()[0] != Boolean.TYPE)) {}
      }
    }
    for (;;)
    {
      try
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Boolean.valueOf(true);
        localMethod.invoke(paramScheduledExecutorService, arrayOfObject);
        return i;
      }
      catch (Exception localException)
      {
        RxJavaPlugins.getInstance().getErrorHandler().handleError(localException);
      }
      m++;
      break;
      int j = 0;
    }
  }
  
  public boolean isUnsubscribed()
  {
    return this.isUnsubscribed;
  }
  
  public Subscription schedule(Action0 paramAction0)
  {
    return schedule(paramAction0, 0L, null);
  }
  
  public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
  {
    if (this.isUnsubscribed) {}
    for (Object localObject = Subscriptions.unsubscribed();; localObject = scheduleActual(paramAction0, paramLong, paramTimeUnit)) {
      return (Subscription)localObject;
    }
  }
  
  public ScheduledAction scheduleActual(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
  {
    ScheduledAction localScheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(paramAction0));
    if (paramLong <= 0L) {}
    for (Object localObject = this.executor.submit(localScheduledAction);; localObject = this.executor.schedule(localScheduledAction, paramLong, paramTimeUnit))
    {
      localScheduledAction.add((Future)localObject);
      return localScheduledAction;
    }
  }
  
  public ScheduledAction scheduleActual(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit, SubscriptionList paramSubscriptionList)
  {
    ScheduledAction localScheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(paramAction0), paramSubscriptionList);
    paramSubscriptionList.add(localScheduledAction);
    if (paramLong <= 0L) {}
    for (Object localObject = this.executor.submit(localScheduledAction);; localObject = this.executor.schedule(localScheduledAction, paramLong, paramTimeUnit))
    {
      localScheduledAction.add((Future)localObject);
      return localScheduledAction;
    }
  }
  
  public ScheduledAction scheduleActual(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit, CompositeSubscription paramCompositeSubscription)
  {
    ScheduledAction localScheduledAction = new ScheduledAction(this.schedulersHook.onSchedule(paramAction0), paramCompositeSubscription);
    paramCompositeSubscription.add(localScheduledAction);
    if (paramLong <= 0L) {}
    for (Object localObject = this.executor.submit(localScheduledAction);; localObject = this.executor.schedule(localScheduledAction, paramLong, paramTimeUnit))
    {
      localScheduledAction.add((Future)localObject);
      return localScheduledAction;
    }
  }
  
  public void unsubscribe()
  {
    this.isUnsubscribed = true;
    this.executor.shutdownNow();
    deregisterExecutor(this.executor);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/schedulers/NewThreadWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */