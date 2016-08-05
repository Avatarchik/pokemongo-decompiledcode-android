package rx.internal.util.unsafe;

import sun.misc.Unsafe;

public final class SpscArrayQueue<E>
  extends SpscArrayQueueL3Pad<E>
{
  public SpscArrayQueue(int paramInt)
  {
    super(paramInt);
  }
  
  private long lvConsumerIndex()
  {
    return UnsafeAccess.UNSAFE.getLongVolatile(this, C_INDEX_OFFSET);
  }
  
  private long lvProducerIndex()
  {
    return UnsafeAccess.UNSAFE.getLongVolatile(this, P_INDEX_OFFSET);
  }
  
  private void soConsumerIndex(long paramLong)
  {
    UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, paramLong);
  }
  
  private void soProducerIndex(long paramLong)
  {
    UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, paramLong);
  }
  
  public boolean offer(E paramE)
  {
    Object[] arrayOfObject = this.buffer;
    long l1 = this.producerIndex;
    long l2 = calcElementOffset(l1);
    if (lvElement(arrayOfObject, l2) != null) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      soProducerIndex(1L + l1);
      soElement(arrayOfObject, l2, paramE);
    }
  }
  
  public E peek()
  {
    return (E)lvElement(calcElementOffset(this.consumerIndex));
  }
  
  public E poll()
  {
    long l1 = this.consumerIndex;
    long l2 = calcElementOffset(l1);
    Object[] arrayOfObject = this.buffer;
    Object localObject = lvElement(arrayOfObject, l2);
    if (localObject == null) {
      localObject = null;
    }
    for (;;)
    {
      return (E)localObject;
      soConsumerIndex(1L + l1);
      soElement(arrayOfObject, l2, null);
    }
  }
  
  public int size()
  {
    long l1 = lvConsumerIndex();
    long l2;
    long l3;
    do
    {
      l2 = l1;
      l3 = lvProducerIndex();
      l1 = lvConsumerIndex();
    } while (l2 != l1);
    return (int)(l3 - l1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/SpscArrayQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */