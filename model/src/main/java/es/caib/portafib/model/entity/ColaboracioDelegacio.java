package es.caib.portafib.model.entity;

public interface ColaboracioDelegacio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getColaboracioDelegacioID();
	public void setColaboracioDelegacioID(long _colaboracioDelegacioID_);

	public java.lang.String getDestinatariID();
	public void setDestinatariID(java.lang.String _destinatariID_);

	public java.lang.String getColaboradorDelegatID();
	public void setColaboradorDelegatID(java.lang.String _colaboradorDelegatID_);

	public boolean isEsDelegat();
	public void setEsDelegat(boolean _esDelegat_);

	public java.lang.String getMotiu();
	public void setMotiu(java.lang.String _motiu_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.sql.Timestamp getDataFi();
	public void setDataFi(java.sql.Timestamp _dataFi_);

	public boolean isActiva();
	public void setActiva(boolean _activa_);

	public boolean isRevisor();
	public void setRevisor(boolean _revisor_);

	public java.lang.String getMotiuDeshabilitada();
	public void setMotiuDeshabilitada(java.lang.String _motiuDeshabilitada_);

	public java.lang.Long getFitxerAutoritzacioID();
	public void setFitxerAutoritzacioID(java.lang.Long _fitxerAutoritzacioID_);

  // Fitxer
  public <F extends Fitxer> F getFitxerAutoritzacio();


  // ======================================

}
