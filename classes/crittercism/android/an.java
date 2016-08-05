package crittercism.android;

import org.apache.http.ParseException;
import org.apache.http.RequestLine;
import org.apache.http.message.BasicLineParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public final class an
  extends af
{
  public an(al paramal)
  {
    super(paramal);
  }
  
  public final boolean a(CharArrayBuffer paramCharArrayBuffer)
  {
    ParserCursor localParserCursor = new ParserCursor(0, paramCharArrayBuffer.length());
    boolean bool = true;
    try
    {
      RequestLine localRequestLine = BasicLineParser.DEFAULT.parseRequestLine(paramCharArrayBuffer, localParserCursor);
      this.a.a(localRequestLine.getMethod(), localRequestLine.getUri());
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
    return new am(this);
  }
  
  public final af c()
  {
    return as.d;
  }
  
  protected final int d()
  {
    return 64;
  }
  
  protected final int e()
  {
    return 2048;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/an.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */