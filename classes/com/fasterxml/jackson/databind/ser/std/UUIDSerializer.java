package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.UUID;

public class UUIDSerializer
  extends StdScalarSerializer<UUID>
{
  static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
  
  public UUIDSerializer()
  {
    super(UUID.class);
  }
  
  private static final void _appendInt(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 >> 24));
    int i = paramInt2 + 1;
    paramArrayOfByte[i] = ((byte)(paramInt1 >> 16));
    int j = i + 1;
    paramArrayOfByte[j] = ((byte)(paramInt1 >> 8));
    paramArrayOfByte[(j + 1)] = ((byte)paramInt1);
  }
  
  private static void _appendInt(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    _appendShort(paramInt1 >> 16, paramArrayOfChar, paramInt2);
    _appendShort(paramInt1, paramArrayOfChar, paramInt2 + 4);
  }
  
  private static void _appendShort(int paramInt1, char[] paramArrayOfChar, int paramInt2)
  {
    paramArrayOfChar[paramInt2] = HEX_CHARS[(0xF & paramInt1 >> 12)];
    int i = paramInt2 + 1;
    paramArrayOfChar[i] = HEX_CHARS[(0xF & paramInt1 >> 8)];
    int j = i + 1;
    paramArrayOfChar[j] = HEX_CHARS[(0xF & paramInt1 >> 4)];
    paramArrayOfChar[(j + 1)] = HEX_CHARS[(paramInt1 & 0xF)];
  }
  
  private static final byte[] _asBytes(UUID paramUUID)
  {
    byte[] arrayOfByte = new byte[16];
    long l1 = paramUUID.getMostSignificantBits();
    long l2 = paramUUID.getLeastSignificantBits();
    _appendInt((int)(l1 >> 32), arrayOfByte, 0);
    _appendInt((int)l1, arrayOfByte, 4);
    _appendInt((int)(l2 >> 32), arrayOfByte, 8);
    _appendInt((int)l2, arrayOfByte, 12);
    return arrayOfByte;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, UUID paramUUID)
  {
    boolean bool = true;
    if (paramUUID == null) {}
    for (;;)
    {
      return bool;
      if ((paramUUID.getLeastSignificantBits() != 0L) || (paramUUID.getMostSignificantBits() != 0L)) {
        bool = false;
      }
    }
  }
  
  @Deprecated
  public boolean isEmpty(UUID paramUUID)
  {
    return isEmpty(null, paramUUID);
  }
  
  public void serialize(UUID paramUUID, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((paramJsonGenerator.canWriteBinaryNatively()) && (!(paramJsonGenerator instanceof TokenBuffer))) {
      paramJsonGenerator.writeBinary(_asBytes(paramUUID));
    }
    for (;;)
    {
      return;
      char[] arrayOfChar = new char[36];
      long l1 = paramUUID.getMostSignificantBits();
      _appendInt((int)(l1 >> 32), arrayOfChar, 0);
      arrayOfChar[8] = '-';
      int i = (int)l1;
      _appendShort(i >>> 16, arrayOfChar, 9);
      arrayOfChar[13] = '-';
      _appendShort(i, arrayOfChar, 14);
      arrayOfChar[18] = '-';
      long l2 = paramUUID.getLeastSignificantBits();
      _appendShort((int)(l2 >>> 48), arrayOfChar, 19);
      arrayOfChar[23] = '-';
      _appendShort((int)(l2 >>> 32), arrayOfChar, 24);
      _appendInt((int)l2, arrayOfChar, 28);
      paramJsonGenerator.writeString(arrayOfChar, 0, 36);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/UUIDSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */