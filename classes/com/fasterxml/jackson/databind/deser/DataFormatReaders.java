package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.format.InputAccessor.Std;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class DataFormatReaders
{
  public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
  protected final int _maxInputLookahead;
  protected final MatchStrength _minimalMatch;
  protected final MatchStrength _optimalMatch;
  protected final ObjectReader[] _readers;
  
  public DataFormatReaders(Collection<ObjectReader> paramCollection)
  {
    this((ObjectReader[])paramCollection.toArray(new ObjectReader[paramCollection.size()]));
  }
  
  public DataFormatReaders(ObjectReader... paramVarArgs)
  {
    this(paramVarArgs, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
  }
  
  private DataFormatReaders(ObjectReader[] paramArrayOfObjectReader, MatchStrength paramMatchStrength1, MatchStrength paramMatchStrength2, int paramInt)
  {
    this._readers = paramArrayOfObjectReader;
    this._optimalMatch = paramMatchStrength1;
    this._minimalMatch = paramMatchStrength2;
    this._maxInputLookahead = paramInt;
  }
  
  private Match _findFormat(AccessorForReader paramAccessorForReader)
    throws IOException
  {
    Object localObject1 = null;
    Object localObject2 = null;
    ObjectReader[] arrayOfObjectReader = this._readers;
    int i = arrayOfObjectReader.length;
    int j = 0;
    if (j < i)
    {
      ObjectReader localObjectReader = arrayOfObjectReader[j];
      paramAccessorForReader.reset();
      MatchStrength localMatchStrength = localObjectReader.getFactory().hasFormat(paramAccessorForReader);
      if ((localMatchStrength == null) || (localMatchStrength.ordinal() < this._minimalMatch.ordinal())) {}
      do
      {
        do
        {
          j++;
          break;
        } while ((localObject1 != null) && (((MatchStrength)localObject2).ordinal() >= localMatchStrength.ordinal()));
        localObject1 = localObjectReader;
        localObject2 = localMatchStrength;
      } while (localMatchStrength.ordinal() < this._optimalMatch.ordinal());
    }
    return paramAccessorForReader.createMatcher((ObjectReader)localObject1, (MatchStrength)localObject2);
  }
  
  public Match findFormat(InputStream paramInputStream)
    throws IOException
  {
    return _findFormat(new AccessorForReader(paramInputStream, new byte[this._maxInputLookahead]));
  }
  
  public Match findFormat(byte[] paramArrayOfByte)
    throws IOException
  {
    return _findFormat(new AccessorForReader(paramArrayOfByte));
  }
  
  public Match findFormat(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    return _findFormat(new AccessorForReader(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    int i = this._readers.length;
    if (i > 0)
    {
      localStringBuilder.append(this._readers[0].getFactory().getFormatName());
      for (int j = 1; j < i; j++)
      {
        localStringBuilder.append(", ");
        localStringBuilder.append(this._readers[j].getFactory().getFormatName());
      }
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public DataFormatReaders with(DeserializationConfig paramDeserializationConfig)
  {
    int i = this._readers.length;
    ObjectReader[] arrayOfObjectReader = new ObjectReader[i];
    for (int j = 0; j < i; j++) {
      arrayOfObjectReader[j] = this._readers[j].with(paramDeserializationConfig);
    }
    return new DataFormatReaders(arrayOfObjectReader, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
  }
  
  public DataFormatReaders with(ObjectReader[] paramArrayOfObjectReader)
  {
    return new DataFormatReaders(paramArrayOfObjectReader, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
  }
  
  public DataFormatReaders withMaxInputLookahead(int paramInt)
  {
    if (paramInt == this._maxInputLookahead) {}
    for (;;)
    {
      return this;
      this = new DataFormatReaders(this._readers, this._optimalMatch, this._minimalMatch, paramInt);
    }
  }
  
  public DataFormatReaders withMinimalMatch(MatchStrength paramMatchStrength)
  {
    if (paramMatchStrength == this._minimalMatch) {}
    for (;;)
    {
      return this;
      this = new DataFormatReaders(this._readers, this._optimalMatch, paramMatchStrength, this._maxInputLookahead);
    }
  }
  
  public DataFormatReaders withOptimalMatch(MatchStrength paramMatchStrength)
  {
    if (paramMatchStrength == this._optimalMatch) {}
    for (;;)
    {
      return this;
      this = new DataFormatReaders(this._readers, paramMatchStrength, this._minimalMatch, this._maxInputLookahead);
    }
  }
  
  public DataFormatReaders withType(JavaType paramJavaType)
  {
    int i = this._readers.length;
    ObjectReader[] arrayOfObjectReader = new ObjectReader[i];
    for (int j = 0; j < i; j++) {
      arrayOfObjectReader[j] = this._readers[j].forType(paramJavaType);
    }
    return new DataFormatReaders(arrayOfObjectReader, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
  }
  
  public static class Match
  {
    protected final byte[] _bufferedData;
    protected final int _bufferedLength;
    protected final int _bufferedStart;
    protected final ObjectReader _match;
    protected final MatchStrength _matchStrength;
    protected final InputStream _originalStream;
    
    protected Match(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2, ObjectReader paramObjectReader, MatchStrength paramMatchStrength)
    {
      this._originalStream = paramInputStream;
      this._bufferedData = paramArrayOfByte;
      this._bufferedStart = paramInt1;
      this._bufferedLength = paramInt2;
      this._match = paramObjectReader;
      this._matchStrength = paramMatchStrength;
    }
    
    public JsonParser createParserWithMatch()
      throws IOException
    {
      JsonParser localJsonParser;
      if (this._match == null) {
        localJsonParser = null;
      }
      for (;;)
      {
        return localJsonParser;
        JsonFactory localJsonFactory = this._match.getFactory();
        if (this._originalStream == null) {
          localJsonParser = localJsonFactory.createParser(this._bufferedData, this._bufferedStart, this._bufferedLength);
        } else {
          localJsonParser = localJsonFactory.createParser(getDataStream());
        }
      }
    }
    
    public InputStream getDataStream()
    {
      if (this._originalStream == null) {}
      for (Object localObject = new ByteArrayInputStream(this._bufferedData, this._bufferedStart, this._bufferedLength);; localObject = new MergedStream(null, this._originalStream, this._bufferedData, this._bufferedStart, this._bufferedLength)) {
        return (InputStream)localObject;
      }
    }
    
    public MatchStrength getMatchStrength()
    {
      if (this._matchStrength == null) {}
      for (MatchStrength localMatchStrength = MatchStrength.INCONCLUSIVE;; localMatchStrength = this._matchStrength) {
        return localMatchStrength;
      }
    }
    
    public String getMatchedFormatName()
    {
      return this._match.getFactory().getFormatName();
    }
    
    public ObjectReader getReader()
    {
      return this._match;
    }
    
    public boolean hasMatch()
    {
      if (this._match != null) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  protected class AccessorForReader
    extends InputAccessor.Std
  {
    public AccessorForReader(InputStream paramInputStream, byte[] paramArrayOfByte)
    {
      super(paramArrayOfByte);
    }
    
    public AccessorForReader(byte[] paramArrayOfByte)
    {
      super();
    }
    
    public AccessorForReader(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      super(paramInt1, paramInt2);
    }
    
    public DataFormatReaders.Match createMatcher(ObjectReader paramObjectReader, MatchStrength paramMatchStrength)
    {
      return new DataFormatReaders.Match(this._in, this._buffer, this._bufferedStart, this._bufferedEnd - this._bufferedStart, paramObjectReader, paramMatchStrength);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/DataFormatReaders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */