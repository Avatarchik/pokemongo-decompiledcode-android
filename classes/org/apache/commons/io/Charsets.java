package org.apache.commons.io;

import java.nio.charset.Charset;

public class Charsets
{
  public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
  public static final Charset US_ASCII = Charset.forName("US-ASCII");
  public static final Charset UTF_16 = Charset.forName("UTF-16");
  public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
  public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
  public static final Charset UTF_8 = Charset.forName("UTF-8");
  
  public static Charset toCharset(String paramString)
  {
    if (paramString == null) {}
    for (Charset localCharset = Charset.defaultCharset();; localCharset = Charset.forName(paramString)) {
      return localCharset;
    }
  }
  
  public static Charset toCharset(Charset paramCharset)
  {
    if (paramCharset == null) {
      paramCharset = Charset.defaultCharset();
    }
    return paramCharset;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/Charsets.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */