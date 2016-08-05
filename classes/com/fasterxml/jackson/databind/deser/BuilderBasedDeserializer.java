package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanAsArrayBuilderDeserializer;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;

public class BuilderBasedDeserializer
  extends BeanDeserializerBase
{
  private static final long serialVersionUID = 1L;
  protected final AnnotatedMethod _buildMethod;
  
  public BuilderBasedDeserializer(BeanDeserializerBuilder paramBeanDeserializerBuilder, BeanDescription paramBeanDescription, BeanPropertyMap paramBeanPropertyMap, Map<String, SettableBeanProperty> paramMap, HashSet<String> paramHashSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramBeanDeserializerBuilder, paramBeanDescription, paramBeanPropertyMap, paramMap, paramHashSet, paramBoolean1, paramBoolean2);
    this._buildMethod = paramBeanDeserializerBuilder.getBuildMethod();
    if (this._objectIdReader != null) {
      throw new IllegalArgumentException("Can not use Object Id with Builder-based deserialization (type " + paramBeanDescription.getType() + ")");
    }
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer)
  {
    this(paramBuilderBasedDeserializer, paramBuilderBasedDeserializer._ignoreAllUnknown);
  }
  
  public BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer, ObjectIdReader paramObjectIdReader)
  {
    super(paramBuilderBasedDeserializer, paramObjectIdReader);
    this._buildMethod = paramBuilderBasedDeserializer._buildMethod;
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer, NameTransformer paramNameTransformer)
  {
    super(paramBuilderBasedDeserializer, paramNameTransformer);
    this._buildMethod = paramBuilderBasedDeserializer._buildMethod;
  }
  
  public BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer, HashSet<String> paramHashSet)
  {
    super(paramBuilderBasedDeserializer, paramHashSet);
    this._buildMethod = paramBuilderBasedDeserializer._buildMethod;
  }
  
  protected BuilderBasedDeserializer(BuilderBasedDeserializer paramBuilderBasedDeserializer, boolean paramBoolean)
  {
    super(paramBuilderBasedDeserializer, paramBoolean);
    this._buildMethod = paramBuilderBasedDeserializer._buildMethod;
  }
  
  private final Object vanillaDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonToken paramJsonToken)
    throws IOException, JsonProcessingException
  {
    Object localObject1 = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
    if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT)
    {
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty localSettableBeanProperty = this._beanProperties.find(str);
      if (localSettableBeanProperty != null) {}
      for (;;)
      {
        try
        {
          Object localObject2 = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
          localObject1 = localObject2;
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, localObject1, str, paramDeserializationContext);
          continue;
        }
        paramJsonParser.nextToken();
        break;
        handleUnknownVanilla(paramJsonParser, paramDeserializationContext, localObject1, str);
      }
    }
    return localObject1;
  }
  
  protected final Object _deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    if (this._injectables != null) {
      injectValues(paramDeserializationContext, paramObject);
    }
    Object localObject1;
    if (this._unwrappedPropertyHandler != null) {
      localObject1 = deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext, paramObject);
    }
    for (;;)
    {
      return localObject1;
      if (this._externalTypeIdHandler != null)
      {
        localObject1 = deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, paramObject);
      }
      else
      {
        if (this._needViewProcesing)
        {
          Class localClass = paramDeserializationContext.getActiveView();
          if (localClass != null)
          {
            localObject1 = deserializeWithView(paramJsonParser, paramDeserializationContext, paramObject, localClass);
            continue;
          }
        }
        JsonToken localJsonToken = paramJsonParser.getCurrentToken();
        if (localJsonToken == JsonToken.START_OBJECT) {
          localJsonToken = paramJsonParser.nextToken();
        }
        if (localJsonToken == JsonToken.FIELD_NAME)
        {
          String str = paramJsonParser.getCurrentName();
          paramJsonParser.nextToken();
          SettableBeanProperty localSettableBeanProperty = this._beanProperties.find(str);
          if (localSettableBeanProperty != null) {}
          for (;;)
          {
            try
            {
              Object localObject2 = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
              paramObject = localObject2;
            }
            catch (Exception localException)
            {
              wrapAndThrow(localException, paramObject, str, paramDeserializationContext);
              continue;
            }
            localJsonToken = paramJsonParser.nextToken();
            break;
            handleUnknownVanilla(paramJsonParser, paramDeserializationContext, handledType(), str);
          }
        }
        localObject1 = paramObject;
      }
    }
  }
  
  protected final Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    PropertyBasedCreator localPropertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer localPropertyValueBuffer = localPropertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext, this._objectIdReader);
    TokenBuffer localTokenBuffer = null;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    String str;
    if (localJsonToken == JsonToken.FIELD_NAME)
    {
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty localSettableBeanProperty1 = localPropertyBasedCreator.findCreatorProperty(str);
      if (localSettableBeanProperty1 != null) {
        if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty1, localSettableBeanProperty1.deserialize(paramJsonParser, paramDeserializationContext))) {
          paramJsonParser.nextToken();
        }
      }
    }
    for (;;)
    {
      Object localObject4;
      try
      {
        Object localObject3 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
        localObject4 = localObject3;
        if (localObject4.getClass() != this._beanType.getRawClass())
        {
          localObject1 = handlePolymorphic(paramJsonParser, paramDeserializationContext, localObject4, localTokenBuffer);
          label120:
          return localObject1;
        }
      }
      catch (Exception localException2)
      {
        wrapAndThrow(localException2, this._beanType.getRawClass(), str, paramDeserializationContext);
      }
      for (;;)
      {
        localJsonToken = paramJsonParser.nextToken();
        break;
        if (localTokenBuffer != null) {
          localObject4 = handleUnknownProperties(paramDeserializationContext, localObject4, localTokenBuffer);
        }
        localObject1 = _deserialize(paramJsonParser, paramDeserializationContext, localObject4);
        break label120;
        if (!localPropertyValueBuffer.readIdProperty(str))
        {
          SettableBeanProperty localSettableBeanProperty2 = this._beanProperties.find(str);
          if (localSettableBeanProperty2 != null)
          {
            localPropertyValueBuffer.bufferProperty(localSettableBeanProperty2, localSettableBeanProperty2.deserialize(paramJsonParser, paramDeserializationContext));
          }
          else if ((this._ignorableProps != null) && (this._ignorableProps.contains(str)))
          {
            handleIgnoredProperty(paramJsonParser, paramDeserializationContext, handledType(), str);
          }
          else if (this._anySetter != null)
          {
            localPropertyValueBuffer.bufferAnyProperty(this._anySetter, str, this._anySetter.deserialize(paramJsonParser, paramDeserializationContext));
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
      }
      try
      {
        Object localObject2 = localPropertyBasedCreator.build(paramDeserializationContext, localPropertyValueBuffer);
        localObject1 = localObject2;
        if (localTokenBuffer == null) {
          continue;
        }
        if (localObject1.getClass() == this._beanType.getRawClass()) {
          break label381;
        }
        localObject1 = handlePolymorphic(null, paramDeserializationContext, localObject1, localTokenBuffer);
      }
      catch (Exception localException1)
      {
        wrapInstantiationProblem(localException1, paramDeserializationContext);
        localObject1 = null;
      }
      continue;
      label381:
      Object localObject1 = handleUnknownProperties(paramDeserializationContext, localObject1, localTokenBuffer);
    }
  }
  
  protected BeanAsArrayBuilderDeserializer asArrayDeserializer()
  {
    return new BeanAsArrayBuilderDeserializer(this, this._beanProperties.getPropertiesInInsertionOrder(), this._buildMethod);
  }
  
  public final Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
    Object localObject;
    if (localJsonToken1 == JsonToken.START_OBJECT)
    {
      JsonToken localJsonToken2 = paramJsonParser.nextToken();
      if (this._vanillaProcessing) {
        localObject = finishBuild(paramDeserializationContext, vanillaDeserialize(paramJsonParser, paramDeserializationContext, localJsonToken2));
      }
    }
    for (;;)
    {
      return localObject;
      localObject = finishBuild(paramDeserializationContext, deserializeFromObject(paramJsonParser, paramDeserializationContext));
      continue;
      switch (localJsonToken1)
      {
      default: 
        throw paramDeserializationContext.mappingException(handledType());
      case ???: 
        localObject = finishBuild(paramDeserializationContext, deserializeFromString(paramJsonParser, paramDeserializationContext));
        break;
      case ???: 
        localObject = finishBuild(paramDeserializationContext, deserializeFromNumber(paramJsonParser, paramDeserializationContext));
        break;
      case ???: 
        localObject = finishBuild(paramDeserializationContext, deserializeFromDouble(paramJsonParser, paramDeserializationContext));
        break;
      case ???: 
        localObject = paramJsonParser.getEmbeddedObject();
        break;
      case ???: 
      case ???: 
        localObject = finishBuild(paramDeserializationContext, deserializeFromBoolean(paramJsonParser, paramDeserializationContext));
        break;
      case ???: 
        localObject = finishBuild(paramDeserializationContext, deserializeFromArray(paramJsonParser, paramDeserializationContext));
        break;
      case ???: 
      case ???: 
        localObject = finishBuild(paramDeserializationContext, deserializeFromObject(paramJsonParser, paramDeserializationContext));
      }
    }
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    return finishBuild(paramDeserializationContext, _deserialize(paramJsonParser, paramDeserializationContext, paramObject));
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject1;
    if (this._nonStandardCreation) {
      if (this._unwrappedPropertyHandler != null) {
        localObject1 = deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext);
      }
    }
    for (;;)
    {
      return localObject1;
      if (this._externalTypeIdHandler != null)
      {
        localObject1 = deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext);
      }
      else
      {
        localObject1 = deserializeFromObjectUsingNonDefault(paramJsonParser, paramDeserializationContext);
        continue;
        localObject1 = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
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
    label103:
    String str;
    SettableBeanProperty localSettableBeanProperty;
    if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT)
    {
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localSettableBeanProperty = this._beanProperties.find(str);
      if (localSettableBeanProperty == null) {
        break label176;
      }
    }
    for (;;)
    {
      try
      {
        Object localObject2 = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
        localObject1 = localObject2;
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, localObject1, str, paramDeserializationContext);
        continue;
      }
      paramJsonParser.nextToken();
      break label103;
      break;
      label176:
      handleUnknownVanilla(paramJsonParser, paramDeserializationContext, localObject1, str);
    }
  }
  
  protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    throw new IllegalStateException("Deserialization with Builder, External type id, @JsonCreator not yet implemented");
  }
  
  protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
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
          if (localPropertyValueBuffer.assignParameter(localSettableBeanProperty1, localSettableBeanProperty1.deserialize(paramJsonParser, paramDeserializationContext)))
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
              localJsonToken1 = paramJsonParser.nextToken();
            }
            catch (Exception localException2)
            {
              wrapAndThrow(localException2, this._beanType.getRawClass(), str, paramDeserializationContext);
            }
          }
          else
          {
            continue;
          }
          localTokenBuffer.writeEndObject();
          if (localObject3.getClass() != this._beanType.getRawClass()) {
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
        localPropertyValueBuffer.bufferProperty(localSettableBeanProperty2, localSettableBeanProperty2.deserialize(paramJsonParser, paramDeserializationContext));
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
      localPropertyValueBuffer.bufferAnyProperty(this._anySetter, str, this._anySetter.deserialize(paramJsonParser, paramDeserializationContext));
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
    throws IOException, JsonProcessingException
  {
    if (this._propertyBasedCreator != null) {}
    for (Object localObject = deserializeUsingPropertyBasedWithExternalTypeId(paramJsonParser, paramDeserializationContext);; localObject = deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, this._valueInstantiator.createUsingDefault(paramDeserializationContext))) {
      return localObject;
    }
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
  {
    Class localClass;
    ExternalTypeHandler localExternalTypeHandler;
    label22:
    String str;
    SettableBeanProperty localSettableBeanProperty;
    if (this._needViewProcesing)
    {
      localClass = paramDeserializationContext.getActiveView();
      localExternalTypeHandler = this._externalTypeIdHandler.start();
      if (paramJsonParser.getCurrentToken() == JsonToken.END_OBJECT) {
        break label217;
      }
      str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      localSettableBeanProperty = this._beanProperties.find(str);
      if (localSettableBeanProperty == null) {
        break label124;
      }
      if ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass))) {
        break label93;
      }
      paramJsonParser.skipChildren();
    }
    for (;;)
    {
      paramJsonParser.nextToken();
      break label22;
      localClass = null;
      break;
      try
      {
        label93:
        Object localObject = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
        paramObject = localObject;
      }
      catch (Exception localException2)
      {
        wrapAndThrow(localException2, paramObject, str, paramDeserializationContext);
      }
      continue;
      label124:
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
    label217:
    return localExternalTypeHandler.complete(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  protected Object deserializeWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object localObject1;
    if (this._delegateDeserializer != null) {
      localObject1 = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
    }
    for (;;)
    {
      return localObject1;
      if (this._propertyBasedCreator != null)
      {
        localObject1 = deserializeUsingPropertyBasedWithUnwrapped(paramJsonParser, paramDeserializationContext);
      }
      else
      {
        TokenBuffer localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
        localTokenBuffer.writeStartObject();
        localObject1 = this._valueInstantiator.createUsingDefault(paramDeserializationContext);
        if (this._injectables != null) {
          injectValues(paramDeserializationContext, localObject1);
        }
        Class localClass;
        String str;
        SettableBeanProperty localSettableBeanProperty;
        if (this._needViewProcesing)
        {
          localClass = paramDeserializationContext.getActiveView();
          if (paramJsonParser.getCurrentToken() == JsonToken.END_OBJECT) {
            break label285;
          }
          str = paramJsonParser.getCurrentName();
          paramJsonParser.nextToken();
          localSettableBeanProperty = this._beanProperties.find(str);
          if (localSettableBeanProperty == null) {
            break label203;
          }
          if ((localClass == null) || (localSettableBeanProperty.visibleInView(localClass))) {
            break label169;
          }
          paramJsonParser.skipChildren();
        }
        for (;;)
        {
          paramJsonParser.nextToken();
          break;
          localClass = null;
          break;
          try
          {
            label169:
            Object localObject2 = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, localObject1);
            localObject1 = localObject2;
          }
          catch (Exception localException2)
          {
            wrapAndThrow(localException2, localObject1, str, paramDeserializationContext);
          }
          continue;
          label203:
          if ((this._ignorableProps != null) && (this._ignorableProps.contains(str)))
          {
            handleIgnoredProperty(paramJsonParser, paramDeserializationContext, localObject1, str);
          }
          else
          {
            localTokenBuffer.writeFieldName(str);
            localTokenBuffer.copyCurrentStructure(paramJsonParser);
            if (this._anySetter != null) {
              try
              {
                this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject1, str);
              }
              catch (Exception localException1)
              {
                wrapAndThrow(localException1, localObject1, str, paramDeserializationContext);
              }
            }
          }
        }
        label285:
        localTokenBuffer.writeEndObject();
        this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, localObject1, localTokenBuffer);
      }
    }
  }
  
  protected Object deserializeWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException, JsonProcessingException
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
        break label216;
      }
      str = paramJsonParser.getCurrentName();
      localSettableBeanProperty = this._beanProperties.find(str);
      paramJsonParser.nextToken();
      if (localSettableBeanProperty == null) {
        break label150;
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
        Object localObject = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
        paramObject = localObject;
      }
      catch (Exception localException)
      {
        wrapAndThrow(localException, paramObject, str, paramDeserializationContext);
      }
      continue;
      label150:
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
    label216:
    localTokenBuffer.writeEndObject();
    this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, paramObject, localTokenBuffer);
    return paramObject;
  }
  
  protected final Object deserializeWithView(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, Class<?> paramClass)
    throws IOException, JsonProcessingException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == JsonToken.FIELD_NAME)
    {
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty localSettableBeanProperty = this._beanProperties.find(str);
      if (localSettableBeanProperty != null) {
        if (!localSettableBeanProperty.visibleInView(paramClass)) {
          paramJsonParser.skipChildren();
        }
      }
      for (;;)
      {
        localJsonToken = paramJsonParser.nextToken();
        break;
        try
        {
          Object localObject = localSettableBeanProperty.deserializeSetAndReturn(paramJsonParser, paramDeserializationContext, paramObject);
          paramObject = localObject;
        }
        catch (Exception localException)
        {
          wrapAndThrow(localException, paramObject, str, paramDeserializationContext);
        }
        continue;
        handleUnknownVanilla(paramJsonParser, paramDeserializationContext, paramObject, str);
      }
    }
    return paramObject;
  }
  
  protected final Object finishBuild(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (this._buildMethod == null) {}
    for (;;)
    {
      return paramObject;
      try
      {
        Object localObject = this._buildMethod.getMember().invoke(paramObject, new Object[0]);
        paramObject = localObject;
      }
      catch (Exception localException)
      {
        wrapInstantiationProblem(localException, paramDeserializationContext);
        paramObject = null;
      }
    }
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    return new BuilderBasedDeserializer(this, paramNameTransformer);
  }
  
  public BuilderBasedDeserializer withIgnorableProperties(HashSet<String> paramHashSet)
  {
    return new BuilderBasedDeserializer(this, paramHashSet);
  }
  
  public BuilderBasedDeserializer withObjectIdReader(ObjectIdReader paramObjectIdReader)
  {
    return new BuilderBasedDeserializer(this, paramObjectIdReader);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/BuilderBasedDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */