package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SimpleFilterProvider
  extends FilterProvider
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected boolean _cfgFailOnUnknownId = true;
  protected PropertyFilter _defaultFilter;
  protected final Map<String, PropertyFilter> _filtersById;
  
  public SimpleFilterProvider()
  {
    this(new HashMap());
  }
  
  public SimpleFilterProvider(Map<String, ?> paramMap)
  {
    Iterator localIterator = paramMap.values().iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
    } while ((localIterator.next() instanceof PropertyFilter));
    for (this._filtersById = _convert(paramMap);; this._filtersById = paramMap) {
      return;
    }
  }
  
  private static final PropertyFilter _convert(BeanPropertyFilter paramBeanPropertyFilter)
  {
    return SimpleBeanPropertyFilter.from(paramBeanPropertyFilter);
  }
  
  private static final Map<String, PropertyFilter> _convert(Map<String, ?> paramMap)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = localEntry.getValue();
      if ((localObject instanceof PropertyFilter)) {
        localHashMap.put(localEntry.getKey(), (PropertyFilter)localObject);
      } else if ((localObject instanceof BeanPropertyFilter)) {
        localHashMap.put(localEntry.getKey(), _convert((BeanPropertyFilter)localObject));
      } else {
        throw new IllegalArgumentException("Unrecognized filter type (" + localObject.getClass().getName() + ")");
      }
    }
    return localHashMap;
  }
  
  @Deprecated
  public SimpleFilterProvider addFilter(String paramString, BeanPropertyFilter paramBeanPropertyFilter)
  {
    this._filtersById.put(paramString, _convert(paramBeanPropertyFilter));
    return this;
  }
  
  public SimpleFilterProvider addFilter(String paramString, PropertyFilter paramPropertyFilter)
  {
    this._filtersById.put(paramString, paramPropertyFilter);
    return this;
  }
  
  public SimpleFilterProvider addFilter(String paramString, SimpleBeanPropertyFilter paramSimpleBeanPropertyFilter)
  {
    this._filtersById.put(paramString, paramSimpleBeanPropertyFilter);
    return this;
  }
  
  @Deprecated
  public BeanPropertyFilter findFilter(Object paramObject)
  {
    throw new UnsupportedOperationException("Access to deprecated filters not supported");
  }
  
  public PropertyFilter findPropertyFilter(Object paramObject1, Object paramObject2)
  {
    PropertyFilter localPropertyFilter = (PropertyFilter)this._filtersById.get(paramObject1);
    if (localPropertyFilter == null)
    {
      localPropertyFilter = this._defaultFilter;
      if ((localPropertyFilter == null) && (this._cfgFailOnUnknownId)) {
        throw new IllegalArgumentException("No filter configured with id '" + paramObject1 + "' (type " + paramObject1.getClass().getName() + ")");
      }
    }
    return localPropertyFilter;
  }
  
  public PropertyFilter getDefaultFilter()
  {
    return this._defaultFilter;
  }
  
  public PropertyFilter removeFilter(String paramString)
  {
    return (PropertyFilter)this._filtersById.remove(paramString);
  }
  
  @Deprecated
  public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter paramBeanPropertyFilter)
  {
    this._defaultFilter = SimpleBeanPropertyFilter.from(paramBeanPropertyFilter);
    return this;
  }
  
  public SimpleFilterProvider setDefaultFilter(PropertyFilter paramPropertyFilter)
  {
    this._defaultFilter = paramPropertyFilter;
    return this;
  }
  
  public SimpleFilterProvider setDefaultFilter(SimpleBeanPropertyFilter paramSimpleBeanPropertyFilter)
  {
    this._defaultFilter = paramSimpleBeanPropertyFilter;
    return this;
  }
  
  public SimpleFilterProvider setFailOnUnknownId(boolean paramBoolean)
  {
    this._cfgFailOnUnknownId = paramBoolean;
    return this;
  }
  
  public boolean willFailOnUnknownId()
  {
    return this._cfgFailOnUnknownId;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/SimpleFilterProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */