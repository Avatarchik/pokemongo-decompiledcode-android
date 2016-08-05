package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@JacksonStdImpl
public class CollectionDeserializer
  extends ContainerDeserializerBase<Collection<Object>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = -1L;
  protected final JavaType _collectionType;
  protected final JsonDeserializer<Object> _delegateDeserializer;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final ValueInstantiator _valueInstantiator;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator)
  {
    this(paramJavaType, paramJsonDeserializer, paramTypeDeserializer, paramValueInstantiator, null);
  }
  
  protected CollectionDeserializer(JavaType paramJavaType, JsonDeserializer<Object> paramJsonDeserializer1, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator, JsonDeserializer<Object> paramJsonDeserializer2)
  {
    super(paramJavaType);
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer1;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramValueInstantiator;
    this._delegateDeserializer = paramJsonDeserializer2;
  }
  
  protected CollectionDeserializer(CollectionDeserializer paramCollectionDeserializer)
  {
    super(paramCollectionDeserializer._collectionType);
    this._collectionType = paramCollectionDeserializer._collectionType;
    this._valueDeserializer = paramCollectionDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramCollectionDeserializer._valueTypeDeserializer;
    this._valueInstantiator = paramCollectionDeserializer._valueInstantiator;
    this._delegateDeserializer = paramCollectionDeserializer._delegateDeserializer;
  }
  
  public CollectionDeserializer createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer1 = null;
    if ((this._valueInstantiator != null) && (this._valueInstantiator.canCreateUsingDelegate()))
    {
      JavaType localJavaType2 = this._valueInstantiator.getDelegateType(paramDeserializationContext.getConfig());
      if (localJavaType2 == null) {
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._collectionType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
      }
      localJsonDeserializer1 = findDeserializer(paramDeserializationContext, localJavaType2, paramBeanProperty);
    }
    JsonDeserializer localJsonDeserializer2 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, this._valueDeserializer);
    JavaType localJavaType1 = this._collectionType.getContentType();
    if (localJsonDeserializer2 == null) {}
    for (JsonDeserializer localJsonDeserializer3 = paramDeserializationContext.findContextualValueDeserializer(localJavaType1, paramBeanProperty);; localJsonDeserializer3 = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer2, paramBeanProperty, localJavaType1))
    {
      TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
      if (localTypeDeserializer != null) {
        localTypeDeserializer = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      return withResolved(localJsonDeserializer1, localJsonDeserializer3, localTypeDeserializer);
    }
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Collection localCollection;
    if (this._delegateDeserializer != null) {
      localCollection = (Collection)this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    for (;;)
    {
      return localCollection;
      if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING)
      {
        String str = paramJsonParser.getText();
        if (str.length() == 0)
        {
          localCollection = (Collection)this._valueInstantiator.createFromString(paramDeserializationContext, str);
          continue;
        }
      }
      localCollection = deserialize(paramJsonParser, paramDeserializationContext, (Collection)this._valueInstantiator.createUsingDefault(paramDeserializationContext));
    }
  }
  
  public Collection<Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException
  {
    if (!paramJsonParser.isExpectedStartArrayToken())
    {
      paramCollection = handleNonArray(paramJsonParser, paramDeserializationContext, paramCollection);
      return paramCollection;
    }
    paramJsonParser.setCurrentValue(paramCollection);
    JsonDeserializer localJsonDeserializer = this._valueDeserializer;
    TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
    CollectionReferringAccumulator localCollectionReferringAccumulator;
    if (localJsonDeserializer.getObjectIdReader() == null) {
      localCollectionReferringAccumulator = null;
    }
    for (;;)
    {
      JsonToken localJsonToken = paramJsonParser.nextToken();
      if (localJsonToken == JsonToken.END_ARRAY) {
        break;
      }
      try
      {
        if (localJsonToken == JsonToken.VALUE_NULL)
        {
          localObject = localJsonDeserializer.getNullValue(paramDeserializationContext);
          if (localCollectionReferringAccumulator == null) {
            break label160;
          }
          localCollectionReferringAccumulator.add(localObject);
        }
      }
      catch (UnresolvedForwardReference localUnresolvedForwardReference)
      {
        Object localObject;
        for (;;)
        {
          if (localCollectionReferringAccumulator != null) {
            break label210;
          }
          throw JsonMappingException.from(paramJsonParser, "Unresolved forward reference but no identity info", localUnresolvedForwardReference);
          localCollectionReferringAccumulator = new CollectionReferringAccumulator(this._collectionType.getContentType().getRawClass(), paramCollection);
          break;
          if (localTypeDeserializer == null) {
            localObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
          } else {
            localObject = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          }
        }
        paramCollection.add(localObject);
      }
      catch (Exception localException)
      {
        label160:
        if ((paramDeserializationContext == null) || (paramDeserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS))) {}
        for (int i = 1;; i = 0)
        {
          if ((i != 0) || (!(localException instanceof RuntimeException))) {
            break label238;
          }
          throw ((RuntimeException)localException);
          label210:
          ReadableObjectId.Referring localReferring = localCollectionReferringAccumulator.handleUnresolvedReference(localUnresolvedForwardReference);
          localUnresolvedForwardReference.getRoid().appendReferring(localReferring);
          break;
        }
        label238:
        throw JsonMappingException.wrapWithPath(localException, paramCollection, paramCollection.size());
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._collectionType.getContentType();
  }
  
  /* Error */
  protected final Collection<Object> handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection)
    throws IOException
  {
    // Byte code:
    //   0: aload_2
    //   1: getstatic 285	com/fasterxml/jackson/databind/DeserializationFeature:ACCEPT_SINGLE_VALUE_AS_ARRAY	Lcom/fasterxml/jackson/databind/DeserializationFeature;
    //   4: invokevirtual 254	com/fasterxml/jackson/databind/DeserializationContext:isEnabled	(Lcom/fasterxml/jackson/databind/DeserializationFeature;)Z
    //   7: ifne +15 -> 22
    //   10: aload_2
    //   11: aload_0
    //   12: getfield 38	com/fasterxml/jackson/databind/deser/std/CollectionDeserializer:_collectionType	Lcom/fasterxml/jackson/databind/JavaType;
    //   15: invokevirtual 234	com/fasterxml/jackson/databind/JavaType:getRawClass	()Ljava/lang/Class;
    //   18: invokevirtual 289	com/fasterxml/jackson/databind/DeserializationContext:mappingException	(Ljava/lang/Class;)Lcom/fasterxml/jackson/databind/JsonMappingException;
    //   21: athrow
    //   22: aload_0
    //   23: getfield 40	com/fasterxml/jackson/databind/deser/std/CollectionDeserializer:_valueDeserializer	Lcom/fasterxml/jackson/databind/JsonDeserializer;
    //   26: astore 4
    //   28: aload_0
    //   29: getfield 42	com/fasterxml/jackson/databind/deser/std/CollectionDeserializer:_valueTypeDeserializer	Lcom/fasterxml/jackson/databind/jsontype/TypeDeserializer;
    //   32: astore 5
    //   34: aload_1
    //   35: invokevirtual 169	com/fasterxml/jackson/core/JsonParser:getCurrentToken	()Lcom/fasterxml/jackson/core/JsonToken;
    //   38: astore 6
    //   40: aload 6
    //   42: getstatic 219	com/fasterxml/jackson/core/JsonToken:VALUE_NULL	Lcom/fasterxml/jackson/core/JsonToken;
    //   45: if_acmpne +26 -> 71
    //   48: aload 4
    //   50: aload_2
    //   51: invokevirtual 222	com/fasterxml/jackson/databind/JsonDeserializer:getNullValue	(Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   54: astore 11
    //   56: aload 11
    //   58: astore 9
    //   60: aload_3
    //   61: aload 9
    //   63: invokeinterface 244 2 0
    //   68: pop
    //   69: aload_3
    //   70: areturn
    //   71: aload 5
    //   73: ifnonnull +15 -> 88
    //   76: aload 4
    //   78: aload_1
    //   79: aload_2
    //   80: invokevirtual 159	com/fasterxml/jackson/databind/JsonDeserializer:deserialize	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Ljava/lang/Object;
    //   83: astore 9
    //   85: goto -25 -> 60
    //   88: aload 4
    //   90: aload_1
    //   91: aload_2
    //   92: aload 5
    //   94: invokevirtual 241	com/fasterxml/jackson/databind/JsonDeserializer:deserializeWithType	(Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/jsontype/TypeDeserializer;)Ljava/lang/Object;
    //   97: astore 8
    //   99: aload 8
    //   101: astore 9
    //   103: goto -43 -> 60
    //   106: astore 7
    //   108: aload 7
    //   110: ldc 90
    //   112: aload_3
    //   113: invokeinterface 273 1 0
    //   118: invokestatic 277	com/fasterxml/jackson/databind/JsonMappingException:wrapWithPath	(Ljava/lang/Throwable;Ljava/lang/Object;I)Lcom/fasterxml/jackson/databind/JsonMappingException;
    //   121: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	122	0	this	CollectionDeserializer
    //   0	122	1	paramJsonParser	JsonParser
    //   0	122	2	paramDeserializationContext	DeserializationContext
    //   0	122	3	paramCollection	Collection<Object>
    //   26	63	4	localJsonDeserializer	JsonDeserializer
    //   32	61	5	localTypeDeserializer	TypeDeserializer
    //   38	3	6	localJsonToken	JsonToken
    //   106	3	7	localException	Exception
    //   97	3	8	localObject1	Object
    //   58	44	9	localObject2	Object
    //   54	3	11	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   40	56	106	java/lang/Exception
    //   76	99	106	java/lang/Exception
  }
  
  public boolean isCachable()
  {
    if ((this._valueDeserializer == null) && (this._valueTypeDeserializer == null) && (this._delegateDeserializer == null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected CollectionDeserializer withResolved(JsonDeserializer<?> paramJsonDeserializer1, JsonDeserializer<?> paramJsonDeserializer2, TypeDeserializer paramTypeDeserializer)
  {
    if ((paramJsonDeserializer1 == this._delegateDeserializer) && (paramJsonDeserializer2 == this._valueDeserializer) && (paramTypeDeserializer == this._valueTypeDeserializer)) {}
    for (;;)
    {
      return this;
      this = new CollectionDeserializer(this._collectionType, paramJsonDeserializer2, paramTypeDeserializer, this._valueInstantiator, paramJsonDeserializer1);
    }
  }
  
  private static final class CollectionReferring
    extends ReadableObjectId.Referring
  {
    private final CollectionDeserializer.CollectionReferringAccumulator _parent;
    public final List<Object> next = new ArrayList();
    
    CollectionReferring(CollectionDeserializer.CollectionReferringAccumulator paramCollectionReferringAccumulator, UnresolvedForwardReference paramUnresolvedForwardReference, Class<?> paramClass)
    {
      super(paramClass);
      this._parent = paramCollectionReferringAccumulator;
    }
    
    public void handleResolvedForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      this._parent.resolveForwardReference(paramObject1, paramObject2);
    }
  }
  
  public static final class CollectionReferringAccumulator
  {
    private List<CollectionDeserializer.CollectionReferring> _accumulator = new ArrayList();
    private final Class<?> _elementType;
    private final Collection<Object> _result;
    
    public CollectionReferringAccumulator(Class<?> paramClass, Collection<Object> paramCollection)
    {
      this._elementType = paramClass;
      this._result = paramCollection;
    }
    
    public void add(Object paramObject)
    {
      if (this._accumulator.isEmpty()) {
        this._result.add(paramObject);
      }
      for (;;)
      {
        return;
        ((CollectionDeserializer.CollectionReferring)this._accumulator.get(-1 + this._accumulator.size())).next.add(paramObject);
      }
    }
    
    public ReadableObjectId.Referring handleUnresolvedReference(UnresolvedForwardReference paramUnresolvedForwardReference)
    {
      CollectionDeserializer.CollectionReferring localCollectionReferring = new CollectionDeserializer.CollectionReferring(this, paramUnresolvedForwardReference, this._elementType);
      this._accumulator.add(localCollectionReferring);
      return localCollectionReferring;
    }
    
    public void resolveForwardReference(Object paramObject1, Object paramObject2)
      throws IOException
    {
      Iterator localIterator = this._accumulator.iterator();
      CollectionDeserializer.CollectionReferring localCollectionReferring;
      for (Object localObject = this._result; localIterator.hasNext(); localObject = localCollectionReferring.next)
      {
        localCollectionReferring = (CollectionDeserializer.CollectionReferring)localIterator.next();
        if (localCollectionReferring.hasId(paramObject1))
        {
          localIterator.remove();
          ((Collection)localObject).add(paramObject2);
          ((Collection)localObject).addAll(localCollectionReferring.next);
          return;
        }
      }
      throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + paramObject1 + "] that wasn't previously seen as unresolved.");
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/CollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */