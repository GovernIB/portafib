
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Plugin;


public class PluginBean implements Plugin {



private static final long serialVersionUID = 1605894563L;

	long pluginID;// PK
	java.lang.String codi;
	long nomID;
	long descripcioCurtaID;
	java.lang.String classe;
	java.lang.Integer ordre;
	int tipus;
	java.lang.String propertiesAdmin;
	java.lang.String propertiesEntitat;
	int politicadeus;
	java.lang.String entitatID;
	boolean actiu;
	int politicaMostrarPropietats;


  /** Constructor Buit */
  public PluginBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginBean(long pluginID , java.lang.String codi , long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.Integer ordre , int tipus , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , int politicadeus , java.lang.String entitatID , boolean actiu , int politicaMostrarPropietats) {
    this.pluginID=pluginID;
    this.codi=codi;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.ordre=ordre;
    this.tipus=tipus;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.politicadeus=politicadeus;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.politicaMostrarPropietats=politicaMostrarPropietats;
}
  /** Constructor sense valors autoincrementals */
  public PluginBean(java.lang.String codi , long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.Integer ordre , int tipus , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , int politicadeus , java.lang.String entitatID , boolean actiu , int politicaMostrarPropietats) {
    this.codi=codi;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.ordre=ordre;
    this.tipus=tipus;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.politicadeus=politicadeus;
    this.entitatID=entitatID;
    this.actiu=actiu;
    this.politicaMostrarPropietats=politicaMostrarPropietats;
}
  /** Constructor dels valors Not Null */
  public PluginBean(long pluginID , java.lang.String codi , long nomID , long descripcioCurtaID , java.lang.String classe , int tipus , int politicadeus , boolean actiu , int politicaMostrarPropietats) {
    this.pluginID=pluginID;
    this.codi=codi;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.tipus=tipus;
    this.politicadeus=politicadeus;
    this.actiu=actiu;
    this.politicaMostrarPropietats=politicaMostrarPropietats;
}
  public PluginBean(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setCodi(__bean.getCodi());
    this.setNomID(__bean.getNomID());
    this.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    this.setClasse(__bean.getClasse());
    this.setOrdre(__bean.getOrdre());
    this.setTipus(__bean.getTipus());
    this.setPropertiesAdmin(__bean.getPropertiesAdmin());
    this.setPropertiesEntitat(__bean.getPropertiesEntitat());
    this.setPoliticadeus(__bean.getPoliticadeus());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
    this.setPoliticaMostrarPropietats(__bean.getPoliticaMostrarPropietats());
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
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

	public java.lang.Integer getOrdre() {
		return(ordre);
	};
	public void setOrdre(java.lang.Integer _ordre_) {
		this.ordre = _ordre_;
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

	public int getPoliticadeus() {
		return(politicadeus);
	};
	public void setPoliticadeus(int _politicadeus_) {
		this.politicadeus = _politicadeus_;
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

	public int getPoliticaMostrarPropietats() {
		return(politicaMostrarPropietats);
	};
	public void setPoliticaMostrarPropietats(int _politicaMostrarPropietats_) {
		this.politicaMostrarPropietats = _politicaMostrarPropietats_;
	};



  // ======================================

  public static PluginBean toBean(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginBean __tmp = new PluginBean();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setPropertiesAdmin(__bean.getPropertiesAdmin());
    __tmp.setPropertiesEntitat(__bean.getPropertiesEntitat());
    __tmp.setPoliticadeus(__bean.getPoliticadeus());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setPoliticaMostrarPropietats(__bean.getPoliticaMostrarPropietats());
		return __tmp;
	}



}
