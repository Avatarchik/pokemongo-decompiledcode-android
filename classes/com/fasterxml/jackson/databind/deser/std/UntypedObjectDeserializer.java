package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JacksonStdImpl
public class UntypedObjectDeserializer
  extends StdDeserializer<Object>
  implements ResolvableDeserializer, ContextualDeserializer
{
  protected static final Object[] NO_OBJECTS = new Object[0];
  @Deprecated
  public static final UntypedObjectDeserializer instance = new UntypedObjectDeserializer(null, null);
  private static final long serialVersionUID = 1L;
  protected JsonDeserializer<Object> _listDeserializer;
  protected JavaType _listType;
  protected JsonDeserializer<Object> _mapDeserializer;
  protected JavaType _mapType;
  protected JsonDeserializer<Object> _numberDeserializer;
  protected JsonDeserializer<Object> _stringDeserializer;
  
  @Deprecated
  public UntypedObjectDeserializer()
  {
    this(null, null);
  }
  
  public UntypedObjectDeserializer(JavaType paramJavaType1, JavaType paramJavaType2)
  {
    super(Object.class);
    this._listType = paramJavaType1;
    this._mapType = paramJavaType2;
  }
  
  public UntypedObjectDeserializer(UntypedObjectDeserializer paramUntypedObjectDeserializer, JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, JsonDeserializer<?> paramJsonDeserializer3, JsonDeserializer<?> paramJsonDeserializer4)
  {
    super(Object.class);
    this._mapDeserializer = paramJsonDeserializer1;
    this._listDeserializer = paramJsonDeserializer2;
    this._stringDeserializer = paramJsonDeserializer3;
    this._numberDeserializer = paramJsonDeserializer4;
    this._listType = paramUntypedObjectDeserializer._listType;
    this._mapType = paramUntypedObjectDeserializer._mapType;
  }
  
  protected JsonDeserializer<Object> _clearIfStdImpl(JsonDeserializer<Object> paramJsonDeserializer)
  {
    if (ClassUtil.isJacksonStdImpl(paramJsonDeserializer)) {
      paramJsonDeserializer = null;
    }
    return paramJsonDeserializer;
  }
  
  protected JsonDeserializer<Object> _findCustomDeser(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    return paramDeserializationContext.findNonContextualValueDeserializer(paramJavaType);
  }
  
  protected JsonDeserializer<?> _withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, JsonDeserializer<?> paramJsonDeserializer3, JsonDeserializer<?> paramJsonDeserializer4)
  {
    return new UntypedObjectDeserializer(this, paramJsonDeserializer1, paramJsonDeserializer2, paramJsonDeserializer3, paramJsonDeserializer4);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    if ((this._stringDeserializer == null) && (this._numberDeserializer == null) && (this._mapDeserializer == null) && (this._listDeserializer == null) && (getClass() == UntypedObjectDeserializer.class)) {
      this = Vanilla.std;
    }
    return this;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 2: 
    case 4: 
    default: 
      throw paramDeserializationContext.mappingException(Object.class);
    case 1: 
    case 5: 
      if (this._mapDeserializer != null) {
        localObject = this._mapDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      break;
    }
    for (;;)
    {
      return localObject;
      localObject = mapObject(paramJsonParser, paramDeserializationContext);
      continue;
      if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY))
      {
        localObject = mapArrayToArray(paramJsonParser, paramDeserializationContext);
      }
      else if (this._listDeserializer != null)
      {
        localObject = this._listDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      else
      {
        localObject = mapArray(paramJsonParser, paramDeserializationContext);
        continue;
        localObject = paramJsonParser.getEmbeddedObject();
        continue;
        if (this._stringDeserializer != null)
        {
          localObject = this._stringDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        }
        else
        {
          localObject = paramJsonParser.getText();
          continue;
          if (this._numberDeserializer != null)
          {
            localObject = this._numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          }
          else if (paramDeserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
          {
            localObject = _coerceIntegral(paramJsonParser, paramDeserializationContext);
          }
          else
          {
            localObject = paramJsonParser.getNumberValue();
            continue;
            if (this._numberDeserializer != null)
            {
              localObject = this._numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
            }
            else if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
            {
              localObject = paramJsonParser.getDecimalValue();
            }
            else
            {
              localObject = Double.valueOf(paramJsonParser.getDoubleValue());
              continue;
              localObject = Boolean.TRUE;
              continue;
              localObject = Boolean.FALSE;
              continue;
              localObject = null;
            }
          }
        }
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    Object localObject;
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 2: 
    case 4: 
    default: 
      throw paramDeserializationContext.mappingException(Object.class);
    case 1: 
    case 3: 
    case 5: 
      localObject = paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
    }
    for (;;)
    {
      return localObject;
      localObject = paramJsonParser.getEmbeddedObject();
      continue;
      if (this._stringDeserializer != null)
      {
        localObject = this._stringDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      else
      {
        localObject = paramJsonParser.getText();
        continue;
        if (this._numberDeserializer != null)
        {
          localObject = this._numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        }
        else if (paramDeserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
        {
          localObject = _coerceIntegral(paramJsonParser, paramDeserializationContext);
        }
        else
        {
          localObject = paramJsonParser.getNumberValue();
          continue;
          if (this._numberDeserializer != null)
          {
            localObject = this._numberDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          }
          else if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
          {
            localObject = paramJsonParser.getDecimalValue();
          }
          else
          {
            localObject = Double.valueOf(paramJsonParser.getDoubleValue());
            continue;
            localObject = Boolean.TRUE;
            continue;
            localObject = Boolean.FALSE;
            continue;
            localObject = null;
          }
        }
      }
    }
  }
  
  public boolean isCachable()
  {
    return true;
  }
  
  protected Object mapArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1;
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
      localObject1 = new ArrayList(2);
    }
    Object localObject2;
    Object localObject3;
    for (;;)
    {
      return localObject1;
      localObject2 = deserialize(paramJsonParser, paramDeserializationContext);
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      {
        localObject1 = new ArrayList(2);
        ((ArrayList)localObject1).add(localObject2);
      }
      else
      {
        localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          break;
        }
        localObject1 = new ArrayList(2);
        ((ArrayList)localObject1).add(localObject2);
        ((ArrayList)localObject1).add(localObject3);
      }
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = localObjectBuffer.resetAndStart();
    int i = 0 + 1;
    arrayOfObject[0] = localObject2;
    int j = i + 1;
    arrayOfObject[i] = localObject3;
    int k = j;
    for (;;)
    {
      Object localObject4 = deserialize(paramJsonParser, paramDeserializationContext);
      k++;
      if (j >= arrayOfObject.length)
      {
        arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
        j = 0;
      }
      int m = j + 1;
      arrayOfObject[j] = localObject4;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      {
        ArrayList localArrayList = new ArrayList(k);
        localObjectBuffer.completeAndClearBuffer(arrayOfObject, m, localArrayList);
        localObject1 = localArrayList;
        break;
      }
      j = m;
    }
  }
  
  protected Object[] mapArrayToArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object[] arrayOfObject2;
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
    {
      arrayOfObject2 = NO_OBJECTS;
      return arrayOfObject2;
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject1 = localObjectBuffer.resetAndStart();
    int j;
    for (int i = 0;; i = j)
    {
      Object localObject = deserialize(paramJsonParser, paramDeserializationContext);
      if (i >= arrayOfObject1.length)
      {
        arrayOfObject1 = localObjectBuffer.appendCompletedChunk(arrayOfObject1);
        i = 0;
      }
      j = i + 1;
      arrayOfObject1[i] = localObject;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      {
        arrayOfObject2 = localObjectBuffer.completeAndClearBuffer(arrayOfObject1, j);
        break;
      }
    }
  }
  
  protected Object mapObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    String str1;
    LinkedHashMap localLinkedHashMap;
    if (localJsonToken == JsonToken.START_OBJECT)
    {
      str1 = paramJsonParser.nextFieldName();
      if (str1 != null) {
        break label78;
      }
      localLinkedHashMap = new LinkedHashMap(2);
    }
    for (;;)
    {
      return localLinkedHashMap;
      if (localJsonToken == JsonToken.FIELD_NAME)
      {
        str1 = paramJsonParser.getCurrentName();
        break;
      }
      if (localJsonToken != JsonToken.END_OBJECT) {
        throw paramDeserializationContext.mappingException(handledType(), paramJsonParser.getCurrentToken());
      }
      str1 = null;
      break;
      label78:
      paramJsonParser.nextToken();
      Object localObject1 = deserialize(paramJsonParser, paramDeserializationContext);
      String str2 = paramJsonParser.nextFieldName();
      if (str2 == null)
      {
        localLinkedHashMap = new LinkedHashMap(2);
        localLinkedHashMap.put(str1, localObject1);
      }
      else
      {
        paramJsonParser.nextToken();
        Object localObject2 = deserialize(paramJsonParser, paramDeserializationContext);
        String str3 = paramJsonParser.nextFieldName();
        if (str3 == null)
        {
          localLinkedHashMap = new LinkedHashMap(4);
          localLinkedHashMap.put(str1, localObject1);
          localLinkedHashMap.put(str2, localObject2);
        }
        else
        {
          localLinkedHashMap = new LinkedHashMap();
          localLinkedHashMap.put(str1, localObject1);
          localLinkedHashMap.put(str2, localObject2);
          do
          {
            paramJsonParser.nextToken();
            localLinkedHashMap.put(str3, deserialize(paramJsonParser, paramDeserializationContext));
            str3 = paramJsonParser.nextFieldName();
          } while (str3 != null);
        }
      }
    }
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    JavaType localJavaType1 = paramDeserializationContext.constructType(Object.class);
    JavaType localJavaType2 = paramDeserializationContext.constructType(String.class);
    TypeFactory localTypeFactory = paramDeserializationContext.getTypeFactory();
    if (this._listType == null)
    {
      this._listDeserializer = _clearIfStdImpl(_findCustomDeser(paramDeserializationContext, localTypeFactory.constructCollectionType(List.class, localJavaType1)));
      if (this._mapType != null) {
        break label197;
      }
    }
    label197:
    for (this._mapDeserializer = _clearIfStdImpl(_findCustomDeser(paramDeserializationContext, localTypeFactory.constructMapType(Map.class, localJavaType2, localJavaType1)));; this._mapDeserializer = _findCustomDeser(paramDeserializationContext, this._mapType))
    {
      this._stringDeserializer = _clearIfStdImpl(_findCustomDeser(paramDeserializationContext, localJavaType2));
      this._numberDeserializer = _clearIfStdImpl(_findCustomDeser(paramDeserializationContext, localTypeFactory.constructType(Number.class)));
      JavaType localJavaType3 = TypeFactory.unknownType();
      this._mapDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._mapDeserializer, null, localJavaType3);
      this._listDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._listDeserializer, null, localJavaType3);
      this._stringDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._stringDeserializer, null, localJavaType3);
      this._numberDeserializer = paramDeserializationContext.handleSecondaryContextualization(this._numberDeserializer, null, localJavaType3);
      return;
      this._listDeserializer = _findCustomDeser(paramDeserializationContext, this._listType);
      break;
    }
  }
  
  @JacksonStdImpl
  public static class Vanilla
    extends StdDeserializer<Object>
  {
    private static final long serialVersionUID = 1L;
    public static final Vanilla std = new Vanilla();
    
    public Vanilla()
    {
      super();
    }
    
    public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      Object localObject;
      switch (paramJsonParser.getCurrentTokenId())
      {
      case 2: 
      case 4: 
      default: 
        throw paramDeserializationContext.mappingException(Object.class);
      case 1: 
        if (paramJsonParser.nextToken() == JsonToken.END_OBJECT) {
          localObject = new LinkedHashMap(2);
        }
        break;
      }
      for (;;)
      {
        return localObject;
        localObject = mapObject(paramJsonParser, paramDeserializationContext);
        continue;
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
        {
          if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
            localObject = UntypedObjectDeserializer.NO_OBJECTS;
          } else {
            localObject = new ArrayList(2);
          }
        }
        else if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY))
        {
          localObject = mapArrayToArray(paramJsonParser, paramDeserializationContext);
        }
        else
        {
          localObject = mapArray(paramJsonParser, paramDeserializationContext);
          continue;
          localObject = paramJsonParser.getEmbeddedObject();
          continue;
          localObject = paramJsonParser.getText();
          continue;
          if (paramDeserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS))
          {
            localObject = _coerceIntegral(paramJsonParser, paramDeserializationContext);
          }
          else
          {
            localObject = paramJsonParser.getNumberValue();
            continue;
            if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
            {
              localObject = paramJsonParser.getDecimalValue();
            }
            else
            {
              localObject = Double.valueOf(paramJsonParser.getDoubleValue());
              continue;
              localObject = Boolean.TRUE;
              continue;
              localObject = Boolean.FALSE;
              continue;
              localObject = null;
            }
          }
        }
      }
    }
    
    public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
      throws IOException
    {
      Object localObject;
      switch (paramJsonParser.getCurrentTokenId())
      {
      case 2: 
      case 4: 
      default: 
        throw paramDeserializationContext.mappingException(Object.class);
      case 1: 
      case 3: 
      case 5: 
        localObject = paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
      }
      for (;;)
      {
        return localObject;
        localObject = paramJsonParser.getText();
        continue;
        if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS))
        {
          localObject = paramJsonParser.getBigIntegerValue();
        }
        else
        {
          localObject = paramJsonParser.getNumberValue();
          continue;
          if (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
          {
            localObject = paramJsonParser.getDecimalValue();
          }
          else
          {
            localObject = Double.valueOf(paramJsonParser.getDoubleValue());
            continue;
            localObject = Boolean.TRUE;
            continue;
            localObject = Boolean.FALSE;
            continue;
            localObject = paramJsonParser.getEmbeddedObject();
            continue;
            localObject = null;
          }
        }
      }
    }
    
    protected Object mapArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      Object localObject1 = deserialize(paramJsonParser, paramDeserializationContext);
      Object localObject2;
      if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
      {
        localObject2 = new ArrayList(2);
        ((ArrayList)localObject2).add(localObject1);
      }
      Object localObject3;
      for (;;)
      {
        return localObject2;
        localObject3 = deserialize(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          break;
        }
        localObject2 = new ArrayList(2);
        ((ArrayList)localObject2).add(localObject1);
        ((ArrayList)localObject2).add(localObject3);
      }
      ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
      Object[] arrayOfObject = localObjectBuffer.resetAndStart();
      int i = 0 + 1;
      arrayOfObject[0] = localObject1;
      int j = i + 1;
      arrayOfObject[i] = localObject3;
      int k = j;
      for (;;)
      {
        Object localObject4 = deserialize(paramJsonParser, paramDeserializationContext);
        k++;
        if (j >= arrayOfObject.length)
        {
          arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
          j = 0;
        }
        int m = j + 1;
        arrayOfObject[j] = localObject4;
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
        {
          ArrayList localArrayList = new ArrayList(k);
          localObjectBuffer.completeAndClearBuffer(arrayOfObject, m, localArrayList);
          localObject2 = localArrayList;
          break;
        }
        j = m;
      }
    }
    
    protected Object[] mapArrayToArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
      Object[] arrayOfObject = localObjectBuffer.resetAndStart();
      int j;
      for (int i = 0;; i = j)
      {
        Object localObject = deserialize(paramJsonParser, paramDeserializationContext);
        if (i >= arrayOfObject.length)
        {
          arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
          i = 0;
        }
        j = i + 1;
        arrayOfObject[i] = localObject;
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
          return localObjectBuffer.completeAndClearBuffer(arrayOfObject, j);
        }
      }
    }
    
    protected Object mapObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      String str1 = paramJsonParser.getText();
      paramJsonParser.nextToken();
      Object localObject1 = deserialize(paramJsonParser, paramDeserializationContext);
      String str2 = paramJsonParser.nextFieldName();
      LinkedHashMap localLinkedHashMap;
      if (str2 == null)
      {
        localLinkedHashMap = new LinkedHashMap(2);
        localLinkedHashMap.put(str1, localObject1);
      }
      for (;;)
      {
        return localLinkedHashMap;
        paramJsonParser.nextToken();
        Object localObject2 = deserialize(paramJsonParser, paramDeserializationContext);
        String str3 = paramJsonParser.nextFieldName();
        if (str3 == null)
        {
          localLinkedHashMap = new LinkedHashMap(4);
          localLinkedHashMap.put(str1, localObject1);
          localLinkedHashMap.put(str2, localObject2);
        }
        else
        {
          localLinkedHashMap = new LinkedHashMap();
          localLinkedHashMap.put(str1, localObject1);
          localLinkedHashMap.put(str2, localObject2);
          do
          {
            paramJsonParser.nextToken();
            localLinkedHashMap.put(str3, deserialize(paramJsonParser, paramDeserializationContext));
            str3 = paramJsonParser.nextFieldName();
          } while (str3 != null);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/UntypedObjectDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */