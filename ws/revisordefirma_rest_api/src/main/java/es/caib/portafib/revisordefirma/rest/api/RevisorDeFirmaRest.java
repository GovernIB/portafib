package es.caib.portafib.revisordefirma.rest.api;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author anadal
 *
 */
@XmlRootElement
public class RevisorDeFirmaRest {

  long revisorDeFirmaID;// ReadOnly
  java.lang.String usuariEntitatID;
  long firmaID;
  boolean obligatori;

  /** Constructor Buit */
  public RevisorDeFirmaRest() {
  }

  /** Constructor amb tots els camps */
  public RevisorDeFirmaRest(long revisorDeFirmaID, java.lang.String usuariEntitatID,
      long firmaID, boolean obligatori) {
    this.revisorDeFirmaID = revisorDeFirmaID;
    this.usuariEntitatID = usuariEntitatID;
    this.firmaID = firmaID;
    this.obligatori = obligatori;

  }

  public long getRevisorDeFirmaID() {
    return (revisorDeFirmaID);
  };

  public void setRevisorDeFirmaID(long _revisorDeFirmaID_) {
    this.revisorDeFirmaID = _revisorDeFirmaID_;
  };

  public java.lang.String getUsuariEntitatID() {
    return (usuariEntitatID);
  };

  public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
    this.usuariEntitatID = _usuariEntitatID_;
  };

  public long getFirmaID() {
    return (firmaID);
  };

  public void setFirmaID(long _firmaID_) {
    this.firmaID = _firmaID_;
  };

  public boolean isObligatori() {
    return (obligatori);
  };

  public void setObligatori(boolean _obligatori_) {
    this.obligatori = _obligatori_;
  };

}
