package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;

public class JsonLocationInstantiator
  extends ValueInstantiator
{
  private static final int _int(Object paramObject)
  {
    if (paramObject == null) {}
    for (int i = 0;; i = ((Number)paramObject).intValue()) {
      return i;
    }
  }
  
  private static final long _long(Object paramObject)
  {
    if (paramObject == null) {}
    for (long l = 0L;; l = ((Number)paramObject).longValue()) {
      return l;
    }
  }
  
  private static CreatorProperty creatorProp(String paramString, JavaType paramJavaType, int paramInt)
  {
    return new CreatorProperty(PropertyName.construct(paramString), paramJavaType, null, null, null, null, paramInt, null, PropertyMetadata.STD_REQUIRED);
  }
  
  public boolean canCreateFromObjectWith()
  {
    return true;
  }
  
  public Object createFromObjectWith(DeserializationContext paramDeserializationContext, Object[] paramArrayOfObject)
  {
    return new JsonLocation(paramArrayOfObject[0], _long(paramArrayOfObject[1]), _long(paramArrayOfObject[2]), _int(paramArrayOfObject[3]), _int(paramArrayOfObject[4]));
  }
  
  public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig paramDeserializationConfig)
  {
    JavaType localJavaType1 = paramDeserializationConfig.constructType(Integer.TYPE);
    JavaType localJavaType2 = paramDeserializationConfig.constructType(Long.TYPE);
    SettableBeanProperty[] arrayOfSettableBeanProperty = new SettableBeanProperty[5];
    arrayOfSettableBeanProperty[0] = creatorProp("sourceRef", paramDeserializationConfig.constructType(Object.class), 0);
    arrayOfSettableBeanProperty[1] = creatorProp("byteOffset", localJavaType2, 1);
    arrayOfSettableBeanProperty[2] = creatorProp("charOffset", localJavaType2, 2);
    arrayOfSettableBeanProperty[3] = creatorProp("lineNr", localJavaType1, 3);
    arrayOfSettableBeanProperty[4] = creatorProp("columnNr", localJavaType1, 4);
    return arrayOfSettableBeanProperty;
  }
  
  public String getValueTypeDesc()
  {
    return JsonLocation.class.getName();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/JsonLocationInstantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */