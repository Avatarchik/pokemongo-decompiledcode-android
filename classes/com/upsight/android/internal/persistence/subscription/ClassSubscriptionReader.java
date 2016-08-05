package com.upsight.android.internal.persistence.subscription;

import com.upsight.android.persistence.annotation.Created;
import com.upsight.android.persistence.annotation.Removed;
import com.upsight.android.persistence.annotation.Updated;
import com.upsight.android.persistence.annotation.UpsightStorableType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class ClassSubscriptionReader
{
  private final Class<?> mClass;
  
  ClassSubscriptionReader(Class<?> paramClass)
  {
    this.mClass = paramClass;
  }
  
  private boolean isSubscriptionMethod(Method paramMethod)
  {
    boolean bool = false;
    if (!paramMethod.getReturnType().equals(Void.TYPE)) {}
    for (;;)
    {
      return bool;
      Class[] arrayOfClass = paramMethod.getParameterTypes();
      if ((arrayOfClass.length == 1) && ((UpsightStorableType)arrayOfClass[0].getAnnotation(UpsightStorableType.class) != null) && (Modifier.isPublic(paramMethod.getModifiers()))) {
        bool = true;
      }
    }
  }
  
  public void accept(ClassSubscriptionVisitor paramClassSubscriptionVisitor)
  {
    Method[] arrayOfMethod = this.mClass.getMethods();
    int i = arrayOfMethod.length;
    int j = 0;
    if (j < i)
    {
      Method localMethod = arrayOfMethod[j];
      if (!isSubscriptionMethod(localMethod)) {}
      for (;;)
      {
        j++;
        break;
        Class localClass = localMethod.getParameterTypes()[0];
        if ((Created)localMethod.getAnnotation(Created.class) != null) {
          paramClassSubscriptionVisitor.visitCreatedSubscription(localMethod, localClass);
        }
        if ((Updated)localMethod.getAnnotation(Updated.class) != null) {
          paramClassSubscriptionVisitor.visitUpdatedSubscription(localMethod, localClass);
        }
        if ((Removed)localMethod.getAnnotation(Removed.class) != null) {
          paramClassSubscriptionVisitor.visitRemovedSubscription(localMethod, localClass);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/subscription/ClassSubscriptionReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */