package com.fasterxml.jackson.core.io;

import java.util.Arrays;

public final class CharTypes
{
  private static final byte[] HB;
  private static final char[] HC = "0123456789ABCDEF".toCharArray();
  static final int[] sHexValues;
  static final int[] sInputCodes;
  static final int[] sInputCodesComment;
  static final int[] sInputCodesJsNames;
  static final int[] sInputCodesUTF8;
  static final int[] sInputCodesUtf8JsNames;
  static final int[] sInputCodesWS;
  static final int[] sOutputEscapes128;
  
  static
  {
    int i = HC.length;
    HB = new byte[i];
    for (int j = 0; j < i; j++) {
      HB[j] = ((byte)HC[j]);
    }
    int[] arrayOfInt1 = new int['Ā'];
    for (int k = 0; k < 32; k++) {
      arrayOfInt1[k] = -1;
    }
    arrayOfInt1[34] = 1;
    arrayOfInt1[92] = 1;
    sInputCodes = arrayOfInt1;
    int[] arrayOfInt2 = new int[sInputCodes.length];
    System.arraycopy(sInputCodes, 0, arrayOfInt2, 0, arrayOfInt2.length);
    int m = 128;
    if (m < 256)
    {
      int i4;
      if ((m & 0xE0) == 192) {
        i4 = 2;
      }
      for (;;)
      {
        arrayOfInt2[m] = i4;
        m++;
        break;
        if ((m & 0xF0) == 224) {
          i4 = 3;
        } else if ((m & 0xF8) == 240) {
          i4 = 4;
        } else {
          i4 = -1;
        }
      }
    }
    sInputCodesUTF8 = arrayOfInt2;
    int[] arrayOfInt3 = new int['Ā'];
    Arrays.fill(arrayOfInt3, -1);
    for (int n = 33; n < 256; n++) {
      if (Character.isJavaIdentifierPart((char)n)) {
        arrayOfInt3[n] = 0;
      }
    }
    arrayOfInt3[64] = 0;
    arrayOfInt3[35] = 0;
    arrayOfInt3[42] = 0;
    arrayOfInt3[45] = 0;
    arrayOfInt3[43] = 0;
    sInputCodesJsNames = arrayOfInt3;
    int[] arrayOfInt4 = new int['Ā'];
    System.arraycopy(sInputCodesJsNames, 0, arrayOfInt4, 0, arrayOfInt4.length);
    Arrays.fill(arrayOfInt4, 128, 128, 0);
    sInputCodesUtf8JsNames = arrayOfInt4;
    int[] arrayOfInt5 = new int['Ā'];
    System.arraycopy(sInputCodesUTF8, 128, arrayOfInt5, 128, 128);
    Arrays.fill(arrayOfInt5, 0, 32, -1);
    arrayOfInt5[9] = 0;
    arrayOfInt5[10] = 10;
    arrayOfInt5[13] = 13;
    arrayOfInt5[42] = 42;
    sInputCodesComment = arrayOfInt5;
    int[] arrayOfInt6 = new int['Ā'];
    System.arraycopy(sInputCodesUTF8, 128, arrayOfInt6, 128, 128);
    Arrays.fill(arrayOfInt6, 0, 32, -1);
    arrayOfInt6[32] = 1;
    arrayOfInt6[9] = 1;
    arrayOfInt6[10] = 10;
    arrayOfInt6[13] = 13;
    arrayOfInt6[47] = 47;
    arrayOfInt6[35] = 35;
    sInputCodesWS = arrayOfInt6;
    int[] arrayOfInt7 = new int[''];
    for (int i1 = 0; i1 < 32; i1++) {
      arrayOfInt7[i1] = -1;
    }
    arrayOfInt7[34] = 34;
    arrayOfInt7[92] = 92;
    arrayOfInt7[8] = 98;
    arrayOfInt7[9] = 116;
    arrayOfInt7[12] = 102;
    arrayOfInt7[10] = 110;
    arrayOfInt7[13] = 114;
    sOutputEscapes128 = arrayOfInt7;
    sHexValues = new int[''];
    Arrays.fill(sHexValues, -1);
    for (int i2 = 0; i2 < 10; i2++) {
      sHexValues[(i2 + 48)] = i2;
    }
    for (int i3 = 0; i3 < 6; i3++)
    {
      sHexValues[(i3 + 97)] = (i3 + 10);
      sHexValues[(i3 + 65)] = (i3 + 10);
    }
  }
  
  public static void appendQuoted(StringBuilder paramStringBuilder, String paramString)
  {
    int[] arrayOfInt = sOutputEscapes128;
    int i = arrayOfInt.length;
    int j = 0;
    int k = paramString.length();
    if (j < k)
    {
      int m = paramString.charAt(j);
      if ((m >= i) || (arrayOfInt[m] == 0)) {
        paramStringBuilder.append(m);
      }
      for (;;)
      {
        j++;
        break;
        paramStringBuilder.append('\\');
        int n = arrayOfInt[m];
        if (n < 0)
        {
          paramStringBuilder.append('u');
          paramStringBuilder.append('0');
          paramStringBuilder.append('0');
          paramStringBuilder.append(HC[(m >> 4)]);
          paramStringBuilder.append(HC[(m & 0xF)]);
        }
        else
        {
          paramStringBuilder.append((char)n);
        }
      }
    }
  }
  
  public static int charToHex(int paramInt)
  {
    if (paramInt > 127) {}
    for (int i = -1;; i = sHexValues[paramInt]) {
      return i;
    }
  }
  
  public static byte[] copyHexBytes()
  {
    return (byte[])HB.clone();
  }
  
  public static char[] copyHexChars()
  {
    return (char[])HC.clone();
  }
  
  public static int[] get7BitOutputEscapes()
  {
    return sOutputEscapes128;
  }
  
  public static int[] getInputCodeComment()
  {
    return sInputCodesComment;
  }
  
  public static int[] getInputCodeLatin1()
  {
    return sInputCodes;
  }
  
  public static int[] getInputCodeLatin1JsNames()
  {
    return sInputCodesJsNames;
  }
  
  public static int[] getInputCodeUtf8()
  {
    return sInputCodesUTF8;
  }
  
  public static int[] getInputCodeUtf8JsNames()
  {
    return sInputCodesUtf8JsNames;
  }
  
  public static int[] getInputCodeWS()
  {
    return sInputCodesWS;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/io/CharTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */