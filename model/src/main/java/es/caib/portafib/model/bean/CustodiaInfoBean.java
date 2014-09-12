
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.CustodiaInfo;


public class CustodiaInfoBean implements CustodiaInfo {



private static final long serialVersionUID = -1840485314L;

	long custodiaInfoID;// PK
	java.lang.String custodiaPluginID;
	java.lang.String custodiaPluginClassID;
	java.lang.String custodiaPluginParameters;
	java.lang.String nomPlantilla;
	boolean custodiar;
	java.lang.String urlFitxerCustodiat;
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


  /** Constructor Buit */
  public CustodiaInfoBean() {
  }

  /** Constructor amb tots els camps  */
  public CustodiaInfoBean(long custodiaInfoID , java.lang.String custodiaPluginID , java.lang.String custodiaPluginClassID , java.lang.String custodiaPluginParameters , java.lang.String nomPlantilla , boolean custodiar , java.lang.String urlFitxerCustodiat , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String titolPeticio , java.sql.Timestamp dataCustodia , boolean editable) {
    this.custodiaInfoID=custodiaInfoID;
    this.custodiaPluginID=custodiaPluginID;
    this.custodiaPluginClassID=custodiaPluginClassID;
    this.custodiaPluginParameters=custodiaPluginParameters;
    this.nomPlantilla=nomPlantilla;
    this.custodiar=custodiar;
    this.urlFitxerCustodiat=urlFitxerCustodiat;
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
}
  /** Constructor sense valors autoincrementals */
  public CustodiaInfoBean(java.lang.String custodiaPluginID , java.lang.String custodiaPluginClassID , java.lang.String custodiaPluginParameters , java.lang.String nomPlantilla , boolean custodiar , java.lang.String urlFitxerCustodiat , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String titolPeticio , java.sql.Timestamp dataCustodia , boolean editable) {
    this.custodiaPluginID=custodiaPluginID;
    this.custodiaPluginClassID=custodiaPluginClassID;
    this.custodiaPluginParameters=custodiaPluginParameters;
    this.nomPlantilla=nomPlantilla;
    this.custodiar=custodiar;
    this.urlFitxerCustodiat=urlFitxerCustodiat;
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
}
  /** Constructor dels valors Not Null */
  public CustodiaInfoBean(long custodiaInfoID , java.lang.String custodiaPluginClassID , boolean custodiar , java.lang.String pagines , java.lang.String missatge , long missatgePosicioPaginaID , java.lang.String codiBarresID , long codiBarresPosicioPaginaID , java.lang.String codiBarresText , boolean editable) {
    this.custodiaInfoID=custodiaInfoID;
    this.custodiaPluginClassID=custodiaPluginClassID;
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
    this.setCustodiaPluginID(__bean.getCustodiaPluginID());
    this.setCustodiaPluginClassID(__bean.getCustodiaPluginClassID());
    this.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    this.setNomPlantilla(__bean.getNomPlantilla());
    this.setCustodiar(__bean.isCustodiar());
    this.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
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
	}

	public long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public java.lang.String getCustodiaPluginID() {
		return(custodiaPluginID);
	};
	public void setCustodiaPluginID(java.lang.String _custodiaPluginID_) {
		this.custodiaPluginID = _custodiaPluginID_;
	};

	public java.lang.String getCustodiaPluginClassID() {
		return(custodiaPluginClassID);
	};
	public void setCustodiaPluginClassID(java.lang.String _custodiaPluginClassID_) {
		this.custodiaPluginClassID = _custodiaPluginClassID_;
	};

	public java.lang.String getCustodiaPluginParameters() {
		return(custodiaPluginParameters);
	};
	public void setCustodiaPluginParameters(java.lang.String _custodiaPluginParameters_) {
		this.custodiaPluginParameters = _custodiaPluginParameters_;
	};

	public java.lang.String getNomPlantilla() {
		return(nomPlantilla);
	};
	public void setNomPlantilla(java.lang.String _nomPlantilla_) {
		this.nomPlantilla = _nomPlantilla_;
	};

	public boolean isCustodiar() {
		return(custodiar);
	};
	public void setCustodiar(boolean _custodiar_) {
		this.custodiar = _custodiar_;
	};

	public java.lang.String getUrlFitxerCustodiat() {
		return(urlFitxerCustodiat);
	};
	public void setUrlFitxerCustodiat(java.lang.String _urlFitxerCustodiat_) {
		this.urlFitxerCustodiat = _urlFitxerCustodiat_;
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



  // ======================================

  public static CustodiaInfoBean toBean(CustodiaInfo __bean) {
    if (__bean == null) { return null;}
    CustodiaInfoBean __tmp = new CustodiaInfoBean();
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setCustodiaPluginID(__bean.getCustodiaPluginID());
    __tmp.setCustodiaPluginClassID(__bean.getCustodiaPluginClassID());
    __tmp.setCustodiaPluginParameters(__bean.getCustodiaPluginParameters());
    __tmp.setNomPlantilla(__bean.getNomPlantilla());
    __tmp.setCustodiar(__bean.isCustodiar());
    __tmp.setUrlFitxerCustodiat(__bean.getUrlFitxerCustodiat());
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
		return __tmp;
	}



}
