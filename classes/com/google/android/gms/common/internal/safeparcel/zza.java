package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class zza
{
  public static BigDecimal[] zzA(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    BigDecimal[] arrayOfBigDecimal;
    if (i == 0) {
      arrayOfBigDecimal = null;
    }
    for (;;)
    {
      return arrayOfBigDecimal;
      int k = paramParcel.readInt();
      arrayOfBigDecimal = new BigDecimal[k];
      for (int m = 0; m < k; m++)
      {
        byte[] arrayOfByte = paramParcel.createByteArray();
        int n = paramParcel.readInt();
        arrayOfBigDecimal[m] = new BigDecimal(new BigInteger(arrayOfByte), n);
      }
      paramParcel.setDataPosition(j + i);
    }
  }
  
  public static String[] zzB(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    String[] arrayOfString;
    if (i == 0) {
      arrayOfString = null;
    }
    for (;;)
    {
      return arrayOfString;
      arrayOfString = paramParcel.createStringArray();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static ArrayList<Integer> zzC(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    Object localObject;
    if (i == 0) {
      localObject = null;
    }
    for (;;)
    {
      return (ArrayList<Integer>)localObject;
      localObject = new ArrayList();
      int k = paramParcel.readInt();
      for (int m = 0; m < k; m++) {
        ((ArrayList)localObject).add(Integer.valueOf(paramParcel.readInt()));
      }
      paramParcel.setDataPosition(j + i);
    }
  }
  
  public static ArrayList<String> zzD(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    Object localObject;
    if (i == 0) {
      localObject = null;
    }
    for (;;)
    {
      return (ArrayList<String>)localObject;
      localObject = paramParcel.createStringArrayList();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static Parcel zzE(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    Parcel localParcel;
    if (i == 0) {
      localParcel = null;
    }
    for (;;)
    {
      return localParcel;
      localParcel = Parcel.obtain();
      localParcel.appendFrom(paramParcel, j, i);
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static Parcel[] zzF(Parcel paramParcel, int paramInt)
  {
    Object localObject = null;
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {}
    for (;;)
    {
      return (Parcel[])localObject;
      int k = paramParcel.readInt();
      Parcel[] arrayOfParcel = new Parcel[k];
      int m = 0;
      if (m < k)
      {
        int n = paramParcel.readInt();
        if (n != 0)
        {
          int i1 = paramParcel.dataPosition();
          Parcel localParcel = Parcel.obtain();
          localParcel.appendFrom(paramParcel, i1, n);
          arrayOfParcel[m] = localParcel;
          paramParcel.setDataPosition(n + i1);
        }
        for (;;)
        {
          m++;
          break;
          arrayOfParcel[m] = null;
        }
      }
      paramParcel.setDataPosition(j + i);
      localObject = arrayOfParcel;
    }
  }
  
  public static int zza(Parcel paramParcel, int paramInt)
  {
    if ((paramInt & 0xFFFF0000) != -65536) {}
    for (int i = 0xFFFF & paramInt >> 16;; i = paramParcel.readInt()) {
      return i;
    }
  }
  
  public static <T extends Parcelable> T zza(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    Object localObject;
    if (i == 0) {
      localObject = null;
    }
    for (;;)
    {
      return (T)localObject;
      localObject = (Parcelable)paramCreator.createFromParcel(paramParcel);
      paramParcel.setDataPosition(i + j);
    }
  }
  
  private static void zza(Parcel paramParcel, int paramInt1, int paramInt2)
  {
    int i = zza(paramParcel, paramInt1);
    if (i != paramInt2) {
      throw new zza("Expected size " + paramInt2 + " got " + i + " (0x" + Integer.toHexString(i) + ")", paramParcel);
    }
  }
  
  private static void zza(Parcel paramParcel, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 != paramInt3) {
      throw new zza("Expected size " + paramInt3 + " got " + paramInt2 + " (0x" + Integer.toHexString(paramInt2) + ")", paramParcel);
    }
  }
  
  public static void zza(Parcel paramParcel, int paramInt, List paramList, ClassLoader paramClassLoader)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {}
    for (;;)
    {
      return;
      paramParcel.readList(paramList, paramClassLoader);
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static int zzao(Parcel paramParcel)
  {
    return paramParcel.readInt();
  }
  
  public static int zzap(Parcel paramParcel)
  {
    int i = zzao(paramParcel);
    int j = zza(paramParcel, i);
    int k = paramParcel.dataPosition();
    if (zzbM(i) != 20293) {
      throw new zza("Expected object header. Got 0x" + Integer.toHexString(i), paramParcel);
    }
    int m = k + j;
    if ((m < k) || (m > paramParcel.dataSize())) {
      throw new zza("Size read is invalid start=" + k + " end=" + m, paramParcel);
    }
    return m;
  }
  
  public static void zzb(Parcel paramParcel, int paramInt)
  {
    paramParcel.setDataPosition(zza(paramParcel, paramInt) + paramParcel.dataPosition());
  }
  
  public static <T> T[] zzb(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    Object localObject;
    if (i == 0) {
      localObject = null;
    }
    for (;;)
    {
      return (T[])localObject;
      localObject = paramParcel.createTypedArray(paramCreator);
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static int zzbM(int paramInt)
  {
    return 0xFFFF & paramInt;
  }
  
  public static <T> ArrayList<T> zzc(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    Object localObject;
    if (i == 0) {
      localObject = null;
    }
    for (;;)
    {
      return (ArrayList<T>)localObject;
      localObject = paramParcel.createTypedArrayList(paramCreator);
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static boolean zzc(Parcel paramParcel, int paramInt)
  {
    zza(paramParcel, paramInt, 4);
    if (paramParcel.readInt() != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static Boolean zzd(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    Boolean localBoolean;
    if (i == 0)
    {
      localBoolean = null;
      return localBoolean;
    }
    zza(paramParcel, paramInt, i, 4);
    if (paramParcel.readInt() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      localBoolean = Boolean.valueOf(bool);
      break;
    }
  }
  
  public static byte zze(Parcel paramParcel, int paramInt)
  {
    zza(paramParcel, paramInt, 4);
    return (byte)paramParcel.readInt();
  }
  
  public static short zzf(Parcel paramParcel, int paramInt)
  {
    zza(paramParcel, paramInt, 4);
    return (short)paramParcel.readInt();
  }
  
  public static int zzg(Parcel paramParcel, int paramInt)
  {
    zza(paramParcel, paramInt, 4);
    return paramParcel.readInt();
  }
  
  public static Integer zzh(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    if (i == 0) {}
    for (Integer localInteger = null;; localInteger = Integer.valueOf(paramParcel.readInt()))
    {
      return localInteger;
      zza(paramParcel, paramInt, i, 4);
    }
  }
  
  public static long zzi(Parcel paramParcel, int paramInt)
  {
    zza(paramParcel, paramInt, 8);
    return paramParcel.readLong();
  }
  
  public static Long zzj(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    if (i == 0) {}
    for (Long localLong = null;; localLong = Long.valueOf(paramParcel.readLong()))
    {
      return localLong;
      zza(paramParcel, paramInt, i, 8);
    }
  }
  
  public static BigInteger zzk(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {}
    byte[] arrayOfByte;
    for (BigInteger localBigInteger = null;; localBigInteger = new BigInteger(arrayOfByte))
    {
      return localBigInteger;
      arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static float zzl(Parcel paramParcel, int paramInt)
  {
    zza(paramParcel, paramInt, 4);
    return paramParcel.readFloat();
  }
  
  public static Float zzm(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    if (i == 0) {}
    for (Float localFloat = null;; localFloat = Float.valueOf(paramParcel.readFloat()))
    {
      return localFloat;
      zza(paramParcel, paramInt, i, 4);
    }
  }
  
  public static double zzn(Parcel paramParcel, int paramInt)
  {
    zza(paramParcel, paramInt, 8);
    return paramParcel.readDouble();
  }
  
  public static BigDecimal zzo(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    if (i == 0) {}
    byte[] arrayOfByte;
    int k;
    for (BigDecimal localBigDecimal = null;; localBigDecimal = new BigDecimal(new BigInteger(arrayOfByte), k))
    {
      return localBigDecimal;
      arrayOfByte = paramParcel.createByteArray();
      k = paramParcel.readInt();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static String zzp(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    String str;
    if (i == 0) {
      str = null;
    }
    for (;;)
    {
      return str;
      str = paramParcel.readString();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static IBinder zzq(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    IBinder localIBinder;
    if (i == 0) {
      localIBinder = null;
    }
    for (;;)
    {
      return localIBinder;
      localIBinder = paramParcel.readStrongBinder();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static Bundle zzr(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    Bundle localBundle;
    if (i == 0) {
      localBundle = null;
    }
    for (;;)
    {
      return localBundle;
      localBundle = paramParcel.readBundle();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static byte[] zzs(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    byte[] arrayOfByte;
    if (i == 0) {
      arrayOfByte = null;
    }
    for (;;)
    {
      return arrayOfByte;
      arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static byte[][] zzt(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    byte[][] arrayOfByte;
    if (i == 0) {
      arrayOfByte = (byte[][])null;
    }
    for (;;)
    {
      return arrayOfByte;
      int k = paramParcel.readInt();
      arrayOfByte = new byte[k][];
      for (int m = 0; m < k; m++) {
        arrayOfByte[m] = paramParcel.createByteArray();
      }
      paramParcel.setDataPosition(j + i);
    }
  }
  
  public static boolean[] zzu(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    boolean[] arrayOfBoolean;
    if (i == 0) {
      arrayOfBoolean = null;
    }
    for (;;)
    {
      return arrayOfBoolean;
      arrayOfBoolean = paramParcel.createBooleanArray();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static int[] zzv(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    int[] arrayOfInt;
    if (i == 0) {
      arrayOfInt = null;
    }
    for (;;)
    {
      return arrayOfInt;
      arrayOfInt = paramParcel.createIntArray();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static long[] zzw(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    long[] arrayOfLong;
    if (i == 0) {
      arrayOfLong = null;
    }
    for (;;)
    {
      return arrayOfLong;
      arrayOfLong = paramParcel.createLongArray();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static BigInteger[] zzx(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    BigInteger[] arrayOfBigInteger;
    if (i == 0) {
      arrayOfBigInteger = null;
    }
    for (;;)
    {
      return arrayOfBigInteger;
      int k = paramParcel.readInt();
      arrayOfBigInteger = new BigInteger[k];
      for (int m = 0; m < k; m++) {
        arrayOfBigInteger[m] = new BigInteger(paramParcel.createByteArray());
      }
      paramParcel.setDataPosition(j + i);
    }
  }
  
  public static float[] zzy(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    float[] arrayOfFloat;
    if (i == 0) {
      arrayOfFloat = null;
    }
    for (;;)
    {
      return arrayOfFloat;
      arrayOfFloat = paramParcel.createFloatArray();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static double[] zzz(Parcel paramParcel, int paramInt)
  {
    int i = zza(paramParcel, paramInt);
    int j = paramParcel.dataPosition();
    double[] arrayOfDouble;
    if (i == 0) {
      arrayOfDouble = null;
    }
    for (;;)
    {
      return arrayOfDouble;
      arrayOfDouble = paramParcel.createDoubleArray();
      paramParcel.setDataPosition(i + j);
    }
  }
  
  public static class zza
    extends RuntimeException
  {
    public zza(String paramString, Parcel paramParcel)
    {
      super();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/safeparcel/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */