
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;


public class UsuariAplicacioConfiguracioBean implements UsuariAplicacioConfiguracio {



private static final long serialVersionUID = 333534165L;

	long usuariAplicacioConfigID;// PK
	java.lang.String usuariAplicacioID;
	int usPoliticaDeFirma;
	java.lang.String policyIdentifier;
	java.lang.String policyIdentifierHash;
	java.lang.String policyIdentifierHashAlgorithm;
	java.lang.String policyUrlDocument;
	java.lang.String filtreCertificats;
	int tipusOperacioFirma;
	int tipusFirmaID;
	java.lang.Integer algorismeDeFirmaID;
	boolean modeDeFirma;
	int politicaCustodia;
	java.lang.Long custodiaInfoID;
	int politicaTaulaFirmes;
	int posicioTaulaFirmesID;
	java.lang.Long firmatPerFormatID;
	java.lang.Long motiuDelegacioID;
	java.lang.String propietatsTaulaFirmes;
	int politicaSegellatDeTemps;
	java.lang.Long pluginSegellatID;
	java.lang.String htmlPerLlistarPluginsFirmaWeb;
	java.lang.Long pluginFirmaServidorID;
	java.lang.Integer maxFirmesEnServidor;
	java.lang.Integer upgradeSignFormat;
	java.lang.Long loginCertificateID;
	java.lang.Boolean comprovarNifFirma;
	java.lang.Boolean checkCanviatDocFirmat;
	java.lang.Boolean validarFirma;
	java.lang.Boolean validarCertificat;


  /** Constructor Buit */
  public UsuariAplicacioConfiguracioBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioConfiguracioBean(long usuariAplicacioConfigID , java.lang.String usuariAplicacioID , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , int politicaCustodia , java.lang.Long custodiaInfoID , int politicaTaulaFirmes , int posicioTaulaFirmesID , java.lang.Long firmatPerFormatID , java.lang.Long motiuDelegacioID , java.lang.String propietatsTaulaFirmes , int politicaSegellatDeTemps , java.lang.Long pluginSegellatID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long pluginFirmaServidorID , java.lang.Integer maxFirmesEnServidor , java.lang.Integer upgradeSignFormat , java.lang.Long loginCertificateID , java.lang.Boolean comprovarNifFirma , java.lang.Boolean checkCanviatDocFirmat , java.lang.Boolean validarFirma , java.lang.Boolean validarCertificat) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.motiuDelegacioID=motiuDelegacioID;
    this.propietatsTaulaFirmes=propietatsTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.pluginSegellatID=pluginSegellatID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.maxFirmesEnServidor=maxFirmesEnServidor;
    this.upgradeSignFormat=upgradeSignFormat;
    this.loginCertificateID=loginCertificateID;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.validarCertificat=validarCertificat;
}
  /** Constructor sense valors autoincrementals */
  public UsuariAplicacioConfiguracioBean(java.lang.String usuariAplicacioID , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , int politicaCustodia , java.lang.Long custodiaInfoID , int politicaTaulaFirmes , int posicioTaulaFirmesID , java.lang.Long firmatPerFormatID , java.lang.Long motiuDelegacioID , java.lang.String propietatsTaulaFirmes , int politicaSegellatDeTemps , java.lang.Long pluginSegellatID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long pluginFirmaServidorID , java.lang.Integer maxFirmesEnServidor , java.lang.Integer upgradeSignFormat , java.lang.Long loginCertificateID , java.lang.Boolean comprovarNifFirma , java.lang.Boolean checkCanviatDocFirmat , java.lang.Boolean validarFirma , java.lang.Boolean validarCertificat) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.motiuDelegacioID=motiuDelegacioID;
    this.propietatsTaulaFirmes=propietatsTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.pluginSegellatID=pluginSegellatID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.maxFirmesEnServidor=maxFirmesEnServidor;
    this.upgradeSignFormat=upgradeSignFormat;
    this.loginCertificateID=loginCertificateID;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.validarCertificat=validarCertificat;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioConfiguracioBean(long usuariAplicacioConfigID , java.lang.String usuariAplicacioID , int usPoliticaDeFirma , int tipusOperacioFirma , int tipusFirmaID , boolean modeDeFirma , int politicaCustodia , int politicaTaulaFirmes , int posicioTaulaFirmesID , int politicaSegellatDeTemps) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.politicaCustodia=politicaCustodia;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
}
  public UsuariAplicacioConfiguracioBean(UsuariAplicacioConfiguracio __bean) {
    this.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    this.setFiltreCertificats(__bean.getFiltreCertificats());
    this.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setModeDeFirma(__bean.isModeDeFirma());
    this.setPoliticaCustodia(__bean.getPoliticaCustodia());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    this.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    this.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    this.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    this.setPluginSegellatID(__bean.getPluginSegellatID());
    this.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    this.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    this.setMaxFirmesEnServidor(__bean.getMaxFirmesEnServidor());
    this.setUpgradeSignFormat(__bean.getUpgradeSignFormat());
    this.setLoginCertificateID(__bean.getLoginCertificateID());
    this.setComprovarNifFirma(__bean.getComprovarNifFirma());
    this.setCheckCanviatDocFirmat(__bean.getCheckCanviatDocFirmat());
    this.setValidarFirma(__bean.getValidarFirma());
    this.setValidarCertificat(__bean.getValidarCertificat());
    // Fitxer
    this.setLoginCertificate(FitxerBean.toBean(__bean.getLoginCertificate()));
	}

	public long getUsuariAplicacioConfigID() {
		return(usuariAplicacioConfigID);
	};
	public void setUsuariAplicacioConfigID(long _usuariAplicacioConfigID_) {
		this.usuariAplicacioConfigID = _usuariAplicacioConfigID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public int getUsPoliticaDeFirma() {
		return(usPoliticaDeFirma);
	};
	public void setUsPoliticaDeFirma(int _usPoliticaDeFirma_) {
		this.usPoliticaDeFirma = _usPoliticaDeFirma_;
	};

	public java.lang.String getPolicyIdentifier() {
		return(policyIdentifier);
	};
	public void setPolicyIdentifier(java.lang.String _policyIdentifier_) {
		this.policyIdentifier = _policyIdentifier_;
	};

	public java.lang.String getPolicyIdentifierHash() {
		return(policyIdentifierHash);
	};
	public void setPolicyIdentifierHash(java.lang.String _policyIdentifierHash_) {
		this.policyIdentifierHash = _policyIdentifierHash_;
	};

	public java.lang.String getPolicyIdentifierHashAlgorithm() {
		return(policyIdentifierHashAlgorithm);
	};
	public void setPolicyIdentifierHashAlgorithm(java.lang.String _policyIdentifierHashAlgorithm_) {
		this.policyIdentifierHashAlgorithm = _policyIdentifierHashAlgorithm_;
	};

	public java.lang.String getPolicyUrlDocument() {
		return(policyUrlDocument);
	};
	public void setPolicyUrlDocument(java.lang.String _policyUrlDocument_) {
		this.policyUrlDocument = _policyUrlDocument_;
	};

	public java.lang.String getFiltreCertificats() {
		return(filtreCertificats);
	};
	public void setFiltreCertificats(java.lang.String _filtreCertificats_) {
		this.filtreCertificats = _filtreCertificats_;
	};

	public int getTipusOperacioFirma() {
		return(tipusOperacioFirma);
	};
	public void setTipusOperacioFirma(int _tipusOperacioFirma_) {
		this.tipusOperacioFirma = _tipusOperacioFirma_;
	};

	public int getTipusFirmaID() {
		return(tipusFirmaID);
	};
	public void setTipusFirmaID(int _tipusFirmaID_) {
		this.tipusFirmaID = _tipusFirmaID_;
	};

	public java.lang.Integer getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(java.lang.Integer _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
	};

	public boolean isModeDeFirma() {
		return(modeDeFirma);
	};
	public void setModeDeFirma(boolean _modeDeFirma_) {
		this.modeDeFirma = _modeDeFirma_;
	};

	public int getPoliticaCustodia() {
		return(politicaCustodia);
	};
	public void setPoliticaCustodia(int _politicaCustodia_) {
		this.politicaCustodia = _politicaCustodia_;
	};

	public java.lang.Long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public int getPoliticaTaulaFirmes() {
		return(politicaTaulaFirmes);
	};
	public void setPoliticaTaulaFirmes(int _politicaTaulaFirmes_) {
		this.politicaTaulaFirmes = _politicaTaulaFirmes_;
	};

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
	};

	public java.lang.Long getFirmatPerFormatID() {
		return(firmatPerFormatID);
	};
	public void setFirmatPerFormatID(java.lang.Long _firmatPerFormatID_) {
		this.firmatPerFormatID = _firmatPerFormatID_;
	};

	public java.lang.Long getMotiuDelegacioID() {
		return(motiuDelegacioID);
	};
	public void setMotiuDelegacioID(java.lang.Long _motiuDelegacioID_) {
		this.motiuDelegacioID = _motiuDelegacioID_;
	};

	public java.lang.String getPropietatsTaulaFirmes() {
		return(propietatsTaulaFirmes);
	};
	public void setPropietatsTaulaFirmes(java.lang.String _propietatsTaulaFirmes_) {
		this.propietatsTaulaFirmes = _propietatsTaulaFirmes_;
	};

	public int getPoliticaSegellatDeTemps() {
		return(politicaSegellatDeTemps);
	};
	public void setPoliticaSegellatDeTemps(int _politicaSegellatDeTemps_) {
		this.politicaSegellatDeTemps = _politicaSegellatDeTemps_;
	};

	public java.lang.Long getPluginSegellatID() {
		return(pluginSegellatID);
	};
	public void setPluginSegellatID(java.lang.Long _pluginSegellatID_) {
		this.pluginSegellatID = _pluginSegellatID_;
	};

	public java.lang.String getHtmlPerLlistarPluginsFirmaWeb() {
		return(htmlPerLlistarPluginsFirmaWeb);
	};
	public void setHtmlPerLlistarPluginsFirmaWeb(java.lang.String _htmlPerLlistarPluginsFirmaWeb_) {
		this.htmlPerLlistarPluginsFirmaWeb = _htmlPerLlistarPluginsFirmaWeb_;
	};

	public java.lang.Long getPluginFirmaServidorID() {
		return(pluginFirmaServidorID);
	};
	public void setPluginFirmaServidorID(java.lang.Long _pluginFirmaServidorID_) {
		this.pluginFirmaServidorID = _pluginFirmaServidorID_;
	};

	public java.lang.Integer getMaxFirmesEnServidor() {
		return(maxFirmesEnServidor);
	};
	public void setMaxFirmesEnServidor(java.lang.Integer _maxFirmesEnServidor_) {
		this.maxFirmesEnServidor = _maxFirmesEnServidor_;
	};

	public java.lang.Integer getUpgradeSignFormat() {
		return(upgradeSignFormat);
	};
	public void setUpgradeSignFormat(java.lang.Integer _upgradeSignFormat_) {
		this.upgradeSignFormat = _upgradeSignFormat_;
	};

	public java.lang.Long getLoginCertificateID() {
		return(loginCertificateID);
	};
	public void setLoginCertificateID(java.lang.Long _loginCertificateID_) {
		this.loginCertificateID = _loginCertificateID_;
	};

	public java.lang.Boolean getComprovarNifFirma() {
		return(comprovarNifFirma);
	};
	public void setComprovarNifFirma(java.lang.Boolean _comprovarNifFirma_) {
		this.comprovarNifFirma = _comprovarNifFirma_;
	};

	public java.lang.Boolean getCheckCanviatDocFirmat() {
		return(checkCanviatDocFirmat);
	};
	public void setCheckCanviatDocFirmat(java.lang.Boolean _checkCanviatDocFirmat_) {
		this.checkCanviatDocFirmat = _checkCanviatDocFirmat_;
	};

	public java.lang.Boolean getValidarFirma() {
		return(validarFirma);
	};
	public void setValidarFirma(java.lang.Boolean _validarFirma_) {
		this.validarFirma = _validarFirma_;
	};

	public java.lang.Boolean getValidarCertificat() {
		return(validarCertificat);
	};
	public void setValidarCertificat(java.lang.Boolean _validarCertificat_) {
		this.validarCertificat = _validarCertificat_;
	};



  // ======================================

  public static UsuariAplicacioConfiguracioBean toBean(UsuariAplicacioConfiguracio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioConfiguracioBean __tmp = new UsuariAplicacioConfiguracioBean();
    __tmp.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    __tmp.setFiltreCertificats(__bean.getFiltreCertificats());
    __tmp.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.isModeDeFirma());
    __tmp.setPoliticaCustodia(__bean.getPoliticaCustodia());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    __tmp.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    __tmp.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    __tmp.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    __tmp.setPluginSegellatID(__bean.getPluginSegellatID());
    __tmp.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    __tmp.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    __tmp.setMaxFirmesEnServidor(__bean.getMaxFirmesEnServidor());
    __tmp.setUpgradeSignFormat(__bean.getUpgradeSignFormat());
    __tmp.setLoginCertificateID(__bean.getLoginCertificateID());
    __tmp.setComprovarNifFirma(__bean.getComprovarNifFirma());
    __tmp.setCheckCanviatDocFirmat(__bean.getCheckCanviatDocFirmat());
    __tmp.setValidarFirma(__bean.getValidarFirma());
    __tmp.setValidarCertificat(__bean.getValidarCertificat());
    // Fitxer
    __tmp.setLoginCertificate(FitxerBean.toBean(__bean.getLoginCertificate()));
		return __tmp;
	}

  protected FitxerBean loginCertificate;
  public FitxerBean getLoginCertificate() {
    return loginCertificate;
  }
  public void setLoginCertificate(FitxerBean __field) {
    this. loginCertificate = __field;
  }


}
