
package es.caib.portafib.ws.v1.impl;

import es.caib.portafib.model.entity.Annex;

/**
 * IMPORTANT: Aquesta Classe no es pot modificar ja que forma part
 * de l'API V1.0. Només es pot modificar el mètode toBean() 
 * i el constructor a partir del Objecte del Mòdel
 * 
 * @author anadal
 *
 */
public class AnnexBean {

	long annexID;// PK
	long peticioDeFirmaID;
	long fitxerID;
	boolean adjuntar;
	boolean firmar;


  /** Constructor Buit */
  public AnnexBean() {
  }

  /** Constructor amb tots els camps  */
  public AnnexBean(long annexID , long peticioDeFirmaID , long fitxerID , boolean adjuntar , boolean firmar) {
    this.annexID=annexID;
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.fitxerID=fitxerID;
    this.adjuntar=adjuntar;
    this.firmar=firmar;
}
  /** Constructor sense valors autoincrementals */
  public AnnexBean(long peticioDeFirmaID , long fitxerID , boolean adjuntar , boolean firmar) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.fitxerID=fitxerID;
    this.adjuntar=adjuntar;
    this.firmar=firmar;
}
  public AnnexBean(Annex __bean) {
    this.setAnnexID(__bean.getAnnexID());
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setFitxerID(__bean.getFitxerID());
    this.setAdjuntar(__bean.isAdjuntar());
    this.setFirmar(__bean.isFirmar());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
	}

	public long getAnnexID() {
		return(annexID);
	};
	public void setAnnexID(long _annexID_) {
		this.annexID = _annexID_;
	};

	public long getPeticioDeFirmaID() {
		return(peticioDeFirmaID);
	};
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_) {
		this.peticioDeFirmaID = _peticioDeFirmaID_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public boolean isAdjuntar() {
		return(adjuntar);
	};
	public void setAdjuntar(boolean _adjuntar_) {
		this.adjuntar = _adjuntar_;
	};

	public boolean isFirmar() {
		return(firmar);
	};
	public void setFirmar(boolean _firmar_) {
		this.firmar = _firmar_;
	};



  // ======================================

  public static AnnexBean toBean(Annex __bean) {
    if (__bean == null) { return null;}
    AnnexBean __tmp = new AnnexBean();
    __tmp.setAnnexID(__bean.getAnnexID());
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setAdjuntar(__bean.isAdjuntar());
    __tmp.setFirmar(__bean.isFirmar());
    // Fitxer
    __tmp.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
		return __tmp;
	}

  protected FitxerBean fitxer;
  public FitxerBean getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerBean __field) {
    this. fitxer = __field;
  }


}
