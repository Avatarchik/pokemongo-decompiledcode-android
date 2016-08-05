package com.upsight.android.internal.persistence.subscription;

class DataStoreEvent
{
  public final Action action;
  public final Object source;
  public final String sourceType;
  
  DataStoreEvent(Action paramAction, String paramString, Object paramObject)
  {
    this.action = paramAction;
    this.sourceType = paramString;
    this.source = paramObject;
  }
  
  public static enum Action
  {
    static
    {
      Removed = new Action("Removed", 2);
      Action[] arrayOfAction = new Action[3];
      arrayOfAction[0] = Created;
      arrayOfAction[1] = Updated;
      arrayOfAction[2] = Removed;
      $VALUES = arrayOfAction;
    }
    
    private Action() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/subscription/DataStoreEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */