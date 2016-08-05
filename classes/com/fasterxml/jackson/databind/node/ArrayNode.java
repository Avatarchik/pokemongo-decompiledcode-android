package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayNode
  extends ContainerNode<ArrayNode>
{
  private final List<JsonNode> _children = new ArrayList();
  
  public ArrayNode(JsonNodeFactory paramJsonNodeFactory)
  {
    super(paramJsonNodeFactory);
  }
  
  protected ArrayNode _add(JsonNode paramJsonNode)
  {
    this._children.add(paramJsonNode);
    return this;
  }
  
  protected JsonNode _at(JsonPointer paramJsonPointer)
  {
    return get(paramJsonPointer.getMatchingIndex());
  }
  
  protected boolean _childrenEqual(ArrayNode paramArrayNode)
  {
    return this._children.equals(paramArrayNode._children);
  }
  
  protected ArrayNode _insert(int paramInt, JsonNode paramJsonNode)
  {
    if (paramInt < 0) {
      this._children.add(0, paramJsonNode);
    }
    for (;;)
    {
      return this;
      if (paramInt >= this._children.size()) {
        this._children.add(paramJsonNode);
      } else {
        this._children.add(paramInt, paramJsonNode);
      }
    }
  }
  
  public ArrayNode add(double paramDouble)
  {
    return _add(numberNode(paramDouble));
  }
  
  public ArrayNode add(float paramFloat)
  {
    return _add(numberNode(paramFloat));
  }
  
  public ArrayNode add(int paramInt)
  {
    _add(numberNode(paramInt));
    return this;
  }
  
  public ArrayNode add(long paramLong)
  {
    return _add(numberNode(paramLong));
  }
  
  public ArrayNode add(JsonNode paramJsonNode)
  {
    if (paramJsonNode == null) {
      paramJsonNode = nullNode();
    }
    _add(paramJsonNode);
    return this;
  }
  
  public ArrayNode add(Boolean paramBoolean)
  {
    if (paramBoolean == null) {}
    for (ArrayNode localArrayNode = addNull();; localArrayNode = _add(booleanNode(paramBoolean.booleanValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode add(Double paramDouble)
  {
    if (paramDouble == null) {}
    for (ArrayNode localArrayNode = addNull();; localArrayNode = _add(numberNode(paramDouble.doubleValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode add(Float paramFloat)
  {
    if (paramFloat == null) {}
    for (ArrayNode localArrayNode = addNull();; localArrayNode = _add(numberNode(paramFloat.floatValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode add(Integer paramInteger)
  {
    if (paramInteger == null) {}
    for (ArrayNode localArrayNode = addNull();; localArrayNode = _add(numberNode(paramInteger.intValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode add(Long paramLong)
  {
    if (paramLong == null) {}
    for (ArrayNode localArrayNode = addNull();; localArrayNode = _add(numberNode(paramLong.longValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode add(String paramString)
  {
    if (paramString == null) {}
    for (ArrayNode localArrayNode = addNull();; localArrayNode = _add(textNode(paramString))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode add(BigDecimal paramBigDecimal)
  {
    if (paramBigDecimal == null) {}
    for (ArrayNode localArrayNode = addNull();; localArrayNode = _add(numberNode(paramBigDecimal))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode add(boolean paramBoolean)
  {
    return _add(booleanNode(paramBoolean));
  }
  
  public ArrayNode add(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {}
    for (ArrayNode localArrayNode = addNull();; localArrayNode = _add(binaryNode(paramArrayOfByte))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode addAll(ArrayNode paramArrayNode)
  {
    this._children.addAll(paramArrayNode._children);
    return this;
  }
  
  public ArrayNode addAll(Collection<? extends JsonNode> paramCollection)
  {
    this._children.addAll(paramCollection);
    return this;
  }
  
  public ArrayNode addArray()
  {
    ArrayNode localArrayNode = arrayNode();
    _add(localArrayNode);
    return localArrayNode;
  }
  
  public ArrayNode addNull()
  {
    _add(nullNode());
    return this;
  }
  
  public ObjectNode addObject()
  {
    ObjectNode localObjectNode = objectNode();
    _add(localObjectNode);
    return localObjectNode;
  }
  
  public ArrayNode addPOJO(Object paramObject)
  {
    if (paramObject == null) {
      addNull();
    }
    for (;;)
    {
      return this;
      _add(pojoNode(paramObject));
    }
  }
  
  public ArrayNode addRawValue(RawValue paramRawValue)
  {
    if (paramRawValue == null) {
      addNull();
    }
    for (;;)
    {
      return this;
      _add(rawValueNode(paramRawValue));
    }
  }
  
  public JsonToken asToken()
  {
    return JsonToken.START_ARRAY;
  }
  
  public ArrayNode deepCopy()
  {
    ArrayNode localArrayNode = new ArrayNode(this._nodeFactory);
    Iterator localIterator = this._children.iterator();
    while (localIterator.hasNext())
    {
      JsonNode localJsonNode = (JsonNode)localIterator.next();
      localArrayNode._children.add(localJsonNode.deepCopy());
    }
    return localArrayNode;
  }
  
  public Iterator<JsonNode> elements()
  {
    return this._children.iterator();
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == this) {}
    for (bool = true;; bool = this._children.equals(((ArrayNode)paramObject)._children)) {
      do
      {
        return bool;
      } while ((paramObject == null) || (!(paramObject instanceof ArrayNode)));
    }
  }
  
  public boolean equals(Comparator<JsonNode> paramComparator, JsonNode paramJsonNode)
  {
    boolean bool = false;
    if (!(paramJsonNode instanceof ArrayNode)) {}
    for (;;)
    {
      return bool;
      ArrayNode localArrayNode = (ArrayNode)paramJsonNode;
      int i = this._children.size();
      if (localArrayNode.size() == i)
      {
        List localList1 = this._children;
        List localList2 = localArrayNode._children;
        for (int j = 0;; j++)
        {
          if (j >= i) {
            break label94;
          }
          if (paramComparator.compare(localList1.get(j), localList2.get(j)) != 0) {
            break;
          }
        }
        label94:
        bool = true;
      }
    }
  }
  
  public ObjectNode findParent(String paramString)
  {
    Iterator localIterator = this._children.iterator();
    JsonNode localJsonNode;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonNode = ((JsonNode)localIterator.next()).findParent(paramString);
    } while (localJsonNode == null);
    for (ObjectNode localObjectNode = (ObjectNode)localJsonNode;; localObjectNode = null) {
      return localObjectNode;
    }
  }
  
  public List<JsonNode> findParents(String paramString, List<JsonNode> paramList)
  {
    Iterator localIterator = this._children.iterator();
    while (localIterator.hasNext()) {
      paramList = ((JsonNode)localIterator.next()).findParents(paramString, paramList);
    }
    return paramList;
  }
  
  public JsonNode findValue(String paramString)
  {
    Iterator localIterator = this._children.iterator();
    JsonNode localJsonNode;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonNode = ((JsonNode)localIterator.next()).findValue(paramString);
    } while (localJsonNode == null);
    for (;;)
    {
      return localJsonNode;
      localJsonNode = null;
    }
  }
  
  public List<JsonNode> findValues(String paramString, List<JsonNode> paramList)
  {
    Iterator localIterator = this._children.iterator();
    while (localIterator.hasNext()) {
      paramList = ((JsonNode)localIterator.next()).findValues(paramString, paramList);
    }
    return paramList;
  }
  
  public List<String> findValuesAsText(String paramString, List<String> paramList)
  {
    Iterator localIterator = this._children.iterator();
    while (localIterator.hasNext()) {
      paramList = ((JsonNode)localIterator.next()).findValuesAsText(paramString, paramList);
    }
    return paramList;
  }
  
  public JsonNode get(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this._children.size())) {}
    for (JsonNode localJsonNode = (JsonNode)this._children.get(paramInt);; localJsonNode = null) {
      return localJsonNode;
    }
  }
  
  public JsonNode get(String paramString)
  {
    return null;
  }
  
  public JsonNodeType getNodeType()
  {
    return JsonNodeType.ARRAY;
  }
  
  public int hashCode()
  {
    return this._children.hashCode();
  }
  
  public ArrayNode insert(int paramInt, double paramDouble)
  {
    return _insert(paramInt, numberNode(paramDouble));
  }
  
  public ArrayNode insert(int paramInt, float paramFloat)
  {
    return _insert(paramInt, numberNode(paramFloat));
  }
  
  public ArrayNode insert(int paramInt1, int paramInt2)
  {
    _insert(paramInt1, numberNode(paramInt2));
    return this;
  }
  
  public ArrayNode insert(int paramInt, long paramLong)
  {
    return _insert(paramInt, numberNode(paramLong));
  }
  
  public ArrayNode insert(int paramInt, JsonNode paramJsonNode)
  {
    if (paramJsonNode == null) {
      paramJsonNode = nullNode();
    }
    _insert(paramInt, paramJsonNode);
    return this;
  }
  
  public ArrayNode insert(int paramInt, Boolean paramBoolean)
  {
    if (paramBoolean == null) {}
    for (ArrayNode localArrayNode = insertNull(paramInt);; localArrayNode = _insert(paramInt, booleanNode(paramBoolean.booleanValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode insert(int paramInt, Double paramDouble)
  {
    if (paramDouble == null) {}
    for (ArrayNode localArrayNode = insertNull(paramInt);; localArrayNode = _insert(paramInt, numberNode(paramDouble.doubleValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode insert(int paramInt, Float paramFloat)
  {
    if (paramFloat == null) {}
    for (ArrayNode localArrayNode = insertNull(paramInt);; localArrayNode = _insert(paramInt, numberNode(paramFloat.floatValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode insert(int paramInt, Integer paramInteger)
  {
    if (paramInteger == null) {
      insertNull(paramInt);
    }
    for (;;)
    {
      return this;
      _insert(paramInt, numberNode(paramInteger.intValue()));
    }
  }
  
  public ArrayNode insert(int paramInt, Long paramLong)
  {
    if (paramLong == null) {}
    for (ArrayNode localArrayNode = insertNull(paramInt);; localArrayNode = _insert(paramInt, numberNode(paramLong.longValue()))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode insert(int paramInt, String paramString)
  {
    if (paramString == null) {}
    for (ArrayNode localArrayNode = insertNull(paramInt);; localArrayNode = _insert(paramInt, textNode(paramString))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode insert(int paramInt, BigDecimal paramBigDecimal)
  {
    if (paramBigDecimal == null) {}
    for (ArrayNode localArrayNode = insertNull(paramInt);; localArrayNode = _insert(paramInt, numberNode(paramBigDecimal))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode insert(int paramInt, boolean paramBoolean)
  {
    return _insert(paramInt, booleanNode(paramBoolean));
  }
  
  public ArrayNode insert(int paramInt, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {}
    for (ArrayNode localArrayNode = insertNull(paramInt);; localArrayNode = _insert(paramInt, binaryNode(paramArrayOfByte))) {
      return localArrayNode;
    }
  }
  
  public ArrayNode insertArray(int paramInt)
  {
    ArrayNode localArrayNode = arrayNode();
    _insert(paramInt, localArrayNode);
    return localArrayNode;
  }
  
  public ArrayNode insertNull(int paramInt)
  {
    _insert(paramInt, nullNode());
    return this;
  }
  
  public ObjectNode insertObject(int paramInt)
  {
    ObjectNode localObjectNode = objectNode();
    _insert(paramInt, localObjectNode);
    return localObjectNode;
  }
  
  public ArrayNode insertPOJO(int paramInt, Object paramObject)
  {
    if (paramObject == null) {}
    for (ArrayNode localArrayNode = insertNull(paramInt);; localArrayNode = _insert(paramInt, pojoNode(paramObject))) {
      return localArrayNode;
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider)
  {
    return this._children.isEmpty();
  }
  
  public JsonNode path(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this._children.size())) {}
    for (Object localObject = (JsonNode)this._children.get(paramInt);; localObject = MissingNode.getInstance()) {
      return (JsonNode)localObject;
    }
  }
  
  public JsonNode path(String paramString)
  {
    return MissingNode.getInstance();
  }
  
  public JsonNode remove(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this._children.size())) {}
    for (JsonNode localJsonNode = (JsonNode)this._children.remove(paramInt);; localJsonNode = null) {
      return localJsonNode;
    }
  }
  
  public ArrayNode removeAll()
  {
    this._children.clear();
    return this;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    List localList = this._children;
    int i = localList.size();
    paramJsonGenerator.writeStartArray(i);
    int j = 0;
    if (j < i)
    {
      JsonNode localJsonNode = (JsonNode)localList.get(j);
      if ((localJsonNode instanceof BaseJsonNode)) {
        ((BaseJsonNode)localJsonNode).serialize(paramJsonGenerator, paramSerializerProvider);
      }
      for (;;)
      {
        j++;
        break;
        localJsonNode.serialize(paramJsonGenerator, paramSerializerProvider);
      }
    }
    paramJsonGenerator.writeEndArray();
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramTypeSerializer.writeTypePrefixForArray(this, paramJsonGenerator);
    Iterator localIterator = this._children.iterator();
    while (localIterator.hasNext()) {
      ((BaseJsonNode)localIterator.next()).serialize(paramJsonGenerator, paramSerializerProvider);
    }
    paramTypeSerializer.writeTypeSuffixForArray(this, paramJsonGenerator);
  }
  
  public JsonNode set(int paramInt, JsonNode paramJsonNode)
  {
    if (paramJsonNode == null) {
      paramJsonNode = nullNode();
    }
    if ((paramInt < 0) || (paramInt >= this._children.size())) {
      throw new IndexOutOfBoundsException("Illegal index " + paramInt + ", array size " + size());
    }
    return (JsonNode)this._children.set(paramInt, paramJsonNode);
  }
  
  public int size()
  {
    return this._children.size();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(16 + (size() << 4));
    localStringBuilder.append('[');
    int i = 0;
    int j = this._children.size();
    while (i < j)
    {
      if (i > 0) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append(((JsonNode)this._children.get(i)).toString());
      i++;
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/ArrayNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */