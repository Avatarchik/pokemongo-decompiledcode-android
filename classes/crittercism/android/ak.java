package crittercism.android;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.message.BasicLineParser;
import org.apache.http.util.CharArrayBuffer;

public abstract class ak
  extends af
{
  boolean d = false;
  int e;
  boolean f = false;
  private boolean g = false;
  private boolean h = false;
  
  public ak(af paramaf)
  {
    super(paramaf);
  }
  
  public final boolean a(CharArrayBuffer paramCharArrayBuffer)
  {
    int i = 1;
    int j = this.b.length();
    if ((j == 0) || ((j == i) && (this.b.charAt(0) == '\r')))
    {
      int k = i;
      if (k == 0) {
        break label53;
      }
      this.h = i;
    }
    for (;;)
    {
      return i;
      int m = 0;
      break;
      try
      {
        label53:
        Header localHeader = BasicLineParser.DEFAULT.parseHeader(paramCharArrayBuffer);
        if ((!this.d) && (localHeader.getName().equalsIgnoreCase("content-length")))
        {
          int n = Integer.parseInt(localHeader.getValue());
          if (n < 0)
          {
            i = 0;
          }
          else
          {
            this.d = true;
            this.e = n;
          }
        }
        else if (localHeader.getName().equalsIgnoreCase("transfer-encoding"))
        {
          this.f = localHeader.getValue().equalsIgnoreCase("chunked");
        }
        else if ((!this.g) && (localHeader.getName().equalsIgnoreCase("host")))
        {
          String str = localHeader.getValue();
          if (str != null)
          {
            this.g = true;
            this.a.a(str);
          }
        }
      }
      catch (ParseException localParseException)
      {
        i = 0;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        i = 0;
      }
    }
  }
  
  public final af b()
  {
    if (this.h) {
      this = g();
    }
    for (;;)
    {
      return this;
      this.b.clear();
    }
  }
  
  public final af c()
  {
    this.b.clear();
    return new ar(this);
  }
  
  protected final int d()
  {
    return 32;
  }
  
  protected final int e()
  {
    return 128;
  }
  
  protected abstract af g();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */