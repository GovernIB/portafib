package es.caib.portafib.model.entity;

public interface Entitat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public java.lang.String getEntitatID();
	public void setEntitatID(java.lang.String _entitatID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public boolean isActiva();
	public void setActiva(boolean _activa_);

	public java.lang.String getWeb();
	public void setWeb(java.lang.String _web_);

	public java.lang.Long getFaviconID();
	public void setFaviconID(java.lang.Long _faviconID_);

	public java.lang.Long getLogoWebID();
	public void setLogoWebID(java.lang.Long _logoWebID_);

	public java.lang.Long getLogoWebPeuID();
	public void setLogoWebPeuID(java.lang.Long _logoWebPeuID_);

	public java.lang.Long getLogoSegellID();
	public void setLogoSegellID(java.lang.Long _logoSegellID_);

	public java.lang.String getAdrezaHtml();
	public void setAdrezaHtml(java.lang.String _adrezaHtml_);

	public java.lang.String getFiltreCertificats();
	public void setFiltreCertificats(java.lang.String _filtreCertificats_);

	public java.lang.Long getPdfAutoritzacioDelegacioID();
	public void setPdfAutoritzacioDelegacioID(java.lang.Long _pdfAutoritzacioDelegacioID_);

	public java.lang.String getSuportTelefon();
	public void setSuportTelefon(java.lang.String _suportTelefon_);

	public java.lang.String getSuportWeb();
	public void setSuportWeb(java.lang.String _suportWeb_);

	public java.lang.String getSuportEmail();
	public void setSuportEmail(java.lang.String _suportEmail_);

	public java.lang.String getUsuariAplicacioID();
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_);

	public java.lang.Long getMaxUploadSize();
	public void setMaxUploadSize(java.lang.Long _maxUploadSize_);

	public java.lang.Long getMaxSizeFitxerAdaptat();
	public void setMaxSizeFitxerAdaptat(java.lang.Long _maxSizeFitxerAdaptat_);

	public java.lang.Integer getMaxFilesToSignAtSameTime();
	public void setMaxFilesToSignAtSameTime(java.lang.Integer _maxFilesToSignAtSameTime_);

	public java.lang.String getPolicyIdentifier();
	public void setPolicyIdentifier(java.lang.String _policyIdentifier_);

	public java.lang.String getPolicyIdentifierHash();
	public void setPolicyIdentifierHash(java.lang.String _policyIdentifierHash_);

	public java.lang.String getPolicyIdentifierHashAlgorithm();
	public void setPolicyIdentifierHashAlgorithm(java.lang.String _policyIdentifierHashAlgorithm_);

	public java.lang.String getPolicyUrlDocument();
	public void setPolicyUrlDocument(java.lang.String _policyUrlDocument_);

	public java.lang.Long getMotiuDelegacioID();
	public void setMotiuDelegacioID(java.lang.Long _motiuDelegacioID_);

	public java.lang.Long getFirmatPerFormatID();
	public void setFirmatPerFormatID(java.lang.Long _firmatPerFormatID_);

	public int getAlgorismeDeFirmaID();
	public void setAlgorismeDeFirmaID(int _algorismeDeFirmaID_);

	public boolean isComprovarCertificatClientCert();
	public void setComprovarCertificatClientCert(boolean _comprovarCertificatClientCert_);

	public boolean isComprovarNifFirma();
	public void setComprovarNifFirma(boolean _comprovarNifFirma_);

	public java.lang.Long getCustodiaInfoID();
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_);

	public java.lang.Long getPluginID();
	public void setPluginID(java.lang.Long _pluginID_);

	public int getSegellDeTempsViaWeb();
	public void setSegellDeTempsViaWeb(int _segellDeTempsViaWeb_);

  // Fitxer
  public <F extends Fitxer> F getFavicon();
  // Fitxer
  public <F extends Fitxer> F getLogoWeb();
  // Fitxer
  public <F extends Fitxer> F getLogoWebPeu();
  // Fitxer
  public <F extends Fitxer> F getLogoSegell();
  // Fitxer
  public <F extends Fitxer> F getPdfAutoritzacioDelegacio();


  // ======================================

}
