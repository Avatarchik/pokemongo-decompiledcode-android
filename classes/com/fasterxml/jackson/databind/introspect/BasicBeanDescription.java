package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription
  extends BeanDescription
{
  protected final AnnotationIntrospector _annotationIntrospector;
  protected TypeBindings _bindings;
  protected final AnnotatedClass _classInfo;
  protected final MapperConfig<?> _config;
  protected ObjectIdInfo _objectIdInfo;
  protected final POJOPropertiesCollector _propCollector;
  protected List<BeanPropertyDefinition> _properties;
  
  protected BasicBeanDescription(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass, List<BeanPropertyDefinition> paramList)
  {
    super(paramJavaType);
    this._propCollector = null;
    this._config = paramMapperConfig;
    if (this._config == null) {}
    for (;;)
    {
      this._annotationIntrospector = localAnnotationIntrospector;
      this._classInfo = paramAnnotatedClass;
      this._properties = paramList;
      return;
      localAnnotationIntrospector = this._config.getAnnotationIntrospector();
    }
  }
  
  protected BasicBeanDescription(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    this(paramPOJOPropertiesCollector, paramPOJOPropertiesCollector.getType(), paramPOJOPropertiesCollector.getClassDef());
    this._objectIdInfo = paramPOJOPropertiesCollector.getObjectIdInfo();
  }
  
  protected BasicBeanDescription(POJOPropertiesCollector paramPOJOPropertiesCollector, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass)
  {
    super(paramJavaType);
    this._propCollector = paramPOJOPropertiesCollector;
    this._config = paramPOJOPropertiesCollector.getConfig();
    if (this._config == null) {}
    for (AnnotationIntrospector localAnnotationIntrospector = null;; localAnnotationIntrospector = this._config.getAnnotationIntrospector())
    {
      this._annotationIntrospector = localAnnotationIntrospector;
      this._classInfo = paramAnnotatedClass;
      return;
    }
  }
  
  public static BasicBeanDescription forDeserialization(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    return new BasicBeanDescription(paramPOJOPropertiesCollector);
  }
  
  public static BasicBeanDescription forOtherUse(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass)
  {
    return new BasicBeanDescription(paramMapperConfig, paramJavaType, paramAnnotatedClass, Collections.emptyList());
  }
  
  public static BasicBeanDescription forSerialization(POJOPropertiesCollector paramPOJOPropertiesCollector)
  {
    return new BasicBeanDescription(paramPOJOPropertiesCollector);
  }
  
  public Converter<Object, Object> _createConverter(Object paramObject)
  {
    Converter localConverter = null;
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    Class localClass;
    for (;;)
    {
      return (Converter<Object, Object>)localObject;
      if ((paramObject instanceof Converter))
      {
        localObject = (Converter)paramObject;
      }
      else
      {
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + paramObject.getClass().getName() + "; expected type Converter or Class<Converter> instead");
        }
        localClass = (Class)paramObject;
        if ((localClass != Converter.None.class) && (!ClassUtil.isBogusClass(localClass))) {
          break;
        }
        localObject = null;
      }
    }
    if (!Converter.class.isAssignableFrom(localClass)) {
      throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<Converter>");
    }
    HandlerInstantiator localHandlerInstantiator = this._config.getHandlerInstantiator();
    if (localHandlerInstantiator == null) {}
    for (;;)
    {
      if (localConverter == null) {
        localConverter = (Converter)ClassUtil.createInstance(localClass, this._config.canOverrideAccessModifiers());
      }
      localObject = localConverter;
      break;
      localConverter = localHandlerInstantiator.converterInstance(this._config, this._classInfo, localClass);
    }
  }
  
  protected PropertyName _findCreatorPropertyName(AnnotatedParameter paramAnnotatedParameter)
  {
    PropertyName localPropertyName = this._annotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);
    if ((localPropertyName == null) || (localPropertyName.isEmpty()))
    {
      String str = this._annotationIntrospector.findImplicitPropertyName(paramAnnotatedParameter);
      if ((str != null) && (!str.isEmpty())) {
        localPropertyName = PropertyName.construct(str);
      }
    }
    return localPropertyName;
  }
  
  public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> paramCollection, boolean paramBoolean)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Iterator localIterator = _properties().iterator();
    while (localIterator.hasNext())
    {
      BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator.next();
      AnnotatedField localAnnotatedField = localBeanPropertyDefinition.getField();
      if (localAnnotatedField != null)
      {
        String str = localBeanPropertyDefinition.getName();
        if ((paramCollection == null) || (!paramCollection.contains(str))) {
          localLinkedHashMap.put(str, localAnnotatedField);
        }
      }
    }
    return localLinkedHashMap;
  }
  
  protected List<BeanPropertyDefinition> _properties()
  {
    if (this._properties == null) {
      this._properties = this._propCollector.getProperties();
    }
    return this._properties;
  }
  
  public boolean addProperty(BeanPropertyDefinition paramBeanPropertyDefinition)
  {
    if (hasProperty(paramBeanPropertyDefinition.getFullName())) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      _properties().add(paramBeanPropertyDefinition);
    }
  }
  
  public TypeBindings bindingsForBeanType()
  {
    if (this._bindings == null) {
      this._bindings = new TypeBindings(this._config.getTypeFactory(), this._type);
    }
    return this._bindings;
  }
  
  public AnnotatedMember findAnyGetter()
    throws IllegalArgumentException
  {
    if (this._propCollector == null) {}
    for (AnnotatedMember localAnnotatedMember = null; (localAnnotatedMember != null) && (!Map.class.isAssignableFrom(localAnnotatedMember.getRawType())); localAnnotatedMember = this._propCollector.getAnyGetter()) {
      throw new IllegalArgumentException("Invalid 'any-getter' annotation on method " + localAnnotatedMember.getName() + "(): return type is not instance of java.util.Map");
    }
    return localAnnotatedMember;
  }
  
  public AnnotatedMethod findAnySetter()
    throws IllegalArgumentException
  {
    if (this._propCollector == null) {}
    for (AnnotatedMethod localAnnotatedMethod = null; localAnnotatedMethod != null; localAnnotatedMethod = this._propCollector.getAnySetterMethod())
    {
      Class localClass = localAnnotatedMethod.getRawParameterType(0);
      if ((localClass == String.class) || (localClass == Object.class)) {
        break;
      }
      throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + localAnnotatedMethod.getName() + "(): first argument not of type String or Object, but " + localClass.getName());
    }
    return localAnnotatedMethod;
  }
  
  public Map<String, AnnotatedMember> findBackReferenceProperties()
  {
    HashMap localHashMap = null;
    Iterator localIterator = _properties().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMember localAnnotatedMember = ((BeanPropertyDefinition)localIterator.next()).getMutator();
      if (localAnnotatedMember != null)
      {
        AnnotationIntrospector.ReferenceProperty localReferenceProperty = this._annotationIntrospector.findReferenceType(localAnnotatedMember);
        if ((localReferenceProperty != null) && (localReferenceProperty.isBackReference()))
        {
          if (localHashMap == null) {
            localHashMap = new HashMap();
          }
          String str = localReferenceProperty.getName();
          if (localHashMap.put(str, localAnnotatedMember) != null) {
            throw new IllegalArgumentException("Multiple back-reference properties with name '" + str + "'");
          }
        }
      }
    }
    return localHashMap;
  }
  
  @Deprecated
  public List<PropertyName> findCreatorParameterNames()
  {
    for (int i = 0; i < 2; i++)
    {
      if (i == 0) {}
      for (List localList = getConstructors();; localList = getFactoryMethods())
      {
        Iterator localIterator = localList.iterator();
        AnnotatedWithParams localAnnotatedWithParams;
        int j;
        PropertyName localPropertyName;
        do
        {
          do
          {
            if (!localIterator.hasNext()) {
              break;
            }
            localAnnotatedWithParams = (AnnotatedWithParams)localIterator.next();
            j = localAnnotatedWithParams.getParameterCount();
          } while (j < 1);
          localPropertyName = _findCreatorPropertyName(localAnnotatedWithParams.getParameter(0));
        } while ((localPropertyName == null) || (localPropertyName.isEmpty()));
        localObject = new ArrayList();
        ((List)localObject).add(localPropertyName);
        for (int k = 1; k < j; k++) {
          ((List)localObject).add(_findCreatorPropertyName(localAnnotatedWithParams.getParameter(k)));
        }
      }
    }
    Object localObject = Collections.emptyList();
    return (List<PropertyName>)localObject;
  }
  
  @Deprecated
  public List<String> findCreatorPropertyNames()
  {
    List localList = findCreatorParameterNames();
    Object localObject;
    if (localList.isEmpty()) {
      localObject = Collections.emptyList();
    }
    for (;;)
    {
      return (List<String>)localObject;
      localObject = new ArrayList(localList.size());
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext()) {
        ((List)localObject).add(((PropertyName)localIterator.next()).getSimpleName());
      }
    }
  }
  
  public AnnotatedConstructor findDefaultConstructor()
  {
    return this._classInfo.getDefaultConstructor();
  }
  
  public Converter<Object, Object> findDeserializationConverter()
  {
    if (this._annotationIntrospector == null) {}
    for (Object localObject = null;; localObject = _createConverter(this._annotationIntrospector.findDeserializationConverter(this._classInfo))) {
      return (Converter<Object, Object>)localObject;
    }
  }
  
  public JsonFormat.Value findExpectedFormat(JsonFormat.Value paramValue)
  {
    JsonFormat.Value localValue;
    if (this._annotationIntrospector != null)
    {
      localValue = this._annotationIntrospector.findFormat(this._classInfo);
      if (localValue == null) {}
    }
    for (;;)
    {
      return localValue;
      localValue = paramValue;
    }
  }
  
  public Method findFactoryMethod(Class<?>... paramVarArgs)
  {
    Iterator localIterator = this._classInfo.getStaticMethods().iterator();
    AnnotatedMethod localAnnotatedMethod;
    int j;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
        if (isFactoryMethod(localAnnotatedMethod))
        {
          Class localClass = localAnnotatedMethod.getRawParameterType(0);
          int i = paramVarArgs.length;
          j = 0;
          label57:
          if (j < i) {
            if (!localClass.isAssignableFrom(paramVarArgs[j])) {
              break;
            }
          }
        }
      }
    }
    for (Method localMethod = localAnnotatedMethod.getAnnotated();; localMethod = null)
    {
      return localMethod;
      j++;
      break label57;
      break;
    }
  }
  
  public Map<Object, AnnotatedMember> findInjectables()
  {
    if (this._propCollector != null) {}
    for (Map localMap = this._propCollector.getInjectables();; localMap = Collections.emptyMap()) {
      return localMap;
    }
  }
  
  public AnnotatedMethod findJsonValueMethod()
  {
    if (this._propCollector == null) {}
    for (AnnotatedMethod localAnnotatedMethod = null;; localAnnotatedMethod = this._propCollector.getJsonValueMethod()) {
      return localAnnotatedMethod;
    }
  }
  
  public AnnotatedMethod findMethod(String paramString, Class<?>[] paramArrayOfClass)
  {
    return this._classInfo.findMethod(paramString, paramArrayOfClass);
  }
  
  public Class<?> findPOJOBuilder()
  {
    if (this._annotationIntrospector == null) {}
    for (Object localObject = null;; localObject = this._annotationIntrospector.findPOJOBuilder(this._classInfo)) {
      return (Class<?>)localObject;
    }
  }
  
  public JsonPOJOBuilder.Value findPOJOBuilderConfig()
  {
    if (this._annotationIntrospector == null) {}
    for (JsonPOJOBuilder.Value localValue = null;; localValue = this._annotationIntrospector.findPOJOBuilderConfig(this._classInfo)) {
      return localValue;
    }
  }
  
  public List<BeanPropertyDefinition> findProperties()
  {
    return _properties();
  }
  
  public BeanPropertyDefinition findProperty(PropertyName paramPropertyName)
  {
    Iterator localIterator = _properties().iterator();
    BeanPropertyDefinition localBeanPropertyDefinition;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator.next();
    } while (!localBeanPropertyDefinition.hasName(paramPropertyName));
    for (;;)
    {
      return localBeanPropertyDefinition;
      localBeanPropertyDefinition = null;
    }
  }
  
  public Converter<Object, Object> findSerializationConverter()
  {
    if (this._annotationIntrospector == null) {}
    for (Object localObject = null;; localObject = _createConverter(this._annotationIntrospector.findSerializationConverter(this._classInfo))) {
      return (Converter<Object, Object>)localObject;
    }
  }
  
  public JsonInclude.Include findSerializationInclusion(JsonInclude.Include paramInclude)
  {
    if (this._annotationIntrospector == null) {}
    for (;;)
    {
      return paramInclude;
      paramInclude = this._annotationIntrospector.findSerializationInclusion(this._classInfo, paramInclude);
    }
  }
  
  public JsonInclude.Include findSerializationInclusionForContent(JsonInclude.Include paramInclude)
  {
    if (this._annotationIntrospector == null) {}
    for (;;)
    {
      return paramInclude;
      paramInclude = this._annotationIntrospector.findSerializationInclusionForContent(this._classInfo, paramInclude);
    }
  }
  
  public Constructor<?> findSingleArgConstructor(Class<?>... paramVarArgs)
  {
    Iterator localIterator = this._classInfo.getConstructors().iterator();
    AnnotatedConstructor localAnnotatedConstructor;
    int j;
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localAnnotatedConstructor = (AnnotatedConstructor)localIterator.next();
        if (localAnnotatedConstructor.getParameterCount() == 1)
        {
          Class localClass = localAnnotatedConstructor.getRawParameterType(0);
          int i = paramVarArgs.length;
          j = 0;
          label57:
          if (j < i) {
            if (paramVarArgs[j] != localClass) {
              break;
            }
          }
        }
      }
    }
    for (Constructor localConstructor = localAnnotatedConstructor.getAnnotated();; localConstructor = null)
    {
      return localConstructor;
      j++;
      break label57;
      break;
    }
  }
  
  public Annotations getClassAnnotations()
  {
    return this._classInfo.getAnnotations();
  }
  
  public AnnotatedClass getClassInfo()
  {
    return this._classInfo;
  }
  
  public List<AnnotatedConstructor> getConstructors()
  {
    return this._classInfo.getConstructors();
  }
  
  public List<AnnotatedMethod> getFactoryMethods()
  {
    Object localObject = this._classInfo.getStaticMethods();
    if (((List)localObject).isEmpty()) {}
    for (;;)
    {
      return (List<AnnotatedMethod>)localObject;
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
        if (isFactoryMethod(localAnnotatedMethod)) {
          localArrayList.add(localAnnotatedMethod);
        }
      }
      localObject = localArrayList;
    }
  }
  
  public Set<String> getIgnoredPropertyNames()
  {
    if (this._propCollector == null) {}
    for (Set localSet = null;; localSet = this._propCollector.getIgnoredPropertyNames())
    {
      if (localSet == null) {
        localSet = Collections.emptySet();
      }
      return localSet;
    }
  }
  
  public ObjectIdInfo getObjectIdInfo()
  {
    return this._objectIdInfo;
  }
  
  public boolean hasKnownClassAnnotations()
  {
    return this._classInfo.hasAnnotations();
  }
  
  public boolean hasProperty(PropertyName paramPropertyName)
  {
    if (findProperty(paramPropertyName) != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Object instantiateBean(boolean paramBoolean)
  {
    AnnotatedConstructor localAnnotatedConstructor = this._classInfo.getDefaultConstructor();
    Object localObject3;
    if (localAnnotatedConstructor == null) {
      localObject3 = null;
    }
    for (;;)
    {
      return localObject3;
      if (paramBoolean) {
        localAnnotatedConstructor.fixAccess();
      }
      try
      {
        Object localObject2 = localAnnotatedConstructor.getAnnotated().newInstance(new Object[0]);
        localObject3 = localObject2;
      }
      catch (Exception localException)
      {
        for (Object localObject1 = localException; ((Throwable)localObject1).getCause() != null; localObject1 = ((Throwable)localObject1).getCause()) {}
        if ((localObject1 instanceof Error)) {
          throw ((Error)localObject1);
        }
        if ((localObject1 instanceof RuntimeException)) {
          throw ((RuntimeException)localObject1);
        }
        throw new IllegalArgumentException("Failed to instantiate bean of type " + this._classInfo.getAnnotated().getName() + ": (" + localObject1.getClass().getName() + ") " + ((Throwable)localObject1).getMessage(), (Throwable)localObject1);
      }
    }
  }
  
  protected boolean isFactoryMethod(AnnotatedMethod paramAnnotatedMethod)
  {
    boolean bool = false;
    Class localClass1 = paramAnnotatedMethod.getRawReturnType();
    if (!getBeanClass().isAssignableFrom(localClass1)) {}
    for (;;)
    {
      return bool;
      if (this._annotationIntrospector.hasCreatorAnnotation(paramAnnotatedMethod))
      {
        bool = true;
      }
      else
      {
        String str = paramAnnotatedMethod.getName();
        if ("valueOf".equals(str))
        {
          bool = true;
        }
        else if (("fromString".equals(str)) && (1 == paramAnnotatedMethod.getParameterCount()))
        {
          Class localClass2 = paramAnnotatedMethod.getRawParameterType(0);
          if ((localClass2 == String.class) || (CharSequence.class.isAssignableFrom(localClass2))) {
            bool = true;
          }
        }
      }
    }
  }
  
  public boolean removeProperty(String paramString)
  {
    Iterator localIterator = _properties().iterator();
    while (localIterator.hasNext()) {
      if (((BeanPropertyDefinition)localIterator.next()).getName().equals(paramString)) {
        localIterator.remove();
      }
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public JavaType resolveType(Type paramType)
  {
    if (paramType == null) {}
    for (JavaType localJavaType = null;; localJavaType = bindingsForBeanType().resolveType(paramType)) {
      return localJavaType;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/BasicBeanDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */