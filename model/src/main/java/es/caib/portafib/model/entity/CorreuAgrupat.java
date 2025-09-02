package es.caib.portafib.model.entity;

public interface CorreuAgrupat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getCorreuAgrupatID();
	public void setCorreuAgrupatID(long _correuAgrupatID_);

	public java.lang.String getEmail();
	public void setEmail(java.lang.String _email_);

	public java.lang.String getSubject();
	public void setSubject(java.lang.String _subject_);

	public java.lang.String getMessage();
	public void setMessage(java.lang.String _message_);

	public boolean isHtml();
	public void setHtml(boolean _html_);

	public java.lang.String getUsuariEntitatID();
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.lang.String getError();
	public void setError(java.lang.String _error_);



  // ======================================

}
