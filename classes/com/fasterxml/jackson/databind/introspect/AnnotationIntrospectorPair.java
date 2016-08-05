package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.KeyDeserializer.None;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationIntrospectorPair
  extends AnnotationIntrospector
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final AnnotationIntrospector _primary;
  protected final AnnotationIntrospector _secondary;
  
  public AnnotationIntrospectorPair(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2)
  {
    this._primary = paramAnnotationIntrospector1;
    this._secondary = paramAnnotationIntrospector2;
  }
  
  public static AnnotationIntrospector create(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2)
  {
    if (paramAnnotationIntrospector1 == null) {}
    for (;;)
    {
      return paramAnnotationIntrospector2;
      if (paramAnnotationIntrospector2 == null) {
        paramAnnotationIntrospector2 = paramAnnotationIntrospector1;
      } else {
        paramAnnotationIntrospector2 = new AnnotationIntrospectorPair(paramAnnotationIntrospector1, paramAnnotationIntrospector2);
      }
    }
  }
  
  protected boolean _isExplicitClassOrOb(Object paramObject, Class<?> paramClass)
  {
    boolean bool = true;
    if (paramObject == null) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      if ((paramObject instanceof Class))
      {
        Class localClass = (Class)paramObject;
        if ((localClass == paramClass) || (ClassUtil.isBogusClass(localClass))) {
          bool = false;
        }
      }
    }
  }
  
  public Collection<AnnotationIntrospector> allIntrospectors()
  {
    return allIntrospectors(new ArrayList());
  }
  
  public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> paramCollection)
  {
    this._primary.allIntrospectors(paramCollection);
    this._secondary.allIntrospectors(paramCollection);
    return paramCollection;
  }
  
  public void findAndAddVirtualProperties(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, List<BeanPropertyWriter> paramList)
  {
    this._primary.findAndAddVirtualProperties(paramMapperConfig, paramAnnotatedClass, paramList);
    this._secondary.findAndAddVirtualProperties(paramMapperConfig, paramAnnotatedClass, paramList);
  }
  
  public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass paramAnnotatedClass, VisibilityChecker<?> paramVisibilityChecker)
  {
    VisibilityChecker localVisibilityChecker = this._secondary.findAutoDetectVisibility(paramAnnotatedClass, paramVisibilityChecker);
    return this._primary.findAutoDetectVisibility(paramAnnotatedClass, localVisibilityChecker);
  }
  
  public Object findContentDeserializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findContentDeserializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonDeserializer.None.class)) {}
    for (;;)
    {
      return localObject;
      localObject = this._secondary.findContentDeserializer(paramAnnotated);
    }
  }
  
  public Object findContentSerializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findContentSerializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonSerializer.None.class)) {}
    for (;;)
    {
      return localObject;
      localObject = this._secondary.findContentSerializer(paramAnnotated);
    }
  }
  
  public JsonCreator.Mode findCreatorBinding(Annotated paramAnnotated)
  {
    JsonCreator.Mode localMode = this._primary.findCreatorBinding(paramAnnotated);
    if (localMode != null) {}
    for (;;)
    {
      return localMode;
      localMode = this._secondary.findCreatorBinding(paramAnnotated);
    }
  }
  
  public Object findDeserializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    Object localObject = this._primary.findDeserializationContentConverter(paramAnnotatedMember);
    if (localObject == null) {
      localObject = this._secondary.findDeserializationContentConverter(paramAnnotatedMember);
    }
    return localObject;
  }
  
  public Class<?> findDeserializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass = this._primary.findDeserializationContentType(paramAnnotated, paramJavaType);
    if (localClass == null) {
      localClass = this._secondary.findDeserializationContentType(paramAnnotated, paramJavaType);
    }
    return localClass;
  }
  
  public Object findDeserializationConverter(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findDeserializationConverter(paramAnnotated);
    if (localObject == null) {
      localObject = this._secondary.findDeserializationConverter(paramAnnotated);
    }
    return localObject;
  }
  
  public Class<?> findDeserializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass = this._primary.findDeserializationKeyType(paramAnnotated, paramJavaType);
    if (localClass == null) {
      localClass = this._secondary.findDeserializationKeyType(paramAnnotated, paramJavaType);
    }
    return localClass;
  }
  
  public Class<?> findDeserializationType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass = this._primary.findDeserializationType(paramAnnotated, paramJavaType);
    if (localClass != null) {}
    for (;;)
    {
      return localClass;
      localClass = this._secondary.findDeserializationType(paramAnnotated, paramJavaType);
    }
  }
  
  public Object findDeserializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findDeserializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonDeserializer.None.class)) {}
    for (;;)
    {
      return localObject;
      localObject = this._secondary.findDeserializer(paramAnnotated);
    }
  }
  
  public String findEnumValue(Enum<?> paramEnum)
  {
    String str = this._primary.findEnumValue(paramEnum);
    if (str == null) {
      str = this._secondary.findEnumValue(paramEnum);
    }
    return str;
  }
  
  public Object findFilterId(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findFilterId(paramAnnotated);
    if (localObject == null) {
      localObject = this._secondary.findFilterId(paramAnnotated);
    }
    return localObject;
  }
  
  @Deprecated
  public Object findFilterId(AnnotatedClass paramAnnotatedClass)
  {
    Object localObject = this._primary.findFilterId(paramAnnotatedClass);
    if (localObject == null) {
      localObject = this._secondary.findFilterId(paramAnnotatedClass);
    }
    return localObject;
  }
  
  public JsonFormat.Value findFormat(Annotated paramAnnotated)
  {
    JsonFormat.Value localValue = this._primary.findFormat(paramAnnotated);
    if (localValue == null) {
      localValue = this._secondary.findFormat(paramAnnotated);
    }
    return localValue;
  }
  
  public Boolean findIgnoreUnknownProperties(AnnotatedClass paramAnnotatedClass)
  {
    Boolean localBoolean = this._primary.findIgnoreUnknownProperties(paramAnnotatedClass);
    if (localBoolean == null) {
      localBoolean = this._secondary.findIgnoreUnknownProperties(paramAnnotatedClass);
    }
    return localBoolean;
  }
  
  public String findImplicitPropertyName(AnnotatedMember paramAnnotatedMember)
  {
    String str = this._primary.findImplicitPropertyName(paramAnnotatedMember);
    if (str == null) {
      str = this._secondary.findImplicitPropertyName(paramAnnotatedMember);
    }
    return str;
  }
  
  public Object findInjectableValueId(AnnotatedMember paramAnnotatedMember)
  {
    Object localObject = this._primary.findInjectableValueId(paramAnnotatedMember);
    if (localObject == null) {
      localObject = this._secondary.findInjectableValueId(paramAnnotatedMember);
    }
    return localObject;
  }
  
  public Object findKeyDeserializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findKeyDeserializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, KeyDeserializer.None.class)) {}
    for (;;)
    {
      return localObject;
      localObject = this._secondary.findKeyDeserializer(paramAnnotated);
    }
  }
  
  public Object findKeySerializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findKeySerializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonSerializer.None.class)) {}
    for (;;)
    {
      return localObject;
      localObject = this._secondary.findKeySerializer(paramAnnotated);
    }
  }
  
  public PropertyName findNameForDeserialization(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findNameForDeserialization(paramAnnotated);
    if (localObject == null) {
      localObject = this._secondary.findNameForDeserialization(paramAnnotated);
    }
    for (;;)
    {
      return (PropertyName)localObject;
      if (localObject == PropertyName.USE_DEFAULT)
      {
        PropertyName localPropertyName = this._secondary.findNameForDeserialization(paramAnnotated);
        if (localPropertyName != null) {
          localObject = localPropertyName;
        }
      }
    }
  }
  
  public PropertyName findNameForSerialization(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findNameForSerialization(paramAnnotated);
    if (localObject == null) {
      localObject = this._secondary.findNameForSerialization(paramAnnotated);
    }
    for (;;)
    {
      return (PropertyName)localObject;
      if (localObject == PropertyName.USE_DEFAULT)
      {
        PropertyName localPropertyName = this._secondary.findNameForSerialization(paramAnnotated);
        if (localPropertyName != null) {
          localObject = localPropertyName;
        }
      }
    }
  }
  
  public Object findNamingStrategy(AnnotatedClass paramAnnotatedClass)
  {
    Object localObject = this._primary.findNamingStrategy(paramAnnotatedClass);
    if (localObject == null) {
      localObject = this._secondary.findNamingStrategy(paramAnnotatedClass);
    }
    return localObject;
  }
  
  public Object findNullSerializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findNullSerializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonSerializer.None.class)) {}
    for (;;)
    {
      return localObject;
      localObject = this._secondary.findNullSerializer(paramAnnotated);
    }
  }
  
  public ObjectIdInfo findObjectIdInfo(Annotated paramAnnotated)
  {
    ObjectIdInfo localObjectIdInfo = this._primary.findObjectIdInfo(paramAnnotated);
    if (localObjectIdInfo == null) {
      localObjectIdInfo = this._secondary.findObjectIdInfo(paramAnnotated);
    }
    return localObjectIdInfo;
  }
  
  public ObjectIdInfo findObjectReferenceInfo(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
  {
    ObjectIdInfo localObjectIdInfo = this._secondary.findObjectReferenceInfo(paramAnnotated, paramObjectIdInfo);
    return this._primary.findObjectReferenceInfo(paramAnnotated, localObjectIdInfo);
  }
  
  public Class<?> findPOJOBuilder(AnnotatedClass paramAnnotatedClass)
  {
    Class localClass = this._primary.findPOJOBuilder(paramAnnotatedClass);
    if (localClass == null) {
      localClass = this._secondary.findPOJOBuilder(paramAnnotatedClass);
    }
    return localClass;
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass paramAnnotatedClass)
  {
    JsonPOJOBuilder.Value localValue = this._primary.findPOJOBuilderConfig(paramAnnotatedClass);
    if (localValue == null) {
      localValue = this._secondary.findPOJOBuilderConfig(paramAnnotatedClass);
    }
    return localValue;
  }
  
  @Deprecated
  public String[] findPropertiesToIgnore(Annotated paramAnnotated)
  {
    String[] arrayOfString = this._primary.findPropertiesToIgnore(paramAnnotated);
    if (arrayOfString == null) {
      arrayOfString = this._secondary.findPropertiesToIgnore(paramAnnotated);
    }
    return arrayOfString;
  }
  
  public String[] findPropertiesToIgnore(Annotated paramAnnotated, boolean paramBoolean)
  {
    String[] arrayOfString = this._primary.findPropertiesToIgnore(paramAnnotated, paramBoolean);
    if (arrayOfString == null) {
      arrayOfString = this._secondary.findPropertiesToIgnore(paramAnnotated, paramBoolean);
    }
    return arrayOfString;
  }
  
  public JsonProperty.Access findPropertyAccess(Annotated paramAnnotated)
  {
    JsonProperty.Access localAccess1 = this._primary.findPropertyAccess(paramAnnotated);
    Object localObject;
    if ((localAccess1 != null) && (localAccess1 != JsonProperty.Access.AUTO)) {
      localObject = localAccess1;
    }
    for (;;)
    {
      return (JsonProperty.Access)localObject;
      JsonProperty.Access localAccess2 = this._secondary.findPropertyAccess(paramAnnotated);
      if (localAccess2 != null) {
        localObject = localAccess2;
      } else {
        localObject = JsonProperty.Access.AUTO;
      }
    }
  }
  
  public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    TypeResolverBuilder localTypeResolverBuilder = this._primary.findPropertyContentTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    if (localTypeResolverBuilder == null) {
      localTypeResolverBuilder = this._secondary.findPropertyContentTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    }
    return localTypeResolverBuilder;
  }
  
  public String findPropertyDefaultValue(Annotated paramAnnotated)
  {
    String str = this._primary.findPropertyDefaultValue(paramAnnotated);
    if ((str == null) || (str.isEmpty())) {
      str = this._secondary.findPropertyDefaultValue(paramAnnotated);
    }
    return str;
  }
  
  public String findPropertyDescription(Annotated paramAnnotated)
  {
    String str = this._primary.findPropertyDescription(paramAnnotated);
    if (str == null) {
      str = this._secondary.findPropertyDescription(paramAnnotated);
    }
    return str;
  }
  
  public JsonInclude.Value findPropertyInclusion(Annotated paramAnnotated)
  {
    JsonInclude.Value localValue1 = this._secondary.findPropertyInclusion(paramAnnotated);
    JsonInclude.Value localValue2 = this._secondary.findPropertyInclusion(paramAnnotated);
    if (localValue1 == null) {}
    for (;;)
    {
      return localValue2;
      localValue2 = localValue1.withOverrides(localValue2);
    }
  }
  
  public Integer findPropertyIndex(Annotated paramAnnotated)
  {
    Integer localInteger = this._primary.findPropertyIndex(paramAnnotated);
    if (localInteger == null) {
      localInteger = this._secondary.findPropertyIndex(paramAnnotated);
    }
    return localInteger;
  }
  
  public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    TypeResolverBuilder localTypeResolverBuilder = this._primary.findPropertyTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    if (localTypeResolverBuilder == null) {
      localTypeResolverBuilder = this._secondary.findPropertyTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    }
    return localTypeResolverBuilder;
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember paramAnnotatedMember)
  {
    AnnotationIntrospector.ReferenceProperty localReferenceProperty = this._primary.findReferenceType(paramAnnotatedMember);
    if (localReferenceProperty == null) {
      localReferenceProperty = this._secondary.findReferenceType(paramAnnotatedMember);
    }
    return localReferenceProperty;
  }
  
  public PropertyName findRootName(AnnotatedClass paramAnnotatedClass)
  {
    Object localObject = this._primary.findRootName(paramAnnotatedClass);
    if (localObject == null) {
      localObject = this._secondary.findRootName(paramAnnotatedClass);
    }
    for (;;)
    {
      return (PropertyName)localObject;
      if (!((PropertyName)localObject).hasSimpleName())
      {
        PropertyName localPropertyName = this._secondary.findRootName(paramAnnotatedClass);
        if (localPropertyName != null) {
          localObject = localPropertyName;
        }
      }
    }
  }
  
  public Object findSerializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    Object localObject = this._primary.findSerializationContentConverter(paramAnnotatedMember);
    if (localObject == null) {
      localObject = this._secondary.findSerializationContentConverter(paramAnnotatedMember);
    }
    return localObject;
  }
  
  public Class<?> findSerializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass = this._primary.findSerializationContentType(paramAnnotated, paramJavaType);
    if (localClass == null) {
      localClass = this._secondary.findSerializationContentType(paramAnnotated, paramJavaType);
    }
    return localClass;
  }
  
  public Object findSerializationConverter(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findSerializationConverter(paramAnnotated);
    if (localObject == null) {
      localObject = this._secondary.findSerializationConverter(paramAnnotated);
    }
    return localObject;
  }
  
  public JsonInclude.Include findSerializationInclusion(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    JsonInclude.Include localInclude = this._secondary.findSerializationInclusion(paramAnnotated, paramInclude);
    return this._primary.findSerializationInclusion(paramAnnotated, localInclude);
  }
  
  public JsonInclude.Include findSerializationInclusionForContent(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    JsonInclude.Include localInclude = this._secondary.findSerializationInclusion(paramAnnotated, paramInclude);
    return this._primary.findSerializationInclusion(paramAnnotated, localInclude);
  }
  
  public Class<?> findSerializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    Class localClass = this._primary.findSerializationKeyType(paramAnnotated, paramJavaType);
    if (localClass == null) {
      localClass = this._secondary.findSerializationKeyType(paramAnnotated, paramJavaType);
    }
    return localClass;
  }
  
  public String[] findSerializationPropertyOrder(AnnotatedClass paramAnnotatedClass)
  {
    String[] arrayOfString = this._primary.findSerializationPropertyOrder(paramAnnotatedClass);
    if (arrayOfString == null) {
      arrayOfString = this._secondary.findSerializationPropertyOrder(paramAnnotatedClass);
    }
    return arrayOfString;
  }
  
  public Boolean findSerializationSortAlphabetically(Annotated paramAnnotated)
  {
    Boolean localBoolean = this._primary.findSerializationSortAlphabetically(paramAnnotated);
    if (localBoolean == null) {
      localBoolean = this._secondary.findSerializationSortAlphabetically(paramAnnotated);
    }
    return localBoolean;
  }
  
  public Class<?> findSerializationType(Annotated paramAnnotated)
  {
    Class localClass = this._primary.findSerializationType(paramAnnotated);
    if (localClass == null) {
      localClass = this._secondary.findSerializationType(paramAnnotated);
    }
    return localClass;
  }
  
  public JsonSerialize.Typing findSerializationTyping(Annotated paramAnnotated)
  {
    JsonSerialize.Typing localTyping = this._primary.findSerializationTyping(paramAnnotated);
    if (localTyping == null) {
      localTyping = this._secondary.findSerializationTyping(paramAnnotated);
    }
    return localTyping;
  }
  
  public Object findSerializer(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findSerializer(paramAnnotated);
    if (_isExplicitClassOrOb(localObject, JsonSerializer.None.class)) {}
    for (;;)
    {
      return localObject;
      localObject = this._secondary.findSerializer(paramAnnotated);
    }
  }
  
  public List<NamedType> findSubtypes(Annotated paramAnnotated)
  {
    List localList1 = this._primary.findSubtypes(paramAnnotated);
    List localList2 = this._secondary.findSubtypes(paramAnnotated);
    Object localObject;
    if ((localList1 == null) || (localList1.isEmpty())) {
      localObject = localList2;
    }
    for (;;)
    {
      return (List<NamedType>)localObject;
      if ((localList2 == null) || (localList2.isEmpty()))
      {
        localObject = localList1;
      }
      else
      {
        localObject = new ArrayList(localList1.size() + localList2.size());
        ((ArrayList)localObject).addAll(localList1);
        ((ArrayList)localObject).addAll(localList2);
      }
    }
  }
  
  public String findTypeName(AnnotatedClass paramAnnotatedClass)
  {
    String str = this._primary.findTypeName(paramAnnotatedClass);
    if ((str == null) || (str.length() == 0)) {
      str = this._secondary.findTypeName(paramAnnotatedClass);
    }
    return str;
  }
  
  public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType)
  {
    TypeResolverBuilder localTypeResolverBuilder = this._primary.findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType);
    if (localTypeResolverBuilder == null) {
      localTypeResolverBuilder = this._secondary.findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType);
    }
    return localTypeResolverBuilder;
  }
  
  public NameTransformer findUnwrappingNameTransformer(AnnotatedMember paramAnnotatedMember)
  {
    NameTransformer localNameTransformer = this._primary.findUnwrappingNameTransformer(paramAnnotatedMember);
    if (localNameTransformer == null) {
      localNameTransformer = this._secondary.findUnwrappingNameTransformer(paramAnnotatedMember);
    }
    return localNameTransformer;
  }
  
  public Object findValueInstantiator(AnnotatedClass paramAnnotatedClass)
  {
    Object localObject = this._primary.findValueInstantiator(paramAnnotatedClass);
    if (localObject == null) {
      localObject = this._secondary.findValueInstantiator(paramAnnotatedClass);
    }
    return localObject;
  }
  
  public Class<?>[] findViews(Annotated paramAnnotated)
  {
    Class[] arrayOfClass = this._primary.findViews(paramAnnotated);
    if (arrayOfClass == null) {
      arrayOfClass = this._secondary.findViews(paramAnnotated);
    }
    return arrayOfClass;
  }
  
  public PropertyName findWrapperName(Annotated paramAnnotated)
  {
    Object localObject = this._primary.findWrapperName(paramAnnotated);
    if (localObject == null) {
      localObject = this._secondary.findWrapperName(paramAnnotated);
    }
    for (;;)
    {
      return (PropertyName)localObject;
      if (localObject == PropertyName.USE_DEFAULT)
      {
        PropertyName localPropertyName = this._secondary.findWrapperName(paramAnnotated);
        if (localPropertyName != null) {
          localObject = localPropertyName;
        }
      }
    }
  }
  
  public boolean hasAnyGetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    if ((this._primary.hasAnyGetterAnnotation(paramAnnotatedMethod)) || (this._secondary.hasAnyGetterAnnotation(paramAnnotatedMethod))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasAnySetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    if ((this._primary.hasAnySetterAnnotation(paramAnnotatedMethod)) || (this._secondary.hasAnySetterAnnotation(paramAnnotatedMethod))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasAsValueAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    if ((this._primary.hasAsValueAnnotation(paramAnnotatedMethod)) || (this._secondary.hasAsValueAnnotation(paramAnnotatedMethod))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasCreatorAnnotation(Annotated paramAnnotated)
  {
    if ((this._primary.hasCreatorAnnotation(paramAnnotated)) || (this._secondary.hasCreatorAnnotation(paramAnnotated))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember paramAnnotatedMember)
  {
    if ((this._primary.hasIgnoreMarker(paramAnnotatedMember)) || (this._secondary.hasIgnoreMarker(paramAnnotatedMember))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Boolean hasRequiredMarker(AnnotatedMember paramAnnotatedMember)
  {
    Boolean localBoolean = this._primary.hasRequiredMarker(paramAnnotatedMember);
    if (localBoolean == null) {
      localBoolean = this._secondary.hasRequiredMarker(paramAnnotatedMember);
    }
    return localBoolean;
  }
  
  public boolean isAnnotationBundle(Annotation paramAnnotation)
  {
    if ((this._primary.isAnnotationBundle(paramAnnotation)) || (this._secondary.isAnnotationBundle(paramAnnotation))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Boolean isIgnorableType(AnnotatedClass paramAnnotatedClass)
  {
    Boolean localBoolean = this._primary.isIgnorableType(paramAnnotatedClass);
    if (localBoolean == null) {
      localBoolean = this._secondary.isIgnorableType(paramAnnotatedClass);
    }
    return localBoolean;
  }
  
  public Boolean isTypeId(AnnotatedMember paramAnnotatedMember)
  {
    Boolean localBoolean = this._primary.isTypeId(paramAnnotatedMember);
    if (localBoolean == null) {
      localBoolean = this._secondary.isTypeId(paramAnnotatedMember);
    }
    return localBoolean;
  }
  
  public Version version()
  {
    return this._primary.version();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotationIntrospectorPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */