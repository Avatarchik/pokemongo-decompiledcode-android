package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import rx.Observable.OnSubscribe;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;

public final class AsyncSubject<T>
  extends Subject<T, T>
{
  volatile Object lastValue;
  private final NotificationLite<T> nl = NotificationLite.instance();
  final SubjectSubscriptionManager<T> state;
  
  protected AsyncSubject(Observable.OnSubscribe<T> paramOnSubscribe, SubjectSubscriptionManager<T> paramSubjectSubscriptionManager)
  {
    super(paramOnSubscribe);
    this.state = paramSubjectSubscriptionManager;
  }
  
  public static <T> AsyncSubject<T> create()
  {
    SubjectSubscriptionManager localSubjectSubscriptionManager = new SubjectSubscriptionManager();
    localSubjectSubscriptionManager.onTerminated = new Action1()
    {
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramAnonymousSubjectObserver)
      {
        Object localObject = AsyncSubject.this.get();
        NotificationLite localNotificationLite = AsyncSubject.this.nl;
        paramAnonymousSubjectObserver.accept(localObject, localNotificationLite);
        if ((localObject == null) || ((!localNotificationLite.isCompleted(localObject)) && (!localNotificationLite.isError(localObject)))) {
          paramAnonymousSubjectObserver.onCompleted();
        }
      }
    };
    return new AsyncSubject(localSubjectSubscriptionManager, localSubjectSubscriptionManager);
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
    Object localObject1 = this.lastValue;
    Object localObject2 = this.state.get();
    if ((!this.nl.isError(localObject2)) && (this.nl.isNext(localObject1))) {}
    for (Object localObject3 = this.nl.getValue(localObject1);; localObject3 = null) {
      return (T)localObject3;
    }
  }
  
  @Experimental
  public T[] getValues(T[] paramArrayOfT)
  {
    Object localObject1 = this.lastValue;
    Object localObject2 = this.state.get();
    if ((!this.nl.isError(localObject2)) && (this.nl.isNext(localObject1)))
    {
      Object localObject3 = this.nl.getValue(localObject1);
      if (paramArrayOfT.length == 0) {
        paramArrayOfT = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), 1);
      }
      paramArrayOfT[0] = localObject3;
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
    if ((localObject != null) && (!this.nl.isError(localObject))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
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
    Object localObject1 = this.lastValue;
    Object localObject2 = this.state.get();
    if ((!this.nl.isError(localObject2)) && (this.nl.isNext(localObject1))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onCompleted()
  {
    if (this.state.active)
    {
      Object localObject = this.lastValue;
      if (localObject == null) {
        localObject = this.nl.completed();
      }
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.state.terminate(localObject);
      int i = arrayOfSubjectObserver.length;
      int j = 0;
      if (j < i)
      {
        SubjectSubscriptionManager.SubjectObserver localSubjectObserver = arrayOfSubjectObserver[j];
        if (localObject == this.nl.completed()) {
          localSubjectObserver.onCompleted();
        }
        for (;;)
        {
          j++;
          break;
          localSubjectObserver.onNext(this.nl.getValue(localObject));
          localSubjectObserver.onCompleted();
        }
      }
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    if (this.state.active)
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
            localSubjectObserver.onError(paramThrowable);
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
    this.lastValue = this.nl.next(paramT);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/subjects/AsyncSubject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */