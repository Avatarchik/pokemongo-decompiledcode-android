package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public final class PasswordSpecification
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  public static final PasswordSpecification zzSt = new zza().zzg(12, 16).zzbD("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zzf("abcdefghijkmnopqrstxyz", 1).zzf("ABCDEFGHJKLMNPQRSTXY", 1).zzf("3456789", 1).zzlK();
  public static final PasswordSpecification zzSu = new zza().zzg(12, 16).zzbD("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zzf("abcdefghijklmnopqrstuvwxyz", 1).zzf("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zzf("1234567890", 1).zzlK();
  final int mVersionCode;
  private final int[] zzSA;
  final String zzSv;
  final List<String> zzSw;
  final List<Integer> zzSx;
  final int zzSy;
  final int zzSz;
  private final Random zzts;
  
  PasswordSpecification(int paramInt1, String paramString, List<String> paramList, List<Integer> paramList1, int paramInt2, int paramInt3)
  {
    this.mVersionCode = paramInt1;
    this.zzSv = paramString;
    this.zzSw = Collections.unmodifiableList(paramList);
    this.zzSx = Collections.unmodifiableList(paramList1);
    this.zzSy = paramInt2;
    this.zzSz = paramInt3;
    this.zzSA = zzlJ();
    this.zzts = new SecureRandom();
  }
  
  private int zza(char paramChar)
  {
    return paramChar + '￠';
  }
  
  private static String zzb(Collection<Character> paramCollection)
  {
    char[] arrayOfChar = new char[paramCollection.size()];
    Iterator localIterator = paramCollection.iterator();
    int k;
    for (int i = 0; localIterator.hasNext(); i = k)
    {
      int j = ((Character)localIterator.next()).charValue();
      k = i + 1;
      arrayOfChar[i] = j;
    }
    return new String(arrayOfChar);
  }
  
  private static boolean zzb(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 < paramInt2) || (paramInt1 > paramInt3)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private int[] zzlJ()
  {
    int[] arrayOfInt = new int[95];
    Arrays.fill(arrayOfInt, -1);
    Iterator localIterator = this.zzSw.iterator();
    for (int i = 0; localIterator.hasNext(); i++)
    {
      char[] arrayOfChar = ((String)localIterator.next()).toCharArray();
      int j = arrayOfChar.length;
      for (int k = 0; k < j; k++) {
        arrayOfInt[zza(arrayOfChar[k])] = i;
      }
    }
    return arrayOfInt;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public static class zzb
    extends Error
  {
    public zzb(String paramString)
    {
      super();
    }
  }
  
  public static class zza
  {
    private final TreeSet<Character> zzSB = new TreeSet();
    private final List<String> zzSw = new ArrayList();
    private final List<Integer> zzSx = new ArrayList();
    private int zzSy = 12;
    private int zzSz = 16;
    
    private void zzlL()
    {
      Iterator localIterator = this.zzSx.iterator();
      int i = 0;
      while (localIterator.hasNext()) {
        i += ((Integer)localIterator.next()).intValue();
      }
      if (i > this.zzSz) {
        throw new PasswordSpecification.zzb("required character count cannot be greater than the max password size");
      }
    }
    
    private void zzlM()
    {
      boolean[] arrayOfBoolean = new boolean[95];
      Iterator localIterator = this.zzSw.iterator();
      while (localIterator.hasNext()) {
        for (char c : ((String)localIterator.next()).toCharArray())
        {
          if (arrayOfBoolean[(c - ' ')] != 0) {
            throw new PasswordSpecification.zzb("character " + c + " occurs in more than one required character set");
          }
          arrayOfBoolean[(c - ' ')] = true;
        }
      }
    }
    
    private TreeSet<Character> zzr(String paramString1, String paramString2)
    {
      if (TextUtils.isEmpty(paramString1)) {
        throw new PasswordSpecification.zzb(paramString2 + " cannot be null or empty");
      }
      TreeSet localTreeSet = new TreeSet();
      for (char c : paramString1.toCharArray())
      {
        if (PasswordSpecification.zzc(c, 32, 126)) {
          throw new PasswordSpecification.zzb(paramString2 + " must only contain ASCII printable characters");
        }
        localTreeSet.add(Character.valueOf(c));
      }
      return localTreeSet;
    }
    
    public zza zzbD(String paramString)
    {
      this.zzSB.addAll(zzr(paramString, "allowedChars"));
      return this;
    }
    
    public zza zzf(String paramString, int paramInt)
    {
      if (paramInt < 1) {
        throw new PasswordSpecification.zzb("count must be at least 1");
      }
      TreeSet localTreeSet = zzr(paramString, "requiredChars");
      this.zzSw.add(PasswordSpecification.zzc(localTreeSet));
      this.zzSx.add(Integer.valueOf(paramInt));
      return this;
    }
    
    public zza zzg(int paramInt1, int paramInt2)
    {
      if (paramInt1 < 1) {
        throw new PasswordSpecification.zzb("minimumSize must be at least 1");
      }
      if (paramInt1 > paramInt2) {
        throw new PasswordSpecification.zzb("maximumSize must be greater than or equal to minimumSize");
      }
      this.zzSy = paramInt1;
      this.zzSz = paramInt2;
      return this;
    }
    
    public PasswordSpecification zzlK()
    {
      if (this.zzSB.isEmpty()) {
        throw new PasswordSpecification.zzb("no allowed characters specified");
      }
      zzlL();
      zzlM();
      return new PasswordSpecification(1, PasswordSpecification.zzc(this.zzSB), this.zzSw, this.zzSx, this.zzSy, this.zzSz);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/PasswordSpecification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */