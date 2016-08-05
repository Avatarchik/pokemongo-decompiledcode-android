package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.databind.PropertyName;

public class ObjectIdInfo
{
  protected final boolean _alwaysAsId;
  protected final Class<? extends ObjectIdGenerator<?>> _generator;
  protected final PropertyName _propertyName;
  protected final Class<? extends ObjectIdResolver> _resolver;
  protected final Class<?> _scope;
  
  @Deprecated
  public ObjectIdInfo(PropertyName paramPropertyName, Class<?> paramClass, Class<? extends ObjectIdGenerator<?>> paramClass1)
  {
    this(paramPropertyName, paramClass, paramClass1, false);
  }
  
  public ObjectIdInfo(PropertyName paramPropertyName, Class<?> paramClass, Class<? extends ObjectIdGenerator<?>> paramClass1, Class<? extends ObjectIdResolver> paramClass2)
  {
    this(paramPropertyName, paramClass, paramClass1, false, paramClass2);
  }
  
  protected ObjectIdInfo(PropertyName paramPropertyName, Class<?> paramClass, Class<? extends ObjectIdGenerator<?>> paramClass1, boolean paramBoolean)
  {
    this(paramPropertyName, paramClass, paramClass1, paramBoolean, SimpleObjectIdResolver.class);
  }
  
  protected ObjectIdInfo(PropertyName paramPropertyName, Class<?> paramClass, Class<? extends ObjectIdGenerator<?>> paramClass1, boolean paramBoolean, Class<? extends ObjectIdResolver> paramClass2)
  {
    this._propertyName = paramPropertyName;
    this._scope = paramClass;
    this._generator = paramClass1;
    this._alwaysAsId = paramBoolean;
    if (paramClass2 == null) {
      paramClass2 = SimpleObjectIdResolver.class;
    }
    this._resolver = paramClass2;
  }
  
  @Deprecated
  public ObjectIdInfo(String paramString, Class<?> paramClass, Class<? extends ObjectIdGenerator<?>> paramClass1)
  {
    this(new PropertyName(paramString), paramClass, paramClass1, false);
  }
  
  public boolean getAlwaysAsId()
  {
    return this._alwaysAsId;
  }
  
  public Class<? extends ObjectIdGenerator<?>> getGeneratorType()
  {
    return this._generator;
  }
  
  public PropertyName getPropertyName()
  {
    return this._propertyName;
  }
  
  public Class<? extends ObjectIdResolver> getResolverType()
  {
    return this._resolver;
  }
  
  public Class<?> getScope()
  {
    return this._scope;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder().append("ObjectIdInfo: propName=").append(this._propertyName).append(", scope=");
    String str1;
    StringBuilder localStringBuilder2;
    if (this._scope == null)
    {
      str1 = "null";
      localStringBuilder2 = localStringBuilder1.append(str1).append(", generatorType=");
      if (this._generator != null) {
        break label90;
      }
    }
    label90:
    for (String str2 = "null";; str2 = this._generator.getName())
    {
      return str2 + ", alwaysAsId=" + this._alwaysAsId;
      str1 = this._scope.getName();
      break;
    }
  }
  
  public ObjectIdInfo withAlwaysAsId(boolean paramBoolean)
  {
    if (this._alwaysAsId == paramBoolean) {}
    for (;;)
    {
      return this;
      this = new ObjectIdInfo(this._propertyName, this._scope, this._generator, paramBoolean, this._resolver);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/ObjectIdInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */