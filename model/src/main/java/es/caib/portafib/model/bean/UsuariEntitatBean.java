
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.UsuariEntitat;


public class UsuariEntitatBean implements UsuariEntitat {



	java.lang.String usuariEntitatID;// PK
	java.lang.String carrec;
	java.lang.String usuariPersonaID;
	java.lang.String entitatID;
	boolean actiu;
	java.lang.String email;
	java.lang.Long logoSegellID;
	boolean predeterminat;
	boolean rebreTotsElsAvisos;
	int politicaDePluginFirmaWeb;
	int politicaCustodia;
	java.lang.Long custodiaInfoID;


  /** Constructor Buit */
  public UsuariEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatBean(java.lang.String usuariEntitatID , java.lang.String carrec , java.lang.String usuariPersonaID , java.lang.String entitatID , boolean actiu , java.lang.String email , java.lang.Long logoSegellID , boolean predeterminat , boolean rebreTotsElsAvisos , int politicaDePluginFirmaWeb , int politicaCustodia , java.lang.Long custodiaInfoID) {
    this.usuariEntitatID=usuariEntitatID;
    this.carrec=carrec;
    this.usuariPersonaID=usuariPersonaID;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.email=email;
    this.logoSegellID=logoSegellID;
    this.predeterminat=predeterminat;
    this.rebreTotsElsAvisos=rebreTotsElsAvisos;
    this.politicaDePluginFirmaWeb=politicaDePluginFirmaWeb;
    this.politicaCustodia=politicaCustodia;
    this.custodiaInfoID=custodiaInfoID;
}
  /** Constructor dels valors Not Null */
  public UsuariEntitatBean(java.lang.String usuariEntitatID , java.lang.String usuariPersonaID , java.lang.String entitatID , boolean actiu , boolean predeterminat , boolean rebreTotsElsAvisos , int politicaDePluginFirmaWeb , int politicaCustodia) {
    this.usuariEntitatID=usuariEntitatID;
    this.usuariPersonaID=usuariPersonaID;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.predeterminat=predeterminat;
    this.rebreTotsElsAvisos=rebreTotsElsAvisos;
    this.politicaDePluginFirmaWeb=politicaDePluginFirmaWeb;
    this.politicaCustodia=politicaCustodia;
}
  public UsuariEntitatBean(UsuariEntitat __bean) {
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setCarrec(__bean.getCarrec());
    this.setUsuariPersonaID(__bean.getUsuariPersonaID());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
    this.setEmail(__bean.getEmail());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setPredeterminat(__bean.isPredeterminat());
    this.setRebreTotsElsAvisos(__bean.isRebreTotsElsAvisos());
    this.setPoliticaDePluginFirmaWeb(__bean.getPoliticaDePluginFirmaWeb());
    this.setPoliticaCustodia(__bean.getPoliticaCustodia());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    // Fitxer
    this.setLogoSegell(FitxerBean.toBean(__bean.getLogoSegell()));
	}

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.lang.String getCarrec() {
		return(carrec);
	};
	public void setCarrec(java.lang.String _carrec_) {
		this.carrec = _carrec_;
	};

	public java.lang.String getUsuariPersonaID() {
		return(usuariPersonaID);
	};
	public void setUsuariPersonaID(java.lang.String _usuariPersonaID_) {
		this.usuariPersonaID = _usuariPersonaID_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.Long getLogoSegellID() {
		return(logoSegellID);
	};
	public void setLogoSegellID(java.lang.Long _logoSegellID_) {
		this.logoSegellID = _logoSegellID_;
	};

	public boolean isPredeterminat() {
		return(predeterminat);
	};
	public void setPredeterminat(boolean _predeterminat_) {
		this.predeterminat = _predeterminat_;
	};

	public boolean isRebreTotsElsAvisos() {
		return(rebreTotsElsAvisos);
	};
	public void setRebreTotsElsAvisos(boolean _rebreTotsElsAvisos_) {
		this.rebreTotsElsAvisos = _rebreTotsElsAvisos_;
	};

	public int getPoliticaDePluginFirmaWeb() {
		return(politicaDePluginFirmaWeb);
	};
	public void setPoliticaDePluginFirmaWeb(int _politicaDePluginFirmaWeb_) {
		this.politicaDePluginFirmaWeb = _politicaDePluginFirmaWeb_;
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



  // ======================================

  public static UsuariEntitatBean toBean(UsuariEntitat __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatBean __tmp = new UsuariEntitatBean();
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setCarrec(__bean.getCarrec());
    __tmp.setUsuariPersonaID(__bean.getUsuariPersonaID());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setPredeterminat(__bean.isPredeterminat());
    __tmp.setRebreTotsElsAvisos(__bean.isRebreTotsElsAvisos());
    __tmp.setPoliticaDePluginFirmaWeb(__bean.getPoliticaDePluginFirmaWeb());
    __tmp.setPoliticaCustodia(__bean.getPoliticaCustodia());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    // Fitxer
    __tmp.setLogoSegell(FitxerBean.toBean(__bean.getLogoSegell()));
		return __tmp;
	}

  protected FitxerBean logoSegell;
  public FitxerBean getLogoSegell() {
    return logoSegell;
  }
  public void setLogoSegell(FitxerBean __field) {
    this. logoSegell = __field;
  }


}
