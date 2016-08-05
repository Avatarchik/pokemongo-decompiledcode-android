package crittercism.android;

import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public final class ap
  extends af
{
  private int d = -1;
  
  public ap(al paramal)
  {
    super(paramal);
  }
  
  public final boolean a(CharArrayBuffer paramCharArrayBuffer)
  {
    ParserCursor localParserCursor = new ParserCursor(0, paramCharArrayBuffer.length());
    boolean bool = true;
    try
    {
      StatusLine localStatusLine = BasicLineParser.DEFAULT.parseStatusLine(paramCharArrayBuffer, localParserCursor);
      this.d = localStatusLine.getStatusCode();
      this.a.a(localStatusLine.getStatusCode());
      return bool;
    }
    catch (ParseException localParseException)
    {
      for (;;)
      {
        bool = false;
      }
    }
  }
  
  public final af b()
  {
    return new ao(this, this.d);
  }
  
  public final af c()
  {
    return as.d;
  }
  
  protected final int d()
  {
    return 20;
  }
  
  protected final int e()
  {
    return 64;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */