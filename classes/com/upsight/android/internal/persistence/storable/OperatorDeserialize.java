package com.upsight.android.internal.persistence.storable;

import com.upsight.android.UpsightException;
import com.upsight.android.internal.persistence.Storable;
import com.upsight.android.persistence.UpsightStorableSerializer;
import rx.Observable.Operator;
import rx.Subscriber;

class OperatorDeserialize<T>
  implements Observable.Operator<T, Storable>
{
  private final StorableInfo<T> mStorableInfo;
  
  public OperatorDeserialize(StorableInfo<T> paramStorableInfo)
  {
    if (paramStorableInfo == null) {
      throw new IllegalArgumentException("StorableInfo can not be null.");
    }
    this.mStorableInfo = paramStorableInfo;
  }
  
  public Subscriber<? super Storable> call(Subscriber<? super T> paramSubscriber)
  {
    return new DeserializeSubscriber(this.mStorableInfo, paramSubscriber);
  }
  
  private static class DeserializeSubscriber<T>
    extends Subscriber<Storable>
  {
    private final Subscriber<? super T> mChildSubscriber;
    private final StorableInfo<T> mStorableInfo;
    
    public DeserializeSubscriber(StorableInfo<T> paramStorableInfo, Subscriber<? super T> paramSubscriber)
    {
      super();
      this.mStorableInfo = paramStorableInfo;
      this.mChildSubscriber = paramSubscriber;
    }
    
    public void onCompleted()
    {
      this.mChildSubscriber.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.mChildSubscriber.onError(paramThrowable);
    }
    
    public void onNext(Storable paramStorable)
    {
      UpsightStorableSerializer localUpsightStorableSerializer = this.mStorableInfo.getDeserializer();
      try
      {
        if (!this.mChildSubscriber.isUnsubscribed())
        {
          Object localObject = localUpsightStorableSerializer.fromString(paramStorable.getValue());
          this.mStorableInfo.getIdentifierAccessor().setId(localObject, paramStorable.getID());
          this.mChildSubscriber.onNext(localObject);
        }
        return;
      }
      catch (UpsightException localUpsightException)
      {
        for (;;)
        {
          this.mChildSubscriber.onError(localUpsightException);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/OperatorDeserialize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */