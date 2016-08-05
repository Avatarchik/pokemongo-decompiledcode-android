package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public abstract class AnnotatedWithParams
  extends AnnotatedMember
{
  private static final long serialVersionUID = 1L;
  protected final AnnotationMap[] _paramAnnotations;
  
  protected AnnotatedWithParams(AnnotatedClass paramAnnotatedClass, AnnotationMap paramAnnotationMap, AnnotationMap[] paramArrayOfAnnotationMap)
  {
    super(paramAnnotatedClass, paramAnnotationMap);
    this._paramAnnotations = paramArrayOfAnnotationMap;
  }
  
  public final void addOrOverrideParam(int paramInt, Annotation paramAnnotation)
  {
    AnnotationMap localAnnotationMap = this._paramAnnotations[paramInt];
    if (localAnnotationMap == null)
    {
      localAnnotationMap = new AnnotationMap();
      this._paramAnnotations[paramInt] = localAnnotationMap;
    }
    localAnnotationMap.add(paramAnnotation);
  }
  
  public abstract Object call()
    throws Exception;
  
  public abstract Object call(Object[] paramArrayOfObject)
    throws Exception;
  
  public abstract Object call1(Object paramObject)
    throws Exception;
  
  public final <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    return this._annotations.get(paramClass);
  }
  
  public final int getAnnotationCount()
  {
    return this._annotations.size();
  }
  
  public abstract Type getGenericParameterType(int paramInt);
  
  public final AnnotatedParameter getParameter(int paramInt)
  {
    return new AnnotatedParameter(this, getGenericParameterType(paramInt), getParameterAnnotations(paramInt), paramInt);
  }
  
  public final AnnotationMap getParameterAnnotations(int paramInt)
  {
    if ((this._paramAnnotations != null) && (paramInt >= 0) && (paramInt < this._paramAnnotations.length)) {}
    for (AnnotationMap localAnnotationMap = this._paramAnnotations[paramInt];; localAnnotationMap = null) {
      return localAnnotationMap;
    }
  }
  
  public abstract int getParameterCount();
  
  public abstract Class<?> getRawParameterType(int paramInt);
  
  protected JavaType getType(TypeBindings paramTypeBindings, TypeVariable<?>[] paramArrayOfTypeVariable)
  {
    if ((paramArrayOfTypeVariable != null) && (paramArrayOfTypeVariable.length > 0))
    {
      paramTypeBindings = paramTypeBindings.childInstance();
      int i = paramArrayOfTypeVariable.length;
      int j = 0;
      if (j < i)
      {
        TypeVariable<?> localTypeVariable = paramArrayOfTypeVariable[j];
        paramTypeBindings._addPlaceholder(localTypeVariable.getName());
        Type localType = localTypeVariable.getBounds()[0];
        if (localType == null) {}
        for (JavaType localJavaType = TypeFactory.unknownType();; localJavaType = paramTypeBindings.resolveType(localType))
        {
          paramTypeBindings.addBinding(localTypeVariable.getName(), localJavaType);
          j++;
          break;
        }
      }
    }
    return paramTypeBindings.resolveType(getGenericType());
  }
  
  protected AnnotatedParameter replaceParameterAnnotations(int paramInt, AnnotationMap paramAnnotationMap)
  {
    this._paramAnnotations[paramInt] = paramAnnotationMap;
    return getParameter(paramInt);
  }
  
  public final JavaType resolveParameterType(int paramInt, TypeBindings paramTypeBindings)
  {
    return paramTypeBindings.resolveType(getGenericParameterType(paramInt));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/AnnotatedWithParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */