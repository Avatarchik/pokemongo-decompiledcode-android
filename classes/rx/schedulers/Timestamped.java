package rx.schedulers;

public final class Timestamped<T>
{
  private final long timestampMillis;
  private final T value;
  
  public Timestamped(long paramLong, T paramT)
  {
    this.value = paramT;
    this.timestampMillis = paramLong;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (paramObject == null)
      {
        bool = false;
      }
      else if (!(paramObject instanceof Timestamped))
      {
        bool = false;
      }
      else
      {
        Timestamped localTimestamped = (Timestamped)paramObject;
        if (this.timestampMillis != localTimestamped.timestampMillis) {
          bool = false;
        } else if (this.value == null)
        {
          if (localTimestamped.value != null) {
            bool = false;
          }
        }
        else if (!this.value.equals(localTimestamped.value)) {
          bool = false;
        }
      }
    }
  }
  
  public long getTimestampMillis()
  {
    return this.timestampMillis;
  }
  
  public T getValue()
  {
    return (T)this.value;
  }
  
  public int hashCode()
  {
    int i = 31 * (31 + (int)(this.timestampMillis ^ this.timestampMillis >>> 32));
    if (this.value == null) {}
    for (int j = 0;; j = this.value.hashCode()) {
      return i + j;
    }
  }
  
  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Long.valueOf(this.timestampMillis);
    arrayOfObject[1] = this.value.toString();
    return String.format("Timestamped(timestampMillis = %d, value = %s)", arrayOfObject);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/schedulers/Timestamped.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */