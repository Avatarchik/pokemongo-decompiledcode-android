package android.support.v4.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.os.CancellationSignal;
import android.os.Handler;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

public final class FingerprintManagerCompatApi23
{
  public static void authenticate(Context paramContext, CryptoObject paramCryptoObject, int paramInt, Object paramObject, AuthenticationCallback paramAuthenticationCallback, Handler paramHandler)
  {
    getFingerprintManager(paramContext).authenticate(wrapCryptoObject(paramCryptoObject), (CancellationSignal)paramObject, paramInt, wrapCallback(paramAuthenticationCallback), paramHandler);
  }
  
  private static FingerprintManager getFingerprintManager(Context paramContext)
  {
    return (FingerprintManager)paramContext.getSystemService(FingerprintManager.class);
  }
  
  public static boolean hasEnrolledFingerprints(Context paramContext)
  {
    return getFingerprintManager(paramContext).hasEnrolledFingerprints();
  }
  
  public static boolean isHardwareDetected(Context paramContext)
  {
    return getFingerprintManager(paramContext).isHardwareDetected();
  }
  
  private static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject paramCryptoObject)
  {
    CryptoObject localCryptoObject = null;
    if (paramCryptoObject == null) {}
    for (;;)
    {
      return localCryptoObject;
      if (paramCryptoObject.getCipher() != null) {
        localCryptoObject = new CryptoObject(paramCryptoObject.getCipher());
      } else if (paramCryptoObject.getSignature() != null) {
        localCryptoObject = new CryptoObject(paramCryptoObject.getSignature());
      } else if (paramCryptoObject.getMac() != null) {
        localCryptoObject = new CryptoObject(paramCryptoObject.getMac());
      }
    }
  }
  
  private static FingerprintManager.AuthenticationCallback wrapCallback(AuthenticationCallback paramAuthenticationCallback)
  {
    new FingerprintManager.AuthenticationCallback()
    {
      public void onAuthenticationError(int paramAnonymousInt, CharSequence paramAnonymousCharSequence)
      {
        FingerprintManagerCompatApi23.this.onAuthenticationError(paramAnonymousInt, paramAnonymousCharSequence);
      }
      
      public void onAuthenticationFailed()
      {
        FingerprintManagerCompatApi23.this.onAuthenticationFailed();
      }
      
      public void onAuthenticationHelp(int paramAnonymousInt, CharSequence paramAnonymousCharSequence)
      {
        FingerprintManagerCompatApi23.this.onAuthenticationHelp(paramAnonymousInt, paramAnonymousCharSequence);
      }
      
      public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult paramAnonymousAuthenticationResult)
      {
        FingerprintManagerCompatApi23.this.onAuthenticationSucceeded(new FingerprintManagerCompatApi23.AuthenticationResultInternal(FingerprintManagerCompatApi23.unwrapCryptoObject(paramAnonymousAuthenticationResult.getCryptoObject())));
      }
    };
  }
  
  private static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject paramCryptoObject)
  {
    FingerprintManager.CryptoObject localCryptoObject = null;
    if (paramCryptoObject == null) {}
    for (;;)
    {
      return localCryptoObject;
      if (paramCryptoObject.getCipher() != null) {
        localCryptoObject = new FingerprintManager.CryptoObject(paramCryptoObject.getCipher());
      } else if (paramCryptoObject.getSignature() != null) {
        localCryptoObject = new FingerprintManager.CryptoObject(paramCryptoObject.getSignature());
      } else if (paramCryptoObject.getMac() != null) {
        localCryptoObject = new FingerprintManager.CryptoObject(paramCryptoObject.getMac());
      }
    }
  }
  
  public static abstract class AuthenticationCallback
  {
    public void onAuthenticationError(int paramInt, CharSequence paramCharSequence) {}
    
    public void onAuthenticationFailed() {}
    
    public void onAuthenticationHelp(int paramInt, CharSequence paramCharSequence) {}
    
    public void onAuthenticationSucceeded(FingerprintManagerCompatApi23.AuthenticationResultInternal paramAuthenticationResultInternal) {}
  }
  
  public static final class AuthenticationResultInternal
  {
    private FingerprintManagerCompatApi23.CryptoObject mCryptoObject;
    
    public AuthenticationResultInternal(FingerprintManagerCompatApi23.CryptoObject paramCryptoObject)
    {
      this.mCryptoObject = paramCryptoObject;
    }
    
    public FingerprintManagerCompatApi23.CryptoObject getCryptoObject()
    {
      return this.mCryptoObject;
    }
  }
  
  public static class CryptoObject
  {
    private final Cipher mCipher;
    private final Mac mMac;
    private final Signature mSignature;
    
    public CryptoObject(Signature paramSignature)
    {
      this.mSignature = paramSignature;
      this.mCipher = null;
      this.mMac = null;
    }
    
    public CryptoObject(Cipher paramCipher)
    {
      this.mCipher = paramCipher;
      this.mSignature = null;
      this.mMac = null;
    }
    
    public CryptoObject(Mac paramMac)
    {
      this.mMac = paramMac;
      this.mCipher = null;
      this.mSignature = null;
    }
    
    public Cipher getCipher()
    {
      return this.mCipher;
    }
    
    public Mac getMac()
    {
      return this.mMac;
    }
    
    public Signature getSignature()
    {
      return this.mSignature;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/hardware/fingerprint/FingerprintManagerCompatApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */