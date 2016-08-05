package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
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
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.fasterxml.jackson.databind.deser.impl.InnerClassProperty;
import com.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReferenceProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class BeanDeserializerBase
  extends StdDeserializer<Object>
  implements ContextualDeserializer, ResolvableDeserializer, Serializable
{
  protected static final PropertyName TEMP_PROPERTY_NAME = new PropertyName("#temporary-name");
  private static final long serialVersionUID = 1L;
  protected SettableAnyProperty _anySetter;
  protected final Map<String, SettableBeanProperty> _backRefs;
  protected final BeanPropertyMap _beanProperties;
  protected final JavaType _beanType;
  private final transient Annotations _classAnnotations;
  protected JsonDeserializer<Object> _delegateDeserializer;
  protected ExternalTypeHandler _externalTypeIdHandler;
  protected final HashSet<String> _ignorableProps;
  protected final boolean _ignoreAllUnknown;
  protected final ValueInjector[] _injectables;
  protected final boolean _needViewProcesing;
  protected boolean _nonStandardCreation;
  protected final ObjectIdReader _objectIdReader;
  protected PropertyBasedCreator _propertyBasedCreator;
  protected final JsonFormat.Shape _serializationShape;
  protected transient HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;
  protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
  protected final ValueInstantiator _valueInstantiator;
  protected boolean _vanillaProcessing;
  
  protected BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase)
  {
    this(paramBeanDeserializerBase, paramBeanDeserializerBase._ignoreAllUnknown);
  }
  
  public BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase, ObjectIdReader paramObjectIdReader)
  {
    super(paramBeanDeserializerBase._beanType);
    this._classAnnotations = paramBeanDeserializerBase._classAnnotations;
    this._beanType = paramBeanDeserializerBase._beanType;
    this._valueInstantiator = paramBeanDeserializerBase._valueInstantiator;
    this._delegateDeserializer = paramBeanDeserializerBase._delegateDeserializer;
    this._propertyBasedCreator = paramBeanDeserializerBase._propertyBasedCreator;
    this._backRefs = paramBeanDeserializerBase._backRefs;
    this._ignorableProps = paramBeanDeserializerBase._ignorableProps;
    this._ignoreAllUnknown = paramBeanDeserializerBase._ignoreAllUnknown;
    this._anySetter = paramBeanDeserializerBase._anySetter;
    this._injectables = paramBeanDeserializerBase._injectables;
    this._nonStandardCreation = paramBeanDeserializerBase._nonStandardCreation;
    this._unwrappedPropertyHandler = paramBeanDeserializerBase._unwrappedPropertyHandler;
    this._needViewProcesing = paramBeanDeserializerBase._needViewProcesing;
    this._serializationShape = paramBeanDeserializerBase._serializationShape;
    this._objectIdReader = paramObjectIdReader;
    if (paramObjectIdReader == null) {
      this._beanProperties = paramBeanDeserializerBase._beanProperties;
    }
    for (this._vanillaProcessing = paramBeanDeserializerBase._vanillaProcessing;; this._vanillaProcessing = false)
    {
      return;
      ObjectIdValueProperty localObjectIdValueProperty = new ObjectIdValueProperty(paramObjectIdReader, PropertyMetadata.STD_REQUIRED);
      this._beanProperties = paramBeanDeserializerBase._beanProperties.withProperty(localObjectIdValueProperty);
    }
  }
  
  protected BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase, NameTransformer paramNameTransformer)
  {
    super(paramBeanDeserializerBase._beanType);
    this._classAnnotations = paramBeanDeserializerBase._classAnnotations;
    this._beanType = paramBeanDeserializerBase._beanType;
    this._valueInstantiator = paramBeanDeserializerBase._valueInstantiator;
    this._delegateDeserializer = paramBeanDeserializerBase._delegateDeserializer;
    this._propertyBasedCreator = paramBeanDeserializerBase._propertyBasedCreator;
    this._backRefs = paramBeanDeserializerBase._backRefs;
    this._ignorableProps = paramBeanDeserializerBase._ignorableProps;
    boolean bool;
    UnwrappedPropertyHandler localUnwrappedPropertyHandler;
    if ((paramNameTransformer != null) || (paramBeanDeserializerBase._ignoreAllUnknown))
    {
      bool = true;
      this._ignoreAllUnknown = bool;
      this._anySetter = paramBeanDeserializerBase._anySetter;
      this._injectables = paramBeanDeserializerBase._injectables;
      this._objectIdReader = paramBeanDeserializerBase._objectIdReader;
      this._nonStandardCreation = paramBeanDeserializerBase._nonStandardCreation;
      localUnwrappedPropertyHandler = paramBeanDeserializerBase._unwrappedPropertyHandler;
      if (paramNameTransformer == null) {
        break label182;
      }
      if (localUnwrappedPropertyHandler != null) {
        localUnwrappedPropertyHandler = localUnwrappedPropertyHandler.renameAll(paramNameTransformer);
      }
    }
    label182:
    for (this._beanProperties = paramBeanDeserializerBase._beanProperties.renameAll(paramNameTransformer);; this._beanProperties = paramBeanDeserializerBase._beanProperties)
    {
      this._unwrappedPropertyHandler = localUnwrappedPropertyHandler;
      this._needViewProcesing = paramBeanDeserializerBase._needViewProcesing;
      this._serializationShape = paramBeanDeserializerBase._serializationShape;
      this._vanillaProcessing = false;
      return;
      bool = false;
      break;
    }
  }
  
  public BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase, HashSet<String> paramHashSet)
  {
    super(paramBeanDeserializerBase._beanType);
    this._classAnnotations = paramBeanDeserializerBase._classAnnotations;
    this._beanType = paramBeanDeserializerBase._beanType;
    this._valueInstantiator = paramBeanDeserializerBase._valueInstantiator;
    this._delegateDeserializer = paramBeanDeserializerBase._delegateDeserializer;
    this._propertyBasedCreator = paramBeanDeserializerBase._propertyBasedCreator;
    this._backRefs = paramBeanDeserializerBase._backRefs;
    this._ignorableProps = paramHashSet;
    this._ignoreAllUnknown = paramBeanDeserializerBase._ignoreAllUnknown;
    this._anySetter = paramBeanDeserializerBase._anySetter;
    this._injectables = paramBeanDeserializerBase._injectables;
    this._nonStandardCreation = paramBeanDeserializerBase._nonStandardCreation;
    this._unwrappedPropertyHandler = paramBeanDeserializerBase._unwrappedPropertyHandler;
    this._needViewProcesing = paramBeanDeserializerBase._needViewProcesing;
    this._serializationShape = paramBeanDeserializerBase._serializationShape;
    this._vanillaProcessing = paramBeanDeserializerBase._vanillaProcessing;
    this._objectIdReader = paramBeanDeserializerBase._objectIdReader;
    this._beanProperties = paramBeanDeserializerBase._beanProperties;
  }
  
  protected BeanDeserializerBase(BeanDeserializerBase paramBeanDeserializerBase, boolean paramBoolean)
  {
    super(paramBeanDeserializerBase._beanType);
    this._classAnnotations = paramBeanDeserializerBase._classAnnotations;
    this._beanType = paramBeanDeserializerBase._beanType;
    this._valueInstantiator = paramBeanDeserializerBase._valueInstantiator;
    this._delegateDeserializer = paramBeanDeserializerBase._delegateDeserializer;
    this._propertyBasedCreator = paramBeanDeserializerBase._propertyBasedCreator;
    this._beanProperties = paramBeanDeserializerBase._beanProperties;
    this._backRefs = paramBeanDeserializerBase._backRefs;
    this._ignorableProps = paramBeanDeserializerBase._ignorableProps;
    this._ignoreAllUnknown = paramBoolean;
    this._anySetter = paramBeanDeserializerBase._anySetter;
    this._injectables = paramBeanDeserializerBase._injectables;
    this._objectIdReader = paramBeanDeserializerBase._objectIdReader;
    this._nonStandardCreation = paramBeanDeserializerBase._nonStandardCreation;
    this._unwrappedPropertyHandler = paramBeanDeserializerBase._unwrappedPropertyHandler;
    this._needViewProcesing = paramBeanDeserializerBase._needViewProcesing;
    this._serializationShape = paramBeanDeserializerBase._serializationShape;
    this._vanillaProcessing = paramBeanDeserializerBase._vanillaProcessing;
  }
  
  protected BeanDeserializerBase(BeanDeserializerBuilder paramBeanDeserializerBuilder, BeanDescription paramBeanDescription, BeanPropertyMap paramBeanPropertyMap, Map<String, SettableBeanProperty> paramMap, HashSet<String> paramHashSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramBeanDescription.getType());
    this._classAnnotations = paramBeanDescription.getClassInfo().getAnnotations();
    this._beanType = paramBeanDescription.getType();
    this._valueInstantiator = paramBeanDeserializerBuilder.getValueInstantiator();
    this._beanProperties = paramBeanPropertyMap;
    this._backRefs = paramMap;
    this._ignorableProps = paramHashSet;
    this._ignoreAllUnknown = paramBoolean1;
    this._anySetter = paramBeanDeserializerBuilder.getAnySetter();
    List localList = paramBeanDeserializerBuilder.getInjectables();
    ValueInjector[] arrayOfValueInjector;
    boolean bool2;
    label151:
    JsonFormat.Value localValue;
    if ((localList == null) || (localList.isEmpty()))
    {
      arrayOfValueInjector = null;
      this._injectables = arrayOfValueInjector;
      this._objectIdReader = paramBeanDeserializerBuilder.getObjectIdReader();
      if ((this._unwrappedPropertyHandler == null) && (!this._valueInstantiator.canCreateUsingDelegate()) && (!this._valueInstantiator.canCreateFromObjectWith()) && (this._valueInstantiator.canCreateUsingDefault())) {
        break label241;
      }
      bool2 = bool1;
      this._nonStandardCreation = bool2;
      localValue = paramBeanDescription.findExpectedFormat(null);
      if (localValue != null) {
        break label247;
      }
      label169:
      this._serializationShape = localShape;
      this._needViewProcesing = paramBoolean2;
      if ((this._nonStandardCreation) || (this._injectables != null) || (this._needViewProcesing) || (this._objectIdReader != null)) {
        break label257;
      }
    }
    for (;;)
    {
      this._vanillaProcessing = bool1;
      return;
      arrayOfValueInjector = (ValueInjector[])localList.toArray(new ValueInjector[localList.size()]);
      break;
      label241:
      bool2 = false;
      break label151;
      label247:
      localShape = localValue.getShape();
      break label169;
      label257:
      bool1 = false;
    }
  }
  
  private Throwable throwOrReturnThrowable(Throwable paramThrowable, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramDeserializationContext == null) || (paramDeserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0)
    {
      if ((i != 0) && ((paramThrowable instanceof JsonProcessingException))) {
        return paramThrowable;
      }
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    return paramThrowable;
  }
  
  protected Object _convertObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, JsonDeserializer<Object> paramJsonDeserializer)
    throws IOException
  {
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
    if ((paramObject instanceof String)) {
      localTokenBuffer.writeString((String)paramObject);
    }
    for (;;)
    {
      JsonParser localJsonParser = localTokenBuffer.asParser();
      localJsonParser.nextToken();
      return paramJsonDeserializer.deserialize(localJsonParser, paramDeserializationContext);
      if ((paramObject instanceof Long)) {
        localTokenBuffer.writeNumber(((Long)paramObject).longValue());
      } else if ((paramObject instanceof Integer)) {
        localTokenBuffer.writeNumber(((Integer)paramObject).intValue());
      } else {
        localTokenBuffer.writeObject(paramObject);
      }
    }
  }
  
  protected abstract Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException;
  
  /* Error */
  protected JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer)
    throws IOException, JsonProcessingException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 298	com/fasterxml/jackson/databind/deser/BeanDeserializerBase:_subDeserializers	Ljava/util/HashMap;
    //   6: ifnonnull +20 -> 26
    //   9: aconst_null
    //   10: astore 5
    //   12: aload_0
    //   13: monitorexit
    //   14: aload 5
    //   16: ifnull +43 -> 59
    //   19: aload 5
    //   21: astore 7
    //   23: goto +108 -> 131
    //   26: aload_0
    //   27: getfield 298	com/fasterxml/jackson/databind/deser/BeanDeserializerBase:_subDeserializers	Ljava/util/HashMap;
    //   30: new 300	com/fasterxml/jackson/databind/type/ClassKey
    //   33: dup
    //   34: aload_2
    //   35: invokevirtual 306	java/lang/Object:getClass	()Ljava/lang/Class;
    //   38: invokespecial 309	com/fasterxml/jackson/databind/type/ClassKey:<init>	(Ljava/lang/Class;)V
    //   41: invokevirtual 315	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   44: checkcast 267	com/fasterxml/jackson/databind/JsonDeserializer
    //   47: astore 5
    //   49: goto -37 -> 12
    //   52: astore 4
    //   54: aload_0
    //   55: monitorexit
    //   56: aload 4
    //   58: athrow
    //   59: aload_1
    //   60: aload_1
    //   61: aload_2
    //   62: invokevirtual 306	java/lang/Object:getClass	()Ljava/lang/Class;
    //   65: invokevirtual 319	com/fasterxml/jackson/databind/DeserializationContext:constructType	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JavaType;
    //   68: invokevirtual 323	com/fasterxml/jackson/databind/DeserializationContext:findRootValueDeserializer	(Lcom/fasterxml/jackson/databind/JavaType;)Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   71: astore 6
    //   73: aload 6
    //   75: ifnull +59 -> 134
    //   78: aload_0
    //   79: monitorenter
    //   80: aload_0
    //   81: getfield 298	com/fasterxml/jackson/databind/deser/BeanDeserializerBase:_subDeserializers	Ljava/util/HashMap;
    //   84: ifnonnull +14 -> 98
    //   87: aload_0
    //   88: new 311	java/util/HashMap
    //   91: dup
    //   92: invokespecial 325	java/util/HashMap:<init>	()V
    //   95: putfield 298	com/fasterxml/jackson/databind/deser/BeanDeserializerBase:_subDeserializers	Ljava/util/HashMap;
    //   98: aload_0
    //   99: getfield 298	com/fasterxml/jackson/databind/deser/BeanDeserializerBase:_subDeserializers	Ljava/util/HashMap;
    //   102: new 300	com/fasterxml/jackson/databind/type/ClassKey
    //   105: dup
    //   106: aload_2
    //   107: invokevirtual 306	java/lang/Object:getClass	()Ljava/lang/Class;
    //   110: invokespecial 309	com/fasterxml/jackson/databind/type/ClassKey:<init>	(Ljava/lang/Class;)V
    //   113: aload 6
    //   115: invokevirtual 329	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   118: pop
    //   119: aload_0
    //   120: monitorexit
    //   121: goto +13 -> 134
    //   124: astore 8
    //   126: aload_0
    //   127: monitorexit
    //   128: aload 8
    //   130: athrow
    //   131: aload 7
    //   133: areturn
    //   134: aload 6
    //   136: astore 7
    //   138: goto -7 -> 131
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	141	0	this	BeanDeserializerBase
    //   0	141	1	paramDeserializationContext	DeserializationContext
    //   0	141	2	paramObject	Object
    //   0	141	3	paramTokenBuffer	TokenBuffer
    //   52	5	4	localObject1	Object
    //   10	38	5	localJsonDeserializer1	JsonDeserializer
    //   71	64	6	localJsonDeserializer2	JsonDeserializer
    //   21	116	7	localObject2	Object
    //   124	5	8	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   2	56	52	finally
    //   80	128	124	finally
  }
  
  protected Object _handleTypedObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject1, Object paramObject2)
    throws IOException
  {
    JsonDeserializer localJsonDeserializer = this._objectIdReader.getDeserializer();
    if (localJsonDeserializer.handledType() == paramObject2.getClass()) {}
    for (Object localObject = paramObject2;; localObject = _convertObjectId(paramJsonParser, paramDeserializationContext, paramObject2, localJsonDeserializer))
    {
      paramDeserializationContext.findObjectId(localObject, this._objectIdReader.generator, this._objectIdReader.resolver).bindItem(paramObject1);
      SettableBeanProperty localSettableBeanProperty = this._objectIdReader.idProperty;
      if (localSettableBeanProperty != null) {
        paramObject1 = localSettableBeanProperty.setAndReturn(paramObject1, localObject);
      }
      return paramObject1;
    }
  }
  
  protected SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
  {
    JsonDeserializer localJsonDeserializer = paramSettableBeanProperty.getValueDeserializer();
    Class localClass2;
    Constructor[] arrayOfConstructor;
    int i;
    if (((localJsonDeserializer instanceof BeanDeserializerBase)) && (!((BeanDeserializerBase)localJsonDeserializer).getValueInstantiator().canCreateUsingDefault()))
    {
      Class localClass1 = paramSettableBeanProperty.getType().getRawClass();
      localClass2 = ClassUtil.getOuterClass(localClass1);
      if ((localClass2 != null) && (localClass2 == this._beanType.getRawClass()))
      {
        arrayOfConstructor = localClass1.getConstructors();
        i = arrayOfConstructor.length;
      }
    }
    for (int j = 0;; j++) {
      if (j < i)
      {
        Constructor localConstructor = arrayOfConstructor[j];
        Class[] arrayOfClass = localConstructor.getParameterTypes();
        if ((arrayOfClass.length == 1) && (arrayOfClass[0] == localClass2))
        {
          if (paramDeserializationContext.getConfig().canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(localConstructor);
          }
          paramSettableBeanProperty = new InnerClassProperty(paramSettableBeanProperty, localConstructor);
        }
      }
      else
      {
        return paramSettableBeanProperty;
      }
    }
  }
  
  protected SettableBeanProperty _resolveManagedReferenceProperty(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
  {
    String str = paramSettableBeanProperty.getManagedReferenceName();
    if (str == null) {}
    for (;;)
    {
      return paramSettableBeanProperty;
      SettableBeanProperty localSettableBeanProperty = paramSettableBeanProperty.getValueDeserializer().findBackReference(str);
      if (localSettableBeanProperty == null) {
        throw new IllegalArgumentException("Can not handle managed/back reference '" + str + "': no back reference property found from type " + paramSettableBeanProperty.getType());
      }
      JavaType localJavaType1 = this._beanType;
      JavaType localJavaType2 = localSettableBeanProperty.getType();
      boolean bool = paramSettableBeanProperty.getType().isContainerType();
      if (!localJavaType2.getRawClass().isAssignableFrom(localJavaType1.getRawClass())) {
        throw new IllegalArgumentException("Can not handle managed/back reference '" + str + "': back reference type (" + localJavaType2.getRawClass().getName() + ") not compatible with managed type (" + localJavaType1.getRawClass().getName() + ")");
      }
      Annotations localAnnotations = this._classAnnotations;
      paramSettableBeanProperty = new ManagedReferenceProperty(paramSettableBeanProperty, str, localSettableBeanProperty, localAnnotations, bool);
    }
  }
  
  protected SettableBeanProperty _resolveUnwrappedProperty(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
  {
    AnnotatedMember localAnnotatedMember = paramSettableBeanProperty.getMember();
    JsonDeserializer localJsonDeserializer2;
    if (localAnnotatedMember != null)
    {
      NameTransformer localNameTransformer = paramDeserializationContext.getAnnotationIntrospector().findUnwrappingNameTransformer(localAnnotatedMember);
      if (localNameTransformer != null)
      {
        JsonDeserializer localJsonDeserializer1 = paramSettableBeanProperty.getValueDeserializer();
        localJsonDeserializer2 = localJsonDeserializer1.unwrappingDeserializer(localNameTransformer);
        if ((localJsonDeserializer2 == localJsonDeserializer1) || (localJsonDeserializer2 == null)) {}
      }
    }
    for (SettableBeanProperty localSettableBeanProperty = paramSettableBeanProperty.withValueDeserializer(localJsonDeserializer2);; localSettableBeanProperty = null) {
      return localSettableBeanProperty;
    }
  }
  
  protected SettableBeanProperty _resolvedObjectIdProperty(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
  {
    ObjectIdInfo localObjectIdInfo = paramSettableBeanProperty.getObjectIdInfo();
    ObjectIdReader localObjectIdReader = paramSettableBeanProperty.getValueDeserializer().getObjectIdReader();
    if ((localObjectIdInfo == null) && (localObjectIdReader == null)) {}
    for (;;)
    {
      return paramSettableBeanProperty;
      paramSettableBeanProperty = new ObjectIdReferenceProperty(paramSettableBeanProperty, localObjectIdInfo);
    }
  }
  
  protected abstract BeanDeserializerBase asArrayDeserializer();
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    ObjectIdReader localObjectIdReader1 = this._objectIdReader;
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    Object localObject1;
    if ((paramBeanProperty == null) || (localAnnotationIntrospector == null)) {
      localObject1 = null;
    }
    ObjectIdInfo localObjectIdInfo2;
    Class localClass;
    ObjectIdResolver localObjectIdResolver;
    SettableBeanProperty localSettableBeanProperty;
    JavaType localJavaType2;
    while ((localObject1 != null) && (localAnnotationIntrospector != null))
    {
      ObjectIdInfo localObjectIdInfo1 = localAnnotationIntrospector.findObjectIdInfo((Annotated)localObject1);
      if (localObjectIdInfo1 != null)
      {
        localObjectIdInfo2 = localAnnotationIntrospector.findObjectReferenceInfo((Annotated)localObject1, localObjectIdInfo1);
        localClass = localObjectIdInfo2.getGeneratorType();
        localObjectIdResolver = paramDeserializationContext.objectIdResolverInstance((Annotated)localObject1, localObjectIdInfo2);
        if (localClass != ObjectIdGenerators.PropertyGenerator.class) {
          break label345;
        }
        PropertyName localPropertyName = localObjectIdInfo2.getPropertyName();
        localSettableBeanProperty = findProperty(localPropertyName);
        if (localSettableBeanProperty == null)
        {
          throw new IllegalArgumentException("Invalid Object Id definition for " + handledType().getName() + ": can not find property with name '" + localPropertyName + "'");
          localObject1 = paramBeanProperty.getMember();
        }
        else
        {
          localJavaType2 = localSettableBeanProperty.getType();
        }
      }
    }
    for (Object localObject2 = new PropertyBasedObjectIdGenerator(localObjectIdInfo2.getScope());; localObject2 = paramDeserializationContext.objectIdGeneratorInstance((Annotated)localObject1, localObjectIdInfo2))
    {
      JsonDeserializer localJsonDeserializer = paramDeserializationContext.findRootValueDeserializer(localJavaType2);
      localObjectIdReader1 = ObjectIdReader.construct(localJavaType2, localObjectIdInfo2.getPropertyName(), (ObjectIdGenerator)localObject2, localJsonDeserializer, localSettableBeanProperty, localObjectIdResolver);
      BeanDeserializerBase localBeanDeserializerBase = this;
      if (localObjectIdReader1 != null)
      {
        ObjectIdReader localObjectIdReader2 = this._objectIdReader;
        if (localObjectIdReader1 != localObjectIdReader2) {
          localBeanDeserializerBase = localBeanDeserializerBase.withObjectIdReader(localObjectIdReader1);
        }
      }
      if (localObject1 != null)
      {
        String[] arrayOfString = localAnnotationIntrospector.findPropertiesToIgnore((Annotated)localObject1, false);
        if ((arrayOfString != null) && (arrayOfString.length != 0)) {
          localBeanDeserializerBase = localBeanDeserializerBase.withIgnorableProperties(ArrayBuilders.setAndArray(localBeanDeserializerBase._ignorableProps, arrayOfString));
        }
      }
      JsonFormat.Shape localShape1 = null;
      if (localObject1 != null)
      {
        JsonFormat.Value localValue = localAnnotationIntrospector.findFormat((Annotated)localObject1);
        if (localValue != null) {
          localShape1 = localValue.getShape();
        }
      }
      if (localShape1 == null) {
        localShape1 = this._serializationShape;
      }
      JsonFormat.Shape localShape2 = JsonFormat.Shape.ARRAY;
      if (localShape1 == localShape2) {
        localBeanDeserializerBase = localBeanDeserializerBase.asArrayDeserializer();
      }
      return localBeanDeserializerBase;
      label345:
      JavaType localJavaType1 = paramDeserializationContext.constructType(localClass);
      localJavaType2 = paramDeserializationContext.getTypeFactory().findTypeParameters(localJavaType1, ObjectIdGenerator.class)[0];
      localSettableBeanProperty = null;
    }
  }
  
  public Iterator<SettableBeanProperty> creatorProperties()
  {
    if (this._propertyBasedCreator == null) {}
    for (Iterator localIterator = Collections.emptyList().iterator();; localIterator = this._propertyBasedCreator.properties().iterator()) {
      return localIterator;
    }
  }
  
  public Object deserializeFromArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._delegateDeserializer != null) {}
    for (;;)
    {
      Object localObject1;
      try
      {
        localObject1 = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
        if (this._injectables != null) {
          injectValues(paramDeserializationContext, localObject1);
        }
        return localObject1;
      }
      catch (Exception localException)
      {
        wrapInstantiationProblem(localException, paramDeserializationContext);
      }
      if (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))
      {
        if ((paramJsonParser.nextToken() == JsonToken.END_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)))
        {
          localObject1 = null;
        }
        else
        {
          Object localObject2 = deserialize(paramJsonParser, paramDeserializationContext);
          if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
            throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
          }
          localObject1 = localObject2;
        }
      }
      else
      {
        if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
          break label186;
        }
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          break;
        }
        localObject1 = null;
      }
    }
    throw paramDeserializationContext.mappingException(handledType(), JsonToken.START_ARRAY);
    label186:
    throw paramDeserializationContext.mappingException(handledType());
  }
  
  public Object deserializeFromBoolean(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if ((this._delegateDeserializer != null) && (!this._valueInstantiator.canCreateFromBoolean()))
    {
      localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      if (this._injectables != null) {
        injectValues(paramDeserializationContext, localObject);
      }
      return localObject;
    }
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_TRUE) {}
    for (boolean bool = true;; bool = false)
    {
      localObject = this._valueInstantiator.createFromBoolean(paramDeserializationContext, bool);
      break;
    }
  }
  
  public Object deserializeFromDouble(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    switch (paramJsonParser.getNumberType())
    {
    default: 
      if (this._delegateDeserializer != null) {
        localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      }
      break;
    case ???: 
    case ???: 
      for (;;)
      {
        return localObject;
        if ((this._delegateDeserializer != null) && (!this._valueInstantiator.canCreateFromDouble()))
        {
          localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
          if (this._injectables != null) {
            injectValues(paramDeserializationContext, localObject);
          }
        }
        else
        {
          localObject = this._valueInstantiator.createFromDouble(paramDeserializationContext, paramJsonParser.getDoubleValue());
        }
      }
    }
    throw paramDeserializationContext.instantiationException(handledType(), "no suitable creator method found to deserialize from JSON floating-point number");
  }
  
  public Object deserializeFromEmbedded(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._objectIdReader != null) {}
    for (Object localObject = deserializeFromObjectId(paramJsonParser, paramDeserializationContext);; localObject = paramJsonParser.getEmbeddedObject()) {
      return localObject;
    }
  }
  
  public Object deserializeFromNumber(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (this._objectIdReader != null) {
      localObject = deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
    }
    for (;;)
    {
      return localObject;
      switch (paramJsonParser.getNumberType())
      {
      default: 
        if (this._delegateDeserializer == null) {
          break label223;
        }
        localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
        if (this._injectables != null) {
          injectValues(paramDeserializationContext, localObject);
        }
        break;
      case ???: 
        if ((this._delegateDeserializer != null) && (!this._valueInstantiator.canCreateFromInt()))
        {
          localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
          if (this._injectables != null) {
            injectValues(paramDeserializationContext, localObject);
          }
        }
        else
        {
          localObject = this._valueInstantiator.createFromInt(paramDeserializationContext, paramJsonParser.getIntValue());
        }
        break;
      case ???: 
        if ((this._delegateDeserializer != null) && (!this._valueInstantiator.canCreateFromInt()))
        {
          localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
          if (this._injectables != null) {
            injectValues(paramDeserializationContext, localObject);
          }
        }
        else
        {
          localObject = this._valueInstantiator.createFromLong(paramDeserializationContext, paramJsonParser.getLongValue());
        }
        break;
      }
    }
    label223:
    throw paramDeserializationContext.instantiationException(handledType(), "no suitable creator method found to deserialize from JSON integer number");
  }
  
  public abstract Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException;
  
  protected Object deserializeFromObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1 = this._objectIdReader.readObjectReference(paramJsonParser, paramDeserializationContext);
    ReadableObjectId localReadableObjectId = paramDeserializationContext.findObjectId(localObject1, this._objectIdReader.generator, this._objectIdReader.resolver);
    Object localObject2 = localReadableObjectId.resolve();
    if (localObject2 == null) {
      throw new UnresolvedForwardReference("Could not resolve Object Id [" + localObject1 + "] (for " + this._beanType + ").", paramJsonParser.getCurrentLocation(), localReadableObjectId);
    }
    return localObject2;
  }
  
  protected Object deserializeFromObjectUsingNonDefault(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._delegateDeserializer != null) {}
    for (Object localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));; localObject = _deserializeUsingPropertyBased(paramJsonParser, paramDeserializationContext))
    {
      return localObject;
      if (this._propertyBasedCreator == null) {
        break;
      }
    }
    if (this._beanType.isAbstract()) {
      throw JsonMappingException.from(paramJsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
    }
    throw JsonMappingException.from(paramJsonParser, "No suitable constructor found for type " + this._beanType + ": can not instantiate from JSON object (missing default constructor or creator, or perhaps need to add/enable type information?)");
  }
  
  public Object deserializeFromString(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (this._objectIdReader != null) {
      localObject = deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
    }
    for (;;)
    {
      return localObject;
      if ((this._delegateDeserializer != null) && (!this._valueInstantiator.canCreateFromString()))
      {
        localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
        if (this._injectables != null) {
          injectValues(paramDeserializationContext, localObject);
        }
      }
      else
      {
        localObject = this._valueInstantiator.createFromString(paramDeserializationContext, paramJsonParser.getText());
      }
    }
  }
  
  protected Object deserializeWithObjectId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return deserializeFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    Object localObject1;
    if (this._objectIdReader != null) {
      if (paramJsonParser.canReadObjectId())
      {
        Object localObject2 = paramJsonParser.getObjectId();
        if (localObject2 != null) {
          localObject1 = _handleTypedObjectId(paramJsonParser, paramDeserializationContext, paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext), localObject2);
        }
      }
    }
    for (;;)
    {
      return localObject1;
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      if (localJsonToken != null)
      {
        if (localJsonToken.isScalarValue())
        {
          localObject1 = deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
          continue;
        }
        if (localJsonToken == JsonToken.START_OBJECT) {
          localJsonToken = paramJsonParser.nextToken();
        }
        if ((localJsonToken == JsonToken.FIELD_NAME) && (this._objectIdReader.maySerializeAsObject()) && (this._objectIdReader.isValidReferencePropertyName(paramJsonParser.getCurrentName(), paramJsonParser)))
        {
          localObject1 = deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
          continue;
        }
      }
      localObject1 = paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
    }
  }
  
  public SettableBeanProperty findBackReference(String paramString)
  {
    if (this._backRefs == null) {}
    for (SettableBeanProperty localSettableBeanProperty = null;; localSettableBeanProperty = (SettableBeanProperty)this._backRefs.get(paramString)) {
      return localSettableBeanProperty;
    }
  }
  
  protected JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramDeserializationContext.getAnnotationIntrospector();
    Converter localConverter;
    JavaType localJavaType;
    if (localAnnotationIntrospector != null)
    {
      Object localObject = localAnnotationIntrospector.findDeserializationConverter(paramSettableBeanProperty.getMember());
      if (localObject != null)
      {
        localConverter = paramDeserializationContext.converterInstance(paramSettableBeanProperty.getMember(), localObject);
        localJavaType = localConverter.getInputType(paramDeserializationContext.getTypeFactory());
      }
    }
    for (StdDelegatingDeserializer localStdDelegatingDeserializer = new StdDelegatingDeserializer(localConverter, localJavaType, paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramSettableBeanProperty));; localStdDelegatingDeserializer = null) {
      return localStdDelegatingDeserializer;
    }
  }
  
  public SettableBeanProperty findProperty(int paramInt)
  {
    if (this._beanProperties == null) {}
    for (SettableBeanProperty localSettableBeanProperty = null;; localSettableBeanProperty = this._beanProperties.find(paramInt))
    {
      if ((localSettableBeanProperty == null) && (this._propertyBasedCreator != null)) {
        localSettableBeanProperty = this._propertyBasedCreator.findCreatorProperty(paramInt);
      }
      return localSettableBeanProperty;
    }
  }
  
  public SettableBeanProperty findProperty(PropertyName paramPropertyName)
  {
    return findProperty(paramPropertyName.getSimpleName());
  }
  
  public SettableBeanProperty findProperty(String paramString)
  {
    if (this._beanProperties == null) {}
    for (SettableBeanProperty localSettableBeanProperty = null;; localSettableBeanProperty = this._beanProperties.find(paramString))
    {
      if ((localSettableBeanProperty == null) && (this._propertyBasedCreator != null)) {
        localSettableBeanProperty = this._propertyBasedCreator.findCreatorProperty(paramString);
      }
      return localSettableBeanProperty;
    }
  }
  
  @Deprecated
  public final Class<?> getBeanClass()
  {
    return this._beanType.getRawClass();
  }
  
  public Collection<Object> getKnownPropertyNames()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this._beanProperties.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((SettableBeanProperty)localIterator.next()).getName());
    }
    return localArrayList;
  }
  
  public ObjectIdReader getObjectIdReader()
  {
    return this._objectIdReader;
  }
  
  public int getPropertyCount()
  {
    return this._beanProperties.size();
  }
  
  public ValueInstantiator getValueInstantiator()
  {
    return this._valueInstantiator;
  }
  
  public JavaType getValueType()
  {
    return this._beanType;
  }
  
  protected void handleIgnoredProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException, JsonProcessingException
  {
    if (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)) {
      throw IgnoredPropertyException.from(paramJsonParser, paramObject, paramString, getKnownPropertyNames());
    }
    paramJsonParser.skipChildren();
  }
  
  protected Object handlePolymorphic(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer)
    throws IOException, JsonProcessingException
  {
    JsonDeserializer localJsonDeserializer = _findSubclassDeserializer(paramDeserializationContext, paramObject, paramTokenBuffer);
    if (localJsonDeserializer != null)
    {
      if (paramTokenBuffer != null)
      {
        paramTokenBuffer.writeEndObject();
        JsonParser localJsonParser = paramTokenBuffer.asParser();
        localJsonParser.nextToken();
        paramObject = localJsonDeserializer.deserialize(localJsonParser, paramDeserializationContext, paramObject);
      }
      if (paramJsonParser != null) {
        paramObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext, paramObject);
      }
    }
    for (Object localObject = paramObject;; localObject = paramObject)
    {
      return localObject;
      if (paramTokenBuffer != null) {
        paramObject = handleUnknownProperties(paramDeserializationContext, paramObject, paramTokenBuffer);
      }
      if (paramJsonParser != null) {
        paramObject = deserialize(paramJsonParser, paramDeserializationContext, paramObject);
      }
    }
  }
  
  protected Object handleUnknownProperties(DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer)
    throws IOException, JsonProcessingException
  {
    paramTokenBuffer.writeEndObject();
    JsonParser localJsonParser = paramTokenBuffer.asParser();
    while (localJsonParser.nextToken() != JsonToken.END_OBJECT)
    {
      String str = localJsonParser.getCurrentName();
      localJsonParser.nextToken();
      handleUnknownProperty(localJsonParser, paramDeserializationContext, paramObject, str);
    }
    return paramObject;
  }
  
  protected void handleUnknownProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException, JsonProcessingException
  {
    if (this._ignoreAllUnknown) {
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      return;
      if ((this._ignorableProps != null) && (this._ignorableProps.contains(paramString))) {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
      }
      super.handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
    }
  }
  
  protected void handleUnknownVanilla(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString)
    throws IOException, JsonProcessingException
  {
    if ((this._ignorableProps != null) && (this._ignorableProps.contains(paramString))) {
      handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
    }
    for (;;)
    {
      return;
      if (this._anySetter != null) {
        try
        {
          this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, paramString);
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, paramObject, paramString, paramDeserializationContext);
        }
      } else {
        handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
      }
    }
  }
  
  public Class<?> handledType()
  {
    return this._beanType.getRawClass();
  }
  
  public boolean hasProperty(String paramString)
  {
    if (this._beanProperties.find(paramString) != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasViews()
  {
    return this._needViewProcesing;
  }
  
  protected void injectValues(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    ValueInjector[] arrayOfValueInjector = this._injectables;
    int i = arrayOfValueInjector.length;
    for (int j = 0; j < i; j++) {
      arrayOfValueInjector[j].inject(paramDeserializationContext, paramObject);
    }
  }
  
  public boolean isCachable()
  {
    return true;
  }
  
  public Iterator<SettableBeanProperty> properties()
  {
    if (this._beanProperties == null) {
      throw new IllegalStateException("Can only call after BeanDeserializer has been resolved");
    }
    return this._beanProperties.iterator();
  }
  
  public void replaceProperty(SettableBeanProperty paramSettableBeanProperty1, SettableBeanProperty paramSettableBeanProperty2)
  {
    this._beanProperties.replace(paramSettableBeanProperty2);
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    ExternalTypeHandler.Builder localBuilder = null;
    if (this._valueInstantiator.canCreateFromObjectWith())
    {
      arrayOfSettableBeanProperty1 = this._valueInstantiator.getFromObjectArguments(paramDeserializationContext.getConfig());
      for (SettableBeanProperty localSettableBeanProperty6 : arrayOfSettableBeanProperty1) {
        if (localSettableBeanProperty6.hasValueTypeDeserializer())
        {
          TypeDeserializer localTypeDeserializer3 = localSettableBeanProperty6.getValueTypeDeserializer();
          if (localTypeDeserializer3.getTypeInclusion() == JsonTypeInfo.As.EXTERNAL_PROPERTY)
          {
            if (localBuilder == null) {
              localBuilder = new ExternalTypeHandler.Builder();
            }
            localBuilder.addExternal(localSettableBeanProperty6, localTypeDeserializer3);
          }
        }
      }
    }
    SettableBeanProperty[] arrayOfSettableBeanProperty1 = null;
    UnwrappedPropertyHandler localUnwrappedPropertyHandler = null;
    Iterator localIterator = this._beanProperties.iterator();
    if (localIterator.hasNext())
    {
      SettableBeanProperty localSettableBeanProperty1 = (SettableBeanProperty)localIterator.next();
      SettableBeanProperty localSettableBeanProperty2 = localSettableBeanProperty1;
      if (!localSettableBeanProperty2.hasValueDeserializer())
      {
        JsonDeserializer localJsonDeserializer3 = findConvertingDeserializer(paramDeserializationContext, localSettableBeanProperty2);
        if (localJsonDeserializer3 == null) {
          localJsonDeserializer3 = findDeserializer(paramDeserializationContext, localSettableBeanProperty2.getType(), localSettableBeanProperty2);
        }
        localSettableBeanProperty2 = localSettableBeanProperty2.withValueDeserializer(localJsonDeserializer3);
      }
      SettableBeanProperty localSettableBeanProperty3;
      for (;;)
      {
        localSettableBeanProperty3 = _resolveManagedReferenceProperty(paramDeserializationContext, localSettableBeanProperty2);
        if (!(localSettableBeanProperty3 instanceof ManagedReferenceProperty)) {
          localSettableBeanProperty3 = _resolvedObjectIdProperty(paramDeserializationContext, localSettableBeanProperty3);
        }
        SettableBeanProperty localSettableBeanProperty4 = _resolveUnwrappedProperty(paramDeserializationContext, localSettableBeanProperty3);
        if (localSettableBeanProperty4 == null) {
          break label304;
        }
        if (localUnwrappedPropertyHandler == null) {
          localUnwrappedPropertyHandler = new UnwrappedPropertyHandler();
        }
        localUnwrappedPropertyHandler.addProperty(localSettableBeanProperty4);
        this._beanProperties.remove(localSettableBeanProperty4);
        break;
        JsonDeserializer localJsonDeserializer1 = localSettableBeanProperty2.getValueDeserializer();
        JavaType localJavaType2 = localSettableBeanProperty2.getType();
        JsonDeserializer localJsonDeserializer2 = paramDeserializationContext.handlePrimaryContextualization(localJsonDeserializer1, localSettableBeanProperty2, localJavaType2);
        if (localJsonDeserializer2 != localJsonDeserializer1) {
          localSettableBeanProperty2 = localSettableBeanProperty2.withValueDeserializer(localJsonDeserializer2);
        }
      }
      label304:
      SettableBeanProperty localSettableBeanProperty5 = _resolveInnerClassValuedProperty(paramDeserializationContext, localSettableBeanProperty3);
      int i;
      int j;
      if (localSettableBeanProperty5 != localSettableBeanProperty1)
      {
        this._beanProperties.replace(localSettableBeanProperty5);
        if (arrayOfSettableBeanProperty1 != null)
        {
          i = 0;
          j = arrayOfSettableBeanProperty1.length;
        }
      }
      for (;;)
      {
        if (i < j)
        {
          if (arrayOfSettableBeanProperty1[i] == localSettableBeanProperty1) {
            arrayOfSettableBeanProperty1[i] = localSettableBeanProperty5;
          }
        }
        else
        {
          if (!localSettableBeanProperty5.hasValueTypeDeserializer()) {
            break;
          }
          TypeDeserializer localTypeDeserializer2 = localSettableBeanProperty5.getValueTypeDeserializer();
          if (localTypeDeserializer2.getTypeInclusion() != JsonTypeInfo.As.EXTERNAL_PROPERTY) {
            break;
          }
          if (localBuilder == null) {
            localBuilder = new ExternalTypeHandler.Builder();
          }
          localBuilder.addExternal(localSettableBeanProperty5, localTypeDeserializer2);
          this._beanProperties.remove(localSettableBeanProperty5);
          break;
        }
        i++;
      }
    }
    if ((this._anySetter != null) && (!this._anySetter.hasValueDeserializer())) {
      this._anySetter = this._anySetter.withValueDeserializer(findDeserializer(paramDeserializationContext, this._anySetter.getType(), this._anySetter.getProperty()));
    }
    if (this._valueInstantiator.canCreateUsingDelegate())
    {
      JavaType localJavaType1 = this._valueInstantiator.getDelegateType(paramDeserializationContext.getConfig());
      if (localJavaType1 == null) {
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._beanType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
      }
      AnnotatedWithParams localAnnotatedWithParams = this._valueInstantiator.getDelegateCreator();
      BeanProperty.Std localStd = new BeanProperty.Std(TEMP_PROPERTY_NAME, localJavaType1, null, this._classAnnotations, localAnnotatedWithParams, PropertyMetadata.STD_OPTIONAL);
      TypeDeserializer localTypeDeserializer1 = (TypeDeserializer)localJavaType1.getTypeHandler();
      if (localTypeDeserializer1 == null) {
        localTypeDeserializer1 = paramDeserializationContext.getConfig().findTypeDeserializer(localJavaType1);
      }
      Object localObject = findDeserializer(paramDeserializationContext, localJavaType1, localStd);
      if (localTypeDeserializer1 != null) {
        localObject = new TypeWrappedDeserializer(localTypeDeserializer1.forProperty(localStd), (JsonDeserializer)localObject);
      }
      this._delegateDeserializer = ((JsonDeserializer)localObject);
    }
    if (arrayOfSettableBeanProperty1 != null) {
      this._propertyBasedCreator = PropertyBasedCreator.construct(paramDeserializationContext, this._valueInstantiator, arrayOfSettableBeanProperty1);
    }
    if (localBuilder != null)
    {
      this._externalTypeIdHandler = localBuilder.build();
      this._nonStandardCreation = true;
    }
    this._unwrappedPropertyHandler = localUnwrappedPropertyHandler;
    if (localUnwrappedPropertyHandler != null) {
      this._nonStandardCreation = true;
    }
    if ((this._vanillaProcessing) && (!this._nonStandardCreation)) {}
    for (boolean bool = true;; bool = false)
    {
      this._vanillaProcessing = bool;
      return;
    }
  }
  
  public abstract JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer);
  
  public abstract BeanDeserializerBase withIgnorableProperties(HashSet<String> paramHashSet);
  
  public abstract BeanDeserializerBase withObjectIdReader(ObjectIdReader paramObjectIdReader);
  
  @Deprecated
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, int paramInt, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(paramThrowable, paramDeserializationContext), paramObject, paramInt);
  }
  
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(paramThrowable, paramDeserializationContext), paramObject, paramString);
  }
  
  protected void wrapInstantiationProblem(Throwable paramThrowable, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramDeserializationContext == null) || (paramDeserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0) {
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    throw paramDeserializationContext.instantiationException(this._beanType.getRawClass(), paramThrowable);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/BeanDeserializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */