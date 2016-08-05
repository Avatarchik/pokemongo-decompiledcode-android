package rx.schedulers;

public class TimeInterval<T>
{
  private final long intervalInMilliseconds;
  private final T value;
  
  public TimeInterval(long paramLong, T paramT)
  {
    this.value = paramT;
    this.intervalInMilliseconds = paramLong;
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
      else if (getClass() != paramObject.getClass())
      {
        bool = false;
      }
      else
      {
        TimeInterval localTimeInterval = (TimeInterval)paramObject;
        if (this.intervalInMilliseconds != localTimeInterval.intervalInMilliseconds) {
          bool = false;
        } else if (this.value == null)
        {
          if (localTimeInterval.value != null) {
            bool = false;
          }
        }
        else if (!this.value.equals(localTimeInterval.value)) {
          bool = false;
        }
      }
    }
  }
  
  public long getIntervalInMilliseconds()
  {
    return this.intervalInMilliseconds;
  }
  
  public T getValue()
  {
    return (T)this.value;
  }
  
  public int hashCode()
  {
    int i = 31 * (31 + (int)(this.intervalInMilliseconds ^ this.intervalInMilliseconds >>> 32));
    if (this.value == null) {}
    for (int j = 0;; j = this.value.hashCode()) {
      return i + j;
    }
  }
  
  public String toString()
  {
    return "TimeInterval [intervalInMilliseconds=" + this.intervalInMilliseconds + ", value=" + this.value + "]";
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/schedulers/TimeInterval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */