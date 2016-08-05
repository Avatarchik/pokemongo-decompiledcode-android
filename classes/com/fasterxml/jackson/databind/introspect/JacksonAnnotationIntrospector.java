package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.annotation.JsonTypeInfo.None;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.None;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.KeyDeserializer.None;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.AttributePropertyWriter;
import com.fasterxml.jackson.databind.ser.std.RawSerializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter.None;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class JacksonAnnotationIntrospector
  extends AnnotationIntrospector
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  private final Boolean _findSortAlpha(Annotated paramAnnotated)
  {
    JsonPropertyOrder localJsonPropertyOrder = (JsonPropertyOrder)_findAnnotation(paramAnnotated, JsonPropertyOrder.class);
    if ((localJsonPropertyOrder != null) && (localJsonPropertyOrder.alphabetic())) {}
    for (Boolean localBoolean = Boolean.TRUE;; localBoolean = null) {
      return localBoolean;
    }
  }
  
  protected Class<?> _classIfExplicit(Class<?> paramClass)
  {
    if ((paramClass == null) || (ClassUtil.isBogusClass(paramClass))) {
      paramClass = null;
    }
    return paramClass;
  }
  
  protected Class<?> _classIfExplicit(Class<?> paramClass1, Class<?> paramClass2)
  {
    Class localClass = _classIfExplicit(paramClass1);
    if ((localClass == null) || (localClass == paramClass2)) {
      localClass = null;
    }
    return localClass;
  }
  
  protected StdTypeResolverBuilder _constructNoTypeResolverBuilder()
  {
    return StdTypeResolverBuilder.noTypeInfoBuilder();
  }
  
  protected StdTypeResolverBuilder _constructStdTypeResolverBuilder()
  {
    return new StdTypeResolverBuilder();
  }
  
  protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Attr paramAttr, MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType)
  {
    if (paramAttr.required()) {}
    for (PropertyMetadata localPropertyMetadata = PropertyMetadata.STD_REQUIRED;; localPropertyMetadata = PropertyMetadata.STD_OPTIONAL)
    {
      String str = paramAttr.value();
      PropertyName localPropertyName = _propertyName(paramAttr.propName(), paramAttr.propNamespace());
      if (!localPropertyName.hasSimpleName()) {
        localPropertyName = PropertyName.construct(str);
      }
      return AttributePropertyWriter.construct(str, SimpleBeanPropertyDefinition.construct(paramMapperConfig, new VirtualAnnotatedMember(paramAnnotatedClass, paramAnnotatedClass.getRawType(), str, paramJavaType.getRawClass()), localPropertyName, localPropertyMetadata, paramAttr.include()), paramAnnotatedClass.getAnnotations(), paramJavaType);
    }
  }
  
  protected BeanPropertyWriter _constructVirtualProperty(JsonAppend.Prop paramProp, MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass)
  {
    PropertyMetadata localPropertyMetadata;
    JavaType localJavaType;
    SimpleBeanPropertyDefinition localSimpleBeanPropertyDefinition;
    Class localClass;
    HandlerInstantiator localHandlerInstantiator;
    if (paramProp.required())
    {
      localPropertyMetadata = PropertyMetadata.STD_REQUIRED;
      PropertyName localPropertyName = _propertyName(paramProp.name(), paramProp.namespace());
      localJavaType = paramMapperConfig.constructType(paramProp.type());
      localSimpleBeanPropertyDefinition = SimpleBeanPropertyDefinition.construct(paramMapperConfig, new VirtualAnnotatedMember(paramAnnotatedClass, paramAnnotatedClass.getRawType(), localPropertyName.getSimpleName(), localJavaType.getRawClass()), localPropertyName, localPropertyMetadata, paramProp.include());
      localClass = paramProp.value();
      localHandlerInstantiator = paramMapperConfig.getHandlerInstantiator();
      if (localHandlerInstantiator != null) {
        break label143;
      }
    }
    label143:
    for (VirtualBeanPropertyWriter localVirtualBeanPropertyWriter = null;; localVirtualBeanPropertyWriter = localHandlerInstantiator.virtualPropertyWriterInstance(paramMapperConfig, localClass))
    {
      if (localVirtualBeanPropertyWriter == null) {
        localVirtualBeanPropertyWriter = (VirtualBeanPropertyWriter)ClassUtil.createInstance(localClass, paramMapperConfig.canOverrideAccessModifiers());
      }
      return localVirtualBeanPropertyWriter.withConfig(paramMapperConfig, paramAnnotatedClass, localSimpleBeanPropertyDefinition, localJavaType);
      localPropertyMetadata = PropertyMetadata.STD_OPTIONAL;
      break;
    }
  }
  
  protected final Object _findFilterId(Annotated paramAnnotated)
  {
    JsonFilter localJsonFilter = (JsonFilter)_findAnnotation(paramAnnotated, JsonFilter.class);
    String str;
    if (localJsonFilter != null)
    {
      str = localJsonFilter.value();
      if (str.length() <= 0) {}
    }
    for (;;)
    {
      return str;
      str = null;
    }
  }
  
  protected TypeResolverBuilder<?> _findTypeResolver(MapperConfig<?> paramMapperConfig, Annotated paramAnnotated, JavaType paramJavaType)
  {
    JsonTypeInfo localJsonTypeInfo = (JsonTypeInfo)_findAnnotation(paramAnnotated, JsonTypeInfo.class);
    JsonTypeResolver localJsonTypeResolver = (JsonTypeResolver)_findAnnotation(paramAnnotated, JsonTypeResolver.class);
    Object localObject2;
    Object localObject1;
    label54:
    JsonTypeIdResolver localJsonTypeIdResolver;
    if (localJsonTypeResolver != null)
    {
      if (localJsonTypeInfo == null)
      {
        localObject2 = null;
        return (TypeResolverBuilder<?>)localObject2;
      }
      localObject1 = paramMapperConfig.typeResolverBuilderInstance(paramAnnotated, localJsonTypeResolver.value());
      localJsonTypeIdResolver = (JsonTypeIdResolver)_findAnnotation(paramAnnotated, JsonTypeIdResolver.class);
      if (localJsonTypeIdResolver != null) {
        break label253;
      }
    }
    label253:
    for (TypeIdResolver localTypeIdResolver = null;; localTypeIdResolver = paramMapperConfig.typeIdResolverInstance(paramAnnotated, localJsonTypeIdResolver.value()))
    {
      if (localTypeIdResolver != null) {
        localTypeIdResolver.init(paramJavaType);
      }
      TypeResolverBuilder localTypeResolverBuilder1 = ((TypeResolverBuilder)localObject1).init(localJsonTypeInfo.use(), localTypeIdResolver);
      JsonTypeInfo.As localAs = localJsonTypeInfo.include();
      if ((localAs == JsonTypeInfo.As.EXTERNAL_PROPERTY) && ((paramAnnotated instanceof AnnotatedClass))) {
        localAs = JsonTypeInfo.As.PROPERTY;
      }
      TypeResolverBuilder localTypeResolverBuilder2 = localTypeResolverBuilder1.inclusion(localAs).typeProperty(localJsonTypeInfo.property());
      Class localClass = localJsonTypeInfo.defaultImpl();
      if ((localClass != JsonTypeInfo.None.class) && (!localClass.isAnnotation())) {
        localTypeResolverBuilder2 = localTypeResolverBuilder2.defaultImpl(localClass);
      }
      localObject2 = localTypeResolverBuilder2.typeIdVisibility(localJsonTypeInfo.visible());
      break;
      if (localJsonTypeInfo == null)
      {
        localObject2 = null;
        break;
      }
      if (localJsonTypeInfo.use() == JsonTypeInfo.Id.NONE)
      {
        localObject2 = _constructNoTypeResolverBuilder();
        break;
      }
      localObject1 = _constructStdTypeResolverBuilder();
      break label54;
    }
  }
  
  protected boolean _isIgnorable(Annotated paramAnnotated)
  {
    JsonIgnore localJsonIgnore = (JsonIgnore)_findAnnotation(paramAnnotated, JsonIgnore.class);
    if ((localJsonIgnore != null) && (localJsonIgnore.value())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected PropertyName _propertyName(String paramString1, String paramString2)
  {
    PropertyName localPropertyName;
    if (paramString1.isEmpty()) {
      localPropertyName = PropertyName.USE_DEFAULT;
    }
    for (;;)
    {
      return localPropertyName;
      if ((paramString2 == null) || (paramString2.isEmpty())) {
        localPropertyName = PropertyName.construct(paramString1);
      } else {
        localPropertyName = PropertyName.construct(paramString1, paramString2);
      }
    }
  }
  
  public void findAndAddVirtualProperties(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, List<BeanPropertyWriter> paramList)
  {
    JsonAppend localJsonAppend = (JsonAppend)_findAnnotation(paramAnnotatedClass, JsonAppend.class);
    if (localJsonAppend == null) {
      return;
    }
    boolean bool = localJsonAppend.prepend();
    JavaType localJavaType = null;
    JsonAppend.Attr[] arrayOfAttr = localJsonAppend.attrs();
    int i = 0;
    int j = arrayOfAttr.length;
    if (i < j)
    {
      if (localJavaType == null) {
        localJavaType = paramMapperConfig.constructType(Object.class);
      }
      BeanPropertyWriter localBeanPropertyWriter2 = _constructVirtualProperty(arrayOfAttr[i], paramMapperConfig, paramAnnotatedClass, localJavaType);
      if (bool) {
        paramList.add(i, localBeanPropertyWriter2);
      }
      for (;;)
      {
        i++;
        break;
        paramList.add(localBeanPropertyWriter2);
      }
    }
    JsonAppend.Prop[] arrayOfProp = localJsonAppend.props();
    int k = 0;
    int m = arrayOfProp.length;
    label134:
    BeanPropertyWriter localBeanPropertyWriter1;
    if (k < m)
    {
      localBeanPropertyWriter1 = _constructVirtualProperty(arrayOfProp[k], paramMapperConfig, paramAnnotatedClass);
      if (!bool) {
        break label175;
      }
      paramList.add(k, localBeanPropertyWriter1);
    }
    for (;;)
    {
      k++;
      break label134;
      break;
      label175:
      paramList.add(localBeanPropertyWriter1);
    }
  }
  
  public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass paramAnnotatedClass, VisibilityChecker<?> paramVisibilityChecker)
  {
    JsonAutoDetect localJsonAutoDetect = (JsonAutoDetect)_findAnnotation(paramAnnotatedClass, JsonAutoDetect.class);
    if (localJsonAutoDetect == null) {}
    for (;;)
    {
      return paramVisibilityChecker;
      paramVisibilityChecker = paramVisibilityChecker.with(localJsonAutoDetect);
    }
  }
  
  public Object findContentDeserializer(Annotated paramAnnotated)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    Class localClass;
    if (localJsonDeserialize != null)
    {
      localClass = localJsonDeserialize.contentUsing();
      if (localClass == JsonDeserializer.None.class) {}
    }
    for (;;)
    {
      return localClass;
      localClass = null;
    }
  }
  
  public Object findContentSerializer(Annotated paramAnnotated)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    Class localClass;
    if (localJsonSerialize != null)
    {
      localClass = localJsonSerialize.contentUsing();
      if (localClass == JsonSerializer.None.class) {}
    }
    for (;;)
    {
      return localClass;
      localClass = null;
    }
  }
  
  public JsonCreator.Mode findCreatorBinding(Annotated paramAnnotated)
  {
    JsonCreator localJsonCreator = (JsonCreator)_findAnnotation(paramAnnotated, JsonCreator.class);
    if (localJsonCreator == null) {}
    for (JsonCreator.Mode localMode = null;; localMode = localJsonCreator.mode()) {
      return localMode;
    }
  }
  
  public Object findDeserializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotatedMember, JsonDeserialize.class);
    if (localJsonDeserialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonDeserialize.contentConverter(), Converter.None.class)) {
      return localObject;
    }
  }
  
  public Class<?> findDeserializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (localJsonDeserialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonDeserialize.contentAs())) {
      return (Class<?>)localObject;
    }
  }
  
  public Object findDeserializationConverter(Annotated paramAnnotated)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (localJsonDeserialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonDeserialize.converter(), Converter.None.class)) {
      return localObject;
    }
  }
  
  public Class<?> findDeserializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (localJsonDeserialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonDeserialize.keyAs())) {
      return (Class<?>)localObject;
    }
  }
  
  public Class<?> findDeserializationType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    if (localJsonDeserialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonDeserialize.as())) {
      return (Class<?>)localObject;
    }
  }
  
  public Object findDeserializer(Annotated paramAnnotated)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    Class localClass;
    if (localJsonDeserialize != null)
    {
      localClass = localJsonDeserialize.using();
      if (localClass == JsonDeserializer.None.class) {}
    }
    for (;;)
    {
      return localClass;
      localClass = null;
    }
  }
  
  public String findEnumValue(Enum<?> paramEnum)
  {
    try
    {
      Field localField = paramEnum.getClass().getField(paramEnum.name());
      if (localField == null) {
        break label63;
      }
      JsonProperty localJsonProperty = (JsonProperty)localField.getAnnotation(JsonProperty.class);
      if (localJsonProperty == null) {
        break label63;
      }
      str = localJsonProperty.value();
      if (str == null) {
        break label63;
      }
      boolean bool = str.isEmpty();
      if (bool) {
        break label63;
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        String str = paramEnum.name();
      }
    }
    catch (SecurityException localSecurityException)
    {
      label63:
      for (;;) {}
    }
    return str;
  }
  
  public Object findFilterId(Annotated paramAnnotated)
  {
    return _findFilterId(paramAnnotated);
  }
  
  @Deprecated
  public Object findFilterId(AnnotatedClass paramAnnotatedClass)
  {
    return _findFilterId(paramAnnotatedClass);
  }
  
  public JsonFormat.Value findFormat(Annotated paramAnnotated)
  {
    JsonFormat localJsonFormat = (JsonFormat)_findAnnotation(paramAnnotated, JsonFormat.class);
    if (localJsonFormat == null) {}
    for (JsonFormat.Value localValue = null;; localValue = new JsonFormat.Value(localJsonFormat)) {
      return localValue;
    }
  }
  
  public Boolean findIgnoreUnknownProperties(AnnotatedClass paramAnnotatedClass)
  {
    JsonIgnoreProperties localJsonIgnoreProperties = (JsonIgnoreProperties)_findAnnotation(paramAnnotatedClass, JsonIgnoreProperties.class);
    if (localJsonIgnoreProperties == null) {}
    for (Boolean localBoolean = null;; localBoolean = Boolean.valueOf(localJsonIgnoreProperties.ignoreUnknown())) {
      return localBoolean;
    }
  }
  
  public String findImplicitPropertyName(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public Object findInjectableValueId(AnnotatedMember paramAnnotatedMember)
  {
    JacksonInject localJacksonInject = (JacksonInject)_findAnnotation(paramAnnotatedMember, JacksonInject.class);
    Object localObject;
    if (localJacksonInject == null) {
      localObject = null;
    }
    for (;;)
    {
      return localObject;
      localObject = localJacksonInject.value();
      if (((String)localObject).length() == 0) {
        if (!(paramAnnotatedMember instanceof AnnotatedMethod))
        {
          localObject = paramAnnotatedMember.getRawType().getName();
        }
        else
        {
          AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)paramAnnotatedMember;
          if (localAnnotatedMethod.getParameterCount() == 0) {
            localObject = paramAnnotatedMember.getRawType().getName();
          } else {
            localObject = localAnnotatedMethod.getRawParameterType(0).getName();
          }
        }
      }
    }
  }
  
  public Object findKeyDeserializer(Annotated paramAnnotated)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotated, JsonDeserialize.class);
    Class localClass;
    if (localJsonDeserialize != null)
    {
      localClass = localJsonDeserialize.keyUsing();
      if (localClass == KeyDeserializer.None.class) {}
    }
    for (;;)
    {
      return localClass;
      localClass = null;
    }
  }
  
  public Object findKeySerializer(Annotated paramAnnotated)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    Class localClass;
    if (localJsonSerialize != null)
    {
      localClass = localJsonSerialize.keyUsing();
      if (localClass == JsonSerializer.None.class) {}
    }
    for (;;)
    {
      return localClass;
      localClass = null;
    }
  }
  
  public PropertyName findNameForDeserialization(Annotated paramAnnotated)
  {
    JsonSetter localJsonSetter = (JsonSetter)_findAnnotation(paramAnnotated, JsonSetter.class);
    String str;
    if (localJsonSetter != null) {
      str = localJsonSetter.value();
    }
    for (PropertyName localPropertyName = PropertyName.construct(str);; localPropertyName = null)
    {
      return localPropertyName;
      JsonProperty localJsonProperty = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
      if (localJsonProperty != null)
      {
        str = localJsonProperty.value();
        break;
      }
      if ((_hasAnnotation(paramAnnotated, JsonDeserialize.class)) || (_hasAnnotation(paramAnnotated, JsonView.class)) || (_hasAnnotation(paramAnnotated, JsonUnwrapped.class)) || (_hasAnnotation(paramAnnotated, JsonBackReference.class)) || (_hasAnnotation(paramAnnotated, JsonManagedReference.class)))
      {
        str = "";
        break;
      }
    }
  }
  
  public PropertyName findNameForSerialization(Annotated paramAnnotated)
  {
    JsonGetter localJsonGetter = (JsonGetter)_findAnnotation(paramAnnotated, JsonGetter.class);
    String str;
    if (localJsonGetter != null) {
      str = localJsonGetter.value();
    }
    for (PropertyName localPropertyName = PropertyName.construct(str);; localPropertyName = null)
    {
      return localPropertyName;
      JsonProperty localJsonProperty = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
      if (localJsonProperty != null)
      {
        str = localJsonProperty.value();
        break;
      }
      if ((_hasAnnotation(paramAnnotated, JsonSerialize.class)) || (_hasAnnotation(paramAnnotated, JsonView.class)) || (_hasAnnotation(paramAnnotated, JsonRawValue.class)))
      {
        str = "";
        break;
      }
    }
  }
  
  public Object findNamingStrategy(AnnotatedClass paramAnnotatedClass)
  {
    JsonNaming localJsonNaming = (JsonNaming)_findAnnotation(paramAnnotatedClass, JsonNaming.class);
    if (localJsonNaming == null) {}
    for (Object localObject = null;; localObject = localJsonNaming.value()) {
      return localObject;
    }
  }
  
  public Object findNullSerializer(Annotated paramAnnotated)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    Class localClass;
    if (localJsonSerialize != null)
    {
      localClass = localJsonSerialize.nullsUsing();
      if (localClass == JsonSerializer.None.class) {}
    }
    for (;;)
    {
      return localClass;
      localClass = null;
    }
  }
  
  public ObjectIdInfo findObjectIdInfo(Annotated paramAnnotated)
  {
    JsonIdentityInfo localJsonIdentityInfo = (JsonIdentityInfo)_findAnnotation(paramAnnotated, JsonIdentityInfo.class);
    if ((localJsonIdentityInfo == null) || (localJsonIdentityInfo.generator() == ObjectIdGenerators.None.class)) {}
    for (ObjectIdInfo localObjectIdInfo = null;; localObjectIdInfo = new ObjectIdInfo(PropertyName.construct(localJsonIdentityInfo.property()), localJsonIdentityInfo.scope(), localJsonIdentityInfo.generator(), localJsonIdentityInfo.resolver())) {
      return localObjectIdInfo;
    }
  }
  
  public ObjectIdInfo findObjectReferenceInfo(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
  {
    JsonIdentityReference localJsonIdentityReference = (JsonIdentityReference)_findAnnotation(paramAnnotated, JsonIdentityReference.class);
    if (localJsonIdentityReference != null) {
      paramObjectIdInfo = paramObjectIdInfo.withAlwaysAsId(localJsonIdentityReference.alwaysAsId());
    }
    return paramObjectIdInfo;
  }
  
  public Class<?> findPOJOBuilder(AnnotatedClass paramAnnotatedClass)
  {
    JsonDeserialize localJsonDeserialize = (JsonDeserialize)_findAnnotation(paramAnnotatedClass, JsonDeserialize.class);
    if (localJsonDeserialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonDeserialize.builder())) {
      return (Class<?>)localObject;
    }
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass paramAnnotatedClass)
  {
    JsonPOJOBuilder localJsonPOJOBuilder = (JsonPOJOBuilder)_findAnnotation(paramAnnotatedClass, JsonPOJOBuilder.class);
    if (localJsonPOJOBuilder == null) {}
    for (JsonPOJOBuilder.Value localValue = null;; localValue = new JsonPOJOBuilder.Value(localJsonPOJOBuilder)) {
      return localValue;
    }
  }
  
  @Deprecated
  public String[] findPropertiesToIgnore(Annotated paramAnnotated)
  {
    JsonIgnoreProperties localJsonIgnoreProperties = (JsonIgnoreProperties)_findAnnotation(paramAnnotated, JsonIgnoreProperties.class);
    if (localJsonIgnoreProperties == null) {}
    for (String[] arrayOfString = null;; arrayOfString = localJsonIgnoreProperties.value()) {
      return arrayOfString;
    }
  }
  
  public String[] findPropertiesToIgnore(Annotated paramAnnotated, boolean paramBoolean)
  {
    String[] arrayOfString = null;
    JsonIgnoreProperties localJsonIgnoreProperties = (JsonIgnoreProperties)_findAnnotation(paramAnnotated, JsonIgnoreProperties.class);
    if (localJsonIgnoreProperties == null) {}
    for (;;)
    {
      return arrayOfString;
      if (paramBoolean)
      {
        if (localJsonIgnoreProperties.allowGetters()) {}
      }
      else {
        while (!localJsonIgnoreProperties.allowSetters())
        {
          arrayOfString = localJsonIgnoreProperties.value();
          break;
        }
      }
    }
  }
  
  public JsonProperty.Access findPropertyAccess(Annotated paramAnnotated)
  {
    JsonProperty localJsonProperty = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
    if (localJsonProperty != null) {}
    for (JsonProperty.Access localAccess = localJsonProperty.access();; localAccess = null) {
      return localAccess;
    }
  }
  
  public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    if (!paramJavaType.isContainerType()) {
      throw new IllegalArgumentException("Must call method with a container type (got " + paramJavaType + ")");
    }
    return _findTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
  }
  
  public String findPropertyDefaultValue(Annotated paramAnnotated)
  {
    Object localObject = null;
    JsonProperty localJsonProperty = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
    if (localJsonProperty == null) {}
    for (;;)
    {
      return (String)localObject;
      String str = localJsonProperty.defaultValue();
      if (str.isEmpty()) {
        str = null;
      }
      localObject = str;
    }
  }
  
  public String findPropertyDescription(Annotated paramAnnotated)
  {
    JsonPropertyDescription localJsonPropertyDescription = (JsonPropertyDescription)_findAnnotation(paramAnnotated, JsonPropertyDescription.class);
    if (localJsonPropertyDescription == null) {}
    for (String str = null;; str = localJsonPropertyDescription.value()) {
      return str;
    }
  }
  
  public JsonInclude.Value findPropertyInclusion(Annotated paramAnnotated)
  {
    JsonInclude localJsonInclude = (JsonInclude)_findAnnotation(paramAnnotated, JsonInclude.class);
    JsonInclude.Include localInclude1;
    if (localJsonInclude == null)
    {
      localInclude1 = JsonInclude.Include.USE_DEFAULTS;
      JsonSerialize.Inclusion localInclusion;
      if (localInclude1 == JsonInclude.Include.USE_DEFAULTS)
      {
        JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
        if (localJsonSerialize != null) {
          localInclusion = localJsonSerialize.include();
        }
      }
      switch (localInclusion)
      {
      default: 
        label92:
        if (localJsonInclude != null) {
          break;
        }
      }
    }
    for (JsonInclude.Include localInclude2 = JsonInclude.Include.USE_DEFAULTS;; localInclude2 = localJsonInclude.content())
    {
      return JsonInclude.Value.construct(localInclude1, localInclude2);
      localInclude1 = localJsonInclude.value();
      break;
      localInclude1 = JsonInclude.Include.ALWAYS;
      break label92;
      localInclude1 = JsonInclude.Include.NON_NULL;
      break label92;
      localInclude1 = JsonInclude.Include.NON_DEFAULT;
      break label92;
      localInclude1 = JsonInclude.Include.NON_EMPTY;
      break label92;
    }
  }
  
  public Integer findPropertyIndex(Annotated paramAnnotated)
  {
    JsonProperty localJsonProperty = (JsonProperty)_findAnnotation(paramAnnotated, JsonProperty.class);
    int i;
    if (localJsonProperty != null)
    {
      i = localJsonProperty.index();
      if (i == -1) {}
    }
    for (Integer localInteger = Integer.valueOf(i);; localInteger = null) {
      return localInteger;
    }
  }
  
  public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    if (paramJavaType.isContainerType()) {}
    for (Object localObject = null;; localObject = _findTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType)) {
      return (TypeResolverBuilder<?>)localObject;
    }
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember paramAnnotatedMember)
  {
    JsonManagedReference localJsonManagedReference = (JsonManagedReference)_findAnnotation(paramAnnotatedMember, JsonManagedReference.class);
    AnnotationIntrospector.ReferenceProperty localReferenceProperty;
    if (localJsonManagedReference != null) {
      localReferenceProperty = AnnotationIntrospector.ReferenceProperty.managed(localJsonManagedReference.value());
    }
    for (;;)
    {
      return localReferenceProperty;
      JsonBackReference localJsonBackReference = (JsonBackReference)_findAnnotation(paramAnnotatedMember, JsonBackReference.class);
      if (localJsonBackReference != null) {
        localReferenceProperty = AnnotationIntrospector.ReferenceProperty.back(localJsonBackReference.value());
      } else {
        localReferenceProperty = null;
      }
    }
  }
  
  public PropertyName findRootName(AnnotatedClass paramAnnotatedClass)
  {
    JsonRootName localJsonRootName = (JsonRootName)_findAnnotation(paramAnnotatedClass, JsonRootName.class);
    if (localJsonRootName == null) {}
    String str;
    for (PropertyName localPropertyName = null;; localPropertyName = PropertyName.construct(localJsonRootName.value(), str))
    {
      return localPropertyName;
      str = localJsonRootName.namespace();
      if ((str != null) && (str.length() == 0)) {
        str = null;
      }
    }
  }
  
  public Object findSerializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotatedMember, JsonSerialize.class);
    if (localJsonSerialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonSerialize.contentConverter(), Converter.None.class)) {
      return localObject;
    }
  }
  
  public Class<?> findSerializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (localJsonSerialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonSerialize.contentAs())) {
      return (Class<?>)localObject;
    }
  }
  
  public Object findSerializationConverter(Annotated paramAnnotated)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (localJsonSerialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonSerialize.converter(), Converter.None.class)) {
      return localObject;
    }
  }
  
  public JsonInclude.Include findSerializationInclusion(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    JsonInclude localJsonInclude = (JsonInclude)_findAnnotation(paramAnnotated, JsonInclude.class);
    JsonInclude.Include localInclude;
    if (localJsonInclude != null)
    {
      localInclude = localJsonInclude.value();
      if (localInclude == JsonInclude.Include.USE_DEFAULTS) {}
    }
    for (;;)
    {
      return localInclude;
      JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
      JsonSerialize.Inclusion localInclusion;
      if (localJsonSerialize != null) {
        localInclusion = localJsonSerialize.include();
      }
      switch (localInclusion)
      {
      default: 
        localInclude = paramInclude;
        break;
      case ???: 
        localInclude = JsonInclude.Include.ALWAYS;
        break;
      case ???: 
        localInclude = JsonInclude.Include.NON_NULL;
        break;
      case ???: 
        localInclude = JsonInclude.Include.NON_DEFAULT;
        break;
      case ???: 
        localInclude = JsonInclude.Include.NON_EMPTY;
      }
    }
  }
  
  public JsonInclude.Include findSerializationInclusionForContent(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    JsonInclude localJsonInclude = (JsonInclude)_findAnnotation(paramAnnotated, JsonInclude.class);
    JsonInclude.Include localInclude;
    if (localJsonInclude != null)
    {
      localInclude = localJsonInclude.content();
      if (localInclude == JsonInclude.Include.USE_DEFAULTS) {}
    }
    for (;;)
    {
      return localInclude;
      localInclude = paramInclude;
    }
  }
  
  public Class<?> findSerializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (localJsonSerialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonSerialize.keyAs())) {
      return (Class<?>)localObject;
    }
  }
  
  public String[] findSerializationPropertyOrder(AnnotatedClass paramAnnotatedClass)
  {
    JsonPropertyOrder localJsonPropertyOrder = (JsonPropertyOrder)_findAnnotation(paramAnnotatedClass, JsonPropertyOrder.class);
    if (localJsonPropertyOrder == null) {}
    for (String[] arrayOfString = null;; arrayOfString = localJsonPropertyOrder.value()) {
      return arrayOfString;
    }
  }
  
  public Boolean findSerializationSortAlphabetically(Annotated paramAnnotated)
  {
    return _findSortAlpha(paramAnnotated);
  }
  
  public Class<?> findSerializationType(Annotated paramAnnotated)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (localJsonSerialize == null) {}
    for (Object localObject = null;; localObject = _classIfExplicit(localJsonSerialize.as())) {
      return (Class<?>)localObject;
    }
  }
  
  public JsonSerialize.Typing findSerializationTyping(Annotated paramAnnotated)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    if (localJsonSerialize == null) {}
    for (JsonSerialize.Typing localTyping = null;; localTyping = localJsonSerialize.typing()) {
      return localTyping;
    }
  }
  
  public Object findSerializer(Annotated paramAnnotated)
  {
    JsonSerialize localJsonSerialize = (JsonSerialize)_findAnnotation(paramAnnotated, JsonSerialize.class);
    Object localObject;
    if (localJsonSerialize != null)
    {
      localObject = localJsonSerialize.using();
      if (localObject == JsonSerializer.None.class) {}
    }
    for (;;)
    {
      return localObject;
      JsonRawValue localJsonRawValue = (JsonRawValue)_findAnnotation(paramAnnotated, JsonRawValue.class);
      if ((localJsonRawValue != null) && (localJsonRawValue.value())) {
        localObject = new RawSerializer(paramAnnotated.getRawType());
      } else {
        localObject = null;
      }
    }
  }
  
  public List<NamedType> findSubtypes(Annotated paramAnnotated)
  {
    JsonSubTypes localJsonSubTypes = (JsonSubTypes)_findAnnotation(paramAnnotated, JsonSubTypes.class);
    Object localObject;
    if (localJsonSubTypes == null) {
      localObject = null;
    }
    for (;;)
    {
      return (List<NamedType>)localObject;
      JsonSubTypes.Type[] arrayOfType = localJsonSubTypes.value();
      localObject = new ArrayList(arrayOfType.length);
      int i = arrayOfType.length;
      for (int j = 0; j < i; j++)
      {
        JsonSubTypes.Type localType = arrayOfType[j];
        ((ArrayList)localObject).add(new NamedType(localType.value(), localType.name()));
      }
    }
  }
  
  public String findTypeName(AnnotatedClass paramAnnotatedClass)
  {
    JsonTypeName localJsonTypeName = (JsonTypeName)_findAnnotation(paramAnnotatedClass, JsonTypeName.class);
    if (localJsonTypeName == null) {}
    for (String str = null;; str = localJsonTypeName.value()) {
      return str;
    }
  }
  
  public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType)
  {
    return _findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType);
  }
  
  public NameTransformer findUnwrappingNameTransformer(AnnotatedMember paramAnnotatedMember)
  {
    JsonUnwrapped localJsonUnwrapped = (JsonUnwrapped)_findAnnotation(paramAnnotatedMember, JsonUnwrapped.class);
    if ((localJsonUnwrapped == null) || (!localJsonUnwrapped.enabled())) {}
    for (NameTransformer localNameTransformer = null;; localNameTransformer = NameTransformer.simpleTransformer(localJsonUnwrapped.prefix(), localJsonUnwrapped.suffix())) {
      return localNameTransformer;
    }
  }
  
  public Object findValueInstantiator(AnnotatedClass paramAnnotatedClass)
  {
    JsonValueInstantiator localJsonValueInstantiator = (JsonValueInstantiator)_findAnnotation(paramAnnotatedClass, JsonValueInstantiator.class);
    if (localJsonValueInstantiator == null) {}
    for (Object localObject = null;; localObject = localJsonValueInstantiator.value()) {
      return localObject;
    }
  }
  
  public Class<?>[] findViews(Annotated paramAnnotated)
  {
    JsonView localJsonView = (JsonView)_findAnnotation(paramAnnotated, JsonView.class);
    if (localJsonView == null) {}
    for (Object localObject = null;; localObject = localJsonView.value()) {
      return (Class<?>[])localObject;
    }
  }
  
  public boolean hasAnyGetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return _hasAnnotation(paramAnnotatedMethod, JsonAnyGetter.class);
  }
  
  public boolean hasAnySetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return _hasAnnotation(paramAnnotatedMethod, JsonAnySetter.class);
  }
  
  public boolean hasAsValueAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    JsonValue localJsonValue = (JsonValue)_findAnnotation(paramAnnotatedMethod, JsonValue.class);
    if ((localJsonValue != null) && (localJsonValue.value())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasCreatorAnnotation(Annotated paramAnnotated)
  {
    JsonCreator localJsonCreator = (JsonCreator)_findAnnotation(paramAnnotated, JsonCreator.class);
    if ((localJsonCreator != null) && (localJsonCreator.mode() != JsonCreator.Mode.DISABLED)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember paramAnnotatedMember)
  {
    return _isIgnorable(paramAnnotatedMember);
  }
  
  public Boolean hasRequiredMarker(AnnotatedMember paramAnnotatedMember)
  {
    JsonProperty localJsonProperty = (JsonProperty)_findAnnotation(paramAnnotatedMember, JsonProperty.class);
    if (localJsonProperty != null) {}
    for (Boolean localBoolean = Boolean.valueOf(localJsonProperty.required());; localBoolean = null) {
      return localBoolean;
    }
  }
  
  public boolean isAnnotationBundle(Annotation paramAnnotation)
  {
    if (paramAnnotation.annotationType().getAnnotation(JacksonAnnotationsInside.class) != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Boolean isIgnorableType(AnnotatedClass paramAnnotatedClass)
  {
    JsonIgnoreType localJsonIgnoreType = (JsonIgnoreType)_findAnnotation(paramAnnotatedClass, JsonIgnoreType.class);
    if (localJsonIgnoreType == null) {}
    for (Boolean localBoolean = null;; localBoolean = Boolean.valueOf(localJsonIgnoreType.value())) {
      return localBoolean;
    }
  }
  
  public Boolean isTypeId(AnnotatedMember paramAnnotatedMember)
  {
    return Boolean.valueOf(_hasAnnotation(paramAnnotatedMember, JsonTypeId.class));
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/JacksonAnnotationIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */