package es.caib.portafib.api.interna.secure.apisimple.v1.commons;


/**
 * 
 * @author anadal
 *
 */

public class FirmaSimpleCustodyInfo {

  protected String custodyID = null;

  // String getCsv
  /** eEMGDE.Firma.FormatoFirma.ValorCSV (eEMGDE17.3) */
  protected String csv;

  // String getCsvValidationWeb
  protected String csvValidationWeb;

  // String getValidationFileUrl
  protected String validationFileUrl;

  // getCsvGenerationDefinition
  /**
   * eEMGDE.DefinicionGeneracionCSV (eEMGDE17.4):
   */
  protected String csvGenerationDefinition;

  // String getOriginalFileUrl
  protected String originalFileDirectURL; //

  // String getPrintableFileUrl
  protected String printableFileDirectUrl;

  // String getEniFileUrl
  protected String eniFileDirectUrl;

  // Futura Integració amb Api d'Arxiu
  protected String expedientID = null;

  // Futura Integració amb Api d'Arxiu
  protected String documentID = null;

  public FirmaSimpleCustodyInfo() {
    super();
  }

  public FirmaSimpleCustodyInfo(String custodyID, String csv, String csvValidationWeb,
      String validationFileUrl, String csvGenerationDefinition, String originalFileDirectURL,
      String printableFileDirectUrl, String eniFileDirectUrl) {
    super();
    this.custodyID = custodyID;
    this.csv = csv;
    this.csvValidationWeb = csvValidationWeb;
    this.validationFileUrl = validationFileUrl;
    this.csvGenerationDefinition = csvGenerationDefinition;
    this.originalFileDirectURL = originalFileDirectURL;
    this.printableFileDirectUrl = printableFileDirectUrl;
    this.eniFileDirectUrl = eniFileDirectUrl;
  }

  public FirmaSimpleCustodyInfo(String custodyID, String csv, String csvValidationWeb,
      String validationFileUrl, String csvGenerationDefinition, String originalFileDirectURL,
      String printableFileDirectUrl, String eniFileDirectUrl, String expedientID,
      String documentID) {
    super();
    this.custodyID = custodyID;
    this.csv = csv;
    this.csvValidationWeb = csvValidationWeb;
    this.validationFileUrl = validationFileUrl;
    this.csvGenerationDefinition = csvGenerationDefinition;
    this.originalFileDirectURL = originalFileDirectURL;
    this.printableFileDirectUrl = printableFileDirectUrl;
    this.eniFileDirectUrl = eniFileDirectUrl;
    this.expedientID = expedientID;
    this.documentID = documentID;
  }

  public String getCustodyID() {
    return custodyID;
  }

  public void setCustodyID(String custodyID) {
    this.custodyID = custodyID;
  }

  public String getCsv() {
    return csv;
  }

  public void setCsv(String csv) {
    this.csv = csv;
  }

  public String getCsvValidationWeb() {
    return csvValidationWeb;
  }

  public void setCsvValidationWeb(String csvValidationWeb) {
    this.csvValidationWeb = csvValidationWeb;
  }

  public String getValidationFileUrl() {
    return validationFileUrl;
  }

  public void setValidationFileUrl(String validationFileUrl) {
    this.validationFileUrl = validationFileUrl;
  }

  public String getCsvGenerationDefinition() {
    return csvGenerationDefinition;
  }

  public void setCsvGenerationDefinition(String csvGenerationDefinition) {
    this.csvGenerationDefinition = csvGenerationDefinition;
  }

  public String getOriginalFileDirectURL() {
    return originalFileDirectURL;
  }

  public void setOriginalFileDirectURL(String originalFileDirectURL) {
    this.originalFileDirectURL = originalFileDirectURL;
  }

  public String getPrintableFileDirectUrl() {
    return printableFileDirectUrl;
  }

  public void setPrintableFileDirectUrl(String printableFileDirectUrl) {
    this.printableFileDirectUrl = printableFileDirectUrl;
  }

  public String getEniFileDirectUrl() {
    return eniFileDirectUrl;
  }

  public void setEniFileDirectUrl(String eniFileDirectUrl) {
    this.eniFileDirectUrl = eniFileDirectUrl;
  }

  public String getExpedientID() {
    return expedientID;
  }

  public void setExpedientID(String expedientID) {
    this.expedientID = expedientID;
  }

  public String getDocumentID() {
    return documentID;
  }

  public void setDocumentID(String documentID) {
    this.documentID = documentID;
  }

}
