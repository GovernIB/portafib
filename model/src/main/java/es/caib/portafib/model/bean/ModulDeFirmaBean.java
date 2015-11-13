
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.ModulDeFirma;


public class ModulDeFirmaBean implements ModulDeFirma {



private static final long serialVersionUID = -1405011463L;

	long modulDeFirmaID;// PK
	long nomID;
	long descripcioCurtaID;
	java.lang.String classe;
	java.lang.String propertiesAdmin;
	java.lang.String propertiesEntitat;
	java.lang.String entitatID;
	boolean actiu;


  /** Constructor Buit */
  public ModulDeFirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public ModulDeFirmaBean(long modulDeFirmaID , long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , java.lang.String entitatID , boolean actiu) {
    this.modulDeFirmaID=modulDeFirmaID;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public ModulDeFirmaBean(long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , java.lang.String entitatID , boolean actiu) {
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  /** Constructor dels valors Not Null */
  public ModulDeFirmaBean(long modulDeFirmaID , long nomID , long descripcioCurtaID , java.lang.String classe , boolean actiu) {
    this.modulDeFirmaID=modulDeFirmaID;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.actiu=actiu;
}
  public ModulDeFirmaBean(ModulDeFirma __bean) {
    this.setModulDeFirmaID(__bean.getModulDeFirmaID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    this.setClasse(__bean.getClasse());
    this.setPropertiesAdmin(__bean.getPropertiesAdmin());
    this.setPropertiesEntitat(__bean.getPropertiesEntitat());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
	}

	public long getModulDeFirmaID() {
		return(modulDeFirmaID);
	};
	public void setModulDeFirmaID(long _modulDeFirmaID_) {
		this.modulDeFirmaID = _modulDeFirmaID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public long getDescripcioCurtaID() {
		return(descripcioCurtaID);
	};
	public void setDescripcioCurtaID(long _descripcioCurtaID_) {
		this.descripcioCurtaID = _descripcioCurtaID_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public java.lang.String getPropertiesAdmin() {
		return(propertiesAdmin);
	};
	public void setPropertiesAdmin(java.lang.String _propertiesAdmin_) {
		this.propertiesAdmin = _propertiesAdmin_;
	};

	public java.lang.String getPropertiesEntitat() {
		return(propertiesEntitat);
	};
	public void setPropertiesEntitat(java.lang.String _propertiesEntitat_) {
		this.propertiesEntitat = _propertiesEntitat_;
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



  // ======================================

  public static ModulDeFirmaBean toBean(ModulDeFirma __bean) {
    if (__bean == null) { return null;}
    ModulDeFirmaBean __tmp = new ModulDeFirmaBean();
    __tmp.setModulDeFirmaID(__bean.getModulDeFirmaID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setPropertiesAdmin(__bean.getPropertiesAdmin());
    __tmp.setPropertiesEntitat(__bean.getPropertiesEntitat());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}



}
