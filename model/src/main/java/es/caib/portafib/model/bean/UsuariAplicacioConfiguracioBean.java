
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.UsuariAplicacioConfiguracio;


public class UsuariAplicacioConfiguracioBean implements UsuariAplicacioConfiguracio {



private static final long serialVersionUID = 333534165L;

	long usuariAplicacioConfigID;// PK
	java.lang.String usuariAplicacioID;
	int usPoliticaDeTirma;
	java.lang.String policyIdentifier;
	java.lang.String policyIdentifierHash;
	java.lang.String policyIdentifierHashAlgorithm;
	java.lang.String policyUrlDocument;
	java.lang.String filtreCertificats;
	int tipusOperacioFirma;
	int tipusFirmaID;
	java.lang.Integer algorismeDeFirmaID;
	boolean modeDeFirma;
	java.lang.Long motiuDelegacioID;
	java.lang.Long firmatPerFormatID;
	java.lang.Long custodiaInfoID;
	int posicioTaulaFirmesID;
	java.lang.Long pluginSegellatID;
	java.lang.Long pluginFirmaServidorID;
	java.lang.String htmlPerLlistarPluginsFirmaWeb;
	java.lang.Long loginCertificateID;
	boolean comprovarNifFirma;
	boolean checkCanviatDocFirmat;
	boolean validarFirma;
	boolean validarCertificat;


  /** Constructor Buit */
  public UsuariAplicacioConfiguracioBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioConfiguracioBean(long usuariAplicacioConfigID , java.lang.String usuariAplicacioID , int usPoliticaDeTirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , java.lang.Long motiuDelegacioID , java.lang.Long firmatPerFormatID , java.lang.Long custodiaInfoID , int posicioTaulaFirmesID , java.lang.Long pluginSegellatID , java.lang.Long pluginFirmaServidorID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long loginCertificateID , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , boolean validarCertificat) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeTirma=usPoliticaDeTirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.motiuDelegacioID=motiuDelegacioID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.custodiaInfoID=custodiaInfoID;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.pluginSegellatID=pluginSegellatID;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.loginCertificateID=loginCertificateID;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.validarCertificat=validarCertificat;
}
  /** Constructor sense valors autoincrementals */
  public UsuariAplicacioConfiguracioBean(java.lang.String usuariAplicacioID , int usPoliticaDeTirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.String filtreCertificats , int tipusOperacioFirma , int tipusFirmaID , java.lang.Integer algorismeDeFirmaID , boolean modeDeFirma , java.lang.Long motiuDelegacioID , java.lang.Long firmatPerFormatID , java.lang.Long custodiaInfoID , int posicioTaulaFirmesID , java.lang.Long pluginSegellatID , java.lang.Long pluginFirmaServidorID , java.lang.String htmlPerLlistarPluginsFirmaWeb , java.lang.Long loginCertificateID , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , boolean validarFirma , boolean validarCertificat) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeTirma=usPoliticaDeTirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.filtreCertificats=filtreCertificats;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.motiuDelegacioID=motiuDelegacioID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.custodiaInfoID=custodiaInfoID;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.pluginSegellatID=pluginSegellatID;
    this.pluginFirmaServidorID=pluginFirmaServidorID;
    this.htmlPerLlistarPluginsFirmaWeb=htmlPerLlistarPluginsFirmaWeb;
    this.loginCertificateID=loginCertificateID;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.validarFirma=validarFirma;
    this.validarCertificat=validarCertificat;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioConfiguracioBean(long usuariAplicacioConfigID , java.lang.String usuariAplicacioID , int usPoliticaDeTirma , int tipusOperacioFirma , int tipusFirmaID , boolean modeDeFirma) {
    this.usuariAplicacioConfigID=usuariAplicacioConfigID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.usPoliticaDeTirma=usPoliticaDeTirma;
    this.tipusOperacioFirma=tipusOperacioFirma;
    this.tipusFirmaID=tipusFirmaID;
    this.modeDeFirma=modeDeFirma;
}
  public UsuariAplicacioConfiguracioBean(UsuariAplicacioConfiguracio __bean) {
    this.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setUsPoliticaDeTirma(__bean.getUsPoliticaDeTirma());
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    this.setFiltreCertificats(__bean.getFiltreCertificats());
    this.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setModeDeFirma(__bean.isModeDeFirma());
    this.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    this.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setPluginSegellatID(__bean.getPluginSegellatID());
    this.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    this.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    this.setLoginCertificateID(__bean.getLoginCertificateID());
    this.setComprovarNifFirma(__bean.isComprovarNifFirma());
    this.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    this.setValidarFirma(__bean.isValidarFirma());
    this.setValidarCertificat(__bean.isValidarCertificat());
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

	public int getUsPoliticaDeTirma() {
		return(usPoliticaDeTirma);
	};
	public void setUsPoliticaDeTirma(int _usPoliticaDeTirma_) {
		this.usPoliticaDeTirma = _usPoliticaDeTirma_;
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

	public java.lang.Long getMotiuDelegacioID() {
		return(motiuDelegacioID);
	};
	public void setMotiuDelegacioID(java.lang.Long _motiuDelegacioID_) {
		this.motiuDelegacioID = _motiuDelegacioID_;
	};

	public java.lang.Long getFirmatPerFormatID() {
		return(firmatPerFormatID);
	};
	public void setFirmatPerFormatID(java.lang.Long _firmatPerFormatID_) {
		this.firmatPerFormatID = _firmatPerFormatID_;
	};

	public java.lang.Long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
	};

	public java.lang.Long getPluginSegellatID() {
		return(pluginSegellatID);
	};
	public void setPluginSegellatID(java.lang.Long _pluginSegellatID_) {
		this.pluginSegellatID = _pluginSegellatID_;
	};

	public java.lang.Long getPluginFirmaServidorID() {
		return(pluginFirmaServidorID);
	};
	public void setPluginFirmaServidorID(java.lang.Long _pluginFirmaServidorID_) {
		this.pluginFirmaServidorID = _pluginFirmaServidorID_;
	};

	public java.lang.String getHtmlPerLlistarPluginsFirmaWeb() {
		return(htmlPerLlistarPluginsFirmaWeb);
	};
	public void setHtmlPerLlistarPluginsFirmaWeb(java.lang.String _htmlPerLlistarPluginsFirmaWeb_) {
		this.htmlPerLlistarPluginsFirmaWeb = _htmlPerLlistarPluginsFirmaWeb_;
	};

	public java.lang.Long getLoginCertificateID() {
		return(loginCertificateID);
	};
	public void setLoginCertificateID(java.lang.Long _loginCertificateID_) {
		this.loginCertificateID = _loginCertificateID_;
	};

	public boolean isComprovarNifFirma() {
		return(comprovarNifFirma);
	};
	public void setComprovarNifFirma(boolean _comprovarNifFirma_) {
		this.comprovarNifFirma = _comprovarNifFirma_;
	};

	public boolean isCheckCanviatDocFirmat() {
		return(checkCanviatDocFirmat);
	};
	public void setCheckCanviatDocFirmat(boolean _checkCanviatDocFirmat_) {
		this.checkCanviatDocFirmat = _checkCanviatDocFirmat_;
	};

	public boolean isValidarFirma() {
		return(validarFirma);
	};
	public void setValidarFirma(boolean _validarFirma_) {
		this.validarFirma = _validarFirma_;
	};

	public boolean isValidarCertificat() {
		return(validarCertificat);
	};
	public void setValidarCertificat(boolean _validarCertificat_) {
		this.validarCertificat = _validarCertificat_;
	};



  // ======================================

  public static UsuariAplicacioConfiguracioBean toBean(UsuariAplicacioConfiguracio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioConfiguracioBean __tmp = new UsuariAplicacioConfiguracioBean();
    __tmp.setUsuariAplicacioConfigID(__bean.getUsuariAplicacioConfigID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setUsPoliticaDeTirma(__bean.getUsPoliticaDeTirma());
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    __tmp.setFiltreCertificats(__bean.getFiltreCertificats());
    __tmp.setTipusOperacioFirma(__bean.getTipusOperacioFirma());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.isModeDeFirma());
    __tmp.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    __tmp.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setPluginSegellatID(__bean.getPluginSegellatID());
    __tmp.setPluginFirmaServidorID(__bean.getPluginFirmaServidorID());
    __tmp.setHtmlPerLlistarPluginsFirmaWeb(__bean.getHtmlPerLlistarPluginsFirmaWeb());
    __tmp.setLoginCertificateID(__bean.getLoginCertificateID());
    __tmp.setComprovarNifFirma(__bean.isComprovarNifFirma());
    __tmp.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    __tmp.setValidarFirma(__bean.isValidarFirma());
    __tmp.setValidarCertificat(__bean.isValidarCertificat());
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
