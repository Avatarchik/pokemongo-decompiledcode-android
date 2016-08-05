package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public class ViewMatcher
  implements Serializable
{
  protected static final ViewMatcher EMPTY = new ViewMatcher();
  private static final long serialVersionUID = 1L;
  
  public static ViewMatcher construct(Class<?>[] paramArrayOfClass)
  {
    Object localObject;
    if (paramArrayOfClass == null) {
      localObject = EMPTY;
    }
    for (;;)
    {
      return (ViewMatcher)localObject;
      switch (paramArrayOfClass.length)
      {
      default: 
        localObject = new Multi(paramArrayOfClass);
        break;
      case 0: 
        localObject = EMPTY;
        break;
      case 1: 
        localObject = new Single(paramArrayOfClass[0]);
      }
    }
  }
  
  public boolean isVisibleForView(Class<?> paramClass)
  {
    return false;
  }
  
  private static final class Multi
    extends ViewMatcher
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private final Class<?>[] _views;
    
    public Multi(Class<?>[] paramArrayOfClass)
    {
      this._views = paramArrayOfClass;
    }
    
    public boolean isVisibleForView(Class<?> paramClass)
    {
      int i = 0;
      int j = this._views.length;
      if (i < j)
      {
        Class localClass = this._views[i];
        if ((paramClass != localClass) && (!localClass.isAssignableFrom(paramClass))) {}
      }
      for (boolean bool = true;; bool = false)
      {
        return bool;
        i++;
        break;
      }
    }
  }
  
  private static final class Single
    extends ViewMatcher
  {
    private static final long serialVersionUID = 1L;
    private final Class<?> _view;
    
    public Single(Class<?> paramClass)
    {
      this._view = paramClass;
    }
    
    public boolean isVisibleForView(Class<?> paramClass)
    {
      if ((paramClass == this._view) || (this._view.isAssignableFrom(paramClass))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/ViewMatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */