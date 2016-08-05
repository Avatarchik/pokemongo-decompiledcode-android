package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public class MapSerializer
  extends ContainerSerializer<Map<?, ?>>
  implements ContextualSerializer
{
  protected static final JavaType UNSPECIFIED_TYPE = ;
  private static final long serialVersionUID = 1L;
  protected PropertySerializerMap _dynamicValueSerializers;
  protected final Object _filterId;
  protected final HashSet<String> _ignoredEntries;
  protected JsonSerializer<Object> _keySerializer;
  protected final JavaType _keyType;
  protected final BeanProperty _property;
  protected final boolean _sortKeys;
  protected final Object _suppressableValue;
  protected JsonSerializer<Object> _valueSerializer;
  protected final JavaType _valueType;
  protected final boolean _valueTypeIsStatic;
  protected final TypeSerializer _valueTypeSerializer;
  
  protected MapSerializer(MapSerializer paramMapSerializer, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2, HashSet<String> paramHashSet)
  {
    super(Map.class, false);
    this._ignoredEntries = paramHashSet;
    this._keyType = paramMapSerializer._keyType;
    this._valueType = paramMapSerializer._valueType;
    this._valueTypeIsStatic = paramMapSerializer._valueTypeIsStatic;
    this._valueTypeSerializer = paramMapSerializer._valueTypeSerializer;
    this._keySerializer = paramJsonSerializer1;
    this._valueSerializer = paramJsonSerializer2;
    this._dynamicValueSerializers = paramMapSerializer._dynamicValueSerializers;
    this._property = paramBeanProperty;
    this._filterId = paramMapSerializer._filterId;
    this._sortKeys = paramMapSerializer._sortKeys;
    this._suppressableValue = paramMapSerializer._suppressableValue;
  }
  
  @Deprecated
  protected MapSerializer(MapSerializer paramMapSerializer, TypeSerializer paramTypeSerializer)
  {
    this(paramMapSerializer, paramTypeSerializer, paramMapSerializer._suppressableValue);
  }
  
  protected MapSerializer(MapSerializer paramMapSerializer, TypeSerializer paramTypeSerializer, Object paramObject)
  {
    super(Map.class, false);
    this._ignoredEntries = paramMapSerializer._ignoredEntries;
    this._keyType = paramMapSerializer._keyType;
    this._valueType = paramMapSerializer._valueType;
    this._valueTypeIsStatic = paramMapSerializer._valueTypeIsStatic;
    this._valueTypeSerializer = paramTypeSerializer;
    this._keySerializer = paramMapSerializer._keySerializer;
    this._valueSerializer = paramMapSerializer._valueSerializer;
    this._dynamicValueSerializers = paramMapSerializer._dynamicValueSerializers;
    this._property = paramMapSerializer._property;
    this._filterId = paramMapSerializer._filterId;
    this._sortKeys = paramMapSerializer._sortKeys;
    if (paramObject == JsonInclude.Include.NON_ABSENT) {
      if (!this._valueType.isReferenceType()) {
        break label119;
      }
    }
    label119:
    for (paramObject = JsonInclude.Include.NON_EMPTY;; paramObject = JsonInclude.Include.NON_NULL)
    {
      this._suppressableValue = paramObject;
      return;
    }
  }
  
  protected MapSerializer(MapSerializer paramMapSerializer, Object paramObject, boolean paramBoolean)
  {
    super(Map.class, false);
    this._ignoredEntries = paramMapSerializer._ignoredEntries;
    this._keyType = paramMapSerializer._keyType;
    this._valueType = paramMapSerializer._valueType;
    this._valueTypeIsStatic = paramMapSerializer._valueTypeIsStatic;
    this._valueTypeSerializer = paramMapSerializer._valueTypeSerializer;
    this._keySerializer = paramMapSerializer._keySerializer;
    this._valueSerializer = paramMapSerializer._valueSerializer;
    this._dynamicValueSerializers = paramMapSerializer._dynamicValueSerializers;
    this._property = paramMapSerializer._property;
    this._filterId = paramObject;
    this._sortKeys = paramBoolean;
    this._suppressableValue = paramMapSerializer._suppressableValue;
  }
  
  protected MapSerializer(HashSet<String> paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2)
  {
    super(Map.class, false);
    this._ignoredEntries = paramHashSet;
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
    this._valueTypeIsStatic = paramBoolean;
    this._valueTypeSerializer = paramTypeSerializer;
    this._keySerializer = paramJsonSerializer1;
    this._valueSerializer = paramJsonSerializer2;
    this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
    this._property = null;
    this._filterId = null;
    this._sortKeys = false;
    this._suppressableValue = null;
  }
  
  public static MapSerializer construct(String[] paramArrayOfString, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer1, JsonSerializer<Object> paramJsonSerializer2, Object paramObject)
  {
    HashSet localHashSet;
    JavaType localJavaType2;
    JavaType localJavaType1;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
    {
      localHashSet = null;
      if (paramJavaType != null) {
        break label91;
      }
      localJavaType2 = UNSPECIFIED_TYPE;
      localJavaType1 = localJavaType2;
      label25:
      if (paramBoolean) {
        break label111;
      }
      if ((localJavaType2 == null) || (!localJavaType2.isFinal())) {
        break label106;
      }
      paramBoolean = true;
    }
    for (;;)
    {
      MapSerializer localMapSerializer = new MapSerializer(localHashSet, localJavaType1, localJavaType2, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2);
      if (paramObject != null) {
        localMapSerializer = localMapSerializer.withFilterId(paramObject);
      }
      return localMapSerializer;
      localHashSet = ArrayBuilders.arrayToSet(paramArrayOfString);
      break;
      label91:
      localJavaType1 = paramJavaType.getKeyType();
      localJavaType2 = paramJavaType.getContentType();
      break label25;
      label106:
      paramBoolean = false;
      continue;
      label111:
      if (localJavaType2.getRawClass() == Object.class) {
        paramBoolean = false;
      }
    }
  }
  
  protected void _ensureOverride()
  {
    if (getClass() != MapSerializer.class) {
      throw new IllegalStateException("Missing override in class " + getClass().getName());
    }
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = paramPropertySerializerMap.findAndAddSecondarySerializer(paramJavaType, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != localSerializerAndMapResult.map) {
      this._dynamicValueSerializers = localSerializerAndMapResult.map;
    }
    return localSerializerAndMapResult.serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = paramPropertySerializerMap.findAndAddSecondarySerializer(paramClass, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != localSerializerAndMapResult.map) {
      this._dynamicValueSerializers = localSerializerAndMapResult.map;
    }
    return localSerializerAndMapResult.serializer;
  }
  
  protected Map<?, ?> _orderEntries(Map<?, ?> paramMap)
  {
    if ((paramMap instanceof SortedMap)) {}
    for (;;)
    {
      return paramMap;
      paramMap = new TreeMap(paramMap);
    }
  }
  
  public MapSerializer _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    if (this._valueTypeSerializer == paramTypeSerializer) {}
    for (;;)
    {
      return this;
      _ensureOverride();
      this = new MapSerializer(this, paramTypeSerializer, null);
    }
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper == null) {}
    for (JsonMapFormatVisitor localJsonMapFormatVisitor = null;; localJsonMapFormatVisitor = paramJsonFormatVisitorWrapper.expectMapFormat(paramJavaType))
    {
      if (localJsonMapFormatVisitor != null)
      {
        localJsonMapFormatVisitor.keyFormat(this._keySerializer, this._keyType);
        JsonSerializer localJsonSerializer = this._valueSerializer;
        if (localJsonSerializer == null) {
          localJsonSerializer = _findAndAddDynamic(this._dynamicValueSerializers, this._valueType, paramJsonFormatVisitorWrapper.getProvider());
        }
        localJsonMapFormatVisitor.valueFormat(localJsonSerializer, this._valueType);
      }
      return;
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = null;
    JsonSerializer localJsonSerializer2 = null;
    AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
    Object localObject1;
    Object localObject2;
    JsonSerializer localJsonSerializer3;
    label162:
    JsonSerializer localJsonSerializer4;
    label189:
    String[] arrayOfString;
    if (paramBeanProperty == null)
    {
      localObject1 = null;
      localObject2 = this._suppressableValue;
      if ((localObject1 != null) && (localAnnotationIntrospector != null))
      {
        Object localObject5 = localAnnotationIntrospector.findKeySerializer((Annotated)localObject1);
        if (localObject5 != null) {
          localJsonSerializer2 = paramSerializerProvider.serializerInstance((Annotated)localObject1, localObject5);
        }
        Object localObject6 = localAnnotationIntrospector.findContentSerializer((Annotated)localObject1);
        if (localObject6 != null) {
          localJsonSerializer1 = paramSerializerProvider.serializerInstance((Annotated)localObject1, localObject6);
        }
        JsonInclude.Include localInclude = localAnnotationIntrospector.findSerializationInclusionForContent((Annotated)localObject1, null);
        if (localInclude != null) {
          localObject2 = localInclude;
        }
      }
      if (localJsonSerializer1 == null) {
        localJsonSerializer1 = this._valueSerializer;
      }
      localJsonSerializer3 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, localJsonSerializer1);
      if (localJsonSerializer3 != null) {
        break label280;
      }
      if (((this._valueTypeIsStatic) && (this._valueType.getRawClass() != Object.class)) || (hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty))) {
        localJsonSerializer3 = paramSerializerProvider.findValueSerializer(this._valueType, paramBeanProperty);
      }
      if (localJsonSerializer2 == null) {
        localJsonSerializer2 = this._keySerializer;
      }
      if (localJsonSerializer2 != null) {
        break label292;
      }
      localJsonSerializer4 = paramSerializerProvider.findKeySerializer(this._keyType, paramBeanProperty);
      localHashSet = this._ignoredEntries;
      bool = false;
      if ((localAnnotationIntrospector == null) || (localObject1 == null)) {
        break label343;
      }
      arrayOfString = localAnnotationIntrospector.findPropertiesToIgnore((Annotated)localObject1, true);
      if (arrayOfString == null) {
        break label318;
      }
      if (localHashSet != null) {
        break label304;
      }
    }
    label280:
    label292:
    label304:
    for (HashSet localHashSet = new HashSet();; localHashSet = new HashSet(localHashSet))
    {
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++) {
        localHashSet.add(arrayOfString[j]);
      }
      localObject1 = paramBeanProperty.getMember();
      break;
      localJsonSerializer3 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer3, paramBeanProperty);
      break label162;
      localJsonSerializer4 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer2, paramBeanProperty);
      break label189;
    }
    label318:
    Boolean localBoolean = localAnnotationIntrospector.findSerializationSortAlphabetically((Annotated)localObject1);
    if ((localBoolean != null) && (localBoolean.booleanValue())) {}
    for (boolean bool = true;; bool = false)
    {
      label343:
      MapSerializer localMapSerializer = withResolved(paramBeanProperty, localJsonSerializer4, localJsonSerializer3, localHashSet, bool);
      Object localObject3 = this._suppressableValue;
      if (localObject2 != localObject3) {
        localMapSerializer = localMapSerializer.withContentInclusion(localObject2);
      }
      if (paramBeanProperty != null)
      {
        AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
        if (localAnnotatedMember != null)
        {
          Object localObject4 = localAnnotationIntrospector.findFilterId(localAnnotatedMember);
          if (localObject4 != null) {
            localMapSerializer = localMapSerializer.withFilterId(localObject4);
          }
        }
      }
      return localMapSerializer;
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return this._valueSerializer;
  }
  
  public JavaType getContentType()
  {
    return this._valueType;
  }
  
  public JsonSerializer<?> getKeySerializer()
  {
    return this._keySerializer;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("object", true);
  }
  
  public boolean hasSingleElement(Map<?, ?> paramMap)
  {
    int i = 1;
    if (paramMap.size() == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Map<?, ?> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void serialize(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.setCurrentValue(paramMap);
    Object localObject;
    if (!paramMap.isEmpty())
    {
      localObject = this._suppressableValue;
      if (localObject != JsonInclude.Include.ALWAYS) {
        break label93;
      }
      localObject = null;
      if ((this._sortKeys) || (paramSerializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS))) {
        paramMap = _orderEntries(paramMap);
      }
      if (this._filterId == null) {
        break label116;
      }
      PropertyFilter localPropertyFilter = findPropertyFilter(paramSerializerProvider, this._filterId, paramMap);
      serializeFilteredFields(paramMap, paramJsonGenerator, paramSerializerProvider, localPropertyFilter, localObject);
    }
    for (;;)
    {
      paramJsonGenerator.writeEndObject();
      return;
      label93:
      if ((localObject != null) || (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))) {
        break;
      }
      localObject = JsonInclude.Include.NON_NULL;
      break;
      label116:
      if (localObject != null) {
        serializeOptionalFields(paramMap, paramJsonGenerator, paramSerializerProvider, localObject);
      } else if (this._valueSerializer != null) {
        serializeFieldsUsing(paramMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
      } else {
        serializeFields(paramMap, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public void serializeFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._valueTypeSerializer != null)
    {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider, null);
      return;
    }
    JsonSerializer localJsonSerializer1 = this._keySerializer;
    HashSet localHashSet = this._ignoredEntries;
    PropertySerializerMap localPropertySerializerMap = this._dynamicValueSerializers;
    Iterator localIterator = paramMap.entrySet().iterator();
    label47:
    Object localObject1;
    Object localObject2;
    label150:
    Class localClass;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localObject1 = localEntry.getValue();
      localObject2 = localEntry.getKey();
      if (localObject2 == null) {
        paramSerializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
      for (;;)
      {
        if (localObject1 != null) {
          break label150;
        }
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        break;
        if ((localHashSet != null) && (localHashSet.contains(localObject2))) {
          break;
        }
        localJsonSerializer1.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
      }
      localClass = localObject1.getClass();
      localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
      if (localJsonSerializer2 == null) {
        if (!this._valueType.hasGenericTypes()) {
          break label252;
        }
      }
    }
    label252:
    for (JsonSerializer localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider))
    {
      localPropertySerializerMap = this._dynamicValueSerializers;
      try
      {
        localJsonSerializer2.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
      }
      catch (Exception localException)
      {
        wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject2);
      }
      break label47;
      break;
    }
  }
  
  protected void serializeFieldsUsing(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    JsonSerializer localJsonSerializer = this._keySerializer;
    HashSet localHashSet = this._ignoredEntries;
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = localEntry.getKey();
      if ((localHashSet == null) || (!localHashSet.contains(localObject1)))
      {
        if (localObject1 == null) {
          paramSerializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, paramJsonGenerator, paramSerializerProvider);
        }
        Object localObject2;
        for (;;)
        {
          localObject2 = localEntry.getValue();
          if (localObject2 != null) {
            break label134;
          }
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          break;
          localJsonSerializer.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
        }
        label134:
        if (localTypeSerializer == null) {
          try
          {
            paramJsonSerializer.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
          }
          catch (Exception localException)
          {
            wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject1);
          }
        } else {
          paramJsonSerializer.serializeWithType(localObject2, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  @Deprecated
  public void serializeFilteredFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, PropertyFilter paramPropertyFilter)
    throws IOException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {}
    for (Object localObject = null;; localObject = JsonInclude.Include.NON_NULL)
    {
      serializeFilteredFields(paramMap, paramJsonGenerator, paramSerializerProvider, paramPropertyFilter, localObject);
      return;
    }
  }
  
  public void serializeFilteredFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, PropertyFilter paramPropertyFilter, Object paramObject)
    throws IOException
  {
    HashSet localHashSet = this._ignoredEntries;
    PropertySerializerMap localPropertySerializerMap = this._dynamicValueSerializers;
    MapProperty localMapProperty = new MapProperty(this._valueTypeSerializer, this._property);
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = localEntry.getKey();
      if ((localHashSet == null) || (!localHashSet.contains(localObject1)))
      {
        if (localObject1 == null) {}
        Object localObject2;
        for (JsonSerializer localJsonSerializer1 = paramSerializerProvider.findNullKeySerializer(this._keyType, this._property);; localJsonSerializer1 = this._keySerializer)
        {
          localObject2 = localEntry.getValue();
          if (localObject2 != null) {
            break label202;
          }
          if (paramObject != null) {
            break;
          }
          localJsonSerializer2 = paramSerializerProvider.getDefaultNullValueSerializer();
          label132:
          localMapProperty.reset(localObject1, localJsonSerializer1, localJsonSerializer2);
          try
          {
            paramPropertyFilter.serializeAsField(localObject2, paramJsonGenerator, paramSerializerProvider, localMapProperty);
          }
          catch (Exception localException)
          {
            wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject1);
          }
          break;
        }
        label202:
        JsonSerializer localJsonSerializer2 = this._valueSerializer;
        Class localClass;
        if (localJsonSerializer2 == null)
        {
          localClass = localObject2.getClass();
          localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
          if (localJsonSerializer2 == null) {
            if (!this._valueType.hasGenericTypes()) {
              break label291;
            }
          }
        }
        label291:
        for (localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider))
        {
          localPropertySerializerMap = this._dynamicValueSerializers;
          if ((paramObject != JsonInclude.Include.NON_EMPTY) || (!localJsonSerializer2.isEmpty(paramSerializerProvider, localObject2))) {
            break label132;
          }
          break;
        }
      }
    }
  }
  
  public void serializeOptionalFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, Object paramObject)
    throws IOException
  {
    if (this._valueTypeSerializer != null)
    {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider, paramObject);
      return;
    }
    HashSet localHashSet = this._ignoredEntries;
    PropertySerializerMap localPropertySerializerMap = this._dynamicValueSerializers;
    Iterator localIterator = paramMap.entrySet().iterator();
    label42:
    Object localObject2;
    label117:
    label196:
    Class localClass;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = localEntry.getKey();
      if (localObject1 == null) {}
      for (JsonSerializer localJsonSerializer1 = paramSerializerProvider.findNullKeySerializer(this._keyType, this._property);; localJsonSerializer1 = this._keySerializer)
      {
        localObject2 = localEntry.getValue();
        if (localObject2 != null) {
          break label196;
        }
        if (paramObject != null) {
          break;
        }
        localJsonSerializer2 = paramSerializerProvider.getDefaultNullValueSerializer();
        try
        {
          localJsonSerializer1.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
          localJsonSerializer2.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject1);
        }
        break;
        if ((localHashSet != null) && (localHashSet.contains(localObject1))) {
          break;
        }
      }
      localJsonSerializer2 = this._valueSerializer;
      if (localJsonSerializer2 == null)
      {
        localClass = localObject2.getClass();
        localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
        if (localJsonSerializer2 == null) {
          if (!this._valueType.hasGenericTypes()) {
            break label285;
          }
        }
      }
    }
    label285:
    for (JsonSerializer localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider))
    {
      localPropertySerializerMap = this._dynamicValueSerializers;
      if ((paramObject != JsonInclude.Include.NON_EMPTY) || (!localJsonSerializer2.isEmpty(paramSerializerProvider, localObject2))) {
        break label117;
      }
      break label42;
      break;
    }
  }
  
  @Deprecated
  protected void serializeTypedFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {}
    for (Object localObject = null;; localObject = JsonInclude.Include.NON_NULL)
    {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider, localObject);
      return;
    }
  }
  
  protected void serializeTypedFields(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, Object paramObject)
    throws IOException
  {
    HashSet localHashSet = this._ignoredEntries;
    PropertySerializerMap localPropertySerializerMap = this._dynamicValueSerializers;
    Iterator localIterator = paramMap.entrySet().iterator();
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject1 = localEntry.getKey();
      if (localObject1 == null) {}
      Object localObject2;
      for (JsonSerializer localJsonSerializer1 = paramSerializerProvider.findNullKeySerializer(this._keyType, this._property);; localJsonSerializer1 = this._keySerializer)
      {
        localObject2 = localEntry.getValue();
        if (localObject2 != null) {
          break label183;
        }
        if (paramObject != null) {
          break;
        }
        localJsonSerializer2 = paramSerializerProvider.getDefaultNullValueSerializer();
        label100:
        localJsonSerializer1.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
        try
        {
          localJsonSerializer2.serializeWithType(localObject2, paramJsonGenerator, paramSerializerProvider, this._valueTypeSerializer);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramMap, "" + localObject1);
        }
        break;
        if ((localHashSet != null) && (localHashSet.contains(localObject1))) {
          break;
        }
      }
      label183:
      Class localClass = localObject2.getClass();
      JsonSerializer localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
      if (localJsonSerializer2 == null) {
        if (!this._valueType.hasGenericTypes()) {
          break label266;
        }
      }
      label266:
      for (localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider))
      {
        localPropertySerializerMap = this._dynamicValueSerializers;
        if ((paramObject != JsonInclude.Include.NON_EMPTY) || (!localJsonSerializer2.isEmpty(paramSerializerProvider, localObject2))) {
          break label100;
        }
        break;
      }
    }
  }
  
  public void serializeWithType(Map<?, ?> paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramTypeSerializer.writeTypePrefixForObject(paramMap, paramJsonGenerator);
    paramJsonGenerator.setCurrentValue(paramMap);
    Object localObject;
    if (!paramMap.isEmpty())
    {
      localObject = this._suppressableValue;
      if (localObject != JsonInclude.Include.ALWAYS) {
        break label99;
      }
      localObject = null;
      if ((this._sortKeys) || (paramSerializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS))) {
        paramMap = _orderEntries(paramMap);
      }
      if (this._filterId == null) {
        break label122;
      }
      PropertyFilter localPropertyFilter = findPropertyFilter(paramSerializerProvider, this._filterId, paramMap);
      serializeFilteredFields(paramMap, paramJsonGenerator, paramSerializerProvider, localPropertyFilter, localObject);
    }
    for (;;)
    {
      paramTypeSerializer.writeTypeSuffixForObject(paramMap, paramJsonGenerator);
      return;
      label99:
      if ((localObject != null) || (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))) {
        break;
      }
      localObject = JsonInclude.Include.NON_NULL;
      break;
      label122:
      if (localObject != null) {
        serializeOptionalFields(paramMap, paramJsonGenerator, paramSerializerProvider, localObject);
      } else if (this._valueSerializer != null) {
        serializeFieldsUsing(paramMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
      } else {
        serializeFields(paramMap, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public MapSerializer withContentInclusion(Object paramObject)
  {
    if (paramObject == this._suppressableValue) {}
    for (;;)
    {
      return this;
      _ensureOverride();
      this = new MapSerializer(this, this._valueTypeSerializer, paramObject);
    }
  }
  
  public MapSerializer withFilterId(Object paramObject)
  {
    if (this._filterId == paramObject) {}
    for (;;)
    {
      return this;
      _ensureOverride();
      this = new MapSerializer(this, paramObject, this._sortKeys);
    }
  }
  
  public MapSerializer withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2, HashSet<String> paramHashSet, boolean paramBoolean)
  {
    _ensureOverride();
    MapSerializer localMapSerializer = new MapSerializer(this, paramBeanProperty, paramJsonSerializer1, paramJsonSerializer2, paramHashSet);
    if (paramBoolean != localMapSerializer._sortKeys) {
      localMapSerializer = new MapSerializer(localMapSerializer, this._filterId, paramBoolean);
    }
    return localMapSerializer;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/MapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */