package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TreeTraversingParser
  extends ParserMinimalBase
{
  protected boolean _closed;
  protected JsonToken _nextToken;
  protected NodeCursor _nodeCursor;
  protected ObjectCodec _objectCodec;
  protected boolean _startContainer;
  
  public TreeTraversingParser(JsonNode paramJsonNode)
  {
    this(paramJsonNode, null);
  }
  
  public TreeTraversingParser(JsonNode paramJsonNode, ObjectCodec paramObjectCodec)
  {
    super(0);
    this._objectCodec = paramObjectCodec;
    if (paramJsonNode.isArray())
    {
      this._nextToken = JsonToken.START_ARRAY;
      this._nodeCursor = new NodeCursor.ArrayCursor(paramJsonNode, null);
    }
    for (;;)
    {
      return;
      if (paramJsonNode.isObject())
      {
        this._nextToken = JsonToken.START_OBJECT;
        this._nodeCursor = new NodeCursor.ObjectCursor(paramJsonNode, null);
      }
      else
      {
        this._nodeCursor = new NodeCursor.RootCursor(paramJsonNode, null);
      }
    }
  }
  
  protected void _handleEOF()
    throws JsonParseException
  {
    _throwInternal();
  }
  
  public void close()
    throws IOException
  {
    if (!this._closed)
    {
      this._closed = true;
      this._nodeCursor = null;
      this._currToken = null;
    }
  }
  
  protected JsonNode currentNode()
  {
    if ((this._closed) || (this._nodeCursor == null)) {}
    for (JsonNode localJsonNode = null;; localJsonNode = this._nodeCursor.currentNode()) {
      return localJsonNode;
    }
  }
  
  protected JsonNode currentNumericNode()
    throws JsonParseException
  {
    JsonNode localJsonNode = currentNode();
    if ((localJsonNode == null) || (!localJsonNode.isNumber()))
    {
      if (localJsonNode == null) {}
      for (Object localObject = null;; localObject = localJsonNode.asToken()) {
        throw _constructError("Current token (" + localObject + ") not numeric, can not use numeric value accessors");
      }
    }
    return localJsonNode;
  }
  
  public BigInteger getBigIntegerValue()
    throws IOException, JsonParseException
  {
    return currentNumericNode().bigIntegerValue();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException, JsonParseException
  {
    JsonNode localJsonNode = currentNode();
    byte[] arrayOfByte;
    if (localJsonNode != null)
    {
      arrayOfByte = localJsonNode.binaryValue();
      if (arrayOfByte == null) {}
    }
    for (;;)
    {
      return arrayOfByte;
      if (localJsonNode.isPojo())
      {
        Object localObject = ((POJONode)localJsonNode).getPojo();
        if ((localObject instanceof byte[]))
        {
          arrayOfByte = (byte[])localObject;
          continue;
        }
      }
      arrayOfByte = null;
    }
  }
  
  public ObjectCodec getCodec()
  {
    return this._objectCodec;
  }
  
  public JsonLocation getCurrentLocation()
  {
    return JsonLocation.NA;
  }
  
  public String getCurrentName()
  {
    if (this._nodeCursor == null) {}
    for (String str = null;; str = this._nodeCursor.getCurrentName()) {
      return str;
    }
  }
  
  public BigDecimal getDecimalValue()
    throws IOException, JsonParseException
  {
    return currentNumericNode().decimalValue();
  }
  
  public double getDoubleValue()
    throws IOException, JsonParseException
  {
    return currentNumericNode().doubleValue();
  }
  
  public Object getEmbeddedObject()
  {
    JsonNode localJsonNode;
    Object localObject;
    if (!this._closed)
    {
      localJsonNode = currentNode();
      if (localJsonNode != null) {
        if (localJsonNode.isPojo()) {
          localObject = ((POJONode)localJsonNode).getPojo();
        }
      }
    }
    for (;;)
    {
      return localObject;
      if (localJsonNode.isBinary()) {
        localObject = ((BinaryNode)localJsonNode).binaryValue();
      } else {
        localObject = null;
      }
    }
  }
  
  public float getFloatValue()
    throws IOException, JsonParseException
  {
    return (float)currentNumericNode().doubleValue();
  }
  
  public int getIntValue()
    throws IOException, JsonParseException
  {
    return currentNumericNode().intValue();
  }
  
  public long getLongValue()
    throws IOException, JsonParseException
  {
    return currentNumericNode().longValue();
  }
  
  public JsonParser.NumberType getNumberType()
    throws IOException, JsonParseException
  {
    JsonNode localJsonNode = currentNumericNode();
    if (localJsonNode == null) {}
    for (JsonParser.NumberType localNumberType = null;; localNumberType = localJsonNode.numberType()) {
      return localNumberType;
    }
  }
  
  public Number getNumberValue()
    throws IOException, JsonParseException
  {
    return currentNumericNode().numberValue();
  }
  
  public JsonStreamContext getParsingContext()
  {
    return this._nodeCursor;
  }
  
  public String getText()
  {
    String str = null;
    if (this._closed) {
      label8:
      break label56;
    }
    for (;;)
    {
      return str;
      switch (this._currToken)
      {
      default: 
        if (this._currToken != null) {
          str = this._currToken.asString();
        }
        break;
      case ???: 
        str = this._nodeCursor.getCurrentName();
        break;
      case ???: 
        str = currentNode().textValue();
        break;
      case ???: 
      case ???: 
        str = String.valueOf(currentNode().numberValue());
        break;
      case ???: 
        label56:
        JsonNode localJsonNode = currentNode();
        if ((localJsonNode == null) || (!localJsonNode.isBinary())) {
          break label8;
        }
        str = localJsonNode.asText();
      }
    }
  }
  
  public char[] getTextCharacters()
    throws IOException, JsonParseException
  {
    return getText().toCharArray();
  }
  
  public int getTextLength()
    throws IOException, JsonParseException
  {
    return getText().length();
  }
  
  public int getTextOffset()
    throws IOException, JsonParseException
  {
    return 0;
  }
  
  public JsonLocation getTokenLocation()
  {
    return JsonLocation.NA;
  }
  
  public boolean hasTextCharacters()
  {
    return false;
  }
  
  public boolean isClosed()
  {
    return this._closed;
  }
  
  public JsonToken nextToken()
    throws IOException, JsonParseException
  {
    JsonToken localJsonToken1 = null;
    if (this._nextToken != null)
    {
      this._currToken = this._nextToken;
      this._nextToken = null;
      localJsonToken1 = this._currToken;
    }
    for (;;)
    {
      return localJsonToken1;
      if (this._startContainer)
      {
        this._startContainer = false;
        if (!this._nodeCursor.currentHasChildren())
        {
          if (this._currToken == JsonToken.START_OBJECT) {}
          for (JsonToken localJsonToken2 = JsonToken.END_OBJECT;; localJsonToken2 = JsonToken.END_ARRAY)
          {
            this._currToken = localJsonToken2;
            localJsonToken1 = this._currToken;
            break;
          }
        }
        this._nodeCursor = this._nodeCursor.iterateChildren();
        this._currToken = this._nodeCursor.nextToken();
        if ((this._currToken == JsonToken.START_OBJECT) || (this._currToken == JsonToken.START_ARRAY)) {
          this._startContainer = true;
        }
        localJsonToken1 = this._currToken;
      }
      else if (this._nodeCursor == null)
      {
        this._closed = true;
      }
      else
      {
        this._currToken = this._nodeCursor.nextToken();
        if (this._currToken != null)
        {
          if ((this._currToken == JsonToken.START_OBJECT) || (this._currToken == JsonToken.START_ARRAY)) {
            this._startContainer = true;
          }
          localJsonToken1 = this._currToken;
        }
        else
        {
          this._currToken = this._nodeCursor.endToken();
          this._nodeCursor = this._nodeCursor.getParent();
          localJsonToken1 = this._currToken;
        }
      }
    }
  }
  
  public void overrideCurrentName(String paramString)
  {
    if (this._nodeCursor != null) {
      this._nodeCursor.overrideCurrentName(paramString);
    }
  }
  
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException, JsonParseException
  {
    int i = 0;
    byte[] arrayOfByte = getBinaryValue(paramBase64Variant);
    if (arrayOfByte != null)
    {
      paramOutputStream.write(arrayOfByte, 0, arrayOfByte.length);
      i = arrayOfByte.length;
    }
    return i;
  }
  
  public void setCodec(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
  }
  
  public JsonParser skipChildren()
    throws IOException, JsonParseException
  {
    if (this._currToken == JsonToken.START_OBJECT)
    {
      this._startContainer = false;
      this._currToken = JsonToken.END_OBJECT;
    }
    for (;;)
    {
      return this;
      if (this._currToken == JsonToken.START_ARRAY)
      {
        this._startContainer = false;
        this._currToken = JsonToken.END_ARRAY;
      }
    }
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/TreeTraversingParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */