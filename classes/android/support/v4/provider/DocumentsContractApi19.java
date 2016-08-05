package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;

class DocumentsContractApi19
{
  private static final String TAG = "DocumentFile";
  
  public static boolean canRead(Context paramContext, Uri paramUri)
  {
    boolean bool = false;
    if (paramContext.checkCallingOrSelfUriPermission(paramUri, 1) != 0) {}
    for (;;)
    {
      return bool;
      if (!TextUtils.isEmpty(getRawType(paramContext, paramUri))) {
        bool = true;
      }
    }
  }
  
  public static boolean canWrite(Context paramContext, Uri paramUri)
  {
    boolean bool = false;
    if (paramContext.checkCallingOrSelfUriPermission(paramUri, 2) != 0) {}
    for (;;)
    {
      return bool;
      String str = getRawType(paramContext, paramUri);
      int i = queryForInt(paramContext, paramUri, "flags", 0);
      if (!TextUtils.isEmpty(str)) {
        if ((i & 0x4) != 0) {
          bool = true;
        } else if (("vnd.android.document/directory".equals(str)) && ((i & 0x8) != 0)) {
          bool = true;
        } else if ((!TextUtils.isEmpty(str)) && ((i & 0x2) != 0)) {
          bool = true;
        }
      }
    }
  }
  
  private static void closeQuietly(AutoCloseable paramAutoCloseable)
  {
    if (paramAutoCloseable != null) {}
    try
    {
      paramAutoCloseable.close();
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public static boolean delete(Context paramContext, Uri paramUri)
  {
    return DocumentsContract.deleteDocument(paramContext.getContentResolver(), paramUri);
  }
  
  public static boolean exists(Context paramContext, Uri paramUri)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    localCursor = null;
    for (;;)
    {
      try
      {
        String[] arrayOfString = new String[1];
        arrayOfString[0] = "document_id";
        localCursor = localContentResolver.query(paramUri, arrayOfString, null, null, null);
        int i = localCursor.getCount();
        if (i <= 0) {
          continue;
        }
        bool = true;
      }
      catch (Exception localException)
      {
        Log.w("DocumentFile", "Failed query: " + localException);
        closeQuietly(localCursor);
        boolean bool = false;
        continue;
      }
      finally
      {
        closeQuietly(localCursor);
      }
      return bool;
      bool = false;
    }
  }
  
  public static String getName(Context paramContext, Uri paramUri)
  {
    return queryForString(paramContext, paramUri, "_display_name", null);
  }
  
  private static String getRawType(Context paramContext, Uri paramUri)
  {
    return queryForString(paramContext, paramUri, "mime_type", null);
  }
  
  public static String getType(Context paramContext, Uri paramUri)
  {
    String str = getRawType(paramContext, paramUri);
    if ("vnd.android.document/directory".equals(str)) {
      str = null;
    }
    return str;
  }
  
  public static boolean isDirectory(Context paramContext, Uri paramUri)
  {
    return "vnd.android.document/directory".equals(getRawType(paramContext, paramUri));
  }
  
  public static boolean isDocumentUri(Context paramContext, Uri paramUri)
  {
    return DocumentsContract.isDocumentUri(paramContext, paramUri);
  }
  
  public static boolean isFile(Context paramContext, Uri paramUri)
  {
    String str = getRawType(paramContext, paramUri);
    if (("vnd.android.document/directory".equals(str)) || (TextUtils.isEmpty(str))) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public static long lastModified(Context paramContext, Uri paramUri)
  {
    return queryForLong(paramContext, paramUri, "last_modified", 0L);
  }
  
  public static long length(Context paramContext, Uri paramUri)
  {
    return queryForLong(paramContext, paramUri, "_size", 0L);
  }
  
  private static int queryForInt(Context paramContext, Uri paramUri, String paramString, int paramInt)
  {
    return (int)queryForLong(paramContext, paramUri, paramString, paramInt);
  }
  
  private static long queryForLong(Context paramContext, Uri paramUri, String paramString, long paramLong)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    localCursor = null;
    for (;;)
    {
      try
      {
        String[] arrayOfString = new String[1];
        arrayOfString[0] = paramString;
        localCursor = localContentResolver.query(paramUri, arrayOfString, null, null, null);
        if ((!localCursor.moveToFirst()) || (localCursor.isNull(0))) {
          continue;
        }
        long l = localCursor.getLong(0);
        paramLong = l;
      }
      catch (Exception localException)
      {
        Log.w("DocumentFile", "Failed query: " + localException);
        closeQuietly(localCursor);
        continue;
      }
      finally
      {
        closeQuietly(localCursor);
      }
      return paramLong;
      closeQuietly(localCursor);
    }
  }
  
  private static String queryForString(Context paramContext, Uri paramUri, String paramString1, String paramString2)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    localCursor = null;
    for (;;)
    {
      try
      {
        String[] arrayOfString = new String[1];
        arrayOfString[0] = paramString1;
        localCursor = localContentResolver.query(paramUri, arrayOfString, null, null, null);
        if ((!localCursor.moveToFirst()) || (localCursor.isNull(0))) {
          continue;
        }
        String str = localCursor.getString(0);
        paramString2 = str;
      }
      catch (Exception localException)
      {
        Log.w("DocumentFile", "Failed query: " + localException);
        closeQuietly(localCursor);
        continue;
      }
      finally
      {
        closeQuietly(localCursor);
      }
      return paramString2;
      closeQuietly(localCursor);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/provider/DocumentsContractApi19.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */