
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PerfilsPerUsuariAplicacio;


public class PerfilsPerUsuariAplicacioBean implements PerfilsPerUsuariAplicacio {



	long perfilsPerUsrAppID;// PK
	long perfilDeFirmaID;
	java.lang.String usuariAplicacioID;


  /** Constructor Buit */
  public PerfilsPerUsuariAplicacioBean() {
  }

  /** Constructor amb tots els camps  */
  public PerfilsPerUsuariAplicacioBean(long perfilsPerUsrAppID , long perfilDeFirmaID , java.lang.String usuariAplicacioID) {
    this.perfilsPerUsrAppID=perfilsPerUsrAppID;
    this.perfilDeFirmaID=perfilDeFirmaID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor sense valors autoincrementals */
  public PerfilsPerUsuariAplicacioBean(long perfilDeFirmaID , java.lang.String usuariAplicacioID) {
    this.perfilDeFirmaID=perfilDeFirmaID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  public PerfilsPerUsuariAplicacioBean(PerfilsPerUsuariAplicacio __bean) {
    this.setPerfilsPerUsrAppID(__bean.getPerfilsPerUsrAppID());
    this.setPerfilDeFirmaID(__bean.getPerfilDeFirmaID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
	}

	public long getPerfilsPerUsrAppID() {
		return(perfilsPerUsrAppID);
	};
	public void setPerfilsPerUsrAppID(long _perfilsPerUsrAppID_) {
		this.perfilsPerUsrAppID = _perfilsPerUsrAppID_;
	};

	public long getPerfilDeFirmaID() {
		return(perfilDeFirmaID);
	};
	public void setPerfilDeFirmaID(long _perfilDeFirmaID_) {
		this.perfilDeFirmaID = _perfilDeFirmaID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};



  // ======================================

  public static PerfilsPerUsuariAplicacioBean toBean(PerfilsPerUsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    PerfilsPerUsuariAplicacioBean __tmp = new PerfilsPerUsuariAplicacioBean();
    __tmp.setPerfilsPerUsrAppID(__bean.getPerfilsPerUsrAppID());
    __tmp.setPerfilDeFirmaID(__bean.getPerfilDeFirmaID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}



}
