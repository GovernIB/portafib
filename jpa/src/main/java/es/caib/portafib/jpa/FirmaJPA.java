
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.ForeignKey;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_firma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class FirmaJPA implements Firma {



private static final long serialVersionUID = -491371752L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_firma_pk_i")
	@Column(name="firmaid",nullable = false,length = 19)
	long firmaID;

  /** Si val null significa que s'ha de substituir pel Sol·licitant de la petició (només podrà valer null en plantilles de flux de firmes) */
	@Index(name="pfi_firma_destinatariid_fk_i")
	@Column(name="destinatariid",nullable = false,length = 101)
	java.lang.String destinatariID;

	@Index(name="pfi_firma_blocdefirmaid_fk_i")
	@Column(name="blocdefirmaid",nullable = false,length = 19)
	long blocDeFirmaID;

	@Column(name="obligatori",nullable = false,length = 1)
	boolean obligatori;

	@Index(name="pfi_firma_fitxerfirmatid_fk_i")
	@Column(name="fitxerfirmatid",length = 19)
	java.lang.Long fitxerFirmatID;

	@Column(name="numfirmadocument",length = 10)
	java.lang.Integer numFirmaDocument;

  /** Indica la pàgina on s'imprimirà la firma. Si aquest valor és -1 indica que la firma anira dins d'una caixa de firmes */
	@Column(name="caixa_pagina",nullable = false,length = 10)
	int caixaPagina;

	@Column(name="caixa_x",length = 10)
	java.lang.Integer caixaX;

	@Column(name="caixa_y",length = 10)
	java.lang.Integer caixaY;

	@Column(name="caixa_ample",length = 10)
	java.lang.Integer caixaAmple;

	@Column(name="caixa_alt",length = 10)
	java.lang.Integer caixaAlt;

	@Column(name="numeroseriecertificat",length = 131089)
	java.math.BigInteger numeroSerieCertificat;

	@Column(name="emissorcertificat",length = 1000)
	java.lang.String emissorCertificat;

	@Column(name="nomcertificat",length = 1000)
	java.lang.String nomCertificat;

  /** Només permetem valors null, firmat, rebutjat o descartat */
	@Index(name="pfi_firma_estatfirmaid_fk_i")
	@Column(name="tipusestatdefirmafinalid",length = 19)
	java.lang.Long tipusEstatDeFirmaFinalID;

  /** Dibuixar la firma(rubrica) de l'usuari persona si s'ha definit la caixa */
	@Column(name="mostrarrubrica",nullable = false,length = 1)
	boolean mostrarRubrica;



  /** Constructor Buit */
  public FirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public FirmaJPA(long firmaID , java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , java.lang.Long fitxerFirmatID , java.lang.Integer numFirmaDocument , int caixaPagina , java.lang.Integer caixaX , java.lang.Integer caixaY , java.lang.Integer caixaAmple , java.lang.Integer caixaAlt , java.math.BigInteger numeroSerieCertificat , java.lang.String emissorCertificat , java.lang.String nomCertificat , java.lang.Long tipusEstatDeFirmaFinalID , boolean mostrarRubrica) {
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
  public FirmaJPA(java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , java.lang.Long fitxerFirmatID , java.lang.Integer numFirmaDocument , int caixaPagina , java.lang.Integer caixaX , java.lang.Integer caixaY , java.lang.Integer caixaAmple , java.lang.Integer caixaAlt , java.math.BigInteger numeroSerieCertificat , java.lang.String emissorCertificat , java.lang.String nomCertificat , java.lang.Long tipusEstatDeFirmaFinalID , boolean mostrarRubrica) {
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
  public FirmaJPA(long firmaID , java.lang.String destinatariID , long blocDeFirmaID , boolean obligatori , int caixaPagina , boolean mostrarRubrica) {
    this.firmaID=firmaID;
    this.destinatariID=destinatariID;
    this.blocDeFirmaID=blocDeFirmaID;
    this.obligatori=obligatori;
    this.caixaPagina=caixaPagina;
    this.mostrarRubrica=mostrarRubrica;
}
  public FirmaJPA(Firma __bean) {
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
    this.setFitxerFirmat(FitxerJPA.toJPA(__bean.getFitxerFirmat()));
	}

  public static FirmaJPA toJPA(Firma __bean) {
    if (__bean == null) { return null;}
    FirmaJPA __tmp = new FirmaJPA();
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
    __tmp.setFitxerFirmat(FitxerJPA.toJPA(__bean.getFitxerFirmat()));
		return __tmp;
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Firma) {
      Firma __instance = (Firma)__obj;
      __result = true;
      __result = __result && (this.getFirmaID() == __instance.getFirmaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:firmaid | Table: pfi_annexfirmat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "firma")
	private Set<AnnexFirmatJPA> annexFirmats = new HashSet<AnnexFirmatJPA>(0);
	public  Set<AnnexFirmatJPA> getAnnexFirmats() {
    return this.annexFirmats;
  }

	public void setAnnexFirmats(Set<AnnexFirmatJPA> annexFirmats) {
	  this.annexFirmats = annexFirmats;
	}


// EXP  Field:firmaid | Table: pfi_estatdefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "firma")
	private Set<EstatDeFirmaJPA> estatDeFirmas = new HashSet<EstatDeFirmaJPA>(0);
	public  Set<EstatDeFirmaJPA> getEstatDeFirmas() {
    return this.estatDeFirmas;
  }

	public void setEstatDeFirmas(Set<EstatDeFirmaJPA> estatDeFirmas) {
	  this.estatDeFirmas = estatDeFirmas;
	}


// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_firma_usrentitat_fk")
	@JoinColumn(name = "destinatariid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }

// IMP Field:blocdefirmesid | Table: pfi_blocdefirmes | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_firma_blocfirmes_fk")
	@JoinColumn(name = "blocdefirmaid", referencedColumnName ="blocDeFirmesID", nullable = false, insertable=false, updatable=false)
	private BlocDeFirmesJPA blocDeFirmes;

	public BlocDeFirmesJPA getBlocDeFirmes() {
    return this.blocDeFirmes;
  }

	public  void setBlocDeFirmes(BlocDeFirmesJPA blocDeFirmes) {
    this.blocDeFirmes = blocDeFirmes;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_firma_fitxer_fk")
	@JoinColumn(name = "fitxerfirmatid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA fitxerFirmat;

	public FitxerJPA getFitxerFirmat() {
    return this.fitxerFirmat;
  }

	public  void setFitxerFirmat(FitxerJPA fitxerFirmat) {
    this.fitxerFirmat = fitxerFirmat;
  }

// IMP Field:tipusestatdefirmafinalid | Table: pfi_tipusestatdefirmafinal | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_firma_estfirmafi_fk")
	@JoinColumn(name = "tipusestatdefirmafinalid", referencedColumnName ="tipusEstatDeFirmaFinalID", nullable = true, insertable=false, updatable=false)
	private TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal;

	public TipusEstatDeFirmaFinalJPA getTipusEstatDeFirmaFinal() {
    return this.tipusEstatDeFirmaFinal;
  }

	public  void setTipusEstatDeFirmaFinal(TipusEstatDeFirmaFinalJPA tipusEstatDeFirmaFinal) {
    this.tipusEstatDeFirmaFinal = tipusEstatDeFirmaFinal;
  }



}
