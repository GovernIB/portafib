package org.fundaciobit.apisib.apifirmasimple.v1.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaSimpleCustodyInfo {

  protected String custodyFileID;

  protected String custodyFileURL;

  /** eEMGDE.Firma.FormatoFirma.ValorCSV (eEMGDE17.3) */
  protected String custodyFileCSV;

  /**
   * eEMGDE.DefinicionGeneracionCSV (eEMGDE17.4):
   */
  protected String custodyFileCSVGenerationDefinition;

  protected String custodyFileCSVValidationWeb;

  public FirmaSimpleCustodyInfo() {
    super();
    // TODO Auto-generated constructor stub
  }

  public FirmaSimpleCustodyInfo(String custodyFileID, String custodyFileURL,
      String custodyFileCSV, String custodyFileCSVGenerationDefinition,
      String custodyFileCSVValidationWeb) {
    super();
    this.custodyFileID = custodyFileID;
    this.custodyFileURL = custodyFileURL;
    this.custodyFileCSV = custodyFileCSV;
    this.custodyFileCSVGenerationDefinition = custodyFileCSVGenerationDefinition;
    this.custodyFileCSVValidationWeb = custodyFileCSVValidationWeb;
  }

  public String getCustodyFileID() {
    return custodyFileID;
  }

  public void setCustodyFileID(String custodyFileID) {
    this.custodyFileID = custodyFileID;
  }

  public String getCustodyFileURL() {
    return custodyFileURL;
  }

  public void setCustodyFileURL(String custodyFileURL) {
    this.custodyFileURL = custodyFileURL;
  }

  public String getCustodyFileCSV() {
    return custodyFileCSV;
  }

  public void setCustodyFileCSV(String custodyFileCSV) {
    this.custodyFileCSV = custodyFileCSV;
  }

  public String getCustodyFileCSVGenerationDefinition() {
    return custodyFileCSVGenerationDefinition;
  }

  public void setCustodyFileCSVGenerationDefinition(String custodyFileCSVGenerationDefinition) {
    this.custodyFileCSVGenerationDefinition = custodyFileCSVGenerationDefinition;
  }

  public String getCustodyFileCSVValidationWeb() {
    return custodyFileCSVValidationWeb;
  }

  public void setCustodyFileCSVValidationWeb(String custodyFileCSVValidationWeb) {
    this.custodyFileCSVValidationWeb = custodyFileCSVValidationWeb;
  }

}
