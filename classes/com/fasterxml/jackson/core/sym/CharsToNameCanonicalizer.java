package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.BitSet;

public final class CharsToNameCanonicalizer
{
  protected static final int DEFAULT_T_SIZE = 64;
  public static final int HASH_MULT = 33;
  static final int MAX_COLL_CHAIN_LENGTH = 100;
  static final int MAX_ENTRIES_FOR_REUSE = 12000;
  protected static final int MAX_T_SIZE = 65536;
  static final CharsToNameCanonicalizer sBootstrapSymbolTable = new CharsToNameCanonicalizer();
  protected Bucket[] _buckets;
  protected boolean _canonicalize;
  protected boolean _dirty;
  protected final int _flags;
  private final int _hashSeed;
  protected int _indexMask;
  protected int _longestCollisionList;
  protected BitSet _overflows;
  protected CharsToNameCanonicalizer _parent;
  protected int _size;
  protected int _sizeThreshold;
  protected String[] _symbols;
  
  private CharsToNameCanonicalizer()
  {
    this._canonicalize = true;
    this._flags = -1;
    this._dirty = true;
    this._hashSeed = 0;
    this._longestCollisionList = 0;
    initTables(64);
  }
  
  private CharsToNameCanonicalizer(CharsToNameCanonicalizer paramCharsToNameCanonicalizer, int paramInt1, String[] paramArrayOfString, Bucket[] paramArrayOfBucket, int paramInt2, int paramInt3, int paramInt4)
  {
    this._parent = paramCharsToNameCanonicalizer;
    this._flags = paramInt1;
    this._canonicalize = JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(paramInt1);
    this._symbols = paramArrayOfString;
    this._buckets = paramArrayOfBucket;
    this._size = paramInt2;
    this._hashSeed = paramInt3;
    int i = paramArrayOfString.length;
    this._sizeThreshold = _thresholdSize(i);
    this._indexMask = (i - 1);
    this._longestCollisionList = paramInt4;
    this._dirty = false;
  }
  
  private String _addSymbol(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    String str;
    if (!this._dirty)
    {
      copyArrays();
      this._dirty = true;
      str = new String(paramArrayOfChar, paramInt1, paramInt2);
      if (JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(this._flags)) {
        str = InternCache.instance.intern(str);
      }
      this._size = (1 + this._size);
      if (this._symbols[paramInt4] != null) {
        break label114;
      }
      this._symbols[paramInt4] = str;
    }
    for (;;)
    {
      return str;
      if (this._size < this._sizeThreshold) {
        break;
      }
      rehash();
      paramInt4 = _hashToIndex(calcHash(paramArrayOfChar, paramInt1, paramInt2));
      break;
      label114:
      int i = paramInt4 >> 1;
      Bucket localBucket = new Bucket(str, this._buckets[i]);
      int j = localBucket.length;
      if (j > 100)
      {
        _handleSpillOverflow(i, localBucket);
      }
      else
      {
        this._buckets[i] = localBucket;
        this._longestCollisionList = Math.max(j, this._longestCollisionList);
      }
    }
  }
  
  private String _findSymbol2(char[] paramArrayOfChar, int paramInt1, int paramInt2, Bucket paramBucket)
  {
    String str;
    if (paramBucket != null)
    {
      str = paramBucket.has(paramArrayOfChar, paramInt1, paramInt2);
      if (str == null) {}
    }
    for (;;)
    {
      return str;
      paramBucket = paramBucket.next;
      break;
      str = null;
    }
  }
  
  private void _handleSpillOverflow(int paramInt, Bucket paramBucket)
  {
    if (this._overflows == null)
    {
      this._overflows = new BitSet();
      this._overflows.set(paramInt);
    }
    for (;;)
    {
      this._symbols[(paramInt + paramInt)] = paramBucket.symbol;
      this._buckets[paramInt] = null;
      this._size -= paramBucket.length;
      this._longestCollisionList = -1;
      return;
      if (this._overflows.get(paramInt))
      {
        if (JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(this._flags)) {
          reportTooManyCollisions(100);
        }
        this._canonicalize = false;
      }
      else
      {
        this._overflows.set(paramInt);
      }
    }
  }
  
  private static int _thresholdSize(int paramInt)
  {
    return paramInt - (paramInt >> 2);
  }
  
  private void copyArrays()
  {
    String[] arrayOfString = this._symbols;
    this._symbols = ((String[])Arrays.copyOf(arrayOfString, arrayOfString.length));
    Bucket[] arrayOfBucket = this._buckets;
    this._buckets = ((Bucket[])Arrays.copyOf(arrayOfBucket, arrayOfBucket.length));
  }
  
  public static CharsToNameCanonicalizer createRoot()
  {
    long l = System.currentTimeMillis();
    return createRoot(0x1 | (int)l + (int)(l >>> 32));
  }
  
  protected static CharsToNameCanonicalizer createRoot(int paramInt)
  {
    return sBootstrapSymbolTable.makeOrphan(paramInt);
  }
  
  private void initTables(int paramInt)
  {
    this._symbols = new String[paramInt];
    this._buckets = new Bucket[paramInt >> 1];
    this._indexMask = (paramInt - 1);
    this._size = 0;
    this._longestCollisionList = 0;
    this._sizeThreshold = _thresholdSize(paramInt);
  }
  
  private CharsToNameCanonicalizer makeOrphan(int paramInt)
  {
    return new CharsToNameCanonicalizer(null, -1, this._symbols, this._buckets, this._size, paramInt, this._longestCollisionList);
  }
  
  private void mergeChild(CharsToNameCanonicalizer paramCharsToNameCanonicalizer)
  {
    if (paramCharsToNameCanonicalizer.size() > 12000) {
      try
      {
        initTables(256);
        this._dirty = false;
        return;
      }
      finally
      {
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
    } else if (paramCharsToNameCanonicalizer.size() > size()) {
      try
      {
        this._symbols = paramCharsToNameCanonicalizer._symbols;
        this._buckets = paramCharsToNameCanonicalizer._buckets;
        this._size = paramCharsToNameCanonicalizer._size;
        this._sizeThreshold = paramCharsToNameCanonicalizer._sizeThreshold;
        this._indexMask = paramCharsToNameCanonicalizer._indexMask;
        this._longestCollisionList = paramCharsToNameCanonicalizer._longestCollisionList;
        this._dirty = false;
      }
      finally
      {
        localObject1 = finally;
        throw ((Throwable)localObject1);
      }
    }
  }
  
  private void rehash()
  {
    int i = this._symbols.length;
    int j = i + i;
    if (j > 65536)
    {
      this._size = 0;
      this._canonicalize = false;
      this._symbols = new String[64];
      this._buckets = new Bucket[32];
      this._indexMask = 63;
      this._dirty = true;
    }
    int k;
    label166:
    do
    {
      return;
      String[] arrayOfString = this._symbols;
      Bucket[] arrayOfBucket = this._buckets;
      this._symbols = new String[j];
      this._buckets = new Bucket[j >> 1];
      this._indexMask = (j - 1);
      this._sizeThreshold = _thresholdSize(j);
      k = 0;
      int m = 0;
      int n = 0;
      if (n < i)
      {
        String str2 = arrayOfString[n];
        int i5;
        if (str2 != null)
        {
          k++;
          i5 = _hashToIndex(calcHash(str2));
          if (this._symbols[i5] != null) {
            break label166;
          }
          this._symbols[i5] = str2;
        }
        for (;;)
        {
          n++;
          break;
          int i6 = i5 >> 1;
          Bucket localBucket3 = new Bucket(str2, this._buckets[i6]);
          this._buckets[i6] = localBucket3;
          m = Math.max(m, localBucket3.length);
        }
      }
      int i1 = i >> 1;
      for (int i2 = 0; i2 < i1; i2++)
      {
        Bucket localBucket1 = arrayOfBucket[i2];
        if (localBucket1 != null)
        {
          k++;
          String str1 = localBucket1.symbol;
          int i3 = _hashToIndex(calcHash(str1));
          if (this._symbols[i3] == null) {
            this._symbols[i3] = str1;
          }
          for (;;)
          {
            localBucket1 = localBucket1.next;
            break;
            int i4 = i3 >> 1;
            Bucket localBucket2 = new Bucket(str1, this._buckets[i4]);
            this._buckets[i4] = localBucket2;
            m = Math.max(m, localBucket2.length);
          }
        }
      }
      this._longestCollisionList = m;
      this._overflows = null;
    } while (k == this._size);
    throw new Error("Internal error on SymbolTable.rehash(): had " + this._size + " entries; now have " + k + ".");
  }
  
  public int _hashToIndex(int paramInt)
  {
    int i = paramInt + (paramInt >>> 15);
    int j = i ^ i << 7;
    return j + (j >>> 3) & this._indexMask;
  }
  
  public int bucketCount()
  {
    return this._symbols.length;
  }
  
  public int calcHash(String paramString)
  {
    int i = paramString.length();
    int j = this._hashSeed;
    for (int k = 0; k < i; k++) {
      j = j * 33 + paramString.charAt(k);
    }
    if (j == 0) {
      j = 1;
    }
    return j;
  }
  
  public int calcHash(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    int i = this._hashSeed;
    int j = paramInt1;
    int k = paramInt1 + paramInt2;
    while (j < k)
    {
      i = i * 33 + paramArrayOfChar[j];
      j++;
    }
    if (i == 0) {
      i = 1;
    }
    return i;
  }
  
  public int collisionCount()
  {
    int i = 0;
    for (Bucket localBucket : this._buckets) {
      if (localBucket != null) {
        i += localBucket.length;
      }
    }
    return i;
  }
  
  public String findSymbol(char[] paramArrayOfChar, int paramInt1, int paramInt2, int paramInt3)
  {
    String str;
    if (paramInt2 < 1) {
      str = "";
    }
    for (;;)
    {
      return str;
      if (!this._canonicalize)
      {
        str = new String(paramArrayOfChar, paramInt1, paramInt2);
      }
      else
      {
        int i = _hashToIndex(paramInt3);
        str = this._symbols[i];
        if (str != null)
        {
          if (str.length() == paramInt2)
          {
            int j = 0;
            for (;;)
            {
              if (str.charAt(j) == paramArrayOfChar[(paramInt1 + j)])
              {
                j++;
                if (j == paramInt2) {
                  break;
                }
              }
            }
          }
          Bucket localBucket = this._buckets[(i >> 1)];
          if (localBucket != null)
          {
            str = localBucket.has(paramArrayOfChar, paramInt1, paramInt2);
            if (str != null) {
              continue;
            }
            str = _findSymbol2(paramArrayOfChar, paramInt1, paramInt2, localBucket.next);
            if (str != null) {
              continue;
            }
          }
        }
        str = _addSymbol(paramArrayOfChar, paramInt1, paramInt2, paramInt3, i);
      }
    }
  }
  
  public int hashSeed()
  {
    return this._hashSeed;
  }
  
  public CharsToNameCanonicalizer makeChild(int paramInt)
  {
    try
    {
      String[] arrayOfString = this._symbols;
      Bucket[] arrayOfBucket = this._buckets;
      int i = this._size;
      int j = this._hashSeed;
      int k = this._longestCollisionList;
      return new CharsToNameCanonicalizer(this, paramInt, arrayOfString, arrayOfBucket, i, j, k);
    }
    finally {}
  }
  
  public int maxCollisionLength()
  {
    return this._longestCollisionList;
  }
  
  public boolean maybeDirty()
  {
    return this._dirty;
  }
  
  public void release()
  {
    if (!maybeDirty()) {}
    for (;;)
    {
      return;
      if ((this._parent != null) && (this._canonicalize))
      {
        this._parent.mergeChild(this);
        this._dirty = false;
      }
    }
  }
  
  protected void reportTooManyCollisions(int paramInt)
  {
    throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._size + ") now exceeds maximum, " + paramInt + " -- suspect a DoS attack based on hash collisions");
  }
  
  public int size()
  {
    return this._size;
  }
  
  static final class Bucket
  {
    public final int length;
    public final Bucket next;
    public final String symbol;
    
    public Bucket(String paramString, Bucket paramBucket)
    {
      this.symbol = paramString;
      this.next = paramBucket;
      if (paramBucket == null) {}
      for (int i = 1;; i = 1 + paramBucket.length)
      {
        this.length = i;
        return;
      }
    }
    
    public String has(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      String str = null;
      if (this.symbol.length() != paramInt2) {
        break label20;
      }
      for (;;)
      {
        return str;
        int i = 0;
        label20:
        if (this.symbol.charAt(i) == paramArrayOfChar[(paramInt1 + i)])
        {
          i++;
          if (i < paramInt2) {
            break;
          }
          str = this.symbol;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/sym/CharsToNameCanonicalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */