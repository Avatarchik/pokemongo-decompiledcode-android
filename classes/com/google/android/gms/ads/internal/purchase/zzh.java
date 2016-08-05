package com.google.android.gms.ads.internal.purchase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzgr;
import java.util.Locale;

@zzgr
public class zzh
{
  private static final String zzCW;
  private static zzh zzCY;
  private static final Object zzpd = new Object();
  private final zza zzCX;
  
  static
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = "InAppPurchase";
    arrayOfObject[1] = "purchase_id";
    arrayOfObject[2] = "product_id";
    arrayOfObject[3] = "developer_payload";
    arrayOfObject[4] = "record_time";
    zzCW = String.format(localLocale, "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER)", arrayOfObject);
  }
  
  zzh(Context paramContext)
  {
    this.zzCX = new zza(paramContext, "google_inapp_purchase.db");
  }
  
  public static zzh zzw(Context paramContext)
  {
    synchronized (zzpd)
    {
      if (zzCY == null) {
        zzCY = new zzh(paramContext);
      }
      zzh localzzh = zzCY;
      return localzzh;
    }
  }
  
  public int getRecordCount()
  {
    localCursor = null;
    int i = 0;
    for (;;)
    {
      SQLiteDatabase localSQLiteDatabase;
      synchronized (zzpd)
      {
        localSQLiteDatabase = getWritableDatabase();
        if (localSQLiteDatabase == null) {
          return i;
        }
      }
      try
      {
        localCursor = localSQLiteDatabase.rawQuery("select count(*) from InAppPurchase", null);
        if (localCursor.moveToFirst())
        {
          int j = localCursor.getInt(0);
          i = j;
          if (localCursor != null) {
            localCursor.close();
          }
          continue;
          localObject2 = finally;
          throw ((Throwable)localObject2);
        }
      }
      catch (SQLiteException localSQLiteException)
      {
        for (;;)
        {
          zzb.zzaH("Error getting record count" + localSQLiteException.getMessage());
          if (localCursor != null) {
            localCursor.close();
          }
        }
      }
      finally
      {
        if (localCursor == null) {
          break;
        }
        localCursor.close();
      }
    }
  }
  
  public SQLiteDatabase getWritableDatabase()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase2 = this.zzCX.getWritableDatabase();
      localSQLiteDatabase1 = localSQLiteDatabase2;
    }
    catch (SQLiteException localSQLiteException)
    {
      for (;;)
      {
        zzb.zzaH("Error opening writable conversion tracking database");
        SQLiteDatabase localSQLiteDatabase1 = null;
      }
    }
    return localSQLiteDatabase1;
  }
  
  public zzf zza(Cursor paramCursor)
  {
    if (paramCursor == null) {}
    for (zzf localzzf = null;; localzzf = new zzf(paramCursor.getLong(0), paramCursor.getString(1), paramCursor.getString(2))) {
      return localzzf;
    }
  }
  
  public void zza(zzf paramzzf)
  {
    if (paramzzf == null) {}
    for (;;)
    {
      return;
      SQLiteDatabase localSQLiteDatabase;
      Locale localLocale;
      Object[] arrayOfObject;
      synchronized (zzpd)
      {
        localSQLiteDatabase = getWritableDatabase();
        if (localSQLiteDatabase != null) {}
      }
    }
  }
  
  public void zzb(zzf paramzzf)
  {
    if (paramzzf == null) {}
    for (;;)
    {
      return;
      SQLiteDatabase localSQLiteDatabase;
      ContentValues localContentValues;
      synchronized (zzpd)
      {
        localSQLiteDatabase = getWritableDatabase();
        if (localSQLiteDatabase != null) {}
      }
    }
  }
  
  /* Error */
  public void zzfo()
  {
    // Byte code:
    //   0: getstatic 49	com/google/android/gms/ads/internal/purchase/zzh:zzpd	Ljava/lang/Object;
    //   3: astore_1
    //   4: aload_1
    //   5: monitorenter
    //   6: aload_0
    //   7: invokevirtual 73	com/google/android/gms/ads/internal/purchase/zzh:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   10: astore_3
    //   11: aload_3
    //   12: ifnonnull +6 -> 18
    //   15: aload_1
    //   16: monitorexit
    //   17: return
    //   18: aload_3
    //   19: ldc 26
    //   21: aconst_null
    //   22: aconst_null
    //   23: aconst_null
    //   24: aconst_null
    //   25: aconst_null
    //   26: ldc -71
    //   28: ldc -69
    //   30: invokevirtual 191	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   33: astore 7
    //   35: aload 7
    //   37: astore 5
    //   39: aload 5
    //   41: ifnull +23 -> 64
    //   44: aload 5
    //   46: invokeinterface 87 1 0
    //   51: ifeq +13 -> 64
    //   54: aload_0
    //   55: aload_0
    //   56: aload 5
    //   58: invokevirtual 193	com/google/android/gms/ads/internal/purchase/zzh:zza	(Landroid/database/Cursor;)Lcom/google/android/gms/ads/internal/purchase/zzf;
    //   61: invokevirtual 195	com/google/android/gms/ads/internal/purchase/zzh:zza	(Lcom/google/android/gms/ads/internal/purchase/zzf;)V
    //   64: aload 5
    //   66: ifnull +10 -> 76
    //   69: aload 5
    //   71: invokeinterface 94 1 0
    //   76: aload_1
    //   77: monitorexit
    //   78: goto -61 -> 17
    //   81: astore_2
    //   82: aload_1
    //   83: monitorexit
    //   84: aload_2
    //   85: athrow
    //   86: astore 6
    //   88: aconst_null
    //   89: astore 5
    //   91: new 96	java/lang/StringBuilder
    //   94: dup
    //   95: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   98: ldc -59
    //   100: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: aload 6
    //   105: invokevirtual 106	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   108: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: invokestatic 115	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   117: aload 5
    //   119: ifnull -43 -> 76
    //   122: aload 5
    //   124: invokeinterface 94 1 0
    //   129: goto -53 -> 76
    //   132: aload 5
    //   134: ifnull +10 -> 144
    //   137: aload 5
    //   139: invokeinterface 94 1 0
    //   144: aload 4
    //   146: athrow
    //   147: astore 4
    //   149: goto -17 -> 132
    //   152: astore 6
    //   154: goto -63 -> 91
    //   157: astore 4
    //   159: aconst_null
    //   160: astore 5
    //   162: goto -30 -> 132
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	165	0	this	zzh
    //   3	80	1	localObject1	Object
    //   81	4	2	localObject2	Object
    //   10	9	3	localSQLiteDatabase	SQLiteDatabase
    //   144	1	4	localObject3	Object
    //   147	1	4	localObject4	Object
    //   157	1	4	localObject5	Object
    //   37	124	5	localCursor1	Cursor
    //   86	18	6	localSQLiteException1	SQLiteException
    //   152	1	6	localSQLiteException2	SQLiteException
    //   33	3	7	localCursor2	Cursor
    // Exception table:
    //   from	to	target	type
    //   6	17	81	finally
    //   69	84	81	finally
    //   122	147	81	finally
    //   18	35	86	android/database/sqlite/SQLiteException
    //   44	64	147	finally
    //   91	117	147	finally
    //   44	64	152	android/database/sqlite/SQLiteException
    //   18	35	157	finally
  }
  
  /* Error */
  public java.util.List<zzf> zzg(long paramLong)
  {
    // Byte code:
    //   0: getstatic 49	com/google/android/gms/ads/internal/purchase/zzh:zzpd	Ljava/lang/Object;
    //   3: astore_3
    //   4: aload_3
    //   5: monitorenter
    //   6: new 201	java/util/LinkedList
    //   9: dup
    //   10: invokespecial 202	java/util/LinkedList:<init>	()V
    //   13: astore 4
    //   15: lload_1
    //   16: lconst_0
    //   17: lcmp
    //   18: ifgt +12 -> 30
    //   21: aload_3
    //   22: monitorexit
    //   23: aload 4
    //   25: astore 10
    //   27: goto +189 -> 216
    //   30: aload_0
    //   31: invokevirtual 73	com/google/android/gms/ads/internal/purchase/zzh:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   34: astore 6
    //   36: aload 6
    //   38: ifnonnull +12 -> 50
    //   41: aload_3
    //   42: monitorexit
    //   43: aload 4
    //   45: astore 10
    //   47: goto +169 -> 216
    //   50: aload 6
    //   52: ldc 26
    //   54: aconst_null
    //   55: aconst_null
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: ldc -71
    //   61: lload_1
    //   62: invokestatic 205	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   65: invokevirtual 191	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   68: astore 11
    //   70: aload 11
    //   72: astore 8
    //   74: aload 8
    //   76: invokeinterface 87 1 0
    //   81: ifeq +31 -> 112
    //   84: aload 4
    //   86: aload_0
    //   87: aload 8
    //   89: invokevirtual 193	com/google/android/gms/ads/internal/purchase/zzh:zza	(Landroid/database/Cursor;)Lcom/google/android/gms/ads/internal/purchase/zzf;
    //   92: invokeinterface 211 2 0
    //   97: pop
    //   98: aload 8
    //   100: invokeinterface 214 1 0
    //   105: istore 13
    //   107: iload 13
    //   109: ifne -25 -> 84
    //   112: aload 8
    //   114: ifnull +10 -> 124
    //   117: aload 8
    //   119: invokeinterface 94 1 0
    //   124: aload_3
    //   125: monitorexit
    //   126: aload 4
    //   128: astore 10
    //   130: goto +86 -> 216
    //   133: astore 9
    //   135: aconst_null
    //   136: astore 8
    //   138: new 96	java/lang/StringBuilder
    //   141: dup
    //   142: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   145: ldc -40
    //   147: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: aload 9
    //   152: invokevirtual 106	android/database/sqlite/SQLiteException:getMessage	()Ljava/lang/String;
    //   155: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   161: invokestatic 115	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   164: aload 8
    //   166: ifnull -42 -> 124
    //   169: aload 8
    //   171: invokeinterface 94 1 0
    //   176: goto -52 -> 124
    //   179: astore 5
    //   181: aload_3
    //   182: monitorexit
    //   183: aload 5
    //   185: athrow
    //   186: astore 7
    //   188: aconst_null
    //   189: astore 8
    //   191: aload 8
    //   193: ifnull +10 -> 203
    //   196: aload 8
    //   198: invokeinterface 94 1 0
    //   203: aload 7
    //   205: athrow
    //   206: astore 7
    //   208: goto -17 -> 191
    //   211: astore 9
    //   213: goto -75 -> 138
    //   216: aload 10
    //   218: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	219	0	this	zzh
    //   0	219	1	paramLong	long
    //   3	179	3	localObject1	Object
    //   13	114	4	localLinkedList1	java.util.LinkedList
    //   179	5	5	localObject2	Object
    //   34	17	6	localSQLiteDatabase	SQLiteDatabase
    //   186	18	7	localObject3	Object
    //   206	1	7	localObject4	Object
    //   72	125	8	localCursor1	Cursor
    //   133	18	9	localSQLiteException1	SQLiteException
    //   211	1	9	localSQLiteException2	SQLiteException
    //   25	192	10	localLinkedList2	java.util.LinkedList
    //   68	3	11	localCursor2	Cursor
    //   105	3	13	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   50	70	133	android/database/sqlite/SQLiteException
    //   6	43	179	finally
    //   117	126	179	finally
    //   169	183	179	finally
    //   196	206	179	finally
    //   50	70	186	finally
    //   74	107	206	finally
    //   138	164	206	finally
    //   74	107	211	android/database/sqlite/SQLiteException
  }
  
  public class zza
    extends SQLiteOpenHelper
  {
    public zza(Context paramContext, String paramString)
    {
      super(paramString, null, 4);
    }
    
    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
      paramSQLiteDatabase.execSQL(zzh.zzfp());
    }
    
    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
      zzb.zzaG("Database updated from version " + paramInt1 + " to version " + paramInt2);
      paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
      onCreate(paramSQLiteDatabase);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/purchase/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */