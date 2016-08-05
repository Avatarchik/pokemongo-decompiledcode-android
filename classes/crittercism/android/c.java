package crittercism.android;

import android.location.Location;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import org.json.JSONArray;

public final class c
  extends bp
{
  public long a = Long.MAX_VALUE;
  public boolean b = false;
  a c = a.a;
  public long d = 0L;
  public int e = 0;
  public String f = "";
  public cn g = new cn(null);
  public k h = new k();
  public String i;
  public b j = b.a;
  private long k = Long.MAX_VALUE;
  private boolean l = false;
  private boolean m = false;
  private String n = cg.a.a();
  private long o = 0L;
  private boolean p = false;
  private boolean q = false;
  private double[] r;
  
  public c() {}
  
  public c(String paramString)
  {
    if (paramString != null) {
      this.i = paramString;
    }
  }
  
  public c(URL paramURL)
  {
    if (paramURL != null) {
      this.i = paramURL.toExternalForm();
    }
  }
  
  private long g()
  {
    long l1 = Long.MAX_VALUE;
    if ((this.a != l1) && (this.k != l1)) {
      l1 = this.k - this.a;
    }
    return l1;
  }
  
  public final String a()
  {
    boolean bool = true;
    Object localObject1 = this.i;
    k localk;
    if (localObject1 == null)
    {
      localk = this.h;
      if (localk.b == null) {
        break label101;
      }
      localObject1 = localk.b;
    }
    for (;;)
    {
      if (localk.f)
      {
        int i1 = localk.e;
        if (i1 > 0)
        {
          String str5 = ":" + i1;
          if (!((String)localObject1).endsWith(str5)) {
            localObject1 = (String)localObject1 + str5;
          }
        }
        this.i = ((String)localObject1);
        return (String)localObject1;
        label101:
        if (localk.a != null) {
          localObject1 = localk.a.getHostName();
        }
      }
      else
      {
        String str1 = localk.c;
        if ((str1 != null) && ((str1.regionMatches(bool, 0, "http:", 0, 5)) || (str1.regionMatches(bool, 0, "https:", 0, 6)))) {}
        for (;;)
        {
          if (!bool) {
            break label174;
          }
          localObject1 = str1;
          break;
          bool = false;
        }
        label174:
        if (localk.d != null) {}
        for (String str2 = "" + k.a.a(localk.d) + ":";; str2 = "")
        {
          if (str1.startsWith("//"))
          {
            localObject1 = str2 + str1;
            break;
          }
          String str3 = str2 + "//";
          if (str1.startsWith((String)localObject1))
          {
            localObject1 = str3 + str1;
            break;
          }
          Object localObject2 = "";
          if ((localk.e > 0) && ((localk.d == null) || (k.a.b(localk.d) != localk.e)))
          {
            String str4 = ":" + localk.e;
            if (!((String)localObject1).endsWith(str4)) {
              localObject2 = str4;
            }
          }
          localObject1 = str3 + (String)localObject1 + (String)localObject2 + str1;
          break;
        }
      }
      localObject1 = "unknown-host";
    }
  }
  
  public final void a(int paramInt)
  {
    k localk = this.h;
    if (paramInt > 0) {
      localk.e = paramInt;
    }
  }
  
  public final void a(long paramLong)
  {
    if (!this.p) {
      this.o = (paramLong + this.o);
    }
  }
  
  public final void a(Location paramLocation)
  {
    double[] arrayOfDouble = new double[2];
    arrayOfDouble[0] = paramLocation.getLatitude();
    arrayOfDouble[1] = paramLocation.getLongitude();
    this.r = arrayOfDouble;
  }
  
  public final void a(k.a parama)
  {
    this.h.d = parama;
  }
  
  public final void a(OutputStream paramOutputStream)
  {
    paramOutputStream.write(d().toString().getBytes());
  }
  
  public final void a(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    this.i = paramString;
  }
  
  public final void a(Throwable paramThrowable)
  {
    this.g = new cn(paramThrowable);
  }
  
  public final void a(InetAddress paramInetAddress)
  {
    this.i = null;
    this.h.a = paramInetAddress;
  }
  
  public final void b()
  {
    if ((!this.l) && (this.a == Long.MAX_VALUE)) {
      this.a = System.currentTimeMillis();
    }
  }
  
  public final void b(long paramLong)
  {
    this.p = true;
    this.o = paramLong;
  }
  
  public final void b(String paramString)
  {
    this.i = null;
    this.h.b = paramString;
  }
  
  public final void c()
  {
    if ((!this.m) && (this.k == Long.MAX_VALUE)) {
      this.k = System.currentTimeMillis();
    }
  }
  
  public final void c(long paramLong)
  {
    if (!this.q) {
      this.d = (paramLong + this.d);
    }
  }
  
  public final JSONArray d()
  {
    JSONArray localJSONArray1 = new JSONArray();
    try
    {
      localJSONArray1.put(this.f);
      localJSONArray1.put(a());
      localJSONArray1.put(ed.a.a(new Date(this.a)));
      localJSONArray1.put(g());
      localJSONArray1.put(this.j.a());
      localJSONArray1.put(this.o);
      localJSONArray1.put(this.d);
      localJSONArray1.put(this.e);
      localJSONArray1.put(this.g.a);
      localJSONArray1.put(this.g.b);
      if (this.r != null)
      {
        JSONArray localJSONArray2 = new JSONArray();
        localJSONArray2.put(this.r[0]);
        localJSONArray2.put(this.r[1]);
        localJSONArray1.put(localJSONArray2);
      }
      return localJSONArray1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        System.out.println("Failed to create statsArray");
        localJSONArray1 = null;
        localException.printStackTrace();
      }
    }
  }
  
  public final void d(long paramLong)
  {
    this.q = true;
    this.d = paramLong;
  }
  
  public final String e()
  {
    return this.n;
  }
  
  public final void e(long paramLong)
  {
    this.a = paramLong;
    this.l = true;
  }
  
  public final void f()
  {
    this.h.f = true;
  }
  
  public final void f(long paramLong)
  {
    this.k = paramLong;
    this.m = true;
  }
  
  public final String toString()
  {
    String str1 = "" + "URI            : " + this.i + "\n";
    String str2 = str1 + "URI Builder    : " + this.h.toString() + "\n";
    String str3 = str2 + "\n";
    String str4 = str3 + "Logged by      : " + this.c.toString() + "\n";
    String str5 = str4 + "Error type:         : " + this.g.a + "\n";
    String str6 = str5 + "Error code:         : " + this.g.b + "\n";
    String str7 = str6 + "\n";
    String str8 = str7 + "Response time  : " + g() + "\n";
    String str9 = str8 + "Start time     : " + this.a + "\n";
    String str10 = str9 + "End time       : " + this.k + "\n";
    String str11 = str10 + "\n";
    String str12 = str11 + "Bytes out    : " + this.d + "\n";
    String str13 = str12 + "Bytes in     : " + this.o + "\n";
    String str14 = str13 + "\n";
    String str15 = str14 + "Response code  : " + this.e + "\n";
    String str16 = str15 + "Request method : " + this.f + "\n";
    if (this.r != null) {
      str16 = str16 + "Location       : " + Arrays.toString(this.r) + "\n";
    }
    return str16;
  }
  
  public static enum a
  {
    private String m;
    
    static
    {
      a[] arrayOfa = new a[12];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
      arrayOfa[3] = d;
      arrayOfa[4] = e;
      arrayOfa[5] = f;
      arrayOfa[6] = g;
      arrayOfa[7] = h;
      arrayOfa[8] = i;
      arrayOfa[9] = j;
      arrayOfa[10] = k;
      arrayOfa[11] = l;
      n = arrayOfa;
    }
    
    private a(String paramString1)
    {
      this.m = paramString1;
    }
    
    public final String toString()
    {
      return this.m;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */