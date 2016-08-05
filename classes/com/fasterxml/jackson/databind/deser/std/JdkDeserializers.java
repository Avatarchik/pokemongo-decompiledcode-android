package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class JdkDeserializers
{
  private static final HashSet<String> _classNames = new HashSet();
  
  static
  {
    Class[] arrayOfClass1 = new Class[4];
    arrayOfClass1[0] = UUID.class;
    arrayOfClass1[1] = AtomicBoolean.class;
    arrayOfClass1[2] = StackTraceElement.class;
    arrayOfClass1[3] = ByteBuffer.class;
    int i = arrayOfClass1.length;
    for (int j = 0; j < i; j++)
    {
      Class localClass2 = arrayOfClass1[j];
      _classNames.add(localClass2.getName());
    }
    for (Class localClass1 : FromStringDeserializer.types()) {
      _classNames.add(localClass1.getName());
    }
  }
  
  public static JsonDeserializer<?> find(Class<?> paramClass, String paramString)
  {
    Object localObject;
    if (_classNames.contains(paramString))
    {
      localObject = FromStringDeserializer.findDeserializer(paramClass);
      if (localObject == null) {}
    }
    for (;;)
    {
      return (JsonDeserializer<?>)localObject;
      if (paramClass == UUID.class) {
        localObject = new UUIDDeserializer();
      } else if (paramClass == StackTraceElement.class) {
        localObject = new StackTraceElementDeserializer();
      } else if (paramClass == AtomicBoolean.class) {
        localObject = new AtomicBooleanDeserializer();
      } else if (paramClass == ByteBuffer.class) {
        localObject = new ByteBufferDeserializer();
      } else {
        localObject = null;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/JdkDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */