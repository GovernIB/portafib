
package es.caib.portafib.ws.v1.impl;

import es.caib.portafib.model.entity.UsuariAplicacio;

/**
 * IMPORTANT: Aquesta Classe no es pot modificar ja que forma part
 * de l'API V1.0. Només es pot modificar el mètode toBean() 
 * i el constructor a partir del Objecte del Model
 * 
 * @author anadal
 *
 */
public class UsuariAplicacioBean  {


	java.lang.String usuariAplicacioID;// PK
	java.lang.String contrasenya;
	java.lang.String entitatID;
	java.lang.String emailAdmin;
	int callbackVersio;
	java.lang.String callbackURL;
	boolean actiu;
	java.lang.String idiomaID;
	java.lang.String descripcio;
	java.lang.Long logoSegellID;
	java.lang.Boolean potCustodiar;


  /** Constructor Buit */
  public UsuariAplicacioBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariAplicacioBean(java.lang.String usuariAplicacioID , java.lang.String contrasenya , java.lang.String entitatID , java.lang.String emailAdmin , int callbackVersio , java.lang.String callbackURL , boolean actiu , java.lang.String idiomaID , java.lang.String descripcio , java.lang.Long logoSegellID , java.lang.Boolean potCustodiar) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.contrasenya=contrasenya;
    this.entitatID=entitatID;
    this.emailAdmin=emailAdmin;
    this.callbackVersio=callbackVersio;
    this.callbackURL=callbackURL;
    this.actiu=actiu;
    this.idiomaID=idiomaID;
    this.descripcio=descripcio;
    this.logoSegellID=logoSegellID;
    this.potCustodiar=potCustodiar;
}
  /** Constructor dels valors Not Null */
  public UsuariAplicacioBean(java.lang.String usuariAplicacioID , java.lang.String entitatID , java.lang.String emailAdmin , int callbackVersio , java.lang.String callbackURL , boolean actiu , java.lang.String idiomaID) {
    this.usuariAplicacioID=usuariAplicacioID;
    this.entitatID=entitatID;
    this.emailAdmin=emailAdmin;
    this.callbackVersio=callbackVersio;
    this.callbackURL=callbackURL;
    this.actiu=actiu;
    this.idiomaID=idiomaID;
}
  public UsuariAplicacioBean(UsuariAplicacio __bean) {
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setContrasenya(__bean.getContrasenya());
    this.setEntitatID(__bean.getEntitatID());
    this.setEmailAdmin(__bean.getEmailAdmin());
    this.setCallbackVersio(__bean.getCallbackVersio());
    this.setCallbackURL(__bean.getCallbackURL());
    this.setActiu(__bean.isActiu());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setDescripcio(__bean.getDescripcio());
    this.setLogoSegellID(__bean.getLogoSegellID());
    //this.setPotCustodiar(__bean.getPotCustodiar());
    // Fitxer
    this.setLogoSegell(FitxerBean.toBean(__bean.getLogoSegell()));
	}

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.String getContrasenya() {
		return(contrasenya);
	};
	public void setContrasenya(java.lang.String _contrasenya_) {
		this.contrasenya = _contrasenya_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getEmailAdmin() {
		return(emailAdmin);
	};
	public void setEmailAdmin(java.lang.String _emailAdmin_) {
		this.emailAdmin = _emailAdmin_;
	};

	public int getCallbackVersio() {
		return(callbackVersio);
	};
	public void setCallbackVersio(int _callbackVersio_) {
		this.callbackVersio = _callbackVersio_;
	};

	public java.lang.String getCallbackURL() {
		return(callbackURL);
	};
	public void setCallbackURL(java.lang.String _callbackURL_) {
		this.callbackURL = _callbackURL_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.Long getLogoSegellID() {
		return(logoSegellID);
	};
	public void setLogoSegellID(java.lang.Long _logoSegellID_) {
		this.logoSegellID = _logoSegellID_;
	};

	public java.lang.Boolean getPotCustodiar() {
		return(potCustodiar);
	};
	public void setPotCustodiar(java.lang.Boolean _potCustodiar_) {
		this.potCustodiar = _potCustodiar_;
	};



  // ======================================

  public static UsuariAplicacioBean toBean(UsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    UsuariAplicacioBean __tmp = new UsuariAplicacioBean();
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setContrasenya(__bean.getContrasenya());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setEmailAdmin(__bean.getEmailAdmin());
    __tmp.setCallbackVersio(__bean.getCallbackVersio());
    __tmp.setCallbackURL(__bean.getCallbackURL());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    //__tmp.setPotCustodiar(__bean.getPotCustodiar());
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
