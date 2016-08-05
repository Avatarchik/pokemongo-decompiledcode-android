package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;
import java.io.Serializable;

@JacksonStdImpl
public class StdValueInstantiator
  extends ValueInstantiator
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected SettableBeanProperty[] _constructorArguments;
  protected AnnotatedWithParams _defaultCreator;
  protected SettableBeanProperty[] _delegateArguments;
  protected AnnotatedWithParams _delegateCreator;
  protected JavaType _delegateType;
  protected AnnotatedWithParams _fromBooleanCreator;
  protected AnnotatedWithParams _fromDoubleCreator;
  protected AnnotatedWithParams _fromIntCreator;
  protected AnnotatedWithParams _fromLongCreator;
  protected AnnotatedWithParams _fromStringCreator;
  protected AnnotatedParameter _incompleteParameter;
  protected final String _valueTypeDesc;
  protected AnnotatedWithParams _withArgsCreator;
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType)
  {
    if (paramJavaType == null) {}
    for (String str = "UNKNOWN TYPE";; str = paramJavaType.toString())
    {
      this._valueTypeDesc = str;
      return;
    }
  }
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, Class<?> paramClass)
  {
    if (paramClass == null) {}
    for (String str = "UNKNOWN TYPE";; str = paramClass.getName())
    {
      this._valueTypeDesc = str;
      return;
    }
  }
  
  protected StdValueInstantiator(StdValueInstantiator paramStdValueInstantiator)
  {
    this._valueTypeDesc = paramStdValueInstantiator._valueTypeDesc;
    this._defaultCreator = paramStdValueInstantiator._defaultCreator;
    this._constructorArguments = paramStdValueInstantiator._constructorArguments;
    this._withArgsCreator = paramStdValueInstantiator._withArgsCreator;
    this._delegateType = paramStdValueInstantiator._delegateType;
    this._delegateCreator = paramStdValueInstantiator._delegateCreator;
    this._delegateArguments = paramStdValueInstantiator._delegateArguments;
    this._fromStringCreator = paramStdValueInstantiator._fromStringCreator;
    this._fromIntCreator = paramStdValueInstantiator._fromIntCreator;
    this._fromLongCreator = paramStdValueInstantiator._fromLongCreator;
    this._fromDoubleCreator = paramStdValueInstantiator._fromDoubleCreator;
    this._fromBooleanCreator = paramStdValueInstantiator._fromBooleanCreator;
  }
  
  public boolean canCreateFromBoolean()
  {
    if (this._fromBooleanCreator != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canCreateFromDouble()
  {
    if (this._fromDoubleCreator != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canCreateFromInt()
  {
    if (this._fromIntCreator != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canCreateFromLong()
  {
    if (this._fromLongCreator != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canCreateFromObjectWith()
  {
    if (this._withArgsCreator != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canCreateFromString()
  {
    if (this._fromStringCreator != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canCreateUsingDefault()
  {
    if (this._defaultCreator != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canCreateUsingDelegate()
  {
    if (this._delegateType != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void configureFromBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromBooleanCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromDoubleCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromIntCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromIntCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromLongCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromLongCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromObjectSettings(AnnotatedWithParams paramAnnotatedWithParams1, AnnotatedWithParams paramAnnotatedWithParams2, JavaType paramJavaType, SettableBeanProperty[] paramArrayOfSettableBeanProperty1, AnnotatedWithParams paramAnnotatedWithParams3, SettableBeanProperty[] paramArrayOfSettableBeanProperty2)
  {
    this._defaultCreator = paramAnnotatedWithParams1;
    this._delegateCreator = paramAnnotatedWithParams2;
    this._delegateType = paramJavaType;
    this._delegateArguments = paramArrayOfSettableBeanProperty1;
    this._withArgsCreator = paramAnnotatedWithParams3;
    this._constructorArguments = paramArrayOfSettableBeanProperty2;
  }
  
  public void configureFromStringCreator(AnnotatedWithParams paramAnnotatedWithParams)
  {
    this._fromStringCreator = paramAnnotatedWithParams;
  }
  
  public void configureIncompleteParameter(AnnotatedParameter paramAnnotatedParameter)
  {
    this._incompleteParameter = paramAnnotatedParameter;
  }
  
  public Object createFromBoolean(DeserializationContext paramDeserializationContext, boolean paramBoolean)
    throws IOException
  {
    try
    {
      if (this._fromBooleanCreator != null)
      {
        Object localObject = this._fromBooleanCreator.call1(Boolean.valueOf(paramBoolean));
        return localObject;
      }
    }
    catch (Exception localException)
    {
      throw wrapException(localException);
    }
    catch (ExceptionInInitializerError localExceptionInInitializerError)
    {
      throw wrapException(localExceptionInInitializerError);
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = Boolean.valueOf(paramBoolean);
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Boolean value (%s); no single-boolean/Boolean-arg constructor/factory method", arrayOfObject);
  }
  
  public Object createFromDouble(DeserializationContext paramDeserializationContext, double paramDouble)
    throws IOException
  {
    try
    {
      if (this._fromDoubleCreator != null)
      {
        Object localObject = this._fromDoubleCreator.call1(Double.valueOf(paramDouble));
        return localObject;
      }
    }
    catch (Exception localException)
    {
      throw wrapException(localException);
    }
    catch (ExceptionInInitializerError localExceptionInInitializerError)
    {
      throw wrapException(localExceptionInInitializerError);
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = Double.valueOf(paramDouble);
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Floating-point number (%s); no one-double/Double-arg constructor/factory method", arrayOfObject);
  }
  
  public Object createFromInt(DeserializationContext paramDeserializationContext, int paramInt)
    throws IOException
  {
    Object localObject2;
    try
    {
      if (this._fromIntCreator != null)
      {
        localObject2 = this._fromIntCreator.call1(Integer.valueOf(paramInt));
      }
      else if (this._fromLongCreator != null)
      {
        Object localObject1 = this._fromLongCreator.call1(Long.valueOf(paramInt));
        localObject2 = localObject1;
      }
    }
    catch (Exception localException)
    {
      throw wrapException(localException);
    }
    catch (ExceptionInInitializerError localExceptionInInitializerError)
    {
      throw wrapException(localExceptionInInitializerError);
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = Integer.valueOf(paramInt);
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Integral number (%s); no single-int-arg constructor/factory method", arrayOfObject);
    return localObject2;
  }
  
  public Object createFromLong(DeserializationContext paramDeserializationContext, long paramLong)
    throws IOException
  {
    try
    {
      if (this._fromLongCreator != null)
      {
        Object localObject = this._fromLongCreator.call1(Long.valueOf(paramLong));
        return localObject;
      }
    }
    catch (Exception localException)
    {
      throw wrapException(localException);
    }
    catch (ExceptionInInitializerError localExceptionInInitializerError)
    {
      throw wrapException(localExceptionInInitializerError);
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getValueTypeDesc();
    arrayOfObject[1] = Long.valueOf(paramLong);
    throw paramDeserializationContext.mappingException("Can not instantiate value of type %s from Long integral number (%s); no single-long-arg constructor/factory method", arrayOfObject);
  }
  
  public Object createFromObjectWith(DeserializationContext paramDeserializationContext, Object[] paramArrayOfObject)
    throws IOException
  {
    if (this._withArgsCreator == null) {
      throw new IllegalStateException("No with-args constructor for " + getValueTypeDesc());
    }
    try
    {
      Object localObject = this._withArgsCreator.call(paramArrayOfObject);
      return localObject;
    }
    catch (ExceptionInInitializerError localExceptionInInitializerError)
    {
      throw wrapException(localExceptionInInitializerError);
    }
    catch (Exception localException)
    {
      throw wrapException(localException);
    }
  }
  
  public Object createFromString(DeserializationContext paramDeserializationContext, String paramString)
    throws IOException
  {
    if (this._fromStringCreator != null) {}
    for (;;)
    {
      try
      {
        Object localObject2 = this._fromStringCreator.call1(paramString);
        localObject1 = localObject2;
        return localObject1;
      }
      catch (Exception localException)
      {
        throw wrapException(localException);
      }
      catch (ExceptionInInitializerError localExceptionInInitializerError)
      {
        throw wrapException(localExceptionInInitializerError);
      }
      Object localObject1 = _createFromStringFallbacks(paramDeserializationContext, paramString);
    }
  }
  
  public Object createUsingDefault(DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (this._defaultCreator == null) {
      throw new IllegalStateException("No default constructor for " + getValueTypeDesc());
    }
    try
    {
      Object localObject = this._defaultCreator.call();
      return localObject;
    }
    catch (ExceptionInInitializerError localExceptionInInitializerError)
    {
      throw wrapException(localExceptionInInitializerError);
    }
    catch (Exception localException)
    {
      throw wrapException(localException);
    }
  }
  
  public Object createUsingDelegate(DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    if (this._delegateCreator == null) {
      throw new IllegalStateException("No delegate constructor for " + getValueTypeDesc());
    }
    for (;;)
    {
      Object localObject2;
      int j;
      try
      {
        if (this._delegateArguments == null)
        {
          localObject2 = this._delegateCreator.call1(paramObject);
        }
        else
        {
          int i = this._delegateArguments.length;
          arrayOfObject = new Object[i];
          j = 0;
          if (j < i)
          {
            SettableBeanProperty localSettableBeanProperty = this._delegateArguments[j];
            if (localSettableBeanProperty == null) {
              arrayOfObject[j] = paramObject;
            } else {
              arrayOfObject[j] = paramDeserializationContext.findInjectableValue(localSettableBeanProperty.getInjectableValueId(), localSettableBeanProperty, null);
            }
          }
        }
      }
      catch (ExceptionInInitializerError localExceptionInInitializerError)
      {
        Object[] arrayOfObject;
        throw wrapException(localExceptionInInitializerError);
        Object localObject1 = this._delegateCreator.call(arrayOfObject);
        localObject2 = localObject1;
      }
      catch (Exception localException)
      {
        throw wrapException(localException);
      }
      return localObject2;
      j++;
    }
  }
  
  public AnnotatedWithParams getDefaultCreator()
  {
    return this._defaultCreator;
  }
  
  public AnnotatedWithParams getDelegateCreator()
  {
    return this._delegateCreator;
  }
  
  public JavaType getDelegateType(DeserializationConfig paramDeserializationConfig)
  {
    return this._delegateType;
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig paramDeserializationConfig)
  {
    return this._constructorArguments;
  }
  
  public AnnotatedParameter getIncompleteParameter()
  {
    return this._incompleteParameter;
  }
  
  public String getValueTypeDesc()
  {
    return this._valueTypeDesc;
  }
  
  public AnnotatedWithParams getWithArgsCreator()
  {
    return this._withArgsCreator;
  }
  
  protected JsonMappingException wrapException(Throwable paramThrowable)
  {
    while (paramThrowable.getCause() != null) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof JsonMappingException)) {}
    for (JsonMappingException localJsonMappingException = (JsonMappingException)paramThrowable;; localJsonMappingException = new JsonMappingException("Instantiation of " + getValueTypeDesc() + " value failed: " + paramThrowable.getMessage(), paramThrowable)) {
      return localJsonMappingException;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StdValueInstantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */