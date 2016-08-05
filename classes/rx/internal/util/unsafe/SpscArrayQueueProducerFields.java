package rx.internal.util.unsafe;

import sun.misc.Unsafe;

abstract class SpscArrayQueueProducerFields<E>
  extends SpscArrayQueueL1Pad<E>
{
  protected static final long P_INDEX_OFFSET;
  protected long producerIndex;
  protected long producerLookAhead;
  
  static
  {
    try
    {
      P_INDEX_OFFSET = UnsafeAccess.UNSAFE.objectFieldOffset(SpscArrayQueueProducerFields.class.getDeclaredField("producerIndex"));
      return;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      throw new RuntimeException(localNoSuchFieldException);
    }
  }
  
  public SpscArrayQueueProducerFields(int paramInt)
  {
    super(paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/SpscArrayQueueProducerFields.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */