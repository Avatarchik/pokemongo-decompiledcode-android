package crittercism.android;

import org.apache.http.util.CharArrayBuffer;

public final class ai
  extends af
{
  private int d = -1;
  
  public ai(af paramaf)
  {
    super(paramaf);
  }
  
  public final boolean a(CharArrayBuffer paramCharArrayBuffer)
  {
    boolean bool = false;
    int i = paramCharArrayBuffer.indexOf(59);
    int j = paramCharArrayBuffer.length();
    if (i > 0) {}
    for (;;)
    {
      try
      {
        this.d = Integer.parseInt(paramCharArrayBuffer.substringTrimmed(0, i), 16);
        bool = true;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        continue;
      }
      return bool;
      i = j;
    }
  }
  
  public final af b()
  {
    if (this.d == 0) {}
    for (Object localObject = new aq(this);; localObject = new ah(this, this.d))
    {
      return (af)localObject;
      this.b.clear();
    }
  }
  
  public final af c()
  {
    return as.d;
  }
  
  protected final int d()
  {
    return 16;
  }
  
  protected final int e()
  {
    return 256;
  }
  
  public final void f()
  {
    this.a.b(a());
    this.a.a(as.d);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */