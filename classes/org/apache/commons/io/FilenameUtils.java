package org.apache.commons.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

public class FilenameUtils
{
  public static final char EXTENSION_SEPARATOR = '.';
  public static final String EXTENSION_SEPARATOR_STR = Character.toString('.');
  private static final char OTHER_SEPARATOR = '\000';
  private static final char SYSTEM_SEPARATOR = File.separatorChar;
  private static final char UNIX_SEPARATOR = '/';
  private static final char WINDOWS_SEPARATOR = '\\';
  
  static
  {
    if (isSystemWindows()) {}
    for (OTHER_SEPARATOR = '/';; OTHER_SEPARATOR = '\\') {
      return;
    }
  }
  
  public static String concat(String paramString1, String paramString2)
  {
    String str = null;
    int i = getPrefixLength(paramString2);
    if (i < 0) {}
    for (;;)
    {
      return str;
      if (i > 0)
      {
        str = normalize(paramString2);
      }
      else if (paramString1 != null)
      {
        int j = paramString1.length();
        if (j == 0) {
          str = normalize(paramString2);
        } else if (isSeparator(paramString1.charAt(j - 1))) {
          str = normalize(paramString1 + paramString2);
        } else {
          str = normalize(paramString1 + '/' + paramString2);
        }
      }
    }
  }
  
  public static boolean directoryContains(String paramString1, String paramString2)
    throws IOException
  {
    boolean bool = false;
    if (paramString1 == null) {
      throw new IllegalArgumentException("Directory must not be null");
    }
    if (paramString2 == null) {}
    for (;;)
    {
      return bool;
      if (!IOCase.SYSTEM.checkEquals(paramString1, paramString2)) {
        bool = IOCase.SYSTEM.checkStartsWith(paramString2, paramString1);
      }
    }
  }
  
  private static String doGetFullPath(String paramString, boolean paramBoolean)
  {
    if (paramString == null) {
      paramString = null;
    }
    int j;
    for (;;)
    {
      return paramString;
      int i = getPrefixLength(paramString);
      if (i < 0)
      {
        paramString = null;
      }
      else if (i >= paramString.length())
      {
        if (paramBoolean) {
          paramString = getPrefix(paramString);
        }
      }
      else
      {
        j = indexOfLastSeparator(paramString);
        if (j >= 0) {
          break;
        }
        paramString = paramString.substring(0, i);
      }
    }
    if (paramBoolean) {}
    for (int k = 1;; k = 0)
    {
      int m = j + k;
      if (m == 0) {
        m++;
      }
      paramString = paramString.substring(0, m);
      break;
    }
  }
  
  private static String doGetPath(String paramString, int paramInt)
  {
    String str = null;
    if (paramString == null) {}
    for (;;)
    {
      return str;
      int i = getPrefixLength(paramString);
      if (i >= 0)
      {
        int j = indexOfLastSeparator(paramString);
        int k = j + paramInt;
        if ((i >= paramString.length()) || (j < 0) || (i >= k)) {
          str = "";
        } else {
          str = paramString.substring(i, k);
        }
      }
    }
  }
  
  private static String doNormalize(String paramString, char paramChar, boolean paramBoolean)
  {
    if (paramString == null) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      int i = paramString.length();
      if (i != 0)
      {
        int j = getPrefixLength(paramString);
        if (j < 0)
        {
          paramString = null;
        }
        else
        {
          char[] arrayOfChar = new char[i + 2];
          paramString.getChars(0, paramString.length(), arrayOfChar, 0);
          if (paramChar == SYSTEM_SEPARATOR) {}
          for (int k = OTHER_SEPARATOR;; k = SYSTEM_SEPARATOR) {
            for (int m = 0; m < arrayOfChar.length; m++) {
              if (arrayOfChar[m] == k) {
                arrayOfChar[m] = paramChar;
              }
            }
          }
          int n = 1;
          if (arrayOfChar[(i - 1)] != paramChar)
          {
            int i5 = i + 1;
            arrayOfChar[i] = paramChar;
            n = 0;
            i = i5;
          }
          for (int i1 = j + 1; i1 < i; i1++) {
            if ((arrayOfChar[i1] == paramChar) && (arrayOfChar[(i1 - 1)] == paramChar))
            {
              System.arraycopy(arrayOfChar, i1, arrayOfChar, i1 - 1, i - i1);
              i--;
              i1--;
            }
          }
          for (int i2 = j + 1; i2 < i; i2++) {
            if ((arrayOfChar[i2] == paramChar) && (arrayOfChar[(i2 - 1)] == '.') && ((i2 == j + 1) || (arrayOfChar[(i2 - 2)] == paramChar)))
            {
              if (i2 == i - 1) {
                n = 1;
              }
              System.arraycopy(arrayOfChar, i2 + 1, arrayOfChar, i2 - 1, i - i2);
              i -= 2;
              i2--;
            }
          }
          int i3 = j + 2;
          if (i3 < i)
          {
            int i4;
            if ((arrayOfChar[i3] == paramChar) && (arrayOfChar[(i3 - 1)] == '.') && (arrayOfChar[(i3 - 2)] == '.') && ((i3 == j + 2) || (arrayOfChar[(i3 - 3)] == paramChar)))
            {
              if (i3 == j + 2)
              {
                paramString = null;
                continue;
              }
              if (i3 == i - 1) {
                n = 1;
              }
              i4 = i3 - 4;
              label386:
              if (i4 < j) {
                break label447;
              }
              if (arrayOfChar[i4] != paramChar) {
                break label441;
              }
              System.arraycopy(arrayOfChar, i3 + 1, arrayOfChar, i4 + 1, i - i3);
              i -= i3 - i4;
            }
            for (i3 = i4 + 1;; i3 = j + 1)
            {
              i3++;
              break;
              label441:
              i4--;
              break label386;
              label447:
              System.arraycopy(arrayOfChar, i3 + 1, arrayOfChar, j, i - i3);
              i -= i3 + 1 - j;
            }
          }
          else if (i <= 0)
          {
            paramString = "";
          }
          else if (i <= j)
          {
            paramString = new String(arrayOfChar, 0, i);
          }
          else if ((n != 0) && (paramBoolean))
          {
            paramString = new String(arrayOfChar, 0, i);
          }
          else
          {
            paramString = new String(arrayOfChar, 0, i - 1);
          }
        }
      }
    }
  }
  
  public static boolean equals(String paramString1, String paramString2)
  {
    return equals(paramString1, paramString2, false, IOCase.SENSITIVE);
  }
  
  public static boolean equals(String paramString1, String paramString2, boolean paramBoolean, IOCase paramIOCase)
  {
    boolean bool;
    if ((paramString1 == null) || (paramString2 == null)) {
      if ((paramString1 == null) && (paramString2 == null)) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      if (paramBoolean)
      {
        paramString1 = normalize(paramString1);
        paramString2 = normalize(paramString2);
        if ((paramString1 == null) || (paramString2 == null)) {
          throw new NullPointerException("Error normalizing one or both of the file names");
        }
      }
      if (paramIOCase == null) {
        paramIOCase = IOCase.SENSITIVE;
      }
      bool = paramIOCase.checkEquals(paramString1, paramString2);
    }
  }
  
  public static boolean equalsNormalized(String paramString1, String paramString2)
  {
    return equals(paramString1, paramString2, true, IOCase.SENSITIVE);
  }
  
  public static boolean equalsNormalizedOnSystem(String paramString1, String paramString2)
  {
    return equals(paramString1, paramString2, true, IOCase.SYSTEM);
  }
  
  public static boolean equalsOnSystem(String paramString1, String paramString2)
  {
    return equals(paramString1, paramString2, false, IOCase.SYSTEM);
  }
  
  public static String getBaseName(String paramString)
  {
    return removeExtension(getName(paramString));
  }
  
  public static String getExtension(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    for (;;)
    {
      return str;
      int i = indexOfExtension(paramString);
      if (i == -1) {
        str = "";
      } else {
        str = paramString.substring(i + 1);
      }
    }
  }
  
  public static String getFullPath(String paramString)
  {
    return doGetFullPath(paramString, true);
  }
  
  public static String getFullPathNoEndSeparator(String paramString)
  {
    return doGetFullPath(paramString, false);
  }
  
  public static String getName(String paramString)
  {
    if (paramString == null) {}
    for (String str = null;; str = paramString.substring(1 + indexOfLastSeparator(paramString))) {
      return str;
    }
  }
  
  public static String getPath(String paramString)
  {
    return doGetPath(paramString, 1);
  }
  
  public static String getPathNoEndSeparator(String paramString)
  {
    return doGetPath(paramString, 0);
  }
  
  public static String getPrefix(String paramString)
  {
    String str = null;
    if (paramString == null) {}
    for (;;)
    {
      return str;
      int i = getPrefixLength(paramString);
      if (i >= 0) {
        if (i > paramString.length()) {
          str = paramString + '/';
        } else {
          str = paramString.substring(0, i);
        }
      }
    }
  }
  
  public static int getPrefixLength(String paramString)
  {
    int i = 1;
    if (paramString == null) {
      i = -1;
    }
    for (;;)
    {
      return i;
      int j = paramString.length();
      if (j == 0)
      {
        i = 0;
      }
      else
      {
        char c1 = paramString.charAt(0);
        if (c1 == ':')
        {
          i = -1;
        }
        else if (j == i)
        {
          if (c1 == '~') {
            i = 2;
          } else if (!isSeparator(c1)) {
            i = 0;
          }
        }
        else if (c1 == '~')
        {
          int i1 = paramString.indexOf('/', i);
          int i2 = paramString.indexOf('\\', i);
          if ((i1 == -1) && (i2 == -1))
          {
            i = j + 1;
          }
          else
          {
            if (i1 == -1) {
              i1 = i2;
            }
            if (i2 == -1) {
              i2 = i1;
            }
            i = 1 + Math.min(i1, i2);
          }
        }
        else
        {
          char c2 = paramString.charAt(i);
          if (c2 == ':')
          {
            int n = Character.toUpperCase(c1);
            if ((n >= 65) && (n <= 90))
            {
              if ((j == 2) || (!isSeparator(paramString.charAt(2)))) {
                i = 2;
              } else {
                i = 3;
              }
            }
            else {
              i = -1;
            }
          }
          else if ((isSeparator(c1)) && (isSeparator(c2)))
          {
            int k = paramString.indexOf('/', 2);
            int m = paramString.indexOf('\\', 2);
            if (((k == -1) && (m == -1)) || (k == 2) || (m == 2))
            {
              i = -1;
            }
            else
            {
              if (k == -1) {
                k = m;
              }
              if (m == -1) {
                m = k;
              }
              i = 1 + Math.min(k, m);
            }
          }
          else if (!isSeparator(c1))
          {
            i = 0;
          }
        }
      }
    }
  }
  
  public static int indexOfExtension(String paramString)
  {
    int i = -1;
    if (paramString == null) {}
    for (;;)
    {
      return i;
      int j = paramString.lastIndexOf('.');
      if (indexOfLastSeparator(paramString) > j) {
        j = i;
      }
      i = j;
    }
  }
  
  public static int indexOfLastSeparator(String paramString)
  {
    if (paramString == null) {}
    for (int i = -1;; i = Math.max(paramString.lastIndexOf('/'), paramString.lastIndexOf('\\'))) {
      return i;
    }
  }
  
  public static boolean isExtension(String paramString1, String paramString2)
  {
    boolean bool = false;
    if (paramString1 == null) {}
    for (;;)
    {
      return bool;
      if ((paramString2 == null) || (paramString2.length() == 0))
      {
        if (indexOfExtension(paramString1) == -1) {
          bool = true;
        }
      }
      else {
        bool = getExtension(paramString1).equals(paramString2);
      }
    }
  }
  
  public static boolean isExtension(String paramString, Collection<String> paramCollection)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramString == null) {
      break label56;
    }
    for (;;)
    {
      return bool2;
      if ((paramCollection == null) || (paramCollection.isEmpty()))
      {
        if (indexOfExtension(paramString) == -1) {}
        for (;;)
        {
          bool2 = bool1;
          break;
          bool1 = false;
        }
      }
      String str = getExtension(paramString);
      Iterator localIterator = paramCollection.iterator();
      label56:
      if (localIterator.hasNext())
      {
        if (!str.equals((String)localIterator.next())) {
          break;
        }
        bool2 = bool1;
      }
    }
  }
  
  public static boolean isExtension(String paramString, String[] paramArrayOfString)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramString == null) {}
    label79:
    for (;;)
    {
      return bool2;
      if ((paramArrayOfString == null) || (paramArrayOfString.length == 0))
      {
        if (indexOfExtension(paramString) == -1) {}
        for (;;)
        {
          bool2 = bool1;
          break;
          bool1 = false;
        }
      }
      String str = getExtension(paramString);
      int i = paramArrayOfString.length;
      for (int j = 0;; j++)
      {
        if (j >= i) {
          break label79;
        }
        if (str.equals(paramArrayOfString[j]))
        {
          bool2 = bool1;
          break;
        }
      }
    }
  }
  
  private static boolean isSeparator(char paramChar)
  {
    if ((paramChar == '/') || (paramChar == '\\')) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static boolean isSystemWindows()
  {
    if (SYSTEM_SEPARATOR == '\\') {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static String normalize(String paramString)
  {
    return doNormalize(paramString, SYSTEM_SEPARATOR, true);
  }
  
  public static String normalize(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (char c = '/';; c = '\\') {
      return doNormalize(paramString, c, true);
    }
  }
  
  public static String normalizeNoEndSeparator(String paramString)
  {
    return doNormalize(paramString, SYSTEM_SEPARATOR, false);
  }
  
  public static String normalizeNoEndSeparator(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (char c = '/';; c = '\\') {
      return doNormalize(paramString, c, false);
    }
  }
  
  public static String removeExtension(String paramString)
  {
    if (paramString == null) {
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      int i = indexOfExtension(paramString);
      if (i != -1) {
        paramString = paramString.substring(0, i);
      }
    }
  }
  
  public static String separatorsToSystem(String paramString)
  {
    String str;
    if (paramString == null) {
      str = null;
    }
    for (;;)
    {
      return str;
      if (isSystemWindows()) {
        str = separatorsToWindows(paramString);
      } else {
        str = separatorsToUnix(paramString);
      }
    }
  }
  
  public static String separatorsToUnix(String paramString)
  {
    if ((paramString == null) || (paramString.indexOf('\\') == -1)) {}
    for (;;)
    {
      return paramString;
      paramString = paramString.replace('\\', '/');
    }
  }
  
  public static String separatorsToWindows(String paramString)
  {
    if ((paramString == null) || (paramString.indexOf('/') == -1)) {}
    for (;;)
    {
      return paramString;
      paramString = paramString.replace('/', '\\');
    }
  }
  
  static String[] splitOnTokens(String paramString)
  {
    String[] arrayOfString;
    if ((paramString.indexOf('?') == -1) && (paramString.indexOf('*') == -1))
    {
      arrayOfString = new String[1];
      arrayOfString[0] = paramString;
    }
    for (;;)
    {
      return arrayOfString;
      char[] arrayOfChar = paramString.toCharArray();
      ArrayList localArrayList = new ArrayList();
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 0;
      if (i < arrayOfChar.length)
      {
        if ((arrayOfChar[i] == '?') || (arrayOfChar[i] == '*'))
        {
          if (localStringBuilder.length() != 0)
          {
            localArrayList.add(localStringBuilder.toString());
            localStringBuilder.setLength(0);
          }
          if (arrayOfChar[i] == '?') {
            localArrayList.add("?");
          }
        }
        for (;;)
        {
          i++;
          break;
          if ((localArrayList.isEmpty()) || ((i > 0) && (!((String)localArrayList.get(-1 + localArrayList.size())).equals("*"))))
          {
            localArrayList.add("*");
            continue;
            localStringBuilder.append(arrayOfChar[i]);
          }
        }
      }
      if (localStringBuilder.length() != 0) {
        localArrayList.add(localStringBuilder.toString());
      }
      arrayOfString = (String[])localArrayList.toArray(new String[localArrayList.size()]);
    }
  }
  
  public static boolean wildcardMatch(String paramString1, String paramString2)
  {
    return wildcardMatch(paramString1, paramString2, IOCase.SENSITIVE);
  }
  
  public static boolean wildcardMatch(String paramString1, String paramString2, IOCase paramIOCase)
  {
    boolean bool = true;
    if ((paramString1 == null) && (paramString2 == null)) {}
    label57:
    label153:
    label162:
    label199:
    label306:
    for (;;)
    {
      return bool;
      if ((paramString1 == null) || (paramString2 == null))
      {
        bool = false;
      }
      else
      {
        if (paramIOCase == null) {
          paramIOCase = IOCase.SENSITIVE;
        }
        String[] arrayOfString = splitOnTokens(paramString2);
        int i = 0;
        int j = 0;
        int k = 0;
        Stack localStack = new Stack();
        if (localStack.size() > 0)
        {
          int[] arrayOfInt2 = (int[])localStack.pop();
          k = arrayOfInt2[0];
          j = arrayOfInt2[bool];
          i = 1;
        }
        if (k < arrayOfString.length)
        {
          if (!arrayOfString[k].equals("?")) {
            break label162;
          }
          j++;
          if (j <= paramString1.length()) {
            break label153;
          }
        }
        for (;;)
        {
          if ((k == arrayOfString.length) && (j == paramString1.length())) {
            break label306;
          }
          if (localStack.size() > 0) {
            break label57;
          }
          bool = false;
          break;
          i = 0;
          for (;;)
          {
            k++;
            break;
            if (!arrayOfString[k].equals("*")) {
              break label199;
            }
            i = 1;
            if (k == -1 + arrayOfString.length) {
              j = paramString1.length();
            }
          }
          if (i != 0)
          {
            j = paramIOCase.checkIndexOf(paramString1, j, arrayOfString[k]);
            if (j == -1) {
              continue;
            }
            m = paramIOCase.checkIndexOf(paramString1, j + 1, arrayOfString[k]);
            if (m >= 0)
            {
              arrayOfInt1 = new int[2];
              arrayOfInt1[0] = k;
              arrayOfInt1[bool] = m;
              localStack.push(arrayOfInt1);
            }
          }
          while (paramIOCase.checkRegionMatches(paramString1, j, arrayOfString[k]))
          {
            int m;
            int[] arrayOfInt1;
            j += arrayOfString[k].length();
            i = 0;
            break;
          }
        }
      }
    }
  }
  
  public static boolean wildcardMatchOnSystem(String paramString1, String paramString2)
  {
    return wildcardMatch(paramString1, paramString2, IOCase.SYSTEM);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/FilenameUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */