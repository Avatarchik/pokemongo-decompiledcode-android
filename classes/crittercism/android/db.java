package crittercism.android;

import java.net.MalformedURLException;
import java.net.URL;

public final class db
{
  private String a;
  private String b;
  
  public db(String paramString1, String paramString2)
  {
    paramString1.endsWith("/");
    paramString2.startsWith("/");
    this.a = paramString1;
    this.b = paramString2;
  }
  
  public final URL a()
  {
    try
    {
      localURL = new URL(this.a + this.b);
      return localURL;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      for (;;)
      {
        new StringBuilder("Invalid url: ").append(this.a).append(this.b);
        URL localURL = null;
        dx.b();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/db.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */