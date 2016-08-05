package crittercism.android;

import java.io.OutputStream;
import org.json.JSONArray;

public final class cf
  extends bp
{
  public static final cf a = new cf("session_start", a.a);
  private String b;
  private String c;
  private String d = cg.a.a();
  private a e;
  
  public cf(String paramString, a parama)
  {
    this(paramString, ed.a.a(), parama);
  }
  
  private cf(String paramString1, String paramString2, a parama)
  {
    if (paramString1.length() > 140) {
      paramString1 = paramString1.substring(0, 140);
    }
    this.b = paramString1;
    this.c = paramString2;
    this.e = parama;
  }
  
  public final void a(OutputStream paramOutputStream)
  {
    JSONArray localJSONArray = new JSONArray();
    localJSONArray.put(this.b);
    localJSONArray.put(this.c);
    String str = localJSONArray.toString();
    new StringBuilder("BREADCRUMB WRITING ").append(str);
    dx.b();
    paramOutputStream.write(str.getBytes());
  }
  
  public final String e()
  {
    return this.d;
  }
  
  public static enum a
  {
    static
    {
      a[] arrayOfa = new a[2];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      c = arrayOfa;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/cf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */