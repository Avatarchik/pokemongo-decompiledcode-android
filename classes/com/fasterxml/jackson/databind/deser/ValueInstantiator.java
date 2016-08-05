package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;

public abstract class ValueInstantiator
{
  protected Object _createFromStringFallbacks(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException, JsonProcessingException
  {
    String str;
    Object localObject;
    if (canCreateFromBoolean())
    {
      str = paramString.trim();
      if ("true".equals(str)) {
        localObject = createFromBoolean(paramDeserializationContext, true);
      }
    }
    for (;;)
    {
      return localObject;
      if ("false".equals(str))
      {
        localObject = createFromBoolean(paramDeserializationContext, false);
      }
      else
      {
        if ((paramString.length() != 0) || (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT))) {
          break;
        }
        localObject = null;
      }
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = paramString;
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from String value ('%s'); no single-String constructor/factory method", arrayOfObject);
  }
  
  public boolean canCreateFromBoolean()
  {
    return false;
  }
  
  public boolean canCreateFromDouble()
  {
    return false;
  }
  
  public boolean canCreateFromInt()
  {
    return false;
  }
  
  public boolean canCreateFromLong()
  {
    return false;
  }
  
  public boolean canCreateFromObjectWith()
  {
    return false;
  }
  
  public boolean canCreateFromString()
  {
    return false;
  }
  
  public boolean canCreateUsingDefault()
  {
    if (getDefaultCreator() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canCreateUsingDelegate()
  {
    return false;
  }
  
  public boolean canInstantiate()
  {
    if ((canCreateUsingDefault()) || (canCreateUsingDelegate()) || (canCreateFromObjectWith()) || (canCreateFromString()) || (canCreateFromInt()) || (canCreateFromLong()) || (canCreateFromDouble()) || (canCreateFromBoolean())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Object createFromBoolean(DeserializationContext paramDeserializationContext, boolean paramBoolean)
    throws IOException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = Boolean.valueOf(paramBoolean);
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Boolean value (%s)", arrayOfObject);
  }
  
  public Object createFromDouble(DeserializationContext paramDeserializationContext, double paramDouble)
    throws IOException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = Double.valueOf(paramDouble);
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Floating-point number (%s, double)", arrayOfObject);
  }
  
  public Object createFromInt(DeserializationContext paramDeserializationContext, int paramInt)
    throws IOException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = Integer.valueOf(paramInt);
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Integer number (%s, int)", arrayOfObject);
  }
  
  public Object createFromLong(DeserializationContext paramDeserializationContext, long paramLong)
    throws IOException
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = Long.valueOf(paramLong);
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Integer number (%s, long)", arrayOfObject);
  }
  
  public Object createFromObjectWith(DeserializationContext paramDeserializationContext, Object[] paramArrayOfObject)
    throws IOException
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = getValueTypeDesc();
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s with arguments", arrayOfObject);
  }
  
  public Object createFromString(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException
  {
    return _createFromStringFallbacks(paramDeserializationContext, paramString);
  }
  
  public Object createUsingDefault(DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = getValueTypeDesc();
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s; no default creator found", arrayOfObject);
  }
  
  public Object createUsingDelegate(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = getValueTypeDesc();
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s using delegate", arrayOfObject);
  }
  
  public AnnotatedWithParams getDefaultCreator()
  {
    return null;
  }
  
  public AnnotatedWithParams getDelegateCreator()
  {
    return null;
  }
  
  public JavaType getDelegateType(DeserializationConfig paramDeserializationConfig)
  {
    return null;
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig paramDeserializationConfig)
  {
    return null;
  }
  
  public AnnotatedParameter getIncompleteParameter()
  {
    return null;
  }
  
  public abstract String getValueTypeDesc();
  
  public AnnotatedWithParams getWithArgsCreator()
  {
    return null;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/ValueInstantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */