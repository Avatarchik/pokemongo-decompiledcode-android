package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePrediction.Substring;
import java.util.Collections;
import java.util.List;

public class AutocompletePredictionEntity
  implements SafeParcelable, AutocompletePrediction
{
  public static final Parcelable.Creator<AutocompletePredictionEntity> CREATOR = new zza();
  private static final List<SubstringEntity> zzaGN = Collections.emptyList();
  final int mVersionCode;
  final List<Integer> zzaFT;
  final String zzaGO;
  final List<SubstringEntity> zzaGP;
  final int zzaGQ;
  final String zzaGR;
  final List<SubstringEntity> zzaGS;
  final String zzaGT;
  final List<SubstringEntity> zzaGU;
  final String zzaGt;
  
  AutocompletePredictionEntity(int paramInt1, String paramString1, List<Integer> paramList, int paramInt2, String paramString2, List<SubstringEntity> paramList1, String paramString3, List<SubstringEntity> paramList2, String paramString4, List<SubstringEntity> paramList3)
  {
    this.mVersionCode = paramInt1;
    this.zzaGt = paramString1;
    this.zzaFT = paramList;
    this.zzaGQ = paramInt2;
    this.zzaGO = paramString2;
    this.zzaGP = paramList1;
    this.zzaGR = paramString3;
    this.zzaGS = paramList2;
    this.zzaGT = paramString4;
    this.zzaGU = paramList3;
  }
  
  public static AutocompletePredictionEntity zza(String paramString1, List<Integer> paramList, int paramInt, String paramString2, List<SubstringEntity> paramList1, String paramString3, List<SubstringEntity> paramList2, String paramString4, List<SubstringEntity> paramList3)
  {
    return new AutocompletePredictionEntity(0, paramString1, paramList, paramInt, (String)zzx.zzw(paramString2), paramList1, paramString3, paramList2, paramString4, paramList3);
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
      if (!(paramObject instanceof AutocompletePredictionEntity))
      {
        bool = false;
      }
      else
      {
        AutocompletePredictionEntity localAutocompletePredictionEntity = (AutocompletePredictionEntity)paramObject;
        if ((!zzw.equal(this.zzaGt, localAutocompletePredictionEntity.zzaGt)) || (!zzw.equal(this.zzaFT, localAutocompletePredictionEntity.zzaFT)) || (!zzw.equal(Integer.valueOf(this.zzaGQ), Integer.valueOf(localAutocompletePredictionEntity.zzaGQ))) || (!zzw.equal(this.zzaGO, localAutocompletePredictionEntity.zzaGO)) || (!zzw.equal(this.zzaGP, localAutocompletePredictionEntity.zzaGP)) || (!zzw.equal(this.zzaGR, localAutocompletePredictionEntity.zzaGR)) || (!zzw.equal(this.zzaGS, localAutocompletePredictionEntity.zzaGS)) || (!zzw.equal(this.zzaGT, localAutocompletePredictionEntity.zzaGT)) || (!zzw.equal(this.zzaGU, localAutocompletePredictionEntity.zzaGU))) {
          bool = false;
        }
      }
    }
  }
  
  public String getDescription()
  {
    return this.zzaGO;
  }
  
  public CharSequence getFullText(CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(this.zzaGO, this.zzaGP, paramCharacterStyle);
  }
  
  public List<? extends AutocompletePrediction.Substring> getMatchedSubstrings()
  {
    return this.zzaGP;
  }
  
  public String getPlaceId()
  {
    return this.zzaGt;
  }
  
  public List<Integer> getPlaceTypes()
  {
    return this.zzaFT;
  }
  
  public CharSequence getPrimaryText(CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(this.zzaGR, this.zzaGS, paramCharacterStyle);
  }
  
  public CharSequence getSecondaryText(CharacterStyle paramCharacterStyle)
  {
    return zzc.zza(this.zzaGT, this.zzaGU, paramCharacterStyle);
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = this.zzaGt;
    arrayOfObject[1] = this.zzaFT;
    arrayOfObject[2] = Integer.valueOf(this.zzaGQ);
    arrayOfObject[3] = this.zzaGO;
    arrayOfObject[4] = this.zzaGP;
    arrayOfObject[5] = this.zzaGR;
    arrayOfObject[6] = this.zzaGS;
    arrayOfObject[7] = this.zzaGT;
    arrayOfObject[8] = this.zzaGU;
    return zzw.hashCode(arrayOfObject);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("placeId", this.zzaGt).zzg("placeTypes", this.zzaFT).zzg("fullText", this.zzaGO).zzg("fullTextMatchedSubstrings", this.zzaGP).zzg("primaryText", this.zzaGR).zzg("primaryTextMatchedSubstrings", this.zzaGS).zzg("secondaryText", this.zzaGT).zzg("secondaryTextMatchedSubstrings", this.zzaGU).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public AutocompletePrediction zzwV()
  {
    return this;
  }
  
  public static class SubstringEntity
    implements SafeParcelable, AutocompletePrediction.Substring
  {
    public static final Parcelable.Creator<SubstringEntity> CREATOR = new zzv();
    final int mLength;
    final int mOffset;
    final int mVersionCode;
    
    public SubstringEntity(int paramInt1, int paramInt2, int paramInt3)
    {
      this.mVersionCode = paramInt1;
      this.mOffset = paramInt2;
      this.mLength = paramInt3;
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
        if (!(paramObject instanceof SubstringEntity))
        {
          bool = false;
        }
        else
        {
          SubstringEntity localSubstringEntity = (SubstringEntity)paramObject;
          if ((!zzw.equal(Integer.valueOf(this.mOffset), Integer.valueOf(localSubstringEntity.mOffset))) || (!zzw.equal(Integer.valueOf(this.mLength), Integer.valueOf(localSubstringEntity.mLength)))) {
            bool = false;
          }
        }
      }
    }
    
    public int getLength()
    {
      return this.mLength;
    }
    
    public int getOffset()
    {
      return this.mOffset;
    }
    
    public int hashCode()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(this.mOffset);
      arrayOfObject[1] = Integer.valueOf(this.mLength);
      return zzw.hashCode(arrayOfObject);
    }
    
    public String toString()
    {
      return zzw.zzv(this).zzg("offset", Integer.valueOf(this.mOffset)).zzg("length", Integer.valueOf(this.mLength)).toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      zzv.zza(this, paramParcel, paramInt);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/AutocompletePredictionEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */