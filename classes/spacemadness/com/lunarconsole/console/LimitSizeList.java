package spacemadness.com.lunarconsole.console;

import java.util.Iterator;
import spacemadness.com.lunarconsole.utils.CycleArray;

public class LimitSizeList<T>
  implements Iterable<T>
{
  private final CycleArray<T> internalArray;
  private final int trimSize;
  
  public LimitSizeList(Class<? extends T> paramClass, int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      throw new IllegalArgumentException("Illegal capacity: " + paramInt1);
    }
    this.internalArray = new CycleArray(paramClass, paramInt1);
    this.trimSize = paramInt2;
  }
  
  public void addObject(T paramT)
  {
    if (willOverflow()) {
      trimHead(this.trimSize);
    }
    this.internalArray.add(paramT);
  }
  
  public int capacity()
  {
    return this.internalArray.getCapacity();
  }
  
  public void clear()
  {
    this.internalArray.clear();
  }
  
  public int count()
  {
    return this.internalArray.realLength();
  }
  
  public int getTrimSize()
  {
    return this.trimSize;
  }
  
  public boolean isOverfloating()
  {
    if ((this.internalArray.getHeadIndex() > 0) && (willOverflow())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isTrimmed()
  {
    if (trimmedCount() > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Iterator<T> iterator()
  {
    return this.internalArray.iterator();
  }
  
  public T objectAtIndex(int paramInt)
  {
    return (T)this.internalArray.get(paramInt + this.internalArray.getHeadIndex());
  }
  
  public int overflowCount()
  {
    return this.internalArray.getHeadIndex();
  }
  
  public int totalCount()
  {
    return this.internalArray.length();
  }
  
  public void trimHead(int paramInt)
  {
    this.internalArray.trimHeadIndex(paramInt);
  }
  
  public int trimmedCount()
  {
    return totalCount() - count();
  }
  
  public boolean willOverflow()
  {
    if (this.internalArray.realLength() == this.internalArray.getCapacity()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/console/LimitSizeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */