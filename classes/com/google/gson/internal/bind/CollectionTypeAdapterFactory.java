package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class CollectionTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  
  public CollectionTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor)
  {
    this.constructorConstructor = paramConstructorConstructor;
  }
  
  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    Type localType1 = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    if (!Collection.class.isAssignableFrom(localClass)) {}
    Type localType2;
    for (Object localObject = null;; localObject = new Adapter(paramGson, localType2, paramGson.getAdapter(TypeToken.get(localType2)), this.constructorConstructor.get(paramTypeToken)))
    {
      return (TypeAdapter<T>)localObject;
      localType2 = .Gson.Types.getCollectionElementType(localType1, localClass);
    }
  }
  
  private static final class Adapter<E>
    extends TypeAdapter<Collection<E>>
  {
    private final ObjectConstructor<? extends Collection<E>> constructor;
    private final TypeAdapter<E> elementTypeAdapter;
    
    public Adapter(Gson paramGson, Type paramType, TypeAdapter<E> paramTypeAdapter, ObjectConstructor<? extends Collection<E>> paramObjectConstructor)
    {
      this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramGson, paramTypeAdapter, paramType);
      this.constructor = paramObjectConstructor;
    }
    
    public Collection<E> read(JsonReader paramJsonReader)
      throws IOException
    {
      Object localObject;
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        localObject = null;
      }
      for (;;)
      {
        return (Collection<E>)localObject;
        localObject = (Collection)this.constructor.construct();
        paramJsonReader.beginArray();
        while (paramJsonReader.hasNext()) {
          ((Collection)localObject).add(this.elementTypeAdapter.read(paramJsonReader));
        }
        paramJsonReader.endArray();
      }
    }
    
    public void write(JsonWriter paramJsonWriter, Collection<E> paramCollection)
      throws IOException
    {
      if (paramCollection == null) {
        paramJsonWriter.nullValue();
      }
      for (;;)
      {
        return;
        paramJsonWriter.beginArray();
        Iterator localIterator = paramCollection.iterator();
        while (localIterator.hasNext())
        {
          Object localObject = localIterator.next();
          this.elementTypeAdapter.write(paramJsonWriter, localObject);
        }
        paramJsonWriter.endArray();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/internal/bind/CollectionTypeAdapterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */