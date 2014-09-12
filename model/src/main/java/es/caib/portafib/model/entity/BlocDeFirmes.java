package es.caib.portafib.model.entity;

public interface BlocDeFirmes extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getBlocDeFirmesID();
	public void setBlocDeFirmesID(long _blocDeFirmesID_);

	public int getOrdre();
	public void setOrdre(int _ordre_);

	public java.sql.Timestamp getDataFinalitzacio();
	public void setDataFinalitzacio(java.sql.Timestamp _dataFinalitzacio_);

	public long getFluxDeFirmesID();
	public void setFluxDeFirmesID(long _fluxDeFirmesID_);

	public int getMinimDeFirmes();
	public void setMinimDeFirmes(int _minimDeFirmes_);



  // ======================================

}
