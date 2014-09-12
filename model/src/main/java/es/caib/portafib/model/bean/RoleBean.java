
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Role;


public class RoleBean implements Role {



private static final long serialVersionUID = -202489498L;

	java.lang.String roleID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public RoleBean() {
  }

  /** Constructor amb tots els camps  */
  public RoleBean(java.lang.String roleID , java.lang.String nom , java.lang.String descripcio) {
    this.roleID=roleID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public RoleBean(java.lang.String roleID , java.lang.String nom) {
    this.roleID=roleID;
    this.nom=nom;
}
  public RoleBean(Role __bean) {
    this.setRoleID(__bean.getRoleID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public java.lang.String getRoleID() {
		return(roleID);
	};
	public void setRoleID(java.lang.String _roleID_) {
		this.roleID = _roleID_;
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



  // ======================================

  public static RoleBean toBean(Role __bean) {
    if (__bean == null) { return null;}
    RoleBean __tmp = new RoleBean();
    __tmp.setRoleID(__bean.getRoleID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
