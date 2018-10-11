package es.caib.portafib.model.entity;

public interface Estadistica extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEstadisticaID();
	public void setEstadisticaID(long _estadisticaID_);

	public java.sql.Timestamp getData();
	public void setData(java.sql.Timestamp _data_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.Long getSubtipus();
	public void setSubtipus(java.lang.Long _subtipus_);

	public java.lang.String getEntitatID();
	public void setEntitatID(java.lang.String _entitatID_);

	public java.lang.Double getValor();
	public void setValor(java.lang.Double _valor_);

	public java.lang.String getParametres();
	public void setParametres(java.lang.String _parametres_);



  // ======================================

}
