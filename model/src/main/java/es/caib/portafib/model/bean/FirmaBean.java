
package es.caib.portafib.model.bean;

import es.caib.portafib.model.entity.Firma;


public class FirmaBean implements Firma {



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
	java.lang.String motiu;
	int minimDeRevisors;
	java.lang.Boolean checkAdministrationIdOfSigner;
	java.lang.Boolean checkDocumentModifications;
	java.lang.Boolean checkValidationSignature;
	java.lang.String perfilDeFirma;
	java.lang.String usuariExternNom;
	java.lang.String usuariExternLlinatges;
	java.lang.String usuariExternEmail;
	java.lang.String usuariExternIdioma;
	java.lang.String usuariExternToken;
	java.lang.Integer usuariExternNivellSeguretat;


  /** Constructor Buit */
  public FirmaBean() {
  }

  /** Constructor amb tots els camps  */
  public FirmaBean(long firmaID , java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , java.lang.Long fitxerFirmatID , java.lang.Integer numFirmaDocument , int caixaPagina , java.lang.Integer caixaX , java.lang.Integer caixaY , java.lang.Integer caixaAmple , java.lang.Integer caixaAlt , java.math.BigInteger numeroSerieCertificat , java.lang.String emissorCertificat , java.lang.String nomCertificat , java.lang.Long tipusEstatDeFirmaFinalID , boolean mostrarRubrica , java.lang.String motiu , int minimDeRevisors , java.lang.Boolean checkAdministrationIdOfSigner , java.lang.Boolean checkDocumentModifications , java.lang.Boolean checkValidationSignature , java.lang.String perfilDeFirma , java.lang.String usuariExternNom , java.lang.String usuariExternLlinatges , java.lang.String usuariExternEmail , java.lang.String usuariExternIdioma , java.lang.String usuariExternToken , java.lang.Integer usuariExternNivellSeguretat) {
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
    this.motiu=motiu;
    this.minimDeRevisors=minimDeRevisors;
    this.checkAdministrationIdOfSigner=checkAdministrationIdOfSigner;
    this.checkDocumentModifications=checkDocumentModifications;
    this.checkValidationSignature=checkValidationSignature;
    this.perfilDeFirma=perfilDeFirma;
    this.usuariExternNom=usuariExternNom;
    this.usuariExternLlinatges=usuariExternLlinatges;
    this.usuariExternEmail=usuariExternEmail;
    this.usuariExternIdioma=usuariExternIdioma;
    this.usuariExternToken=usuariExternToken;
    this.usuariExternNivellSeguretat=usuariExternNivellSeguretat;
}
  /** Constructor sense valors autoincrementals */
  public FirmaBean(java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , java.lang.Long fitxerFirmatID , java.lang.Integer numFirmaDocument , int caixaPagina , java.lang.Integer caixaX , java.lang.Integer caixaY , java.lang.Integer caixaAmple , java.lang.Integer caixaAlt , java.math.BigInteger numeroSerieCertificat , java.lang.String emissorCertificat , java.lang.String nomCertificat , java.lang.Long tipusEstatDeFirmaFinalID , boolean mostrarRubrica , java.lang.String motiu , int minimDeRevisors , java.lang.Boolean checkAdministrationIdOfSigner , java.lang.Boolean checkDocumentModifications , java.lang.Boolean checkValidationSignature , java.lang.String perfilDeFirma , java.lang.String usuariExternNom , java.lang.String usuariExternLlinatges , java.lang.String usuariExternEmail , java.lang.String usuariExternIdioma , java.lang.String usuariExternToken , java.lang.Integer usuariExternNivellSeguretat) {
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
    this.motiu=motiu;
    this.minimDeRevisors=minimDeRevisors;
    this.checkAdministrationIdOfSigner=checkAdministrationIdOfSigner;
    this.checkDocumentModifications=checkDocumentModifications;
    this.checkValidationSignature=checkValidationSignature;
    this.perfilDeFirma=perfilDeFirma;
    this.usuariExternNom=usuariExternNom;
    this.usuariExternLlinatges=usuariExternLlinatges;
    this.usuariExternEmail=usuariExternEmail;
    this.usuariExternIdioma=usuariExternIdioma;
    this.usuariExternToken=usuariExternToken;
    this.usuariExternNivellSeguretat=usuariExternNivellSeguretat;
}
  /** Constructor dels valors Not Null */
  public FirmaBean(long firmaID , java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , int caixaPagina , boolean mostrarRubrica , int minimDeRevisors) {
    this.firmaID=firmaID;
    this.destinatariID=destinatariID;
    this.blocDeFirmaID=blocDeFirmaID;
    this.obligatori=obligatori;
    this.caixaPagina=caixaPagina;
    this.mostrarRubrica=mostrarRubrica;
    this.minimDeRevisors=minimDeRevisors;
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
    this.setMotiu(__bean.getMotiu());
    this.setMinimDeRevisors(__bean.getMinimDeRevisors());
    this.setCheckAdministrationIdOfSigner(__bean.getCheckAdministrationIdOfSigner());
    this.setCheckDocumentModifications(__bean.getCheckDocumentModifications());
    this.setCheckValidationSignature(__bean.getCheckValidationSignature());
    this.setPerfilDeFirma(__bean.getPerfilDeFirma());
    this.setUsuariExternNom(__bean.getUsuariExternNom());
    this.setUsuariExternLlinatges(__bean.getUsuariExternLlinatges());
    this.setUsuariExternEmail(__bean.getUsuariExternEmail());
    this.setUsuariExternIdioma(__bean.getUsuariExternIdioma());
    this.setUsuariExternToken(__bean.getUsuariExternToken());
    this.setUsuariExternNivellSeguretat(__bean.getUsuariExternNivellSeguretat());
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

	public java.lang.String getMotiu() {
		return(motiu);
	};
	public void setMotiu(java.lang.String _motiu_) {
		this.motiu = _motiu_;
	};

	public int getMinimDeRevisors() {
		return(minimDeRevisors);
	};
	public void setMinimDeRevisors(int _minimDeRevisors_) {
		this.minimDeRevisors = _minimDeRevisors_;
	};

	public java.lang.Boolean getCheckAdministrationIdOfSigner() {
		return(checkAdministrationIdOfSigner);
	};
	public void setCheckAdministrationIdOfSigner(java.lang.Boolean _checkAdministrationIdOfSigner_) {
		this.checkAdministrationIdOfSigner = _checkAdministrationIdOfSigner_;
	};

	public java.lang.Boolean getCheckDocumentModifications() {
		return(checkDocumentModifications);
	};
	public void setCheckDocumentModifications(java.lang.Boolean _checkDocumentModifications_) {
		this.checkDocumentModifications = _checkDocumentModifications_;
	};

	public java.lang.Boolean getCheckValidationSignature() {
		return(checkValidationSignature);
	};
	public void setCheckValidationSignature(java.lang.Boolean _checkValidationSignature_) {
		this.checkValidationSignature = _checkValidationSignature_;
	};

	public java.lang.String getPerfilDeFirma() {
		return(perfilDeFirma);
	};
	public void setPerfilDeFirma(java.lang.String _perfilDeFirma_) {
		this.perfilDeFirma = _perfilDeFirma_;
	};

	public java.lang.String getUsuariExternNom() {
		return(usuariExternNom);
	};
	public void setUsuariExternNom(java.lang.String _usuariExternNom_) {
		this.usuariExternNom = _usuariExternNom_;
	};

	public java.lang.String getUsuariExternLlinatges() {
		return(usuariExternLlinatges);
	};
	public void setUsuariExternLlinatges(java.lang.String _usuariExternLlinatges_) {
		this.usuariExternLlinatges = _usuariExternLlinatges_;
	};

	public java.lang.String getUsuariExternEmail() {
		return(usuariExternEmail);
	};
	public void setUsuariExternEmail(java.lang.String _usuariExternEmail_) {
		this.usuariExternEmail = _usuariExternEmail_;
	};

	public java.lang.String getUsuariExternIdioma() {
		return(usuariExternIdioma);
	};
	public void setUsuariExternIdioma(java.lang.String _usuariExternIdioma_) {
		this.usuariExternIdioma = _usuariExternIdioma_;
	};

	public java.lang.String getUsuariExternToken() {
		return(usuariExternToken);
	};
	public void setUsuariExternToken(java.lang.String _usuariExternToken_) {
		this.usuariExternToken = _usuariExternToken_;
	};

	public java.lang.Integer getUsuariExternNivellSeguretat() {
		return(usuariExternNivellSeguretat);
	};
	public void setUsuariExternNivellSeguretat(java.lang.Integer _usuariExternNivellSeguretat_) {
		this.usuariExternNivellSeguretat = _usuariExternNivellSeguretat_;
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
    __tmp.setMotiu(__bean.getMotiu());
    __tmp.setMinimDeRevisors(__bean.getMinimDeRevisors());
    __tmp.setCheckAdministrationIdOfSigner(__bean.getCheckAdministrationIdOfSigner());
    __tmp.setCheckDocumentModifications(__bean.getCheckDocumentModifications());
    __tmp.setCheckValidationSignature(__bean.getCheckValidationSignature());
    __tmp.setPerfilDeFirma(__bean.getPerfilDeFirma());
    __tmp.setUsuariExternNom(__bean.getUsuariExternNom());
    __tmp.setUsuariExternLlinatges(__bean.getUsuariExternLlinatges());
    __tmp.setUsuariExternEmail(__bean.getUsuariExternEmail());
    __tmp.setUsuariExternIdioma(__bean.getUsuariExternIdioma());
    __tmp.setUsuariExternToken(__bean.getUsuariExternToken());
    __tmp.setUsuariExternNivellSeguretat(__bean.getUsuariExternNivellSeguretat());
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
