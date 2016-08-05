package com.nianticlabs.pokemongoplus.bridge;

public class BridgeConstants
{
  public static enum SfidaState
  {
    static
    {
      Connected = new SfidaState("Connected", 2);
      Discovered = new SfidaState("Discovered", 3);
      Certified = new SfidaState("Certified", 4);
      SoftwareUpdate = new SfidaState("SoftwareUpdate", 5);
      Failed = new SfidaState("Failed", 6);
      BadValue = new SfidaState("BadValue", 7);
      SfidaState[] arrayOfSfidaState = new SfidaState[8];
      arrayOfSfidaState[0] = Disconnected;
      arrayOfSfidaState[1] = Disconnecting;
      arrayOfSfidaState[2] = Connected;
      arrayOfSfidaState[3] = Discovered;
      arrayOfSfidaState[4] = Certified;
      arrayOfSfidaState[5] = SoftwareUpdate;
      arrayOfSfidaState[6] = Failed;
      arrayOfSfidaState[7] = BadValue;
      $VALUES = arrayOfSfidaState;
    }
    
    private SfidaState() {}
    
    public static SfidaState fromInt(int paramInt)
    {
      try
      {
        localSfidaState = values()[paramInt];
        return localSfidaState;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          SfidaState localSfidaState = BadValue;
        }
      }
    }
  }
  
  public static enum PgpState
  {
    static
    {
      Initialized = new PgpState("Initialized", 1);
      Started = new PgpState("Started", 2);
      Resumed = new PgpState("Resumed", 3);
      Paused = new PgpState("Paused", 4);
      Stopped = new PgpState("Stopped", 5);
      BadValue = new PgpState("BadValue", 6);
      PgpState[] arrayOfPgpState = new PgpState[7];
      arrayOfPgpState[0] = Unknown;
      arrayOfPgpState[1] = Initialized;
      arrayOfPgpState[2] = Started;
      arrayOfPgpState[3] = Resumed;
      arrayOfPgpState[4] = Paused;
      arrayOfPgpState[5] = Stopped;
      arrayOfPgpState[6] = BadValue;
      $VALUES = arrayOfPgpState;
    }
    
    private PgpState() {}
    
    public static PgpState fromInt(int paramInt)
    {
      try
      {
        localPgpState = values()[paramInt];
        return localPgpState;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          PgpState localPgpState = BadValue;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/bridge/BridgeConstants.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */