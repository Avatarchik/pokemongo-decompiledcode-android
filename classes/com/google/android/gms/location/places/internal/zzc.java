package com.google.android.gms.location.places.internal;

import android.text.SpannableString;
import android.text.style.CharacterStyle;
import java.util.Iterator;
import java.util.List;

public class zzc
{
  public static CharSequence zza(String paramString, List<AutocompletePredictionEntity.SubstringEntity> paramList, CharacterStyle paramCharacterStyle)
  {
    if (paramCharacterStyle == null) {}
    for (;;)
    {
      return paramString;
      SpannableString localSpannableString = new SpannableString(paramString);
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        AutocompletePredictionEntity.SubstringEntity localSubstringEntity = (AutocompletePredictionEntity.SubstringEntity)localIterator.next();
        localSpannableString.setSpan(CharacterStyle.wrap(paramCharacterStyle), localSubstringEntity.getOffset(), localSubstringEntity.getOffset() + localSubstringEntity.getLength(), 0);
      }
      paramString = localSpannableString;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */