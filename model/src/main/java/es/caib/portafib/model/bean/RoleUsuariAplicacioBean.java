
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.RoleUsuariAplicacio;


public class RoleUsuariAplicacioBean implements RoleUsuariAplicacio {



private static final long serialVersionUID = 818017592L;

	long id;// PK
	java.lang.String roleID;
	java.lang.String usuariAplicacioID;


  /** Constructor Buit */
  public RoleUsuariAplicacioBean() {
  }

  /** Constructor amb tots els camps  */
  public RoleUsuariAplicacioBean(long id , java.lang.String roleID , java.lang.String usuariAplicacioID) {
    this.id=id;
    this.roleID=roleID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor sense valors autoincrementals */
  public RoleUsuariAplicacioBean(java.lang.String roleID , java.lang.String usuariAplicacioID) {
    this.roleID=roleID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  public RoleUsuariAplicacioBean(RoleUsuariAplicacio __bean) {
    this.setId(__bean.getId());
    this.setRoleID(__bean.getRoleID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
	}

	public long getId() {
		return(id);
	};
	public void setId(long _id_) {
		this.id = _id_;
	};

	public java.lang.String getRoleID() {
		return(roleID);
	};
	public void setRoleID(java.lang.String _roleID_) {
		this.roleID = _roleID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};



  // ======================================

  public static RoleUsuariAplicacioBean toBean(RoleUsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    RoleUsuariAplicacioBean __tmp = new RoleUsuariAplicacioBean();
    __tmp.setId(__bean.getId());
    __tmp.setRoleID(__bean.getRoleID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}



}
