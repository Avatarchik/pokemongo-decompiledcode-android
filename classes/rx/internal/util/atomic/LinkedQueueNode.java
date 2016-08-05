package rx.internal.util.atomic;

import java.util.concurrent.atomic.AtomicReference;

public final class LinkedQueueNode<E>
  extends AtomicReference<LinkedQueueNode<E>>
{
  private static final long serialVersionUID = 2404266111789071508L;
  private E value;
  
  public LinkedQueueNode() {}
  
  public LinkedQueueNode(E paramE)
  {
    spValue(paramE);
  }
  
  public E getAndNullValue()
  {
    Object localObject = lpValue();
    spValue(null);
    return (E)localObject;
  }
  
  public E lpValue()
  {
    return (E)this.value;
  }
  
  public LinkedQueueNode<E> lvNext()
  {
    return (LinkedQueueNode)get();
  }
  
  public void soNext(LinkedQueueNode<E> paramLinkedQueueNode)
  {
    lazySet(paramLinkedQueueNode);
  }
  
  public void spValue(E paramE)
  {
    this.value = paramE;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/atomic/LinkedQueueNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */