package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzsg
{
  final int tag;
  final byte[] zzbiw;
  
  zzsg(int paramInt, byte[] paramArrayOfByte)
  {
    this.tag = paramInt;
    this.zzbiw = paramArrayOfByte;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof zzsg))
      {
        bool = false;
      }
      else
      {
        zzsg localzzsg = (zzsg)paramObject;
        if ((this.tag != localzzsg.tag) || (!Arrays.equals(this.zzbiw, localzzsg.zzbiw))) {
          bool = false;
        }
      }
    }
  }
  
  public int hashCode()
  {
    return 31 * (527 + this.tag) + Arrays.hashCode(this.zzbiw);
  }
  
  int zzB()
  {
    return 0 + zzrx.zzlO(this.tag) + this.zzbiw.length;
  }
  
  void zza(zzrx paramzzrx)
    throws IOException
  {
    paramzzrx.zzlN(this.tag);
    paramzzrx.zzF(this.zzbiw);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzsg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */