package es.caib.portafib.model.entity;

public interface PluginCridada extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginCridadaID();
	public void setPluginCridadaID(long _pluginCridadaID_);

	public java.lang.String getEntitatID();
	public void setEntitatID(java.lang.String _entitatID_);

	public java.sql.Timestamp getData();
	public void setData(java.sql.Timestamp _data_);

	public int getTipusPlugin();
	public void setTipusPlugin(int _tipusPlugin_);

	public java.lang.String getDadesPlugin();
	public void setDadesPlugin(java.lang.String _dadesPlugin_);

	public java.lang.String getMetodePlugin();
	public void setMetodePlugin(java.lang.String _metodePlugin_);

	public java.lang.String getDadesCridada();
	public void setDadesCridada(java.lang.String _dadesCridada_);

	public int getTipusTesultat();
	public void setTipusTesultat(int _tipusTesultat_);

	public java.lang.String getResultat();
	public void setResultat(java.lang.String _resultat_);

	public long getTempsExecucio();
	public void setTempsExecucio(long _tempsExecucio_);



  // ======================================

}
