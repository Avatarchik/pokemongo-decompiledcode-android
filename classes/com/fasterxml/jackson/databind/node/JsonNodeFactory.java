package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.databind.util.RawValue;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNodeFactory
  implements Serializable, JsonNodeCreator
{
  private static final JsonNodeFactory decimalsAsIs = new JsonNodeFactory(true);
  private static final JsonNodeFactory decimalsNormalized = new JsonNodeFactory(false);
  public static final JsonNodeFactory instance = decimalsNormalized;
  private static final long serialVersionUID = 1L;
  private final boolean _cfgBigDecimalExact;
  
  protected JsonNodeFactory()
  {
    this(false);
  }
  
  public JsonNodeFactory(boolean paramBoolean)
  {
    this._cfgBigDecimalExact = paramBoolean;
  }
  
  public static JsonNodeFactory withExactBigDecimals(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (JsonNodeFactory localJsonNodeFactory = decimalsAsIs;; localJsonNodeFactory = decimalsNormalized) {
      return localJsonNodeFactory;
    }
  }
  
  protected boolean _inIntRange(long paramLong)
  {
    if ((int)paramLong == paramLong) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public ArrayNode arrayNode()
  {
    return new ArrayNode(this);
  }
  
  public BinaryNode binaryNode(byte[] paramArrayOfByte)
  {
    return BinaryNode.valueOf(paramArrayOfByte);
  }
  
  public BinaryNode binaryNode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return BinaryNode.valueOf(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public BooleanNode booleanNode(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (BooleanNode localBooleanNode = BooleanNode.getTrue();; localBooleanNode = BooleanNode.getFalse()) {
      return localBooleanNode;
    }
  }
  
  public NullNode nullNode()
  {
    return NullNode.getInstance();
  }
  
  public NumericNode numberNode(byte paramByte)
  {
    return IntNode.valueOf(paramByte);
  }
  
  public NumericNode numberNode(double paramDouble)
  {
    return DoubleNode.valueOf(paramDouble);
  }
  
  public NumericNode numberNode(float paramFloat)
  {
    return FloatNode.valueOf(paramFloat);
  }
  
  public NumericNode numberNode(int paramInt)
  {
    return IntNode.valueOf(paramInt);
  }
  
  public NumericNode numberNode(long paramLong)
  {
    return LongNode.valueOf(paramLong);
  }
  
  public NumericNode numberNode(BigDecimal paramBigDecimal)
  {
    DecimalNode localDecimalNode;
    if (this._cfgBigDecimalExact) {
      localDecimalNode = DecimalNode.valueOf(paramBigDecimal);
    }
    for (;;)
    {
      return localDecimalNode;
      if (paramBigDecimal.compareTo(BigDecimal.ZERO) == 0) {
        localDecimalNode = DecimalNode.ZERO;
      } else {
        localDecimalNode = DecimalNode.valueOf(paramBigDecimal.stripTrailingZeros());
      }
    }
  }
  
  public NumericNode numberNode(BigInteger paramBigInteger)
  {
    return BigIntegerNode.valueOf(paramBigInteger);
  }
  
  public NumericNode numberNode(short paramShort)
  {
    return ShortNode.valueOf(paramShort);
  }
  
  public ValueNode numberNode(Byte paramByte)
  {
    if (paramByte == null) {}
    for (Object localObject = nullNode();; localObject = IntNode.valueOf(paramByte.intValue())) {
      return (ValueNode)localObject;
    }
  }
  
  public ValueNode numberNode(Double paramDouble)
  {
    if (paramDouble == null) {}
    for (Object localObject = nullNode();; localObject = DoubleNode.valueOf(paramDouble.doubleValue())) {
      return (ValueNode)localObject;
    }
  }
  
  public ValueNode numberNode(Float paramFloat)
  {
    if (paramFloat == null) {}
    for (Object localObject = nullNode();; localObject = FloatNode.valueOf(paramFloat.floatValue())) {
      return (ValueNode)localObject;
    }
  }
  
  public ValueNode numberNode(Integer paramInteger)
  {
    if (paramInteger == null) {}
    for (Object localObject = nullNode();; localObject = IntNode.valueOf(paramInteger.intValue())) {
      return (ValueNode)localObject;
    }
  }
  
  public ValueNode numberNode(Long paramLong)
  {
    if (paramLong == null) {}
    for (Object localObject = nullNode();; localObject = LongNode.valueOf(paramLong.longValue())) {
      return (ValueNode)localObject;
    }
  }
  
  public ValueNode numberNode(Short paramShort)
  {
    if (paramShort == null) {}
    for (Object localObject = nullNode();; localObject = ShortNode.valueOf(paramShort.shortValue())) {
      return (ValueNode)localObject;
    }
  }
  
  public ObjectNode objectNode()
  {
    return new ObjectNode(this);
  }
  
  public ValueNode pojoNode(Object paramObject)
  {
    return new POJONode(paramObject);
  }
  
  public ValueNode rawValueNode(RawValue paramRawValue)
  {
    return new POJONode(paramRawValue);
  }
  
  public TextNode textNode(String paramString)
  {
    return TextNode.valueOf(paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/JsonNodeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */