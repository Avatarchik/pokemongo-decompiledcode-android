package android.support.v4.text;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Locale;

public class TextUtilsCompat
{
  private static String ARAB_SCRIPT_SUBTAG;
  private static String HEBR_SCRIPT_SUBTAG;
  private static final TextUtilsCompatImpl IMPL;
  public static final Locale ROOT;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 17) {}
    for (IMPL = new TextUtilsCompatJellybeanMr1Impl(null);; IMPL = new TextUtilsCompatImpl(null))
    {
      ROOT = new Locale("", "");
      ARAB_SCRIPT_SUBTAG = "Arab";
      HEBR_SCRIPT_SUBTAG = "Hebr";
      return;
    }
  }
  
  public static int getLayoutDirectionFromLocale(@Nullable Locale paramLocale)
  {
    return IMPL.getLayoutDirectionFromLocale(paramLocale);
  }
  
  @NonNull
  public static String htmlEncode(@NonNull String paramString)
  {
    return IMPL.htmlEncode(paramString);
  }
  
  private static class TextUtilsCompatJellybeanMr1Impl
    extends TextUtilsCompat.TextUtilsCompatImpl
  {
    private TextUtilsCompatJellybeanMr1Impl()
    {
      super();
    }
    
    public int getLayoutDirectionFromLocale(@Nullable Locale paramLocale)
    {
      return TextUtilsCompatJellybeanMr1.getLayoutDirectionFromLocale(paramLocale);
    }
    
    @NonNull
    public String htmlEncode(@NonNull String paramString)
    {
      return TextUtilsCompatJellybeanMr1.htmlEncode(paramString);
    }
  }
  
  private static class TextUtilsCompatImpl
  {
    private static int getLayoutDirectionFromFirstChar(@NonNull Locale paramLocale)
    {
      int i = 0;
      switch (Character.getDirectionality(paramLocale.getDisplayName(paramLocale).charAt(0)))
      {
      }
      for (;;)
      {
        return i;
        i = 1;
      }
    }
    
    public int getLayoutDirectionFromLocale(@Nullable Locale paramLocale)
    {
      String str;
      int i;
      if ((paramLocale != null) && (!paramLocale.equals(TextUtilsCompat.ROOT)))
      {
        str = ICUCompat.maximizeAndGetScript(paramLocale);
        if (str == null) {
          i = getLayoutDirectionFromFirstChar(paramLocale);
        }
      }
      for (;;)
      {
        return i;
        if ((str.equalsIgnoreCase(TextUtilsCompat.ARAB_SCRIPT_SUBTAG)) || (str.equalsIgnoreCase(TextUtilsCompat.HEBR_SCRIPT_SUBTAG))) {
          i = 1;
        } else {
          i = 0;
        }
      }
    }
    
    @NonNull
    public String htmlEncode(@NonNull String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 0;
      if (i < paramString.length())
      {
        char c = paramString.charAt(i);
        switch (c)
        {
        default: 
          localStringBuilder.append(c);
        }
        for (;;)
        {
          i++;
          break;
          localStringBuilder.append("&lt;");
          continue;
          localStringBuilder.append("&gt;");
          continue;
          localStringBuilder.append("&amp;");
          continue;
          localStringBuilder.append("&#39;");
          continue;
          localStringBuilder.append("&quot;");
        }
      }
      return localStringBuilder.toString();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/text/TextUtilsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */