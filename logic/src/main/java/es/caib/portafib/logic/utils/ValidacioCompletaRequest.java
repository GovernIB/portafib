package es.caib.portafib.logic.utils;

import java.util.Map;

import es.caib.portafib.logic.utils.datasource.IPortaFIBDataSource;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class ValidacioCompletaRequest {

  protected String entitatID;

  protected boolean validarFitxerFirma;

  protected boolean checkCanviatDocFirmat;

  protected boolean comprovarNifFirma;

  protected IPortaFIBDataSource originalData;

  protected IPortaFIBDataSource signatureData;

  protected IPortaFIBDataSource documentDetachedData;

  protected int signTypeID;
  
  protected boolean signMode;

  protected String languageUI;

  protected Map<Integer, IPortaFIBDataSource> fitxersByNumFirma;

  protected int numFirmaPortaFIB;
  protected int numFirmesOriginals;

  protected String nifEsperat;

  /**
   * 
   * @param entitatID
   * @param validarFitxerFirma
   * @param checkCanviatDocFirmat
   * @param comprovarNifFirma
   * @param originalData
   * @param signatureData
   * @param documentDetachedData
   * @param signTypeID
   * @param languageUI
   * @param fitxersByNumFirma
   * @param numFirmaPortaFIB
   * @param numFirmesOriginals
   * @param nifEsperat
   */
  public ValidacioCompletaRequest(String entitatID, boolean validarFitxerFirma,
      boolean checkCanviatDocFirmat, boolean comprovarNifFirma, IPortaFIBDataSource originalData,
      IPortaFIBDataSource signatureData, IPortaFIBDataSource documentDetachedData,
      int signTypeID, boolean signMode, String languageUI,
      Map<Integer, IPortaFIBDataSource> fitxersByNumFirma, int numFirmaPortaFIB, int numFirmesOriginals,
      String nifEsperat) {
    super();
    this.entitatID = entitatID;
    this.validarFitxerFirma = validarFitxerFirma;
    this.checkCanviatDocFirmat = checkCanviatDocFirmat;
    this.comprovarNifFirma = comprovarNifFirma;
    this.originalData = originalData;
    this.signatureData = signatureData;
    this.documentDetachedData = documentDetachedData;
    this.signTypeID = signTypeID;
    this.signMode = signMode;
    this.languageUI = languageUI;
    this.fitxersByNumFirma = fitxersByNumFirma;
    this.numFirmaPortaFIB = numFirmaPortaFIB;
    this.numFirmesOriginals = numFirmesOriginals;
    this.nifEsperat = nifEsperat;
  }

  public Map<Integer, IPortaFIBDataSource> getFitxersByNumFirma() {
    return fitxersByNumFirma;
  }

  public void setFitxersByNumFirma(Map<Integer, IPortaFIBDataSource> fitxersByNumFirma) {
    this.fitxersByNumFirma = fitxersByNumFirma;
  }

  public boolean isValidarFitxerFirma() {
    return validarFitxerFirma;
  }

  public void setValidarFitxerFirma(boolean validarFitxerFirma) {
    this.validarFitxerFirma = validarFitxerFirma;
  }

  public int getSignTypeID() {
    return signTypeID;
  }

  public void setSignTypeID(int signTypeID) {
    this.signTypeID = signTypeID;
  }

  public String getEntitatID() {
    return entitatID;
  }

  public void setEntitatID(String entitatID) {
    this.entitatID = entitatID;
  }

  public String getLanguageUI() {
    return languageUI;
  }

  public void setLanguageUI(String languageUI) {
    this.languageUI = languageUI;
  }

  public boolean isCheckCanviatDocFirmat() {
    return checkCanviatDocFirmat;
  }

  public void setCheckCanviatDocFirmat(boolean checkCanviatDocFirmat) {
    this.checkCanviatDocFirmat = checkCanviatDocFirmat;
  }

  public IPortaFIBDataSource getOriginalData() {
    return originalData;
  }

  public void setOriginalData(IPortaFIBDataSource originalData) {
    this.originalData = originalData;
  }

  public IPortaFIBDataSource getSignatureData() {
    return signatureData;
  }

  public void setSignatureData(IPortaFIBDataSource signatureData) {
    this.signatureData = signatureData;
  }

  public IPortaFIBDataSource getDocumentDetachedData() {
    return documentDetachedData;
  }

  public void setDocumentDetachedData(IPortaFIBDataSource documentDetachedData) {
    this.documentDetachedData = documentDetachedData;
  }

  public int getNumFirmaPortaFIB() {
    return numFirmaPortaFIB;
  }

  public void setNumFirmaPortaFIB(int numFirmaPortaFIB) {
    this.numFirmaPortaFIB = numFirmaPortaFIB;
  }

  public int getNumFirmesOriginals() {
    return numFirmesOriginals;
  }

  public void setNumFirmesOriginals(int numFirmesOriginals) {
    this.numFirmesOriginals = numFirmesOriginals;
  }

  public boolean isComprovarNifFirma() {
    return comprovarNifFirma;
  }

  public void setComprovarNifFirma(boolean comprovarNifFirma) {
    this.comprovarNifFirma = comprovarNifFirma;
  }

  public String getNifEsperat() {
    return nifEsperat;
  }

  public void setNifEsperat(String nifEsperat) {
    this.nifEsperat = nifEsperat;
  }

  public boolean getSignMode() {
    return signMode;
  }

  public void setSignMode(boolean signMode) {
    this.signMode = signMode;
  }

}
