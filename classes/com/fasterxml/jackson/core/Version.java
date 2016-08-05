package com.fasterxml.jackson.core;

import java.io.Serializable;

public class Version
  implements Comparable<Version>, Serializable
{
  private static final Version UNKNOWN_VERSION = new Version(0, 0, 0, null, null, null);
  private static final long serialVersionUID = 1L;
  protected final String _artifactId;
  protected final String _groupId;
  protected final int _majorVersion;
  protected final int _minorVersion;
  protected final int _patchLevel;
  protected final String _snapshotInfo;
  
  @Deprecated
  public Version(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    this(paramInt1, paramInt2, paramInt3, paramString, null, null);
  }
  
  public Version(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, String paramString3)
  {
    this._majorVersion = paramInt1;
    this._minorVersion = paramInt2;
    this._patchLevel = paramInt3;
    this._snapshotInfo = paramString1;
    if (paramString2 == null) {
      paramString2 = "";
    }
    this._groupId = paramString2;
    if (paramString3 == null) {
      paramString3 = "";
    }
    this._artifactId = paramString3;
  }
  
  public static Version unknownVersion()
  {
    return UNKNOWN_VERSION;
  }
  
  public int compareTo(Version paramVersion)
  {
    int i;
    if (paramVersion == this) {
      i = 0;
    }
    for (;;)
    {
      return i;
      i = this._groupId.compareTo(paramVersion._groupId);
      if (i == 0)
      {
        i = this._artifactId.compareTo(paramVersion._artifactId);
        if (i == 0)
        {
          i = this._majorVersion - paramVersion._majorVersion;
          if (i == 0)
          {
            i = this._minorVersion - paramVersion._minorVersion;
            if (i == 0) {
              i = this._patchLevel - paramVersion._patchLevel;
            }
          }
        }
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (paramObject == null)
      {
        bool = false;
      }
      else if (paramObject.getClass() != getClass())
      {
        bool = false;
      }
      else
      {
        Version localVersion = (Version)paramObject;
        if ((localVersion._majorVersion != this._majorVersion) || (localVersion._minorVersion != this._minorVersion) || (localVersion._patchLevel != this._patchLevel) || (!localVersion._artifactId.equals(this._artifactId)) || (!localVersion._groupId.equals(this._groupId))) {
          bool = false;
        }
      }
    }
  }
  
  public String getArtifactId()
  {
    return this._artifactId;
  }
  
  public String getGroupId()
  {
    return this._groupId;
  }
  
  public int getMajorVersion()
  {
    return this._majorVersion;
  }
  
  public int getMinorVersion()
  {
    return this._minorVersion;
  }
  
  public int getPatchLevel()
  {
    return this._patchLevel;
  }
  
  public int hashCode()
  {
    return this._artifactId.hashCode() ^ this._groupId.hashCode() + this._majorVersion - this._minorVersion + this._patchLevel;
  }
  
  public boolean isSnapshot()
  {
    if ((this._snapshotInfo != null) && (this._snapshotInfo.length() > 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isUknownVersion()
  {
    if (this == UNKNOWN_VERSION) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String toFullString()
  {
    return this._groupId + '/' + this._artifactId + '/' + toString();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this._majorVersion).append('.');
    localStringBuilder.append(this._minorVersion).append('.');
    localStringBuilder.append(this._patchLevel);
    if (isSnapshot()) {
      localStringBuilder.append('-').append(this._snapshotInfo);
    }
    return localStringBuilder.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/Version.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */