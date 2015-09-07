package es.caib.portafib.back.utils;

import es.caib.portafib.utils.Constants;
import es.caib.portafib.utils.SignBoxRectangle;

/**
 * 
 * @author anadal
 *
 */
public class AppletSignFile {

  public String source;
  
  public String destination;
  
  public String idname;

  /**
   *  Constants.TAULADEFIRMES_SENSETAULA = 0;
   *  Constants.TAULADEFIRMES_PRIMERAPAGINA = 1;
   *  Constants.TAULADEFIRMES_DARRERAPAGINA = -1;
   */
  public long locationSignTable;

  public String reason;
  
  public String firmatPerFormat;
  
  public int signNumber;
  
  public String languageSign;
  
  public long signType;
  
  public long signAlgorithm;
  
  public boolean signMode;
  
  public SignBoxRectangle signBoxRectangle = null;

  /**
   * @param source
   * @param destination
   * @param idname
   * @param location_sign_table
   * @param reason
   * @param sign_number
   */
  public AppletSignFile(String source, String destination, String idname,
      long location_sign_table, String reason, int sign_number, String languageSign,
      long signType, long signAlgorithm, boolean signMode, String firmatPerFormat) {
    super();
    this.source = source;
    this.destination = destination;
    this.idname = idname;
    this.locationSignTable = location_sign_table;
    this.reason = reason;
    this.signNumber = sign_number;
    this.languageSign = languageSign;
    
    this.signType = signType;
    this.signAlgorithm = signAlgorithm;
    this.signMode = signMode;
    
    this.firmatPerFormat = firmatPerFormat;
    
    if (this.signType == Constants.TIPUSFIRMA_PADES &&
        (this.locationSignTable == Constants.TAULADEFIRMES_PRIMERAPAGINA
       || this.locationSignTable == Constants.TAULADEFIRMES_DARRERAPAGINA)) {
      this.signBoxRectangle = SignBoxRectangle.getPositionOfVisibleSignature(sign_number);
    }
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getIdname() {
    return idname;
  }

  public void setIdname(String idname) {
    this.idname = idname;
  }

  public long getLocationSignTable() {
    return locationSignTable;
  }

  public void setLocationSignTable(long locationSignTable) {
    this.locationSignTable = locationSignTable;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public int getSignNumber() {
    return signNumber;
  }

  public void setSignNumber(int signNumber) {
    this.signNumber = signNumber;
  }

  public String getLanguageSign() {
    return languageSign;
  }

  public void setLanguageSign(String languageSign) {
    this.languageSign = languageSign;
  }

  public long getSignType() {
    return signType;
  }

  public void setSignType(long signType) {
    this.signType = signType;
  }

  public long getSignAlgorithm() {
    return signAlgorithm;
  }

  public void setSignAlgorithm(long signAlgorithm) {
    this.signAlgorithm = signAlgorithm;
  }

  public boolean isSignMode() {
    return signMode;
  }

  public void setSignMode(boolean signMode) {
    this.signMode = signMode;
  }

  public SignBoxRectangle getSignBoxRectangle() {
    return signBoxRectangle;
  }

  public void setSignBoxRectangle(SignBoxRectangle signBoxRectangle) {
    this.signBoxRectangle = signBoxRectangle;
  }

  public String getFirmatPerFormat() {
    return firmatPerFormat;
  }

  public void setFirmatPerFormat(String firmatPerFormat) {
    this.firmatPerFormat = firmatPerFormat;
  }

}
