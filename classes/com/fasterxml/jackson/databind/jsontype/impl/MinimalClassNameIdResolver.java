package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class MinimalClassNameIdResolver
  extends ClassNameIdResolver
{
  protected final String _basePackageName;
  protected final String _basePackagePrefix;
  
  protected MinimalClassNameIdResolver(JavaType paramJavaType, TypeFactory paramTypeFactory)
  {
    super(paramJavaType, paramTypeFactory);
    String str = paramJavaType.getRawClass().getName();
    int i = str.lastIndexOf('.');
    if (i < 0)
    {
      this._basePackageName = "";
      this._basePackagePrefix = ".";
    }
    for (;;)
    {
      return;
      this._basePackagePrefix = str.substring(0, i + 1);
      this._basePackageName = str.substring(0, i);
    }
  }
  
  protected JavaType _typeFromId(String paramString, TypeFactory paramTypeFactory)
  {
    StringBuilder localStringBuilder;
    if (paramString.startsWith("."))
    {
      localStringBuilder = new StringBuilder(paramString.length() + this._basePackageName.length());
      if (this._basePackageName.length() != 0) {
        break label61;
      }
      localStringBuilder.append(paramString.substring(1));
    }
    for (;;)
    {
      paramString = localStringBuilder.toString();
      return super._typeFromId(paramString, paramTypeFactory);
      label61:
      localStringBuilder.append(this._basePackageName).append(paramString);
    }
  }
  
  public JsonTypeInfo.Id getMechanism()
  {
    return JsonTypeInfo.Id.MINIMAL_CLASS;
  }
  
  public String idFromValue(Object paramObject)
  {
    String str = paramObject.getClass().getName();
    if (str.startsWith(this._basePackagePrefix)) {
      str = str.substring(-1 + this._basePackagePrefix.length());
    }
    return str;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/MinimalClassNameIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */