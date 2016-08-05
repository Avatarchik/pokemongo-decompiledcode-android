package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.NumberInput;

public class JsonPointer
{
  protected static final JsonPointer EMPTY = new JsonPointer();
  protected final String _asString;
  protected volatile JsonPointer _head;
  protected final int _matchingElementIndex;
  protected final String _matchingPropertyName;
  protected final JsonPointer _nextSegment;
  
  protected JsonPointer()
  {
    this._nextSegment = null;
    this._matchingPropertyName = "";
    this._matchingElementIndex = -1;
    this._asString = "";
  }
  
  protected JsonPointer(String paramString1, String paramString2, int paramInt, JsonPointer paramJsonPointer)
  {
    this._asString = paramString1;
    this._nextSegment = paramJsonPointer;
    this._matchingPropertyName = paramString2;
    this._matchingElementIndex = paramInt;
  }
  
  protected JsonPointer(String paramString1, String paramString2, JsonPointer paramJsonPointer)
  {
    this._asString = paramString1;
    this._nextSegment = paramJsonPointer;
    this._matchingPropertyName = paramString2;
    this._matchingElementIndex = _parseIndex(paramString2);
  }
  
  private static void _appendEscape(StringBuilder paramStringBuilder, char paramChar)
  {
    if (paramChar == '0') {
      paramChar = '~';
    }
    for (;;)
    {
      paramStringBuilder.append(paramChar);
      return;
      if (paramChar == '1') {
        paramChar = '/';
      } else {
        paramStringBuilder.append('~');
      }
    }
  }
  
  private static final int _parseIndex(String paramString)
  {
    int i = 0;
    int j = -1;
    int k = paramString.length();
    if ((k == 0) || (k > 10)) {}
    for (;;)
    {
      return j;
      int m = paramString.charAt(0);
      if (m <= 48)
      {
        if ((k == 1) && (m == 48)) {}
        for (;;)
        {
          j = i;
          break;
          i = j;
        }
      }
      if (m <= 57)
      {
        for (int n = 1;; n++)
        {
          if (n >= k) {
            break label102;
          }
          int i1 = paramString.charAt(n);
          if ((i1 > 57) || (i1 < 48)) {
            break;
          }
        }
        label102:
        if ((k != 10) || (NumberInput.parseLong(paramString) <= 2147483647L)) {
          j = NumberInput.parseInt(paramString);
        }
      }
    }
  }
  
  protected static JsonPointer _parseQuotedTail(String paramString, int paramInt)
  {
    int i = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(Math.max(16, i));
    if (paramInt > 2) {
      localStringBuilder.append(paramString, 1, paramInt - 1);
    }
    int j = paramInt + 1;
    _appendEscape(localStringBuilder, paramString.charAt(paramInt));
    int k = j;
    char c;
    if (k < i)
    {
      c = paramString.charAt(k);
      if (c != '/') {}
    }
    for (JsonPointer localJsonPointer = new JsonPointer(paramString, localStringBuilder.toString(), _parseTail(paramString.substring(k)));; localJsonPointer = new JsonPointer(paramString, localStringBuilder.toString(), EMPTY))
    {
      return localJsonPointer;
      k++;
      if ((c == '~') && (k < i))
      {
        int m = k + 1;
        _appendEscape(localStringBuilder, paramString.charAt(k));
        k = m;
        break;
      }
      localStringBuilder.append(c);
      break;
    }
  }
  
  protected static JsonPointer _parseTail(String paramString)
  {
    int i = paramString.length();
    int j = 1;
    int k;
    JsonPointer localJsonPointer;
    if (j < i)
    {
      k = paramString.charAt(j);
      if (k == 47) {
        localJsonPointer = new JsonPointer(paramString, paramString.substring(1, j), _parseTail(paramString.substring(j)));
      }
    }
    for (;;)
    {
      return localJsonPointer;
      j++;
      if ((k != 126) || (j >= i)) {
        break;
      }
      localJsonPointer = _parseQuotedTail(paramString, j);
      continue;
      localJsonPointer = new JsonPointer(paramString, paramString.substring(1), EMPTY);
    }
  }
  
  public static JsonPointer compile(String paramString)
    throws IllegalArgumentException
  {
    if ((paramString == null) || (paramString.length() == 0)) {}
    for (JsonPointer localJsonPointer = EMPTY;; localJsonPointer = _parseTail(paramString))
    {
      return localJsonPointer;
      if (paramString.charAt(0) != '/') {
        throw new IllegalArgumentException("Invalid input: JSON Pointer expression must start with '/': \"" + paramString + "\"");
      }
    }
  }
  
  public static JsonPointer valueOf(String paramString)
  {
    return compile(paramString);
  }
  
  protected JsonPointer _constructHead()
  {
    JsonPointer localJsonPointer1 = last();
    if (localJsonPointer1 == this) {}
    int i;
    JsonPointer localJsonPointer2;
    for (JsonPointer localJsonPointer3 = EMPTY;; localJsonPointer3 = new JsonPointer(this._asString.substring(0, this._asString.length() - i), this._matchingPropertyName, this._matchingElementIndex, localJsonPointer2._constructHead(i, localJsonPointer1)))
    {
      return localJsonPointer3;
      i = localJsonPointer1._asString.length();
      localJsonPointer2 = this._nextSegment;
    }
  }
  
  protected JsonPointer _constructHead(int paramInt, JsonPointer paramJsonPointer)
  {
    if (this == paramJsonPointer) {}
    JsonPointer localJsonPointer1;
    String str;
    for (JsonPointer localJsonPointer2 = EMPTY;; localJsonPointer2 = new JsonPointer(str.substring(0, str.length() - paramInt), this._matchingPropertyName, this._matchingElementIndex, localJsonPointer1._constructHead(paramInt, paramJsonPointer)))
    {
      return localJsonPointer2;
      localJsonPointer1 = this._nextSegment;
      str = this._asString;
    }
  }
  
  public JsonPointer append(JsonPointer paramJsonPointer)
  {
    if (this == EMPTY) {}
    for (;;)
    {
      return paramJsonPointer;
      if (paramJsonPointer == EMPTY)
      {
        paramJsonPointer = this;
      }
      else
      {
        String str = this._asString;
        if (str.endsWith("/")) {
          str = str.substring(0, -1 + str.length());
        }
        paramJsonPointer = compile(str + paramJsonPointer._asString);
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == this) {}
    for (bool = true;; bool = this._asString.equals(((JsonPointer)paramObject)._asString)) {
      do
      {
        return bool;
      } while ((paramObject == null) || (!(paramObject instanceof JsonPointer)));
    }
  }
  
  public int getMatchingIndex()
  {
    return this._matchingElementIndex;
  }
  
  public String getMatchingProperty()
  {
    return this._matchingPropertyName;
  }
  
  public int hashCode()
  {
    return this._asString.hashCode();
  }
  
  public JsonPointer head()
  {
    JsonPointer localJsonPointer = this._head;
    if (localJsonPointer == null)
    {
      if (this != EMPTY) {
        localJsonPointer = _constructHead();
      }
      this._head = localJsonPointer;
    }
    return localJsonPointer;
  }
  
  public JsonPointer last()
  {
    Object localObject1 = this;
    if (localObject1 == EMPTY) {}
    for (Object localObject2 = null;; localObject2 = localObject1)
    {
      return (JsonPointer)localObject2;
      for (;;)
      {
        JsonPointer localJsonPointer = ((JsonPointer)localObject1)._nextSegment;
        if (localJsonPointer == EMPTY) {
          break;
        }
        localObject1 = localJsonPointer;
      }
    }
  }
  
  public JsonPointer matchElement(int paramInt)
  {
    if ((paramInt != this._matchingElementIndex) || (paramInt < 0)) {}
    for (JsonPointer localJsonPointer = null;; localJsonPointer = this._nextSegment) {
      return localJsonPointer;
    }
  }
  
  public JsonPointer matchProperty(String paramString)
  {
    if ((this._nextSegment != null) && (this._matchingPropertyName.equals(paramString))) {}
    for (JsonPointer localJsonPointer = this._nextSegment;; localJsonPointer = null) {
      return localJsonPointer;
    }
  }
  
  public boolean matches()
  {
    if (this._nextSegment == null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean matchesElement(int paramInt)
  {
    if ((paramInt == this._matchingElementIndex) && (paramInt >= 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean matchesProperty(String paramString)
  {
    if ((this._nextSegment != null) && (this._matchingPropertyName.equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean mayMatchElement()
  {
    if (this._matchingElementIndex >= 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean mayMatchProperty()
  {
    if (this._matchingPropertyName != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public JsonPointer tail()
  {
    return this._nextSegment;
  }
  
  public String toString()
  {
    return this._asString;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonPointer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */