
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.PermisGrupPlantilla;


public class PermisGrupPlantillaBean implements PermisGrupPlantilla {



	long permisGrupPlantillaID;// PK
	long grupEntitatID;
	long plantillaFluxDeFirmesID;


  /** Constructor Buit */
  public PermisGrupPlantillaBean() {
  }

  /** Constructor amb tots els camps  */
  public PermisGrupPlantillaBean(long permisGrupPlantillaID , long grupEntitatID , long plantillaFluxDeFirmesID) {
    this.permisGrupPlantillaID=permisGrupPlantillaID;
    this.grupEntitatID=grupEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  /** Constructor sense valors autoincrementals */
  public PermisGrupPlantillaBean(long grupEntitatID , long plantillaFluxDeFirmesID) {
    this.grupEntitatID=grupEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  public PermisGrupPlantillaBean(PermisGrupPlantilla __bean) {
    this.setPermisGrupPlantillaID(__bean.getPermisGrupPlantillaID());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
	}

	public long getPermisGrupPlantillaID() {
		return(permisGrupPlantillaID);
	};
	public void setPermisGrupPlantillaID(long _permisGrupPlantillaID_) {
		this.permisGrupPlantillaID = _permisGrupPlantillaID_;
	};

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
	};

	public long getPlantillaFluxDeFirmesID() {
		return(plantillaFluxDeFirmesID);
	};
	public void setPlantillaFluxDeFirmesID(long _plantillaFluxDeFirmesID_) {
		this.plantillaFluxDeFirmesID = _plantillaFluxDeFirmesID_;
	};



  // ======================================

  public static PermisGrupPlantillaBean toBean(PermisGrupPlantilla __bean) {
    if (__bean == null) { return null;}
    PermisGrupPlantillaBean __tmp = new PermisGrupPlantillaBean();
    __tmp.setPermisGrupPlantillaID(__bean.getPermisGrupPlantillaID());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
		return __tmp;
	}



}
