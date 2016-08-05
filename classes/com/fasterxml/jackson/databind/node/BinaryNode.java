package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Arrays;

public class BinaryNode
  extends ValueNode
{
  static final BinaryNode EMPTY_BINARY_NODE = new BinaryNode(new byte[0]);
  protected final byte[] _data;
  
  public BinaryNode(byte[] paramArrayOfByte)
  {
    this._data = paramArrayOfByte;
  }
  
  public BinaryNode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramInt2 == paramArrayOfByte.length)) {
      this._data = paramArrayOfByte;
    }
    for (;;)
    {
      return;
      this._data = new byte[paramInt2];
      System.arraycopy(paramArrayOfByte, paramInt1, this._data, 0, paramInt2);
    }
  }
  
  public static BinaryNode valueOf(byte[] paramArrayOfByte)
  {
    BinaryNode localBinaryNode;
    if (paramArrayOfByte == null) {
      localBinaryNode = null;
    }
    for (;;)
    {
      return localBinaryNode;
      if (paramArrayOfByte.length == 0) {
        localBinaryNode = EMPTY_BINARY_NODE;
      } else {
        localBinaryNode = new BinaryNode(paramArrayOfByte);
      }
    }
  }
  
  public static BinaryNode valueOf(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    BinaryNode localBinaryNode;
    if (paramArrayOfByte == null) {
      localBinaryNode = null;
    }
    for (;;)
    {
      return localBinaryNode;
      if (paramInt2 == 0) {
        localBinaryNode = EMPTY_BINARY_NODE;
      } else {
        localBinaryNode = new BinaryNode(paramArrayOfByte, paramInt1, paramInt2);
      }
    }
  }
  
  public String asText()
  {
    return Base64Variants.getDefaultVariant().encode(this._data, false);
  }
  
  public JsonToken asToken()
  {
    return JsonToken.VALUE_EMBEDDED_OBJECT;
  }
  
  public byte[] binaryValue()
  {
    return this._data;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == this) {}
    for (bool = true;; bool = Arrays.equals(((BinaryNode)paramObject)._data, this._data)) {
      do
      {
        return bool;
      } while ((paramObject == null) || (!(paramObject instanceof BinaryNode)));
    }
  }
  
  public JsonNodeType getNodeType()
  {
    return JsonNodeType.BINARY;
  }
  
  public int hashCode()
  {
    if (this._data == null) {}
    for (int i = -1;; i = this._data.length) {
      return i;
    }
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeBinary(paramSerializerProvider.getConfig().getBase64Variant(), this._data, 0, this._data.length);
  }
  
  public String toString()
  {
    return Base64Variants.getDefaultVariant().encode(this._data, true);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/BinaryNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */