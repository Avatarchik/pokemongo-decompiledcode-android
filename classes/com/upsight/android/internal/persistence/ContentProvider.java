package com.upsight.android.internal.persistence;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

public final class ContentProvider
  extends android.content.ContentProvider
{
  private static final int MODEL = 2;
  private static final int MODEL_ALL = 3;
  private static final int MODEL_ITEM = 1;
  private static final String TYPE_SELECTION = "type = ?";
  private static UriMatcher sMatcher;
  private DataHelper mDataHelper;
  
  private static String updatedSelection(String paramString1, String paramString2)
  {
    String str = "_id = '" + paramString2 + "'";
    if (paramString1 != null) {
      str = str + " AND " + paramString1;
    }
    return str;
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    switch (sMatcher.match(paramUri))
    {
    default: 
      throw new IllegalArgumentException("Uri not supported by insert:" + paramUri);
    }
    String str = updatedSelection(paramString, paramUri.getLastPathSegment());
    int i = this.mDataHelper.delete(str, paramArrayOfString);
    if (i > 0) {
      getContext().getContentResolver().notifyChange(paramUri, null);
    }
    return i;
  }
  
  public String getType(Uri paramUri)
  {
    String str1 = null;
    switch (sMatcher.match(paramUri))
    {
    }
    for (;;)
    {
      return str1;
      str1 = "vnd.android.cursor.item/vnd.com.upsight.model";
      List localList = paramUri.getPathSegments();
      if (localList.size() < 3) {
        throw new IllegalArgumentException("Unexpected content uri: " + paramUri);
      }
      if (!TextUtils.isEmpty((String)localList.get(1)))
      {
        str1 = str1 + "." + (String)localList.get(1);
        continue;
        str1 = "vnd.android.cursor.dir/vnd.com.upsight.model";
        String str2 = paramUri.getLastPathSegment();
        if (!"model".equals(str2)) {
          str1 = str1 + "." + str2;
        }
      }
    }
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    switch (sMatcher.match(paramUri))
    {
    default: 
      throw new IllegalArgumentException("Uri not supported by insert:" + paramUri);
    }
    if (TextUtils.isEmpty(paramContentValues.getAsString("type"))) {
      throw new IllegalArgumentException("ContentValues must have a model type");
    }
    if (0L > this.mDataHelper.insert(paramContentValues)) {
      throw new IllegalArgumentException("Failed to insert model for uri: " + paramUri);
    }
    Uri localUri = Uri.withAppendedPath(paramUri, paramContentValues.getAsString("_id"));
    getContext().getContentResolver().notifyChange(localUri, null);
    return localUri;
  }
  
  public boolean onCreate()
  {
    return onCreate(getContext());
  }
  
  public boolean onCreate(Context paramContext)
  {
    return onCreate(paramContext, new SQLiteDataHelper(paramContext));
  }
  
  public boolean onCreate(Context paramContext, DataHelper paramDataHelper)
  {
    this.mDataHelper = paramDataHelper;
    if (sMatcher == null)
    {
      sMatcher = new UriMatcher(-1);
      String str = Content.getAuthoritytUri(paramContext).getAuthority();
      sMatcher.addURI(str, "model/*/*", 1);
      sMatcher.addURI(str, "model/*", 2);
      sMatcher.addURI(str, "model", 3);
    }
    return true;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    Cursor localCursor;
    switch (sMatcher.match(paramUri))
    {
    default: 
      throw new IllegalArgumentException("Uri not supported by query: " + paramUri);
    case 1: 
      String str = updatedSelection(paramString1, paramUri.getLastPathSegment());
      localCursor = this.mDataHelper.query(paramArrayOfString1, str, paramArrayOfString2, paramString2);
    }
    for (;;)
    {
      return localCursor;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = paramUri.getLastPathSegment();
      localCursor = this.mDataHelper.query(paramArrayOfString1, "type = ?", arrayOfString, paramString2);
      continue;
      localCursor = this.mDataHelper.query(paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
    }
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    switch (sMatcher.match(paramUri))
    {
    default: 
      throw new IllegalArgumentException("Uri not supported by update:" + paramUri);
    }
    String str = updatedSelection(paramString, paramUri.getLastPathSegment());
    int i = this.mDataHelper.update(paramContentValues, str, paramArrayOfString);
    if (i > 0) {
      getContext().getContentResolver().notifyChange(paramUri, null);
    }
    return i;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/ContentProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */