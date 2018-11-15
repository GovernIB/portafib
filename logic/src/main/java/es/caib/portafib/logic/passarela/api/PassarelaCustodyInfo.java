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

  String custodyFileID = null;
  String custodyFileURL = null;
  String custodyFileCSV = null;
  String custodyFileCSVValidationWeb = null;
  String custodyFileCSVGenerationDefinition = null;

  public PassarelaCustodyInfo() {
    super();
    // TODO Auto-generated constructor stub
  }

  public PassarelaCustodyInfo(String custodyFileID, String custodyFileURL,
      String custodyFileCSV, String custodyFileCSVValidationWeb,
      String custodyFileCSVGenerationDefinition) {
    super();
    this.custodyFileID = custodyFileID;
    this.custodyFileURL = custodyFileURL;
    this.custodyFileCSV = custodyFileCSV;
    this.custodyFileCSVValidationWeb = custodyFileCSVValidationWeb;
    this.custodyFileCSVGenerationDefinition = custodyFileCSVGenerationDefinition;
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

}
