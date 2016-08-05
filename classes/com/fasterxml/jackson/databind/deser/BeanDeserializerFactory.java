package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.fasterxml.jackson.databind.deser.impl.NoClassDefFoundDeserializer;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BeanDeserializerFactory
  extends BasicDeserializerFactory
  implements Serializable
{
  private static final Class<?>[] INIT_CAUSE_PARAMS;
  private static final Class<?>[] NO_VIEWS = new Class[0];
  public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
  private static final long serialVersionUID = 1L;
  
  static
  {
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Throwable.class;
    INIT_CAUSE_PARAMS = arrayOfClass;
  }
  
  public BeanDeserializerFactory(DeserializerFactoryConfig paramDeserializerFactoryConfig)
  {
    super(paramDeserializerFactoryConfig);
  }
  
  protected void addBeanProps(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder)
    throws JsonMappingException
  {
    SettableBeanProperty[] arrayOfSettableBeanProperty = paramBeanDeserializerBuilder.getValueInstantiator().getFromObjectArguments(paramDeserializationContext.getConfig());
    if (!paramBeanDescription.getType().isAbstract()) {}
    HashSet localHashSet;
    for (int i = 1;; i = 0)
    {
      AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
      Boolean localBoolean = localAnnotationIntrospector.findIgnoreUnknownProperties(paramBeanDescription.getClassInfo());
      if (localBoolean != null) {
        paramBeanDeserializerBuilder.setIgnoreUnknownProperties(localBoolean.booleanValue());
      }
      localHashSet = ArrayBuilders.arrayToSet(localAnnotationIntrospector.findPropertiesToIgnore(paramBeanDescription.getClassInfo(), false));
      Iterator localIterator1 = localHashSet.iterator();
      while (localIterator1.hasNext()) {
        paramBeanDeserializerBuilder.addIgnorable((String)localIterator1.next());
      }
    }
    AnnotatedMethod localAnnotatedMethod = paramBeanDescription.findAnySetter();
    if (localAnnotatedMethod != null) {
      paramBeanDeserializerBuilder.setAnySetter(constructAnySetter(paramDeserializationContext, paramBeanDescription, localAnnotatedMethod));
    }
    if (localAnnotatedMethod == null)
    {
      Set localSet = paramBeanDescription.getIgnoredPropertyNames();
      if (localSet != null)
      {
        Iterator localIterator4 = localSet.iterator();
        while (localIterator4.hasNext()) {
          paramBeanDeserializerBuilder.addIgnorable((String)localIterator4.next());
        }
      }
    }
    if ((paramDeserializationContext.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS)) && (paramDeserializationContext.isEnabled(MapperFeature.AUTO_DETECT_GETTERS))) {}
    List localList;
    for (int j = 1;; j = 0)
    {
      localList = filterBeanProps(paramDeserializationContext, paramBeanDescription, paramBeanDeserializerBuilder, paramBeanDescription.findProperties(), localHashSet);
      if (!this._factoryConfig.hasDeserializerModifiers()) {
        break;
      }
      Iterator localIterator3 = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator3.hasNext()) {
        localList = ((BeanDeserializerModifier)localIterator3.next()).updateProperties(paramDeserializationContext.getConfig(), paramBeanDescription, localList);
      }
    }
    Iterator localIterator2 = localList.iterator();
    while (localIterator2.hasNext())
    {
      BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator2.next();
      SettableBeanProperty localSettableBeanProperty1 = null;
      String str;
      CreatorProperty localCreatorProperty;
      int k;
      if (localBeanPropertyDefinition.hasSetter())
      {
        localSettableBeanProperty1 = constructSettableProperty(paramDeserializationContext, paramBeanDescription, localBeanPropertyDefinition, localBeanPropertyDefinition.getSetter().getGenericParameterType(0));
        if ((i == 0) || (!localBeanPropertyDefinition.hasConstructorParameter())) {
          break label584;
        }
        str = localBeanPropertyDefinition.getName();
        localCreatorProperty = null;
        if (arrayOfSettableBeanProperty != null) {
          k = arrayOfSettableBeanProperty.length;
        }
      }
      for (int m = 0;; m++) {
        if (m < k)
        {
          SettableBeanProperty localSettableBeanProperty2 = arrayOfSettableBeanProperty[m];
          if ((str.equals(localSettableBeanProperty2.getName())) && ((localSettableBeanProperty2 instanceof CreatorProperty))) {
            localCreatorProperty = (CreatorProperty)localSettableBeanProperty2;
          }
        }
        else
        {
          if (localCreatorProperty != null) {
            break label560;
          }
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = str;
          arrayOfObject[1] = paramBeanDescription.getBeanClass().getName();
          throw paramDeserializationContext.mappingException("Could not find creator property with name '%s' (in class %s)", arrayOfObject);
          if (localBeanPropertyDefinition.hasField())
          {
            localSettableBeanProperty1 = constructSettableProperty(paramDeserializationContext, paramBeanDescription, localBeanPropertyDefinition, localBeanPropertyDefinition.getField().getGenericType());
            break;
          }
          if ((j == 0) || (!localBeanPropertyDefinition.hasGetter())) {
            break;
          }
          Class localClass = localBeanPropertyDefinition.getGetter().getRawType();
          if ((!Collection.class.isAssignableFrom(localClass)) && (!Map.class.isAssignableFrom(localClass))) {
            break;
          }
          localSettableBeanProperty1 = constructSetterlessProperty(paramDeserializationContext, paramBeanDescription, localBeanPropertyDefinition);
          break;
        }
      }
      label560:
      if (localSettableBeanProperty1 != null) {
        localCreatorProperty.setFallbackSetter(localSettableBeanProperty1);
      }
      paramBeanDeserializerBuilder.addCreatorProperty(localCreatorProperty);
      continue;
      label584:
      if (localSettableBeanProperty1 != null)
      {
        Class[] arrayOfClass = localBeanPropertyDefinition.findViews();
        if ((arrayOfClass == null) && (!paramDeserializationContext.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION))) {
          arrayOfClass = NO_VIEWS;
        }
        localSettableBeanProperty1.setViews(arrayOfClass);
        paramBeanDeserializerBuilder.addProperty(localSettableBeanProperty1);
      }
    }
  }
  
  protected void addInjectables(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder)
    throws JsonMappingException
  {
    Map localMap = paramBeanDescription.findInjectables();
    if (localMap != null)
    {
      boolean bool = paramDeserializationContext.canOverrideAccessModifiers();
      Iterator localIterator = localMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        AnnotatedMember localAnnotatedMember = (AnnotatedMember)localEntry.getValue();
        if (bool) {
          localAnnotatedMember.fixAccess();
        }
        paramBeanDeserializerBuilder.addInjectable(PropertyName.construct(localAnnotatedMember.getName()), paramBeanDescription.resolveType(localAnnotatedMember.getGenericType()), paramBeanDescription.getClassAnnotations(), localAnnotatedMember, localEntry.getKey());
      }
    }
  }
  
  protected void addObjectIdReader(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder)
    throws JsonMappingException
  {
    ObjectIdInfo localObjectIdInfo = paramBeanDescription.getObjectIdInfo();
    if (localObjectIdInfo == null) {
      return;
    }
    Class localClass = localObjectIdInfo.getGeneratorType();
    ObjectIdResolver localObjectIdResolver = paramDeserializationContext.objectIdResolverInstance(paramBeanDescription.getClassInfo(), localObjectIdInfo);
    SettableBeanProperty localSettableBeanProperty;
    JavaType localJavaType2;
    if (localClass == ObjectIdGenerators.PropertyGenerator.class)
    {
      PropertyName localPropertyName = localObjectIdInfo.getPropertyName();
      localSettableBeanProperty = paramBeanDeserializerBuilder.findProperty(localPropertyName);
      if (localSettableBeanProperty == null) {
        throw new IllegalArgumentException("Invalid Object Id definition for " + paramBeanDescription.getBeanClass().getName() + ": can not find property with name '" + localPropertyName + "'");
      }
      localJavaType2 = localSettableBeanProperty.getType();
    }
    for (Object localObject = new PropertyBasedObjectIdGenerator(localObjectIdInfo.getScope());; localObject = paramDeserializationContext.objectIdGeneratorInstance(paramBeanDescription.getClassInfo(), localObjectIdInfo))
    {
      JsonDeserializer localJsonDeserializer = paramDeserializationContext.findRootValueDeserializer(localJavaType2);
      paramBeanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(localJavaType2, localObjectIdInfo.getPropertyName(), (ObjectIdGenerator)localObject, localJsonDeserializer, localSettableBeanProperty, localObjectIdResolver));
      break;
      JavaType localJavaType1 = paramDeserializationContext.constructType(localClass);
      localJavaType2 = paramDeserializationContext.getTypeFactory().findTypeParameters(localJavaType1, ObjectIdGenerator.class)[0];
      localSettableBeanProperty = null;
    }
  }
  
  protected void addReferenceProperties(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder)
    throws JsonMappingException
  {
    Map localMap = paramBeanDescription.findBackReferenceProperties();
    if (localMap != null)
    {
      Iterator localIterator = localMap.entrySet().iterator();
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str = (String)localEntry.getKey();
        AnnotatedMember localAnnotatedMember = (AnnotatedMember)localEntry.getValue();
        if ((localAnnotatedMember instanceof AnnotatedMethod)) {}
        for (Object localObject = ((AnnotatedMethod)localAnnotatedMember).getGenericParameterType(0);; localObject = localAnnotatedMember.getRawType())
        {
          paramBeanDeserializerBuilder.addBackReferenceProperty(str, constructSettableProperty(paramDeserializationContext, paramBeanDescription, SimpleBeanPropertyDefinition.construct(paramDeserializationContext.getConfig(), localAnnotatedMember), (Type)localObject));
          break;
        }
      }
    }
  }
  
  public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    ValueInstantiator localValueInstantiator;
    BeanDeserializerBuilder localBeanDeserializerBuilder;
    DeserializationConfig localDeserializationConfig;
    Object localObject;
    try
    {
      localValueInstantiator = findValueInstantiator(paramDeserializationContext, paramBeanDescription);
      localBeanDeserializerBuilder = constructBeanDeserializerBuilder(paramDeserializationContext, paramBeanDescription);
      localBeanDeserializerBuilder.setValueInstantiator(localValueInstantiator);
      addBeanProps(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
      addObjectIdReader(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
      addReferenceProperties(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
      addInjectables(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
      localDeserializationConfig = paramDeserializationContext.getConfig();
      if (this._factoryConfig.hasDeserializerModifiers())
      {
        Iterator localIterator2 = this._factoryConfig.deserializerModifiers().iterator();
        while (localIterator2.hasNext())
        {
          localBeanDeserializerBuilder = ((BeanDeserializerModifier)localIterator2.next()).updateBuilder(localDeserializationConfig, paramBeanDescription, localBeanDeserializerBuilder);
          continue;
          return (JsonDeserializer<Object>)localObject;
        }
      }
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      localObject = new NoClassDefFoundDeserializer(localNoClassDefFoundError);
    }
    label221:
    for (;;)
    {
      if ((paramJavaType.isAbstract()) && (!localValueInstantiator.canInstantiate())) {}
      for (localObject = localBeanDeserializerBuilder.buildAbstract();; localObject = localBeanDeserializerBuilder.build())
      {
        if (!this._factoryConfig.hasDeserializerModifiers()) {
          break label221;
        }
        Iterator localIterator1 = this._factoryConfig.deserializerModifiers().iterator();
        while (localIterator1.hasNext()) {
          localObject = ((BeanDeserializerModifier)localIterator1.next()).modifyDeserializer(localDeserializationConfig, paramBeanDescription, (JsonDeserializer)localObject);
        }
        break;
      }
    }
  }
  
  protected JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    ValueInstantiator localValueInstantiator = findValueInstantiator(paramDeserializationContext, paramBeanDescription);
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    BeanDeserializerBuilder localBeanDeserializerBuilder = constructBeanDeserializerBuilder(paramDeserializationContext, paramBeanDescription);
    localBeanDeserializerBuilder.setValueInstantiator(localValueInstantiator);
    addBeanProps(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
    addObjectIdReader(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
    addReferenceProperties(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
    addInjectables(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
    JsonPOJOBuilder.Value localValue = paramBeanDescription.findPOJOBuilderConfig();
    if (localValue == null) {}
    for (String str = "build";; str = localValue.buildMethodName)
    {
      AnnotatedMethod localAnnotatedMethod = paramBeanDescription.findMethod(str, null);
      if ((localAnnotatedMethod != null) && (localDeserializationConfig.canOverrideAccessModifiers())) {
        ClassUtil.checkAndFixAccess(localAnnotatedMethod.getMember());
      }
      localBeanDeserializerBuilder.setPOJOBuilder(localAnnotatedMethod, localValue);
      if (!this._factoryConfig.hasDeserializerModifiers()) {
        break;
      }
      Iterator localIterator2 = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator2.hasNext()) {
        localBeanDeserializerBuilder = ((BeanDeserializerModifier)localIterator2.next()).updateBuilder(localDeserializationConfig, paramBeanDescription, localBeanDeserializerBuilder);
      }
    }
    JsonDeserializer localJsonDeserializer = localBeanDeserializerBuilder.buildBuilderBased(paramJavaType, str);
    if (this._factoryConfig.hasDeserializerModifiers())
    {
      Iterator localIterator1 = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator1.hasNext()) {
        localJsonDeserializer = ((BeanDeserializerModifier)localIterator1.next()).modifyDeserializer(localDeserializationConfig, paramBeanDescription, localJsonDeserializer);
      }
    }
    return localJsonDeserializer;
  }
  
  public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    BeanDeserializerBuilder localBeanDeserializerBuilder = constructBeanDeserializerBuilder(paramDeserializationContext, paramBeanDescription);
    localBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(paramDeserializationContext, paramBeanDescription));
    addBeanProps(paramDeserializationContext, paramBeanDescription, localBeanDeserializerBuilder);
    AnnotatedMethod localAnnotatedMethod = paramBeanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
    if (localAnnotatedMethod != null)
    {
      SettableBeanProperty localSettableBeanProperty = constructSettableProperty(paramDeserializationContext, paramBeanDescription, SimpleBeanPropertyDefinition.construct(paramDeserializationContext.getConfig(), localAnnotatedMethod, new PropertyName("cause")), localAnnotatedMethod.getGenericParameterType(0));
      if (localSettableBeanProperty != null) {
        localBeanDeserializerBuilder.addOrReplaceProperty(localSettableBeanProperty, true);
      }
    }
    localBeanDeserializerBuilder.addIgnorable("localizedMessage");
    localBeanDeserializerBuilder.addIgnorable("suppressed");
    localBeanDeserializerBuilder.addIgnorable("message");
    if (this._factoryConfig.hasDeserializerModifiers())
    {
      Iterator localIterator2 = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator2.hasNext()) {
        localBeanDeserializerBuilder = ((BeanDeserializerModifier)localIterator2.next()).updateBuilder(localDeserializationConfig, paramBeanDescription, localBeanDeserializerBuilder);
      }
    }
    Object localObject = localBeanDeserializerBuilder.build();
    if ((localObject instanceof BeanDeserializer)) {
      localObject = new ThrowableDeserializer((BeanDeserializer)localObject);
    }
    if (this._factoryConfig.hasDeserializerModifiers())
    {
      Iterator localIterator1 = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator1.hasNext()) {
        localObject = ((BeanDeserializerModifier)localIterator1.next()).modifyDeserializer(localDeserializationConfig, paramBeanDescription, (JsonDeserializer)localObject);
      }
    }
    return (JsonDeserializer<Object>)localObject;
  }
  
  protected SettableAnyProperty constructAnySetter(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, AnnotatedMethod paramAnnotatedMethod)
    throws JsonMappingException
  {
    if (paramDeserializationContext.canOverrideAccessModifiers()) {
      paramAnnotatedMethod.fixAccess();
    }
    JavaType localJavaType1 = paramBeanDescription.bindingsForBeanType().resolveType(paramAnnotatedMethod.getGenericParameterType(1));
    BeanProperty.Std localStd = new BeanProperty.Std(PropertyName.construct(paramAnnotatedMethod.getName()), localJavaType1, null, paramBeanDescription.getClassAnnotations(), paramAnnotatedMethod, PropertyMetadata.STD_OPTIONAL);
    JavaType localJavaType2 = resolveType(paramDeserializationContext, paramBeanDescription, localJavaType1, paramAnnotatedMethod);
    JsonDeserializer localJsonDeserializer = findDeserializerFromAnnotation(paramDeserializationContext, paramAnnotatedMethod);
    JavaType localJavaType3 = modifyTypeByAnnotation(paramDeserializationContext, paramAnnotatedMethod, localJavaType2);
    if (localJsonDeserializer == null) {
      localJsonDeserializer = (JsonDeserializer)localJavaType3.getValueHandler();
    }
    return new SettableAnyProperty(localStd, paramAnnotatedMethod, localJavaType3, localJsonDeserializer, (TypeDeserializer)localJavaType3.getTypeHandler());
  }
  
  protected BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
  {
    return new BeanDeserializerBuilder(paramBeanDescription, paramDeserializationContext.getConfig());
  }
  
  protected SettableBeanProperty constructSettableProperty(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, BeanPropertyDefinition paramBeanPropertyDefinition, Type paramType)
    throws JsonMappingException
  {
    AnnotatedMember localAnnotatedMember = paramBeanPropertyDefinition.getNonConstructorMutator();
    if (paramDeserializationContext.canOverrideAccessModifiers()) {
      localAnnotatedMember.fixAccess();
    }
    JavaType localJavaType1 = paramBeanDescription.resolveType(paramType);
    BeanProperty.Std localStd = new BeanProperty.Std(paramBeanPropertyDefinition.getFullName(), localJavaType1, paramBeanPropertyDefinition.getWrapperName(), paramBeanDescription.getClassAnnotations(), localAnnotatedMember, paramBeanPropertyDefinition.getMetadata());
    JavaType localJavaType2 = resolveType(paramDeserializationContext, paramBeanDescription, localJavaType1, localAnnotatedMember);
    if (localJavaType2 != localJavaType1) {
      localStd.withType(localJavaType2);
    }
    JsonDeserializer localJsonDeserializer = findDeserializerFromAnnotation(paramDeserializationContext, localAnnotatedMember);
    JavaType localJavaType3 = modifyTypeByAnnotation(paramDeserializationContext, localAnnotatedMember, localJavaType2);
    TypeDeserializer localTypeDeserializer = (TypeDeserializer)localJavaType3.getTypeHandler();
    if ((localAnnotatedMember instanceof AnnotatedMethod)) {}
    for (Object localObject = new MethodProperty(paramBeanPropertyDefinition, localJavaType3, localTypeDeserializer, paramBeanDescription.getClassAnnotations(), (AnnotatedMethod)localAnnotatedMember);; localObject = new FieldProperty(paramBeanPropertyDefinition, localJavaType3, localTypeDeserializer, paramBeanDescription.getClassAnnotations(), (AnnotatedField)localAnnotatedMember))
    {
      if (localJsonDeserializer != null) {
        localObject = ((SettableBeanProperty)localObject).withValueDeserializer(localJsonDeserializer);
      }
      AnnotationIntrospector.ReferenceProperty localReferenceProperty = paramBeanPropertyDefinition.findReferenceType();
      if ((localReferenceProperty != null) && (localReferenceProperty.isManagedReference())) {
        ((SettableBeanProperty)localObject).setManagedReferenceName(localReferenceProperty.getName());
      }
      ObjectIdInfo localObjectIdInfo = paramBeanPropertyDefinition.findObjectIdInfo();
      if (localObjectIdInfo != null) {
        ((SettableBeanProperty)localObject).setObjectIdInfo(localObjectIdInfo);
      }
      return (SettableBeanProperty)localObject;
    }
  }
  
  protected SettableBeanProperty constructSetterlessProperty(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, BeanPropertyDefinition paramBeanPropertyDefinition)
    throws JsonMappingException
  {
    AnnotatedMethod localAnnotatedMethod = paramBeanPropertyDefinition.getGetter();
    if (paramDeserializationContext.canOverrideAccessModifiers()) {
      localAnnotatedMethod.fixAccess();
    }
    JavaType localJavaType1 = localAnnotatedMethod.getType(paramBeanDescription.bindingsForBeanType());
    JsonDeserializer localJsonDeserializer = findDeserializerFromAnnotation(paramDeserializationContext, localAnnotatedMethod);
    JavaType localJavaType2 = resolveType(paramDeserializationContext, paramBeanDescription, modifyTypeByAnnotation(paramDeserializationContext, localAnnotatedMethod, localJavaType1), localAnnotatedMethod);
    Object localObject = new SetterlessProperty(paramBeanPropertyDefinition, localJavaType2, (TypeDeserializer)localJavaType2.getTypeHandler(), paramBeanDescription.getClassAnnotations(), localAnnotatedMethod);
    if (localJsonDeserializer != null) {
      localObject = ((SettableBeanProperty)localObject).withValueDeserializer(localJsonDeserializer);
    }
    return (SettableBeanProperty)localObject;
  }
  
  public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Object localObject = _findCustomBeanDeserializer(paramJavaType, localDeserializationConfig, paramBeanDescription);
    if (localObject != null) {}
    for (;;)
    {
      return (JsonDeserializer<Object>)localObject;
      if (paramJavaType.isThrowable())
      {
        localObject = buildThrowableDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
      }
      else
      {
        if (paramJavaType.isAbstract())
        {
          JavaType localJavaType = materializeAbstractType(paramDeserializationContext, paramJavaType, paramBeanDescription);
          if (localJavaType != null)
          {
            localObject = buildBeanDeserializer(paramDeserializationContext, localJavaType, localDeserializationConfig.introspect(localJavaType));
            continue;
          }
        }
        JsonDeserializer localJsonDeserializer = findStdDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
        if (localJsonDeserializer != null) {
          localObject = localJsonDeserializer;
        } else if (!isPotentialBeanType(paramJavaType.getRawClass())) {
          localObject = null;
        } else {
          localObject = buildBeanDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
        }
      }
    }
  }
  
  public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription, Class<?> paramClass)
    throws JsonMappingException
  {
    JavaType localJavaType = paramDeserializationContext.constructType(paramClass);
    return buildBuilderBasedDeserializer(paramDeserializationContext, paramJavaType, paramDeserializationContext.getConfig().introspectForBuilder(localJavaType));
  }
  
  protected List<BeanPropertyDefinition> filterBeanProps(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, BeanDeserializerBuilder paramBeanDeserializerBuilder, List<BeanPropertyDefinition> paramList, Set<String> paramSet)
    throws JsonMappingException
  {
    ArrayList localArrayList = new ArrayList(Math.max(4, paramList.size()));
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator.next();
      String str = localBeanPropertyDefinition.getName();
      if (!paramSet.contains(str))
      {
        if (!localBeanPropertyDefinition.hasConstructorParameter())
        {
          Class localClass = null;
          if (localBeanPropertyDefinition.hasSetter()) {
            localClass = localBeanPropertyDefinition.getSetter().getRawParameterType(0);
          }
          for (;;)
          {
            if ((localClass == null) || (!isIgnorableType(paramDeserializationContext.getConfig(), paramBeanDescription, localClass, localHashMap))) {
              break label160;
            }
            paramBeanDeserializerBuilder.addIgnorable(str);
            break;
            if (localBeanPropertyDefinition.hasField()) {
              localClass = localBeanPropertyDefinition.getField().getRawType();
            }
          }
        }
        label160:
        localArrayList.add(localBeanPropertyDefinition);
      }
    }
    return localArrayList;
  }
  
  protected JsonDeserializer<?> findStdDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer = findDefaultDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
    if ((localJsonDeserializer != null) && (this._factoryConfig.hasDeserializerModifiers()))
    {
      Iterator localIterator = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator.hasNext()) {
        localJsonDeserializer = ((BeanDeserializerModifier)localIterator.next()).modifyDeserializer(paramDeserializationContext.getConfig(), paramBeanDescription, localJsonDeserializer);
      }
    }
    return localJsonDeserializer;
  }
  
  protected boolean isIgnorableType(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, Class<?> paramClass, Map<Class<?>, Boolean> paramMap)
  {
    Boolean localBoolean1 = (Boolean)paramMap.get(paramClass);
    boolean bool;
    if (localBoolean1 != null) {
      bool = localBoolean1.booleanValue();
    }
    for (;;)
    {
      return bool;
      BeanDescription localBeanDescription = paramDeserializationConfig.introspectClassAnnotations(paramClass);
      Boolean localBoolean2 = paramDeserializationConfig.getAnnotationIntrospector().isIgnorableType(localBeanDescription.getClassInfo());
      if (localBoolean2 == null) {
        bool = false;
      } else {
        bool = localBoolean2.booleanValue();
      }
    }
  }
  
  protected boolean isPotentialBeanType(Class<?> paramClass)
  {
    String str1 = ClassUtil.canBeABeanType(paramClass);
    if (str1 != null) {
      throw new IllegalArgumentException("Can not deserialize Class " + paramClass.getName() + " (of type " + str1 + ") as a Bean");
    }
    if (ClassUtil.isProxyType(paramClass)) {
      throw new IllegalArgumentException("Can not deserialize Proxy class " + paramClass.getName() + " as a Bean");
    }
    String str2 = ClassUtil.isLocalType(paramClass, true);
    if (str2 != null) {
      throw new IllegalArgumentException("Can not deserialize Class " + paramClass.getName() + " (of type " + str2 + ") as a Bean");
    }
    return true;
  }
  
  protected JavaType materializeAbstractType(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JavaType localJavaType1 = paramBeanDescription.getType();
    Iterator localIterator = this._factoryConfig.abstractTypeResolvers().iterator();
    JavaType localJavaType2;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJavaType2 = ((AbstractTypeResolver)localIterator.next()).resolveAbstractType(paramDeserializationContext.getConfig(), localJavaType1);
    } while (localJavaType2 == null);
    for (;;)
    {
      return localJavaType2;
      localJavaType2 = null;
    }
  }
  
  public DeserializerFactory withConfig(DeserializerFactoryConfig paramDeserializerFactoryConfig)
  {
    if (this._factoryConfig == paramDeserializerFactoryConfig) {}
    for (;;)
    {
      return this;
      if (getClass() != BeanDeserializerFactory.class) {
        throw new IllegalStateException("Subtype of BeanDeserializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with " + "additional deserializer definitions");
      }
      this = new BeanDeserializerFactory(paramDeserializerFactoryConfig);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/BeanDeserializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */