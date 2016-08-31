package org.fundaciobit.plugins.signature.api;

/**
 * 
 * @author anadal
 *
 */
public interface ISecureVerificationCodeStamper {
  
  
  /**
   * Estampa el codi segur de verificació a un pdf. La informació del missatge, 
   * codi de bares i pàgina estampar es troba en FileInfoSignature.getSecureVerificationCodeStampInfo()  
   * @param pdf PDf original
   * @return PDF estampat amb misstage i/o codi de barres.
   * @throws Exception
   */
  public byte[] stamp(byte[] pdf) throws Exception; 
  

}
