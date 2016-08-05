package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

public class UUIDDeserializer
  extends FromStringDeserializer<UUID>
{
  static final int[] HEX_DIGITS = new int[127];
  private static final long serialVersionUID = 1L;
  
  static
  {
    Arrays.fill(HEX_DIGITS, -1);
    for (int i = 0; i < 10; i++) {
      HEX_DIGITS[(i + 48)] = i;
    }
    for (int j = 0; j < 6; j++)
    {
      HEX_DIGITS[(j + 97)] = (j + 10);
      HEX_DIGITS[(j + 65)] = (j + 10);
    }
  }
  
  public UUIDDeserializer()
  {
    super(UUID.class);
  }
  
  static int _badChar(String paramString, int paramInt, char paramChar)
  {
    throw new NumberFormatException("Non-hex character '" + paramChar + "', not valid character for a UUID String" + "' (value 0x" + Integer.toHexString(paramChar) + ") for UUID String \"" + paramString + "\"");
  }
  
  private void _badFormat(String paramString)
  {
    throw new NumberFormatException("UUID has to be represented by the standard 36-char representation");
  }
  
  private UUID _fromBytes(byte[] paramArrayOfByte, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramArrayOfByte.length != 16)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramArrayOfByte.length);
      paramDeserializationContext.mappingException("Can only construct UUIDs from byte[16]; got %d bytes", arrayOfObject);
    }
    return new UUID(_long(paramArrayOfByte, 0), _long(paramArrayOfByte, 8));
  }
  
  private static int _int(byte[] paramArrayOfByte, int paramInt)
  {
    return paramArrayOfByte[paramInt] << 24 | (0xFF & paramArrayOfByte[(paramInt + 1)]) << 16 | (0xFF & paramArrayOfByte[(paramInt + 2)]) << 8 | 0xFF & paramArrayOfByte[(paramInt + 3)];
  }
  
  private static long _long(byte[] paramArrayOfByte, int paramInt)
  {
    return _int(paramArrayOfByte, paramInt) << 32 | _int(paramArrayOfByte, paramInt + 4) << 32 >>> 32;
  }
  
  static int byteFromChars(String paramString, int paramInt)
  {
    char c1 = paramString.charAt(paramInt);
    char c2 = paramString.charAt(paramInt + 1);
    int i;
    if ((c1 <= '') && (c2 <= ''))
    {
      i = HEX_DIGITS[c1] << 4 | HEX_DIGITS[c2];
      if (i < 0) {}
    }
    for (;;)
    {
      return i;
      if ((c1 > '') || (HEX_DIGITS[c1] < 0)) {
        i = _badChar(paramString, paramInt, c1);
      } else {
        i = _badChar(paramString, paramInt + 1, c2);
      }
    }
  }
  
  static int intFromChars(String paramString, int paramInt)
  {
    return (byteFromChars(paramString, paramInt) << 24) + (byteFromChars(paramString, paramInt + 2) << 16) + (byteFromChars(paramString, paramInt + 4) << 8) + byteFromChars(paramString, paramInt + 6);
  }
  
  static int shortFromChars(String paramString, int paramInt)
  {
    return (byteFromChars(paramString, paramInt) << 8) + byteFromChars(paramString, paramInt + 2);
  }
  
  protected UUID _deserialize(String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramString.length() != 36) {
      if (paramString.length() != 24) {}
    }
    for (UUID localUUID = _fromBytes(Base64Variants.getDefaultVariant().decode(paramString), paramDeserializationContext);; localUUID = new UUID((intFromChars(paramString, 0) << 32) + (shortFromChars(paramString, 9) << 16 | shortFromChars(paramString, 14)), (shortFromChars(paramString, 19) << 16 | shortFromChars(paramString, 24)) << 32 | intFromChars(paramString, 28) << 32 >>> 32))
    {
      return localUUID;
      _badFormat(paramString);
      if ((paramString.charAt(8) != '-') || (paramString.charAt(13) != '-') || (paramString.charAt(18) != '-') || (paramString.charAt(23) != '-')) {
        _badFormat(paramString);
      }
    }
  }
  
  protected UUID _deserializeEmbedded(Object paramObject, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if ((paramObject instanceof byte[])) {}
    for (UUID localUUID = _fromBytes((byte[])paramObject, paramDeserializationContext);; localUUID = null)
    {
      return localUUID;
      super._deserializeEmbedded(paramObject, paramDeserializationContext);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/UUIDDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */