package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.util.EmptyIterator;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public abstract class JsonNode
  extends JsonSerializable.Base
  implements TreeNode, Iterable<JsonNode>
{
  protected abstract JsonNode _at(JsonPointer paramJsonPointer);
  
  public boolean asBoolean()
  {
    return asBoolean(false);
  }
  
  public boolean asBoolean(boolean paramBoolean)
  {
    return paramBoolean;
  }
  
  public double asDouble()
  {
    return asDouble(0.0D);
  }
  
  public double asDouble(double paramDouble)
  {
    return paramDouble;
  }
  
  public int asInt()
  {
    return asInt(0);
  }
  
  public int asInt(int paramInt)
  {
    return paramInt;
  }
  
  public long asLong()
  {
    return asLong(0L);
  }
  
  public long asLong(long paramLong)
  {
    return paramLong;
  }
  
  public abstract String asText();
  
  public String asText(String paramString)
  {
    String str = asText();
    if (str == null) {}
    for (;;)
    {
      return paramString;
      paramString = str;
    }
  }
  
  public final JsonNode at(JsonPointer paramJsonPointer)
  {
    if (paramJsonPointer.matches()) {}
    for (;;)
    {
      return this;
      JsonNode localJsonNode = _at(paramJsonPointer);
      if (localJsonNode == null) {
        this = MissingNode.getInstance();
      } else {
        this = localJsonNode.at(paramJsonPointer.tail());
      }
    }
  }
  
  public final JsonNode at(String paramString)
  {
    return at(JsonPointer.compile(paramString));
  }
  
  public BigInteger bigIntegerValue()
  {
    return BigInteger.ZERO;
  }
  
  public byte[] binaryValue()
    throws IOException
  {
    return null;
  }
  
  public boolean booleanValue()
  {
    return false;
  }
  
  public boolean canConvertToInt()
  {
    return false;
  }
  
  public boolean canConvertToLong()
  {
    return false;
  }
  
  public BigDecimal decimalValue()
  {
    return BigDecimal.ZERO;
  }
  
  public abstract <T extends JsonNode> T deepCopy();
  
  public double doubleValue()
  {
    return 0.0D;
  }
  
  public Iterator<JsonNode> elements()
  {
    return EmptyIterator.instance();
  }
  
  public abstract boolean equals(Object paramObject);
  
  public boolean equals(Comparator<JsonNode> paramComparator, JsonNode paramJsonNode)
  {
    if (paramComparator.compare(this, paramJsonNode) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Iterator<String> fieldNames()
  {
    return EmptyIterator.instance();
  }
  
  public Iterator<Map.Entry<String, JsonNode>> fields()
  {
    return EmptyIterator.instance();
  }
  
  public abstract JsonNode findParent(String paramString);
  
  public final List<JsonNode> findParents(String paramString)
  {
    List localList = findParents(paramString, null);
    if (localList == null) {
      localList = Collections.emptyList();
    }
    return localList;
  }
  
  public abstract List<JsonNode> findParents(String paramString, List<JsonNode> paramList);
  
  public abstract JsonNode findPath(String paramString);
  
  public abstract JsonNode findValue(String paramString);
  
  public final List<JsonNode> findValues(String paramString)
  {
    List localList = findValues(paramString, null);
    if (localList == null) {
      localList = Collections.emptyList();
    }
    return localList;
  }
  
  public abstract List<JsonNode> findValues(String paramString, List<JsonNode> paramList);
  
  public final List<String> findValuesAsText(String paramString)
  {
    List localList = findValuesAsText(paramString, null);
    if (localList == null) {
      localList = Collections.emptyList();
    }
    return localList;
  }
  
  public abstract List<String> findValuesAsText(String paramString, List<String> paramList);
  
  public float floatValue()
  {
    return 0.0F;
  }
  
  public abstract JsonNode get(int paramInt);
  
  public JsonNode get(String paramString)
  {
    return null;
  }
  
  public abstract JsonNodeType getNodeType();
  
  public boolean has(int paramInt)
  {
    if (get(paramInt) != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean has(String paramString)
  {
    if (get(paramString) != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasNonNull(int paramInt)
  {
    JsonNode localJsonNode = get(paramInt);
    if ((localJsonNode != null) && (!localJsonNode.isNull())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasNonNull(String paramString)
  {
    JsonNode localJsonNode = get(paramString);
    if ((localJsonNode != null) && (!localJsonNode.isNull())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int intValue()
  {
    return 0;
  }
  
  public final boolean isArray()
  {
    if (getNodeType() == JsonNodeType.ARRAY) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isBigDecimal()
  {
    return false;
  }
  
  public boolean isBigInteger()
  {
    return false;
  }
  
  public final boolean isBinary()
  {
    if (getNodeType() == JsonNodeType.BINARY) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isBoolean()
  {
    if (getNodeType() == JsonNodeType.BOOLEAN) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isContainerNode()
  {
    JsonNodeType localJsonNodeType = getNodeType();
    if ((localJsonNodeType == JsonNodeType.OBJECT) || (localJsonNodeType == JsonNodeType.ARRAY)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isDouble()
  {
    return false;
  }
  
  public boolean isFloat()
  {
    return false;
  }
  
  public boolean isFloatingPointNumber()
  {
    return false;
  }
  
  public boolean isInt()
  {
    return false;
  }
  
  public boolean isIntegralNumber()
  {
    return false;
  }
  
  public boolean isLong()
  {
    return false;
  }
  
  public final boolean isMissingNode()
  {
    if (getNodeType() == JsonNodeType.MISSING) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isNull()
  {
    if (getNodeType() == JsonNodeType.NULL) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isNumber()
  {
    if (getNodeType() == JsonNodeType.NUMBER) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isObject()
  {
    if (getNodeType() == JsonNodeType.OBJECT) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isPojo()
  {
    if (getNodeType() == JsonNodeType.POJO) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isShort()
  {
    return false;
  }
  
  public final boolean isTextual()
  {
    if (getNodeType() == JsonNodeType.STRING) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isValueNode()
  {
    switch (getNodeType())
    {
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final Iterator<JsonNode> iterator()
  {
    return elements();
  }
  
  public long longValue()
  {
    return 0L;
  }
  
  public Number numberValue()
  {
    return null;
  }
  
  public abstract JsonNode path(int paramInt);
  
  public abstract JsonNode path(String paramString);
  
  public short shortValue()
  {
    return 0;
  }
  
  public int size()
  {
    return 0;
  }
  
  public String textValue()
  {
    return null;
  }
  
  public abstract String toString();
  
  public JsonNode with(String paramString)
  {
    throw new UnsupportedOperationException("JsonNode not of type ObjectNode (but " + getClass().getName() + "), can not call with() on it");
  }
  
  public JsonNode withArray(String paramString)
  {
    throw new UnsupportedOperationException("JsonNode not of type ObjectNode (but " + getClass().getName() + "), can not call withArray() on it");
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/JsonNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */