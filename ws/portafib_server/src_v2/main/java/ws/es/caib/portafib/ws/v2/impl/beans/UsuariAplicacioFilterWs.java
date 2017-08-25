package es.caib.portafib.ws.v2.impl.beans;

/**
 * 
 * @author anadal
 * 
 */
public class UsuariAplicacioFilterWs {

  private String filterByUsuariAplicacioID;
  private String filterByEntitatID;
  private String filterByCallBackURL;
  private Boolean filterByActiu;

  public String getFilterByUsuariAplicacioID() {
    return filterByUsuariAplicacioID;
  }

  public void setFilterByUsuariAplicacioID(String filterByUsuariAplicacioID) {
    this.filterByUsuariAplicacioID = filterByUsuariAplicacioID;
  }

  public String getFilterByEntitatID() {
    return filterByEntitatID;
  }

  public void setFilterByEntitatID(String filterByEntitatID) {
    this.filterByEntitatID = filterByEntitatID;
  }

  public String getFilterByCallBackURL() {
    return filterByCallBackURL;
  }

  public void setFilterByCallBackURL(String filterByCallBackURL) {
    this.filterByCallBackURL = filterByCallBackURL;
  }

  public Boolean getFilterByActiu() {
    return filterByActiu;
  }

  public void setFilterByActiu(Boolean filterByActiu) {
    this.filterByActiu = filterByActiu;
  }

}
