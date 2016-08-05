package com.fasterxml.jackson.core.sym;

public final class Name3
  extends Name
{
  private final int q1;
  private final int q2;
  private final int q3;
  
  Name3(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramString, paramInt1);
    this.q1 = paramInt2;
    this.q2 = paramInt3;
    this.q3 = paramInt4;
  }
  
  public boolean equals(int paramInt)
  {
    return false;
  }
  
  public boolean equals(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public boolean equals(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((this.q1 == paramInt1) && (this.q2 == paramInt2) && (this.q3 == paramInt3)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean equals(int[] paramArrayOfInt, int paramInt)
  {
    boolean bool = true;
    if ((paramInt == 3) && (paramArrayOfInt[0] == this.q1) && (paramArrayOfInt[bool] == this.q2) && (paramArrayOfInt[2] == this.q3)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/sym/Name3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */