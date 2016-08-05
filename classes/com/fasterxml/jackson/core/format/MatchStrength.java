package com.fasterxml.jackson.core.format;

public enum MatchStrength
{
  static
  {
    INCONCLUSIVE = new MatchStrength("INCONCLUSIVE", 1);
    WEAK_MATCH = new MatchStrength("WEAK_MATCH", 2);
    SOLID_MATCH = new MatchStrength("SOLID_MATCH", 3);
    FULL_MATCH = new MatchStrength("FULL_MATCH", 4);
    MatchStrength[] arrayOfMatchStrength = new MatchStrength[5];
    arrayOfMatchStrength[0] = NO_MATCH;
    arrayOfMatchStrength[1] = INCONCLUSIVE;
    arrayOfMatchStrength[2] = WEAK_MATCH;
    arrayOfMatchStrength[3] = SOLID_MATCH;
    arrayOfMatchStrength[4] = FULL_MATCH;
    $VALUES = arrayOfMatchStrength;
  }
  
  private MatchStrength() {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/format/MatchStrength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */