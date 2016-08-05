package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class TypeBase
  extends JavaType
  implements JsonSerializable
{
  private static final long serialVersionUID = -3581199092426900829L;
  volatile transient String _canonicalName;
  
  @Deprecated
  protected TypeBase(Class<?> paramClass, int paramInt, Object paramObject1, Object paramObject2)
  {
    this(paramClass, paramInt, paramObject1, paramObject2, false);
  }
  
  protected TypeBase(Class<?> paramClass, int paramInt, Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    super(paramClass, paramInt, paramObject1, paramObject2, paramBoolean);
  }
  
  protected static StringBuilder _classSignature(Class<?> paramClass, StringBuilder paramStringBuilder, boolean paramBoolean)
  {
    if (paramClass.isPrimitive()) {
      if (paramClass == Boolean.TYPE) {
        paramStringBuilder.append('Z');
      }
    }
    for (;;)
    {
      return paramStringBuilder;
      if (paramClass == Byte.TYPE)
      {
        paramStringBuilder.append('B');
      }
      else if (paramClass == Short.TYPE)
      {
        paramStringBuilder.append('S');
      }
      else if (paramClass == Character.TYPE)
      {
        paramStringBuilder.append('C');
      }
      else if (paramClass == Integer.TYPE)
      {
        paramStringBuilder.append('I');
      }
      else if (paramClass == Long.TYPE)
      {
        paramStringBuilder.append('J');
      }
      else if (paramClass == Float.TYPE)
      {
        paramStringBuilder.append('F');
      }
      else if (paramClass == Double.TYPE)
      {
        paramStringBuilder.append('D');
      }
      else if (paramClass == Void.TYPE)
      {
        paramStringBuilder.append('V');
      }
      else
      {
        throw new IllegalStateException("Unrecognized primitive type: " + paramClass.getName());
        paramStringBuilder.append('L');
        String str = paramClass.getName();
        int i = 0;
        int j = str.length();
        while (i < j)
        {
          char c = str.charAt(i);
          if (c == '.') {
            c = '/';
          }
          paramStringBuilder.append(c);
          i++;
        }
        if (paramBoolean) {
          paramStringBuilder.append(';');
        }
      }
    }
  }
  
  protected abstract String buildCanonicalName();
  
  public abstract StringBuilder getErasedSignature(StringBuilder paramStringBuilder);
  
  public abstract StringBuilder getGenericSignature(StringBuilder paramStringBuilder);
  
  public <T> T getTypeHandler()
  {
    return (T)this._typeHandler;
  }
  
  public <T> T getValueHandler()
  {
    return (T)this._valueHandler;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeString(toCanonical());
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonProcessingException
  {
    paramTypeSerializer.writeTypePrefixForScalar(this, paramJsonGenerator);
    serialize(paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(this, paramJsonGenerator);
  }
  
  public String toCanonical()
  {
    String str = this._canonicalName;
    if (str == null) {
      str = buildCanonicalName();
    }
    return str;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/TypeBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */