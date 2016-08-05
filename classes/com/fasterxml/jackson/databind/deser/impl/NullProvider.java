package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import java.io.Serializable;

@Deprecated
public final class NullProvider
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final boolean _isPrimitive;
  private final Object _nullValue;
  private final Class<?> _rawType;
  
  public NullProvider(JavaType paramJavaType, Object paramObject)
  {
    this._nullValue = paramObject;
    this._isPrimitive = paramJavaType.isPrimitive();
    this._rawType = paramJavaType.getRawClass();
  }
  
  public Object nullValue(DeserializationContext paramDeserializationContext)
    throws JsonProcessingException
  {
    if ((this._isPrimitive) && (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)))
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this._rawType.getName();
      throw paramDeserializationContext.mappingException("Can not map JSON null into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", arrayOfObject);
    }
    return this._nullValue;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/NullProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */