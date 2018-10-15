package es.caib.portafib.model.entity;

public interface PluginCridada extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginCridadaID();
	public void setPluginCridadaID(long _pluginCridadaID_);

	public java.lang.String getEntitatID();
	public void setEntitatID(java.lang.String _entitatID_);

	public java.sql.Timestamp getData();
	public void setData(java.sql.Timestamp _data_);

	public long getPluginID();
	public void setPluginID(long _pluginID_);

	public java.lang.String getMetodePlugin();
	public void setMetodePlugin(java.lang.String _metodePlugin_);

	public java.lang.String getParametresText();
	public void setParametresText(java.lang.String _parametresText_);

	public java.lang.Long getParametresFitxerID();
	public void setParametresFitxerID(java.lang.Long _parametresFitxerID_);

	public java.lang.String getRetornText();
	public void setRetornText(java.lang.String _retornText_);

	public java.lang.Long getRetornFitxerID();
	public void setRetornFitxerID(java.lang.Long _retornFitxerID_);

	public int getTipusTesultat();
	public void setTipusTesultat(int _tipusTesultat_);

	public long getTempsExecucio();
	public void setTempsExecucio(long _tempsExecucio_);

  // Fitxer
  public <F extends Fitxer> F getParametresFitxer();
  // Fitxer
  public <F extends Fitxer> F getRetornFitxer();


  // ======================================

}
