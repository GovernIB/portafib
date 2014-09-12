package es.caib.portafib.model.entity;

public interface Metadada extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getMetadadaID();
	public void setMetadadaID(long _metadadaID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getValor();
	public void setValor(java.lang.String _valor_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public long getPeticioDeFirmaID();
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_);

	public int getTipusMetadadaID();
	public void setTipusMetadadaID(int _tipusMetadadaID_);



  // ======================================

}
