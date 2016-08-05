package com.google.android.gms.internal;

public class zzrv
{
  private final byte[] zzbhX = new byte['Ā'];
  private int zzbhY;
  private int zzbhZ;
  
  public zzrv(byte[] paramArrayOfByte)
  {
    for (int i = 0; i < 256; i++) {
      this.zzbhX[i] = ((byte)i);
    }
    int j = 0;
    for (int k = 0; k < 256; k++)
    {
      j = 0xFF & j + this.zzbhX[k] + paramArrayOfByte[(k % paramArrayOfByte.length)];
      int m = this.zzbhX[k];
      this.zzbhX[k] = this.zzbhX[j];
      this.zzbhX[j] = m;
    }
    this.zzbhY = 0;
    this.zzbhZ = 0;
  }
  
  public void zzA(byte[] paramArrayOfByte)
  {
    int i = this.zzbhY;
    int j = this.zzbhZ;
    for (int k = 0; k < paramArrayOfByte.length; k++)
    {
      i = 0xFF & i + 1;
      j = 0xFF & j + this.zzbhX[i];
      int m = this.zzbhX[i];
      this.zzbhX[i] = this.zzbhX[j];
      this.zzbhX[j] = m;
      paramArrayOfByte[k] = ((byte)(paramArrayOfByte[k] ^ this.zzbhX[(0xFF & this.zzbhX[i] + this.zzbhX[j])]));
    }
    this.zzbhY = i;
    this.zzbhZ = j;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzrv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */