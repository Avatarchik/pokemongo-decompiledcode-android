package crittercism.android;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class cd
  implements ch
{
  private JSONObject a;
  private JSONObject b;
  private JSONArray c;
  private JSONArray d;
  private File e;
  private String f;
  
  public cd(File paramFile, bs parambs1, bs parambs2, bs parambs3, bs parambs4)
  {
    paramFile.exists();
    this.f = cg.a.a();
    this.e = paramFile;
    this.a = new bu().a(new bx.c()).a(new bx.b()).a(new bx.d()).a(new bx.f()).a(new bx.o()).a(new bx.p()).a(new bx.j()).a(new bx.h()).a(new bx.z()).a(new bx.aa()).a(new bx.k()).a(new bx.r()).a(new bx.m()).a(new bx.s()).a(new bx.w()).a(new bx.x()).a();
    HashMap localHashMap = new HashMap();
    localHashMap.put("crashed_session", new bo(parambs1).a);
    if (parambs2.b() > 0) {
      localHashMap.put("previous_session", new bo(parambs2).a);
    }
    this.b = new JSONObject(localHashMap);
    this.c = new bo(parambs3).a;
    this.d = new bo(parambs4).a;
  }
  
  public final void a(OutputStream paramOutputStream)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("app_state", this.a);
    localHashMap1.put("breadcrumbs", this.b);
    localHashMap1.put("endpoints", this.c);
    localHashMap1.put("systemBreadcrumbs", this.d);
    Object localObject = new byte[0];
    byte[] arrayOfByte1 = new byte[' '];
    FileInputStream localFileInputStream = new FileInputStream(this.e);
    for (;;)
    {
      int i = localFileInputStream.read(arrayOfByte1);
      if (i == -1) {
        break;
      }
      byte[] arrayOfByte2 = new byte[i + localObject.length];
      System.arraycopy(localObject, 0, arrayOfByte2, 0, localObject.length);
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, localObject.length, i);
      localObject = arrayOfByte2;
    }
    localFileInputStream.close();
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("dmp_name", this.e.getName());
    localHashMap2.put("dmp_file", cr.a((byte[])localObject));
    localHashMap1.put("ndk_dmp_info", new JSONObject(localHashMap2));
    paramOutputStream.write(new JSONObject(localHashMap1).toString().getBytes());
  }
  
  public final String e()
  {
    return this.f;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/cd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */