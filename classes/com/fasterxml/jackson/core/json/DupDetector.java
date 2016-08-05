package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import java.util.HashSet;

public class DupDetector
{
  protected String _firstName;
  protected String _secondName;
  protected HashSet<String> _seen;
  protected final Object _source;
  
  private DupDetector(Object paramObject)
  {
    this._source = paramObject;
  }
  
  public static DupDetector rootDetector(JsonGenerator paramJsonGenerator)
  {
    return new DupDetector(paramJsonGenerator);
  }
  
  public static DupDetector rootDetector(JsonParser paramJsonParser)
  {
    return new DupDetector(paramJsonParser);
  }
  
  public DupDetector child()
  {
    return new DupDetector(this._source);
  }
  
  public JsonLocation findLocation()
  {
    if ((this._source instanceof JsonParser)) {}
    for (JsonLocation localJsonLocation = ((JsonParser)this._source).getCurrentLocation();; localJsonLocation = null) {
      return localJsonLocation;
    }
  }
  
  public boolean isDup(String paramString)
    throws JsonParseException
  {
    boolean bool = true;
    if (this._firstName == null)
    {
      this._firstName = paramString;
      bool = false;
    }
    for (;;)
    {
      return bool;
      if (!paramString.equals(this._firstName)) {
        if (this._secondName == null)
        {
          this._secondName = paramString;
          bool = false;
        }
        else if (!paramString.equals(this._secondName))
        {
          if (this._seen == null)
          {
            this._seen = new HashSet(16);
            this._seen.add(this._firstName);
            this._seen.add(this._secondName);
          }
          if (this._seen.add(paramString)) {
            bool = false;
          }
        }
      }
    }
  }
  
  public void reset()
  {
    this._firstName = null;
    this._secondName = null;
    this._seen = null;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/json/DupDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */