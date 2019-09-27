package es.caib.portafib.model.entity;

public interface Bitacola extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getBitacolaID();
	public void setBitacolaID(long _bitacolaID_);

	public java.lang.String getEntitatid();
	public void setEntitatid(java.lang.String _entitatid_);

	public java.lang.String getUsuariid();
	public void setUsuariid(java.lang.String _usuariid_);

	public java.sql.Timestamp getData();
	public void setData(java.sql.Timestamp _data_);

	public int getTipusObjecte();
	public void setTipusObjecte(int _tipusObjecte_);

	public java.lang.String getObjecteid();
	public void setObjecteid(java.lang.String _objecteid_);

	public int getTipusOperacio();
	public void setTipusOperacio(int _tipusOperacio_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.lang.String getObjecteSerialitzat();
	public void setObjecteSerialitzat(java.lang.String _objecteSerialitzat_);



  // ======================================

}
