package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;

public class TokenFilterContext
  extends JsonStreamContext
{
  protected TokenFilterContext _child = null;
  protected String _currentName;
  protected TokenFilter _filter;
  protected boolean _needToHandleName;
  protected final TokenFilterContext _parent;
  protected boolean _startHandled;
  
  protected TokenFilterContext(int paramInt, TokenFilterContext paramTokenFilterContext, TokenFilter paramTokenFilter, boolean paramBoolean)
  {
    this._type = paramInt;
    this._parent = paramTokenFilterContext;
    this._filter = paramTokenFilter;
    this._index = -1;
    this._startHandled = paramBoolean;
    this._needToHandleName = false;
  }
  
  private void _writePath(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._filter == null) || (this._filter == TokenFilter.INCLUDE_ALL)) {}
    for (;;)
    {
      return;
      if (this._parent != null) {
        this._parent._writePath(paramJsonGenerator);
      }
      if (this._startHandled)
      {
        if (this._needToHandleName)
        {
          this._needToHandleName = false;
          paramJsonGenerator.writeFieldName(this._currentName);
        }
      }
      else
      {
        this._startHandled = true;
        if (this._type == 2)
        {
          paramJsonGenerator.writeStartObject();
          if (this._needToHandleName)
          {
            this._needToHandleName = false;
            paramJsonGenerator.writeFieldName(this._currentName);
          }
        }
        else if (this._type == 1)
        {
          paramJsonGenerator.writeStartArray();
        }
      }
    }
  }
  
  public static TokenFilterContext createRootContext(TokenFilter paramTokenFilter)
  {
    return new TokenFilterContext(0, null, paramTokenFilter, true);
  }
  
  protected void appendDesc(StringBuilder paramStringBuilder)
  {
    if (this._parent != null) {
      this._parent.appendDesc(paramStringBuilder);
    }
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
  
  public TokenFilter checkValue(TokenFilter paramTokenFilter)
  {
    if (this._type == 2) {}
    for (;;)
    {
      return paramTokenFilter;
      int i = 1 + this._index;
      this._index = i;
      if (this._type == 1) {
        paramTokenFilter = paramTokenFilter.includeElement(i);
      } else {
        paramTokenFilter = paramTokenFilter.includeRootValue(i);
      }
    }
  }
  
  public TokenFilterContext closeArray(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (this._startHandled) {
      paramJsonGenerator.writeEndArray();
    }
    if ((this._filter != null) && (this._filter != TokenFilter.INCLUDE_ALL)) {
      this._filter.filterFinishArray();
    }
    return this._parent;
  }
  
  public TokenFilterContext closeObject(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (this._startHandled) {
      paramJsonGenerator.writeEndObject();
    }
    if ((this._filter != null) && (this._filter != TokenFilter.INCLUDE_ALL)) {
      this._filter.filterFinishObject();
    }
    return this._parent;
  }
  
  public TokenFilterContext createChildArrayContext(TokenFilter paramTokenFilter, boolean paramBoolean)
  {
    TokenFilterContext localTokenFilterContext1 = this._child;
    TokenFilterContext localTokenFilterContext2;
    if (localTokenFilterContext1 == null)
    {
      localTokenFilterContext2 = new TokenFilterContext(1, this, paramTokenFilter, paramBoolean);
      this._child = localTokenFilterContext2;
    }
    for (TokenFilterContext localTokenFilterContext3 = localTokenFilterContext2;; localTokenFilterContext3 = localTokenFilterContext1.reset(1, paramTokenFilter, paramBoolean)) {
      return localTokenFilterContext3;
    }
  }
  
  public TokenFilterContext createChildObjectContext(TokenFilter paramTokenFilter, boolean paramBoolean)
  {
    TokenFilterContext localTokenFilterContext1 = this._child;
    TokenFilterContext localTokenFilterContext2;
    if (localTokenFilterContext1 == null)
    {
      localTokenFilterContext2 = new TokenFilterContext(2, this, paramTokenFilter, paramBoolean);
      this._child = localTokenFilterContext2;
    }
    for (TokenFilterContext localTokenFilterContext3 = localTokenFilterContext2;; localTokenFilterContext3 = localTokenFilterContext1.reset(2, paramTokenFilter, paramBoolean)) {
      return localTokenFilterContext3;
    }
  }
  
  public TokenFilterContext findChildOf(TokenFilterContext paramTokenFilterContext)
  {
    if (this._parent == paramTokenFilterContext) {}
    for (;;)
    {
      return this;
      TokenFilterContext localTokenFilterContext;
      for (Object localObject = this._parent;; localObject = localTokenFilterContext)
      {
        if (localObject == null) {
          break label39;
        }
        localTokenFilterContext = ((TokenFilterContext)localObject)._parent;
        if (localTokenFilterContext == paramTokenFilterContext)
        {
          this = (TokenFilterContext)localObject;
          break;
        }
      }
      label39:
      this = null;
    }
  }
  
  public final String getCurrentName()
  {
    return this._currentName;
  }
  
  public Object getCurrentValue()
  {
    return null;
  }
  
  public TokenFilter getFilter()
  {
    return this._filter;
  }
  
  public final TokenFilterContext getParent()
  {
    return this._parent;
  }
  
  public boolean isStartHandled()
  {
    return this._startHandled;
  }
  
  public JsonToken nextTokenToRead()
  {
    JsonToken localJsonToken;
    if (!this._startHandled)
    {
      this._startHandled = true;
      if (this._type == 2) {
        localJsonToken = JsonToken.START_OBJECT;
      }
    }
    for (;;)
    {
      return localJsonToken;
      localJsonToken = JsonToken.START_ARRAY;
      continue;
      if ((this._needToHandleName) && (this._type == 2))
      {
        this._needToHandleName = false;
        localJsonToken = JsonToken.FIELD_NAME;
      }
      else
      {
        localJsonToken = null;
      }
    }
  }
  
  protected TokenFilterContext reset(int paramInt, TokenFilter paramTokenFilter, boolean paramBoolean)
  {
    this._type = paramInt;
    this._filter = paramTokenFilter;
    this._index = -1;
    this._currentName = null;
    this._startHandled = paramBoolean;
    this._needToHandleName = false;
    return this;
  }
  
  public void setCurrentValue(Object paramObject) {}
  
  public TokenFilter setFieldName(String paramString)
    throws JsonProcessingException
  {
    this._currentName = paramString;
    this._needToHandleName = true;
    return this._filter;
  }
  
  public void skipParentChecks()
  {
    this._filter = null;
    for (TokenFilterContext localTokenFilterContext = this._parent; localTokenFilterContext != null; localTokenFilterContext = localTokenFilterContext._parent) {
      this._parent._filter = null;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(64);
    appendDesc(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  public void writeImmediatePath(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._filter == null) || (this._filter == TokenFilter.INCLUDE_ALL)) {}
    for (;;)
    {
      return;
      if (this._startHandled)
      {
        if (this._needToHandleName) {
          paramJsonGenerator.writeFieldName(this._currentName);
        }
      }
      else
      {
        this._startHandled = true;
        if (this._type == 2)
        {
          paramJsonGenerator.writeStartObject();
          if (this._needToHandleName) {
            paramJsonGenerator.writeFieldName(this._currentName);
          }
        }
        else if (this._type == 1)
        {
          paramJsonGenerator.writeStartArray();
        }
      }
    }
  }
  
  public void writePath(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._filter == null) || (this._filter == TokenFilter.INCLUDE_ALL)) {}
    for (;;)
    {
      return;
      if (this._parent != null) {
        this._parent._writePath(paramJsonGenerator);
      }
      if (this._startHandled)
      {
        if (this._needToHandleName) {
          paramJsonGenerator.writeFieldName(this._currentName);
        }
      }
      else
      {
        this._startHandled = true;
        if (this._type == 2)
        {
          paramJsonGenerator.writeStartObject();
          paramJsonGenerator.writeFieldName(this._currentName);
        }
        else if (this._type == 1)
        {
          paramJsonGenerator.writeStartArray();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/filter/TokenFilterContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */