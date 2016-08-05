package rx.internal.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public final class RxThreadFactory
  implements ThreadFactory
{
  static final AtomicLongFieldUpdater<RxThreadFactory> COUNTER_UPDATER = AtomicLongFieldUpdater.newUpdater(RxThreadFactory.class, "counter");
  volatile long counter;
  final String prefix;
  
  public RxThreadFactory(String paramString)
  {
    this.prefix = paramString;
  }
  
  public Thread newThread(Runnable paramRunnable)
  {
    Thread localThread = new Thread(paramRunnable, this.prefix + COUNTER_UPDATER.incrementAndGet(this));
    localThread.setDaemon(true);
    return localThread;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/RxThreadFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */