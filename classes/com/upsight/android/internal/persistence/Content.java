package com.upsight.android.internal.persistence;

import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;

public final class Content
{
  public static Uri getAuthoritytUri(Context paramContext)
  {
    return Uri.parse("content://" + paramContext.getPackageName() + ".upsight");
  }
  
  static Uri getContentTypeUri(Context paramContext, String paramString)
  {
    return Uri.withAppendedPath(getContentUri(paramContext), paramString);
  }
  
  static Uri getContentUri(Context paramContext)
  {
    return Uri.withAppendedPath(getAuthoritytUri(paramContext), "model");
  }
  
  static final class Models
    implements Content.ModelColumns
  {
    public static final String CONTENT_DIRECTORY = "model";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.upsight.model";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.upsight.model";
  }
  
  static abstract interface ModelColumns
    extends BaseColumns
  {
    public static final String DATA = "data";
    public static final String TYPE = "type";
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/Content.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */