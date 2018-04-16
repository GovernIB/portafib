package es.caib.portafib.model.entity;

public interface UsuariAplicacioConfiguracio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getUsuariAplicacioConfigID();
	public void setUsuariAplicacioConfigID(long _usuariAplicacioConfigID_);

	public java.lang.String getUsuariAplicacioID();
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_);

	public int getUsPoliticaDeTirma();
	public void setUsPoliticaDeTirma(int _usPoliticaDeTirma_);

	public java.lang.String getPolicyIdentifier();
	public void setPolicyIdentifier(java.lang.String _policyIdentifier_);

	public java.lang.String getPolicyIdentifierHash();
	public void setPolicyIdentifierHash(java.lang.String _policyIdentifierHash_);

	public java.lang.String getPolicyIdentifierHashAlgorithm();
	public void setPolicyIdentifierHashAlgorithm(java.lang.String _policyIdentifierHashAlgorithm_);

	public java.lang.String getPolicyUrlDocument();
	public void setPolicyUrlDocument(java.lang.String _policyUrlDocument_);

	public java.lang.String getFiltreCertificats();
	public void setFiltreCertificats(java.lang.String _filtreCertificats_);

	public int getTipusOperacioFirma();
	public void setTipusOperacioFirma(int _tipusOperacioFirma_);

	public int getTipusFirmaID();
	public void setTipusFirmaID(int _tipusFirmaID_);

	public java.lang.Integer getAlgorismeDeFirmaID();
	public void setAlgorismeDeFirmaID(java.lang.Integer _algorismeDeFirmaID_);

	public boolean isModeDeFirma();
	public void setModeDeFirma(boolean _modeDeFirma_);

	public java.lang.Long getMotiuDelegacioID();
	public void setMotiuDelegacioID(java.lang.Long _motiuDelegacioID_);

	public java.lang.Long getFirmatPerFormatID();
	public void setFirmatPerFormatID(java.lang.Long _firmatPerFormatID_);

	public java.lang.Long getCustodiaInfoID();
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_);

	public int getPosicioTaulaFirmesID();
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_);

	public java.lang.Long getPluginSegellatID();
	public void setPluginSegellatID(java.lang.Long _pluginSegellatID_);

	public java.lang.Long getPluginFirmaServidorID();
	public void setPluginFirmaServidorID(java.lang.Long _pluginFirmaServidorID_);

	public java.lang.String getHtmlPerLlistarPluginsFirmaWeb();
	public void setHtmlPerLlistarPluginsFirmaWeb(java.lang.String _htmlPerLlistarPluginsFirmaWeb_);

	public java.lang.Long getLoginCertificateID();
	public void setLoginCertificateID(java.lang.Long _loginCertificateID_);

	public boolean isComprovarNifFirma();
	public void setComprovarNifFirma(boolean _comprovarNifFirma_);

	public boolean isCheckCanviatDocFirmat();
	public void setCheckCanviatDocFirmat(boolean _checkCanviatDocFirmat_);

	public boolean isValidarFirma();
	public void setValidarFirma(boolean _validarFirma_);

	public boolean isValidarCertificat();
	public void setValidarCertificat(boolean _validarCertificat_);

  // Fitxer
  public <F extends Fitxer> F getLoginCertificate();


  // ======================================

}
