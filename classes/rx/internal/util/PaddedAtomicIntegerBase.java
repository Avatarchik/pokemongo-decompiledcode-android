package rx.internal.util;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

abstract class PaddedAtomicIntegerBase
  extends FrontPadding
{
  private static final long serialVersionUID = 6513142711280243198L;
  private static final AtomicIntegerFieldUpdater<PaddedAtomicIntegerBase> updater = AtomicIntegerFieldUpdater.newUpdater(PaddedAtomicIntegerBase.class, "value");
  private volatile int value;
  
  public final int addAndGet(int paramInt)
  {
    return updater.addAndGet(this, paramInt);
  }
  
  public final boolean compareAndSet(int paramInt1, int paramInt2)
  {
    return updater.compareAndSet(this, paramInt1, paramInt2);
  }
  
  public final int decrementAndGet()
  {
    return updater.decrementAndGet(this);
  }
  
  public final int get()
  {
    return this.value;
  }
  
  public final int getAndAdd(int paramInt)
  {
    return updater.getAndAdd(this, paramInt);
  }
  
  public final int getAndDecrement()
  {
    return updater.getAndDecrement(this);
  }
  
  public final int getAndIncrement()
  {
    return updater.getAndIncrement(this);
  }
  
  public final int getAndSet(int paramInt)
  {
    return updater.getAndSet(this, this.value);
  }
  
  public final int incrementAndGet()
  {
    return updater.incrementAndGet(this);
  }
  
  public final void lazySet(int paramInt)
  {
    updater.lazySet(this, paramInt);
  }
  
  public final void set(int paramInt)
  {
    this.value = paramInt;
  }
  
  public String toString()
  {
    return String.valueOf(get());
  }
  
  public final boolean weakCompareAndSet(int paramInt1, int paramInt2)
  {
    return updater.weakCompareAndSet(this, paramInt1, paramInt2);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/PaddedAtomicIntegerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */