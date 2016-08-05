package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class MapTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final boolean complexMapKeySerialization;
  private final ConstructorConstructor constructorConstructor;
  
  public MapTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor, boolean paramBoolean)
  {
    this.constructorConstructor = paramConstructorConstructor;
    this.complexMapKeySerialization = paramBoolean;
  }
  
  private TypeAdapter<?> getKeyAdapter(Gson paramGson, Type paramType)
  {
    if ((paramType == Boolean.TYPE) || (paramType == Boolean.class)) {}
    for (TypeAdapter localTypeAdapter = TypeAdapters.BOOLEAN_AS_STRING;; localTypeAdapter = paramGson.getAdapter(TypeToken.get(paramType))) {
      return localTypeAdapter;
    }
  }
  
  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    Type localType = paramTypeToken.getType();
    if (!Map.class.isAssignableFrom(paramTypeToken.getRawType())) {}
    Type[] arrayOfType;
    TypeAdapter localTypeAdapter1;
    TypeAdapter localTypeAdapter2;
    ObjectConstructor localObjectConstructor;
    for (Object localObject = null;; localObject = new Adapter(paramGson, arrayOfType[0], localTypeAdapter1, arrayOfType[1], localTypeAdapter2, localObjectConstructor))
    {
      return (TypeAdapter<T>)localObject;
      arrayOfType = .Gson.Types.getMapKeyAndValueTypes(localType, .Gson.Types.getRawType(localType));
      localTypeAdapter1 = getKeyAdapter(paramGson, arrayOfType[0]);
      localTypeAdapter2 = paramGson.getAdapter(TypeToken.get(arrayOfType[1]));
      localObjectConstructor = this.constructorConstructor.get(paramTypeToken);
    }
  }
  
  private final class Adapter<K, V>
    extends TypeAdapter<Map<K, V>>
  {
    private final ObjectConstructor<? extends Map<K, V>> constructor;
    private final TypeAdapter<K> keyTypeAdapter;
    private final TypeAdapter<V> valueTypeAdapter;
    
    public Adapter(Type paramType1, TypeAdapter<K> paramTypeAdapter, Type paramType2, TypeAdapter<V> paramTypeAdapter1, ObjectConstructor<? extends Map<K, V>> paramObjectConstructor)
    {
      this.keyTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramType1, paramType2, paramTypeAdapter);
      this.valueTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramType1, paramObjectConstructor, paramTypeAdapter1);
      ObjectConstructor localObjectConstructor;
      this.constructor = localObjectConstructor;
    }
    
    private String keyToString(JsonElement paramJsonElement)
    {
      JsonPrimitive localJsonPrimitive;
      String str;
      if (paramJsonElement.isJsonPrimitive())
      {
        localJsonPrimitive = paramJsonElement.getAsJsonPrimitive();
        if (localJsonPrimitive.isNumber()) {
          str = String.valueOf(localJsonPrimitive.getAsNumber());
        }
      }
      for (;;)
      {
        return str;
        if (localJsonPrimitive.isBoolean())
        {
          str = Boolean.toString(localJsonPrimitive.getAsBoolean());
        }
        else if (localJsonPrimitive.isString())
        {
          str = localJsonPrimitive.getAsString();
        }
        else
        {
          throw new AssertionError();
          if (!paramJsonElement.isJsonNull()) {
            break;
          }
          str = "null";
        }
      }
      throw new AssertionError();
    }
    
    public Map<K, V> read(JsonReader paramJsonReader)
      throws IOException
    {
      JsonToken localJsonToken = paramJsonReader.peek();
      Object localObject1;
      if (localJsonToken == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        localObject1 = null;
      }
      for (;;)
      {
        return (Map<K, V>)localObject1;
        localObject1 = (Map)this.constructor.construct();
        if (localJsonToken == JsonToken.BEGIN_ARRAY)
        {
          paramJsonReader.beginArray();
          while (paramJsonReader.hasNext())
          {
            paramJsonReader.beginArray();
            Object localObject3 = this.keyTypeAdapter.read(paramJsonReader);
            if (((Map)localObject1).put(localObject3, this.valueTypeAdapter.read(paramJsonReader)) != null) {
              throw new JsonSyntaxException("duplicate key: " + localObject3);
            }
            paramJsonReader.endArray();
          }
          paramJsonReader.endArray();
        }
        else
        {
          paramJsonReader.beginObject();
          while (paramJsonReader.hasNext())
          {
            JsonReaderInternalAccess.INSTANCE.promoteNameToValue(paramJsonReader);
            Object localObject2 = this.keyTypeAdapter.read(paramJsonReader);
            if (((Map)localObject1).put(localObject2, this.valueTypeAdapter.read(paramJsonReader)) != null) {
              throw new JsonSyntaxException("duplicate key: " + localObject2);
            }
          }
          paramJsonReader.endObject();
        }
      }
    }
    
    public void write(JsonWriter paramJsonWriter, Map<K, V> paramMap)
      throws IOException
    {
      if (paramMap == null) {
        paramJsonWriter.nullValue();
      }
      for (;;)
      {
        return;
        if (!MapTypeAdapterFactory.this.complexMapKeySerialization)
        {
          paramJsonWriter.beginObject();
          Iterator localIterator2 = paramMap.entrySet().iterator();
          while (localIterator2.hasNext())
          {
            Map.Entry localEntry2 = (Map.Entry)localIterator2.next();
            paramJsonWriter.name(String.valueOf(localEntry2.getKey()));
            this.valueTypeAdapter.write(paramJsonWriter, localEntry2.getValue());
          }
          paramJsonWriter.endObject();
        }
        else
        {
          int i = 0;
          ArrayList localArrayList1 = new ArrayList(paramMap.size());
          ArrayList localArrayList2 = new ArrayList(paramMap.size());
          Iterator localIterator1 = paramMap.entrySet().iterator();
          if (localIterator1.hasNext())
          {
            Map.Entry localEntry1 = (Map.Entry)localIterator1.next();
            JsonElement localJsonElement = this.keyTypeAdapter.toJsonTree(localEntry1.getKey());
            localArrayList1.add(localJsonElement);
            localArrayList2.add(localEntry1.getValue());
            if ((localJsonElement.isJsonArray()) || (localJsonElement.isJsonObject())) {}
            for (int m = 1;; m = 0)
            {
              i |= m;
              break;
            }
          }
          if (i != 0)
          {
            paramJsonWriter.beginArray();
            for (int k = 0; k < localArrayList1.size(); k++)
            {
              paramJsonWriter.beginArray();
              Streams.write((JsonElement)localArrayList1.get(k), paramJsonWriter);
              this.valueTypeAdapter.write(paramJsonWriter, localArrayList2.get(k));
              paramJsonWriter.endArray();
            }
            paramJsonWriter.endArray();
          }
          else
          {
            paramJsonWriter.beginObject();
            for (int j = 0; j < localArrayList1.size(); j++)
            {
              paramJsonWriter.name(keyToString((JsonElement)localArrayList1.get(j)));
              this.valueTypeAdapter.write(paramJsonWriter, localArrayList2.get(j));
            }
            paramJsonWriter.endObject();
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/internal/bind/MapTypeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */