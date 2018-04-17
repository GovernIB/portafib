package es.caib.portafib.model.entity;

public interface UsuariEntitat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public java.lang.String getUsuariEntitatID();
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_);

	public java.lang.String getCarrec();
	public void setCarrec(java.lang.String _carrec_);

	public java.lang.String getUsuariPersonaID();
	public void setUsuariPersonaID(java.lang.String _usuariPersonaID_);

	public java.lang.String getEntitatID();
	public void setEntitatID(java.lang.String _entitatID_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public java.lang.String getEmail();
	public void setEmail(java.lang.String _email_);

	public java.lang.Long getLogoSegellID();
	public void setLogoSegellID(java.lang.Long _logoSegellID_);

	public boolean isPredeterminat();
	public void setPredeterminat(boolean _predeterminat_);

	public boolean isRebreTotsElsAvisos();
	public void setRebreTotsElsAvisos(boolean _rebreTotsElsAvisos_);

	public java.lang.Boolean getPotCustodiar();
	public void setPotCustodiar(java.lang.Boolean _potCustodiar_);

	public int getPoliticaCustodia();
	public void setPoliticaCustodia(int _politicaCustodia_);

	public int getPoliticaDePluginFirmaWeb();
	public void setPoliticaDePluginFirmaWeb(int _politicaDePluginFirmaWeb_);

  // Fitxer
  public <F extends Fitxer> F getLogoSegell();


  // ======================================

}
