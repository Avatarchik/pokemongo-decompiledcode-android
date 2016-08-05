package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

public class BeanDeserializer
  extends BeanDeserializerBase
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  protected BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase)
  {
    super(paramBeanDeserializerBase, paramBeanDeserializerBase._ignoreAllUnknown);
  }
  
  public BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase, ObjectIdReader paramObjectIdReader)
  {
    super(paramBeanDeserializerBase, paramObjectIdReader);
  }
  
  protected BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase, NameTransformer paramNameTransformer)
  {
    super(paramBeanDeserializerBase, paramNameTransformer);
  }
  
  public BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase, HashSet<String> paramHashSet)
  {
    super(paramBeanDeserializerBase, paramHashSet);
  }
  
  protected BeanDeserializer(BeanDeserializerBase paramBeanDeserializerBase, boolean paramBoolean)
  {
    super(paramBeanDeserializerBase, paramBoolean);
  }
  
  public BeanDeserializer(BeanDeserializerBuilder paramBeanDeserializerBuilder, BeanDescription paramBeanDescription, BeanPropertyMap paramBeanPropertyMap, Map<String, SettableBeanProperty> paramMap, HashSet<String> paramHashSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramBeanDeserializerBuilder, paramBeanDescription, paramBeanPropertyMap, paramMap, paramHashSet, paramBoolean1, paramBoolean2);
  }
  
  private final Object vanillaDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonToken paramJsonToken)
    throws IOException
  {
    Object localObject = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
    paramJsonParser.setCurrentValue(localObject);
    String str;
    SettableBeanProperty localSettableBeanProperty;
    if (paramJsonParser.hasTokenId(5))
    {
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localSettableBeanProperty = this._beanProperties.find(str);
      if (localSettableBeanProperty == null) {
        break label90;
      }
    }
    for (;;)
    {
      try
      {
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject);
        str = paramJsonParser.nextFieldName();
        if (str != null) {
          break;
        }
        return localObject;
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, localObject, str, paramDeserializationContext);
        continue;
      }
      label90:
      handleUnknownVanilla(paramJsonParser, paramDeserializationContext, localObject, str);
    }
  }
  
  protected final Object _deserializeOther(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonToken paramJsonToken)
    throws IOException
  {
    Object localObject;
    switch (paramJsonToken)
    {
    default: 
      throw paramDeserializationContext.mappingException(handledType());
    case ???: 
      localObject = deserializeFromString(paramJsonParser, paramDeserializationContext);
    }
    for (;;)
    {
      return localObject;
      localObject = deserializeFromNumber(paramJsonParser, paramDeserializationContext);
      continue;
      localObject = deserializeFromDouble(paramJsonParser, paramDeserializationContext);
      continue;
      localObject = deserializeFromEmbedded(paramJsonParser, paramDeserializationContext);
      continue;
      localObject = deserializeFromBoolean(paramJsonParser, paramDeserializationContext);
      continue;
      localObject = deserializeFromArray(paramJsonParser, paramDeserializationContext);
      continue;
      if (this._vanillaProcessing) {
        localObject = vanillaDeserialize(paramJsonParser, paramDeserializationContext, paramJsonToken);
      } else if (this._objectIdReader != null) {
        localObject = deserializeWithObjectId(paramJsonParser, paramDeserializationContext);
      } else {
        localObject = deserializeFromObject(paramJsonParser, paramDeserializationContext);
      }
    }
  }
  
  protected Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, this._objectIdReader);
    TokenBuffer localTokenBuffer = null;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    String str;
    Object localObject3;
    Object localObject1;
    if (localJsonToken == JsonToken.FIELD_NAME)
    {
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty localSettableBeanProperty1 = localPropertyBasedCreator.findCreatorProperty(str);
      if (localSettableBeanProperty1 != null)
      {
        if (!localPropertyValueBuffer.assignParameter(localSettableBeanProperty1, _deserializeWithErrorWrapping(paramJsonParser, paramDeserializationContext, localSettableBeanProperty1))) {
          break label203;
        }
        paramJsonParser.nextToken();
        try
        {
          Object localObject4 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
          localObject3 = localObject4;
        }
        catch (Exception localException3)
        {
          for (;;)
          {
            wrapInstantiationProblem(localException3, paramDeserializationContext);
            localObject3 = null;
          }
          paramJsonParser.setCurrentValue(localObject3);
          if (localObject3.getClass() == this._beanType.getRawClass()) {
            break label164;
          }
        }
        if (localObject3 == null) {
          throw paramDeserializationContext.instantiationException(this._beanType.getRawClass(), "JSON Creator returned null");
        }
        localObject1 = handlePolymorphic(paramJsonParser, paramDeserializationContext, localObject3, localTokenBuffer);
      }
    }
    for (;;)
    {
      return localObject1;
      label164:
      if (localTokenBuffer != null) {
        localObject3 = handleUnknownProperties(paramDeserializationContext, localObject3, localTokenBuffer);
      }
      localObject1 = deserialize(paramJsonParser, paramDeserializationContext, localObject3);
      continue;
      if (localPropertyValueBuffer.readIdProperty(str)) {}
      for (;;)
      {
        label203:
        localJsonToken = paramJsonParser.nextToken();
        break;
        SettableBeanProperty localSettableBeanProperty2 = this._beanProperties.find(str);
        if (localSettableBeanProperty2 != null)
        {
          localPropertyValueBuffer.bufferProperty(localSettableBeanProperty2, _deserializeWithErrorWrapping(paramJsonParser, paramDeserializationContext, localSettableBeanProperty2));
        }
        else if ((this._ignorableProps != null) && (this._ignorableProps.contains(str)))
        {
          handleIgnoredProperty(paramJsonParser, paramDeserializationContext, handledType(), str);
        }
        else if (this._anySetter != null)
        {
          try
          {
            localPropertyValueBuffer.bufferAnyProperty(this._anySetter, str, this._anySetter.deserialize(paramJsonParser, paramDeserializationContext));
          }
          catch (Exception localException2)
          {
            wrapAndThrow(localException2, this._beanType.getRawClass(), str, paramDeserializationContext);
          }
        }
        else
        {
          if (localTokenBuffer == null) {
            localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
          }
          localTokenBuffer.writeFieldName(str);
          localTokenBuffer.copyCurrentStructure(paramJsonParser);
        }
      }
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
        localObject1 = handleUnknownProperties(paramDeserializationContext, localObject1, localTokenBuffer);
      }
      if (localTokenBuffer != null) {
        if (localObject1.getClass() != this._beanType.getRawClass()) {
          localObject1 = handlePolymorphic(null, paramDeserializationContext, localObject1, localTokenBuffer);
        }
      }
    }
  }
  
  protected final Object _deserializeWithErrorWrapping(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, SettableBeanProperty paramSettableBeanProperty)
    throws IOException
  {
    try
    {
      Object localObject2 = paramSettableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        wrapAndThrow(localException, this._beanType.getRawClass(), paramSettableBeanProperty.getName(), paramDeserializationContext);
        Object localObject1 = null;
      }
    }
    return localObject1;
  }
  
  protected Object _missingToken(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    throw paramDeserializationContext.endOfInputException(handledType());
  }
  
  protected BeanDeserializerBase asArrayDeserializer()
  {
    return new BeanAsArrayDeserializer(this, this._beanProperties.getPropertiesInInsertionOrder());
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (paramJsonParser.isExpectedStartObjectToken()) {
      if (this._vanillaProcessing) {
        localObject = vanillaDeserialize(paramJsonParser, paramDeserializationContext, paramJsonParser.nextToken());
      }
    }
    for (;;)
    {
      return localObject;
      paramJsonParser.nextToken();
      if (this._objectIdReader != null)
      {
        localObject = deserializeWithObjectId(paramJsonParser, paramDeserializationContext);
      }
      else
      {
        localObject = deserializeFromObject(paramJsonParser, paramDeserializationContext);
        continue;
        localObject = _deserializeOther(paramJsonParser, paramDeserializationContext, paramJsonParser.getCurrentToken());
      }
    }
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    paramJsonParser.setCurrentValue(paramObject);
    if (this._injectables != null) {
      injectValues(paramDeserializationContext, paramObject);
    }
    if (this._unwrappedPropertyHandler != null) {
      paramObject = deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext, paramObject);
    }
    String str;
    do
    {
      for (;;)
      {
        return paramObject;
        if (this._externalTypeIdHandler == null) {
          break;
        }
        paramObject = deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, paramObject);
      }
      if (!paramJsonParser.isExpectedStartObjectToken()) {
        break;
      }
      str = paramJsonParser.nextFieldName();
    } while (str == null);
    for (;;)
    {
      if (!this._needViewProcesing) {
        break label119;
      }
      Class localClass = paramDeserializationContext.getActiveView();
      if (localClass == null) {
        break label119;
      }
      paramObject = deserializeWithView(paramJsonParser, paramDeserializationContext, paramObject, localClass);
      break;
      if (!paramJsonParser.hasTokenId(5)) {
        break;
      }
      str = paramJsonParser.getCurrentName();
    }
    label119:
    paramJsonParser.nextToken();
    SettableBeanProperty localSettableBeanProperty = this._beanProperties.find(str);
    if (localSettableBeanProperty != null) {}
    for (;;)
    {
      try
      {
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
        str = paramJsonParser.nextFieldName();
        if (str != null) {
          break label119;
        }
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, paramObject, str, paramDeserializationContext);
        continue;
      }
      handleUnknownVanilla(paramJsonParser, paramDeserializationContext, paramObject, str);
    }
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1;
    if ((this._objectIdReader != null) && (this._objectIdReader.maySerializeAsObject()) && (paramJsonParser.hasTokenId(5)) && (this._objectIdReader.isValidReferencePropertyName(paramJsonParser.getCurrentName(), paramJsonParser))) {
      localObject1 = deserializeFromObjectId(paramJsonParser, paramDeserializationContext);
    }
    do
    {
      for (;;)
      {
        return localObject1;
        if (this._nonStandardCreation)
        {
          if (this._unwrappedPropertyHandler != null)
          {
            localObject1 = deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext);
          }
          else if (this._externalTypeIdHandler != null)
          {
            localObject1 = deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext);
          }
          else
          {
            localObject1 = deserializeFromObjectUsingNonDefault(paramJsonParser, paramDeserializationContext);
            if (this._injectables != null) {
              injectValues(paramDeserializationContext, localObject1);
            }
          }
        }
        else
        {
          localObject1 = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
          paramJsonParser.setCurrentValue(localObject1);
          if (paramJsonParser.canReadObjectId())
          {
            Object localObject2 = paramJsonParser.getObjectId();
            if (localObject2 != null) {
              _handleTypedObjectId(paramJsonParser, paramDeserializationContext, localObject1, localObject2);
            }
          }
          if (this._injectables != null) {
            injectValues(paramDeserializationContext, localObject1);
          }
          if (!this._needViewProcesing) {
            break;
          }
          Class localClass = paramDeserializationContext.getActiveView();
          if (localClass == null) {
            break;
          }
          localObject1 = deserializeWithView(paramJsonParser, paramDeserializationContext, localObject1, localClass);
        }
      }
    } while (!paramJsonParser.hasTokenId(5));
    String str = paramJsonParser.getCurrentName();
    label213:
    paramJsonParser.nextToken();
    SettableBeanProperty localSettableBeanProperty = this._beanProperties.find(str);
    if (localSettableBeanProperty != null) {}
    for (;;)
    {
      try
      {
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject1);
        str = paramJsonParser.nextFieldName();
        if (str != null) {
          break label213;
        }
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, localObject1, str, paramDeserializationContext);
        continue;
      }
      handleUnknownVanilla(paramJsonParser, paramDeserializationContext, localObject1, str);
    }
  }
  
  protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1 = null;
    ExternalTypeHandler localExternalTypeHandler = this._externalTypeIdHandler.start();
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, this._objectIdReader);
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
    localTokenBuffer.writeStartObject();
    JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
    String str;
    if (localJsonToken1 == JsonToken.FIELD_NAME)
    {
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty localSettableBeanProperty1 = localPropertyBasedCreator.findCreatorProperty(str);
      if (localSettableBeanProperty1 != null)
      {
        if (localExternalTypeHandler.handlePropertyValue(paramJsonParser, paramDeserializationContext, str, null)) {}
        Object localObject3;
        for (;;)
        {
          localJsonToken1 = paramJsonParser.nextToken();
          break;
          if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty1, _deserializeWithErrorWrapping(paramJsonParser, paramDeserializationContext, localSettableBeanProperty1)))
          {
            JsonToken localJsonToken2 = paramJsonParser.nextToken();
            try
            {
              localObject3 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
              while (localJsonToken2 == JsonToken.FIELD_NAME)
              {
                paramJsonParser.nextToken();
                localTokenBuffer.copyCurrentStructure(paramJsonParser);
                localJsonToken2 = paramJsonParser.nextToken();
              }
            }
            catch (Exception localException2)
            {
              wrapAndThrow(localException2, this._beanType.getRawClass(), str, paramDeserializationContext);
            }
          }
        }
        if (localObject3.getClass() != this._beanType.getRawClass()) {
          throw paramDeserializationContext.mappingException("Can not create polymorphic instances with unwrapped values");
        }
        localObject1 = localExternalTypeHandler.complete(paramJsonParser, paramDeserializationContext, localObject3);
      }
    }
    for (;;)
    {
      return localObject1;
      if (localPropertyValueBuffer.readIdProperty(str)) {
        break;
      }
      SettableBeanProperty localSettableBeanProperty2 = this._beanProperties.find(str);
      if (localSettableBeanProperty2 != null)
      {
        localPropertyValueBuffer.bufferProperty(localSettableBeanProperty2, localSettableBeanProperty2.deserialize(paramJsonParser, paramDeserializationContext));
        break;
      }
      if (localExternalTypeHandler.handlePropertyValue(paramJsonParser, paramDeserializationContext, str, null)) {
        break;
      }
      if ((this._ignorableProps != null) && (this._ignorableProps.contains(str)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, handledType(), str);
        break;
      }
      if (this._anySetter == null) {
        break;
      }
      localPropertyValueBuffer.bufferAnyProperty(this._anySetter, str, this._anySetter.deserialize(paramJsonParser, paramDeserializationContext));
      break;
      try
      {
        Object localObject2 = localExternalTypeHandler.complete(paramJsonParser, paramDeserializationContext, localPropertyValueBuffer, localPropertyBasedCreator);
        localObject1 = localObject2;
      }
      catch (Exception localException1)
      {
        wrapInstantiationProblem(localException1, paramDeserializationContext);
      }
    }
  }
  
  protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, this._objectIdReader);
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
    localTokenBuffer.writeStartObject();
    JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
    String str;
    Object localObject1;
    for (;;)
    {
      if (localJsonToken1 == JsonToken.FIELD_NAME)
      {
        str = paramJsonParser.getCurrentName();
        paramJsonParser.nextToken();
        SettableBeanProperty localSettableBeanProperty1 = localPropertyBasedCreator.findCreatorProperty(str);
        if (localSettableBeanProperty1 != null)
        {
          Object localObject3;
          if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty1, _deserializeWithErrorWrapping(paramJsonParser, paramDeserializationContext, localSettableBeanProperty1)))
          {
            JsonToken localJsonToken2 = paramJsonParser.nextToken();
            try
            {
              localObject3 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
              paramJsonParser.setCurrentValue(localObject3);
              while (localJsonToken2 == JsonToken.FIELD_NAME)
              {
                paramJsonParser.nextToken();
                localTokenBuffer.copyCurrentStructure(paramJsonParser);
                localJsonToken2 = paramJsonParser.nextToken();
              }
              localJsonToken1 = paramJsonParser.nextToken();
            }
            catch (Exception localException3)
            {
              wrapInstantiationProblem(localException3, paramDeserializationContext);
            }
          }
          continue;
          localTokenBuffer.writeEndObject();
          if (localObject3.getClass() != this._beanType.getRawClass())
          {
            localTokenBuffer.close();
            throw paramDeserializationContext.mappingException("Can not create polymorphic instances with unwrapped values");
          }
          localObject1 = this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, localObject3, localTokenBuffer);
        }
      }
    }
    for (;;)
    {
      return localObject1;
      if (localPropertyValueBuffer.readIdProperty(str)) {
        break;
      }
      SettableBeanProperty localSettableBeanProperty2 = this._beanProperties.find(str);
      if (localSettableBeanProperty2 != null)
      {
        localPropertyValueBuffer.bufferProperty(localSettableBeanProperty2, _deserializeWithErrorWrapping(paramJsonParser, paramDeserializationContext, localSettableBeanProperty2));
        break;
      }
      if ((this._ignorableProps != null) && (this._ignorableProps.contains(str)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, handledType(), str);
        break;
      }
      localTokenBuffer.writeFieldName(str);
      localTokenBuffer.copyCurrentStructure(paramJsonParser);
      if (this._anySetter == null) {
        break;
      }
      try
      {
        localPropertyValueBuffer.bufferAnyProperty(this._anySetter, str, this._anySetter.deserialize(paramJsonParser, paramDeserializationContext));
      }
      catch (Exception localException2)
      {
        wrapAndThrow(localException2, this._beanType.getRawClass(), str, paramDeserializationContext);
      }
      break;
      try
      {
        Object localObject2 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
        localObject1 = this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, localObject2, localTokenBuffer);
      }
      catch (Exception localException1)
      {
        wrapInstantiationProblem(localException1, paramDeserializationContext);
        localObject1 = null;
      }
    }
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._propertyBasedCreator != null) {}
    for (Object localObject = deserializeUsingPropertyBasedWithExternalTypeId(paramJsonParser, paramDeserializationContext);; localObject = deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, this._valueInstantiator.createUsingDefault(paramDeserializationContext))) {
      return localObject;
    }
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    Class localClass;
    ExternalTypeHandler localExternalTypeHandler;
    JsonToken localJsonToken1;
    label28:
    String str;
    SettableBeanProperty localSettableBeanProperty;
    if (this._needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      localExternalTypeHandler = this._externalTypeIdHandler.start();
      localJsonToken1 = paramJsonParser.getCurrentToken();
      if (localJsonToken1 != JsonToken.FIELD_NAME) {
        break label237;
      }
      str = paramJsonParser.getCurrentName();
      JsonToken localJsonToken2 = paramJsonParser.nextToken();
      localSettableBeanProperty = this._beanProperties.find(str);
      if (localSettableBeanProperty == null) {
        break label144;
      }
      if (localJsonToken2.isScalarValue()) {
        localExternalTypeHandler.handleTypePropertyValue(paramJsonParser, paramDeserializationContext, str, paramObject);
      }
      if ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass))) {
        break label118;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      localJsonToken1 = paramJsonParser.nextToken();
      break label28;
      localClass = null;
      break;
      try
      {
        label118:
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
      }
      catch (Exception localException2)
      {
        wrapAndThrow(localException2, paramObject, str, paramDeserializationContext);
      }
      continue;
      label144:
      if ((this._ignorableProps != null) && (this._ignorableProps.contains(str))) {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
      } else if (!localExternalTypeHandler.handlePropertyValue(paramJsonParser, paramDeserializationContext, str, paramObject)) {
        if (this._anySetter != null) {
          try
          {
            this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, str);
          }
          catch (Exception localException1)
          {
            wrapAndThrow(localException1, paramObject, str, paramDeserializationContext);
          }
        } else {
          handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
        }
      }
    }
    label237:
    return localExternalTypeHandler.complete(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  protected Object deserializeWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (this._delegateDeserializer != null) {
      localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    for (;;)
    {
      return localObject;
      if (this._propertyBasedCreator != null)
      {
        localObject = deserializeUsingPropertyBasedWithUnwrapped(paramJsonParser, paramDeserializationContext);
      }
      else
      {
        TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
        localTokenBuffer.writeStartObject();
        localObject = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
        paramJsonParser.setCurrentValue(localObject);
        if (this._injectables != null) {
          injectValues(paramDeserializationContext, localObject);
        }
        Class localClass;
        String str;
        label118:
        SettableBeanProperty localSettableBeanProperty;
        if (this._needViewProcesing)
        {
          localClass = paramDeserializationContext.getActiveView();
          if (!paramJsonParser.hasTokenId(5)) {
            break label179;
          }
          str = paramJsonParser.getCurrentName();
          if (str == null) {
            break label295;
          }
          paramJsonParser.nextToken();
          localSettableBeanProperty = this._beanProperties.find(str);
          if (localSettableBeanProperty == null) {
            break label213;
          }
          if ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass))) {
            break label185;
          }
          paramJsonParser.skipChildren();
        }
        for (;;)
        {
          str = paramJsonParser.nextFieldName();
          break label118;
          localClass = null;
          break;
          label179:
          str = null;
          break label118;
          try
          {
            label185:
            localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject);
          }
          catch (Exception localException2)
          {
            wrapAndThrow(localException2, localObject, str, paramDeserializationContext);
          }
          continue;
          label213:
          if ((this._ignorableProps != null) && (this._ignorableProps.contains(str)))
          {
            handleIgnoredProperty(paramJsonParser, paramDeserializationContext, localObject, str);
          }
          else
          {
            localTokenBuffer.writeFieldName(str);
            localTokenBuffer.copyCurrentStructure(paramJsonParser);
            if (this._anySetter != null) {
              try
              {
                this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject, str);
              }
              catch (Exception localException1)
              {
                wrapAndThrow(localException1, localObject, str, paramDeserializationContext);
              }
            }
          }
        }
        label295:
        localTokenBuffer.writeEndObject();
        this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, localObject, localTokenBuffer);
      }
    }
  }
  
  protected Object deserializeWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.START_OBJECT) {
      localJsonToken = paramJsonParser.nextToken();
    }
    TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
    localTokenBuffer.writeStartObject();
    Class localClass;
    String str;
    SettableBeanProperty localSettableBeanProperty;
    if (this._needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      if (localJsonToken != JsonToken.FIELD_NAME) {
        break label211;
      }
      str = paramJsonParser.getCurrentName();
      localSettableBeanProperty = this._beanProperties.find(str);
      paramJsonParser.nextToken();
      if (localSettableBeanProperty == null) {
        break label145;
      }
      if ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass))) {
        break label119;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      localJsonToken = paramJsonParser.nextToken();
      break;
      localClass = null;
      break;
      try
      {
        label119:
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, paramObject, str, paramDeserializationContext);
      }
      continue;
      label145:
      if ((this._ignorableProps != null) && (this._ignorableProps.contains(str)))
      {
        handleIgnoredProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
      }
      else
      {
        localTokenBuffer.writeFieldName(str);
        localTokenBuffer.copyCurrentStructure(paramJsonParser);
        if (this._anySetter != null) {
          this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, str);
        }
      }
    }
    label211:
    localTokenBuffer.writeEndObject();
    this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, paramObject, localTokenBuffer);
    return paramObject;
  }
  
  protected final Object deserializeWithView(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, Class<?> paramClass)
    throws IOException
  {
    String str;
    SettableBeanProperty localSettableBeanProperty;
    if (paramJsonParser.hasTokenId(5))
    {
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localSettableBeanProperty = this._beanProperties.find(str);
      if (localSettableBeanProperty == null) {
        break label89;
      }
      if (localSettableBeanProperty.visibleInView(paramClass)) {
        break label63;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      str = paramJsonParser.nextFieldName();
      if (str != null) {
        break;
      }
      return paramObject;
      try
      {
        label63:
        localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, paramObject, str, paramDeserializationContext);
      }
      continue;
      label89:
      handleUnknownVanilla(paramJsonParser, paramDeserializationContext, paramObject, str);
    }
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    if (getClass() != BeanDeserializer.class) {}
    for (;;)
    {
      return this;
      this = new BeanDeserializer(this, paramNameTransformer);
    }
  }
  
  public BeanDeserializer withIgnorableProperties(HashSet<String> paramHashSet)
  {
    return new BeanDeserializer(this, paramHashSet);
  }
  
  public BeanDeserializer withObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    return new BeanDeserializer(this, paramObjectIdReader);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/BeanDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */