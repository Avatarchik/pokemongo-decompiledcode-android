package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicDeserializerFactory
  extends DeserializerFactory
  implements Serializable
{
  private static final Class<?> CLASS_CHAR_BUFFER;
  private static final Class<?> CLASS_ITERABLE;
  private static final Class<?> CLASS_MAP_ENTRY;
  private static final Class<?> CLASS_OBJECT = Object.class;
  private static final Class<?> CLASS_STRING = String.class;
  protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME;
  static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
  static final HashMap<String, Class<? extends Map>> _mapFallbacks;
  protected final DeserializerFactoryConfig _factoryConfig;
  
  static
  {
    CLASS_CHAR_BUFFER = CharSequence.class;
    CLASS_ITERABLE = Iterable.class;
    CLASS_MAP_ENTRY = Map.Entry.class;
    UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    _mapFallbacks = new HashMap();
    _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
    _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
    _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
    _mapFallbacks.put(NavigableMap.class.getName(), TreeMap.class);
    _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
    _collectionFallbacks = new HashMap();
    _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
    _collectionFallbacks.put(List.class.getName(), ArrayList.class);
    _collectionFallbacks.put(Set.class.getName(), HashSet.class);
    _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
    _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
    _collectionFallbacks.put("java.util.Deque", LinkedList.class);
    _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
  }
  
  protected BasicDeserializerFactory(DeserializerFactoryConfig paramDeserializerFactoryConfig)
  {
    this._factoryConfig = paramDeserializerFactoryConfig;
  }
  
  private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Class localClass = paramJavaType.getRawClass();
    BeanDescription localBeanDescription = localDeserializationConfig.introspect(paramJavaType);
    KeyDeserializer localKeyDeserializer = findKeyDeserializerFromAnnotation(paramDeserializationContext, localBeanDescription.getClassInfo());
    if (localKeyDeserializer != null) {}
    for (;;)
    {
      return localKeyDeserializer;
      JsonDeserializer localJsonDeserializer1 = _findCustomEnumDeserializer(localClass, localDeserializationConfig, localBeanDescription);
      if (localJsonDeserializer1 != null)
      {
        localKeyDeserializer = StdKeyDeserializers.constructDelegatingKeyDeserializer(localDeserializationConfig, paramJavaType, localJsonDeserializer1);
      }
      else
      {
        JsonDeserializer localJsonDeserializer2 = findDeserializerFromAnnotation(paramDeserializationContext, localBeanDescription.getClassInfo());
        if (localJsonDeserializer2 != null)
        {
          localKeyDeserializer = StdKeyDeserializers.constructDelegatingKeyDeserializer(localDeserializationConfig, paramJavaType, localJsonDeserializer2);
        }
        else
        {
          EnumResolver localEnumResolver = constructEnumResolver(localClass, localDeserializationConfig, localBeanDescription.findJsonValueMethod());
          Iterator localIterator = localBeanDescription.getFactoryMethods().iterator();
          for (;;)
          {
            if (localIterator.hasNext())
            {
              AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
              if (localDeserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation(localAnnotatedMethod))
              {
                if ((localAnnotatedMethod.getParameterCount() == 1) && (localAnnotatedMethod.getRawReturnType().isAssignableFrom(localClass)))
                {
                  if (localAnnotatedMethod.getGenericParameterType(0) != String.class) {
                    throw new IllegalArgumentException("Parameter #0 type for factory method (" + localAnnotatedMethod + ") not suitable, must be java.lang.String");
                  }
                  if (localDeserializationConfig.canOverrideAccessModifiers()) {
                    ClassUtil.checkAndFixAccess(localAnnotatedMethod.getMember());
                  }
                  localKeyDeserializer = StdKeyDeserializers.constructEnumKeyDeserializer(localEnumResolver, localAnnotatedMethod);
                  break;
                }
                throw new IllegalArgumentException("Unsuitable method (" + localAnnotatedMethod + ") decorated with @JsonCreator (for Enum type " + localClass.getName() + ")");
              }
            }
          }
          localKeyDeserializer = StdKeyDeserializers.constructEnumKeyDeserializer(localEnumResolver);
        }
      }
    }
  }
  
  private ValueInstantiator _findStdValueInstantiator(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    if (paramBeanDescription.getBeanClass() == JsonLocation.class) {}
    for (JsonLocationInstantiator localJsonLocationInstantiator = new JsonLocationInstantiator();; localJsonLocationInstantiator = null) {
      return localJsonLocationInstantiator;
    }
  }
  
  private JavaType _mapAbstractType2(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    Class localClass = paramJavaType.getRawClass();
    JavaType localJavaType;
    if (this._factoryConfig.hasAbstractTypeResolvers())
    {
      Iterator localIterator = this._factoryConfig.abstractTypeResolvers().iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        localJavaType = ((AbstractTypeResolver)localIterator.next()).findTypeMapping(paramDeserializationConfig, paramJavaType);
      } while ((localJavaType == null) || (localJavaType.getRawClass() == localClass));
    }
    for (;;)
    {
      return localJavaType;
      localJavaType = null;
    }
  }
  
  protected void _addDeserializerConstructors(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, Map<AnnotatedWithParams, BeanPropertyDefinition[]> paramMap)
    throws JsonMappingException
  {
    AnnotatedConstructor localAnnotatedConstructor1 = paramBeanDescription.findDefaultConstructor();
    if ((localAnnotatedConstructor1 != null) && ((!paramCreatorCollector.hasDefaultCreator()) || (paramAnnotationIntrospector.hasCreatorAnnotation(localAnnotatedConstructor1)))) {
      paramCreatorCollector.setDefaultCreator(localAnnotatedConstructor1);
    }
    LinkedList localLinkedList = null;
    Iterator localIterator = paramBeanDescription.getConstructors().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedConstructor localAnnotatedConstructor2 = (AnnotatedConstructor)localIterator.next();
      boolean bool = paramAnnotationIntrospector.hasCreatorAnnotation(localAnnotatedConstructor2);
      BeanPropertyDefinition[] arrayOfBeanPropertyDefinition = (BeanPropertyDefinition[])paramMap.get(localAnnotatedConstructor2);
      int i = localAnnotatedConstructor2.getParameterCount();
      if (i == 1)
      {
        BeanPropertyDefinition localBeanPropertyDefinition2;
        label116:
        SettableBeanProperty[] arrayOfSettableBeanProperty2;
        if (arrayOfBeanPropertyDefinition == null)
        {
          localBeanPropertyDefinition2 = null;
          if (!_checkIfCreatorPropertyBased(paramAnnotationIntrospector, localAnnotatedConstructor2, localBeanPropertyDefinition2)) {
            break label206;
          }
          arrayOfSettableBeanProperty2 = new SettableBeanProperty[1];
          if (localBeanPropertyDefinition2 != null) {
            break label196;
          }
        }
        label196:
        for (PropertyName localPropertyName3 = null;; localPropertyName3 = localBeanPropertyDefinition2.getFullName())
        {
          AnnotatedParameter localAnnotatedParameter2 = localAnnotatedConstructor2.getParameter(0);
          arrayOfSettableBeanProperty2[0] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName3, 0, localAnnotatedParameter2, paramAnnotationIntrospector.findInjectableValueId(localAnnotatedParameter2));
          paramCreatorCollector.addPropertyCreator(localAnnotatedConstructor2, bool, arrayOfSettableBeanProperty2);
          break;
          localBeanPropertyDefinition2 = arrayOfBeanPropertyDefinition[0];
          break label116;
        }
        label206:
        _handleSingleArgumentConstructor(paramDeserializationContext, paramBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, localAnnotatedConstructor2, bool, paramVisibilityChecker.isCreatorVisible(localAnnotatedConstructor2));
        if (localBeanPropertyDefinition2 != null) {
          ((POJOPropertyBuilder)localBeanPropertyDefinition2).removeConstructors();
        }
      }
      else
      {
        Object localObject1 = null;
        SettableBeanProperty[] arrayOfSettableBeanProperty1 = new SettableBeanProperty[i];
        int j = 0;
        int k = 0;
        int m = 0;
        int n = 0;
        if (n < i)
        {
          AnnotatedParameter localAnnotatedParameter1 = localAnnotatedConstructor2.getParameter(n);
          BeanPropertyDefinition localBeanPropertyDefinition1;
          label292:
          Object localObject2;
          PropertyName localPropertyName2;
          if (arrayOfBeanPropertyDefinition == null)
          {
            localBeanPropertyDefinition1 = null;
            localObject2 = paramAnnotationIntrospector.findInjectableValueId(localAnnotatedParameter1);
            if (localBeanPropertyDefinition1 != null) {
              break label360;
            }
            localPropertyName2 = null;
            label309:
            if ((localBeanPropertyDefinition1 == null) || (!localBeanPropertyDefinition1.isExplicitlyNamed())) {
              break label370;
            }
            j++;
            arrayOfSettableBeanProperty1[n] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName2, n, localAnnotatedParameter1, localObject2);
          }
          for (;;)
          {
            n++;
            break;
            localBeanPropertyDefinition1 = arrayOfBeanPropertyDefinition[n];
            break label292;
            label360:
            localPropertyName2 = localBeanPropertyDefinition1.getFullName();
            break label309;
            label370:
            if (localObject2 != null)
            {
              m++;
              arrayOfSettableBeanProperty1[n] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName2, n, localAnnotatedParameter1, localObject2);
            }
            else if (paramAnnotationIntrospector.findUnwrappingNameTransformer(localAnnotatedParameter1) != null)
            {
              arrayOfSettableBeanProperty1[n] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, UNWRAPPED_CREATOR_PARAM_NAME, n, localAnnotatedParameter1, null);
              j++;
            }
            else if ((bool) && (localPropertyName2 != null) && (!localPropertyName2.isEmpty()))
            {
              k++;
              arrayOfSettableBeanProperty1[n] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName2, n, localAnnotatedParameter1, localObject2);
            }
            else if (localObject1 == null)
            {
              localObject1 = localAnnotatedParameter1;
            }
          }
        }
        int i1 = j + k;
        if ((bool) || (j > 0) || (m > 0))
        {
          if (i1 + m == i)
          {
            paramCreatorCollector.addPropertyCreator(localAnnotatedConstructor2, bool, arrayOfSettableBeanProperty1);
            continue;
          }
          if ((j == 0) && (m + 1 == i))
          {
            paramCreatorCollector.addDelegatingCreator(localAnnotatedConstructor2, bool, arrayOfSettableBeanProperty1);
            continue;
          }
          PropertyName localPropertyName1 = _findImplicitParamName((AnnotatedParameter)localObject1, paramAnnotationIntrospector);
          if ((localPropertyName1 == null) || (localPropertyName1.isEmpty()))
          {
            int i2 = ((AnnotatedParameter)localObject1).getIndex();
            if ((i2 == 0) && (ClassUtil.isNonStaticInnerClass(localAnnotatedConstructor2.getDeclaringClass()))) {
              throw new IllegalArgumentException("Non-static inner classes like " + localAnnotatedConstructor2.getDeclaringClass().getName() + " can not use @JsonCreator for constructors");
            }
            throw new IllegalArgumentException("Argument #" + i2 + " of constructor " + localAnnotatedConstructor2 + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
          }
        }
        if (!paramCreatorCollector.hasDefaultCreator())
        {
          if (localLinkedList == null) {
            localLinkedList = new LinkedList();
          }
          localLinkedList.add(localAnnotatedConstructor2);
        }
      }
    }
    if ((localLinkedList != null) && (!paramCreatorCollector.hasDelegatingCreator()) && (!paramCreatorCollector.hasPropertyBasedCreator())) {
      _checkImplicitlyNamedConstructors(paramDeserializationContext, paramBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, localLinkedList);
    }
  }
  
  protected void _addDeserializerFactoryMethods(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, Map<AnnotatedWithParams, BeanPropertyDefinition[]> paramMap)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Iterator localIterator = paramBeanDescription.getFactoryMethods().iterator();
    while (localIterator.hasNext())
    {
      AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)localIterator.next();
      boolean bool = paramAnnotationIntrospector.hasCreatorAnnotation(localAnnotatedMethod);
      int i = localAnnotatedMethod.getParameterCount();
      if (i == 0)
      {
        if (bool) {
          paramCreatorCollector.setDefaultCreator(localAnnotatedMethod);
        }
      }
      else
      {
        BeanPropertyDefinition[] arrayOfBeanPropertyDefinition = (BeanPropertyDefinition[])paramMap.get(localAnnotatedMethod);
        if (i == 1)
        {
          if (arrayOfBeanPropertyDefinition == null) {}
          for (BeanPropertyDefinition localBeanPropertyDefinition2 = null;; localBeanPropertyDefinition2 = arrayOfBeanPropertyDefinition[0])
          {
            if (_checkIfCreatorPropertyBased(paramAnnotationIntrospector, localAnnotatedMethod, localBeanPropertyDefinition2)) {
              break label150;
            }
            _handleSingleArgumentFactory(localDeserializationConfig, paramBeanDescription, paramVisibilityChecker, paramAnnotationIntrospector, paramCreatorCollector, localAnnotatedMethod, bool);
            break;
          }
        }
        if (bool)
        {
          label150:
          Object localObject1 = null;
          SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[i];
          int j = 0;
          int k = 0;
          int m = 0;
          int n = 0;
          if (n < i)
          {
            AnnotatedParameter localAnnotatedParameter = localAnnotatedMethod.getParameter(n);
            BeanPropertyDefinition localBeanPropertyDefinition1;
            label196:
            Object localObject2;
            PropertyName localPropertyName;
            if (arrayOfBeanPropertyDefinition == null)
            {
              localBeanPropertyDefinition1 = null;
              localObject2 = paramAnnotationIntrospector.findInjectableValueId(localAnnotatedParameter);
              if (localBeanPropertyDefinition1 != null) {
                break label264;
              }
              localPropertyName = null;
              label213:
              if ((localBeanPropertyDefinition1 == null) || (!localBeanPropertyDefinition1.isExplicitlyNamed())) {
                break label274;
              }
              k++;
              arrayOfSettableBeanProperty[n] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, n, localAnnotatedParameter, localObject2);
            }
            for (;;)
            {
              n++;
              break;
              localBeanPropertyDefinition1 = arrayOfBeanPropertyDefinition[n];
              break label196;
              label264:
              localPropertyName = localBeanPropertyDefinition1.getFullName();
              break label213;
              label274:
              if (localObject2 != null)
              {
                m++;
                arrayOfSettableBeanProperty[n] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, n, localAnnotatedParameter, localObject2);
              }
              else if (paramAnnotationIntrospector.findUnwrappingNameTransformer(localAnnotatedParameter) != null)
              {
                arrayOfSettableBeanProperty[n] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, UNWRAPPED_CREATOR_PARAM_NAME, n, localAnnotatedParameter, null);
                j++;
              }
              else if ((bool) && (localPropertyName != null) && (!localPropertyName.isEmpty()))
              {
                j++;
                arrayOfSettableBeanProperty[n] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName, n, localAnnotatedParameter, localObject2);
              }
              else if (localObject1 == null)
              {
                localObject1 = localAnnotatedParameter;
              }
            }
          }
          int i1 = k + j;
          if ((bool) || (k > 0) || (m > 0)) {
            if (i1 + m == i) {
              paramCreatorCollector.addPropertyCreator(localAnnotatedMethod, bool, arrayOfSettableBeanProperty);
            } else if ((k == 0) && (m + 1 == i)) {
              paramCreatorCollector.addDelegatingCreator(localAnnotatedMethod, bool, arrayOfSettableBeanProperty);
            } else {
              throw new IllegalArgumentException("Argument #" + ((AnnotatedParameter)localObject1).getIndex() + " of factory method " + localAnnotatedMethod + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
            }
          }
        }
      }
    }
  }
  
  protected boolean _checkIfCreatorPropertyBased(AnnotationIntrospector paramAnnotationIntrospector, AnnotatedWithParams paramAnnotatedWithParams, BeanPropertyDefinition paramBeanPropertyDefinition)
  {
    boolean bool = true;
    JsonCreator.Mode localMode = paramAnnotationIntrospector.findCreatorBinding(paramAnnotatedWithParams);
    if (localMode == JsonCreator.Mode.PROPERTIES) {}
    for (;;)
    {
      return bool;
      if (localMode == JsonCreator.Mode.DELEGATING) {
        bool = false;
      } else if (((paramBeanPropertyDefinition == null) || (!paramBeanPropertyDefinition.isExplicitlyNamed())) && (paramAnnotationIntrospector.findInjectableValueId(paramAnnotatedWithParams.getParameter(0)) == null)) {
        if (paramBeanPropertyDefinition != null)
        {
          String str = paramBeanPropertyDefinition.getName();
          if ((str != null) && (!str.isEmpty()) && (paramBeanPropertyDefinition.couldSerialize())) {}
        }
        else
        {
          bool = false;
        }
      }
    }
  }
  
  protected void _checkImplicitlyNamedConstructors(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, List<AnnotatedConstructor> paramList)
    throws JsonMappingException
  {
    Object localObject1 = null;
    Object localObject2 = null;
    Iterator localIterator = paramList.iterator();
    for (;;)
    {
      AnnotatedConstructor localAnnotatedConstructor;
      SettableBeanProperty[] arrayOfSettableBeanProperty;
      if (localIterator.hasNext())
      {
        localAnnotatedConstructor = (AnnotatedConstructor)localIterator.next();
        if (!paramVisibilityChecker.isCreatorVisible(localAnnotatedConstructor)) {
          continue;
        }
        int k = localAnnotatedConstructor.getParameterCount();
        arrayOfSettableBeanProperty = new SettableBeanProperty[k];
        for (int m = 0;; m++)
        {
          if (m >= k) {
            break label131;
          }
          AnnotatedParameter localAnnotatedParameter = localAnnotatedConstructor.getParameter(m);
          PropertyName localPropertyName2 = _findParamName(localAnnotatedParameter, paramAnnotationIntrospector);
          if ((localPropertyName2 == null) || (localPropertyName2.isEmpty())) {
            break;
          }
          arrayOfSettableBeanProperty[m] = constructCreatorProperty(paramDeserializationContext, paramBeanDescription, localPropertyName2, localAnnotatedParameter.getIndex(), localAnnotatedParameter, null);
        }
        label131:
        if (localObject1 != null) {
          localObject1 = null;
        }
      }
      else
      {
        if (localObject1 == null) {
          break;
        }
        paramCreatorCollector.addPropertyCreator((AnnotatedWithParams)localObject1, false, (SettableBeanProperty[])localObject2);
        BasicBeanDescription localBasicBeanDescription = (BasicBeanDescription)paramBeanDescription;
        for (Object localObject4 : localObject2)
        {
          PropertyName localPropertyName1 = ((SettableBeanProperty)localObject4).getFullName();
          if (!localBasicBeanDescription.hasProperty(localPropertyName1)) {
            localBasicBeanDescription.addProperty(SimpleBeanPropertyDefinition.construct(paramDeserializationContext.getConfig(), ((SettableBeanProperty)localObject4).getMember(), localPropertyName1));
          }
        }
      }
      localObject1 = localAnnotatedConstructor;
      localObject2 = arrayOfSettableBeanProperty;
    }
  }
  
  protected ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    CreatorCollector localCreatorCollector = new CreatorCollector(paramBeanDescription, paramDeserializationContext.canOverrideAccessModifiers());
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    VisibilityChecker localVisibilityChecker1 = localDeserializationConfig.getDefaultVisibilityChecker();
    VisibilityChecker localVisibilityChecker2 = localAnnotationIntrospector.findAutoDetectVisibility(paramBeanDescription.getClassInfo(), localVisibilityChecker1);
    Map localMap = _findCreatorsFromProperties(paramDeserializationContext, paramBeanDescription);
    _addDeserializerFactoryMethods(paramDeserializationContext, paramBeanDescription, localVisibilityChecker2, localAnnotationIntrospector, localCreatorCollector, localMap);
    if (paramBeanDescription.getType().isConcrete()) {
      _addDeserializerConstructors(paramDeserializationContext, paramBeanDescription, localVisibilityChecker2, localAnnotationIntrospector, localCreatorCollector, localMap);
    }
    return localCreatorCollector.constructValueInstantiator(localDeserializationConfig);
  }
  
  protected Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject = Collections.emptyMap();
    Iterator localIterator1 = paramBeanDescription.findProperties().iterator();
    if (localIterator1.hasNext())
    {
      BeanPropertyDefinition localBeanPropertyDefinition = (BeanPropertyDefinition)localIterator1.next();
      Iterator localIterator2 = localBeanPropertyDefinition.getConstructorParameters();
      label44:
      AnnotatedWithParams localAnnotatedWithParams;
      BeanPropertyDefinition[] arrayOfBeanPropertyDefinition;
      int i;
      if (localIterator2.hasNext())
      {
        AnnotatedParameter localAnnotatedParameter = (AnnotatedParameter)localIterator2.next();
        localAnnotatedWithParams = localAnnotatedParameter.getOwner();
        arrayOfBeanPropertyDefinition = (BeanPropertyDefinition[])((Map)localObject).get(localAnnotatedWithParams);
        i = localAnnotatedParameter.getIndex();
        if (arrayOfBeanPropertyDefinition != null) {
          break label146;
        }
        if (((Map)localObject).isEmpty()) {
          localObject = new LinkedHashMap();
        }
        arrayOfBeanPropertyDefinition = new BeanPropertyDefinition[localAnnotatedWithParams.getParameterCount()];
        ((Map)localObject).put(localAnnotatedWithParams, arrayOfBeanPropertyDefinition);
      }
      label146:
      while (arrayOfBeanPropertyDefinition[i] == null)
      {
        arrayOfBeanPropertyDefinition[i] = localBeanPropertyDefinition;
        break label44;
        break;
      }
      throw new IllegalStateException("Conflict: parameter #" + i + " of " + localAnnotatedWithParams + " bound to more than one property; " + arrayOfBeanPropertyDefinition[i] + " vs " + localBeanPropertyDefinition);
    }
    return (Map<AnnotatedWithParams, BeanPropertyDefinition[]>)localObject;
  }
  
  protected JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType paramArrayType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    JsonDeserializer localJsonDeserializer;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonDeserializer = ((Deserializers)localIterator.next()).findArrayDeserializer(paramArrayType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
    } while (localJsonDeserializer == null);
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = null;
    }
  }
  
  protected JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    JsonDeserializer localJsonDeserializer;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonDeserializer = ((Deserializers)localIterator.next()).findBeanDeserializer(paramJavaType, paramDeserializationConfig, paramBeanDescription);
    } while (localJsonDeserializer == null);
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = null;
    }
  }
  
  protected JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType paramCollectionType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    JsonDeserializer localJsonDeserializer;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonDeserializer = ((Deserializers)localIterator.next()).findCollectionDeserializer(paramCollectionType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
    } while (localJsonDeserializer == null);
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = null;
    }
  }
  
  protected JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType paramCollectionLikeType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    JsonDeserializer localJsonDeserializer;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonDeserializer = ((Deserializers)localIterator.next()).findCollectionLikeDeserializer(paramCollectionLikeType, paramDeserializationConfig, paramBeanDescription, paramTypeDeserializer, paramJsonDeserializer);
    } while (localJsonDeserializer == null);
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = null;
    }
  }
  
  protected JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    JsonDeserializer localJsonDeserializer;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonDeserializer = ((Deserializers)localIterator.next()).findEnumDeserializer(paramClass, paramDeserializationConfig, paramBeanDescription);
    } while (localJsonDeserializer == null);
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = null;
    }
  }
  
  protected JsonDeserializer<?> _findCustomMapDeserializer(MapType paramMapType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    JsonDeserializer localJsonDeserializer;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonDeserializer = ((Deserializers)localIterator.next()).findMapDeserializer(paramMapType, paramDeserializationConfig, paramBeanDescription, paramKeyDeserializer, paramTypeDeserializer, paramJsonDeserializer);
    } while (localJsonDeserializer == null);
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = null;
    }
  }
  
  protected JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType paramMapLikeType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    JsonDeserializer localJsonDeserializer;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonDeserializer = ((Deserializers)localIterator.next()).findMapLikeDeserializer(paramMapLikeType, paramDeserializationConfig, paramBeanDescription, paramKeyDeserializer, paramTypeDeserializer, paramJsonDeserializer);
    } while (localJsonDeserializer == null);
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = null;
    }
  }
  
  protected JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Iterator localIterator = this._factoryConfig.deserializers().iterator();
    JsonDeserializer localJsonDeserializer;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localJsonDeserializer = ((Deserializers)localIterator.next()).findTreeNodeDeserializer(paramClass, paramDeserializationConfig, paramBeanDescription);
    } while (localJsonDeserializer == null);
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = null;
    }
  }
  
  @Deprecated
  protected PropertyName _findExplicitParamName(AnnotatedParameter paramAnnotatedParameter, AnnotationIntrospector paramAnnotationIntrospector)
  {
    if ((paramAnnotatedParameter != null) && (paramAnnotationIntrospector != null)) {}
    for (PropertyName localPropertyName = paramAnnotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);; localPropertyName = null) {
      return localPropertyName;
    }
  }
  
  protected PropertyName _findImplicitParamName(AnnotatedParameter paramAnnotatedParameter, AnnotationIntrospector paramAnnotationIntrospector)
  {
    String str = paramAnnotationIntrospector.findImplicitPropertyName(paramAnnotatedParameter);
    if ((str != null) && (!str.isEmpty())) {}
    for (PropertyName localPropertyName = PropertyName.construct(str);; localPropertyName = null) {
      return localPropertyName;
    }
  }
  
  protected AnnotatedMethod _findJsonValueFor(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (AnnotatedMethod localAnnotatedMethod = null;; localAnnotatedMethod = paramDeserializationConfig.introspect(paramJavaType).findJsonValueMethod()) {
      return localAnnotatedMethod;
    }
  }
  
  protected PropertyName _findParamName(AnnotatedParameter paramAnnotatedParameter, AnnotationIntrospector paramAnnotationIntrospector)
  {
    PropertyName localPropertyName;
    if ((paramAnnotatedParameter != null) && (paramAnnotationIntrospector != null))
    {
      localPropertyName = paramAnnotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);
      if (localPropertyName == null) {}
    }
    for (;;)
    {
      return localPropertyName;
      String str = paramAnnotationIntrospector.findImplicitPropertyName(paramAnnotatedParameter);
      if ((str != null) && (!str.isEmpty())) {
        localPropertyName = PropertyName.construct(str);
      } else {
        localPropertyName = null;
      }
    }
  }
  
  protected JavaType _findRemappedType(DeserializationConfig paramDeserializationConfig, Class<?> paramClass)
    throws JsonMappingException
  {
    JavaType localJavaType = mapAbstractType(paramDeserializationConfig, paramDeserializationConfig.constructType(paramClass));
    if ((localJavaType == null) || (localJavaType.hasRawClass(paramClass))) {
      localJavaType = null;
    }
    return localJavaType;
  }
  
  protected boolean _handleSingleArgumentConstructor(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, AnnotatedConstructor paramAnnotatedConstructor, boolean paramBoolean1, boolean paramBoolean2)
    throws JsonMappingException
  {
    boolean bool = true;
    Class localClass = paramAnnotatedConstructor.getRawParameterType(0);
    if ((localClass == String.class) || (localClass == CharSequence.class)) {
      if ((paramBoolean1) || (paramBoolean2)) {
        paramCreatorCollector.addStringCreator(paramAnnotatedConstructor, paramBoolean1);
      }
    }
    for (;;)
    {
      return bool;
      if ((localClass == Integer.TYPE) || (localClass == Integer.class))
      {
        if ((paramBoolean1) || (paramBoolean2)) {
          paramCreatorCollector.addIntCreator(paramAnnotatedConstructor, paramBoolean1);
        }
      }
      else if ((localClass == Long.TYPE) || (localClass == Long.class))
      {
        if ((paramBoolean1) || (paramBoolean2)) {
          paramCreatorCollector.addLongCreator(paramAnnotatedConstructor, paramBoolean1);
        }
      }
      else if ((localClass == Double.TYPE) || (localClass == Double.class))
      {
        if ((paramBoolean1) || (paramBoolean2)) {
          paramCreatorCollector.addDoubleCreator(paramAnnotatedConstructor, paramBoolean1);
        }
      }
      else if ((localClass == Boolean.TYPE) || (localClass == Boolean.class))
      {
        if ((paramBoolean1) || (paramBoolean2)) {
          paramCreatorCollector.addBooleanCreator(paramAnnotatedConstructor, paramBoolean1);
        }
      }
      else if (paramBoolean1) {
        paramCreatorCollector.addDelegatingCreator(paramAnnotatedConstructor, paramBoolean1, null);
      } else {
        bool = false;
      }
    }
  }
  
  protected boolean _handleSingleArgumentFactory(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, VisibilityChecker<?> paramVisibilityChecker, AnnotationIntrospector paramAnnotationIntrospector, CreatorCollector paramCreatorCollector, AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean)
    throws JsonMappingException
  {
    boolean bool = true;
    Class localClass = paramAnnotatedMethod.getRawParameterType(0);
    if ((localClass == String.class) || (localClass == CharSequence.class)) {
      if ((paramBoolean) || (paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod))) {
        paramCreatorCollector.addStringCreator(paramAnnotatedMethod, paramBoolean);
      }
    }
    for (;;)
    {
      return bool;
      if ((localClass == Integer.TYPE) || (localClass == Integer.class))
      {
        if ((paramBoolean) || (paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod))) {
          paramCreatorCollector.addIntCreator(paramAnnotatedMethod, paramBoolean);
        }
      }
      else if ((localClass == Long.TYPE) || (localClass == Long.class))
      {
        if ((paramBoolean) || (paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod))) {
          paramCreatorCollector.addLongCreator(paramAnnotatedMethod, paramBoolean);
        }
      }
      else if ((localClass == Double.TYPE) || (localClass == Double.class))
      {
        if ((paramBoolean) || (paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod))) {
          paramCreatorCollector.addDoubleCreator(paramAnnotatedMethod, paramBoolean);
        }
      }
      else if ((localClass == Boolean.TYPE) || (localClass == Boolean.class))
      {
        if ((paramBoolean) || (paramVisibilityChecker.isCreatorVisible(paramAnnotatedMethod))) {
          paramCreatorCollector.addBooleanCreator(paramAnnotatedMethod, paramBoolean);
        }
      }
      else if (paramBoolean) {
        paramCreatorCollector.addDelegatingCreator(paramAnnotatedMethod, paramBoolean, null);
      } else {
        bool = false;
      }
    }
  }
  
  @Deprecated
  protected boolean _hasExplicitParamName(AnnotatedParameter paramAnnotatedParameter, AnnotationIntrospector paramAnnotationIntrospector)
  {
    boolean bool = false;
    if ((paramAnnotatedParameter != null) && (paramAnnotationIntrospector != null))
    {
      PropertyName localPropertyName = paramAnnotationIntrospector.findNameForDeserialization(paramAnnotatedParameter);
      if ((localPropertyName != null) && (localPropertyName.hasSimpleName())) {
        bool = true;
      }
    }
    return bool;
  }
  
  protected CollectionType _mapAbstractCollectionType(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig)
  {
    Class localClass1 = paramJavaType.getRawClass();
    Class localClass2 = (Class)_collectionFallbacks.get(localClass1.getName());
    if (localClass2 == null) {}
    for (CollectionType localCollectionType = null;; localCollectionType = (CollectionType)paramDeserializationConfig.constructSpecializedType(paramJavaType, localClass2)) {
      return localCollectionType;
    }
  }
  
  public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig paramDeserializationConfig, Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    for (;;)
    {
      return (ValueInstantiator)localObject;
      if ((paramObject instanceof ValueInstantiator))
      {
        localObject = (ValueInstantiator)paramObject;
      }
      else
      {
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + paramObject.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
        }
        Class localClass = (Class)paramObject;
        if (ClassUtil.isBogusClass(localClass))
        {
          localObject = null;
        }
        else
        {
          if (!ValueInstantiator.class.isAssignableFrom(localClass)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<ValueInstantiator>");
          }
          HandlerInstantiator localHandlerInstantiator = paramDeserializationConfig.getHandlerInstantiator();
          if (localHandlerInstantiator != null)
          {
            ValueInstantiator localValueInstantiator = localHandlerInstantiator.valueInstantiatorInstance(paramDeserializationConfig, paramAnnotated, localClass);
            if (localValueInstantiator != null)
            {
              localObject = localValueInstantiator;
              continue;
            }
          }
          localObject = (ValueInstantiator)ClassUtil.createInstance(localClass, paramDeserializationConfig.canOverrideAccessModifiers());
        }
      }
    }
  }
  
  protected SettableBeanProperty constructCreatorProperty(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, PropertyName paramPropertyName, int paramInt, AnnotatedParameter paramAnnotatedParameter, Object paramObject)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    PropertyMetadata localPropertyMetadata;
    if (localAnnotationIntrospector == null)
    {
      localPropertyMetadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
      JavaType localJavaType1 = localDeserializationConfig.getTypeFactory().constructType(paramAnnotatedParameter.getParameterType(), paramBeanDescription.bindingsForBeanType());
      BeanProperty.Std localStd = new BeanProperty.Std(paramPropertyName, localJavaType1, localAnnotationIntrospector.findWrapperName(paramAnnotatedParameter), paramBeanDescription.getClassAnnotations(), paramAnnotatedParameter, localPropertyMetadata);
      JavaType localJavaType2 = resolveType(paramDeserializationContext, paramBeanDescription, localJavaType1, paramAnnotatedParameter);
      if (localJavaType2 != localJavaType1) {
        localStd = localStd.withType(localJavaType2);
      }
      JsonDeserializer localJsonDeserializer = findDeserializerFromAnnotation(paramDeserializationContext, paramAnnotatedParameter);
      JavaType localJavaType3 = modifyTypeByAnnotation(paramDeserializationContext, paramAnnotatedParameter, localJavaType2);
      TypeDeserializer localTypeDeserializer = (TypeDeserializer)localJavaType3.getTypeHandler();
      if (localTypeDeserializer == null) {
        localTypeDeserializer = findTypeDeserializer(localDeserializationConfig, localJavaType3);
      }
      Object localObject = new CreatorProperty(paramPropertyName, localJavaType3, localStd.getWrapperName(), localTypeDeserializer, paramBeanDescription.getClassAnnotations(), paramAnnotatedParameter, paramInt, paramObject, localPropertyMetadata);
      if (localJsonDeserializer != null) {
        localObject = ((SettableBeanProperty)localObject).withValueDeserializer(paramDeserializationContext.handlePrimaryContextualization(localJsonDeserializer, (BeanProperty)localObject, localJavaType3));
      }
      return (SettableBeanProperty)localObject;
    }
    Boolean localBoolean = localAnnotationIntrospector.hasRequiredMarker(paramAnnotatedParameter);
    if ((localBoolean != null) && (localBoolean.booleanValue())) {}
    for (boolean bool = true;; bool = false)
    {
      String str1 = localAnnotationIntrospector.findPropertyDescription(paramAnnotatedParameter);
      Integer localInteger = localAnnotationIntrospector.findPropertyIndex(paramAnnotatedParameter);
      String str2 = localAnnotationIntrospector.findPropertyDefaultValue(paramAnnotatedParameter);
      localPropertyMetadata = PropertyMetadata.construct(bool, str1, localInteger, str2);
      break;
    }
  }
  
  protected EnumResolver constructEnumResolver(Class<?> paramClass, DeserializationConfig paramDeserializationConfig, AnnotatedMethod paramAnnotatedMethod)
  {
    EnumResolver localEnumResolver;
    if (paramAnnotatedMethod != null)
    {
      Method localMethod = paramAnnotatedMethod.getAnnotated();
      if (paramDeserializationConfig.canOverrideAccessModifiers()) {
        ClassUtil.checkAndFixAccess(localMethod);
      }
      localEnumResolver = EnumResolver.constructUnsafeUsingMethod(paramClass, localMethod);
    }
    for (;;)
    {
      return localEnumResolver;
      if (paramDeserializationConfig.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING)) {
        localEnumResolver = EnumResolver.constructUnsafeUsingToString(paramClass);
      } else {
        localEnumResolver = EnumResolver.constructUnsafe(paramClass, paramDeserializationConfig.getAnnotationIntrospector());
      }
    }
  }
  
  public JsonDeserializer<?> createArrayDeserializer(DeserializationContext paramDeserializationContext, ArrayType paramArrayType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    JavaType localJavaType = paramArrayType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)localJavaType.getValueHandler();
    TypeDeserializer localTypeDeserializer = (TypeDeserializer)localJavaType.getTypeHandler();
    if (localTypeDeserializer == null) {
      localTypeDeserializer = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    Object localObject1 = _findCustomArrayDeserializer(paramArrayType, localDeserializationConfig, paramBeanDescription, localTypeDeserializer, localJsonDeserializer);
    Class localClass;
    Object localObject2;
    if (localObject1 == null) {
      if (localJsonDeserializer == null)
      {
        localClass = localJavaType.getRawClass();
        if (localJavaType.isPrimitive()) {
          localObject2 = PrimitiveArrayDeserializers.forType(localClass);
        }
      }
    }
    for (;;)
    {
      return (JsonDeserializer<?>)localObject2;
      if (localClass == String.class)
      {
        localObject2 = StringArrayDeserializer.instance;
      }
      else
      {
        localObject1 = new ObjectArrayDeserializer(paramArrayType, localJsonDeserializer, localTypeDeserializer);
        if (this._factoryConfig.hasDeserializerModifiers())
        {
          Iterator localIterator = this._factoryConfig.deserializerModifiers().iterator();
          while (localIterator.hasNext()) {
            localObject1 = ((BeanDeserializerModifier)localIterator.next()).modifyArrayDeserializer(localDeserializationConfig, paramArrayType, paramBeanDescription, (JsonDeserializer)localObject1);
          }
        }
        localObject2 = localObject1;
      }
    }
  }
  
  public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext paramDeserializationContext, CollectionType paramCollectionType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JavaType localJavaType = paramCollectionType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)localJavaType.getValueHandler();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    TypeDeserializer localTypeDeserializer = (TypeDeserializer)localJavaType.getTypeHandler();
    if (localTypeDeserializer == null) {
      localTypeDeserializer = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    Object localObject1 = _findCustomCollectionDeserializer(paramCollectionType, localDeserializationConfig, paramBeanDescription, localTypeDeserializer, localJsonDeserializer);
    if (localObject1 == null)
    {
      Class localClass = paramCollectionType.getRawClass();
      if ((localJsonDeserializer == null) && (EnumSet.class.isAssignableFrom(localClass))) {
        localObject1 = new EnumSetDeserializer(localJavaType, null);
      }
    }
    CollectionType localCollectionType;
    ValueInstantiator localValueInstantiator;
    if (localObject1 == null) {
      if ((paramCollectionType.isInterface()) || (paramCollectionType.isAbstract()))
      {
        localCollectionType = _mapAbstractCollectionType(paramCollectionType, localDeserializationConfig);
        if (localCollectionType == null)
        {
          if (paramCollectionType.getTypeHandler() == null) {
            throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + paramCollectionType);
          }
          localObject1 = AbstractDeserializer.constructForNonPOJO(paramBeanDescription);
        }
      }
      else
      {
        if (localObject1 != null) {
          break label262;
        }
        localValueInstantiator = findValueInstantiator(paramDeserializationContext, paramBeanDescription);
        if ((localValueInstantiator.canCreateUsingDefault()) || (paramCollectionType.getRawClass() != ArrayBlockingQueue.class)) {
          break label238;
        }
      }
    }
    for (Object localObject2 = new ArrayBlockingQueueDeserializer(paramCollectionType, localJsonDeserializer, localTypeDeserializer, localValueInstantiator, null);; localObject2 = localObject1)
    {
      return (JsonDeserializer<?>)localObject2;
      paramCollectionType = localCollectionType;
      paramBeanDescription = localDeserializationConfig.introspectForCreation(paramCollectionType);
      break;
      label238:
      if (localJavaType.getRawClass() == String.class) {}
      for (localObject1 = new StringCollectionDeserializer(paramCollectionType, localJsonDeserializer, localValueInstantiator); this._factoryConfig.hasDeserializerModifiers(); localObject1 = new CollectionDeserializer(paramCollectionType, localJsonDeserializer, localTypeDeserializer, localValueInstantiator))
      {
        label262:
        Iterator localIterator = this._factoryConfig.deserializerModifiers().iterator();
        while (localIterator.hasNext()) {
          localObject1 = ((BeanDeserializerModifier)localIterator.next()).modifyCollectionDeserializer(localDeserializationConfig, paramCollectionType, paramBeanDescription, (JsonDeserializer)localObject1);
        }
      }
    }
  }
  
  public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext paramDeserializationContext, CollectionLikeType paramCollectionLikeType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JavaType localJavaType = paramCollectionLikeType.getContentType();
    JsonDeserializer localJsonDeserializer1 = (JsonDeserializer)localJavaType.getValueHandler();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    TypeDeserializer localTypeDeserializer = (TypeDeserializer)localJavaType.getTypeHandler();
    if (localTypeDeserializer == null) {
      localTypeDeserializer = findTypeDeserializer(localDeserializationConfig, localJavaType);
    }
    JsonDeserializer localJsonDeserializer2 = _findCustomCollectionLikeDeserializer(paramCollectionLikeType, localDeserializationConfig, paramBeanDescription, localTypeDeserializer, localJsonDeserializer1);
    if ((localJsonDeserializer2 != null) && (this._factoryConfig.hasDeserializerModifiers()))
    {
      Iterator localIterator = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator.hasNext()) {
        localJsonDeserializer2 = ((BeanDeserializerModifier)localIterator.next()).modifyCollectionLikeDeserializer(localDeserializationConfig, paramCollectionLikeType, paramBeanDescription, localJsonDeserializer2);
      }
    }
    return localJsonDeserializer2;
  }
  
  public JsonDeserializer<?> createEnumDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    Class localClass = paramJavaType.getRawClass();
    Object localObject = _findCustomEnumDeserializer(localClass, localDeserializationConfig, paramBeanDescription);
    AnnotatedMethod localAnnotatedMethod;
    if (localObject == null)
    {
      Iterator localIterator2 = paramBeanDescription.getFactoryMethods().iterator();
      while (localIterator2.hasNext())
      {
        localAnnotatedMethod = (AnnotatedMethod)localIterator2.next();
        if (paramDeserializationContext.getAnnotationIntrospector().hasCreatorAnnotation(localAnnotatedMethod))
        {
          if ((localAnnotatedMethod.getParameterCount() != 1) || (!localAnnotatedMethod.getRawReturnType().isAssignableFrom(localClass))) {
            break label190;
          }
          localObject = EnumDeserializer.deserializerForCreator(localDeserializationConfig, localClass, localAnnotatedMethod);
        }
      }
      if (localObject == null) {
        localObject = new EnumDeserializer(constructEnumResolver(localClass, localDeserializationConfig, paramBeanDescription.findJsonValueMethod()));
      }
    }
    if (this._factoryConfig.hasDeserializerModifiers())
    {
      Iterator localIterator1 = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator1.hasNext())
      {
        localObject = ((BeanDeserializerModifier)localIterator1.next()).modifyEnumDeserializer(localDeserializationConfig, paramJavaType, paramBeanDescription, (JsonDeserializer)localObject);
        continue;
        label190:
        throw new IllegalArgumentException("Unsuitable method (" + localAnnotatedMethod + ") decorated with @JsonCreator (for Enum type " + localClass.getName() + ")");
      }
    }
    return (JsonDeserializer<?>)localObject;
  }
  
  public KeyDeserializer createKeyDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    KeyDeserializer localKeyDeserializer1 = null;
    if (this._factoryConfig.hasKeyDeserializers())
    {
      BeanDescription localBeanDescription = localDeserializationConfig.introspectClassAnnotations(paramJavaType.getRawClass());
      Iterator localIterator2 = this._factoryConfig.keyDeserializers().iterator();
      do
      {
        if (!localIterator2.hasNext()) {
          break;
        }
        localKeyDeserializer1 = ((KeyDeserializers)localIterator2.next()).findKeyDeserializer(paramJavaType, localDeserializationConfig, localBeanDescription);
      } while (localKeyDeserializer1 == null);
    }
    if (localKeyDeserializer1 == null) {
      if (!paramJavaType.isEnumType()) {}
    }
    for (KeyDeserializer localKeyDeserializer2 = _createEnumKeyDeserializer(paramDeserializationContext, paramJavaType);; localKeyDeserializer2 = localKeyDeserializer1)
    {
      return localKeyDeserializer2;
      localKeyDeserializer1 = StdKeyDeserializers.findStringBasedKeyDeserializer(localDeserializationConfig, paramJavaType);
      if ((localKeyDeserializer1 != null) && (this._factoryConfig.hasDeserializerModifiers()))
      {
        Iterator localIterator1 = this._factoryConfig.deserializerModifiers().iterator();
        while (localIterator1.hasNext()) {
          localKeyDeserializer1 = ((BeanDeserializerModifier)localIterator1.next()).modifyKeyDeserializer(localDeserializationConfig, paramJavaType, localKeyDeserializer1);
        }
      }
    }
  }
  
  public JsonDeserializer<?> createMapDeserializer(DeserializationContext paramDeserializationContext, MapType paramMapType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    JavaType localJavaType1 = paramMapType.getKeyType();
    JavaType localJavaType2 = paramMapType.getContentType();
    JsonDeserializer localJsonDeserializer = (JsonDeserializer)localJavaType2.getValueHandler();
    KeyDeserializer localKeyDeserializer = (KeyDeserializer)localJavaType1.getValueHandler();
    TypeDeserializer localTypeDeserializer = (TypeDeserializer)localJavaType2.getTypeHandler();
    if (localTypeDeserializer == null) {
      localTypeDeserializer = findTypeDeserializer(localDeserializationConfig, localJavaType2);
    }
    Object localObject = _findCustomMapDeserializer(paramMapType, localDeserializationConfig, paramBeanDescription, localKeyDeserializer, localTypeDeserializer, localJsonDeserializer);
    if (localObject == null)
    {
      Class localClass1 = paramMapType.getRawClass();
      if (EnumMap.class.isAssignableFrom(localClass1))
      {
        Class localClass3 = localJavaType1.getRawClass();
        if ((localClass3 == null) || (!localClass3.isEnum())) {
          throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
        }
        localObject = new EnumMapDeserializer(paramMapType, null, localJsonDeserializer, localTypeDeserializer);
      }
      if (localObject == null) {
        if ((paramMapType.isInterface()) || (paramMapType.isAbstract()))
        {
          Class localClass2 = (Class)_mapFallbacks.get(localClass1.getName());
          if (localClass2 == null) {
            break label317;
          }
          paramMapType = (MapType)localDeserializationConfig.constructSpecializedType(paramMapType, localClass2);
          paramBeanDescription = localDeserializationConfig.introspectForCreation(paramMapType);
        }
      }
    }
    for (;;)
    {
      if (localObject == null)
      {
        ValueInstantiator localValueInstantiator = findValueInstantiator(paramDeserializationContext, paramBeanDescription);
        MapDeserializer localMapDeserializer = new MapDeserializer(paramMapType, localValueInstantiator, localKeyDeserializer, localJsonDeserializer, localTypeDeserializer);
        localMapDeserializer.setIgnorableProperties(localDeserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(paramBeanDescription.getClassInfo(), false));
        localObject = localMapDeserializer;
      }
      if (!this._factoryConfig.hasDeserializerModifiers()) {
        break;
      }
      Iterator localIterator = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator.hasNext()) {
        localObject = ((BeanDeserializerModifier)localIterator.next()).modifyMapDeserializer(localDeserializationConfig, paramMapType, paramBeanDescription, (JsonDeserializer)localObject);
      }
      label317:
      if (paramMapType.getTypeHandler() == null) {
        throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + paramMapType);
      }
      localObject = AbstractDeserializer.constructForNonPOJO(paramBeanDescription);
    }
    return (JsonDeserializer<?>)localObject;
  }
  
  public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext paramDeserializationContext, MapLikeType paramMapLikeType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    JavaType localJavaType1 = paramMapLikeType.getKeyType();
    JavaType localJavaType2 = paramMapLikeType.getContentType();
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    JsonDeserializer localJsonDeserializer1 = (JsonDeserializer)localJavaType2.getValueHandler();
    KeyDeserializer localKeyDeserializer = (KeyDeserializer)localJavaType1.getValueHandler();
    TypeDeserializer localTypeDeserializer = (TypeDeserializer)localJavaType2.getTypeHandler();
    if (localTypeDeserializer == null) {
      localTypeDeserializer = findTypeDeserializer(localDeserializationConfig, localJavaType2);
    }
    JsonDeserializer localJsonDeserializer2 = _findCustomMapLikeDeserializer(paramMapLikeType, localDeserializationConfig, paramBeanDescription, localKeyDeserializer, localTypeDeserializer, localJsonDeserializer1);
    if ((localJsonDeserializer2 != null) && (this._factoryConfig.hasDeserializerModifiers()))
    {
      Iterator localIterator = this._factoryConfig.deserializerModifiers().iterator();
      while (localIterator.hasNext()) {
        localJsonDeserializer2 = ((BeanDeserializerModifier)localIterator.next()).modifyMapLikeDeserializer(localDeserializationConfig, paramMapLikeType, paramBeanDescription, localJsonDeserializer2);
      }
    }
    return localJsonDeserializer2;
  }
  
  public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Class localClass = paramJavaType.getRawClass();
    JsonDeserializer localJsonDeserializer = _findCustomTreeNodeDeserializer(localClass, paramDeserializationConfig, paramBeanDescription);
    if (localJsonDeserializer != null) {}
    for (;;)
    {
      return localJsonDeserializer;
      localJsonDeserializer = JsonNodeDeserializer.getDeserializer(localClass);
    }
  }
  
  public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Class localClass = paramJavaType.getRawClass();
    JavaType localJavaType6;
    JavaType localJavaType5;
    Object localObject;
    if (localClass == CLASS_OBJECT)
    {
      DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
      if (this._factoryConfig.hasAbstractTypeResolvers())
      {
        localJavaType6 = _findRemappedType(localDeserializationConfig, List.class);
        localJavaType5 = _findRemappedType(localDeserializationConfig, Map.class);
        localObject = new UntypedObjectDeserializer(localJavaType6, localJavaType5);
      }
    }
    for (;;)
    {
      return (JsonDeserializer<?>)localObject;
      localJavaType5 = null;
      localJavaType6 = null;
      break;
      if ((localClass == CLASS_STRING) || (localClass == CLASS_CHAR_BUFFER))
      {
        localObject = StringDeserializer.instance;
      }
      else
      {
        if (paramJavaType.isReferenceType())
        {
          JavaType localJavaType4 = paramJavaType.getReferencedType();
          if (AtomicReference.class.isAssignableFrom(localClass))
          {
            TypeDeserializer localTypeDeserializer2 = findTypeDeserializer(paramDeserializationContext.getConfig(), localJavaType4);
            JsonDeserializer localJsonDeserializer2 = findDeserializerFromAnnotation(paramDeserializationContext, paramDeserializationContext.getConfig().introspectClassAnnotations(localJavaType4).getClassInfo());
            AtomicReferenceDeserializer localAtomicReferenceDeserializer = new AtomicReferenceDeserializer(localJavaType4, localTypeDeserializer2, localJsonDeserializer2);
            localObject = localAtomicReferenceDeserializer;
            continue;
          }
        }
        if (localClass == CLASS_ITERABLE)
        {
          TypeFactory localTypeFactory = paramDeserializationContext.getTypeFactory();
          JavaType[] arrayOfJavaType = localTypeFactory.findTypeParameters(paramJavaType, CLASS_ITERABLE);
          if ((arrayOfJavaType == null) || (arrayOfJavaType.length != 1)) {}
          for (JavaType localJavaType3 = TypeFactory.unknownType();; localJavaType3 = arrayOfJavaType[0])
          {
            localObject = createCollectionDeserializer(paramDeserializationContext, localTypeFactory.constructCollectionType(Collection.class, localJavaType3), paramBeanDescription);
            break;
          }
        }
        if (localClass == CLASS_MAP_ENTRY)
        {
          JavaType localJavaType1 = paramJavaType.containedType(0);
          if (localJavaType1 == null) {
            localJavaType1 = TypeFactory.unknownType();
          }
          JavaType localJavaType2 = paramJavaType.containedType(1);
          if (localJavaType2 == null) {
            localJavaType2 = TypeFactory.unknownType();
          }
          TypeDeserializer localTypeDeserializer1 = (TypeDeserializer)localJavaType2.getTypeHandler();
          if (localTypeDeserializer1 == null) {
            localTypeDeserializer1 = findTypeDeserializer(paramDeserializationContext.getConfig(), localJavaType2);
          }
          JsonDeserializer localJsonDeserializer1 = (JsonDeserializer)localJavaType2.getValueHandler();
          localObject = new MapEntryDeserializer(paramJavaType, (KeyDeserializer)localJavaType1.getValueHandler(), localJsonDeserializer1, localTypeDeserializer1);
        }
        else
        {
          String str = localClass.getName();
          if ((localClass.isPrimitive()) || (str.startsWith("java.")))
          {
            localObject = NumberDeserializers.find(localClass, str);
            if (localObject == null) {
              localObject = DateDeserializers.find(localClass, str);
            }
            if (localObject != null) {}
          }
          else if (localClass == TokenBuffer.class)
          {
            localObject = new TokenBufferDeserializer();
          }
          else
          {
            localObject = findOptionalStdDeserializer(paramDeserializationContext, paramJavaType, paramBeanDescription);
            if (localObject == null) {
              localObject = JdkDeserializers.find(localClass, str);
            }
          }
        }
      }
    }
  }
  
  protected JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject1 = paramDeserializationContext.getAnnotationIntrospector().findDeserializer(paramAnnotated);
    if (localObject1 == null) {}
    for (Object localObject2 = null;; localObject2 = paramDeserializationContext.deserializerInstance(paramAnnotated, localObject1)) {
      return (JsonDeserializer<Object>)localObject2;
    }
  }
  
  protected KeyDeserializer findKeyDeserializerFromAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject = paramDeserializationContext.getAnnotationIntrospector().findKeyDeserializer(paramAnnotated);
    if (localObject == null) {}
    for (KeyDeserializer localKeyDeserializer = null;; localKeyDeserializer = paramDeserializationContext.keyDeserializerInstance(paramAnnotated, localObject)) {
      return localKeyDeserializer;
    }
  }
  
  protected JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext paramDeserializationContext, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    return OptionalHandlerFactory.instance.findDeserializer(paramJavaType, paramDeserializationContext.getConfig(), paramBeanDescription);
  }
  
  public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    TypeResolverBuilder localTypeResolverBuilder = paramDeserializationConfig.getAnnotationIntrospector().findPropertyContentTypeResolver(paramDeserializationConfig, paramAnnotatedMember, paramJavaType);
    JavaType localJavaType = paramJavaType.getContentType();
    if (localTypeResolverBuilder == null) {}
    for (TypeDeserializer localTypeDeserializer = findTypeDeserializer(paramDeserializationConfig, localJavaType);; localTypeDeserializer = localTypeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, localJavaType, paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(paramDeserializationConfig, paramAnnotatedMember, localJavaType))) {
      return localTypeDeserializer;
    }
  }
  
  public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    TypeResolverBuilder localTypeResolverBuilder = paramDeserializationConfig.getAnnotationIntrospector().findPropertyTypeResolver(paramDeserializationConfig, paramAnnotatedMember, paramJavaType);
    if (localTypeResolverBuilder == null) {}
    for (TypeDeserializer localTypeDeserializer = findTypeDeserializer(paramDeserializationConfig, paramJavaType);; localTypeDeserializer = localTypeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(paramDeserializationConfig, paramAnnotatedMember, paramJavaType))) {
      return localTypeDeserializer;
    }
  }
  
  public TypeDeserializer findTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    AnnotatedClass localAnnotatedClass = paramDeserializationConfig.introspectClassAnnotations(paramJavaType.getRawClass()).getClassInfo();
    TypeResolverBuilder localTypeResolverBuilder = paramDeserializationConfig.getAnnotationIntrospector().findTypeResolver(paramDeserializationConfig, localAnnotatedClass, paramJavaType);
    Collection localCollection = null;
    if (localTypeResolverBuilder == null)
    {
      localTypeResolverBuilder = paramDeserializationConfig.getDefaultTyper(paramJavaType);
      if (localTypeResolverBuilder != null) {
        break label61;
      }
    }
    for (TypeDeserializer localTypeDeserializer = null;; localTypeDeserializer = localTypeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, localCollection))
    {
      return localTypeDeserializer;
      localCollection = paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(paramDeserializationConfig, localAnnotatedClass);
      label61:
      if ((localTypeResolverBuilder.getDefaultImpl() == null) && (paramJavaType.isAbstract()))
      {
        JavaType localJavaType = mapAbstractType(paramDeserializationConfig, paramJavaType);
        if ((localJavaType != null) && (localJavaType.getRawClass() != paramJavaType.getRawClass())) {
          localTypeResolverBuilder = localTypeResolverBuilder.defaultImpl(localJavaType.getRawClass());
        }
      }
    }
  }
  
  public ValueInstantiator findValueInstantiator(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    DeserializationConfig localDeserializationConfig = paramDeserializationContext.getConfig();
    ValueInstantiator localValueInstantiator = null;
    AnnotatedClass localAnnotatedClass = paramBeanDescription.getClassInfo();
    Object localObject = paramDeserializationContext.getAnnotationIntrospector().findValueInstantiator(localAnnotatedClass);
    if (localObject != null) {
      localValueInstantiator = _valueInstantiatorInstance(localDeserializationConfig, localAnnotatedClass, localObject);
    }
    if (localValueInstantiator == null)
    {
      localValueInstantiator = _findStdValueInstantiator(localDeserializationConfig, paramBeanDescription);
      if (localValueInstantiator == null) {
        localValueInstantiator = _constructDefaultValueInstantiator(paramDeserializationContext, paramBeanDescription);
      }
    }
    if (this._factoryConfig.hasValueInstantiators())
    {
      Iterator localIterator = this._factoryConfig.valueInstantiators().iterator();
      while (localIterator.hasNext())
      {
        ValueInstantiators localValueInstantiators = (ValueInstantiators)localIterator.next();
        localValueInstantiator = localValueInstantiators.findValueInstantiator(localDeserializationConfig, paramBeanDescription, localValueInstantiator);
        if (localValueInstantiator == null) {
          throw new JsonMappingException("Broken registered ValueInstantiators (of type " + localValueInstantiators.getClass().getName() + "): returned null ValueInstantiator");
        }
      }
    }
    if (localValueInstantiator.getIncompleteParameter() != null)
    {
      AnnotatedParameter localAnnotatedParameter = localValueInstantiator.getIncompleteParameter();
      AnnotatedWithParams localAnnotatedWithParams = localAnnotatedParameter.getOwner();
      throw new IllegalArgumentException("Argument #" + localAnnotatedParameter.getIndex() + " of constructor " + localAnnotatedWithParams + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
    }
    return localValueInstantiator;
  }
  
  public DeserializerFactoryConfig getFactoryConfig()
  {
    return this._factoryConfig;
  }
  
  public JavaType mapAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
    throws JsonMappingException
  {
    for (;;)
    {
      JavaType localJavaType = _mapAbstractType2(paramDeserializationConfig, paramJavaType);
      if (localJavaType == null) {
        return paramJavaType;
      }
      Class localClass1 = paramJavaType.getRawClass();
      Class localClass2 = localJavaType.getRawClass();
      if ((localClass1 == localClass2) || (!localClass1.isAssignableFrom(localClass2))) {
        throw new IllegalArgumentException("Invalid abstract type resolution from " + paramJavaType + " to " + localJavaType + ": latter is not a subtype of former");
      }
      paramJavaType = localJavaType;
    }
  }
  
  /* Error */
  protected <T extends JavaType> T modifyTypeByAnnotation(DeserializationContext paramDeserializationContext, Annotated paramAnnotated, T paramT)
    throws JsonMappingException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 473	com/fasterxml/jackson/databind/DeserializationContext:getAnnotationIntrospector	()Lcom/fasterxml/jackson/databind/AnnotationIntrospector;
    //   4: astore 4
    //   6: aload 4
    //   8: aload_2
    //   9: aload_3
    //   10: invokevirtual 1217	com/fasterxml/jackson/databind/AnnotationIntrospector:findDeserializationType	(Lcom/fasterxml/jackson/databind/introspect/Annotated;Lcom/fasterxml/jackson/databind/JavaType;)Ljava/lang/Class;
    //   13: astore 5
    //   15: aload 5
    //   17: ifnull +18 -> 35
    //   20: aload_1
    //   21: invokevirtual 1060	com/fasterxml/jackson/databind/DeserializationContext:getTypeFactory	()Lcom/fasterxml/jackson/databind/type/TypeFactory;
    //   24: aload_3
    //   25: aload 5
    //   27: invokevirtual 1218	com/fasterxml/jackson/databind/type/TypeFactory:constructSpecializedType	(Lcom/fasterxml/jackson/databind/JavaType;Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JavaType;
    //   30: astore 17
    //   32: aload 17
    //   34: astore_3
    //   35: aload_3
    //   36: invokevirtual 1221	com/fasterxml/jackson/databind/JavaType:isContainerType	()Z
    //   39: ifeq +264 -> 303
    //   42: aload 4
    //   44: aload_2
    //   45: aload_3
    //   46: invokevirtual 1222	com/fasterxml/jackson/databind/JavaType:getKeyType	()Lcom/fasterxml/jackson/databind/JavaType;
    //   49: invokevirtual 1225	com/fasterxml/jackson/databind/AnnotationIntrospector:findDeserializationKeyType	(Lcom/fasterxml/jackson/databind/introspect/Annotated;Lcom/fasterxml/jackson/databind/JavaType;)Ljava/lang/Class;
    //   52: astore 6
    //   54: aload 6
    //   56: ifnull +132 -> 188
    //   59: aload_3
    //   60: instanceof 1014
    //   63: ifne +111 -> 174
    //   66: new 120	com/fasterxml/jackson/databind/JsonMappingException
    //   69: dup
    //   70: new 219	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   77: ldc_w 1227
    //   80: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload_3
    //   84: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   87: ldc_w 1229
    //   90: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokespecial 1199	com/fasterxml/jackson/databind/JsonMappingException:<init>	(Ljava/lang/String;)V
    //   99: athrow
    //   100: astore 16
    //   102: new 120	com/fasterxml/jackson/databind/JsonMappingException
    //   105: dup
    //   106: new 219	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   113: ldc_w 1231
    //   116: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: aload_3
    //   120: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   123: ldc_w 1233
    //   126: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload 5
    //   131: invokevirtual 68	java/lang/Class:getName	()Ljava/lang/String;
    //   134: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: ldc_w 1235
    //   140: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: aload_2
    //   144: invokevirtual 1238	com/fasterxml/jackson/databind/introspect/Annotated:getName	()Ljava/lang/String;
    //   147: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: ldc_w 1240
    //   153: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: aload 16
    //   158: invokevirtual 1243	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   161: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   167: aconst_null
    //   168: aload 16
    //   170: invokespecial 1246	com/fasterxml/jackson/databind/JsonMappingException:<init>	(Ljava/lang/String;Lcom/fasterxml/jackson/core/JsonLocation;Ljava/lang/Throwable;)V
    //   173: athrow
    //   174: aload_3
    //   175: checkcast 1014	com/fasterxml/jackson/databind/type/MapLikeType
    //   178: aload 6
    //   180: invokevirtual 1249	com/fasterxml/jackson/databind/type/MapLikeType:narrowKey	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JavaType;
    //   183: astore 15
    //   185: aload 15
    //   187: astore_3
    //   188: aload_3
    //   189: invokevirtual 1222	com/fasterxml/jackson/databind/JavaType:getKeyType	()Lcom/fasterxml/jackson/databind/JavaType;
    //   192: astore 7
    //   194: aload 7
    //   196: ifnull +44 -> 240
    //   199: aload 7
    //   201: invokevirtual 811	com/fasterxml/jackson/databind/JavaType:getValueHandler	()Ljava/lang/Object;
    //   204: ifnonnull +36 -> 240
    //   207: aload_1
    //   208: aload_2
    //   209: aload 4
    //   211: aload_2
    //   212: invokevirtual 1117	com/fasterxml/jackson/databind/AnnotationIntrospector:findKeyDeserializer	(Lcom/fasterxml/jackson/databind/introspect/Annotated;)Ljava/lang/Object;
    //   215: invokevirtual 1121	com/fasterxml/jackson/databind/DeserializationContext:keyDeserializerInstance	(Lcom/fasterxml/jackson/databind/introspect/Annotated;Ljava/lang/Object;)Lcom/fasterxml/jackson/databind/KeyDeserializer;
    //   218: astore 12
    //   220: aload 12
    //   222: ifnull +18 -> 240
    //   225: aload_3
    //   226: checkcast 1014	com/fasterxml/jackson/databind/type/MapLikeType
    //   229: aload 12
    //   231: invokevirtual 1253	com/fasterxml/jackson/databind/type/MapLikeType:withKeyValueHandler	(Ljava/lang/Object;)Lcom/fasterxml/jackson/databind/type/MapLikeType;
    //   234: astore_3
    //   235: aload_3
    //   236: invokevirtual 1222	com/fasterxml/jackson/databind/JavaType:getKeyType	()Lcom/fasterxml/jackson/databind/JavaType;
    //   239: pop
    //   240: aload 4
    //   242: aload_2
    //   243: aload_3
    //   244: invokevirtual 1135	com/fasterxml/jackson/databind/JavaType:getContentType	()Lcom/fasterxml/jackson/databind/JavaType;
    //   247: invokevirtual 1256	com/fasterxml/jackson/databind/AnnotationIntrospector:findDeserializationContentType	(Lcom/fasterxml/jackson/databind/introspect/Annotated;Lcom/fasterxml/jackson/databind/JavaType;)Ljava/lang/Class;
    //   250: astore 8
    //   252: aload 8
    //   254: ifnull +14 -> 268
    //   257: aload_3
    //   258: aload 8
    //   260: invokevirtual 1259	com/fasterxml/jackson/databind/JavaType:narrowContentsBy	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JavaType;
    //   263: astore 11
    //   265: aload 11
    //   267: astore_3
    //   268: aload_3
    //   269: invokevirtual 1135	com/fasterxml/jackson/databind/JavaType:getContentType	()Lcom/fasterxml/jackson/databind/JavaType;
    //   272: invokevirtual 811	com/fasterxml/jackson/databind/JavaType:getValueHandler	()Ljava/lang/Object;
    //   275: ifnonnull +28 -> 303
    //   278: aload_1
    //   279: aload_2
    //   280: aload 4
    //   282: aload_2
    //   283: invokevirtual 1262	com/fasterxml/jackson/databind/AnnotationIntrospector:findContentDeserializer	(Lcom/fasterxml/jackson/databind/introspect/Annotated;)Ljava/lang/Object;
    //   286: invokevirtual 1115	com/fasterxml/jackson/databind/DeserializationContext:deserializerInstance	(Lcom/fasterxml/jackson/databind/introspect/Annotated;Ljava/lang/Object;)Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   289: astore 9
    //   291: aload 9
    //   293: ifnull +10 -> 303
    //   296: aload_3
    //   297: aload 9
    //   299: invokevirtual 1266	com/fasterxml/jackson/databind/JavaType:withContentValueHandler	(Ljava/lang/Object;)Lcom/fasterxml/jackson/databind/JavaType;
    //   302: astore_3
    //   303: aload_3
    //   304: areturn
    //   305: astore 14
    //   307: new 120	com/fasterxml/jackson/databind/JsonMappingException
    //   310: dup
    //   311: new 219	java/lang/StringBuilder
    //   314: dup
    //   315: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   318: ldc_w 1268
    //   321: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: aload_3
    //   325: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   328: ldc_w 1270
    //   331: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: aload 6
    //   336: invokevirtual 68	java/lang/Class:getName	()Ljava/lang/String;
    //   339: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: ldc_w 1272
    //   345: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: aload 14
    //   350: invokevirtual 1243	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   353: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: aconst_null
    //   360: aload 14
    //   362: invokespecial 1246	com/fasterxml/jackson/databind/JsonMappingException:<init>	(Ljava/lang/String;Lcom/fasterxml/jackson/core/JsonLocation;Ljava/lang/Throwable;)V
    //   365: athrow
    //   366: astore 10
    //   368: new 120	com/fasterxml/jackson/databind/JsonMappingException
    //   371: dup
    //   372: new 219	java/lang/StringBuilder
    //   375: dup
    //   376: invokespecial 220	java/lang/StringBuilder:<init>	()V
    //   379: ldc_w 1274
    //   382: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   385: aload_3
    //   386: invokevirtual 229	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   389: ldc_w 1276
    //   392: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: aload 8
    //   397: invokevirtual 68	java/lang/Class:getName	()Ljava/lang/String;
    //   400: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: ldc_w 1272
    //   406: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: aload 10
    //   411: invokevirtual 1243	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   414: invokevirtual 226	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   420: aconst_null
    //   421: aload 10
    //   423: invokespecial 1246	com/fasterxml/jackson/databind/JsonMappingException:<init>	(Ljava/lang/String;Lcom/fasterxml/jackson/core/JsonLocation;Ljava/lang/Throwable;)V
    //   426: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	427	0	this	BasicDeserializerFactory
    //   0	427	1	paramDeserializationContext	DeserializationContext
    //   0	427	2	paramAnnotated	Annotated
    //   0	427	3	paramT	T
    //   4	277	4	localAnnotationIntrospector	AnnotationIntrospector
    //   13	117	5	localClass1	Class
    //   52	283	6	localClass2	Class
    //   192	8	7	localJavaType1	JavaType
    //   250	146	8	localClass3	Class
    //   289	9	9	localJsonDeserializer	JsonDeserializer
    //   366	56	10	localIllegalArgumentException1	IllegalArgumentException
    //   263	3	11	localJavaType2	JavaType
    //   218	12	12	localKeyDeserializer	KeyDeserializer
    //   305	56	14	localIllegalArgumentException2	IllegalArgumentException
    //   183	3	15	localJavaType3	JavaType
    //   100	69	16	localIllegalArgumentException3	IllegalArgumentException
    //   30	3	17	localJavaType4	JavaType
    // Exception table:
    //   from	to	target	type
    //   20	32	100	java/lang/IllegalArgumentException
    //   174	185	305	java/lang/IllegalArgumentException
    //   257	265	366	java/lang/IllegalArgumentException
  }
  
  protected JavaType resolveType(DeserializationContext paramDeserializationContext, BeanDescription paramBeanDescription, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember)
    throws JsonMappingException
  {
    if (paramJavaType.isContainerType())
    {
      AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
      if (paramJavaType.getKeyType() != null)
      {
        KeyDeserializer localKeyDeserializer = paramDeserializationContext.keyDeserializerInstance(paramAnnotatedMember, localAnnotationIntrospector.findKeyDeserializer(paramAnnotatedMember));
        if (localKeyDeserializer != null)
        {
          paramJavaType = ((MapLikeType)paramJavaType).withKeyValueHandler(localKeyDeserializer);
          paramJavaType.getKeyType();
        }
      }
      JsonDeserializer localJsonDeserializer = paramDeserializationContext.deserializerInstance(paramAnnotatedMember, localAnnotationIntrospector.findContentDeserializer(paramAnnotatedMember));
      if (localJsonDeserializer != null) {
        paramJavaType = paramJavaType.withContentValueHandler(localJsonDeserializer);
      }
      if ((paramAnnotatedMember instanceof AnnotatedMember))
      {
        TypeDeserializer localTypeDeserializer2 = findPropertyContentTypeDeserializer(paramDeserializationContext.getConfig(), paramJavaType, paramAnnotatedMember);
        if (localTypeDeserializer2 != null) {
          paramJavaType = paramJavaType.withContentTypeHandler(localTypeDeserializer2);
        }
      }
    }
    if ((paramAnnotatedMember instanceof AnnotatedMember)) {}
    for (TypeDeserializer localTypeDeserializer1 = findPropertyTypeDeserializer(paramDeserializationContext.getConfig(), paramJavaType, paramAnnotatedMember);; localTypeDeserializer1 = findTypeDeserializer(paramDeserializationContext.getConfig(), paramJavaType))
    {
      if (localTypeDeserializer1 != null) {
        paramJavaType = paramJavaType.withTypeHandler(localTypeDeserializer1);
      }
      return paramJavaType;
    }
  }
  
  public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver)
  {
    return withConfig(this._factoryConfig.withAbstractTypeResolver(paramAbstractTypeResolver));
  }
  
  public final DeserializerFactory withAdditionalDeserializers(Deserializers paramDeserializers)
  {
    return withConfig(this._factoryConfig.withAdditionalDeserializers(paramDeserializers));
  }
  
  public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers paramKeyDeserializers)
  {
    return withConfig(this._factoryConfig.withAdditionalKeyDeserializers(paramKeyDeserializers));
  }
  
  protected abstract DeserializerFactory withConfig(DeserializerFactoryConfig paramDeserializerFactoryConfig);
  
  public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier)
  {
    return withConfig(this._factoryConfig.withDeserializerModifier(paramBeanDeserializerModifier));
  }
  
  public final DeserializerFactory withValueInstantiators(ValueInstantiators paramValueInstantiators)
  {
    return withConfig(this._factoryConfig.withValueInstantiators(paramValueInstantiators));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/BasicDeserializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */