
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "pfi_peticiodefirma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class PeticioDeFirmaJPA implements PeticioDeFirma {



private static final long serialVersionUID = -940591816L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_peticiodefirma_pk_i")
	@Column(name="peticiodefirmaid",nullable = false,length = 19)
	long peticioDeFirmaID;

	@Column(name="titol",nullable = false,length = 255)
	java.lang.String titol;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;

	@Column(name="motiu",nullable = false,length = 255)
	java.lang.String motiu;

	@Index(name="pfi_petifirma_fitxerid_fk_i")
	@Column(name="fitxerafirmarid",length = 19)
	java.lang.Long fitxerAFirmarID;

	@Index(name="pfi_petifirma_fitxeadaid_fk_i")
	@Column(name="fitxeradaptatid",length = 19)
	java.lang.Long fitxerAdaptatID;

	@Index(name="pfi_petifirma_tipusdocid_fk_i")
	@Column(name="tipusdocumentid",nullable = false,length = 19)
	long tipusDocumentID;

	@Column(name="descripciotipusdocument",length = 255)
	java.lang.String descripcioTipusDocument;

  /** Indica la posicio de taula de firmes així com si es vol emprar o no */
	@Index(name="pfi_petifirma_postaulaid_fk_i")
	@Column(name="posiciotaulafirmesid",nullable = false,length = 19)
	int posicioTaulaFirmesID;

	@Column(name="datasolicitud",length = 29,precision = 6)
	java.sql.Timestamp dataSolicitud;

	@Column(name="datafinal",length = 29,precision = 6)
	java.sql.Timestamp dataFinal;

	@Column(name="datacaducitat",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataCaducitat;

	@Index(name="pfi_petifirma_tipofirmid_fk_i")
	@Column(name="tipusfirmaid",nullable = false,length = 19)
	int tipusFirmaID;

	@Index(name="pfi_petifirma_algofirmid_fk_i")
	@Column(name="algorismedefirmaid",nullable = false,length = 19)
	int algorismeDeFirmaID;

	@Column(name="modedefirma",nullable = false,length = 1)
	java.lang.Boolean modeDeFirma;

	@Index(name="pfi_petifirma_estatid_fk_i")
	@Column(name="tipusestatpeticiodefirmaid",nullable = false,length = 19)
	int tipusEstatPeticioDeFirmaID;

	@Column(name="motiuderebuig",length = 255)
	java.lang.String motiuDeRebuig;

	@Index(name="pfi_petifirma_idiomaid_fk_i")
	@Column(name="idiomaid",nullable = false,length = 5)
	java.lang.String idiomaID;

	@Index(name="pfi_petifirma_prioritatid_fk_i")
	@Column(name="prioritatid",nullable = false,length = 10)
	int prioritatID;

	@Index(name="pfi_petifirma_fluxid_fk_i")
	@Column(name="fluxdefirmesid",nullable = false,unique = true,length = 19)
	long fluxDeFirmesID;

	@Index(name="pfi_petifirma_usrappid_fk_i")
	@Column(name="usuariaplicacioid",nullable = false,length = 101)
	java.lang.String usuariAplicacioID;

  /** En peticions web, el nom de l'usuari solicitant. Via WS informació de la persona que en nom de l'aplicacio fa la peticio */
	@Column(name="remitentnom",nullable = false,length = 100)
	java.lang.String remitentNom;

  /** Normalemnt el correu de la persona descrita en el camp remitantNom */
	@Column(name="remitentdescripcio",length = 500)
	java.lang.String remitentDescripcio;

	@Column(name="informacioaddicional",length = 500)
	java.lang.String informacioAdicional;

	@Index(name="pfi_petifirma_logosegid_fk_i")
	@Column(name="logosegellid",length = 19)
	java.lang.Long logoSegellID;

	@Index(name="pfi_petifirma_custinfoid_fk_i")
	@Column(name="custodiainfoid",length = 19)
	java.lang.Long custodiaInfoID;

	@Index(name="pfi_petifirma_usrentiid_fk_i")
	@Column(name="usuarientitatid",length = 101)
	java.lang.String usuariEntitatID;

  /** Només per peticions d'usuaris-entitat. Aquest camp valdrà true quan la peticio acabi (firmada o rebutjada). 
Manualment l'usuari haurà d'indicar que ha vist la finalitzaio d'aquesta petició (llavors es posarà a false) */
	@Column(name="avisweb",nullable = false,length = 1)
	boolean avisWeb;

	@Column(name="segellatdetemps",nullable = false,length = 1)
	boolean segellatDeTemps;



  /** Constructor Buit */
  public PeticioDeFirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public PeticioDeFirmaJPA(long peticioDeFirmaID , java.lang.String titol , java.lang.String descripcio , java.lang.String motiu , java.lang.Long fitxerAFirmarID , java.lang.Long fitxerAdaptatID , long tipusDocumentID , java.lang.String descripcioTipusDocument , int posicioTaulaFirmesID , java.sql.Timestamp dataSolicitud , java.sql.Timestamp dataFinal , java.sql.Timestamp dataCaducitat , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int tipusEstatPeticioDeFirmaID , java.lang.String motiuDeRebuig , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String usuariAplicacioID , java.lang.String remitentNom , java.lang.String remitentDescripcio , java.lang.String informacioAdicional , java.lang.Long logoSegellID , java.lang.Long custodiaInfoID , java.lang.String usuariEntitatID , boolean avisWeb , boolean segellatDeTemps) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.motiu=motiu;
    this.fitxerAFirmarID=fitxerAFirmarID;
    this.fitxerAdaptatID=fitxerAdaptatID;
    this.tipusDocumentID=tipusDocumentID;
    this.descripcioTipusDocument=descripcioTipusDocument;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.dataSolicitud=dataSolicitud;
    this.dataFinal=dataFinal;
    this.dataCaducitat=dataCaducitat;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.motiuDeRebuig=motiuDeRebuig;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.remitentNom=remitentNom;
    this.remitentDescripcio=remitentDescripcio;
    this.informacioAdicional=informacioAdicional;
    this.logoSegellID=logoSegellID;
    this.custodiaInfoID=custodiaInfoID;
    this.usuariEntitatID=usuariEntitatID;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
}
  /** Constructor sense valors autoincrementals */
  public PeticioDeFirmaJPA(java.lang.String titol , java.lang.String descripcio , java.lang.String motiu , java.lang.Long fitxerAFirmarID , java.lang.Long fitxerAdaptatID , long tipusDocumentID , java.lang.String descripcioTipusDocument , int posicioTaulaFirmesID , java.sql.Timestamp dataSolicitud , java.sql.Timestamp dataFinal , java.sql.Timestamp dataCaducitat , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int tipusEstatPeticioDeFirmaID , java.lang.String motiuDeRebuig , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String usuariAplicacioID , java.lang.String remitentNom , java.lang.String remitentDescripcio , java.lang.String informacioAdicional , java.lang.Long logoSegellID , java.lang.Long custodiaInfoID , java.lang.String usuariEntitatID , boolean avisWeb , boolean segellatDeTemps) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.motiu=motiu;
    this.fitxerAFirmarID=fitxerAFirmarID;
    this.fitxerAdaptatID=fitxerAdaptatID;
    this.tipusDocumentID=tipusDocumentID;
    this.descripcioTipusDocument=descripcioTipusDocument;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.dataSolicitud=dataSolicitud;
    this.dataFinal=dataFinal;
    this.dataCaducitat=dataCaducitat;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.motiuDeRebuig=motiuDeRebuig;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.remitentNom=remitentNom;
    this.remitentDescripcio=remitentDescripcio;
    this.informacioAdicional=informacioAdicional;
    this.logoSegellID=logoSegellID;
    this.custodiaInfoID=custodiaInfoID;
    this.usuariEntitatID=usuariEntitatID;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
}
  /** Constructor dels valors Not Null */
  public PeticioDeFirmaJPA(long peticioDeFirmaID , java.lang.String titol , java.lang.String motiu , long tipusDocumentID , int posicioTaulaFirmesID , java.sql.Timestamp dataCaducitat , int tipusFirmaID , int algorismeDeFirmaID , java.lang.Boolean modeDeFirma , int tipusEstatPeticioDeFirmaID , java.lang.String idiomaID , int prioritatID , long fluxDeFirmesID , java.lang.String usuariAplicacioID , java.lang.String remitentNom , boolean avisWeb , boolean segellatDeTemps) {
    this.peticioDeFirmaID=peticioDeFirmaID;
    this.titol=titol;
    this.motiu=motiu;
    this.tipusDocumentID=tipusDocumentID;
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.dataCaducitat=dataCaducitat;
    this.tipusFirmaID=tipusFirmaID;
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.modeDeFirma=modeDeFirma;
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.idiomaID=idiomaID;
    this.prioritatID=prioritatID;
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.usuariAplicacioID=usuariAplicacioID;
    this.remitentNom=remitentNom;
    this.avisWeb=avisWeb;
    this.segellatDeTemps=segellatDeTemps;
}
  public PeticioDeFirmaJPA(PeticioDeFirma __bean) {
    this.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setMotiu(__bean.getMotiu());
    this.setFitxerAFirmarID(__bean.getFitxerAFirmarID());
    this.setFitxerAdaptatID(__bean.getFitxerAdaptatID());
    this.setTipusDocumentID(__bean.getTipusDocumentID());
    this.setDescripcioTipusDocument(__bean.getDescripcioTipusDocument());
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setDataSolicitud(__bean.getDataSolicitud());
    this.setDataFinal(__bean.getDataFinal());
    this.setDataCaducitat(__bean.getDataCaducitat());
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setModeDeFirma(__bean.getModeDeFirma());
    this.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    this.setMotiuDeRebuig(__bean.getMotiuDeRebuig());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setPrioritatID(__bean.getPrioritatID());
    this.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    this.setRemitentNom(__bean.getRemitentNom());
    this.setRemitentDescripcio(__bean.getRemitentDescripcio());
    this.setInformacioAdicional(__bean.getInformacioAdicional());
    this.setLogoSegellID(__bean.getLogoSegellID());
    this.setCustodiaInfoID(__bean.getCustodiaInfoID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setAvisWeb(__bean.isAvisWeb());
    this.setSegellatDeTemps(__bean.isSegellatDeTemps());
    // Fitxer
    this.setFitxerAFirmar(FitxerJPA.toJPA(__bean.getFitxerAFirmar()));
    // Fitxer
    this.setFitxerAdaptat(FitxerJPA.toJPA(__bean.getFitxerAdaptat()));
    // Fitxer
    this.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
	}

	public long getPeticioDeFirmaID() {
		return(peticioDeFirmaID);
	};
	public void setPeticioDeFirmaID(long _peticioDeFirmaID_) {
		this.peticioDeFirmaID = _peticioDeFirmaID_;
	};

	public java.lang.String getTitol() {
		return(titol);
	};
	public void setTitol(java.lang.String _titol_) {
		this.titol = _titol_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getMotiu() {
		return(motiu);
	};
	public void setMotiu(java.lang.String _motiu_) {
		this.motiu = _motiu_;
	};

	public java.lang.Long getFitxerAFirmarID() {
		return(fitxerAFirmarID);
	};
	public void setFitxerAFirmarID(java.lang.Long _fitxerAFirmarID_) {
		this.fitxerAFirmarID = _fitxerAFirmarID_;
	};

	public java.lang.Long getFitxerAdaptatID() {
		return(fitxerAdaptatID);
	};
	public void setFitxerAdaptatID(java.lang.Long _fitxerAdaptatID_) {
		this.fitxerAdaptatID = _fitxerAdaptatID_;
	};

	public long getTipusDocumentID() {
		return(tipusDocumentID);
	};
	public void setTipusDocumentID(long _tipusDocumentID_) {
		this.tipusDocumentID = _tipusDocumentID_;
	};

	public java.lang.String getDescripcioTipusDocument() {
		return(descripcioTipusDocument);
	};
	public void setDescripcioTipusDocument(java.lang.String _descripcioTipusDocument_) {
		this.descripcioTipusDocument = _descripcioTipusDocument_;
	};

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
	};

	public java.sql.Timestamp getDataSolicitud() {
		return(dataSolicitud);
	};
	public void setDataSolicitud(java.sql.Timestamp _dataSolicitud_) {
		this.dataSolicitud = _dataSolicitud_;
	};

	public java.sql.Timestamp getDataFinal() {
		return(dataFinal);
	};
	public void setDataFinal(java.sql.Timestamp _dataFinal_) {
		this.dataFinal = _dataFinal_;
	};

	public java.sql.Timestamp getDataCaducitat() {
		return(dataCaducitat);
	};
	public void setDataCaducitat(java.sql.Timestamp _dataCaducitat_) {
		this.dataCaducitat = _dataCaducitat_;
	};

	public int getTipusFirmaID() {
		return(tipusFirmaID);
	};
	public void setTipusFirmaID(int _tipusFirmaID_) {
		this.tipusFirmaID = _tipusFirmaID_;
	};

	public int getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(int _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
	};

	public java.lang.Boolean getModeDeFirma() {
		return(modeDeFirma);
	};
	public void setModeDeFirma(java.lang.Boolean _modeDeFirma_) {
		this.modeDeFirma = _modeDeFirma_;
	};

	public int getTipusEstatPeticioDeFirmaID() {
		return(tipusEstatPeticioDeFirmaID);
	};
	public void setTipusEstatPeticioDeFirmaID(int _tipusEstatPeticioDeFirmaID_) {
		this.tipusEstatPeticioDeFirmaID = _tipusEstatPeticioDeFirmaID_;
	};

	public java.lang.String getMotiuDeRebuig() {
		return(motiuDeRebuig);
	};
	public void setMotiuDeRebuig(java.lang.String _motiuDeRebuig_) {
		this.motiuDeRebuig = _motiuDeRebuig_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public int getPrioritatID() {
		return(prioritatID);
	};
	public void setPrioritatID(int _prioritatID_) {
		this.prioritatID = _prioritatID_;
	};

	public long getFluxDeFirmesID() {
		return(fluxDeFirmesID);
	};
	public void setFluxDeFirmesID(long _fluxDeFirmesID_) {
		this.fluxDeFirmesID = _fluxDeFirmesID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};

	public java.lang.String getRemitentNom() {
		return(remitentNom);
	};
	public void setRemitentNom(java.lang.String _remitentNom_) {
		this.remitentNom = _remitentNom_;
	};

	public java.lang.String getRemitentDescripcio() {
		return(remitentDescripcio);
	};
	public void setRemitentDescripcio(java.lang.String _remitentDescripcio_) {
		this.remitentDescripcio = _remitentDescripcio_;
	};

	public java.lang.String getInformacioAdicional() {
		return(informacioAdicional);
	};
	public void setInformacioAdicional(java.lang.String _informacioAdicional_) {
		this.informacioAdicional = _informacioAdicional_;
	};

	public java.lang.Long getLogoSegellID() {
		return(logoSegellID);
	};
	public void setLogoSegellID(java.lang.Long _logoSegellID_) {
		this.logoSegellID = _logoSegellID_;
	};

	public java.lang.Long getCustodiaInfoID() {
		return(custodiaInfoID);
	};
	public void setCustodiaInfoID(java.lang.Long _custodiaInfoID_) {
		this.custodiaInfoID = _custodiaInfoID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public boolean isAvisWeb() {
		return(avisWeb);
	};
	public void setAvisWeb(boolean _avisWeb_) {
		this.avisWeb = _avisWeb_;
	};

	public boolean isSegellatDeTemps() {
		return(segellatDeTemps);
	};
	public void setSegellatDeTemps(boolean _segellatDeTemps_) {
		this.segellatDeTemps = _segellatDeTemps_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PeticioDeFirma) {
      PeticioDeFirma __instance = (PeticioDeFirma)__obj;
      __result = true;
      __result = __result && (this.getPeticioDeFirmaID() == __instance.getPeticioDeFirmaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:peticiodefirmaid | Table: pfi_annex | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peticioDeFirma")
	private Set<AnnexJPA> annexs = new HashSet<AnnexJPA>(0);
	public  Set<AnnexJPA> getAnnexs() {
    return this.annexs;
  }

	public void setAnnexs(Set<AnnexJPA> annexs) {
	  this.annexs = annexs;
	}


// EXP  Field:peticiodefirmaid | Table: pfi_bitacola | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peticioDeFirma")
	private Set<BitacolaJPA> bitacolas = new HashSet<BitacolaJPA>(0);
	public  Set<BitacolaJPA> getBitacolas() {
    return this.bitacolas;
  }

	public void setBitacolas(Set<BitacolaJPA> bitacolas) {
	  this.bitacolas = bitacolas;
	}


// EXP  Field:peticiodefirmaid | Table: pfi_metadada | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peticioDeFirma")
	private Set<MetadadaJPA> metadadas = new HashSet<MetadadaJPA>(0);
	public  Set<MetadadaJPA> getMetadadas() {
    return this.metadadas;
  }

	public void setMetadadas(Set<MetadadaJPA> metadadas) {
	  this.metadadas = metadadas;
	}


// EXP  Field:peticiodefirmaid | Table: pfi_notificacio | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "peticioDeFirma")
	private Set<NotificacioWSJPA> notificacioWSs = new HashSet<NotificacioWSJPA>(0);
	public  Set<NotificacioWSJPA> getNotificacioWSs() {
    return this.notificacioWSs;
  }

	public void setNotificacioWSs(Set<NotificacioWSJPA> notificacioWSs) {
	  this.notificacioWSs = notificacioWSs;
	}


// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_petifirma_fitxer_fir_fk")
	@JoinColumn(name = "fitxerafirmarid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA fitxerAFirmar;

	public FitxerJPA getFitxerAFirmar() {
    return this.fitxerAFirmar;
  }

	public  void setFitxerAFirmar(FitxerJPA fitxerAFirmar) {
    this.fitxerAFirmar = fitxerAFirmar;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_petifirma_fitxer_ada_fk")
	@JoinColumn(name = "fitxeradaptatid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA fitxerAdaptat;

	public FitxerJPA getFitxerAdaptat() {
    return this.fitxerAdaptat;
  }

	public  void setFitxerAdaptat(FitxerJPA fitxerAdaptat) {
    this.fitxerAdaptat = fitxerAdaptat;
  }

// IMP Field:tipusdocumentid | Table: pfi_tipusdocument | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_tipusdoc_fk")
	@JoinColumn(name = "tipusdocumentid", referencedColumnName ="tipusDocumentID", nullable = false, insertable=false, updatable=false)
	private TipusDocumentJPA tipusDocument;

	public TipusDocumentJPA getTipusDocument() {
    return this.tipusDocument;
  }

	public  void setTipusDocument(TipusDocumentJPA tipusDocument) {
    this.tipusDocument = tipusDocument;
  }

// IMP Field:posiciotaulafirmesid | Table: pfi_posiciotaulafirmes | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_postaufir_fk")
	@JoinColumn(name = "posiciotaulafirmesid", referencedColumnName ="posicioTaulaFirmesID", nullable = false, insertable=false, updatable=false)
	private PosicioTaulaFirmesJPA posicioTaulaFirmes;

	public PosicioTaulaFirmesJPA getPosicioTaulaFirmes() {
    return this.posicioTaulaFirmes;
  }

	public  void setPosicioTaulaFirmes(PosicioTaulaFirmesJPA posicioTaulaFirmes) {
    this.posicioTaulaFirmes = posicioTaulaFirmes;
  }

// IMP Field:tipusfirmaid | Table: pfi_tipusfirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_tipusfirma_fk")
	@JoinColumn(name = "tipusfirmaid", referencedColumnName ="tipusFirmaID", nullable = false, insertable=false, updatable=false)
	private TipusFirmaJPA tipusFirma;

	public TipusFirmaJPA getTipusFirma() {
    return this.tipusFirma;
  }

	public  void setTipusFirma(TipusFirmaJPA tipusFirma) {
    this.tipusFirma = tipusFirma;
  }

// IMP Field:algorismedefirmaid | Table: pfi_algorismedefirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_algofirma_fk")
	@JoinColumn(name = "algorismedefirmaid", referencedColumnName ="algorismeDeFirmaID", nullable = false, insertable=false, updatable=false)
	private AlgorismeDeFirmaJPA algorismeDeFirma;

	public AlgorismeDeFirmaJPA getAlgorismeDeFirma() {
    return this.algorismeDeFirma;
  }

	public  void setAlgorismeDeFirma(AlgorismeDeFirmaJPA algorismeDeFirma) {
    this.algorismeDeFirma = algorismeDeFirma;
  }

// IMP Field:tipusestatpeticiodefirmaid | Table: pfi_tipusestatpeticiodefirma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_estpetfirm_fk")
	@JoinColumn(name = "tipusestatpeticiodefirmaid", referencedColumnName ="tipusEstatPeticioDeFirmaID", nullable = false, insertable=false, updatable=false)
	private TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma;

	public TipusEstatPeticioDeFirmaJPA getTipusEstatPeticioDeFirma() {
    return this.tipusEstatPeticioDeFirma;
  }

	public  void setTipusEstatPeticioDeFirma(TipusEstatPeticioDeFirmaJPA tipusEstatPeticioDeFirma) {
    this.tipusEstatPeticioDeFirma = tipusEstatPeticioDeFirma;
  }

// IMP Field:idiomaid | Table: pfi_idioma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_idioma_fk")
	@JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false)
	private IdiomaJPA idioma;

	public IdiomaJPA getIdioma() {
    return this.idioma;
  }

	public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }

// IMP Field:prioritatid | Table: pfi_prioritat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_prioritat_fk")
	@JoinColumn(name = "prioritatid", referencedColumnName ="prioritatID", nullable = false, insertable=false, updatable=false)
	private PrioritatJPA prioritat;

	public PrioritatJPA getPrioritat() {
    return this.prioritat;
  }

	public  void setPrioritat(PrioritatJPA prioritat) {
    this.prioritat = prioritat;
  }

// IMP Field:fluxdefirmesid | Table: pfi_fluxdefirmes | Type: 1  

	@OneToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_fluxfirmes_fk")
	@JoinColumn(name = "fluxdefirmesid", nullable = false, insertable=false, updatable=false)
	private FluxDeFirmesJPA fluxDeFirmes;

	public FluxDeFirmesJPA getFluxDeFirmes() {
    return this.fluxDeFirmes;
  }

	public  void setFluxDeFirmes(FluxDeFirmesJPA fluxDeFirmes) {
    this.fluxDeFirmes = fluxDeFirmes;
  }

// IMP Field:usuariaplicacioid | Table: pfi_usuariaplicacio | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_usrapp_fk")
	@JoinColumn(name = "usuariaplicacioid", referencedColumnName ="usuariAplicacioID", nullable = false, insertable=false, updatable=false)
	private UsuariAplicacioJPA usuariAplicacio;

	public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

	public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_petifirma_fitxer_log_fk")
	@JoinColumn(name = "logosegellid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA logoSegell;

	public FitxerJPA getLogoSegell() {
    return this.logoSegell;
  }

	public  void setLogoSegell(FitxerJPA logoSegell) {
    this.logoSegell = logoSegell;
  }

// IMP Field:custodiainfoid | Table: pfi_custodiainfo | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_custodia_fk")
	@JoinColumn(name = "custodiainfoid", referencedColumnName ="custodiaInfoID", nullable = true, insertable=false, updatable=false)
	private CustodiaInfoJPA custodiaInfo;

	public CustodiaInfoJPA getCustodiaInfo() {
    return this.custodiaInfo;
  }

	public  void setCustodiaInfo(CustodiaInfoJPA custodiaInfo) {
    this.custodiaInfo = custodiaInfo;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_petifirma_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = true, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static PeticioDeFirmaJPA toJPA(PeticioDeFirma __bean) {
    if (__bean == null) { return null;}
    PeticioDeFirmaJPA __tmp = new PeticioDeFirmaJPA();
    __tmp.setPeticioDeFirmaID(__bean.getPeticioDeFirmaID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setMotiu(__bean.getMotiu());
    __tmp.setFitxerAFirmarID(__bean.getFitxerAFirmarID());
    __tmp.setFitxerAdaptatID(__bean.getFitxerAdaptatID());
    __tmp.setTipusDocumentID(__bean.getTipusDocumentID());
    __tmp.setDescripcioTipusDocument(__bean.getDescripcioTipusDocument());
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setDataSolicitud(__bean.getDataSolicitud());
    __tmp.setDataFinal(__bean.getDataFinal());
    __tmp.setDataCaducitat(__bean.getDataCaducitat());
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setModeDeFirma(__bean.getModeDeFirma());
    __tmp.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    __tmp.setMotiuDeRebuig(__bean.getMotiuDeRebuig());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setPrioritatID(__bean.getPrioritatID());
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
    __tmp.setRemitentNom(__bean.getRemitentNom());
    __tmp.setRemitentDescripcio(__bean.getRemitentDescripcio());
    __tmp.setInformacioAdicional(__bean.getInformacioAdicional());
    __tmp.setLogoSegellID(__bean.getLogoSegellID());
    __tmp.setCustodiaInfoID(__bean.getCustodiaInfoID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setAvisWeb(__bean.isAvisWeb());
    __tmp.setSegellatDeTemps(__bean.isSegellatDeTemps());
    // Fitxer
    __tmp.setFitxerAFirmar(FitxerJPA.toJPA(__bean.getFitxerAFirmar()));
    // Fitxer
    __tmp.setFitxerAdaptat(FitxerJPA.toJPA(__bean.getFitxerAdaptat()));
    // Fitxer
    __tmp.setLogoSegell(FitxerJPA.toJPA(__bean.getLogoSegell()));
		return __tmp;
	}


  public static PeticioDeFirmaJPA copyJPA(PeticioDeFirmaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PeticioDeFirmaJPA> copyJPA(java.util.Set<PeticioDeFirmaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PeticioDeFirmaJPA> __tmpSet = (java.util.Set<PeticioDeFirmaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PeticioDeFirmaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PeticioDeFirmaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PeticioDeFirmaJPA copyJPA(PeticioDeFirmaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PeticioDeFirmaJPA __tmp = (PeticioDeFirmaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"BitacolaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.bitacolas) || org.hibernate.Hibernate.isInitialized(__jpa.getBitacolas())) ) {
      __tmp.setBitacolas(BitacolaJPA.copyJPA(__jpa.getBitacolas(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"NotificacioWSJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.notificacioWSs) || org.hibernate.Hibernate.isInitialized(__jpa.getNotificacioWSs())) ) {
      __tmp.setNotificacioWSs(NotificacioWSJPA.copyJPA(__jpa.getNotificacioWSs(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"AnnexJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.annexs) || org.hibernate.Hibernate.isInitialized(__jpa.getAnnexs())) ) {
      __tmp.setAnnexs(AnnexJPA.copyJPA(__jpa.getAnnexs(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"MetadadaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.metadadas) || org.hibernate.Hibernate.isInitialized(__jpa.getMetadadas())) ) {
      __tmp.setMetadadas(MetadadaJPA.copyJPA(__jpa.getMetadadas(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"TipusDocumentJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusDocument) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusDocument()) ) ) {
      __tmp.setTipusDocument(TipusDocumentJPA.copyJPA(__jpa.getTipusDocument(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"TipusFirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusFirma()) ) ) {
      __tmp.setTipusFirma(TipusFirmaJPA.copyJPA(__jpa.getTipusFirma(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"PrioritatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.prioritat) || org.hibernate.Hibernate.isInitialized(__jpa.getPrioritat()) ) ) {
      __tmp.setPrioritat(PrioritatJPA.copyJPA(__jpa.getPrioritat(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfo) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfo()) ) ) {
      __tmp.setCustodiaInfo(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfo(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"PosicioTaulaFirmesJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.posicioTaulaFirmes) || org.hibernate.Hibernate.isInitialized(__jpa.getPosicioTaulaFirmes()) ) ) {
      __tmp.setPosicioTaulaFirmes(PosicioTaulaFirmesJPA.copyJPA(__jpa.getPosicioTaulaFirmes(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"FluxDeFirmesJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.fluxDeFirmes) || org.hibernate.Hibernate.isInitialized(__jpa.getFluxDeFirmes()) ) ) {
      __tmp.setFluxDeFirmes(FluxDeFirmesJPA.copyJPA(__jpa.getFluxDeFirmes(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"TipusEstatPeticioDeFirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusEstatPeticioDeFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusEstatPeticioDeFirma()) ) ) {
      __tmp.setTipusEstatPeticioDeFirma(TipusEstatPeticioDeFirmaJPA.copyJPA(__jpa.getTipusEstatPeticioDeFirma(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"IdiomaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.idioma) || org.hibernate.Hibernate.isInitialized(__jpa.getIdioma()) ) ) {
      __tmp.setIdioma(IdiomaJPA.copyJPA(__jpa.getIdioma(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"AlgorismeDeFirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.algorismeDeFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getAlgorismeDeFirma()) ) ) {
      __tmp.setAlgorismeDeFirma(AlgorismeDeFirmaJPA.copyJPA(__jpa.getAlgorismeDeFirma(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"PeticioDeFirmaJPA"));
    }

    return __tmp;
  }




}
