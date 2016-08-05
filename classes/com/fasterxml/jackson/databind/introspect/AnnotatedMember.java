package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.util.Collections;

public abstract class AnnotatedMember
  extends Annotated
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final transient AnnotationMap _annotations;
  protected final transient AnnotatedClass _context;
  
  protected AnnotatedMember(AnnotatedClass paramAnnotatedClass, AnnotationMap paramAnnotationMap)
  {
    this._context = paramAnnotatedClass;
    this._annotations = paramAnnotationMap;
  }
  
  protected AnnotatedMember(AnnotatedMember paramAnnotatedMember)
  {
    this._context = paramAnnotatedMember._context;
    this._annotations = paramAnnotatedMember._annotations;
  }
  
  public final boolean addIfNotPresent(Annotation paramAnnotation)
  {
    return this._annotations.addIfNotPresent(paramAnnotation);
  }
  
  public final boolean addOrOverride(Annotation paramAnnotation)
  {
    return this._annotations.add(paramAnnotation);
  }
  
  public Iterable<Annotation> annotations()
  {
    if (this._annotations == null) {}
    for (Object localObject = Collections.emptyList();; localObject = this._annotations.annotations()) {
      return (Iterable<Annotation>)localObject;
    }
  }
  
  public final void fixAccess()
  {
    ClassUtil.checkAndFixAccess(getMember());
  }
  
  protected AnnotationMap getAllAnnotations()
  {
    return this._annotations;
  }
  
  public AnnotatedClass getContextClass()
  {
    return this._context;
  }
  
  public abstract Class<?> getDeclaringClass();
  
  public abstract Member getMember();
  
  public abstract Object getValue(Object paramObject)
    throws UnsupportedOperationException, IllegalArgumentException;
  
  public abstract void setValue(Object paramObject1, Object paramObject2)
    throws UnsupportedOperationException, IllegalArgumentException;
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotatedMember.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */