
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Entitat;


public class EntitatBean implements Entitat {



private static final long serialVersionUID = -2014602951L;

	java.lang.String entitatID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	boolean activa;
	java.lang.String web;
	java.lang.Long faviconID;
	java.lang.Long logoWebID;
	java.lang.Long logoWebPeuID;
	java.lang.Long logoSegellID;
	java.lang.String adrezaHtml;
	java.lang.String filtreCertificats;
	java.lang.Long pdfAutoritzacioDelegacioID;
	java.lang.String suportTelefon;
	java.lang.String suportWeb;
	java.lang.String suportEmail;
	java.lang.String usuariAplicacioID;
	java.lang.Long maxUploadSize;
	java.lang.Long maxSizeFitxerAdaptat;
	java.lang.Integer maxFilesToSignAtSameTime;
	int usPoliticaDeFirma;
	java.lang.String policyIdentifier;
	java.lang.String policyIdentifierHash;
	java.lang.String policyIdentifierHashAlgorithm;
	java.lang.String policyUrlDocument;
	java.lang.Long motiuDelegacioID;
	java.lang.Long firmatPerFormatID;
	int algorismeDeFirmaID;
	int politicaCustodia;
	java.lang.Long custodiaInfoID;
	int politicaTaulaFirmes;
	int posicioTaulaFirmes;
	java.lang.String propietatsTaulaFirmes;
	int politicaSegellatDeTemps;
	java.lang.Long pluginSegellTempsID;
	java.lang.Long pluginRubricaID;
	boolean validarfirma;
	boolean comprovarNifFirma;
	boolean checkCanviatDocFirmat;
	java.lang.Long pluginValidaFirmesID;
	java.lang.Long pluginValidaCertificatID;


  /** Constructor Buit */
  public EntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public EntitatBean(java.lang.String entitatID , java.lang.String nom , java.lang.String descripcio , boolean activa , java.lang.String web , java.lang.Long faviconID , java.lang.Long logoWebID , java.lang.Long logoWebPeuID , java.lang.Long logoSegellID , java.lang.String adrezaHtml , java.lang.String filtreCertificats , java.lang.Long pdfAutoritzacioDelegacioID , java.lang.String suportTelefon , java.lang.String suportWeb , java.lang.String suportEmail , java.lang.String usuariAplicacioID , java.lang.Long maxUploadSize , java.lang.Long maxSizeFitxerAdaptat , java.lang.Integer maxFilesToSignAtSameTime , int usPoliticaDeFirma , java.lang.String policyIdentifier , java.lang.String policyIdentifierHash , java.lang.String policyIdentifierHashAlgorithm , java.lang.String policyUrlDocument , java.lang.Long motiuDelegacioID , java.lang.Long firmatPerFormatID , int algorismeDeFirmaID , int politicaCustodia , java.lang.Long custodiaInfoID , int politicaTaulaFirmes , int posicioTaulaFirmes , java.lang.String propietatsTaulaFirmes , int politicaSegellatDeTemps , java.lang.Long pluginSegellTempsID , java.lang.Long pluginRubricaID , boolean validarfirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat , java.lang.Long pluginValidaFirmesID , java.lang.Long pluginValidaCertificatID) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.activa=activa;
    this.web=web;
    this.faviconID=faviconID;
    this.logoWebID=logoWebID;
    this.logoWebPeuID=logoWebPeuID;
    this.logoSegellID=logoSegellID;
    this.adrezaHtml=adrezaHtml;
    this.filtreCertificats=filtreCertificats;
    this.pdfAutoritzacioDelegacioID=pdfAutoritzacioDelegacioID;
    this.suportTelefon=suportTelefon;
    this.suportWeb=suportWeb;
    this.suportEmail=suportEmail;
    this.usuariAplicacioID=usuariAplicacioID;
    this.maxUploadSize=maxUploadSize;
    this.maxSizeFitxerAdaptat=maxSizeFitxerAdaptat;
    this.maxFilesToSignAtSameTime=maxFilesToSignAtSameTime;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.policyIdentifier=policyIdentifier;
    this.policyIdentifierHash=policyIdentifierHash;
    this.policyIdentifierHashAlgorithm=policyIdentifierHashAlgorithm;
    this.policyUrlDocument=policyUrlDocument;
    this.motiuDelegacioID=motiuDelegacioID;
    this.firmatPerFormatID=firmatPerFormatID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmes=posicioTaulaFirmes;
    this.propietatsTaulaFirmes=propietatsTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.pluginSegellTempsID=pluginSegellTempsID;
    this.pluginRubricaID=pluginRubricaID;
    this.validarfirma=validarfirma;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
    this.pluginValidaFirmesID=pluginValidaFirmesID;
    this.pluginValidaCertificatID=pluginValidaCertificatID;
}
  /** Constructor dels valors Not Null */
  public EntitatBean(java.lang.String entitatID , java.lang.String nom , boolean activa , java.lang.String web , java.lang.Long faviconID , java.lang.Long logoWebID , java.lang.Long logoWebPeuID , java.lang.Long logoSegellID , java.lang.String adrezaHtml , java.lang.String filtreCertificats , java.lang.Long pdfAutoritzacioDelegacioID , int usPoliticaDeFirma , int algorismeDeFirmaID , int politicaCustodia , int politicaTaulaFirmes , int posicioTaulaFirmes , int politicaSegellatDeTemps , boolean validarfirma , boolean comprovarNifFirma , boolean checkCanviatDocFirmat) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.activa=activa;
    this.web=web;
    this.faviconID=faviconID;
    this.logoWebID=logoWebID;
    this.logoWebPeuID=logoWebPeuID;
    this.logoSegellID=logoSegellID;
    this.adrezaHtml=adrezaHtml;
    this.filtreCertificats=filtreCertificats;
    this.pdfAutoritzacioDelegacioID=pdfAutoritzacioDelegacioID;
    this.usPoliticaDeFirma=usPoliticaDeFirma;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.politicaCustodia=politicaCustodia;
    this.politicaTaulaFirmes=politicaTaulaFirmes;
    this.posicioTaulaFirmes=posicioTaulaFirmes;
    this.politicaSegellatDeTemps=politicaSegellatDeTemps;
    this.validarfirma=validarfirma;
    this.comprovarNifFirma=comprovarNifFirma;
    this.checkCanviatDocFirmat=checkCanviatDocFirmat;
}
  public EntitatBean(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setActiva(__bean.isActiva());
    this.setWeb(__bean.getWeb());
    this.setFaviconID(__bean.getFaviconID());
    this.setLogoWebID(__bean.getLogoWebID());
    this.setLogoWebPeuID(__bean.getLogoWebPeuID());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setAdrezaHtml(__bean.getAdrezaHtml());
    this.setFiltreCertificats(__bean.getFiltreCertificats());
    this.setPdfAutoritzacioDelegacioID(__bean.getPdfAutoritzacioDelegacioID());
    this.setSuportTelefon(__bean.getSuportTelefon());
    this.setSuportWeb(__bean.getSuportWeb());
    this.setSuportEmail(__bean.getSuportEmail());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setMaxUploadSize(__bean.getMaxUploadSize());
    this.setMaxSizeFitxerAdaptat(__bean.getMaxSizeFitxerAdaptat());
    this.setMaxFilesToSignAtSameTime(__bean.getMaxFilesToSignAtSameTime());
    this.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    this.setPolicyIdentifier(__bean.getPolicyIdentifier());
    this.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    this.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    this.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    this.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    this.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setPoliticaCustodia(__bean.getPoliticaCustodia());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    this.setPosicioTaulaFirmes(__bean.getPosicioTaulaFirmes());
    this.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    this.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    this.setPluginSegellTempsID(__bean.getPluginSegellTempsID());
    this.setPluginRubricaID(__bean.getPluginRubricaID());
    this.setValidarfirma(__bean.isValidarfirma());
    this.setComprovarNifFirma(__bean.isComprovarNifFirma());
    this.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    this.setPluginValidaFirmesID(__bean.getPluginValidaFirmesID());
    this.setPluginValidaCertificatID(__bean.getPluginValidaCertificatID());
    // Fitxer
    this.setFavicon(FitxerBean.toBean(__bean.getFavicon()));
    // Fitxer
    this.setLogoWeb(FitxerBean.toBean(__bean.getLogoWeb()));
    // Fitxer
    this.setLogoWebPeu(FitxerBean.toBean(__bean.getLogoWebPeu()));
    // Fitxer
    this.setLogoSegell(FitxerBean.toBean(__bean.getLogoSegell()));
    // Fitxer
    this.setPdfAutoritzacioDelegacio(FitxerBean.toBean(__bean.getPdfAutoritzacioDelegacio()));
	}

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public java.lang.String getWeb() {
		return(web);
	};
	public void setWeb(java.lang.String _web_) {
		this.web = _web_;
	};

	public java.lang.Long getFaviconID() {
		return(faviconID);
	};
	public void setFaviconID(java.lang.Long _faviconID_) {
		this.faviconID = _faviconID_;
	};

	public java.lang.Long getLogoWebID() {
		return(logoWebID);
	};
	public void setLogoWebID(java.lang.Long _logoWebID_) {
		this.logoWebID = _logoWebID_;
	};

	public java.lang.Long getLogoWebPeuID() {
		return(logoWebPeuID);
	};
	public void setLogoWebPeuID(java.lang.Long _logoWebPeuID_) {
		this.logoWebPeuID = _logoWebPeuID_;
	};

	public java.lang.Long getLogoSegellID() {
		return(logoSegellID);
	};
	public void setLogoSegellID(java.lang.Long _logoSegellID_) {
		this.logoSegellID = _logoSegellID_;
	};

	public java.lang.String getAdrezaHtml() {
		return(adrezaHtml);
	};
	public void setAdrezaHtml(java.lang.String _adrezaHtml_) {
		this.adrezaHtml = _adrezaHtml_;
	};

	public java.lang.String getFiltreCertificats() {
		return(filtreCertificats);
	};
	public void setFiltreCertificats(java.lang.String _filtreCertificats_) {
		this.filtreCertificats = _filtreCertificats_;
	};

	public java.lang.Long getPdfAutoritzacioDelegacioID() {
		return(pdfAutoritzacioDelegacioID);
	};
	public void setPdfAutoritzacioDelegacioID(java.lang.Long _pdfAutoritzacioDelegacioID_) {
		this.pdfAutoritzacioDelegacioID = _pdfAutoritzacioDelegacioID_;
	};

	public java.lang.String getSuportTelefon() {
		return(suportTelefon);
	};
	public void setSuportTelefon(java.lang.String _suportTelefon_) {
		this.suportTelefon = _suportTelefon_;
	};

	public java.lang.String getSuportWeb() {
		return(suportWeb);
	};
	public void setSuportWeb(java.lang.String _suportWeb_) {
		this.suportWeb = _suportWeb_;
	};

	public java.lang.String getSuportEmail() {
		return(suportEmail);
	};
	public void setSuportEmail(java.lang.String _suportEmail_) {
		this.suportEmail = _suportEmail_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.Long getMaxUploadSize() {
		return(maxUploadSize);
	};
	public void setMaxUploadSize(java.lang.Long _maxUploadSize_) {
		this.maxUploadSize = _maxUploadSize_;
	};

	public java.lang.Long getMaxSizeFitxerAdaptat() {
		return(maxSizeFitxerAdaptat);
	};
	public void setMaxSizeFitxerAdaptat(java.lang.Long _maxSizeFitxerAdaptat_) {
		this.maxSizeFitxerAdaptat = _maxSizeFitxerAdaptat_;
	};

	public java.lang.Integer getMaxFilesToSignAtSameTime() {
		return(maxFilesToSignAtSameTime);
	};
	public void setMaxFilesToSignAtSameTime(java.lang.Integer _maxFilesToSignAtSameTime_) {
		this.maxFilesToSignAtSameTime = _maxFilesToSignAtSameTime_;
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

	public int getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(int _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
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

	public int getPosicioTaulaFirmes() {
		return(posicioTaulaFirmes);
	};
	public void setPosicioTaulaFirmes(int _posicioTaulaFirmes_) {
		this.posicioTaulaFirmes = _posicioTaulaFirmes_;
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

	public java.lang.Long getPluginSegellTempsID() {
		return(pluginSegellTempsID);
	};
	public void setPluginSegellTempsID(java.lang.Long _pluginSegellTempsID_) {
		this.pluginSegellTempsID = _pluginSegellTempsID_;
	};

	public java.lang.Long getPluginRubricaID() {
		return(pluginRubricaID);
	};
	public void setPluginRubricaID(java.lang.Long _pluginRubricaID_) {
		this.pluginRubricaID = _pluginRubricaID_;
	};

	public boolean isValidarfirma() {
		return(validarfirma);
	};
	public void setValidarfirma(boolean _validarfirma_) {
		this.validarfirma = _validarfirma_;
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

	public java.lang.Long getPluginValidaFirmesID() {
		return(pluginValidaFirmesID);
	};
	public void setPluginValidaFirmesID(java.lang.Long _pluginValidaFirmesID_) {
		this.pluginValidaFirmesID = _pluginValidaFirmesID_;
	};

	public java.lang.Long getPluginValidaCertificatID() {
		return(pluginValidaCertificatID);
	};
	public void setPluginValidaCertificatID(java.lang.Long _pluginValidaCertificatID_) {
		this.pluginValidaCertificatID = _pluginValidaCertificatID_;
	};



  // ======================================

  public static EntitatBean toBean(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatBean __tmp = new EntitatBean();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setWeb(__bean.getWeb());
    __tmp.setFaviconID(__bean.getFaviconID());
    __tmp.setLogoWebID(__bean.getLogoWebID());
    __tmp.setLogoWebPeuID(__bean.getLogoWebPeuID());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setAdrezaHtml(__bean.getAdrezaHtml());
    __tmp.setFiltreCertificats(__bean.getFiltreCertificats());
    __tmp.setPdfAutoritzacioDelegacioID(__bean.getPdfAutoritzacioDelegacioID());
    __tmp.setSuportTelefon(__bean.getSuportTelefon());
    __tmp.setSuportWeb(__bean.getSuportWeb());
    __tmp.setSuportEmail(__bean.getSuportEmail());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setMaxUploadSize(__bean.getMaxUploadSize());
    __tmp.setMaxSizeFitxerAdaptat(__bean.getMaxSizeFitxerAdaptat());
    __tmp.setMaxFilesToSignAtSameTime(__bean.getMaxFilesToSignAtSameTime());
    __tmp.setUsPoliticaDeFirma(__bean.getUsPoliticaDeFirma());
    __tmp.setPolicyIdentifier(__bean.getPolicyIdentifier());
    __tmp.setPolicyIdentifierHash(__bean.getPolicyIdentifierHash());
    __tmp.setPolicyIdentifierHashAlgorithm(__bean.getPolicyIdentifierHashAlgorithm());
    __tmp.setPolicyUrlDocument(__bean.getPolicyUrlDocument());
    __tmp.setMotiuDelegacioID(__bean.getMotiuDelegacioID());
    __tmp.setFirmatPerFormatID(__bean.getFirmatPerFormatID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setPoliticaCustodia(__bean.getPoliticaCustodia());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setPoliticaTaulaFirmes(__bean.getPoliticaTaulaFirmes());
    __tmp.setPosicioTaulaFirmes(__bean.getPosicioTaulaFirmes());
    __tmp.setPropietatsTaulaFirmes(__bean.getPropietatsTaulaFirmes());
    __tmp.setPoliticaSegellatDeTemps(__bean.getPoliticaSegellatDeTemps());
    __tmp.setPluginSegellTempsID(__bean.getPluginSegellTempsID());
    __tmp.setPluginRubricaID(__bean.getPluginRubricaID());
    __tmp.setValidarfirma(__bean.isValidarfirma());
    __tmp.setComprovarNifFirma(__bean.isComprovarNifFirma());
    __tmp.setCheckCanviatDocFirmat(__bean.isCheckCanviatDocFirmat());
    __tmp.setPluginValidaFirmesID(__bean.getPluginValidaFirmesID());
    __tmp.setPluginValidaCertificatID(__bean.getPluginValidaCertificatID());
    // Fitxer
    __tmp.setFavicon(FitxerBean.toBean(__bean.getFavicon()));
    // Fitxer
    __tmp.setLogoWeb(FitxerBean.toBean(__bean.getLogoWeb()));
    // Fitxer
    __tmp.setLogoWebPeu(FitxerBean.toBean(__bean.getLogoWebPeu()));
    // Fitxer
    __tmp.setLogoSegell(FitxerBean.toBean(__bean.getLogoSegell()));
    // Fitxer
    __tmp.setPdfAutoritzacioDelegacio(FitxerBean.toBean(__bean.getPdfAutoritzacioDelegacio()));
		return __tmp;
	}

  protected FitxerBean favicon;
  public FitxerBean getFavicon() {
    return favicon;
  }
  public void setFavicon(FitxerBean __field) {
    this. favicon = __field;
  }
  protected FitxerBean logoWeb;
  public FitxerBean getLogoWeb() {
    return logoWeb;
  }
  public void setLogoWeb(FitxerBean __field) {
    this. logoWeb = __field;
  }
  protected FitxerBean logoWebPeu;
  public FitxerBean getLogoWebPeu() {
    return logoWebPeu;
  }
  public void setLogoWebPeu(FitxerBean __field) {
    this. logoWebPeu = __field;
  }
  protected FitxerBean logoSegell;
  public FitxerBean getLogoSegell() {
    return logoSegell;
  }
  public void setLogoSegell(FitxerBean __field) {
    this. logoSegell = __field;
  }
  protected FitxerBean pdfAutoritzacioDelegacio;
  public FitxerBean getPdfAutoritzacioDelegacio() {
    return pdfAutoritzacioDelegacio;
  }
  public void setPdfAutoritzacioDelegacio(FitxerBean __field) {
    this. pdfAutoritzacioDelegacio = __field;
  }


}
