package org.apache.commons.io;

import java.io.Serializable;

public class ByteOrderMark
  implements Serializable
{
  public static final ByteOrderMark UTF_16BE;
  public static final ByteOrderMark UTF_16LE;
  public static final ByteOrderMark UTF_32BE;
  public static final ByteOrderMark UTF_32LE;
  public static final ByteOrderMark UTF_8;
  private static final long serialVersionUID = 1L;
  private final int[] bytes;
  private final String charsetName;
  
  static
  {
    int[] arrayOfInt1 = new int[3];
    arrayOfInt1[0] = 239;
    arrayOfInt1[1] = 187;
    arrayOfInt1[2] = 191;
    UTF_8 = new ByteOrderMark("UTF-8", arrayOfInt1);
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = 254;
    arrayOfInt2[1] = 255;
    UTF_16BE = new ByteOrderMark("UTF-16BE", arrayOfInt2);
    int[] arrayOfInt3 = new int[2];
    arrayOfInt3[0] = 255;
    arrayOfInt3[1] = 254;
    UTF_16LE = new ByteOrderMark("UTF-16LE", arrayOfInt3);
    int[] arrayOfInt4 = new int[4];
    arrayOfInt4[0] = 0;
    arrayOfInt4[1] = 0;
    arrayOfInt4[2] = 254;
    arrayOfInt4[3] = 255;
    UTF_32BE = new ByteOrderMark("UTF-32BE", arrayOfInt4);
    int[] arrayOfInt5 = new int[4];
    arrayOfInt5[0] = 255;
    arrayOfInt5[1] = 254;
    arrayOfInt5[2] = 0;
    arrayOfInt5[3] = 0;
    UTF_32LE = new ByteOrderMark("UTF-32LE", arrayOfInt5);
  }
  
  public ByteOrderMark(String paramString, int... paramVarArgs)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      throw new IllegalArgumentException("No charsetName specified");
    }
    if ((paramVarArgs == null) || (paramVarArgs.length == 0)) {
      throw new IllegalArgumentException("No bytes specified");
    }
    this.charsetName = paramString;
    this.bytes = new int[paramVarArgs.length];
    System.arraycopy(paramVarArgs, 0, this.bytes, 0, paramVarArgs.length);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof ByteOrderMark)) {}
    for (;;)
    {
      return bool;
      ByteOrderMark localByteOrderMark = (ByteOrderMark)paramObject;
      if (this.bytes.length == localByteOrderMark.length())
      {
        for (int i = 0;; i++)
        {
          if (i >= this.bytes.length) {
            break label63;
          }
          if (this.bytes[i] != localByteOrderMark.get(i)) {
            break;
          }
        }
        label63:
        bool = true;
      }
    }
  }
  
  public int get(int paramInt)
  {
    return this.bytes[paramInt];
  }
  
  public byte[] getBytes()
  {
    byte[] arrayOfByte = new byte[this.bytes.length];
    for (int i = 0; i < this.bytes.length; i++) {
      arrayOfByte[i] = ((byte)this.bytes[i]);
    }
    return arrayOfByte;
  }
  
  public String getCharsetName()
  {
    return this.charsetName;
  }
  
  public int hashCode()
  {
    int i = getClass().hashCode();
    int[] arrayOfInt = this.bytes;
    int j = arrayOfInt.length;
    for (int k = 0; k < j; k++) {
      i += arrayOfInt[k];
    }
    return i;
  }
  
  public int length()
  {
    return this.bytes.length;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append('[');
    localStringBuilder.append(this.charsetName);
    localStringBuilder.append(": ");
    for (int i = 0; i < this.bytes.length; i++)
    {
      if (i > 0) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append("0x");
      localStringBuilder.append(Integer.toHexString(0xFF & this.bytes[i]).toUpperCase());
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/ByteOrderMark.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */