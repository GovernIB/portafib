package es.caib.portafib.logic.passarela.api;

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
public class PassarelaCustodyInfo {

  protected String custodyFileID = null;
  protected String custodyFileURL = null;
  protected String custodyFileCSV = null;
  protected String custodyFileCSVValidationWeb = null;
  protected String custodyFileCSVGenerationDefinition = null;

  protected String custodyFileOriginalFileDirectURL = null;
  protected String custodyFilePrintableFileDirectUrl = null;
  protected String custodyFileEniFileDirectUrl = null;

  public PassarelaCustodyInfo() {
    super();
  }

  public PassarelaCustodyInfo(String custodyFileID, String custodyFileURL,
      String custodyFileCSV, String custodyFileCSVValidationWeb,
      String custodyFileCSVGenerationDefinition, String custodyFileOriginalFileDirectURL,
      String custodyFilePrintableFileDirectUrl, String custodyFileEniFileDirectUrl) {
    super();
    this.custodyFileID = custodyFileID;
    this.custodyFileURL = custodyFileURL;
    this.custodyFileCSV = custodyFileCSV;
    this.custodyFileCSVValidationWeb = custodyFileCSVValidationWeb;
    this.custodyFileCSVGenerationDefinition = custodyFileCSVGenerationDefinition;
    this.custodyFileOriginalFileDirectURL = custodyFileOriginalFileDirectURL;
    this.custodyFilePrintableFileDirectUrl = custodyFilePrintableFileDirectUrl;
    this.custodyFileEniFileDirectUrl = custodyFileEniFileDirectUrl;
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

  public String getCustodyFileCSVValidationWeb() {
    return custodyFileCSVValidationWeb;
  }

  public void setCustodyFileCSVValidationWeb(String custodyFileCSVValidationWeb) {
    this.custodyFileCSVValidationWeb = custodyFileCSVValidationWeb;
  }

  public String getCustodyFileCSVGenerationDefinition() {
    return custodyFileCSVGenerationDefinition;
  }

  public void setCustodyFileCSVGenerationDefinition(String custodyFileCSVGenerationDefinition) {
    this.custodyFileCSVGenerationDefinition = custodyFileCSVGenerationDefinition;
  }

  public String getCustodyFileOriginalFileDirectURL() {
    return custodyFileOriginalFileDirectURL;
  }

  public void setCustodyFileOriginalFileDirectURL(String custodyFileOriginalFileDirectURL) {
    this.custodyFileOriginalFileDirectURL = custodyFileOriginalFileDirectURL;
  }

  public String getCustodyFilePrintableFileDirectUrl() {
    return custodyFilePrintableFileDirectUrl;
  }

  public void setCustodyFilePrintableFileDirectUrl(String custodyFilePrintableFileDirectUrl) {
    this.custodyFilePrintableFileDirectUrl = custodyFilePrintableFileDirectUrl;
  }

  public String getCustodyFileEniFileDirectUrl() {
    return custodyFileEniFileDirectUrl;
  }

  public void setCustodyFileEniFileDirectUrl(String custodyFileEniFileDirectUrl) {
    this.custodyFileEniFileDirectUrl = custodyFileEniFileDirectUrl;
  }

}
