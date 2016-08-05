package com.upsight.android.internal.persistence.storable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightException;
import com.upsight.android.persistence.UpsightStorableSerializer;
import java.io.IOException;

public class DefaultJsonSerializer<T>
  implements UpsightStorableSerializer<T>
{
  private final Class<T> mClass;
  private final ObjectMapper mObjectMapper;
  
  public DefaultJsonSerializer(ObjectMapper paramObjectMapper, Class<T> paramClass)
  {
    this.mObjectMapper = paramObjectMapper;
    this.mClass = paramClass;
  }
  
  public T fromString(String paramString)
    throws UpsightException
  {
    try
    {
      Object localObject = this.mObjectMapper.treeToValue(this.mObjectMapper.readTree(paramString), this.mClass);
      return (T)localObject;
    }
    catch (IOException localIOException)
    {
      throw new UpsightException(localIOException);
    }
  }
  
  public String toString(T paramT)
  {
    return this.mObjectMapper.valueToTree(paramT).toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/DefaultJsonSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */