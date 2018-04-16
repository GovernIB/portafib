
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_fitxer" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class FitxerJPA implements Fitxer {



private static final long serialVersionUID = -252813913L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_fitxer_pk_i")
	@Column(name="fitxerid",nullable = false,length = 19)
	long fitxerID;

	@Column(name="nom",nullable = false,length = 255)
	java.lang.String nom;

	@Column(name="descripcio",length = 1000)
	java.lang.String descripcio;

	@Column(name="tamany",nullable = false,length = 19)
	long tamany;

	@Column(name="mime",nullable = false,length = 255)
	java.lang.String mime;



  /** Constructor Buit */
  public FitxerJPA() {
  }

  /** Constructor amb tots els camps  */
  public FitxerJPA(long fitxerID , java.lang.String nom , java.lang.String descripcio , long tamany , java.lang.String mime) {
    this.fitxerID=fitxerID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.tamany=tamany;
    this.mime=mime;
}
  /** Constructor sense valors autoincrementals */
  public FitxerJPA(java.lang.String nom , java.lang.String descripcio , long tamany , java.lang.String mime) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.tamany=tamany;
    this.mime=mime;
}
  public FitxerJPA(Fitxer __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Fitxer) {
      Fitxer __instance = (Fitxer)__obj;
      __result = true;
      __result = __result && (this.getFitxerID() == __instance.getFitxerID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:fitxerid | Table: pfi_annex | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerID")
	private Set<AnnexJPA> annexs = new HashSet<AnnexJPA>(0);
	public  Set<AnnexJPA> getAnnexs() {
    return this.annexs;
  }

	public void setAnnexs(Set<AnnexJPA> annexs) {
	  this.annexs = annexs;
	}


// EXP  Field:fitxerid | Table: pfi_annexfirmat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerID")
	private Set<AnnexFirmatJPA> annexFirmats = new HashSet<AnnexFirmatJPA>(0);
	public  Set<AnnexFirmatJPA> getAnnexFirmats() {
    return this.annexFirmats;
  }

	public void setAnnexFirmats(Set<AnnexFirmatJPA> annexFirmats) {
	  this.annexFirmats = annexFirmats;
	}


// EXP  Field:fitxerautoritzacioid | Table: pfi_colaboraciodelegacio | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerAutoritzacioID")
	private Set<ColaboracioDelegacioJPA> colaboracioDelegacios = new HashSet<ColaboracioDelegacioJPA>(0);
	public  Set<ColaboracioDelegacioJPA> getColaboracioDelegacios() {
    return this.colaboracioDelegacios;
  }

	public void setColaboracioDelegacios(Set<ColaboracioDelegacioJPA> colaboracioDelegacios) {
	  this.colaboracioDelegacios = colaboracioDelegacios;
	}


// EXP  Field:logowebpeuid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoWebPeuID")
	private Set<EntitatJPA> entitat_logowebpeuids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_logowebpeuids() {
    return this.entitat_logowebpeuids;
  }

	public void setEntitat_logowebpeuids(Set<EntitatJPA> entitat_logowebpeuids) {
	  this.entitat_logowebpeuids = entitat_logowebpeuids;
	}


// EXP  Field:logosegellid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoSegellID")
	private Set<EntitatJPA> entitat_logosegellids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_logosegellids() {
    return this.entitat_logosegellids;
  }

	public void setEntitat_logosegellids(Set<EntitatJPA> entitat_logosegellids) {
	  this.entitat_logosegellids = entitat_logosegellids;
	}


// EXP  Field:pdfautoritzaciodelegacioid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pdfAutoritzacioDelegacioID")
	private Set<EntitatJPA> entitat_pdfautoritzaciodelegacioids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_pdfautoritzaciodelegacioids() {
    return this.entitat_pdfautoritzaciodelegacioids;
  }

	public void setEntitat_pdfautoritzaciodelegacioids(Set<EntitatJPA> entitat_pdfautoritzaciodelegacioids) {
	  this.entitat_pdfautoritzaciodelegacioids = entitat_pdfautoritzaciodelegacioids;
	}


// EXP  Field:faviconid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "faviconID")
	private Set<EntitatJPA> entitat_faviconids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_faviconids() {
    return this.entitat_faviconids;
  }

	public void setEntitat_faviconids(Set<EntitatJPA> entitat_faviconids) {
	  this.entitat_faviconids = entitat_faviconids;
	}


// EXP  Field:logowebid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoWebID")
	private Set<EntitatJPA> entitat_logowebids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_logowebids() {
    return this.entitat_logowebids;
  }

	public void setEntitat_logowebids(Set<EntitatJPA> entitat_logowebids) {
	  this.entitat_logowebids = entitat_logowebids;
	}


// EXP  Field:fitxerfirmatid | Table: pfi_firma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerFirmatID")
	private Set<FirmaJPA> firmas = new HashSet<FirmaJPA>(0);
	public  Set<FirmaJPA> getFirmas() {
    return this.firmas;
  }

	public void setFirmas(Set<FirmaJPA> firmas) {
	  this.firmas = firmas;
	}


// EXP  Field:firmaoriginaldetachedid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "firmaOriginalDetachedID")
	private Set<PeticioDeFirmaJPA> peticioDeFirma_firmaoriginaldetachedids = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirma_firmaoriginaldetachedids() {
    return this.peticioDeFirma_firmaoriginaldetachedids;
  }

	public void setPeticioDeFirma_firmaoriginaldetachedids(Set<PeticioDeFirmaJPA> peticioDeFirma_firmaoriginaldetachedids) {
	  this.peticioDeFirma_firmaoriginaldetachedids = peticioDeFirma_firmaoriginaldetachedids;
	}


// EXP  Field:fitxeradaptatid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerAdaptatID")
	private Set<PeticioDeFirmaJPA> peticioDeFirma_fitxeradaptatids = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirma_fitxeradaptatids() {
    return this.peticioDeFirma_fitxeradaptatids;
  }

	public void setPeticioDeFirma_fitxeradaptatids(Set<PeticioDeFirmaJPA> peticioDeFirma_fitxeradaptatids) {
	  this.peticioDeFirma_fitxeradaptatids = peticioDeFirma_fitxeradaptatids;
	}


// EXP  Field:fitxerafirmarid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerAFirmarID")
	private Set<PeticioDeFirmaJPA> peticioDeFirma_fitxerafirmarids = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirma_fitxerafirmarids() {
    return this.peticioDeFirma_fitxerafirmarids;
  }

	public void setPeticioDeFirma_fitxerafirmarids(Set<PeticioDeFirmaJPA> peticioDeFirma_fitxerafirmarids) {
	  this.peticioDeFirma_fitxerafirmarids = peticioDeFirma_fitxerafirmarids;
	}


// EXP  Field:logosegellid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoSegellID")
	private Set<PeticioDeFirmaJPA> peticioDeFirma_logosegellids = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirma_logosegellids() {
    return this.peticioDeFirma_logosegellids;
  }

	public void setPeticioDeFirma_logosegellids(Set<PeticioDeFirmaJPA> peticioDeFirma_logosegellids) {
	  this.peticioDeFirma_logosegellids = peticioDeFirma_logosegellids;
	}


// EXP  Field:logosegellid | Table: pfi_usuariaplicacio | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoSegellID")
	private Set<UsuariAplicacioJPA> usuariAplicacios = new HashSet<UsuariAplicacioJPA>(0);
	public  Set<UsuariAplicacioJPA> getUsuariAplicacios() {
    return this.usuariAplicacios;
  }

	public void setUsuariAplicacios(Set<UsuariAplicacioJPA> usuariAplicacios) {
	  this.usuariAplicacios = usuariAplicacios;
	}


// EXP  Field:logincertificateid | Table: pfi_usuariaplicacioconfig | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loginCertificateID")
	private Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracios = new HashSet<UsuariAplicacioConfiguracioJPA>(0);
	public  Set<UsuariAplicacioConfiguracioJPA> getUsuariAplicacioConfiguracios() {
    return this.usuariAplicacioConfiguracios;
  }

	public void setUsuariAplicacioConfiguracios(Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracios) {
	  this.usuariAplicacioConfiguracios = usuariAplicacioConfiguracios;
	}


// EXP  Field:logosegellid | Table: pfi_usuarientitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "logoSegellID")
	private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
	public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

	public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
	  this.usuariEntitats = usuariEntitats;
	}


// EXP  Field:rubricaid | Table: pfi_usuaripersona | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rubricaID")
	private Set<UsuariPersonaJPA> usuariPersonas = new HashSet<UsuariPersonaJPA>(0);
	public  Set<UsuariPersonaJPA> getUsuariPersonas() {
    return this.usuariPersonas;
  }

	public void setUsuariPersonas(Set<UsuariPersonaJPA> usuariPersonas) {
	  this.usuariPersonas = usuariPersonas;
	}



  @javax.persistence.Transient
  javax.activation.DataHandler data;

  public javax.activation.DataHandler getData() {
    return data;
  }

  public void setData(javax.activation.DataHandler data) {
    this.data = data;
  }

  @javax.persistence.Transient
  String encryptedFileID;

  public String getEncryptedFileID() {
    return encryptedFileID;
  }

  public void setEncryptedFileID(String encryptedFileID) {
    this.encryptedFileID = encryptedFileID;
  }


  final static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

  public static void enableEncryptedFileIDGeneration() {
    threadLocal.set("");
  }

  public static void disableEncryptedFileIDGeneration() {
    threadLocal.remove();
  }

  @javax.persistence.PostPersist
  @javax.persistence.PostLoad
  void postLoad() {
    if (threadLocal.get() != null) {
      this.encryptedFileID = es.caib.portafib.hibernate.HibernateFileUtil.encryptFileID(fitxerID);
    }
  }


 // ---------------  STATIC METHODS ------------------
  public static FitxerJPA toJPA(Fitxer __bean) {
    if (__bean == null) { return null;}
    FitxerJPA __tmp = new FitxerJPA();
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


  public static FitxerJPA copyJPA(FitxerJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FitxerJPA> copyJPA(java.util.Set<FitxerJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FitxerJPA> __tmpSet = (java.util.Set<FitxerJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FitxerJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FitxerJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FitxerJPA copyJPA(FitxerJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FitxerJPA __tmp = (FitxerJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma_firmaoriginaldetachedids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma_firmaoriginaldetachedids())) ) {
      __tmp.setPeticioDeFirma_firmaoriginaldetachedids(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma_firmaoriginaldetachedids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"ColaboracioDelegacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.colaboracioDelegacios) || org.hibernate.Hibernate.isInitialized(__jpa.getColaboracioDelegacios())) ) {
      __tmp.setColaboracioDelegacios(ColaboracioDelegacioJPA.copyJPA(__jpa.getColaboracioDelegacios(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_pdfautoritzaciodelegacioids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_pdfautoritzaciodelegacioids())) ) {
      __tmp.setEntitat_pdfautoritzaciodelegacioids(EntitatJPA.copyJPA(__jpa.getEntitat_pdfautoritzaciodelegacioids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacios())) ) {
      __tmp.setUsuariAplicacios(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacios(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_logosegellids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_logosegellids())) ) {
      __tmp.setEntitat_logosegellids(EntitatJPA.copyJPA(__jpa.getEntitat_logosegellids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma_fitxeradaptatids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma_fitxeradaptatids())) ) {
      __tmp.setPeticioDeFirma_fitxeradaptatids(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma_fitxeradaptatids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"FirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.firmas) || org.hibernate.Hibernate.isInitialized(__jpa.getFirmas())) ) {
      __tmp.setFirmas(FirmaJPA.copyJPA(__jpa.getFirmas(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma_fitxerafirmarids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma_fitxerafirmarids())) ) {
      __tmp.setPeticioDeFirma_fitxerafirmarids(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma_fitxerafirmarids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"AnnexJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.annexs) || org.hibernate.Hibernate.isInitialized(__jpa.getAnnexs())) ) {
      __tmp.setAnnexs(AnnexJPA.copyJPA(__jpa.getAnnexs(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_logowebids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_logowebids())) ) {
      __tmp.setEntitat_logowebids(EntitatJPA.copyJPA(__jpa.getEntitat_logowebids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"UsuariPersonaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariPersonas) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariPersonas())) ) {
      __tmp.setUsuariPersonas(UsuariPersonaJPA.copyJPA(__jpa.getUsuariPersonas(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacioConfiguracios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacioConfiguracios())) ) {
      __tmp.setUsuariAplicacioConfiguracios(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getUsuariAplicacioConfiguracios(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"AnnexFirmatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.annexFirmats) || org.hibernate.Hibernate.isInitialized(__jpa.getAnnexFirmats())) ) {
      __tmp.setAnnexFirmats(AnnexFirmatJPA.copyJPA(__jpa.getAnnexFirmats(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_logowebpeuids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_logowebpeuids())) ) {
      __tmp.setEntitat_logowebpeuids(EntitatJPA.copyJPA(__jpa.getEntitat_logowebpeuids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma_logosegellids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma_logosegellids())) ) {
      __tmp.setPeticioDeFirma_logosegellids(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma_logosegellids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_faviconids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_faviconids())) ) {
      __tmp.setEntitat_faviconids(EntitatJPA.copyJPA(__jpa.getEntitat_faviconids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"FitxerJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
