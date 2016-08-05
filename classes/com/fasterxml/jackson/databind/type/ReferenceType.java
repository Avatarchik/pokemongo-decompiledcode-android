package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ReferenceType
  extends SimpleType
{
  private static final long serialVersionUID = 1L;
  protected final JavaType _referencedType;
  
  protected ReferenceType(Class<?> paramClass, JavaType paramJavaType, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramJavaType.hashCode(), paramObject1, paramObject2, paramBoolean);
    this._referencedType = paramJavaType;
  }
  
  public static ReferenceType construct(Class<?> paramClass, JavaType paramJavaType, Object paramObject1, Object paramObject2)
  {
    return new ReferenceType(paramClass, paramJavaType, null, null, false);
  }
  
  protected JavaType _narrow(Class<?> paramClass)
  {
    return new ReferenceType(paramClass, this._referencedType, this._valueHandler, this._typeHandler, this._asStatic);
  }
  
  protected String buildCanonicalName()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._class.getName());
    localStringBuilder.append('<');
    localStringBuilder.append(this._referencedType.toCanonical());
    return localStringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt)
  {
    if (paramInt == 0) {}
    for (JavaType localJavaType = this._referencedType;; localJavaType = null) {
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
    for (String str = "T";; str = null) {
      return str;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == this) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      if ((paramObject != null) && (paramObject.getClass() == getClass()))
      {
        ReferenceType localReferenceType = (ReferenceType)paramObject;
        if (localReferenceType._class == this._class) {
          bool = this._referencedType.equals(localReferenceType._referencedType);
        }
      }
    }
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder)
  {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder)
  {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    StringBuilder localStringBuilder = this._referencedType.getGenericSignature(paramStringBuilder);
    localStringBuilder.append(';');
    return localStringBuilder;
  }
  
  public Class<?> getParameterSource()
  {
    return this._class;
  }
  
  public JavaType getReferencedType()
  {
    return this._referencedType;
  }
  
  public boolean isReferenceType()
  {
    return true;
  }
  
  public String toString()
  {
    return 40 + "[reference type, class " + buildCanonicalName() + '<' + this._referencedType + '>' + ']';
  }
  
  public ReferenceType withContentTypeHandler(Object paramObject)
  {
    if (paramObject == this._referencedType.getTypeHandler()) {}
    for (;;)
    {
      return this;
      this = new ReferenceType(this._class, this._referencedType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public ReferenceType withContentValueHandler(Object paramObject)
  {
    if (paramObject == this._referencedType.getValueHandler()) {}
    for (;;)
    {
      return this;
      this = new ReferenceType(this._class, this._referencedType.withValueHandler(paramObject), this._valueHandler, this._typeHandler, this._asStatic);
    }
  }
  
  public ReferenceType withStaticTyping()
  {
    if (this._asStatic) {}
    for (;;)
    {
      return this;
      this = new ReferenceType(this._class, this._referencedType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }
  }
  
  public ReferenceType withTypeHandler(Object paramObject)
  {
    if (paramObject == this._typeHandler) {}
    for (;;)
    {
      return this;
      this = new ReferenceType(this._class, this._referencedType, this._valueHandler, paramObject, this._asStatic);
    }
  }
  
  public ReferenceType withValueHandler(Object paramObject)
  {
    if (paramObject == this._valueHandler) {}
    for (;;)
    {
      return this;
      this = new ReferenceType(this._class, this._referencedType, paramObject, this._typeHandler, this._asStatic);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/ReferenceType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */