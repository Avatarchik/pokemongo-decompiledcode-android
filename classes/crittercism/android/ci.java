package crittercism.android;

import java.io.OutputStream;
import org.json.JSONArray;

public abstract class ci
  extends bp
{
  public abstract JSONArray a();
  
  public final void a(OutputStream paramOutputStream)
  {
    String str = a().toString();
    new StringBuilder("BREADCRUMB WRITING ").append(str);
    dx.b();
    paramOutputStream.write(str.getBytes());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ci.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */