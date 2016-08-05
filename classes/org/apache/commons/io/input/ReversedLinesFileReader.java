package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.apache.commons.io.Charsets;

public class ReversedLinesFileReader
  implements Closeable
{
  private final int avoidNewlineSplitBufferSize;
  private final int blockSize;
  private final int byteDecrement;
  private FilePart currentFilePart;
  private final Charset encoding;
  private final byte[][] newLineSequences;
  private final RandomAccessFile randomAccessFile;
  private final long totalBlockCount;
  private final long totalByteLength;
  private boolean trailingNewlineOfFileSkipped = false;
  
  public ReversedLinesFileReader(File paramFile)
    throws IOException
  {
    this(paramFile, 4096, Charset.defaultCharset().toString());
  }
  
  public ReversedLinesFileReader(File paramFile, int paramInt, String paramString)
    throws IOException
  {
    this(paramFile, paramInt, Charsets.toCharset(paramString));
  }
  
  public ReversedLinesFileReader(File paramFile, int paramInt, Charset paramCharset)
    throws IOException
  {
    this.blockSize = paramInt;
    this.encoding = paramCharset;
    this.randomAccessFile = new RandomAccessFile(paramFile, "r");
    this.totalByteLength = this.randomAccessFile.length();
    int i = (int)(this.totalByteLength % paramInt);
    Charset localCharset;
    if (i > 0)
    {
      this.totalBlockCount = (1L + this.totalByteLength / paramInt);
      this.currentFilePart = new FilePart(this.totalBlockCount, i, null, null);
      localCharset = Charsets.toCharset(paramCharset);
      if (localCharset.newEncoder().maxBytesPerChar() != 1.0F) {
        break label196;
      }
      this.byteDecrement = 1;
    }
    for (;;)
    {
      byte[][] arrayOfByte = new byte[3][];
      arrayOfByte[0] = "\r\n".getBytes(paramCharset);
      arrayOfByte[1] = "\n".getBytes(paramCharset);
      arrayOfByte[2] = "\r".getBytes(paramCharset);
      this.newLineSequences = arrayOfByte;
      this.avoidNewlineSplitBufferSize = this.newLineSequences[0].length;
      return;
      this.totalBlockCount = (this.totalByteLength / paramInt);
      if (this.totalByteLength <= 0L) {
        break;
      }
      i = paramInt;
      break;
      label196:
      if (localCharset == Charset.forName("UTF-8"))
      {
        this.byteDecrement = 1;
      }
      else if (localCharset == Charset.forName("Shift_JIS"))
      {
        this.byteDecrement = 1;
      }
      else
      {
        if ((localCharset != Charset.forName("UTF-16BE")) && (localCharset != Charset.forName("UTF-16LE"))) {
          break label260;
        }
        this.byteDecrement = 2;
      }
    }
    label260:
    if (localCharset == Charset.forName("UTF-16")) {
      throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
    }
    throw new UnsupportedEncodingException("Encoding " + paramCharset + " is not supported yet (feel free to submit a patch)");
  }
  
  public void close()
    throws IOException
  {
    this.randomAccessFile.close();
  }
  
  public String readLine()
    throws IOException
  {
    for (String str = this.currentFilePart.readLine(); str == null; str = this.currentFilePart.readLine())
    {
      this.currentFilePart = this.currentFilePart.rollOver();
      if (this.currentFilePart == null) {
        break;
      }
    }
    if (("".equals(str)) && (!this.trailingNewlineOfFileSkipped))
    {
      this.trailingNewlineOfFileSkipped = true;
      str = readLine();
    }
    return str;
  }
  
  private class FilePart
  {
    private int currentLastBytePos;
    private final byte[] data;
    private byte[] leftOver;
    private final long no;
    
    private FilePart(long paramLong, int paramInt, byte[] paramArrayOfByte)
      throws IOException
    {
      this.no = paramLong;
      if (paramArrayOfByte != null) {}
      for (int i = paramArrayOfByte.length;; i = 0)
      {
        this.data = new byte[paramInt + i];
        long l = (paramLong - 1L) * ReversedLinesFileReader.this.blockSize;
        if (paramLong <= 0L) {
          break;
        }
        ReversedLinesFileReader.this.randomAccessFile.seek(l);
        if (ReversedLinesFileReader.this.randomAccessFile.read(this.data, 0, paramInt) == paramInt) {
          break;
        }
        throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
      }
      if (paramArrayOfByte != null) {
        System.arraycopy(paramArrayOfByte, 0, this.data, paramInt, paramArrayOfByte.length);
      }
      this.currentLastBytePos = (-1 + this.data.length);
      this.leftOver = null;
    }
    
    private void createLeftOver()
    {
      int i = 1 + this.currentLastBytePos;
      if (i > 0)
      {
        this.leftOver = new byte[i];
        System.arraycopy(this.data, 0, this.leftOver, 0, i);
      }
      for (;;)
      {
        this.currentLastBytePos = -1;
        return;
        this.leftOver = null;
      }
    }
    
    private int getNewLineMatchByteCount(byte[] paramArrayOfByte, int paramInt)
    {
      int i = 0;
      byte[][] arrayOfByte = ReversedLinesFileReader.this.newLineSequences;
      int j = arrayOfByte.length;
      for (int k = 0;; k++) {
        if (k < j)
        {
          byte[] arrayOfByte1 = arrayOfByte[k];
          int m = 1;
          int n = -1 + arrayOfByte1.length;
          if (n >= 0)
          {
            int i1 = paramInt + n - (-1 + arrayOfByte1.length);
            if ((i1 >= 0) && (paramArrayOfByte[i1] == arrayOfByte1[n])) {}
            for (int i2 = 1;; i2 = 0)
            {
              m &= i2;
              n--;
              break;
            }
          }
          if (m != 0) {
            i = arrayOfByte1.length;
          }
        }
        else
        {
          return i;
        }
      }
    }
    
    private String readLine()
      throws IOException
    {
      String str = null;
      int i;
      int j;
      if (this.no == 1L)
      {
        i = 1;
        j = this.currentLastBytePos;
        label18:
        if (j > -1)
        {
          if ((i != 0) || (j >= ReversedLinesFileReader.this.avoidNewlineSplitBufferSize)) {
            break label85;
          }
          createLeftOver();
        }
      }
      for (;;)
      {
        if ((i != 0) && (this.leftOver != null))
        {
          str = new String(this.leftOver, ReversedLinesFileReader.this.encoding);
          this.leftOver = null;
        }
        return str;
        i = 0;
        break;
        label85:
        int k = getNewLineMatchByteCount(this.data, j);
        if (k > 0)
        {
          int m = j + 1;
          int n = 1 + (this.currentLastBytePos - m);
          if (n < 0) {
            throw new IllegalStateException("Unexpected negative line length=" + n);
          }
          byte[] arrayOfByte = new byte[n];
          System.arraycopy(this.data, m, arrayOfByte, 0, n);
          str = new String(arrayOfByte, ReversedLinesFileReader.this.encoding);
          this.currentLastBytePos = (j - k);
        }
        else
        {
          j -= ReversedLinesFileReader.this.byteDecrement;
          if (j >= 0) {
            break label18;
          }
          createLeftOver();
        }
      }
    }
    
    private FilePart rollOver()
      throws IOException
    {
      if (this.currentLastBytePos > -1) {
        throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
      }
      if (this.no > 1L) {}
      for (FilePart localFilePart = new FilePart(ReversedLinesFileReader.this, this.no - 1L, ReversedLinesFileReader.this.blockSize, this.leftOver);; localFilePart = null)
      {
        return localFilePart;
        if (this.leftOver != null) {
          throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this.encoding));
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/input/ReversedLinesFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */