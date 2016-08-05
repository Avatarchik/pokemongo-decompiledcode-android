package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import org.json.JSONException;
import org.json.JSONObject;

public final class WebImage
  implements SafeParcelable
{
  public static final Parcelable.Creator<WebImage> CREATOR = new zzb();
  private final int mVersionCode;
  private final Uri zzaeg;
  private final int zznQ;
  private final int zznR;
  
  WebImage(int paramInt1, Uri paramUri, int paramInt2, int paramInt3)
  {
    this.mVersionCode = paramInt1;
    this.zzaeg = paramUri;
    this.zznQ = paramInt2;
    this.zznR = paramInt3;
  }
  
  public WebImage(Uri paramUri)
    throws IllegalArgumentException
  {
    this(paramUri, 0, 0);
  }
  
  public WebImage(Uri paramUri, int paramInt1, int paramInt2)
    throws IllegalArgumentException
  {
    this(1, paramUri, paramInt1, paramInt2);
    if (paramUri == null) {
      throw new IllegalArgumentException("url cannot be null");
    }
    if ((paramInt1 < 0) || (paramInt2 < 0)) {
      throw new IllegalArgumentException("width and height must not be negative");
    }
  }
  
  public WebImage(JSONObject paramJSONObject)
    throws IllegalArgumentException
  {
    this(zzi(paramJSONObject), paramJSONObject.optInt("width", 0), paramJSONObject.optInt("height", 0));
  }
  
  private static Uri zzi(JSONObject paramJSONObject)
  {
    Object localObject = null;
    if (paramJSONObject.has("url")) {}
    try
    {
      Uri localUri = Uri.parse(paramJSONObject.getString("url"));
      localObject = localUri;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
    return (Uri)localObject;
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
      if ((paramObject == null) || (!(paramObject instanceof WebImage)))
      {
        bool = false;
      }
      else
      {
        WebImage localWebImage = (WebImage)paramObject;
        if ((!zzw.equal(this.zzaeg, localWebImage.zzaeg)) || (this.zznQ != localWebImage.zznQ) || (this.zznR != localWebImage.zznR)) {
          bool = false;
        }
      }
    }
  }
  
  public int getHeight()
  {
    return this.zznR;
  }
  
  public Uri getUrl()
  {
    return this.zzaeg;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int getWidth()
  {
    return this.zznQ;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.zzaeg;
    arrayOfObject[1] = Integer.valueOf(this.zznQ);
    arrayOfObject[2] = Integer.valueOf(this.zznR);
    return zzw.hashCode(arrayOfObject);
  }
  
  public JSONObject toJson()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("url", this.zzaeg.toString());
      localJSONObject.put("width", this.zznQ);
      localJSONObject.put("height", this.zznR);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public String toString()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(this.zznQ);
    arrayOfObject[1] = Integer.valueOf(this.zznR);
    arrayOfObject[2] = this.zzaeg.toString();
    return String.format("Image %dx%d %s", arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/images/WebImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */