package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer;
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer;
import com.fasterxml.jackson.databind.ser.std.AtomicReferenceSerializer;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.ByteBufferSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSetSerializer;
import com.fasterxml.jackson.databind.ser.std.InetAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.InetSocketAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlTimeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.TokenBufferSerializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.RandomAccess;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicSerializerFactory
  extends SerializerFactory
  implements Serializable
{
  protected static final HashMap<String, JsonSerializer<?>> _concrete = new HashMap();
  protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy = new HashMap();
  protected final SerializerFactoryConfig _factoryConfig;
  
  static
  {
    _concrete.put(String.class.getName(), new StringSerializer());
    ToStringSerializer localToStringSerializer = ToStringSerializer.instance;
    _concrete.put(StringBuffer.class.getName(), localToStringSerializer);
    _concrete.put(StringBuilder.class.getName(), localToStringSerializer);
    _concrete.put(Character.class.getName(), localToStringSerializer);
    _concrete.put(Character.TYPE.getName(), localToStringSerializer);
    NumberSerializers.addAll(_concrete);
    _concrete.put(Boolean.TYPE.getName(), new BooleanSerializer(true));
    _concrete.put(Boolean.class.getName(), new BooleanSerializer(false));
    _concrete.put(BigInteger.class.getName(), new NumberSerializer(BigInteger.class));
    _concrete.put(BigDecimal.class.getName(), new NumberSerializer(BigDecimal.class));
    _concrete.put(Calendar.class.getName(), CalendarSerializer.instance);
    DateSerializer localDateSerializer = DateSerializer.instance;
    _concrete.put(java.util.Date.class.getName(), localDateSerializer);
    _concrete.put(Timestamp.class.getName(), localDateSerializer);
    _concreteLazy.put(java.sql.Date.class.getName(), SqlDateSerializer.class);
    _concreteLazy.put(Time.class.getName(), SqlTimeSerializer.class);
    Iterator localIterator = StdJdkSerializers.all().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = localEntry.getValue();
      if ((localObject instanceof JsonSerializer))
      {
        _concrete.put(((Class)localEntry.getKey()).getName(), (JsonSerializer)localObject);
      }
      else if ((localObject instanceof Class))
      {
        Class localClass = (Class)localObject;
        _concreteLazy.put(((Class)localEntry.getKey()).getName(), localClass);
      }
      else
      {
        throw new IllegalStateException("Internal error: unrecognized value of type " + localEntry.getClass().getName());
      }
    }
    _concreteLazy.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
  }
  
  protected BasicSerializerFactory(SerializerFactoryConfig paramSerializerFactoryConfig)
  {
    if (paramSerializerFactoryConfig == null) {
      paramSerializerFactoryConfig = new SerializerFactoryConfig();
    }
    this._factoryConfig = paramSerializerFactoryConfig;
  }
  
  /* Error */
  protected static <T extends JavaType> T modifySecondaryTypesByAnnotation(SerializationConfig paramSerializationConfig, Annotated paramAnnotated, T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 178	com/fasterxml/jackson/databind/SerializationConfig:getAnnotationIntrospector	()Lcom/fasterxml/jackson/databind/AnnotationIntrospector;
    //   4: astore_3
    //   5: aload_2
    //   6: invokevirtual 183	com/fasterxml/jackson/databind/JavaType:isContainerType	()Z
    //   9: ifeq +99 -> 108
    //   12: aload_3
    //   13: aload_1
    //   14: aload_2
    //   15: invokevirtual 187	com/fasterxml/jackson/databind/JavaType:getKeyType	()Lcom/fasterxml/jackson/databind/JavaType;
    //   18: invokevirtual 193	com/fasterxml/jackson/databind/AnnotationIntrospector:findSerializationKeyType	(Lcom/fasterxml/jackson/databind/introspect/Annotated;Lcom/fasterxml/jackson/databind/JavaType;)Ljava/lang/Class;
    //   21: astore 4
    //   23: aload 4
    //   25: ifnull +56 -> 81
    //   28: aload_2
    //   29: instanceof 195
    //   32: ifne +35 -> 67
    //   35: new 172	java/lang/IllegalArgumentException
    //   38: dup
    //   39: new 51	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   46: ldc -59
    //   48: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: aload_2
    //   52: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   55: ldc -54
    //   57: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokespecial 203	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   66: athrow
    //   67: aload_2
    //   68: checkcast 195	com/fasterxml/jackson/databind/type/MapType
    //   71: aload 4
    //   73: invokevirtual 207	com/fasterxml/jackson/databind/type/MapType:widenKey	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JavaType;
    //   76: astore 9
    //   78: aload 9
    //   80: astore_2
    //   81: aload_3
    //   82: aload_1
    //   83: aload_2
    //   84: invokevirtual 210	com/fasterxml/jackson/databind/JavaType:getContentType	()Lcom/fasterxml/jackson/databind/JavaType;
    //   87: invokevirtual 213	com/fasterxml/jackson/databind/AnnotationIntrospector:findSerializationContentType	(Lcom/fasterxml/jackson/databind/introspect/Annotated;Lcom/fasterxml/jackson/databind/JavaType;)Ljava/lang/Class;
    //   90: astore 5
    //   92: aload 5
    //   94: ifnull +14 -> 108
    //   97: aload_2
    //   98: aload 5
    //   100: invokevirtual 216	com/fasterxml/jackson/databind/JavaType:widenContentsBy	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JavaType;
    //   103: astore 7
    //   105: aload 7
    //   107: astore_2
    //   108: aload_2
    //   109: areturn
    //   110: astore 8
    //   112: new 172	java/lang/IllegalArgumentException
    //   115: dup
    //   116: new 51	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   123: ldc -38
    //   125: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: aload_2
    //   129: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   132: ldc -36
    //   134: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload 4
    //   139: invokevirtual 34	java/lang/Class:getName	()Ljava/lang/String;
    //   142: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc -34
    //   147: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: aload 8
    //   152: invokevirtual 225	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   155: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   161: invokespecial 203	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   164: athrow
    //   165: astore 6
    //   167: new 172	java/lang/IllegalArgumentException
    //   170: dup
    //   171: new 51	java/lang/StringBuilder
    //   174: dup
    //   175: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   178: ldc -29
    //   180: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   183: aload_2
    //   184: invokevirtual 200	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   187: ldc -27
    //   189: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: aload 5
    //   194: invokevirtual 34	java/lang/Class:getName	()Ljava/lang/String;
    //   197: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: ldc -34
    //   202: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: aload 6
    //   207: invokevirtual 225	java/lang/IllegalArgumentException:getMessage	()Ljava/lang/String;
    //   210: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   216: invokespecial 203	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   219: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	220	0	paramSerializationConfig	SerializationConfig
    //   0	220	1	paramAnnotated	Annotated
    //   0	220	2	paramT	T
    //   4	78	3	localAnnotationIntrospector	AnnotationIntrospector
    //   21	117	4	localClass1	Class
    //   90	103	5	localClass2	Class
    //   165	41	6	localIllegalArgumentException1	IllegalArgumentException
    //   103	3	7	localJavaType1	JavaType
    //   110	41	8	localIllegalArgumentException2	IllegalArgumentException
    //   76	3	9	localJavaType2	JavaType
    // Exception table:
    //   from	to	target	type
    //   67	78	110	java/lang/IllegalArgumentException
    //   97	105	165	java/lang/IllegalArgumentException
  }
  
  protected JsonSerializer<Object> _findContentSerializer(SerializerProvider paramSerializerProvider, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject = paramSerializerProvider.getAnnotationIntrospector().findContentSerializer(paramAnnotated);
    if (localObject != null) {}
    for (JsonSerializer localJsonSerializer = paramSerializerProvider.serializerInstance(paramAnnotated, localObject);; localJsonSerializer = null) {
      return localJsonSerializer;
    }
  }
  
  protected JsonSerializer<Object> _findKeySerializer(SerializerProvider paramSerializerProvider, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject = paramSerializerProvider.getAnnotationIntrospector().findKeySerializer(paramAnnotated);
    if (localObject != null) {}
    for (JsonSerializer localJsonSerializer = paramSerializerProvider.serializerInstance(paramAnnotated, localObject);; localJsonSerializer = null) {
      return localJsonSerializer;
    }
  }
  
  protected Class<?> _verifyAsClass(Object paramObject, String paramString, Class<?> paramClass)
  {
    if (paramObject == null) {}
    for (Object localObject = null;; localObject = null) {
      do
      {
        return (Class<?>)localObject;
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector." + paramString + "() returned value of type " + paramObject.getClass().getName() + ": expected type JsonSerializer or Class<JsonSerializer> instead");
        }
        localObject = (Class)paramObject;
      } while ((localObject != paramClass) && (!ClassUtil.isBogusClass((Class)localObject)));
    }
  }
  
  protected JsonSerializer<?> buildArraySerializer(SerializerProvider paramSerializerProvider, ArrayType paramArrayType, BeanDescription paramBeanDescription, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
    throws JsonMappingException
  {
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    Object localObject = null;
    Iterator localIterator1 = customSerializers().iterator();
    do
    {
      if (!localIterator1.hasNext()) {
        break;
      }
      localObject = ((Serializers)localIterator1.next()).findArraySerializer(localSerializationConfig, paramArrayType, paramBeanDescription, paramTypeSerializer, paramJsonSerializer);
    } while (localObject == null);
    Class localClass;
    if (localObject == null)
    {
      localClass = paramArrayType.getRawClass();
      if ((paramJsonSerializer == null) || (ClassUtil.isJacksonStdImpl(paramJsonSerializer))) {
        if (String[].class != localClass) {
          break label179;
        }
      }
    }
    label179:
    for (localObject = StringArraySerializer.instance;; localObject = StdArraySerializers.findStandardImpl(localClass))
    {
      if (localObject == null) {
        localObject = new ObjectArraySerializer(paramArrayType.getContentType(), paramBoolean, paramTypeSerializer, paramJsonSerializer);
      }
      if (!this._factoryConfig.hasSerializerModifiers()) {
        break;
      }
      Iterator localIterator2 = this._factoryConfig.serializerModifiers().iterator();
      while (localIterator2.hasNext()) {
        localObject = ((BeanSerializerModifier)localIterator2.next()).modifyArraySerializer(localSerializationConfig, paramArrayType, paramBeanDescription, (JsonSerializer)localObject);
      }
    }
    return (JsonSerializer<?>)localObject;
  }
  
  protected JsonSerializer<?> buildCollectionSerializer(SerializerProvider paramSerializerProvider, CollectionType paramCollectionType, BeanDescription paramBeanDescription, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
    throws JsonMappingException
  {
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    Object localObject1 = null;
    Iterator localIterator1 = customSerializers().iterator();
    do
    {
      if (!localIterator1.hasNext()) {
        break;
      }
      localObject1 = ((Serializers)localIterator1.next()).findCollectionSerializer(localSerializationConfig, paramCollectionType, paramBeanDescription, paramTypeSerializer, paramJsonSerializer);
    } while (localObject1 == null);
    if (localObject1 == null)
    {
      localObject1 = findSerializerByAnnotations(paramSerializerProvider, paramCollectionType, paramBeanDescription);
      if (localObject1 == null)
      {
        JsonFormat.Value localValue = paramBeanDescription.findExpectedFormat(null);
        if ((localValue == null) || (localValue.getShape() != JsonFormat.Shape.OBJECT)) {}
      }
    }
    for (Object localObject2 = null;; localObject2 = localObject1)
    {
      return (JsonSerializer<?>)localObject2;
      Class localClass1 = paramCollectionType.getRawClass();
      if (EnumSet.class.isAssignableFrom(localClass1))
      {
        JavaType localJavaType = paramCollectionType.getContentType();
        if (!localJavaType.isEnumType()) {
          localJavaType = null;
        }
        localObject1 = buildEnumSetSerializer(localJavaType);
      }
      label320:
      while (this._factoryConfig.hasSerializerModifiers())
      {
        Iterator localIterator2 = this._factoryConfig.serializerModifiers().iterator();
        while (localIterator2.hasNext()) {
          localObject1 = ((BeanSerializerModifier)localIterator2.next()).modifyCollectionSerializer(localSerializationConfig, paramCollectionType, paramBeanDescription, (JsonSerializer)localObject1);
        }
        Class localClass2 = paramCollectionType.getContentType().getRawClass();
        if (isIndexedList(localClass1)) {
          if (localClass2 == String.class) {
            if ((paramJsonSerializer == null) || (ClassUtil.isJacksonStdImpl(paramJsonSerializer))) {
              localObject1 = IndexedStringListSerializer.instance;
            }
          }
        }
        for (;;)
        {
          if (localObject1 != null) {
            break label320;
          }
          localObject1 = buildCollectionSerializer(paramCollectionType.getContentType(), paramBoolean, paramTypeSerializer, paramJsonSerializer);
          break;
          localObject1 = buildIndexedListSerializer(paramCollectionType.getContentType(), paramBoolean, paramTypeSerializer, paramJsonSerializer);
          continue;
          if ((localClass2 == String.class) && ((paramJsonSerializer == null) || (ClassUtil.isJacksonStdImpl(paramJsonSerializer)))) {
            localObject1 = StringCollectionSerializer.instance;
          }
        }
      }
    }
  }
  
  public ContainerSerializer<?> buildCollectionSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    return new CollectionSerializer(paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer);
  }
  
  protected JsonSerializer<?> buildContainerSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription, boolean paramBoolean)
    throws JsonMappingException
  {
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    if ((!paramBoolean) && (paramJavaType.useStaticType()) && ((!paramJavaType.isContainerType()) || (paramJavaType.getContentType().getRawClass() != Object.class))) {
      paramBoolean = true;
    }
    TypeSerializer localTypeSerializer = createTypeSerializer(localSerializationConfig, paramJavaType.getContentType());
    if (localTypeSerializer != null) {
      paramBoolean = false;
    }
    JsonSerializer localJsonSerializer1 = _findContentSerializer(paramSerializerProvider, paramBeanDescription.getClassInfo());
    JsonSerializer localJsonSerializer3;
    JsonSerializer localJsonSerializer2;
    if (paramJavaType.isMapLikeType())
    {
      MapLikeType localMapLikeType1 = (MapLikeType)paramJavaType;
      localJsonSerializer3 = _findKeySerializer(paramSerializerProvider, paramBeanDescription.getClassInfo());
      if (localMapLikeType1.isTrueMapType()) {
        localJsonSerializer2 = buildMapSerializer(paramSerializerProvider, (MapType)localMapLikeType1, paramBeanDescription, paramBoolean, localJsonSerializer3, localTypeSerializer, localJsonSerializer1);
      }
    }
    for (;;)
    {
      return localJsonSerializer2;
      localJsonSerializer2 = null;
      MapLikeType localMapLikeType2 = (MapLikeType)paramJavaType;
      Iterator localIterator3 = customSerializers().iterator();
      do
      {
        if (!localIterator3.hasNext()) {
          break;
        }
        localJsonSerializer2 = ((Serializers)localIterator3.next()).findMapLikeSerializer(localSerializationConfig, localMapLikeType2, paramBeanDescription, localJsonSerializer3, localTypeSerializer, localJsonSerializer1);
      } while (localJsonSerializer2 == null);
      if (localJsonSerializer2 == null) {
        localJsonSerializer2 = findSerializerByAnnotations(paramSerializerProvider, paramJavaType, paramBeanDescription);
      }
      if ((localJsonSerializer2 != null) && (this._factoryConfig.hasSerializerModifiers()))
      {
        Iterator localIterator4 = this._factoryConfig.serializerModifiers().iterator();
        while (localIterator4.hasNext()) {
          localJsonSerializer2 = ((BeanSerializerModifier)localIterator4.next()).modifyMapLikeSerializer(localSerializationConfig, localMapLikeType2, paramBeanDescription, localJsonSerializer2);
        }
        continue;
        if (paramJavaType.isCollectionLikeType())
        {
          CollectionLikeType localCollectionLikeType1 = (CollectionLikeType)paramJavaType;
          if (localCollectionLikeType1.isTrueCollectionType())
          {
            localJsonSerializer2 = buildCollectionSerializer(paramSerializerProvider, (CollectionType)localCollectionLikeType1, paramBeanDescription, paramBoolean, localTypeSerializer, localJsonSerializer1);
          }
          else
          {
            localJsonSerializer2 = null;
            CollectionLikeType localCollectionLikeType2 = (CollectionLikeType)paramJavaType;
            Iterator localIterator1 = customSerializers().iterator();
            do
            {
              if (!localIterator1.hasNext()) {
                break;
              }
              localJsonSerializer2 = ((Serializers)localIterator1.next()).findCollectionLikeSerializer(localSerializationConfig, localCollectionLikeType2, paramBeanDescription, localTypeSerializer, localJsonSerializer1);
            } while (localJsonSerializer2 == null);
            if (localJsonSerializer2 == null) {
              localJsonSerializer2 = findSerializerByAnnotations(paramSerializerProvider, paramJavaType, paramBeanDescription);
            }
            if ((localJsonSerializer2 != null) && (this._factoryConfig.hasSerializerModifiers()))
            {
              Iterator localIterator2 = this._factoryConfig.serializerModifiers().iterator();
              while (localIterator2.hasNext()) {
                localJsonSerializer2 = ((BeanSerializerModifier)localIterator2.next()).modifyCollectionLikeSerializer(localSerializationConfig, localCollectionLikeType2, paramBeanDescription, localJsonSerializer2);
              }
            }
          }
        }
        else if (paramJavaType.isArrayType())
        {
          localJsonSerializer2 = buildArraySerializer(paramSerializerProvider, (ArrayType)paramJavaType, paramBeanDescription, paramBoolean, localTypeSerializer, localJsonSerializer1);
        }
        else
        {
          localJsonSerializer2 = null;
        }
      }
    }
  }
  
  protected JsonSerializer<?> buildEnumSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject = null;
    JsonFormat.Value localValue = paramBeanDescription.findExpectedFormat(null);
    if ((localValue != null) && (localValue.getShape() == JsonFormat.Shape.OBJECT)) {
      ((BasicBeanDescription)paramBeanDescription).removeProperty("declaringClass");
    }
    for (;;)
    {
      return (JsonSerializer<?>)localObject;
      localObject = EnumSerializer.construct(paramJavaType.getRawClass(), paramSerializationConfig, paramBeanDescription, localValue);
      if (this._factoryConfig.hasSerializerModifiers())
      {
        Iterator localIterator = this._factoryConfig.serializerModifiers().iterator();
        while (localIterator.hasNext()) {
          localObject = ((BeanSerializerModifier)localIterator.next()).modifyEnumSerializer(paramSerializationConfig, paramJavaType, paramBeanDescription, (JsonSerializer)localObject);
        }
      }
    }
  }
  
  public JsonSerializer<?> buildEnumSetSerializer(JavaType paramJavaType)
  {
    return new EnumSetSerializer(paramJavaType);
  }
  
  public ContainerSerializer<?> buildIndexedListSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    return new IndexedListSerializer(paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer);
  }
  
  @Deprecated
  protected JsonSerializer<?> buildIterableSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription, boolean paramBoolean)
    throws JsonMappingException
  {
    JavaType[] arrayOfJavaType = paramSerializationConfig.getTypeFactory().findTypeParameters(paramJavaType, Iterable.class);
    if ((arrayOfJavaType == null) || (arrayOfJavaType.length != 1)) {}
    for (JavaType localJavaType = TypeFactory.unknownType();; localJavaType = arrayOfJavaType[0]) {
      return buildIterableSerializer(paramSerializationConfig, paramJavaType, paramBeanDescription, paramBoolean, localJavaType);
    }
  }
  
  protected JsonSerializer<?> buildIterableSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType1, BeanDescription paramBeanDescription, boolean paramBoolean, JavaType paramJavaType2)
    throws JsonMappingException
  {
    return new IterableSerializer(paramJavaType2, paramBoolean, createTypeSerializer(paramSerializationConfig, paramJavaType2));
  }
  
  @Deprecated
  protected JsonSerializer<?> buildIteratorSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription, boolean paramBoolean)
    throws JsonMappingException
  {
    JavaType[] arrayOfJavaType = paramSerializationConfig.getTypeFactory().findTypeParameters(paramJavaType, Iterator.class);
    if ((arrayOfJavaType == null) || (arrayOfJavaType.length != 1)) {}
    for (JavaType localJavaType = TypeFactory.unknownType();; localJavaType = arrayOfJavaType[0]) {
      return buildIteratorSerializer(paramSerializationConfig, paramJavaType, paramBeanDescription, paramBoolean, localJavaType);
    }
  }
  
  protected JsonSerializer<?> buildIteratorSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType1, BeanDescription paramBeanDescription, boolean paramBoolean, JavaType paramJavaType2)
    throws JsonMappingException
  {
    return new IteratorSerializer(paramJavaType2, paramBoolean, createTypeSerializer(paramSerializationConfig, paramJavaType2));
  }
  
  protected JsonSerializer<?> buildMapEntrySerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType1, BeanDescription paramBeanDescription, boolean paramBoolean, JavaType paramJavaType2, JavaType paramJavaType3)
    throws JsonMappingException
  {
    return new MapEntrySerializer(paramJavaType3, paramJavaType2, paramJavaType3, paramBoolean, createTypeSerializer(paramSerializationConfig, paramJavaType3), null);
  }
  
  protected JsonSerializer<?> buildMapSerializer(SerializerProvider paramSerializerProvider, MapType paramMapType, BeanDescription paramBeanDescription, boolean paramBoolean, JsonSerializer<Object> paramJsonSerializer1, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer2)
    throws JsonMappingException
  {
    SerializationConfig localSerializationConfig = paramSerializerProvider.getConfig();
    Object localObject1 = null;
    Iterator localIterator1 = customSerializers().iterator();
    do
    {
      if (!localIterator1.hasNext()) {
        break;
      }
      localObject1 = ((Serializers)localIterator1.next()).findMapSerializer(localSerializationConfig, paramMapType, paramBeanDescription, paramJsonSerializer1, paramTypeSerializer, paramJsonSerializer2);
    } while (localObject1 == null);
    if (localObject1 == null)
    {
      localObject1 = findSerializerByAnnotations(paramSerializerProvider, paramMapType, paramBeanDescription);
      if (localObject1 == null)
      {
        Object localObject2 = findFilterId(localSerializationConfig, paramBeanDescription);
        MapSerializer localMapSerializer = MapSerializer.construct(localSerializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(paramBeanDescription.getClassInfo(), true), paramMapType, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2, localObject2);
        Object localObject3 = findSuppressableContentValue(localSerializationConfig, paramMapType.getContentType(), paramBeanDescription);
        if (localObject3 != null) {
          localMapSerializer = localMapSerializer.withContentInclusion(localObject3);
        }
        localObject1 = localMapSerializer;
      }
    }
    if (this._factoryConfig.hasSerializerModifiers())
    {
      Iterator localIterator2 = this._factoryConfig.serializerModifiers().iterator();
      while (localIterator2.hasNext()) {
        localObject1 = ((BeanSerializerModifier)localIterator2.next()).modifyMapSerializer(localSerializationConfig, paramMapType, paramBeanDescription, (JsonSerializer)localObject1);
      }
    }
    return (JsonSerializer<?>)localObject1;
  }
  
  public JsonSerializer<Object> createKeySerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer)
  {
    BeanDescription localBeanDescription = paramSerializationConfig.introspectClassAnnotations(paramJavaType.getRawClass());
    Object localObject = null;
    if (this._factoryConfig.hasKeySerializers())
    {
      Iterator localIterator2 = this._factoryConfig.keySerializers().iterator();
      do
      {
        if (!localIterator2.hasNext()) {
          break;
        }
        localObject = ((Serializers)localIterator2.next()).findSerializer(paramSerializationConfig, paramJavaType, localBeanDescription);
      } while (localObject == null);
    }
    JsonSerializer localJsonSerializer;
    Method localMethod;
    if (localObject == null)
    {
      localObject = paramJsonSerializer;
      if (localObject == null)
      {
        localObject = StdKeySerializers.getStdKeySerializer(paramSerializationConfig, paramJavaType.getRawClass(), false);
        if (localObject == null)
        {
          localBeanDescription = paramSerializationConfig.introspect(paramJavaType);
          AnnotatedMethod localAnnotatedMethod = localBeanDescription.findJsonValueMethod();
          if (localAnnotatedMethod == null) {
            break label223;
          }
          localJsonSerializer = StdKeySerializers.getStdKeySerializer(paramSerializationConfig, localAnnotatedMethod.getRawReturnType(), true);
          localMethod = localAnnotatedMethod.getAnnotated();
          if (paramSerializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(localMethod);
          }
        }
      }
    }
    label223:
    for (localObject = new JsonValueSerializer(localMethod, localJsonSerializer); this._factoryConfig.hasSerializerModifiers(); localObject = StdKeySerializers.getFallbackKeySerializer(paramSerializationConfig, paramJavaType.getRawClass()))
    {
      Iterator localIterator1 = this._factoryConfig.serializerModifiers().iterator();
      while (localIterator1.hasNext()) {
        localObject = ((BeanSerializerModifier)localIterator1.next()).modifyKeySerializer(paramSerializationConfig, paramJavaType, localBeanDescription, (JsonSerializer)localObject);
      }
    }
    return (JsonSerializer<Object>)localObject;
  }
  
  public abstract JsonSerializer<Object> createSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType)
    throws JsonMappingException;
  
  public TypeSerializer createTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType)
  {
    AnnotatedClass localAnnotatedClass = paramSerializationConfig.introspectClassAnnotations(paramJavaType.getRawClass()).getClassInfo();
    TypeResolverBuilder localTypeResolverBuilder = paramSerializationConfig.getAnnotationIntrospector().findTypeResolver(paramSerializationConfig, localAnnotatedClass, paramJavaType);
    Collection localCollection = null;
    if (localTypeResolverBuilder == null)
    {
      localTypeResolverBuilder = paramSerializationConfig.getDefaultTyper(paramJavaType);
      if (localTypeResolverBuilder != null) {
        break label64;
      }
    }
    label64:
    for (TypeSerializer localTypeSerializer = null;; localTypeSerializer = localTypeResolverBuilder.buildTypeSerializer(paramSerializationConfig, paramJavaType, localCollection))
    {
      return localTypeSerializer;
      localCollection = paramSerializationConfig.getSubtypeResolver().collectAndResolveSubtypesByClass(paramSerializationConfig, localAnnotatedClass);
      break;
    }
  }
  
  protected abstract Iterable<Serializers> customSerializers();
  
  protected Converter<Object, Object> findConverter(SerializerProvider paramSerializerProvider, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject1 = paramSerializerProvider.getAnnotationIntrospector().findSerializationConverter(paramAnnotated);
    if (localObject1 == null) {}
    for (Object localObject2 = null;; localObject2 = paramSerializerProvider.converterInstance(paramAnnotated, localObject1)) {
      return (Converter<Object, Object>)localObject2;
    }
  }
  
  protected JsonSerializer<?> findConvertingSerializer(SerializerProvider paramSerializerProvider, Annotated paramAnnotated, JsonSerializer<?> paramJsonSerializer)
    throws JsonMappingException
  {
    Converter localConverter = findConverter(paramSerializerProvider, paramAnnotated);
    if (localConverter == null) {}
    for (;;)
    {
      return paramJsonSerializer;
      paramJsonSerializer = new StdDelegatingSerializer(localConverter, localConverter.getOutputType(paramSerializerProvider.getTypeFactory()), paramJsonSerializer);
    }
  }
  
  protected Object findFilterId(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription)
  {
    return paramSerializationConfig.getAnnotationIntrospector().findFilterId(paramBeanDescription.getClassInfo());
  }
  
  protected JsonSerializer<?> findOptionalStdSerializer(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription, boolean paramBoolean)
    throws JsonMappingException
  {
    return OptionalHandlerFactory.instance.findSerializer(paramSerializerProvider.getConfig(), paramJavaType, paramBeanDescription);
  }
  
  protected final JsonSerializer<?> findSerializerByAddonType(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription, boolean paramBoolean)
    throws JsonMappingException
  {
    Class localClass = paramJavaType.getRawClass();
    JavaType[] arrayOfJavaType2;
    JavaType localJavaType2;
    Object localObject;
    if (Iterator.class.isAssignableFrom(localClass))
    {
      arrayOfJavaType2 = paramSerializationConfig.getTypeFactory().findTypeParameters(paramJavaType, Iterator.class);
      if ((arrayOfJavaType2 == null) || (arrayOfJavaType2.length != 1))
      {
        localJavaType2 = TypeFactory.unknownType();
        localObject = buildIteratorSerializer(paramSerializationConfig, paramJavaType, paramBeanDescription, paramBoolean, localJavaType2);
      }
    }
    for (;;)
    {
      return (JsonSerializer<?>)localObject;
      localJavaType2 = arrayOfJavaType2[0];
      break;
      if (Iterable.class.isAssignableFrom(localClass))
      {
        JavaType[] arrayOfJavaType1 = paramSerializationConfig.getTypeFactory().findTypeParameters(paramJavaType, Iterable.class);
        if ((arrayOfJavaType1 == null) || (arrayOfJavaType1.length != 1)) {}
        for (JavaType localJavaType1 = TypeFactory.unknownType();; localJavaType1 = arrayOfJavaType1[0])
        {
          localObject = buildIterableSerializer(paramSerializationConfig, paramJavaType, paramBeanDescription, paramBoolean, localJavaType1);
          break;
        }
      }
      if (CharSequence.class.isAssignableFrom(localClass)) {
        localObject = ToStringSerializer.instance;
      } else {
        localObject = null;
      }
    }
  }
  
  protected final JsonSerializer<?> findSerializerByAnnotations(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject;
    if (JsonSerializable.class.isAssignableFrom(paramJavaType.getRawClass())) {
      localObject = SerializableSerializer.instance;
    }
    for (;;)
    {
      return (JsonSerializer<?>)localObject;
      AnnotatedMethod localAnnotatedMethod = paramBeanDescription.findJsonValueMethod();
      if (localAnnotatedMethod != null)
      {
        Method localMethod = localAnnotatedMethod.getAnnotated();
        if (paramSerializerProvider.canOverrideAccessModifiers()) {
          ClassUtil.checkAndFixAccess(localMethod);
        }
        localObject = new JsonValueSerializer(localMethod, findSerializerFromAnnotation(paramSerializerProvider, localAnnotatedMethod));
      }
      else
      {
        localObject = null;
      }
    }
  }
  
  protected final JsonSerializer<?> findSerializerByLookup(JavaType paramJavaType, SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, boolean paramBoolean)
  {
    String str = paramJavaType.getRawClass().getName();
    if ((paramJavaType.isReferenceType()) && (paramJavaType.isTypeOrSubTypeOf(AtomicReference.class))) {}
    JsonSerializer localJsonSerializer;
    for (Object localObject = new AtomicReferenceSerializer((ReferenceType)paramJavaType);; localObject = localJsonSerializer) {
      for (;;)
      {
        return (JsonSerializer<?>)localObject;
        localJsonSerializer = (JsonSerializer)_concrete.get(str);
        if (localJsonSerializer == null)
        {
          Class localClass = (Class)_concreteLazy.get(str);
          if (localClass != null) {
            try
            {
              localObject = (JsonSerializer)localClass.newInstance();
            }
            catch (Exception localException)
            {
              throw new IllegalStateException("Failed to instantiate standard serializer (of type " + localClass.getName() + "): " + localException.getMessage(), localException);
            }
          }
        }
      }
    }
  }
  
  protected final JsonSerializer<?> findSerializerByPrimaryType(SerializerProvider paramSerializerProvider, JavaType paramJavaType, BeanDescription paramBeanDescription, boolean paramBoolean)
    throws JsonMappingException
  {
    Class localClass = paramJavaType.getRawClass();
    Object localObject = findOptionalStdSerializer(paramSerializerProvider, paramJavaType, paramBeanDescription, paramBoolean);
    if (localObject != null) {}
    for (;;)
    {
      return (JsonSerializer<?>)localObject;
      if (Calendar.class.isAssignableFrom(localClass))
      {
        localObject = CalendarSerializer.instance;
      }
      else if (java.util.Date.class.isAssignableFrom(localClass))
      {
        localObject = DateSerializer.instance;
      }
      else if (Map.Entry.class.isAssignableFrom(localClass))
      {
        JavaType localJavaType1 = paramJavaType.containedType(0);
        if (localJavaType1 == null) {
          localJavaType1 = TypeFactory.unknownType();
        }
        JavaType localJavaType2 = paramJavaType.containedType(1);
        if (localJavaType2 == null) {
          localJavaType2 = TypeFactory.unknownType();
        }
        localObject = buildMapEntrySerializer(paramSerializerProvider.getConfig(), paramJavaType, paramBeanDescription, paramBoolean, localJavaType1, localJavaType2);
      }
      else if (ByteBuffer.class.isAssignableFrom(localClass))
      {
        localObject = new ByteBufferSerializer();
      }
      else if (InetAddress.class.isAssignableFrom(localClass))
      {
        localObject = new InetAddressSerializer();
      }
      else if (InetSocketAddress.class.isAssignableFrom(localClass))
      {
        localObject = new InetSocketAddressSerializer();
      }
      else if (TimeZone.class.isAssignableFrom(localClass))
      {
        localObject = new TimeZoneSerializer();
      }
      else if (Charset.class.isAssignableFrom(localClass))
      {
        localObject = ToStringSerializer.instance;
      }
      else if (Number.class.isAssignableFrom(localClass))
      {
        JsonFormat.Value localValue = paramBeanDescription.findExpectedFormat(null);
        if (localValue != null) {}
        switch (1.$SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[localValue.getShape().ordinal()])
        {
        default: 
          localObject = NumberSerializer.instance;
          break;
        case 1: 
          localObject = ToStringSerializer.instance;
          break;
        case 2: 
        case 3: 
          localObject = null;
          break;
        }
      }
      else if (Enum.class.isAssignableFrom(localClass))
      {
        localObject = buildEnumSerializer(paramSerializerProvider.getConfig(), paramJavaType, paramBeanDescription);
      }
      else
      {
        localObject = null;
      }
    }
  }
  
  protected JsonSerializer<Object> findSerializerFromAnnotation(SerializerProvider paramSerializerProvider, Annotated paramAnnotated)
    throws JsonMappingException
  {
    Object localObject1 = paramSerializerProvider.getAnnotationIntrospector().findSerializer(paramAnnotated);
    if (localObject1 == null) {}
    for (Object localObject2 = null;; localObject2 = findConvertingSerializer(paramSerializerProvider, paramAnnotated, paramSerializerProvider.serializerInstance(paramAnnotated, localObject1))) {
      return (JsonSerializer<Object>)localObject2;
    }
  }
  
  protected Object findSuppressableContentValue(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
    throws JsonMappingException
  {
    Object localObject = null;
    JsonInclude.Include localInclude = paramBeanDescription.findSerializationInclusionForContent(null);
    if (localInclude != null) {
      switch (localInclude)
      {
      }
    }
    for (;;)
    {
      localObject = localInclude;
      return localObject;
      localInclude = JsonInclude.Include.NON_EMPTY;
    }
  }
  
  public SerializerFactoryConfig getFactoryConfig()
  {
    return this._factoryConfig;
  }
  
  protected boolean isIndexedList(Class<?> paramClass)
  {
    return RandomAccess.class.isAssignableFrom(paramClass);
  }
  
  protected <T extends JavaType> T modifyTypeByAnnotation(SerializationConfig paramSerializationConfig, Annotated paramAnnotated, T paramT)
  {
    Class localClass = paramSerializationConfig.getAnnotationIntrospector().findSerializationType(paramAnnotated);
    if (localClass != null) {}
    try
    {
      JavaType localJavaType = paramT.widenBy(localClass);
      paramT = localJavaType;
      return modifySecondaryTypesByAnnotation(paramSerializationConfig, paramAnnotated, paramT);
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new IllegalArgumentException("Failed to widen type " + paramT + " with concrete-type annotation (value " + localClass.getName() + "), method '" + paramAnnotated.getName() + "': " + localIllegalArgumentException.getMessage());
    }
  }
  
  protected boolean usesStaticTyping(SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, TypeSerializer paramTypeSerializer)
  {
    boolean bool = false;
    if (paramTypeSerializer != null) {}
    for (;;)
    {
      return bool;
      JsonSerialize.Typing localTyping = paramSerializationConfig.getAnnotationIntrospector().findSerializationTyping(paramBeanDescription.getClassInfo());
      if ((localTyping != null) && (localTyping != JsonSerialize.Typing.DEFAULT_TYPING))
      {
        if (localTyping == JsonSerialize.Typing.STATIC) {
          bool = true;
        }
      }
      else {
        bool = paramSerializationConfig.isEnabled(MapperFeature.USE_STATIC_TYPING);
      }
    }
  }
  
  public final SerializerFactory withAdditionalKeySerializers(Serializers paramSerializers)
  {
    return withConfig(this._factoryConfig.withAdditionalKeySerializers(paramSerializers));
  }
  
  public final SerializerFactory withAdditionalSerializers(Serializers paramSerializers)
  {
    return withConfig(this._factoryConfig.withAdditionalSerializers(paramSerializers));
  }
  
  public abstract SerializerFactory withConfig(SerializerFactoryConfig paramSerializerFactoryConfig);
  
  public final SerializerFactory withSerializerModifier(BeanSerializerModifier paramBeanSerializerModifier)
  {
    return withConfig(this._factoryConfig.withSerializerModifier(paramBeanSerializerModifier));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/BasicSerializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */