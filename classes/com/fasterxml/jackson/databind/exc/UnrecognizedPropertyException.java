package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import java.util.Collection;

public class UnrecognizedPropertyException
  extends PropertyBindingException
{
  private static final long serialVersionUID = 1L;
  
  public UnrecognizedPropertyException(String paramString1, JsonLocation paramJsonLocation, Class<?> paramClass, String paramString2, Collection<Object> paramCollection)
  {
    super(paramString1, paramJsonLocation, paramClass, paramString2, paramCollection);
  }
  
  public static UnrecognizedPropertyException from(JsonParser paramJsonParser, Object paramObject, String paramString, Collection<Object> paramCollection)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException();
    }
    if ((paramObject instanceof Class)) {}
    for (Class localClass = (Class)paramObject;; localClass = paramObject.getClass())
    {
      UnrecognizedPropertyException localUnrecognizedPropertyException = new UnrecognizedPropertyException("Unrecognized field \"" + paramString + "\" (class " + localClass.getName() + "), not marked as ignorable", paramJsonParser.getCurrentLocation(), localClass, paramString, paramCollection);
      localUnrecognizedPropertyException.prependPath(paramObject, paramString);
      return localUnrecognizedPropertyException;
    }
  }
  
  @Deprecated
  public String getUnrecognizedPropertyName()
  {
    return getPropertyName();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/exc/UnrecognizedPropertyException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */