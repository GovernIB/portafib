
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Plugin;


public class PluginBean implements Plugin {



private static final long serialVersionUID = 1605894563L;

	long pluginID;// PK
	long nomID;
	long descripcioCurtaID;
	java.lang.String classe;
	int tipus;
	java.lang.String propertiesAdmin;
	java.lang.String propertiesEntitat;
	java.lang.String entitatID;
	boolean actiu;


  /** Constructor Buit */
  public PluginBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginBean(long pluginID , long nomID , long descripcioCurtaID , java.lang.String classe , int tipus , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , java.lang.String entitatID , boolean actiu) {
    this.pluginID=pluginID;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.tipus=tipus;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public PluginBean(long nomID , long descripcioCurtaID , java.lang.String classe , int tipus , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , java.lang.String entitatID , boolean actiu) {
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.tipus=tipus;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  /** Constructor dels valors Not Null */
  public PluginBean(long pluginID , long nomID , long descripcioCurtaID , java.lang.String classe , int tipus , boolean actiu) {
    this.pluginID=pluginID;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.tipus=tipus;
    this.actiu=actiu;
}
  public PluginBean(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    this.setClasse(__bean.getClasse());
    this.setTipus(__bean.getTipus());
    this.setPropertiesAdmin(__bean.getPropertiesAdmin());
    this.setPropertiesEntitat(__bean.getPropertiesEntitat());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
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

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
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

  public static PluginBean toBean(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginBean __tmp = new PluginBean();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setPropertiesAdmin(__bean.getPropertiesAdmin());
    __tmp.setPropertiesEntitat(__bean.getPropertiesEntitat());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}



}
