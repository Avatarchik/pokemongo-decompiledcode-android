package com.fasterxml.jackson.databind;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class InjectableValues
{
  public abstract Object findInjectableValue(Object paramObject1, DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty, Object paramObject2);
  
  public static class Std
    extends InjectableValues
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected final Map<String, Object> _values;
    
    public Std()
    {
      this(new HashMap());
    }
    
    public Std(Map<String, Object> paramMap)
    {
      this._values = paramMap;
    }
    
    public Std addValue(Class<?> paramClass, Object paramObject)
    {
      this._values.put(paramClass.getName(), paramObject);
      return this;
    }
    
    public Std addValue(String paramString, Object paramObject)
    {
      this._values.put(paramString, paramObject);
      return this;
    }
    
    public Object findInjectableValue(Object paramObject1, DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty, Object paramObject2)
    {
      if (!(paramObject1 instanceof String))
      {
        if (paramObject1 == null) {}
        for (String str2 = "[null]";; str2 = paramObject1.getClass().getName()) {
          throw new IllegalArgumentException("Unrecognized inject value id type (" + str2 + "), expecting String");
        }
      }
      String str1 = (String)paramObject1;
      Object localObject = this._values.get(str1);
      if ((localObject == null) && (!this._values.containsKey(str1))) {
        throw new IllegalArgumentException("No injectable id with value '" + str1 + "' found (for property '" + paramBeanProperty.getName() + "')");
      }
      return localObject;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/InjectableValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */