package es.caib.portafib.model.entity;

public interface EstatDeFirma extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEstatDeFirmaID();
	public void setEstatDeFirmaID(long _estatDeFirmaID_);

	public long getFirmaID();
	public void setFirmaID(long _firmaID_);

	public java.lang.String getUsuariEntitatID();
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.sql.Timestamp getDataFi();
	public void setDataFi(java.sql.Timestamp _dataFi_);

	public long getTipusEstatDeFirmaInicialID();
	public void setTipusEstatDeFirmaInicialID(long _tipusEstatDeFirmaInicialID_);

	public java.lang.Long getTipusEstatDeFirmaFinalID();
	public void setTipusEstatDeFirmaFinalID(java.lang.Long _tipusEstatDeFirmaFinalID_);

	public java.lang.Long getColaboracioDelegacioID();
	public void setColaboracioDelegacioID(java.lang.Long _colaboracioDelegacioID_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);



  // ======================================

}
