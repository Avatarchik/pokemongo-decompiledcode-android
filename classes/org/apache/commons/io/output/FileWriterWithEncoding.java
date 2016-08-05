package org.apache.commons.io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FileWriterWithEncoding
  extends Writer
{
  private final Writer out;
  
  public FileWriterWithEncoding(File paramFile, String paramString)
    throws IOException
  {
    this(paramFile, paramString, false);
  }
  
  public FileWriterWithEncoding(File paramFile, String paramString, boolean paramBoolean)
    throws IOException
  {
    this.out = initWriter(paramFile, paramString, paramBoolean);
  }
  
  public FileWriterWithEncoding(File paramFile, Charset paramCharset)
    throws IOException
  {
    this(paramFile, paramCharset, false);
  }
  
  public FileWriterWithEncoding(File paramFile, Charset paramCharset, boolean paramBoolean)
    throws IOException
  {
    this.out = initWriter(paramFile, paramCharset, paramBoolean);
  }
  
  public FileWriterWithEncoding(File paramFile, CharsetEncoder paramCharsetEncoder)
    throws IOException
  {
    this(paramFile, paramCharsetEncoder, false);
  }
  
  public FileWriterWithEncoding(File paramFile, CharsetEncoder paramCharsetEncoder, boolean paramBoolean)
    throws IOException
  {
    this.out = initWriter(paramFile, paramCharsetEncoder, paramBoolean);
  }
  
  public FileWriterWithEncoding(String paramString1, String paramString2)
    throws IOException
  {
    this(new File(paramString1), paramString2, false);
  }
  
  public FileWriterWithEncoding(String paramString1, String paramString2, boolean paramBoolean)
    throws IOException
  {
    this(new File(paramString1), paramString2, paramBoolean);
  }
  
  public FileWriterWithEncoding(String paramString, Charset paramCharset)
    throws IOException
  {
    this(new File(paramString), paramCharset, false);
  }
  
  public FileWriterWithEncoding(String paramString, Charset paramCharset, boolean paramBoolean)
    throws IOException
  {
    this(new File(paramString), paramCharset, paramBoolean);
  }
  
  public FileWriterWithEncoding(String paramString, CharsetEncoder paramCharsetEncoder)
    throws IOException
  {
    this(new File(paramString), paramCharsetEncoder, false);
  }
  
  public FileWriterWithEncoding(String paramString, CharsetEncoder paramCharsetEncoder, boolean paramBoolean)
    throws IOException
  {
    this(new File(paramString), paramCharsetEncoder, paramBoolean);
  }
  
  private static Writer initWriter(File paramFile, Object paramObject, boolean paramBoolean)
    throws IOException
  {
    if (paramFile == null) {
      throw new NullPointerException("File is missing");
    }
    if (paramObject == null) {
      throw new NullPointerException("Encoding is missing");
    }
    boolean bool = paramFile.exists();
    localObject1 = null;
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(paramFile, paramBoolean);
      Object localObject2;
      OutputStreamWriter localOutputStreamWriter;
      return (Writer)localObject2;
    }
    catch (IOException localIOException1)
    {
      try
      {
        if ((paramObject instanceof Charset))
        {
          localObject2 = new OutputStreamWriter(localFileOutputStream, (Charset)paramObject);
        }
        else if ((paramObject instanceof CharsetEncoder))
        {
          localObject2 = new OutputStreamWriter(localFileOutputStream, (CharsetEncoder)paramObject);
        }
        else
        {
          localOutputStreamWriter = new OutputStreamWriter(localFileOutputStream, (String)paramObject);
          localObject2 = localOutputStreamWriter;
        }
      }
      catch (RuntimeException localRuntimeException2)
      {
        for (;;)
        {
          localObject1 = localFileOutputStream;
        }
      }
      catch (IOException localIOException2)
      {
        for (;;)
        {
          localObject1 = localFileOutputStream;
        }
      }
      localIOException1 = localIOException1;
      IOUtils.closeQuietly(null);
      IOUtils.closeQuietly((OutputStream)localObject1);
      if (!bool) {
        FileUtils.deleteQuietly(paramFile);
      }
      throw localIOException1;
    }
    catch (RuntimeException localRuntimeException1)
    {
      IOUtils.closeQuietly(null);
      IOUtils.closeQuietly((OutputStream)localObject1);
      if (!bool) {
        FileUtils.deleteQuietly(paramFile);
      }
      throw localRuntimeException1;
    }
  }
  
  public void close()
    throws IOException
  {
    this.out.close();
  }
  
  public void flush()
    throws IOException
  {
    this.out.flush();
  }
  
  public void write(int paramInt)
    throws IOException
  {
    this.out.write(paramInt);
  }
  
  public void write(String paramString)
    throws IOException
  {
    this.out.write(paramString);
  }
  
  public void write(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramString, paramInt1, paramInt2);
  }
  
  public void write(char[] paramArrayOfChar)
    throws IOException
  {
    this.out.write(paramArrayOfChar);
  }
  
  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    this.out.write(paramArrayOfChar, paramInt1, paramInt2);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/output/FileWriterWithEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */