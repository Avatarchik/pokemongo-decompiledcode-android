package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.HashSet;

public class ThrowableDeserializer
  extends BeanDeserializer
{
  protected static final String PROP_NAME_MESSAGE = "message";
  private static final long serialVersionUID = 1L;
  
  public ThrowableDeserializer(BeanDeserializer paramBeanDeserializer)
  {
    super(paramBeanDeserializer);
    this._vanillaProcessing = false;
  }
  
  protected ThrowableDeserializer(BeanDeserializer paramBeanDeserializer, NameTransformer paramNameTransformer)
  {
    super(paramBeanDeserializer, paramNameTransformer);
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    if (this._propertyBasedCreator != null) {
      localObject = _deserializeUsingPropertyBased(paramJsonParser, paramDeserializationContext);
    }
    label494:
    for (;;)
    {
      return localObject;
      if (this._delegateDeserializer != null)
      {
        localObject = this._valueInstantiator.createUsingDelegate(paramDeserializationContext, this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      }
      else
      {
        if (this._beanType.isAbstract()) {
          throw JsonMappingException.from(paramJsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
        }
        boolean bool1 = this._valueInstantiator.canCreateFromString();
        boolean bool2 = this._valueInstantiator.canCreateUsingDefault();
        if ((!bool1) && (!bool2)) {
          throw new JsonMappingException("Can not deserialize Throwable of type " + this._beanType + " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
        }
        localObject = null;
        Object[] arrayOfObject = null;
        int i = 0;
        if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT)
        {
          String str = paramJsonParser.getCurrentName();
          SettableBeanProperty localSettableBeanProperty = this._beanProperties.find(str);
          paramJsonParser.nextToken();
          if (localSettableBeanProperty != null) {
            if (localObject != null) {
              localSettableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject);
            }
          }
          for (;;)
          {
            paramJsonParser.nextToken();
            break;
            if (arrayOfObject == null)
            {
              int i2 = this._beanProperties.size();
              arrayOfObject = new Object[i2 + i2];
            }
            int i1 = i + 1;
            arrayOfObject[i] = localSettableBeanProperty;
            i = i1 + 1;
            arrayOfObject[i1] = localSettableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
            continue;
            if (("message".equals(str)) && (bool1))
            {
              localObject = this._valueInstantiator.createFromString(paramDeserializationContext, paramJsonParser.getText());
              if (arrayOfObject != null)
              {
                int m = 0;
                int n = i;
                while (m < n)
                {
                  ((SettableBeanProperty)arrayOfObject[m]).set(localObject, arrayOfObject[(m + 1)]);
                  m += 2;
                }
                arrayOfObject = null;
              }
            }
            else if ((this._ignorableProps != null) && (this._ignorableProps.contains(str)))
            {
              paramJsonParser.skipChildren();
            }
            else if (this._anySetter != null)
            {
              this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, localObject, str);
            }
            else
            {
              handleUnknownProperty(paramJsonParser, paramDeserializationContext, localObject, str);
            }
          }
        }
        if (localObject == null)
        {
          if (bool1) {}
          for (localObject = this._valueInstantiator.createFromString(paramDeserializationContext, null);; localObject = this._valueInstantiator.createUsingDefault(paramDeserializationContext))
          {
            if (arrayOfObject == null) {
              break label494;
            }
            int j = 0;
            int k = i;
            while (j < k)
            {
              ((SettableBeanProperty)arrayOfObject[j]).set(localObject, arrayOfObject[(j + 1)]);
              j += 2;
            }
            break;
          }
        }
      }
    }
  }
  
  public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer paramNameTransformer)
  {
    if (getClass() != ThrowableDeserializer.class) {}
    for (;;)
    {
      return this;
      this = new ThrowableDeserializer(this, paramNameTransformer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/ThrowableDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */