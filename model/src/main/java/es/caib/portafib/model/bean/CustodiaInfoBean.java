
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.CustodiaInfo;


public class CustodiaInfoBean implements CustodiaInfo {



	long custodiaInfoID;// PK
	java.lang.String nomPlantilla;
	java.lang.String custodiaDocumentID;
	long pluginID;
	java.lang.String custodiaPluginParameters;
	boolean custodiar;
	java.lang.String pagines;
	java.lang.String missatge;
	long missatgePosicioPaginaID;
	java.lang.String codiBarresID;
	long codiBarresPosicioPaginaID;
	java.lang.String codiBarresText;
	java.lang.String usuariEntitatID;
	java.lang.String usuariAplicacioID;
	java.lang.String entitatID;
	java.lang.String titolPeticio;
	java.sql.Timestamp dataCustodia;
	boolean editable;
	java.lang.String csv;
	java.lang.String csvValidationWeb;
	java.lang.String csvGenerationDefinition;
	java.lang.String urlFitxerCustodiat;
	java.lang.String originalFileDirectUrl;
	java.lang.String printableFileDirectUrl;
	java.lang.String eniFileDirectUrl;
	java.lang.String expedientArxiuId;
	java.lang.String documentArxiuId;


  /** Constructor Buit */
  public CustodiaInfoBean() {
  }

  /** Constructor amb tots els camps  */
  public CustodiaInfoBean(long custodiaInfoID , java.lang.String nomPlantilla , java.lang.String custodiaDocumentID , long pluginID , java.lang.String custodiaPluginParameters , boolean custodiar , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String titolPeticio , java.sql.Timestamp dataCustodia , boolean editable , java.lang.String csv , java.lang.String csvValidationWeb , java.lang.String csvGenerationDefinition , java.lang.String urlFitxerCustodiat , java.lang.String originalFileDirectUrl , java.lang.String printableFileDirectUrl , java.lang.String eniFileDirectUrl , java.lang.String expedientArxiuId , java.lang.String documentArxiuId) {
    this.custodiaInfoID=custodiaInfoID;
    this.nomPlantilla=nomPlantilla;
    this.custodiaDocumentID=custodiaDocumentID;
    this.pluginID=pluginID;
    this.custodiaPluginParameters=custodiaPluginParameters;
    this.custodiar=custodiar;
    this.pagines=pagines;
    this.missatge=missatge;
    this.missatgePosicioPaginaID=missatgePosicioPaginaID;
    this.codiBarresID=codiBarresID;
    this.codiBarresPosicioPaginaID=codiBarresPosicioPaginaID;
    this.codiBarresText=codiBarresText;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.entitatID=entitatID;
    this.titolPeticio=titolPeticio;
    this.dataCustodia=dataCustodia;
    this.editable=editable;
    this.csv=csv;
    this.csvValidationWeb=csvValidationWeb;
    this.csvGenerationDefinition=csvGenerationDefinition;
    this.urlFitxerCustodiat=urlFitxerCustodiat;
    this.originalFileDirectUrl=originalFileDirectUrl;
    this.printableFileDirectUrl=printableFileDirectUrl;
    this.eniFileDirectUrl=eniFileDirectUrl;
    this.expedientArxiuId=expedientArxiuId;
    this.documentArxiuId=documentArxiuId;
}
  /** Constructor sense valors autoincrementals */
  public CustodiaInfoBean(java.lang.String nomPlantilla , java.lang.String custodiaDocumentID , long pluginID , java.lang.String custodiaPluginParameters , boolean custodiar , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String titolPeticio , java.sql.Timestamp dataCustodia , boolean editable , java.lang.String csv , java.lang.String csvValidationWeb , java.lang.String csvGenerationDefinition , java.lang.String urlFitxerCustodiat , java.lang.String originalFileDirectUrl , java.lang.String printableFileDirectUrl , java.lang.String eniFileDirectUrl , java.lang.String expedientArxiuId , java.lang.String documentArxiuId) {
    this.nomPlantilla=nomPlantilla;
    this.custodiaDocumentID=custodiaDocumentID;
    this.pluginID=pluginID;
    this.custodiaPluginParameters=custodiaPluginParameters;
    this.custodiar=custodiar;
    this.pagines=pagines;
    this.missatge=missatge;
    this.missatgePosicioPaginaID=missatgePosicioPaginaID;
    this.codiBarresID=codiBarresID;
    this.codiBarresPosicioPaginaID=codiBarresPosicioPaginaID;
    this.codiBarresText=codiBarresText;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.entitatID=entitatID;
    this.titolPeticio=titolPeticio;
    this.dataCustodia=dataCustodia;
    this.editable=editable;
    this.csv=csv;
    this.csvValidationWeb=csvValidationWeb;
    this.csvGenerationDefinition=csvGenerationDefinition;
    this.urlFitxerCustodiat=urlFitxerCustodiat;
    this.originalFileDirectUrl=originalFileDirectUrl;
    this.printableFileDirectUrl=printableFileDirectUrl;
    this.eniFileDirectUrl=eniFileDirectUrl;
    this.expedientArxiuId=expedientArxiuId;
    this.documentArxiuId=documentArxiuId;
}
  /** Constructor dels valors Not Null */
  public CustodiaInfoBean(long custodiaInfoID , long pluginID , boolean custodiar , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , boolean editable) {
    this.custodiaInfoID=custodiaInfoID;
    this.pluginID=pluginID;
    this.custodiar=custodiar;
    this.pagines=pagines;
    this.missatge=missatge;
    this.missatgePosicioPaginaID=missatgePosicioPaginaID;
    this.codiBarresID=codiBarresID;
    this.codiBarresPosicioPaginaID=codiBarresPosicioPaginaID;
    this.codiBarresText=codiBarresText;
    this.editable=editable;
}
  public CustodiaInfoBean(CustodiaInfo __bean) {
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setNomPlantilla(__bean.getNomPlantilla());
    this.setCustodiaDocumentID(__bean.getCustodiaDocumentID());
    this.setPluginID(__bean.getPluginID());
    this.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    this.setCustodiar(__bean.isCustodiar());
    this.setPagines(__bean.getPagines());
    this.setMissatge(__bean.getMissatge());
    this.setMissatgePosicioPaginaID(__bean.getMissatgePosicioPaginaID());
    this.setCodiBarresID(__bean.getCodiBarresID());
    this.setCodiBarresPosicioPaginaID(__bean.getCodiBarresPosicioPaginaID());
    this.setCodiBarresText(__bean.getCodiBarresText());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setEntitatID(__bean.getEntitatID());
    this.setTitolPeticio(__bean.getTitolPeticio());
    this.setDataCustodia(__bean.getDataCustodia());
    this.setEditable(__bean.isEditable());
    this.setCsv(__bean.getCsv());
    this.setCsvValidationWeb(__bean.getCsvValidationWeb());
    this.setCsvGenerationDefinition(__bean.getCsvGenerationDefinition());
    this.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
    this.setOriginalFileDirectUrl(__bean.getOriginalFileDirectUrl());
    this.setPrintableFileDirectUrl(__bean.getPrintableFileDirectUrl());
    this.setEniFileDirectUrl(__bean.getEniFileDirectUrl());
    this.setExpedientArxiuId(__bean.getExpedientArxiuId());
    this.setDocumentArxiuId(__bean.getDocumentArxiuId());
	}

	public long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public java.lang.String getNomPlantilla() {
		return(nomPlantilla);
	};
	public void setNomPlantilla(java.lang.String _nomPlantilla_) {
		this.nomPlantilla = _nomPlantilla_;
	};

	public java.lang.String getCustodiaDocumentID() {
		return(custodiaDocumentID);
	};
	public void setCustodiaDocumentID(java.lang.String _custodiaDocumentID_) {
		this.custodiaDocumentID = _custodiaDocumentID_;
	};

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getCustodiaPluginParameters() {
		return(custodiaPluginParameters);
	};
	public void setCustodiaPluginParameters(java.lang.String _custodiaPluginParameters_) {
		this.custodiaPluginParameters = _custodiaPluginParameters_;
	};

	public boolean isCustodiar() {
		return(custodiar);
	};
	public void setCustodiar(boolean _custodiar_) {
		this.custodiar = _custodiar_;
	};

	public java.lang.String getPagines() {
		return(pagines);
	};
	public void setPagines(java.lang.String _pagines_) {
		this.pagines = _pagines_;
	};

	public java.lang.String getMissatge() {
		return(missatge);
	};
	public void setMissatge(java.lang.String _missatge_) {
		this.missatge = _missatge_;
	};

	public long getMissatgePosicioPaginaID() {
		return(missatgePosicioPaginaID);
	};
	public void setMissatgePosicioPaginaID(long _missatgePosicioPaginaID_) {
		this.missatgePosicioPaginaID = _missatgePosicioPaginaID_;
	};

	public java.lang.String getCodiBarresID() {
		return(codiBarresID);
	};
	public void setCodiBarresID(java.lang.String _codiBarresID_) {
		this.codiBarresID = _codiBarresID_;
	};

	public long getCodiBarresPosicioPaginaID() {
		return(codiBarresPosicioPaginaID);
	};
	public void setCodiBarresPosicioPaginaID(long _codiBarresPosicioPaginaID_) {
		this.codiBarresPosicioPaginaID = _codiBarresPosicioPaginaID_;
	};

	public java.lang.String getCodiBarresText() {
		return(codiBarresText);
	};
	public void setCodiBarresText(java.lang.String _codiBarresText_) {
		this.codiBarresText = _codiBarresText_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getTitolPeticio() {
		return(titolPeticio);
	};
	public void setTitolPeticio(java.lang.String _titolPeticio_) {
		this.titolPeticio = _titolPeticio_;
	};

	public java.sql.Timestamp getDataCustodia() {
		return(dataCustodia);
	};
	public void setDataCustodia(java.sql.Timestamp _dataCustodia_) {
		this.dataCustodia = _dataCustodia_;
	};

	public boolean isEditable() {
		return(editable);
	};
	public void setEditable(boolean _editable_) {
		this.editable = _editable_;
	};

	public java.lang.String getCsv() {
		return(csv);
	};
	public void setCsv(java.lang.String _csv_) {
		this.csv = _csv_;
	};

	public java.lang.String getCsvValidationWeb() {
		return(csvValidationWeb);
	};
	public void setCsvValidationWeb(java.lang.String _csvValidationWeb_) {
		this.csvValidationWeb = _csvValidationWeb_;
	};

	public java.lang.String getCsvGenerationDefinition() {
		return(csvGenerationDefinition);
	};
	public void setCsvGenerationDefinition(java.lang.String _csvGenerationDefinition_) {
		this.csvGenerationDefinition = _csvGenerationDefinition_;
	};

	public java.lang.String getUrlFitxerCustodiat() {
		return(urlFitxerCustodiat);
	};
	public void setUrlFitxerCustodiat(java.lang.String _urlFitxerCustodiat_) {
		this.urlFitxerCustodiat = _urlFitxerCustodiat_;
	};

	public java.lang.String getOriginalFileDirectUrl() {
		return(originalFileDirectUrl);
	};
	public void setOriginalFileDirectUrl(java.lang.String _originalFileDirectUrl_) {
		this.originalFileDirectUrl = _originalFileDirectUrl_;
	};

	public java.lang.String getPrintableFileDirectUrl() {
		return(printableFileDirectUrl);
	};
	public void setPrintableFileDirectUrl(java.lang.String _printableFileDirectUrl_) {
		this.printableFileDirectUrl = _printableFileDirectUrl_;
	};

	public java.lang.String getEniFileDirectUrl() {
		return(eniFileDirectUrl);
	};
	public void setEniFileDirectUrl(java.lang.String _eniFileDirectUrl_) {
		this.eniFileDirectUrl = _eniFileDirectUrl_;
	};

	public java.lang.String getExpedientArxiuId() {
		return(expedientArxiuId);
	};
	public void setExpedientArxiuId(java.lang.String _expedientArxiuId_) {
		this.expedientArxiuId = _expedientArxiuId_;
	};

	public java.lang.String getDocumentArxiuId() {
		return(documentArxiuId);
	};
	public void setDocumentArxiuId(java.lang.String _documentArxiuId_) {
		this.documentArxiuId = _documentArxiuId_;
	};



  // ======================================

  public static CustodiaInfoBean toBean(CustodiaInfo __bean) {
    if (__bean == null) { return null;}
    CustodiaInfoBean __tmp = new CustodiaInfoBean();
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setNomPlantilla(__bean.getNomPlantilla());
    __tmp.setCustodiaDocumentID(__bean.getCustodiaDocumentID());
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    __tmp.setCustodiar(__bean.isCustodiar());
    __tmp.setPagines(__bean.getPagines());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setMissatgePosicioPaginaID(__bean.getMissatgePosicioPaginaID());
    __tmp.setCodiBarresID(__bean.getCodiBarresID());
    __tmp.setCodiBarresPosicioPaginaID(__bean.getCodiBarresPosicioPaginaID());
    __tmp.setCodiBarresText(__bean.getCodiBarresText());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setTitolPeticio(__bean.getTitolPeticio());
    __tmp.setDataCustodia(__bean.getDataCustodia());
    __tmp.setEditable(__bean.isEditable());
    __tmp.setCsv(__bean.getCsv());
    __tmp.setCsvValidationWeb(__bean.getCsvValidationWeb());
    __tmp.setCsvGenerationDefinition(__bean.getCsvGenerationDefinition());
    __tmp.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
    __tmp.setOriginalFileDirectUrl(__bean.getOriginalFileDirectUrl());
    __tmp.setPrintableFileDirectUrl(__bean.getPrintableFileDirectUrl());
    __tmp.setEniFileDirectUrl(__bean.getEniFileDirectUrl());
    __tmp.setExpedientArxiuId(__bean.getExpedientArxiuId());
    __tmp.setDocumentArxiuId(__bean.getDocumentArxiuId());
		return __tmp;
	}



}
