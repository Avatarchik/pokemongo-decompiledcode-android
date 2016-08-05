package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators.Base;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleValueInstantiators
  extends ValueInstantiators.Base
  implements Serializable
{
  private static final long serialVersionUID = -8929386427526115130L;
  protected HashMap<ClassKey, ValueInstantiator> _classMappings = new HashMap();
  
  public SimpleValueInstantiators addValueInstantiator(Class<?> paramClass, ValueInstantiator paramValueInstantiator)
  {
    this._classMappings.put(new ClassKey(paramClass), paramValueInstantiator);
    return this;
  }
  
  public ValueInstantiator findValueInstantiator(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, ValueInstantiator paramValueInstantiator)
  {
    ValueInstantiator localValueInstantiator = (ValueInstantiator)this._classMappings.get(new ClassKey(paramBeanDescription.getBeanClass()));
    if (localValueInstantiator == null) {}
    for (;;)
    {
      return paramValueInstantiator;
      paramValueInstantiator = localValueInstantiator;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/module/SimpleValueInstantiators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */