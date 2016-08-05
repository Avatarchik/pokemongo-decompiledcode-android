package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.Annotations;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BeanDeserializerBuilder
{
  protected SettableAnyProperty _anySetter;
  protected HashMap<String, SettableBeanProperty> _backRefProperties;
  protected final BeanDescription _beanDesc;
  protected AnnotatedMethod _buildMethod;
  protected JsonPOJOBuilder.Value _builderConfig;
  protected final boolean _caseInsensitivePropertyComparison;
  protected final boolean _defaultViewInclusion;
  protected HashSet<String> _ignorableProps;
  protected boolean _ignoreAllUnknown;
  protected List<ValueInjector> _injectables;
  protected ObjectIdReader _objectIdReader;
  protected final Map<String, SettableBeanProperty> _properties = new LinkedHashMap();
  protected ValueInstantiator _valueInstantiator;
  
  public BeanDeserializerBuilder(BeanDescription paramBeanDescription, DeserializationConfig paramDeserializationConfig)
  {
    this._beanDesc = paramBeanDescription;
    this._defaultViewInclusion = paramDeserializationConfig.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
    this._caseInsensitivePropertyComparison = paramDeserializationConfig.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
  }
  
  protected BeanDeserializerBuilder(BeanDeserializerBuilder paramBeanDeserializerBuilder)
  {
    this._beanDesc = paramBeanDeserializerBuilder._beanDesc;
    this._defaultViewInclusion = paramBeanDeserializerBuilder._defaultViewInclusion;
    this._caseInsensitivePropertyComparison = paramBeanDeserializerBuilder._caseInsensitivePropertyComparison;
    this._properties.putAll(paramBeanDeserializerBuilder._properties);
    this._injectables = _copy(paramBeanDeserializerBuilder._injectables);
    this._backRefProperties = _copy(paramBeanDeserializerBuilder._backRefProperties);
    this._ignorableProps = paramBeanDeserializerBuilder._ignorableProps;
    this._valueInstantiator = paramBeanDeserializerBuilder._valueInstantiator;
    this._objectIdReader = paramBeanDeserializerBuilder._objectIdReader;
    this._anySetter = paramBeanDeserializerBuilder._anySetter;
    this._ignoreAllUnknown = paramBeanDeserializerBuilder._ignoreAllUnknown;
    this._buildMethod = paramBeanDeserializerBuilder._buildMethod;
    this._builderConfig = paramBeanDeserializerBuilder._builderConfig;
  }
  
  private static HashMap<String, SettableBeanProperty> _copy(HashMap<String, SettableBeanProperty> paramHashMap)
  {
    if (paramHashMap == null) {}
    for (Object localObject = null;; localObject = new HashMap(paramHashMap)) {
      return (HashMap<String, SettableBeanProperty>)localObject;
    }
  }
  
  private static <T> List<T> _copy(List<T> paramList)
  {
    if (paramList == null) {}
    for (Object localObject = null;; localObject = new ArrayList(paramList)) {
      return (List<T>)localObject;
    }
  }
  
  public void addBackReferenceProperty(String paramString, SettableBeanProperty paramSettableBeanProperty)
  {
    if (this._backRefProperties == null) {
      this._backRefProperties = new HashMap(4);
    }
    this._backRefProperties.put(paramString, paramSettableBeanProperty);
    if (this._properties != null) {
      this._properties.remove(paramSettableBeanProperty.getName());
    }
  }
  
  public void addCreatorProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    addProperty(paramSettableBeanProperty);
  }
  
  public void addIgnorable(String paramString)
  {
    if (this._ignorableProps == null) {
      this._ignorableProps = new HashSet();
    }
    this._ignorableProps.add(paramString);
  }
  
  public void addInjectable(PropertyName paramPropertyName, JavaType paramJavaType, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, Object paramObject)
  {
    if (this._injectables == null) {
      this._injectables = new ArrayList();
    }
    this._injectables.add(new ValueInjector(paramPropertyName, paramJavaType, paramAnnotations, paramAnnotatedMember, paramObject));
  }
  
  public void addOrReplaceProperty(SettableBeanProperty paramSettableBeanProperty, boolean paramBoolean)
  {
    this._properties.put(paramSettableBeanProperty.getName(), paramSettableBeanProperty);
  }
  
  public void addProperty(SettableBeanProperty paramSettableBeanProperty)
  {
    SettableBeanProperty localSettableBeanProperty = (SettableBeanProperty)this._properties.put(paramSettableBeanProperty.getName(), paramSettableBeanProperty);
    if ((localSettableBeanProperty != null) && (localSettableBeanProperty != paramSettableBeanProperty)) {
      throw new IllegalArgumentException("Duplicate property '" + paramSettableBeanProperty.getName() + "' for " + this._beanDesc.getType());
    }
  }
  
  public JsonDeserializer<?> build()
  {
    Collection localCollection = this._properties.values();
    BeanPropertyMap localBeanPropertyMap = BeanPropertyMap.construct(localCollection, this._caseInsensitivePropertyComparison);
    localBeanPropertyMap.assignIndexes();
    if (!this._defaultViewInclusion) {}
    for (boolean bool = true;; bool = false)
    {
      if (!bool)
      {
        Iterator localIterator = localCollection.iterator();
        while (localIterator.hasNext()) {
          if (((SettableBeanProperty)localIterator.next()).hasViews()) {
            bool = true;
          }
        }
      }
      if (this._objectIdReader != null) {
        localBeanPropertyMap = localBeanPropertyMap.withProperty(new ObjectIdValueProperty(this._objectIdReader, PropertyMetadata.STD_REQUIRED));
      }
      return new BeanDeserializer(this, this._beanDesc, localBeanPropertyMap, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, bool);
    }
  }
  
  public AbstractDeserializer buildAbstract()
  {
    return new AbstractDeserializer(this, this._beanDesc, this._backRefProperties);
  }
  
  public JsonDeserializer<?> buildBuilderBased(JavaType paramJavaType, String paramString)
  {
    if (this._buildMethod == null)
    {
      if (!paramString.isEmpty()) {
        throw new IllegalArgumentException("Builder class " + this._beanDesc.getBeanClass().getName() + " does not have build method (name: '" + paramString + "')");
      }
    }
    else
    {
      Class localClass1 = this._buildMethod.getRawReturnType();
      Class localClass2 = paramJavaType.getRawClass();
      if ((localClass1 != localClass2) && (!localClass1.isAssignableFrom(localClass2)) && (!localClass2.isAssignableFrom(localClass1))) {
        throw new IllegalArgumentException("Build method '" + this._buildMethod.getFullName() + " has bad return type (" + localClass1.getName() + "), not compatible with POJO type (" + paramJavaType.getRawClass().getName() + ")");
      }
    }
    Collection localCollection = this._properties.values();
    BeanPropertyMap localBeanPropertyMap = BeanPropertyMap.construct(localCollection, this._caseInsensitivePropertyComparison);
    localBeanPropertyMap.assignIndexes();
    if (!this._defaultViewInclusion) {}
    for (boolean bool = true;; bool = false)
    {
      if (!bool)
      {
        Iterator localIterator = localCollection.iterator();
        while (localIterator.hasNext()) {
          if (((SettableBeanProperty)localIterator.next()).hasViews()) {
            bool = true;
          }
        }
      }
      if (this._objectIdReader != null) {
        localBeanPropertyMap = localBeanPropertyMap.withProperty(new ObjectIdValueProperty(this._objectIdReader, PropertyMetadata.STD_REQUIRED));
      }
      return new BuilderBasedDeserializer(this, this._beanDesc, localBeanPropertyMap, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, bool);
    }
  }
  
  public SettableBeanProperty findProperty(PropertyName paramPropertyName)
  {
    return (SettableBeanProperty)this._properties.get(paramPropertyName.getSimpleName());
  }
  
  public SettableAnyProperty getAnySetter()
  {
    return this._anySetter;
  }
  
  public AnnotatedMethod getBuildMethod()
  {
    return this._buildMethod;
  }
  
  public JsonPOJOBuilder.Value getBuilderConfig()
  {
    return this._builderConfig;
  }
  
  public List<ValueInjector> getInjectables()
  {
    return this._injectables;
  }
  
  public ObjectIdReader getObjectIdReader()
  {
    return this._objectIdReader;
  }
  
  public Iterator<SettableBeanProperty> getProperties()
  {
    return this._properties.values().iterator();
  }
  
  public ValueInstantiator getValueInstantiator()
  {
    return this._valueInstantiator;
  }
  
  public boolean hasProperty(PropertyName paramPropertyName)
  {
    if (findProperty(paramPropertyName) != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public SettableBeanProperty removeProperty(PropertyName paramPropertyName)
  {
    return (SettableBeanProperty)this._properties.remove(paramPropertyName.getSimpleName());
  }
  
  public void setAnySetter(SettableAnyProperty paramSettableAnyProperty)
  {
    if ((this._anySetter != null) && (paramSettableAnyProperty != null)) {
      throw new IllegalStateException("_anySetter already set to non-null");
    }
    this._anySetter = paramSettableAnyProperty;
  }
  
  public void setIgnoreUnknownProperties(boolean paramBoolean)
  {
    this._ignoreAllUnknown = paramBoolean;
  }
  
  public void setObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    this._objectIdReader = paramObjectIdReader;
  }
  
  public void setPOJOBuilder(AnnotatedMethod paramAnnotatedMethod, JsonPOJOBuilder.Value paramValue)
  {
    this._buildMethod = paramAnnotatedMethod;
    this._builderConfig = paramValue;
  }
  
  public void setValueInstantiator(ValueInstantiator paramValueInstantiator)
  {
    this._valueInstantiator = paramValueInstantiator;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/BeanDeserializerBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */