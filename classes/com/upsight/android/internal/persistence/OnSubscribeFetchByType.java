package com.upsight.android.internal.persistence;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.upsight.android.UpsightException;
import java.lang.ref.WeakReference;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

class OnSubscribeFetchByType
  implements Observable.OnSubscribe<Storable>
{
  private final WeakReference<Context> reference;
  private final String type;
  
  OnSubscribeFetchByType(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      throw new IllegalArgumentException("Provided Context can not be null.");
    }
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Provided type can not be empty or null.");
    }
    this.reference = new WeakReference(paramContext);
    this.type = paramString;
  }
  
  public void call(Subscriber<? super Storable> paramSubscriber)
  {
    Context localContext = (Context)this.reference.get();
    if (localContext == null) {
      paramSubscriber.onError(new IllegalStateException("Context has been reclaimed by Android."));
    }
    for (;;)
    {
      return;
      Cursor localCursor = null;
      try
      {
        Uri localUri = Content.getContentTypeUri(localContext, this.type);
        localCursor = localContext.getContentResolver().query(localUri, null, null, null, null);
        if (localCursor == null)
        {
          paramSubscriber.onError(new UpsightException("Unable to retrieve stored objects.", new Object[0]));
          if (localCursor == null) {
            continue;
          }
          localCursor.close();
          continue;
        }
        while (localCursor.moveToNext()) {
          paramSubscriber.onNext(Storable.create(localCursor.getString(localCursor.getColumnIndex("_id")), localCursor.getString(localCursor.getColumnIndex("type")), localCursor.getString(localCursor.getColumnIndex("data"))));
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
      }
      finally
      {
        if (localCursor != null) {
          localCursor.close();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/OnSubscribeFetchByType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */