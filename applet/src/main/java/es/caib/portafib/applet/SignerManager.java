package es.caib.portafib.applet;
/**
 * 
 * @author anadal
 *
 */
public final class SignerManager {

  public static ISigner signer;

  public static ISigner getSigner() {
    return signer;
  }

  public static void setSigner(ISigner signer) {
    SignerManager.signer = signer;
  } 

}
