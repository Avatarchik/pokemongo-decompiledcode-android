package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParserSequence
  extends JsonParserDelegate
{
  protected int _nextParser;
  protected final JsonParser[] _parsers;
  
  protected JsonParserSequence(JsonParser[] paramArrayOfJsonParser)
  {
    super(paramArrayOfJsonParser[0]);
    this._parsers = paramArrayOfJsonParser;
    this._nextParser = 1;
  }
  
  public static JsonParserSequence createFlattened(JsonParser paramJsonParser1, JsonParser paramJsonParser2)
  {
    JsonParserSequence localJsonParserSequence;
    if ((!(paramJsonParser1 instanceof JsonParserSequence)) && (!(paramJsonParser2 instanceof JsonParserSequence)))
    {
      JsonParser[] arrayOfJsonParser = new JsonParser[2];
      arrayOfJsonParser[0] = paramJsonParser1;
      arrayOfJsonParser[1] = paramJsonParser2;
      localJsonParserSequence = new JsonParserSequence(arrayOfJsonParser);
      return localJsonParserSequence;
    }
    ArrayList localArrayList = new ArrayList();
    if ((paramJsonParser1 instanceof JsonParserSequence))
    {
      ((JsonParserSequence)paramJsonParser1).addFlattenedActiveParsers(localArrayList);
      label67:
      if (!(paramJsonParser2 instanceof JsonParserSequence)) {
        break label117;
      }
      ((JsonParserSequence)paramJsonParser2).addFlattenedActiveParsers(localArrayList);
    }
    for (;;)
    {
      localJsonParserSequence = new JsonParserSequence((JsonParser[])localArrayList.toArray(new JsonParser[localArrayList.size()]));
      break;
      localArrayList.add(paramJsonParser1);
      break label67;
      label117:
      localArrayList.add(paramJsonParser2);
    }
  }
  
  protected void addFlattenedActiveParsers(List<JsonParser> paramList)
  {
    int i = -1 + this._nextParser;
    int j = this._parsers.length;
    if (i < j)
    {
      JsonParser localJsonParser = this._parsers[i];
      if ((localJsonParser instanceof JsonParserSequence)) {
        ((JsonParserSequence)localJsonParser).addFlattenedActiveParsers(paramList);
      }
      for (;;)
      {
        i++;
        break;
        paramList.add(localJsonParser);
      }
    }
  }
  
  public void close()
    throws IOException
  {
    do
    {
      this.delegate.close();
    } while (switchToNext());
  }
  
  public int containedParsersCount()
  {
    return this._parsers.length;
  }
  
  public JsonToken nextToken()
    throws IOException, JsonParseException
  {
    JsonToken localJsonToken1 = this.delegate.nextToken();
    Object localObject;
    if (localJsonToken1 != null) {
      localObject = localJsonToken1;
    }
    for (;;)
    {
      return (JsonToken)localObject;
      for (;;)
      {
        if (switchToNext())
        {
          JsonToken localJsonToken2 = this.delegate.nextToken();
          if (localJsonToken2 != null)
          {
            localObject = localJsonToken2;
            break;
          }
        }
      }
      localObject = null;
    }
  }
  
  protected boolean switchToNext()
  {
    if (this._nextParser >= this._parsers.length) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      JsonParser[] arrayOfJsonParser = this._parsers;
      int i = this._nextParser;
      this._nextParser = (i + 1);
      this.delegate = arrayOfJsonParser[i];
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/util/JsonParserSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */