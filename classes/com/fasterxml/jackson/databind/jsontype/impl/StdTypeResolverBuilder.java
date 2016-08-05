package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.util.Collection;

public class StdTypeResolverBuilder
  implements TypeResolverBuilder<StdTypeResolverBuilder>
{
  protected TypeIdResolver _customIdResolver;
  protected Class<?> _defaultImpl;
  protected JsonTypeInfo.Id _idType;
  protected JsonTypeInfo.As _includeAs;
  protected boolean _typeIdVisible = false;
  protected String _typeProperty;
  
  public static StdTypeResolverBuilder noTypeInfoBuilder()
  {
    return new StdTypeResolverBuilder().init(JsonTypeInfo.Id.NONE, null);
  }
  
  public TypeDeserializer buildTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection)
  {
    Object localObject;
    if (this._idType == JsonTypeInfo.Id.NONE) {
      localObject = null;
    }
    for (;;)
    {
      return (TypeDeserializer)localObject;
      TypeIdResolver localTypeIdResolver = idResolver(paramDeserializationConfig, paramJavaType, paramCollection, false, true);
      switch (1.$SwitchMap$com$fasterxml$jackson$annotation$JsonTypeInfo$As[this._includeAs.ordinal()])
      {
      default: 
        throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
      case 1: 
        localObject = new AsArrayTypeDeserializer(paramJavaType, localTypeIdResolver, this._typeProperty, this._typeIdVisible, this._defaultImpl);
        break;
      case 2: 
      case 5: 
        localObject = new AsPropertyTypeDeserializer(paramJavaType, localTypeIdResolver, this._typeProperty, this._typeIdVisible, this._defaultImpl, this._includeAs);
        break;
      case 3: 
        localObject = new AsWrapperTypeDeserializer(paramJavaType, localTypeIdResolver, this._typeProperty, this._typeIdVisible, this._defaultImpl);
        break;
      case 4: 
        localObject = new AsExternalTypeDeserializer(paramJavaType, localTypeIdResolver, this._typeProperty, this._typeIdVisible, this._defaultImpl);
      }
    }
  }
  
  public TypeSerializer buildTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, Collection<NamedType> paramCollection)
  {
    Object localObject;
    if (this._idType == JsonTypeInfo.Id.NONE) {
      localObject = null;
    }
    for (;;)
    {
      return (TypeSerializer)localObject;
      TypeIdResolver localTypeIdResolver = idResolver(paramSerializationConfig, paramJavaType, paramCollection, true, false);
      switch (1.$SwitchMap$com$fasterxml$jackson$annotation$JsonTypeInfo$As[this._includeAs.ordinal()])
      {
      default: 
        throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
      case 1: 
        localObject = new AsArrayTypeSerializer(localTypeIdResolver, null);
        break;
      case 2: 
        localObject = new AsPropertyTypeSerializer(localTypeIdResolver, null, this._typeProperty);
        break;
      case 3: 
        localObject = new AsWrapperTypeSerializer(localTypeIdResolver, null);
        break;
      case 4: 
        localObject = new AsExternalTypeSerializer(localTypeIdResolver, null, this._typeProperty);
        break;
      case 5: 
        localObject = new AsExistingPropertyTypeSerializer(localTypeIdResolver, null, this._typeProperty);
      }
    }
  }
  
  public StdTypeResolverBuilder defaultImpl(Class<?> paramClass)
  {
    this._defaultImpl = paramClass;
    return this;
  }
  
  public Class<?> getDefaultImpl()
  {
    return this._defaultImpl;
  }
  
  public String getTypeProperty()
  {
    return this._typeProperty;
  }
  
  protected TypeIdResolver idResolver(MapperConfig<?> paramMapperConfig, JavaType paramJavaType, Collection<NamedType> paramCollection, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject;
    if (this._customIdResolver != null) {
      localObject = this._customIdResolver;
    }
    for (;;)
    {
      return (TypeIdResolver)localObject;
      if (this._idType == null) {
        throw new IllegalStateException("Can not build, 'init()' not yet called");
      }
      switch (this._idType)
      {
      default: 
        throw new IllegalStateException("Do not know how to construct standard type id resolver for idType: " + this._idType);
      case ???: 
        localObject = new ClassNameIdResolver(paramJavaType, paramMapperConfig.getTypeFactory());
        break;
      case ???: 
        localObject = new MinimalClassNameIdResolver(paramJavaType, paramMapperConfig.getTypeFactory());
        break;
      case ???: 
        localObject = TypeNameIdResolver.construct(paramMapperConfig, paramJavaType, paramCollection, paramBoolean1, paramBoolean2);
        break;
      case ???: 
        localObject = null;
      }
    }
  }
  
  public StdTypeResolverBuilder inclusion(JsonTypeInfo.As paramAs)
  {
    if (paramAs == null) {
      throw new IllegalArgumentException("includeAs can not be null");
    }
    this._includeAs = paramAs;
    return this;
  }
  
  public StdTypeResolverBuilder init(JsonTypeInfo.Id paramId, TypeIdResolver paramTypeIdResolver)
  {
    if (paramId == null) {
      throw new IllegalArgumentException("idType can not be null");
    }
    this._idType = paramId;
    this._customIdResolver = paramTypeIdResolver;
    this._typeProperty = paramId.getDefaultPropertyName();
    return this;
  }
  
  public boolean isTypeIdVisible()
  {
    return this._typeIdVisible;
  }
  
  public StdTypeResolverBuilder typeIdVisibility(boolean paramBoolean)
  {
    this._typeIdVisible = paramBoolean;
    return this;
  }
  
  public StdTypeResolverBuilder typeProperty(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      paramString = this._idType.getDefaultPropertyName();
    }
    this._typeProperty = paramString;
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/StdTypeResolverBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */