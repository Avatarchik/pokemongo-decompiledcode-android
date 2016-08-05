package rx.internal.util.unsafe;

public class MpmcArrayQueue<E>
  extends MpmcArrayQueueConsumerField<E>
{
  long p30;
  long p31;
  long p32;
  long p33;
  long p34;
  long p35;
  long p36;
  long p37;
  long p40;
  long p41;
  long p42;
  long p43;
  long p44;
  long p45;
  long p46;
  
  public MpmcArrayQueue(int paramInt)
  {
    super(Math.max(2, paramInt));
  }
  
  public boolean isEmpty()
  {
    if (lvConsumerIndex() == lvProducerIndex()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean offer(E paramE)
  {
    if (paramE == null) {
      throw new NullPointerException("Null is not a valid element");
    }
    long l1 = 1L + this.mask;
    long[] arrayOfLong = this.sequenceBuffer;
    long l2 = Long.MAX_VALUE;
    long l3;
    long l4;
    long l5;
    do
    {
      l3 = lvProducerIndex();
      l4 = calcSequenceOffset(l3);
      l5 = lvSequence(arrayOfLong, l4) - l3;
      if (l5 != 0L) {
        break;
      }
    } while (!casProducerIndex(l3, 1L + l3));
    spElement(calcElementOffset(l3), paramE);
    soSequence(arrayOfLong, l4, 1L + l3);
    for (boolean bool = true;; bool = false)
    {
      return bool;
      if ((l5 >= 0L) || (l3 - l1 > l2)) {
        break;
      }
      long l6 = l3 - l1;
      l2 = lvConsumerIndex();
      if (l6 > l2) {
        break;
      }
    }
  }
  
  public E peek()
  {
    long l;
    Object localObject;
    do
    {
      l = lvConsumerIndex();
      localObject = lpElement(calcElementOffset(l));
    } while ((localObject == null) && (l != lvProducerIndex()));
    return (E)localObject;
  }
  
  public E poll()
  {
    long[] arrayOfLong = this.sequenceBuffer;
    long l1 = -1L;
    long l2;
    long l3;
    long l4;
    do
    {
      l2 = lvConsumerIndex();
      l3 = calcSequenceOffset(l2);
      l4 = lvSequence(arrayOfLong, l3) - (1L + l2);
      if (l4 != 0L) {
        break;
      }
    } while (!casConsumerIndex(l2, 1L + l2));
    long l5 = calcElementOffset(l2);
    Object localObject = lpElement(l5);
    spElement(l5, null);
    soSequence(arrayOfLong, l3, 1L + (l2 + this.mask));
    for (;;)
    {
      return (E)localObject;
      if ((l4 >= 0L) || (l2 < l1)) {
        break;
      }
      l1 = lvProducerIndex();
      if (l2 != l1) {
        break;
      }
      localObject = null;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/MpmcArrayQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */