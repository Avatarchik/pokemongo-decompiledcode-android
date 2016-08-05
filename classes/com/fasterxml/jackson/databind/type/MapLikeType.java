package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Map;

public class MapLikeType
  extends TypeBase
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _keyType;
  protected final JavaType _valueType;
  
  protected MapLikeType(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramJavaType1.hashCode() ^ paramJavaType2.hashCode(), paramObject1, paramObject2, paramBoolean);
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
  }
  
  public static MapLikeType construct(Class<?> paramClass, JavaType paramJavaType1, JavaType paramJavaType2)
  {
    return new MapLikeType(paramClass, paramJavaType1, paramJavaType2, null, null, false);
  }
  
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new MapLikeType(paramClass, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    if (this._keyType != null)
    {
      localStringBuilder.append('<');
      localStringBuilder.append(this._keyType.toCanonical());
      localStringBuilder.append(',');
      localStringBuilder.append(this._valueType.toCanonical());
      localStringBuilder.append('>');
    }
    return localStringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt)
  {
    JavaType localJavaType;
    if (paramInt == 0) {
      localJavaType = this._keyType;
    }
    for (;;)
    {
      return localJavaType;
      if (paramInt == 1) {
        localJavaType = this._valueType;
      } else {
        localJavaType = null;
      }
    }
  }
  
  public int containedTypeCount()
  {
    return 2;
  }
  
  public String containedTypeName(int paramInt)
  {
    String str;
    if (paramInt == 0) {
      str = "K";
    }
    for (;;)
    {
      return str;
      if (paramInt == 1) {
        str = "V";
      } else {
        str = null;
      }
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
        MapLikeType localMapLikeType = (MapLikeType)paramObject;
        if ((this._class != localMapLikeType._class) || (!this._keyType.equals(localMapLikeType._keyType)) || (!this._valueType.equals(localMapLikeType._valueType))) {
          bool = false;
        }
      }
    }
  }
  
  public JavaType getContentType()
  {
    return this._valueType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    this._keyType.getGenericSignature(paramStringBuilder);
    this._valueType.getGenericSignature(paramStringBuilder);
    paramStringBuilder.append(">;");
    return paramStringBuilder;
  }
  
  public JavaType getKeyType()
  {
    return this._keyType;
  }
  
  public Class<?> getParameterSource()
  {
    return null;
  }
  
  public boolean isContainerType()
  {
    return true;
  }
  
  public boolean isMapLikeType()
  {
    return true;
  }
  
  public boolean isTrueMapType()
  {
    return Map.class.isAssignableFrom(this._class);
  }
  
  public JavaType narrowContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._valueType.getRawClass()) {}
    for (;;)
    {
      return this;
      this = new MapLikeType(this._class, this._keyType, this._valueType.narrowBy(paramClass), this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public JavaType narrowKey(Class<?> paramClass)
  {
    if (paramClass == this._keyType.getRawClass()) {}
    for (;;)
    {
      return this;
      this = new MapLikeType(this._class, this._keyType.narrowBy(paramClass), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public String toString()
  {
    return "[map-like type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
  }
  
  public JavaType widenContentsBy(Class<?> paramClass)
  {
    if (paramClass == this._valueType.getRawClass()) {}
    for (;;)
    {
      return this;
      this = new MapLikeType(this._class, this._keyType, this._valueType.widenBy(paramClass), this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public JavaType widenKey(Class<?> paramClass)
  {
    if (paramClass == this._keyType.getRawClass()) {}
    for (;;)
    {
      return this;
      this = new MapLikeType(this._class, this._keyType.widenBy(paramClass), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public MapLikeType withContentTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType, this._valueType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withContentValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType, this._valueType.withValueHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withKeyTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType.withTypeHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withKeyValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType.withValueHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  public MapLikeType withStaticTyping()
  {
    if (this._asStatic) {}
    for (;;)
    {
      return this;
      this = new MapLikeType(this._class, this._keyType, this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }
  }
  
  public MapLikeType withTypeHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType, this._valueType, this._valueHandler, paramObject, this._asStatic);
  }
  
  public MapLikeType withValueHandler(Object paramObject)
  {
    return new MapLikeType(this._class, this._keyType, this._valueType, paramObject, this._typeHandler, this._asStatic);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/MapLikeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */