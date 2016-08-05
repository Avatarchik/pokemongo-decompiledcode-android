package com.fasterxml.jackson.core;

public enum JsonToken
{
  final int _id;
  final boolean _isBoolean;
  final boolean _isNumber;
  final boolean _isScalar;
  final boolean _isStructEnd;
  final boolean _isStructStart;
  final String _serialized;
  final byte[] _serializedBytes;
  final char[] _serializedChars;
  
  static
  {
    END_OBJECT = new JsonToken("END_OBJECT", 2, "}", 2);
    START_ARRAY = new JsonToken("START_ARRAY", 3, "[", 3);
    END_ARRAY = new JsonToken("END_ARRAY", 4, "]", 4);
    FIELD_NAME = new JsonToken("FIELD_NAME", 5, null, 5);
    VALUE_EMBEDDED_OBJECT = new JsonToken("VALUE_EMBEDDED_OBJECT", 6, null, 12);
    VALUE_STRING = new JsonToken("VALUE_STRING", 7, null, 6);
    VALUE_NUMBER_INT = new JsonToken("VALUE_NUMBER_INT", 8, null, 7);
    VALUE_NUMBER_FLOAT = new JsonToken("VALUE_NUMBER_FLOAT", 9, null, 8);
    VALUE_TRUE = new JsonToken("VALUE_TRUE", 10, "true", 9);
    VALUE_FALSE = new JsonToken("VALUE_FALSE", 11, "false", 10);
    VALUE_NULL = new JsonToken("VALUE_NULL", 12, "null", 11);
    JsonToken[] arrayOfJsonToken = new JsonToken[13];
    arrayOfJsonToken[0] = NOT_AVAILABLE;
    arrayOfJsonToken[1] = START_OBJECT;
    arrayOfJsonToken[2] = END_OBJECT;
    arrayOfJsonToken[3] = START_ARRAY;
    arrayOfJsonToken[4] = END_ARRAY;
    arrayOfJsonToken[5] = FIELD_NAME;
    arrayOfJsonToken[6] = VALUE_EMBEDDED_OBJECT;
    arrayOfJsonToken[7] = VALUE_STRING;
    arrayOfJsonToken[8] = VALUE_NUMBER_INT;
    arrayOfJsonToken[9] = VALUE_NUMBER_FLOAT;
    arrayOfJsonToken[10] = VALUE_TRUE;
    arrayOfJsonToken[11] = VALUE_FALSE;
    arrayOfJsonToken[12] = VALUE_NULL;
    $VALUES = arrayOfJsonToken;
  }
  
  private JsonToken(String paramString, int paramInt)
  {
    int m;
    label52:
    int n;
    label76:
    int i1;
    label99:
    int i2;
    if (paramString == null)
    {
      this._serialized = null;
      this._serializedChars = null;
      this._serializedBytes = null;
      this._id = paramInt;
      if ((paramInt != 10) && (paramInt != 9)) {
        break label220;
      }
      m = i;
      this._isBoolean = m;
      if ((paramInt != 7) && (paramInt != 8)) {
        break label226;
      }
      n = i;
      this._isNumber = n;
      if ((paramInt != i) && (paramInt != 3)) {
        break label232;
      }
      i1 = i;
      this._isStructStart = i1;
      if ((paramInt != 2) && (paramInt != 4)) {
        break label238;
      }
      i2 = i;
      label121:
      this._isStructEnd = i2;
      if ((this._isStructStart) || (this._isStructEnd) || (paramInt == 5) || (paramInt == -1)) {
        break label244;
      }
    }
    for (;;)
    {
      this._isScalar = i;
      return;
      this._serialized = paramString;
      this._serializedChars = paramString.toCharArray();
      int j = this._serializedChars.length;
      this._serializedBytes = new byte[j];
      for (int k = 0; k < j; k++) {
        this._serializedBytes[k] = ((byte)this._serializedChars[k]);
      }
      break;
      label220:
      m = 0;
      break label52;
      label226:
      n = 0;
      break label76;
      label232:
      i1 = 0;
      break label99;
      label238:
      i2 = 0;
      break label121;
      label244:
      i = 0;
    }
  }
  
  public final byte[] asByteArray()
  {
    return this._serializedBytes;
  }
  
  public final char[] asCharArray()
  {
    return this._serializedChars;
  }
  
  public final String asString()
  {
    return this._serialized;
  }
  
  public final int id()
  {
    return this._id;
  }
  
  public final boolean isBoolean()
  {
    return this._isBoolean;
  }
  
  public final boolean isNumeric()
  {
    return this._isNumber;
  }
  
  public final boolean isScalarValue()
  {
    return this._isScalar;
  }
  
  public final boolean isStructEnd()
  {
    return this._isStructEnd;
  }
  
  public final boolean isStructStart()
  {
    return this._isStructStart;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */