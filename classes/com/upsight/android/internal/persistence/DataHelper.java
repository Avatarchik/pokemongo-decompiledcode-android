package com.upsight.android.internal.persistence;

import android.content.ContentValues;
import android.database.Cursor;

abstract interface DataHelper
{
  public abstract int delete(String paramString, String[] paramArrayOfString);
  
  public abstract long insert(ContentValues paramContentValues);
  
  public abstract Cursor query(String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2);
  
  public abstract int update(ContentValues paramContentValues, String paramString, String[] paramArrayOfString);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/DataHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */