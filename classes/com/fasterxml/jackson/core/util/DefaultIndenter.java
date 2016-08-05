package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;

public class DefaultIndenter
  extends DefaultPrettyPrinter.NopIndenter
{
  private static final int INDENT_LEVELS = 16;
  public static final DefaultIndenter SYSTEM_LINEFEED_INSTANCE = new DefaultIndenter("  ", SYS_LF);
  public static final String SYS_LF;
  private static final long serialVersionUID = 1L;
  private final int charsPerLevel;
  private final String eol;
  private final char[] indents;
  
  static
  {
    try
    {
      String str2 = System.getProperty("line.separator");
      str1 = str2;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        String str1 = "\n";
      }
    }
    SYS_LF = str1;
  }
  
  public DefaultIndenter()
  {
    this("  ", SYS_LF);
  }
  
  public DefaultIndenter(String paramString1, String paramString2)
  {
    this.charsPerLevel = paramString1.length();
    this.indents = new char[16 * paramString1.length()];
    int i = 0;
    for (int j = 0; j < 16; j++)
    {
      paramString1.getChars(0, paramString1.length(), this.indents, i);
      i += paramString1.length();
    }
    this.eol = paramString2;
  }
  
  public String getEol()
  {
    return this.eol;
  }
  
  public String getIndent()
  {
    return new String(this.indents, 0, this.charsPerLevel);
  }
  
  public boolean isInline()
  {
    return false;
  }
  
  public DefaultIndenter withIndent(String paramString)
  {
    if (paramString.equals(getIndent())) {}
    for (;;)
    {
      return this;
      this = new DefaultIndenter(paramString, this.eol);
    }
  }
  
  public DefaultIndenter withLinefeed(String paramString)
  {
    if (paramString.equals(this.eol)) {}
    for (;;)
    {
      return this;
      this = new DefaultIndenter(getIndent(), paramString);
    }
  }
  
  public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException
  {
    paramJsonGenerator.writeRaw(this.eol);
    if (paramInt > 0)
    {
      int i = paramInt * this.charsPerLevel;
      while (i > this.indents.length)
      {
        paramJsonGenerator.writeRaw(this.indents, 0, this.indents.length);
        i -= this.indents.length;
      }
      paramJsonGenerator.writeRaw(this.indents, 0, i);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/util/DefaultIndenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */