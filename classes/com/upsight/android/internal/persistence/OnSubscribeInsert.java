package com.upsight.android.internal.persistence;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

class OnSubscribeInsert
  implements Observable.OnSubscribe<Storable>
{
  private final Context mContext;
  private final Storable mStorable;
  
  OnSubscribeInsert(Context paramContext, Storable paramStorable)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("Provided Context can not be null.");
    }
    if (paramStorable == null) {
      throw new IllegalArgumentException("Provided UpsightStorable can not be null.");
    }
    this.mContext = paramContext;
    this.mStorable = paramStorable;
  }
  
  public void call(Subscriber<? super Storable> paramSubscriber)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_id", this.mStorable.getID());
    localContentValues.put("type", this.mStorable.getType());
    localContentValues.put("data", this.mStorable.getValue());
    Uri localUri = Uri.withAppendedPath(Content.getContentUri(this.mContext), this.mStorable.getType());
    if (this.mContext.getContentResolver().insert(localUri, localContentValues) == null) {
      throw new IllegalStateException("Unable to persist model!");
    }
    paramSubscriber.onNext(this.mStorable);
    paramSubscriber.onCompleted();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/OnSubscribeInsert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */