package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.util.EmptyIterator;
import com.fasterxml.jackson.databind.util.Named;
import java.util.Iterator;

public abstract class BeanPropertyDefinition
  implements Named
{
  public boolean couldDeserialize()
  {
    if (getMutator() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean couldSerialize()
  {
    if (getAccessor() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public JsonInclude.Include findInclusion()
  {
    return null;
  }
  
  public ObjectIdInfo findObjectIdInfo()
  {
    return null;
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType()
  {
    return null;
  }
  
  public Class<?>[] findViews()
  {
    return null;
  }
  
  public abstract AnnotatedMember getAccessor();
  
  public abstract AnnotatedParameter getConstructorParameter();
  
  public Iterator<AnnotatedParameter> getConstructorParameters()
  {
    return EmptyIterator.instance();
  }
  
  public abstract AnnotatedField getField();
  
  public abstract PropertyName getFullName();
  
  public abstract AnnotatedMethod getGetter();
  
  public abstract String getInternalName();
  
  public abstract PropertyMetadata getMetadata();
  
  public abstract AnnotatedMember getMutator();
  
  public abstract String getName();
  
  public abstract AnnotatedMember getNonConstructorMutator();
  
  public abstract AnnotatedMember getPrimaryMember();
  
  public abstract AnnotatedMethod getSetter();
  
  public abstract PropertyName getWrapperName();
  
  public abstract boolean hasConstructorParameter();
  
  public abstract boolean hasField();
  
  public abstract boolean hasGetter();
  
  public boolean hasName(PropertyName paramPropertyName)
  {
    return getFullName().equals(paramPropertyName);
  }
  
  public abstract boolean hasSetter();
  
  public abstract boolean isExplicitlyIncluded();
  
  public boolean isExplicitlyNamed()
  {
    return isExplicitlyIncluded();
  }
  
  public boolean isRequired()
  {
    PropertyMetadata localPropertyMetadata = getMetadata();
    if ((localPropertyMetadata != null) && (localPropertyMetadata.isRequired())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isTypeId()
  {
    return false;
  }
  
  public abstract BeanPropertyDefinition withName(PropertyName paramPropertyName);
  
  public abstract BeanPropertyDefinition withSimpleName(String paramString);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/BeanPropertyDefinition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */