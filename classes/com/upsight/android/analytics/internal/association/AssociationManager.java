package com.upsight.android.analytics.internal.association;

import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract interface AssociationManager
{
  public abstract void associate(String paramString, ObjectNode paramObjectNode);
  
  public abstract void launch();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/association/AssociationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */