package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public final class AnnotationMap
  implements Annotations
{
  protected HashMap<Class<? extends Annotation>, Annotation> _annotations;
  
  public AnnotationMap() {}
  
  private AnnotationMap(HashMap<Class<? extends Annotation>, Annotation> paramHashMap)
  {
    this._annotations = paramHashMap;
  }
  
  public static AnnotationMap merge(AnnotationMap paramAnnotationMap1, AnnotationMap paramAnnotationMap2)
  {
    if ((paramAnnotationMap1 == null) || (paramAnnotationMap1._annotations == null) || (paramAnnotationMap1._annotations.isEmpty())) {}
    HashMap localHashMap;
    for (paramAnnotationMap1 = paramAnnotationMap2;; paramAnnotationMap1 = new AnnotationMap(localHashMap))
    {
      do
      {
        return paramAnnotationMap1;
      } while ((paramAnnotationMap2 == null) || (paramAnnotationMap2._annotations == null) || (paramAnnotationMap2._annotations.isEmpty()));
      localHashMap = new HashMap();
      Iterator localIterator1 = paramAnnotationMap2._annotations.values().iterator();
      while (localIterator1.hasNext())
      {
        Annotation localAnnotation2 = (Annotation)localIterator1.next();
        localHashMap.put(localAnnotation2.annotationType(), localAnnotation2);
      }
      Iterator localIterator2 = paramAnnotationMap1._annotations.values().iterator();
      while (localIterator2.hasNext())
      {
        Annotation localAnnotation1 = (Annotation)localIterator2.next();
        localHashMap.put(localAnnotation1.annotationType(), localAnnotation1);
      }
    }
  }
  
  protected final boolean _add(Annotation paramAnnotation)
  {
    if (this._annotations == null) {
      this._annotations = new HashMap();
    }
    Annotation localAnnotation = (Annotation)this._annotations.put(paramAnnotation.annotationType(), paramAnnotation);
    if ((localAnnotation == null) || (!localAnnotation.equals(paramAnnotation))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean add(Annotation paramAnnotation)
  {
    return _add(paramAnnotation);
  }
  
  public boolean addIfNotPresent(Annotation paramAnnotation)
  {
    if ((this._annotations == null) || (!this._annotations.containsKey(paramAnnotation.annotationType()))) {
      _add(paramAnnotation);
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Iterable<Annotation> annotations()
  {
    if ((this._annotations == null) || (this._annotations.size() == 0)) {}
    for (Object localObject = Collections.emptyList();; localObject = this._annotations.values()) {
      return (Iterable<Annotation>)localObject;
    }
  }
  
  public <A extends Annotation> A get(Class<A> paramClass)
  {
    if (this._annotations == null) {}
    for (Object localObject = null;; localObject = (Annotation)this._annotations.get(paramClass)) {
      return (A)localObject;
    }
  }
  
  public int size()
  {
    if (this._annotations == null) {}
    for (int i = 0;; i = this._annotations.size()) {
      return i;
    }
  }
  
  public String toString()
  {
    if (this._annotations == null) {}
    for (String str = "[null]";; str = this._annotations.toString()) {
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotationMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */