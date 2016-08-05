package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class POJOPropertiesCollector
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected LinkedList<AnnotatedMember> _anyGetters;
  protected LinkedList<AnnotatedMethod> _anySetters;
  protected final AnnotatedClass _classDef;
  protected boolean _collected;
  protected final MapperConfig<?> _config;
  protected LinkedList<POJOPropertyBuilder> _creatorProperties;
  protected final boolean _forSerialization;
  protected HashSet<String> _ignoredPropertyNames;
  protected LinkedHashMap<Object, AnnotatedMember> _injectables;
  protected LinkedList<AnnotatedMethod> _jsonValueGetters;
  protected final String _mutatorPrefix;
  protected LinkedHashMap<String, POJOPropertyBuilder> _properties;
  protected final boolean _stdBeanNaming;
  protected final JavaType _type;
  protected final VisibilityChecker<?> _visibilityChecker;
  
  protected POJOPropertiesCollector(MapperConfig<?> paramMapperConfig, boolean paramBoolean, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass, String paramString)
  {
    this._config = paramMapperConfig;
    this._stdBeanNaming = paramMapperConfig.isEnabled(MapperFeature.USE_STD_BEAN_NAMING);
    this._forSerialization = paramBoolean;
    this._type = paramJavaType;
    this._classDef = paramAnnotatedClass;
    if (paramString == null) {
      paramString = "set";
    }
    this._mutatorPrefix = paramString;
    AnnotationIntrospector localAnnotationIntrospector;
    if (paramMapperConfig.isAnnotationProcessingEnabled())
    {
      localAnnotationIntrospector = this._config.getAnnotationIntrospector();
      this._annotationIntrospector = localAnnotationIntrospector;
      if (this._annotationIntrospector != null) {
        break label98;
      }
    }
    label98:
    for (this._visibilityChecker = this._config.getDefaultVisibilityChecker();; this._visibilityChecker = this._annotationIntrospector.findAutoDetectVisibility(paramAnnotatedClass, this._config.getDefaultVisibilityChecker()))
    {
      return;
      localAnnotationIntrospector = null;
      break;
    }
  }
  
  private void _collectIgnorals(String paramString)
  {
    if (!this._forSerialization)
    {
      if (this._ignoredPropertyNames == null) {
        this._ignoredPropertyNames = new HashSet();
      }
      this._ignoredPropertyNames.add(paramString);
    }
  }
  
  private PropertyNamingStrategy _findNamingStrategy()
  {
    Object localObject1;
    Object localObject2;
    if (this._annotationIntrospector == null)
    {
      localObject1 = null;
      if (localObject1 != null) {
        break label40;
      }
      localObject2 = this._config.getPropertyNamingStrategy();
    }
    for (;;)
    {
      return (PropertyNamingStrategy)localObject2;
      localObject1 = this._annotationIntrospector.findNamingStrategy(this._classDef);
      break;
      label40:
      if ((localObject1 instanceof PropertyNamingStrategy))
      {
        localObject2 = (PropertyNamingStrategy)localObject1;
      }
      else
      {
        if (!(localObject1 instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector returned PropertyNamingStrategy definition of type " + localObject1.getClass().getName() + "; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead");
        }
        Class localClass = (Class)localObject1;
        if (!PropertyNamingStrategy.class.isAssignableFrom(localClass)) {
          throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<PropertyNamingStrategy>");
        }
        HandlerInstantiator localHandlerInstantiator = this._config.getHandlerInstantiator();
        if (localHandlerInstantiator != null)
        {
          PropertyNamingStrategy localPropertyNamingStrategy = localHandlerInstantiator.namingStrategyInstance(this._config, this._classDef, localClass);
          if (localPropertyNamingStrategy != null)
          {
            localObject2 = localPropertyNamingStrategy;
            continue;
          }
        }
        localObject2 = (PropertyNamingStrategy)ClassUtil.createInstance(localClass, this._config.canOverrideAccessModifiers());
      }
    }
  }
  
  private PropertyName _propNameFromSimple(String paramString)
  {
    return PropertyName.construct(paramString, null);
  }
  
  protected void _addCreatorParam(Map<String, POJOPropertyBuilder> paramMap, AnnotatedParameter paramAnnotatedParameter)
  {
    String str = this._annotationIntrospector.findImplicitPropertyName(paramAnnotatedParameter);
    if (str == null) {
      str = "";
    }
    PropertyName localPropertyName = this._annotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);
    boolean bool;
    if ((localPropertyName != null) && (!localPropertyName.isEmpty()))
    {
      bool = true;
      if (bool) {
        break label81;
      }
      if (!str.isEmpty()) {
        break label61;
      }
    }
    label61:
    while (!this._annotationIntrospector.hasCreatorAnnotation(paramAnnotatedParameter.getOwner()))
    {
      return;
      bool = false;
      break;
    }
    localPropertyName = PropertyName.construct(str);
    label81:
    if ((bool) && (str.isEmpty())) {}
    for (POJOPropertyBuilder localPOJOPropertyBuilder = _property(paramMap, localPropertyName);; localPOJOPropertyBuilder = _property(paramMap, str))
    {
      localPOJOPropertyBuilder.addCtor(paramAnnotatedParameter, localPropertyName, bool, true, false);
      this._creatorProperties.add(localPOJOPropertyBuilder);
      break;
    }
  }
  
  protected void _addCreators(Map<String, POJOPropertyBuilder> paramMap)
  {
    if (this._annotationIntrospector != null)
    {
      Iterator localIterator1 = this._classDef.getConstructors().iterator();
      while (localIterator1.hasNext())
      {
        AnnotatedConstructor localAnnotatedConstructor = (AnnotatedConstructor)localIterator1.next();
        if (this._creatorProperties == null) {
          this._creatorProperties = new LinkedList();
        }
        int k = 0;
        int m = localAnnotatedConstructor.getParameterCount();
        while (k < m)
        {
          _addCreatorParam(paramMap, localAnnotatedConstructor.getParameter(k));
          k++;
        }
      }
      Iterator localIterator2 = this._classDef.getStaticMethods().iterator();
      while (localIterator2.hasNext())
      {
        AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator2.next();
        if (this._creatorProperties == null) {
          this._creatorProperties = new LinkedList();
        }
        int i = 0;
        int j = localAnnotatedMethod.getParameterCount();
        while (i < j)
        {
          _addCreatorParam(paramMap, localAnnotatedMethod.getParameter(i));
          i++;
        }
      }
    }
  }
  
  protected void _addFields(Map<String, POJOPropertyBuilder> paramMap)
  {
    AnnotationIntrospector localAnnotationIntrospector = this._annotationIntrospector;
    int i;
    boolean bool1;
    label53:
    AnnotatedField localAnnotatedField;
    String str;
    label82:
    PropertyName localPropertyName;
    label101:
    boolean bool2;
    label109:
    boolean bool3;
    if ((!this._forSerialization) && (!this._config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS)))
    {
      i = 1;
      bool1 = this._config.isEnabled(MapperFeature.PROPAGATE_TRANSIENT_MARKER);
      Iterator localIterator = this._classDef.fields().iterator();
      if (!localIterator.hasNext()) {
        return;
      }
      localAnnotatedField = (AnnotatedField)localIterator.next();
      if (localAnnotationIntrospector != null) {
        break label247;
      }
      str = null;
      if (str == null) {
        str = localAnnotatedField.getName();
      }
      if (localAnnotationIntrospector != null) {
        break label258;
      }
      localPropertyName = null;
      if (localPropertyName == null) {
        break label287;
      }
      bool2 = true;
      if ((bool2) && (localPropertyName.isEmpty()))
      {
        localPropertyName = _propNameFromSimple(str);
        bool2 = false;
      }
      if (localPropertyName == null) {
        break label293;
      }
      bool3 = true;
      label141:
      if (!bool3) {
        bool3 = this._visibilityChecker.isFieldVisible(localAnnotatedField);
      }
      if ((localAnnotationIntrospector == null) || (!localAnnotationIntrospector.hasIgnoreMarker(localAnnotatedField))) {
        break label299;
      }
    }
    label247:
    label258:
    label287:
    label293:
    label299:
    for (boolean bool4 = true;; bool4 = false)
    {
      if (localAnnotatedField.isTransient())
      {
        bool3 = false;
        if (bool1) {
          bool4 = true;
        }
      }
      if ((i != 0) && (localPropertyName == null) && (!bool4) && (Modifier.isFinal(localAnnotatedField.getModifiers()))) {
        break label53;
      }
      _property(paramMap, str).addField(localAnnotatedField, localPropertyName, bool2, bool3, bool4);
      break label53;
      i = 0;
      break;
      str = localAnnotationIntrospector.findImplicitPropertyName(localAnnotatedField);
      break label82;
      if (this._forSerialization)
      {
        localPropertyName = localAnnotationIntrospector.findNameForSerialization(localAnnotatedField);
        break label101;
      }
      localPropertyName = localAnnotationIntrospector.findNameForDeserialization(localAnnotatedField);
      break label101;
      bool2 = false;
      break label109;
      bool3 = false;
      break label141;
    }
  }
  
  protected void _addGetterMethod(Map<String, POJOPropertyBuilder> paramMap, AnnotatedMethod paramAnnotatedMethod, AnnotationIntrospector paramAnnotationIntrospector)
  {
    boolean bool1 = false;
    String str = null;
    if (!paramAnnotatedMethod.hasReturnType()) {}
    PropertyName localPropertyName;
    label101:
    boolean bool2;
    label109:
    label118:
    do
    {
      for (;;)
      {
        return;
        if (paramAnnotationIntrospector == null) {
          break;
        }
        if (paramAnnotationIntrospector.hasAnyGetterAnnotation(paramAnnotatedMethod))
        {
          if (this._anyGetters == null) {
            this._anyGetters = new LinkedList();
          }
          this._anyGetters.add(paramAnnotatedMethod);
        }
        else
        {
          if (!paramAnnotationIntrospector.hasAsValueAnnotation(paramAnnotatedMethod)) {
            break;
          }
          if (this._jsonValueGetters == null) {
            this._jsonValueGetters = new LinkedList();
          }
          this._jsonValueGetters.add(paramAnnotatedMethod);
        }
      }
      if (paramAnnotationIntrospector != null) {
        break;
      }
      localPropertyName = null;
      if (localPropertyName == null) {
        break label209;
      }
      bool2 = true;
      if (bool2) {
        break label240;
      }
      if (paramAnnotationIntrospector != null) {
        break label215;
      }
      if (str == null) {
        str = BeanUtil.okNameForRegularGetter(paramAnnotatedMethod, paramAnnotatedMethod.getName(), this._stdBeanNaming);
      }
      if (str != null) {
        break label225;
      }
      str = BeanUtil.okNameForIsGetter(paramAnnotatedMethod, paramAnnotatedMethod.getName(), this._stdBeanNaming);
    } while (str == null);
    boolean bool3 = this._visibilityChecker.isIsGetterVisible(paramAnnotatedMethod);
    label173:
    if (paramAnnotationIntrospector == null) {}
    for (;;)
    {
      _property(paramMap, str).addGetter(paramAnnotatedMethod, localPropertyName, bool2, bool3, bool1);
      break;
      localPropertyName = paramAnnotationIntrospector.findNameForSerialization(paramAnnotatedMethod);
      break label101;
      label209:
      bool2 = false;
      break label109;
      label215:
      str = paramAnnotationIntrospector.findImplicitPropertyName(paramAnnotatedMethod);
      break label118;
      label225:
      bool3 = this._visibilityChecker.isGetterVisible(paramAnnotatedMethod);
      break label173;
      label240:
      if (paramAnnotationIntrospector == null) {}
      for (;;)
      {
        if (str == null) {
          str = BeanUtil.okNameForGetter(paramAnnotatedMethod, this._stdBeanNaming);
        }
        if (str == null) {
          str = paramAnnotatedMethod.getName();
        }
        if (localPropertyName.isEmpty())
        {
          localPropertyName = _propNameFromSimple(str);
          bool2 = false;
        }
        bool3 = true;
        break;
        str = paramAnnotationIntrospector.findImplicitPropertyName(paramAnnotatedMethod);
      }
      bool1 = paramAnnotationIntrospector.hasIgnoreMarker(paramAnnotatedMethod);
    }
  }
  
  protected void _addInjectables(Map<String, POJOPropertyBuilder> paramMap)
  {
    AnnotationIntrospector localAnnotationIntrospector = this._annotationIntrospector;
    if (localAnnotationIntrospector == null) {}
    for (;;)
    {
      return;
      Iterator localIterator1 = this._classDef.fields().iterator();
      while (localIterator1.hasNext())
      {
        AnnotatedField localAnnotatedField = (AnnotatedField)localIterator1.next();
        _doAddInjectable(localAnnotationIntrospector.findInjectableValueId(localAnnotatedField), localAnnotatedField);
      }
      Iterator localIterator2 = this._classDef.memberMethods().iterator();
      while (localIterator2.hasNext())
      {
        AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator2.next();
        if (localAnnotatedMethod.getParameterCount() == 1) {
          _doAddInjectable(localAnnotationIntrospector.findInjectableValueId(localAnnotatedMethod), localAnnotatedMethod);
        }
      }
    }
  }
  
  protected void _addMethods(Map<String, POJOPropertyBuilder> paramMap)
  {
    AnnotationIntrospector localAnnotationIntrospector = this._annotationIntrospector;
    Iterator localIterator = this._classDef.memberMethods().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
      int i = localAnnotatedMethod.getParameterCount();
      if (i == 0)
      {
        _addGetterMethod(paramMap, localAnnotatedMethod, localAnnotationIntrospector);
      }
      else if (i == 1)
      {
        _addSetterMethod(paramMap, localAnnotatedMethod, localAnnotationIntrospector);
      }
      else if ((i == 2) && (localAnnotationIntrospector != null) && (localAnnotationIntrospector.hasAnySetterAnnotation(localAnnotatedMethod)))
      {
        if (this._anySetters == null) {
          this._anySetters = new LinkedList();
        }
        this._anySetters.add(localAnnotatedMethod);
      }
    }
  }
  
  protected void _addSetterMethod(Map<String, POJOPropertyBuilder> paramMap, AnnotatedMethod paramAnnotatedMethod, AnnotationIntrospector paramAnnotationIntrospector)
  {
    boolean bool1 = false;
    String str = null;
    PropertyName localPropertyName;
    boolean bool2;
    if (paramAnnotationIntrospector == null)
    {
      localPropertyName = null;
      if (localPropertyName == null) {
        break label65;
      }
      bool2 = true;
      label21:
      if (bool2) {
        break label119;
      }
      if (paramAnnotationIntrospector != null) {
        break label71;
      }
    }
    for (;;)
    {
      if (str == null) {
        str = BeanUtil.okNameForMutator(paramAnnotatedMethod, this._mutatorPrefix, this._stdBeanNaming);
      }
      if (str != null) {
        break label81;
      }
      return;
      localPropertyName = paramAnnotationIntrospector.findNameForDeserialization(paramAnnotatedMethod);
      break;
      label65:
      bool2 = false;
      break label21;
      label71:
      str = paramAnnotationIntrospector.findImplicitPropertyName(paramAnnotatedMethod);
    }
    label81:
    boolean bool3 = this._visibilityChecker.isSetterVisible(paramAnnotatedMethod);
    if (paramAnnotationIntrospector == null) {}
    for (;;)
    {
      _property(paramMap, str).addSetter(paramAnnotatedMethod, localPropertyName, bool2, bool3, bool1);
      break;
      label119:
      if (paramAnnotationIntrospector == null) {}
      for (;;)
      {
        if (str == null) {
          str = BeanUtil.okNameForMutator(paramAnnotatedMethod, this._mutatorPrefix, this._stdBeanNaming);
        }
        if (str == null) {
          str = paramAnnotatedMethod.getName();
        }
        if (localPropertyName.isEmpty())
        {
          localPropertyName = _propNameFromSimple(str);
          bool2 = false;
        }
        bool3 = true;
        break;
        str = paramAnnotationIntrospector.findImplicitPropertyName(paramAnnotatedMethod);
      }
      bool1 = paramAnnotationIntrospector.hasIgnoreMarker(paramAnnotatedMethod);
    }
  }
  
  protected void _doAddInjectable(Object paramObject, AnnotatedMember paramAnnotatedMember)
  {
    if (paramObject == null) {}
    do
    {
      return;
      if (this._injectables == null) {
        this._injectables = new LinkedHashMap();
      }
    } while ((AnnotatedMember)this._injectables.put(paramObject, paramAnnotatedMember) == null);
    String str = paramObject.getClass().getName();
    throw new IllegalArgumentException("Duplicate injectable value with id '" + String.valueOf(paramObject) + "' (of type " + str + ")");
  }
  
  protected POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> paramMap, PropertyName paramPropertyName)
  {
    return _property(paramMap, paramPropertyName.getSimpleName());
  }
  
  protected POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> paramMap, String paramString)
  {
    POJOPropertyBuilder localPOJOPropertyBuilder = (POJOPropertyBuilder)paramMap.get(paramString);
    if (localPOJOPropertyBuilder == null)
    {
      localPOJOPropertyBuilder = new POJOPropertyBuilder(PropertyName.construct(paramString), this._annotationIntrospector, this._forSerialization);
      paramMap.put(paramString, localPOJOPropertyBuilder);
    }
    return localPOJOPropertyBuilder;
  }
  
  protected void _removeUnwantedAccessor(Map<String, POJOPropertyBuilder> paramMap)
  {
    boolean bool = this._config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS);
    Iterator localIterator = paramMap.values().iterator();
    while (localIterator.hasNext()) {
      ((POJOPropertyBuilder)localIterator.next()).removeNonVisible(bool);
    }
  }
  
  protected void _removeUnwantedProperties(Map<String, POJOPropertyBuilder> paramMap)
  {
    Iterator localIterator = paramMap.values().iterator();
    while (localIterator.hasNext())
    {
      POJOPropertyBuilder localPOJOPropertyBuilder = (POJOPropertyBuilder)localIterator.next();
      if (!localPOJOPropertyBuilder.anyVisible()) {
        localIterator.remove();
      } else if (localPOJOPropertyBuilder.anyIgnorals()) {
        if (!localPOJOPropertyBuilder.isExplicitlyIncluded())
        {
          localIterator.remove();
          _collectIgnorals(localPOJOPropertyBuilder.getName());
        }
        else
        {
          localPOJOPropertyBuilder.removeIgnored();
          if ((!this._forSerialization) && (!localPOJOPropertyBuilder.couldDeserialize())) {
            _collectIgnorals(localPOJOPropertyBuilder.getName());
          }
        }
      }
    }
  }
  
  protected void _renameProperties(Map<String, POJOPropertyBuilder> paramMap)
  {
    Iterator localIterator1 = paramMap.entrySet().iterator();
    LinkedList localLinkedList = null;
    while (localIterator1.hasNext())
    {
      POJOPropertyBuilder localPOJOPropertyBuilder3 = (POJOPropertyBuilder)((Map.Entry)localIterator1.next()).getValue();
      Set localSet = localPOJOPropertyBuilder3.findExplicitNames();
      if (!localSet.isEmpty())
      {
        localIterator1.remove();
        if (localLinkedList == null) {
          localLinkedList = new LinkedList();
        }
        if (localSet.size() == 1) {
          localLinkedList.add(localPOJOPropertyBuilder3.withName((PropertyName)localSet.iterator().next()));
        } else {
          localLinkedList.addAll(localPOJOPropertyBuilder3.explode(localSet));
        }
      }
    }
    if (localLinkedList != null)
    {
      Iterator localIterator2 = localLinkedList.iterator();
      if (localIterator2.hasNext())
      {
        POJOPropertyBuilder localPOJOPropertyBuilder1 = (POJOPropertyBuilder)localIterator2.next();
        String str = localPOJOPropertyBuilder1.getName();
        POJOPropertyBuilder localPOJOPropertyBuilder2 = (POJOPropertyBuilder)paramMap.get(str);
        if (localPOJOPropertyBuilder2 == null) {
          paramMap.put(str, localPOJOPropertyBuilder1);
        }
        for (;;)
        {
          _updateCreatorProperty(localPOJOPropertyBuilder1, this._creatorProperties);
          break;
          localPOJOPropertyBuilder2.addAll(localPOJOPropertyBuilder1);
        }
      }
    }
  }
  
  protected void _renameUsing(Map<String, POJOPropertyBuilder> paramMap, PropertyNamingStrategy paramPropertyNamingStrategy)
  {
    POJOPropertyBuilder[] arrayOfPOJOPropertyBuilder = (POJOPropertyBuilder[])paramMap.values().toArray(new POJOPropertyBuilder[paramMap.size()]);
    paramMap.clear();
    int i = arrayOfPOJOPropertyBuilder.length;
    int j = 0;
    if (j < i)
    {
      POJOPropertyBuilder localPOJOPropertyBuilder1 = arrayOfPOJOPropertyBuilder[j];
      PropertyName localPropertyName = localPOJOPropertyBuilder1.getFullName();
      String str1 = null;
      label103:
      String str2;
      label131:
      POJOPropertyBuilder localPOJOPropertyBuilder2;
      if (!localPOJOPropertyBuilder1.isExplicitlyNamed())
      {
        if (!this._forSerialization) {
          break label207;
        }
        if (localPOJOPropertyBuilder1.hasGetter()) {
          str1 = paramPropertyNamingStrategy.nameForGetterMethod(this._config, localPOJOPropertyBuilder1.getGetter(), localPropertyName.getSimpleName());
        }
      }
      else
      {
        if ((str1 == null) || (localPropertyName.hasSimpleName(str1))) {
          break label331;
        }
        localPOJOPropertyBuilder1 = localPOJOPropertyBuilder1.withSimpleName(str1);
        str2 = str1;
        localPOJOPropertyBuilder2 = (POJOPropertyBuilder)paramMap.get(str2);
        if (localPOJOPropertyBuilder2 != null) {
          break label341;
        }
        paramMap.put(str2, localPOJOPropertyBuilder1);
      }
      for (;;)
      {
        _updateCreatorProperty(localPOJOPropertyBuilder1, this._creatorProperties);
        j++;
        break;
        if (!localPOJOPropertyBuilder1.hasField()) {
          break label103;
        }
        str1 = paramPropertyNamingStrategy.nameForField(this._config, localPOJOPropertyBuilder1.getField(), localPropertyName.getSimpleName());
        break label103;
        label207:
        if (localPOJOPropertyBuilder1.hasSetter())
        {
          str1 = paramPropertyNamingStrategy.nameForSetterMethod(this._config, localPOJOPropertyBuilder1.getSetter(), localPropertyName.getSimpleName());
          break label103;
        }
        if (localPOJOPropertyBuilder1.hasConstructorParameter())
        {
          str1 = paramPropertyNamingStrategy.nameForConstructorParameter(this._config, localPOJOPropertyBuilder1.getConstructorParameter(), localPropertyName.getSimpleName());
          break label103;
        }
        if (localPOJOPropertyBuilder1.hasField())
        {
          str1 = paramPropertyNamingStrategy.nameForField(this._config, localPOJOPropertyBuilder1.getField(), localPropertyName.getSimpleName());
          break label103;
        }
        if (!localPOJOPropertyBuilder1.hasGetter()) {
          break label103;
        }
        str1 = paramPropertyNamingStrategy.nameForGetterMethod(this._config, localPOJOPropertyBuilder1.getGetter(), localPropertyName.getSimpleName());
        break label103;
        label331:
        str2 = localPropertyName.getSimpleName();
        break label131;
        label341:
        localPOJOPropertyBuilder2.addAll(localPOJOPropertyBuilder1);
      }
    }
  }
  
  protected void _renameWithWrappers(Map<String, POJOPropertyBuilder> paramMap)
  {
    Iterator localIterator1 = paramMap.entrySet().iterator();
    LinkedList localLinkedList = null;
    while (localIterator1.hasNext())
    {
      POJOPropertyBuilder localPOJOPropertyBuilder3 = (POJOPropertyBuilder)((Map.Entry)localIterator1.next()).getValue();
      AnnotatedMember localAnnotatedMember = localPOJOPropertyBuilder3.getPrimaryMember();
      if (localAnnotatedMember != null)
      {
        PropertyName localPropertyName = this._annotationIntrospector.findWrapperName(localAnnotatedMember);
        if ((localPropertyName != null) && (localPropertyName.hasSimpleName()) && (!localPropertyName.equals(localPOJOPropertyBuilder3.getFullName())))
        {
          if (localLinkedList == null) {
            localLinkedList = new LinkedList();
          }
          localLinkedList.add(localPOJOPropertyBuilder3.withName(localPropertyName));
          localIterator1.remove();
        }
      }
    }
    if (localLinkedList != null)
    {
      Iterator localIterator2 = localLinkedList.iterator();
      while (localIterator2.hasNext())
      {
        POJOPropertyBuilder localPOJOPropertyBuilder1 = (POJOPropertyBuilder)localIterator2.next();
        String str = localPOJOPropertyBuilder1.getName();
        POJOPropertyBuilder localPOJOPropertyBuilder2 = (POJOPropertyBuilder)paramMap.get(str);
        if (localPOJOPropertyBuilder2 == null) {
          paramMap.put(str, localPOJOPropertyBuilder1);
        } else {
          localPOJOPropertyBuilder2.addAll(localPOJOPropertyBuilder1);
        }
      }
    }
  }
  
  protected void _sortProperties(Map<String, POJOPropertyBuilder> paramMap)
  {
    AnnotationIntrospector localAnnotationIntrospector = this._annotationIntrospector;
    Boolean localBoolean;
    boolean bool;
    label24:
    String[] arrayOfString1;
    if (localAnnotationIntrospector == null)
    {
      localBoolean = null;
      if (localBoolean != null) {
        break label61;
      }
      bool = this._config.shouldSortPropertiesAlphabetically();
      if (localAnnotationIntrospector != null) {
        break label70;
      }
      arrayOfString1 = null;
      label31:
      if ((bool) || (this._creatorProperties != null) || (arrayOfString1 != null)) {
        break label83;
      }
    }
    for (;;)
    {
      return;
      localBoolean = localAnnotationIntrospector.findSerializationSortAlphabetically(this._classDef);
      break;
      label61:
      bool = localBoolean.booleanValue();
      break label24;
      label70:
      arrayOfString1 = localAnnotationIntrospector.findSerializationPropertyOrder(this._classDef);
      break label31;
      label83:
      int i = paramMap.size();
      if (bool) {}
      for (Object localObject1 = new TreeMap();; localObject1 = new LinkedHashMap(i + i))
      {
        Iterator localIterator1 = paramMap.values().iterator();
        while (localIterator1.hasNext())
        {
          POJOPropertyBuilder localPOJOPropertyBuilder4 = (POJOPropertyBuilder)localIterator1.next();
          ((Map)localObject1).put(localPOJOPropertyBuilder4.getName(), localPOJOPropertyBuilder4);
        }
      }
      LinkedHashMap localLinkedHashMap = new LinkedHashMap(i + i);
      if (arrayOfString1 != null) {
        for (String str : arrayOfString1)
        {
          Object localObject3 = (POJOPropertyBuilder)((Map)localObject1).get(str);
          if (localObject3 == null)
          {
            Iterator localIterator4 = paramMap.values().iterator();
            while (localIterator4.hasNext())
            {
              POJOPropertyBuilder localPOJOPropertyBuilder3 = (POJOPropertyBuilder)localIterator4.next();
              if (str.equals(localPOJOPropertyBuilder3.getInternalName()))
              {
                localObject3 = localPOJOPropertyBuilder3;
                str = localPOJOPropertyBuilder3.getName();
              }
            }
          }
          if (localObject3 != null) {
            localLinkedHashMap.put(str, localObject3);
          }
        }
      }
      if (this._creatorProperties != null)
      {
        TreeMap localTreeMap;
        if (bool)
        {
          localTreeMap = new TreeMap();
          Iterator localIterator2 = this._creatorProperties.iterator();
          while (localIterator2.hasNext())
          {
            POJOPropertyBuilder localPOJOPropertyBuilder2 = (POJOPropertyBuilder)localIterator2.next();
            localTreeMap.put(localPOJOPropertyBuilder2.getName(), localPOJOPropertyBuilder2);
          }
        }
        for (Object localObject2 = localTreeMap.values();; localObject2 = this._creatorProperties)
        {
          Iterator localIterator3 = ((Collection)localObject2).iterator();
          while (localIterator3.hasNext())
          {
            POJOPropertyBuilder localPOJOPropertyBuilder1 = (POJOPropertyBuilder)localIterator3.next();
            localLinkedHashMap.put(localPOJOPropertyBuilder1.getName(), localPOJOPropertyBuilder1);
          }
        }
      }
      localLinkedHashMap.putAll((Map)localObject1);
      paramMap.clear();
      paramMap.putAll(localLinkedHashMap);
    }
  }
  
  protected void _updateCreatorProperty(POJOPropertyBuilder paramPOJOPropertyBuilder, List<POJOPropertyBuilder> paramList)
  {
    int i;
    int j;
    if (paramList != null)
    {
      i = 0;
      j = paramList.size();
    }
    for (;;)
    {
      if (i < j)
      {
        if (((POJOPropertyBuilder)paramList.get(i)).getInternalName().equals(paramPOJOPropertyBuilder.getInternalName())) {
          paramList.set(i, paramPOJOPropertyBuilder);
        }
      }
      else {
        return;
      }
      i++;
    }
  }
  
  @Deprecated
  public POJOPropertiesCollector collect()
  {
    return this;
  }
  
  protected void collectAll()
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    _addFields(localLinkedHashMap);
    _addMethods(localLinkedHashMap);
    _addCreators(localLinkedHashMap);
    _addInjectables(localLinkedHashMap);
    _removeUnwantedProperties(localLinkedHashMap);
    Iterator localIterator1 = localLinkedHashMap.values().iterator();
    while (localIterator1.hasNext()) {
      ((POJOPropertyBuilder)localIterator1.next()).mergeAnnotations(this._forSerialization);
    }
    _removeUnwantedAccessor(localLinkedHashMap);
    _renameProperties(localLinkedHashMap);
    PropertyNamingStrategy localPropertyNamingStrategy = _findNamingStrategy();
    if (localPropertyNamingStrategy != null) {
      _renameUsing(localLinkedHashMap, localPropertyNamingStrategy);
    }
    Iterator localIterator2 = localLinkedHashMap.values().iterator();
    while (localIterator2.hasNext()) {
      ((POJOPropertyBuilder)localIterator2.next()).trimByVisibility();
    }
    if (this._config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
      _renameWithWrappers(localLinkedHashMap);
    }
    _sortProperties(localLinkedHashMap);
    this._properties = localLinkedHashMap;
    this._collected = true;
  }
  
  public Class<?> findPOJOBuilderClass()
  {
    return this._annotationIntrospector.findPOJOBuilder(this._classDef);
  }
  
  public AnnotationIntrospector getAnnotationIntrospector()
  {
    return this._annotationIntrospector;
  }
  
  public AnnotatedMember getAnyGetter()
  {
    if (!this._collected) {
      collectAll();
    }
    if (this._anyGetters != null) {
      if (this._anyGetters.size() > 1) {
        reportProblem("Multiple 'any-getters' defined (" + this._anyGetters.get(0) + " vs " + this._anyGetters.get(1) + ")");
      }
    }
    for (AnnotatedMember localAnnotatedMember = (AnnotatedMember)this._anyGetters.getFirst();; localAnnotatedMember = null) {
      return localAnnotatedMember;
    }
  }
  
  public AnnotatedMethod getAnySetterMethod()
  {
    if (!this._collected) {
      collectAll();
    }
    if (this._anySetters != null) {
      if (this._anySetters.size() > 1) {
        reportProblem("Multiple 'any-setters' defined (" + this._anySetters.get(0) + " vs " + this._anySetters.get(1) + ")");
      }
    }
    for (AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)this._anySetters.getFirst();; localAnnotatedMethod = null) {
      return localAnnotatedMethod;
    }
  }
  
  public AnnotatedClass getClassDef()
  {
    return this._classDef;
  }
  
  public MapperConfig<?> getConfig()
  {
    return this._config;
  }
  
  public Set<String> getIgnoredPropertyNames()
  {
    return this._ignoredPropertyNames;
  }
  
  public Map<Object, AnnotatedMember> getInjectables()
  {
    if (!this._collected) {
      collectAll();
    }
    return this._injectables;
  }
  
  public AnnotatedMethod getJsonValueMethod()
  {
    if (!this._collected) {
      collectAll();
    }
    if (this._jsonValueGetters != null) {
      if (this._jsonValueGetters.size() > 1) {
        reportProblem("Multiple value properties defined (" + this._jsonValueGetters.get(0) + " vs " + this._jsonValueGetters.get(1) + ")");
      }
    }
    for (AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)this._jsonValueGetters.get(0);; localAnnotatedMethod = null) {
      return localAnnotatedMethod;
    }
  }
  
  public ObjectIdInfo getObjectIdInfo()
  {
    ObjectIdInfo localObjectIdInfo;
    if (this._annotationIntrospector == null) {
      localObjectIdInfo = null;
    }
    for (;;)
    {
      return localObjectIdInfo;
      localObjectIdInfo = this._annotationIntrospector.findObjectIdInfo(this._classDef);
      if (localObjectIdInfo != null) {
        localObjectIdInfo = this._annotationIntrospector.findObjectReferenceInfo(this._classDef, localObjectIdInfo);
      }
    }
  }
  
  public List<BeanPropertyDefinition> getProperties()
  {
    return new ArrayList(getPropertyMap().values());
  }
  
  protected Map<String, POJOPropertyBuilder> getPropertyMap()
  {
    if (!this._collected) {
      collectAll();
    }
    return this._properties;
  }
  
  public JavaType getType()
  {
    return this._type;
  }
  
  protected void reportProblem(String paramString)
  {
    throw new IllegalArgumentException("Problem with definition of " + this._classDef + ": " + paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/POJOPropertiesCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */