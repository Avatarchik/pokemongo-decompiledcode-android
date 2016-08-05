package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public abstract class PropertyBindingException
  extends JsonMappingException
{
  private static final int MAX_DESC_LENGTH = 1000;
  protected transient String _propertiesAsString;
  protected final Collection<Object> _propertyIds;
  protected final String _propertyName;
  protected final Class<?> _referringClass;
  
  protected PropertyBindingException(String paramString1, JsonLocation paramJsonLocation, Class<?> paramClass, String paramString2, Collection<Object> paramCollection)
  {
    super(paramString1, paramJsonLocation);
    this._referringClass = paramClass;
    this._propertyName = paramString2;
    this._propertyIds = paramCollection;
  }
  
  public Collection<Object> getKnownPropertyIds()
  {
    if (this._propertyIds == null) {}
    for (Object localObject = null;; localObject = Collections.unmodifiableCollection(this._propertyIds)) {
      return (Collection<Object>)localObject;
    }
  }
  
  public String getMessageSuffix()
  {
    String str = this._propertiesAsString;
    StringBuilder localStringBuilder;
    int i;
    if ((str == null) && (this._propertyIds != null))
    {
      localStringBuilder = new StringBuilder(100);
      i = this._propertyIds.size();
      if (i != 1) {
        break label96;
      }
      localStringBuilder.append(" (one known property: \"");
      localStringBuilder.append(String.valueOf(this._propertyIds.iterator().next()));
      localStringBuilder.append('"');
    }
    label96:
    label200:
    for (;;)
    {
      localStringBuilder.append("])");
      str = localStringBuilder.toString();
      this._propertiesAsString = str;
      return str;
      localStringBuilder.append(" (").append(i).append(" known properties: ");
      Iterator localIterator = this._propertyIds.iterator();
      for (;;)
      {
        if (!localIterator.hasNext()) {
          break label200;
        }
        localStringBuilder.append('"');
        localStringBuilder.append(String.valueOf(localIterator.next()));
        localStringBuilder.append('"');
        if (localStringBuilder.length() > 1000)
        {
          localStringBuilder.append(" [truncated]");
          break;
        }
        if (localIterator.hasNext()) {
          localStringBuilder.append(", ");
        }
      }
    }
  }
  
  public String getPropertyName()
  {
    return this._propertyName;
  }
  
  public Class<?> getReferringClass()
  {
    return this._referringClass;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/exc/PropertyBindingException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */