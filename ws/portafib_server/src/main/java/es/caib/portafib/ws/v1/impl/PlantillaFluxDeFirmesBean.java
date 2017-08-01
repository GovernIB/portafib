
package es.caib.portafib.ws.v1.impl;

import es.caib.portafib.model.entity.PlantillaFluxDeFirmes;

/**
 * IMPORTANT: Aquesta Classe no es pot modificar ja que forma part
 * de l'API V1.0. Només es pot modificar el mètode toBean() 
 * i el constructor a partir del Objecte del Mòdel
 * 
 * @author anadal
 *
 */
//TODO XYZ ZZZ Aquest Bean No s'utilitza !!!!!!!
public class PlantillaFluxDeFirmesBean {


	long fluxDeFirmesID;// PK
	java.lang.String descripcio;
	java.lang.String usuariEntitatID;
	java.lang.String usuariAplicacioID;
	java.lang.Boolean compartir;


  /** Constructor Buit */
  public PlantillaFluxDeFirmesBean() {
  }

  /** Constructor amb tots els camps  */
  public PlantillaFluxDeFirmesBean(long fluxDeFirmesID , java.lang.String descripcio , java.lang.String usuariEntitatID , java.lang.String usuariAplicacioID , java.lang.Boolean compartir) {
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.descripcio=descripcio;
    this.usuariEntitatID=usuariEntitatID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.compartir=compartir;
}
  /** Constructor dels valors Not Null */
  public PlantillaFluxDeFirmesBean(long fluxDeFirmesID , java.lang.String descripcio) {
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.descripcio=descripcio;
}
  public PlantillaFluxDeFirmesBean(PlantillaFluxDeFirmes __bean) {
    this.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    this.setDescripcio(__bean.getDescripcio());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setCompartir(__bean.getCompartir());
	}

	public long getFluxDeFirmesID() {
		return(fluxDeFirmesID);
	};
	public void setFluxDeFirmesID(long _fluxDeFirmesID_) {
		this.fluxDeFirmesID = _fluxDeFirmesID_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.Boolean getCompartir() {
		return(compartir);
	};
	public void setCompartir(java.lang.Boolean _compartir_) {
		this.compartir = _compartir_;
	};



  // ======================================

  public static PlantillaFluxDeFirmesBean toBean(PlantillaFluxDeFirmes __bean) {
    if (__bean == null) { return null;}
    PlantillaFluxDeFirmesBean __tmp = new PlantillaFluxDeFirmesBean();
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setCompartir(__bean.getCompartir());
		return __tmp;
	}



}
