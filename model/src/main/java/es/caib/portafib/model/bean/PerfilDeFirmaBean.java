
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PerfilDeFirma;


public class PerfilDeFirmaBean implements PerfilDeFirma {



private static final long serialVersionUID = -1439896794L;

	long usuariAplicacioPerfilID;// PK
	java.lang.String nom;
	java.lang.String codi;
	java.lang.String descripcio;
	java.lang.String condicio;
	long configuracioDeFirma1ID;
	java.lang.Long configuracioDeFirma2ID;
	java.lang.Long configuracioDeFirma3ID;
	java.lang.Long configuracioDeFirma4ID;
	java.lang.Long configuracioDeFirma5ID;
	java.lang.String urlBase;


  /** Constructor Buit */
  public PerfilDeFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public PerfilDeFirmaBean(long usuariAplicacioPerfilID , java.lang.String nom , java.lang.String codi , java.lang.String descripcio , java.lang.String condicio , long configuracioDeFirma1ID , java.lang.Long configuracioDeFirma2ID , java.lang.Long configuracioDeFirma3ID , java.lang.Long configuracioDeFirma4ID , java.lang.Long configuracioDeFirma5ID , java.lang.String urlBase) {
    this.usuariAplicacioPerfilID=usuariAplicacioPerfilID;
    this.nom=nom;
    this.codi=codi;
    this.descripcio=descripcio;
    this.condicio=condicio;
    this.configuracioDeFirma1ID=configuracioDeFirma1ID;
    this.configuracioDeFirma2ID=configuracioDeFirma2ID;
    this.configuracioDeFirma3ID=configuracioDeFirma3ID;
    this.configuracioDeFirma4ID=configuracioDeFirma4ID;
    this.configuracioDeFirma5ID=configuracioDeFirma5ID;
    this.urlBase=urlBase;
}
  /** Constructor sense valors autoincrementals */
  public PerfilDeFirmaBean(java.lang.String nom , java.lang.String codi , java.lang.String descripcio , java.lang.String condicio , long configuracioDeFirma1ID , java.lang.Long configuracioDeFirma2ID , java.lang.Long configuracioDeFirma3ID , java.lang.Long configuracioDeFirma4ID , java.lang.Long configuracioDeFirma5ID , java.lang.String urlBase) {
    this.nom=nom;
    this.codi=codi;
    this.descripcio=descripcio;
    this.condicio=condicio;
    this.configuracioDeFirma1ID=configuracioDeFirma1ID;
    this.configuracioDeFirma2ID=configuracioDeFirma2ID;
    this.configuracioDeFirma3ID=configuracioDeFirma3ID;
    this.configuracioDeFirma4ID=configuracioDeFirma4ID;
    this.configuracioDeFirma5ID=configuracioDeFirma5ID;
    this.urlBase=urlBase;
}
  /** Constructor dels valors Not Null */
  public PerfilDeFirmaBean(long usuariAplicacioPerfilID , java.lang.String nom , java.lang.String codi , long configuracioDeFirma1ID) {
    this.usuariAplicacioPerfilID=usuariAplicacioPerfilID;
    this.nom=nom;
    this.codi=codi;
    this.configuracioDeFirma1ID=configuracioDeFirma1ID;
}
  public PerfilDeFirmaBean(PerfilDeFirma __bean) {
    this.setUsuariAplicacioPerfilID(__bean.getUsuariAplicacioPerfilID());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setDescripcio(__bean.getDescripcio());
    this.setCondicio(__bean.getCondicio());
    this.setConfiguracioDeFirma1ID(__bean.getConfiguracioDeFirma1ID());
    this.setConfiguracioDeFirma2ID(__bean.getConfiguracioDeFirma2ID());
    this.setConfiguracioDeFirma3ID(__bean.getConfiguracioDeFirma3ID());
    this.setConfiguracioDeFirma4ID(__bean.getConfiguracioDeFirma4ID());
    this.setConfiguracioDeFirma5ID(__bean.getConfiguracioDeFirma5ID());
    this.setUrlBase(__bean.getUrlBase());
	}

	public long getUsuariAplicacioPerfilID() {
		return(usuariAplicacioPerfilID);
	};
	public void setUsuariAplicacioPerfilID(long _usuariAplicacioPerfilID_) {
		this.usuariAplicacioPerfilID = _usuariAplicacioPerfilID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getCondicio() {
		return(condicio);
	};
	public void setCondicio(java.lang.String _condicio_) {
		this.condicio = _condicio_;
	};

	public long getConfiguracioDeFirma1ID() {
		return(configuracioDeFirma1ID);
	};
	public void setConfiguracioDeFirma1ID(long _configuracioDeFirma1ID_) {
		this.configuracioDeFirma1ID = _configuracioDeFirma1ID_;
	};

	public java.lang.Long getConfiguracioDeFirma2ID() {
		return(configuracioDeFirma2ID);
	};
	public void setConfiguracioDeFirma2ID(java.lang.Long _configuracioDeFirma2ID_) {
		this.configuracioDeFirma2ID = _configuracioDeFirma2ID_;
	};

	public java.lang.Long getConfiguracioDeFirma3ID() {
		return(configuracioDeFirma3ID);
	};
	public void setConfiguracioDeFirma3ID(java.lang.Long _configuracioDeFirma3ID_) {
		this.configuracioDeFirma3ID = _configuracioDeFirma3ID_;
	};

	public java.lang.Long getConfiguracioDeFirma4ID() {
		return(configuracioDeFirma4ID);
	};
	public void setConfiguracioDeFirma4ID(java.lang.Long _configuracioDeFirma4ID_) {
		this.configuracioDeFirma4ID = _configuracioDeFirma4ID_;
	};

	public java.lang.Long getConfiguracioDeFirma5ID() {
		return(configuracioDeFirma5ID);
	};
	public void setConfiguracioDeFirma5ID(java.lang.Long _configuracioDeFirma5ID_) {
		this.configuracioDeFirma5ID = _configuracioDeFirma5ID_;
	};

	public java.lang.String getUrlBase() {
		return(urlBase);
	};
	public void setUrlBase(java.lang.String _urlBase_) {
		this.urlBase = _urlBase_;
	};



  // ======================================

  public static PerfilDeFirmaBean toBean(PerfilDeFirma __bean) {
    if (__bean == null) { return null;}
    PerfilDeFirmaBean __tmp = new PerfilDeFirmaBean();
    __tmp.setUsuariAplicacioPerfilID(__bean.getUsuariAplicacioPerfilID());
    __tmp.setNom(__bean.getNom());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setCondicio(__bean.getCondicio());
    __tmp.setConfiguracioDeFirma1ID(__bean.getConfiguracioDeFirma1ID());
    __tmp.setConfiguracioDeFirma2ID(__bean.getConfiguracioDeFirma2ID());
    __tmp.setConfiguracioDeFirma3ID(__bean.getConfiguracioDeFirma3ID());
    __tmp.setConfiguracioDeFirma4ID(__bean.getConfiguracioDeFirma4ID());
    __tmp.setConfiguracioDeFirma5ID(__bean.getConfiguracioDeFirma5ID());
    __tmp.setUrlBase(__bean.getUrlBase());
		return __tmp;
	}



}
