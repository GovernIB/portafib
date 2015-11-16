package es.caib.portafib.model.entity;

public interface Plugin extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginID();
	public void setPluginID(long _pluginID_);

	public long getNomID();
	public void setNomID(long _nomID_);

	public long getDescripcioCurtaID();
	public void setDescripcioCurtaID(long _descripcioCurtaID_);

	public java.lang.String getClasse();
	public void setClasse(java.lang.String _classe_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.String getPropertiesAdmin();
	public void setPropertiesAdmin(java.lang.String _propertiesAdmin_);

	public java.lang.String getPropertiesEntitat();
	public void setPropertiesEntitat(java.lang.String _propertiesEntitat_);

	public java.lang.String getEntitatID();
	public void setEntitatID(java.lang.String _entitatID_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);



  // ======================================

}
