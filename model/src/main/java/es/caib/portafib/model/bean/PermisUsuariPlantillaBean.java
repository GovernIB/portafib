
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PermisUsuariPlantilla;


public class PermisUsuariPlantillaBean implements PermisUsuariPlantilla {



	long permisUsuariPlantillaID;// PK
	java.lang.String usuariEntitatID;
	long plantillaFluxDeFirmesID;


  /** Constructor Buit */
  public PermisUsuariPlantillaBean() {
  }

  /** Constructor amb tots els camps  */
  public PermisUsuariPlantillaBean(long permisUsuariPlantillaID , java.lang.String usuariEntitatID , long plantillaFluxDeFirmesID) {
    this.permisUsuariPlantillaID=permisUsuariPlantillaID;
    this.usuariEntitatID=usuariEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  /** Constructor sense valors autoincrementals */
  public PermisUsuariPlantillaBean(java.lang.String usuariEntitatID , long plantillaFluxDeFirmesID) {
    this.usuariEntitatID=usuariEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  public PermisUsuariPlantillaBean(PermisUsuariPlantilla __bean) {
    this.setPermisUsuariPlantillaID(__bean.getPermisUsuariPlantillaID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
	}

	public long getPermisUsuariPlantillaID() {
		return(permisUsuariPlantillaID);
	};
	public void setPermisUsuariPlantillaID(long _permisUsuariPlantillaID_) {
		this.permisUsuariPlantillaID = _permisUsuariPlantillaID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public long getPlantillaFluxDeFirmesID() {
		return(plantillaFluxDeFirmesID);
	};
	public void setPlantillaFluxDeFirmesID(long _plantillaFluxDeFirmesID_) {
		this.plantillaFluxDeFirmesID = _plantillaFluxDeFirmesID_;
	};



  // ======================================

  public static PermisUsuariPlantillaBean toBean(PermisUsuariPlantilla __bean) {
    if (__bean == null) { return null;}
    PermisUsuariPlantillaBean __tmp = new PermisUsuariPlantillaBean();
    __tmp.setPermisUsuariPlantillaID(__bean.getPermisUsuariPlantillaID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
		return __tmp;
	}



}
