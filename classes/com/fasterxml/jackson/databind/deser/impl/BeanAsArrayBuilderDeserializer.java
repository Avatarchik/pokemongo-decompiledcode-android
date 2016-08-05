package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;

public class BeanAsArrayBuilderDeserializer
  extends BeanDeserializerBase
{
  private static final long serialVersionUID = 1L;
  protected final AnnotatedMethod _buildMethod;
  protected final BeanDeserializerBase _delegate;
  protected final SettableBeanProperty[] _orderedProperties;
  
  public BeanAsArrayBuilderDeserializer(BeanDeserializerBase paramBeanDeserializerBase, SettableBeanProperty[] paramArrayOfSettableBeanProperty, AnnotatedMethod paramAnnotatedMethod)
  {
    super(paramBeanDeserializerBase);
    this._delegate = paramBeanDeserializerBase;
    this._orderedProperties = paramArrayOfSettableBeanProperty;
    this._buildMethod = paramAnnotatedMethod;
  }
  
  protected Object _deserializeFromNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this._beanType.getRawClass().getName();
    arrayOfObject[1] = paramJsonParser.getCurrentToken();
    throw paramDeserializationContext.mappingException("Can not deserialize a POJO (of type %s) from non-Array representation (token: %s): type/property designed to be serialized as JSON Array", arrayOfObject);
  }
  
  protected Object _deserializeNonVanilla(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (this._nonStandardCreation) {
      localObject = _deserializeWithCreator(paramJsonParser, paramDeserializationContext);
    }
    for (;;)
    {
      return localObject;
      localObject = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
      if (this._injectables != null) {
        injectValues(paramDeserializationContext, localObject);
      }
      Class localClass;
      SettableBeanProperty[] arrayOfSettableBeanProperty;
      int i;
      int j;
      if (this._needViewProcesing)
      {
        localClass = paramDeserializationContext.getActiveView();
        arrayOfSettableBeanProperty = this._orderedProperties;
        i = 0;
        j = arrayOfSettableBeanProperty.length;
      }
      for (;;)
      {
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {
          break label185;
        }
        if (i == j)
        {
          if (this._ignoreAllUnknown) {
            break label187;
          }
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(j);
          throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most %d properties (in JSON Array)", arrayOfObject);
          localClass = null;
          break;
        }
        SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
        i++;
        if ((localSettableBeanProperty != null) && ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass)))) {
          try
          {
            localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject);
          }
          catch (Exception localException)
          {
            wrapAndThrow(localException, localObject, localSettableBeanProperty.getName(), paramDeserializationContext);
          }
        } else {
          paramJsonParser.skipChildren();
        }
      }
      label185:
      continue;
      label187:
      while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        paramJsonParser.skipChildren();
      }
    }
  }
  
  protected final Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, this._objectIdReader);
    SettableBeanProperty[] arrayOfSettableBeanProperty = this._orderedProperties;
    int i = arrayOfSettableBeanProperty.length;
    int j = 0;
    localObject1 = null;
    if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
    {
      SettableBeanProperty localSettableBeanProperty1;
      if (j < i)
      {
        localSettableBeanProperty1 = arrayOfSettableBeanProperty[j];
        label58:
        if (localSettableBeanProperty1 != null) {
          break label80;
        }
        paramJsonParser.skipChildren();
      }
      for (;;)
      {
        j++;
        break;
        localSettableBeanProperty1 = null;
        break label58;
        label80:
        if (localObject1 != null)
        {
          try
          {
            Object localObject4 = localSettableBeanProperty1.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
            localObject1 = localObject4;
          }
          catch (Exception localException3)
          {
            wrapAndThrow(localException3, localObject1, localSettableBeanProperty1.getName(), paramDeserializationContext);
          }
        }
        else
        {
          String str = localSettableBeanProperty1.getName();
          SettableBeanProperty localSettableBeanProperty2 = localPropertyBasedCreator.findCreatorProperty(str);
          if (localSettableBeanProperty2 != null)
          {
            if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty2, localSettableBeanProperty2.deserialize(paramJsonParser, paramDeserializationContext))) {
              try
              {
                Object localObject3 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
                localObject1 = localObject3;
                if (localObject1.getClass() == this._beanType.getRawClass()) {
                  continue;
                }
                Object[] arrayOfObject = new Object[2];
                arrayOfObject[0] = this._beanType.getRawClass().getName();
                arrayOfObject[1] = localObject1.getClass().getName();
                throw paramDeserializationContext.mappingException("Can not support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type %s, actual type %s", arrayOfObject);
              }
              catch (Exception localException2)
              {
                wrapAndThrow(localException2, this._beanType.getRawClass(), str, paramDeserializationContext);
              }
            }
          }
          else if (!localPropertyValueBuffer.readIdProperty(str)) {
            localPropertyValueBuffer.bufferProperty(localSettableBeanProperty1, localSettableBeanProperty1.deserialize(paramJsonParser, paramDeserializationContext));
          }
        }
      }
    }
    if (localObject1 == null) {}
    try
    {
      Object localObject2 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
      localObject1 = localObject2;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        wrapInstantiationProblem(localException1, paramDeserializationContext);
        localObject1 = null;
      }
    }
    return localObject1;
  }
  
  protected Object _deserializeWithCreator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
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
    throw JsonMappingException.from(paramJsonParser, "No suitable constructor found for type " + this._beanType + ": can not instantiate from JSON object (need to add/enable type information?)");
  }
  
  protected BeanAsArrayBuilderDeserializer asArrayDeserializer()
  {
    return this;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject3;
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      localObject3 = finishBuild(paramDeserializationContext, _deserializeFromNonArray(paramJsonParser, paramDeserializationContext));
    }
    for (;;)
    {
      return localObject3;
      if (!this._vanillaProcessing)
      {
        localObject3 = finishBuild(paramDeserializationContext, _deserializeNonVanilla(paramJsonParser, paramDeserializationContext));
      }
      else
      {
        Object localObject1 = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
        SettableBeanProperty[] arrayOfSettableBeanProperty = this._orderedProperties;
        int i = 0;
        int j = arrayOfSettableBeanProperty.length;
        if (paramJsonParser.nextToken() == JsonToken.END_ARRAY)
        {
          localObject3 = finishBuild(paramDeserializationContext, localObject1);
        }
        else
        {
          if (i == j)
          {
            if (!this._ignoreAllUnknown)
            {
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = Integer.valueOf(j);
              throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most %d properties (in JSON Array)", arrayOfObject);
            }
          }
          else
          {
            SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
            if (localSettableBeanProperty != null) {}
            for (;;)
            {
              try
              {
                Object localObject2 = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
                localObject1 = localObject2;
                i++;
              }
              catch (Exception localException)
              {
                wrapAndThrow(localException, localObject1, localSettableBeanProperty.getName(), paramDeserializationContext);
                continue;
              }
              break;
              paramJsonParser.skipChildren();
            }
          }
          while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
            paramJsonParser.skipChildren();
          }
          localObject3 = finishBuild(paramDeserializationContext, localObject1);
        }
      }
    }
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (this._injectables != null) {
      injectValues(paramDeserializationContext, paramObject);
    }
    SettableBeanProperty[] arrayOfSettableBeanProperty = this._orderedProperties;
    int i = 0;
    int j = arrayOfSettableBeanProperty.length;
    if (paramJsonParser.nextToken() == JsonToken.END_ARRAY) {}
    for (Object localObject2 = finishBuild(paramDeserializationContext, paramObject);; localObject2 = finishBuild(paramDeserializationContext, paramObject))
    {
      return localObject2;
      if (i == j)
      {
        if (!this._ignoreAllUnknown)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(j);
          throw paramDeserializationContext.mappingException("Unexpected JSON values; expected at most %d properties (in JSON Array)", arrayOfObject);
        }
      }
      else
      {
        SettableBeanProperty localSettableBeanProperty = arrayOfSettableBeanProperty[i];
        if (localSettableBeanProperty != null) {}
        for (;;)
        {
          try
          {
            Object localObject1 = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
            paramObject = localObject1;
            i++;
          }
          catch (Exception localException)
          {
            wrapAndThrow(localException, paramObject, localSettableBeanProperty.getName(), paramDeserializationContext);
            continue;
          }
          break;
          paramJsonParser.skipChildren();
        }
      }
      while (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        paramJsonParser.skipChildren();
      }
    }
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return _deserializeFromNonArray(paramJsonParser, paramDeserializationContext);
  }
  
  protected final Object finishBuild(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    try
    {
      Object localObject2 = this._buildMethod.getMember().invoke(paramObject, new Object[0]);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        wrapInstantiationProblem(localException, paramDeserializationContext);
        Object localObject1 = null;
      }
    }
    return localObject1;
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    return this._delegate.unwrappingDeserializer(paramNameTransformer);
  }
  
  public BeanAsArrayBuilderDeserializer withIgnorableProperties(HashSet<String> paramHashSet)
  {
    return new BeanAsArrayBuilderDeserializer(this._delegate.withIgnorableProperties(paramHashSet), this._orderedProperties, this._buildMethod);
  }
  
  public BeanAsArrayBuilderDeserializer withObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    return new BeanAsArrayBuilderDeserializer(this._delegate.withObjectIdReader(paramObjectIdReader), this._orderedProperties, this._buildMethod);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/BeanAsArrayBuilderDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */