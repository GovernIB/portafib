
package es.caib.portafib.ws.v1.impl;

import es.caib.portafib.model.entity.Firma;

/**
 * IMPORTANT: Aquesta Classe no es pot modificar ja que forma part
 * de l'API V1.0. Només es pot modificar el mètode toBean() 
 * i el constructor a partir del Objecte del Mòdel
 * 
 * @author anadal
 *
 */
public class FirmaBean {

	long firmaID;// PK
	java.lang.String destinatariID;
	long blocDeFirmaID;
	boolean obligatori;
	java.lang.Long fitxerFirmatID;
	java.lang.Integer numFirmaDocument;
	int caixaPagina;
	java.lang.Integer caixaX;
	java.lang.Integer caixaY;
	java.lang.Integer caixaAmple;
	java.lang.Integer caixaAlt;
	java.math.BigInteger numeroSerieCertificat;
	java.lang.String emissorCertificat;
	java.lang.String nomCertificat;
	java.lang.Long tipusEstatDeFirmaFinalID;
	boolean mostrarRubrica;


  /** Constructor Buit */
  public FirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public FirmaBean(long firmaID , java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , java.lang.Long fitxerFirmatID , java.lang.Integer numFirmaDocument , int caixaPagina , java.lang.Integer caixaX , java.lang.Integer caixaY , java.lang.Integer caixaAmple , java.lang.Integer caixaAlt , java.math.BigInteger numeroSerieCertificat , java.lang.String emissorCertificat , java.lang.String nomCertificat , java.lang.Long tipusEstatDeFirmaFinalID , boolean mostrarRubrica) {
    this.firmaID=firmaID;
    this.destinatariID=destinatariID;
    this.blocDeFirmaID=blocDeFirmaID;
    this.obligatori=obligatori;
    this.fitxerFirmatID=fitxerFirmatID;
    this.numFirmaDocument=numFirmaDocument;
    this.caixaPagina=caixaPagina;
    this.caixaX=caixaX;
    this.caixaY=caixaY;
    this.caixaAmple=caixaAmple;
    this.caixaAlt=caixaAlt;
    this.numeroSerieCertificat=numeroSerieCertificat;
    this.emissorCertificat=emissorCertificat;
    this.nomCertificat=nomCertificat;
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.mostrarRubrica=mostrarRubrica;
}
  /** Constructor sense valors autoincrementals */
  public FirmaBean(java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , java.lang.Long fitxerFirmatID , java.lang.Integer numFirmaDocument , int caixaPagina , java.lang.Integer caixaX , java.lang.Integer caixaY , java.lang.Integer caixaAmple , java.lang.Integer caixaAlt , java.math.BigInteger numeroSerieCertificat , java.lang.String emissorCertificat , java.lang.String nomCertificat , java.lang.Long tipusEstatDeFirmaFinalID , boolean mostrarRubrica) {
    this.destinatariID=destinatariID;
    this.blocDeFirmaID=blocDeFirmaID;
    this.obligatori=obligatori;
    this.fitxerFirmatID=fitxerFirmatID;
    this.numFirmaDocument=numFirmaDocument;
    this.caixaPagina=caixaPagina;
    this.caixaX=caixaX;
    this.caixaY=caixaY;
    this.caixaAmple=caixaAmple;
    this.caixaAlt=caixaAlt;
    this.numeroSerieCertificat=numeroSerieCertificat;
    this.emissorCertificat=emissorCertificat;
    this.nomCertificat=nomCertificat;
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.mostrarRubrica=mostrarRubrica;
}
  /** Constructor dels valors Not Null */
  public FirmaBean(long firmaID , java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , int caixaPagina , boolean mostrarRubrica) {
    this.firmaID=firmaID;
    this.destinatariID=destinatariID;
    this.blocDeFirmaID=blocDeFirmaID;
    this.obligatori=obligatori;
    this.caixaPagina=caixaPagina;
    this.mostrarRubrica=mostrarRubrica;
}
  public FirmaBean(Firma __bean) {
    this.setFirmaID(__bean.getFirmaID());
    this.setDestinatariID(__bean.getDestinatariID());
    this.setBlocDeFirmaID(__bean.getBlocDeFirmaID());
    this.setObligatori(__bean.isObligatori());
    this.setFitxerFirmatID(__bean.getFitxerFirmatID());
    this.setNumFirmaDocument(__bean.getNumFirmaDocument());
    this.setCaixaPagina(__bean.getCaixaPagina());
    this.setCaixaX(__bean.getCaixaX());
    this.setCaixaY(__bean.getCaixaY());
    this.setCaixaAmple(__bean.getCaixaAmple());
    this.setCaixaAlt(__bean.getCaixaAlt());
    this.setNumeroSerieCertificat(__bean.getNumeroSerieCertificat());
    this.setEmissorCertificat(__bean.getEmissorCertificat());
    this.setNomCertificat(__bean.getNomCertificat());
    this.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    this.setMostrarRubrica(__bean.isMostrarRubrica());
    // Fitxer
    this.setFitxerFirmat(FitxerBean.toBean(__bean.getFitxerFirmat()));
	}

	public long getFirmaID() {
		return(firmaID);
	};
	public void setFirmaID(long _firmaID_) {
		this.firmaID = _firmaID_;
	};

	public java.lang.String getDestinatariID() {
		return(destinatariID);
	};
	public void setDestinatariID(java.lang.String _destinatariID_) {
		this.destinatariID = _destinatariID_;
	};

	public long getBlocDeFirmaID() {
		return(blocDeFirmaID);
	};
	public void setBlocDeFirmaID(long _blocDeFirmaID_) {
		this.blocDeFirmaID = _blocDeFirmaID_;
	};

	public boolean isObligatori() {
		return(obligatori);
	};
	public void setObligatori(boolean _obligatori_) {
		this.obligatori = _obligatori_;
	};

	public java.lang.Long getFitxerFirmatID() {
		return(fitxerFirmatID);
	};
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_) {
		this.fitxerFirmatID = _fitxerFirmatID_;
	};

	public java.lang.Integer getNumFirmaDocument() {
		return(numFirmaDocument);
	};
	public void setNumFirmaDocument(java.lang.Integer _numFirmaDocument_) {
		this.numFirmaDocument = _numFirmaDocument_;
	};

	public int getCaixaPagina() {
		return(caixaPagina);
	};
	public void setCaixaPagina(int _caixaPagina_) {
		this.caixaPagina = _caixaPagina_;
	};

	public java.lang.Integer getCaixaX() {
		return(caixaX);
	};
	public void setCaixaX(java.lang.Integer _caixaX_) {
		this.caixaX = _caixaX_;
	};

	public java.lang.Integer getCaixaY() {
		return(caixaY);
	};
	public void setCaixaY(java.lang.Integer _caixaY_) {
		this.caixaY = _caixaY_;
	};

	public java.lang.Integer getCaixaAmple() {
		return(caixaAmple);
	};
	public void setCaixaAmple(java.lang.Integer _caixaAmple_) {
		this.caixaAmple = _caixaAmple_;
	};

	public java.lang.Integer getCaixaAlt() {
		return(caixaAlt);
	};
	public void setCaixaAlt(java.lang.Integer _caixaAlt_) {
		this.caixaAlt = _caixaAlt_;
	};

	public java.math.BigInteger getNumeroSerieCertificat() {
		return(numeroSerieCertificat);
	};
	public void setNumeroSerieCertificat(java.math.BigInteger _numeroSerieCertificat_) {
		this.numeroSerieCertificat = _numeroSerieCertificat_;
	};

	public java.lang.String getEmissorCertificat() {
		return(emissorCertificat);
	};
	public void setEmissorCertificat(java.lang.String _emissorCertificat_) {
		this.emissorCertificat = _emissorCertificat_;
	};

	public java.lang.String getNomCertificat() {
		return(nomCertificat);
	};
	public void setNomCertificat(java.lang.String _nomCertificat_) {
		this.nomCertificat = _nomCertificat_;
	};

	public java.lang.Long getTipusEstatDeFirmaFinalID() {
		return(tipusEstatDeFirmaFinalID);
	};
	public void setTipusEstatDeFirmaFinalID(java.lang.Long _tipusEstatDeFirmaFinalID_) {
		this.tipusEstatDeFirmaFinalID = _tipusEstatDeFirmaFinalID_;
	};

	public boolean isMostrarRubrica() {
		return(mostrarRubrica);
	};
	public void setMostrarRubrica(boolean _mostrarRubrica_) {
		this.mostrarRubrica = _mostrarRubrica_;
	};



  // ======================================

  public static FirmaBean toBean(Firma __bean) {
    if (__bean == null) { return null;}
    FirmaBean __tmp = new FirmaBean();
    __tmp.setFirmaID(__bean.getFirmaID());
    __tmp.setDestinatariID(__bean.getDestinatariID());
    __tmp.setBlocDeFirmaID(__bean.getBlocDeFirmaID());
    __tmp.setObligatori(__bean.isObligatori());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    __tmp.setNumFirmaDocument(__bean.getNumFirmaDocument());
    __tmp.setCaixaPagina(__bean.getCaixaPagina());
    __tmp.setCaixaX(__bean.getCaixaX());
    __tmp.setCaixaY(__bean.getCaixaY());
    __tmp.setCaixaAmple(__bean.getCaixaAmple());
    __tmp.setCaixaAlt(__bean.getCaixaAlt());
    __tmp.setNumeroSerieCertificat(__bean.getNumeroSerieCertificat());
    __tmp.setEmissorCertificat(__bean.getEmissorCertificat());
    __tmp.setNomCertificat(__bean.getNomCertificat());
    __tmp.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    __tmp.setMostrarRubrica(__bean.isMostrarRubrica());
    // Fitxer
    __tmp.setFitxerFirmat(FitxerBean.toBean(__bean.getFitxerFirmat()));
		return __tmp;
	}

  protected FitxerBean fitxerFirmat;
  public FitxerBean getFitxerFirmat() {
    return fitxerFirmat;
  }
  public void setFitxerFirmat(FitxerBean __field) {
    this. fitxerFirmat = __field;
  }


}
