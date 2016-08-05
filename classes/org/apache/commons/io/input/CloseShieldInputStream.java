package org.apache.commons.io.input;

import java.io.InputStream;

public class CloseShieldInputStream
  extends ProxyInputStream
{
  public CloseShieldInputStream(InputStream paramInputStream)
  {
    super(paramInputStream);
  }
  
  public void close()
  {
    this.in = new ClosedInputStream();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/input/CloseShieldInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */