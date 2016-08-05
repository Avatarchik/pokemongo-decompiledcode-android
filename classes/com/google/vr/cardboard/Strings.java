package com.google.vr.cardboard;

import java.util.Locale;
import java.util.Map;

public class Strings
{
  public static final String CANCEL_BUTTON = "CANCEL_BUTTON";
  public static final String DIALOG_MESSAGE_NO_CARDBOARD = "DIALOG_MESSAGE_NO_CARDBOARD";
  public static final String DIALOG_MESSAGE_SETUP = "DIALOG_MESSAGE_SETUP";
  public static final String DIALOG_TITLE = "DIALOG_TITLE";
  public static final String GO_TO_PLAYSTORE_BUTTON = "GO_TO_PLAYSTORE_BUTTON";
  private static final Map<String, Map<String, String>> LANGUAGE_MAP;
  public static final String NO_BROWSER_TEXT = "NO_BROWSER_TEXT";
  public static final String SETUP_BUTTON = "SETUP_BUTTON";
  
  private static Map<String, String> getLanguageMap(Locale paramLocale)
  {
    String str = paramLocale.getLanguage();
    if (LANGUAGE_MAP.containsKey(str)) {}
    for (Map localMap = (Map)LANGUAGE_MAP.get(str);; localMap = (Map)LANGUAGE_MAP.get("en")) {
      return localMap;
    }
  }
  
  public static String getString(String paramString)
  {
    return getString(paramString, Locale.getDefault());
  }
  
  private static String getString(String paramString, Locale paramLocale)
  {
    Map localMap = getLanguageMap(paramLocale);
    if (localMap.containsKey(paramString)) {}
    for (String str = (String)localMap.get(paramString);; str = (String)((Map)LANGUAGE_MAP.get("en")).get(paramString)) {
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/vr/cardboard/Strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */