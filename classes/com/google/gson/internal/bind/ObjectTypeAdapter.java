package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class ObjectTypeAdapter
  extends TypeAdapter<Object>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Object.class) {}
      for (ObjectTypeAdapter localObjectTypeAdapter = new ObjectTypeAdapter(paramAnonymousGson, null);; localObjectTypeAdapter = null) {
        return localObjectTypeAdapter;
      }
    }
  };
  private final Gson gson;
  
  private ObjectTypeAdapter(Gson paramGson)
  {
    this.gson = paramGson;
  }
  
  public Object read(JsonReader paramJsonReader)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonReader.peek();
    Object localObject;
    switch (localJsonToken)
    {
    default: 
      throw new IllegalStateException();
    case ???: 
      localObject = new ArrayList();
      paramJsonReader.beginArray();
      while (paramJsonReader.hasNext()) {
        ((List)localObject).add(read(paramJsonReader));
      }
      paramJsonReader.endArray();
    }
    for (;;)
    {
      return localObject;
      LinkedTreeMap localLinkedTreeMap = new LinkedTreeMap();
      paramJsonReader.beginObject();
      while (paramJsonReader.hasNext()) {
        localLinkedTreeMap.put(paramJsonReader.nextName(), read(paramJsonReader));
      }
      paramJsonReader.endObject();
      localObject = localLinkedTreeMap;
      continue;
      localObject = paramJsonReader.nextString();
      continue;
      localObject = Double.valueOf(paramJsonReader.nextDouble());
      continue;
      localObject = Boolean.valueOf(paramJsonReader.nextBoolean());
      continue;
      paramJsonReader.nextNull();
      localObject = null;
    }
  }
  
  public void write(JsonWriter paramJsonWriter, Object paramObject)
    throws IOException
  {
    if (paramObject == null) {
      paramJsonWriter.nullValue();
    }
    for (;;)
    {
      return;
      TypeAdapter localTypeAdapter = this.gson.getAdapter(paramObject.getClass());
      if ((localTypeAdapter instanceof ObjectTypeAdapter))
      {
        paramJsonWriter.beginObject();
        paramJsonWriter.endObject();
      }
      else
      {
        localTypeAdapter.write(paramJsonWriter, paramObject);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/internal/bind/ObjectTypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */