package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;

public abstract class FilteredBeanPropertyWriter
{
  public static BeanPropertyWriter constructViewBased(BeanPropertyWriter paramBeanPropertyWriter, Class<?>[] paramArrayOfClass)
  {
    if (paramArrayOfClass.length == 1) {}
    for (Object localObject = new SingleView(paramBeanPropertyWriter, paramArrayOfClass[0]);; localObject = new MultiView(paramBeanPropertyWriter, paramArrayOfClass)) {
      return (BeanPropertyWriter)localObject;
    }
  }
  
  private static final class MultiView
    extends BeanPropertyWriter
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected final BeanPropertyWriter _delegate;
    protected final Class<?>[] _views;
    
    protected MultiView(BeanPropertyWriter paramBeanPropertyWriter, Class<?>[] paramArrayOfClass)
    {
      super();
      this._delegate = paramBeanPropertyWriter;
      this._views = paramArrayOfClass;
    }
    
    public void assignNullSerializer(JsonSerializer<Object> paramJsonSerializer)
    {
      this._delegate.assignNullSerializer(paramJsonSerializer);
    }
    
    public void assignSerializer(JsonSerializer<Object> paramJsonSerializer)
    {
      this._delegate.assignSerializer(paramJsonSerializer);
    }
    
    public MultiView rename(NameTransformer paramNameTransformer)
    {
      return new MultiView(this._delegate.rename(paramNameTransformer), this._views);
    }
    
    public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws Exception
    {
      Class localClass = paramSerializerProvider.getActiveView();
      int i;
      if (localClass != null)
      {
        i = 0;
        int j = this._views.length;
        if ((i >= j) || (this._views[i].isAssignableFrom(localClass)))
        {
          if (i != j) {
            break label67;
          }
          this._delegate.serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
        }
      }
      for (;;)
      {
        return;
        i++;
        break;
        label67:
        this._delegate.serializeAsElement(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
    }
    
    public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws Exception
    {
      Class localClass = paramSerializerProvider.getActiveView();
      int i;
      if (localClass != null)
      {
        i = 0;
        int j = this._views.length;
        if ((i >= j) || (this._views[i].isAssignableFrom(localClass)))
        {
          if (i != j) {
            break label67;
          }
          this._delegate.serializeAsOmittedField(paramObject, paramJsonGenerator, paramSerializerProvider);
        }
      }
      for (;;)
      {
        return;
        i++;
        break;
        label67:
        this._delegate.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  private static final class SingleView
    extends BeanPropertyWriter
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected final BeanPropertyWriter _delegate;
    protected final Class<?> _view;
    
    protected SingleView(BeanPropertyWriter paramBeanPropertyWriter, Class<?> paramClass)
    {
      super();
      this._delegate = paramBeanPropertyWriter;
      this._view = paramClass;
    }
    
    public void assignNullSerializer(JsonSerializer<Object> paramJsonSerializer)
    {
      this._delegate.assignNullSerializer(paramJsonSerializer);
    }
    
    public void assignSerializer(JsonSerializer<Object> paramJsonSerializer)
    {
      this._delegate.assignSerializer(paramJsonSerializer);
    }
    
    public SingleView rename(NameTransformer paramNameTransformer)
    {
      return new SingleView(this._delegate.rename(paramNameTransformer), this._view);
    }
    
    public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws Exception
    {
      Class localClass = paramSerializerProvider.getActiveView();
      if ((localClass == null) || (this._view.isAssignableFrom(localClass))) {
        this._delegate.serializeAsElement(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
      for (;;)
      {
        return;
        this._delegate.serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
    }
    
    public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws Exception
    {
      Class localClass = paramSerializerProvider.getActiveView();
      if ((localClass == null) || (this._view.isAssignableFrom(localClass))) {
        this._delegate.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
      for (;;)
      {
        return;
        this._delegate.serializeAsOmittedField(paramObject, paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/FilteredBeanPropertyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */