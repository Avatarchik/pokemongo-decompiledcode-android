package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;

public final class CollectionType
  extends CollectionLikeType
{
  private static final long serialVersionUID = 1L;
  
  private CollectionType(Class<?> paramClass, JavaType paramJavaType, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramJavaType, paramObject1, paramObject2, paramBoolean);
  }
  
  public static CollectionType construct(Class<?> paramClass, JavaType paramJavaType)
  {
    return new CollectionType(paramClass, paramJavaType, null, null, false);
  }
  
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new CollectionType(paramClass, this._elementType, null, null, this._asStatic);
  }
  
  public Class<?> getParameterSource()
  {
    return Collection.class;
  }
  
  public JavaType narrowContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._elementType.getRawClass()) {}
    for (;;)
    {
      return this;
      this = new CollectionType(this._class, this._elementType.narrowBy(paramClass), this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public String toString()
  {
    return "[collection type; class " + this._class.getName() + ", contains " + this._elementType + "]";
  }
  
  public JavaType widenContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._elementType.getRawClass()) {}
    for (;;)
    {
      return this;
      this = new CollectionType(this._class, this._elementType.widenBy(paramClass), this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public CollectionType withContentTypeHandler(Object paramObject)
  {
    return new CollectionType(this._class, this._elementType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withContentValueHandler(Object paramObject)
  {
    return new CollectionType(this._class, this._elementType.withValueHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionType withStaticTyping()
  {
    if (this._asStatic) {}
    for (;;)
    {
      return this;
      this = new CollectionType(this._class, this._elementType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }
  }
  
  public CollectionType withTypeHandler(Object paramObject)
  {
    return new CollectionType(this._class, this._elementType, this._valueHandler, paramObject, this._asStatic);
  }
  
  public CollectionType withValueHandler(Object paramObject)
  {
    return new CollectionType(this._class, this._elementType, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/CollectionType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */