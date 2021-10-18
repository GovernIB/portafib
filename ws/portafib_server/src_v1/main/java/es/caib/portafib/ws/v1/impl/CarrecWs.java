package es.caib.portafib.ws.v1.impl;

import es.caib.portafib.persistence.UsuariEntitatJPA;
import es.caib.portafib.model.entity.UsuariEntitat;

/**
 * 
 * @author anadal
 * 
 */
public class CarrecWs {

  private String usuariPersonaID;

  private String entitatID;

  private String carrecID;

  private String carrecName;

  boolean actiu;

  /**
   * 
   */
  public CarrecWs() {
    super();
  }


  public String getUsuariPersonaID() {
    return usuariPersonaID;
  }

  public void setUsuariPersonaID(String usuariPersonaID) {
    this.usuariPersonaID = usuariPersonaID;
  }

  public String getEntitatID() {
    return entitatID;
  }

  public void setEntitatID(String entitatID) {
    this.entitatID = entitatID;
  }

  public String getCarrecID() {
    return carrecID;
  }

  public void setCarrecID(String carrecID) {
    this.carrecID = carrecID;
  }

  public String getCarrecName() {
    return carrecName;
  }

  public void setCarrecName(String carrecName) {
    this.carrecName = carrecName;
  }

  public boolean isActiu() {
    return actiu;
  }

  public void setActiu(boolean actiu) {
    this.actiu = actiu;
  }

  public static CarrecWs toCarrecWs(UsuariEntitatBean __bean) {
    if (__bean == null) {
      return null;
    }
    CarrecWs carrec = new CarrecWs();
    carrec.setCarrecID(__bean.getUsuariEntitatID());
    carrec.setCarrecName(__bean.getCarrec());
    carrec.setUsuariPersonaID(__bean.getUsuariPersonaID());
    carrec.setEntitatID(__bean.getEntitatID());
    carrec.setActiu(__bean.isActiu());
    return carrec;
  }
  
  public static CarrecWs toCarrecWs(UsuariEntitat __bean) {
    if (__bean == null) {
      return null;
    }
    CarrecWs carrec = new CarrecWs();
    carrec.setCarrecID(__bean.getUsuariEntitatID());
    carrec.setCarrecName(__bean.getCarrec());
    carrec.setUsuariPersonaID(__bean.getUsuariPersonaID());
    carrec.setEntitatID(__bean.getEntitatID());
    carrec.setActiu(__bean.isActiu());
    return carrec;
  }
  
  
  public static UsuariEntitatJPA toUsuariEntitatJPA(CarrecWs carrec) {
    if (carrec == null) {
      return null;
    }
    UsuariEntitatJPA jpa = new UsuariEntitatJPA();
    jpa.setUsuariEntitatID(carrec.getCarrecID());
    jpa.setCarrec(carrec.getCarrecName());
    jpa.setUsuariPersonaID(carrec.getUsuariPersonaID());
    jpa.setEntitatID(carrec.getEntitatID());
    jpa.setActiu(carrec.isActiu());
    
    jpa.setEmail(null);
    jpa.setLogoSegellID(null);
    jpa.setLogoSegell(null);
    jpa.setPredeterminat(false);
    jpa.setRebreTotsElsAvisos(false);
    
    return jpa;
  }
  
  
  
}
