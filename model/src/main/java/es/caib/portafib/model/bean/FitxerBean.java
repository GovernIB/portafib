
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Fitxer;


public class FitxerBean implements Fitxer {



private static final long serialVersionUID = 752486244L;

	long fitxerID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	long tamany;
	java.lang.String mime;


  /** Constructor Buit */
  public FitxerBean() {
  }

  /** Constructor amb tots els camps  */
  public FitxerBean(long fitxerID , java.lang.String nom , java.lang.String descripcio , long tamany , java.lang.String mime) {
    this.fitxerID=fitxerID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.tamany=tamany;
    this.mime=mime;
}
  /** Constructor sense valors autoincrementals */
  public FitxerBean(java.lang.String nom , java.lang.String descripcio , long tamany , java.lang.String mime) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.tamany=tamany;
    this.mime=mime;
}
  public FitxerBean(Fitxer __bean) {
    this.setFitxerID(__bean.getFitxerID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setTamany(__bean.getTamany());
    this.setMime(__bean.getMime());
    // DataHandler
    this.setData(__bean.getData());
    // EncryptedFileID
    this.setEncryptedFileID(__bean.getEncryptedFileID());
	}

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public long getTamany() {
		return(tamany);
	};
	public void setTamany(long _tamany_) {
		this.tamany = _tamany_;
	};

	public java.lang.String getMime() {
		return(mime);
	};
	public void setMime(java.lang.String _mime_) {
		this.mime = _mime_;
	};



  // ======================================

  public static FitxerBean toBean(Fitxer __bean) {
    if (__bean == null) { return null;}
    FitxerBean __tmp = new FitxerBean();
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setTamany(__bean.getTamany());
    __tmp.setMime(__bean.getMime());
    // DataHandler
    __tmp.setData(__bean.getData());
    // EncryptedFileID
    __tmp.setEncryptedFileID(__bean.getEncryptedFileID());
		return __tmp;
	}



  javax.activation.DataHandler data;

  public javax.activation.DataHandler getData() {
    return data;
  }

  public void setData(javax.activation.DataHandler data) {
    this.data = data;
  }


  String encryptedFileID;

  public String getEncryptedFileID() {
    return encryptedFileID;
  }

  public void setEncryptedFileID(String encryptedFileID) {
    this.encryptedFileID = encryptedFileID;
  }



}
