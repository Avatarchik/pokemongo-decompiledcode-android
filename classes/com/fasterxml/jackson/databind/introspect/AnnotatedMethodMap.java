package com.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public final class AnnotatedMethodMap
  implements Iterable<AnnotatedMethod>
{
  protected LinkedHashMap<MemberKey, AnnotatedMethod> _methods;
  
  public void add(AnnotatedMethod paramAnnotatedMethod)
  {
    if (this._methods == null) {
      this._methods = new LinkedHashMap();
    }
    this._methods.put(new MemberKey(paramAnnotatedMethod.getAnnotated()), paramAnnotatedMethod);
  }
  
  public AnnotatedMethod find(String paramString, Class<?>[] paramArrayOfClass)
  {
    if (this._methods == null) {}
    for (AnnotatedMethod localAnnotatedMethod = null;; localAnnotatedMethod = (AnnotatedMethod)this._methods.get(new MemberKey(paramString, paramArrayOfClass))) {
      return localAnnotatedMethod;
    }
  }
  
  public AnnotatedMethod find(Method paramMethod)
  {
    if (this._methods == null) {}
    for (AnnotatedMethod localAnnotatedMethod = null;; localAnnotatedMethod = (AnnotatedMethod)this._methods.get(new MemberKey(paramMethod))) {
      return localAnnotatedMethod;
    }
  }
  
  public boolean isEmpty()
  {
    if ((this._methods == null) || (this._methods.size() == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Iterator<AnnotatedMethod> iterator()
  {
    if (this._methods != null) {}
    for (Iterator localIterator = this._methods.values().iterator();; localIterator = Collections.emptyList().iterator()) {
      return localIterator;
    }
  }
  
  public AnnotatedMethod remove(AnnotatedMethod paramAnnotatedMethod)
  {
    return remove(paramAnnotatedMethod.getAnnotated());
  }
  
  public AnnotatedMethod remove(Method paramMethod)
  {
    if (this._methods != null) {}
    for (AnnotatedMethod localAnnotatedMethod = (AnnotatedMethod)this._methods.remove(new MemberKey(paramMethod));; localAnnotatedMethod = null) {
      return localAnnotatedMethod;
    }
  }
  
  public int size()
  {
    if (this._methods == null) {}
    for (int i = 0;; i = this._methods.size()) {
      return i;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotatedMethodMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */