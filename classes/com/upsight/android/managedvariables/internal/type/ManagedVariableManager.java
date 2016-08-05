package com.upsight.android.managedvariables.internal.type;

import com.upsight.android.UpsightException;
import com.upsight.android.internal.persistence.subscription.Subscriptions;
import com.upsight.android.managedvariables.type.UpsightManagedBoolean;
import com.upsight.android.managedvariables.type.UpsightManagedFloat;
import com.upsight.android.managedvariables.type.UpsightManagedInt;
import com.upsight.android.managedvariables.type.UpsightManagedString;
import com.upsight.android.managedvariables.type.UpsightManagedVariable.Listener;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightSubscription;
import com.upsight.android.persistence.annotation.Created;
import com.upsight.android.persistence.annotation.Removed;
import com.upsight.android.persistence.annotation.Updated;
import java.util.HashMap;
import java.util.Map;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.BlockingObservable;

public class ManagedVariableManager
{
  private static final Map<Class<? extends ManagedVariable>, Class<? extends ManagedVariableModel>> sModelMap = new HashMap() {};
  private final Map<String, ManagedVariable> mCache = new HashMap();
  private Scheduler mCallbackScheduler;
  private UpsightDataStore mDataStore;
  private UxmSchema mUxmSchema;
  
  ManagedVariableManager(Scheduler paramScheduler, UpsightDataStore paramUpsightDataStore, UxmSchema paramUxmSchema)
  {
    this.mCallbackScheduler = paramScheduler;
    this.mDataStore = paramUpsightDataStore;
    this.mUxmSchema = paramUxmSchema;
    paramUpsightDataStore.subscribe(this);
  }
  
  private <T extends ManagedVariable> Observable<? extends ManagedVariableModel> fetchDataStoreObservable(final Class<T> paramClass, final String paramString)
  {
    this.mDataStore.fetchObservable((Class)sModelMap.get(paramClass)).filter(new Func1()
    {
      public Boolean call(ManagedVariableModel paramAnonymousManagedVariableModel)
      {
        return Boolean.valueOf(ManagedVariableManager.this.mUxmSchema.get(paramClass, paramString).tag.equals(paramAnonymousManagedVariableModel.getTag()));
      }
    }).defaultIfEmpty(null);
  }
  
  private <T extends ManagedVariable> T fromModel(Class<T> paramClass, String paramString, ManagedVariableModel paramManagedVariableModel)
  {
    Object localObject1 = null;
    Object localObject2 = null;
    UxmSchema.BaseSchema localBaseSchema = this.mUxmSchema.get(paramClass, paramString);
    if (localBaseSchema == null) {
      return (T)localObject1;
    }
    if (UpsightManagedString.class.equals(paramClass))
    {
      String str = (String)localBaseSchema.defaultValue;
      if (paramManagedVariableModel != null) {
        localObject1 = paramManagedVariableModel.getValue();
      }
      localObject2 = new ManagedString(paramString, str, (String)localObject1);
    }
    for (;;)
    {
      localObject1 = localObject2;
      break;
      if (UpsightManagedBoolean.class.equals(paramClass))
      {
        Boolean localBoolean = (Boolean)localBaseSchema.defaultValue;
        if (paramManagedVariableModel != null) {
          localObject1 = paramManagedVariableModel.getValue();
        }
        localObject2 = new ManagedBoolean(paramString, localBoolean, (Boolean)localObject1);
      }
      else if (UpsightManagedInt.class.equals(paramClass))
      {
        Integer localInteger = (Integer)localBaseSchema.defaultValue;
        if (paramManagedVariableModel != null) {
          localObject1 = paramManagedVariableModel.getValue();
        }
        localObject2 = new ManagedInt(paramString, localInteger, (Integer)localObject1);
      }
      else if (UpsightManagedFloat.class.equals(paramClass))
      {
        Float localFloat = (Float)localBaseSchema.defaultValue;
        if (paramManagedVariableModel != null) {
          localObject1 = paramManagedVariableModel.getValue();
        }
        localObject2 = new ManagedFloat(paramString, localFloat, (Float)localObject1);
      }
    }
  }
  
  private <T extends ManagedVariable> void resetValue(Class<T> paramClass, String paramString)
  {
    synchronized (this.mCache)
    {
      ManagedVariable localManagedVariable = (ManagedVariable)this.mCache.get(paramString);
      if ((localManagedVariable != null) && (paramClass.isInstance(localManagedVariable))) {
        localManagedVariable.set(null);
      }
      return;
    }
  }
  
  private <T extends ManagedVariable> void updateValue(Class<T> paramClass, String paramString, Object paramObject)
  {
    synchronized (this.mCache)
    {
      ManagedVariable localManagedVariable = (ManagedVariable)this.mCache.get(paramString);
      if ((localManagedVariable != null) && (paramClass.isInstance(localManagedVariable))) {
        localManagedVariable.set(paramObject);
      }
      return;
    }
  }
  
  public <T extends ManagedVariable> T fetch(Class<T> paramClass, String paramString)
  {
    synchronized (this.mCache)
    {
      ManagedVariable localManagedVariable1 = (ManagedVariable)this.mCache.get(paramString);
      ManagedVariable localManagedVariable2;
      if (localManagedVariable1 != null) {
        localManagedVariable2 = localManagedVariable1;
      }
      do
      {
        return localManagedVariable2;
        localManagedVariable2 = fromModel(paramClass, paramString, (ManagedVariableModel)fetchDataStoreObservable(paramClass, paramString).toBlocking().first());
      } while (localManagedVariable2 == null);
      this.mCache.put(paramString, localManagedVariable2);
    }
  }
  
  public <T extends ManagedVariable> UpsightSubscription fetch(final Class<T> paramClass, final String paramString, final UpsightManagedVariable.Listener<T> paramListener)
  {
    synchronized (this.mCache)
    {
      ManagedVariable localManagedVariable = (ManagedVariable)this.mCache.get(paramString);
      if (localManagedVariable != null)
      {
        localUpsightSubscription = Subscriptions.from(Observable.just(localManagedVariable).observeOn(this.mCallbackScheduler).subscribe(new Action1()
        {
          public void call(T paramAnonymousT)
          {
            paramListener.onSuccess(paramAnonymousT);
          }
        }));
        return localUpsightSubscription;
      }
      UpsightSubscription localUpsightSubscription = Subscriptions.from(fetchDataStoreObservable(paramClass, paramString).subscribe(new Action1()new Action1
      {
        public void call(ManagedVariableModel paramAnonymousManagedVariableModel)
        {
          synchronized (ManagedVariableManager.this.mCache)
          {
            ManagedVariable localManagedVariable1 = (ManagedVariable)ManagedVariableManager.this.mCache.get(paramString);
            if (localManagedVariable1 != null)
            {
              paramListener.onSuccess(localManagedVariable1);
              return;
            }
            ManagedVariable localManagedVariable2 = ManagedVariableManager.this.fromModel(paramClass, paramString, paramAnonymousManagedVariableModel);
            if (localManagedVariable2 != null)
            {
              ManagedVariableManager.this.mCache.put(paramString, localManagedVariable2);
              paramListener.onSuccess(localManagedVariable2);
            }
          }
        }
      }, new Action1()
      {
        public void call(Throwable paramAnonymousThrowable)
        {
          paramListener.onFailure(new UpsightException(paramAnonymousThrowable));
        }
      }));
    }
  }
  
  @Removed
  public void handleManagedVariableRemoval(ManagedBoolean.Model paramModel)
  {
    resetValue(UpsightManagedBoolean.class, paramModel.getTag());
  }
  
  @Removed
  public void handleManagedVariableRemoval(ManagedFloat.Model paramModel)
  {
    resetValue(UpsightManagedFloat.class, paramModel.getTag());
  }
  
  @Removed
  public void handleManagedVariableRemoval(ManagedInt.Model paramModel)
  {
    resetValue(UpsightManagedInt.class, paramModel.getTag());
  }
  
  @Removed
  public void handleManagedVariableRemoval(ManagedString.Model paramModel)
  {
    resetValue(UpsightManagedString.class, paramModel.getTag());
  }
  
  @Created
  @Updated
  public void handleManagedVariableUpdate(ManagedBoolean.Model paramModel)
  {
    updateValue(UpsightManagedBoolean.class, paramModel.getTag(), paramModel.getValue());
  }
  
  @Created
  @Updated
  public void handleManagedVariableUpdate(ManagedFloat.Model paramModel)
  {
    updateValue(UpsightManagedFloat.class, paramModel.getTag(), paramModel.getValue());
  }
  
  @Created
  @Updated
  public void handleManagedVariableUpdate(ManagedInt.Model paramModel)
  {
    updateValue(UpsightManagedInt.class, paramModel.getTag(), paramModel.getValue());
  }
  
  @Created
  @Updated
  public void handleManagedVariableUpdate(ManagedString.Model paramModel)
  {
    updateValue(UpsightManagedString.class, paramModel.getTag(), paramModel.getValue());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/ManagedVariableManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */