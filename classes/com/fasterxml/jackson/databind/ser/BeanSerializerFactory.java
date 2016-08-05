package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.FilteredBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BeanSerializerFactory
  extends BasicSerializerFactory
  implements Serializable
{
  public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);
  private static final long serialVersionUID = 1L;
  
  protected BeanSerializerFactory(SerializerFactoryConfig paramSerializerFactoryConfig)
  {
    super(paramSerializerFactoryConfig);
  }
  
  protected BeanPropertyWriter _constructWriter(SerializerProvider paramSerializerProvider, BeanPropertyDefinition paramBeanPropertyDefinition, TypeBindings paramTypeBindings, PropertyBuilder paramPropertyBuilder, boolean paramBoolean, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    PropertyName localPropertyName = paramBeanPropertyDefinition.getFullName();
    if (paramSerializerProvider.canOverrideAccessModifiers()) {
      paramAnnotatedMember.fixAccess();
    }
    JavaType localJavaType = paramAnnotatedMember.getType(paramTypeBindings);
    BeanProperty.Std localStd = new BeanProperty.Std(localPropertyName, localJavaType, paramBeanPropertyDefinition.getWrapperName(), paramPropertyBuilder.getClassAnnotations(), paramAnnotatedMember, paramBeanPropertyDefinition.getMetadata());
    JsonSerializer localJsonSerializer1 = findSerializerFromAnnotation(paramSerializerProvider, paramAnnotatedMember);
    if ((localJsonSerializer1 instanceof ResolvableSerializer)) {
      ((ResolvableSerializer)localJsonSerializer1).resolve(paramSerializerProvider);
    }
    JsonSerializer localJsonSerializer2 = paramSerializerProvider.handlePrimaryContextualization(localJsonSerializer1, localStd);
    TypeSerializer localTypeSerializer = null;
    if ((ClassUtil.isCollectionMapOrArray(localJavaType.getRawClass())) || (localJavaType.isCollectionLikeType()) || (localJavaType.isMapLikeType())) {
      localTypeSerializer = findPropertyContentTypeSerializer(localJavaType, paramSerializerProvider.getConfig(), paramAnnotatedMember);
    }
    return paramPropertyBuilder.buildWriter(paramSerializerProvider, paramBeanPropertyDefinition, localJavaType, localJsonSerializer2, findPropertyTypeSerializer(localJavaType, paramSerializerProvider.getConfig(), paramAnnotatedMember), localTypeSerializer, paramAnnotatedMember, paramBoolean);
  }
  
  protected JsonSerializer<?> _createSerializer2(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription, boolean paramBoolean)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = null;
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    if (paramJavaType.isContainerType())
    {
      if (!paramBoolean) {
        paramBoolean = usesStaticTyping(localSerializationConfig, paramBeanDescription, null);
      }
      localJsonSerializer1 = buildContainerSerializer(paramSerializerProvider, paramJavaType, paramBeanDescription, paramBoolean);
      if (localJsonSerializer1 == null) {
        break label115;
      }
    }
    for (JsonSerializer localJsonSerializer2 = localJsonSerializer1;; localJsonSerializer2 = localJsonSerializer1)
    {
      return localJsonSerializer2;
      Iterator localIterator1 = customSerializers().iterator();
      do
      {
        if (!localIterator1.hasNext()) {
          break;
        }
        localJsonSerializer1 = ((Serializers)localIterator1.next()).findSerializer(localSerializationConfig, paramJavaType, paramBeanDescription);
      } while (localJsonSerializer1 == null);
      if (localJsonSerializer1 == null) {
        localJsonSerializer1 = findSerializerByAnnotations(paramSerializerProvider, paramJavaType, paramBeanDescription);
      }
      label115:
      if (localJsonSerializer1 == null)
      {
        localJsonSerializer1 = findSerializerByLookup(paramJavaType, localSerializationConfig, paramBeanDescription, paramBoolean);
        if (localJsonSerializer1 == null)
        {
          localJsonSerializer1 = findSerializerByPrimaryType(paramSerializerProvider, paramJavaType, paramBeanDescription, paramBoolean);
          if (localJsonSerializer1 == null)
          {
            localJsonSerializer1 = findBeanSerializer(paramSerializerProvider, paramJavaType, paramBeanDescription);
            if (localJsonSerializer1 == null)
            {
              localJsonSerializer1 = findSerializerByAddonType(localSerializationConfig, paramJavaType, paramBeanDescription, paramBoolean);
              if (localJsonSerializer1 == null) {
                localJsonSerializer1 = paramSerializerProvider.getUnknownTypeSerializer(paramBeanDescription.getBeanClass());
              }
            }
          }
        }
      }
      if ((localJsonSerializer1 != null) && (this._factoryConfig.hasSerializerModifiers()))
      {
        Iterator localIterator2 = this._factoryConfig.serializerModifiers().iterator();
        while (localIterator2.hasNext()) {
          localJsonSerializer1 = ((BeanSerializerModifier)localIterator2.next()).modifySerializer(localSerializationConfig, paramBeanDescription, localJsonSerializer1);
        }
      }
    }
  }
  
  protected JsonSerializer<Object> constructBeanSerializer(SerializerProvider paramSerializerProvider, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if (paramBeanDescription.getBeanClass() == Object.class) {}
    BeanSerializerBuilder localBeanSerializerBuilder;
    for (Object localObject3 = paramSerializerProvider.getUnknownTypeSerializer(Object.class);; localObject3 = localBeanSerializerBuilder.createDummy()) {
      do
      {
        return (JsonSerializer<Object>)localObject3;
        SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
        localBeanSerializerBuilder = constructBeanSerializerBuilder(paramBeanDescription);
        localBeanSerializerBuilder.setConfig(localSerializationConfig);
        List localList1 = findBeanProperties(paramSerializerProvider, paramBeanDescription, localBeanSerializerBuilder);
        if (localList1 == null) {}
        for (Object localObject1 = new ArrayList();; localObject1 = removeOverlappingTypeIds(paramSerializerProvider, paramBeanDescription, localBeanSerializerBuilder, localList1))
        {
          paramSerializerProvider.getAnnotationIntrospector().findAndAddVirtualProperties(localSerializationConfig, paramBeanDescription.getClassInfo(), (List)localObject1);
          if (!this._factoryConfig.hasSerializerModifiers()) {
            break;
          }
          Iterator localIterator3 = this._factoryConfig.serializerModifiers().iterator();
          while (localIterator3.hasNext()) {
            localObject1 = ((BeanSerializerModifier)localIterator3.next()).changeProperties(localSerializationConfig, paramBeanDescription, (List)localObject1);
          }
        }
        List localList2 = filterBeanProperties(localSerializationConfig, paramBeanDescription, (List)localObject1);
        if (this._factoryConfig.hasSerializerModifiers())
        {
          Iterator localIterator2 = this._factoryConfig.serializerModifiers().iterator();
          while (localIterator2.hasNext()) {
            localList2 = ((BeanSerializerModifier)localIterator2.next()).orderProperties(localSerializationConfig, paramBeanDescription, localList2);
          }
        }
        ObjectIdWriter localObjectIdWriter = constructObjectIdHandler(paramSerializerProvider, paramBeanDescription, localList2);
        localBeanSerializerBuilder.setObjectIdWriter(localObjectIdWriter);
        localBeanSerializerBuilder.setProperties(localList2);
        Object localObject2 = findFilterId(localSerializationConfig, paramBeanDescription);
        localBeanSerializerBuilder.setFilterId(localObject2);
        AnnotatedMember localAnnotatedMember = paramBeanDescription.findAnyGetter();
        if (localAnnotatedMember != null)
        {
          if (localSerializationConfig.canOverrideAccessModifiers()) {
            localAnnotatedMember.fixAccess();
          }
          JavaType localJavaType1 = localAnnotatedMember.getType(paramBeanDescription.bindingsForBeanType());
          boolean bool = localSerializationConfig.isEnabled(MapperFeature.USE_STATIC_TYPING);
          JavaType localJavaType2 = localJavaType1.getContentType();
          TypeSerializer localTypeSerializer = createTypeSerializer(localSerializationConfig, localJavaType2);
          Object localObject4 = findSerializerFromAnnotation(paramSerializerProvider, localAnnotatedMember);
          if (localObject4 == null) {
            localObject4 = MapSerializer.construct(null, localJavaType1, bool, localTypeSerializer, null, null, null);
          }
          AnyGetterWriter localAnyGetterWriter = new AnyGetterWriter(new BeanProperty.Std(PropertyName.construct(localAnnotatedMember.getName()), localJavaType2, null, paramBeanDescription.getClassAnnotations(), localAnnotatedMember, PropertyMetadata.STD_OPTIONAL), localAnnotatedMember, (JsonSerializer)localObject4);
          localBeanSerializerBuilder.setAnyGetter(localAnyGetterWriter);
        }
        processViews(localSerializationConfig, localBeanSerializerBuilder);
        if (this._factoryConfig.hasSerializerModifiers())
        {
          Iterator localIterator1 = this._factoryConfig.serializerModifiers().iterator();
          while (localIterator1.hasNext()) {
            localBeanSerializerBuilder = ((BeanSerializerModifier)localIterator1.next()).updateBuilder(localSerializationConfig, paramBeanDescription, localBeanSerializerBuilder);
          }
        }
        localObject3 = localBeanSerializerBuilder.build();
      } while ((localObject3 != null) || (!paramBeanDescription.hasKnownClassAnnotations()));
    }
  }
  
  protected BeanSerializerBuilder constructBeanSerializerBuilder(BeanDescription paramBeanDescription)
  {
    return new BeanSerializerBuilder(paramBeanDescription);
  }
  
  protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter paramBeanPropertyWriter, Class<?>[] paramArrayOfClass)
  {
    return FilteredBeanPropertyWriter.constructViewBased(paramBeanPropertyWriter, paramArrayOfClass);
  }
  
  protected ObjectIdWriter constructObjectIdHandler(SerializerProvider paramSerializerProvider, BeanDescription paramBeanDescription, List<BeanPropertyWriter> paramList)
    throws JsonMappingException
  {
    ObjectIdInfo localObjectIdInfo = paramBeanDescription.getObjectIdInfo();
    ObjectIdWriter localObjectIdWriter;
    if (localObjectIdInfo == null) {
      localObjectIdWriter = null;
    }
    for (;;)
    {
      return localObjectIdWriter;
      Class localClass = localObjectIdInfo.getGeneratorType();
      if (localClass == ObjectIdGenerators.PropertyGenerator.class)
      {
        String str = localObjectIdInfo.getPropertyName().getSimpleName();
        int i = 0;
        int j = paramList.size();
        for (;;)
        {
          if (i == j) {
            throw new IllegalArgumentException("Invalid Object Id definition for " + paramBeanDescription.getBeanClass().getName() + ": can not find property with name '" + str + "'");
          }
          BeanPropertyWriter localBeanPropertyWriter = (BeanPropertyWriter)paramList.get(i);
          if (str.equals(localBeanPropertyWriter.getName()))
          {
            if (i > 0)
            {
              paramList.remove(i);
              paramList.add(0, localBeanPropertyWriter);
            }
            JavaType localJavaType3 = localBeanPropertyWriter.getType();
            PropertyBasedObjectIdGenerator localPropertyBasedObjectIdGenerator = new PropertyBasedObjectIdGenerator(localObjectIdInfo, localBeanPropertyWriter);
            localObjectIdWriter = ObjectIdWriter.construct(localJavaType3, (PropertyName)null, localPropertyBasedObjectIdGenerator, localObjectIdInfo.getAlwaysAsId());
            break;
          }
          i++;
        }
      }
      JavaType localJavaType1 = paramSerializerProvider.constructType(localClass);
      JavaType localJavaType2 = paramSerializerProvider.getTypeFactory().findTypeParameters(localJavaType1, ObjectIdGenerator.class)[0];
      ObjectIdGenerator localObjectIdGenerator = paramSerializerProvider.objectIdGeneratorInstance(paramBeanDescription.getClassInfo(), localObjectIdInfo);
      localObjectIdWriter = ObjectIdWriter.construct(localJavaType2, localObjectIdInfo.getPropertyName(), localObjectIdGenerator, localObjectIdInfo.getAlwaysAsId());
    }
  }
  
  protected PropertyBuilder constructPropertyBuilder(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription)
  {
    return new PropertyBuilder(paramSerializationConfig, paramBeanDescription);
  }
  
  public JsonSerializer<Object> createSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType)
    throws JsonMappingException
  {
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    BeanDescription localBeanDescription = localSerializationConfig.introspect(paramJavaType);
    JsonSerializer localJsonSerializer = findSerializerFromAnnotation(paramSerializerProvider, localBeanDescription.getClassInfo());
    Object localObject;
    if (localJsonSerializer != null) {
      localObject = localJsonSerializer;
    }
    for (;;)
    {
      return (JsonSerializer<Object>)localObject;
      JavaType localJavaType1 = modifyTypeByAnnotation(localSerializationConfig, localBeanDescription.getClassInfo(), paramJavaType);
      boolean bool;
      if (localJavaType1 == paramJavaType) {
        bool = false;
      }
      Converter localConverter;
      for (;;)
      {
        localConverter = localBeanDescription.findSerializationConverter();
        if (localConverter != null) {
          break label112;
        }
        localObject = _createSerializer2(paramSerializerProvider, localJavaType1, localBeanDescription, bool);
        break;
        bool = true;
        if (!localJavaType1.hasRawClass(paramJavaType.getRawClass())) {
          localBeanDescription = localSerializationConfig.introspect(localJavaType1);
        }
      }
      label112:
      JavaType localJavaType2 = localConverter.getOutputType(paramSerializerProvider.getTypeFactory());
      if (!localJavaType2.hasRawClass(localJavaType1.getRawClass()))
      {
        localBeanDescription = localSerializationConfig.introspect(localJavaType2);
        localJsonSerializer = findSerializerFromAnnotation(paramSerializerProvider, localBeanDescription.getClassInfo());
      }
      if ((localJsonSerializer == null) && (!localJavaType2.isJavaLangObject())) {
        localJsonSerializer = _createSerializer2(paramSerializerProvider, localJavaType2, localBeanDescription, true);
      }
      localObject = new StdDelegatingSerializer(localConverter, localJavaType2, localJsonSerializer);
    }
  }
  
  protected Iterable<Serializers> customSerializers()
  {
    return this._factoryConfig.serializers();
  }
  
  protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, List<BeanPropertyWriter> paramList)
  {
    String[] arrayOfString = paramSerializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(paramBeanDescription.getClassInfo(), true);
    if ((arrayOfString != null) && (arrayOfString.length > 0))
    {
      HashSet localHashSet = ArrayBuilders.arrayToSet(arrayOfString);
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext()) {
        if (localHashSet.contains(((BeanPropertyWriter)localIterator.next()).getName())) {
          localIterator.remove();
        }
      }
    }
    return paramList;
  }
  
  protected List<BeanPropertyWriter> findBeanProperties(SerializerProvider paramSerializerProvider, BeanDescription paramBeanDescription, BeanSerializerBuilder paramBeanSerializerBuilder)
    throws JsonMappingException
  {
    List localList = paramBeanDescription.findProperties();
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    removeIgnorableTypes(localSerializationConfig, paramBeanDescription, localList);
    if (localSerializationConfig.isEnabled(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS)) {
      removeSetterlessGetters(localSerializationConfig, paramBeanDescription, localList);
    }
    Object localObject;
    if (localList.isEmpty()) {
      localObject = null;
    }
    for (;;)
    {
      return (List<BeanPropertyWriter>)localObject;
      boolean bool = usesStaticTyping(localSerializationConfig, paramBeanDescription, null);
      PropertyBuilder localPropertyBuilder = constructPropertyBuilder(localSerializationConfig, paramBeanDescription);
      localObject = new ArrayList(localList.size());
      TypeBindings localTypeBindings = paramBeanDescription.bindingsForBeanType();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator.next();
        AnnotatedMember localAnnotatedMember = localBeanPropertyDefinition.getAccessor();
        if (localBeanPropertyDefinition.isTypeId())
        {
          if (localAnnotatedMember != null)
          {
            if (localSerializationConfig.canOverrideAccessModifiers()) {
              localAnnotatedMember.fixAccess();
            }
            paramBeanSerializerBuilder.setTypeId(localAnnotatedMember);
          }
        }
        else
        {
          AnnotationIntrospector.ReferenceProperty localReferenceProperty = localBeanPropertyDefinition.findReferenceType();
          if ((localReferenceProperty == null) || (!localReferenceProperty.isBackReference())) {
            if ((localAnnotatedMember instanceof AnnotatedMethod)) {
              ((ArrayList)localObject).add(_constructWriter(paramSerializerProvider, localBeanPropertyDefinition, localTypeBindings, localPropertyBuilder, bool, (AnnotatedMethod)localAnnotatedMember));
            } else {
              ((ArrayList)localObject).add(_constructWriter(paramSerializerProvider, localBeanPropertyDefinition, localTypeBindings, localPropertyBuilder, bool, (AnnotatedField)localAnnotatedMember));
            }
          }
        }
      }
    }
  }
  
  public JsonSerializer<Object> findBeanSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if ((!isPotentialBeanType(paramJavaType.getRawClass())) && (!paramJavaType.isEnumType())) {}
    for (Object localObject = null;; localObject = constructBeanSerializer(paramSerializerProvider, paramBeanDescription)) {
      return (JsonSerializer<Object>)localObject;
    }
  }
  
  public TypeSerializer findPropertyContentTypeSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    JavaType localJavaType = paramJavaType.getContentType();
    TypeResolverBuilder localTypeResolverBuilder = paramSerializationConfig.getAnnotationIntrospector().findPropertyContentTypeResolver(paramSerializationConfig, paramAnnotatedMember, paramJavaType);
    if (localTypeResolverBuilder == null) {}
    for (TypeSerializer localTypeSerializer = createTypeSerializer(paramSerializationConfig, localJavaType);; localTypeSerializer = localTypeResolverBuilder.buildTypeSerializer(paramSerializationConfig, localJavaType, paramSerializationConfig.getSubtypeResolver().collectAndResolveSubtypesByClass(paramSerializationConfig, paramAnnotatedMember, localJavaType))) {
      return localTypeSerializer;
    }
  }
  
  public TypeSerializer findPropertyTypeSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    TypeResolverBuilder localTypeResolverBuilder = paramSerializationConfig.getAnnotationIntrospector().findPropertyTypeResolver(paramSerializationConfig, paramAnnotatedMember, paramJavaType);
    if (localTypeResolverBuilder == null) {}
    for (TypeSerializer localTypeSerializer = createTypeSerializer(paramSerializationConfig, paramJavaType);; localTypeSerializer = localTypeResolverBuilder.buildTypeSerializer(paramSerializationConfig, paramJavaType, paramSerializationConfig.getSubtypeResolver().collectAndResolveSubtypesByClass(paramSerializationConfig, paramAnnotatedMember, paramJavaType))) {
      return localTypeSerializer;
    }
  }
  
  protected boolean isPotentialBeanType(Class<?> paramClass)
  {
    if ((ClassUtil.canBeABeanType(paramClass) == null) && (!ClassUtil.isProxyType(paramClass))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected void processViews(SerializationConfig paramSerializationConfig, BeanSerializerBuilder paramBeanSerializerBuilder)
  {
    List localList = paramBeanSerializerBuilder.getProperties();
    boolean bool = paramSerializationConfig.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION);
    int i = localList.size();
    int j = 0;
    BeanPropertyWriter[] arrayOfBeanPropertyWriter = new BeanPropertyWriter[i];
    int k = 0;
    if (k < i)
    {
      BeanPropertyWriter localBeanPropertyWriter = (BeanPropertyWriter)localList.get(k);
      Class[] arrayOfClass = localBeanPropertyWriter.getViews();
      if (arrayOfClass == null) {
        if (bool) {
          arrayOfBeanPropertyWriter[k] = localBeanPropertyWriter;
        }
      }
      for (;;)
      {
        k++;
        break;
        j++;
        arrayOfBeanPropertyWriter[k] = constructFilteredBeanWriter(localBeanPropertyWriter, arrayOfClass);
      }
    }
    if ((bool) && (j == 0)) {}
    for (;;)
    {
      return;
      paramBeanSerializerBuilder.setFilteredProperties(arrayOfBeanPropertyWriter);
    }
  }
  
  protected void removeIgnorableTypes(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, List<BeanPropertyDefinition> paramList)
  {
    AnnotationIntrospector localAnnotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMember localAnnotatedMember = ((BeanPropertyDefinition)localIterator.next()).getAccessor();
      if (localAnnotatedMember == null)
      {
        localIterator.remove();
      }
      else
      {
        Class localClass = localAnnotatedMember.getRawType();
        Boolean localBoolean = (Boolean)localHashMap.get(localClass);
        if (localBoolean == null)
        {
          localBoolean = localAnnotationIntrospector.isIgnorableType(paramSerializationConfig.introspectClassAnnotations(localClass).getClassInfo());
          if (localBoolean == null) {
            localBoolean = Boolean.FALSE;
          }
          localHashMap.put(localClass, localBoolean);
        }
        if (localBoolean.booleanValue()) {
          localIterator.remove();
        }
      }
    }
  }
  
  protected List<BeanPropertyWriter> removeOverlappingTypeIds(SerializerProvider paramSerializerProvider, BeanDescription paramBeanDescription, BeanSerializerBuilder paramBeanSerializerBuilder, List<BeanPropertyWriter> paramList)
  {
    int i = 0;
    int j = paramList.size();
    if (i < j)
    {
      BeanPropertyWriter localBeanPropertyWriter1 = (BeanPropertyWriter)paramList.get(i);
      TypeSerializer localTypeSerializer = localBeanPropertyWriter1.getTypeSerializer();
      if ((localTypeSerializer == null) || (localTypeSerializer.getTypeInclusion() != JsonTypeInfo.As.EXTERNAL_PROPERTY)) {
        label55:
        i++;
      }
      for (;;)
      {
        break;
        PropertyName localPropertyName = PropertyName.construct(localTypeSerializer.getPropertyName());
        Iterator localIterator = paramList.iterator();
        if (localIterator.hasNext())
        {
          BeanPropertyWriter localBeanPropertyWriter2 = (BeanPropertyWriter)localIterator.next();
          if ((localBeanPropertyWriter2 == localBeanPropertyWriter1) || (!localBeanPropertyWriter2.wouldConflictWithName(localPropertyName))) {
            break label55;
          }
          localBeanPropertyWriter1.assignTypeSerializer(null);
        }
      }
    }
    return paramList;
  }
  
  protected void removeSetterlessGetters(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, List<BeanPropertyDefinition> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator.next();
      if ((!localBeanPropertyDefinition.couldDeserialize()) && (!localBeanPropertyDefinition.isExplicitlyIncluded())) {
        localIterator.remove();
      }
    }
  }
  
  public SerializerFactory withConfig(SerializerFactoryConfig paramSerializerFactoryConfig)
  {
    if (this._factoryConfig == paramSerializerFactoryConfig) {}
    for (;;)
    {
      return this;
      if (getClass() != BeanSerializerFactory.class) {
        throw new IllegalStateException("Subtype of BeanSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with " + "additional serializer definitions");
      }
      this = new BeanSerializerFactory(paramSerializerFactoryConfig);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/BeanSerializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */