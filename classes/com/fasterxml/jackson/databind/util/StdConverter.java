package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class StdConverter<IN, OUT>
  implements Converter<IN, OUT>
{
  public abstract OUT convert(IN paramIN);
  
  public JavaType getInputType(TypeFactory paramTypeFactory)
  {
    JavaType[] arrayOfJavaType = paramTypeFactory.findTypeParameters(getClass(), Converter.class);
    if ((arrayOfJavaType == null) || (arrayOfJavaType.length < 2)) {
      throw new IllegalStateException("Can not find OUT type parameter for Converter of type " + getClass().getName());
    }
    return arrayOfJavaType[0];
  }
  
  public JavaType getOutputType(TypeFactory paramTypeFactory)
  {
    JavaType[] arrayOfJavaType = paramTypeFactory.findTypeParameters(getClass(), Converter.class);
    if ((arrayOfJavaType == null) || (arrayOfJavaType.length < 2)) {
      throw new IllegalStateException("Can not find OUT type parameter for Converter of type " + getClass().getName());
    }
    return arrayOfJavaType[1];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/StdConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */