package com.upsight.android.internal.persistence.subscription;

import java.lang.reflect.Method;

abstract interface ClassSubscriptionVisitor
{
  public abstract void visitCreatedSubscription(Method paramMethod, Class<?> paramClass);
  
  public abstract void visitRemovedSubscription(Method paramMethod, Class<?> paramClass);
  
  public abstract void visitUpdatedSubscription(Method paramMethod, Class<?> paramClass);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/subscription/ClassSubscriptionVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */