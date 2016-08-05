package org.apache.commons.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.output.NullOutputStream;

public class FileUtils
{
  public static final File[] EMPTY_FILE_ARRAY = new File[0];
  private static final long FILE_COPY_BUFFER_SIZE = 31457280L;
  public static final long ONE_EB = 1152921504606846976L;
  public static final BigInteger ONE_EB_BI;
  public static final long ONE_GB = 1073741824L;
  public static final BigInteger ONE_GB_BI;
  public static final long ONE_KB = 1024L;
  public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024L);
  public static final long ONE_MB = 1048576L;
  public static final BigInteger ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
  public static final long ONE_PB = 1125899906842624L;
  public static final BigInteger ONE_PB_BI;
  public static final long ONE_TB = 1099511627776L;
  public static final BigInteger ONE_TB_BI;
  public static final BigInteger ONE_YB;
  public static final BigInteger ONE_ZB;
  private static final Charset UTF8 = Charset.forName("UTF-8");
  
  static
  {
    ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
    ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
    ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
    ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
    ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(1152921504606846976L));
    ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
  }
  
  public static String byteCountToDisplaySize(long paramLong)
  {
    return byteCountToDisplaySize(BigInteger.valueOf(paramLong));
  }
  
  public static String byteCountToDisplaySize(BigInteger paramBigInteger)
  {
    String str;
    if (paramBigInteger.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0) {
      str = String.valueOf(paramBigInteger.divide(ONE_EB_BI)) + " EB";
    }
    for (;;)
    {
      return str;
      if (paramBigInteger.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0) {
        str = String.valueOf(paramBigInteger.divide(ONE_PB_BI)) + " PB";
      } else if (paramBigInteger.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0) {
        str = String.valueOf(paramBigInteger.divide(ONE_TB_BI)) + " TB";
      } else if (paramBigInteger.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0) {
        str = String.valueOf(paramBigInteger.divide(ONE_GB_BI)) + " GB";
      } else if (paramBigInteger.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0) {
        str = String.valueOf(paramBigInteger.divide(ONE_MB_BI)) + " MB";
      } else if (paramBigInteger.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0) {
        str = String.valueOf(paramBigInteger.divide(ONE_KB_BI)) + " KB";
      } else {
        str = String.valueOf(paramBigInteger) + " bytes";
      }
    }
  }
  
  private static void checkDirectory(File paramFile)
  {
    if (!paramFile.exists()) {
      throw new IllegalArgumentException(paramFile + " does not exist");
    }
    if (!paramFile.isDirectory()) {
      throw new IllegalArgumentException(paramFile + " is not a directory");
    }
  }
  
  public static Checksum checksum(File paramFile, Checksum paramChecksum)
    throws IOException
  {
    if (paramFile.isDirectory()) {
      throw new IllegalArgumentException("Checksums can't be computed on directories");
    }
    localObject1 = null;
    try
    {
      localCheckedInputStream = new CheckedInputStream(new FileInputStream(paramFile), paramChecksum);
      IOUtils.closeQuietly((InputStream)localObject1);
    }
    finally
    {
      try
      {
        IOUtils.copy(localCheckedInputStream, new NullOutputStream());
        IOUtils.closeQuietly(localCheckedInputStream);
        return paramChecksum;
      }
      finally
      {
        CheckedInputStream localCheckedInputStream;
        localObject1 = localCheckedInputStream;
      }
      localObject2 = finally;
    }
    throw ((Throwable)localObject2);
  }
  
  public static long checksumCRC32(File paramFile)
    throws IOException
  {
    CRC32 localCRC32 = new CRC32();
    checksum(paramFile, localCRC32);
    return localCRC32.getValue();
  }
  
  public static void cleanDirectory(File paramFile)
    throws IOException
  {
    if (!paramFile.exists()) {
      throw new IllegalArgumentException(paramFile + " does not exist");
    }
    if (!paramFile.isDirectory()) {
      throw new IllegalArgumentException(paramFile + " is not a directory");
    }
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null) {
      throw new IOException("Failed to list contents of " + paramFile);
    }
    Object localObject = null;
    int i = arrayOfFile.length;
    int j = 0;
    for (;;)
    {
      if (j < i)
      {
        File localFile = arrayOfFile[j];
        try
        {
          forceDelete(localFile);
          j++;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            localObject = localIOException;
          }
        }
      }
    }
    if (localObject != null) {
      throw ((Throwable)localObject);
    }
  }
  
  private static void cleanDirectoryOnExit(File paramFile)
    throws IOException
  {
    if (!paramFile.exists()) {
      throw new IllegalArgumentException(paramFile + " does not exist");
    }
    if (!paramFile.isDirectory()) {
      throw new IllegalArgumentException(paramFile + " is not a directory");
    }
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null) {
      throw new IOException("Failed to list contents of " + paramFile);
    }
    Object localObject = null;
    int i = arrayOfFile.length;
    int j = 0;
    for (;;)
    {
      if (j < i)
      {
        File localFile = arrayOfFile[j];
        try
        {
          forceDeleteOnExit(localFile);
          j++;
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            localObject = localIOException;
          }
        }
      }
    }
    if (localObject != null) {
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public static boolean contentEquals(File paramFile1, File paramFile2)
    throws IOException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: invokevirtual 135	java/io/File:exists	()Z
    //   6: istore_3
    //   7: iload_3
    //   8: aload_1
    //   9: invokevirtual 135	java/io/File:exists	()Z
    //   12: if_icmpeq +5 -> 17
    //   15: iload_2
    //   16: ireturn
    //   17: iload_3
    //   18: ifne +8 -> 26
    //   21: iconst_1
    //   22: istore_2
    //   23: goto -8 -> 15
    //   26: aload_0
    //   27: invokevirtual 148	java/io/File:isDirectory	()Z
    //   30: ifne +10 -> 40
    //   33: aload_1
    //   34: invokevirtual 148	java/io/File:isDirectory	()Z
    //   37: ifeq +13 -> 50
    //   40: new 154	java/io/IOException
    //   43: dup
    //   44: ldc -48
    //   46: invokespecial 197	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   49: athrow
    //   50: aload_0
    //   51: invokevirtual 211	java/io/File:length	()J
    //   54: aload_1
    //   55: invokevirtual 211	java/io/File:length	()J
    //   58: lcmp
    //   59: ifne -44 -> 15
    //   62: aload_0
    //   63: invokevirtual 215	java/io/File:getCanonicalFile	()Ljava/io/File;
    //   66: aload_1
    //   67: invokevirtual 215	java/io/File:getCanonicalFile	()Ljava/io/File;
    //   70: invokevirtual 219	java/io/File:equals	(Ljava/lang/Object;)Z
    //   73: ifeq +8 -> 81
    //   76: iconst_1
    //   77: istore_2
    //   78: goto -63 -> 15
    //   81: aconst_null
    //   82: astore 4
    //   84: aconst_null
    //   85: astore 5
    //   87: new 160	java/io/FileInputStream
    //   90: dup
    //   91: aload_0
    //   92: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   95: astore 6
    //   97: new 160	java/io/FileInputStream
    //   100: dup
    //   101: aload_1
    //   102: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   105: astore 7
    //   107: aload 6
    //   109: aload 7
    //   111: invokestatic 222	org/apache/commons/io/IOUtils:contentEquals	(Ljava/io/InputStream;Ljava/io/InputStream;)Z
    //   114: istore 9
    //   116: iload 9
    //   118: istore_2
    //   119: aload 6
    //   121: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   124: aload 7
    //   126: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   129: goto -114 -> 15
    //   132: astore 8
    //   134: aload 4
    //   136: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   139: aload 5
    //   141: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   144: aload 8
    //   146: athrow
    //   147: astore 8
    //   149: aload 6
    //   151: astore 4
    //   153: goto -19 -> 134
    //   156: astore 8
    //   158: aload 7
    //   160: astore 5
    //   162: aload 6
    //   164: astore 4
    //   166: goto -32 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	paramFile1	File
    //   0	169	1	paramFile2	File
    //   1	118	2	bool1	boolean
    //   6	12	3	bool2	boolean
    //   82	83	4	localObject1	Object
    //   85	76	5	localObject2	Object
    //   95	68	6	localFileInputStream1	FileInputStream
    //   105	54	7	localFileInputStream2	FileInputStream
    //   132	13	8	localObject3	Object
    //   147	1	8	localObject4	Object
    //   156	1	8	localObject5	Object
    //   114	3	9	bool3	boolean
    // Exception table:
    //   from	to	target	type
    //   87	97	132	finally
    //   97	107	147	finally
    //   107	116	156	finally
  }
  
  public static boolean contentEqualsIgnoreEOL(File paramFile1, File paramFile2, String paramString)
    throws IOException
  {
    boolean bool1 = true;
    boolean bool2 = paramFile1.exists();
    if (bool2 != paramFile2.exists()) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
      } while (!bool2);
      if ((paramFile1.isDirectory()) || (paramFile2.isDirectory())) {
        throw new IOException("Can't compare directories, only files");
      }
    } while (paramFile1.getCanonicalFile().equals(paramFile2.getCanonicalFile()));
    localObject1 = null;
    Object localObject2 = null;
    if (paramString == null) {}
    for (;;)
    {
      InputStreamReader localInputStreamReader3;
      boolean bool3;
      try
      {
        localInputStreamReader1 = new InputStreamReader(new FileInputStream(paramFile1));
      }
      finally {}
      try
      {
        localInputStreamReader3 = new InputStreamReader(new FileInputStream(paramFile2));
        localObject2 = localInputStreamReader3;
        localObject1 = localInputStreamReader1;
        bool3 = IOUtils.contentEqualsIgnoreEOL((Reader)localObject1, (Reader)localObject2);
        bool1 = bool3;
        IOUtils.closeQuietly((Reader)localObject1);
        IOUtils.closeQuietly((Reader)localObject2);
        break;
      }
      finally
      {
        for (;;)
        {
          localObject1 = localInputStreamReader1;
        }
      }
      localInputStreamReader1 = new InputStreamReader(new FileInputStream(paramFile1), paramString);
      InputStreamReader localInputStreamReader2 = new InputStreamReader(new FileInputStream(paramFile2), paramString);
      localObject2 = localInputStreamReader2;
      localObject1 = localInputStreamReader1;
    }
    IOUtils.closeQuietly((Reader)localObject1);
    IOUtils.closeQuietly((Reader)localObject2);
    throw ((Throwable)localObject3);
  }
  
  public static File[] convertFileCollectionToFileArray(Collection<File> paramCollection)
  {
    return (File[])paramCollection.toArray(new File[paramCollection.size()]);
  }
  
  public static void copyDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    copyDirectory(paramFile1, paramFile2, true);
  }
  
  public static void copyDirectory(File paramFile1, File paramFile2, FileFilter paramFileFilter)
    throws IOException
  {
    copyDirectory(paramFile1, paramFile2, paramFileFilter, true);
  }
  
  public static void copyDirectory(File paramFile1, File paramFile2, FileFilter paramFileFilter, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null) {
      throw new NullPointerException("Source must not be null");
    }
    if (paramFile2 == null) {
      throw new NullPointerException("Destination must not be null");
    }
    if (!paramFile1.exists()) {
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    }
    if (!paramFile1.isDirectory()) {
      throw new IOException("Source '" + paramFile1 + "' exists but is not a directory");
    }
    if (paramFile1.getCanonicalPath().equals(paramFile2.getCanonicalPath())) {
      throw new IOException("Source '" + paramFile1 + "' and destination '" + paramFile2 + "' are the same");
    }
    ArrayList localArrayList = null;
    if (paramFile2.getCanonicalPath().startsWith(paramFile1.getCanonicalPath()))
    {
      if (paramFileFilter == null) {}
      for (File[] arrayOfFile1 = paramFile1.listFiles(); (arrayOfFile1 != null) && (arrayOfFile1.length > 0); arrayOfFile1 = paramFile1.listFiles(paramFileFilter))
      {
        localArrayList = new ArrayList(arrayOfFile1.length);
        File[] arrayOfFile2 = arrayOfFile1;
        int i = arrayOfFile2.length;
        for (int j = 0; j < i; j++) {
          localArrayList.add(new File(paramFile2, arrayOfFile2[j].getName()).getCanonicalPath());
        }
      }
    }
    doCopyDirectory(paramFile1, paramFile2, paramFileFilter, paramBoolean, localArrayList);
  }
  
  public static void copyDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    copyDirectory(paramFile1, paramFile2, null, paramBoolean);
  }
  
  public static void copyDirectoryToDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    if (paramFile1 == null) {
      throw new NullPointerException("Source must not be null");
    }
    if ((paramFile1.exists()) && (!paramFile1.isDirectory())) {
      throw new IllegalArgumentException("Source '" + paramFile2 + "' is not a directory");
    }
    if (paramFile2 == null) {
      throw new NullPointerException("Destination must not be null");
    }
    if ((paramFile2.exists()) && (!paramFile2.isDirectory())) {
      throw new IllegalArgumentException("Destination '" + paramFile2 + "' is not a directory");
    }
    copyDirectory(paramFile1, new File(paramFile2, paramFile1.getName()), true);
  }
  
  public static long copyFile(File paramFile, OutputStream paramOutputStream)
    throws IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(paramFile);
    try
    {
      long l = IOUtils.copyLarge(localFileInputStream, paramOutputStream);
      return l;
    }
    finally
    {
      localFileInputStream.close();
    }
  }
  
  public static void copyFile(File paramFile1, File paramFile2)
    throws IOException
  {
    copyFile(paramFile1, paramFile2, true);
  }
  
  public static void copyFile(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null) {
      throw new NullPointerException("Source must not be null");
    }
    if (paramFile2 == null) {
      throw new NullPointerException("Destination must not be null");
    }
    if (!paramFile1.exists()) {
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    }
    if (paramFile1.isDirectory()) {
      throw new IOException("Source '" + paramFile1 + "' exists but is a directory");
    }
    if (paramFile1.getCanonicalPath().equals(paramFile2.getCanonicalPath())) {
      throw new IOException("Source '" + paramFile1 + "' and destination '" + paramFile2 + "' are the same");
    }
    File localFile = paramFile2.getParentFile();
    if ((localFile != null) && (!localFile.mkdirs()) && (!localFile.isDirectory())) {
      throw new IOException("Destination '" + localFile + "' directory cannot be created");
    }
    if ((paramFile2.exists()) && (!paramFile2.canWrite())) {
      throw new IOException("Destination '" + paramFile2 + "' exists but is read-only");
    }
    doCopyFile(paramFile1, paramFile2, paramBoolean);
  }
  
  public static void copyFileToDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    copyFileToDirectory(paramFile1, paramFile2, true);
  }
  
  public static void copyFileToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile2 == null) {
      throw new NullPointerException("Destination must not be null");
    }
    if ((paramFile2.exists()) && (!paramFile2.isDirectory())) {
      throw new IllegalArgumentException("Destination '" + paramFile2 + "' is not a directory");
    }
    copyFile(paramFile1, new File(paramFile2, paramFile1.getName()), paramBoolean);
  }
  
  /* Error */
  public static void copyInputStreamToFile(InputStream paramInputStream, File paramFile)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 353	org/apache/commons/io/FileUtils:openOutputStream	(Ljava/io/File;)Ljava/io/FileOutputStream;
    //   4: astore_3
    //   5: aload_0
    //   6: aload_3
    //   7: invokestatic 174	org/apache/commons/io/IOUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   10: pop
    //   11: aload_3
    //   12: invokevirtual 356	java/io/FileOutputStream:close	()V
    //   15: aload_3
    //   16: invokestatic 359	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   19: aload_0
    //   20: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   23: return
    //   24: astore 4
    //   26: aload_3
    //   27: invokestatic 359	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   30: aload 4
    //   32: athrow
    //   33: astore_2
    //   34: aload_0
    //   35: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   38: aload_2
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	paramInputStream	InputStream
    //   0	40	1	paramFile	File
    //   33	6	2	localObject1	Object
    //   4	23	3	localFileOutputStream	FileOutputStream
    //   24	7	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   5	15	24	finally
    //   0	5	33	finally
    //   15	19	33	finally
    //   26	33	33	finally
  }
  
  public static void copyURLToFile(URL paramURL, File paramFile)
    throws IOException
  {
    copyInputStreamToFile(paramURL.openStream(), paramFile);
  }
  
  public static void copyURLToFile(URL paramURL, File paramFile, int paramInt1, int paramInt2)
    throws IOException
  {
    URLConnection localURLConnection = paramURL.openConnection();
    localURLConnection.setConnectTimeout(paramInt1);
    localURLConnection.setReadTimeout(paramInt2);
    copyInputStreamToFile(localURLConnection.getInputStream(), paramFile);
  }
  
  /* Error */
  static String decodeUrl(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: astore_1
    //   2: aload_0
    //   3: ifnull +254 -> 257
    //   6: aload_0
    //   7: bipush 37
    //   9: invokevirtual 393	java/lang/String:indexOf	(I)I
    //   12: iflt +245 -> 257
    //   15: aload_0
    //   16: invokevirtual 395	java/lang/String:length	()I
    //   19: istore_2
    //   20: new 397	java/lang/StringBuffer
    //   23: dup
    //   24: invokespecial 398	java/lang/StringBuffer:<init>	()V
    //   27: astore_3
    //   28: iload_2
    //   29: invokestatic 404	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   32: astore 4
    //   34: iconst_0
    //   35: istore 5
    //   37: iload 5
    //   39: iload_2
    //   40: if_icmpge +212 -> 252
    //   43: aload_0
    //   44: iload 5
    //   46: invokevirtual 408	java/lang/String:charAt	(I)C
    //   49: bipush 37
    //   51: if_icmpne +136 -> 187
    //   54: iload 5
    //   56: iconst_1
    //   57: iadd
    //   58: istore 8
    //   60: iload 5
    //   62: iconst_3
    //   63: iadd
    //   64: istore 9
    //   66: aload 4
    //   68: aload_0
    //   69: iload 8
    //   71: iload 9
    //   73: invokevirtual 412	java/lang/String:substring	(II)Ljava/lang/String;
    //   76: bipush 16
    //   78: invokestatic 418	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   81: i2b
    //   82: invokevirtual 422	java/nio/ByteBuffer:put	(B)Ljava/nio/ByteBuffer;
    //   85: pop
    //   86: iinc 5 3
    //   89: iload 5
    //   91: iload_2
    //   92: if_icmpge +18 -> 110
    //   95: aload_0
    //   96: iload 5
    //   98: invokevirtual 408	java/lang/String:charAt	(I)C
    //   101: istore 22
    //   103: iload 22
    //   105: bipush 37
    //   107: if_icmpeq -53 -> 54
    //   110: aload 4
    //   112: invokevirtual 425	java/nio/ByteBuffer:position	()I
    //   115: ifle -78 -> 37
    //   118: aload 4
    //   120: invokevirtual 429	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   123: pop
    //   124: aload_3
    //   125: getstatic 81	org/apache/commons/io/FileUtils:UTF8	Ljava/nio/charset/Charset;
    //   128: aload 4
    //   130: invokevirtual 433	java/nio/charset/Charset:decode	(Ljava/nio/ByteBuffer;)Ljava/nio/CharBuffer;
    //   133: invokevirtual 436	java/nio/CharBuffer:toString	()Ljava/lang/String;
    //   136: invokevirtual 439	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   139: pop
    //   140: aload 4
    //   142: invokevirtual 442	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   145: pop
    //   146: goto -109 -> 37
    //   149: astore 14
    //   151: aload 4
    //   153: invokevirtual 425	java/nio/ByteBuffer:position	()I
    //   156: ifle +31 -> 187
    //   159: aload 4
    //   161: invokevirtual 429	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   164: pop
    //   165: aload_3
    //   166: getstatic 81	org/apache/commons/io/FileUtils:UTF8	Ljava/nio/charset/Charset;
    //   169: aload 4
    //   171: invokevirtual 433	java/nio/charset/Charset:decode	(Ljava/nio/ByteBuffer;)Ljava/nio/CharBuffer;
    //   174: invokevirtual 436	java/nio/CharBuffer:toString	()Ljava/lang/String;
    //   177: invokevirtual 439	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   180: pop
    //   181: aload 4
    //   183: invokevirtual 442	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   186: pop
    //   187: iload 5
    //   189: iconst_1
    //   190: iadd
    //   191: istore 6
    //   193: aload_3
    //   194: aload_0
    //   195: iload 5
    //   197: invokevirtual 408	java/lang/String:charAt	(I)C
    //   200: invokevirtual 445	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   203: pop
    //   204: iload 6
    //   206: istore 5
    //   208: goto -171 -> 37
    //   211: astore 10
    //   213: aload 4
    //   215: invokevirtual 425	java/nio/ByteBuffer:position	()I
    //   218: ifle +31 -> 249
    //   221: aload 4
    //   223: invokevirtual 429	java/nio/ByteBuffer:flip	()Ljava/nio/Buffer;
    //   226: pop
    //   227: aload_3
    //   228: getstatic 81	org/apache/commons/io/FileUtils:UTF8	Ljava/nio/charset/Charset;
    //   231: aload 4
    //   233: invokevirtual 433	java/nio/charset/Charset:decode	(Ljava/nio/ByteBuffer;)Ljava/nio/CharBuffer;
    //   236: invokevirtual 436	java/nio/CharBuffer:toString	()Ljava/lang/String;
    //   239: invokevirtual 439	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   242: pop
    //   243: aload 4
    //   245: invokevirtual 442	java/nio/ByteBuffer:clear	()Ljava/nio/Buffer;
    //   248: pop
    //   249: aload 10
    //   251: athrow
    //   252: aload_3
    //   253: invokevirtual 446	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   256: astore_1
    //   257: aload_1
    //   258: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	259	0	paramString	String
    //   1	257	1	str	String
    //   19	74	2	i	int
    //   27	226	3	localStringBuffer	StringBuffer
    //   32	212	4	localByteBuffer	java.nio.ByteBuffer
    //   35	172	5	j	int
    //   191	14	6	k	int
    //   58	12	8	m	int
    //   64	8	9	n	int
    //   211	39	10	localObject	Object
    //   149	1	14	localRuntimeException	RuntimeException
    //   101	7	22	i1	int
    // Exception table:
    //   from	to	target	type
    //   66	103	149	java/lang/RuntimeException
    //   66	103	211	finally
  }
  
  public static void deleteDirectory(File paramFile)
    throws IOException
  {
    if (!paramFile.exists()) {}
    do
    {
      return;
      if (!isSymlink(paramFile)) {
        cleanDirectory(paramFile);
      }
    } while (paramFile.delete());
    throw new IOException("Unable to delete directory " + paramFile + ".");
  }
  
  private static void deleteDirectoryOnExit(File paramFile)
    throws IOException
  {
    if (!paramFile.exists()) {}
    for (;;)
    {
      return;
      paramFile.deleteOnExit();
      if (!isSymlink(paramFile)) {
        cleanDirectoryOnExit(paramFile);
      }
    }
  }
  
  public static boolean deleteQuietly(File paramFile)
  {
    boolean bool1 = false;
    if (paramFile == null) {}
    for (;;)
    {
      return bool1;
      try
      {
        if (paramFile.isDirectory()) {
          cleanDirectory(paramFile);
        }
        try
        {
          boolean bool2 = paramFile.delete();
          bool1 = bool2;
        }
        catch (Exception localException2) {}
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
  }
  
  public static boolean directoryContains(File paramFile1, File paramFile2)
    throws IOException
  {
    boolean bool = false;
    if (paramFile1 == null) {
      throw new IllegalArgumentException("Directory must not be null");
    }
    if (!paramFile1.isDirectory()) {
      throw new IllegalArgumentException("Not a directory: " + paramFile1);
    }
    if (paramFile2 == null) {}
    for (;;)
    {
      return bool;
      if ((paramFile1.exists()) && (paramFile2.exists())) {
        bool = FilenameUtils.directoryContains(paramFile1.getCanonicalPath(), paramFile2.getCanonicalPath());
      }
    }
  }
  
  private static void doCopyDirectory(File paramFile1, File paramFile2, FileFilter paramFileFilter, boolean paramBoolean, List<String> paramList)
    throws IOException
  {
    if (paramFileFilter == null) {}
    for (File[] arrayOfFile1 = paramFile1.listFiles(); arrayOfFile1 == null; arrayOfFile1 = paramFile1.listFiles(paramFileFilter)) {
      throw new IOException("Failed to list contents of " + paramFile1);
    }
    if (paramFile2.exists())
    {
      if (!paramFile2.isDirectory()) {
        throw new IOException("Destination '" + paramFile2 + "' exists but is not a directory");
      }
    }
    else if ((!paramFile2.mkdirs()) && (!paramFile2.isDirectory())) {
      throw new IOException("Destination '" + paramFile2 + "' directory cannot be created");
    }
    if (!paramFile2.canWrite()) {
      throw new IOException("Destination '" + paramFile2 + "' cannot be written to");
    }
    File[] arrayOfFile2 = arrayOfFile1;
    int i = arrayOfFile2.length;
    int j = 0;
    if (j < i)
    {
      File localFile1 = arrayOfFile2[j];
      File localFile2 = new File(paramFile2, localFile1.getName());
      if ((paramList == null) || (!paramList.contains(localFile1.getCanonicalPath())))
      {
        if (!localFile1.isDirectory()) {
          break label275;
        }
        doCopyDirectory(localFile1, localFile2, paramFileFilter, paramBoolean, paramList);
      }
      for (;;)
      {
        j++;
        break;
        label275:
        doCopyFile(localFile1, localFile2, paramBoolean);
      }
    }
    if (paramBoolean) {
      paramFile2.setLastModified(paramFile1.lastModified());
    }
  }
  
  /* Error */
  private static void doCopyFile(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 135	java/io/File:exists	()Z
    //   4: ifeq +44 -> 48
    //   7: aload_1
    //   8: invokevirtual 148	java/io/File:isDirectory	()Z
    //   11: ifeq +37 -> 48
    //   14: new 154	java/io/IOException
    //   17: dup
    //   18: new 101	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   25: ldc_w 315
    //   28: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: aload_1
    //   32: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   35: ldc_w 328
    //   38: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: invokespecial 197	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   47: athrow
    //   48: aconst_null
    //   49: astore_3
    //   50: aconst_null
    //   51: astore 4
    //   53: aconst_null
    //   54: astore 5
    //   56: aconst_null
    //   57: astore 6
    //   59: new 160	java/io/FileInputStream
    //   62: dup
    //   63: aload_0
    //   64: invokespecial 162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   67: astore 7
    //   69: new 355	java/io/FileOutputStream
    //   72: dup
    //   73: aload_1
    //   74: invokespecial 492	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   77: astore 8
    //   79: aload 7
    //   81: invokevirtual 496	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   84: astore 5
    //   86: aload 8
    //   88: invokevirtual 497	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   91: astore 6
    //   93: aload 5
    //   95: invokevirtual 501	java/nio/channels/FileChannel:size	()J
    //   98: lstore 10
    //   100: lconst_0
    //   101: lstore 12
    //   103: goto +170 -> 273
    //   106: aload 6
    //   108: aload 5
    //   110: lload 12
    //   112: lload 15
    //   114: invokevirtual 505	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   117: lstore 17
    //   119: lload 12
    //   121: lload 17
    //   123: ladd
    //   124: lstore 12
    //   126: goto +147 -> 273
    //   129: lload 10
    //   131: lload 12
    //   133: lsub
    //   134: lstore 15
    //   136: goto -30 -> 106
    //   139: aload 6
    //   141: invokestatic 508	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   144: aload 8
    //   146: invokestatic 359	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   149: aload 5
    //   151: invokestatic 508	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   154: aload 7
    //   156: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   159: aload_0
    //   160: invokevirtual 211	java/io/File:length	()J
    //   163: aload_1
    //   164: invokevirtual 211	java/io/File:length	()J
    //   167: lcmp
    //   168: ifeq +71 -> 239
    //   171: new 154	java/io/IOException
    //   174: dup
    //   175: new 101	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 102	java/lang/StringBuilder:<init>	()V
    //   182: ldc_w 510
    //   185: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: aload_0
    //   189: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   192: ldc_w 512
    //   195: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: aload_1
    //   199: invokevirtual 140	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   202: ldc_w 514
    //   205: invokevirtual 111	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: invokevirtual 117	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   211: invokespecial 197	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   214: athrow
    //   215: astore 9
    //   217: aload 6
    //   219: invokestatic 508	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   222: aload 4
    //   224: invokestatic 359	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   227: aload 5
    //   229: invokestatic 508	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   232: aload_3
    //   233: invokestatic 178	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   236: aload 9
    //   238: athrow
    //   239: iload_2
    //   240: ifeq +12 -> 252
    //   243: aload_1
    //   244: aload_0
    //   245: invokevirtual 487	java/io/File:lastModified	()J
    //   248: invokevirtual 491	java/io/File:setLastModified	(J)Z
    //   251: pop
    //   252: return
    //   253: astore 9
    //   255: aload 7
    //   257: astore_3
    //   258: goto -41 -> 217
    //   261: astore 9
    //   263: aload 8
    //   265: astore 4
    //   267: aload 7
    //   269: astore_3
    //   270: goto -53 -> 217
    //   273: lload 12
    //   275: lload 10
    //   277: lcmp
    //   278: ifge -139 -> 139
    //   281: lload 10
    //   283: lload 12
    //   285: lsub
    //   286: ldc2_w 9
    //   289: lcmp
    //   290: ifle -161 -> 129
    //   293: ldc2_w 9
    //   296: lstore 15
    //   298: goto -192 -> 106
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	301	0	paramFile1	File
    //   0	301	1	paramFile2	File
    //   0	301	2	paramBoolean	boolean
    //   49	221	3	localObject1	Object
    //   51	215	4	localObject2	Object
    //   54	174	5	localFileChannel1	java.nio.channels.FileChannel
    //   57	161	6	localFileChannel2	java.nio.channels.FileChannel
    //   67	201	7	localFileInputStream	FileInputStream
    //   77	187	8	localFileOutputStream	FileOutputStream
    //   215	22	9	localObject3	Object
    //   253	1	9	localObject4	Object
    //   261	1	9	localObject5	Object
    //   98	184	10	l1	long
    //   101	183	12	l2	long
    //   112	1	15	localObject6	Object
    //   134	163	15	l3	long
    //   117	5	17	l4	long
    // Exception table:
    //   from	to	target	type
    //   59	69	215	finally
    //   69	79	253	finally
    //   79	119	261	finally
  }
  
  public static void forceDelete(File paramFile)
    throws IOException
  {
    if (paramFile.isDirectory()) {
      deleteDirectory(paramFile);
    }
    boolean bool;
    do
    {
      return;
      bool = paramFile.exists();
    } while (paramFile.delete());
    if (!bool) {
      throw new FileNotFoundException("File does not exist: " + paramFile);
    }
    throw new IOException("Unable to delete file: " + paramFile);
  }
  
  public static void forceDeleteOnExit(File paramFile)
    throws IOException
  {
    if (paramFile.isDirectory()) {
      deleteDirectoryOnExit(paramFile);
    }
    for (;;)
    {
      return;
      paramFile.deleteOnExit();
    }
  }
  
  public static void forceMkdir(File paramFile)
    throws IOException
  {
    if (paramFile.exists())
    {
      if (!paramFile.isDirectory()) {
        throw new IOException("File " + paramFile + " exists and is " + "not a directory. Unable to create directory.");
      }
    }
    else if ((!paramFile.mkdirs()) && (!paramFile.isDirectory())) {
      throw new IOException("Unable to create directory " + paramFile);
    }
  }
  
  public static File getFile(File paramFile, String... paramVarArgs)
  {
    if (paramFile == null) {
      throw new NullPointerException("directorydirectory must not be null");
    }
    if (paramVarArgs == null) {
      throw new NullPointerException("names must not be null");
    }
    int i = paramVarArgs.length;
    int j = 0;
    File localFile;
    for (Object localObject = paramFile; j < i; localObject = localFile)
    {
      localFile = new File((File)localObject, paramVarArgs[j]);
      j++;
    }
    return (File)localObject;
  }
  
  public static File getFile(String... paramVarArgs)
  {
    if (paramVarArgs == null) {
      throw new NullPointerException("names must not be null");
    }
    int i = paramVarArgs.length;
    int j = 0;
    Object localObject = null;
    if (j < i)
    {
      String str = paramVarArgs[j];
      if (localObject == null) {}
      for (File localFile = new File(str);; localFile = new File((File)localObject, str))
      {
        j++;
        localObject = localFile;
        break;
      }
    }
    return (File)localObject;
  }
  
  public static File getTempDirectory()
  {
    return new File(getTempDirectoryPath());
  }
  
  public static String getTempDirectoryPath()
  {
    return System.getProperty("java.io.tmpdir");
  }
  
  public static File getUserDirectory()
  {
    return new File(getUserDirectoryPath());
  }
  
  public static String getUserDirectoryPath()
  {
    return System.getProperty("user.home");
  }
  
  private static void innerListFiles(Collection<File> paramCollection, File paramFile, IOFileFilter paramIOFileFilter, boolean paramBoolean)
  {
    File[] arrayOfFile = paramFile.listFiles(paramIOFileFilter);
    if (arrayOfFile != null)
    {
      int i = arrayOfFile.length;
      int j = 0;
      if (j < i)
      {
        File localFile = arrayOfFile[j];
        if (localFile.isDirectory())
        {
          if (paramBoolean) {
            paramCollection.add(localFile);
          }
          innerListFiles(paramCollection, localFile, paramIOFileFilter, paramBoolean);
        }
        for (;;)
        {
          j++;
          break;
          paramCollection.add(localFile);
        }
      }
    }
  }
  
  public static boolean isFileNewer(File paramFile, long paramLong)
  {
    boolean bool = false;
    if (paramFile == null) {
      throw new IllegalArgumentException("No specified file");
    }
    if (!paramFile.exists()) {}
    for (;;)
    {
      return bool;
      if (paramFile.lastModified() > paramLong) {
        bool = true;
      }
    }
  }
  
  public static boolean isFileNewer(File paramFile1, File paramFile2)
  {
    if (paramFile2 == null) {
      throw new IllegalArgumentException("No specified reference file");
    }
    if (!paramFile2.exists()) {
      throw new IllegalArgumentException("The reference file '" + paramFile2 + "' doesn't exist");
    }
    return isFileNewer(paramFile1, paramFile2.lastModified());
  }
  
  public static boolean isFileNewer(File paramFile, Date paramDate)
  {
    if (paramDate == null) {
      throw new IllegalArgumentException("No specified date");
    }
    return isFileNewer(paramFile, paramDate.getTime());
  }
  
  public static boolean isFileOlder(File paramFile, long paramLong)
  {
    boolean bool = false;
    if (paramFile == null) {
      throw new IllegalArgumentException("No specified file");
    }
    if (!paramFile.exists()) {}
    for (;;)
    {
      return bool;
      if (paramFile.lastModified() < paramLong) {
        bool = true;
      }
    }
  }
  
  public static boolean isFileOlder(File paramFile1, File paramFile2)
  {
    if (paramFile2 == null) {
      throw new IllegalArgumentException("No specified reference file");
    }
    if (!paramFile2.exists()) {
      throw new IllegalArgumentException("The reference file '" + paramFile2 + "' doesn't exist");
    }
    return isFileOlder(paramFile1, paramFile2.lastModified());
  }
  
  public static boolean isFileOlder(File paramFile, Date paramDate)
  {
    if (paramDate == null) {
      throw new IllegalArgumentException("No specified date");
    }
    return isFileOlder(paramFile, paramDate.getTime());
  }
  
  public static boolean isSymlink(File paramFile)
    throws IOException
  {
    boolean bool = false;
    if (paramFile == null) {
      throw new NullPointerException("File must not be null");
    }
    if (FilenameUtils.isSystemWindows()) {}
    label73:
    for (;;)
    {
      return bool;
      if (paramFile.getParent() == null) {}
      for (File localFile = paramFile;; localFile = new File(paramFile.getParentFile().getCanonicalFile(), paramFile.getName()))
      {
        if (localFile.getCanonicalFile().equals(localFile.getAbsoluteFile())) {
          break label73;
        }
        bool = true;
        break;
      }
    }
  }
  
  public static Iterator<File> iterateFiles(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    return listFiles(paramFile, paramIOFileFilter1, paramIOFileFilter2).iterator();
  }
  
  public static Iterator<File> iterateFiles(File paramFile, String[] paramArrayOfString, boolean paramBoolean)
  {
    return listFiles(paramFile, paramArrayOfString, paramBoolean).iterator();
  }
  
  public static Iterator<File> iterateFilesAndDirs(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    return listFilesAndDirs(paramFile, paramIOFileFilter1, paramIOFileFilter2).iterator();
  }
  
  public static LineIterator lineIterator(File paramFile)
    throws IOException
  {
    return lineIterator(paramFile, null);
  }
  
  public static LineIterator lineIterator(File paramFile, String paramString)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      LineIterator localLineIterator = IOUtils.lineIterator(localFileInputStream, paramString);
      return localLineIterator;
    }
    catch (IOException localIOException)
    {
      IOUtils.closeQuietly(localFileInputStream);
      throw localIOException;
    }
    catch (RuntimeException localRuntimeException)
    {
      IOUtils.closeQuietly(localFileInputStream);
      throw localRuntimeException;
    }
  }
  
  public static Collection<File> listFiles(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    validateListFilesParameters(paramFile, paramIOFileFilter1);
    IOFileFilter localIOFileFilter1 = setUpEffectiveFileFilter(paramIOFileFilter1);
    IOFileFilter localIOFileFilter2 = setUpEffectiveDirFilter(paramIOFileFilter2);
    LinkedList localLinkedList = new LinkedList();
    IOFileFilter[] arrayOfIOFileFilter = new IOFileFilter[2];
    arrayOfIOFileFilter[0] = localIOFileFilter1;
    arrayOfIOFileFilter[1] = localIOFileFilter2;
    innerListFiles(localLinkedList, paramFile, FileFilterUtils.or(arrayOfIOFileFilter), false);
    return localLinkedList;
  }
  
  public static Collection<File> listFiles(File paramFile, String[] paramArrayOfString, boolean paramBoolean)
  {
    Object localObject;
    if (paramArrayOfString == null)
    {
      localObject = TrueFileFilter.INSTANCE;
      if (!paramBoolean) {
        break label40;
      }
    }
    label40:
    for (IOFileFilter localIOFileFilter = TrueFileFilter.INSTANCE;; localIOFileFilter = FalseFileFilter.INSTANCE)
    {
      return listFiles(paramFile, (IOFileFilter)localObject, localIOFileFilter);
      localObject = new SuffixFileFilter(toSuffixes(paramArrayOfString));
      break;
    }
  }
  
  public static Collection<File> listFilesAndDirs(File paramFile, IOFileFilter paramIOFileFilter1, IOFileFilter paramIOFileFilter2)
  {
    validateListFilesParameters(paramFile, paramIOFileFilter1);
    IOFileFilter localIOFileFilter1 = setUpEffectiveFileFilter(paramIOFileFilter1);
    IOFileFilter localIOFileFilter2 = setUpEffectiveDirFilter(paramIOFileFilter2);
    LinkedList localLinkedList = new LinkedList();
    if (paramFile.isDirectory()) {
      localLinkedList.add(paramFile);
    }
    IOFileFilter[] arrayOfIOFileFilter = new IOFileFilter[2];
    arrayOfIOFileFilter[0] = localIOFileFilter1;
    arrayOfIOFileFilter[1] = localIOFileFilter2;
    innerListFiles(localLinkedList, paramFile, FileFilterUtils.or(arrayOfIOFileFilter), true);
    return localLinkedList;
  }
  
  public static void moveDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    if (paramFile1 == null) {
      throw new NullPointerException("Source must not be null");
    }
    if (paramFile2 == null) {
      throw new NullPointerException("Destination must not be null");
    }
    if (!paramFile1.exists()) {
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    }
    if (!paramFile1.isDirectory()) {
      throw new IOException("Source '" + paramFile1 + "' is not a directory");
    }
    if (paramFile2.exists()) {
      throw new FileExistsException("Destination '" + paramFile2 + "' already exists");
    }
    if (!paramFile1.renameTo(paramFile2))
    {
      if (paramFile2.getCanonicalPath().startsWith(paramFile1.getCanonicalPath())) {
        throw new IOException("Cannot move directory: " + paramFile1 + " to a subdirectory of itself: " + paramFile2);
      }
      copyDirectory(paramFile1, paramFile2);
      deleteDirectory(paramFile1);
      if (paramFile1.exists()) {
        throw new IOException("Failed to delete original directory '" + paramFile1 + "' after copy to '" + paramFile2 + "'");
      }
    }
  }
  
  public static void moveDirectoryToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null) {
      throw new NullPointerException("Source must not be null");
    }
    if (paramFile2 == null) {
      throw new NullPointerException("Destination directory must not be null");
    }
    if ((!paramFile2.exists()) && (paramBoolean)) {
      paramFile2.mkdirs();
    }
    if (!paramFile2.exists()) {
      throw new FileNotFoundException("Destination directory '" + paramFile2 + "' does not exist [createDestDir=" + paramBoolean + "]");
    }
    if (!paramFile2.isDirectory()) {
      throw new IOException("Destination '" + paramFile2 + "' is not a directory");
    }
    moveDirectory(paramFile1, new File(paramFile2, paramFile1.getName()));
  }
  
  public static void moveFile(File paramFile1, File paramFile2)
    throws IOException
  {
    if (paramFile1 == null) {
      throw new NullPointerException("Source must not be null");
    }
    if (paramFile2 == null) {
      throw new NullPointerException("Destination must not be null");
    }
    if (!paramFile1.exists()) {
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    }
    if (paramFile1.isDirectory()) {
      throw new IOException("Source '" + paramFile1 + "' is a directory");
    }
    if (paramFile2.exists()) {
      throw new FileExistsException("Destination '" + paramFile2 + "' already exists");
    }
    if (paramFile2.isDirectory()) {
      throw new IOException("Destination '" + paramFile2 + "' is a directory");
    }
    if (!paramFile1.renameTo(paramFile2))
    {
      copyFile(paramFile1, paramFile2);
      if (!paramFile1.delete())
      {
        deleteQuietly(paramFile2);
        throw new IOException("Failed to delete original file '" + paramFile1 + "' after copy to '" + paramFile2 + "'");
      }
    }
  }
  
  public static void moveFileToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null) {
      throw new NullPointerException("Source must not be null");
    }
    if (paramFile2 == null) {
      throw new NullPointerException("Destination directory must not be null");
    }
    if ((!paramFile2.exists()) && (paramBoolean)) {
      paramFile2.mkdirs();
    }
    if (!paramFile2.exists()) {
      throw new FileNotFoundException("Destination directory '" + paramFile2 + "' does not exist [createDestDir=" + paramBoolean + "]");
    }
    if (!paramFile2.isDirectory()) {
      throw new IOException("Destination '" + paramFile2 + "' is not a directory");
    }
    moveFile(paramFile1, new File(paramFile2, paramFile1.getName()));
  }
  
  public static void moveToDirectory(File paramFile1, File paramFile2, boolean paramBoolean)
    throws IOException
  {
    if (paramFile1 == null) {
      throw new NullPointerException("Source must not be null");
    }
    if (paramFile2 == null) {
      throw new NullPointerException("Destination must not be null");
    }
    if (!paramFile1.exists()) {
      throw new FileNotFoundException("Source '" + paramFile1 + "' does not exist");
    }
    if (paramFile1.isDirectory()) {
      moveDirectoryToDirectory(paramFile1, paramFile2, paramBoolean);
    }
    for (;;)
    {
      return;
      moveFileToDirectory(paramFile1, paramFile2, paramBoolean);
    }
  }
  
  public static FileInputStream openInputStream(File paramFile)
    throws IOException
  {
    if (paramFile.exists())
    {
      if (paramFile.isDirectory()) {
        throw new IOException("File '" + paramFile + "' exists but is a directory");
      }
      if (!paramFile.canRead()) {
        throw new IOException("File '" + paramFile + "' cannot be read");
      }
    }
    else
    {
      throw new FileNotFoundException("File '" + paramFile + "' does not exist");
    }
    return new FileInputStream(paramFile);
  }
  
  public static FileOutputStream openOutputStream(File paramFile)
    throws IOException
  {
    return openOutputStream(paramFile, false);
  }
  
  public static FileOutputStream openOutputStream(File paramFile, boolean paramBoolean)
    throws IOException
  {
    if (paramFile.exists())
    {
      if (paramFile.isDirectory()) {
        throw new IOException("File '" + paramFile + "' exists but is a directory");
      }
      if (!paramFile.canWrite()) {
        throw new IOException("File '" + paramFile + "' cannot be written to");
      }
    }
    else
    {
      File localFile = paramFile.getParentFile();
      if ((localFile != null) && (!localFile.mkdirs()) && (!localFile.isDirectory())) {
        throw new IOException("Directory '" + localFile + "' could not be created");
      }
    }
    return new FileOutputStream(paramFile, paramBoolean);
  }
  
  public static byte[] readFileToByteArray(File paramFile)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      byte[] arrayOfByte = IOUtils.toByteArray(localFileInputStream, paramFile.length());
      return arrayOfByte;
    }
    finally
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
  }
  
  public static String readFileToString(File paramFile)
    throws IOException
  {
    return readFileToString(paramFile, Charset.defaultCharset());
  }
  
  public static String readFileToString(File paramFile, String paramString)
    throws IOException
  {
    return readFileToString(paramFile, Charsets.toCharset(paramString));
  }
  
  public static String readFileToString(File paramFile, Charset paramCharset)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      String str = IOUtils.toString(localFileInputStream, Charsets.toCharset(paramCharset));
      return str;
    }
    finally
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
  }
  
  public static List<String> readLines(File paramFile)
    throws IOException
  {
    return readLines(paramFile, Charset.defaultCharset());
  }
  
  public static List<String> readLines(File paramFile, String paramString)
    throws IOException
  {
    return readLines(paramFile, Charsets.toCharset(paramString));
  }
  
  public static List<String> readLines(File paramFile, Charset paramCharset)
    throws IOException
  {
    FileInputStream localFileInputStream = null;
    try
    {
      localFileInputStream = openInputStream(paramFile);
      List localList = IOUtils.readLines(localFileInputStream, Charsets.toCharset(paramCharset));
      return localList;
    }
    finally
    {
      IOUtils.closeQuietly(localFileInputStream);
    }
  }
  
  private static IOFileFilter setUpEffectiveDirFilter(IOFileFilter paramIOFileFilter)
  {
    if (paramIOFileFilter == null) {}
    IOFileFilter[] arrayOfIOFileFilter;
    for (IOFileFilter localIOFileFilter = FalseFileFilter.INSTANCE;; localIOFileFilter = FileFilterUtils.and(arrayOfIOFileFilter))
    {
      return localIOFileFilter;
      arrayOfIOFileFilter = new IOFileFilter[2];
      arrayOfIOFileFilter[0] = paramIOFileFilter;
      arrayOfIOFileFilter[1] = DirectoryFileFilter.INSTANCE;
    }
  }
  
  private static IOFileFilter setUpEffectiveFileFilter(IOFileFilter paramIOFileFilter)
  {
    IOFileFilter[] arrayOfIOFileFilter = new IOFileFilter[2];
    arrayOfIOFileFilter[0] = paramIOFileFilter;
    arrayOfIOFileFilter[1] = FileFilterUtils.notFileFilter(DirectoryFileFilter.INSTANCE);
    return FileFilterUtils.and(arrayOfIOFileFilter);
  }
  
  public static long sizeOf(File paramFile)
  {
    if (!paramFile.exists()) {
      throw new IllegalArgumentException(paramFile + " does not exist");
    }
    if (paramFile.isDirectory()) {}
    for (long l = sizeOfDirectory(paramFile);; l = paramFile.length()) {
      return l;
    }
  }
  
  public static BigInteger sizeOfAsBigInteger(File paramFile)
  {
    if (!paramFile.exists()) {
      throw new IllegalArgumentException(paramFile + " does not exist");
    }
    if (paramFile.isDirectory()) {}
    for (BigInteger localBigInteger = sizeOfDirectoryAsBigInteger(paramFile);; localBigInteger = BigInteger.valueOf(paramFile.length())) {
      return localBigInteger;
    }
  }
  
  public static long sizeOfDirectory(File paramFile)
  {
    checkDirectory(paramFile);
    File[] arrayOfFile = paramFile.listFiles();
    long l1;
    if (arrayOfFile == null) {
      l1 = 0L;
    }
    label69:
    for (;;)
    {
      return l1;
      l1 = 0L;
      int i = arrayOfFile.length;
      int j = 0;
      for (;;)
      {
        if (j >= i) {
          break label69;
        }
        File localFile = arrayOfFile[j];
        try
        {
          if (!isSymlink(localFile))
          {
            long l2 = sizeOf(localFile);
            l1 += l2;
            if (l1 < 0L) {
              break;
            }
          }
          j++;
        }
        catch (IOException localIOException)
        {
          for (;;) {}
        }
      }
    }
  }
  
  public static BigInteger sizeOfDirectoryAsBigInteger(File paramFile)
  {
    checkDirectory(paramFile);
    File[] arrayOfFile = paramFile.listFiles();
    Object localObject;
    if (arrayOfFile == null) {
      localObject = BigInteger.ZERO;
    }
    for (;;)
    {
      return (BigInteger)localObject;
      localObject = BigInteger.ZERO;
      int i = arrayOfFile.length;
      int j = 0;
      while (j < i)
      {
        File localFile = arrayOfFile[j];
        try
        {
          if (!isSymlink(localFile))
          {
            BigInteger localBigInteger = ((BigInteger)localObject).add(BigInteger.valueOf(sizeOf(localFile)));
            localObject = localBigInteger;
          }
          j++;
        }
        catch (IOException localIOException)
        {
          for (;;) {}
        }
      }
    }
  }
  
  public static File toFile(URL paramURL)
  {
    if ((paramURL == null) || (!"file".equalsIgnoreCase(paramURL.getProtocol()))) {}
    for (File localFile = null;; localFile = new File(decodeUrl(paramURL.getFile().replace('/', File.separatorChar)))) {
      return localFile;
    }
  }
  
  public static File[] toFiles(URL[] paramArrayOfURL)
  {
    File[] arrayOfFile;
    if ((paramArrayOfURL == null) || (paramArrayOfURL.length == 0)) {
      arrayOfFile = EMPTY_FILE_ARRAY;
    }
    for (;;)
    {
      return arrayOfFile;
      arrayOfFile = new File[paramArrayOfURL.length];
      for (int i = 0; i < paramArrayOfURL.length; i++)
      {
        URL localURL = paramArrayOfURL[i];
        if (localURL != null)
        {
          if (!localURL.getProtocol().equals("file")) {
            throw new IllegalArgumentException("URL could not be converted to a File: " + localURL);
          }
          arrayOfFile[i] = toFile(localURL);
        }
      }
    }
  }
  
  private static String[] toSuffixes(String[] paramArrayOfString)
  {
    String[] arrayOfString = new String[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; i++) {
      arrayOfString[i] = ("." + paramArrayOfString[i]);
    }
    return arrayOfString;
  }
  
  public static URL[] toURLs(File[] paramArrayOfFile)
    throws IOException
  {
    URL[] arrayOfURL = new URL[paramArrayOfFile.length];
    for (int i = 0; i < arrayOfURL.length; i++) {
      arrayOfURL[i] = paramArrayOfFile[i].toURI().toURL();
    }
    return arrayOfURL;
  }
  
  public static void touch(File paramFile)
    throws IOException
  {
    if (!paramFile.exists()) {
      IOUtils.closeQuietly(openOutputStream(paramFile));
    }
    if (!paramFile.setLastModified(System.currentTimeMillis())) {
      throw new IOException("Unable to set the last modification time for " + paramFile);
    }
  }
  
  private static void validateListFilesParameters(File paramFile, IOFileFilter paramIOFileFilter)
  {
    if (!paramFile.isDirectory()) {
      throw new IllegalArgumentException("Parameter 'directory' is not a directory");
    }
    if (paramIOFileFilter == null) {
      throw new NullPointerException("Parameter 'fileFilter' is null");
    }
  }
  
  public static boolean waitFor(File paramFile, int paramInt)
  {
    int i = 0;
    int j = 0;
    int k;
    boolean bool;
    if (!paramFile.exists())
    {
      k = j + 1;
      if (j < 10) {
        break label65;
      }
      j = 0;
      int m = i + 1;
      if (i > paramInt)
      {
        bool = false;
        label37:
        return bool;
      }
      i = m;
    }
    for (;;)
    {
      try
      {
        Thread.sleep(100L);
      }
      catch (InterruptedException localInterruptedException) {}catch (Exception localException) {}
      bool = true;
      break label37;
      label65:
      j = k;
    }
  }
  
  public static void write(File paramFile, CharSequence paramCharSequence)
    throws IOException
  {
    write(paramFile, paramCharSequence, Charset.defaultCharset(), false);
  }
  
  public static void write(File paramFile, CharSequence paramCharSequence, String paramString)
    throws IOException
  {
    write(paramFile, paramCharSequence, paramString, false);
  }
  
  public static void write(File paramFile, CharSequence paramCharSequence, String paramString, boolean paramBoolean)
    throws IOException
  {
    write(paramFile, paramCharSequence, Charsets.toCharset(paramString), paramBoolean);
  }
  
  public static void write(File paramFile, CharSequence paramCharSequence, Charset paramCharset)
    throws IOException
  {
    write(paramFile, paramCharSequence, paramCharset, false);
  }
  
  public static void write(File paramFile, CharSequence paramCharSequence, Charset paramCharset, boolean paramBoolean)
    throws IOException
  {
    if (paramCharSequence == null) {}
    for (String str = null;; str = paramCharSequence.toString())
    {
      writeStringToFile(paramFile, str, paramCharset, paramBoolean);
      return;
    }
  }
  
  public static void write(File paramFile, CharSequence paramCharSequence, boolean paramBoolean)
    throws IOException
  {
    write(paramFile, paramCharSequence, Charset.defaultCharset(), paramBoolean);
  }
  
  public static void writeByteArrayToFile(File paramFile, byte[] paramArrayOfByte)
    throws IOException
  {
    writeByteArrayToFile(paramFile, paramArrayOfByte, false);
  }
  
  public static void writeByteArrayToFile(File paramFile, byte[] paramArrayOfByte, boolean paramBoolean)
    throws IOException
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = openOutputStream(paramFile, paramBoolean);
      localFileOutputStream.write(paramArrayOfByte);
      localFileOutputStream.close();
      return;
    }
    finally
    {
      IOUtils.closeQuietly(localFileOutputStream);
    }
  }
  
  public static void writeLines(File paramFile, String paramString, Collection<?> paramCollection)
    throws IOException
  {
    writeLines(paramFile, paramString, paramCollection, null, false);
  }
  
  public static void writeLines(File paramFile, String paramString1, Collection<?> paramCollection, String paramString2)
    throws IOException
  {
    writeLines(paramFile, paramString1, paramCollection, paramString2, false);
  }
  
  public static void writeLines(File paramFile, String paramString1, Collection<?> paramCollection, String paramString2, boolean paramBoolean)
    throws IOException
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = openOutputStream(paramFile, paramBoolean);
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream);
      IOUtils.writeLines(paramCollection, paramString2, localBufferedOutputStream, paramString1);
      localBufferedOutputStream.flush();
      localFileOutputStream.close();
      return;
    }
    finally
    {
      IOUtils.closeQuietly(localFileOutputStream);
    }
  }
  
  public static void writeLines(File paramFile, String paramString, Collection<?> paramCollection, boolean paramBoolean)
    throws IOException
  {
    writeLines(paramFile, paramString, paramCollection, null, paramBoolean);
  }
  
  public static void writeLines(File paramFile, Collection<?> paramCollection)
    throws IOException
  {
    writeLines(paramFile, null, paramCollection, null, false);
  }
  
  public static void writeLines(File paramFile, Collection<?> paramCollection, String paramString)
    throws IOException
  {
    writeLines(paramFile, null, paramCollection, paramString, false);
  }
  
  public static void writeLines(File paramFile, Collection<?> paramCollection, String paramString, boolean paramBoolean)
    throws IOException
  {
    writeLines(paramFile, null, paramCollection, paramString, paramBoolean);
  }
  
  public static void writeLines(File paramFile, Collection<?> paramCollection, boolean paramBoolean)
    throws IOException
  {
    writeLines(paramFile, null, paramCollection, null, paramBoolean);
  }
  
  public static void writeStringToFile(File paramFile, String paramString)
    throws IOException
  {
    writeStringToFile(paramFile, paramString, Charset.defaultCharset(), false);
  }
  
  public static void writeStringToFile(File paramFile, String paramString1, String paramString2)
    throws IOException
  {
    writeStringToFile(paramFile, paramString1, paramString2, false);
  }
  
  public static void writeStringToFile(File paramFile, String paramString1, String paramString2, boolean paramBoolean)
    throws IOException
  {
    writeStringToFile(paramFile, paramString1, Charsets.toCharset(paramString2), paramBoolean);
  }
  
  public static void writeStringToFile(File paramFile, String paramString, Charset paramCharset)
    throws IOException
  {
    writeStringToFile(paramFile, paramString, paramCharset, false);
  }
  
  public static void writeStringToFile(File paramFile, String paramString, Charset paramCharset, boolean paramBoolean)
    throws IOException
  {
    FileOutputStream localFileOutputStream = null;
    try
    {
      localFileOutputStream = openOutputStream(paramFile, paramBoolean);
      IOUtils.write(paramString, localFileOutputStream, paramCharset);
      localFileOutputStream.close();
      return;
    }
    finally
    {
      IOUtils.closeQuietly(localFileOutputStream);
    }
  }
  
  public static void writeStringToFile(File paramFile, String paramString, boolean paramBoolean)
    throws IOException
  {
    writeStringToFile(paramFile, paramString, Charset.defaultCharset(), paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */