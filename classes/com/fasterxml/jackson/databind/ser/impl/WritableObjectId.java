package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class WritableObjectId
{
  public final ObjectIdGenerator<?> generator;
  public Object id;
  protected boolean idWritten = false;
  
  public WritableObjectId(ObjectIdGenerator<?> paramObjectIdGenerator)
  {
    this.generator = paramObjectIdGenerator;
  }
  
  public Object generateId(Object paramObject)
  {
    Object localObject = this.generator.generateId(paramObject);
    this.id = localObject;
    return localObject;
  }
  
  public void writeAsField(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, ObjectIdWriter paramObjectIdWriter)
    throws IOException
  {
    this.idWritten = true;
    if (paramJsonGenerator.canWriteObjectId()) {
      paramJsonGenerator.writeObjectId(String.valueOf(this.id));
    }
    for (;;)
    {
      return;
      SerializableString localSerializableString = paramObjectIdWriter.propertyName;
      if (localSerializableString != null)
      {
        paramJsonGenerator.writeFieldName(localSerializableString);
        paramObjectIdWriter.serializer.serialize(this.id, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public boolean writeAsId(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, ObjectIdWriter paramObjectIdWriter)
    throws IOException
  {
    if ((this.id != null) && ((this.idWritten) || (paramObjectIdWriter.alwaysAsId))) {
      if (paramJsonGenerator.canWriteObjectId()) {
        paramJsonGenerator.writeObjectRef(String.valueOf(this.id));
      }
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      paramObjectIdWriter.serializer.serialize(this.id, paramJsonGenerator, paramSerializerProvider);
      break;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/WritableObjectId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */