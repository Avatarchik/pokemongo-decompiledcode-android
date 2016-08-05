package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import rx.Observable.OnSubscribe;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;

public final class BehaviorSubject<T>
  extends Subject<T, T>
{
  private final NotificationLite<T> nl = NotificationLite.instance();
  private final SubjectSubscriptionManager<T> state;
  
  protected BehaviorSubject(Observable.OnSubscribe<T> paramOnSubscribe, SubjectSubscriptionManager<T> paramSubjectSubscriptionManager)
  {
    super(paramOnSubscribe);
    this.state = paramSubjectSubscriptionManager;
  }
  
  public static <T> BehaviorSubject<T> create()
  {
    return create(null, false);
  }
  
  public static <T> BehaviorSubject<T> create(T paramT)
  {
    return create(paramT, true);
  }
  
  private static <T> BehaviorSubject<T> create(T paramT, boolean paramBoolean)
  {
    SubjectSubscriptionManager localSubjectSubscriptionManager = new SubjectSubscriptionManager();
    if (paramBoolean) {
      localSubjectSubscriptionManager.set(NotificationLite.instance().next(paramT));
    }
    localSubjectSubscriptionManager.onAdded = new Action1()
    {
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramAnonymousSubjectObserver)
      {
        paramAnonymousSubjectObserver.emitFirst(BehaviorSubject.this.get(), BehaviorSubject.this.nl);
      }
    };
    localSubjectSubscriptionManager.onTerminated = localSubjectSubscriptionManager.onAdded;
    return new BehaviorSubject(localSubjectSubscriptionManager, localSubjectSubscriptionManager);
  }
  
  @Experimental
  public Throwable getThrowable()
  {
    Object localObject = this.state.get();
    if (this.nl.isError(localObject)) {}
    for (Throwable localThrowable = this.nl.getError(localObject);; localThrowable = null) {
      return localThrowable;
    }
  }
  
  @Experimental
  public T getValue()
  {
    Object localObject1 = this.state.get();
    if (this.nl.isNext(localObject1)) {}
    for (Object localObject2 = this.nl.getValue(localObject1);; localObject2 = null) {
      return (T)localObject2;
    }
  }
  
  @Experimental
  public T[] getValues(T[] paramArrayOfT)
  {
    Object localObject = this.state.get();
    if (this.nl.isNext(localObject))
    {
      if (paramArrayOfT.length == 0) {
        paramArrayOfT = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), 1);
      }
      paramArrayOfT[0] = this.nl.getValue(localObject);
      if (paramArrayOfT.length > 1) {
        paramArrayOfT[1] = null;
      }
    }
    for (;;)
    {
      return paramArrayOfT;
      if (paramArrayOfT.length > 0) {
        paramArrayOfT[0] = null;
      }
    }
  }
  
  @Experimental
  public boolean hasCompleted()
  {
    Object localObject = this.state.get();
    return this.nl.isCompleted(localObject);
  }
  
  public boolean hasObservers()
  {
    if (this.state.observers().length > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Experimental
  public boolean hasThrowable()
  {
    Object localObject = this.state.get();
    return this.nl.isError(localObject);
  }
  
  @Experimental
  public boolean hasValue()
  {
    Object localObject = this.state.get();
    return this.nl.isNext(localObject);
  }
  
  public void onCompleted()
  {
    if ((this.state.get() == null) || (this.state.active))
    {
      Object localObject = this.nl.completed();
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.terminate(localObject);
      int i = arrayOfSubjectObserver.length;
      for (int j = 0; j < i; j++) {
        arrayOfSubjectObserver[j].emitNext(localObject, this.state.nl);
      }
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    if ((this.state.get() == null) || (this.state.active))
    {
      Object localObject = this.nl.error(paramThrowable);
      ArrayList localArrayList = null;
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.terminate(localObject);
      int i = arrayOfSubjectObserver.length;
      int j = 0;
      for (;;)
      {
        if (j < i)
        {
          SubjectSubscriptionManager.SubjectObserver localSubjectObserver = arrayOfSubjectObserver[j];
          try
          {
            localSubjectObserver.emitNext(localObject, this.state.nl);
            j++;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              if (localArrayList == null) {
                localArrayList = new ArrayList();
              }
              localArrayList.add(localThrowable);
            }
          }
        }
      }
      Exceptions.throwIfAny(localArrayList);
    }
  }
  
  public void onNext(T paramT)
  {
    if ((this.state.get() == null) || (this.state.active))
    {
      Object localObject = this.nl.next(paramT);
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.next(localObject);
      int i = arrayOfSubjectObserver.length;
      for (int j = 0; j < i; j++) {
        arrayOfSubjectObserver[j].emitNext(localObject, this.state.nl);
      }
    }
  }
  
  int subscriberCount()
  {
    return this.state.observers().length;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/subjects/BehaviorSubject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */