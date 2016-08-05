package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public abstract class zzak
  implements zzaj
{
  protected MotionEvent zzmU;
  protected DisplayMetrics zzmV;
  protected zzap zzmW;
  private zzaq zzmX;
  
  protected zzak(Context paramContext, zzap paramzzap, zzaq paramzzaq)
  {
    this.zzmW = paramzzap;
    this.zzmX = paramzzaq;
    try
    {
      this.zzmV = paramContext.getResources().getDisplayMetrics();
      return;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      for (;;)
      {
        this.zzmV = new DisplayMetrics();
        this.zzmV.density = 1.0F;
      }
    }
  }
  
  private void zzS()
  {
    this.zzmX.reset();
  }
  
  private byte[] zzT()
    throws IOException
  {
    return this.zzmX.zzac();
  }
  
  /* Error */
  private String zza(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 67	com/google/android/gms/internal/zzak:zzS	()V
    //   6: iload_3
    //   7: ifeq +35 -> 42
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual 71	com/google/android/gms/internal/zzak:zzd	(Landroid/content/Context;)V
    //   15: aload_0
    //   16: invokespecial 73	com/google/android/gms/internal/zzak:zzT	()[B
    //   19: astore 9
    //   21: aload_0
    //   22: monitorexit
    //   23: aload 9
    //   25: arraylength
    //   26: ifne +43 -> 69
    //   29: iconst_5
    //   30: invokestatic 79	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   33: astore 11
    //   35: aload 11
    //   37: astore 5
    //   39: aload 5
    //   41: areturn
    //   42: aload_0
    //   43: aload_1
    //   44: invokevirtual 82	com/google/android/gms/internal/zzak:zzc	(Landroid/content/Context;)V
    //   47: goto -32 -> 15
    //   50: astore 8
    //   52: aload_0
    //   53: monitorexit
    //   54: aload 8
    //   56: athrow
    //   57: astore 7
    //   59: bipush 7
    //   61: invokestatic 79	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   64: astore 5
    //   66: goto -27 -> 39
    //   69: aload_0
    //   70: aload 9
    //   72: aload_2
    //   73: invokevirtual 85	com/google/android/gms/internal/zzak:zza	([BLjava/lang/String;)Ljava/lang/String;
    //   76: astore 10
    //   78: aload 10
    //   80: astore 5
    //   82: goto -43 -> 39
    //   85: astore 6
    //   87: bipush 7
    //   89: invokestatic 79	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   92: astore 5
    //   94: goto -55 -> 39
    //   97: astore 4
    //   99: iconst_3
    //   100: invokestatic 79	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   103: astore 5
    //   105: goto -66 -> 39
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	zzak
    //   0	108	1	paramContext	Context
    //   0	108	2	paramString	String
    //   0	108	3	paramBoolean	boolean
    //   97	1	4	localIOException	IOException
    //   37	67	5	localObject1	Object
    //   85	1	6	localUnsupportedEncodingException	UnsupportedEncodingException
    //   57	1	7	localNoSuchAlgorithmException	NoSuchAlgorithmException
    //   50	5	8	localObject2	Object
    //   19	52	9	arrayOfByte	byte[]
    //   76	3	10	str1	String
    //   33	3	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   2	23	50	finally
    //   42	54	50	finally
    //   0	2	57	java/security/NoSuchAlgorithmException
    //   23	35	57	java/security/NoSuchAlgorithmException
    //   54	57	57	java/security/NoSuchAlgorithmException
    //   69	78	57	java/security/NoSuchAlgorithmException
    //   0	2	85	java/io/UnsupportedEncodingException
    //   23	35	85	java/io/UnsupportedEncodingException
    //   54	57	85	java/io/UnsupportedEncodingException
    //   69	78	85	java/io/UnsupportedEncodingException
    //   0	2	97	java/io/IOException
    //   23	35	97	java/io/IOException
    //   54	57	97	java/io/IOException
    //   69	78	97	java/io/IOException
  }
  
  String zza(byte[] paramArrayOfByte, String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException
  {
    if (paramArrayOfByte.length > 239)
    {
      zzS();
      zza(20, 1L);
      paramArrayOfByte = zzT();
    }
    byte[] arrayOfByte5;
    if (paramArrayOfByte.length < 239)
    {
      arrayOfByte5 = new byte[239 - paramArrayOfByte.length];
      new SecureRandom().nextBytes(arrayOfByte5);
    }
    for (byte[] arrayOfByte1 = ByteBuffer.allocate(240).put((byte)paramArrayOfByte.length).put(paramArrayOfByte).put(arrayOfByte5).array();; arrayOfByte1 = ByteBuffer.allocate(240).put((byte)paramArrayOfByte.length).put(paramArrayOfByte).array())
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      byte[] arrayOfByte3 = ByteBuffer.allocate(256).put(arrayOfByte2).put(arrayOfByte1).array();
      byte[] arrayOfByte4 = new byte['Ā'];
      new zzai().zzb(arrayOfByte3, arrayOfByte4);
      if ((paramString != null) && (paramString.length() > 0)) {
        zza(paramString, arrayOfByte4);
      }
      return this.zzmW.zza(arrayOfByte4, true);
    }
  }
  
  public void zza(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.zzmU != null) {
      this.zzmU.recycle();
    }
    this.zzmU = MotionEvent.obtain(0L, paramInt3, 1, paramInt1 * this.zzmV.density, paramInt2 * this.zzmV.density, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
  }
  
  protected void zza(int paramInt, long paramLong)
    throws IOException
  {
    this.zzmX.zzb(paramInt, paramLong);
  }
  
  protected void zza(int paramInt, String paramString)
    throws IOException
  {
    this.zzmX.zzb(paramInt, paramString);
  }
  
  public void zza(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1)
    {
      if (this.zzmU != null) {
        this.zzmU.recycle();
      }
      this.zzmU = MotionEvent.obtain(paramMotionEvent);
    }
  }
  
  void zza(String paramString, byte[] paramArrayOfByte)
    throws UnsupportedEncodingException
  {
    if (paramString.length() > 32) {
      paramString = paramString.substring(0, 32);
    }
    new zzrv(paramString.getBytes("UTF-8")).zzA(paramArrayOfByte);
  }
  
  public String zzb(Context paramContext)
  {
    return zza(paramContext, null, false);
  }
  
  public String zzb(Context paramContext, String paramString)
  {
    return zza(paramContext, paramString, true);
  }
  
  protected abstract void zzc(Context paramContext);
  
  protected abstract void zzd(Context paramContext);
  
  protected String zzk(String paramString)
  {
    if ((paramString != null) && (paramString.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")))
    {
      UUID localUUID = UUID.fromString(paramString);
      byte[] arrayOfByte = new byte[16];
      ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte);
      localByteBuffer.putLong(localUUID.getMostSignificantBits());
      localByteBuffer.putLong(localUUID.getLeastSignificantBits());
      paramString = this.zzmW.zza(arrayOfByte, true);
    }
    return paramString;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */