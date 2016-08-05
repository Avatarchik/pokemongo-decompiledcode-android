package org.apache.commons.io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DeferredFileOutputStream
  extends ThresholdingOutputStream
{
  private boolean closed = false;
  private OutputStream currentOutputStream;
  private final File directory;
  private ByteArrayOutputStream memoryOutputStream;
  private File outputFile;
  private final String prefix;
  private final String suffix;
  
  public DeferredFileOutputStream(int paramInt, File paramFile)
  {
    this(paramInt, paramFile, null, null, null);
  }
  
  private DeferredFileOutputStream(int paramInt, File paramFile1, String paramString1, String paramString2, File paramFile2)
  {
    super(paramInt);
    this.outputFile = paramFile1;
    this.memoryOutputStream = new ByteArrayOutputStream();
    this.currentOutputStream = this.memoryOutputStream;
    this.prefix = paramString1;
    this.suffix = paramString2;
    this.directory = paramFile2;
  }
  
  public DeferredFileOutputStream(int paramInt, String paramString1, String paramString2, File paramFile)
  {
    this(paramInt, null, paramString1, paramString2, paramFile);
    if (paramString1 == null) {
      throw new IllegalArgumentException("Temporary file prefix is missing");
    }
  }
  
  public void close()
    throws IOException
  {
    super.close();
    this.closed = true;
  }
  
  public byte[] getData()
  {
    if (this.memoryOutputStream != null) {}
    for (byte[] arrayOfByte = this.memoryOutputStream.toByteArray();; arrayOfByte = null) {
      return arrayOfByte;
    }
  }
  
  public File getFile()
  {
    return this.outputFile;
  }
  
  protected OutputStream getStream()
    throws IOException
  {
    return this.currentOutputStream;
  }
  
  public boolean isInMemory()
  {
    if (!isThresholdExceeded()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected void thresholdReached()
    throws IOException
  {
    if (this.prefix != null) {
      this.outputFile = File.createTempFile(this.prefix, this.suffix, this.directory);
    }
    FileOutputStream localFileOutputStream = new FileOutputStream(this.outputFile);
    this.memoryOutputStream.writeTo(localFileOutputStream);
    this.currentOutputStream = localFileOutputStream;
    this.memoryOutputStream = null;
  }
  
  /* Error */
  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	org/apache/commons/io/output/DeferredFileOutputStream:closed	Z
    //   4: ifne +13 -> 17
    //   7: new 54	java/io/IOException
    //   10: dup
    //   11: ldc 88
    //   13: invokespecial 89	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: invokevirtual 91	org/apache/commons/io/output/DeferredFileOutputStream:isInMemory	()Z
    //   21: ifeq +12 -> 33
    //   24: aload_0
    //   25: getfield 35	org/apache/commons/io/output/DeferredFileOutputStream:memoryOutputStream	Lorg/apache/commons/io/output/ByteArrayOutputStream;
    //   28: aload_1
    //   29: invokevirtual 86	org/apache/commons/io/output/ByteArrayOutputStream:writeTo	(Ljava/io/OutputStream;)V
    //   32: return
    //   33: new 93	java/io/FileInputStream
    //   36: dup
    //   37: aload_0
    //   38: getfield 28	org/apache/commons/io/output/DeferredFileOutputStream:outputFile	Ljava/io/File;
    //   41: invokespecial 94	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   44: astore_2
    //   45: aload_2
    //   46: aload_1
    //   47: invokestatic 100	org/apache/commons/io/IOUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   50: pop
    //   51: aload_2
    //   52: invokestatic 104	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   55: goto -23 -> 32
    //   58: astore_3
    //   59: aload_2
    //   60: invokestatic 104	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   63: aload_3
    //   64: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	65	0	this	DeferredFileOutputStream
    //   0	65	1	paramOutputStream	OutputStream
    //   44	16	2	localFileInputStream	java.io.FileInputStream
    //   58	6	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   45	51	58	finally
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/output/DeferredFileOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */