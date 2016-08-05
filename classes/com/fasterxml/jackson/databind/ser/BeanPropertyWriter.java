package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

@JacksonStdImpl
public class BeanPropertyWriter
  extends PropertyWriter
  implements BeanProperty, Serializable
{
  public static final Object MARKER_FOR_EMPTY = JsonInclude.Include.NON_EMPTY;
  protected static final JsonFormat.Value NO_FORMAT = new JsonFormat.Value();
  private static final long serialVersionUID = 4603296144163950020L;
  protected transient Method _accessorMethod;
  protected final JavaType _cfgSerializationType;
  protected final transient Annotations _contextAnnotations;
  protected final JavaType _declaredType;
  protected transient PropertySerializerMap _dynamicSerializers;
  protected transient Field _field;
  protected transient JsonFormat.Value _format;
  protected final Class<?>[] _includeInViews;
  protected transient HashMap<Object, Object> _internalSettings;
  protected final AnnotatedMember _member;
  protected final PropertyMetadata _metadata;
  protected final SerializedString _name;
  protected JavaType _nonTrivialBaseType;
  protected JsonSerializer<Object> _nullSerializer;
  protected JsonSerializer<Object> _serializer;
  protected final boolean _suppressNulls;
  protected final Object _suppressableValue;
  protected TypeSerializer _typeSerializer;
  protected final PropertyName _wrapperName;
  
  protected BeanPropertyWriter()
  {
    this._member = null;
    this._contextAnnotations = null;
    this._name = null;
    this._wrapperName = null;
    this._metadata = null;
    this._includeInViews = null;
    this._declaredType = null;
    this._serializer = null;
    this._dynamicSerializers = null;
    this._typeSerializer = null;
    this._cfgSerializationType = null;
    this._accessorMethod = null;
    this._field = null;
    this._suppressNulls = false;
    this._suppressableValue = null;
    this._nullSerializer = null;
  }
  
  public BeanPropertyWriter(BeanPropertyDefinition paramBeanPropertyDefinition, AnnotatedMember paramAnnotatedMember, Annotations paramAnnotations, JavaType paramJavaType1, JsonSerializer<?> paramJsonSerializer, TypeSerializer paramTypeSerializer, JavaType paramJavaType2, boolean paramBoolean, Object paramObject)
  {
    this._member = paramAnnotatedMember;
    this._contextAnnotations = paramAnnotations;
    this._name = new SerializedString(paramBeanPropertyDefinition.getName());
    this._wrapperName = paramBeanPropertyDefinition.getWrapperName();
    this._metadata = paramBeanPropertyDefinition.getMetadata();
    this._includeInViews = paramBeanPropertyDefinition.findViews();
    this._declaredType = paramJavaType1;
    this._serializer = paramJsonSerializer;
    PropertySerializerMap localPropertySerializerMap;
    if (paramJsonSerializer == null)
    {
      localPropertySerializerMap = PropertySerializerMap.emptyForProperties();
      this._dynamicSerializers = localPropertySerializerMap;
      this._typeSerializer = paramTypeSerializer;
      this._cfgSerializationType = paramJavaType2;
      if (!(paramAnnotatedMember instanceof AnnotatedField)) {
        break label140;
      }
      this._accessorMethod = null;
      this._field = ((Field)paramAnnotatedMember.getMember());
    }
    for (;;)
    {
      this._suppressNulls = paramBoolean;
      this._suppressableValue = paramObject;
      this._nullSerializer = null;
      return;
      localPropertySerializerMap = null;
      break;
      label140:
      if ((paramAnnotatedMember instanceof AnnotatedMethod))
      {
        this._accessorMethod = ((Method)paramAnnotatedMember.getMember());
        this._field = null;
      }
      else
      {
        this._accessorMethod = null;
        this._field = null;
      }
    }
  }
  
  protected BeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter)
  {
    this(paramBeanPropertyWriter, paramBeanPropertyWriter._name);
  }
  
  protected BeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter, SerializedString paramSerializedString)
  {
    this._name = paramSerializedString;
    this._wrapperName = paramBeanPropertyWriter._wrapperName;
    this._member = paramBeanPropertyWriter._member;
    this._contextAnnotations = paramBeanPropertyWriter._contextAnnotations;
    this._declaredType = paramBeanPropertyWriter._declaredType;
    this._accessorMethod = paramBeanPropertyWriter._accessorMethod;
    this._field = paramBeanPropertyWriter._field;
    this._serializer = paramBeanPropertyWriter._serializer;
    this._nullSerializer = paramBeanPropertyWriter._nullSerializer;
    if (paramBeanPropertyWriter._internalSettings != null) {
      this._internalSettings = new HashMap(paramBeanPropertyWriter._internalSettings);
    }
    this._cfgSerializationType = paramBeanPropertyWriter._cfgSerializationType;
    this._dynamicSerializers = paramBeanPropertyWriter._dynamicSerializers;
    this._suppressNulls = paramBeanPropertyWriter._suppressNulls;
    this._suppressableValue = paramBeanPropertyWriter._suppressableValue;
    this._includeInViews = paramBeanPropertyWriter._includeInViews;
    this._typeSerializer = paramBeanPropertyWriter._typeSerializer;
    this._nonTrivialBaseType = paramBeanPropertyWriter._nonTrivialBaseType;
    this._metadata = paramBeanPropertyWriter._metadata;
  }
  
  protected BeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter, PropertyName paramPropertyName)
  {
    this._name = new SerializedString(paramPropertyName.getSimpleName());
    this._wrapperName = paramBeanPropertyWriter._wrapperName;
    this._contextAnnotations = paramBeanPropertyWriter._contextAnnotations;
    this._declaredType = paramBeanPropertyWriter._declaredType;
    this._member = paramBeanPropertyWriter._member;
    this._accessorMethod = paramBeanPropertyWriter._accessorMethod;
    this._field = paramBeanPropertyWriter._field;
    this._serializer = paramBeanPropertyWriter._serializer;
    this._nullSerializer = paramBeanPropertyWriter._nullSerializer;
    if (paramBeanPropertyWriter._internalSettings != null) {
      this._internalSettings = new HashMap(paramBeanPropertyWriter._internalSettings);
    }
    this._cfgSerializationType = paramBeanPropertyWriter._cfgSerializationType;
    this._dynamicSerializers = paramBeanPropertyWriter._dynamicSerializers;
    this._suppressNulls = paramBeanPropertyWriter._suppressNulls;
    this._suppressableValue = paramBeanPropertyWriter._suppressableValue;
    this._includeInViews = paramBeanPropertyWriter._includeInViews;
    this._typeSerializer = paramBeanPropertyWriter._typeSerializer;
    this._nonTrivialBaseType = paramBeanPropertyWriter._nonTrivialBaseType;
    this._metadata = paramBeanPropertyWriter._metadata;
  }
  
  protected void _depositSchemaProperty(ObjectNode paramObjectNode, JsonNode paramJsonNode)
  {
    paramObjectNode.set(getName(), paramJsonNode);
  }
  
  protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if (this._nonTrivialBaseType != null) {}
    for (PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = paramPropertySerializerMap.findAndAddPrimarySerializer(paramSerializerProvider.constructSpecializedType(this._nonTrivialBaseType, paramClass), paramSerializerProvider, this);; localSerializerAndMapResult = paramPropertySerializerMap.findAndAddPrimarySerializer(paramClass, paramSerializerProvider, this))
    {
      if (paramPropertySerializerMap != localSerializerAndMapResult.map) {
        this._dynamicSerializers = localSerializerAndMapResult.map;
      }
      return localSerializerAndMapResult.serializer;
    }
  }
  
  protected boolean _handleSelfReference(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<?> paramJsonSerializer)
    throws JsonMappingException
  {
    if ((paramSerializerProvider.isEnabled(SerializationFeature.FAIL_ON_SELF_REFERENCES)) && (!paramJsonSerializer.usesObjectId()) && ((paramJsonSerializer instanceof BeanSerializerBase))) {
      throw new JsonMappingException("Direct self-reference leading to cycle");
    }
    return false;
  }
  
  protected BeanPropertyWriter _new(PropertyName paramPropertyName)
  {
    return new BeanPropertyWriter(this, paramPropertyName);
  }
  
  public void assignNullSerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if ((this._nullSerializer != null) && (this._nullSerializer != paramJsonSerializer)) {
      throw new IllegalStateException("Can not override null serializer");
    }
    this._nullSerializer = paramJsonSerializer;
  }
  
  public void assignSerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    if ((this._serializer != null) && (this._serializer != paramJsonSerializer)) {
      throw new IllegalStateException("Can not override serializer");
    }
    this._serializer = paramJsonSerializer;
  }
  
  public void assignTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    this._typeSerializer = paramTypeSerializer;
  }
  
  public void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor)
    throws JsonMappingException
  {
    if (paramJsonObjectFormatVisitor != null)
    {
      if (!isRequired()) {
        break label19;
      }
      paramJsonObjectFormatVisitor.property(this);
    }
    for (;;)
    {
      return;
      label19:
      paramJsonObjectFormatVisitor.optionalProperty(this);
    }
  }
  
  @Deprecated
  public void depositSchemaProperty(ObjectNode paramObjectNode, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    JavaType localJavaType = getSerializationType();
    Object localObject;
    JsonSerializer localJsonSerializer;
    boolean bool;
    if (localJavaType == null)
    {
      localObject = getGenericPropertyType();
      localJsonSerializer = getSerializer();
      if (localJsonSerializer == null) {
        localJsonSerializer = paramSerializerProvider.findValueSerializer(getType(), this);
      }
      if (isRequired()) {
        break label89;
      }
      bool = true;
      label47:
      if (!(localJsonSerializer instanceof SchemaAware)) {
        break label95;
      }
    }
    label89:
    label95:
    for (JsonNode localJsonNode = ((SchemaAware)localJsonSerializer).getSchema(paramSerializerProvider, (Type)localObject, bool);; localJsonNode = JsonSchema.getDefaultSchemaNode())
    {
      _depositSchemaProperty(paramObjectNode, localJsonNode);
      return;
      localObject = localJavaType.getRawClass();
      break;
      bool = false;
      break label47;
    }
  }
  
  public JsonFormat.Value findFormatOverrides(AnnotationIntrospector paramAnnotationIntrospector)
  {
    JsonFormat.Value localValue1 = this._format;
    if (localValue1 == null)
    {
      if ((paramAnnotationIntrospector != null) && (this._member != null)) {
        break label46;
      }
      localValue1 = null;
      if (localValue1 != null) {
        break label58;
      }
    }
    label46:
    label58:
    for (JsonFormat.Value localValue2 = NO_FORMAT;; localValue2 = localValue1)
    {
      this._format = localValue2;
      if (localValue1 == NO_FORMAT) {
        localValue1 = null;
      }
      return localValue1;
      localValue1 = paramAnnotationIntrospector.findFormat(this._member);
      break;
    }
  }
  
  public final Object get(Object paramObject)
    throws Exception
  {
    if (this._accessorMethod == null) {}
    for (Object localObject = this._field.get(paramObject);; localObject = this._accessorMethod.invoke(paramObject, new Object[0])) {
      return localObject;
    }
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    if (this._member == null) {}
    for (Object localObject = null;; localObject = this._member.getAnnotation(paramClass)) {
      return (A)localObject;
    }
  }
  
  public <A extends Annotation> A getContextAnnotation(Class<A> paramClass)
  {
    if (this._contextAnnotations == null) {}
    for (Object localObject = null;; localObject = this._contextAnnotations.get(paramClass)) {
      return (A)localObject;
    }
  }
  
  public PropertyName getFullName()
  {
    return new PropertyName(this._name.getValue());
  }
  
  public Type getGenericPropertyType()
  {
    Type localType;
    if (this._accessorMethod != null) {
      localType = this._accessorMethod.getGenericReturnType();
    }
    for (;;)
    {
      return localType;
      if (this._field != null) {
        localType = this._field.getGenericType();
      } else {
        localType = null;
      }
    }
  }
  
  public Object getInternalSetting(Object paramObject)
  {
    if (this._internalSettings == null) {}
    for (Object localObject = null;; localObject = this._internalSettings.get(paramObject)) {
      return localObject;
    }
  }
  
  public AnnotatedMember getMember()
  {
    return this._member;
  }
  
  public PropertyMetadata getMetadata()
  {
    return this._metadata;
  }
  
  public String getName()
  {
    return this._name.getValue();
  }
  
  public Class<?> getPropertyType()
  {
    if (this._accessorMethod != null) {}
    for (Class localClass = this._accessorMethod.getReturnType();; localClass = this._field.getType()) {
      return localClass;
    }
  }
  
  public Class<?> getRawSerializationType()
  {
    if (this._cfgSerializationType == null) {}
    for (Object localObject = null;; localObject = this._cfgSerializationType.getRawClass()) {
      return (Class<?>)localObject;
    }
  }
  
  public JavaType getSerializationType()
  {
    return this._cfgSerializationType;
  }
  
  public SerializableString getSerializedName()
  {
    return this._name;
  }
  
  public JsonSerializer<Object> getSerializer()
  {
    return this._serializer;
  }
  
  public JavaType getType()
  {
    return this._declaredType;
  }
  
  public TypeSerializer getTypeSerializer()
  {
    return this._typeSerializer;
  }
  
  public Class<?>[] getViews()
  {
    return this._includeInViews;
  }
  
  public PropertyName getWrapperName()
  {
    return this._wrapperName;
  }
  
  public boolean hasNullSerializer()
  {
    if (this._nullSerializer != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasSerializer()
  {
    if (this._serializer != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isRequired()
  {
    return this._metadata.isRequired();
  }
  
  public boolean isUnwrapping()
  {
    return false;
  }
  
  public boolean isVirtual()
  {
    return false;
  }
  
  Object readResolve()
  {
    if ((this._member instanceof AnnotatedField))
    {
      this._accessorMethod = null;
      this._field = ((Field)this._member.getMember());
    }
    for (;;)
    {
      if (this._serializer == null) {
        this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
      }
      return this;
      if ((this._member instanceof AnnotatedMethod))
      {
        this._accessorMethod = ((Method)this._member.getMember());
        this._field = null;
      }
    }
  }
  
  public Object removeInternalSetting(Object paramObject)
  {
    Object localObject = null;
    if (this._internalSettings != null)
    {
      localObject = this._internalSettings.remove(paramObject);
      if (this._internalSettings.size() == 0) {
        this._internalSettings = null;
      }
    }
    return localObject;
  }
  
  public BeanPropertyWriter rename(NameTransformer paramNameTransformer)
  {
    String str = paramNameTransformer.transform(this._name.getValue());
    if (str.equals(this._name.toString())) {}
    for (;;)
    {
      return this;
      this = _new(PropertyName.construct(str));
    }
  }
  
  public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject;
    if (this._accessorMethod == null)
    {
      localObject = this._field.get(paramObject);
      if (localObject != null) {
        break label64;
      }
      if (this._nullSerializer == null) {
        break label57;
      }
      this._nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      localObject = this._accessorMethod.invoke(paramObject, new Object[0]);
      break;
      label57:
      paramJsonGenerator.writeNull();
      continue;
      label64:
      JsonSerializer localJsonSerializer = this._serializer;
      if (localJsonSerializer == null)
      {
        Class localClass = localObject.getClass();
        PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
        localJsonSerializer = localPropertySerializerMap.serializerFor(localClass);
        if (localJsonSerializer == null) {
          localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        }
      }
      if (this._suppressableValue != null) {
        if (MARKER_FOR_EMPTY == this._suppressableValue)
        {
          if (localJsonSerializer.isEmpty(paramSerializerProvider, localObject)) {
            serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
          }
        }
        else if (this._suppressableValue.equals(localObject))
        {
          serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
          continue;
        }
      }
      if ((localObject != paramObject) || (!_handleSelfReference(paramObject, paramJsonGenerator, paramSerializerProvider, localJsonSerializer))) {
        if (this._typeSerializer == null) {
          localJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        } else {
          localJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
        }
      }
    }
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject;
    if (this._accessorMethod == null)
    {
      localObject = this._field.get(paramObject);
      if (localObject != null) {
        break label65;
      }
      if (this._nullSerializer != null)
      {
        paramJsonGenerator.writeFieldName(this._name);
        this._nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
      label46:
      return;
      break label142;
    }
    for (;;)
    {
      localObject = this._accessorMethod.invoke(paramObject, new Object[0]);
      break;
      label65:
      JsonSerializer localJsonSerializer = this._serializer;
      if (localJsonSerializer == null)
      {
        Class localClass = localObject.getClass();
        PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
        localJsonSerializer = localPropertySerializerMap.serializerFor(localClass);
        if (localJsonSerializer == null) {
          localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        }
      }
      if (this._suppressableValue != null)
      {
        if (MARKER_FOR_EMPTY == this._suppressableValue) {
          if (localJsonSerializer.isEmpty(paramSerializerProvider, localObject)) {
            continue;
          }
        }
      }
      else
      {
        label142:
        if ((localObject == paramObject) && (_handleSelfReference(paramObject, paramJsonGenerator, paramSerializerProvider, localJsonSerializer))) {
          continue;
        }
        paramJsonGenerator.writeFieldName(this._name);
        if (this._typeSerializer != null) {
          break label202;
        }
        localJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        continue;
      }
      if (!this._suppressableValue.equals(localObject)) {
        break label46;
      }
      continue;
      label202:
      localJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
    }
  }
  
  public void serializeAsOmittedField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    if (!paramJsonGenerator.canOmitFields()) {
      paramJsonGenerator.writeOmittedField(this._name.getValue());
    }
  }
  
  public void serializeAsPlaceholder(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    if (this._nullSerializer != null) {
      this._nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeNull();
    }
  }
  
  public Object setInternalSetting(Object paramObject1, Object paramObject2)
  {
    if (this._internalSettings == null) {
      this._internalSettings = new HashMap();
    }
    return this._internalSettings.put(paramObject1, paramObject2);
  }
  
  public void setNonTrivialBaseType(JavaType paramJavaType)
  {
    this._nonTrivialBaseType = paramJavaType;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(40);
    localStringBuilder.append("property '").append(getName()).append("' (");
    if (this._accessorMethod != null)
    {
      localStringBuilder.append("via method ").append(this._accessorMethod.getDeclaringClass().getName()).append("#").append(this._accessorMethod.getName());
      if (this._serializer != null) {
        break label160;
      }
      localStringBuilder.append(", no static serializer");
    }
    for (;;)
    {
      localStringBuilder.append(')');
      return localStringBuilder.toString();
      if (this._field != null)
      {
        localStringBuilder.append("field \"").append(this._field.getDeclaringClass().getName()).append("#").append(this._field.getName());
        break;
      }
      localStringBuilder.append("virtual");
      break;
      label160:
      localStringBuilder.append(", static serializer of type " + this._serializer.getClass().getName());
    }
  }
  
  public BeanPropertyWriter unwrappingWriter(NameTransformer paramNameTransformer)
  {
    return new UnwrappingBeanPropertyWriter(this, paramNameTransformer);
  }
  
  public boolean willSuppressNulls()
  {
    return this._suppressNulls;
  }
  
  public boolean wouldConflictWithName(PropertyName paramPropertyName)
  {
    boolean bool;
    if (this._wrapperName != null) {
      bool = this._wrapperName.equals(paramPropertyName);
    }
    for (;;)
    {
      return bool;
      if ((paramPropertyName.hasSimpleName(this._name.getValue())) && (!paramPropertyName.hasNamespace())) {
        bool = true;
      } else {
        bool = false;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/BeanPropertyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */