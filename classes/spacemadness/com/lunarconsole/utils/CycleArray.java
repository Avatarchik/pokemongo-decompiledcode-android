package spacemadness.com.lunarconsole.utils;

import java.lang.reflect.Array;
import java.util.Iterator;

public class CycleArray<E>
  implements Iterable<E>
{
  private final Class<? extends E> componentType;
  private int headIndex;
  private E[] internalArray;
  private int length;
  
  public CycleArray(Class<? extends E> paramClass, int paramInt)
  {
    if (paramClass == null) {
      throw new NullPointerException("Component type is null");
    }
    this.componentType = paramClass;
    this.internalArray = ((Object[])Array.newInstance(paramClass, paramInt));
  }
  
  private int toArrayIndex(E[] paramArrayOfE, int paramInt)
  {
    return paramInt % paramArrayOfE.length;
  }
  
  public E add(E paramE)
  {
    int i = toArrayIndex(this.length);
    Object localObject = this.internalArray[i];
    this.internalArray[i] = paramE;
    this.length = (1 + this.length);
    if (this.length - this.headIndex > this.internalArray.length) {
      this.headIndex = (1 + this.headIndex);
    }
    for (;;)
    {
      return (E)localObject;
      localObject = null;
    }
  }
  
  public void clear()
  {
    for (int i = 0; i < this.internalArray.length; i++) {
      this.internalArray[i] = null;
    }
    this.length = 0;
    this.headIndex = 0;
  }
  
  public boolean contains(Object paramObject)
  {
    int i = this.headIndex;
    if (i < this.length)
    {
      int j = toArrayIndex(i);
      if (!ObjectUtils.areEqual(this.internalArray[j], paramObject)) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      i++;
      break;
    }
  }
  
  public E get(int paramInt)
  {
    int i = toArrayIndex(paramInt);
    return (E)this.internalArray[i];
  }
  
  public int getCapacity()
  {
    return this.internalArray.length;
  }
  
  public int getHeadIndex()
  {
    return this.headIndex;
  }
  
  public E[] internalArray()
  {
    return this.internalArray;
  }
  
  public boolean isValidIndex(int paramInt)
  {
    if ((paramInt >= this.headIndex) && (paramInt < this.length)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Iterator<E> iterator()
  {
    return new CycleIterator();
  }
  
  public int length()
  {
    return this.length;
  }
  
  public int realLength()
  {
    return this.length - this.headIndex;
  }
  
  public void set(int paramInt, E paramE)
  {
    int i = toArrayIndex(paramInt);
    this.internalArray[i] = paramE;
  }
  
  public void setCapacity(int paramInt)
  {
    if (paramInt > getCapacity())
    {
      arrayOfObject = (Object[])Array.newInstance(this.componentType, paramInt);
      i = realLength();
      j = toArrayIndex(this.internalArray, this.headIndex);
      for (k = toArrayIndex(arrayOfObject, this.headIndex); i > 0; k = toArrayIndex(arrayOfObject, k + m))
      {
        m = Math.min(i, Math.min(this.internalArray.length - j, arrayOfObject.length - k));
        System.arraycopy(this.internalArray, j, arrayOfObject, k, m);
        i -= m;
        j = toArrayIndex(this.internalArray, j + m);
      }
      this.internalArray = arrayOfObject;
    }
    while (paramInt >= getCapacity())
    {
      Object[] arrayOfObject;
      int i;
      int j;
      int k;
      int m;
      return;
    }
    throw new NotImplementedException();
  }
  
  public int toArrayIndex(int paramInt)
  {
    return paramInt % this.internalArray.length;
  }
  
  public void trimHeadIndex(int paramInt)
  {
    trimToHeadIndex(paramInt + this.headIndex);
  }
  
  public void trimLength(int paramInt)
  {
    trimToLength(this.length - paramInt);
  }
  
  public void trimToHeadIndex(int paramInt)
  {
    if ((paramInt < this.headIndex) || (paramInt > this.length)) {
      throw new IllegalArgumentException("Trimmed head index " + paramInt + " should be between head index " + this.headIndex + " and length " + this.length);
    }
    this.headIndex = paramInt;
  }
  
  public void trimToLength(int paramInt)
  {
    if ((paramInt < this.headIndex) || (paramInt > this.length)) {
      throw new IllegalArgumentException("Trimmed length " + paramInt + " should be between head index " + this.headIndex + " and length " + this.length);
    }
    this.length = paramInt;
  }
  
  private class CycleIterator
    implements Iterator<E>
  {
    private int index = CycleArray.this.getHeadIndex();
    
    public CycleIterator() {}
    
    public boolean hasNext()
    {
      if (this.index < CycleArray.this.length()) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public E next()
    {
      CycleArray localCycleArray = CycleArray.this;
      int i = this.index;
      this.index = (i + 1);
      return (E)localCycleArray.get(i);
    }
    
    public void remove()
    {
      throw new NotImplementedException();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/utils/CycleArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */