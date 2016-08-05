package rx.internal.util;

import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.Subscription;
import rx.functions.Func1;

public final class IndexedRingBuffer<E>
  implements Subscription
{
  private static final ObjectPool<IndexedRingBuffer<?>> POOL = new ObjectPool()
  {
    protected IndexedRingBuffer<?> createObject()
    {
      return new IndexedRingBuffer(null);
    }
  };
  static final int SIZE;
  static int _size = 256;
  private final ElementSection<E> elements = new ElementSection(null);
  final AtomicInteger index = new AtomicInteger();
  private final IndexSection removed = new IndexSection(null);
  final AtomicInteger removedIndex = new AtomicInteger();
  
  static
  {
    if (PlatformDependent.isAndroid()) {
      _size = 8;
    }
    String str = System.getProperty("rx.indexed-ring-buffer.size");
    if (str != null) {}
    try
    {
      _size = Integer.parseInt(str);
      SIZE = _size;
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        System.err.println("Failed to set 'rx.indexed-ring-buffer.size' with value " + str + " => " + localException.getMessage());
      }
    }
  }
  
  private int forEach(Func1<? super E, Boolean> paramFunc1, int paramInt1, int paramInt2)
  {
    int i = this.index.get();
    int j = paramInt1;
    ElementSection localElementSection = this.elements;
    if (paramInt1 >= SIZE)
    {
      localElementSection = getElementSection(paramInt1);
      paramInt1 %= SIZE;
    }
    for (;;)
    {
      int m;
      if (localElementSection != null)
      {
        m = paramInt1;
        if (m >= SIZE) {
          break label130;
        }
        if ((j < i) && (j < paramInt2)) {
          break label76;
        }
      }
      label76:
      int n;
      for (int k = j;; k = n)
      {
        return k;
        Object localObject = localElementSection.array.get(m);
        if (localObject == null) {}
        do
        {
          m++;
          j++;
          break;
          n = j;
        } while (((Boolean)paramFunc1.call(localObject)).booleanValue());
      }
      label130:
      localElementSection = (ElementSection)localElementSection.next.get();
      paramInt1 = 0;
    }
  }
  
  private ElementSection<E> getElementSection(int paramInt)
  {
    ElementSection localElementSection;
    if (paramInt < SIZE) {
      localElementSection = this.elements;
    }
    for (;;)
    {
      return localElementSection;
      int i = paramInt / SIZE;
      localElementSection = this.elements;
      for (int j = 0; j < i; j++) {
        localElementSection = localElementSection.getNext();
      }
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  private int getIndexForAdd()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 154	rx/internal/util/IndexedRingBuffer:getIndexFromPreviouslyRemoved	()I
    //   6: istore_2
    //   7: iload_2
    //   8: iflt +71 -> 79
    //   11: iload_2
    //   12: getstatic 62	rx/internal/util/IndexedRingBuffer:SIZE	I
    //   15: if_icmpge +40 -> 55
    //   18: aload_0
    //   19: getfield 99	rx/internal/util/IndexedRingBuffer:removed	Lrx/internal/util/IndexedRingBuffer$IndexSection;
    //   22: iload_2
    //   23: bipush -1
    //   25: invokevirtual 158	rx/internal/util/IndexedRingBuffer$IndexSection:getAndSet	(II)I
    //   28: istore 4
    //   30: iload 4
    //   32: aload_0
    //   33: getfield 104	rx/internal/util/IndexedRingBuffer:index	Ljava/util/concurrent/atomic/AtomicInteger;
    //   36: invokevirtual 113	java/util/concurrent/atomic/AtomicInteger:get	()I
    //   39: if_icmpne +11 -> 50
    //   42: aload_0
    //   43: getfield 104	rx/internal/util/IndexedRingBuffer:index	Ljava/util/concurrent/atomic/AtomicInteger;
    //   46: invokevirtual 161	java/util/concurrent/atomic/AtomicInteger:getAndIncrement	()I
    //   49: pop
    //   50: aload_0
    //   51: monitorexit
    //   52: iload 4
    //   54: ireturn
    //   55: iload_2
    //   56: getstatic 62	rx/internal/util/IndexedRingBuffer:SIZE	I
    //   59: irem
    //   60: istore 5
    //   62: aload_0
    //   63: iload_2
    //   64: invokespecial 165	rx/internal/util/IndexedRingBuffer:getIndexSection	(I)Lrx/internal/util/IndexedRingBuffer$IndexSection;
    //   67: iload 5
    //   69: bipush -1
    //   71: invokevirtual 158	rx/internal/util/IndexedRingBuffer$IndexSection:getAndSet	(II)I
    //   74: istore 4
    //   76: goto -46 -> 30
    //   79: aload_0
    //   80: getfield 104	rx/internal/util/IndexedRingBuffer:index	Ljava/util/concurrent/atomic/AtomicInteger;
    //   83: invokevirtual 161	java/util/concurrent/atomic/AtomicInteger:getAndIncrement	()I
    //   86: istore_3
    //   87: iload_3
    //   88: istore 4
    //   90: goto -40 -> 50
    //   93: astore_1
    //   94: aload_0
    //   95: monitorexit
    //   96: aload_1
    //   97: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	98	0	this	IndexedRingBuffer
    //   93	4	1	localObject	Object
    //   6	58	2	i	int
    //   86	2	3	j	int
    //   28	61	4	k	int
    //   60	8	5	m	int
    // Exception table:
    //   from	to	target	type
    //   2	50	93	finally
    //   55	87	93	finally
  }
  
  /* Error */
  /**
   * @deprecated
   */
  private int getIndexFromPreviouslyRemoved()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 106	rx/internal/util/IndexedRingBuffer:removedIndex	Ljava/util/concurrent/atomic/AtomicInteger;
    //   6: invokevirtual 113	java/util/concurrent/atomic/AtomicInteger:get	()I
    //   9: istore_2
    //   10: iload_2
    //   11: ifle +29 -> 40
    //   14: aload_0
    //   15: getfield 106	rx/internal/util/IndexedRingBuffer:removedIndex	Ljava/util/concurrent/atomic/AtomicInteger;
    //   18: iload_2
    //   19: iload_2
    //   20: iconst_1
    //   21: isub
    //   22: invokevirtual 169	java/util/concurrent/atomic/AtomicInteger:compareAndSet	(II)Z
    //   25: istore 4
    //   27: iload 4
    //   29: ifeq -27 -> 2
    //   32: iload_2
    //   33: iconst_1
    //   34: isub
    //   35: istore_3
    //   36: aload_0
    //   37: monitorexit
    //   38: iload_3
    //   39: ireturn
    //   40: bipush -1
    //   42: istore_3
    //   43: goto -7 -> 36
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	this	IndexedRingBuffer
    //   46	4	1	localObject	Object
    //   9	26	2	i	int
    //   35	8	3	j	int
    //   25	3	4	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	27	46	finally
  }
  
  private IndexSection getIndexSection(int paramInt)
  {
    IndexSection localIndexSection;
    if (paramInt < SIZE) {
      localIndexSection = this.removed;
    }
    for (;;)
    {
      return localIndexSection;
      int i = paramInt / SIZE;
      localIndexSection = this.removed;
      for (int j = 0; j < i; j++) {
        localIndexSection = localIndexSection.getNext();
      }
    }
  }
  
  public static final <T> IndexedRingBuffer<T> getInstance()
  {
    return (IndexedRingBuffer)POOL.borrowObject();
  }
  
  /* Error */
  /**
   * @deprecated
   */
  private void pushRemovedIndex(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 106	rx/internal/util/IndexedRingBuffer:removedIndex	Ljava/util/concurrent/atomic/AtomicInteger;
    //   6: invokevirtual 161	java/util/concurrent/atomic/AtomicInteger:getAndIncrement	()I
    //   9: istore_3
    //   10: iload_3
    //   11: getstatic 62	rx/internal/util/IndexedRingBuffer:SIZE	I
    //   14: if_icmpge +15 -> 29
    //   17: aload_0
    //   18: getfield 99	rx/internal/util/IndexedRingBuffer:removed	Lrx/internal/util/IndexedRingBuffer$IndexSection;
    //   21: iload_3
    //   22: iload_1
    //   23: invokevirtual 185	rx/internal/util/IndexedRingBuffer$IndexSection:set	(II)V
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: iload_3
    //   30: getstatic 62	rx/internal/util/IndexedRingBuffer:SIZE	I
    //   33: irem
    //   34: istore 4
    //   36: aload_0
    //   37: iload_3
    //   38: invokespecial 165	rx/internal/util/IndexedRingBuffer:getIndexSection	(I)Lrx/internal/util/IndexedRingBuffer$IndexSection;
    //   41: iload 4
    //   43: iload_1
    //   44: invokevirtual 185	rx/internal/util/IndexedRingBuffer$IndexSection:set	(II)V
    //   47: goto -21 -> 26
    //   50: astore_2
    //   51: aload_0
    //   52: monitorexit
    //   53: aload_2
    //   54: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	55	0	this	IndexedRingBuffer
    //   0	55	1	paramInt	int
    //   50	4	2	localObject	Object
    //   9	29	3	i	int
    //   34	8	4	j	int
    // Exception table:
    //   from	to	target	type
    //   2	26	50	finally
    //   29	47	50	finally
  }
  
  public int add(E paramE)
  {
    int i = getIndexForAdd();
    if (i < SIZE) {
      this.elements.array.set(i, paramE);
    }
    for (;;)
    {
      return i;
      int j = i % SIZE;
      getElementSection(i).array.set(j, paramE);
    }
  }
  
  public int forEach(Func1<? super E, Boolean> paramFunc1)
  {
    return forEach(paramFunc1, 0);
  }
  
  public int forEach(Func1<? super E, Boolean> paramFunc1, int paramInt)
  {
    int i = forEach(paramFunc1, paramInt, this.index.get());
    if ((paramInt > 0) && (i == this.index.get())) {
      i = forEach(paramFunc1, 0, paramInt);
    }
    for (;;)
    {
      return i;
      if (i == this.index.get()) {
        i = 0;
      }
    }
  }
  
  public boolean isUnsubscribed()
  {
    return false;
  }
  
  public void releaseToPool()
  {
    int i = this.index.get();
    int j = 0;
    for (ElementSection localElementSection = this.elements;; localElementSection = (ElementSection)localElementSection.next.get())
    {
      int k;
      if (localElementSection != null) {
        k = 0;
      }
      while (k < SIZE)
      {
        if (j >= i)
        {
          this.index.set(0);
          this.removedIndex.set(0);
          POOL.returnObject(this);
          return;
        }
        localElementSection.array.set(k, null);
        k++;
        j++;
      }
    }
  }
  
  public E remove(int paramInt)
  {
    if (paramInt < SIZE) {}
    int i;
    for (Object localObject = this.elements.array.getAndSet(paramInt, null);; localObject = getElementSection(paramInt).array.getAndSet(i, null))
    {
      pushRemovedIndex(paramInt);
      return (E)localObject;
      i = paramInt % SIZE;
    }
  }
  
  public void unsubscribe()
  {
    releaseToPool();
  }
  
  private static class IndexSection
  {
    private final AtomicReference<IndexSection> _next = new AtomicReference();
    private final AtomicIntegerArray unsafeArray = new AtomicIntegerArray(IndexedRingBuffer.SIZE);
    
    public int getAndSet(int paramInt1, int paramInt2)
    {
      return this.unsafeArray.getAndSet(paramInt1, paramInt2);
    }
    
    IndexSection getNext()
    {
      Object localObject;
      if (this._next.get() != null) {
        localObject = (IndexSection)this._next.get();
      }
      for (;;)
      {
        return (IndexSection)localObject;
        IndexSection localIndexSection = new IndexSection();
        if (this._next.compareAndSet(null, localIndexSection)) {
          localObject = localIndexSection;
        } else {
          localObject = (IndexSection)this._next.get();
        }
      }
    }
    
    public void set(int paramInt1, int paramInt2)
    {
      this.unsafeArray.set(paramInt1, paramInt2);
    }
  }
  
  private static class ElementSection<E>
  {
    private final AtomicReferenceArray<E> array = new AtomicReferenceArray(IndexedRingBuffer.SIZE);
    private final AtomicReference<ElementSection<E>> next = new AtomicReference();
    
    ElementSection<E> getNext()
    {
      Object localObject;
      if (this.next.get() != null) {
        localObject = (ElementSection)this.next.get();
      }
      for (;;)
      {
        return (ElementSection<E>)localObject;
        ElementSection localElementSection = new ElementSection();
        if (this.next.compareAndSet(null, localElementSection)) {
          localObject = localElementSection;
        } else {
          localObject = (ElementSection)this.next.get();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/IndexedRingBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */