package org.fundaciobit.pluginsib.signature.firmasimple.firmasimpleexemple.utils;

import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.pluginsib.signature.firmasimple.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;

/**
 * 
 * @author anadal
 *
 */
public class InfoGlobal {

  final FirmaSimpleFileInfoSignature peticio;

  FirmaSimpleSignatureResult resultat;

  String signatureDetails;

  /**
   * @param peticio
   */
  public InfoGlobal(FirmaSimpleFileInfoSignature peticio) {
    super();
    this.peticio = peticio;
  }

  public FirmaSimpleSignatureResult getResultat() {
    return resultat;
  }

  public void setResultat(FirmaSimpleSignatureResult resultat) {
    this.resultat = resultat;
  }

  public FirmaSimpleFileInfoSignature getPeticio() {
    return peticio;
  }

  public String getSignatureDetails() {
    return signatureDetails;
  }

  public void setSignatureDetails(String signatureDetails) {
    this.signatureDetails = signatureDetails;
  }

}
