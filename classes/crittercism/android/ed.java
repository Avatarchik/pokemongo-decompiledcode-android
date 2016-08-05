package crittercism.android;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class ed
{
  public static final ed a = new ed();
  private ee b = new a((byte)0);
  private ThreadLocal c = new ThreadLocal();
  
  private SimpleDateFormat b()
  {
    SimpleDateFormat localSimpleDateFormat = (SimpleDateFormat)this.c.get();
    if (localSimpleDateFormat == null)
    {
      localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
      localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      localSimpleDateFormat.setLenient(false);
      this.c.set(localSimpleDateFormat);
    }
    return localSimpleDateFormat;
  }
  
  public final long a(String paramString)
  {
    return b().parse(paramString).getTime();
  }
  
  public final String a()
  {
    return a(this.b.a());
  }
  
  public final String a(Date paramDate)
  {
    return b().format(paramDate);
  }
  
  class a
    implements ee
  {
    private a() {}
    
    public final Date a()
    {
      return new Date();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */