package com.crittercism.app;

public enum CritterRateMyAppButtons
{
  static
  {
    NO = new CritterRateMyAppButtons("NO", 1);
    LATER = new CritterRateMyAppButtons("LATER", 2);
    CritterRateMyAppButtons[] arrayOfCritterRateMyAppButtons = new CritterRateMyAppButtons[3];
    arrayOfCritterRateMyAppButtons[0] = YES;
    arrayOfCritterRateMyAppButtons[1] = NO;
    arrayOfCritterRateMyAppButtons[2] = LATER;
    $VALUES = arrayOfCritterRateMyAppButtons;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/crittercism/app/CritterRateMyAppButtons.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */