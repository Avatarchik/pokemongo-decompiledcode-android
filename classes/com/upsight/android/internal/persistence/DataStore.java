package com.upsight.android.internal.persistence;

import android.content.Context;
import android.text.TextUtils;
import com.squareup.otto.Bus;
import com.upsight.android.UpsightException;
import com.upsight.android.internal.persistence.storable.StorableIdFactory;
import com.upsight.android.internal.persistence.storable.StorableIdentifierAccessor;
import com.upsight.android.internal.persistence.storable.StorableInfo;
import com.upsight.android.internal.persistence.storable.StorableInfoCache;
import com.upsight.android.internal.persistence.storable.StorableTypeAccessor;
import com.upsight.android.internal.persistence.storable.Storables;
import com.upsight.android.internal.persistence.subscription.Subscriptions;
import com.upsight.android.internal.util.LoggingListener;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightDataStoreListener;
import com.upsight.android.persistence.UpsightStorableSerializer;
import com.upsight.android.persistence.UpsightSubscription;
import com.upsight.android.persistence.annotation.UpsightStorableType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

class DataStore
  implements UpsightDataStore
{
  private final Bus mBus;
  private final Context mContext;
  private final StorableIdFactory mIdFactory;
  private final StorableInfoCache mInfoCache;
  private final Scheduler mObserveOnScheduler;
  private final Scheduler mSubscribeOnScheduler;
  
  DataStore(Context paramContext, StorableInfoCache paramStorableInfoCache, StorableIdFactory paramStorableIdFactory, Scheduler paramScheduler1, Scheduler paramScheduler2, Bus paramBus)
  {
    this.mContext = paramContext;
    this.mInfoCache = paramStorableInfoCache;
    this.mIdFactory = paramStorableIdFactory;
    this.mSubscribeOnScheduler = paramScheduler1;
    this.mObserveOnScheduler = paramScheduler2;
    this.mBus = paramBus;
  }
  
  public <T> UpsightSubscription fetch(Class<T> paramClass, final UpsightDataStoreListener<Set<T>> paramUpsightDataStoreListener)
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("A non null class must be specified for fetch(..)");
    }
    if (paramUpsightDataStoreListener == null) {
      throw new IllegalArgumentException("A non null listener must be specified for fetch(..)");
    }
    Subscriptions.from(fetchObservable(paramClass).toList().subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new Action1()new Action1
    {
      public void call(List<T> paramAnonymousList)
      {
        paramUpsightDataStoreListener.onSuccess(new HashSet(paramAnonymousList));
      }
    }, new Action1()
    {
      public void call(Throwable paramAnonymousThrowable)
      {
        paramUpsightDataStoreListener.onFailure(new UpsightException(paramAnonymousThrowable));
      }
    }));
  }
  
  public <T> UpsightSubscription fetch(Class<T> paramClass, Set<String> paramSet, final UpsightDataStoreListener<Set<T>> paramUpsightDataStoreListener)
  {
    Subscriptions.from(fetchObservable(paramClass, (String[])paramSet.toArray(new String[paramSet.size()])).toList().subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new Action1()new Action1
    {
      public void call(List<T> paramAnonymousList)
      {
        paramUpsightDataStoreListener.onSuccess(new HashSet(paramAnonymousList));
      }
    }, new Action1()
    {
      public void call(Throwable paramAnonymousThrowable)
      {
        paramUpsightDataStoreListener.onFailure(new UpsightException(paramAnonymousThrowable));
      }
    }));
  }
  
  public <T> Observable<T> fetchObservable(Class<T> paramClass)
  {
    StorableInfo localStorableInfo;
    Object localObject;
    try
    {
      localStorableInfo = this.mInfoCache.get(paramClass);
      if (localStorableInfo == null)
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = paramClass.getName();
        arrayOfObject2[1] = Storable.class.getSimpleName();
        throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", arrayOfObject2));
      }
    }
    catch (UpsightException localUpsightException)
    {
      localObject = Observable.error(localUpsightException);
    }
    for (;;)
    {
      return (Observable<T>)localObject;
      String str = localStorableInfo.getStorableTypeAccessor().getType();
      if (TextUtils.isEmpty(str))
      {
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = paramClass.getSimpleName();
        arrayOfObject1[1] = UpsightStorableType.class.getSimpleName();
        throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", arrayOfObject1));
      }
      Observable localObservable = ContentObservables.fetch(this.mContext, str).lift(Storables.deserialize(localStorableInfo));
      localObject = localObservable;
    }
  }
  
  public <T> Observable<T> fetchObservable(Class<T> paramClass, String... paramVarArgs)
  {
    StorableInfo localStorableInfo;
    Object localObject;
    try
    {
      localStorableInfo = this.mInfoCache.get(paramClass);
      if (localStorableInfo == null)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramClass.getName();
        arrayOfObject[1] = Storable.class.getSimpleName();
        throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", arrayOfObject));
      }
    }
    catch (UpsightException localUpsightException)
    {
      localObject = Observable.error(localUpsightException);
    }
    for (;;)
    {
      return (Observable<T>)localObject;
      String str = localStorableInfo.getStorableTypeAccessor().getType();
      Observable localObservable = ContentObservables.fetch(this.mContext, str, paramVarArgs).lift(Storables.deserialize(localStorableInfo));
      localObject = localObservable;
    }
  }
  
  public <T> UpsightSubscription remove(Class<T> paramClass, Set<String> paramSet)
  {
    return remove(paramClass, paramSet, new LoggingListener());
  }
  
  public <T> UpsightSubscription remove(Class<T> paramClass, Set<String> paramSet, final UpsightDataStoreListener<Set<T>> paramUpsightDataStoreListener)
  {
    Subscriptions.from(removeObservable(paramClass, (String[])paramSet.toArray(new String[paramSet.size()])).toList().subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new Action1()new Action1
    {
      public void call(List<T> paramAnonymousList)
      {
        paramUpsightDataStoreListener.onSuccess(new HashSet(paramAnonymousList));
      }
    }, new Action1()
    {
      public void call(Throwable paramAnonymousThrowable)
      {
        paramUpsightDataStoreListener.onFailure(new UpsightException(paramAnonymousThrowable));
      }
    }));
  }
  
  public <T> UpsightSubscription remove(T paramT)
  {
    return remove(paramT, new LoggingListener());
  }
  
  public <T> UpsightSubscription remove(T paramT, final UpsightDataStoreListener<T> paramUpsightDataStoreListener)
  {
    if (paramUpsightDataStoreListener == null) {
      throw new IllegalArgumentException("Listener can not be null.");
    }
    Subscriptions.from(removeObservable(paramT).subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new Action1()new Action1
    {
      public void call(T paramAnonymousT)
      {
        paramUpsightDataStoreListener.onSuccess(paramAnonymousT);
      }
    }, new Action1()
    {
      public void call(Throwable paramAnonymousThrowable)
      {
        paramUpsightDataStoreListener.onFailure(new UpsightException(paramAnonymousThrowable));
      }
    }));
  }
  
  public <T> Observable<T> removeObservable(Class<T> paramClass, String... paramVarArgs)
  {
    StorableInfo localStorableInfo;
    Object localObject;
    try
    {
      localStorableInfo = this.mInfoCache.get(paramClass);
      if (localStorableInfo == null)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramClass.getName();
        arrayOfObject[1] = Storable.class.getSimpleName();
        throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", arrayOfObject));
      }
    }
    catch (UpsightException localUpsightException)
    {
      localObject = Observable.error(localUpsightException);
    }
    for (;;)
    {
      return (Observable<T>)localObject;
      String str = localStorableInfo.getStorableTypeAccessor().getType();
      Observable localObservable = ContentObservables.fetch(this.mContext, str, paramVarArgs).lift(Storables.deserialize(localStorableInfo)).flatMap(new Func1()
      {
        public Observable<T> call(T paramAnonymousT)
        {
          return DataStore.this.removeObservable(paramAnonymousT);
        }
      });
      localObject = localObservable;
    }
  }
  
  public <T> Observable<T> removeObservable(T paramT)
  {
    int i = 1;
    if (paramT == null) {
      throw new IllegalArgumentException("Object can not be null.");
    }
    Class localClass = paramT.getClass();
    for (;;)
    {
      Object localObject;
      try
      {
        StorableInfo localStorableInfo = this.mInfoCache.get(localClass);
        if (localStorableInfo != null)
        {
          if (TextUtils.isEmpty(localStorableInfo.getIdentifierAccessor().getId(paramT))) {
            break label211;
          }
          if (i != 0)
          {
            String str = localStorableInfo.getStorableTypeAccessor().getType(paramT);
            Observable localObservable2 = Observable.just(paramT);
            localObject = localObservable2.lift(Storables.serialize(localStorableInfo, this.mIdFactory)).flatMap(new Func1()
            {
              public Observable<Storable> call(Storable paramAnonymousStorable)
              {
                return ContentObservables.remove(DataStore.this.mContext, paramAnonymousStorable);
              }
            }).zipWith(localObservable2, new Func2()
            {
              public T call(Storable paramAnonymousStorable, T paramAnonymousT)
              {
                return paramAnonymousT;
              }
            }).doOnNext(Subscriptions.publishRemoved(this.mBus, str));
          }
          else
          {
            localObject = Observable.error(new UpsightException("Object must be stored before removal.", new Object[0]));
          }
        }
        else
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = localClass.getName();
          arrayOfObject[1] = Storable.class.getSimpleName();
          Observable localObservable1 = Observable.error(new UpsightException("Class %s must be annotated with @%s", arrayOfObject));
          localObject = localObservable1;
        }
      }
      catch (UpsightException localUpsightException)
      {
        localObject = Observable.error(localUpsightException);
      }
      return (Observable<T>)localObject;
      label211:
      i = 0;
    }
  }
  
  public <T> void setSerializer(Class<T> paramClass, UpsightStorableSerializer<T> paramUpsightStorableSerializer)
  {
    if (paramClass == null) {
      throw new IllegalArgumentException("Class can not be null.");
    }
    if (paramUpsightStorableSerializer == null) {
      throw new IllegalArgumentException("UpsightStorableSerializer can not be null.");
    }
    this.mInfoCache.setSerializer(paramClass, paramUpsightStorableSerializer);
  }
  
  public <T> UpsightSubscription store(T paramT)
  {
    return store(paramT, new LoggingListener());
  }
  
  public <T> UpsightSubscription store(T paramT, final UpsightDataStoreListener<T> paramUpsightDataStoreListener)
  {
    Subscriptions.from(storeObservable(paramT).subscribeOn(this.mSubscribeOnScheduler).observeOn(this.mObserveOnScheduler).subscribe(new Action1()new Action1
    {
      public void call(T paramAnonymousT)
      {
        paramUpsightDataStoreListener.onSuccess(paramAnonymousT);
      }
    }, new Action1()
    {
      public void call(Throwable paramAnonymousThrowable)
      {
        paramUpsightDataStoreListener.onFailure(new UpsightException(paramAnonymousThrowable));
      }
    }));
  }
  
  public <T> Observable<T> storeObservable(T paramT)
  {
    final boolean bool = true;
    if (paramT == null) {
      throw new IllegalArgumentException("Attempting to store null object.");
    }
    Class localClass = paramT.getClass();
    StorableInfo localStorableInfo;
    Object localObject;
    try
    {
      localStorableInfo = this.mInfoCache.get(localClass);
      if (localStorableInfo != null) {
        break label87;
      }
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localClass.getName();
      arrayOfObject[1] = Storable.class.getSimpleName();
      throw new IllegalArgumentException(String.format("Class %s must be annotated with @%s", arrayOfObject));
    }
    catch (UpsightException localUpsightException)
    {
      localObject = Observable.error(localUpsightException);
    }
    return (Observable<T>)localObject;
    label87:
    if (!TextUtils.isEmpty(localStorableInfo.getIdentifierAccessor().getId(paramT))) {}
    for (;;)
    {
      if (!bool) {
        localStorableInfo.getIdentifierAccessor().setId(paramT, this.mIdFactory.createObjectID());
      }
      Observable localObservable1 = Observable.just(paramT);
      Observable localObservable2 = localObservable1.lift(Storables.serialize(localStorableInfo, this.mIdFactory)).flatMap(new Func1()
      {
        public Observable<Storable> call(Storable paramAnonymousStorable)
        {
          if (bool) {}
          for (Observable localObservable = ContentObservables.update(DataStore.this.mContext, paramAnonymousStorable);; localObservable = ContentObservables.insert(DataStore.this.mContext, paramAnonymousStorable)) {
            return localObservable;
          }
        }
      }).zipWith(localObservable1, new Func2()
      {
        public T call(Storable paramAnonymousStorable, T paramAnonymousT)
        {
          return paramAnonymousT;
        }
      });
      String str = localStorableInfo.getStorableTypeAccessor().getType(paramT);
      if (bool)
      {
        localObject = localObservable2.doOnNext(Subscriptions.publishUpdated(this.mBus, str));
        break;
      }
      Observable localObservable3 = localObservable2.doOnNext(Subscriptions.publishCreated(this.mBus, str));
      localObject = localObservable3;
      break;
      bool = false;
    }
  }
  
  public UpsightSubscription subscribe(Object paramObject)
  {
    return Subscriptions.from(Subscriptions.toObservable(this.mBus).observeOn(this.mObserveOnScheduler).subscribe(Subscriptions.create(paramObject)));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/DataStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */