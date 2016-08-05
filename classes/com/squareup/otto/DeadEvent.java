package com.squareup.otto;

public class DeadEvent
{
  public final Object event;
  public final Object source;
  
  public DeadEvent(Object paramObject1, Object paramObject2)
  {
    this.source = paramObject1;
    this.event = paramObject2;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/squareup/otto/DeadEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */