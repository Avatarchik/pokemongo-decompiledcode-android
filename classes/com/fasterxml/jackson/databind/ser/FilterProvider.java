package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public abstract class FilterProvider
{
  @Deprecated
  public abstract BeanPropertyFilter findFilter(Object paramObject);
  
  public PropertyFilter findPropertyFilter(Object paramObject1, Object paramObject2)
  {
    BeanPropertyFilter localBeanPropertyFilter = findFilter(paramObject1);
    if (localBeanPropertyFilter == null) {}
    for (PropertyFilter localPropertyFilter = null;; localPropertyFilter = SimpleBeanPropertyFilter.from(localBeanPropertyFilter)) {
      return localPropertyFilter;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/FilterProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */