package com.fasterxml.jackson.databind.util;

public final class LinkedNode<T>
{
  private LinkedNode<T> next;
  private final T value;
  
  public LinkedNode(T paramT, LinkedNode<T> paramLinkedNode)
  {
    this.value = paramT;
    this.next = paramLinkedNode;
  }
  
  public static <ST> boolean contains(LinkedNode<ST> paramLinkedNode, ST paramST)
  {
    if (paramLinkedNode != null) {
      if (paramLinkedNode.value() != paramST) {}
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      paramLinkedNode = paramLinkedNode.next();
      break;
    }
  }
  
  public void linkNext(LinkedNode<T> paramLinkedNode)
  {
    if (this.next != null) {
      throw new IllegalStateException();
    }
    this.next = paramLinkedNode;
  }
  
  public LinkedNode<T> next()
  {
    return this.next;
  }
  
  public T value()
  {
    return (T)this.value;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/LinkedNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */