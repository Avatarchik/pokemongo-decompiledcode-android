package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmj;
import com.google.android.gms.internal.zzmk;
import com.google.android.gms.internal.zzmu;
import com.google.android.gms.internal.zzmv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SafeParcelResponse
  extends FastJsonResponse
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  private final String mClassName;
  private final int mVersionCode;
  private final FieldMappingDictionary zzahc;
  private final Parcel zzahj;
  private final int zzahk;
  private int zzahl;
  private int zzahm;
  
  SafeParcelResponse(int paramInt, Parcel paramParcel, FieldMappingDictionary paramFieldMappingDictionary)
  {
    this.mVersionCode = paramInt;
    this.zzahj = ((Parcel)zzx.zzw(paramParcel));
    this.zzahk = 2;
    this.zzahc = paramFieldMappingDictionary;
    if (this.zzahc == null) {}
    for (this.mClassName = null;; this.mClassName = this.zzahc.zzpT())
    {
      this.zzahl = 2;
      return;
    }
  }
  
  private SafeParcelResponse(SafeParcelable paramSafeParcelable, FieldMappingDictionary paramFieldMappingDictionary, String paramString)
  {
    this.mVersionCode = 1;
    this.zzahj = Parcel.obtain();
    paramSafeParcelable.writeToParcel(this.zzahj, 0);
    this.zzahk = 1;
    this.zzahc = ((FieldMappingDictionary)zzx.zzw(paramFieldMappingDictionary));
    this.mClassName = ((String)zzx.zzw(paramString));
    this.zzahl = 2;
  }
  
  private static HashMap<Integer, Map.Entry<String, FastJsonResponse.Field<?, ?>>> zzG(Map<String, FastJsonResponse.Field<?, ?>> paramMap)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localHashMap.put(Integer.valueOf(((FastJsonResponse.Field)localEntry.getValue()).zzpK()), localEntry);
    }
    return localHashMap;
  }
  
  public static <T extends FastJsonResponse,  extends SafeParcelable> SafeParcelResponse zza(T paramT)
  {
    String str = paramT.getClass().getCanonicalName();
    FieldMappingDictionary localFieldMappingDictionary = zzb(paramT);
    return new SafeParcelResponse((SafeParcelable)paramT, localFieldMappingDictionary, str);
  }
  
  private static void zza(FieldMappingDictionary paramFieldMappingDictionary, FastJsonResponse paramFastJsonResponse)
  {
    Class localClass1 = paramFastJsonResponse.getClass();
    if (!paramFieldMappingDictionary.zzb(localClass1))
    {
      Map localMap = paramFastJsonResponse.zzpD();
      paramFieldMappingDictionary.zza(localClass1, localMap);
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        FastJsonResponse.Field localField = (FastJsonResponse.Field)localMap.get((String)localIterator.next());
        Class localClass2 = localField.zzpL();
        if (localClass2 != null) {
          try
          {
            zza(paramFieldMappingDictionary, (FastJsonResponse)localClass2.newInstance());
          }
          catch (InstantiationException localInstantiationException)
          {
            throw new IllegalStateException("Could not instantiate an object of type " + localField.zzpL().getCanonicalName(), localInstantiationException);
          }
          catch (IllegalAccessException localIllegalAccessException)
          {
            throw new IllegalStateException("Could not access object of type " + localField.zzpL().getCanonicalName(), localIllegalAccessException);
          }
        }
      }
    }
  }
  
  private void zza(StringBuilder paramStringBuilder, int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("Unknown type = " + paramInt);
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
      paramStringBuilder.append(paramObject);
    case 7: 
    case 8: 
    case 9: 
    case 10: 
      for (;;)
      {
        return;
        paramStringBuilder.append("\"").append(zzmu.zzcz(paramObject.toString())).append("\"");
        continue;
        paramStringBuilder.append("\"").append(zzmk.zzi((byte[])paramObject)).append("\"");
        continue;
        paramStringBuilder.append("\"").append(zzmk.zzj((byte[])paramObject));
        paramStringBuilder.append("\"");
        continue;
        zzmv.zza(paramStringBuilder, (HashMap)paramObject);
      }
    }
    throw new IllegalArgumentException("Method does not accept concrete type.");
  }
  
  private void zza(StringBuilder paramStringBuilder, FastJsonResponse.Field<?, ?> paramField, Parcel paramParcel, int paramInt)
  {
    switch (paramField.zzpC())
    {
    default: 
      throw new IllegalArgumentException("Unknown field out type = " + paramField.zzpC());
    case 0: 
      zzb(paramStringBuilder, paramField, zza(paramField, Integer.valueOf(zza.zzg(paramParcel, paramInt))));
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
      for (;;)
      {
        return;
        zzb(paramStringBuilder, paramField, zza(paramField, zza.zzk(paramParcel, paramInt)));
        continue;
        zzb(paramStringBuilder, paramField, zza(paramField, Long.valueOf(zza.zzi(paramParcel, paramInt))));
        continue;
        zzb(paramStringBuilder, paramField, zza(paramField, Float.valueOf(zza.zzl(paramParcel, paramInt))));
        continue;
        zzb(paramStringBuilder, paramField, zza(paramField, Double.valueOf(zza.zzn(paramParcel, paramInt))));
        continue;
        zzb(paramStringBuilder, paramField, zza(paramField, zza.zzo(paramParcel, paramInt)));
        continue;
        zzb(paramStringBuilder, paramField, zza(paramField, Boolean.valueOf(zza.zzc(paramParcel, paramInt))));
        continue;
        zzb(paramStringBuilder, paramField, zza(paramField, zza.zzp(paramParcel, paramInt)));
        continue;
        zzb(paramStringBuilder, paramField, zza(paramField, zza.zzs(paramParcel, paramInt)));
        continue;
        zzb(paramStringBuilder, paramField, zza(paramField, zzi(zza.zzr(paramParcel, paramInt))));
      }
    }
    throw new IllegalArgumentException("Method does not accept concrete type.");
  }
  
  private void zza(StringBuilder paramStringBuilder, String paramString, FastJsonResponse.Field<?, ?> paramField, Parcel paramParcel, int paramInt)
  {
    paramStringBuilder.append("\"").append(paramString).append("\":");
    if (paramField.zzpN()) {
      zza(paramStringBuilder, paramField, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      zzb(paramStringBuilder, paramField, paramParcel, paramInt);
    }
  }
  
  private void zza(StringBuilder paramStringBuilder, Map<String, FastJsonResponse.Field<?, ?>> paramMap, Parcel paramParcel)
  {
    HashMap localHashMap = zzG(paramMap);
    paramStringBuilder.append('{');
    int i = zza.zzap(paramParcel);
    int j = 0;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      Map.Entry localEntry = (Map.Entry)localHashMap.get(Integer.valueOf(zza.zzbM(k)));
      if (localEntry != null)
      {
        if (j != 0) {
          paramStringBuilder.append(",");
        }
        zza(paramStringBuilder, (String)localEntry.getKey(), (FastJsonResponse.Field)localEntry.getValue(), paramParcel, k);
        j = 1;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    paramStringBuilder.append('}');
  }
  
  private static FieldMappingDictionary zzb(FastJsonResponse paramFastJsonResponse)
  {
    FieldMappingDictionary localFieldMappingDictionary = new FieldMappingDictionary(paramFastJsonResponse.getClass());
    zza(localFieldMappingDictionary, paramFastJsonResponse);
    localFieldMappingDictionary.zzpR();
    localFieldMappingDictionary.zzpQ();
    return localFieldMappingDictionary;
  }
  
  private void zzb(StringBuilder paramStringBuilder, FastJsonResponse.Field<?, ?> paramField, Parcel paramParcel, int paramInt)
  {
    if (paramField.zzpI())
    {
      paramStringBuilder.append("[");
      switch (paramField.zzpC())
      {
      default: 
        throw new IllegalStateException("Unknown field type out.");
      case 0: 
        zzmj.zza(paramStringBuilder, zza.zzv(paramParcel, paramInt));
        paramStringBuilder.append("]");
      }
    }
    for (;;)
    {
      return;
      zzmj.zza(paramStringBuilder, zza.zzx(paramParcel, paramInt));
      break;
      zzmj.zza(paramStringBuilder, zza.zzw(paramParcel, paramInt));
      break;
      zzmj.zza(paramStringBuilder, zza.zzy(paramParcel, paramInt));
      break;
      zzmj.zza(paramStringBuilder, zza.zzz(paramParcel, paramInt));
      break;
      zzmj.zza(paramStringBuilder, zza.zzA(paramParcel, paramInt));
      break;
      zzmj.zza(paramStringBuilder, zza.zzu(paramParcel, paramInt));
      break;
      zzmj.zza(paramStringBuilder, zza.zzB(paramParcel, paramInt));
      break;
      throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
      Parcel[] arrayOfParcel = zza.zzF(paramParcel, paramInt);
      int j = arrayOfParcel.length;
      for (int k = 0; k < j; k++)
      {
        if (k > 0) {
          paramStringBuilder.append(",");
        }
        arrayOfParcel[k].setDataPosition(0);
        zza(paramStringBuilder, paramField.zzpP(), arrayOfParcel[k]);
      }
      break;
      switch (paramField.zzpC())
      {
      default: 
        throw new IllegalStateException("Unknown field type out");
      case 0: 
        paramStringBuilder.append(zza.zzg(paramParcel, paramInt));
        break;
      case 1: 
        paramStringBuilder.append(zza.zzk(paramParcel, paramInt));
        break;
      case 2: 
        paramStringBuilder.append(zza.zzi(paramParcel, paramInt));
        break;
      case 3: 
        paramStringBuilder.append(zza.zzl(paramParcel, paramInt));
        break;
      case 4: 
        paramStringBuilder.append(zza.zzn(paramParcel, paramInt));
        break;
      case 5: 
        paramStringBuilder.append(zza.zzo(paramParcel, paramInt));
        break;
      case 6: 
        paramStringBuilder.append(zza.zzc(paramParcel, paramInt));
        break;
      case 7: 
        String str2 = zza.zzp(paramParcel, paramInt);
        paramStringBuilder.append("\"").append(zzmu.zzcz(str2)).append("\"");
        break;
      case 8: 
        byte[] arrayOfByte2 = zza.zzs(paramParcel, paramInt);
        paramStringBuilder.append("\"").append(zzmk.zzi(arrayOfByte2)).append("\"");
        break;
      case 9: 
        byte[] arrayOfByte1 = zza.zzs(paramParcel, paramInt);
        paramStringBuilder.append("\"").append(zzmk.zzj(arrayOfByte1));
        paramStringBuilder.append("\"");
        break;
      case 10: 
        Bundle localBundle = zza.zzr(paramParcel, paramInt);
        Set localSet = localBundle.keySet();
        localSet.size();
        paramStringBuilder.append("{");
        Iterator localIterator = localSet.iterator();
        for (int i = 1; localIterator.hasNext(); i = 0)
        {
          String str1 = (String)localIterator.next();
          if (i == 0) {
            paramStringBuilder.append(",");
          }
          paramStringBuilder.append("\"").append(str1).append("\"");
          paramStringBuilder.append(":");
          paramStringBuilder.append("\"").append(zzmu.zzcz(localBundle.getString(str1))).append("\"");
        }
        paramStringBuilder.append("}");
        break;
      case 11: 
        Parcel localParcel = zza.zzE(paramParcel, paramInt);
        localParcel.setDataPosition(0);
        zza(paramStringBuilder, paramField.zzpP(), localParcel);
      }
    }
  }
  
  private void zzb(StringBuilder paramStringBuilder, FastJsonResponse.Field<?, ?> paramField, Object paramObject)
  {
    if (paramField.zzpH()) {
      zzb(paramStringBuilder, paramField, (ArrayList)paramObject);
    }
    for (;;)
    {
      return;
      zza(paramStringBuilder, paramField.zzpB(), paramObject);
    }
  }
  
  private void zzb(StringBuilder paramStringBuilder, FastJsonResponse.Field<?, ?> paramField, ArrayList<?> paramArrayList)
  {
    paramStringBuilder.append("[");
    int i = paramArrayList.size();
    for (int j = 0; j < i; j++)
    {
      if (j != 0) {
        paramStringBuilder.append(",");
      }
      zza(paramStringBuilder, paramField.zzpB(), paramArrayList.get(j));
    }
    paramStringBuilder.append("]");
  }
  
  public static HashMap<String, String> zzi(Bundle paramBundle)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, paramBundle.getString(str));
    }
    return localHashMap;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public String toString()
  {
    zzx.zzb(this.zzahc, "Cannot convert to JSON on client side.");
    Parcel localParcel = zzpV();
    localParcel.setDataPosition(0);
    StringBuilder localStringBuilder = new StringBuilder(100);
    zza(localStringBuilder, this.zzahc.zzcw(this.mClassName), localParcel);
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  protected Object zzcs(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  protected boolean zzct(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  public Map<String, FastJsonResponse.Field<?, ?>> zzpD()
  {
    if (this.zzahc == null) {}
    for (Object localObject = null;; localObject = this.zzahc.zzcw(this.mClassName)) {
      return (Map<String, FastJsonResponse.Field<?, ?>>)localObject;
    }
  }
  
  public Parcel zzpV()
  {
    switch (this.zzahl)
    {
    }
    for (;;)
    {
      return this.zzahj;
      this.zzahm = zzb.zzaq(this.zzahj);
      zzb.zzI(this.zzahj, this.zzahm);
      this.zzahl = 2;
      continue;
      zzb.zzI(this.zzahj, this.zzahm);
      this.zzahl = 2;
    }
  }
  
  FieldMappingDictionary zzpW()
  {
    FieldMappingDictionary localFieldMappingDictionary;
    switch (this.zzahk)
    {
    default: 
      throw new IllegalStateException("Invalid creation type: " + this.zzahk);
    case 0: 
      localFieldMappingDictionary = null;
    }
    for (;;)
    {
      return localFieldMappingDictionary;
      localFieldMappingDictionary = this.zzahc;
      continue;
      localFieldMappingDictionary = this.zzahc;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/server/response/SafeParcelResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */