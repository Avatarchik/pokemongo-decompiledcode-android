package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;

public class JsonWriteContext
  extends JsonStreamContext
{
  public static final int STATUS_EXPECT_NAME = 5;
  public static final int STATUS_EXPECT_VALUE = 4;
  public static final int STATUS_OK_AFTER_COLON = 2;
  public static final int STATUS_OK_AFTER_COMMA = 1;
  public static final int STATUS_OK_AFTER_SPACE = 3;
  public static final int STATUS_OK_AS_IS;
  protected JsonWriteContext _child = null;
  protected String _currentName;
  protected Object _currentValue;
  protected DupDetector _dups;
  protected boolean _gotName;
  protected final JsonWriteContext _parent;
  
  protected JsonWriteContext(int paramInt, JsonWriteContext paramJsonWriteContext, DupDetector paramDupDetector)
  {
    this._type = paramInt;
    this._parent = paramJsonWriteContext;
    this._dups = paramDupDetector;
    this._index = -1;
  }
  
  private final void _checkDup(DupDetector paramDupDetector, String paramString)
    throws JsonProcessingException
  {
    if (paramDupDetector.isDup(paramString)) {
      throw new JsonGenerationException("Duplicate field '" + paramString + "'");
    }
  }
  
  @Deprecated
  public static JsonWriteContext createRootContext()
  {
    return createRootContext(null);
  }
  
  public static JsonWriteContext createRootContext(DupDetector paramDupDetector)
  {
    return new JsonWriteContext(0, null, paramDupDetector);
  }
  
  protected void appendDesc(StringBuilder paramStringBuilder)
  {
    if (this._type == 2)
    {
      paramStringBuilder.append('{');
      if (this._currentName != null)
      {
        paramStringBuilder.append('"');
        paramStringBuilder.append(this._currentName);
        paramStringBuilder.append('"');
        paramStringBuilder.append('}');
      }
    }
    for (;;)
    {
      return;
      paramStringBuilder.append('?');
      break;
      if (this._type == 1)
      {
        paramStringBuilder.append('[');
        paramStringBuilder.append(getCurrentIndex());
        paramStringBuilder.append(']');
      }
      else
      {
        paramStringBuilder.append("/");
      }
    }
  }
  
  public JsonWriteContext createChildArrayContext()
  {
    JsonWriteContext localJsonWriteContext1 = this._child;
    DupDetector localDupDetector;
    JsonWriteContext localJsonWriteContext3;
    if (localJsonWriteContext1 == null) {
      if (this._dups == null)
      {
        localDupDetector = null;
        localJsonWriteContext3 = new JsonWriteContext(1, this, localDupDetector);
        this._child = localJsonWriteContext3;
      }
    }
    for (JsonWriteContext localJsonWriteContext2 = localJsonWriteContext3;; localJsonWriteContext2 = localJsonWriteContext1.reset(1))
    {
      return localJsonWriteContext2;
      localDupDetector = this._dups.child();
      break;
    }
  }
  
  public JsonWriteContext createChildObjectContext()
  {
    JsonWriteContext localJsonWriteContext1 = this._child;
    DupDetector localDupDetector;
    JsonWriteContext localJsonWriteContext3;
    if (localJsonWriteContext1 == null) {
      if (this._dups == null)
      {
        localDupDetector = null;
        localJsonWriteContext3 = new JsonWriteContext(2, this, localDupDetector);
        this._child = localJsonWriteContext3;
      }
    }
    for (JsonWriteContext localJsonWriteContext2 = localJsonWriteContext3;; localJsonWriteContext2 = localJsonWriteContext1.reset(2))
    {
      return localJsonWriteContext2;
      localDupDetector = this._dups.child();
      break;
    }
  }
  
  public final String getCurrentName()
  {
    return this._currentName;
  }
  
  public Object getCurrentValue()
  {
    return this._currentValue;
  }
  
  public DupDetector getDupDetector()
  {
    return this._dups;
  }
  
  public final JsonWriteContext getParent()
  {
    return this._parent;
  }
  
  protected JsonWriteContext reset(int paramInt)
  {
    this._type = paramInt;
    this._index = -1;
    this._currentName = null;
    this._gotName = false;
    this._currentValue = null;
    if (this._dups != null) {
      this._dups.reset();
    }
    return this;
  }
  
  public void setCurrentValue(Object paramObject)
  {
    this._currentValue = paramObject;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendDesc(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public JsonWriteContext withDupDetector(DupDetector paramDupDetector)
  {
    this._dups = paramDupDetector;
    return this;
  }
  
  public int writeFieldName(String paramString)
    throws JsonProcessingException
  {
    int i = 1;
    if (this._gotName) {
      i = 4;
    }
    for (;;)
    {
      return i;
      this._gotName = i;
      this._currentName = paramString;
      if (this._dups != null) {
        _checkDup(this._dups, paramString);
      }
      if (this._index < 0) {
        i = 0;
      }
    }
  }
  
  public int writeValue()
  {
    int i = 0;
    if (this._type == 2) {
      if (!this._gotName) {
        i = 5;
      }
    }
    for (;;)
    {
      return i;
      this._gotName = false;
      this._index = (1 + this._index);
      i = 2;
      continue;
      if (this._type == 1)
      {
        int j = this._index;
        this._index = (1 + this._index);
        if (j >= 0) {
          i = 1;
        }
      }
      else
      {
        this._index = (1 + this._index);
        if (this._index != 0) {
          i = 3;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/json/JsonWriteContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */