package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class DemuxInputStream
  extends InputStream
{
  private final InheritableThreadLocal<InputStream> m_streams = new InheritableThreadLocal();
  
  public InputStream bindStream(InputStream paramInputStream)
  {
    InputStream localInputStream = (InputStream)this.m_streams.get();
    this.m_streams.set(paramInputStream);
    return localInputStream;
  }
  
  public void close()
    throws IOException
  {
    InputStream localInputStream = (InputStream)this.m_streams.get();
    if (localInputStream != null) {
      localInputStream.close();
    }
  }
  
  public int read()
    throws IOException
  {
    InputStream localInputStream = (InputStream)this.m_streams.get();
    if (localInputStream != null) {}
    for (int i = localInputStream.read();; i = -1) {
      return i;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/input/DemuxInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */