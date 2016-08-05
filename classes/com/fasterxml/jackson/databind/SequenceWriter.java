package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class SequenceWriter
  implements Versioned, Closeable, Flushable
{
  protected final boolean _cfgCloseCloseable;
  protected final boolean _cfgFlush;
  protected final boolean _closeGenerator;
  protected boolean _closed;
  protected final SerializationConfig _config;
  protected PropertySerializerMap _dynamicSerializers;
  protected final JsonGenerator _generator;
  protected boolean _openArray;
  protected final DefaultSerializerProvider _provider;
  protected final JsonSerializer<Object> _rootSerializer;
  protected final TypeSerializer _typeSerializer;
  
  public SequenceWriter(DefaultSerializerProvider paramDefaultSerializerProvider, JsonGenerator paramJsonGenerator, boolean paramBoolean, ObjectWriter.Prefetch paramPrefetch)
    throws IOException
  {
    this._provider = paramDefaultSerializerProvider;
    this._generator = paramJsonGenerator;
    this._closeGenerator = paramBoolean;
    this._rootSerializer = paramPrefetch.getValueSerializer();
    this._typeSerializer = paramPrefetch.getTypeSerializer();
    this._config = paramDefaultSerializerProvider.getConfig();
    this._cfgFlush = this._config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE);
    this._cfgCloseCloseable = this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE);
    this._dynamicSerializers = PropertySerializerMap.emptyForRootValues();
  }
  
  private final JsonSerializer<Object> _findAndAddDynamic(JavaType paramJavaType)
    throws JsonMappingException
  {
    if (this._typeSerializer == null) {}
    for (PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = this._dynamicSerializers.findAndAddRootValueSerializer(paramJavaType, this._provider);; localSerializerAndMapResult = this._dynamicSerializers.addSerializer(paramJavaType, new TypeWrappedSerializer(this._typeSerializer, this._provider.findValueSerializer(paramJavaType, null))))
    {
      this._dynamicSerializers = localSerializerAndMapResult.map;
      return localSerializerAndMapResult.serializer;
    }
  }
  
  private final JsonSerializer<Object> _findAndAddDynamic(Class<?> paramClass)
    throws JsonMappingException
  {
    if (this._typeSerializer == null) {}
    for (PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = this._dynamicSerializers.findAndAddRootValueSerializer(paramClass, this._provider);; localSerializerAndMapResult = this._dynamicSerializers.addSerializer(paramClass, new TypeWrappedSerializer(this._typeSerializer, this._provider.findValueSerializer(paramClass, null))))
    {
      this._dynamicSerializers = localSerializerAndMapResult.map;
      return localSerializerAndMapResult.serializer;
    }
  }
  
  /* Error */
  protected SequenceWriter _writeCloseableValue(Object paramObject)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: checkcast 8	java/io/Closeable
    //   4: astore_2
    //   5: aload_0
    //   6: getfield 50	com/fasterxml/jackson/databind/SequenceWriter:_rootSerializer	Lcom/fasterxml/jackson/databind/JsonSerializer;
    //   9: astore 5
    //   11: aload 5
    //   13: ifnonnull +33 -> 46
    //   16: aload_1
    //   17: invokevirtual 136	java/lang/Object:getClass	()Ljava/lang/Class;
    //   20: astore 8
    //   22: aload_0
    //   23: getfield 91	com/fasterxml/jackson/databind/SequenceWriter:_dynamicSerializers	Lcom/fasterxml/jackson/databind/ser/impl/PropertySerializerMap;
    //   26: aload 8
    //   28: invokevirtual 139	com/fasterxml/jackson/databind/ser/impl/PropertySerializerMap:serializerFor	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JsonSerializer;
    //   31: astore 5
    //   33: aload 5
    //   35: ifnonnull +11 -> 46
    //   38: aload_0
    //   39: aload 8
    //   41: invokespecial 141	com/fasterxml/jackson/databind/SequenceWriter:_findAndAddDynamic	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JsonSerializer;
    //   44: astore 5
    //   46: aload_0
    //   47: getfield 38	com/fasterxml/jackson/databind/SequenceWriter:_provider	Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   50: aload_0
    //   51: getfield 40	com/fasterxml/jackson/databind/SequenceWriter:_generator	Lcom/fasterxml/jackson/core/JsonGenerator;
    //   54: aload_1
    //   55: aconst_null
    //   56: aload 5
    //   58: invokevirtual 145	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/databind/JsonSerializer;)V
    //   61: aload_0
    //   62: getfield 78	com/fasterxml/jackson/databind/SequenceWriter:_cfgFlush	Z
    //   65: ifeq +10 -> 75
    //   68: aload_0
    //   69: getfield 40	com/fasterxml/jackson/databind/SequenceWriter:_generator	Lcom/fasterxml/jackson/core/JsonGenerator;
    //   72: invokevirtual 150	com/fasterxml/jackson/core/JsonGenerator:flush	()V
    //   75: aload_2
    //   76: astore 6
    //   78: aconst_null
    //   79: astore_2
    //   80: aload 6
    //   82: invokeinterface 153 1 0
    //   87: iconst_0
    //   88: ifeq +9 -> 97
    //   91: aconst_null
    //   92: invokeinterface 153 1 0
    //   97: aload_0
    //   98: areturn
    //   99: astore_3
    //   100: aload_2
    //   101: ifnull +9 -> 110
    //   104: aload_2
    //   105: invokeinterface 153 1 0
    //   110: aload_3
    //   111: athrow
    //   112: astore 7
    //   114: goto -17 -> 97
    //   117: astore 4
    //   119: goto -9 -> 110
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	122	0	this	SequenceWriter
    //   0	122	1	paramObject	Object
    //   4	101	2	localCloseable1	Closeable
    //   99	12	3	localObject	Object
    //   117	1	4	localIOException1	IOException
    //   9	48	5	localJsonSerializer	JsonSerializer
    //   76	5	6	localCloseable2	Closeable
    //   112	1	7	localIOException2	IOException
    //   20	20	8	localClass	Class
    // Exception table:
    //   from	to	target	type
    //   5	87	99	finally
    //   91	97	112	java/io/IOException
    //   104	110	117	java/io/IOException
  }
  
  /* Error */
  protected SequenceWriter _writeCloseableValue(Object paramObject, JavaType paramJavaType)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: checkcast 8	java/io/Closeable
    //   4: astore_3
    //   5: aload_0
    //   6: getfield 91	com/fasterxml/jackson/databind/SequenceWriter:_dynamicSerializers	Lcom/fasterxml/jackson/databind/ser/impl/PropertySerializerMap;
    //   9: aload_2
    //   10: invokevirtual 159	com/fasterxml/jackson/databind/JavaType:getRawClass	()Ljava/lang/Class;
    //   13: invokevirtual 139	com/fasterxml/jackson/databind/ser/impl/PropertySerializerMap:serializerFor	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JsonSerializer;
    //   16: astore 6
    //   18: aload 6
    //   20: ifnonnull +10 -> 30
    //   23: aload_0
    //   24: aload_2
    //   25: invokespecial 161	com/fasterxml/jackson/databind/SequenceWriter:_findAndAddDynamic	(Lcom/fasterxml/jackson/databind/JavaType;)Lcom/fasterxml/jackson/databind/JsonSerializer;
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 38	com/fasterxml/jackson/databind/SequenceWriter:_provider	Lcom/fasterxml/jackson/databind/ser/DefaultSerializerProvider;
    //   34: aload_0
    //   35: getfield 40	com/fasterxml/jackson/databind/SequenceWriter:_generator	Lcom/fasterxml/jackson/core/JsonGenerator;
    //   38: aload_1
    //   39: aload_2
    //   40: aload 6
    //   42: invokevirtual 145	com/fasterxml/jackson/databind/ser/DefaultSerializerProvider:serializeValue	(Lcom/fasterxml/jackson/core/JsonGenerator;Ljava/lang/Object;Lcom/fasterxml/jackson/databind/JavaType;Lcom/fasterxml/jackson/databind/JsonSerializer;)V
    //   45: aload_0
    //   46: getfield 78	com/fasterxml/jackson/databind/SequenceWriter:_cfgFlush	Z
    //   49: ifeq +10 -> 59
    //   52: aload_0
    //   53: getfield 40	com/fasterxml/jackson/databind/SequenceWriter:_generator	Lcom/fasterxml/jackson/core/JsonGenerator;
    //   56: invokevirtual 150	com/fasterxml/jackson/core/JsonGenerator:flush	()V
    //   59: aload_3
    //   60: astore 7
    //   62: aconst_null
    //   63: astore_3
    //   64: aload 7
    //   66: invokeinterface 153 1 0
    //   71: iconst_0
    //   72: ifeq +9 -> 81
    //   75: aconst_null
    //   76: invokeinterface 153 1 0
    //   81: aload_0
    //   82: areturn
    //   83: astore 4
    //   85: aload_3
    //   86: ifnull +9 -> 95
    //   89: aload_3
    //   90: invokeinterface 153 1 0
    //   95: aload 4
    //   97: athrow
    //   98: astore 8
    //   100: goto -19 -> 81
    //   103: astore 5
    //   105: goto -10 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	SequenceWriter
    //   0	108	1	paramObject	Object
    //   0	108	2	paramJavaType	JavaType
    //   4	86	3	localCloseable1	Closeable
    //   83	13	4	localObject	Object
    //   103	1	5	localIOException1	IOException
    //   16	25	6	localJsonSerializer	JsonSerializer
    //   60	5	7	localCloseable2	Closeable
    //   98	1	8	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   5	71	83	finally
    //   75	81	98	java/io/IOException
    //   89	95	103	java/io/IOException
  }
  
  public void close()
    throws IOException
  {
    if (!this._closed)
    {
      this._closed = true;
      if (this._openArray)
      {
        this._openArray = false;
        this._generator.writeEndArray();
      }
      if (this._closeGenerator) {
        this._generator.close();
      }
    }
  }
  
  public void flush()
    throws IOException
  {
    if (!this._closed) {
      this._generator.flush();
    }
  }
  
  public SequenceWriter init(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      this._generator.writeStartArray();
      this._openArray = true;
    }
    return this;
  }
  
  public Version version()
  {
    return PackageVersion.VERSION;
  }
  
  public SequenceWriter write(Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      this._provider.serializeValue(this._generator, null);
    }
    for (;;)
    {
      return this;
      if ((this._cfgCloseCloseable) && ((paramObject instanceof Closeable)))
      {
        this = _writeCloseableValue(paramObject);
      }
      else
      {
        JsonSerializer localJsonSerializer = this._rootSerializer;
        if (localJsonSerializer == null)
        {
          Class localClass = paramObject.getClass();
          localJsonSerializer = this._dynamicSerializers.serializerFor(localClass);
          if (localJsonSerializer == null) {
            localJsonSerializer = _findAndAddDynamic(localClass);
          }
        }
        this._provider.serializeValue(this._generator, paramObject, null, localJsonSerializer);
        if (this._cfgFlush) {
          this._generator.flush();
        }
      }
    }
  }
  
  public SequenceWriter write(Object paramObject, JavaType paramJavaType)
    throws IOException
  {
    if (paramObject == null) {
      this._provider.serializeValue(this._generator, null);
    }
    for (;;)
    {
      return this;
      if ((this._cfgCloseCloseable) && ((paramObject instanceof Closeable)))
      {
        this = _writeCloseableValue(paramObject, paramJavaType);
      }
      else
      {
        JsonSerializer localJsonSerializer = this._dynamicSerializers.serializerFor(paramJavaType.getRawClass());
        if (localJsonSerializer == null) {
          localJsonSerializer = _findAndAddDynamic(paramJavaType);
        }
        this._provider.serializeValue(this._generator, paramObject, paramJavaType, localJsonSerializer);
        if (this._cfgFlush) {
          this._generator.flush();
        }
      }
    }
  }
  
  public <C extends Collection<?>> SequenceWriter writeAll(C paramC)
    throws IOException
  {
    Iterator localIterator = paramC.iterator();
    while (localIterator.hasNext()) {
      write(localIterator.next());
    }
    return this;
  }
  
  public SequenceWriter writeAll(Object[] paramArrayOfObject)
    throws IOException
  {
    int i = 0;
    int j = paramArrayOfObject.length;
    while (i < j)
    {
      write(paramArrayOfObject[i]);
      i++;
    }
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/SequenceWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */