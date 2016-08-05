package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringParserDelegate
  extends JsonParserDelegate
{
  protected boolean _allowMultipleMatches;
  protected JsonToken _currToken;
  protected TokenFilterContext _exposedContext;
  protected TokenFilterContext _headContext;
  @Deprecated
  protected boolean _includeImmediateParent = false;
  protected boolean _includePath;
  protected TokenFilter _itemFilter;
  protected JsonToken _lastClearedToken;
  protected int _matchCount;
  protected TokenFilter rootFilter;
  
  public FilteringParserDelegate(JsonParser paramJsonParser, TokenFilter paramTokenFilter, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramJsonParser);
    this.rootFilter = paramTokenFilter;
    this._itemFilter = paramTokenFilter;
    this._headContext = TokenFilterContext.createRootContext(paramTokenFilter);
    this._includePath = paramBoolean1;
    this._allowMultipleMatches = paramBoolean2;
  }
  
  private JsonToken _nextBuffered(TokenFilterContext paramTokenFilterContext)
    throws IOException
  {
    this._exposedContext = paramTokenFilterContext;
    TokenFilterContext localTokenFilterContext = paramTokenFilterContext;
    JsonToken localJsonToken1 = localTokenFilterContext.nextTokenToRead();
    if (localJsonToken1 != null) {}
    JsonToken localJsonToken2;
    for (Object localObject = localJsonToken1;; localObject = localJsonToken2)
    {
      return (JsonToken)localObject;
      do
      {
        if (localTokenFilterContext == this._headContext) {
          throw _constructError("Internal error: failed to locate expected buffered tokens");
        }
        localTokenFilterContext = this._exposedContext.findChildOf(localTokenFilterContext);
        this._exposedContext = localTokenFilterContext;
        if (localTokenFilterContext == null) {
          throw _constructError("Unexpected problem: chain of filtered context broken");
        }
        localJsonToken2 = this._exposedContext.nextTokenToRead();
      } while (localJsonToken2 == null);
    }
  }
  
  protected JsonStreamContext _filterContext()
  {
    if (this._exposedContext != null) {}
    for (TokenFilterContext localTokenFilterContext = this._exposedContext;; localTokenFilterContext = this._headContext) {
      return localTokenFilterContext;
    }
  }
  
  protected final JsonToken _nextToken2()
    throws IOException
  {
    JsonToken localJsonToken1 = this.delegate.nextToken();
    Object localObject;
    if (localJsonToken1 == null)
    {
      this._currToken = localJsonToken1;
      localObject = localJsonToken1;
    }
    for (;;)
    {
      return (JsonToken)localObject;
      TokenFilter localTokenFilter8;
      switch (localJsonToken1.id())
      {
      default: 
        localTokenFilter8 = this._itemFilter;
        if (localTokenFilter8 == TokenFilter.INCLUDE_ALL)
        {
          this._currToken = localJsonToken1;
          localObject = localJsonToken1;
        }
        break;
      case 3: 
        TokenFilter localTokenFilter6 = this._itemFilter;
        if (localTokenFilter6 == TokenFilter.INCLUDE_ALL)
        {
          this._headContext = this._headContext.createChildArrayContext(localTokenFilter6, true);
          this._currToken = localJsonToken1;
          localObject = localJsonToken1;
        }
        else
        {
          if (localTokenFilter6 == null)
          {
            this.delegate.skipChildren();
            break;
          }
          TokenFilter localTokenFilter7 = this._headContext.checkValue(localTokenFilter6);
          if (localTokenFilter7 == null)
          {
            this.delegate.skipChildren();
            break;
          }
          if (localTokenFilter7 != TokenFilter.INCLUDE_ALL) {
            localTokenFilter7 = localTokenFilter7.filterStartArray();
          }
          this._itemFilter = localTokenFilter7;
          if (localTokenFilter7 == TokenFilter.INCLUDE_ALL)
          {
            this._headContext = this._headContext.createChildArrayContext(localTokenFilter7, true);
            this._currToken = localJsonToken1;
            localObject = localJsonToken1;
          }
          else
          {
            this._headContext = this._headContext.createChildArrayContext(localTokenFilter7, false);
            if (!this._includePath) {
              break;
            }
            JsonToken localJsonToken4 = _nextTokenWithBuffering(this._headContext);
            if (localJsonToken4 == null) {
              break;
            }
            this._currToken = localJsonToken4;
            localObject = localJsonToken4;
          }
        }
        break;
      case 1: 
        TokenFilter localTokenFilter4 = this._itemFilter;
        if (localTokenFilter4 == TokenFilter.INCLUDE_ALL)
        {
          this._headContext = this._headContext.createChildObjectContext(localTokenFilter4, true);
          this._currToken = localJsonToken1;
          localObject = localJsonToken1;
        }
        else
        {
          if (localTokenFilter4 == null)
          {
            this.delegate.skipChildren();
            break;
          }
          TokenFilter localTokenFilter5 = this._headContext.checkValue(localTokenFilter4);
          if (localTokenFilter5 == null)
          {
            this.delegate.skipChildren();
            break;
          }
          if (localTokenFilter5 != TokenFilter.INCLUDE_ALL) {
            localTokenFilter5 = localTokenFilter5.filterStartObject();
          }
          this._itemFilter = localTokenFilter5;
          if (localTokenFilter5 == TokenFilter.INCLUDE_ALL)
          {
            this._headContext = this._headContext.createChildObjectContext(localTokenFilter5, true);
            this._currToken = localJsonToken1;
            localObject = localJsonToken1;
          }
          else
          {
            this._headContext = this._headContext.createChildObjectContext(localTokenFilter5, false);
            if (!this._includePath) {
              break;
            }
            JsonToken localJsonToken3 = _nextTokenWithBuffering(this._headContext);
            if (localJsonToken3 == null) {
              break;
            }
            this._currToken = localJsonToken3;
            localObject = localJsonToken3;
          }
        }
        break;
      case 2: 
      case 4: 
        boolean bool = this._headContext.isStartHandled();
        TokenFilter localTokenFilter3 = this._headContext.getFilter();
        if ((localTokenFilter3 != null) && (localTokenFilter3 != TokenFilter.INCLUDE_ALL)) {
          localTokenFilter3.filterFinishArray();
        }
        this._headContext = this._headContext.getParent();
        this._itemFilter = this._headContext.getFilter();
        if (!bool) {
          break;
        }
        this._currToken = localJsonToken1;
        localObject = localJsonToken1;
        break;
      case 5: 
        String str = this.delegate.getCurrentName();
        TokenFilter localTokenFilter1 = this._headContext.setFieldName(str);
        if (localTokenFilter1 == TokenFilter.INCLUDE_ALL)
        {
          this._itemFilter = localTokenFilter1;
          this._currToken = localJsonToken1;
          localObject = localJsonToken1;
        }
        else
        {
          if (localTokenFilter1 == null)
          {
            this.delegate.nextToken();
            this.delegate.skipChildren();
            break;
          }
          TokenFilter localTokenFilter2 = localTokenFilter1.includeProperty(str);
          if (localTokenFilter2 == null)
          {
            this.delegate.nextToken();
            this.delegate.skipChildren();
            break;
          }
          this._itemFilter = localTokenFilter2;
          if (localTokenFilter2 == TokenFilter.INCLUDE_ALL)
          {
            if (!this._includePath) {
              break;
            }
            this._currToken = localJsonToken1;
            localObject = localJsonToken1;
            continue;
          }
          if (!this._includePath) {
            break;
          }
          JsonToken localJsonToken2 = _nextTokenWithBuffering(this._headContext);
          if (localJsonToken2 == null) {
            break;
          }
          this._currToken = localJsonToken2;
          localObject = localJsonToken2;
          continue;
          if (localTokenFilter8 == null) {
            break;
          }
          TokenFilter localTokenFilter9 = this._headContext.checkValue(localTokenFilter8);
          if ((localTokenFilter9 != TokenFilter.INCLUDE_ALL) && ((localTokenFilter9 == null) || (!localTokenFilter9.includeValue(this.delegate)))) {
            break;
          }
          this._currToken = localJsonToken1;
          localObject = localJsonToken1;
        }
        break;
      }
    }
  }
  
  protected final JsonToken _nextTokenWithBuffering(TokenFilterContext paramTokenFilterContext)
    throws IOException
  {
    JsonToken localJsonToken = this.delegate.nextToken();
    if (localJsonToken == null) {}
    for (;;)
    {
      label12:
      return localJsonToken;
      TokenFilter localTokenFilter7;
      switch (localJsonToken.id())
      {
      default: 
        localTokenFilter7 = this._itemFilter;
        if (localTokenFilter7 == TokenFilter.INCLUDE_ALL) {
          localJsonToken = _nextBuffered(paramTokenFilterContext);
        }
        break;
      case 3: 
        TokenFilter localTokenFilter6 = this._headContext.checkValue(this._itemFilter);
        if (localTokenFilter6 == null)
        {
          this.delegate.skipChildren();
          break;
        }
        if (localTokenFilter6 != TokenFilter.INCLUDE_ALL) {
          localTokenFilter6 = localTokenFilter6.filterStartArray();
        }
        this._itemFilter = localTokenFilter6;
        if (localTokenFilter6 == TokenFilter.INCLUDE_ALL)
        {
          this._headContext = this._headContext.createChildArrayContext(localTokenFilter6, true);
          localJsonToken = _nextBuffered(paramTokenFilterContext);
        }
        else
        {
          this._headContext = this._headContext.createChildArrayContext(localTokenFilter6, false);
        }
        break;
      case 1: 
        TokenFilter localTokenFilter4 = this._itemFilter;
        if (localTokenFilter4 == TokenFilter.INCLUDE_ALL)
        {
          this._headContext = this._headContext.createChildObjectContext(localTokenFilter4, true);
        }
        else
        {
          if (localTokenFilter4 == null)
          {
            this.delegate.skipChildren();
            break;
          }
          TokenFilter localTokenFilter5 = this._headContext.checkValue(localTokenFilter4);
          if (localTokenFilter5 == null)
          {
            this.delegate.skipChildren();
            break;
          }
          if (localTokenFilter5 != TokenFilter.INCLUDE_ALL) {
            localTokenFilter5 = localTokenFilter5.filterStartObject();
          }
          this._itemFilter = localTokenFilter5;
          if (localTokenFilter5 == TokenFilter.INCLUDE_ALL)
          {
            this._headContext = this._headContext.createChildObjectContext(localTokenFilter5, true);
            localJsonToken = _nextBuffered(paramTokenFilterContext);
          }
          else
          {
            this._headContext = this._headContext.createChildObjectContext(localTokenFilter5, false);
          }
        }
        break;
      case 2: 
      case 4: 
        TokenFilter localTokenFilter3 = this._headContext.getFilter();
        if ((localTokenFilter3 != null) && (localTokenFilter3 != TokenFilter.INCLUDE_ALL)) {
          localTokenFilter3.filterFinishArray();
        }
        int i;
        if (this._headContext == paramTokenFilterContext)
        {
          i = 1;
          if ((i == 0) || (!this._headContext.isStartHandled())) {
            break label423;
          }
        }
        for (int j = 1;; j = 0)
        {
          this._headContext = this._headContext.getParent();
          this._itemFilter = this._headContext.getFilter();
          if (j != 0) {
            break label12;
          }
          if ((i == 0) && (this._headContext != paramTokenFilterContext)) {
            break;
          }
          localJsonToken = null;
          break label12;
          i = 0;
          break label354;
        }
      case 5: 
        label354:
        label423:
        String str = this.delegate.getCurrentName();
        TokenFilter localTokenFilter1 = this._headContext.setFieldName(str);
        if (localTokenFilter1 == TokenFilter.INCLUDE_ALL)
        {
          this._itemFilter = localTokenFilter1;
          localJsonToken = _nextBuffered(paramTokenFilterContext);
        }
        else
        {
          if (localTokenFilter1 == null)
          {
            this.delegate.nextToken();
            this.delegate.skipChildren();
            break;
          }
          TokenFilter localTokenFilter2 = localTokenFilter1.includeProperty(str);
          if (localTokenFilter2 == null)
          {
            this.delegate.nextToken();
            this.delegate.skipChildren();
            break;
          }
          this._itemFilter = localTokenFilter2;
          if (localTokenFilter2 != TokenFilter.INCLUDE_ALL) {
            break;
          }
          localJsonToken = _nextBuffered(paramTokenFilterContext);
          continue;
          if (localTokenFilter7 == null) {
            break;
          }
          TokenFilter localTokenFilter8 = this._headContext.checkValue(localTokenFilter7);
          if ((localTokenFilter8 != TokenFilter.INCLUDE_ALL) && ((localTokenFilter8 == null) || (!localTokenFilter8.includeValue(this.delegate)))) {
            break;
          }
          localJsonToken = _nextBuffered(paramTokenFilterContext);
        }
        break;
      }
    }
  }
  
  public void clearCurrentToken()
  {
    if (this._currToken != null)
    {
      this._lastClearedToken = this._currToken;
      this._currToken = null;
    }
  }
  
  public BigInteger getBigIntegerValue()
    throws IOException
  {
    return this.delegate.getBigIntegerValue();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException
  {
    return this.delegate.getBinaryValue(paramBase64Variant);
  }
  
  public boolean getBooleanValue()
    throws IOException
  {
    return this.delegate.getBooleanValue();
  }
  
  public byte getByteValue()
    throws IOException
  {
    return this.delegate.getByteValue();
  }
  
  public JsonLocation getCurrentLocation()
  {
    return this.delegate.getCurrentLocation();
  }
  
  public String getCurrentName()
    throws IOException
  {
    JsonStreamContext localJsonStreamContext1 = _filterContext();
    JsonStreamContext localJsonStreamContext2;
    String str;
    if ((this._currToken == JsonToken.START_OBJECT) || (this._currToken == JsonToken.START_ARRAY))
    {
      localJsonStreamContext2 = localJsonStreamContext1.getParent();
      if (localJsonStreamContext2 == null) {
        str = null;
      }
    }
    for (;;)
    {
      return str;
      str = localJsonStreamContext2.getCurrentName();
      continue;
      str = localJsonStreamContext1.getCurrentName();
    }
  }
  
  public JsonToken getCurrentToken()
  {
    return this._currToken;
  }
  
  public final int getCurrentTokenId()
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == null) {}
    for (int i = 0;; i = localJsonToken.id()) {
      return i;
    }
  }
  
  public BigDecimal getDecimalValue()
    throws IOException
  {
    return this.delegate.getDecimalValue();
  }
  
  public double getDoubleValue()
    throws IOException
  {
    return this.delegate.getDoubleValue();
  }
  
  public Object getEmbeddedObject()
    throws IOException
  {
    return this.delegate.getEmbeddedObject();
  }
  
  public TokenFilter getFilter()
  {
    return this.rootFilter;
  }
  
  public float getFloatValue()
    throws IOException
  {
    return this.delegate.getFloatValue();
  }
  
  public int getIntValue()
    throws IOException
  {
    return this.delegate.getIntValue();
  }
  
  public JsonToken getLastClearedToken()
  {
    return this._lastClearedToken;
  }
  
  public long getLongValue()
    throws IOException
  {
    return this.delegate.getLongValue();
  }
  
  public int getMatchCount()
  {
    return this._matchCount;
  }
  
  public JsonParser.NumberType getNumberType()
    throws IOException
  {
    return this.delegate.getNumberType();
  }
  
  public Number getNumberValue()
    throws IOException
  {
    return this.delegate.getNumberValue();
  }
  
  public JsonStreamContext getParsingContext()
  {
    return _filterContext();
  }
  
  public short getShortValue()
    throws IOException
  {
    return this.delegate.getShortValue();
  }
  
  public String getText()
    throws IOException
  {
    return this.delegate.getText();
  }
  
  public char[] getTextCharacters()
    throws IOException
  {
    return this.delegate.getTextCharacters();
  }
  
  public int getTextLength()
    throws IOException
  {
    return this.delegate.getTextLength();
  }
  
  public int getTextOffset()
    throws IOException
  {
    return this.delegate.getTextOffset();
  }
  
  public JsonLocation getTokenLocation()
  {
    return this.delegate.getTokenLocation();
  }
  
  public boolean getValueAsBoolean()
    throws IOException
  {
    return this.delegate.getValueAsBoolean();
  }
  
  public boolean getValueAsBoolean(boolean paramBoolean)
    throws IOException
  {
    return this.delegate.getValueAsBoolean(paramBoolean);
  }
  
  public double getValueAsDouble()
    throws IOException
  {
    return this.delegate.getValueAsDouble();
  }
  
  public double getValueAsDouble(double paramDouble)
    throws IOException
  {
    return this.delegate.getValueAsDouble(paramDouble);
  }
  
  public int getValueAsInt()
    throws IOException
  {
    return this.delegate.getValueAsInt();
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException
  {
    return this.delegate.getValueAsInt(paramInt);
  }
  
  public long getValueAsLong()
    throws IOException
  {
    return this.delegate.getValueAsLong();
  }
  
  public long getValueAsLong(long paramLong)
    throws IOException
  {
    return this.delegate.getValueAsLong(paramLong);
  }
  
  public String getValueAsString()
    throws IOException
  {
    return this.delegate.getValueAsString();
  }
  
  public String getValueAsString(String paramString)
    throws IOException
  {
    return this.delegate.getValueAsString(paramString);
  }
  
  public boolean hasCurrentToken()
  {
    if (this._currToken != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasTextCharacters()
  {
    return this.delegate.hasTextCharacters();
  }
  
  public final boolean hasToken(JsonToken paramJsonToken)
  {
    if (this._currToken == paramJsonToken) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasTokenId(int paramInt)
  {
    boolean bool = true;
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == null) {
      if (paramInt != 0) {}
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      if (localJsonToken.id() != paramInt) {
        bool = false;
      }
    }
  }
  
  public boolean isExpectedStartArrayToken()
  {
    if (this._currToken == JsonToken.START_ARRAY) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isExpectedStartObjectToken()
  {
    if (this._currToken == JsonToken.START_OBJECT) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public JsonToken nextToken()
    throws IOException
  {
    TokenFilterContext localTokenFilterContext = this._exposedContext;
    Object localObject;
    if (localTokenFilterContext != null)
    {
      JsonToken localJsonToken5 = localTokenFilterContext.nextTokenToRead();
      if (localJsonToken5 != null)
      {
        this._currToken = localJsonToken5;
        localObject = localJsonToken5;
      }
    }
    for (;;)
    {
      return (JsonToken)localObject;
      if (localTokenFilterContext == this._headContext)
      {
        this._exposedContext = null;
        if (localTokenFilterContext.inArray())
        {
          JsonToken localJsonToken6 = this.delegate.getCurrentToken();
          this._currToken = localJsonToken6;
          localObject = localJsonToken6;
        }
      }
      else
      {
        localTokenFilterContext = this._headContext.findChildOf(localTokenFilterContext);
        this._exposedContext = localTokenFilterContext;
        if (localTokenFilterContext != null) {
          break;
        }
        throw _constructError("Unexpected problem: chain of filtered context broken");
      }
      JsonToken localJsonToken1 = this.delegate.nextToken();
      if (localJsonToken1 == null)
      {
        this._currToken = localJsonToken1;
        localObject = localJsonToken1;
      }
      else
      {
        TokenFilter localTokenFilter8;
        TokenFilter localTokenFilter6;
        switch (localJsonToken1.id())
        {
        default: 
          localTokenFilter8 = this._itemFilter;
          if (localTokenFilter8 == TokenFilter.INCLUDE_ALL)
          {
            this._currToken = localJsonToken1;
            localObject = localJsonToken1;
          }
          break;
        case 3: 
          localTokenFilter6 = this._itemFilter;
          if (localTokenFilter6 == TokenFilter.INCLUDE_ALL)
          {
            this._headContext = this._headContext.createChildArrayContext(localTokenFilter6, true);
            this._currToken = localJsonToken1;
            localObject = localJsonToken1;
          }
          else if (localTokenFilter6 == null)
          {
            this.delegate.skipChildren();
          }
          break;
        case 1: 
        case 2: 
        case 4: 
        case 5: 
          label774:
          TokenFilter localTokenFilter9;
          do
          {
            do
            {
              JsonToken localJsonToken2;
              do
              {
                do
                {
                  TokenFilter localTokenFilter2;
                  for (;;)
                  {
                    localObject = _nextToken2();
                    break;
                    TokenFilter localTokenFilter7 = this._headContext.checkValue(localTokenFilter6);
                    if (localTokenFilter7 == null)
                    {
                      this.delegate.skipChildren();
                    }
                    else
                    {
                      if (localTokenFilter7 != TokenFilter.INCLUDE_ALL) {
                        localTokenFilter7 = localTokenFilter7.filterStartArray();
                      }
                      this._itemFilter = localTokenFilter7;
                      if (localTokenFilter7 == TokenFilter.INCLUDE_ALL)
                      {
                        this._headContext = this._headContext.createChildArrayContext(localTokenFilter7, true);
                        this._currToken = localJsonToken1;
                        localObject = localJsonToken1;
                        break;
                      }
                      this._headContext = this._headContext.createChildArrayContext(localTokenFilter7, false);
                      if (this._includePath)
                      {
                        JsonToken localJsonToken4 = _nextTokenWithBuffering(this._headContext);
                        if (localJsonToken4 != null)
                        {
                          this._currToken = localJsonToken4;
                          localObject = localJsonToken4;
                          break;
                          TokenFilter localTokenFilter4 = this._itemFilter;
                          if (localTokenFilter4 == TokenFilter.INCLUDE_ALL)
                          {
                            this._headContext = this._headContext.createChildObjectContext(localTokenFilter4, true);
                            this._currToken = localJsonToken1;
                            localObject = localJsonToken1;
                            break;
                          }
                          if (localTokenFilter4 == null)
                          {
                            this.delegate.skipChildren();
                          }
                          else
                          {
                            TokenFilter localTokenFilter5 = this._headContext.checkValue(localTokenFilter4);
                            if (localTokenFilter5 == null)
                            {
                              this.delegate.skipChildren();
                            }
                            else
                            {
                              if (localTokenFilter5 != TokenFilter.INCLUDE_ALL) {
                                localTokenFilter5 = localTokenFilter5.filterStartObject();
                              }
                              this._itemFilter = localTokenFilter5;
                              if (localTokenFilter5 == TokenFilter.INCLUDE_ALL)
                              {
                                this._headContext = this._headContext.createChildObjectContext(localTokenFilter5, true);
                                this._currToken = localJsonToken1;
                                localObject = localJsonToken1;
                                break;
                              }
                              this._headContext = this._headContext.createChildObjectContext(localTokenFilter5, false);
                              if (this._includePath)
                              {
                                JsonToken localJsonToken3 = _nextTokenWithBuffering(this._headContext);
                                if (localJsonToken3 != null)
                                {
                                  this._currToken = localJsonToken3;
                                  localObject = localJsonToken3;
                                  break;
                                  boolean bool = this._headContext.isStartHandled();
                                  TokenFilter localTokenFilter3 = this._headContext.getFilter();
                                  if ((localTokenFilter3 != null) && (localTokenFilter3 != TokenFilter.INCLUDE_ALL)) {
                                    localTokenFilter3.filterFinishArray();
                                  }
                                  this._headContext = this._headContext.getParent();
                                  this._itemFilter = this._headContext.getFilter();
                                  if (bool)
                                  {
                                    this._currToken = localJsonToken1;
                                    localObject = localJsonToken1;
                                    break;
                                    String str = this.delegate.getCurrentName();
                                    TokenFilter localTokenFilter1 = this._headContext.setFieldName(str);
                                    if (localTokenFilter1 == TokenFilter.INCLUDE_ALL)
                                    {
                                      this._itemFilter = localTokenFilter1;
                                      if ((!this._includePath) && (this._includeImmediateParent) && (!this._headContext.isStartHandled()))
                                      {
                                        localJsonToken1 = this._headContext.nextTokenToRead();
                                        this._exposedContext = this._headContext;
                                      }
                                      this._currToken = localJsonToken1;
                                      localObject = localJsonToken1;
                                      break;
                                    }
                                    if (localTokenFilter1 == null)
                                    {
                                      this.delegate.nextToken();
                                      this.delegate.skipChildren();
                                    }
                                    else
                                    {
                                      localTokenFilter2 = localTokenFilter1.includeProperty(str);
                                      if (localTokenFilter2 != null) {
                                        break label774;
                                      }
                                      this.delegate.nextToken();
                                      this.delegate.skipChildren();
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                  this._itemFilter = localTokenFilter2;
                  if ((localTokenFilter2 == TokenFilter.INCLUDE_ALL) && (this._includePath))
                  {
                    this._currToken = localJsonToken1;
                    localObject = localJsonToken1;
                    break;
                  }
                } while (!this._includePath);
                localJsonToken2 = _nextTokenWithBuffering(this._headContext);
              } while (localJsonToken2 == null);
              this._currToken = localJsonToken2;
              localObject = localJsonToken2;
              break;
            } while (localTokenFilter8 == null);
            localTokenFilter9 = this._headContext.checkValue(localTokenFilter8);
          } while ((localTokenFilter9 != TokenFilter.INCLUDE_ALL) && ((localTokenFilter9 == null) || (!localTokenFilter9.includeValue(this.delegate))));
          this._currToken = localJsonToken1;
          localObject = localJsonToken1;
        }
      }
    }
  }
  
  public JsonToken nextValue()
    throws IOException
  {
    JsonToken localJsonToken = nextToken();
    if (localJsonToken == JsonToken.FIELD_NAME) {
      localJsonToken = nextToken();
    }
    return localJsonToken;
  }
  
  public void overrideCurrentName(String paramString)
  {
    throw new UnsupportedOperationException("Can not currently override name during filtering read");
  }
  
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException
  {
    return this.delegate.readBinaryValue(paramBase64Variant, paramOutputStream);
  }
  
  public JsonParser skipChildren()
    throws IOException
  {
    if ((this._currToken != JsonToken.START_OBJECT) && (this._currToken != JsonToken.START_ARRAY)) {}
    for (;;)
    {
      return this;
      int i = 1;
      label46:
      do
      {
        JsonToken localJsonToken;
        do
        {
          for (;;)
          {
            localJsonToken = nextToken();
            if (localJsonToken == null) {
              break;
            }
            if (!localJsonToken.isStructStart()) {
              break label46;
            }
            i++;
          }
        } while (!localJsonToken.isStructEnd());
        i--;
      } while (i != 0);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/filter/FilteringParserDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */