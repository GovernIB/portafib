package es.caib.portafib.model.entity;

public interface NotificacioWS extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getNotificacioID();
	public void setNotificacioID(long _notificacioID_);

	public long getPeticioDeFirmaID();
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_);

	public long getTipusNotificacioID();
	public void setTipusNotificacioID(long _tipusNotificacioID_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.sql.Timestamp getDataEnviament();
	public void setDataEnviament(java.sql.Timestamp _dataEnviament_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public boolean isBloquejada();
	public void setBloquejada(boolean _bloquejada_);

	public java.lang.String getError();
	public void setError(java.lang.String _error_);

	public java.sql.Timestamp getDataError();
	public void setDataError(java.sql.Timestamp _dataError_);

	public int getReintents();
	public void setReintents(int _reintents_);



  // ======================================

}
