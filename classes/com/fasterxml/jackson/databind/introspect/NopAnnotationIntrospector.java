package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import java.io.Serializable;

public abstract class NopAnnotationIntrospector
  extends AnnotationIntrospector
  implements Serializable
{
  public static final NopAnnotationIntrospector instance = new NopAnnotationIntrospector()
  {
    private static final long serialVersionUID = 1L;
    
    public Version version()
    {
      return PackageVersion.VERSION;
    }
  };
  private static final long serialVersionUID = 1L;
  
  public Version version()
  {
    return Version.unknownVersion();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/NopAnnotationIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */