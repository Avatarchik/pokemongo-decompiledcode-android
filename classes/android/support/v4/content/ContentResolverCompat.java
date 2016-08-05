package android.support.v4.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.os.CancellationSignal;
import android.support.v4.os.OperationCanceledException;

public class ContentResolverCompat
{
  private static final ContentResolverCompatImpl IMPL;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 16) {}
    for (IMPL = new ContentResolverCompatImplJB();; IMPL = new ContentResolverCompatImplBase()) {
      return;
    }
  }
  
  public static Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal)
  {
    return IMPL.query(paramContentResolver, paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, paramCancellationSignal);
  }
  
  static class ContentResolverCompatImplJB
    extends ContentResolverCompat.ContentResolverCompatImplBase
  {
    public Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal)
    {
      if (paramCancellationSignal != null) {}
      for (;;)
      {
        try
        {
          localObject = paramCancellationSignal.getCancellationSignalObject();
          Cursor localCursor = ContentResolverCompatJellybean.query(paramContentResolver, paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2, localObject);
          return localCursor;
        }
        catch (Exception localException)
        {
          Object localObject;
          if (!ContentResolverCompatJellybean.isFrameworkOperationCanceledException(localException)) {
            continue;
          }
          throw new OperationCanceledException();
          throw localException;
        }
        localObject = null;
      }
    }
  }
  
  static class ContentResolverCompatImplBase
    implements ContentResolverCompat.ContentResolverCompatImpl
  {
    public Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal)
    {
      if (paramCancellationSignal != null) {
        paramCancellationSignal.throwIfCanceled();
      }
      return paramContentResolver.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
    }
  }
  
  static abstract interface ContentResolverCompatImpl
  {
    public abstract Cursor query(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/content/ContentResolverCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */