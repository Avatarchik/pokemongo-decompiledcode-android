package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader
  implements Closeable
{
  private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
  private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
  private static final int NUMBER_CHAR_DECIMAL = 3;
  private static final int NUMBER_CHAR_DIGIT = 2;
  private static final int NUMBER_CHAR_EXP_DIGIT = 7;
  private static final int NUMBER_CHAR_EXP_E = 5;
  private static final int NUMBER_CHAR_EXP_SIGN = 6;
  private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
  private static final int NUMBER_CHAR_NONE = 0;
  private static final int NUMBER_CHAR_SIGN = 1;
  private static final int PEEKED_BEGIN_ARRAY = 3;
  private static final int PEEKED_BEGIN_OBJECT = 1;
  private static final int PEEKED_BUFFERED = 11;
  private static final int PEEKED_DOUBLE_QUOTED = 9;
  private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
  private static final int PEEKED_END_ARRAY = 4;
  private static final int PEEKED_END_OBJECT = 2;
  private static final int PEEKED_EOF = 17;
  private static final int PEEKED_FALSE = 6;
  private static final int PEEKED_LONG = 15;
  private static final int PEEKED_NONE = 0;
  private static final int PEEKED_NULL = 7;
  private static final int PEEKED_NUMBER = 16;
  private static final int PEEKED_SINGLE_QUOTED = 8;
  private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
  private static final int PEEKED_TRUE = 5;
  private static final int PEEKED_UNQUOTED = 10;
  private static final int PEEKED_UNQUOTED_NAME = 14;
  private final char[] buffer = new char['Ѐ'];
  private final Reader in;
  private boolean lenient = false;
  private int limit = 0;
  private int lineNumber = 0;
  private int lineStart = 0;
  private int peeked = 0;
  private long peekedLong;
  private int peekedNumberLength;
  private String peekedString;
  private int pos = 0;
  private int[] stack = new int[32];
  private int stackSize = 0;
  
  static
  {
    JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess()
    {
      public void promoteNameToValue(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if ((paramAnonymousJsonReader instanceof JsonTreeReader)) {
          ((JsonTreeReader)paramAnonymousJsonReader).promoteNameToValue();
        }
        for (;;)
        {
          return;
          int i = paramAnonymousJsonReader.peeked;
          if (i == 0) {
            i = paramAnonymousJsonReader.doPeek();
          }
          if (i == 13)
          {
            JsonReader.access$002(paramAnonymousJsonReader, 9);
          }
          else if (i == 12)
          {
            JsonReader.access$002(paramAnonymousJsonReader, 8);
          }
          else
          {
            if (i != 14) {
              break;
            }
            JsonReader.access$002(paramAnonymousJsonReader, 10);
          }
        }
        throw new IllegalStateException("Expected a name but was " + paramAnonymousJsonReader.peek() + " " + " at line " + paramAnonymousJsonReader.getLineNumber() + " column " + paramAnonymousJsonReader.getColumnNumber());
      }
    };
  }
  
  public JsonReader(Reader paramReader)
  {
    int[] arrayOfInt = this.stack;
    int i = this.stackSize;
    this.stackSize = (i + 1);
    arrayOfInt[i] = 6;
    if (paramReader == null) {
      throw new NullPointerException("in == null");
    }
    this.in = paramReader;
  }
  
  private void checkLenient()
    throws IOException
  {
    if (!this.lenient) {
      throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
    }
  }
  
  private void consumeNonExecutePrefix()
    throws IOException
  {
    nextNonWhitespace(true);
    this.pos = (-1 + this.pos);
    if ((this.pos + NON_EXECUTE_PREFIX.length > this.limit) && (!fillBuffer(NON_EXECUTE_PREFIX.length))) {}
    for (;;)
    {
      return;
      for (int i = 0;; i++)
      {
        if (i >= NON_EXECUTE_PREFIX.length) {
          break label80;
        }
        if (this.buffer[(i + this.pos)] != NON_EXECUTE_PREFIX[i]) {
          break;
        }
      }
      label80:
      this.pos += NON_EXECUTE_PREFIX.length;
    }
  }
  
  private int doPeek()
    throws IOException
  {
    int i = 4;
    int j = this.stack[(-1 + this.stackSize)];
    if (j == 1)
    {
      this.stack[(-1 + this.stackSize)] = 2;
      label33:
      switch (nextNonWhitespace(true))
      {
      default: 
        this.pos = (-1 + this.pos);
        if (this.stackSize == 1) {
          checkLenient();
        }
        i = peekKeyword();
        if (i == 0) {
          break;
        }
      }
    }
    for (;;)
    {
      return i;
      if (j == 2)
      {
        switch (nextNonWhitespace(true))
        {
        case 44: 
        default: 
          throw syntaxError("Unterminated array");
        case 93: 
          this.peeked = i;
          break;
        case 59: 
          checkLenient();
          break;
        }
      }
      else
      {
        int k;
        if ((j == 3) || (j == 5))
        {
          this.stack[(-1 + this.stackSize)] = i;
          if (j == 5) {
            switch (nextNonWhitespace(true))
            {
            default: 
              throw syntaxError("Unterminated object");
            case 125: 
              this.peeked = 2;
              i = 2;
              break;
            case 59: 
              checkLenient();
            }
          } else {
            k = nextNonWhitespace(true);
          }
        }
        else
        {
          switch (k)
          {
          default: 
            checkLenient();
            this.pos = (-1 + this.pos);
            if (isLiteral((char)k))
            {
              i = 14;
              this.peeked = i;
            }
            break;
          case 34: 
            i = 13;
            this.peeked = i;
            break;
          case 39: 
            checkLenient();
            i = 12;
            this.peeked = i;
            break;
          case 125: 
            if (j != 5)
            {
              this.peeked = 2;
              i = 2;
            }
            else
            {
              throw syntaxError("Expected name");
              throw syntaxError("Expected name");
              if (j == i)
              {
                this.stack[(-1 + this.stackSize)] = 5;
                switch (nextNonWhitespace(true))
                {
                case 58: 
                case 59: 
                case 60: 
                default: 
                  throw syntaxError("Expected ':'");
                }
                checkLenient();
                if (((this.pos >= this.limit) && (!fillBuffer(1))) || (this.buffer[this.pos] != '>')) {
                  break label33;
                }
                this.pos = (1 + this.pos);
                break label33;
              }
              if (j == 6)
              {
                if (this.lenient) {
                  consumeNonExecutePrefix();
                }
                this.stack[(-1 + this.stackSize)] = 7;
                break label33;
              }
              if (j == 7)
              {
                if (nextNonWhitespace(false) == -1)
                {
                  i = 17;
                  this.peeked = i;
                  continue;
                }
                checkLenient();
                this.pos = (-1 + this.pos);
                break label33;
              }
              if (j != 8) {
                break label33;
              }
              throw new IllegalStateException("JsonReader is closed");
              if (j == 1)
              {
                this.peeked = i;
              }
              else if ((j == 1) || (j == 2))
              {
                checkLenient();
                this.pos = (-1 + this.pos);
                this.peeked = 7;
                i = 7;
              }
              else
              {
                throw syntaxError("Unexpected value");
                checkLenient();
                i = 8;
                this.peeked = i;
                continue;
                if (this.stackSize == 1) {
                  checkLenient();
                }
                i = 9;
                this.peeked = i;
                continue;
                i = 3;
                this.peeked = i;
                continue;
                this.peeked = 1;
                i = 1;
                continue;
                i = peekNumber();
                if (i == 0)
                {
                  if (!isLiteral(this.buffer[this.pos])) {
                    throw syntaxError("Expected value");
                  }
                  checkLenient();
                  i = 10;
                  this.peeked = i;
                }
              }
            }
            break;
          }
        }
      }
    }
  }
  
  private boolean fillBuffer(int paramInt)
    throws IOException
  {
    boolean bool = false;
    char[] arrayOfChar = this.buffer;
    this.lineStart -= this.pos;
    if (this.limit != this.pos)
    {
      this.limit -= this.pos;
      System.arraycopy(arrayOfChar, this.pos, arrayOfChar, 0, this.limit);
    }
    for (;;)
    {
      this.pos = 0;
      do
      {
        int i = this.in.read(arrayOfChar, this.limit, arrayOfChar.length - this.limit);
        if (i == -1) {
          break;
        }
        this.limit = (i + this.limit);
        if ((this.lineNumber == 0) && (this.lineStart == 0) && (this.limit > 0) && (arrayOfChar[0] == 65279))
        {
          this.pos = (1 + this.pos);
          this.lineStart = (1 + this.lineStart);
          paramInt++;
        }
      } while (this.limit < paramInt);
      bool = true;
      return bool;
      this.limit = 0;
    }
  }
  
  private int getColumnNumber()
  {
    return 1 + (this.pos - this.lineStart);
  }
  
  private int getLineNumber()
  {
    return 1 + this.lineNumber;
  }
  
  private boolean isLiteral(char paramChar)
    throws IOException
  {
    switch (paramChar)
    {
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      checkLenient();
    }
  }
  
  private int nextNonWhitespace(boolean paramBoolean)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    int j = this.limit;
    for (;;)
    {
      int k;
      int m;
      if (i == j)
      {
        this.pos = i;
        if (!fillBuffer(1))
        {
          if (paramBoolean) {
            throw new EOFException("End of input at line " + getLineNumber() + " column " + getColumnNumber());
          }
        }
        else
        {
          i = this.pos;
          j = this.limit;
        }
      }
      else
      {
        k = i + 1;
        m = arrayOfChar[i];
        if (m == 10)
        {
          this.lineNumber = (1 + this.lineNumber);
          this.lineStart = k;
          i = k;
          continue;
        }
        if ((m == 32) || (m == 13)) {
          break label376;
        }
        if (m == 9)
        {
          i = k;
          continue;
        }
        if (m == 47)
        {
          this.pos = k;
          if (k == j)
          {
            this.pos = (-1 + this.pos);
            boolean bool = fillBuffer(2);
            this.pos = (1 + this.pos);
            if (bool) {}
          }
        }
      }
      for (;;)
      {
        return m;
        checkLenient();
        switch (arrayOfChar[this.pos])
        {
        default: 
          break;
        case '*': 
          this.pos = (1 + this.pos);
          if (!skipTo("*/")) {
            throw syntaxError("Unterminated comment");
          }
          i = 2 + this.pos;
          j = this.limit;
          break;
        case '/': 
          this.pos = (1 + this.pos);
          skipToEndOfLine();
          i = this.pos;
          j = this.limit;
          break;
          if (m == 35)
          {
            this.pos = k;
            checkLenient();
            skipToEndOfLine();
            i = this.pos;
            j = this.limit;
            break;
          }
          this.pos = k;
          continue;
          m = -1;
        }
      }
      label376:
      i = k;
    }
  }
  
  private String nextQuotedValue(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    StringBuilder localStringBuilder = new StringBuilder();
    do
    {
      int i = this.pos;
      int j = this.limit;
      int k = i;
      int m = i;
      if (m < j)
      {
        int n = m + 1;
        char c = arrayOfChar[m];
        if (c == paramChar)
        {
          this.pos = n;
          localStringBuilder.append(arrayOfChar, k, -1 + (n - k));
          return localStringBuilder.toString();
        }
        if (c == '\\')
        {
          this.pos = n;
          localStringBuilder.append(arrayOfChar, k, -1 + (n - k));
          localStringBuilder.append(readEscapeCharacter());
          n = this.pos;
          j = this.limit;
          k = n;
        }
        for (;;)
        {
          m = n;
          break;
          if (c == '\n')
          {
            this.lineNumber = (1 + this.lineNumber);
            this.lineStart = n;
          }
        }
      }
      localStringBuilder.append(arrayOfChar, k, m - k);
      this.pos = m;
    } while (fillBuffer(1));
    throw syntaxError("Unterminated string");
  }
  
  private String nextUnquotedValue()
    throws IOException
  {
    StringBuilder localStringBuilder = null;
    int i = 0;
    while (i + this.pos < this.limit) {
      switch (this.buffer[(i + this.pos)])
      {
      default: 
        i++;
        break;
      case '#': 
      case '/': 
      case ';': 
      case '=': 
      case '\\': 
        checkLenient();
      case '\t': 
      case '\n': 
      case '\f': 
      case '\r': 
      case ' ': 
      case ',': 
      case ':': 
      case '[': 
      case ']': 
      case '{': 
      case '}': 
        label178:
        if (localStringBuilder != null) {
          break label284;
        }
      }
    }
    for (String str = new String(this.buffer, this.pos, i);; str = localStringBuilder.toString())
    {
      this.pos = (i + this.pos);
      return str;
      if (i < this.buffer.length)
      {
        if (!fillBuffer(i + 1)) {
          break label178;
        }
        break;
      }
      if (localStringBuilder == null) {
        localStringBuilder = new StringBuilder();
      }
      localStringBuilder.append(this.buffer, this.pos, i);
      this.pos = (i + this.pos);
      i = 0;
      if (fillBuffer(1)) {
        break;
      }
      break label178;
      label284:
      localStringBuilder.append(this.buffer, this.pos, i);
    }
  }
  
  private int peekKeyword()
    throws IOException
  {
    int i = this.buffer[this.pos];
    String str1;
    String str2;
    int j;
    int k;
    int m;
    if ((i == 116) || (i == 84))
    {
      str1 = "true";
      str2 = "TRUE";
      j = 5;
      k = str1.length();
      m = 1;
      label42:
      if (m >= k) {
        break label188;
      }
      if ((m + this.pos < this.limit) || (fillBuffer(m + 1))) {
        break label140;
      }
      j = 0;
    }
    for (;;)
    {
      return j;
      if ((i == 102) || (i == 70))
      {
        str1 = "false";
        str2 = "FALSE";
        j = 6;
        break;
      }
      if ((i == 110) || (i == 78))
      {
        str1 = "null";
        str2 = "NULL";
        j = 7;
        break;
      }
      j = 0;
      continue;
      label140:
      int n = this.buffer[(m + this.pos)];
      if ((n != str1.charAt(m)) && (n != str2.charAt(m)))
      {
        j = 0;
      }
      else
      {
        m++;
        break label42;
        label188:
        if (((k + this.pos < this.limit) || (fillBuffer(k + 1))) && (isLiteral(this.buffer[(k + this.pos)])))
        {
          j = 0;
        }
        else
        {
          this.pos = (k + this.pos);
          this.peeked = j;
        }
      }
    }
  }
  
  private int peekNumber()
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    int j = this.limit;
    long l1 = 0L;
    int k = 0;
    int m = 1;
    int n = 0;
    int i1 = 0;
    int i2;
    if (i + i1 == j) {
      if (i1 == arrayOfChar.length) {
        i2 = 0;
      }
    }
    for (;;)
    {
      label48:
      return i2;
      if (!fillBuffer(i1 + 1))
      {
        label62:
        if ((n != 2) || (m == 0) || ((l1 == Long.MIN_VALUE) && (k == 0))) {
          break label467;
        }
        if (k == 0) {
          break label459;
        }
      }
      for (;;)
      {
        this.peekedLong = l1;
        this.pos = (i1 + this.pos);
        i2 = 15;
        this.peeked = i2;
        break label48;
        i = this.pos;
        j = this.limit;
        char c = arrayOfChar[(i + i1)];
        switch (c)
        {
        default: 
          if ((c < '0') || (c > '9'))
          {
            if (!isLiteral(c)) {
              break label62;
            }
            i2 = 0;
          }
          break;
        case '-': 
          if (n == 0)
          {
            k = 1;
            n = 1;
          }
        case '+': 
        case 'E': 
        case 'e': 
        case '.': 
          for (;;)
          {
            i1++;
            break;
            if (n == 5)
            {
              n = 6;
            }
            else
            {
              i2 = 0;
              break label48;
              if (n == 5)
              {
                n = 6;
              }
              else
              {
                i2 = 0;
                break label48;
                if ((n == 2) || (n == 4))
                {
                  n = 5;
                }
                else
                {
                  i2 = 0;
                  break label48;
                  if (n == 2)
                  {
                    n = 3;
                  }
                  else
                  {
                    i2 = 0;
                    break label48;
                    if ((n == 1) || (n == 0))
                    {
                      l1 = -(c - '0');
                      n = 2;
                    }
                    else
                    {
                      if (n == 2)
                      {
                        if (l1 == 0L)
                        {
                          i2 = 0;
                          break label48;
                        }
                        long l2 = 10L * l1 - (c - '0');
                        if ((l1 > -922337203685477580L) || ((l1 == -922337203685477580L) && (l2 < l1))) {}
                        for (int i3 = 1;; i3 = 0)
                        {
                          m &= i3;
                          l1 = l2;
                          break;
                        }
                      }
                      if (n == 3) {
                        n = 4;
                      } else if ((n == 5) || (n == 6)) {
                        n = 7;
                      }
                    }
                  }
                }
              }
            }
          }
          label459:
          l1 = -l1;
        }
      }
      label467:
      if ((n == 2) || (n == 4) || (n == 7))
      {
        this.peekedNumberLength = i1;
        i2 = 16;
        this.peeked = i2;
      }
      else
      {
        i2 = 0;
      }
    }
  }
  
  private void push(int paramInt)
  {
    if (this.stackSize == this.stack.length)
    {
      int[] arrayOfInt2 = new int[2 * this.stackSize];
      System.arraycopy(this.stack, 0, arrayOfInt2, 0, this.stackSize);
      this.stack = arrayOfInt2;
    }
    int[] arrayOfInt1 = this.stack;
    int i = this.stackSize;
    this.stackSize = (i + 1);
    arrayOfInt1[i] = paramInt;
  }
  
  private char readEscapeCharacter()
    throws IOException
  {
    if ((this.pos == this.limit) && (!fillBuffer(1))) {
      throw syntaxError("Unterminated escape sequence");
    }
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    this.pos = (i + 1);
    char c1 = arrayOfChar[i];
    switch (c1)
    {
    }
    for (;;)
    {
      char c2 = c1;
      for (;;)
      {
        return c2;
        if ((4 + this.pos > this.limit) && (!fillBuffer(4))) {
          throw syntaxError("Unterminated escape sequence");
        }
        c2 = '\000';
        int k = this.pos;
        int m = k + 4;
        if (k < m)
        {
          int n = this.buffer[k];
          int i1 = (char)(c2 << '\004');
          if ((n >= 48) && (n <= 57)) {
            j = i1 + (n + -48);
          }
          for (;;)
          {
            k++;
            break;
            if ((n >= 97) && (n <= 102))
            {
              j = i1 + (10 + (n + -97));
            }
            else
            {
              if ((n < 65) || (n > 70)) {
                break label279;
              }
              j = i1 + (10 + (n + -65));
            }
          }
          label279:
          throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
        }
        this.pos = (4 + this.pos);
        continue;
        int j = 9;
        continue;
        j = 8;
        continue;
        j = 10;
        continue;
        j = 13;
        continue;
        j = 12;
      }
      this.lineNumber = (1 + this.lineNumber);
      this.lineStart = this.pos;
    }
  }
  
  private void skipQuotedValue(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    do
    {
      int i = this.pos;
      int j = this.limit;
      int k = i;
      if (k < j)
      {
        int m = k + 1;
        char c = arrayOfChar[k];
        if (c == paramChar)
        {
          this.pos = m;
          return;
        }
        if (c == '\\')
        {
          this.pos = m;
          readEscapeCharacter();
          m = this.pos;
          j = this.limit;
        }
        for (;;)
        {
          k = m;
          break;
          if (c == '\n')
          {
            this.lineNumber = (1 + this.lineNumber);
            this.lineStart = m;
          }
        }
      }
      this.pos = k;
    } while (fillBuffer(1));
    throw syntaxError("Unterminated string");
  }
  
  private boolean skipTo(String paramString)
    throws IOException
  {
    while ((this.pos + paramString.length() <= this.limit) || (fillBuffer(paramString.length()))) {
      if (this.buffer[this.pos] == '\n')
      {
        this.lineNumber = (1 + this.lineNumber);
        this.lineStart = (1 + this.pos);
        this.pos = (1 + this.pos);
      }
      else
      {
        for (int i = 0;; i++)
        {
          if (i >= paramString.length()) {
            break label109;
          }
          if (this.buffer[(i + this.pos)] != paramString.charAt(i)) {
            break;
          }
        }
      }
    }
    label109:
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void skipToEndOfLine()
    throws IOException
  {
    int j;
    if ((this.pos < this.limit) || (fillBuffer(1)))
    {
      char[] arrayOfChar = this.buffer;
      int i = this.pos;
      this.pos = (i + 1);
      j = arrayOfChar[i];
      if (j != 10) {
        break label65;
      }
      this.lineNumber = (1 + this.lineNumber);
      this.lineStart = this.pos;
    }
    for (;;)
    {
      return;
      label65:
      if (j != 13) {
        break;
      }
    }
  }
  
  private void skipUnquotedValue()
    throws IOException
  {
    int i = 0;
    while (i + this.pos < this.limit) {
      switch (this.buffer[(i + this.pos)])
      {
      default: 
        i++;
        break;
      case '#': 
      case '/': 
      case ';': 
      case '=': 
      case '\\': 
        checkLenient();
      case '\t': 
      case '\n': 
      case '\f': 
      case '\r': 
      case ' ': 
      case ',': 
      case ':': 
      case '[': 
      case ']': 
      case '{': 
      case '}': 
        this.pos = (i + this.pos);
      }
    }
    for (;;)
    {
      return;
      this.pos = (i + this.pos);
      if (fillBuffer(1)) {
        break;
      }
    }
  }
  
  private IOException syntaxError(String paramString)
    throws IOException
  {
    throw new MalformedJsonException(paramString + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public void beginArray()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    if (i == 3)
    {
      push(1);
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public void beginObject()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    if (i == 1)
    {
      push(3);
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public void close()
    throws IOException
  {
    this.peeked = 0;
    this.stack[0] = 8;
    this.stackSize = 1;
    this.in.close();
  }
  
  public void endArray()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    if (i == 4)
    {
      this.stackSize = (-1 + this.stackSize);
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected END_ARRAY but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public void endObject()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    if (i == 2)
    {
      this.stackSize = (-1 + this.stackSize);
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected END_OBJECT but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public boolean hasNext()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    if ((i != 2) && (i != 4)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isLenient()
  {
    return this.lenient;
  }
  
  public boolean nextBoolean()
    throws IOException
  {
    boolean bool = false;
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    if (i == 5)
    {
      this.peeked = 0;
      bool = true;
    }
    for (;;)
    {
      return bool;
      if (i != 6) {
        break;
      }
      this.peeked = 0;
    }
    throw new IllegalStateException("Expected a boolean but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public double nextDouble()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    double d;
    if (i == 15)
    {
      this.peeked = 0;
      d = this.peekedLong;
    }
    for (;;)
    {
      return d;
      if (i == 16)
      {
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
      }
      do
      {
        for (;;)
        {
          this.peeked = 11;
          d = Double.parseDouble(this.peekedString);
          if ((this.lenient) || ((!Double.isNaN(d)) && (!Double.isInfinite(d)))) {
            break label281;
          }
          throw new MalformedJsonException("JSON forbids NaN and infinities: " + d + " at line " + getLineNumber() + " column " + getColumnNumber());
          if ((i == 8) || (i == 9))
          {
            if (i == 8) {}
            for (char c = '\'';; c = '"')
            {
              this.peekedString = nextQuotedValue(c);
              break;
            }
          }
          if (i != 10) {
            break;
          }
          this.peekedString = nextUnquotedValue();
        }
      } while (i == 11);
      throw new IllegalStateException("Expected a double but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
      label281:
      this.peekedString = null;
      this.peeked = 0;
    }
  }
  
  public int nextInt()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    int k;
    if (i == 15)
    {
      int n = (int)this.peekedLong;
      if (this.peekedLong != n) {
        throw new NumberFormatException("Expected an int but was " + this.peekedLong + " at line " + getLineNumber() + " column " + getColumnNumber());
      }
      this.peeked = 0;
      k = n;
      return k;
    }
    if (i == 16)
    {
      this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
      this.pos += this.peekedNumberLength;
    }
    for (;;)
    {
      this.peeked = 11;
      double d = Double.parseDouble(this.peekedString);
      int j = (int)d;
      if (j != d)
      {
        throw new NumberFormatException("Expected an int but was " + this.peekedString + " at line " + getLineNumber() + " column " + getColumnNumber());
        if ((i == 8) || (i == 9))
        {
          if (i == 8) {}
          for (char c = '\'';; c = '"')
          {
            this.peekedString = nextQuotedValue(c);
            try
            {
              int m = Integer.parseInt(this.peekedString);
              this.peeked = 0;
              k = m;
            }
            catch (NumberFormatException localNumberFormatException) {}
          }
        }
        throw new IllegalStateException("Expected an int but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
      }
      this.peekedString = null;
      this.peeked = 0;
      k = j;
      break;
    }
  }
  
  public long nextLong()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    long l;
    if (i == 15)
    {
      this.peeked = 0;
      l = this.peekedLong;
    }
    for (;;)
    {
      return l;
      if (i == 16)
      {
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
        label76:
        this.peeked = 11;
        double d = Double.parseDouble(this.peekedString);
        l = d;
        if (l != d) {
          throw new NumberFormatException("Expected a long but was " + this.peekedString + " at line " + getLineNumber() + " column " + getColumnNumber());
        }
      }
      else
      {
        if ((i == 8) || (i == 9))
        {
          if (i == 8) {}
          for (char c = '\'';; c = '"')
          {
            this.peekedString = nextQuotedValue(c);
            try
            {
              l = Long.parseLong(this.peekedString);
              this.peeked = 0;
            }
            catch (NumberFormatException localNumberFormatException) {}
            break label76;
          }
        }
        throw new IllegalStateException("Expected a long but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
      }
      this.peekedString = null;
      this.peeked = 0;
    }
  }
  
  public String nextName()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    String str;
    if (i == 14) {
      str = nextUnquotedValue();
    }
    for (;;)
    {
      this.peeked = 0;
      return str;
      if (i == 12)
      {
        str = nextQuotedValue('\'');
      }
      else
      {
        if (i != 13) {
          break;
        }
        str = nextQuotedValue('"');
      }
    }
    throw new IllegalStateException("Expected a name but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public void nextNull()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    if (i == 7)
    {
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected null but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public String nextString()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    String str;
    if (i == 10) {
      str = nextUnquotedValue();
    }
    for (;;)
    {
      this.peeked = 0;
      return str;
      if (i == 8)
      {
        str = nextQuotedValue('\'');
      }
      else if (i == 9)
      {
        str = nextQuotedValue('"');
      }
      else if (i == 11)
      {
        str = this.peekedString;
        this.peekedString = null;
      }
      else if (i == 15)
      {
        str = Long.toString(this.peekedLong);
      }
      else
      {
        if (i != 16) {
          break;
        }
        str = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
      }
    }
    throw new IllegalStateException("Expected a string but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber());
  }
  
  public JsonToken peek()
    throws IOException
  {
    int i = this.peeked;
    if (i == 0) {
      i = doPeek();
    }
    JsonToken localJsonToken;
    switch (i)
    {
    default: 
      throw new AssertionError();
    case 1: 
      localJsonToken = JsonToken.BEGIN_OBJECT;
    }
    for (;;)
    {
      return localJsonToken;
      localJsonToken = JsonToken.END_OBJECT;
      continue;
      localJsonToken = JsonToken.BEGIN_ARRAY;
      continue;
      localJsonToken = JsonToken.END_ARRAY;
      continue;
      localJsonToken = JsonToken.NAME;
      continue;
      localJsonToken = JsonToken.BOOLEAN;
      continue;
      localJsonToken = JsonToken.NULL;
      continue;
      localJsonToken = JsonToken.STRING;
      continue;
      localJsonToken = JsonToken.NUMBER;
      continue;
      localJsonToken = JsonToken.END_DOCUMENT;
    }
  }
  
  public final void setLenient(boolean paramBoolean)
  {
    this.lenient = paramBoolean;
  }
  
  public void skipValue()
    throws IOException
  {
    int i = 0;
    int j = this.peeked;
    if (j == 0) {
      j = doPeek();
    }
    if (j == 3)
    {
      push(1);
      i++;
    }
    for (;;)
    {
      this.peeked = 0;
      if (i != 0) {
        break;
      }
      return;
      if (j == 1)
      {
        push(3);
        i++;
      }
      else if (j == 4)
      {
        this.stackSize = (-1 + this.stackSize);
        i--;
      }
      else if (j == 2)
      {
        this.stackSize = (-1 + this.stackSize);
        i--;
      }
      else if ((j == 14) || (j == 10))
      {
        skipUnquotedValue();
      }
      else if ((j == 8) || (j == 12))
      {
        skipQuotedValue('\'');
      }
      else if ((j == 9) || (j == 13))
      {
        skipQuotedValue('"');
      }
      else if (j == 16)
      {
        this.pos += this.peekedNumberLength;
      }
    }
  }
  
  public String toString()
  {
    return getClass().getSimpleName() + " at line " + getLineNumber() + " column " + getColumnNumber();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/stream/JsonReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */