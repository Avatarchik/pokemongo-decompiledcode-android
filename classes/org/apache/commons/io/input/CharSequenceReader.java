package org.apache.commons.io.input;

import java.io.Reader;
import java.io.Serializable;

public class CharSequenceReader
  extends Reader
  implements Serializable
{
  private final CharSequence charSequence;
  private int idx;
  private int mark;
  
  public CharSequenceReader(CharSequence paramCharSequence)
  {
    if (paramCharSequence != null) {}
    for (;;)
    {
      this.charSequence = paramCharSequence;
      return;
      paramCharSequence = "";
    }
  }
  
  public void close()
  {
    this.idx = 0;
    this.mark = 0;
  }
  
  public void mark(int paramInt)
  {
    this.mark = this.idx;
  }
  
  public boolean markSupported()
  {
    return true;
  }
  
  public int read()
  {
    if (this.idx >= this.charSequence.length()) {}
    CharSequence localCharSequence;
    int i;
    for (int j = -1;; j = localCharSequence.charAt(i))
    {
      return j;
      localCharSequence = this.charSequence;
      i = this.idx;
      this.idx = (i + 1);
    }
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i;
    if (this.idx >= this.charSequence.length()) {
      i = -1;
    }
    label140:
    for (;;)
    {
      return i;
      if (paramArrayOfChar == null) {
        throw new NullPointerException("Character array is missing");
      }
      if ((paramInt2 < 0) || (paramInt1 < 0) || (paramInt1 + paramInt2 > paramArrayOfChar.length)) {
        throw new IndexOutOfBoundsException("Array Size=" + paramArrayOfChar.length + ", offset=" + paramInt1 + ", length=" + paramInt2);
      }
      i = 0;
      for (int j = 0;; j++)
      {
        if (j >= paramInt2) {
          break label140;
        }
        int k = read();
        if (k == -1) {
          break;
        }
        paramArrayOfChar[(paramInt1 + j)] = ((char)k);
        i++;
      }
    }
  }
  
  public void reset()
  {
    this.idx = this.mark;
  }
  
  public long skip(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("Number of characters to skip is less than zero: " + paramLong);
    }
    if (this.idx >= this.charSequence.length()) {}
    int j;
    for (long l = -1L;; l = j)
    {
      return l;
      int i = (int)Math.min(this.charSequence.length(), paramLong + this.idx);
      j = i - this.idx;
      this.idx = i;
    }
  }
  
  public String toString()
  {
    return this.charSequence.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/input/CharSequenceReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */