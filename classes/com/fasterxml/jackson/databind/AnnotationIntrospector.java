package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AnnotationIntrospector
  implements Versioned, Serializable
{
  public static AnnotationIntrospector nopInstance()
  {
    return NopAnnotationIntrospector.instance;
  }
  
  public static AnnotationIntrospector pair(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2)
  {
    return new AnnotationIntrospectorPair(paramAnnotationIntrospector1, paramAnnotationIntrospector2);
  }
  
  protected <A extends Annotation> A _findAnnotation(Annotated paramAnnotated, Class<A> paramClass)
  {
    return paramAnnotated.getAnnotation(paramClass);
  }
  
  protected boolean _hasAnnotation(Annotated paramAnnotated, Class<? extends Annotation> paramClass)
  {
    return paramAnnotated.hasAnnotation(paramClass);
  }
  
  public Collection<AnnotationIntrospector> allIntrospectors()
  {
    return Collections.singletonList(this);
  }
  
  public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> paramCollection)
  {
    paramCollection.add(this);
    return paramCollection;
  }
  
  public void findAndAddVirtualProperties(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, List<BeanPropertyWriter> paramList) {}
  
  public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass paramAnnotatedClass, VisibilityChecker<?> paramVisibilityChecker)
  {
    return paramVisibilityChecker;
  }
  
  public Object findContentDeserializer(Annotated paramAnnotated)
  {
    return null;
  }
  
  public Object findContentSerializer(Annotated paramAnnotated)
  {
    return null;
  }
  
  public JsonCreator.Mode findCreatorBinding(Annotated paramAnnotated)
  {
    return null;
  }
  
  public Object findDeserializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public Class<?> findDeserializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    return null;
  }
  
  public Object findDeserializationConverter(Annotated paramAnnotated)
  {
    return null;
  }
  
  public Class<?> findDeserializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    return null;
  }
  
  public Class<?> findDeserializationType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    return null;
  }
  
  public Object findDeserializer(Annotated paramAnnotated)
  {
    return null;
  }
  
  public String findEnumValue(Enum<?> paramEnum)
  {
    return paramEnum.name();
  }
  
  public Object findFilterId(Annotated paramAnnotated)
  {
    return null;
  }
  
  @Deprecated
  public Object findFilterId(AnnotatedClass paramAnnotatedClass)
  {
    return findFilterId(paramAnnotatedClass);
  }
  
  public JsonFormat.Value findFormat(Annotated paramAnnotated)
  {
    return null;
  }
  
  public Boolean findIgnoreUnknownProperties(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  public String findImplicitPropertyName(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public Object findInjectableValueId(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public Object findKeyDeserializer(Annotated paramAnnotated)
  {
    return null;
  }
  
  public Object findKeySerializer(Annotated paramAnnotated)
  {
    return null;
  }
  
  public PropertyName findNameForDeserialization(Annotated paramAnnotated)
  {
    return null;
  }
  
  public PropertyName findNameForSerialization(Annotated paramAnnotated)
  {
    return null;
  }
  
  public Object findNamingStrategy(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  public Object findNullSerializer(Annotated paramAnnotated)
  {
    return null;
  }
  
  public ObjectIdInfo findObjectIdInfo(Annotated paramAnnotated)
  {
    return null;
  }
  
  public ObjectIdInfo findObjectReferenceInfo(Annotated paramAnnotated, ObjectIdInfo paramObjectIdInfo)
  {
    return paramObjectIdInfo;
  }
  
  public Class<?> findPOJOBuilder(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  @Deprecated
  public String[] findPropertiesToIgnore(Annotated paramAnnotated)
  {
    return null;
  }
  
  public String[] findPropertiesToIgnore(Annotated paramAnnotated, boolean paramBoolean)
  {
    return findPropertiesToIgnore(paramAnnotated);
  }
  
  public JsonProperty.Access findPropertyAccess(Annotated paramAnnotated)
  {
    return null;
  }
  
  public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    return null;
  }
  
  public String findPropertyDefaultValue(Annotated paramAnnotated)
  {
    return null;
  }
  
  public String findPropertyDescription(Annotated paramAnnotated)
  {
    return null;
  }
  
  public JsonInclude.Value findPropertyInclusion(Annotated paramAnnotated)
  {
    JsonInclude.Include localInclude = JsonInclude.Include.USE_DEFAULTS;
    return JsonInclude.Value.construct(findSerializationInclusion(paramAnnotated, localInclude), findSerializationInclusionForContent(paramAnnotated, localInclude));
  }
  
  public Integer findPropertyIndex(Annotated paramAnnotated)
  {
    return null;
  }
  
  public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType)
  {
    return null;
  }
  
  public ReferenceProperty findReferenceType(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public PropertyName findRootName(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  public Object findSerializationContentConverter(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public Class<?> findSerializationContentType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    return null;
  }
  
  public Object findSerializationConverter(Annotated paramAnnotated)
  {
    return null;
  }
  
  public JsonInclude.Include findSerializationInclusion(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    return paramInclude;
  }
  
  public JsonInclude.Include findSerializationInclusionForContent(Annotated paramAnnotated, JsonInclude.Include paramInclude)
  {
    return paramInclude;
  }
  
  public Class<?> findSerializationKeyType(Annotated paramAnnotated, JavaType paramJavaType)
  {
    return null;
  }
  
  public String[] findSerializationPropertyOrder(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  public Boolean findSerializationSortAlphabetically(Annotated paramAnnotated)
  {
    return null;
  }
  
  public Class<?> findSerializationType(Annotated paramAnnotated)
  {
    return null;
  }
  
  public JsonSerialize.Typing findSerializationTyping(Annotated paramAnnotated)
  {
    return null;
  }
  
  public Object findSerializer(Annotated paramAnnotated)
  {
    return null;
  }
  
  public List<NamedType> findSubtypes(Annotated paramAnnotated)
  {
    return null;
  }
  
  public String findTypeName(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType)
  {
    return null;
  }
  
  public NameTransformer findUnwrappingNameTransformer(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public Object findValueInstantiator(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  public Class<?>[] findViews(Annotated paramAnnotated)
  {
    return null;
  }
  
  public PropertyName findWrapperName(Annotated paramAnnotated)
  {
    return null;
  }
  
  public boolean hasAnyGetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return false;
  }
  
  public boolean hasAnySetterAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return false;
  }
  
  public boolean hasAsValueAnnotation(AnnotatedMethod paramAnnotatedMethod)
  {
    return false;
  }
  
  public boolean hasCreatorAnnotation(Annotated paramAnnotated)
  {
    return false;
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember paramAnnotatedMember)
  {
    return false;
  }
  
  public Boolean hasRequiredMarker(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public boolean isAnnotationBundle(Annotation paramAnnotation)
  {
    return false;
  }
  
  public Boolean isIgnorableType(AnnotatedClass paramAnnotatedClass)
  {
    return null;
  }
  
  public Boolean isTypeId(AnnotatedMember paramAnnotatedMember)
  {
    return null;
  }
  
  public abstract Version version();
  
  public static class ReferenceProperty
  {
    private final String _name;
    private final Type _type;
    
    public ReferenceProperty(Type paramType, String paramString)
    {
      this._type = paramType;
      this._name = paramString;
    }
    
    public static ReferenceProperty back(String paramString)
    {
      return new ReferenceProperty(Type.BACK_REFERENCE, paramString);
    }
    
    public static ReferenceProperty managed(String paramString)
    {
      return new ReferenceProperty(Type.MANAGED_REFERENCE, paramString);
    }
    
    public String getName()
    {
      return this._name;
    }
    
    public Type getType()
    {
      return this._type;
    }
    
    public boolean isBackReference()
    {
      if (this._type == Type.BACK_REFERENCE) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean isManagedReference()
    {
      if (this._type == Type.MANAGED_REFERENCE) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public static enum Type
    {
      static
      {
        BACK_REFERENCE = new Type("BACK_REFERENCE", 1);
        Type[] arrayOfType = new Type[2];
        arrayOfType[0] = MANAGED_REFERENCE;
        arrayOfType[1] = BACK_REFERENCE;
        $VALUES = arrayOfType;
      }
      
      private Type() {}
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/AnnotationIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */