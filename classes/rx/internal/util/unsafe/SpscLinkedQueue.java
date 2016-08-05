package rx.internal.util.unsafe;

import rx.internal.util.atomic.LinkedQueueNode;

public final class SpscLinkedQueue<E>
  extends BaseLinkedQueue<E>
{
  public SpscLinkedQueue()
  {
    spProducerNode(new LinkedQueueNode());
    spConsumerNode(this.producerNode);
    this.consumerNode.soNext(null);
  }
  
  public boolean offer(E paramE)
  {
    if (paramE == null) {
      throw new IllegalArgumentException("null elements not allowed");
    }
    LinkedQueueNode localLinkedQueueNode = new LinkedQueueNode(paramE);
    this.producerNode.soNext(localLinkedQueueNode);
    this.producerNode = localLinkedQueueNode;
    return true;
  }
  
  public E peek()
  {
    LinkedQueueNode localLinkedQueueNode = this.consumerNode.lvNext();
    if (localLinkedQueueNode != null) {}
    for (Object localObject = localLinkedQueueNode.lpValue();; localObject = null) {
      return (E)localObject;
    }
  }
  
  public E poll()
  {
    LinkedQueueNode localLinkedQueueNode = this.consumerNode.lvNext();
    Object localObject;
    if (localLinkedQueueNode != null)
    {
      localObject = localLinkedQueueNode.getAndNullValue();
      this.consumerNode = localLinkedQueueNode;
    }
    for (;;)
    {
      return (E)localObject;
      localObject = null;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/SpscLinkedQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */