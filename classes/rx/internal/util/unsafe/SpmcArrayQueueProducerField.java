package rx.internal.util.unsafe;

import sun.misc.Unsafe;

abstract class SpmcArrayQueueProducerField<E>
  extends SpmcArrayQueueL1Pad<E>
{
  protected static final long P_INDEX_OFFSET;
  private volatile long producerIndex;
  
  static
  {
    try
    {
      P_INDEX_OFFSET = UnsafeAccess.UNSAFE.objectFieldOffset(SpmcArrayQueueProducerField.class.getDeclaredField("producerIndex"));
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      throw new RuntimeException(localNoSuchFieldException);
    }
  }
  
  public SpmcArrayQueueProducerField(int paramInt)
  {
    super(paramInt);
  }
  
  protected final long lvProducerIndex()
  {
    return this.producerIndex;
  }
  
  protected final void soTail(long paramLong)
  {
    UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, paramLong);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/SpmcArrayQueueProducerField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */