package com.nianticlabs.pokemongoplus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import com.nianticlabs.pokemongoplus.bridge.BackgroundBridge;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypt
{
  private static final String ALGORITHM = "AES/CTR/NoPadding";
  private static final SecureRandom secureRandom = new SecureRandom();
  
  public static byte[] encryptNonce(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    try
    {
      byte[] arrayOfByte2 = new byte[16];
      secureRandom.nextBytes(arrayOfByte2);
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
      Cipher localCipher = Cipher.getInstance("AES/CTR/NoPadding");
      localCipher.init(1, localSecretKeySpec, new IvParameterSpec(arrayOfByte2));
      byte[] arrayOfByte3 = localCipher.doFinal(paramArrayOfByte2);
      arrayOfByte1 = new byte[arrayOfByte2.length + arrayOfByte3.length];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, arrayOfByte2.length);
      System.arraycopy(arrayOfByte3, 0, arrayOfByte1, arrayOfByte2.length, arrayOfByte3.length);
      return arrayOfByte1;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      for (;;)
      {
        localGeneralSecurityException.printStackTrace();
        byte[] arrayOfByte1 = new byte[0];
      }
    }
  }
  
  public static byte[] generateNonce()
  {
    byte[] arrayOfByte = new byte[16];
    secureRandom.nextBytes(arrayOfByte);
    return arrayOfByte;
  }
  
  public static byte[] getPersistedByteArray(String paramString)
  {
    String str = getPreferences().getString(paramString, null);
    if (str != null) {}
    for (byte[] arrayOfByte = Base64.decode(str, 0);; arrayOfByte = new byte[0]) {
      return arrayOfByte;
    }
  }
  
  private static SharedPreferences getPreferences()
  {
    return BackgroundBridge.currentContext.getSharedPreferences("pgp", 0);
  }
  
  public static void persistByteArray(String paramString, byte[] paramArrayOfByte)
  {
    String str = Base64.encodeToString(paramArrayOfByte, 0);
    getPreferences().edit().putString(paramString, str).commit();
  }
  
  public static byte[] unencryptNonce(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if (paramArrayOfByte2.length == 32) {}
    for (;;)
    {
      try
      {
        SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "AES");
        byte[] arrayOfByte2 = new byte[16];
        byte[] arrayOfByte3 = new byte[16];
        System.arraycopy(paramArrayOfByte2, 0, arrayOfByte2, 0, 16);
        System.arraycopy(paramArrayOfByte2, 16, arrayOfByte3, 0, 16);
        Cipher localCipher = Cipher.getInstance("AES/CTR/NoPadding");
        localCipher.init(2, localSecretKeySpec, new IvParameterSpec(arrayOfByte2));
        byte[] arrayOfByte4 = localCipher.doFinal(arrayOfByte3);
        arrayOfByte1 = arrayOfByte4;
        return arrayOfByte1;
      }
      catch (GeneralSecurityException localGeneralSecurityException)
      {
        localGeneralSecurityException.printStackTrace();
      }
      byte[] arrayOfByte1 = new byte[0];
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/pokemongoplus/util/Crypt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */