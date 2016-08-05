package com.fasterxml.jackson.core;

import java.io.Serializable;

public class JsonLocation
  implements Serializable
{
  public static final JsonLocation NA = new JsonLocation("N/A", -1L, -1L, -1, -1);
  private static final long serialVersionUID = 1L;
  final int _columnNr;
  final int _lineNr;
  final transient Object _sourceRef;
  final long _totalBytes;
  final long _totalChars;
  
  public JsonLocation(Object paramObject, long paramLong, int paramInt1, int paramInt2)
  {
    this(paramObject, -1L, paramLong, paramInt1, paramInt2);
  }
  
  public JsonLocation(Object paramObject, long paramLong1, long paramLong2, int paramInt1, int paramInt2)
  {
    this._sourceRef = paramObject;
    this._totalBytes = paramLong1;
    this._totalChars = paramLong2;
    this._lineNr = paramInt1;
    this._columnNr = paramInt2;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramObject == this) {
      bool2 = bool1;
    }
    JsonLocation localJsonLocation;
    do
    {
      do
      {
        return bool2;
      } while ((paramObject == null) || (!(paramObject instanceof JsonLocation)));
      localJsonLocation = (JsonLocation)paramObject;
      if (this._sourceRef != null) {
        break;
      }
    } while (localJsonLocation._sourceRef != null);
    label45:
    if ((this._lineNr == localJsonLocation._lineNr) && (this._columnNr == localJsonLocation._columnNr) && (this._totalChars == localJsonLocation._totalChars) && (getByteOffset() == localJsonLocation.getByteOffset())) {}
    for (;;)
    {
      bool2 = bool1;
      break;
      if (this._sourceRef.equals(localJsonLocation._sourceRef)) {
        break label45;
      }
      break;
      bool1 = false;
    }
  }
  
  public long getByteOffset()
  {
    return this._totalBytes;
  }
  
  public long getCharOffset()
  {
    return this._totalChars;
  }
  
  public int getColumnNr()
  {
    return this._columnNr;
  }
  
  public int getLineNr()
  {
    return this._lineNr;
  }
  
  public Object getSourceRef()
  {
    return this._sourceRef;
  }
  
  public int hashCode()
  {
    if (this._sourceRef == null) {}
    for (int i = 1;; i = this._sourceRef.hashCode()) {
      return ((i ^ this._lineNr) + this._columnNr ^ (int)this._totalChars) + (int)this._totalBytes;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(80);
    localStringBuilder.append("[Source: ");
    if (this._sourceRef == null) {
      localStringBuilder.append("UNKNOWN");
    }
    for (;;)
    {
      localStringBuilder.append("; line: ");
      localStringBuilder.append(this._lineNr);
      localStringBuilder.append(", column: ");
      localStringBuilder.append(this._columnNr);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
      localStringBuilder.append(this._sourceRef.toString());
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */