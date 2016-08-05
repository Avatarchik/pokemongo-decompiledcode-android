package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Gson
{
  static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
  private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
  private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls = new ThreadLocal();
  private final ConstructorConstructor constructorConstructor;
  final JsonDeserializationContext deserializationContext = new JsonDeserializationContext()
  {
    public <T> T deserialize(JsonElement paramAnonymousJsonElement, Type paramAnonymousType)
      throws JsonParseException
    {
      return (T)Gson.this.fromJson(paramAnonymousJsonElement, paramAnonymousType);
    }
  };
  private final List<TypeAdapterFactory> factories;
  private final boolean generateNonExecutableJson;
  private final boolean htmlSafe;
  private final boolean prettyPrinting;
  final JsonSerializationContext serializationContext = new JsonSerializationContext()
  {
    public JsonElement serialize(Object paramAnonymousObject)
    {
      return Gson.this.toJsonTree(paramAnonymousObject);
    }
    
    public JsonElement serialize(Object paramAnonymousObject, Type paramAnonymousType)
    {
      return Gson.this.toJsonTree(paramAnonymousObject, paramAnonymousType);
    }
  };
  private final boolean serializeNulls;
  private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache = Collections.synchronizedMap(new HashMap());
  
  public Gson()
  {
    this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
  }
  
  Gson(Excluder paramExcluder, FieldNamingStrategy paramFieldNamingStrategy, Map<Type, InstanceCreator<?>> paramMap, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, LongSerializationPolicy paramLongSerializationPolicy, List<TypeAdapterFactory> paramList)
  {
    this.constructorConstructor = new ConstructorConstructor(paramMap);
    this.serializeNulls = paramBoolean1;
    this.generateNonExecutableJson = paramBoolean3;
    this.htmlSafe = paramBoolean4;
    this.prettyPrinting = paramBoolean5;
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(TypeAdapters.JSON_ELEMENT_FACTORY);
    localArrayList.add(ObjectTypeAdapter.FACTORY);
    localArrayList.add(paramExcluder);
    localArrayList.addAll(paramList);
    localArrayList.add(TypeAdapters.STRING_FACTORY);
    localArrayList.add(TypeAdapters.INTEGER_FACTORY);
    localArrayList.add(TypeAdapters.BOOLEAN_FACTORY);
    localArrayList.add(TypeAdapters.BYTE_FACTORY);
    localArrayList.add(TypeAdapters.SHORT_FACTORY);
    localArrayList.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter(paramLongSerializationPolicy)));
    localArrayList.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(paramBoolean6)));
    localArrayList.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(paramBoolean6)));
    localArrayList.add(TypeAdapters.NUMBER_FACTORY);
    localArrayList.add(TypeAdapters.CHARACTER_FACTORY);
    localArrayList.add(TypeAdapters.STRING_BUILDER_FACTORY);
    localArrayList.add(TypeAdapters.STRING_BUFFER_FACTORY);
    localArrayList.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
    localArrayList.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
    localArrayList.add(TypeAdapters.URL_FACTORY);
    localArrayList.add(TypeAdapters.URI_FACTORY);
    localArrayList.add(TypeAdapters.UUID_FACTORY);
    localArrayList.add(TypeAdapters.LOCALE_FACTORY);
    localArrayList.add(TypeAdapters.INET_ADDRESS_FACTORY);
    localArrayList.add(TypeAdapters.BIT_SET_FACTORY);
    localArrayList.add(DateTypeAdapter.FACTORY);
    localArrayList.add(TypeAdapters.CALENDAR_FACTORY);
    localArrayList.add(TimeTypeAdapter.FACTORY);
    localArrayList.add(SqlDateTypeAdapter.FACTORY);
    localArrayList.add(TypeAdapters.TIMESTAMP_FACTORY);
    localArrayList.add(ArrayTypeAdapter.FACTORY);
    localArrayList.add(TypeAdapters.ENUM_FACTORY);
    localArrayList.add(TypeAdapters.CLASS_FACTORY);
    localArrayList.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
    localArrayList.add(new MapTypeAdapterFactory(this.constructorConstructor, paramBoolean2));
    localArrayList.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, paramFieldNamingStrategy, paramExcluder));
    this.factories = Collections.unmodifiableList(localArrayList);
  }
  
  private static void assertFullConsumption(Object paramObject, JsonReader paramJsonReader)
  {
    if (paramObject != null) {
      try
      {
        if (paramJsonReader.peek() != JsonToken.END_DOCUMENT) {
          throw new JsonIOException("JSON document was not fully consumed.");
        }
      }
      catch (MalformedJsonException localMalformedJsonException)
      {
        throw new JsonSyntaxException(localMalformedJsonException);
      }
      catch (IOException localIOException)
      {
        throw new JsonIOException(localIOException);
      }
    }
  }
  
  private void checkValidFloatingPoint(double paramDouble)
  {
    if ((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) {
      throw new IllegalArgumentException(paramDouble + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
    }
  }
  
  private TypeAdapter<Number> doubleAdapter(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Object localObject = TypeAdapters.DOUBLE;; localObject = new TypeAdapter()
        {
          public Double read(JsonReader paramAnonymousJsonReader)
            throws IOException
          {
            if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
              paramAnonymousJsonReader.nextNull();
            }
            for (Double localDouble = null;; localDouble = Double.valueOf(paramAnonymousJsonReader.nextDouble())) {
              return localDouble;
            }
          }
          
          public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
            throws IOException
          {
            if (paramAnonymousNumber == null) {
              paramAnonymousJsonWriter.nullValue();
            }
            for (;;)
            {
              return;
              double d = paramAnonymousNumber.doubleValue();
              Gson.this.checkValidFloatingPoint(d);
              paramAnonymousJsonWriter.value(paramAnonymousNumber);
            }
          }
        }) {
      return (TypeAdapter<Number>)localObject;
    }
  }
  
  private TypeAdapter<Number> floatAdapter(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (Object localObject = TypeAdapters.FLOAT;; localObject = new TypeAdapter()
        {
          public Float read(JsonReader paramAnonymousJsonReader)
            throws IOException
          {
            if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
              paramAnonymousJsonReader.nextNull();
            }
            for (Float localFloat = null;; localFloat = Float.valueOf((float)paramAnonymousJsonReader.nextDouble())) {
              return localFloat;
            }
          }
          
          public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
            throws IOException
          {
            if (paramAnonymousNumber == null) {
              paramAnonymousJsonWriter.nullValue();
            }
            for (;;)
            {
              return;
              float f = paramAnonymousNumber.floatValue();
              Gson.this.checkValidFloatingPoint(f);
              paramAnonymousJsonWriter.value(paramAnonymousNumber);
            }
          }
        }) {
      return (TypeAdapter<Number>)localObject;
    }
  }
  
  private TypeAdapter<Number> longAdapter(LongSerializationPolicy paramLongSerializationPolicy)
  {
    if (paramLongSerializationPolicy == LongSerializationPolicy.DEFAULT) {}
    for (Object localObject = TypeAdapters.LONG;; localObject = new TypeAdapter()
        {
          public Number read(JsonReader paramAnonymousJsonReader)
            throws IOException
          {
            if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
              paramAnonymousJsonReader.nextNull();
            }
            for (Object localObject = null;; localObject = Long.valueOf(paramAnonymousJsonReader.nextLong())) {
              return (Number)localObject;
            }
          }
          
          public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
            throws IOException
          {
            if (paramAnonymousNumber == null) {
              paramAnonymousJsonWriter.nullValue();
            }
            for (;;)
            {
              return;
              paramAnonymousJsonWriter.value(paramAnonymousNumber.toString());
            }
          }
        }) {
      return (TypeAdapter<Number>)localObject;
    }
  }
  
  private JsonWriter newJsonWriter(Writer paramWriter)
    throws IOException
  {
    if (this.generateNonExecutableJson) {
      paramWriter.write(")]}'\n");
    }
    JsonWriter localJsonWriter = new JsonWriter(paramWriter);
    if (this.prettyPrinting) {
      localJsonWriter.setIndent("  ");
    }
    localJsonWriter.setSerializeNulls(this.serializeNulls);
    return localJsonWriter;
  }
  
  public <T> T fromJson(JsonElement paramJsonElement, Class<T> paramClass)
    throws JsonSyntaxException
  {
    Object localObject = fromJson(paramJsonElement, paramClass);
    return (T)Primitives.wrap(paramClass).cast(localObject);
  }
  
  public <T> T fromJson(JsonElement paramJsonElement, Type paramType)
    throws JsonSyntaxException
  {
    if (paramJsonElement == null) {}
    for (Object localObject = null;; localObject = fromJson(new JsonTreeReader(paramJsonElement), paramType)) {
      return (T)localObject;
    }
  }
  
  /* Error */
  public <T> T fromJson(JsonReader paramJsonReader, Type paramType)
    throws JsonIOException, JsonSyntaxException
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual 400	com/google/gson/stream/JsonReader:isLenient	()Z
    //   6: istore 4
    //   8: aload_1
    //   9: iconst_1
    //   10: invokevirtual 403	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   13: aload_1
    //   14: invokevirtual 287	com/google/gson/stream/JsonReader:peek	()Lcom/google/gson/stream/JsonToken;
    //   17: pop
    //   18: iconst_0
    //   19: istore_3
    //   20: aload_0
    //   21: aload_2
    //   22: invokestatic 409	com/google/gson/reflect/TypeToken:get	(Ljava/lang/reflect/Type;)Lcom/google/gson/reflect/TypeToken;
    //   25: invokevirtual 413	com/google/gson/Gson:getAdapter	(Lcom/google/gson/reflect/TypeToken;)Lcom/google/gson/TypeAdapter;
    //   28: aload_1
    //   29: invokevirtual 419	com/google/gson/TypeAdapter:read	(Lcom/google/gson/stream/JsonReader;)Ljava/lang/Object;
    //   32: astore 11
    //   34: aload 11
    //   36: astore 9
    //   38: aload_1
    //   39: iload 4
    //   41: invokevirtual 403	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   44: aload 9
    //   46: areturn
    //   47: astore 8
    //   49: iload_3
    //   50: ifeq +15 -> 65
    //   53: aconst_null
    //   54: astore 9
    //   56: aload_1
    //   57: iload 4
    //   59: invokevirtual 403	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   62: goto -18 -> 44
    //   65: new 302	com/google/gson/JsonSyntaxException
    //   68: dup
    //   69: aload 8
    //   71: invokespecial 305	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   74: athrow
    //   75: astore 6
    //   77: aload_1
    //   78: iload 4
    //   80: invokevirtual 403	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   83: aload 6
    //   85: athrow
    //   86: astore 7
    //   88: new 302	com/google/gson/JsonSyntaxException
    //   91: dup
    //   92: aload 7
    //   94: invokespecial 305	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   97: athrow
    //   98: astore 5
    //   100: new 302	com/google/gson/JsonSyntaxException
    //   103: dup
    //   104: aload 5
    //   106: invokespecial 305	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   109: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	110	0	this	Gson
    //   0	110	1	paramJsonReader	JsonReader
    //   0	110	2	paramType	Type
    //   1	49	3	i	int
    //   6	73	4	bool	boolean
    //   98	7	5	localIOException	IOException
    //   75	9	6	localObject1	Object
    //   86	7	7	localIllegalStateException	IllegalStateException
    //   47	23	8	localEOFException	java.io.EOFException
    //   36	19	9	localObject2	Object
    //   32	3	11	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   13	34	47	java/io/EOFException
    //   13	34	75	finally
    //   65	75	75	finally
    //   88	110	75	finally
    //   13	34	86	java/lang/IllegalStateException
    //   13	34	98	java/io/IOException
  }
  
  public <T> T fromJson(Reader paramReader, Class<T> paramClass)
    throws JsonSyntaxException, JsonIOException
  {
    JsonReader localJsonReader = new JsonReader(paramReader);
    Object localObject = fromJson(localJsonReader, paramClass);
    assertFullConsumption(localObject, localJsonReader);
    return (T)Primitives.wrap(paramClass).cast(localObject);
  }
  
  public <T> T fromJson(Reader paramReader, Type paramType)
    throws JsonIOException, JsonSyntaxException
  {
    JsonReader localJsonReader = new JsonReader(paramReader);
    Object localObject = fromJson(localJsonReader, paramType);
    assertFullConsumption(localObject, localJsonReader);
    return (T)localObject;
  }
  
  public <T> T fromJson(String paramString, Class<T> paramClass)
    throws JsonSyntaxException
  {
    Object localObject = fromJson(paramString, paramClass);
    return (T)Primitives.wrap(paramClass).cast(localObject);
  }
  
  public <T> T fromJson(String paramString, Type paramType)
    throws JsonSyntaxException
  {
    if (paramString == null) {}
    for (Object localObject = null;; localObject = fromJson(new StringReader(paramString), paramType)) {
      return (T)localObject;
    }
  }
  
  public <T> TypeAdapter<T> getAdapter(TypeToken<T> paramTypeToken)
  {
    Object localObject1 = (TypeAdapter)this.typeTokenCache.get(paramTypeToken);
    if (localObject1 != null) {}
    for (;;)
    {
      return (TypeAdapter<T>)localObject1;
      Object localObject2 = (Map)this.calls.get();
      int i = 0;
      if (localObject2 == null)
      {
        localObject2 = new HashMap();
        this.calls.set(localObject2);
        i = 1;
      }
      FutureTypeAdapter localFutureTypeAdapter1 = (FutureTypeAdapter)((Map)localObject2).get(paramTypeToken);
      if (localFutureTypeAdapter1 != null)
      {
        localObject1 = localFutureTypeAdapter1;
        continue;
      }
      try
      {
        FutureTypeAdapter localFutureTypeAdapter2 = new FutureTypeAdapter();
        ((Map)localObject2).put(paramTypeToken, localFutureTypeAdapter2);
        Iterator localIterator = this.factories.iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            TypeAdapter localTypeAdapter = ((TypeAdapterFactory)localIterator.next()).create(this, paramTypeToken);
            if (localTypeAdapter != null)
            {
              localFutureTypeAdapter2.setDelegate(localTypeAdapter);
              this.typeTokenCache.put(paramTypeToken, localTypeAdapter);
              ((Map)localObject2).remove(paramTypeToken);
              if (i != 0) {
                this.calls.remove();
              }
              localObject1 = localTypeAdapter;
              break;
            }
          }
        }
        throw new IllegalArgumentException("GSON cannot handle " + paramTypeToken);
      }
      finally
      {
        ((Map)localObject2).remove(paramTypeToken);
        if (i != 0) {
          this.calls.remove();
        }
      }
    }
  }
  
  public <T> TypeAdapter<T> getAdapter(Class<T> paramClass)
  {
    return getAdapter(TypeToken.get(paramClass));
  }
  
  public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory paramTypeAdapterFactory, TypeToken<T> paramTypeToken)
  {
    int i = 0;
    Iterator localIterator = this.factories.iterator();
    while (localIterator.hasNext())
    {
      TypeAdapterFactory localTypeAdapterFactory = (TypeAdapterFactory)localIterator.next();
      if (i == 0)
      {
        if (localTypeAdapterFactory == paramTypeAdapterFactory) {
          i = 1;
        }
      }
      else
      {
        TypeAdapter localTypeAdapter = localTypeAdapterFactory.create(this, paramTypeToken);
        if (localTypeAdapter != null) {
          return localTypeAdapter;
        }
      }
    }
    throw new IllegalArgumentException("GSON cannot serialize " + paramTypeToken);
  }
  
  public String toJson(JsonElement paramJsonElement)
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(paramJsonElement, localStringWriter);
    return localStringWriter.toString();
  }
  
  public String toJson(Object paramObject)
  {
    if (paramObject == null) {}
    for (String str = toJson(JsonNull.INSTANCE);; str = toJson(paramObject, paramObject.getClass())) {
      return str;
    }
  }
  
  public String toJson(Object paramObject, Type paramType)
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(paramObject, paramType, localStringWriter);
    return localStringWriter.toString();
  }
  
  public void toJson(JsonElement paramJsonElement, JsonWriter paramJsonWriter)
    throws JsonIOException
  {
    boolean bool1 = paramJsonWriter.isLenient();
    paramJsonWriter.setLenient(true);
    boolean bool2 = paramJsonWriter.isHtmlSafe();
    paramJsonWriter.setHtmlSafe(this.htmlSafe);
    boolean bool3 = paramJsonWriter.getSerializeNulls();
    paramJsonWriter.setSerializeNulls(this.serializeNulls);
    try
    {
      Streams.write(paramJsonElement, paramJsonWriter);
      return;
    }
    catch (IOException localIOException)
    {
      throw new JsonIOException(localIOException);
    }
    finally
    {
      paramJsonWriter.setLenient(bool1);
      paramJsonWriter.setHtmlSafe(bool2);
      paramJsonWriter.setSerializeNulls(bool3);
    }
  }
  
  public void toJson(JsonElement paramJsonElement, Appendable paramAppendable)
    throws JsonIOException
  {
    try
    {
      toJson(paramJsonElement, newJsonWriter(Streams.writerForAppendable(paramAppendable)));
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  public void toJson(Object paramObject, Appendable paramAppendable)
    throws JsonIOException
  {
    if (paramObject != null) {
      toJson(paramObject, paramObject.getClass(), paramAppendable);
    }
    for (;;)
    {
      return;
      toJson(JsonNull.INSTANCE, paramAppendable);
    }
  }
  
  public void toJson(Object paramObject, Type paramType, JsonWriter paramJsonWriter)
    throws JsonIOException
  {
    TypeAdapter localTypeAdapter = getAdapter(TypeToken.get(paramType));
    boolean bool1 = paramJsonWriter.isLenient();
    paramJsonWriter.setLenient(true);
    boolean bool2 = paramJsonWriter.isHtmlSafe();
    paramJsonWriter.setHtmlSafe(this.htmlSafe);
    boolean bool3 = paramJsonWriter.getSerializeNulls();
    paramJsonWriter.setSerializeNulls(this.serializeNulls);
    try
    {
      localTypeAdapter.write(paramJsonWriter, paramObject);
      return;
    }
    catch (IOException localIOException)
    {
      throw new JsonIOException(localIOException);
    }
    finally
    {
      paramJsonWriter.setLenient(bool1);
      paramJsonWriter.setHtmlSafe(bool2);
      paramJsonWriter.setSerializeNulls(bool3);
    }
  }
  
  public void toJson(Object paramObject, Type paramType, Appendable paramAppendable)
    throws JsonIOException
  {
    try
    {
      toJson(paramObject, paramType, newJsonWriter(Streams.writerForAppendable(paramAppendable)));
      return;
    }
    catch (IOException localIOException)
    {
      throw new JsonIOException(localIOException);
    }
  }
  
  public JsonElement toJsonTree(Object paramObject)
  {
    if (paramObject == null) {}
    for (Object localObject = JsonNull.INSTANCE;; localObject = toJsonTree(paramObject, paramObject.getClass())) {
      return (JsonElement)localObject;
    }
  }
  
  public JsonElement toJsonTree(Object paramObject, Type paramType)
  {
    JsonTreeWriter localJsonTreeWriter = new JsonTreeWriter();
    toJson(paramObject, paramType, localJsonTreeWriter);
    return localJsonTreeWriter.get();
  }
  
  public String toString()
  {
    return "{serializeNulls:" + this.serializeNulls + "factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
  }
  
  static class FutureTypeAdapter<T>
    extends TypeAdapter<T>
  {
    private TypeAdapter<T> delegate;
    
    public T read(JsonReader paramJsonReader)
      throws IOException
    {
      if (this.delegate == null) {
        throw new IllegalStateException();
      }
      return (T)this.delegate.read(paramJsonReader);
    }
    
    public void setDelegate(TypeAdapter<T> paramTypeAdapter)
    {
      if (this.delegate != null) {
        throw new AssertionError();
      }
      this.delegate = paramTypeAdapter;
    }
    
    public void write(JsonWriter paramJsonWriter, T paramT)
      throws IOException
    {
      if (this.delegate == null) {
        throw new IllegalStateException();
      }
      this.delegate.write(paramJsonWriter, paramT);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/Gson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */