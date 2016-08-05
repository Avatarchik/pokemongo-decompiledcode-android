package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Named;
import java.lang.annotation.Annotation;

public abstract interface BeanProperty
  extends Named
{
  public abstract void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor)
    throws JsonMappingException;
  
  public abstract JsonFormat.Value findFormatOverrides(AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract <A extends Annotation> A getAnnotation(Class<A> paramClass);
  
  public abstract <A extends Annotation> A getContextAnnotation(Class<A> paramClass);
  
  public abstract PropertyName getFullName();
  
  public abstract AnnotatedMember getMember();
  
  public abstract PropertyMetadata getMetadata();
  
  public abstract String getName();
  
  public abstract JavaType getType();
  
  public abstract PropertyName getWrapperName();
  
  public abstract boolean isRequired();
  
  public static class Std
    implements BeanProperty
  {
    protected final Annotations _contextAnnotations;
    protected final AnnotatedMember _member;
    protected final PropertyMetadata _metadata;
    protected final PropertyName _name;
    protected final JavaType _type;
    protected final PropertyName _wrapperName;
    
    public Std(Std paramStd, JavaType paramJavaType)
    {
      this(paramStd._name, paramJavaType, paramStd._wrapperName, paramStd._contextAnnotations, paramStd._member, paramStd._metadata);
    }
    
    public Std(PropertyName paramPropertyName1, JavaType paramJavaType, PropertyName paramPropertyName2, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, PropertyMetadata paramPropertyMetadata)
    {
      this._name = paramPropertyName1;
      this._type = paramJavaType;
      this._wrapperName = paramPropertyName2;
      this._metadata = paramPropertyMetadata;
      this._member = paramAnnotatedMember;
      this._contextAnnotations = paramAnnotations;
    }
    
    @Deprecated
    public Std(String paramString, JavaType paramJavaType, PropertyName paramPropertyName, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, boolean paramBoolean) {}
    
    public void depositSchemaProperty(JsonObjectFormatVisitor paramJsonObjectFormatVisitor)
    {
      throw new UnsupportedOperationException("Instances of " + getClass().getName() + " should not get visited");
    }
    
    public JsonFormat.Value findFormatOverrides(AnnotationIntrospector paramAnnotationIntrospector)
    {
      return null;
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
      return this._name;
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
      return this._name.getSimpleName();
    }
    
    public JavaType getType()
    {
      return this._type;
    }
    
    public PropertyName getWrapperName()
    {
      return this._wrapperName;
    }
    
    public boolean isRequired()
    {
      return this._metadata.isRequired();
    }
    
    public boolean isVirtual()
    {
      return false;
    }
    
    public Std withType(JavaType paramJavaType)
    {
      return new Std(this, paramJavaType);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/BeanProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */