package com.google.android.gms.common.images;

public final class Size
{
  private final int zznQ;
  private final int zznR;
  
  public Size(int paramInt1, int paramInt2)
  {
    this.zznQ = paramInt1;
    this.zznR = paramInt2;
  }
  
  public static Size parseSize(String paramString)
    throws NumberFormatException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("string must not be null");
    }
    int i = paramString.indexOf('*');
    if (i < 0) {
      i = paramString.indexOf('x');
    }
    if (i < 0) {
      throw zzch(paramString);
    }
    try
    {
      Size localSize = new Size(Integer.parseInt(paramString.substring(0, i)), Integer.parseInt(paramString.substring(i + 1)));
      return localSize;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw zzch(paramString);
    }
  }
  
  private static NumberFormatException zzch(String paramString)
  {
    throw new NumberFormatException("Invalid Size: \"" + paramString + "\"");
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramObject == null) {}
    do
    {
      for (;;)
      {
        return bool2;
        if (this != paramObject) {
          break;
        }
        bool2 = bool1;
      }
    } while (!(paramObject instanceof Size));
    Size localSize = (Size)paramObject;
    if ((this.zznQ == localSize.zznQ) && (this.zznR == localSize.zznR)) {}
    for (;;)
    {
      bool2 = bool1;
      break;
      bool1 = false;
    }
  }
  
  public int getHeight()
  {
    return this.zznR;
  }
  
  public int getWidth()
  {
    return this.zznQ;
  }
  
  public int hashCode()
  {
    return this.zznR ^ (this.zznQ << 16 | this.zznQ >>> 16);
  }
  
  public String toString()
  {
    return this.zznQ + "x" + this.zznR;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/images/Size.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */