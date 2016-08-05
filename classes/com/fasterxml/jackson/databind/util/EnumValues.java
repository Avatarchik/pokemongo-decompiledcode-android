package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class EnumValues
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private transient EnumMap<?, SerializableString> _asMap;
  private final Class<Enum<?>> _enumClass;
  private final SerializableString[] _textual;
  private final Enum<?>[] _values;
  
  private EnumValues(Class<Enum<?>> paramClass, SerializableString[] paramArrayOfSerializableString)
  {
    this._enumClass = paramClass;
    this._values = ((Enum[])paramClass.getEnumConstants());
    this._textual = paramArrayOfSerializableString;
  }
  
  public static EnumValues construct(SerializationConfig paramSerializationConfig, Class<Enum<?>> paramClass)
  {
    if (paramSerializationConfig.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {}
    for (EnumValues localEnumValues = constructFromToString(paramSerializationConfig, paramClass);; localEnumValues = constructFromName(paramSerializationConfig, paramClass)) {
      return localEnumValues;
    }
  }
  
  public static EnumValues constructFromName(MapperConfig<?> paramMapperConfig, Class<Enum<?>> paramClass)
  {
    Enum[] arrayOfEnum = (Enum[])ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (arrayOfEnum != null)
    {
      SerializableString[] arrayOfSerializableString = new SerializableString[arrayOfEnum.length];
      int i = arrayOfEnum.length;
      for (int j = 0; j < i; j++)
      {
        Enum localEnum = arrayOfEnum[j];
        String str = paramMapperConfig.getAnnotationIntrospector().findEnumValue(localEnum);
        arrayOfSerializableString[localEnum.ordinal()] = paramMapperConfig.compileString(str);
      }
      return new EnumValues(paramClass, arrayOfSerializableString);
    }
    throw new IllegalArgumentException("Can not determine enum constants for Class " + paramClass.getName());
  }
  
  public static EnumValues constructFromToString(MapperConfig<?> paramMapperConfig, Class<Enum<?>> paramClass)
  {
    Enum[] arrayOfEnum = (Enum[])ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (arrayOfEnum != null)
    {
      SerializableString[] arrayOfSerializableString = new SerializableString[arrayOfEnum.length];
      int i = arrayOfEnum.length;
      for (int j = 0; j < i; j++)
      {
        Enum localEnum = arrayOfEnum[j];
        arrayOfSerializableString[localEnum.ordinal()] = paramMapperConfig.compileString(localEnum.toString());
      }
      return new EnumValues(paramClass, arrayOfSerializableString);
    }
    throw new IllegalArgumentException("Can not determine enum constants for Class " + paramClass.getName());
  }
  
  public List<Enum<?>> enums()
  {
    return Arrays.asList(this._values);
  }
  
  public Class<Enum<?>> getEnumClass()
  {
    return this._enumClass;
  }
  
  public EnumMap<?, SerializableString> internalMap()
  {
    EnumMap localEnumMap = this._asMap;
    if (localEnumMap == null)
    {
      LinkedHashMap localLinkedHashMap = new LinkedHashMap();
      for (Enum localEnum : this._values) {
        localLinkedHashMap.put(localEnum, this._textual[localEnum.ordinal()]);
      }
      localEnumMap = new EnumMap(localLinkedHashMap);
    }
    return localEnumMap;
  }
  
  public SerializableString serializedValueFor(Enum<?> paramEnum)
  {
    return this._textual[paramEnum.ordinal()];
  }
  
  public Collection<SerializableString> values()
  {
    return Arrays.asList(this._textual);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/EnumValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */