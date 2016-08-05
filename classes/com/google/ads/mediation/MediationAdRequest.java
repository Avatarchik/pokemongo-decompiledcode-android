package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest.Gender;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
public class MediationAdRequest
{
  private final Date zzaT;
  private final AdRequest.Gender zzaU;
  private final Set<String> zzaV;
  private final boolean zzaW;
  private final Location zzaX;
  
  public MediationAdRequest(Date paramDate, AdRequest.Gender paramGender, Set<String> paramSet, boolean paramBoolean, Location paramLocation)
  {
    this.zzaT = paramDate;
    this.zzaU = paramGender;
    this.zzaV = paramSet;
    this.zzaW = paramBoolean;
    this.zzaX = paramLocation;
  }
  
  public Integer getAgeInYears()
  {
    if (this.zzaT != null)
    {
      Calendar localCalendar1 = Calendar.getInstance();
      Calendar localCalendar2 = Calendar.getInstance();
      localCalendar1.setTime(this.zzaT);
      localInteger = Integer.valueOf(localCalendar2.get(1) - localCalendar1.get(1));
      if ((localCalendar2.get(2) >= localCalendar1.get(2)) && ((localCalendar2.get(2) != localCalendar1.get(2)) || (localCalendar2.get(5) >= localCalendar1.get(5)))) {}
    }
    for (Integer localInteger = Integer.valueOf(-1 + localInteger.intValue());; localInteger = null) {
      return localInteger;
    }
  }
  
  public Date getBirthday()
  {
    return this.zzaT;
  }
  
  public AdRequest.Gender getGender()
  {
    return this.zzaU;
  }
  
  public Set<String> getKeywords()
  {
    return this.zzaV;
  }
  
  public Location getLocation()
  {
    return this.zzaX;
  }
  
  public boolean isTesting()
  {
    return this.zzaW;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/ads/mediation/MediationAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */