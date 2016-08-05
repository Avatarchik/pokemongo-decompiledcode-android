package rx.internal.util.unsafe;

import java.util.Iterator;
import rx.internal.util.atomic.LinkedQueueNode;

abstract class BaseLinkedQueue<E>
  extends BaseLinkedQueueConsumerNodeRef<E>
{
  long p00;
  long p01;
  long p02;
  long p03;
  long p04;
  long p05;
  long p06;
  long p07;
  long p30;
  long p31;
  long p32;
  long p33;
  long p34;
  long p35;
  long p36;
  long p37;
  
  public final boolean isEmpty()
  {
    if (lvConsumerNode() == lvProducerNode()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final Iterator<E> iterator()
  {
    throw new UnsupportedOperationException();
  }
  
  public final int size()
  {
    Object localObject = lvConsumerNode();
    LinkedQueueNode localLinkedQueueNode1 = lvProducerNode();
    for (int i = 0; (localObject != localLinkedQueueNode1) && (i < Integer.MAX_VALUE); i++)
    {
      LinkedQueueNode localLinkedQueueNode2;
      do
      {
        localLinkedQueueNode2 = ((LinkedQueueNode)localObject).lvNext();
      } while (localLinkedQueueNode2 == null);
      localObject = localLinkedQueueNode2;
    }
    return i;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/BaseLinkedQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */