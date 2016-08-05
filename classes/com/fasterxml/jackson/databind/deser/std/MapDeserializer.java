package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer
  extends ContainerDeserializerBase<Map<Object, Object>>
  implements ContextualDeserializer, ResolvableDeserializer
{
  private static final long serialVersionUID = 1L;
  protected JsonDeserializer<Object> _delegateDeserializer;
  protected final boolean _hasDefaultCreator;
  protected HashSet<String> _ignorableProperties;
  protected final KeyDeserializer _keyDeserializer;
  protected final JavaType _mapType;
  protected PropertyBasedCreator _propertyBasedCreator;
  protected boolean _standardStringKey;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public MapDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType);
    this._mapType = paramJavaType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramValueInstantiator;
    this._hasDefaultCreator = paramValueInstantiator.canCreateUsingDefault();
    this._delegateDeserializer = null;
    this._propertyBasedCreator = null;
    this._standardStringKey = _isStdKeyDeser(paramJavaType, paramKeyDeserializer);
  }
  
  protected MapDeserializer(MapDeserializer paramMapDeserializer)
  {
    super(paramMapDeserializer._mapType);
    this._mapType = paramMapDeserializer._mapType;
    this._keyDeserializer = paramMapDeserializer._keyDeserializer;
    this._valueDeserializer = paramMapDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramMapDeserializer._valueTypeDeserializer;
    this._valueInstantiator = paramMapDeserializer._valueInstantiator;
    this._propertyBasedCreator = paramMapDeserializer._propertyBasedCreator;
    this._delegateDeserializer = paramMapDeserializer._delegateDeserializer;
    this._hasDefaultCreator = paramMapDeserializer._hasDefaultCreator;
    this._ignorableProperties = paramMapDeserializer._ignorableProperties;
    this._standardStringKey = paramMapDeserializer._standardStringKey;
  }
  
  protected MapDeserializer(MapDeserializer paramMapDeserializer, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, HashSet<String> paramHashSet)
  {
    super(paramMapDeserializer._mapType);
    this._mapType = paramMapDeserializer._mapType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramMapDeserializer._valueInstantiator;
    this._propertyBasedCreator = paramMapDeserializer._propertyBasedCreator;
    this._delegateDeserializer = paramMapDeserializer._delegateDeserializer;
    this._hasDefaultCreator = paramMapDeserializer._hasDefaultCreator;
    this._ignorableProperties = paramHashSet;
    this._standardStringKey = _isStdKeyDeser(this._mapType, paramKeyDeserializer);
  }
  
  private void handleUnresolvedReference(JsonParser paramJsonParser, MapReferringAccumulator paramMapReferringAccumulator, Object paramObject, UnresolvedForwardReference paramUnresolvedForwardReference)
    throws JsonMappingException
  {
    if (paramMapReferringAccumulator == null) {
      throw JsonMappingException.from(paramJsonParser, "Unresolved forward reference but no identity info.", paramUnresolvedForwardReference);
    }
    ReadableObjectId.Referring localReferring = paramMapReferringAccumulator.handleUnresolvedReference(paramUnresolvedForwardReference, paramObject);
    paramUnresolvedForwardReference.getRoid().appendReferring(localReferring);
  }
  
  public Map<Object, Object> _deserializeUsingCreator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, null);
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    String str;
    JsonToken localJsonToken;
    if (paramJsonParser.isExpectedStartObjectToken())
    {
      str = paramJsonParser.nextFieldName();
      if (str == null) {
        break label291;
      }
      localJsonToken = paramJsonParser.nextToken();
      if ((this._ignorableProperties == null) || (!this._ignorableProperties.contains(str))) {
        break label108;
      }
      paramJsonParser.skipChildren();
    }
    label108:
    SettableBeanProperty localSettableBeanProperty;
    do
    {
      str = paramJsonParser.nextFieldName();
      break;
      if (paramJsonParser.hasToken(JsonToken.FIELD_NAME))
      {
        str = paramJsonParser.getCurrentName();
        break;
      }
      str = null;
      break;
      localSettableBeanProperty = localPropertyBasedCreator.findCreatorProperty(str);
      if (localSettableBeanProperty == null) {
        break label189;
      }
    } while (!localPropertyValueBuffer.assignParameter(localSettableBeanProperty, localSettableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext)));
    paramJsonParser.nextToken();
    for (;;)
    {
      Object localObject1;
      try
      {
        localObject1 = (Map)localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
        _readAndBind(paramJsonParser, paramDeserializationContext, (Map)localObject1);
      }
      catch (Exception localException3)
      {
        wrapAndThrow(localException3, this._mapType.getRawClass(), str);
        localObject1 = null;
        continue;
      }
      return (Map<Object, Object>)localObject1;
      label189:
      Object localObject2 = this._keyDeserializer.deserializeKey(str, paramDeserializationContext);
      try
      {
        Object localObject4;
        if (localJsonToken == JsonToken.VALUE_NULL)
        {
          Object localObject5 = localJsonDeserializer.getNullValue(paramDeserializationContext);
          localObject4 = localObject5;
        }
        for (;;)
        {
          localPropertyValueBuffer.bufferMapProperty(localObject2, localObject4);
          break;
          if (localTypeDeserializer == null)
          {
            localObject4 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          }
          else
          {
            Object localObject3 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
            localObject4 = localObject3;
          }
        }
      }
      catch (Exception localException2)
      {
        wrapAndThrow(localException2, this._mapType.getRawClass(), str);
        localObject1 = null;
      }
      try
      {
        label291:
        Map localMap = (Map)localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
        localObject1 = localMap;
      }
      catch (Exception localException1)
      {
        wrapAndThrow(localException1, this._mapType.getRawClass(), null);
        localObject1 = null;
      }
    }
  }
  
  protected final boolean _isStdKeyDeser(JavaType paramJavaType, KeyDeserializer paramKeyDeserializer)
  {
    boolean bool = true;
    if (paramKeyDeserializer == null) {}
    for (;;)
    {
      return bool;
      JavaType localJavaType = paramJavaType.getKeyType();
      if (localJavaType != null)
      {
        Class localClass = localJavaType.getRawClass();
        if (((localClass != String.class) && (localClass != Object.class)) || (!isDefaultKeyDeserializer(paramKeyDeserializer))) {
          bool = false;
        }
      }
    }
  }
  
  protected final void _readAndBind(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException
  {
    KeyDeserializer localKeyDeserializer = this._keyDeserializer;
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    MapReferringAccumulator localMapReferringAccumulator = null;
    int i;
    String str;
    label70:
    Object localObject1;
    JsonToken localJsonToken2;
    if (localJsonDeserializer.getObjectIdReader() != null)
    {
      i = 1;
      if (i != 0) {
        localMapReferringAccumulator = new MapReferringAccumulator(this._mapType.getContentType().getRawClass(), paramMap);
      }
      if (!paramJsonParser.isExpectedStartObjectToken()) {
        break label130;
      }
      str = paramJsonParser.nextFieldName();
      if (str == null) {
        break label144;
      }
      localObject1 = localKeyDeserializer.deserializeKey(str, paramDeserializationContext);
      localJsonToken2 = paramJsonParser.nextToken();
      if ((this._ignorableProperties == null) || (!this._ignorableProperties.contains(str))) {
        break label178;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      str = paramJsonParser.nextFieldName();
      break label70;
      i = 0;
      break;
      label130:
      JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
      if (localJsonToken1 == JsonToken.END_OBJECT) {
        label144:
        return;
      }
      if (localJsonToken1 != JsonToken.FIELD_NAME) {
        throw paramDeserializationContext.mappingException(this._mapType.getRawClass(), paramJsonParser.getCurrentToken());
      }
      str = paramJsonParser.getCurrentName();
      break label70;
      try
      {
        label178:
        if (localJsonToken2 == JsonToken.VALUE_NULL)
        {
          localObject2 = localJsonDeserializer.getNullValue(paramDeserializationContext);
          if (i == 0) {
            break label258;
          }
          localMapReferringAccumulator.put(localObject1, localObject2);
        }
      }
      catch (UnresolvedForwardReference localUnresolvedForwardReference)
      {
        Object localObject2;
        for (;;)
        {
          handleUnresolvedReference(paramJsonParser, localMapReferringAccumulator, localObject1, localUnresolvedForwardReference);
          break;
          if (localTypeDeserializer == null) {
            localObject2 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject2 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
        paramMap.put(localObject1, localObject2);
      }
      catch (Exception localException)
      {
        label258:
        wrapAndThrow(localException, paramMap, str);
      }
    }
  }
  
  protected final void _readAndBindStringMap(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException
  {
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    MapReferringAccumulator localMapReferringAccumulator = null;
    int i;
    String str;
    label64:
    JsonToken localJsonToken2;
    if (localJsonDeserializer.getObjectIdReader() != null)
    {
      i = 1;
      if (i != 0) {
        localMapReferringAccumulator = new MapReferringAccumulator(this._mapType.getContentType().getRawClass(), paramMap);
      }
      if (!paramJsonParser.isExpectedStartObjectToken()) {
        break label114;
      }
      str = paramJsonParser.nextFieldName();
      if (str == null) {
        break label128;
      }
      localJsonToken2 = paramJsonParser.nextToken();
      if ((this._ignorableProperties == null) || (!this._ignorableProperties.contains(str))) {
        break label162;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      str = paramJsonParser.nextFieldName();
      break label64;
      i = 0;
      break;
      label114:
      JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
      if (localJsonToken1 == JsonToken.END_OBJECT) {
        label128:
        return;
      }
      if (localJsonToken1 != JsonToken.FIELD_NAME) {
        throw paramDeserializationContext.mappingException(this._mapType.getRawClass(), paramJsonParser.getCurrentToken());
      }
      str = paramJsonParser.getCurrentName();
      break label64;
      try
      {
        label162:
        if (localJsonToken2 == JsonToken.VALUE_NULL)
        {
          localObject = localJsonDeserializer.getNullValue(paramDeserializationContext);
          if (i == 0) {
            break label242;
          }
          localMapReferringAccumulator.put(str, localObject);
        }
      }
      catch (UnresolvedForwardReference localUnresolvedForwardReference)
      {
        Object localObject;
        for (;;)
        {
          handleUnresolvedReference(paramJsonParser, localMapReferringAccumulator, str, localUnresolvedForwardReference);
          break;
          if (localTypeDeserializer == null) {
            localObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
        paramMap.put(str, localObject);
      }
      catch (Exception localException)
      {
        label242:
        wrapAndThrow(localException, paramMap, str);
      }
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    KeyDeserializer localKeyDeserializer = this._keyDeserializer;
    JsonDeserializer localJsonDeserializer1;
    JavaType localJavaType;
    JsonDeserializer localJsonDeserializer2;
    label65:
    TypeDeserializer localTypeDeserializer;
    String[] arrayOfString;
    if (localKeyDeserializer == null)
    {
      localKeyDeserializer = paramDeserializationContext.findKeyDeserializer(this._mapType.getKeyType(), paramBeanProperty);
      localJsonDeserializer1 = this._valueDeserializer;
      if (paramBeanProperty != null) {
        localJsonDeserializer1 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, localJsonDeserializer1);
      }
      localJavaType = this._mapType.getContentType();
      if (localJsonDeserializer1 != null) {
        break label201;
      }
      localJsonDeserializer2 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);
      localTypeDeserializer = this._valueTypeDeserializer;
      if (localTypeDeserializer != null) {
        localTypeDeserializer = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      localHashSet = this._ignorableProperties;
      AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
      if ((localAnnotationIntrospector == null) || (paramBeanProperty == null)) {
        break label229;
      }
      AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
      if (localAnnotatedMember == null) {
        break label229;
      }
      arrayOfString = localAnnotationIntrospector.findPropertiesToIgnore(localAnnotatedMember, false);
      if (arrayOfString == null) {
        break label229;
      }
      if (localHashSet != null) {
        break label215;
      }
    }
    label201:
    label215:
    for (HashSet localHashSet = new HashSet();; localHashSet = new HashSet(localHashSet))
    {
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++) {
        localHashSet.add(arrayOfString[j]);
      }
      if (!(localKeyDeserializer instanceof ContextualKeyDeserializer)) {
        break;
      }
      localKeyDeserializer = ((ContextualKeyDeserializer)localKeyDeserializer).createContextual(paramDeserializationContext, paramBeanProperty);
      break;
      localJsonDeserializer2 = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer1, paramBeanProperty, localJavaType);
      break label65;
    }
    label229:
    return withResolved(localKeyDeserializer, localTypeDeserializer, localJsonDeserializer2, localHashSet);
  }
  
  public Map<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (this._propertyBasedCreator != null) {
      localObject = _deserializeUsingCreator(paramJsonParser, paramDeserializationContext);
    }
    for (;;)
    {
      return (Map<Object, Object>)localObject;
      if (this._delegateDeserializer != null)
      {
        localObject = (Map)this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      }
      else
      {
        if (!this._hasDefaultCreator) {
          throw paramDeserializationContext.instantiationException(getMapClass(), "No default constructor found");
        }
        JsonToken localJsonToken = paramJsonParser.getCurrentToken();
        if ((localJsonToken != JsonToken.START_OBJECT) && (localJsonToken != JsonToken.FIELD_NAME) && (localJsonToken != JsonToken.END_OBJECT))
        {
          if (localJsonToken == JsonToken.VALUE_STRING) {
            localObject = (Map)this._valueInstantiator.createFromString(paramDeserializationContext, paramJsonParser.getText());
          } else {
            localObject = (Map)_deserializeFromEmpty(paramJsonParser, paramDeserializationContext);
          }
        }
        else
        {
          Map localMap = (Map)this._valueInstantiator.createUsingDefault(paramDeserializationContext);
          if (this._standardStringKey)
          {
            _readAndBindStringMap(paramJsonParser, paramDeserializationContext, localMap);
            localObject = localMap;
          }
          else
          {
            _readAndBind(paramJsonParser, paramDeserializationContext, localMap);
            localObject = localMap;
          }
        }
      }
    }
  }
  
  public Map<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap)
    throws IOException
  {
    paramJsonParser.setCurrentValue(paramMap);
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if ((localJsonToken != JsonToken.START_OBJECT) && (localJsonToken != JsonToken.FIELD_NAME)) {
      throw paramDeserializationContext.mappingException(getMapClass());
    }
    if (this._standardStringKey) {
      _readAndBindStringMap(paramJsonParser, paramDeserializationContext, paramMap);
    }
    for (;;)
    {
      return paramMap;
      _readAndBind(paramJsonParser, paramDeserializationContext, paramMap);
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._mapType.getContentType();
  }
  
  public final Class<?> getMapClass()
  {
    return this._mapType.getRawClass();
  }
  
  public JavaType getValueType()
  {
    return this._mapType;
  }
  
  public boolean isCachable()
  {
    if ((this._valueDeserializer == null) && (this._keyDeserializer == null) && (this._valueTypeDeserializer == null) && (this._ignorableProperties == null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    if (this._valueInstantiator.canCreateUsingDelegate())
    {
      JavaType localJavaType = this._valueInstantiator.getDelegateType(paramDeserializationContext.getConfig());
      if (localJavaType == null) {
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._mapType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
      }
      this._delegateDeserializer = findDeserializer(paramDeserializationContext, localJavaType, null);
    }
    if (this._valueInstantiator.canCreateFromObjectWith())
    {
      SettableBeanProperty[] arrayOfSettableBeanProperty = this._valueInstantiator.getFromObjectArguments(paramDeserializationContext.getConfig());
      this._propertyBasedCreator = PropertyBasedCreator.construct(paramDeserializationContext, this._valueInstantiator, arrayOfSettableBeanProperty);
    }
    this._standardStringKey = _isStdKeyDeser(this._mapType, this._keyDeserializer);
  }
  
  public void setIgnorableProperties(String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    for (HashSet localHashSet = null;; localHashSet = ArrayBuilders.arrayToSet(paramArrayOfString))
    {
      this._ignorableProperties = localHashSet;
      return;
    }
  }
  
  protected MapDeserializer withResolved(KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer, HashSet<String> paramHashSet)
  {
    if ((this._keyDeserializer == paramKeyDeserializer) && (this._valueDeserializer == paramJsonDeserializer) && (this._valueTypeDeserializer == paramTypeDeserializer) && (this._ignorableProperties == paramHashSet)) {}
    for (;;)
    {
      return this;
      this = new MapDeserializer(this, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer, paramHashSet);
    }
  }
  
  @Deprecated
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject)
    throws IOException
  {
    wrapAndThrow(paramThrowable, paramObject, null);
  }
  
  static final class MapReferring
    extends ReadableObjectId.Referring
  {
    private final MapDeserializer.MapReferringAccumulator _parent;
    public final Object key;
    public final Map<Object, Object> next = new LinkedHashMap();
    
    MapReferring(MapDeserializer.MapReferringAccumulator paramMapReferringAccumulator, UnresolvedForwardReference paramUnresolvedForwardReference, Class<?> paramClass, Object paramObject)
    {
      super(paramClass);
      this._parent = paramMapReferringAccumulator;
      this.key = paramObject;
    }
    
    public void handleResolvedForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      this._parent.resolveForwardReference(paramObject1, paramObject2);
    }
  }
  
  private static final class MapReferringAccumulator
  {
    private List<MapDeserializer.MapReferring> _accumulator = new ArrayList();
    private Map<Object, Object> _result;
    private final Class<?> _valueType;
    
    public MapReferringAccumulator(Class<?> paramClass, Map<Object, Object> paramMap)
    {
      this._valueType = paramClass;
      this._result = paramMap;
    }
    
    public ReadableObjectId.Referring handleUnresolvedReference(UnresolvedForwardReference paramUnresolvedForwardReference, Object paramObject)
    {
      MapDeserializer.MapReferring localMapReferring = new MapDeserializer.MapReferring(this, paramUnresolvedForwardReference, this._valueType, paramObject);
      this._accumulator.add(localMapReferring);
      return localMapReferring;
    }
    
    public void put(Object paramObject1, Object paramObject2)
    {
      if (this._accumulator.isEmpty()) {
        this._result.put(paramObject1, paramObject2);
      }
      for (;;)
      {
        return;
        ((MapDeserializer.MapReferring)this._accumulator.get(-1 + this._accumulator.size())).next.put(paramObject1, paramObject2);
      }
    }
    
    public void resolveForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      Iterator localIterator = this._accumulator.iterator();
      MapDeserializer.MapReferring localMapReferring;
      for (Map localMap = this._result; localIterator.hasNext(); localMap = localMapReferring.next)
      {
        localMapReferring = (MapDeserializer.MapReferring)localIterator.next();
        if (localMapReferring.hasId(paramObject1))
        {
          localIterator.remove();
          localMap.put(localMapReferring.key, paramObject2);
          localMap.putAll(localMapReferring.next);
          return;
        }
      }
      throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + paramObject1 + "] that wasn't previously seen as unresolved.");
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/MapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */