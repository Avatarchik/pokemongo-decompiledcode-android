package com.upsight.android.internal.persistence;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.upsight.android.UpsightException;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

class OnSubscribeFetchById
  implements Observable.OnSubscribe<Storable>
{
  private final Context mContext;
  private final String[] mIds;
  private final String mType;
  
  OnSubscribeFetchById(Context paramContext, String paramString, String... paramVarArgs)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("Provided Context can not be null.");
    }
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Provided type can not be empty or null.");
    }
    if ((paramVarArgs == null) || (paramVarArgs.length == 0)) {
      throw new IllegalArgumentException("Object identifiers can not be null or empty.");
    }
    this.mContext = paramContext;
    this.mType = paramString;
    this.mIds = paramVarArgs;
  }
  
  public void call(Subscriber<? super Storable> paramSubscriber)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("_id").append(" IN (");
    for (int i = 0; i < this.mIds.length; i++)
    {
      localStringBuffer.append("?");
      if (i < -1 + this.mIds.length) {
        localStringBuffer.append(",");
      }
    }
    localStringBuffer.append(")");
    localCursor = null;
    for (;;)
    {
      try
      {
        localCursor = this.mContext.getContentResolver().query(Content.getContentUri(this.mContext), null, localStringBuffer.toString(), this.mIds, null);
        if (localCursor == null)
        {
          paramSubscriber.onError(new UpsightException("Unable to retrieve stored objects.", new Object[0]));
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        paramSubscriber.onError(localThrowable);
        if (localCursor == null) {
          continue;
        }
        localCursor.close();
        continue;
        paramSubscriber.onCompleted();
        if (localCursor == null) {
          continue;
        }
        localCursor.close();
        continue;
      }
      finally
      {
        if (localCursor == null) {
          continue;
        }
        localCursor.close();
      }
      if (localCursor.getCount() == this.mIds.length) {
        continue;
      }
      paramSubscriber.onError(new UpsightException("Unable to retrieve stored objects. Some ID(s) were not found.", new Object[0]));
      if (localCursor != null) {
        localCursor.close();
      }
    }
    while (localCursor.moveToNext()) {
      paramSubscriber.onNext(Storable.create(localCursor.getString(localCursor.getColumnIndex("_id")), localCursor.getString(localCursor.getColumnIndex("type")), localCursor.getString(localCursor.getColumnIndex("data"))));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/OnSubscribeFetchById.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */