package com.upsight.android.internal.persistence.storable;

import com.upsight.android.UpsightException;
import com.upsight.android.internal.persistence.Storable;
import com.upsight.android.persistence.UpsightStorableSerializer;
import rx.Observable.Operator;
import rx.Subscriber;

class OperatorSerialize<T>
  implements Observable.Operator<Storable, T>
{
  private final StorableIdFactory mIDFactory;
  private final StorableInfo<T> mStorableInfo;
  
  public OperatorSerialize(StorableInfo<T> paramStorableInfo, StorableIdFactory paramStorableIdFactory)
  {
    if (paramStorableInfo == null) {
      throw new IllegalArgumentException("StorableInfo can not be null.");
    }
    this.mStorableInfo = paramStorableInfo;
    this.mIDFactory = paramStorableIdFactory;
  }
  
  public Subscriber<? super T> call(Subscriber<? super Storable> paramSubscriber)
  {
    return new DeserializeSubscriber(this.mStorableInfo, this.mIDFactory, paramSubscriber);
  }
  
  private static class DeserializeSubscriber<T>
    extends Subscriber<T>
  {
    private final Subscriber<? super Storable> mChildSubscriber;
    private final StorableIdFactory mIdFactory;
    private final StorableInfo<T> mStorableInfo;
    
    public DeserializeSubscriber(StorableInfo<T> paramStorableInfo, StorableIdFactory paramStorableIdFactory, Subscriber<? super Storable> paramSubscriber)
    {
      super();
      this.mStorableInfo = paramStorableInfo;
      this.mChildSubscriber = paramSubscriber;
      this.mIdFactory = paramStorableIdFactory;
    }
    
    public void onCompleted()
    {
      this.mChildSubscriber.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.mChildSubscriber.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      UpsightStorableSerializer localUpsightStorableSerializer = this.mStorableInfo.getDeserializer();
      try
      {
        if (!this.mChildSubscriber.isUnsubscribed())
        {
          String str1 = this.mStorableInfo.getIdentifierAccessor().getId(paramT);
          String str2 = this.mStorableInfo.getStorableTypeAccessor().getType(paramT);
          String str3 = localUpsightStorableSerializer.toString(paramT);
          this.mChildSubscriber.onNext(Storable.create(str1, str2, str3));
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/OperatorSerialize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */