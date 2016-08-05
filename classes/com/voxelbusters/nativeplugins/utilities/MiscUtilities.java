package com.voxelbusters.nativeplugins.utilities;

import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import com.voxelbusters.nativeplugins.features.sharing.SharingHelper;
import java.util.Comparator;

public class MiscUtilities
{
  public static Comparator<ResolveInfo> resolveInfoComparator = new Comparator()
  {
    public int compare(ResolveInfo paramAnonymousResolveInfo1, ResolveInfo paramAnonymousResolveInfo2)
    {
      boolean bool1 = SharingHelper.isSocialNetwork(paramAnonymousResolveInfo1.activityInfo.packageName);
      boolean bool2 = SharingHelper.isSocialNetwork(paramAnonymousResolveInfo2.activityInfo.packageName);
      int i;
      if (((bool1) && (bool2)) || (bool1)) {
        i = -1;
      }
      for (;;)
      {
        return i;
        if (bool2) {
          i = 1;
        } else {
          i = 0;
        }
      }
    }
  };
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/voxelbusters/nativeplugins/utilities/MiscUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */