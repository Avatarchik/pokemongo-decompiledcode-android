package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.List;

public final class ObjectBuffer
{
  private static final int MAX_CHUNK = 262144;
  private static final int SMALL_CHUNK = 16384;
  private Object[] _freeBuffer;
  private LinkedNode<Object[]> _head;
  private int _size;
  private LinkedNode<Object[]> _tail;
  
  protected final void _copyTo(Object paramObject, int paramInt1, Object[] paramArrayOfObject, int paramInt2)
  {
    int i = 0;
    for (LinkedNode localLinkedNode = this._head; localLinkedNode != null; localLinkedNode = localLinkedNode.next())
    {
      Object[] arrayOfObject = (Object[])localLinkedNode.value();
      int k = arrayOfObject.length;
      System.arraycopy(arrayOfObject, 0, paramObject, i, k);
      i += k;
    }
    System.arraycopy(paramArrayOfObject, 0, paramObject, i, paramInt2);
    int j = i + paramInt2;
    if (j != paramInt1) {
      throw new IllegalStateException("Should have gotten " + paramInt1 + " entries, got " + j);
    }
  }
  
  protected void _reset()
  {
    if (this._tail != null) {
      this._freeBuffer = ((Object[])this._tail.value());
    }
    this._tail = null;
    this._head = null;
    this._size = 0;
  }
  
  public Object[] appendCompletedChunk(Object[] paramArrayOfObject)
  {
    LinkedNode localLinkedNode = new LinkedNode(paramArrayOfObject, null);
    int i;
    if (this._head == null)
    {
      this._tail = localLinkedNode;
      this._head = localLinkedNode;
      i = paramArrayOfObject.length;
      this._size = (i + this._size);
      if (i >= 16384) {
        break label72;
      }
      i += i;
    }
    for (;;)
    {
      return new Object[i];
      this._tail.linkNext(localLinkedNode);
      this._tail = localLinkedNode;
      break;
      label72:
      if (i < 262144) {
        i += (i >> 2);
      }
    }
  }
  
  public int bufferedSize()
  {
    return this._size;
  }
  
  public void completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt, List<Object> paramList)
  {
    for (LinkedNode localLinkedNode = this._head; localLinkedNode != null; localLinkedNode = localLinkedNode.next())
    {
      Object[] arrayOfObject = (Object[])localLinkedNode.value();
      int j = 0;
      int k = arrayOfObject.length;
      while (j < k)
      {
        paramList.add(arrayOfObject[j]);
        j++;
      }
    }
    for (int i = 0; i < paramInt; i++) {
      paramList.add(paramArrayOfObject[i]);
    }
  }
  
  public Object[] completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt)
  {
    int i = paramInt + this._size;
    Object[] arrayOfObject = new Object[i];
    _copyTo(arrayOfObject, i, paramArrayOfObject, paramInt);
    return arrayOfObject;
  }
  
  public <T> T[] completeAndClearBuffer(Object[] paramArrayOfObject, int paramInt, Class<T> paramClass)
  {
    int i = paramInt + this._size;
    Object[] arrayOfObject = (Object[])Array.newInstance(paramClass, i);
    _copyTo(arrayOfObject, i, paramArrayOfObject, paramInt);
    _reset();
    return arrayOfObject;
  }
  
  public int initialCapacity()
  {
    if (this._freeBuffer == null) {}
    for (int i = 0;; i = this._freeBuffer.length) {
      return i;
    }
  }
  
  public Object[] resetAndStart()
  {
    _reset();
    if (this._freeBuffer == null) {}
    for (Object[] arrayOfObject = new Object[12];; arrayOfObject = this._freeBuffer) {
      return arrayOfObject;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/ObjectBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */