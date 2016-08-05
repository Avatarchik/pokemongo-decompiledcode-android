package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter<T>
{
  public final T fromJson(Reader paramReader)
    throws IOException
  {
    return (T)read(new JsonReader(paramReader));
  }
  
  public final T fromJson(String paramString)
    throws IOException
  {
    return (T)fromJson(new StringReader(paramString));
  }
  
  public final T fromJsonTree(JsonElement paramJsonElement)
  {
    try
    {
      Object localObject = read(new JsonTreeReader(paramJsonElement));
      return (T)localObject;
    }
    catch (IOException localIOException)
    {
      throw new JsonIOException(localIOException);
    }
  }
  
  public final TypeAdapter<T> nullSafe()
  {
    new TypeAdapter()
    {
      public T read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL) {
          paramAnonymousJsonReader.nextNull();
        }
        for (Object localObject = null;; localObject = TypeAdapter.this.read(paramAnonymousJsonReader)) {
          return (T)localObject;
        }
      }
      
      public void write(JsonWriter paramAnonymousJsonWriter, T paramAnonymousT)
        throws IOException
      {
        if (paramAnonymousT == null) {
          paramAnonymousJsonWriter.nullValue();
        }
        for (;;)
        {
          return;
          TypeAdapter.this.write(paramAnonymousJsonWriter, paramAnonymousT);
        }
      }
    };
  }
  
  public abstract T read(JsonReader paramJsonReader)
    throws IOException;
  
  public final String toJson(T paramT)
    throws IOException
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(localStringWriter, paramT);
    return localStringWriter.toString();
  }
  
  public final void toJson(Writer paramWriter, T paramT)
    throws IOException
  {
    write(new JsonWriter(paramWriter), paramT);
  }
  
  public final JsonElement toJsonTree(T paramT)
  {
    try
    {
      JsonTreeWriter localJsonTreeWriter = new JsonTreeWriter();
      write(localJsonTreeWriter, paramT);
      JsonElement localJsonElement = localJsonTreeWriter.get();
      return localJsonElement;
    }
    catch (IOException localIOException)
    {
      throw new JsonIOException(localIOException);
    }
  }
  
  public abstract void write(JsonWriter paramJsonWriter, T paramT)
    throws IOException;
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/TypeAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */