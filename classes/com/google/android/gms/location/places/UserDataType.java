package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class UserDataType
  implements SafeParcelable
{
  public static final zzm CREATOR = new zzm();
  public static final UserDataType zzaGI = zzy("test_type", 1);
  public static final UserDataType zzaGJ = zzy("labeled_place", 6);
  public static final UserDataType zzaGK = zzy("here_content", 7);
  public static final Set<UserDataType> zzaGL;
  final int mVersionCode;
  final String zzGq;
  final int zzaGM;
  
  static
  {
    UserDataType[] arrayOfUserDataType = new UserDataType[3];
    arrayOfUserDataType[0] = zzaGI;
    arrayOfUserDataType[1] = zzaGJ;
    arrayOfUserDataType[2] = zzaGK;
    zzaGL = Collections.unmodifiableSet(new HashSet(Arrays.asList(arrayOfUserDataType)));
  }
  
  UserDataType(int paramInt1, String paramString, int paramInt2)
  {
    zzx.zzcr(paramString);
    this.mVersionCode = paramInt1;
    this.zzGq = paramString;
    this.zzaGM = paramInt2;
  }
  
  private static UserDataType zzy(String paramString, int paramInt)
  {
    return new UserDataType(0, paramString, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof UserDataType))
      {
        bool = false;
      }
      else
      {
        UserDataType localUserDataType = (UserDataType)paramObject;
        if ((!this.zzGq.equals(localUserDataType.zzGq)) || (this.zzaGM != localUserDataType.zzaGM)) {
          bool = false;
        }
      }
    }
  }
  
  public int hashCode()
  {
    return this.zzGq.hashCode();
  }
  
  public String toString()
  {
    return this.zzGq;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzm.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/UserDataType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */