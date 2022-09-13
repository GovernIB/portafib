
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.RoleUsuariEntitat;


public class RoleUsuariEntitatBean implements RoleUsuariEntitat {



	long id;// PK
	java.lang.String roleID;
	java.lang.String usuariEntitatID;


  /** Constructor Buit */
  public RoleUsuariEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public RoleUsuariEntitatBean(long id , java.lang.String roleID , java.lang.String usuariEntitatID) {
    this.id=id;
    this.roleID=roleID;
    this.usuariEntitatID=usuariEntitatID;
}
  /** Constructor sense valors autoincrementals */
  public RoleUsuariEntitatBean(java.lang.String roleID , java.lang.String usuariEntitatID) {
    this.roleID=roleID;
    this.usuariEntitatID=usuariEntitatID;
}
  public RoleUsuariEntitatBean(RoleUsuariEntitat __bean) {
    this.setId(__bean.getId());
    this.setRoleID(__bean.getRoleID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
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

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};



  // ======================================

  public static RoleUsuariEntitatBean toBean(RoleUsuariEntitat __bean) {
    if (__bean == null) { return null;}
    RoleUsuariEntitatBean __tmp = new RoleUsuariEntitatBean();
    __tmp.setId(__bean.getId());
    __tmp.setRoleID(__bean.getRoleID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
		return __tmp;
	}



}
