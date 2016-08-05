package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.util.InternCache;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;

public class PropertyName
  implements Serializable
{
  public static final PropertyName NO_NAME = new PropertyName(new String(""), null);
  public static final PropertyName USE_DEFAULT = new PropertyName("", null);
  private static final String _NO_NAME = "";
  private static final String _USE_DEFAULT = "";
  private static final long serialVersionUID = 1L;
  protected SerializableString _encodedSimple;
  protected final String _namespace;
  protected final String _simpleName;
  
  public PropertyName(String paramString)
  {
    this(paramString, null);
  }
  
  public PropertyName(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      paramString1 = "";
    }
    this._simpleName = paramString1;
    this._namespace = paramString2;
  }
  
  public static PropertyName construct(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    for (PropertyName localPropertyName = USE_DEFAULT;; localPropertyName = new PropertyName(InternCache.instance.intern(paramString), null)) {
      return localPropertyName;
    }
  }
  
  public static PropertyName construct(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      paramString1 = "";
    }
    if ((paramString2 == null) && (paramString1.length() == 0)) {}
    for (PropertyName localPropertyName = USE_DEFAULT;; localPropertyName = new PropertyName(InternCache.instance.intern(paramString1), paramString2)) {
      return localPropertyName;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramObject == this) {
      bool2 = bool1;
    }
    for (;;)
    {
      return bool2;
      if ((paramObject != null) && (paramObject.getClass() == getClass()))
      {
        PropertyName localPropertyName = (PropertyName)paramObject;
        if (this._simpleName == null)
        {
          if (localPropertyName._simpleName != null) {
            continue;
          }
          label49:
          if (this._namespace != null) {
            break label92;
          }
          if (localPropertyName._namespace != null) {
            break label87;
          }
        }
        for (;;)
        {
          bool2 = bool1;
          break;
          if (this._simpleName.equals(localPropertyName._simpleName)) {
            break label49;
          }
          break;
          label87:
          bool1 = false;
        }
        label92:
        bool2 = this._namespace.equals(localPropertyName._namespace);
      }
    }
  }
  
  public String getNamespace()
  {
    return this._namespace;
  }
  
  public String getSimpleName()
  {
    return this._simpleName;
  }
  
  public boolean hasNamespace()
  {
    if (this._namespace != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasSimpleName()
  {
    if (this._simpleName.length() > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasSimpleName(String paramString)
  {
    boolean bool;
    if (paramString == null) {
      if (this._simpleName == null) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = paramString.equals(this._simpleName);
    }
  }
  
  public int hashCode()
  {
    if (this._namespace == null) {}
    for (int i = this._simpleName.hashCode();; i = this._namespace.hashCode() ^ this._simpleName.hashCode()) {
      return i;
    }
  }
  
  public PropertyName internSimpleName()
  {
    if (this._simpleName.length() == 0) {}
    for (;;)
    {
      return this;
      String str = InternCache.instance.intern(this._simpleName);
      if (str != this._simpleName) {
        this = new PropertyName(str, this._namespace);
      }
    }
  }
  
  public boolean isEmpty()
  {
    if ((this._namespace == null) && (this._simpleName.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected Object readResolve()
  {
    if ((this._simpleName == null) || ("".equals(this._simpleName))) {}
    for (this = USE_DEFAULT;; this = NO_NAME) {
      do
      {
        return this;
      } while ((!this._simpleName.equals("")) || (this._namespace != null));
    }
  }
  
  public SerializableString simpleAsEncoded(MapperConfig<?> paramMapperConfig)
  {
    Object localObject = this._encodedSimple;
    if (localObject == null) {
      if (paramMapperConfig != null) {
        break label32;
      }
    }
    label32:
    for (localObject = new SerializedString(this._simpleName);; localObject = paramMapperConfig.compileString(this._simpleName))
    {
      this._encodedSimple = ((SerializableString)localObject);
      return (SerializableString)localObject;
    }
  }
  
  public String toString()
  {
    if (this._namespace == null) {}
    for (String str = this._simpleName;; str = "{" + this._namespace + "}" + this._simpleName) {
      return str;
    }
  }
  
  public PropertyName withNamespace(String paramString)
  {
    if (paramString == null) {
      if (this._namespace != null) {
        break label24;
      }
    }
    for (;;)
    {
      return this;
      if (!paramString.equals(this._namespace)) {
        label24:
        this = new PropertyName(this._simpleName, paramString);
      }
    }
  }
  
  public PropertyName withSimpleName(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    if (paramString.equals(this._simpleName)) {}
    for (;;)
    {
      return this;
      this = new PropertyName(paramString, this._namespace);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/PropertyName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */