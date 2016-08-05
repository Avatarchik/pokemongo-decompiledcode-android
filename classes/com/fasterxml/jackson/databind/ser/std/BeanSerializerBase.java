package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public abstract class BeanSerializerBase
  extends StdSerializer<Object>
  implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware
{
  protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
  protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
  protected final AnyGetterWriter _anyGetterWriter;
  protected final BeanPropertyWriter[] _filteredProps;
  protected final ObjectIdWriter _objectIdWriter;
  protected final Object _propertyFilterId;
  protected final BeanPropertyWriter[] _props;
  protected final JsonFormat.Shape _serializationShape;
  protected final AnnotatedMember _typeId;
  
  protected BeanSerializerBase(JavaType paramJavaType, BeanSerializerBuilder paramBeanSerializerBuilder, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2)
  {
    super(paramJavaType);
    this._props = paramArrayOfBeanPropertyWriter1;
    this._filteredProps = paramArrayOfBeanPropertyWriter2;
    if (paramBeanSerializerBuilder == null)
    {
      this._typeId = null;
      this._anyGetterWriter = null;
      this._propertyFilterId = null;
      this._objectIdWriter = null;
      this._serializationShape = null;
      return;
    }
    this._typeId = paramBeanSerializerBuilder.getTypeId();
    this._anyGetterWriter = paramBeanSerializerBuilder.getAnyGetter();
    this._propertyFilterId = paramBeanSerializerBuilder.getFilterId();
    this._objectIdWriter = paramBeanSerializerBuilder.getObjectIdWriter();
    JsonFormat.Value localValue = paramBeanSerializerBuilder.getBeanDescription().findExpectedFormat(null);
    if (localValue == null) {}
    for (;;)
    {
      this._serializationShape = localShape;
      break;
      localShape = localValue.getShape();
    }
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase)
  {
    this(paramBeanSerializerBase, paramBeanSerializerBase._props, paramBeanSerializerBase._filteredProps);
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, ObjectIdWriter paramObjectIdWriter)
  {
    this(paramBeanSerializerBase, paramObjectIdWriter, paramBeanSerializerBase._propertyFilterId);
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, ObjectIdWriter paramObjectIdWriter, Object paramObject)
  {
    super(paramBeanSerializerBase._handledType);
    this._props = paramBeanSerializerBase._props;
    this._filteredProps = paramBeanSerializerBase._filteredProps;
    this._typeId = paramBeanSerializerBase._typeId;
    this._anyGetterWriter = paramBeanSerializerBase._anyGetterWriter;
    this._objectIdWriter = paramObjectIdWriter;
    this._propertyFilterId = paramObject;
    this._serializationShape = paramBeanSerializerBase._serializationShape;
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, NameTransformer paramNameTransformer)
  {
    this(paramBeanSerializerBase, rename(paramBeanSerializerBase._props, paramNameTransformer), rename(paramBeanSerializerBase._filteredProps, paramNameTransformer));
  }
  
  public BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2)
  {
    super(paramBeanSerializerBase._handledType);
    this._props = paramArrayOfBeanPropertyWriter1;
    this._filteredProps = paramArrayOfBeanPropertyWriter2;
    this._typeId = paramBeanSerializerBase._typeId;
    this._anyGetterWriter = paramBeanSerializerBase._anyGetterWriter;
    this._objectIdWriter = paramBeanSerializerBase._objectIdWriter;
    this._propertyFilterId = paramBeanSerializerBase._propertyFilterId;
    this._serializationShape = paramBeanSerializerBase._serializationShape;
  }
  
  protected BeanSerializerBase(BeanSerializerBase paramBeanSerializerBase, String[] paramArrayOfString)
  {
    super(paramBeanSerializerBase._handledType);
    HashSet localHashSet = ArrayBuilders.arrayToSet(paramArrayOfString);
    BeanPropertyWriter[] arrayOfBeanPropertyWriter2 = paramBeanSerializerBase._props;
    BeanPropertyWriter[] arrayOfBeanPropertyWriter3 = paramBeanSerializerBase._filteredProps;
    int i = arrayOfBeanPropertyWriter2.length;
    ArrayList localArrayList1 = new ArrayList(i);
    ArrayList localArrayList2;
    int j;
    label55:
    BeanPropertyWriter localBeanPropertyWriter;
    if (arrayOfBeanPropertyWriter3 == null)
    {
      localArrayList2 = null;
      j = 0;
      if (j >= i) {
        break label129;
      }
      localBeanPropertyWriter = arrayOfBeanPropertyWriter2[j];
      if (!localHashSet.contains(localBeanPropertyWriter.getName())) {
        break label102;
      }
    }
    for (;;)
    {
      j++;
      break label55;
      localArrayList2 = new ArrayList(i);
      break;
      label102:
      localArrayList1.add(localBeanPropertyWriter);
      if (arrayOfBeanPropertyWriter3 != null) {
        localArrayList2.add(arrayOfBeanPropertyWriter3[j]);
      }
    }
    label129:
    this._props = ((BeanPropertyWriter[])localArrayList1.toArray(new BeanPropertyWriter[localArrayList1.size()]));
    if (localArrayList2 == null) {}
    for (;;)
    {
      this._filteredProps = arrayOfBeanPropertyWriter1;
      this._typeId = paramBeanSerializerBase._typeId;
      this._anyGetterWriter = paramBeanSerializerBase._anyGetterWriter;
      this._objectIdWriter = paramBeanSerializerBase._objectIdWriter;
      this._propertyFilterId = paramBeanSerializerBase._propertyFilterId;
      this._serializationShape = paramBeanSerializerBase._serializationShape;
      return;
      arrayOfBeanPropertyWriter1 = (BeanPropertyWriter[])localArrayList2.toArray(new BeanPropertyWriter[localArrayList2.size()]);
    }
  }
  
  private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] paramArrayOfBeanPropertyWriter, NameTransformer paramNameTransformer)
  {
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    if ((paramArrayOfBeanPropertyWriter == null) || (paramArrayOfBeanPropertyWriter.length == 0) || (paramNameTransformer == null) || (paramNameTransformer == NameTransformer.NOP)) {
      arrayOfBeanPropertyWriter = paramArrayOfBeanPropertyWriter;
    }
    for (;;)
    {
      return arrayOfBeanPropertyWriter;
      int i = paramArrayOfBeanPropertyWriter.length;
      arrayOfBeanPropertyWriter = new BeanPropertyWriter[i];
      for (int j = 0; j < i; j++)
      {
        BeanPropertyWriter localBeanPropertyWriter = paramArrayOfBeanPropertyWriter[j];
        if (localBeanPropertyWriter != null) {
          arrayOfBeanPropertyWriter[j] = localBeanPropertyWriter.rename(paramNameTransformer);
        }
      }
    }
  }
  
  protected final String _customTypeId(Object paramObject)
  {
    Object localObject = this._typeId.getValue(paramObject);
    String str;
    if (localObject == null) {
      str = "";
    }
    for (;;)
    {
      return str;
      if ((localObject instanceof String)) {
        str = (String)localObject;
      } else {
        str = localObject.toString();
      }
    }
  }
  
  protected void _serializeObjectId(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer, WritableObjectId paramWritableObjectId)
    throws IOException
  {
    ObjectIdWriter localObjectIdWriter = this._objectIdWriter;
    String str;
    if (this._typeId == null)
    {
      str = null;
      if (str != null) {
        break label74;
      }
      paramTypeSerializer.writeTypePrefixForObject(paramObject, paramJsonGenerator);
      label28:
      paramWritableObjectId.writeAsField(paramJsonGenerator, paramSerializerProvider, localObjectIdWriter);
      if (this._propertyFilterId == null) {
        break label86;
      }
      serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
      label51:
      if (str != null) {
        break label96;
      }
      paramTypeSerializer.writeTypeSuffixForObject(paramObject, paramJsonGenerator);
    }
    for (;;)
    {
      return;
      str = _customTypeId(paramObject);
      break;
      label74:
      paramTypeSerializer.writeCustomTypePrefixForObject(paramObject, paramJsonGenerator, str);
      break label28;
      label86:
      serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
      break label51;
      label96:
      paramTypeSerializer.writeCustomTypeSuffixForObject(paramObject, paramJsonGenerator, str);
    }
  }
  
  protected final void _serializeWithObjectId(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    ObjectIdWriter localObjectIdWriter = this._objectIdWriter;
    WritableObjectId localWritableObjectId = paramSerializerProvider.findObjectId(paramObject, localObjectIdWriter.generator);
    if (localWritableObjectId.writeAsId(paramJsonGenerator, paramSerializerProvider, localObjectIdWriter)) {}
    for (;;)
    {
      return;
      Object localObject = localWritableObjectId.generateId(paramObject);
      if (localObjectIdWriter.alwaysAsId) {
        localObjectIdWriter.serializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
      } else {
        _serializeObjectId(paramObject, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer, localWritableObjectId);
      }
    }
  }
  
  protected final void _serializeWithObjectId(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, boolean paramBoolean)
    throws IOException
  {
    ObjectIdWriter localObjectIdWriter = this._objectIdWriter;
    WritableObjectId localWritableObjectId = paramSerializerProvider.findObjectId(paramObject, localObjectIdWriter.generator);
    if (localWritableObjectId.writeAsId(paramJsonGenerator, paramSerializerProvider, localObjectIdWriter)) {}
    label114:
    for (;;)
    {
      return;
      Object localObject = localWritableObjectId.generateId(paramObject);
      if (localObjectIdWriter.alwaysAsId)
      {
        localObjectIdWriter.serializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
      }
      else
      {
        if (paramBoolean) {
          paramJsonGenerator.writeStartObject();
        }
        localWritableObjectId.writeAsField(paramJsonGenerator, paramSerializerProvider, localObjectIdWriter);
        if (this._propertyFilterId != null) {
          serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
        }
        for (;;)
        {
          if (!paramBoolean) {
            break label114;
          }
          paramJsonGenerator.writeEndObject();
          break;
          serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
        }
      }
    }
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper == null) {}
    for (;;)
    {
      return;
      JsonObjectFormatVisitor localJsonObjectFormatVisitor = paramJsonFormatVisitorWrapper.expectObjectFormat(paramJavaType);
      if (localJsonObjectFormatVisitor != null) {
        if (this._propertyFilterId != null)
        {
          PropertyFilter localPropertyFilter = findPropertyFilter(paramJsonFormatVisitorWrapper.getProvider(), this._propertyFilterId, null);
          for (int j = 0; j < this._props.length; j++) {
            localPropertyFilter.depositSchemaProperty(this._props[j], localJsonObjectFormatVisitor, paramJsonFormatVisitorWrapper.getProvider());
          }
        }
        else
        {
          for (int i = 0; i < this._props.length; i++) {
            this._props[i].depositSchemaProperty(localJsonObjectFormatVisitor);
          }
        }
      }
    }
  }
  
  protected abstract BeanSerializerBase asArraySerializer();
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
    Object localObject1;
    SerializationConfig localSerializationConfig;
    JsonFormat.Shape localShape1;
    JsonFormat.Value localValue;
    ObjectIdWriter localObjectIdWriter1;
    String[] arrayOfString;
    Object localObject2;
    ObjectIdInfo localObjectIdInfo1;
    if ((paramBeanProperty == null) || (localAnnotationIntrospector == null))
    {
      localObject1 = null;
      localSerializationConfig = paramSerializerProvider.getConfig();
      localShape1 = null;
      if (localObject1 != null)
      {
        localValue = localAnnotationIntrospector.findFormat((Annotated)localObject1);
        if (localValue != null)
        {
          localShape1 = localValue.getShape();
          JsonFormat.Shape localShape3 = this._serializationShape;
          if ((localShape1 == localShape3) || (!this._handledType.isEnum())) {}
        }
      }
      switch (localShape1)
      {
      default: 
        localObjectIdWriter1 = this._objectIdWriter;
        arrayOfString = null;
        localObject2 = null;
        if (localObject1 != null)
        {
          arrayOfString = localAnnotationIntrospector.findPropertiesToIgnore((Annotated)localObject1, true);
          localObjectIdInfo1 = localAnnotationIntrospector.findObjectIdInfo((Annotated)localObject1);
          if (localObjectIdInfo1 != null) {
            break label384;
          }
          if (localObjectIdWriter1 != null)
          {
            ObjectIdInfo localObjectIdInfo3 = localAnnotationIntrospector.findObjectReferenceInfo((Annotated)localObject1, new ObjectIdInfo(NAME_FOR_OBJECT_REF, null, null, null));
            localObjectIdWriter1 = this._objectIdWriter.withAlwaysAsId(localObjectIdInfo3.getAlwaysAsId());
          }
        }
        break;
      }
    }
    for (;;)
    {
      Object localObject4 = localAnnotationIntrospector.findFilterId((Annotated)localObject1);
      if ((localObject4 != null) && ((this._propertyFilterId == null) || (!localObject4.equals(this._propertyFilterId)))) {
        localObject2 = localObject4;
      }
      Object localObject3 = this;
      if (localObjectIdWriter1 != null)
      {
        JsonSerializer localJsonSerializer = paramSerializerProvider.findValueSerializer(localObjectIdWriter1.idType, paramBeanProperty);
        ObjectIdWriter localObjectIdWriter2 = localObjectIdWriter1.withSerializer(localJsonSerializer);
        if (localObjectIdWriter2 != this._objectIdWriter) {
          localObject3 = ((BeanSerializerBase)localObject3).withObjectIdWriter(localObjectIdWriter2);
        }
      }
      if ((arrayOfString != null) && (arrayOfString.length != 0)) {
        localObject3 = ((BeanSerializerBase)localObject3).withIgnorals(arrayOfString);
      }
      if (localObject2 != null) {
        localObject3 = ((BeanSerializerBase)localObject3).withFilterId(localObject2);
      }
      if (localShape1 == null) {
        localShape1 = this._serializationShape;
      }
      JsonFormat.Shape localShape2 = JsonFormat.Shape.ARRAY;
      if (localShape1 == localShape2) {}
      BeanDescription localBeanDescription;
      for (localObject3 = ((BeanSerializerBase)localObject3).asArraySerializer();; localObject3 = paramSerializerProvider.handlePrimaryContextualization(EnumSerializer.construct(this._handledType, paramSerializerProvider.getConfig(), localBeanDescription, localValue), paramBeanProperty))
      {
        return (JsonSerializer<?>)localObject3;
        localObject1 = paramBeanProperty.getMember();
        break;
        localBeanDescription = localSerializationConfig.introspectClassAnnotations(this._handledType);
      }
      label384:
      ObjectIdInfo localObjectIdInfo2 = localAnnotationIntrospector.findObjectReferenceInfo((Annotated)localObject1, localObjectIdInfo1);
      Class localClass = localObjectIdInfo2.getGeneratorType();
      JavaType localJavaType1 = paramSerializerProvider.constructType(localClass);
      JavaType localJavaType2 = paramSerializerProvider.getTypeFactory().findTypeParameters(localJavaType1, ObjectIdGenerator.class)[0];
      if (localClass == ObjectIdGenerators.PropertyGenerator.class)
      {
        String str = localObjectIdInfo2.getPropertyName().getSimpleName();
        int i = 0;
        int j = this._props.length;
        for (;;)
        {
          if (i == j) {
            throw new IllegalArgumentException("Invalid Object Id definition for " + this._handledType.getName() + ": can not find property with name '" + str + "'");
          }
          BeanPropertyWriter localBeanPropertyWriter1 = this._props[i];
          if (str.equals(localBeanPropertyWriter1.getName()))
          {
            if (i > 0)
            {
              System.arraycopy(this._props, 0, this._props, 1, i);
              this._props[0] = localBeanPropertyWriter1;
              if (this._filteredProps != null)
              {
                BeanPropertyWriter localBeanPropertyWriter2 = this._filteredProps[i];
                System.arraycopy(this._filteredProps, 0, this._filteredProps, 1, i);
                this._filteredProps[0] = localBeanPropertyWriter2;
              }
            }
            JavaType localJavaType3 = localBeanPropertyWriter1.getType();
            PropertyBasedObjectIdGenerator localPropertyBasedObjectIdGenerator = new PropertyBasedObjectIdGenerator(localObjectIdInfo2, localBeanPropertyWriter1);
            localObjectIdWriter1 = ObjectIdWriter.construct(localJavaType3, (PropertyName)null, localPropertyBasedObjectIdGenerator, localObjectIdInfo2.getAlwaysAsId());
            break;
          }
          i++;
        }
      }
      ObjectIdGenerator localObjectIdGenerator = paramSerializerProvider.objectIdGeneratorInstance((Annotated)localObject1, localObjectIdInfo2);
      localObjectIdWriter1 = ObjectIdWriter.construct(localJavaType2, localObjectIdInfo2.getPropertyName(), localObjectIdGenerator, localObjectIdInfo2.getAlwaysAsId());
    }
  }
  
  protected JsonSerializer<Object> findConvertingSerializer(SerializerProvider paramSerializerProvider, BeanPropertyWriter paramBeanPropertyWriter)
    throws JsonMappingException
  {
    Object localObject1 = null;
    AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
    Converter localConverter;
    JavaType localJavaType;
    if (localAnnotationIntrospector != null)
    {
      AnnotatedMember localAnnotatedMember = paramBeanPropertyWriter.getMember();
      if (localAnnotatedMember != null)
      {
        Object localObject2 = localAnnotationIntrospector.findSerializationConverter(localAnnotatedMember);
        if (localObject2 != null)
        {
          localConverter = paramSerializerProvider.converterInstance(paramBeanPropertyWriter.getMember(), localObject2);
          localJavaType = localConverter.getOutputType(paramSerializerProvider.getTypeFactory());
          if (!localJavaType.isJavaLangObject()) {
            break label86;
          }
        }
      }
    }
    for (;;)
    {
      localObject1 = new StdDelegatingSerializer(localConverter, localJavaType, (JsonSerializer)localObject1);
      return (JsonSerializer<Object>)localObject1;
      label86:
      localObject1 = paramSerializerProvider.findValueSerializer(localJavaType, paramBeanPropertyWriter);
    }
  }
  
  @Deprecated
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    ObjectNode localObjectNode1 = createSchemaNode("object", true);
    JsonSerializableSchema localJsonSerializableSchema = (JsonSerializableSchema)this._handledType.getAnnotation(JsonSerializableSchema.class);
    if (localJsonSerializableSchema != null)
    {
      String str = localJsonSerializableSchema.id();
      if ((str != null) && (str.length() > 0)) {
        localObjectNode1.put("id", str);
      }
    }
    ObjectNode localObjectNode2 = localObjectNode1.objectNode();
    PropertyFilter localPropertyFilter;
    int i;
    label89:
    BeanPropertyWriter localBeanPropertyWriter;
    if (this._propertyFilterId != null)
    {
      localPropertyFilter = findPropertyFilter(paramSerializerProvider, this._propertyFilterId, null);
      i = 0;
      if (i >= this._props.length) {
        break label148;
      }
      localBeanPropertyWriter = this._props[i];
      if (localPropertyFilter != null) {
        break label133;
      }
      localBeanPropertyWriter.depositSchemaProperty(localObjectNode2, paramSerializerProvider);
    }
    for (;;)
    {
      i++;
      break label89;
      localPropertyFilter = null;
      break;
      label133:
      localPropertyFilter.depositSchemaProperty(localBeanPropertyWriter, localObjectNode2, paramSerializerProvider);
    }
    label148:
    localObjectNode1.set("properties", localObjectNode2);
    return localObjectNode1;
  }
  
  public Iterator<PropertyWriter> properties()
  {
    return Arrays.asList(this._props).iterator();
  }
  
  public void resolve(SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    int i;
    int j;
    label18:
    BeanPropertyWriter localBeanPropertyWriter1;
    if (this._filteredProps == null)
    {
      i = 0;
      j = 0;
      int k = this._props.length;
      if (j >= k) {
        break label278;
      }
      localBeanPropertyWriter1 = this._props[j];
      if ((!localBeanPropertyWriter1.willSuppressNulls()) && (!localBeanPropertyWriter1.hasNullSerializer()))
      {
        JsonSerializer localJsonSerializer = paramSerializerProvider.findNullValueSerializer(localBeanPropertyWriter1);
        if (localJsonSerializer != null)
        {
          localBeanPropertyWriter1.assignNullSerializer(localJsonSerializer);
          if (j < i)
          {
            BeanPropertyWriter localBeanPropertyWriter3 = this._filteredProps[j];
            if (localBeanPropertyWriter3 != null) {
              localBeanPropertyWriter3.assignNullSerializer(localJsonSerializer);
            }
          }
        }
      }
      if (!localBeanPropertyWriter1.hasSerializer()) {
        break label116;
      }
    }
    for (;;)
    {
      j++;
      break label18;
      i = this._filteredProps.length;
      break;
      label116:
      Object localObject = findConvertingSerializer(paramSerializerProvider, localBeanPropertyWriter1);
      if (localObject == null)
      {
        JavaType localJavaType = localBeanPropertyWriter1.getSerializationType();
        if (localJavaType == null)
        {
          localJavaType = paramSerializerProvider.constructType(localBeanPropertyWriter1.getGenericPropertyType());
          if (!localJavaType.isFinal())
          {
            if ((!localJavaType.isContainerType()) && (localJavaType.containedTypeCount() <= 0)) {
              continue;
            }
            localBeanPropertyWriter1.setNonTrivialBaseType(localJavaType);
            continue;
          }
        }
        localObject = paramSerializerProvider.findValueSerializer(localJavaType, localBeanPropertyWriter1);
        if (localJavaType.isContainerType())
        {
          TypeSerializer localTypeSerializer = (TypeSerializer)localJavaType.getContentType().getTypeHandler();
          if ((localTypeSerializer != null) && ((localObject instanceof ContainerSerializer))) {
            localObject = ((ContainerSerializer)localObject).withValueTypeSerializer(localTypeSerializer);
          }
        }
      }
      localBeanPropertyWriter1.assignSerializer((JsonSerializer)localObject);
      if (j < i)
      {
        BeanPropertyWriter localBeanPropertyWriter2 = this._filteredProps[j];
        if (localBeanPropertyWriter2 != null) {
          localBeanPropertyWriter2.assignSerializer((JsonSerializer)localObject);
        }
      }
    }
    label278:
    if (this._anyGetterWriter != null) {
      this._anyGetterWriter.resolve(paramSerializerProvider);
    }
  }
  
  public abstract void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException;
  
  protected void serializeFields(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    if ((this._filteredProps != null) && (paramSerializerProvider.getActiveView() != null)) {
      arrayOfBeanPropertyWriter = this._filteredProps;
    }
    int i;
    JsonMappingException localJsonMappingException;
    for (;;)
    {
      i = 0;
      try
      {
        int j = arrayOfBeanPropertyWriter.length;
        for (;;)
        {
          if (i < j)
          {
            BeanPropertyWriter localBeanPropertyWriter = arrayOfBeanPropertyWriter[i];
            if (localBeanPropertyWriter != null) {
              localBeanPropertyWriter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider);
            }
            i++;
            continue;
            arrayOfBeanPropertyWriter = this._props;
            break;
          }
        }
        if (this._anyGetterWriter != null) {
          this._anyGetterWriter.getAndSerialize(paramObject, paramJsonGenerator, paramSerializerProvider);
        }
        return;
      }
      catch (Exception localException)
      {
        if (i == arrayOfBeanPropertyWriter.length) {}
        for (String str2 = "[anySetter]";; str2 = arrayOfBeanPropertyWriter[i].getName())
        {
          wrapAndThrow(paramSerializerProvider, localException, paramObject, str2);
          break;
        }
      }
      catch (StackOverflowError localStackOverflowError)
      {
        localJsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)", localStackOverflowError);
        if (i != arrayOfBeanPropertyWriter.length) {}
      }
    }
    for (String str1 = "[anySetter]";; str1 = arrayOfBeanPropertyWriter[i].getName())
    {
      localJsonMappingException.prependPath(new JsonMappingException.Reference(paramObject, str1));
      throw localJsonMappingException;
    }
  }
  
  protected void serializeFieldsFiltered(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    PropertyFilter localPropertyFilter;
    if ((this._filteredProps != null) && (paramSerializerProvider.getActiveView() != null))
    {
      arrayOfBeanPropertyWriter = this._filteredProps;
      localPropertyFilter = findPropertyFilter(paramSerializerProvider, this._propertyFilterId, paramObject);
      if (localPropertyFilter != null) {
        break label54;
      }
      serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      arrayOfBeanPropertyWriter = this._props;
      break;
      label54:
      int i = 0;
      try
      {
        int j = arrayOfBeanPropertyWriter.length;
        if (i < j)
        {
          BeanPropertyWriter localBeanPropertyWriter = arrayOfBeanPropertyWriter[i];
          if (localBeanPropertyWriter != null) {
            localPropertyFilter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider, localBeanPropertyWriter);
          }
        }
        else
        {
          if (this._anyGetterWriter == null) {
            continue;
          }
          this._anyGetterWriter.getAndFilter(paramObject, paramJsonGenerator, paramSerializerProvider, localPropertyFilter);
        }
      }
      catch (Exception localException)
      {
        if (i == arrayOfBeanPropertyWriter.length) {}
        for (String str2 = "[anySetter]";; str2 = arrayOfBeanPropertyWriter[i].getName())
        {
          wrapAndThrow(paramSerializerProvider, localException, paramObject, str2);
          break;
        }
      }
      catch (StackOverflowError localStackOverflowError)
      {
        for (;;)
        {
          JsonMappingException localJsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)", localStackOverflowError);
          if (i == arrayOfBeanPropertyWriter.length) {}
          for (String str1 = "[anySetter]";; str1 = arrayOfBeanPropertyWriter[i].getName())
          {
            localJsonMappingException.prependPath(new JsonMappingException.Reference(paramObject, str1));
            throw localJsonMappingException;
          }
          i++;
        }
      }
    }
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if (this._objectIdWriter != null)
    {
      paramJsonGenerator.setCurrentValue(paramObject);
      _serializeWithObjectId(paramObject, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
    }
    for (;;)
    {
      return;
      String str;
      if (this._typeId == null)
      {
        str = null;
        label32:
        if (str != null) {
          break label88;
        }
        paramTypeSerializer.writeTypePrefixForObject(paramObject, paramJsonGenerator);
        label44:
        paramJsonGenerator.setCurrentValue(paramObject);
        if (this._propertyFilterId == null) {
          break label100;
        }
        serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
      for (;;)
      {
        if (str != null) {
          break label110;
        }
        paramTypeSerializer.writeTypeSuffixForObject(paramObject, paramJsonGenerator);
        break;
        str = _customTypeId(paramObject);
        break label32;
        label88:
        paramTypeSerializer.writeCustomTypePrefixForObject(paramObject, paramJsonGenerator, str);
        break label44;
        label100:
        serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
      label110:
      paramTypeSerializer.writeCustomTypeSuffixForObject(paramObject, paramJsonGenerator, str);
    }
  }
  
  public boolean usesObjectId()
  {
    if (this._objectIdWriter != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public abstract BeanSerializerBase withFilterId(Object paramObject);
  
  protected abstract BeanSerializerBase withIgnorals(String[] paramArrayOfString);
  
  public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter paramObjectIdWriter);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/BeanSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */