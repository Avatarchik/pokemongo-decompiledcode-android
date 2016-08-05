package com.upsight.android.managedvariables.internal.type;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.text.TextUtils;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.dispatcher.schema.AbstractUxmBlockProvider;
import com.upsight.android.internal.util.PreferencesHelper;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.managedvariables.type.UpsightManagedBoolean;
import com.upsight.android.managedvariables.type.UpsightManagedFloat;
import com.upsight.android.managedvariables.type.UpsightManagedInt;
import com.upsight.android.managedvariables.type.UpsightManagedString;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.inject.Named;

public class UxmBlockProvider
  extends AbstractUxmBlockProvider
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  private static final String HASH_ALGORITHM = "SHA-1";
  private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
  private Observer mBundleHashObserver = new Observer()
  {
    public void update(Observable paramAnonymousObservable, Object paramAnonymousObject)
    {
      UxmBlockProvider.this.put("bundle.hash", UxmBlockProvider.this.getBundleHash());
    }
  };
  private MessageDigest mDigest;
  private UpsightContext mUpsight;
  private UxmSchema mUxmSchema;
  private String mUxmSchemaRawString;
  
  UxmBlockProvider(UpsightContext paramUpsightContext, @Named("stringRawUxmSchema") String paramString, UxmSchema paramUxmSchema)
  {
    this.mUpsight = paramUpsightContext;
    this.mUxmSchemaRawString = paramString;
    this.mUxmSchema = paramUxmSchema;
    try
    {
      this.mDigest = MessageDigest.getInstance("SHA-1");
      PreferencesHelper.registerListener(paramUpsightContext, this);
      subscribeManagedVariables();
      put("bundle.schema_hash", getBundleSchemaHash());
      put("bundle.id", getBundleId());
      put("bundle.hash", getBundleHash());
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;)
      {
        paramUpsightContext.getLogger().e("Upsight", localNoSuchAlgorithmException, "Failed to generate UXM hashes because SHA-1 is unavailable on this device", new Object[0]);
      }
    }
  }
  
  private static String bytesToHex(byte[] paramArrayOfByte)
  {
    String str = null;
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0))
    {
      char[] arrayOfChar = new char[2 * paramArrayOfByte.length];
      for (int i = 0; i < paramArrayOfByte.length; i++)
      {
        int j = 0xFF & paramArrayOfByte[i];
        arrayOfChar[(i * 2)] = HEX_ARRAY[(j >>> 4)];
        arrayOfChar[(1 + i * 2)] = HEX_ARRAY[(j & 0xF)];
      }
      str = new String(arrayOfChar);
    }
    return str;
  }
  
  /**
   * @deprecated
   */
  private String generateHash(String paramString)
  {
    Object localObject1 = null;
    try
    {
      if ((this.mDigest != null) && (!TextUtils.isEmpty(paramString)))
      {
        byte[] arrayOfByte = paramString.getBytes();
        this.mDigest.update(arrayOfByte, 0, arrayOfByte.length);
        String str = bytesToHex(this.mDigest.digest());
        localObject1 = str;
      }
      return (String)localObject1;
    }
    finally
    {
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
  }
  
  private void subscribeManagedVariables()
  {
    Iterator localIterator = this.mUxmSchema.getAllOrdered().iterator();
    while (localIterator.hasNext())
    {
      UxmSchema.BaseSchema localBaseSchema = (UxmSchema.BaseSchema)localIterator.next();
      if ("com.upsight.uxm.string".equals(localBaseSchema.type)) {
        UpsightManagedString.fetch(this.mUpsight, localBaseSchema.tag).addObserver(this.mBundleHashObserver);
      } else if ("com.upsight.uxm.boolean".equals(localBaseSchema.type)) {
        UpsightManagedBoolean.fetch(this.mUpsight, localBaseSchema.tag).addObserver(this.mBundleHashObserver);
      } else if ("com.upsight.uxm.integer".equals(localBaseSchema.type)) {
        UpsightManagedInt.fetch(this.mUpsight, localBaseSchema.tag).addObserver(this.mBundleHashObserver);
      } else if ("com.upsight.uxm.float".equals(localBaseSchema.type)) {
        UpsightManagedFloat.fetch(this.mUpsight, localBaseSchema.tag).addObserver(this.mBundleHashObserver);
      }
    }
  }
  
  public String getBundleHash()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = this.mUxmSchema.getAllOrdered().iterator();
    if (localIterator.hasNext())
    {
      UxmSchema.BaseSchema localBaseSchema = (UxmSchema.BaseSchema)localIterator.next();
      Object localObject = null;
      if ("com.upsight.uxm.string".equals(localBaseSchema.type)) {
        localObject = UpsightManagedString.fetch(this.mUpsight, localBaseSchema.tag).get();
      }
      for (;;)
      {
        localStringBuilder.append(localBaseSchema.tag).append(localObject).append(localBaseSchema.type);
        break;
        if ("com.upsight.uxm.boolean".equals(localBaseSchema.type)) {
          localObject = UpsightManagedBoolean.fetch(this.mUpsight, localBaseSchema.tag).get();
        } else if ("com.upsight.uxm.integer".equals(localBaseSchema.type)) {
          localObject = UpsightManagedInt.fetch(this.mUpsight, localBaseSchema.tag).get();
        } else if ("com.upsight.uxm.float".equals(localBaseSchema.type)) {
          localObject = UpsightManagedFloat.fetch(this.mUpsight, localBaseSchema.tag).get();
        }
      }
    }
    return generateHash(localStringBuilder.toString());
  }
  
  public String getBundleId()
  {
    return PreferencesHelper.getString(this.mUpsight, "uxmBundleId", null);
  }
  
  public String getBundleSchemaHash()
  {
    return generateHash(this.mUxmSchemaRawString);
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if ("uxmBundleId".equals(paramString)) {
      put("bundle.id", getBundleId());
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/UxmBlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */