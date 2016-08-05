package com.fasterxml.jackson.databind.util;

public abstract class PrimitiveArrayBuilder<T>
{
  static final int INITIAL_CHUNK_SIZE = 12;
  static final int MAX_CHUNK_SIZE = 262144;
  static final int SMALL_CHUNK_SIZE = 16384;
  protected Node<T> _bufferHead;
  protected Node<T> _bufferTail;
  protected int _bufferedEntryCount;
  protected T _freeBuffer;
  
  protected abstract T _constructArray(int paramInt);
  
  protected void _reset()
  {
    if (this._bufferTail != null) {
      this._freeBuffer = this._bufferTail.getData();
    }
    this._bufferTail = null;
    this._bufferHead = null;
    this._bufferedEntryCount = 0;
  }
  
  public final T appendCompletedChunk(T paramT, int paramInt)
  {
    Node localNode = new Node(paramT, paramInt);
    if (this._bufferHead == null)
    {
      this._bufferTail = localNode;
      this._bufferHead = localNode;
      this._bufferedEntryCount = (paramInt + this._bufferedEntryCount);
      if (paramInt >= 16384) {
        break label72;
      }
    }
    label72:
    for (int i = paramInt + paramInt;; i = paramInt + (paramInt >> 2))
    {
      return (T)_constructArray(i);
      this._bufferTail.linkNext(localNode);
      this._bufferTail = localNode;
      break;
    }
  }
  
  public int bufferedSize()
  {
    return this._bufferedEntryCount;
  }
  
  public T completeAndClearBuffer(T paramT, int paramInt)
  {
    int i = paramInt + this._bufferedEntryCount;
    Object localObject = _constructArray(i);
    int j = 0;
    for (Node localNode = this._bufferHead; localNode != null; localNode = localNode.next()) {
      j = localNode.copyData(localObject, j);
    }
    System.arraycopy(paramT, 0, localObject, j, paramInt);
    int k = j + paramInt;
    if (k != i) {
      throw new IllegalStateException("Should have gotten " + i + " entries, got " + k);
    }
    return (T)localObject;
  }
  
  public T resetAndStart()
  {
    _reset();
    if (this._freeBuffer == null) {}
    for (Object localObject = _constructArray(12);; localObject = this._freeBuffer) {
      return (T)localObject;
    }
  }
  
  static final class Node<T>
  {
    final T _data;
    final int _dataLength;
    Node<T> _next;
    
    public Node(T paramT, int paramInt)
    {
      this._data = paramT;
      this._dataLength = paramInt;
    }
    
    public int copyData(T paramT, int paramInt)
    {
      System.arraycopy(this._data, 0, paramT, paramInt, this._dataLength);
      return paramInt + this._dataLength;
    }
    
    public T getData()
    {
      return (T)this._data;
    }
    
    public void linkNext(Node<T> paramNode)
    {
      if (this._next != null) {
        throw new IllegalStateException();
      }
      this._next = paramNode;
    }
    
    public Node<T> next()
    {
      return this._next;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/PrimitiveArrayBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */