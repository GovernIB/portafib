package org.fundaciobit.plugins.signatureweb.miniappletinserversia;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.util.Properties;

import org.fundaciobit.plugins.signatureweb.miniappletutils.AbstractTriFaseSigner;

/**
 * 
 * @author anadal
 *
 */
public class MiniAppletInServerSIASigner extends AbstractTriFaseSigner {

  final String algorithm;

  final Properties params;

  /**
   * @param key
   */
  public MiniAppletInServerSIASigner(final String algorithm, final Properties params) {
    super();
    this.algorithm = algorithm;
    this.params = params;
  }

  @Override
  public byte[] step2_signHash(final String algorithm, final byte[] hashDocumentoParam)
      throws Exception {

    // NO FER RES
    throw new Exception("La firma es genera cridant al servidor de SIA");

  }

  public String getAlgorithm() {
    return algorithm;
  }

  public Properties getParams() {
    return params;
  }
  
  
  // XYZ
  public static void main(String[] args)throws Exception
  {
    
      long h = 256;
    
      MessageDigest md = MessageDigest.getInstance("SHA-" + h, "BC");
      ByteArrayInputStream fis = new ByteArrayInputStream("c:\\loging.log".getBytes());
      
      byte[] dataBytes = new byte[1024];
   
      int nread = 0; 
      while ((nread = fis.read(dataBytes)) != -1) {
        md.update(dataBytes, 0, nread);
      };
      byte[] mdbytes = md.digest();
      
      
      System.out.println(" HASH-" + h + " = " + mdbytes.length);
      
    
   
      /*
      //convert the byte to hex format method 1
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < mdbytes.length; i++) {
        sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
      }

      System.out.println("Hex format : " + sb.toString());
      
     //convert the byte to hex format method 2
      StringBuffer hexString = new StringBuffer();
    for (int i=0;i<mdbytes.length;i++) {
      hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
    }

    System.out.println("Hex format : " + hexString.toString());
    */
  }

}
