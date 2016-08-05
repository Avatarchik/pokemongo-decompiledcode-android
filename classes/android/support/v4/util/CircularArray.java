package android.support.v4.util;

public final class CircularArray<E>
{
  private int mCapacityBitmask;
  private E[] mElements;
  private int mHead;
  private int mTail;
  
  public CircularArray()
  {
    this(8);
  }
  
  public CircularArray(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("capacity must be positive");
    }
    int i = paramInt;
    if (Integer.bitCount(paramInt) != 1) {
      i = 1 << 1 + Integer.highestOneBit(paramInt);
    }
    this.mCapacityBitmask = (i - 1);
    this.mElements = ((Object[])new Object[i]);
  }
  
  private void doubleCapacity()
  {
    int i = this.mElements.length;
    int j = i - this.mHead;
    int k = i << 1;
    if (k < 0) {
      throw new RuntimeException("Max array capacity exceeded");
    }
    Object[] arrayOfObject = new Object[k];
    System.arraycopy(this.mElements, this.mHead, arrayOfObject, 0, j);
    System.arraycopy(this.mElements, 0, arrayOfObject, j, this.mHead);
    this.mElements = ((Object[])arrayOfObject);
    this.mHead = 0;
    this.mTail = i;
    this.mCapacityBitmask = (k - 1);
  }
  
  public void addFirst(E paramE)
  {
    this.mHead = (-1 + this.mHead & this.mCapacityBitmask);
    this.mElements[this.mHead] = paramE;
    if (this.mHead == this.mTail) {
      doubleCapacity();
    }
  }
  
  public void addLast(E paramE)
  {
    this.mElements[this.mTail] = paramE;
    this.mTail = (1 + this.mTail & this.mCapacityBitmask);
    if (this.mTail == this.mHead) {
      doubleCapacity();
    }
  }
  
  public void clear()
  {
    removeFromStart(size());
  }
  
  public E get(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= size())) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return (E)this.mElements[(paramInt + this.mHead & this.mCapacityBitmask)];
  }
  
  public E getFirst()
  {
    if (this.mHead == this.mTail) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return (E)this.mElements[this.mHead];
  }
  
  public E getLast()
  {
    if (this.mHead == this.mTail) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return (E)this.mElements[(-1 + this.mTail & this.mCapacityBitmask)];
  }
  
  public boolean isEmpty()
  {
    if (this.mHead == this.mTail) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public E popFirst()
  {
    if (this.mHead == this.mTail) {
      throw new ArrayIndexOutOfBoundsException();
    }
    Object localObject = this.mElements[this.mHead];
    this.mElements[this.mHead] = null;
    this.mHead = (1 + this.mHead & this.mCapacityBitmask);
    return (E)localObject;
  }
  
  public E popLast()
  {
    if (this.mHead == this.mTail) {
      throw new ArrayIndexOutOfBoundsException();
    }
    int i = -1 + this.mTail & this.mCapacityBitmask;
    Object localObject = this.mElements[i];
    this.mElements[i] = null;
    this.mTail = i;
    return (E)localObject;
  }
  
  public void removeFromEnd(int paramInt)
  {
    if (paramInt <= 0) {}
    for (;;)
    {
      return;
      if (paramInt > size()) {
        throw new ArrayIndexOutOfBoundsException();
      }
      int i = 0;
      if (paramInt < this.mTail) {
        i = this.mTail - paramInt;
      }
      for (int j = i; j < this.mTail; j++) {
        this.mElements[j] = null;
      }
      int k = this.mTail - i;
      int m = paramInt - k;
      this.mTail -= k;
      if (m > 0)
      {
        this.mTail = this.mElements.length;
        int n = this.mTail - m;
        for (int i1 = n; i1 < this.mTail; i1++) {
          this.mElements[i1] = null;
        }
        this.mTail = n;
      }
    }
  }
  
  public void removeFromStart(int paramInt)
  {
    if (paramInt <= 0) {}
    for (;;)
    {
      return;
      if (paramInt > size()) {
        throw new ArrayIndexOutOfBoundsException();
      }
      int i = this.mElements.length;
      if (paramInt < i - this.mHead) {
        i = paramInt + this.mHead;
      }
      for (int j = this.mHead; j < i; j++) {
        this.mElements[j] = null;
      }
      int k = i - this.mHead;
      int m = paramInt - k;
      this.mHead = (k + this.mHead & this.mCapacityBitmask);
      if (m > 0)
      {
        for (int n = 0; n < m; n++) {
          this.mElements[n] = null;
        }
        this.mHead = m;
      }
    }
  }
  
  public int size()
  {
    return this.mTail - this.mHead & this.mCapacityBitmask;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/util/CircularArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */