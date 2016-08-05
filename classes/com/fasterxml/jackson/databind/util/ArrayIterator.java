package com.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T>
  implements Iterator<T>, Iterable<T>
{
  private final T[] _a;
  private int _index;
  
  public ArrayIterator(T[] paramArrayOfT)
  {
    this._a = paramArrayOfT;
    this._index = 0;
  }
  
  public boolean hasNext()
  {
    if (this._index < this._a.length) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Iterator<T> iterator()
  {
    return this;
  }
  
  public T next()
  {
    if (this._index >= this._a.length) {
      throw new NoSuchElementException();
    }
    Object[] arrayOfObject = this._a;
    int i = this._index;
    this._index = (i + 1);
    return (T)arrayOfObject[i];
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/ArrayIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */