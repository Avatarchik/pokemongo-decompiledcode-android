package crittercism.android;

import android.util.SparseArray;

public enum b
{
  private static SparseArray e;
  private int f;
  
  static
  {
    b[] arrayOfb = new b[4];
    arrayOfb[0] = a;
    arrayOfb[1] = b;
    arrayOfb[2] = c;
    arrayOfb[3] = d;
    g = arrayOfb;
    SparseArray localSparseArray = new SparseArray();
    e = localSparseArray;
    localSparseArray.put(0, a);
    e.put(1, b);
  }
  
  private b(int paramInt1)
  {
    this.f = paramInt1;
  }
  
  public static b a(int paramInt)
  {
    b localb = (b)e.get(paramInt);
    if (localb == null) {
      localb = c;
    }
    return localb;
  }
  
  public final int a()
  {
    return this.f;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */