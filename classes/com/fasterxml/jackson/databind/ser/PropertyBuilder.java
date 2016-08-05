package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.NameTransformer;

public class PropertyBuilder
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected final BeanDescription _beanDesc;
  protected final SerializationConfig _config;
  protected Object _defaultBean;
  protected final JsonInclude.Include _defaultInclusion;
  
  public PropertyBuilder(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription)
  {
    this._config = paramSerializationConfig;
    this._beanDesc = paramBeanDescription;
    this._defaultInclusion = paramBeanDescription.findSerializationInclusion(paramSerializationConfig.getSerializationInclusion());
    this._annotationIntrospector = this._config.getAnnotationIntrospector();
  }
  
  protected Object _throwWrapped(Exception paramException, String paramString, Object paramObject)
  {
    for (Object localObject = paramException; ((Throwable)localObject).getCause() != null; localObject = ((Throwable)localObject).getCause()) {}
    if ((localObject instanceof Error)) {
      throw ((Error)localObject);
    }
    if ((localObject instanceof RuntimeException)) {
      throw ((RuntimeException)localObject);
    }
    throw new IllegalArgumentException("Failed to get property '" + paramString + "' of default " + paramObject.getClass().getName() + " instance");
  }
  
  protected BeanPropertyWriter buildWriter(SerializerProvider paramSerializerProvider, BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType, JsonSerializer<?> paramJsonSerializer, TypeSerializer paramTypeSerializer1, TypeSerializer paramTypeSerializer2, AnnotatedMember paramAnnotatedMember, boolean paramBoolean)
    throws JsonMappingException
  {
    JavaType localJavaType = findSerializationType(paramAnnotatedMember, paramBoolean, paramJavaType);
    if (paramTypeSerializer2 != null)
    {
      if (localJavaType == null) {
        localJavaType = paramJavaType;
      }
      if (localJavaType.getContentType() == null) {
        throw new IllegalStateException("Problem trying to create BeanPropertyWriter for property '" + paramBeanPropertyDefinition.getName() + "' (of type " + this._beanDesc.getType() + "); serialization type " + localJavaType + " has no content");
      }
      localJavaType = localJavaType.withContentTypeHandler(paramTypeSerializer2);
      localJavaType.getContentType();
    }
    Object localObject1 = null;
    boolean bool = false;
    JsonInclude.Include localInclude = paramBeanPropertyDefinition.findInclusion();
    if ((localInclude == null) || (localInclude == JsonInclude.Include.USE_DEFAULTS))
    {
      localInclude = this._defaultInclusion;
      if (localInclude == null) {
        localInclude = JsonInclude.Include.ALWAYS;
      }
    }
    switch (localInclude)
    {
    }
    for (;;)
    {
      if ((paramJavaType.isContainerType()) && (!this._config.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS))) {
        localObject1 = BeanPropertyWriter.MARKER_FOR_EMPTY;
      }
      for (;;)
      {
        BeanPropertyWriter localBeanPropertyWriter = new BeanPropertyWriter(paramBeanPropertyDefinition, paramAnnotatedMember, this._beanDesc.getClassAnnotations(), paramJavaType, paramJsonSerializer, paramTypeSerializer1, localJavaType, bool, localObject1);
        Object localObject2 = this._annotationIntrospector.findNullSerializer(paramAnnotatedMember);
        if (localObject2 != null) {
          localBeanPropertyWriter.assignNullSerializer(paramSerializerProvider.serializerInstance(paramAnnotatedMember, localObject2));
        }
        NameTransformer localNameTransformer = this._annotationIntrospector.findUnwrappingNameTransformer(paramAnnotatedMember);
        if (localNameTransformer != null) {
          localBeanPropertyWriter = localBeanPropertyWriter.unwrappingWriter(localNameTransformer);
        }
        return localBeanPropertyWriter;
        localObject1 = getDefaultValue(paramBeanPropertyDefinition.getName(), paramAnnotatedMember);
        if (localObject1 == null)
        {
          bool = true;
        }
        else if (localObject1.getClass().isArray())
        {
          localObject1 = ArrayBuilders.getArrayComparator(localObject1);
          continue;
          bool = true;
          if (paramJavaType.isReferenceType())
          {
            localObject1 = BeanPropertyWriter.MARKER_FOR_EMPTY;
            continue;
            bool = true;
            localObject1 = BeanPropertyWriter.MARKER_FOR_EMPTY;
          }
        }
      }
      bool = true;
    }
  }
  
  protected JavaType findSerializationType(Annotated paramAnnotated, boolean paramBoolean, JavaType paramJavaType)
  {
    Class localClass1 = this._annotationIntrospector.findSerializationType(paramAnnotated);
    Class localClass2;
    if (localClass1 != null)
    {
      localClass2 = paramJavaType.getRawClass();
      if (localClass1.isAssignableFrom(localClass2))
      {
        paramJavaType = paramJavaType.widenBy(localClass1);
        paramBoolean = true;
      }
    }
    else
    {
      JavaType localJavaType = BasicSerializerFactory.modifySecondaryTypesByAnnotation(this._config, paramAnnotated, paramJavaType);
      if (localJavaType != paramJavaType)
      {
        paramBoolean = true;
        paramJavaType = localJavaType;
      }
      JsonSerialize.Typing localTyping = this._annotationIntrospector.findSerializationTyping(paramAnnotated);
      if ((localTyping != null) && (localTyping != JsonSerialize.Typing.DEFAULT_TYPING))
      {
        if (localTyping != JsonSerialize.Typing.STATIC) {
          break label181;
        }
        paramBoolean = true;
      }
      label95:
      if (!paramBoolean) {
        break label186;
      }
    }
    for (;;)
    {
      return paramJavaType;
      if (!localClass2.isAssignableFrom(localClass1)) {
        throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + paramAnnotated.getName() + "': class " + localClass1.getName() + " not a super-type of (declared) class " + localClass2.getName());
      }
      paramJavaType = this._config.constructSpecializedType(paramJavaType, localClass1);
      break;
      label181:
      paramBoolean = false;
      break label95;
      label186:
      paramJavaType = null;
    }
  }
  
  public Annotations getClassAnnotations()
  {
    return this._beanDesc.getClassAnnotations();
  }
  
  protected Object getDefaultBean()
  {
    if (this._defaultBean == null)
    {
      this._defaultBean = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
      if (this._defaultBean == null)
      {
        Class localClass = this._beanDesc.getClassInfo().getAnnotated();
        throw new IllegalArgumentException("Class " + localClass.getName() + " has no default constructor; can not instantiate default bean value to support 'properties=JsonSerialize.Inclusion.NON_DEFAULT' annotation");
      }
    }
    return this._defaultBean;
  }
  
  protected Object getDefaultValue(String paramString, AnnotatedMember paramAnnotatedMember)
  {
    localObject1 = getDefaultBean();
    try
    {
      Object localObject3 = paramAnnotatedMember.getValue(localObject1);
      localObject2 = localObject3;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2 = _throwWrapped(localException, paramString, localObject1);
      }
    }
    return localObject2;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/PropertyBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */