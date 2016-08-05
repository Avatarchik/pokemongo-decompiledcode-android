package com.fasterxml.jackson.core.sym;

public final class Name1
  extends Name
{
  private static final Name1 EMPTY = new Name1("", 0, 0);
  private final int q;
  
  Name1(String paramString, int paramInt1, int paramInt2)
  {
    super(paramString, paramInt1);
    this.q = paramInt2;
  }
  
  public static Name1 getEmptyName()
  {
    return EMPTY;
  }
  
  public boolean equals(int paramInt)
  {
    if (paramInt == this.q) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean equals(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == this.q) && (paramInt2 == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean equals(int paramInt1, int paramInt2, int paramInt3)
  {
    return false;
  }
  
  public boolean equals(int[] paramArrayOfInt, int paramInt)
  {
    int i = 1;
    if ((paramInt == i) && (paramArrayOfInt[0] == this.q)) {}
    for (;;)
    {
      return i;
      i = 0;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/sym/Name1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */