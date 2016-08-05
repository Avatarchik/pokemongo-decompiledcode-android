package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class DataFormatDetector
{
  public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
  protected final JsonFactory[] _detectors;
  protected final int _maxInputLookahead;
  protected final MatchStrength _minimalMatch;
  protected final MatchStrength _optimalMatch;
  
  public DataFormatDetector(Collection<JsonFactory> paramCollection)
  {
    this((JsonFactory[])paramCollection.toArray(new JsonFactory[paramCollection.size()]));
  }
  
  public DataFormatDetector(JsonFactory... paramVarArgs)
  {
    this(paramVarArgs, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
  }
  
  private DataFormatDetector(JsonFactory[] paramArrayOfJsonFactory, MatchStrength paramMatchStrength1, MatchStrength paramMatchStrength2, int paramInt)
  {
    this._detectors = paramArrayOfJsonFactory;
    this._optimalMatch = paramMatchStrength1;
    this._minimalMatch = paramMatchStrength2;
    this._maxInputLookahead = paramInt;
  }
  
  private DataFormatMatcher _findFormat(InputAccessor.Std paramStd)
    throws IOException
  {
    Object localObject1 = null;
    Object localObject2 = null;
    JsonFactory[] arrayOfJsonFactory = this._detectors;
    int i = arrayOfJsonFactory.length;
    int j = 0;
    if (j < i)
    {
      JsonFactory localJsonFactory = arrayOfJsonFactory[j];
      paramStd.reset();
      MatchStrength localMatchStrength = localJsonFactory.hasFormat(paramStd);
      if ((localMatchStrength == null) || (localMatchStrength.ordinal() < this._minimalMatch.ordinal())) {}
      do
      {
        do
        {
          j++;
          break;
        } while ((localObject1 != null) && (((MatchStrength)localObject2).ordinal() >= localMatchStrength.ordinal()));
        localObject1 = localJsonFactory;
        localObject2 = localMatchStrength;
      } while (localMatchStrength.ordinal() < this._optimalMatch.ordinal());
    }
    return paramStd.createMatcher((JsonFactory)localObject1, (MatchStrength)localObject2);
  }
  
  public DataFormatMatcher findFormat(InputStream paramInputStream)
    throws IOException
  {
    return _findFormat(new InputAccessor.Std(paramInputStream, new byte[this._maxInputLookahead]));
  }
  
  public DataFormatMatcher findFormat(byte[] paramArrayOfByte)
    throws IOException
  {
    return _findFormat(new InputAccessor.Std(paramArrayOfByte));
  }
  
  public DataFormatMatcher findFormat(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    return _findFormat(new InputAccessor.Std(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('[');
    int i = this._detectors.length;
    if (i > 0)
    {
      localStringBuilder.append(this._detectors[0].getFormatName());
      for (int j = 1; j < i; j++)
      {
        localStringBuilder.append(", ");
        localStringBuilder.append(this._detectors[j].getFormatName());
      }
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public DataFormatDetector withMaxInputLookahead(int paramInt)
  {
    if (paramInt == this._maxInputLookahead) {}
    for (;;)
    {
      return this;
      this = new DataFormatDetector(this._detectors, this._optimalMatch, this._minimalMatch, paramInt);
    }
  }
  
  public DataFormatDetector withMinimalMatch(MatchStrength paramMatchStrength)
  {
    if (paramMatchStrength == this._minimalMatch) {}
    for (;;)
    {
      return this;
      this = new DataFormatDetector(this._detectors, this._optimalMatch, paramMatchStrength, this._maxInputLookahead);
    }
  }
  
  public DataFormatDetector withOptimalMatch(MatchStrength paramMatchStrength)
  {
    if (paramMatchStrength == this._optimalMatch) {}
    for (;;)
    {
      return this;
      this = new DataFormatDetector(this._detectors, paramMatchStrength, this._minimalMatch, this._maxInputLookahead);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/format/DataFormatDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */