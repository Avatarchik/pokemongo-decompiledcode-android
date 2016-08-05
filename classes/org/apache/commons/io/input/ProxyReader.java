package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public abstract class ProxyReader
  extends FilterReader
{
  public ProxyReader(Reader paramReader)
  {
    super(paramReader);
  }
  
  protected void afterRead(int paramInt)
    throws IOException
  {}
  
  protected void beforeRead(int paramInt)
    throws IOException
  {}
  
  public void close()
    throws IOException
  {
    try
    {
      this.in.close();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
      }
    }
  }
  
  protected void handleIOException(IOException paramIOException)
    throws IOException
  {
    throw paramIOException;
  }
  
  /**
   * @deprecated
   */
  public void mark(int paramInt)
    throws IOException
  {
    try
    {
      this.in.mark(paramInt);
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
      }
    }
    finally {}
  }
  
  public boolean markSupported()
  {
    return this.in.markSupported();
  }
  
  public int read()
    throws IOException
  {
    int i = 1;
    try
    {
      beforeRead(1);
      j = this.in.read();
      if (j != -1) {}
      for (;;)
      {
        afterRead(i);
        return j;
        i = -1;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
        int j = -1;
      }
    }
  }
  
  public int read(CharBuffer paramCharBuffer)
    throws IOException
  {
    if (paramCharBuffer != null) {}
    try
    {
      for (int i = paramCharBuffer.length();; i = 0)
      {
        beforeRead(i);
        j = this.in.read(paramCharBuffer);
        afterRead(j);
        return j;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
        int j = -1;
      }
    }
  }
  
  public int read(char[] paramArrayOfChar)
    throws IOException
  {
    if (paramArrayOfChar != null) {}
    try
    {
      for (int i = paramArrayOfChar.length;; i = 0)
      {
        beforeRead(i);
        j = this.in.read(paramArrayOfChar);
        afterRead(j);
        return j;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
        int j = -1;
      }
    }
  }
  
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      beforeRead(paramInt2);
      i = this.in.read(paramArrayOfChar, paramInt1, paramInt2);
      afterRead(i);
      return i;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
        int i = -1;
      }
    }
  }
  
  public boolean ready()
    throws IOException
  {
    try
    {
      boolean bool2 = this.in.ready();
      bool1 = bool2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  /**
   * @deprecated
   */
  public void reset()
    throws IOException
  {
    try
    {
      this.in.reset();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
      }
    }
    finally {}
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    try
    {
      long l2 = this.in.skip(paramLong);
      l1 = l2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        handleIOException(localIOException);
        long l1 = 0L;
      }
    }
    return l1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/input/ProxyReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */