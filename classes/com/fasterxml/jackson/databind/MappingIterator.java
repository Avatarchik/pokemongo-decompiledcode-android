package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MappingIterator<T>
  implements Iterator<T>, Closeable
{
  protected static final MappingIterator<?> EMPTY_ITERATOR = new MappingIterator(null, null, null, null, false, null);
  protected static final int STATE_CLOSED = 0;
  protected static final int STATE_HAS_VALUE = 3;
  protected static final int STATE_MAY_HAVE_VALUE = 2;
  protected static final int STATE_NEED_RESYNC = 1;
  protected final boolean _closeParser;
  protected final DeserializationContext _context;
  protected final JsonDeserializer<T> _deserializer;
  protected final JsonParser _parser;
  protected final JsonStreamContext _seqContext;
  protected int _state;
  protected final JavaType _type;
  protected final T _updatedValue;
  
  protected MappingIterator(JavaType paramJavaType, JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonDeserializer<?> paramJsonDeserializer, boolean paramBoolean, Object paramObject)
  {
    this._type = paramJavaType;
    this._parser = paramJsonParser;
    this._context = paramDeserializationContext;
    this._deserializer = paramJsonDeserializer;
    this._closeParser = paramBoolean;
    if (paramObject == null) {}
    for (this._updatedValue = null; paramJsonParser == null; this._updatedValue = paramObject)
    {
      this._seqContext = null;
      this._state = 0;
      return;
    }
    JsonStreamContext localJsonStreamContext = paramJsonParser.getParsingContext();
    if ((paramBoolean) && (paramJsonParser.isExpectedStartArrayToken())) {
      paramJsonParser.clearCurrentToken();
    }
    for (;;)
    {
      this._seqContext = localJsonStreamContext;
      this._state = 2;
      break;
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      if ((localJsonToken == JsonToken.START_OBJECT) || (localJsonToken == JsonToken.START_ARRAY)) {
        localJsonStreamContext = localJsonStreamContext.getParent();
      }
    }
  }
  
  protected static <T> MappingIterator<T> emptyIterator()
  {
    return EMPTY_ITERATOR;
  }
  
  protected <R> R _handleIOException(IOException paramIOException)
  {
    throw new RuntimeException(paramIOException.getMessage(), paramIOException);
  }
  
  protected <R> R _handleMappingException(JsonMappingException paramJsonMappingException)
  {
    throw new RuntimeJsonMappingException(paramJsonMappingException.getMessage(), paramJsonMappingException);
  }
  
  protected void _resync()
    throws IOException
  {
    JsonParser localJsonParser = this._parser;
    if (localJsonParser.getParsingContext() == this._seqContext) {}
    for (;;)
    {
      return;
      JsonToken localJsonToken;
      if ((localJsonToken == JsonToken.START_ARRAY) || (localJsonToken == JsonToken.START_OBJECT)) {
        localJsonParser.skipChildren();
      }
      while (localJsonToken != null)
      {
        do
        {
          localJsonToken = localJsonParser.nextToken();
          if ((localJsonToken != JsonToken.END_ARRAY) && (localJsonToken != JsonToken.END_OBJECT)) {
            break;
          }
        } while (localJsonParser.getParsingContext() != this._seqContext);
        localJsonParser.clearCurrentToken();
        break;
      }
    }
  }
  
  protected <R> R _throwNoSuchElement()
  {
    throw new NoSuchElementException();
  }
  
  public void close()
    throws IOException
  {
    if (this._state != 0)
    {
      this._state = 0;
      if (this._parser != null) {
        this._parser.close();
      }
    }
  }
  
  public JsonLocation getCurrentLocation()
  {
    return this._parser.getCurrentLocation();
  }
  
  public JsonParser getParser()
  {
    return this._parser;
  }
  
  public FormatSchema getParserSchema()
  {
    return this._parser.getSchema();
  }
  
  public boolean hasNext()
  {
    try
    {
      boolean bool2 = hasNextValue();
      bool1 = bool2;
    }
    catch (JsonMappingException localJsonMappingException)
    {
      for (;;)
      {
        bool1 = ((Boolean)_handleMappingException(localJsonMappingException)).booleanValue();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        boolean bool1 = ((Boolean)_handleIOException(localIOException)).booleanValue();
      }
    }
    return bool1;
  }
  
  public boolean hasNextValue()
    throws IOException
  {
    boolean bool = false;
    switch (this._state)
    {
    default: 
      bool = true;
    }
    for (;;)
    {
      return bool;
      _resync();
      if (this._parser.getCurrentToken() == null)
      {
        JsonToken localJsonToken = this._parser.nextToken();
        if ((localJsonToken == null) || (localJsonToken == JsonToken.END_ARRAY))
        {
          this._state = 0;
          if ((!this._closeParser) || (this._parser == null)) {
            continue;
          }
          this._parser.close();
          continue;
        }
      }
      this._state = 3;
      bool = true;
    }
  }
  
  public T next()
  {
    try
    {
      Object localObject = nextValue();
      return (T)localObject;
    }
    catch (JsonMappingException localJsonMappingException)
    {
      throw new RuntimeJsonMappingException(localJsonMappingException.getMessage(), localJsonMappingException);
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException.getMessage(), localIOException);
    }
  }
  
  /* Error */
  public T nextValue()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 64	com/fasterxml/jackson/databind/MappingIterator:_state	I
    //   4: tableswitch	default:+28->32, 0:+69->73, 1:+77->81, 2:+77->81
    //   32: aload_0
    //   33: getfield 60	com/fasterxml/jackson/databind/MappingIterator:_updatedValue	Ljava/lang/Object;
    //   36: ifnonnull +60 -> 96
    //   39: aload_0
    //   40: getfield 56	com/fasterxml/jackson/databind/MappingIterator:_deserializer	Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   43: aload_0
    //   44: getfield 52	com/fasterxml/jackson/databind/MappingIterator:_parser	Lcom/fasterxml/jackson/core/JsonParser;
    //   47: aload_0
    //   48: getfield 54	com/fasterxml/jackson/databind/MappingIterator:_context	Lcom/fasterxml/jackson/databind/DeserializationContext;
    //   51: invokevirtual 177	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   54: astore 4
    //   56: aload 4
    //   58: astore_1
    //   59: aload_0
    //   60: iconst_2
    //   61: putfield 64	com/fasterxml/jackson/databind/MappingIterator:_state	I
    //   64: aload_0
    //   65: getfield 52	com/fasterxml/jackson/databind/MappingIterator:_parser	Lcom/fasterxml/jackson/core/JsonParser;
    //   68: invokevirtual 77	com/fasterxml/jackson/core/JsonParser:clearCurrentToken	()V
    //   71: aload_1
    //   72: areturn
    //   73: aload_0
    //   74: invokevirtual 179	com/fasterxml/jackson/databind/MappingIterator:_throwNoSuchElement	()Ljava/lang/Object;
    //   77: astore_1
    //   78: goto -7 -> 71
    //   81: aload_0
    //   82: invokevirtual 156	com/fasterxml/jackson/databind/MappingIterator:hasNextValue	()Z
    //   85: ifne -53 -> 32
    //   88: aload_0
    //   89: invokevirtual 179	com/fasterxml/jackson/databind/MappingIterator:_throwNoSuchElement	()Ljava/lang/Object;
    //   92: astore_1
    //   93: goto -22 -> 71
    //   96: aload_0
    //   97: getfield 56	com/fasterxml/jackson/databind/MappingIterator:_deserializer	Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   100: aload_0
    //   101: getfield 52	com/fasterxml/jackson/databind/MappingIterator:_parser	Lcom/fasterxml/jackson/core/JsonParser;
    //   104: aload_0
    //   105: getfield 54	com/fasterxml/jackson/databind/MappingIterator:_context	Lcom/fasterxml/jackson/databind/DeserializationContext;
    //   108: aload_0
    //   109: getfield 60	com/fasterxml/jackson/databind/MappingIterator:_updatedValue	Ljava/lang/Object;
    //   112: invokevirtual 182	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Ljava/lang/Object;)Ljava/lang/Object;
    //   115: pop
    //   116: aload_0
    //   117: getfield 60	com/fasterxml/jackson/databind/MappingIterator:_updatedValue	Ljava/lang/Object;
    //   120: astore_1
    //   121: goto -62 -> 59
    //   124: astore_2
    //   125: aload_0
    //   126: iconst_1
    //   127: putfield 64	com/fasterxml/jackson/databind/MappingIterator:_state	I
    //   130: aload_0
    //   131: getfield 52	com/fasterxml/jackson/databind/MappingIterator:_parser	Lcom/fasterxml/jackson/core/JsonParser;
    //   134: invokevirtual 77	com/fasterxml/jackson/core/JsonParser:clearCurrentToken	()V
    //   137: aload_2
    //   138: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	MappingIterator
    //   58	63	1	localObject1	Object
    //   124	14	2	localObject2	Object
    //   54	3	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   32	56	124	finally
    //   96	121	124	finally
  }
  
  public <C extends Collection<? super T>> C readAll(C paramC)
    throws IOException
  {
    while (hasNextValue()) {
      paramC.add(nextValue());
    }
    return paramC;
  }
  
  public List<T> readAll()
    throws IOException
  {
    return readAll(new ArrayList());
  }
  
  public <L extends List<? super T>> L readAll(L paramL)
    throws IOException
  {
    while (hasNextValue()) {
      paramL.add(nextValue());
    }
    return paramL;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/MappingIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */