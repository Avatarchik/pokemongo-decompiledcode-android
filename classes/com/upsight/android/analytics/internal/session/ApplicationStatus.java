package com.upsight.android.analytics.internal.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.application.status")
public class ApplicationStatus
{
  @UpsightStorableIdentifier
  String id;
  @JsonProperty
  State state;
  
  ApplicationStatus() {}
  
  public ApplicationStatus(State paramState)
  {
    this.state = paramState;
  }
  
  public State getState()
  {
    return this.state;
  }
  
  public static enum State
  {
    static
    {
      State[] arrayOfState = new State[2];
      arrayOfState[0] = BACKGROUND;
      arrayOfState[1] = FOREGROUND;
      $VALUES = arrayOfState;
    }
    
    private State() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/ApplicationStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */