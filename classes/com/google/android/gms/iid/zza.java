package com.google.android.gms.iid;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class zza
{
  public static KeyPair zzwl()
  {
    try
    {
      KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
      localKeyPairGenerator.initialize(2048);
      KeyPair localKeyPair = localKeyPairGenerator.generateKeyPair();
      return localKeyPair;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new AssertionError(localNoSuchAlgorithmException);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/iid/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */