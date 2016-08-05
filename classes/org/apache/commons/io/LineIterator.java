package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LineIterator
  implements Iterator<String>
{
  private final BufferedReader bufferedReader;
  private String cachedLine;
  private boolean finished = false;
  
  public LineIterator(Reader paramReader)
    throws IllegalArgumentException
  {
    if (paramReader == null) {
      throw new IllegalArgumentException("Reader must not be null");
    }
    if ((paramReader instanceof BufferedReader)) {}
    for (this.bufferedReader = ((BufferedReader)paramReader);; this.bufferedReader = new BufferedReader(paramReader)) {
      return;
    }
  }
  
  public static void closeQuietly(LineIterator paramLineIterator)
  {
    if (paramLineIterator != null) {
      paramLineIterator.close();
    }
  }
  
  public void close()
  {
    this.finished = true;
    IOUtils.closeQuietly(this.bufferedReader);
    this.cachedLine = null;
  }
  
  public boolean hasNext()
  {
    boolean bool = true;
    if (this.cachedLine != null) {}
    for (;;)
    {
      return bool;
      if (this.finished)
      {
        bool = false;
        continue;
      }
      try
      {
        String str;
        do
        {
          str = this.bufferedReader.readLine();
          if (str == null)
          {
            this.finished = true;
            bool = false;
            break;
          }
        } while (!isValidLine(str));
        this.cachedLine = str;
      }
      catch (IOException localIOException)
      {
        close();
        throw new IllegalStateException(localIOException);
      }
    }
  }
  
  protected boolean isValidLine(String paramString)
  {
    return true;
  }
  
  public String next()
  {
    return nextLine();
  }
  
  public String nextLine()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("No more lines");
    }
    String str = this.cachedLine;
    this.cachedLine = null;
    return str;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Remove unsupported on LineIterator");
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/LineIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */