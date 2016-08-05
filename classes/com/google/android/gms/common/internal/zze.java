package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class zze
{
  public static final zze zzaeL = zza("\t\n\013\f\r     　 ᠎ ").zza(zza(' ', ' '));
  public static final zze zzaeM = zza("\t\n\013\f\r     　").zza(zza(' ', ' ')).zza(zza(' ', ' '));
  public static final zze zzaeN = zza('\000', '');
  public static final zze zzaeO;
  public static final zze zzaeP = zza('\t', '\r').zza(zza('\034', ' ')).zza(zzc(' ')).zza(zzc('᠎')).zza(zza(' ', ' ')).zza(zza(' ', '​')).zza(zza(' ', ' ')).zza(zzc(' ')).zza(zzc('　'));
  public static final zze zzaeQ = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isDigit(paramAnonymousChar);
    }
  };
  public static final zze zzaeR = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isLetter(paramAnonymousChar);
    }
  };
  public static final zze zzaeS = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isLetterOrDigit(paramAnonymousChar);
    }
  };
  public static final zze zzaeT = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isUpperCase(paramAnonymousChar);
    }
  };
  public static final zze zzaeU = new zze()
  {
    public boolean zzd(char paramAnonymousChar)
    {
      return Character.isLowerCase(paramAnonymousChar);
    }
  };
  public static final zze zzaeV = zza('\000', '\037').zza(zza('', ''));
  public static final zze zzaeW = zza('\000', ' ').zza(zza('', ' ')).zza(zzc('­')).zza(zza('؀', '؃')).zza(zza("۝܏ ឴឵᠎")).zza(zza(' ', '‏')).zza(zza(' ', ' ')).zza(zza(' ', '⁤')).zza(zza('⁪', '⁯')).zza(zzc('　')).zza(zza(55296, 63743)).zza(zza("﻿￹￺￻"));
  public static final zze zzaeX = zza('\000', 'ӹ').zza(zzc('־')).zza(zza('א', 'ת')).zza(zzc('׳')).zza(zzc('״')).zza(zza('؀', 'ۿ')).zza(zza('ݐ', 'ݿ')).zza(zza('฀', '๿')).zza(zza('Ḁ', '₯')).zza(zza('℀', '℺')).zza(zza(64336, 65023)).zza(zza(65136, 65279)).zza(zza(65377, 65500));
  public static final zze zzaeY = new zze()
  {
    public zze zza(zze paramAnonymouszze)
    {
      zzx.zzw(paramAnonymouszze);
      return this;
    }
    
    public boolean zzb(CharSequence paramAnonymousCharSequence)
    {
      zzx.zzw(paramAnonymousCharSequence);
      return true;
    }
    
    public boolean zzd(char paramAnonymousChar)
    {
      return true;
    }
  };
  public static final zze zzaeZ = new zze()
  {
    public zze zza(zze paramAnonymouszze)
    {
      return (zze)zzx.zzw(paramAnonymouszze);
    }
    
    public boolean zzb(CharSequence paramAnonymousCharSequence)
    {
      if (paramAnonymousCharSequence.length() == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean zzd(char paramAnonymousChar)
    {
      return false;
    }
  };
  
  static
  {
    zze localzze1 = zza('0', '9');
    char[] arrayOfChar = "٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０".toCharArray();
    int i = arrayOfChar.length;
    zze localzze2 = localzze1;
    for (int j = 0; j < i; j++)
    {
      char c = arrayOfChar[j];
      localzze2 = localzze2.zza(zza(c, c + '\t'));
    }
    zzaeO = localzze2;
  }
  
  public static zze zza(char paramChar1, final char paramChar2)
  {
    if (paramChar2 >= paramChar1) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzaa(bool);
      new zze()
      {
        public boolean zzd(char paramAnonymousChar)
        {
          if ((this.zzafd <= paramAnonymousChar) && (paramAnonymousChar <= paramChar2)) {}
          for (boolean bool = true;; bool = false) {
            return bool;
          }
        }
      };
    }
  }
  
  public static zze zza(CharSequence paramCharSequence)
  {
    Object localObject;
    switch (paramCharSequence.length())
    {
    default: 
      char[] arrayOfChar = paramCharSequence.toString().toCharArray();
      Arrays.sort(arrayOfChar);
      localObject = new zze()
      {
        public boolean zzd(char paramAnonymousChar)
        {
          if (Arrays.binarySearch(zze.this, paramAnonymousChar) >= 0) {}
          for (boolean bool = true;; bool = false) {
            return bool;
          }
        }
      };
    }
    for (;;)
    {
      return (zze)localObject;
      localObject = zzaeZ;
      continue;
      localObject = zzc(paramCharSequence.charAt(0));
      continue;
      localObject = new zze()
      {
        public boolean zzd(char paramAnonymousChar)
        {
          if ((paramAnonymousChar == this.zzafa) || (paramAnonymousChar == this.zzafb)) {}
          for (boolean bool = true;; bool = false) {
            return bool;
          }
        }
      };
    }
  }
  
  public static zze zzc(char paramChar)
  {
    new zze()
    {
      public zze zza(zze paramAnonymouszze)
      {
        if (paramAnonymouszze.zzd(this.zzaff)) {}
        for (;;)
        {
          return paramAnonymouszze;
          paramAnonymouszze = super.zza(paramAnonymouszze);
        }
      }
      
      public boolean zzd(char paramAnonymousChar)
      {
        if (paramAnonymousChar == this.zzaff) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
    };
  }
  
  public zze zza(zze paramzze)
  {
    zze[] arrayOfzze = new zze[2];
    arrayOfzze[0] = this;
    arrayOfzze[1] = ((zze)zzx.zzw(paramzze));
    return new zza(Arrays.asList(arrayOfzze));
  }
  
  public boolean zzb(CharSequence paramCharSequence)
  {
    int i = -1 + paramCharSequence.length();
    if (i >= 0) {
      if (zzd(paramCharSequence.charAt(i))) {}
    }
    for (boolean bool = false;; bool = true)
    {
      return bool;
      i--;
      break;
    }
  }
  
  public abstract boolean zzd(char paramChar);
  
  private static class zza
    extends zze
  {
    List<zze> zzafg;
    
    zza(List<zze> paramList)
    {
      this.zzafg = paramList;
    }
    
    public zze zza(zze paramzze)
    {
      ArrayList localArrayList = new ArrayList(this.zzafg);
      localArrayList.add(zzx.zzw(paramzze));
      return new zza(localArrayList);
    }
    
    public boolean zzd(char paramChar)
    {
      Iterator localIterator = this.zzafg.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!((zze)localIterator.next()).zzd(paramChar));
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */