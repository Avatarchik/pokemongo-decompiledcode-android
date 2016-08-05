package com.voxelbusters.nativeplugins.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtility
{
  public static final int IMAGE_QUALITY = 100;
  
  public static void createDirectoriesIfUnAvailable(String paramString)
  {
    File localFile = new File(paramString);
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
  }
  
  public static String createFileFromStream(InputStream paramInputStream, File paramFile, String paramString)
  {
    File localFile = new File(paramFile, paramString);
    createPathIfUnAvailable(paramFile, localFile);
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte);
        if (i <= 0)
        {
          localFileOutputStream.close();
          paramInputStream.close();
          return localFile.getAbsolutePath();
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  static void createPathIfUnAvailable(File paramFile1, File paramFile2)
  {
    if (!paramFile2.exists()) {}
    try
    {
      paramFile1.mkdirs();
      paramFile2.createNewFile();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Debug.error("NativePlugins.FileUtils", "Creating file failed!");
        localIOException.printStackTrace();
      }
    }
  }
  
  public static Uri createSharingFileUri(Context paramContext, byte[] paramArrayOfByte, int paramInt, String paramString1, String paramString2)
  {
    boolean bool = ApplicationUtility.hasExternalStorageWritable(paramContext);
    String str = getSavedFile(paramArrayOfByte, paramInt, ApplicationUtility.getExternalTempDirectoryIfExists(paramContext, paramString1), paramString2, false);
    Debug.log("NativePlugins.Sharing", "Saving temp at " + str);
    Uri localUri = null;
    if (!StringUtility.isNullOrEmpty(str)) {
      if (bool) {
        break label92;
      }
    }
    label92:
    for (localUri = FileProvider.getUriForFile(paramContext, ApplicationUtility.getFileProviderAuthoityName(paramContext), new File(str));; localUri = Uri.fromFile(new File(str)))
    {
      paramContext.grantUriPermission(ApplicationUtility.getPackageName(paramContext), localUri, 3);
      return localUri;
    }
  }
  
  public static ByteArrayOutputStream getBitmapStream(String paramString)
  {
    Bitmap localBitmap = BitmapFactory.decodeFile(new File(paramString).getAbsolutePath());
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream;
  }
  
  /* Error */
  public static String getContentsOfFile(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore_2
    //   4: new 170	java/io/BufferedReader
    //   7: dup
    //   8: new 172	java/io/FileReader
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 173	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   16: invokespecial 176	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   19: astore_3
    //   20: aload_3
    //   21: ifnull +80 -> 101
    //   24: aload_3
    //   25: invokevirtual 179	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore 6
    //   30: aload 6
    //   32: ifnonnull +15 -> 47
    //   35: aload_3
    //   36: astore_2
    //   37: aload_2
    //   38: ifnull +7 -> 45
    //   41: aload_2
    //   42: invokevirtual 180	java/io/BufferedReader:close	()V
    //   45: aload_1
    //   46: areturn
    //   47: new 99	java/lang/StringBuilder
    //   50: dup
    //   51: aload_1
    //   52: invokestatic 186	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   55: invokespecial 102	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   58: aload 6
    //   60: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: astore 7
    //   68: aload 7
    //   70: astore_1
    //   71: goto -47 -> 24
    //   74: astore 5
    //   76: aload 5
    //   78: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   81: goto -44 -> 37
    //   84: astore 4
    //   86: aload 4
    //   88: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   91: goto -46 -> 45
    //   94: astore 5
    //   96: aload_3
    //   97: astore_2
    //   98: goto -22 -> 76
    //   101: aload_3
    //   102: astore_2
    //   103: goto -66 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	paramString	String
    //   1	70	1	localObject1	Object
    //   3	100	2	localObject2	Object
    //   19	83	3	localBufferedReader	java.io.BufferedReader
    //   84	3	4	localIOException1	IOException
    //   74	3	5	localIOException2	IOException
    //   94	1	5	localIOException3	IOException
    //   28	31	6	str1	String
    //   66	3	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   4	20	74	java/io/IOException
    //   41	45	84	java/io/IOException
    //   24	30	94	java/io/IOException
    //   47	68	94	java/io/IOException
  }
  
  public static String getFilePathromURI(Context paramContext, Uri paramUri)
  {
    return new File(paramUri.toString()).getAbsolutePath();
  }
  
  public static String getSavedFile(byte[] paramArrayOfByte, int paramInt, File paramFile, String paramString, boolean paramBoolean)
  {
    return getSavedFile(paramArrayOfByte, paramInt, paramFile, paramString, paramBoolean, true);
  }
  
  public static String getSavedFile(byte[] paramArrayOfByte, int paramInt, File paramFile, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject = null;
    File localFile;
    if ((paramArrayOfByte != null) && (paramInt > 0))
    {
      createDirectoriesIfUnAvailable(paramFile.getAbsolutePath());
      localFile = new File(paramFile, paramString);
      if (localFile.exists()) {
        localFile.delete();
      }
    }
    try
    {
      localFile.createNewFile();
      if (paramBoolean2)
      {
        localFile.setReadable(true, false);
        localFile.setWritable(true, false);
      }
    }
    catch (IOException localIOException3)
    {
      for (;;)
      {
        try
        {
          localFileOutputStream = new FileOutputStream(localFile);
        }
        catch (FileNotFoundException localFileNotFoundException1)
        {
          FileOutputStream localFileOutputStream;
          String str;
          localFileNotFoundException1.printStackTrace();
          continue;
        }
        catch (IOException localIOException1)
        {
          localIOException1.printStackTrace();
          continue;
        }
        try
        {
          localFileOutputStream.write(paramArrayOfByte);
          localFileOutputStream.close();
          str = localFile.getAbsolutePath();
          localObject = str;
          if ((localObject != null) && (paramBoolean1)) {
            localObject = "file://" + (String)localObject;
          }
          return (String)localObject;
        }
        catch (IOException localIOException2)
        {
          continue;
        }
        catch (FileNotFoundException localFileNotFoundException2)
        {
          continue;
        }
        localIOException3 = localIOException3;
        localIOException3.printStackTrace();
      }
    }
  }
  
  /* Error */
  public static String getSavedFileFromUri(Context paramContext, Uri paramUri, File paramFile, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: invokevirtual 219	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   10: astore 6
    //   12: aload 6
    //   14: aload_1
    //   15: invokevirtual 225	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   18: astore 11
    //   20: new 153	java/io/ByteArrayOutputStream
    //   23: dup
    //   24: invokespecial 154	java/io/ByteArrayOutputStream:<init>	()V
    //   27: astore 12
    //   29: sipush 1024
    //   32: newarray <illegal type>
    //   34: astore 13
    //   36: aload 11
    //   38: aload 13
    //   40: invokevirtual 46	java/io/InputStream:read	([B)I
    //   43: bipush -1
    //   45: if_icmpne +54 -> 99
    //   48: aload 12
    //   50: invokevirtual 228	java/io/ByteArrayOutputStream:flush	()V
    //   53: aload 12
    //   55: invokevirtual 232	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   58: astore 14
    //   60: aload 14
    //   62: astore 5
    //   64: aload 12
    //   66: astore 4
    //   68: aload 4
    //   70: ifnull +8 -> 78
    //   73: aload 4
    //   75: invokevirtual 233	java/io/ByteArrayOutputStream:close	()V
    //   78: aload 5
    //   80: ifnull +63 -> 143
    //   83: aload 5
    //   85: aload 5
    //   87: arraylength
    //   88: aload_2
    //   89: aload_3
    //   90: iconst_1
    //   91: invokestatic 95	com/voxelbusters/nativeplugins/utilities/FileUtility:getSavedFile	([BILjava/io/File;Ljava/lang/String;Z)Ljava/lang/String;
    //   94: astore 8
    //   96: aload 8
    //   98: areturn
    //   99: aload 12
    //   101: aload 13
    //   103: invokevirtual 234	java/io/ByteArrayOutputStream:write	([B)V
    //   106: goto -70 -> 36
    //   109: astore 7
    //   111: aload 12
    //   113: astore 4
    //   115: aload 7
    //   117: invokevirtual 213	java/io/FileNotFoundException:printStackTrace	()V
    //   120: goto -52 -> 68
    //   123: astore 10
    //   125: aload 10
    //   127: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   130: goto -62 -> 68
    //   133: astore 9
    //   135: aload 9
    //   137: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   140: goto -62 -> 78
    //   143: aconst_null
    //   144: astore 8
    //   146: goto -50 -> 96
    //   149: astore 10
    //   151: aload 12
    //   153: astore 4
    //   155: goto -30 -> 125
    //   158: astore 7
    //   160: goto -45 -> 115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	163	0	paramContext	Context
    //   0	163	1	paramUri	Uri
    //   0	163	2	paramFile	File
    //   0	163	3	paramString	String
    //   1	153	4	localObject1	Object
    //   4	82	5	localObject2	Object
    //   10	3	6	localContentResolver	android.content.ContentResolver
    //   109	7	7	localFileNotFoundException1	FileNotFoundException
    //   158	1	7	localFileNotFoundException2	FileNotFoundException
    //   94	51	8	str	String
    //   133	3	9	localIOException1	IOException
    //   123	3	10	localIOException2	IOException
    //   149	1	10	localIOException3	IOException
    //   18	19	11	localInputStream	InputStream
    //   27	125	12	localByteArrayOutputStream	ByteArrayOutputStream
    //   34	68	13	arrayOfByte1	byte[]
    //   58	3	14	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   29	60	109	java/io/FileNotFoundException
    //   99	106	109	java/io/FileNotFoundException
    //   12	29	123	java/io/IOException
    //   73	78	133	java/io/IOException
    //   29	60	149	java/io/IOException
    //   99	106	149	java/io/IOException
    //   12	29	158	java/io/FileNotFoundException
  }
  
  public static Uri getSavedFileUri(byte[] paramArrayOfByte, int paramInt, File paramFile, String paramString, boolean paramBoolean)
  {
    return Uri.fromFile(new File(getSavedFile(paramArrayOfByte, paramInt, paramFile, paramString, paramBoolean, true)));
  }
  
  public static String getSavedLocalFileFromUri(Context paramContext, Uri paramUri, String paramString1, String paramString2)
  {
    return getSavedFileFromUri(paramContext, paramUri, paramContext.getDir(paramString1, 0), paramString2);
  }
  
  public static String getScaledImagePath(String paramString1, File paramFile, String paramString2, float paramFloat, boolean paramBoolean)
  {
    File localFile = new File(paramString1);
    String str = getScaledImagePathFromBitmap(BitmapFactory.decodeFile(localFile.getAbsolutePath()), paramFile, paramString2, paramFloat);
    if (paramBoolean) {
      localFile.delete();
    }
    return str;
  }
  
  /* Error */
  public static String getScaledImagePathFromBitmap(Bitmap paramBitmap, File paramFile, String paramString, float paramFloat)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 15	java/io/File
    //   6: dup
    //   7: aload_1
    //   8: aload_2
    //   9: invokespecial 31	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   12: astore 5
    //   14: aload_1
    //   15: aload 5
    //   17: invokestatic 35	com/voxelbusters/nativeplugins/utilities/FileUtility:createPathIfUnAvailable	(Ljava/io/File;Ljava/io/File;)V
    //   20: aconst_null
    //   21: astore 6
    //   23: new 37	java/io/FileOutputStream
    //   26: dup
    //   27: aload 5
    //   29: invokespecial 40	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   32: astore 7
    //   34: iconst_0
    //   35: istore 8
    //   37: iconst_0
    //   38: istore 9
    //   40: aload_0
    //   41: ifnull +23 -> 64
    //   44: fload_3
    //   45: aload_0
    //   46: invokevirtual 254	android/graphics/Bitmap:getWidth	()I
    //   49: i2f
    //   50: fmul
    //   51: f2i
    //   52: istore 8
    //   54: fload_3
    //   55: aload_0
    //   56: invokevirtual 257	android/graphics/Bitmap:getHeight	()I
    //   59: i2f
    //   60: fmul
    //   61: f2i
    //   62: istore 9
    //   64: iload 8
    //   66: ifeq +57 -> 123
    //   69: iload 9
    //   71: ifeq +52 -> 123
    //   74: aload_0
    //   75: iload 8
    //   77: iload 9
    //   79: iconst_1
    //   80: invokestatic 261	android/graphics/Bitmap:createScaledBitmap	(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
    //   83: getstatic 264	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   86: bipush 100
    //   88: aload 7
    //   90: invokevirtual 166	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   93: pop
    //   94: aload 5
    //   96: invokevirtual 56	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   99: astore 16
    //   101: aload 16
    //   103: astore 4
    //   105: aload 7
    //   107: ifnull +129 -> 236
    //   110: aload 7
    //   112: invokevirtual 265	java/io/OutputStream:flush	()V
    //   115: aload 7
    //   117: invokevirtual 51	java/io/OutputStream:close	()V
    //   120: aload 4
    //   122: areturn
    //   123: ldc 70
    //   125: ldc_w 267
    //   128: invokestatic 78	com/voxelbusters/nativeplugins/utilities/Debug:error	(Ljava/lang/String;Ljava/lang/String;)V
    //   131: goto -26 -> 105
    //   134: astore 12
    //   136: aload 7
    //   138: astore 6
    //   140: ldc 70
    //   142: new 99	java/lang/StringBuilder
    //   145: dup
    //   146: ldc_w 269
    //   149: invokespecial 102	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   152: aload 12
    //   154: invokevirtual 272	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   157: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokestatic 78	com/voxelbusters/nativeplugins/utilities/Debug:error	(Ljava/lang/String;Ljava/lang/String;)V
    //   166: aload 12
    //   168: invokevirtual 213	java/io/FileNotFoundException:printStackTrace	()V
    //   171: aload 6
    //   173: ifnull -53 -> 120
    //   176: aload 6
    //   178: invokevirtual 265	java/io/OutputStream:flush	()V
    //   181: aload 6
    //   183: invokevirtual 51	java/io/OutputStream:close	()V
    //   186: goto -66 -> 120
    //   189: astore 13
    //   191: aload 13
    //   193: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   196: goto -76 -> 120
    //   199: astore 10
    //   201: aload 6
    //   203: ifnull +13 -> 216
    //   206: aload 6
    //   208: invokevirtual 265	java/io/OutputStream:flush	()V
    //   211: aload 6
    //   213: invokevirtual 51	java/io/OutputStream:close	()V
    //   216: aload 10
    //   218: athrow
    //   219: astore 11
    //   221: aload 11
    //   223: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   226: goto -10 -> 216
    //   229: astore 14
    //   231: aload 14
    //   233: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   236: goto -116 -> 120
    //   239: astore 10
    //   241: aload 7
    //   243: astore 6
    //   245: goto -44 -> 201
    //   248: astore 12
    //   250: goto -110 -> 140
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	253	0	paramBitmap	Bitmap
    //   0	253	1	paramFile	File
    //   0	253	2	paramString	String
    //   0	253	3	paramFloat	float
    //   1	120	4	localObject1	Object
    //   12	83	5	localFile	File
    //   21	223	6	localObject2	Object
    //   32	210	7	localFileOutputStream	FileOutputStream
    //   35	41	8	i	int
    //   38	40	9	j	int
    //   199	18	10	localObject3	Object
    //   239	1	10	localObject4	Object
    //   219	3	11	localIOException1	IOException
    //   134	33	12	localFileNotFoundException1	FileNotFoundException
    //   248	1	12	localFileNotFoundException2	FileNotFoundException
    //   189	3	13	localIOException2	IOException
    //   229	3	14	localIOException3	IOException
    //   99	3	16	str	String
    // Exception table:
    //   from	to	target	type
    //   44	101	134	java/io/FileNotFoundException
    //   123	131	134	java/io/FileNotFoundException
    //   176	186	189	java/io/IOException
    //   23	34	199	finally
    //   140	171	199	finally
    //   206	216	219	java/io/IOException
    //   110	120	229	java/io/IOException
    //   44	101	239	finally
    //   123	131	239	finally
    //   23	34	248	java/io/FileNotFoundException
  }
  
  public static void replaceFile(byte[] paramArrayOfByte, File paramFile, String paramString)
  {
    if (paramArrayOfByte != null) {
      getSavedFile(paramArrayOfByte, paramArrayOfByte.length, paramFile, paramString, false);
    }
    for (;;)
    {
      return;
      File localFile = new File(paramFile, paramString);
      if (localFile.exists()) {
        localFile.delete();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/utilities/FileUtility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */