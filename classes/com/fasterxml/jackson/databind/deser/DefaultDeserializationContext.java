package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.KeyDeserializer.None;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId.Referring;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public abstract class DefaultDeserializationContext
  extends DeserializationContext
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private List<ObjectIdResolver> _objectIdResolvers;
  protected transient LinkedHashMap<ObjectIdGenerator.IdKey, ReadableObjectId> _objectIds;
  
  protected DefaultDeserializationContext(DefaultDeserializationContext paramDefaultDeserializationContext)
  {
    super(paramDefaultDeserializationContext);
  }
  
  protected DefaultDeserializationContext(DefaultDeserializationContext paramDefaultDeserializationContext, DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues)
  {
    super(paramDefaultDeserializationContext, paramDeserializationConfig, paramJsonParser, paramInjectableValues);
  }
  
  protected DefaultDeserializationContext(DefaultDeserializationContext paramDefaultDeserializationContext, DeserializerFactory paramDeserializerFactory)
  {
    super(paramDefaultDeserializationContext, paramDeserializerFactory);
  }
  
  protected DefaultDeserializationContext(DeserializerFactory paramDeserializerFactory, DeserializerCache paramDeserializerCache)
  {
    super(paramDeserializerFactory, paramDeserializerCache);
  }
  
  public void checkUnresolvedObjectId()
    throws UnresolvedForwardReference
  {
    if (this._objectIds == null) {}
    UnresolvedForwardReference localUnresolvedForwardReference;
    do
    {
      do
      {
        return;
      } while (!isEnabled(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS));
      localUnresolvedForwardReference = null;
      Iterator localIterator1 = this._objectIds.entrySet().iterator();
      while (localIterator1.hasNext())
      {
        ReadableObjectId localReadableObjectId = (ReadableObjectId)((Map.Entry)localIterator1.next()).getValue();
        if ((localReadableObjectId.hasReferringProperties()) && (!tryToResolveUnresolvedObjectId(localReadableObjectId)))
        {
          if (localUnresolvedForwardReference == null) {
            localUnresolvedForwardReference = new UnresolvedForwardReference("Unresolved forward references for: ");
          }
          Object localObject = localReadableObjectId.getKey().key;
          Iterator localIterator2 = localReadableObjectId.referringProperties();
          while (localIterator2.hasNext())
          {
            ReadableObjectId.Referring localReferring = (ReadableObjectId.Referring)localIterator2.next();
            localUnresolvedForwardReference.addUnresolvedId(localObject, localReferring.getBeanType(), localReferring.getLocation());
          }
        }
      }
    } while (localUnresolvedForwardReference == null);
    throw localUnresolvedForwardReference;
  }
  
  public DefaultDeserializationContext copy()
  {
    throw new IllegalStateException("DefaultDeserializationContext sub-class not overriding copy()");
  }
  
  public abstract DefaultDeserializationContext createInstance(DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues);
  
  public JsonDeserializer<Object> deserializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    Object localObject = null;
    if (paramObject == null) {
      return (JsonDeserializer<Object>)localObject;
    }
    if ((paramObject instanceof JsonDeserializer)) {
      localObject = (JsonDeserializer)paramObject;
    }
    label20:
    label189:
    label203:
    for (;;)
    {
      Class localClass;
      HandlerInstantiator localHandlerInstantiator;
      if ((localObject instanceof ResolvableDeserializer))
      {
        ((ResolvableDeserializer)localObject).resolve(this);
        break;
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + paramObject.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead");
        }
        localClass = (Class)paramObject;
        if ((localClass == JsonDeserializer.None.class) || (ClassUtil.isBogusClass(localClass))) {
          break;
        }
        if (!JsonDeserializer.class.isAssignableFrom(localClass)) {
          throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<JsonDeserializer>");
        }
        localHandlerInstantiator = this._config.getHandlerInstantiator();
        if (localHandlerInstantiator != null) {
          break label189;
        }
      }
      for (;;)
      {
        if (localObject != null) {
          break label203;
        }
        localObject = (JsonDeserializer)ClassUtil.createInstance(localClass, this._config.canOverrideAccessModifiers());
        break label20;
        break;
        localObject = localHandlerInstantiator.deserializerInstance(this._config, paramAnnotated, localClass);
      }
    }
  }
  
  @Deprecated
  public ReadableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator)
  {
    return findObjectId(paramObject, paramObjectIdGenerator, new SimpleObjectIdResolver());
  }
  
  public ReadableObjectId findObjectId(Object paramObject, ObjectIdGenerator<?> paramObjectIdGenerator, ObjectIdResolver paramObjectIdResolver)
  {
    ReadableObjectId localReadableObjectId;
    if (paramObject == null)
    {
      localReadableObjectId = null;
      return localReadableObjectId;
    }
    ObjectIdGenerator.IdKey localIdKey = paramObjectIdGenerator.key(paramObject);
    label35:
    Object localObject;
    if (this._objectIds == null)
    {
      this._objectIds = new LinkedHashMap();
      localObject = null;
      if (this._objectIdResolvers != null) {
        break label139;
      }
      this._objectIdResolvers = new ArrayList(8);
      label57:
      break label150;
    }
    for (;;)
    {
      if (localObject == null)
      {
        localObject = paramObjectIdResolver.newForDeserialization(this);
        this._objectIdResolvers.add(localObject);
      }
      localReadableObjectId = new ReadableObjectId(localIdKey);
      localReadableObjectId.setResolver((ObjectIdResolver)localObject);
      this._objectIds.put(localIdKey, localReadableObjectId);
      break;
      localReadableObjectId = (ReadableObjectId)this._objectIds.get(localIdKey);
      if (localReadableObjectId == null) {
        break label35;
      }
      break;
      label139:
      Iterator localIterator = this._objectIdResolvers.iterator();
      label150:
      if (localIterator.hasNext())
      {
        ObjectIdResolver localObjectIdResolver = (ObjectIdResolver)localIterator.next();
        if (!localObjectIdResolver.canUseFor(paramObjectIdResolver)) {
          break label57;
        }
        localObject = localObjectIdResolver;
      }
    }
  }
  
  public final KeyDeserializer keyDeserializerInstance(Annotated paramAnnotated, Object paramObject)
    throws JsonMappingException
  {
    KeyDeserializer localKeyDeserializer = null;
    if (paramObject == null) {
      return localKeyDeserializer;
    }
    if ((paramObject instanceof KeyDeserializer)) {
      localKeyDeserializer = (KeyDeserializer)paramObject;
    }
    label20:
    label194:
    label208:
    for (;;)
    {
      Class localClass;
      HandlerInstantiator localHandlerInstantiator;
      if ((localKeyDeserializer instanceof ResolvableDeserializer))
      {
        ((ResolvableDeserializer)localKeyDeserializer).resolve(this);
        break;
        if (!(paramObject instanceof Class)) {
          throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + paramObject.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
        }
        localClass = (Class)paramObject;
        if ((localClass == KeyDeserializer.None.class) || (ClassUtil.isBogusClass(localClass))) {
          break;
        }
        if (!KeyDeserializer.class.isAssignableFrom(localClass)) {
          throw new IllegalStateException("AnnotationIntrospector returned Class " + localClass.getName() + "; expected Class<KeyDeserializer>");
        }
        localHandlerInstantiator = this._config.getHandlerInstantiator();
        if (localHandlerInstantiator != null) {
          break label194;
        }
      }
      for (;;)
      {
        if (localKeyDeserializer != null) {
          break label208;
        }
        localKeyDeserializer = (KeyDeserializer)ClassUtil.createInstance(localClass, this._config.canOverrideAccessModifiers());
        break label20;
        break;
        localKeyDeserializer = localHandlerInstantiator.keyDeserializerInstance(this._config, paramAnnotated, localClass);
      }
    }
  }
  
  protected boolean tryToResolveUnresolvedObjectId(ReadableObjectId paramReadableObjectId)
  {
    return paramReadableObjectId.tryToResolveUnresolved(this);
  }
  
  public abstract DefaultDeserializationContext with(DeserializerFactory paramDeserializerFactory);
  
  public static final class Impl
    extends DefaultDeserializationContext
  {
    private static final long serialVersionUID = 1L;
    
    protected Impl(Impl paramImpl)
    {
      super();
    }
    
    protected Impl(Impl paramImpl, DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues)
    {
      super(paramDeserializationConfig, paramJsonParser, paramInjectableValues);
    }
    
    protected Impl(Impl paramImpl, DeserializerFactory paramDeserializerFactory)
    {
      super(paramDeserializerFactory);
    }
    
    public Impl(DeserializerFactory paramDeserializerFactory)
    {
      super(null);
    }
    
    public DefaultDeserializationContext copy()
    {
      if (getClass() != Impl.class) {}
      for (Object localObject = super.copy();; localObject = new Impl(this)) {
        return (DefaultDeserializationContext)localObject;
      }
    }
    
    public DefaultDeserializationContext createInstance(DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, InjectableValues paramInjectableValues)
    {
      return new Impl(this, paramDeserializationConfig, paramJsonParser, paramInjectableValues);
    }
    
    public DefaultDeserializationContext with(DeserializerFactory paramDeserializerFactory)
    {
      return new Impl(this, paramDeserializerFactory);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/DefaultDeserializationContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */