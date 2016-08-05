package rx.internal.util.unsafe;

abstract interface MessagePassingQueue<M>
{
  public abstract boolean isEmpty();
  
  public abstract boolean offer(M paramM);
  
  public abstract M peek();
  
  public abstract M poll();
  
  public abstract int size();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/MessagePassingQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */