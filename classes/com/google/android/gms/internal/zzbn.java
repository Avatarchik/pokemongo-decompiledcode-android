package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class zzbn
{
  public static int zzC(String paramString)
  {
    try
    {
      byte[] arrayOfByte2 = paramString.getBytes("UTF-8");
      arrayOfByte1 = arrayOfByte2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        byte[] arrayOfByte1 = paramString.getBytes();
      }
    }
    return zzmw.zza(arrayOfByte1, 0, arrayOfByte1.length, 0);
  }
  
  public static String[] zzD(String paramString)
  {
    String[] arrayOfString;
    if (paramString == null)
    {
      arrayOfString = null;
      return arrayOfString;
    }
    ArrayList localArrayList = new ArrayList();
    char[] arrayOfChar = paramString.toCharArray();
    int i = paramString.length();
    int j = 0;
    int k = 0;
    int m = 0;
    label37:
    int n;
    int i1;
    int i2;
    int i3;
    if (m < i)
    {
      n = Character.codePointAt(arrayOfChar, m);
      i1 = Character.charCount(n);
      if (zzh(n))
      {
        if (j != 0) {
          localArrayList.add(new String(arrayOfChar, k, m - k));
        }
        localArrayList.add(new String(arrayOfChar, m, i1));
        i2 = k;
        i3 = 0;
      }
    }
    for (;;)
    {
      m += i1;
      int i4 = i3;
      k = i2;
      j = i4;
      break label37;
      if ((Character.isLetterOrDigit(n)) || (Character.getType(n) == 6) || (Character.getType(n) == 8))
      {
        if (j == 0) {
          k = m;
        }
        i2 = k;
        i3 = 1;
      }
      else
      {
        if (j != 0)
        {
          localArrayList.add(new String(arrayOfChar, k, m - k));
          i2 = k;
          i3 = 0;
          continue;
          if (j != 0) {
            localArrayList.add(new String(arrayOfChar, k, m - k));
          }
          arrayOfString = (String[])localArrayList.toArray(new String[localArrayList.size()]);
          break;
        }
        int i5 = j;
        i2 = k;
        i3 = i5;
      }
    }
  }
  
  private static boolean zza(Character.UnicodeBlock paramUnicodeBlock)
  {
    if ((paramUnicodeBlock == Character.UnicodeBlock.BOPOMOFO) || (paramUnicodeBlock == Character.UnicodeBlock.BOPOMOFO_EXTENDED) || (paramUnicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY) || (paramUnicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) || (paramUnicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT) || (paramUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) || (paramUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) || (paramUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) || (paramUnicodeBlock == Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS) || (paramUnicodeBlock == Character.UnicodeBlock.HANGUL_JAMO) || (paramUnicodeBlock == Character.UnicodeBlock.HANGUL_SYLLABLES) || (paramUnicodeBlock == Character.UnicodeBlock.HIRAGANA) || (paramUnicodeBlock == Character.UnicodeBlock.KATAKANA) || (paramUnicodeBlock == Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static boolean zzh(int paramInt)
  {
    if ((Character.isLetter(paramInt)) && ((zza(Character.UnicodeBlock.of(paramInt))) || (zzi(paramInt)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean zzi(int paramInt)
  {
    if (((paramInt >= 65382) && (paramInt <= 65437)) || ((paramInt >= 65441) && (paramInt <= 65500))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzbn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */