package crittercism.android;

import java.lang.reflect.Constructor;

public final class l
{
  public static Constructor a(String paramString, String[] paramArrayOfString)
  {
    Constructor[] arrayOfConstructor = Class.forName(paramString).getDeclaredConstructors();
    int i = 0;
    Class[] arrayOfClass;
    int k;
    if (i < arrayOfConstructor.length)
    {
      arrayOfClass = arrayOfConstructor[i].getParameterTypes();
      if (arrayOfClass.length != paramArrayOfString.length)
      {
        k = 0;
        label35:
        if (k == 0) {
          break label95;
        }
      }
    }
    for (Constructor localConstructor = arrayOfConstructor[i];; localConstructor = null)
    {
      return localConstructor;
      for (int j = 0;; j++)
      {
        if (j >= arrayOfClass.length) {
          break label89;
        }
        if (!arrayOfClass[j].getName().equals(paramArrayOfString[j]))
        {
          k = 0;
          break;
        }
      }
      label89:
      k = 1;
      break label35;
      label95:
      i++;
      break;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */