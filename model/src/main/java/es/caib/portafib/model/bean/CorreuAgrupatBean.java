
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.CorreuAgrupat;


public class CorreuAgrupatBean implements CorreuAgrupat {



	long correuAgrupatID;// PK
	java.lang.String email;
	java.lang.String subject;
	java.lang.String message;
	boolean html;
	java.lang.String usuariEntitatID;
	java.sql.Timestamp dataCreacio;
	java.lang.String error;


  /** Constructor Buit */
  public CorreuAgrupatBean() {
  }

  /** Constructor amb tots els camps  */
  public CorreuAgrupatBean(long correuAgrupatID , java.lang.String email , java.lang.String subject , java.lang.String message , boolean html , java.lang.String usuariEntitatID , java.sql.Timestamp dataCreacio , java.lang.String error) {
    this.correuAgrupatID=correuAgrupatID;
    this.email=email;
    this.subject=subject;
    this.message=message;
    this.html=html;
    this.usuariEntitatID=usuariEntitatID;
    this.dataCreacio=dataCreacio;
    this.error=error;
}
  /** Constructor sense valors autoincrementals */
  public CorreuAgrupatBean(java.lang.String email , java.lang.String subject , java.lang.String message , boolean html , java.lang.String usuariEntitatID , java.sql.Timestamp dataCreacio , java.lang.String error) {
    this.email=email;
    this.subject=subject;
    this.message=message;
    this.html=html;
    this.usuariEntitatID=usuariEntitatID;
    this.dataCreacio=dataCreacio;
    this.error=error;
}
  public CorreuAgrupatBean(CorreuAgrupat __bean) {
    this.setCorreuAgrupatID(__bean.getCorreuAgrupatID());
    this.setEmail(__bean.getEmail());
    this.setSubject(__bean.getSubject());
    this.setMessage(__bean.getMessage());
    this.setHtml(__bean.isHtml());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setError(__bean.getError());
	}

	public long getCorreuAgrupatID() {
		return(correuAgrupatID);
	};
	public void setCorreuAgrupatID(long _correuAgrupatID_) {
		this.correuAgrupatID = _correuAgrupatID_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.String getSubject() {
		return(subject);
	};
	public void setSubject(java.lang.String _subject_) {
		this.subject = _subject_;
	};

	public java.lang.String getMessage() {
		return(message);
	};
	public void setMessage(java.lang.String _message_) {
		this.message = _message_;
	};

	public boolean isHtml() {
		return(html);
	};
	public void setHtml(boolean _html_) {
		this.html = _html_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.String getError() {
		return(error);
	};
	public void setError(java.lang.String _error_) {
		this.error = _error_;
	};



  // ======================================

  public static CorreuAgrupatBean toBean(CorreuAgrupat __bean) {
    if (__bean == null) { return null;}
    CorreuAgrupatBean __tmp = new CorreuAgrupatBean();
    __tmp.setCorreuAgrupatID(__bean.getCorreuAgrupatID());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setSubject(__bean.getSubject());
    __tmp.setMessage(__bean.getMessage());
    __tmp.setHtml(__bean.isHtml());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setError(__bean.getError());
		return __tmp;
	}



}
