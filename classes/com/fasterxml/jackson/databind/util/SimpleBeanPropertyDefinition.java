package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class SimpleBeanPropertyDefinition
  extends BeanPropertyDefinition
{
  protected final PropertyName _fullName;
  protected final JsonInclude.Include _inclusion;
  protected final AnnotationIntrospector _introspector;
  protected final AnnotatedMember _member;
  protected final PropertyMetadata _metadata;
  @Deprecated
  protected final String _name;
  
  @Deprecated
  public SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember)
  {
    this(paramAnnotatedMember, paramAnnotatedMember.getName(), null);
  }
  
  protected SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember, PropertyName paramPropertyName, AnnotationIntrospector paramAnnotationIntrospector, PropertyMetadata paramPropertyMetadata, JsonInclude.Include paramInclude)
  {
    this._introspector = paramAnnotationIntrospector;
    this._member = paramAnnotatedMember;
    this._fullName = paramPropertyName;
    this._name = paramPropertyName.getSimpleName();
    if (paramPropertyMetadata == null) {
      paramPropertyMetadata = PropertyMetadata.STD_OPTIONAL;
    }
    this._metadata = paramPropertyMetadata;
    this._inclusion = paramInclude;
  }
  
  @Deprecated
  public SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember, String paramString)
  {
    this(paramAnnotatedMember, new PropertyName(paramString), null, null, null);
  }
  
  @Deprecated
  protected SimpleBeanPropertyDefinition(AnnotatedMember paramAnnotatedMember, String paramString, AnnotationIntrospector paramAnnotationIntrospector)
  {
    this(paramAnnotatedMember, new PropertyName(paramString), paramAnnotationIntrospector, null, null);
  }
  
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember)
  {
    PropertyName localPropertyName = PropertyName.construct(paramAnnotatedMember.getName());
    if (paramMapperConfig == null) {}
    for (AnnotationIntrospector localAnnotationIntrospector = null;; localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector()) {
      return new SimpleBeanPropertyDefinition(paramAnnotatedMember, localPropertyName, localAnnotationIntrospector, null, null);
    }
  }
  
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, PropertyName paramPropertyName)
  {
    return construct(paramMapperConfig, paramAnnotatedMember, paramPropertyName, null, null);
  }
  
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, PropertyName paramPropertyName, PropertyMetadata paramPropertyMetadata, JsonInclude.Include paramInclude)
  {
    if (paramMapperConfig == null) {}
    for (AnnotationIntrospector localAnnotationIntrospector = null;; localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector()) {
      return new SimpleBeanPropertyDefinition(paramAnnotatedMember, paramPropertyName, localAnnotationIntrospector, paramPropertyMetadata, paramInclude);
    }
  }
  
  @Deprecated
  public static SimpleBeanPropertyDefinition construct(MapperConfig<?> paramMapperConfig, AnnotatedMember paramAnnotatedMember, String paramString)
  {
    PropertyName localPropertyName = PropertyName.construct(paramString);
    if (paramMapperConfig == null) {}
    for (AnnotationIntrospector localAnnotationIntrospector = null;; localAnnotationIntrospector = paramMapperConfig.getAnnotationIntrospector()) {
      return new SimpleBeanPropertyDefinition(paramAnnotatedMember, localPropertyName, localAnnotationIntrospector, null, null);
    }
  }
  
  public JsonInclude.Include findInclusion()
  {
    return this._inclusion;
  }
  
  public AnnotatedMember getAccessor()
  {
    Object localObject = getGetter();
    if (localObject == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedParameter getConstructorParameter()
  {
    if ((this._member instanceof AnnotatedParameter)) {}
    for (AnnotatedParameter localAnnotatedParameter = (AnnotatedParameter)this._member;; localAnnotatedParameter = null) {
      return localAnnotatedParameter;
    }
  }
  
  public Iterator<AnnotatedParameter> getConstructorParameters()
  {
    AnnotatedParameter localAnnotatedParameter = getConstructorParameter();
    if (localAnnotatedParameter == null) {}
    for (Iterator localIterator = EmptyIterator.instance();; localIterator = Collections.singleton(localAnnotatedParameter).iterator()) {
      return localIterator;
    }
  }
  
  public AnnotatedField getField()
  {
    if ((this._member instanceof AnnotatedField)) {}
    for (AnnotatedField localAnnotatedField = (AnnotatedField)this._member;; localAnnotatedField = null) {
      return localAnnotatedField;
    }
  }
  
  public PropertyName getFullName()
  {
    return this._fullName;
  }
  
  public AnnotatedMethod getGetter()
  {
    if (((this._member instanceof AnnotatedMethod)) && (((AnnotatedMethod)this._member).getParameterCount() == 0)) {}
    for (AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)this._member;; localAnnotatedMethod = null) {
      return localAnnotatedMethod;
    }
  }
  
  public String getInternalName()
  {
    return getName();
  }
  
  public PropertyMetadata getMetadata()
  {
    return this._metadata;
  }
  
  public AnnotatedMember getMutator()
  {
    Object localObject = getConstructorParameter();
    if (localObject == null)
    {
      localObject = getSetter();
      if (localObject == null) {
        localObject = getField();
      }
    }
    return (AnnotatedMember)localObject;
  }
  
  public String getName()
  {
    return this._fullName.getSimpleName();
  }
  
  public AnnotatedMember getNonConstructorMutator()
  {
    Object localObject = getSetter();
    if (localObject == null) {
      localObject = getField();
    }
    return (AnnotatedMember)localObject;
  }
  
  public AnnotatedMember getPrimaryMember()
  {
    return this._member;
  }
  
  public AnnotatedMethod getSetter()
  {
    if (((this._member instanceof AnnotatedMethod)) && (((AnnotatedMethod)this._member).getParameterCount() == 1)) {}
    for (AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)this._member;; localAnnotatedMethod = null) {
      return localAnnotatedMethod;
    }
  }
  
  public PropertyName getWrapperName()
  {
    if ((this._introspector == null) && (this._member != null)) {}
    for (PropertyName localPropertyName = null;; localPropertyName = this._introspector.findWrapperName(this._member)) {
      return localPropertyName;
    }
  }
  
  public boolean hasConstructorParameter()
  {
    return this._member instanceof AnnotatedParameter;
  }
  
  public boolean hasField()
  {
    return this._member instanceof AnnotatedField;
  }
  
  public boolean hasGetter()
  {
    if (getGetter() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasName(PropertyName paramPropertyName)
  {
    return this._fullName.equals(paramPropertyName);
  }
  
  public boolean hasSetter()
  {
    if (getSetter() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isExplicitlyIncluded()
  {
    return false;
  }
  
  public boolean isExplicitlyNamed()
  {
    return false;
  }
  
  public BeanPropertyDefinition withInclusion(JsonInclude.Include paramInclude)
  {
    if (this._inclusion == paramInclude) {}
    for (;;)
    {
      return this;
      this = new SimpleBeanPropertyDefinition(this._member, this._fullName, this._introspector, this._metadata, paramInclude);
    }
  }
  
  public BeanPropertyDefinition withMetadata(PropertyMetadata paramPropertyMetadata)
  {
    if (paramPropertyMetadata.equals(this._metadata)) {}
    for (;;)
    {
      return this;
      this = new SimpleBeanPropertyDefinition(this._member, this._fullName, this._introspector, paramPropertyMetadata, this._inclusion);
    }
  }
  
  public BeanPropertyDefinition withName(PropertyName paramPropertyName)
  {
    if (this._fullName.equals(paramPropertyName)) {}
    for (;;)
    {
      return this;
      this = new SimpleBeanPropertyDefinition(this._member, paramPropertyName, this._introspector, this._metadata, this._inclusion);
    }
  }
  
  @Deprecated
  public BeanPropertyDefinition withName(String paramString)
  {
    return withSimpleName(paramString);
  }
  
  public BeanPropertyDefinition withSimpleName(String paramString)
  {
    if ((this._fullName.hasSimpleName(paramString)) && (!this._fullName.hasNamespace())) {}
    for (;;)
    {
      return this;
      this = new SimpleBeanPropertyDefinition(this._member, new PropertyName(paramString), this._introspector, this._metadata, this._inclusion);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/SimpleBeanPropertyDefinition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */