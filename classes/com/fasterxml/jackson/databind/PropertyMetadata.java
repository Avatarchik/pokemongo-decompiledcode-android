package com.fasterxml.jackson.databind;

import java.io.Serializable;

public class PropertyMetadata
  implements Serializable
{
  public static final PropertyMetadata STD_OPTIONAL = new PropertyMetadata(Boolean.FALSE, null, null, null);
  public static final PropertyMetadata STD_REQUIRED = new PropertyMetadata(Boolean.TRUE, null, null, null);
  public static final PropertyMetadata STD_REQUIRED_OR_OPTIONAL = new PropertyMetadata(null, null, null, null);
  private static final long serialVersionUID = -1L;
  protected final String _defaultValue;
  protected final String _description;
  protected final Integer _index;
  protected final Boolean _required;
  
  @Deprecated
  protected PropertyMetadata(Boolean paramBoolean, String paramString)
  {
    this(paramBoolean, paramString, null, null);
  }
  
  protected PropertyMetadata(Boolean paramBoolean, String paramString1, Integer paramInteger, String paramString2)
  {
    this._required = paramBoolean;
    this._description = paramString1;
    this._index = paramInteger;
    if ((paramString2 == null) || (paramString2.isEmpty())) {
      paramString2 = null;
    }
    this._defaultValue = paramString2;
  }
  
  @Deprecated
  public static PropertyMetadata construct(boolean paramBoolean, String paramString)
  {
    return construct(paramBoolean, paramString, null, null);
  }
  
  public static PropertyMetadata construct(boolean paramBoolean, String paramString1, Integer paramInteger, String paramString2)
  {
    PropertyMetadata localPropertyMetadata;
    if ((paramString1 != null) || (paramInteger != null) || (paramString2 != null)) {
      localPropertyMetadata = new PropertyMetadata(Boolean.valueOf(paramBoolean), paramString1, paramInteger, paramString2);
    }
    for (;;)
    {
      return localPropertyMetadata;
      if (paramBoolean) {
        localPropertyMetadata = STD_REQUIRED;
      } else {
        localPropertyMetadata = STD_OPTIONAL;
      }
    }
  }
  
  public String getDefaultValue()
  {
    return this._defaultValue;
  }
  
  public String getDescription()
  {
    return this._description;
  }
  
  public Integer getIndex()
  {
    return this._index;
  }
  
  public Boolean getRequired()
  {
    return this._required;
  }
  
  public boolean hasDefaultValue()
  {
    if (this._defaultValue != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Deprecated
  public boolean hasDefuaultValue()
  {
    return hasDefaultValue();
  }
  
  public boolean hasIndex()
  {
    if (this._index != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isRequired()
  {
    if ((this._required != null) && (this._required.booleanValue())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected Object readResolve()
  {
    PropertyMetadata localPropertyMetadata;
    if ((this._description == null) && (this._index == null) && (this._defaultValue == null)) {
      if (this._required == null) {
        localPropertyMetadata = STD_REQUIRED_OR_OPTIONAL;
      }
    }
    for (;;)
    {
      return localPropertyMetadata;
      if (this._required.booleanValue())
      {
        localPropertyMetadata = STD_REQUIRED;
      }
      else
      {
        localPropertyMetadata = STD_OPTIONAL;
        continue;
        localPropertyMetadata = this;
      }
    }
  }
  
  public PropertyMetadata withDefaultValue(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      if (this._defaultValue != null) {}
    }
    for (;;)
    {
      return this;
      paramString = null;
      do
      {
        this = new PropertyMetadata(this._required, this._description, this._index, paramString);
        break;
      } while (!this._defaultValue.equals(paramString));
    }
  }
  
  public PropertyMetadata withDescription(String paramString)
  {
    return new PropertyMetadata(this._required, paramString, this._index, this._defaultValue);
  }
  
  public PropertyMetadata withIndex(Integer paramInteger)
  {
    return new PropertyMetadata(this._required, this._description, paramInteger, this._defaultValue);
  }
  
  public PropertyMetadata withRequired(Boolean paramBoolean)
  {
    if (paramBoolean == null) {
      if (this._required != null) {
        break label34;
      }
    }
    for (;;)
    {
      return this;
      if ((this._required == null) || (this._required.booleanValue() != paramBoolean.booleanValue())) {
        label34:
        this = new PropertyMetadata(paramBoolean, this._description, this._index, this._defaultValue);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/PropertyMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */