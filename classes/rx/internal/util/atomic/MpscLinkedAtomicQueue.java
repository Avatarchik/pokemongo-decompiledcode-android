package rx.internal.util.atomic;

public final class MpscLinkedAtomicQueue<E>
  extends BaseLinkedAtomicQueue<E>
{
  public MpscLinkedAtomicQueue()
  {
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode();
    spConsumerNode(localLinkedQueueNode);
    xchgProducerNode(localLinkedQueueNode);
  }
  
  public final boolean offer(E paramE)
  {
    if (paramE == null) {
      throw new IllegalArgumentException("null elements not allowed");
    }
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode(paramE);
    xchgProducerNode(localLinkedQueueNode).soNext(localLinkedQueueNode);
    return true;
  }
  
  public final E peek()
  {
    LinkedQueueNode localLinkedQueueNode1 = lpConsumerNode();
    LinkedQueueNode localLinkedQueueNode2 = localLinkedQueueNode1.lvNext();
    Object localObject;
    if (localLinkedQueueNode2 != null) {
      localObject = localLinkedQueueNode2.lpValue();
    }
    for (;;)
    {
      return (E)localObject;
      if (localLinkedQueueNode1 != lvProducerNode())
      {
        LinkedQueueNode localLinkedQueueNode3;
        do
        {
          localLinkedQueueNode3 = localLinkedQueueNode1.lvNext();
        } while (localLinkedQueueNode3 == null);
        localObject = localLinkedQueueNode3.lpValue();
      }
      else
      {
        localObject = null;
      }
    }
  }
  
  public final E poll()
  {
    LinkedQueueNode localLinkedQueueNode1 = lpConsumerNode();
    LinkedQueueNode localLinkedQueueNode2 = localLinkedQueueNode1.lvNext();
    Object localObject;
    if (localLinkedQueueNode2 != null)
    {
      localObject = localLinkedQueueNode2.getAndNullValue();
      spConsumerNode(localLinkedQueueNode2);
    }
    for (;;)
    {
      return (E)localObject;
      if (localLinkedQueueNode1 != lvProducerNode())
      {
        LinkedQueueNode localLinkedQueueNode3;
        do
        {
          localLinkedQueueNode3 = localLinkedQueueNode1.lvNext();
        } while (localLinkedQueueNode3 == null);
        localObject = localLinkedQueueNode3.getAndNullValue();
        spConsumerNode(localLinkedQueueNode3);
      }
      else
      {
        localObject = null;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/atomic/MpscLinkedAtomicQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */