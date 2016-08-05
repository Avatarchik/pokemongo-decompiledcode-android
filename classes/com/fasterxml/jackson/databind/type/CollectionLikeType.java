package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;

public class CollectionLikeType
  extends TypeBase
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _elementType;
  
  protected CollectionLikeType(Class<?> paramClass, JavaType paramJavaType, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramJavaType.hashCode(), paramObject1, paramObject2, paramBoolean);
    this._elementType = paramJavaType;
  }
  
  public static CollectionLikeType construct(Class<?> paramClass, JavaType paramJavaType)
  {
    return new CollectionLikeType(paramClass, paramJavaType, null, null, false);
  }
  
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new CollectionLikeType(paramClass, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    if (this._elementType != null)
    {
      localStringBuilder.append('<');
      localStringBuilder.append(this._elementType.toCanonical());
      localStringBuilder.append('>');
    }
    return localStringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt)
  {
    if (paramInt == 0) {}
    for (JavaType localJavaType = this._elementType;; localJavaType = null) {
      return localJavaType;
    }
  }
  
  public int containedTypeCount()
  {
    return 1;
  }
  
  public String containedTypeName(int paramInt)
  {
    if (paramInt == 0) {}
    for (String str = "E";; str = null) {
      return str;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (paramObject == null)
      {
        bool = false;
      }
      else if (paramObject.getClass() != getClass())
      {
        bool = false;
      }
      else
      {
        CollectionLikeType localCollectionLikeType = (CollectionLikeType)paramObject;
        if ((this._class != localCollectionLikeType._class) || (!this._elementType.equals(localCollectionLikeType._elementType))) {
          bool = false;
        }
      }
    }
  }
  
  public JavaType getContentType()
  {
    return this._elementType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    this._elementType.getGenericSignature(paramStringBuilder);
    paramStringBuilder.append(">;");
    return paramStringBuilder;
  }
  
  public Class<?> getParameterSource()
  {
    return null;
  }
  
  public boolean isCollectionLikeType()
  {
    return true;
  }
  
  public boolean isContainerType()
  {
    return true;
  }
  
  public boolean isTrueCollectionType()
  {
    return Collection.class.isAssignableFrom(this._class);
  }
  
  public JavaType narrowContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._elementType.getRawClass()) {}
    for (;;)
    {
      return this;
      this = new CollectionLikeType(this._class, this._elementType.narrowBy(paramClass), this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public String toString()
  {
    return "[collection-like type; class " + this._class.getName() + ", contains " + this._elementType + "]";
  }
  
  public JavaType widenContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._elementType.getRawClass()) {}
    for (;;)
    {
      return this;
      this = new CollectionLikeType(this._class, this._elementType.widenBy(paramClass), this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public CollectionLikeType withContentTypeHandler(Object paramObject)
  {
    return new CollectionLikeType(this._class, this._elementType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionLikeType withContentValueHandler(Object paramObject)
  {
    return new CollectionLikeType(this._class, this._elementType.withValueHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public CollectionLikeType withStaticTyping()
  {
    if (this._asStatic) {}
    for (;;)
    {
      return this;
      this = new CollectionLikeType(this._class, this._elementType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }
  }
  
  public CollectionLikeType withTypeHandler(Object paramObject)
  {
    return new CollectionLikeType(this._class, this._elementType, this._valueHandler, paramObject, this._asStatic);
  }
  
  public CollectionLikeType withValueHandler(Object paramObject)
  {
    return new CollectionLikeType(this._class, this._elementType, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/CollectionLikeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */