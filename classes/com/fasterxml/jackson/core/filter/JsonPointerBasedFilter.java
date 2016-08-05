package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonPointer;

public class JsonPointerBasedFilter
  extends TokenFilter
{
  protected final JsonPointer _pathToMatch;
  
  public JsonPointerBasedFilter(JsonPointer paramJsonPointer)
  {
    this._pathToMatch = paramJsonPointer;
  }
  
  public JsonPointerBasedFilter(String paramString)
  {
    this(JsonPointer.compile(paramString));
  }
  
  protected boolean _includeScalar()
  {
    return this._pathToMatch.matches();
  }
  
  public TokenFilter filterStartArray()
  {
    return this;
  }
  
  public TokenFilter filterStartObject()
  {
    return this;
  }
  
  public TokenFilter includeElement(int paramInt)
  {
    JsonPointer localJsonPointer = this._pathToMatch.matchElement(paramInt);
    Object localObject;
    if (localJsonPointer == null) {
      localObject = null;
    }
    for (;;)
    {
      return (TokenFilter)localObject;
      if (localJsonPointer.matches()) {
        localObject = TokenFilter.INCLUDE_ALL;
      } else {
        localObject = new JsonPointerBasedFilter(localJsonPointer);
      }
    }
  }
  
  public TokenFilter includeProperty(String paramString)
  {
    JsonPointer localJsonPointer = this._pathToMatch.matchProperty(paramString);
    Object localObject;
    if (localJsonPointer == null) {
      localObject = null;
    }
    for (;;)
    {
      return (TokenFilter)localObject;
      if (localJsonPointer.matches()) {
        localObject = TokenFilter.INCLUDE_ALL;
      } else {
        localObject = new JsonPointerBasedFilter(localJsonPointer);
      }
    }
  }
  
  public String toString()
  {
    return "[JsonPointerFilter at: " + this._pathToMatch + "]";
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/filter/JsonPointerBasedFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */