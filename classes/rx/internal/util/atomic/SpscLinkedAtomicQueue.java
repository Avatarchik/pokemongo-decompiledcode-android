package rx.internal.util.atomic;

public final class SpscLinkedAtomicQueue<E>
  extends BaseLinkedAtomicQueue<E>
{
  public SpscLinkedAtomicQueue()
  {
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode();
    spProducerNode(localLinkedQueueNode);
    spConsumerNode(localLinkedQueueNode);
    localLinkedQueueNode.soNext(null);
  }
  
  public boolean offer(E paramE)
  {
    if (paramE == null) {
      throw new IllegalArgumentException("null elements not allowed");
    }
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode(paramE);
    lpProducerNode().soNext(localLinkedQueueNode);
    spProducerNode(localLinkedQueueNode);
    return true;
  }
  
  public E peek()
  {
    LinkedQueueNode localLinkedQueueNode = lpConsumerNode().lvNext();
    if (localLinkedQueueNode != null) {}
    for (Object localObject = localLinkedQueueNode.lpValue();; localObject = null) {
      return (E)localObject;
    }
  }
  
  public E poll()
  {
    LinkedQueueNode localLinkedQueueNode = lpConsumerNode().lvNext();
    Object localObject;
    if (localLinkedQueueNode != null)
    {
      localObject = localLinkedQueueNode.getAndNullValue();
      spConsumerNode(localLinkedQueueNode);
    }
    for (;;)
    {
      return (E)localObject;
      localObject = null;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/atomic/SpscLinkedAtomicQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */