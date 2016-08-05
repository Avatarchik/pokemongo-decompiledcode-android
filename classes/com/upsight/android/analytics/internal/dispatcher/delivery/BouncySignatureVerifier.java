package com.upsight.android.analytics.internal.dispatcher.delivery;

import android.text.TextUtils;
import android.util.Base64;
import com.upsight.android.UpsightContext;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

class BouncySignatureVerifier
  implements SignatureVerifier
{
  private static final String CHARSET_NAME = "UTF-8";
  private static final String CRYPTO_ALGORITHM_KEY = "RSA";
  private static final String CRYPTO_ALGORITHM_SIGNATURE = "SHA512WITHRSA";
  private static final String CRYPTO_PROVIDER = "BC";
  private UpsightLogger mLogger;
  private Signature mSigner;
  
  BouncySignatureVerifier(UpsightContext paramUpsightContext)
  {
    this.mLogger = paramUpsightContext.getLogger();
    String str = paramUpsightContext.getPublicKey();
    try
    {
      if (!TextUtils.isEmpty(str))
      {
        byte[] arrayOfByte = Base64.decode(str.getBytes("UTF-8"), 0);
        PublicKey localPublicKey = KeyFactory.getInstance("RSA", "BC").generatePublic(new X509EncodedKeySpec(arrayOfByte));
        Signature localSignature = Signature.getInstance("SHA512WITHRSA", "BC");
        localSignature.initVerify(localPublicKey);
        this.mSigner = localSignature;
        this.mLogger.d("Upsight", "Public key: " + localPublicKey, new Object[0]);
      }
      else
      {
        this.mLogger.e("Upsight", "Please check your public key.", new Object[0]);
      }
    }
    catch (IOException localIOException)
    {
      this.mLogger.e("Upsight", localIOException, "Failed to initialize " + BouncySignatureVerifier.class.getSimpleName(), new Object[0]);
    }
    catch (NoSuchProviderException localNoSuchProviderException)
    {
      for (;;) {}
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      for (;;) {}
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      for (;;) {}
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      for (;;) {}
    }
  }
  
  /**
   * @deprecated
   */
  public boolean verify(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    boolean bool1 = false;
    try
    {
      Signature localSignature = this.mSigner;
      if (localSignature != null) {}
      try
      {
        this.mSigner.update(paramArrayOfByte1);
        boolean bool2 = this.mSigner.verify(paramArrayOfByte2);
        bool1 = bool2;
      }
      catch (SignatureException localSignatureException)
      {
        for (;;)
        {
          this.mLogger.e("Upsight", localSignatureException, "Failed to verify signature " + BouncySignatureVerifier.class.getSimpleName(), new Object[0]);
        }
      }
      if (!bool1) {
        this.mLogger.e("Upsight", "Failed to verify signature. Please check your public key.", new Object[0]);
      }
      return bool1;
    }
    finally {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/BouncySignatureVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */