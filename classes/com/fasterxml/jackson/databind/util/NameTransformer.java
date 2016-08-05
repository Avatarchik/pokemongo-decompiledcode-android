package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public abstract class NameTransformer
{
  public static final NameTransformer NOP = new NopTransformer();
  
  public static NameTransformer chainedTransformer(NameTransformer paramNameTransformer1, NameTransformer paramNameTransformer2)
  {
    return new Chained(paramNameTransformer1, paramNameTransformer2);
  }
  
  public static NameTransformer simpleTransformer(String paramString1, final String paramString2)
  {
    int i = 1;
    int j;
    label26:
    Object localObject;
    if ((paramString1 != null) && (paramString1.length() > 0))
    {
      j = i;
      if ((paramString2 == null) || (paramString2.length() <= 0)) {
        break label53;
      }
      if (j == 0) {
        break label71;
      }
      if (i == 0) {
        break label58;
      }
      localObject = new NameTransformer()
      {
        public String reverse(String paramAnonymousString)
        {
          String str2;
          if (paramAnonymousString.startsWith(NameTransformer.this))
          {
            str2 = paramAnonymousString.substring(NameTransformer.this.length());
            if (!str2.endsWith(paramString2)) {}
          }
          for (String str1 = str2.substring(0, str2.length() - paramString2.length());; str1 = null) {
            return str1;
          }
        }
        
        public String toString()
        {
          return "[PreAndSuffixTransformer('" + NameTransformer.this + "','" + paramString2 + "')]";
        }
        
        public String transform(String paramAnonymousString)
        {
          return NameTransformer.this + paramAnonymousString + paramString2;
        }
      };
    }
    for (;;)
    {
      return (NameTransformer)localObject;
      j = 0;
      break;
      label53:
      i = 0;
      break label26;
      label58:
      localObject = new NameTransformer()
      {
        public String reverse(String paramAnonymousString)
        {
          if (paramAnonymousString.startsWith(NameTransformer.this)) {}
          for (String str = paramAnonymousString.substring(NameTransformer.this.length());; str = null) {
            return str;
          }
        }
        
        public String toString()
        {
          return "[PrefixTransformer('" + NameTransformer.this + "')]";
        }
        
        public String transform(String paramAnonymousString)
        {
          return NameTransformer.this + paramAnonymousString;
        }
      };
      continue;
      label71:
      if (i != 0) {
        localObject = new NameTransformer()
        {
          public String reverse(String paramAnonymousString)
          {
            if (paramAnonymousString.endsWith(NameTransformer.this)) {}
            for (String str = paramAnonymousString.substring(0, paramAnonymousString.length() - NameTransformer.this.length());; str = null) {
              return str;
            }
          }
          
          public String toString()
          {
            return "[SuffixTransformer('" + NameTransformer.this + "')]";
          }
          
          public String transform(String paramAnonymousString)
          {
            return paramAnonymousString + NameTransformer.this;
          }
        };
      } else {
        localObject = NOP;
      }
    }
  }
  
  public abstract String reverse(String paramString);
  
  public abstract String transform(String paramString);
  
  public static class Chained
    extends NameTransformer
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    protected final NameTransformer _t1;
    protected final NameTransformer _t2;
    
    public Chained(NameTransformer paramNameTransformer1, NameTransformer paramNameTransformer2)
    {
      this._t1 = paramNameTransformer1;
      this._t2 = paramNameTransformer2;
    }
    
    public String reverse(String paramString)
    {
      String str = this._t1.reverse(paramString);
      if (str != null) {
        str = this._t2.reverse(str);
      }
      return str;
    }
    
    public String toString()
    {
      return "[ChainedTransformer(" + this._t1 + ", " + this._t2 + ")]";
    }
    
    public String transform(String paramString)
    {
      return this._t1.transform(this._t2.transform(paramString));
    }
  }
  
  protected static final class NopTransformer
    extends NameTransformer
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    
    public String reverse(String paramString)
    {
      return paramString;
    }
    
    public String transform(String paramString)
    {
      return paramString;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/NameTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */