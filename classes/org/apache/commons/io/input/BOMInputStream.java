package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.ByteOrderMark;

public class BOMInputStream
  extends ProxyInputStream
{
  private static final Comparator<ByteOrderMark> ByteOrderMarkLengthComparator = new Comparator()
  {
    public int compare(ByteOrderMark paramAnonymousByteOrderMark1, ByteOrderMark paramAnonymousByteOrderMark2)
    {
      int i = paramAnonymousByteOrderMark1.length();
      int j = paramAnonymousByteOrderMark2.length();
      int k;
      if (i > j) {
        k = -1;
      }
      for (;;)
      {
        return k;
        if (j > i) {
          k = 1;
        } else {
          k = 0;
        }
      }
    }
  };
  private final List<ByteOrderMark> boms;
  private ByteOrderMark byteOrderMark;
  private int fbIndex;
  private int fbLength;
  private int[] firstBytes;
  private final boolean include;
  private int markFbIndex;
  private boolean markedAtStart;
  
  public BOMInputStream(InputStream paramInputStream)
  {
    this(paramInputStream, false, arrayOfByteOrderMark);
  }
  
  public BOMInputStream(InputStream paramInputStream, boolean paramBoolean)
  {
    this(paramInputStream, paramBoolean, arrayOfByteOrderMark);
  }
  
  public BOMInputStream(InputStream paramInputStream, boolean paramBoolean, ByteOrderMark... paramVarArgs)
  {
    super(paramInputStream);
    if ((paramVarArgs == null) || (paramVarArgs.length == 0)) {
      throw new IllegalArgumentException("No BOMs specified");
    }
    this.include = paramBoolean;
    Arrays.sort(paramVarArgs, ByteOrderMarkLengthComparator);
    this.boms = Arrays.asList(paramVarArgs);
  }
  
  public BOMInputStream(InputStream paramInputStream, ByteOrderMark... paramVarArgs)
  {
    this(paramInputStream, false, paramVarArgs);
  }
  
  private ByteOrderMark find()
  {
    Iterator localIterator = this.boms.iterator();
    ByteOrderMark localByteOrderMark;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localByteOrderMark = (ByteOrderMark)localIterator.next();
    } while (!matches(localByteOrderMark));
    for (;;)
    {
      return localByteOrderMark;
      localByteOrderMark = null;
    }
  }
  
  private boolean matches(ByteOrderMark paramByteOrderMark)
  {
    int i = 0;
    if (i < paramByteOrderMark.length()) {
      if (paramByteOrderMark.get(i) == this.firstBytes[i]) {}
    }
    for (boolean bool = false;; bool = true)
    {
      return bool;
      i++;
      break;
    }
  }
  
  private int readFirstBytes()
    throws IOException
  {
    getBOM();
    int[] arrayOfInt;
    int j;
    if (this.fbIndex < this.fbLength)
    {
      arrayOfInt = this.firstBytes;
      j = this.fbIndex;
      this.fbIndex = (j + 1);
    }
    for (int i = arrayOfInt[j];; i = -1) {
      return i;
    }
  }
  
  public ByteOrderMark getBOM()
    throws IOException
  {
    int i;
    if (this.firstBytes == null)
    {
      this.fbLength = 0;
      this.firstBytes = new int[((ByteOrderMark)this.boms.get(0)).length()];
      i = 0;
      if (i < this.firstBytes.length)
      {
        this.firstBytes[i] = this.in.read();
        this.fbLength = (1 + this.fbLength);
        if (this.firstBytes[i] >= 0) {
          break label130;
        }
      }
      this.byteOrderMark = find();
      if ((this.byteOrderMark != null) && (!this.include))
      {
        if (this.byteOrderMark.length() >= this.firstBytes.length) {
          break label136;
        }
        this.fbIndex = this.byteOrderMark.length();
      }
    }
    for (;;)
    {
      return this.byteOrderMark;
      label130:
      i++;
      break;
      label136:
      this.fbLength = 0;
    }
  }
  
  public String getBOMCharsetName()
    throws IOException
  {
    getBOM();
    if (this.byteOrderMark == null) {}
    for (String str = null;; str = this.byteOrderMark.getCharsetName()) {
      return str;
    }
  }
  
  public boolean hasBOM()
    throws IOException
  {
    if (getBOM() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasBOM(ByteOrderMark paramByteOrderMark)
    throws IOException
  {
    if (!this.boms.contains(paramByteOrderMark)) {
      throw new IllegalArgumentException("Stream not configure to detect " + paramByteOrderMark);
    }
    if ((this.byteOrderMark != null) && (getBOM().equals(paramByteOrderMark))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public void mark(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield 104	org/apache/commons/io/input/BOMInputStream:fbIndex	I
    //   7: putfield 154	org/apache/commons/io/input/BOMInputStream:markFbIndex	I
    //   10: aload_0
    //   11: getfield 96	org/apache/commons/io/input/BOMInputStream:firstBytes	[I
    //   14: ifnonnull +21 -> 35
    //   17: iconst_1
    //   18: istore_3
    //   19: aload_0
    //   20: iload_3
    //   21: putfield 156	org/apache/commons/io/input/BOMInputStream:markedAtStart	Z
    //   24: aload_0
    //   25: getfield 113	org/apache/commons/io/input/BOMInputStream:in	Ljava/io/InputStream;
    //   28: iload_1
    //   29: invokevirtual 158	java/io/InputStream:mark	(I)V
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: iconst_0
    //   36: istore_3
    //   37: goto -18 -> 19
    //   40: astore_2
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_2
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	BOMInputStream
    //   0	45	1	paramInt	int
    //   40	4	2	localObject	Object
    //   18	19	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	32	40	finally
  }
  
  public int read()
    throws IOException
  {
    int i = readFirstBytes();
    if (i >= 0) {}
    for (;;)
    {
      return i;
      i = this.in.read();
    }
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    int i = 0;
    int j = 0;
    int k = paramInt1;
    while ((paramInt2 > 0) && (j >= 0))
    {
      j = readFirstBytes();
      if (j >= 0)
      {
        int n = k + 1;
        paramArrayOfByte[k] = ((byte)(j & 0xFF));
        paramInt2--;
        i++;
        k = n;
      }
    }
    int m = this.in.read(paramArrayOfByte, k, paramInt2);
    if (m < 0) {
      if (i <= 0) {}
    }
    for (;;)
    {
      return i;
      i = -1;
      continue;
      i += m;
    }
  }
  
  /**
   * @deprecated
   */
  public void reset()
    throws IOException
  {
    try
    {
      this.fbIndex = this.markFbIndex;
      if (this.markedAtStart) {
        this.firstBytes = null;
      }
      this.in.reset();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    while ((paramLong > 0L) && (readFirstBytes() >= 0)) {
      paramLong -= 1L;
    }
    return this.in.skip(paramLong);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/input/BOMInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */