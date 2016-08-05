package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class ByteQuadsCanonicalizer
{
  private static final int DEFAULT_T_SIZE = 64;
  static final int MAX_ENTRIES_FOR_REUSE = 6000;
  private static final int MAX_T_SIZE = 65536;
  static final int MIN_HASH_SIZE = 16;
  private static final int MULT = 33;
  private static final int MULT2 = 65599;
  private static final int MULT3 = 31;
  protected int _count;
  protected final boolean _failOnDoS;
  protected int[] _hashArea;
  private boolean _hashShared;
  protected int _hashSize;
  protected boolean _intern;
  protected int _longNameOffset;
  protected String[] _names;
  private transient boolean _needRehash;
  protected final ByteQuadsCanonicalizer _parent;
  protected int _secondaryStart;
  private final int _seed;
  protected int _spilloverEnd;
  protected final AtomicReference<TableInfo> _tableInfo;
  protected int _tertiaryShift;
  protected int _tertiaryStart;
  
  private ByteQuadsCanonicalizer(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
  {
    this._parent = null;
    this._seed = paramInt2;
    this._intern = paramBoolean1;
    this._failOnDoS = paramBoolean2;
    if (paramInt1 < 16) {
      paramInt1 = 16;
    }
    for (;;)
    {
      this._tableInfo = new AtomicReference(TableInfo.createInitial(paramInt1));
      return;
      if ((paramInt1 & paramInt1 - 1) != 0)
      {
        int i = 16;
        while (i < paramInt1) {
          i += i;
        }
        paramInt1 = i;
      }
    }
  }
  
  private ByteQuadsCanonicalizer(ByteQuadsCanonicalizer paramByteQuadsCanonicalizer, boolean paramBoolean1, int paramInt, boolean paramBoolean2, TableInfo paramTableInfo)
  {
    this._parent = paramByteQuadsCanonicalizer;
    this._seed = paramInt;
    this._intern = paramBoolean1;
    this._failOnDoS = paramBoolean2;
    this._tableInfo = null;
    this._count = paramTableInfo.count;
    this._hashSize = paramTableInfo.size;
    this._secondaryStart = (this._hashSize << 2);
    this._tertiaryStart = (this._secondaryStart + (this._secondaryStart >> 1));
    this._tertiaryShift = paramTableInfo.tertiaryShift;
    this._hashArea = paramTableInfo.mainHash;
    this._names = paramTableInfo.names;
    this._spilloverEnd = paramTableInfo.spilloverEnd;
    this._longNameOffset = paramTableInfo.longNameOffset;
    this._needRehash = false;
    this._hashShared = true;
  }
  
  private int _appendLongName(int[] paramArrayOfInt, int paramInt)
  {
    int i = this._longNameOffset;
    if (i + paramInt > this._hashArea.length)
    {
      int j = i + paramInt - this._hashArea.length;
      int k = Math.min(4096, this._hashSize);
      int m = this._hashArea.length + Math.max(j, k);
      this._hashArea = Arrays.copyOf(this._hashArea, m);
    }
    System.arraycopy(paramArrayOfInt, 0, this._hashArea, i, paramInt);
    this._longNameOffset = (paramInt + this._longNameOffset);
    return i;
  }
  
  private final int _calcOffset(int paramInt)
  {
    return (paramInt & -1 + this._hashSize) << 2;
  }
  
  static int _calcTertiaryShift(int paramInt)
  {
    int i = paramInt >> 2;
    int j;
    if (i < 64) {
      j = 4;
    }
    for (;;)
    {
      return j;
      if (i <= 256) {
        j = 5;
      } else if (i <= 1024) {
        j = 6;
      } else {
        j = 7;
      }
    }
  }
  
  private int _findOffsetForAdd(int paramInt)
  {
    int i = _calcOffset(paramInt);
    int[] arrayOfInt = this._hashArea;
    int j;
    if (arrayOfInt[(i + 3)] == 0) {
      j = i;
    }
    for (;;)
    {
      return j;
      j = this._secondaryStart + (i >> 3 << 2);
      if (arrayOfInt[(j + 3)] != 0)
      {
        j = this._tertiaryStart + (i >> 2 + this._tertiaryShift << this._tertiaryShift);
        int k = j + (1 << this._tertiaryShift);
        for (;;)
        {
          if (j >= k) {
            break label102;
          }
          if (arrayOfInt[(j + 3)] == 0) {
            break;
          }
          j += 4;
        }
        label102:
        int m = this._spilloverEnd;
        this._spilloverEnd = (4 + this._spilloverEnd);
        int n = this._hashSize << 3;
        if (this._spilloverEnd >= n)
        {
          if (this._failOnDoS) {
            _reportTooManyCollisions();
          }
          this._needRehash = true;
        }
        j = m;
      }
    }
  }
  
  private String _findSecondary(int paramInt1, int paramInt2)
  {
    String str = null;
    int i = this._tertiaryStart + (paramInt1 >> 2 + this._tertiaryShift << this._tertiaryShift);
    int[] arrayOfInt = this._hashArea;
    int j = i + (1 << this._tertiaryShift);
    int m;
    if (i < j)
    {
      m = arrayOfInt[(i + 3)];
      if ((paramInt2 == arrayOfInt[i]) && (1 == m)) {
        str = this._names[(i >> 2)];
      }
    }
    label145:
    for (;;)
    {
      return str;
      if (m != 0)
      {
        i += 4;
        break;
        for (int k = _spilloverStart();; k += 4)
        {
          if (k >= this._spilloverEnd) {
            break label145;
          }
          if ((paramInt2 == arrayOfInt[k]) && (1 == arrayOfInt[(k + 3)]))
          {
            str = this._names[(k >> 2)];
            break;
          }
        }
      }
    }
  }
  
  private String _findSecondary(int paramInt1, int paramInt2, int paramInt3)
  {
    String str = null;
    int i = this._tertiaryStart + (paramInt1 >> 2 + this._tertiaryShift << this._tertiaryShift);
    int[] arrayOfInt = this._hashArea;
    int j = i + (1 << this._tertiaryShift);
    int m;
    if (i < j)
    {
      m = arrayOfInt[(i + 3)];
      if ((paramInt2 == arrayOfInt[i]) && (paramInt3 == arrayOfInt[(i + 1)]) && (2 == m)) {
        str = this._names[(i >> 2)];
      }
    }
    label171:
    for (;;)
    {
      return str;
      if (m != 0)
      {
        i += 4;
        break;
        for (int k = _spilloverStart();; k += 4)
        {
          if (k >= this._spilloverEnd) {
            break label171;
          }
          if ((paramInt2 == arrayOfInt[k]) && (paramInt3 == arrayOfInt[(k + 1)]) && (2 == arrayOfInt[(k + 3)]))
          {
            str = this._names[(k >> 2)];
            break;
          }
        }
      }
    }
  }
  
  private String _findSecondary(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    String str = null;
    int i = this._tertiaryStart + (paramInt1 >> 2 + this._tertiaryShift << this._tertiaryShift);
    int[] arrayOfInt = this._hashArea;
    int j = i + (1 << this._tertiaryShift);
    int m;
    if (i < j)
    {
      m = arrayOfInt[(i + 3)];
      if ((paramInt2 == arrayOfInt[i]) && (paramInt3 == arrayOfInt[(i + 1)]) && (paramInt4 == arrayOfInt[(i + 2)]) && (3 == m)) {
        str = this._names[(i >> 2)];
      }
    }
    label195:
    for (;;)
    {
      return str;
      if (m != 0)
      {
        i += 4;
        break;
        for (int k = _spilloverStart();; k += 4)
        {
          if (k >= this._spilloverEnd) {
            break label195;
          }
          if ((paramInt2 == arrayOfInt[k]) && (paramInt3 == arrayOfInt[(k + 1)]) && (paramInt4 == arrayOfInt[(k + 2)]) && (3 == arrayOfInt[(k + 3)]))
          {
            str = this._names[(k >> 2)];
            break;
          }
        }
      }
    }
  }
  
  private String _findSecondary(int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3)
  {
    String str = null;
    int i = this._tertiaryStart + (paramInt1 >> 2 + this._tertiaryShift << this._tertiaryShift);
    int[] arrayOfInt = this._hashArea;
    int j = i + (1 << this._tertiaryShift);
    int m;
    if (i < j)
    {
      m = arrayOfInt[(i + 3)];
      if ((paramInt2 == arrayOfInt[i]) && (paramInt3 == m) && (_verifyLongName(paramArrayOfInt, paramInt3, arrayOfInt[(i + 1)]))) {
        str = this._names[(i >> 2)];
      }
    }
    label185:
    for (;;)
    {
      return str;
      if (m != 0)
      {
        i += 4;
        break;
        for (int k = _spilloverStart();; k += 4)
        {
          if (k >= this._spilloverEnd) {
            break label185;
          }
          if ((paramInt2 == arrayOfInt[k]) && (paramInt3 == arrayOfInt[(k + 3)]) && (_verifyLongName(paramArrayOfInt, paramInt3, arrayOfInt[(k + 1)])))
          {
            str = this._names[(k >> 2)];
            break;
          }
        }
      }
    }
  }
  
  private final int _spilloverStart()
  {
    int i = this._hashSize;
    return (i << 3) - i;
  }
  
  private boolean _verifyLongName(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    boolean bool = false;
    int[] arrayOfInt = this._hashArea;
    int i = 0;
    switch (paramInt1)
    {
    default: 
      bool = _verifyLongName2(paramArrayOfInt, paramInt1, paramInt2);
    }
    for (;;)
    {
      return bool;
      int i16 = 0 + 1;
      int i17 = paramArrayOfInt[0];
      int i18 = paramInt2 + 1;
      if (i17 == arrayOfInt[paramInt2])
      {
        i = i16;
        paramInt2 = i18;
        int i13 = i + 1;
        int i14 = paramArrayOfInt[i];
        int i15 = paramInt2 + 1;
        if (i14 == arrayOfInt[paramInt2])
        {
          i = i13;
          paramInt2 = i15;
          int i10 = i + 1;
          int i11 = paramArrayOfInt[i];
          int i12 = paramInt2 + 1;
          if (i11 == arrayOfInt[paramInt2])
          {
            i = i10;
            paramInt2 = i12;
            int i7 = i + 1;
            int i8 = paramArrayOfInt[i];
            int i9 = paramInt2 + 1;
            if (i8 == arrayOfInt[paramInt2])
            {
              i = i7;
              paramInt2 = i9;
              int j = i + 1;
              int k = paramArrayOfInt[i];
              int m = paramInt2 + 1;
              if (k == arrayOfInt[paramInt2])
              {
                int n = j + 1;
                int i1 = paramArrayOfInt[j];
                int i2 = m + 1;
                if (i1 == arrayOfInt[m])
                {
                  int i3 = n + 1;
                  int i4 = paramArrayOfInt[n];
                  int i5 = i2 + 1;
                  if (i4 == arrayOfInt[i2])
                  {
                    (i3 + 1);
                    int i6 = paramArrayOfInt[i3];
                    (i5 + 1);
                    if (i6 == arrayOfInt[i5]) {
                      bool = true;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  private boolean _verifyLongName2(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = 0;
    for (;;)
    {
      int j = i + 1;
      int k = paramArrayOfInt[i];
      int[] arrayOfInt = this._hashArea;
      int m = paramInt2 + 1;
      if (k != arrayOfInt[paramInt2]) {}
      for (boolean bool = false;; bool = true)
      {
        return bool;
        if (j < paramInt1) {
          break;
        }
      }
      i = j;
      paramInt2 = m;
    }
  }
  
  private void _verifyNeedForRehash()
  {
    if ((this._count > this._hashSize >> 1) && ((this._spilloverEnd - _spilloverStart() >> 2 > 1 + this._count >> 7) || (this._count > 0.8D * this._hashSize))) {
      this._needRehash = true;
    }
  }
  
  private void _verifySharing()
  {
    if (this._hashShared)
    {
      this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length);
      this._names = ((String[])Arrays.copyOf(this._names, this._names.length));
      this._hashShared = false;
      _verifyNeedForRehash();
    }
    if (this._needRehash) {
      rehash();
    }
  }
  
  public static ByteQuadsCanonicalizer createRoot()
  {
    long l = System.currentTimeMillis();
    return createRoot(0x1 | (int)l + (int)(l >>> 32));
  }
  
  protected static ByteQuadsCanonicalizer createRoot(int paramInt)
  {
    return new ByteQuadsCanonicalizer(64, true, paramInt, true);
  }
  
  private void mergeChild(TableInfo paramTableInfo)
  {
    int i = paramTableInfo.count;
    TableInfo localTableInfo = (TableInfo)this._tableInfo.get();
    if (i == localTableInfo.count) {}
    for (;;)
    {
      return;
      if (i > 6000) {
        paramTableInfo = TableInfo.createInitial(64);
      }
      this._tableInfo.compareAndSet(localTableInfo, paramTableInfo);
    }
  }
  
  private void nukeSymbols(boolean paramBoolean)
  {
    this._count = 0;
    this._spilloverEnd = _spilloverStart();
    this._longNameOffset = (this._hashSize << 3);
    if (paramBoolean)
    {
      Arrays.fill(this._hashArea, 0);
      Arrays.fill(this._names, null);
    }
  }
  
  private void rehash()
  {
    this._needRehash = false;
    this._hashShared = false;
    int[] arrayOfInt1 = this._hashArea;
    String[] arrayOfString = this._names;
    int i = this._hashSize;
    int j = this._count;
    int k = i + i;
    int m = this._spilloverEnd;
    if (k > 65536) {
      nukeSymbols(true);
    }
    int n;
    do
    {
      return;
      this._hashArea = new int[arrayOfInt1.length + (i << 3)];
      this._hashSize = k;
      this._secondaryStart = (k << 2);
      this._tertiaryStart = (this._secondaryStart + (this._secondaryStart >> 1));
      this._tertiaryShift = _calcTertiaryShift(k);
      this._names = new String[arrayOfString.length << 1];
      nukeSymbols(false);
      n = 0;
      int[] arrayOfInt2 = new int[16];
      int i1 = 0;
      if (i1 < m)
      {
        int i2 = arrayOfInt1[(i1 + 3)];
        if (i2 == 0) {}
        for (;;)
        {
          i1 += 4;
          break;
          n++;
          String str = arrayOfString[(i1 >> 2)];
          switch (i2)
          {
          default: 
            if (i2 > arrayOfInt2.length) {
              arrayOfInt2 = new int[i2];
            }
            System.arraycopy(arrayOfInt1, arrayOfInt1[(i1 + 1)], arrayOfInt2, 0, i2);
            addName(str, arrayOfInt2, i2);
            break;
          case 1: 
            arrayOfInt2[0] = arrayOfInt1[i1];
            addName(str, arrayOfInt2, 1);
            break;
          case 2: 
            arrayOfInt2[0] = arrayOfInt1[i1];
            arrayOfInt2[1] = arrayOfInt1[(i1 + 1)];
            addName(str, arrayOfInt2, 2);
            break;
          case 3: 
            arrayOfInt2[0] = arrayOfInt1[i1];
            arrayOfInt2[1] = arrayOfInt1[(i1 + 1)];
            arrayOfInt2[2] = arrayOfInt1[(i1 + 2)];
            addName(str, arrayOfInt2, 3);
          }
        }
      }
    } while (n == j);
    throw new IllegalStateException("Failed rehash(): old count=" + j + ", copyCount=" + n);
  }
  
  protected void _reportTooManyCollisions()
  {
    if (this._hashSize <= 1024) {
      return;
    }
    throw new IllegalStateException("Spill-over slots in symbol table with " + this._count + " entries, hash area of " + this._hashSize + " slots is now full (all " + (this._hashSize >> 3) + " slots -- suspect a DoS attack based on hash collisions." + " You can disable the check via `JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW`");
  }
  
  public String addName(String paramString, int paramInt)
  {
    _verifySharing();
    if (this._intern) {
      paramString = InternCache.instance.intern(paramString);
    }
    int i = _findOffsetForAdd(calcHash(paramInt));
    this._hashArea[i] = paramInt;
    this._hashArea[(i + 3)] = 1;
    this._names[(i >> 2)] = paramString;
    this._count = (1 + this._count);
    _verifyNeedForRehash();
    return paramString;
  }
  
  public String addName(String paramString, int paramInt1, int paramInt2)
  {
    _verifySharing();
    if (this._intern) {
      paramString = InternCache.instance.intern(paramString);
    }
    if (paramInt2 == 0) {}
    for (int i = calcHash(paramInt1);; i = calcHash(paramInt1, paramInt2))
    {
      int j = _findOffsetForAdd(i);
      this._hashArea[j] = paramInt1;
      this._hashArea[(j + 1)] = paramInt2;
      this._hashArea[(j + 3)] = 2;
      this._names[(j >> 2)] = paramString;
      this._count = (1 + this._count);
      _verifyNeedForRehash();
      return paramString;
    }
  }
  
  public String addName(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    _verifySharing();
    if (this._intern) {
      paramString = InternCache.instance.intern(paramString);
    }
    int i = _findOffsetForAdd(calcHash(paramInt1, paramInt2, paramInt3));
    this._hashArea[i] = paramInt1;
    this._hashArea[(i + 1)] = paramInt2;
    this._hashArea[(i + 2)] = paramInt3;
    this._hashArea[(i + 3)] = 3;
    this._names[(i >> 2)] = paramString;
    this._count = (1 + this._count);
    _verifyNeedForRehash();
    return paramString;
  }
  
  public String addName(String paramString, int[] paramArrayOfInt, int paramInt)
  {
    _verifySharing();
    if (this._intern) {
      paramString = InternCache.instance.intern(paramString);
    }
    int i;
    switch (paramInt)
    {
    default: 
      int j = calcHash(paramArrayOfInt, paramInt);
      i = _findOffsetForAdd(j);
      this._hashArea[i] = j;
      int k = _appendLongName(paramArrayOfInt, paramInt);
      this._hashArea[(i + 1)] = k;
      this._hashArea[(i + 3)] = paramInt;
    }
    for (;;)
    {
      this._names[(i >> 2)] = paramString;
      this._count = (1 + this._count);
      _verifyNeedForRehash();
      return paramString;
      i = _findOffsetForAdd(calcHash(paramArrayOfInt[0]));
      this._hashArea[i] = paramArrayOfInt[0];
      this._hashArea[(i + 3)] = 1;
      continue;
      i = _findOffsetForAdd(calcHash(paramArrayOfInt[0], paramArrayOfInt[1]));
      this._hashArea[i] = paramArrayOfInt[0];
      this._hashArea[(i + 1)] = paramArrayOfInt[1];
      this._hashArea[(i + 3)] = 2;
      continue;
      i = _findOffsetForAdd(calcHash(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]));
      this._hashArea[i] = paramArrayOfInt[0];
      this._hashArea[(i + 1)] = paramArrayOfInt[1];
      this._hashArea[(i + 2)] = paramArrayOfInt[2];
      this._hashArea[(i + 3)] = 3;
    }
  }
  
  public int bucketCount()
  {
    return this._hashSize;
  }
  
  public int calcHash(int paramInt)
  {
    int i = paramInt ^ this._seed;
    int j = i + (i >>> 16);
    int k = j ^ j << 3;
    return k + (k >>> 12);
  }
  
  public int calcHash(int paramInt1, int paramInt2)
  {
    int i = paramInt1 + (paramInt1 >>> 15);
    int j = (i ^ i >>> 9) + paramInt2 * 33 ^ this._seed;
    int k = j + (j >>> 16);
    int m = k ^ k >>> 4;
    return m + (m << 3);
  }
  
  public int calcHash(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1 ^ this._seed;
    int j = 33 * (paramInt2 + 31 * (i + (i >>> 9)));
    int k = paramInt3 ^ j + (j >>> 15);
    int m = k + (k >>> 4);
    int n = m + (m >>> 15);
    return n ^ n << 9;
  }
  
  public int calcHash(int[] paramArrayOfInt, int paramInt)
  {
    if (paramInt < 4) {
      throw new IllegalArgumentException();
    }
    int i = paramArrayOfInt[0] ^ this._seed;
    int j = i + (i >>> 9) + paramArrayOfInt[1];
    int k = 33 * (j + (j >>> 15)) ^ paramArrayOfInt[2];
    int m = k + (k >>> 4);
    for (int n = 3; n < paramInt; n++)
    {
      int i3 = paramArrayOfInt[n];
      m += (i3 ^ i3 >> 21);
    }
    int i1 = m * 65599;
    int i2 = i1 + (i1 >>> 19);
    return i2 ^ i2 << 5;
  }
  
  public String findName(int paramInt)
  {
    String str = null;
    int i = _calcOffset(calcHash(paramInt));
    int[] arrayOfInt = this._hashArea;
    int j = arrayOfInt[(i + 3)];
    if (j == 1)
    {
      if (arrayOfInt[i] != paramInt) {
        break label56;
      }
      str = this._names[(i >> 2)];
    }
    for (;;)
    {
      return str;
      if (j != 0)
      {
        label56:
        int k = this._secondaryStart + (i >> 3 << 2);
        int m = arrayOfInt[(k + 3)];
        if (m == 1)
        {
          if (arrayOfInt[k] == paramInt) {
            str = this._names[(k >> 2)];
          }
        }
        else {
          if (m == 0) {
            continue;
          }
        }
        str = _findSecondary(i, paramInt);
      }
    }
  }
  
  public String findName(int paramInt1, int paramInt2)
  {
    String str = null;
    int i = _calcOffset(calcHash(paramInt1, paramInt2));
    int[] arrayOfInt = this._hashArea;
    int j = arrayOfInt[(i + 3)];
    if (j == 2)
    {
      if ((paramInt1 != arrayOfInt[i]) || (paramInt2 != arrayOfInt[(i + 1)])) {
        break label72;
      }
      str = this._names[(i >> 2)];
    }
    for (;;)
    {
      return str;
      if (j != 0)
      {
        label72:
        int k = this._secondaryStart + (i >> 3 << 2);
        int m = arrayOfInt[(k + 3)];
        if (m == 2)
        {
          if ((paramInt1 == arrayOfInt[k]) && (paramInt2 == arrayOfInt[(k + 1)])) {
            str = this._names[(k >> 2)];
          }
        }
        else {
          if (m == 0) {
            continue;
          }
        }
        str = _findSecondary(i, paramInt1, paramInt2);
      }
    }
  }
  
  public String findName(int paramInt1, int paramInt2, int paramInt3)
  {
    String str = null;
    int i = _calcOffset(calcHash(paramInt1, paramInt2, paramInt3));
    int[] arrayOfInt = this._hashArea;
    int j = arrayOfInt[(i + 3)];
    if (j == 3)
    {
      if ((paramInt1 != arrayOfInt[i]) || (arrayOfInt[(i + 1)] != paramInt2) || (arrayOfInt[(i + 2)] != paramInt3)) {
        break label87;
      }
      str = this._names[(i >> 2)];
    }
    for (;;)
    {
      return str;
      if (j != 0)
      {
        label87:
        int k = this._secondaryStart + (i >> 3 << 2);
        int m = arrayOfInt[(k + 3)];
        if (m == 3)
        {
          if ((paramInt1 == arrayOfInt[k]) && (arrayOfInt[(k + 1)] == paramInt2) && (arrayOfInt[(k + 2)] == paramInt3)) {
            str = this._names[(k >> 2)];
          }
        }
        else {
          if (m == 0) {
            continue;
          }
        }
        str = _findSecondary(i, paramInt1, paramInt2, paramInt3);
      }
    }
  }
  
  public String findName(int[] paramArrayOfInt, int paramInt)
  {
    String str = null;
    if (paramInt < 4) {
      if (paramInt == 3) {
        str = findName(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
      }
    }
    for (;;)
    {
      return str;
      if (paramInt == 2)
      {
        str = findName(paramArrayOfInt[0], paramArrayOfInt[1]);
      }
      else
      {
        str = findName(paramArrayOfInt[0]);
        continue;
        int i = calcHash(paramArrayOfInt, paramInt);
        int j = _calcOffset(i);
        int[] arrayOfInt = this._hashArea;
        int k = arrayOfInt[(j + 3)];
        if ((i == arrayOfInt[j]) && (k == paramInt) && (_verifyLongName(paramArrayOfInt, paramInt, arrayOfInt[(j + 1)])))
        {
          str = this._names[(j >> 2)];
        }
        else if (k != 0)
        {
          int m = this._secondaryStart + (j >> 3 << 2);
          int n = arrayOfInt[(m + 3)];
          if ((i == arrayOfInt[m]) && (n == paramInt) && (_verifyLongName(paramArrayOfInt, paramInt, arrayOfInt[(m + 1)]))) {
            str = this._names[(m >> 2)];
          } else if (k != 0) {
            str = _findSecondary(j, i, paramArrayOfInt, paramInt);
          }
        }
      }
    }
  }
  
  public int hashSeed()
  {
    return this._seed;
  }
  
  public ByteQuadsCanonicalizer makeChild(int paramInt)
  {
    return new ByteQuadsCanonicalizer(this, JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(paramInt), this._seed, JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(paramInt), (TableInfo)this._tableInfo.get());
  }
  
  public boolean maybeDirty()
  {
    if (!this._hashShared) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int primaryCount()
  {
    int i = 0;
    int j = 3;
    int k = this._secondaryStart;
    while (j < k)
    {
      if (this._hashArea[j] != 0) {
        i++;
      }
      j += 4;
    }
    return i;
  }
  
  public void release()
  {
    if ((this._parent != null) && (maybeDirty()))
    {
      this._parent.mergeChild(new TableInfo(this));
      this._hashShared = true;
    }
  }
  
  public int secondaryCount()
  {
    int i = 0;
    int j = 3 + this._secondaryStart;
    int k = this._tertiaryStart;
    while (j < k)
    {
      if (this._hashArea[j] != 0) {
        i++;
      }
      j += 4;
    }
    return i;
  }
  
  public int size()
  {
    if (this._tableInfo != null) {}
    for (int i = ((TableInfo)this._tableInfo.get()).count;; i = this._count) {
      return i;
    }
  }
  
  public int spilloverCount()
  {
    return this._spilloverEnd - _spilloverStart() >> 2;
  }
  
  public int tertiaryCount()
  {
    int i = 0;
    int j = 3 + this._tertiaryStart;
    int k = j + this._hashSize;
    while (j < k)
    {
      if (this._hashArea[j] != 0) {
        i++;
      }
      j += 4;
    }
    return i;
  }
  
  public String toString()
  {
    int i = primaryCount();
    int j = secondaryCount();
    int k = tertiaryCount();
    int m = spilloverCount();
    int n = totalCount();
    Object[] arrayOfObject = new Object[10];
    arrayOfObject[0] = getClass().getName();
    arrayOfObject[1] = Integer.valueOf(this._count);
    arrayOfObject[2] = Integer.valueOf(this._hashSize);
    arrayOfObject[3] = Integer.valueOf(i);
    arrayOfObject[4] = Integer.valueOf(j);
    arrayOfObject[5] = Integer.valueOf(k);
    arrayOfObject[6] = Integer.valueOf(m);
    arrayOfObject[7] = Integer.valueOf(n);
    arrayOfObject[8] = Integer.valueOf(m + (k + (i + j)));
    arrayOfObject[9] = Integer.valueOf(n);
    return String.format("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]", arrayOfObject);
  }
  
  public int totalCount()
  {
    int i = 0;
    int j = 3;
    int k = this._hashSize << 3;
    while (j < k)
    {
      if (this._hashArea[j] != 0) {
        i++;
      }
      j += 4;
    }
    return i;
  }
  
  private static final class TableInfo
  {
    public final int count;
    public final int longNameOffset;
    public final int[] mainHash;
    public final String[] names;
    public final int size;
    public final int spilloverEnd;
    public final int tertiaryShift;
    
    public TableInfo(int paramInt1, int paramInt2, int paramInt3, int[] paramArrayOfInt, String[] paramArrayOfString, int paramInt4, int paramInt5)
    {
      this.size = paramInt1;
      this.count = paramInt2;
      this.tertiaryShift = paramInt3;
      this.mainHash = paramArrayOfInt;
      this.names = paramArrayOfString;
      this.spilloverEnd = paramInt4;
      this.longNameOffset = paramInt5;
    }
    
    public TableInfo(ByteQuadsCanonicalizer paramByteQuadsCanonicalizer)
    {
      this.size = paramByteQuadsCanonicalizer._hashSize;
      this.count = paramByteQuadsCanonicalizer._count;
      this.tertiaryShift = paramByteQuadsCanonicalizer._tertiaryShift;
      this.mainHash = paramByteQuadsCanonicalizer._hashArea;
      this.names = paramByteQuadsCanonicalizer._names;
      this.spilloverEnd = paramByteQuadsCanonicalizer._spilloverEnd;
      this.longNameOffset = paramByteQuadsCanonicalizer._longNameOffset;
    }
    
    public static TableInfo createInitial(int paramInt)
    {
      int i = paramInt << 3;
      return new TableInfo(paramInt, 0, ByteQuadsCanonicalizer._calcTertiaryShift(paramInt), new int[i], new String[paramInt << 1], i - paramInt, i);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/sym/ByteQuadsCanonicalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */