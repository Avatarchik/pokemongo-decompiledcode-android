package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import java.io.IOException;

public class MappingJsonFactory
  extends JsonFactory
{
  private static final long serialVersionUID = -6744103724013275513L;
  
  public MappingJsonFactory()
  {
    this(null);
  }
  
  public MappingJsonFactory(JsonFactory paramJsonFactory, ObjectMapper paramObjectMapper)
  {
    super(paramJsonFactory, paramObjectMapper);
    if (paramObjectMapper == null) {
      setCodec(new ObjectMapper(this));
    }
  }
  
  public MappingJsonFactory(ObjectMapper paramObjectMapper)
  {
    super(paramObjectMapper);
    if (paramObjectMapper == null) {
      setCodec(new ObjectMapper(this));
    }
  }
  
  public JsonFactory copy()
  {
    _checkInvalidCopy(MappingJsonFactory.class);
    return new MappingJsonFactory(this, null);
  }
  
  public final ObjectMapper getCodec()
  {
    return (ObjectMapper)this._objectCodec;
  }
  
  public String getFormatName()
  {
    return "JSON";
  }
  
  public MatchStrength hasFormat(InputAccessor paramInputAccessor)
    throws IOException
  {
    if (getClass() == MappingJsonFactory.class) {}
    for (MatchStrength localMatchStrength = hasJSONFormat(paramInputAccessor);; localMatchStrength = null) {
      return localMatchStrength;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/MappingJsonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */