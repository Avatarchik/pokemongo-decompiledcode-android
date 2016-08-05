package rx.internal.util.unsafe;

abstract class SpmcArrayQueueProducerIndexCacheField<E>
  extends SpmcArrayQueueMidPad<E>
{
  private volatile long producerIndexCache;
  
  public SpmcArrayQueueProducerIndexCacheField(int paramInt)
  {
    super(paramInt);
  }
  
  protected final long lvProducerIndexCache()
  {
    return this.producerIndexCache;
  }
  
  protected final void svProducerIndexCache(long paramLong)
  {
    this.producerIndexCache = paramLong;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/unsafe/SpmcArrayQueueProducerIndexCacheField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */