package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public final class TextBuffer
{
  static final int MAX_SEGMENT_LEN = 262144;
  static final int MIN_SEGMENT_LEN = 1000;
  static final char[] NO_CHARS = new char[0];
  private final BufferRecycler _allocator;
  private char[] _currentSegment;
  private int _currentSize;
  private boolean _hasSegments = false;
  private char[] _inputBuffer;
  private int _inputLen;
  private int _inputStart;
  private char[] _resultArray;
  private String _resultString;
  private int _segmentSize;
  private ArrayList<char[]> _segments;
  
  public TextBuffer(BufferRecycler paramBufferRecycler)
  {
    this._allocator = paramBufferRecycler;
  }
  
  private char[] buf(int paramInt)
  {
    if (this._allocator != null) {}
    for (char[] arrayOfChar = this._allocator.allocCharBuffer(2, paramInt);; arrayOfChar = new char[Math.max(paramInt, 1000)]) {
      return arrayOfChar;
    }
  }
  
  private char[] carr(int paramInt)
  {
    return new char[paramInt];
  }
  
  private void clearSegments()
  {
    this._hasSegments = false;
    this._segments.clear();
    this._segmentSize = 0;
    this._currentSize = 0;
  }
  
  private void expand(int paramInt)
  {
    if (this._segments == null) {
      this._segments = new ArrayList();
    }
    char[] arrayOfChar = this._currentSegment;
    this._hasSegments = true;
    this._segments.add(arrayOfChar);
    this._segmentSize += arrayOfChar.length;
    this._currentSize = 0;
    int i = arrayOfChar.length;
    int j = i + (i >> 1);
    if (j < 1000) {
      j = 1000;
    }
    for (;;)
    {
      this._currentSegment = carr(j);
      return;
      if (j > 262144) {
        j = 262144;
      }
    }
  }
  
  private char[] resultArray()
  {
    char[] arrayOfChar1;
    if (this._resultString != null) {
      arrayOfChar1 = this._resultString.toCharArray();
    }
    for (;;)
    {
      return arrayOfChar1;
      if (this._inputStart >= 0)
      {
        int i1 = this._inputLen;
        if (i1 < 1)
        {
          arrayOfChar1 = NO_CHARS;
        }
        else
        {
          int i2 = this._inputStart;
          if (i2 == 0) {
            arrayOfChar1 = Arrays.copyOf(this._inputBuffer, i1);
          } else {
            arrayOfChar1 = Arrays.copyOfRange(this._inputBuffer, i2, i2 + i1);
          }
        }
      }
      else
      {
        int i = size();
        if (i < 1)
        {
          arrayOfChar1 = NO_CHARS;
        }
        else
        {
          int j = 0;
          arrayOfChar1 = carr(i);
          if (this._segments != null)
          {
            int k = 0;
            int m = this._segments.size();
            while (k < m)
            {
              char[] arrayOfChar2 = (char[])this._segments.get(k);
              int n = arrayOfChar2.length;
              System.arraycopy(arrayOfChar2, 0, arrayOfChar1, j, n);
              j += n;
              k++;
            }
          }
          System.arraycopy(this._currentSegment, 0, arrayOfChar1, j, this._currentSize);
        }
      }
    }
  }
  
  private void unshare(int paramInt)
  {
    int i = this._inputLen;
    this._inputLen = 0;
    char[] arrayOfChar = this._inputBuffer;
    this._inputBuffer = null;
    int j = this._inputStart;
    this._inputStart = -1;
    int k = i + paramInt;
    if ((this._currentSegment == null) || (k > this._currentSegment.length)) {
      this._currentSegment = buf(k);
    }
    if (i > 0) {
      System.arraycopy(arrayOfChar, j, this._currentSegment, 0, i);
    }
    this._segmentSize = 0;
    this._currentSize = i;
  }
  
  public void append(char paramChar)
  {
    if (this._inputStart >= 0) {
      unshare(16);
    }
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar = this._currentSegment;
    if (this._currentSize >= arrayOfChar.length)
    {
      expand(1);
      arrayOfChar = this._currentSegment;
    }
    int i = this._currentSize;
    this._currentSize = (i + 1);
    arrayOfChar[i] = paramChar;
  }
  
  public void append(String paramString, int paramInt1, int paramInt2)
  {
    if (this._inputStart >= 0) {
      unshare(paramInt2);
    }
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar = this._currentSegment;
    int i = arrayOfChar.length - this._currentSize;
    if (i >= paramInt2)
    {
      paramString.getChars(paramInt1, paramInt1 + paramInt2, arrayOfChar, this._currentSize);
      this._currentSize = (paramInt2 + this._currentSize);
    }
    for (;;)
    {
      return;
      if (i > 0)
      {
        paramString.getChars(paramInt1, paramInt1 + i, arrayOfChar, this._currentSize);
        paramInt2 -= i;
        paramInt1 += i;
      }
      do
      {
        expand(paramInt2);
        int j = Math.min(this._currentSegment.length, paramInt2);
        paramString.getChars(paramInt1, paramInt1 + j, this._currentSegment, 0);
        this._currentSize = (j + this._currentSize);
        paramInt1 += j;
        paramInt2 -= j;
      } while (paramInt2 > 0);
    }
  }
  
  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this._inputStart >= 0) {
      unshare(paramInt2);
    }
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar = this._currentSegment;
    int i = arrayOfChar.length - this._currentSize;
    if (i >= paramInt2)
    {
      System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, this._currentSize, paramInt2);
      this._currentSize = (paramInt2 + this._currentSize);
    }
    for (;;)
    {
      return;
      if (i > 0)
      {
        System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, this._currentSize, i);
        paramInt1 += i;
        paramInt2 -= i;
      }
      do
      {
        expand(paramInt2);
        int j = Math.min(this._currentSegment.length, paramInt2);
        System.arraycopy(paramArrayOfChar, paramInt1, this._currentSegment, 0, j);
        this._currentSize = (j + this._currentSize);
        paramInt1 += j;
        paramInt2 -= j;
      } while (paramInt2 > 0);
    }
  }
  
  public char[] contentsAsArray()
  {
    char[] arrayOfChar = this._resultArray;
    if (arrayOfChar == null)
    {
      arrayOfChar = resultArray();
      this._resultArray = arrayOfChar;
    }
    return arrayOfChar;
  }
  
  public BigDecimal contentsAsDecimal()
    throws NumberFormatException
  {
    BigDecimal localBigDecimal;
    if (this._resultArray != null) {
      localBigDecimal = NumberInput.parseBigDecimal(this._resultArray);
    }
    for (;;)
    {
      return localBigDecimal;
      if ((this._inputStart >= 0) && (this._inputBuffer != null)) {
        localBigDecimal = NumberInput.parseBigDecimal(this._inputBuffer, this._inputStart, this._inputLen);
      } else if ((this._segmentSize == 0) && (this._currentSegment != null)) {
        localBigDecimal = NumberInput.parseBigDecimal(this._currentSegment, 0, this._currentSize);
      } else {
        localBigDecimal = NumberInput.parseBigDecimal(contentsAsArray());
      }
    }
  }
  
  public double contentsAsDouble()
    throws NumberFormatException
  {
    return NumberInput.parseDouble(contentsAsString());
  }
  
  public String contentsAsString()
  {
    if (this._resultString == null)
    {
      if (this._resultArray == null) {
        break label36;
      }
      this._resultString = new String(this._resultArray);
    }
    for (;;)
    {
      String str1 = this._resultString;
      for (;;)
      {
        return str1;
        label36:
        if (this._inputStart < 0) {
          break label88;
        }
        if (this._inputLen >= 1) {
          break;
        }
        str1 = "";
        this._resultString = str1;
      }
      this._resultString = new String(this._inputBuffer, this._inputStart, this._inputLen);
      continue;
      label88:
      int i = this._segmentSize;
      int j = this._currentSize;
      if (i == 0)
      {
        if (j == 0) {}
        for (String str2 = "";; str2 = new String(this._currentSegment, 0, j))
        {
          this._resultString = str2;
          break;
        }
      }
      StringBuilder localStringBuilder = new StringBuilder(i + j);
      if (this._segments != null)
      {
        int k = 0;
        int m = this._segments.size();
        while (k < m)
        {
          char[] arrayOfChar = (char[])this._segments.get(k);
          localStringBuilder.append(arrayOfChar, 0, arrayOfChar.length);
          k++;
        }
      }
      localStringBuilder.append(this._currentSegment, 0, this._currentSize);
      this._resultString = localStringBuilder.toString();
    }
  }
  
  public char[] emptyAndGetCurrentSegment()
  {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    }
    char[] arrayOfChar = this._currentSegment;
    if (arrayOfChar == null)
    {
      arrayOfChar = buf(0);
      this._currentSegment = arrayOfChar;
    }
    return arrayOfChar;
  }
  
  public void ensureNotShared()
  {
    if (this._inputStart >= 0) {
      unshare(16);
    }
  }
  
  public char[] expandCurrentSegment()
  {
    char[] arrayOfChar1 = this._currentSegment;
    int i = arrayOfChar1.length;
    int j = i + (i >> 1);
    if (j > 262144) {
      j = i + (i >> 2);
    }
    char[] arrayOfChar2 = Arrays.copyOf(arrayOfChar1, j);
    this._currentSegment = arrayOfChar2;
    return arrayOfChar2;
  }
  
  public char[] expandCurrentSegment(int paramInt)
  {
    char[] arrayOfChar1 = this._currentSegment;
    if (arrayOfChar1.length >= paramInt) {}
    char[] arrayOfChar2;
    for (Object localObject = arrayOfChar1;; localObject = arrayOfChar2)
    {
      return (char[])localObject;
      arrayOfChar2 = Arrays.copyOf(arrayOfChar1, paramInt);
      this._currentSegment = arrayOfChar2;
    }
  }
  
  public char[] finishCurrentSegment()
  {
    if (this._segments == null) {
      this._segments = new ArrayList();
    }
    this._hasSegments = true;
    this._segments.add(this._currentSegment);
    int i = this._currentSegment.length;
    this._segmentSize = (i + this._segmentSize);
    this._currentSize = 0;
    int j = i + (i >> 1);
    if (j < 1000) {
      j = 1000;
    }
    for (;;)
    {
      char[] arrayOfChar = carr(j);
      this._currentSegment = arrayOfChar;
      return arrayOfChar;
      if (j > 262144) {
        j = 262144;
      }
    }
  }
  
  public char[] getCurrentSegment()
  {
    if (this._inputStart >= 0) {
      unshare(1);
    }
    for (;;)
    {
      return this._currentSegment;
      char[] arrayOfChar = this._currentSegment;
      if (arrayOfChar == null) {
        this._currentSegment = buf(0);
      } else if (this._currentSize >= arrayOfChar.length) {
        expand(1);
      }
    }
  }
  
  public int getCurrentSegmentSize()
  {
    return this._currentSize;
  }
  
  public char[] getTextBuffer()
  {
    char[] arrayOfChar;
    if (this._inputStart >= 0) {
      arrayOfChar = this._inputBuffer;
    }
    for (;;)
    {
      return arrayOfChar;
      if (this._resultArray != null)
      {
        arrayOfChar = this._resultArray;
      }
      else if (this._resultString != null)
      {
        arrayOfChar = this._resultString.toCharArray();
        this._resultArray = arrayOfChar;
      }
      else if (!this._hasSegments)
      {
        if (this._currentSegment == null) {
          arrayOfChar = NO_CHARS;
        } else {
          arrayOfChar = this._currentSegment;
        }
      }
      else
      {
        arrayOfChar = contentsAsArray();
      }
    }
  }
  
  public int getTextOffset()
  {
    if (this._inputStart >= 0) {}
    for (int i = this._inputStart;; i = 0) {
      return i;
    }
  }
  
  public boolean hasTextAsCharacters()
  {
    boolean bool = true;
    if ((this._inputStart >= 0) || (this._resultArray != null)) {}
    for (;;)
    {
      return bool;
      if (this._resultString != null) {
        bool = false;
      }
    }
  }
  
  public void releaseBuffers()
  {
    if (this._allocator == null) {
      resetWithEmpty();
    }
    for (;;)
    {
      return;
      if (this._currentSegment != null)
      {
        resetWithEmpty();
        char[] arrayOfChar = this._currentSegment;
        this._currentSegment = null;
        this._allocator.releaseCharBuffer(2, arrayOfChar);
      }
    }
  }
  
  public void resetWithCopy(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    }
    for (;;)
    {
      this._segmentSize = 0;
      this._currentSize = 0;
      append(paramArrayOfChar, paramInt1, paramInt2);
      return;
      if (this._currentSegment == null) {
        this._currentSegment = buf(paramInt2);
      }
    }
  }
  
  public void resetWithEmpty()
  {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    }
  }
  
  public void resetWithShared(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this._resultString = null;
    this._resultArray = null;
    this._inputBuffer = paramArrayOfChar;
    this._inputStart = paramInt1;
    this._inputLen = paramInt2;
    if (this._hasSegments) {
      clearSegments();
    }
  }
  
  public void resetWithString(String paramString)
  {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = paramString;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    }
    this._currentSize = 0;
  }
  
  public String setCurrentAndReturn(int paramInt)
  {
    this._currentSize = paramInt;
    if (this._segmentSize > 0)
    {
      str = contentsAsString();
      return str;
    }
    int i = this._currentSize;
    if (i == 0) {}
    for (String str = "";; str = new String(this._currentSegment, 0, i))
    {
      this._resultString = str;
      break;
    }
  }
  
  public void setCurrentLength(int paramInt)
  {
    this._currentSize = paramInt;
  }
  
  public int size()
  {
    int i;
    if (this._inputStart >= 0) {
      i = this._inputLen;
    }
    for (;;)
    {
      return i;
      if (this._resultArray != null) {
        i = this._resultArray.length;
      } else if (this._resultString != null) {
        i = this._resultString.length();
      } else {
        i = this._segmentSize + this._currentSize;
      }
    }
  }
  
  public String toString()
  {
    return contentsAsString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/util/TextBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */