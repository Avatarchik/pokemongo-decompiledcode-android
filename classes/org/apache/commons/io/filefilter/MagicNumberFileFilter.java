package org.apache.commons.io.filefilter;

import java.io.Serializable;

public class MagicNumberFileFilter
  extends AbstractFileFilter
  implements Serializable
{
  private static final long serialVersionUID = -547733176983104172L;
  private final long byteOffset;
  private final byte[] magicNumbers;
  
  public MagicNumberFileFilter(String paramString)
  {
    this(paramString, 0L);
  }
  
  public MagicNumberFileFilter(String paramString, long paramLong)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("The magic number cannot be null");
    }
    if (paramString.length() == 0) {
      throw new IllegalArgumentException("The magic number must contain at least one byte");
    }
    if (paramLong < 0L) {
      throw new IllegalArgumentException("The offset cannot be negative");
    }
    this.magicNumbers = paramString.getBytes();
    this.byteOffset = paramLong;
  }
  
  public MagicNumberFileFilter(byte[] paramArrayOfByte)
  {
    this(paramArrayOfByte, 0L);
  }
  
  public MagicNumberFileFilter(byte[] paramArrayOfByte, long paramLong)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("The magic number cannot be null");
    }
    if (paramArrayOfByte.length == 0) {
      throw new IllegalArgumentException("The magic number must contain at least one byte");
    }
    if (paramLong < 0L) {
      throw new IllegalArgumentException("The offset cannot be negative");
    }
    this.magicNumbers = new byte[paramArrayOfByte.length];
    System.arraycopy(paramArrayOfByte, 0, this.magicNumbers, 0, paramArrayOfByte.length);
    this.byteOffset = paramLong;
  }
  
  /* Error */
  public boolean accept(java.io.File paramFile)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_1
    //   3: ifnull +77 -> 80
    //   6: aload_1
    //   7: invokevirtual 65	java/io/File:isFile	()Z
    //   10: ifeq +70 -> 80
    //   13: aload_1
    //   14: invokevirtual 68	java/io/File:canRead	()Z
    //   17: ifeq +63 -> 80
    //   20: aconst_null
    //   21: astore_3
    //   22: aload_0
    //   23: getfield 43	org/apache/commons/io/filefilter/MagicNumberFileFilter:magicNumbers	[B
    //   26: arraylength
    //   27: newarray <illegal type>
    //   29: astore 6
    //   31: new 70	java/io/RandomAccessFile
    //   34: dup
    //   35: aload_1
    //   36: ldc 72
    //   38: invokespecial 75	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   41: astore 7
    //   43: aload 7
    //   45: aload_0
    //   46: getfield 45	org/apache/commons/io/filefilter/MagicNumberFileFilter:byteOffset	J
    //   49: invokevirtual 79	java/io/RandomAccessFile:seek	(J)V
    //   52: aload 7
    //   54: aload 6
    //   56: invokevirtual 83	java/io/RandomAccessFile:read	([B)I
    //   59: istore 9
    //   61: aload_0
    //   62: getfield 43	org/apache/commons/io/filefilter/MagicNumberFileFilter:magicNumbers	[B
    //   65: arraylength
    //   66: istore 10
    //   68: iload 9
    //   70: iload 10
    //   72: if_icmpeq +10 -> 82
    //   75: aload 7
    //   77: invokestatic 89	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   80: iload_2
    //   81: ireturn
    //   82: aload_0
    //   83: getfield 43	org/apache/commons/io/filefilter/MagicNumberFileFilter:magicNumbers	[B
    //   86: aload 6
    //   88: invokestatic 95	java/util/Arrays:equals	([B[B)Z
    //   91: istore 11
    //   93: iload 11
    //   95: istore_2
    //   96: aload 7
    //   98: invokestatic 89	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   101: goto -21 -> 80
    //   104: astore 5
    //   106: aload_3
    //   107: invokestatic 89	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   110: goto -30 -> 80
    //   113: astore 4
    //   115: aload_3
    //   116: invokestatic 89	org/apache/commons/io/IOUtils:closeQuietly	(Ljava/io/Closeable;)V
    //   119: aload 4
    //   121: athrow
    //   122: astore 4
    //   124: aload 7
    //   126: astore_3
    //   127: goto -12 -> 115
    //   130: astore 8
    //   132: aload 7
    //   134: astore_3
    //   135: goto -29 -> 106
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	138	0	this	MagicNumberFileFilter
    //   0	138	1	paramFile	java.io.File
    //   1	95	2	bool1	boolean
    //   21	114	3	localObject1	Object
    //   113	7	4	localObject2	Object
    //   122	1	4	localObject3	Object
    //   104	1	5	localIOException1	java.io.IOException
    //   29	58	6	arrayOfByte	byte[]
    //   41	92	7	localRandomAccessFile	java.io.RandomAccessFile
    //   130	1	8	localIOException2	java.io.IOException
    //   59	14	9	i	int
    //   66	7	10	j	int
    //   91	3	11	bool2	boolean
    // Exception table:
    //   from	to	target	type
    //   22	43	104	java/io/IOException
    //   22	43	113	finally
    //   43	68	122	finally
    //   82	93	122	finally
    //   43	68	130	java/io/IOException
    //   82	93	130	java/io/IOException
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(super.toString());
    localStringBuilder.append("(");
    localStringBuilder.append(new String(this.magicNumbers));
    localStringBuilder.append(",");
    localStringBuilder.append(this.byteOffset);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/filefilter/MagicNumberFileFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */