package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class TreeTypeAdapter<T>
  extends TypeAdapter<T>
{
  private TypeAdapter<T> delegate;
  private final JsonDeserializer<T> deserializer;
  private final Gson gson;
  private final JsonSerializer<T> serializer;
  private final TypeAdapterFactory skipPast;
  private final TypeToken<T> typeToken;
  
  private TreeTypeAdapter(JsonSerializer<T> paramJsonSerializer, JsonDeserializer<T> paramJsonDeserializer, Gson paramGson, TypeToken<T> paramTypeToken, TypeAdapterFactory paramTypeAdapterFactory)
  {
    this.serializer = paramJsonSerializer;
    this.deserializer = paramJsonDeserializer;
    this.gson = paramGson;
    this.typeToken = paramTypeToken;
    this.skipPast = paramTypeAdapterFactory;
  }
  
  private TypeAdapter<T> delegate()
  {
    TypeAdapter localTypeAdapter = this.delegate;
    if (localTypeAdapter != null) {}
    for (;;)
    {
      return localTypeAdapter;
      localTypeAdapter = this.gson.getDelegateAdapter(this.skipPast, this.typeToken);
      this.delegate = localTypeAdapter;
    }
  }
  
  public static TypeAdapterFactory newFactory(TypeToken<?> paramTypeToken, Object paramObject)
  {
    return new SingleTypeFactory(paramObject, paramTypeToken, false, null, null);
  }
  
  public static TypeAdapterFactory newFactoryWithMatchRawType(TypeToken<?> paramTypeToken, Object paramObject)
  {
    if (paramTypeToken.getType() == paramTypeToken.getRawType()) {}
    for (boolean bool = true;; bool = false) {
      return new SingleTypeFactory(paramObject, paramTypeToken, bool, null, null);
    }
  }
  
  public static TypeAdapterFactory newTypeHierarchyFactory(Class<?> paramClass, Object paramObject)
  {
    return new SingleTypeFactory(paramObject, null, false, paramClass, null);
  }
  
  public T read(JsonReader paramJsonReader)
    throws IOException
  {
    Object localObject;
    if (this.deserializer == null) {
      localObject = delegate().read(paramJsonReader);
    }
    for (;;)
    {
      return (T)localObject;
      JsonElement localJsonElement = Streams.parse(paramJsonReader);
      if (localJsonElement.isJsonNull()) {
        localObject = null;
      } else {
        localObject = this.deserializer.deserialize(localJsonElement, this.typeToken.getType(), this.gson.deserializationContext);
      }
    }
  }
  
  public void write(JsonWriter paramJsonWriter, T paramT)
    throws IOException
  {
    if (this.serializer == null) {
      delegate().write(paramJsonWriter, paramT);
    }
    for (;;)
    {
      return;
      if (paramT == null) {
        paramJsonWriter.nullValue();
      } else {
        Streams.write(this.serializer.serialize(paramT, this.typeToken.getType(), this.gson.serializationContext), paramJsonWriter);
      }
    }
  }
  
  private static class SingleTypeFactory
    implements TypeAdapterFactory
  {
    private final JsonDeserializer<?> deserializer;
    private final TypeToken<?> exactType;
    private final Class<?> hierarchyType;
    private final boolean matchRawType;
    private final JsonSerializer<?> serializer;
    
    private SingleTypeFactory(Object paramObject, TypeToken<?> paramTypeToken, boolean paramBoolean, Class<?> paramClass)
    {
      JsonSerializer localJsonSerializer;
      JsonDeserializer localJsonDeserializer;
      if ((paramObject instanceof JsonSerializer))
      {
        localJsonSerializer = (JsonSerializer)paramObject;
        this.serializer = localJsonSerializer;
        if (!(paramObject instanceof JsonDeserializer)) {
          break label87;
        }
        localJsonDeserializer = (JsonDeserializer)paramObject;
        label36:
        this.deserializer = localJsonDeserializer;
        if ((this.serializer == null) && (this.deserializer == null)) {
          break label93;
        }
      }
      label87:
      label93:
      for (boolean bool = true;; bool = false)
      {
        .Gson.Preconditions.checkArgument(bool);
        this.exactType = paramTypeToken;
        this.matchRawType = paramBoolean;
        this.hierarchyType = paramClass;
        return;
        localJsonSerializer = null;
        break;
        localJsonDeserializer = null;
        break label36;
      }
    }
    
    public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
    {
      boolean bool;
      if (this.exactType != null) {
        if ((this.exactType.equals(paramTypeToken)) || ((this.matchRawType) && (this.exactType.getType() == paramTypeToken.getRawType())))
        {
          bool = true;
          if (!bool) {
            break label89;
          }
        }
      }
      label89:
      for (TreeTypeAdapter localTreeTypeAdapter = new TreeTypeAdapter(this.serializer, this.deserializer, paramGson, paramTypeToken, this, null);; localTreeTypeAdapter = null)
      {
        return localTreeTypeAdapter;
        bool = false;
        break;
        bool = this.hierarchyType.isAssignableFrom(paramTypeToken.getRawType());
        break;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/TreeTypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */