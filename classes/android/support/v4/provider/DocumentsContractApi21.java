package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;

class DocumentsContractApi21
{
  private static final String TAG = "DocumentFile";
  
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
  
  public static Uri createDirectory(Context paramContext, Uri paramUri, String paramString)
  {
    return createFile(paramContext, paramUri, "vnd.android.document/directory", paramString);
  }
  
  public static Uri createFile(Context paramContext, Uri paramUri, String paramString1, String paramString2)
  {
    return DocumentsContract.createDocument(paramContext.getContentResolver(), paramUri, paramString1, paramString2);
  }
  
  /* Error */
  public static Uri[] listFiles(Context paramContext, Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 37	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   4: astore_2
    //   5: aload_1
    //   6: aload_1
    //   7: invokestatic 49	android/provider/DocumentsContract:getDocumentId	(Landroid/net/Uri;)Ljava/lang/String;
    //   10: invokestatic 53	android/provider/DocumentsContract:buildChildDocumentsUriUsingTree	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   13: astore_3
    //   14: new 55	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 56	java/util/ArrayList:<init>	()V
    //   21: astore 4
    //   23: aconst_null
    //   24: astore 5
    //   26: iconst_1
    //   27: anewarray 58	java/lang/String
    //   30: astore 9
    //   32: aload 9
    //   34: iconst_0
    //   35: ldc 60
    //   37: aastore
    //   38: aload_2
    //   39: aload_3
    //   40: aload 9
    //   42: aconst_null
    //   43: aconst_null
    //   44: aconst_null
    //   45: invokevirtual 66	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   48: astore 5
    //   50: aload 5
    //   52: invokeinterface 72 1 0
    //   57: ifeq +74 -> 131
    //   60: aload 4
    //   62: aload_1
    //   63: aload 5
    //   65: iconst_0
    //   66: invokeinterface 76 2 0
    //   71: invokestatic 79	android/provider/DocumentsContract:buildDocumentUriUsingTree	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   74: invokevirtual 83	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   77: pop
    //   78: goto -28 -> 50
    //   81: astore 7
    //   83: ldc 8
    //   85: new 85	java/lang/StringBuilder
    //   88: dup
    //   89: invokespecial 86	java/lang/StringBuilder:<init>	()V
    //   92: ldc 88
    //   94: invokevirtual 92	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload 7
    //   99: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   102: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   105: invokestatic 105	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   108: pop
    //   109: aload 5
    //   111: invokestatic 107	android/support/v4/provider/DocumentsContractApi21:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   114: aload 4
    //   116: aload 4
    //   118: invokevirtual 111	java/util/ArrayList:size	()I
    //   121: anewarray 113	android/net/Uri
    //   124: invokevirtual 117	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   127: checkcast 119	[Landroid/net/Uri;
    //   130: areturn
    //   131: aload 5
    //   133: invokestatic 107	android/support/v4/provider/DocumentsContractApi21:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   136: goto -22 -> 114
    //   139: astore 6
    //   141: aload 5
    //   143: invokestatic 107	android/support/v4/provider/DocumentsContractApi21:closeQuietly	(Ljava/lang/AutoCloseable;)V
    //   146: aload 6
    //   148: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	149	0	paramContext	Context
    //   0	149	1	paramUri	Uri
    //   4	35	2	localContentResolver	android.content.ContentResolver
    //   13	27	3	localUri	Uri
    //   21	96	4	localArrayList	java.util.ArrayList
    //   24	118	5	localCursor	android.database.Cursor
    //   139	8	6	localObject	Object
    //   81	17	7	localException	Exception
    //   30	11	9	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   26	78	81	java/lang/Exception
    //   26	78	139	finally
    //   83	109	139	finally
  }
  
  public static Uri prepareTreeUri(Uri paramUri)
  {
    return DocumentsContract.buildDocumentUriUsingTree(paramUri, DocumentsContract.getTreeDocumentId(paramUri));
  }
  
  public static Uri renameTo(Context paramContext, Uri paramUri, String paramString)
  {
    return DocumentsContract.renameDocument(paramContext.getContentResolver(), paramUri, paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/provider/DocumentsContractApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */