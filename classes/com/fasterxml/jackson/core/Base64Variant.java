package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.Serializable;
import java.util.Arrays;

public final class Base64Variant
  implements Serializable
{
  public static final int BASE64_VALUE_INVALID = -1;
  public static final int BASE64_VALUE_PADDING = -2;
  private static final int INT_SPACE = 32;
  static final char PADDING_CHAR_NONE = '\000';
  private static final long serialVersionUID = 1L;
  private final transient int[] _asciiToBase64 = new int[''];
  private final transient byte[] _base64ToAsciiB = new byte[64];
  private final transient char[] _base64ToAsciiC = new char[64];
  protected final transient int _maxLineLength;
  protected final String _name;
  protected final transient char _paddingChar;
  protected final transient boolean _usesPadding;
  
  public Base64Variant(Base64Variant paramBase64Variant, String paramString, int paramInt)
  {
    this(paramBase64Variant, paramString, paramBase64Variant._usesPadding, paramBase64Variant._paddingChar, paramInt);
  }
  
  public Base64Variant(Base64Variant paramBase64Variant, String paramString, boolean paramBoolean, char paramChar, int paramInt)
  {
    this._name = paramString;
    byte[] arrayOfByte = paramBase64Variant._base64ToAsciiB;
    System.arraycopy(arrayOfByte, 0, this._base64ToAsciiB, 0, arrayOfByte.length);
    char[] arrayOfChar = paramBase64Variant._base64ToAsciiC;
    System.arraycopy(arrayOfChar, 0, this._base64ToAsciiC, 0, arrayOfChar.length);
    int[] arrayOfInt = paramBase64Variant._asciiToBase64;
    System.arraycopy(arrayOfInt, 0, this._asciiToBase64, 0, arrayOfInt.length);
    this._usesPadding = paramBoolean;
    this._paddingChar = paramChar;
    this._maxLineLength = paramInt;
  }
  
  public Base64Variant(String paramString1, String paramString2, boolean paramBoolean, char paramChar, int paramInt)
  {
    this._name = paramString1;
    this._usesPadding = paramBoolean;
    this._paddingChar = paramChar;
    this._maxLineLength = paramInt;
    int i = paramString2.length();
    if (i != 64) {
      throw new IllegalArgumentException("Base64Alphabet length must be exactly 64 (was " + i + ")");
    }
    paramString2.getChars(0, i, this._base64ToAsciiC, 0);
    Arrays.fill(this._asciiToBase64, -1);
    for (int j = 0; j < i; j++)
    {
      int k = this._base64ToAsciiC[j];
      this._base64ToAsciiB[j] = ((byte)k);
      this._asciiToBase64[k] = j;
    }
    if (paramBoolean) {
      this._asciiToBase64[paramChar] = -2;
    }
  }
  
  protected void _reportBase64EOF()
    throws IllegalArgumentException
  {
    throw new IllegalArgumentException("Unexpected end-of-String in base64 content");
  }
  
  protected void _reportInvalidBase64(char paramChar, int paramInt, String paramString)
    throws IllegalArgumentException
  {
    String str;
    if (paramChar <= ' ') {
      str = "Illegal white space character (code 0x" + Integer.toHexString(paramChar) + ") as character #" + (paramInt + 1) + " of 4-char base64 unit: can only used between units";
    }
    for (;;)
    {
      if (paramString != null) {
        str = str + ": " + paramString;
      }
      throw new IllegalArgumentException(str);
      if (usesPaddingChar(paramChar)) {
        str = "Unexpected padding character ('" + getPaddingChar() + "') as character #" + (paramInt + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
      } else if ((!Character.isDefined(paramChar)) || (Character.isISOControl(paramChar))) {
        str = "Illegal character (code 0x" + Integer.toHexString(paramChar) + ") in base64 content";
      } else {
        str = "Illegal character '" + paramChar + "' (code 0x" + Integer.toHexString(paramChar) + ") in base64 content";
      }
    }
  }
  
  public void decode(String paramString, ByteArrayBuilder paramByteArrayBuilder)
    throws IllegalArgumentException
  {
    int i = 0;
    int j = paramString.length();
    if (i < j) {}
    for (;;)
    {
      int k = i + 1;
      char c1 = paramString.charAt(i);
      if (k >= j) {}
      int i3;
      int i5;
      for (;;)
      {
        return;
        if (c1 <= ' ') {
          break label373;
        }
        int m = decodeBase64Char(c1);
        if (m < 0) {
          _reportInvalidBase64(c1, 0, null);
        }
        if (k >= j) {
          _reportBase64EOF();
        }
        int n = k + 1;
        char c2 = paramString.charAt(k);
        int i1 = decodeBase64Char(c2);
        if (i1 < 0) {
          _reportInvalidBase64(c2, 1, null);
        }
        int i2 = i1 | m << 6;
        if (n >= j)
        {
          if (!usesPadding()) {
            paramByteArrayBuilder.append(i2 >> 4);
          } else {
            _reportBase64EOF();
          }
        }
        else
        {
          i3 = n + 1;
          char c3 = paramString.charAt(n);
          int i4 = decodeBase64Char(c3);
          if (i4 < 0)
          {
            if (i4 != -2) {
              _reportInvalidBase64(c3, 2, null);
            }
            if (i3 >= j) {
              _reportBase64EOF();
            }
            i = i3 + 1;
            char c5 = paramString.charAt(i3);
            if (!usesPaddingChar(c5)) {
              _reportInvalidBase64(c5, 3, "expected padding character '" + getPaddingChar() + "'");
            }
            paramByteArrayBuilder.append(i2 >> 4);
            break;
          }
          i5 = i4 | i2 << 6;
          if (i3 < j) {
            break label306;
          }
          if (usesPadding()) {
            break label302;
          }
          paramByteArrayBuilder.appendTwoBytes(i5 >> 2);
        }
      }
      label302:
      _reportBase64EOF();
      label306:
      i = i3 + 1;
      char c4 = paramString.charAt(i3);
      int i6 = decodeBase64Char(c4);
      if (i6 < 0)
      {
        if (i6 != -2) {
          _reportInvalidBase64(c4, 3, null);
        }
        paramByteArrayBuilder.appendTwoBytes(i5 >> 2);
        break;
      }
      paramByteArrayBuilder.appendThreeBytes(i6 | i5 << 6);
      break;
      label373:
      i = k;
    }
  }
  
  public byte[] decode(String paramString)
    throws IllegalArgumentException
  {
    ByteArrayBuilder localByteArrayBuilder = new ByteArrayBuilder();
    decode(paramString, localByteArrayBuilder);
    return localByteArrayBuilder.toByteArray();
  }
  
  public int decodeBase64Byte(byte paramByte)
  {
    if (paramByte <= Byte.MAX_VALUE) {}
    for (int i = this._asciiToBase64[paramByte];; i = -1) {
      return i;
    }
  }
  
  public int decodeBase64Char(char paramChar)
  {
    if (paramChar <= '') {}
    for (int i = this._asciiToBase64[paramChar];; i = -1) {
      return i;
    }
  }
  
  public int decodeBase64Char(int paramInt)
  {
    if (paramInt <= 127) {}
    for (int i = this._asciiToBase64[paramInt];; i = -1) {
      return i;
    }
  }
  
  public String encode(byte[] paramArrayOfByte)
  {
    return encode(paramArrayOfByte, false);
  }
  
  public String encode(byte[] paramArrayOfByte, boolean paramBoolean)
  {
    int i = paramArrayOfByte.length;
    StringBuilder localStringBuilder = new StringBuilder(i + (i >> 2) + (i >> 3));
    if (paramBoolean) {
      localStringBuilder.append('"');
    }
    int j = getMaxLineLength() >> 2;
    int k = i - 3;
    int i7;
    for (int m = 0; m <= k; m = i7)
    {
      int i3 = m + 1;
      int i4 = paramArrayOfByte[m] << 8;
      int i5 = i3 + 1;
      int i6 = (i4 | 0xFF & paramArrayOfByte[i3]) << 8;
      i7 = i5 + 1;
      encodeBase64Chunk(localStringBuilder, i6 | 0xFF & paramArrayOfByte[i5]);
      j--;
      if (j <= 0)
      {
        localStringBuilder.append('\\');
        localStringBuilder.append('n');
        j = getMaxLineLength() >> 2;
      }
    }
    int n = i - m;
    if (n > 0)
    {
      int i1 = m + 1;
      int i2 = paramArrayOfByte[m] << 16;
      if (n == 2)
      {
        (i1 + 1);
        i2 |= (0xFF & paramArrayOfByte[i1]) << 8;
      }
      encodeBase64Partial(localStringBuilder, i2, n);
    }
    for (;;)
    {
      if (paramBoolean) {
        localStringBuilder.append('"');
      }
      return localStringBuilder.toString();
    }
  }
  
  public byte encodeBase64BitsAsByte(int paramInt)
  {
    return this._base64ToAsciiB[paramInt];
  }
  
  public char encodeBase64BitsAsChar(int paramInt)
  {
    return this._base64ToAsciiC[paramInt];
  }
  
  public int encodeBase64Chunk(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    int i = paramInt2 + 1;
    paramArrayOfByte[paramInt2] = this._base64ToAsciiB[(0x3F & paramInt1 >> 18)];
    int j = i + 1;
    paramArrayOfByte[i] = this._base64ToAsciiB[(0x3F & paramInt1 >> 12)];
    int k = j + 1;
    paramArrayOfByte[j] = this._base64ToAsciiB[(0x3F & paramInt1 >> 6)];
    int m = k + 1;
    paramArrayOfByte[k] = this._base64ToAsciiB[(paramInt1 & 0x3F)];
    return m;
  }
  
  public int encodeBase64Chunk(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    int i = paramInt2 + 1;
    paramArrayOfChar[paramInt2] = this._base64ToAsciiC[(0x3F & paramInt1 >> 18)];
    int j = i + 1;
    paramArrayOfChar[i] = this._base64ToAsciiC[(0x3F & paramInt1 >> 12)];
    int k = j + 1;
    paramArrayOfChar[j] = this._base64ToAsciiC[(0x3F & paramInt1 >> 6)];
    int m = k + 1;
    paramArrayOfChar[k] = this._base64ToAsciiC[(paramInt1 & 0x3F)];
    return m;
  }
  
  public void encodeBase64Chunk(StringBuilder paramStringBuilder, int paramInt)
  {
    paramStringBuilder.append(this._base64ToAsciiC[(0x3F & paramInt >> 18)]);
    paramStringBuilder.append(this._base64ToAsciiC[(0x3F & paramInt >> 12)]);
    paramStringBuilder.append(this._base64ToAsciiC[(0x3F & paramInt >> 6)]);
    paramStringBuilder.append(this._base64ToAsciiC[(paramInt & 0x3F)]);
  }
  
  public int encodeBase64Partial(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3)
  {
    int i = paramInt3 + 1;
    paramArrayOfByte[paramInt3] = this._base64ToAsciiB[(0x3F & paramInt1 >> 18)];
    int j = i + 1;
    paramArrayOfByte[i] = this._base64ToAsciiB[(0x3F & paramInt1 >> 12)];
    int m;
    int i1;
    if (this._usesPadding)
    {
      m = (byte)this._paddingChar;
      int n = j + 1;
      if (paramInt2 == 2)
      {
        i1 = this._base64ToAsciiB[(0x3F & paramInt1 >> 6)];
        paramArrayOfByte[j] = i1;
        j = n + 1;
        paramArrayOfByte[n] = m;
      }
    }
    for (;;)
    {
      return j;
      i1 = m;
      break;
      if (paramInt2 == 2)
      {
        int k = j + 1;
        paramArrayOfByte[j] = this._base64ToAsciiB[(0x3F & paramInt1 >> 6)];
        j = k;
      }
    }
  }
  
  public int encodeBase64Partial(int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3)
  {
    int i = paramInt3 + 1;
    paramArrayOfChar[paramInt3] = this._base64ToAsciiC[(0x3F & paramInt1 >> 18)];
    int j = i + 1;
    paramArrayOfChar[i] = this._base64ToAsciiC[(0x3F & paramInt1 >> 12)];
    int n;
    if (this._usesPadding)
    {
      int m = j + 1;
      if (paramInt2 == 2)
      {
        n = this._base64ToAsciiC[(0x3F & paramInt1 >> 6)];
        paramArrayOfChar[j] = n;
        j = m + 1;
        paramArrayOfChar[m] = this._paddingChar;
      }
    }
    for (;;)
    {
      return j;
      n = this._paddingChar;
      break;
      if (paramInt2 == 2)
      {
        int k = j + 1;
        paramArrayOfChar[j] = this._base64ToAsciiC[(0x3F & paramInt1 >> 6)];
        j = k;
      }
    }
  }
  
  public void encodeBase64Partial(StringBuilder paramStringBuilder, int paramInt1, int paramInt2)
  {
    paramStringBuilder.append(this._base64ToAsciiC[(0x3F & paramInt1 >> 18)]);
    paramStringBuilder.append(this._base64ToAsciiC[(0x3F & paramInt1 >> 12)]);
    char c;
    if (this._usesPadding) {
      if (paramInt2 == 2)
      {
        c = this._base64ToAsciiC[(0x3F & paramInt1 >> 6)];
        paramStringBuilder.append(c);
        paramStringBuilder.append(this._paddingChar);
      }
    }
    for (;;)
    {
      return;
      c = this._paddingChar;
      break;
      if (paramInt2 == 2) {
        paramStringBuilder.append(this._base64ToAsciiC[(0x3F & paramInt1 >> 6)]);
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int getMaxLineLength()
  {
    return this._maxLineLength;
  }
  
  public String getName()
  {
    return this._name;
  }
  
  public byte getPaddingByte()
  {
    return (byte)this._paddingChar;
  }
  
  public char getPaddingChar()
  {
    return this._paddingChar;
  }
  
  public int hashCode()
  {
    return this._name.hashCode();
  }
  
  protected Object readResolve()
  {
    return Base64Variants.valueOf(this._name);
  }
  
  public String toString()
  {
    return this._name;
  }
  
  public boolean usesPadding()
  {
    return this._usesPadding;
  }
  
  public boolean usesPaddingChar(char paramChar)
  {
    if (paramChar == this._paddingChar) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean usesPaddingChar(int paramInt)
  {
    if (paramInt == this._paddingChar) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/Base64Variant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */