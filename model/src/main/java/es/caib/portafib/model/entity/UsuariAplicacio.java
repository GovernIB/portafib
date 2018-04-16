package es.caib.portafib.model.entity;

public interface UsuariAplicacio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public java.lang.String getUsuariAplicacioID();
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_);

	public java.lang.String getContrasenya();
	public void setContrasenya(java.lang.String _contrasenya_);

	public java.lang.String getEntitatID();
	public void setEntitatID(java.lang.String _entitatID_);

	public java.lang.String getEmailAdmin();
	public void setEmailAdmin(java.lang.String _emailAdmin_);

	public int getCallbackVersio();
	public void setCallbackVersio(int _callbackVersio_);

	public java.lang.String getCallbackURL();
	public void setCallbackURL(java.lang.String _callbackURL_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public java.lang.String getIdiomaID();
	public void setIdiomaID(java.lang.String _idiomaID_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.lang.Long getLogoSegellID();
	public void setLogoSegellID(java.lang.Long _logoSegellID_);

	public java.lang.Boolean getPotCustodiar();
	public void setPotCustodiar(java.lang.Boolean _potCustodiar_);

	public java.lang.Integer getPoliticaCustodia();
	public void setPoliticaCustodia(java.lang.Integer _politicaCustodia_);

	public int getPoliticaDePluginFirmaWeb();
	public void setPoliticaDePluginFirmaWeb(int _politicaDePluginFirmaWeb_);

  // Fitxer
  public <F extends Fitxer> F getLogoSegell();


  // ======================================

}
