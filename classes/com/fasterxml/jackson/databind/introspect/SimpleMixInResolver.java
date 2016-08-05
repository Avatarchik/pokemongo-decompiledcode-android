package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SimpleMixInResolver
  implements ClassIntrospector.MixInResolver, Serializable
{
  private static final long serialVersionUID = 1L;
  protected Map<ClassKey, Class<?>> _localMixIns;
  protected final ClassIntrospector.MixInResolver _overrides;
  
  public SimpleMixInResolver(ClassIntrospector.MixInResolver paramMixInResolver)
  {
    this._overrides = paramMixInResolver;
  }
  
  protected SimpleMixInResolver(ClassIntrospector.MixInResolver paramMixInResolver, Map<ClassKey, Class<?>> paramMap)
  {
    this._overrides = paramMixInResolver;
    this._localMixIns = paramMap;
  }
  
  public void addLocalDefinition(Class<?> paramClass1, Class<?> paramClass2)
  {
    if (this._localMixIns == null) {
      this._localMixIns = new HashMap();
    }
    this._localMixIns.put(new ClassKey(paramClass1), paramClass2);
  }
  
  public SimpleMixInResolver copy()
  {
    ClassIntrospector.MixInResolver localMixInResolver;
    if (this._overrides == null)
    {
      localMixInResolver = null;
      if (this._localMixIns != null) {
        break label41;
      }
    }
    label41:
    for (Object localObject = null;; localObject = new HashMap(this._localMixIns))
    {
      return new SimpleMixInResolver(localMixInResolver, (Map)localObject);
      localMixInResolver = this._overrides.copy();
      break;
    }
  }
  
  public Class<?> findMixInClassFor(Class<?> paramClass)
  {
    if (this._overrides == null) {}
    for (Class localClass = null;; localClass = this._overrides.findMixInClassFor(paramClass))
    {
      if ((localClass == null) && (this._localMixIns != null)) {
        localClass = (Class)this._localMixIns.get(new ClassKey(paramClass));
      }
      return localClass;
    }
  }
  
  public int localSize()
  {
    if (this._localMixIns == null) {}
    for (int i = 0;; i = this._localMixIns.size()) {
      return i;
    }
  }
  
  public void setLocalDefinitions(Map<Class<?>, Class<?>> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      this._localMixIns = null;
    }
    HashMap localHashMap = new HashMap(paramMap.size());
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localHashMap.put(new ClassKey((Class)localEntry.getKey()), localEntry.getValue());
    }
    this._localMixIns = localHashMap;
  }
  
  public SimpleMixInResolver withOverrides(ClassIntrospector.MixInResolver paramMixInResolver)
  {
    return new SimpleMixInResolver(paramMixInResolver, this._localMixIns);
  }
  
  public SimpleMixInResolver withoutLocalDefinitions()
  {
    return new SimpleMixInResolver(this._overrides, null);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/SimpleMixInResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */