package rx.internal.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedQueue<T>
  implements Queue<T>
{
  private final LinkedList<T> list = new LinkedList();
  private final int size;
  
  public SynchronizedQueue()
  {
    this.size = -1;
  }
  
  public SynchronizedQueue(int paramInt)
  {
    this.size = paramInt;
  }
  
  /**
   * @deprecated
   */
  public boolean add(T paramT)
  {
    try
    {
      boolean bool = this.list.add(paramT);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean addAll(Collection<? extends T> paramCollection)
  {
    try
    {
      boolean bool = this.list.addAll(paramCollection);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void clear()
  {
    try
    {
      this.list.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public Object clone()
  {
    try
    {
      SynchronizedQueue localSynchronizedQueue = new SynchronizedQueue(this.size);
      localSynchronizedQueue.addAll(this.list);
      return localSynchronizedQueue;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean contains(Object paramObject)
  {
    try
    {
      boolean bool = this.list.contains(paramObject);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean containsAll(Collection<?> paramCollection)
  {
    try
    {
      boolean bool = this.list.containsAll(paramCollection);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public T element()
  {
    try
    {
      Object localObject2 = this.list.element();
      return (T)localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw ((Throwable)localObject1);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean equals(Object paramObject)
  {
    try
    {
      boolean bool = this.list.equals(paramObject);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public int hashCode()
  {
    try
    {
      int i = this.list.hashCode();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean isEmpty()
  {
    try
    {
      boolean bool = this.list.isEmpty();
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public Iterator<T> iterator()
  {
    try
    {
      Iterator localIterator = this.list.iterator();
      return localIterator;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public boolean offer(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 23	rx/internal/util/SynchronizedQueue:size	I
    //   6: bipush -1
    //   8: if_icmple +35 -> 43
    //   11: iconst_1
    //   12: aload_0
    //   13: getfield 21	rx/internal/util/SynchronizedQueue:list	Ljava/util/LinkedList;
    //   16: invokevirtual 67	java/util/LinkedList:size	()I
    //   19: iadd
    //   20: istore 5
    //   22: aload_0
    //   23: getfield 23	rx/internal/util/SynchronizedQueue:size	I
    //   26: istore 6
    //   28: iload 5
    //   30: iload 6
    //   32: if_icmple +11 -> 43
    //   35: iconst_0
    //   36: istore 4
    //   38: aload_0
    //   39: monitorexit
    //   40: iload 4
    //   42: ireturn
    //   43: aload_0
    //   44: getfield 21	rx/internal/util/SynchronizedQueue:list	Ljava/util/LinkedList;
    //   47: aload_1
    //   48: invokevirtual 69	java/util/LinkedList:offer	(Ljava/lang/Object;)Z
    //   51: istore_3
    //   52: iload_3
    //   53: istore 4
    //   55: goto -17 -> 38
    //   58: astore_2
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_2
    //   62: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	63	0	this	SynchronizedQueue
    //   0	63	1	paramT	T
    //   58	4	2	localObject	Object
    //   51	2	3	bool1	boolean
    //   36	18	4	bool2	boolean
    //   20	13	5	i	int
    //   26	7	6	j	int
    // Exception table:
    //   from	to	target	type
    //   2	28	58	finally
    //   43	52	58	finally
  }
  
  /**
   * @deprecated
   */
  public T peek()
  {
    try
    {
      Object localObject2 = this.list.peek();
      return (T)localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw ((Throwable)localObject1);
    }
  }
  
  /**
   * @deprecated
   */
  public T poll()
  {
    try
    {
      Object localObject2 = this.list.poll();
      return (T)localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw ((Throwable)localObject1);
    }
  }
  
  /**
   * @deprecated
   */
  public T remove()
  {
    try
    {
      Object localObject2 = this.list.remove();
      return (T)localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw ((Throwable)localObject1);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean remove(Object paramObject)
  {
    try
    {
      boolean bool = this.list.remove(paramObject);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean removeAll(Collection<?> paramCollection)
  {
    try
    {
      boolean bool = this.list.removeAll(paramCollection);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public boolean retainAll(Collection<?> paramCollection)
  {
    try
    {
      boolean bool = this.list.retainAll(paramCollection);
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public int size()
  {
    try
    {
      int i = this.list.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public Object[] toArray()
  {
    try
    {
      Object[] arrayOfObject = this.list.toArray();
      return arrayOfObject;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public <R> R[] toArray(R[] paramArrayOfR)
  {
    try
    {
      Object[] arrayOfObject = this.list.toArray(paramArrayOfR);
      return arrayOfObject;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public String toString()
  {
    try
    {
      String str = this.list.toString();
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/SynchronizedQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */