package es.caib.portafib.ws.test.v1;

import es.caib.portafib.ws.test.v1.helium.DocumentPortasignatures;

/**
 * 
 * @author anadal
 *
 */
public class DocInfo extends DocumentPortasignatures {
  
  protected String contentType;

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }



}
