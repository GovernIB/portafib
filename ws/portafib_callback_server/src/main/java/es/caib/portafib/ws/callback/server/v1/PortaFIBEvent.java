package es.caib.portafib.ws.callback.server.v1;

import java.util.Date;

/**
 * 
 * @author anadal
 * 
 */
public class PortaFIBEvent {

  private int eventTypeID;

  private int version;

  private Date eventDate;

  // És l'aplicacio a qui va dirigida la informació
  private String applicationID;

  private String entityID;

  // ========== PeticioDeFirma ============
  SigningRequest signingRequest;

  // ========== EstatDeFirma ============

  private Sign sign;

  // És la persona que ha realitzat l'acció (firmat, rebutjat, validat, ...)
  private Actor actor;

  /**
   * 
   */
  public PortaFIBEvent() {
    super();
  }

  public Actor getActor() {
    return actor;
  }

  public void setActor(Actor actor) {
    this.actor = actor;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public int getEventTypeID() {
    return eventTypeID;
  }

  public void setEventTypeID(int eventTypeID) {
    this.eventTypeID = eventTypeID;
  }

  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
  }

  public String getApplicationID() {
    return applicationID;
  }

  public void setApplicationID(String applicationID) {
    this.applicationID = applicationID;
  }

  public String getEntityID() {
    return entityID;
  }

  public void setEntityID(String entityID) {
    this.entityID = entityID;
  }

  public SigningRequest getSigningRequest() {
    return signingRequest;
  }

  public void setSigningRequest(SigningRequest signingRequest) {
    this.signingRequest = signingRequest;
  }

  public Sign getSign() {
    return sign;
  }

  public void setSign(Sign sign) {
    this.sign = sign;
  }

}
