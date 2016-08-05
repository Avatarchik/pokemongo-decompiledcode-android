package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.impl.FailingDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ViewMatcher;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public abstract class SettableBeanProperty
  implements BeanProperty, Serializable
{
  protected static final JsonDeserializer<Object> MISSING_VALUE_DESERIALIZER = new FailingDeserializer("No _valueDeserializer assigned");
  protected final transient Annotations _contextAnnotations;
  protected String _managedReferenceName;
  protected final PropertyMetadata _metadata;
  protected ObjectIdInfo _objectIdInfo;
  protected final PropertyName _propName;
  protected int _propertyIndex = -1;
  protected final JavaType _type;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  protected ViewMatcher _viewMatcher;
  protected final PropertyName _wrapperName;
  
  protected SettableBeanProperty(PropertyName paramPropertyName, JavaType paramJavaType, PropertyMetadata paramPropertyMetadata, JsonDeserializer<Object> paramJsonDeserializer)
  {
    if (paramPropertyName == null) {}
    for (this._propName = PropertyName.NO_NAME;; this._propName = paramPropertyName.internSimpleName())
    {
      this._type = paramJavaType;
      this._wrapperName = null;
      this._metadata = paramPropertyMetadata;
      this._contextAnnotations = null;
      this._viewMatcher = null;
      this._valueTypeDeserializer = null;
      this._valueDeserializer = paramJsonDeserializer;
      return;
    }
  }
  
  protected SettableBeanProperty(PropertyName paramPropertyName1, JavaType paramJavaType, PropertyName paramPropertyName2, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, PropertyMetadata paramPropertyMetadata)
  {
    if (paramPropertyName1 == null) {}
    for (this._propName = PropertyName.NO_NAME;; this._propName = paramPropertyName1.internSimpleName())
    {
      this._type = paramJavaType;
      this._wrapperName = paramPropertyName2;
      this._metadata = paramPropertyMetadata;
      this._contextAnnotations = paramAnnotations;
      this._viewMatcher = null;
      if (paramTypeDeserializer != null) {
        paramTypeDeserializer = paramTypeDeserializer.forProperty(this);
      }
      this._valueTypeDeserializer = paramTypeDeserializer;
      this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
      return;
    }
  }
  
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    this._propName = paramSettableBeanProperty._propName;
    this._type = paramSettableBeanProperty._type;
    this._wrapperName = paramSettableBeanProperty._wrapperName;
    this._metadata = paramSettableBeanProperty._metadata;
    this._contextAnnotations = paramSettableBeanProperty._contextAnnotations;
    this._valueDeserializer = paramSettableBeanProperty._valueDeserializer;
    this._valueTypeDeserializer = paramSettableBeanProperty._valueTypeDeserializer;
    this._managedReferenceName = paramSettableBeanProperty._managedReferenceName;
    this._propertyIndex = paramSettableBeanProperty._propertyIndex;
    this._viewMatcher = paramSettableBeanProperty._viewMatcher;
  }
  
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty, JsonDeserializer<?> paramJsonDeserializer)
  {
    this._propName = paramSettableBeanProperty._propName;
    this._type = paramSettableBeanProperty._type;
    this._wrapperName = paramSettableBeanProperty._wrapperName;
    this._metadata = paramSettableBeanProperty._metadata;
    this._contextAnnotations = paramSettableBeanProperty._contextAnnotations;
    this._valueTypeDeserializer = paramSettableBeanProperty._valueTypeDeserializer;
    this._managedReferenceName = paramSettableBeanProperty._managedReferenceName;
    this._propertyIndex = paramSettableBeanProperty._propertyIndex;
    if (paramJsonDeserializer == null) {}
    for (this._valueDeserializer = MISSING_VALUE_DESERIALIZER;; this._valueDeserializer = paramJsonDeserializer)
    {
      this._viewMatcher = paramSettableBeanProperty._viewMatcher;
      return;
    }
  }
  
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty, PropertyName paramPropertyName)
  {
    this._propName = paramPropertyName;
    this._type = paramSettableBeanProperty._type;
    this._wrapperName = paramSettableBeanProperty._wrapperName;
    this._metadata = paramSettableBeanProperty._metadata;
    this._contextAnnotations = paramSettableBeanProperty._contextAnnotations;
    this._valueDeserializer = paramSettableBeanProperty._valueDeserializer;
    this._valueTypeDeserializer = paramSettableBeanProperty._valueTypeDeserializer;
    this._managedReferenceName = paramSettableBeanProperty._managedReferenceName;
    this._propertyIndex = paramSettableBeanProperty._propertyIndex;
    this._viewMatcher = paramSettableBeanProperty._viewMatcher;
  }
  
  @Deprecated
  protected SettableBeanProperty(SettableBeanProperty paramSettableBeanProperty, String paramString)
  {
    this(paramSettableBeanProperty, new PropertyName(paramString));
  }
  
  protected SettableBeanProperty(BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations)
  {
    this(paramBeanPropertyDefinition.getFullName(), paramJavaType, paramBeanPropertyDefinition.getWrapperName(), paramTypeDeserializer, paramAnnotations, paramBeanPropertyDefinition.getMetadata());
  }
  
  @Deprecated
  protected SettableBeanProperty(String paramString, JavaType paramJavaType, PropertyName paramPropertyName, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, boolean paramBoolean)
  {
    this(new PropertyName(paramString), paramJavaType, paramPropertyName, paramTypeDeserializer, paramAnnotations, PropertyMetadata.construct(paramBoolean, null, null, null));
  }
  
  protected IOException _throwAsIOE(Exception paramException)
    throws IOException
  {
    if ((paramException instanceof IOException)) {
      throw ((IOException)paramException);
    }
    if ((paramException instanceof RuntimeException)) {
      throw ((RuntimeException)paramException);
    }
    for (Object localObject = paramException; ((Throwable)localObject).getCause() != null; localObject = ((Throwable)localObject).getCause()) {}
    throw new JsonMappingException(((Throwable)localObject).getMessage(), null, (Throwable)localObject);
  }
  
  protected void _throwAsIOE(Exception paramException, Object paramObject)
    throws IOException
  {
    if ((paramException instanceof IllegalArgumentException))
    {
      String str1;
      StringBuilder localStringBuilder;
      if (paramObject == null)
      {
        str1 = "[NULL]";
        localStringBuilder = new StringBuilder("Problem deserializing property '").append(getName());
        localStringBuilder.append("' (expected type: ").append(getType());
        localStringBuilder.append("; actual type: ").append(str1).append(")");
        String str2 = paramException.getMessage();
        if (str2 == null) {
          break label117;
        }
        localStringBuilder.append(", problem: ").append(str2);
      }
      for (;;)
      {
        throw new JsonMappingException(localStringBuilder.toString(), null, paramException);
        str1 = paramObject.getClass().getName();
        break;
        label117:
        localStringBuilder.append(" (no error message provided)");
      }
    }
    _throwAsIOE(paramException);
  }
  
  public void assignIndex(int paramInt)
  {
    if (this._propertyIndex != -1) {
      throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + paramInt);
    }
    this._propertyIndex = paramInt;
  }
  
  public void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor)
    throws JsonMappingException
  {
    if (isRequired()) {
      paramJsonObjectFormatVisitor.property(this);
    }
    for (;;)
    {
      return;
      paramJsonObjectFormatVisitor.optionalProperty(this);
    }
  }
  
  public final Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      localObject = this._valueDeserializer.getNullValue(paramDeserializationContext);
    }
    for (;;)
    {
      return localObject;
      if (this._valueTypeDeserializer != null) {
        localObject = this._valueDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._valueTypeDeserializer);
      } else {
        localObject = this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
    }
  }
  
  public abstract void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException;
  
  public abstract Object deserializeSetAndReturn(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException;
  
  public JsonFormat.Value findFormatOverrides(AnnotationIntrospector paramAnnotationIntrospector)
  {
    AnnotatedMember localAnnotatedMember;
    if (paramAnnotationIntrospector != null)
    {
      localAnnotatedMember = getMember();
      if (localAnnotatedMember == null) {}
    }
    for (JsonFormat.Value localValue = paramAnnotationIntrospector.findFormat(localAnnotatedMember);; localValue = null) {
      return localValue;
    }
  }
  
  public abstract <A extends Annotation> A getAnnotation(Class<A> paramClass);
  
  public <A extends Annotation> A getContextAnnotation(Class<A> paramClass)
  {
    return this._contextAnnotations.get(paramClass);
  }
  
  public int getCreatorIndex()
  {
    return -1;
  }
  
  protected final Class<?> getDeclaringClass()
  {
    return getMember().getDeclaringClass();
  }
  
  public PropertyName getFullName()
  {
    return this._propName;
  }
  
  public Object getInjectableValueId()
  {
    return null;
  }
  
  public String getManagedReferenceName()
  {
    return this._managedReferenceName;
  }
  
  public abstract AnnotatedMember getMember();
  
  public PropertyMetadata getMetadata()
  {
    return this._metadata;
  }
  
  public final String getName()
  {
    return this._propName.getSimpleName();
  }
  
  public ObjectIdInfo getObjectIdInfo()
  {
    return this._objectIdInfo;
  }
  
  public int getPropertyIndex()
  {
    return this._propertyIndex;
  }
  
  public JavaType getType()
  {
    return this._type;
  }
  
  public JsonDeserializer<Object> getValueDeserializer()
  {
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    if (localJsonDeserializer == MISSING_VALUE_DESERIALIZER) {
      localJsonDeserializer = null;
    }
    return localJsonDeserializer;
  }
  
  public TypeDeserializer getValueTypeDeserializer()
  {
    return this._valueTypeDeserializer;
  }
  
  public PropertyName getWrapperName()
  {
    return this._wrapperName;
  }
  
  public boolean hasValueDeserializer()
  {
    if ((this._valueDeserializer != null) && (this._valueDeserializer != MISSING_VALUE_DESERIALIZER)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasValueTypeDeserializer()
  {
    if (this._valueTypeDeserializer != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasViews()
  {
    if (this._viewMatcher != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isRequired()
  {
    return this._metadata.isRequired();
  }
  
  public abstract void set(Object paramObject1, Object paramObject2)
    throws IOException;
  
  public abstract Object setAndReturn(Object paramObject1, Object paramObject2)
    throws IOException;
  
  public void setManagedReferenceName(String paramString)
  {
    this._managedReferenceName = paramString;
  }
  
  public void setObjectIdInfo(ObjectIdInfo paramObjectIdInfo)
  {
    this._objectIdInfo = paramObjectIdInfo;
  }
  
  public void setViews(Class<?>[] paramArrayOfClass)
  {
    if (paramArrayOfClass == null) {}
    for (this._viewMatcher = null;; this._viewMatcher = ViewMatcher.construct(paramArrayOfClass)) {
      return;
    }
  }
  
  public String toString()
  {
    return "[property '" + getName() + "']";
  }
  
  public boolean visibleInView(Class<?> paramClass)
  {
    if ((this._viewMatcher == null) || (this._viewMatcher.isVisibleForView(paramClass))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public abstract SettableBeanProperty withName(PropertyName paramPropertyName);
  
  @Deprecated
  public SettableBeanProperty withName(String paramString)
  {
    return withName(new PropertyName(paramString));
  }
  
  public SettableBeanProperty withSimpleName(String paramString)
  {
    PropertyName localPropertyName;
    if (this._propName == null)
    {
      localPropertyName = new PropertyName(paramString);
      if (localPropertyName != this._propName) {
        break label38;
      }
    }
    for (;;)
    {
      return this;
      localPropertyName = this._propName.withSimpleName(paramString);
      break;
      label38:
      this = withName(localPropertyName);
    }
  }
  
  public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<?> paramJsonDeserializer);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/SettableBeanProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */