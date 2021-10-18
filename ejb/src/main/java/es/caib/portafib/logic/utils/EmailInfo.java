package es.caib.portafib.logic.utils;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Fundacio Bit
 * 
 * @author anadal
 */
@XmlRootElement
public class EmailInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long idObjectSent;

  private String email;
  private String subject;
  private String message;
  private boolean html;
  private String usuariEntitatID;
  private long eventID = -1;

  /**
   * 
   */
  public EmailInfo() {
    super();
  }

  /**
   * @param idObjectSent
   * @param email
   * @param subject
   * @param message
   * @param html
   */
  public EmailInfo(Long idObjectSent, String email, String subject, String message,
      boolean html, String usuariEntitatID, long eventID) {
    super();
    this.idObjectSent = idObjectSent;
    this.email = email;
    this.subject = subject;
    this.message = message;
    this.html = html;
    this.usuariEntitatID = usuariEntitatID;
    this.eventID = eventID;
  }

  public Long getIdObjectSent() {
    return idObjectSent;
  }

  public void setIdObjectSent(Long idObjectSent) {
    this.idObjectSent = idObjectSent;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String url) {
    this.message = url;
  }

  public boolean isHtml() {
    return html;
  }

  public void setHtml(boolean html) {
    this.html = html;
  }

  public String getUsuariEntitatID() {
    return usuariEntitatID;
  }

  public void setUsuariEntitatID(String usuariEntitatID) {
    this.usuariEntitatID = usuariEntitatID;
  }

  public long getEventID() {
    return eventID;
  }

  public void setEventID(long eventID) {
    this.eventID = eventID;
  }

}
