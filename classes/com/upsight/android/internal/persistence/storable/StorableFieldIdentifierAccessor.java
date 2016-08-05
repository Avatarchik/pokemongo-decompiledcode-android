package com.upsight.android.internal.persistence.storable;

import com.upsight.android.UpsightException;
import java.lang.reflect.Field;

class StorableFieldIdentifierAccessor
  implements StorableIdentifierAccessor
{
  private final Field mField;
  
  StorableFieldIdentifierAccessor(Field paramField)
  {
    if (paramField == null) {
      throw new IllegalArgumentException("Specified Field can not be null.");
    }
    this.mField = paramField;
  }
  
  public String getId(Object paramObject)
    throws UpsightException
  {
    try
    {
      this.mField.setAccessible(true);
      String str2 = (String)this.mField.get(paramObject);
      return str2;
    }
    catch (Exception localException)
    {
      String str1 = "Error accessing field: " + this.mField;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localException;
      throw new UpsightException(str1, arrayOfObject);
    }
    finally
    {
      this.mField.setAccessible(false);
    }
  }
  
  public void setId(Object paramObject, String paramString)
    throws UpsightException
  {
    try
    {
      this.mField.setAccessible(true);
      this.mField.set(paramObject, paramString);
      return;
    }
    catch (Exception localException)
    {
      String str = "Error accessing field: " + this.mField;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localException;
      throw new UpsightException(str, arrayOfObject);
    }
    finally
    {
      this.mField.setAccessible(false);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/StorableFieldIdentifierAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */