package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import java.io.Serializable;

public class DeserializerFactoryConfig
  implements Serializable
{
  protected static final KeyDeserializers[] DEFAULT_KEY_DESERIALIZERS;
  protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS;
  protected static final Deserializers[] NO_DESERIALIZERS = new Deserializers[0];
  protected static final BeanDeserializerModifier[] NO_MODIFIERS = new BeanDeserializerModifier[0];
  protected static final ValueInstantiators[] NO_VALUE_INSTANTIATORS;
  private static final long serialVersionUID = 1L;
  protected final AbstractTypeResolver[] _abstractTypeResolvers;
  protected final Deserializers[] _additionalDeserializers;
  protected final KeyDeserializers[] _additionalKeyDeserializers;
  protected final BeanDeserializerModifier[] _modifiers;
  protected final ValueInstantiators[] _valueInstantiators;
  
  static
  {
    NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];
    NO_VALUE_INSTANTIATORS = new ValueInstantiators[0];
    KeyDeserializers[] arrayOfKeyDeserializers = new KeyDeserializers[1];
    arrayOfKeyDeserializers[0] = new StdKeyDeserializers();
    DEFAULT_KEY_DESERIALIZERS = arrayOfKeyDeserializers;
  }
  
  public DeserializerFactoryConfig()
  {
    this(null, null, null, null, null);
  }
  
  protected DeserializerFactoryConfig(Deserializers[] paramArrayOfDeserializers, KeyDeserializers[] paramArrayOfKeyDeserializers, BeanDeserializerModifier[] paramArrayOfBeanDeserializerModifier, AbstractTypeResolver[] paramArrayOfAbstractTypeResolver, ValueInstantiators[] paramArrayOfValueInstantiators)
  {
    if (paramArrayOfDeserializers == null) {
      paramArrayOfDeserializers = NO_DESERIALIZERS;
    }
    this._additionalDeserializers = paramArrayOfDeserializers;
    if (paramArrayOfKeyDeserializers == null) {
      paramArrayOfKeyDeserializers = DEFAULT_KEY_DESERIALIZERS;
    }
    this._additionalKeyDeserializers = paramArrayOfKeyDeserializers;
    if (paramArrayOfBeanDeserializerModifier == null) {
      paramArrayOfBeanDeserializerModifier = NO_MODIFIERS;
    }
    this._modifiers = paramArrayOfBeanDeserializerModifier;
    if (paramArrayOfAbstractTypeResolver == null) {
      paramArrayOfAbstractTypeResolver = NO_ABSTRACT_TYPE_RESOLVERS;
    }
    this._abstractTypeResolvers = paramArrayOfAbstractTypeResolver;
    if (paramArrayOfValueInstantiators == null) {
      paramArrayOfValueInstantiators = NO_VALUE_INSTANTIATORS;
    }
    this._valueInstantiators = paramArrayOfValueInstantiators;
  }
  
  public Iterable<AbstractTypeResolver> abstractTypeResolvers()
  {
    return new ArrayIterator(this._abstractTypeResolvers);
  }
  
  public Iterable<BeanDeserializerModifier> deserializerModifiers()
  {
    return new ArrayIterator(this._modifiers);
  }
  
  public Iterable<Deserializers> deserializers()
  {
    return new ArrayIterator(this._additionalDeserializers);
  }
  
  public boolean hasAbstractTypeResolvers()
  {
    if (this._abstractTypeResolvers.length > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasDeserializerModifiers()
  {
    if (this._modifiers.length > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasDeserializers()
  {
    if (this._additionalDeserializers.length > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasKeyDeserializers()
  {
    if (this._additionalKeyDeserializers.length > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasValueInstantiators()
  {
    if (this._valueInstantiators.length > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Iterable<KeyDeserializers> keyDeserializers()
  {
    return new ArrayIterator(this._additionalKeyDeserializers);
  }
  
  public Iterable<ValueInstantiators> valueInstantiators()
  {
    return new ArrayIterator(this._valueInstantiators);
  }
  
  public DeserializerFactoryConfig withAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver)
  {
    if (paramAbstractTypeResolver == null) {
      throw new IllegalArgumentException("Can not pass null resolver");
    }
    AbstractTypeResolver[] arrayOfAbstractTypeResolver = (AbstractTypeResolver[])ArrayBuilders.insertInListNoDup(this._abstractTypeResolvers, paramAbstractTypeResolver);
    return new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, arrayOfAbstractTypeResolver, this._valueInstantiators);
  }
  
  public DeserializerFactoryConfig withAdditionalDeserializers(Deserializers paramDeserializers)
  {
    if (paramDeserializers == null) {
      throw new IllegalArgumentException("Can not pass null Deserializers");
    }
    return new DeserializerFactoryConfig((Deserializers[])ArrayBuilders.insertInListNoDup(this._additionalDeserializers, paramDeserializers), this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
  }
  
  public DeserializerFactoryConfig withAdditionalKeyDeserializers(KeyDeserializers paramKeyDeserializers)
  {
    if (paramKeyDeserializers == null) {
      throw new IllegalArgumentException("Can not pass null KeyDeserializers");
    }
    KeyDeserializers[] arrayOfKeyDeserializers = (KeyDeserializers[])ArrayBuilders.insertInListNoDup(this._additionalKeyDeserializers, paramKeyDeserializers);
    return new DeserializerFactoryConfig(this._additionalDeserializers, arrayOfKeyDeserializers, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
  }
  
  public DeserializerFactoryConfig withDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier)
  {
    if (paramBeanDeserializerModifier == null) {
      throw new IllegalArgumentException("Can not pass null modifier");
    }
    BeanDeserializerModifier[] arrayOfBeanDeserializerModifier = (BeanDeserializerModifier[])ArrayBuilders.insertInListNoDup(this._modifiers, paramBeanDeserializerModifier);
    return new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, arrayOfBeanDeserializerModifier, this._abstractTypeResolvers, this._valueInstantiators);
  }
  
  public DeserializerFactoryConfig withValueInstantiators(ValueInstantiators paramValueInstantiators)
  {
    if (paramValueInstantiators == null) {
      throw new IllegalArgumentException("Can not pass null resolver");
    }
    ValueInstantiators[] arrayOfValueInstantiators = (ValueInstantiators[])ArrayBuilders.insertInListNoDup(this._valueInstantiators, paramValueInstantiators);
    return new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, arrayOfValueInstantiators);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/cfg/DeserializerFactoryConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */