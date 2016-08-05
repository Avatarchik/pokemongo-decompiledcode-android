package com.voxelbusters.nativeplugins.extensions;

import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MatrixCursor.RowBuilder;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import java.util.Arrays;

public class FileProviderExtended
  extends FileProvider
{
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    Cursor localCursor = super.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
    String[] arrayOfString1 = localCursor.getColumnNames();
    String[] arrayOfString2 = null;
    int i = arrayOfString1.length;
    int j = 0;
    MatrixCursor localMatrixCursor;
    if (j >= i)
    {
      if (arrayOfString2 == null)
      {
        arrayOfString2 = (String[])Arrays.copyOf(arrayOfString1, 1 + arrayOfString1.length);
        arrayOfString2[arrayOfString1.length] = "_data";
      }
      localMatrixCursor = new MatrixCursor(arrayOfString2, localCursor.getCount());
      localCursor.moveToPosition(-1);
    }
    for (;;)
    {
      if (!localCursor.moveToNext())
      {
        return localMatrixCursor;
        if ("_data".equals(arrayOfString1[j])) {
          arrayOfString2 = arrayOfString1;
        }
        j++;
        break;
      }
      MatrixCursor.RowBuilder localRowBuilder = localMatrixCursor.newRow();
      for (int k = 0; k < arrayOfString1.length; k++) {
        localRowBuilder.add(localCursor.getString(k));
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/extensions/FileProviderExtended.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */