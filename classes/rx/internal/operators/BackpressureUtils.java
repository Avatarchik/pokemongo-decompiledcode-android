package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public final class BackpressureUtils
{
  private BackpressureUtils()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static long getAndAddRequest(AtomicLong paramAtomicLong, long paramLong)
  {
    long l1;
    long l2;
    do
    {
      l1 = paramAtomicLong.get();
      l2 = l1 + paramLong;
      if (l2 < 0L) {
        l2 = Long.MAX_VALUE;
      }
    } while (!paramAtomicLong.compareAndSet(l1, l2));
    return l1;
  }
  
  public static <T> long getAndAddRequest(AtomicLongFieldUpdater<T> paramAtomicLongFieldUpdater, T paramT, long paramLong)
  {
    long l1;
    long l2;
    do
    {
      l1 = paramAtomicLongFieldUpdater.get(paramT);
      l2 = l1 + paramLong;
      if (l2 < 0L) {
        l2 = Long.MAX_VALUE;
      }
    } while (!paramAtomicLongFieldUpdater.compareAndSet(paramT, l1, l2));
    return l1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/BackpressureUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */