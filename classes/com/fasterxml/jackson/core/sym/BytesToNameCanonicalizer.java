package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

@Deprecated
public final class BytesToNameCanonicalizer
{
  private static final int DEFAULT_T_SIZE = 64;
  static final int INITIAL_COLLISION_LEN = 32;
  static final int LAST_VALID_BUCKET = 254;
  private static final int MAX_COLL_CHAIN_LENGTH = 200;
  static final int MAX_ENTRIES_FOR_REUSE = 6000;
  private static final int MAX_T_SIZE = 65536;
  static final int MIN_HASH_SIZE = 16;
  private static final int MULT = 33;
  private static final int MULT2 = 65599;
  private static final int MULT3 = 31;
  protected int _collCount;
  protected int _collEnd;
  protected Bucket[] _collList;
  private boolean _collListShared;
  protected int _count;
  protected final boolean _failOnDoS;
  protected int[] _hash;
  protected int _hashMask;
  private boolean _hashShared;
  protected boolean _intern;
  protected int _longestCollisionList;
  protected Name[] _mainNames;
  private boolean _namesShared;
  private transient boolean _needRehash;
  protected BitSet _overflows;
  protected final BytesToNameCanonicalizer _parent;
  private final int _seed;
  protected final AtomicReference<TableInfo> _tableInfo;
  
  private BytesToNameCanonicalizer(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2)
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
      this._tableInfo = new AtomicReference(initTableInfo(paramInt1));
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
  
  private BytesToNameCanonicalizer(BytesToNameCanonicalizer paramBytesToNameCanonicalizer, boolean paramBoolean1, int paramInt, boolean paramBoolean2, TableInfo paramTableInfo)
  {
    this._parent = paramBytesToNameCanonicalizer;
    this._seed = paramInt;
    this._intern = paramBoolean1;
    this._failOnDoS = paramBoolean2;
    this._tableInfo = null;
    this._count = paramTableInfo.count;
    this._hashMask = paramTableInfo.mainHashMask;
    this._hash = paramTableInfo.mainHash;
    this._mainNames = paramTableInfo.mainNames;
    this._collList = paramTableInfo.collList;
    this._collCount = paramTableInfo.collCount;
    this._collEnd = paramTableInfo.collEnd;
    this._longestCollisionList = paramTableInfo.longestCollisionList;
    this._needRehash = false;
    this._hashShared = true;
    this._namesShared = true;
    this._collListShared = true;
  }
  
  private void _addSymbol(int paramInt, Name paramName)
  {
    if (this._hashShared) {
      unshareMain();
    }
    if (this._needRehash) {
      rehash();
    }
    this._count = (1 + this._count);
    int i = paramInt & this._hashMask;
    int i1;
    if (this._mainNames[i] == null)
    {
      this._hash[i] = (paramInt << 8);
      if (this._namesShared) {
        unshareNames();
      }
      this._mainNames[i] = paramName;
      int n = this._hash.length;
      if (this._count > n >> 1)
      {
        i1 = n >> 2;
        if (this._count <= n - i1) {
          break label302;
        }
        this._needRehash = true;
      }
    }
    for (;;)
    {
      return;
      if (this._collListShared) {
        unshareCollision();
      }
      this._collCount = (1 + this._collCount);
      int j = this._hash[i];
      int k = j & 0xFF;
      int m;
      if (k == 0) {
        if (this._collEnd <= 254)
        {
          m = this._collEnd;
          this._collEnd = (1 + this._collEnd);
          if (m >= this._collList.length) {
            expandCollision();
          }
          label200:
          this._hash[i] = (j & 0xFF00 | m + 1);
        }
      }
      Bucket localBucket;
      for (;;)
      {
        localBucket = new Bucket(paramName, this._collList[m]);
        if (localBucket.length <= 200) {
          break label274;
        }
        _handleSpillOverflow(m, localBucket);
        break;
        m = findBestBucket();
        break label200;
        m = k - 1;
      }
      label274:
      this._collList[m] = localBucket;
      this._longestCollisionList = Math.max(localBucket.length, this._longestCollisionList);
      break;
      label302:
      if (this._collCount >= i1) {
        this._needRehash = true;
      }
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
      this._collList[paramInt] = null;
      this._count -= paramBucket.length;
      this._longestCollisionList = -1;
      return;
      if (this._overflows.get(paramInt))
      {
        if (this._failOnDoS) {
          reportTooManyCollisions(200);
        }
        this._intern = false;
      }
      else
      {
        this._overflows.set(paramInt);
      }
    }
  }
  
  protected static int[] calcQuads(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    int[] arrayOfInt = new int[(i + 3) / 4];
    int m;
    for (int j = 0; j < i; j = m + 1)
    {
      int k = 0xFF & paramArrayOfByte[j];
      m = j + 1;
      if (m < i)
      {
        k = k << 8 | 0xFF & paramArrayOfByte[m];
        m++;
        if (m < i)
        {
          k = k << 8 | 0xFF & paramArrayOfByte[m];
          m++;
          if (m < i) {
            k = k << 8 | 0xFF & paramArrayOfByte[m];
          }
        }
      }
      arrayOfInt[(m >> 2)] = k;
    }
    return arrayOfInt;
  }
  
  private static Name constructName(int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    if (paramInt3 == 0) {}
    for (Object localObject = new Name1(paramString, paramInt1, paramInt2);; localObject = new Name2(paramString, paramInt1, paramInt2, paramInt3)) {
      return (Name)localObject;
    }
  }
  
  private static Name constructName(int paramInt1, String paramString, int[] paramArrayOfInt, int paramInt2)
  {
    Object localObject;
    if (paramInt2 < 4) {
      switch (paramInt2)
      {
      default: 
        localObject = new Name3(paramString, paramInt1, paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
      }
    }
    for (;;)
    {
      return (Name)localObject;
      localObject = new Name1(paramString, paramInt1, paramArrayOfInt[0]);
      continue;
      localObject = new Name2(paramString, paramInt1, paramArrayOfInt[0], paramArrayOfInt[1]);
      continue;
      localObject = NameN.construct(paramString, paramInt1, paramArrayOfInt, paramInt2);
    }
  }
  
  public static BytesToNameCanonicalizer createRoot()
  {
    long l = System.currentTimeMillis();
    return createRoot(0x1 | (int)l + (int)(l >>> 32));
  }
  
  protected static BytesToNameCanonicalizer createRoot(int paramInt)
  {
    return new BytesToNameCanonicalizer(64, true, paramInt, true);
  }
  
  private void expandCollision()
  {
    Bucket[] arrayOfBucket = this._collList;
    this._collList = ((Bucket[])Arrays.copyOf(arrayOfBucket, 2 * arrayOfBucket.length));
  }
  
  private int findBestBucket()
  {
    Bucket[] arrayOfBucket = this._collList;
    int i = Integer.MAX_VALUE;
    int j = -1;
    int k = 0;
    int m = this._collEnd;
    Bucket localBucket;
    if (k < m)
    {
      localBucket = arrayOfBucket[k];
      if (localBucket != null) {}
    }
    for (;;)
    {
      return k;
      int n = localBucket.length;
      if (n < i)
      {
        if (n != 1)
        {
          i = n;
          j = k;
        }
      }
      else
      {
        k++;
        break;
        k = j;
      }
    }
  }
  
  public static Name getEmptyName()
  {
    return Name1.getEmptyName();
  }
  
  private TableInfo initTableInfo(int paramInt)
  {
    return new TableInfo(0, paramInt - 1, new int[paramInt], new Name[paramInt], null, 0, 0, 0);
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
        paramTableInfo = initTableInfo(64);
      }
      this._tableInfo.compareAndSet(localTableInfo, paramTableInfo);
    }
  }
  
  private void nukeSymbols()
  {
    this._count = 0;
    this._longestCollisionList = 0;
    Arrays.fill(this._hash, 0);
    Arrays.fill(this._mainNames, null);
    Arrays.fill(this._collList, null);
    this._collCount = 0;
    this._collEnd = 0;
  }
  
  private void rehash()
  {
    this._needRehash = false;
    this._namesShared = false;
    int i = this._hash.length;
    int j = i + i;
    if (j > 65536) {
      nukeSymbols();
    }
    int k;
    label341:
    int i3;
    do
    {
      int n;
      for (;;)
      {
        return;
        this._hash = new int[j];
        this._hashMask = (j - 1);
        Name[] arrayOfName = this._mainNames;
        this._mainNames = new Name[j];
        k = 0;
        for (int m = 0; m < i; m++)
        {
          Name localName2 = arrayOfName[m];
          if (localName2 != null)
          {
            k++;
            int i9 = localName2.hashCode();
            int i10 = i9 & this._hashMask;
            this._mainNames[i10] = localName2;
            this._hash[i10] = (i9 << 8);
          }
        }
        n = this._collEnd;
        if (n != 0) {
          break;
        }
        this._longestCollisionList = 0;
      }
      this._collCount = 0;
      this._collEnd = 0;
      this._collListShared = false;
      int i1 = 0;
      Bucket[] arrayOfBucket = this._collList;
      this._collList = new Bucket[arrayOfBucket.length];
      for (int i2 = 0; i2 < n; i2++)
      {
        Bucket localBucket1 = arrayOfBucket[i2];
        while (localBucket1 != null)
        {
          k++;
          Name localName1 = localBucket1.name;
          int i4 = localName1.hashCode();
          int i5 = i4 & this._hashMask;
          int i6 = this._hash[i5];
          if (this._mainNames[i5] == null)
          {
            this._hash[i5] = (i4 << 8);
            this._mainNames[i5] = localName1;
            localBucket1 = localBucket1.next;
          }
          else
          {
            this._collCount = (1 + this._collCount);
            int i7 = i6 & 0xFF;
            int i8;
            if (i7 == 0) {
              if (this._collEnd <= 254)
              {
                i8 = this._collEnd;
                this._collEnd = (1 + this._collEnd);
                if (i8 >= this._collList.length) {
                  expandCollision();
                }
                this._hash[i5] = (i6 & 0xFF00 | i8 + 1);
              }
            }
            for (;;)
            {
              Bucket localBucket2 = new Bucket(localName1, this._collList[i8]);
              this._collList[i8] = localBucket2;
              i1 = Math.max(i1, localBucket2.length);
              break;
              i8 = findBestBucket();
              break label341;
              i8 = i7 - 1;
            }
          }
        }
      }
      this._longestCollisionList = i1;
      i3 = this._count;
    } while (k == i3);
    throw new RuntimeException("Internal error: count after rehash " + k + "; should be " + this._count);
  }
  
  private void unshareCollision()
  {
    Bucket[] arrayOfBucket = this._collList;
    if (arrayOfBucket == null) {}
    for (this._collList = new Bucket[32];; this._collList = ((Bucket[])Arrays.copyOf(arrayOfBucket, arrayOfBucket.length)))
    {
      this._collListShared = false;
      return;
    }
  }
  
  private void unshareMain()
  {
    int[] arrayOfInt = this._hash;
    this._hash = Arrays.copyOf(arrayOfInt, arrayOfInt.length);
    this._hashShared = false;
  }
  
  private void unshareNames()
  {
    Name[] arrayOfName = this._mainNames;
    this._mainNames = ((Name[])Arrays.copyOf(arrayOfName, arrayOfName.length));
    this._namesShared = false;
  }
  
  public Name addName(String paramString, int paramInt1, int paramInt2)
  {
    if (this._intern) {
      paramString = InternCache.instance.intern(paramString);
    }
    if (paramInt2 == 0) {}
    for (int i = calcHash(paramInt1);; i = calcHash(paramInt1, paramInt2))
    {
      Name localName = constructName(i, paramString, paramInt1, paramInt2);
      _addSymbol(i, localName);
      return localName;
    }
  }
  
  public Name addName(String paramString, int[] paramArrayOfInt, int paramInt)
  {
    if (this._intern) {
      paramString = InternCache.instance.intern(paramString);
    }
    int i;
    if (paramInt < 4) {
      if (paramInt == 1) {
        i = calcHash(paramArrayOfInt[0]);
      }
    }
    for (;;)
    {
      Name localName = constructName(i, paramString, paramArrayOfInt, paramInt);
      _addSymbol(i, localName);
      return localName;
      if (paramInt == 2)
      {
        i = calcHash(paramArrayOfInt[0], paramArrayOfInt[1]);
      }
      else
      {
        i = calcHash(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
        continue;
        i = calcHash(paramArrayOfInt, paramInt);
      }
    }
  }
  
  public int bucketCount()
  {
    return this._hash.length;
  }
  
  public int calcHash(int paramInt)
  {
    int i = paramInt ^ this._seed;
    int j = i + (i >>> 15);
    return j ^ j >>> 9;
  }
  
  public int calcHash(int paramInt1, int paramInt2)
  {
    int i = (paramInt1 ^ paramInt1 >>> 15) + paramInt2 * 33 ^ this._seed;
    int j = i + (i >>> 7);
    return j ^ j >>> 4;
  }
  
  public int calcHash(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1 ^ this._seed;
    int j = 65599 * (paramInt2 + 33 * (i + (i >>> 9)));
    int k = paramInt3 ^ j + (j >>> 15);
    int m = k + (k >>> 17);
    int n = m + (m >>> 15);
    return n ^ n << 9;
  }
  
  public int calcHash(int[] paramArrayOfInt, int paramInt)
  {
    if (paramInt < 4) {
      throw new IllegalArgumentException();
    }
    int i = paramArrayOfInt[0] ^ this._seed;
    int j = 65599 * (33 * (i + (i >>> 9)) + paramArrayOfInt[1]);
    int k = j + (j >>> 15) ^ paramArrayOfInt[2];
    int m = k + (k >>> 17);
    for (int n = 3; n < paramInt; n++)
    {
      int i2 = m * 31 ^ paramArrayOfInt[n];
      int i3 = i2 + (i2 >>> 3);
      m = i3 ^ i3 << 7;
    }
    int i1 = m + (m >>> 15);
    return i1 ^ i1 << 9;
  }
  
  public int collisionCount()
  {
    return this._collCount;
  }
  
  public Name findName(int paramInt)
  {
    int i = calcHash(paramInt);
    int j = i & this._hashMask;
    int k = this._hash[j];
    Name localName;
    if ((i ^ k >> 8) << 8 == 0)
    {
      localName = this._mainNames[j];
      if (localName == null) {
        localName = null;
      }
    }
    for (;;)
    {
      return localName;
      if (!localName.equals(paramInt))
      {
        do
        {
          int m = k & 0xFF;
          if (m <= 0) {
            break label119;
          }
          int n = m - 1;
          Bucket localBucket = this._collList[n];
          if (localBucket == null) {
            break label119;
          }
          localName = localBucket.find(i, paramInt, 0);
          break;
        } while (k != 0);
        localName = null;
        continue;
        label119:
        localName = null;
      }
    }
  }
  
  public Name findName(int paramInt1, int paramInt2)
  {
    int i;
    int k;
    Name localName;
    if (paramInt2 == 0)
    {
      i = calcHash(paramInt1);
      int j = i & this._hashMask;
      k = this._hash[j];
      if ((i ^ k >> 8) << 8 != 0) {
        break label126;
      }
      localName = this._mainNames[j];
      if (localName != null) {
        break label70;
      }
      localName = null;
    }
    for (;;)
    {
      return localName;
      i = calcHash(paramInt1, paramInt2);
      break;
      label70:
      if (!localName.equals(paramInt1, paramInt2))
      {
        label126:
        do
        {
          int m = k & 0xFF;
          if (m <= 0) {
            break label137;
          }
          int n = m - 1;
          Bucket localBucket = this._collList[n];
          if (localBucket == null) {
            break label137;
          }
          localName = localBucket.find(i, paramInt1, paramInt2);
          break;
        } while (k != 0);
        localName = null;
        continue;
        label137:
        localName = null;
      }
    }
  }
  
  public Name findName(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = calcHash(paramInt1, paramInt2, paramInt3);
    int j = i & this._hashMask;
    int k = this._hash[j];
    Name localName;
    if ((i ^ k >> 8) << 8 == 0)
    {
      localName = this._mainNames[j];
      if (localName == null) {
        localName = null;
      }
    }
    for (;;)
    {
      return localName;
      if (!localName.equals(paramInt1, paramInt2, paramInt3))
      {
        do
        {
          int m = k & 0xFF;
          if (m <= 0) {
            break label131;
          }
          int n = m - 1;
          Bucket localBucket = this._collList[n];
          if (localBucket == null) {
            break label131;
          }
          localName = localBucket.find(i, paramInt1, paramInt2, paramInt3);
          break;
        } while (k != 0);
        localName = null;
        continue;
        label131:
        localName = null;
      }
    }
  }
  
  public Name findName(int[] paramArrayOfInt, int paramInt)
  {
    int i = 0;
    Name localName;
    if (paramInt < 4) {
      if (paramInt == 3) {
        localName = findName(paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2]);
      }
    }
    for (;;)
    {
      return localName;
      int i2 = paramArrayOfInt[0];
      if (paramInt < 2) {}
      for (;;)
      {
        localName = findName(i2, i);
        break;
        i = paramArrayOfInt[1];
      }
      int j = calcHash(paramArrayOfInt, paramInt);
      int k = j & this._hashMask;
      int m = this._hash[k];
      if ((j ^ m >> 8) << 8 == 0)
      {
        localName = this._mainNames[k];
        if ((localName == null) || (localName.equals(paramArrayOfInt, paramInt))) {}
      }
      else
      {
        while (m != 0)
        {
          int n = m & 0xFF;
          if (n <= 0) {
            break label181;
          }
          int i1 = n - 1;
          Bucket localBucket = this._collList[i1];
          if (localBucket == null) {
            break label181;
          }
          localName = localBucket.find(j, paramArrayOfInt, paramInt);
          break;
        }
        localName = null;
        continue;
        label181:
        localName = null;
      }
    }
  }
  
  public int hashSeed()
  {
    return this._seed;
  }
  
  public BytesToNameCanonicalizer makeChild(int paramInt)
  {
    return new BytesToNameCanonicalizer(this, JsonFactory.Feature.INTERN_FIELD_NAMES.enabledIn(paramInt), this._seed, JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(paramInt), (TableInfo)this._tableInfo.get());
  }
  
  @Deprecated
  public BytesToNameCanonicalizer makeChild(boolean paramBoolean1, boolean paramBoolean2)
  {
    return new BytesToNameCanonicalizer(this, paramBoolean2, this._seed, true, (TableInfo)this._tableInfo.get());
  }
  
  public int maxCollisionLength()
  {
    return this._longestCollisionList;
  }
  
  public boolean maybeDirty()
  {
    if (!this._hashShared) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void release()
  {
    if ((this._parent != null) && (maybeDirty()))
    {
      this._parent.mergeChild(new TableInfo(this));
      this._hashShared = true;
      this._namesShared = true;
      this._collListShared = true;
    }
  }
  
  protected void reportTooManyCollisions(int paramInt)
  {
    throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._count + ") now exceeds maximum, " + paramInt + " -- suspect a DoS attack based on hash collisions");
  }
  
  public int size()
  {
    if (this._tableInfo != null) {}
    for (int i = ((TableInfo)this._tableInfo.get()).count;; i = this._count) {
      return i;
    }
  }
  
  private static final class Bucket
  {
    public final int hash;
    public final int length;
    public final Name name;
    public final Bucket next;
    
    Bucket(Name paramName, Bucket paramBucket)
    {
      this.name = paramName;
      this.next = paramBucket;
      if (paramBucket == null) {}
      for (int i = 1;; i = 1 + paramBucket.length)
      {
        this.length = i;
        this.hash = paramName.hashCode();
        return;
      }
    }
    
    public Name find(int paramInt1, int paramInt2, int paramInt3)
    {
      Name localName;
      if ((this.hash == paramInt1) && (this.name.equals(paramInt2, paramInt3))) {
        localName = this.name;
      }
      for (;;)
      {
        return localName;
        for (Bucket localBucket = this.next;; localBucket = localBucket.next)
        {
          if (localBucket == null) {
            break label76;
          }
          if (localBucket.hash == paramInt1)
          {
            localName = localBucket.name;
            if (localName.equals(paramInt2, paramInt3)) {
              break;
            }
          }
        }
        label76:
        localName = null;
      }
    }
    
    public Name find(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      Name localName;
      if ((this.hash == paramInt1) && (this.name.equals(paramInt2, paramInt3, paramInt4))) {
        localName = this.name;
      }
      for (;;)
      {
        return localName;
        for (Bucket localBucket = this.next;; localBucket = localBucket.next)
        {
          if (localBucket == null) {
            break label80;
          }
          if (localBucket.hash == paramInt1)
          {
            localName = localBucket.name;
            if (localName.equals(paramInt2, paramInt3, paramInt4)) {
              break;
            }
          }
        }
        label80:
        localName = null;
      }
    }
    
    public Name find(int paramInt1, int[] paramArrayOfInt, int paramInt2)
    {
      Name localName;
      if ((this.hash == paramInt1) && (this.name.equals(paramArrayOfInt, paramInt2))) {
        localName = this.name;
      }
      for (;;)
      {
        return localName;
        for (Bucket localBucket = this.next;; localBucket = localBucket.next)
        {
          if (localBucket == null) {
            break label76;
          }
          if (localBucket.hash == paramInt1)
          {
            localName = localBucket.name;
            if (localName.equals(paramArrayOfInt, paramInt2)) {
              break;
            }
          }
        }
        label76:
        localName = null;
      }
    }
  }
  
  private static final class TableInfo
  {
    public final int collCount;
    public final int collEnd;
    public final BytesToNameCanonicalizer.Bucket[] collList;
    public final int count;
    public final int longestCollisionList;
    public final int[] mainHash;
    public final int mainHashMask;
    public final Name[] mainNames;
    
    public TableInfo(int paramInt1, int paramInt2, int[] paramArrayOfInt, Name[] paramArrayOfName, BytesToNameCanonicalizer.Bucket[] paramArrayOfBucket, int paramInt3, int paramInt4, int paramInt5)
    {
      this.count = paramInt1;
      this.mainHashMask = paramInt2;
      this.mainHash = paramArrayOfInt;
      this.mainNames = paramArrayOfName;
      this.collList = paramArrayOfBucket;
      this.collCount = paramInt3;
      this.collEnd = paramInt4;
      this.longestCollisionList = paramInt5;
    }
    
    public TableInfo(BytesToNameCanonicalizer paramBytesToNameCanonicalizer)
    {
      this.count = paramBytesToNameCanonicalizer._count;
      this.mainHashMask = paramBytesToNameCanonicalizer._hashMask;
      this.mainHash = paramBytesToNameCanonicalizer._hash;
      this.mainNames = paramBytesToNameCanonicalizer._mainNames;
      this.collList = paramBytesToNameCanonicalizer._collList;
      this.collCount = paramBytesToNameCanonicalizer._collCount;
      this.collEnd = paramBytesToNameCanonicalizer._collEnd;
      this.longestCollisionList = paramBytesToNameCanonicalizer._longestCollisionList;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/sym/BytesToNameCanonicalizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */