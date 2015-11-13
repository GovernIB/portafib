
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "pfi_moduldefirma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class ModulDeFirmaJPA implements ModulDeFirma {



private static final long serialVersionUID = -1153694606L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_moduldefirma_pk_i")
	@Column(name="moduldefirmaid",nullable = false,length = 19)
	long modulDeFirmaID;

	@Index(name="pfi_moduldefirma_nomid_fk_i")
	@Column(name="nomid",nullable = false,length = 19)
	long nomID;

	@Index(name="pfi_modfirm_desccurtaid_fk_i")
	@Column(name="descripciocurtaid",nullable = false,length = 19)
	long descripcioCurtaID;

	@Column(name="classe",nullable = false,length = 255)
	java.lang.String classe;

	@Column(name="propertiesadmin",length = 2147483647)
  @Lob
	java.lang.String propertiesAdmin;

	@Column(name="propertiesentitat",length = 2147483647)
  @Lob
	java.lang.String propertiesEntitat;

  /** Si val null indica que és de l'Administrador. En cas conytrari ja és una instanciació d'una Entitat */
	@Index(name="pfi_modfirm_entitatid_fk_i")
	@Column(name="entitatid",length = 50)
	java.lang.String entitatID;

	@Column(name="actiu",nullable = false,length = 1)
	boolean actiu;



  /** Constructor Buit */
  public ModulDeFirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public ModulDeFirmaJPA(long modulDeFirmaID , long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , java.lang.String entitatID , boolean actiu) {
    this.modulDeFirmaID=modulDeFirmaID;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public ModulDeFirmaJPA(long nomID , long descripcioCurtaID , java.lang.String classe , java.lang.String propertiesAdmin , java.lang.String propertiesEntitat , java.lang.String entitatID , boolean actiu) {
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.propertiesAdmin=propertiesAdmin;
    this.propertiesEntitat=propertiesEntitat;
    this.entitatID=entitatID;
    this.actiu=actiu;
}
  /** Constructor dels valors Not Null */
  public ModulDeFirmaJPA(long modulDeFirmaID , long nomID , long descripcioCurtaID , java.lang.String classe , boolean actiu) {
    this.modulDeFirmaID=modulDeFirmaID;
    this.nomID=nomID;
    this.descripcioCurtaID=descripcioCurtaID;
    this.classe=classe;
    this.actiu=actiu;
}
  public ModulDeFirmaJPA(ModulDeFirma __bean) {
    this.setModulDeFirmaID(__bean.getModulDeFirmaID());
    this.setNomID(__bean.getNomID());
    this.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    this.setClasse(__bean.getClasse());
    this.setPropertiesAdmin(__bean.getPropertiesAdmin());
    this.setPropertiesEntitat(__bean.getPropertiesEntitat());
    this.setEntitatID(__bean.getEntitatID());
    this.setActiu(__bean.isActiu());
	}

	public long getModulDeFirmaID() {
		return(modulDeFirmaID);
	};
	public void setModulDeFirmaID(long _modulDeFirmaID_) {
		this.modulDeFirmaID = _modulDeFirmaID_;
	};

	public long getNomID() {
		return(nomID);
	};
	public void setNomID(long _nomID_) {
		this.nomID = _nomID_;
	};

	public long getDescripcioCurtaID() {
		return(descripcioCurtaID);
	};
	public void setDescripcioCurtaID(long _descripcioCurtaID_) {
		this.descripcioCurtaID = _descripcioCurtaID_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public java.lang.String getPropertiesAdmin() {
		return(propertiesAdmin);
	};
	public void setPropertiesAdmin(java.lang.String _propertiesAdmin_) {
		this.propertiesAdmin = _propertiesAdmin_;
	};

	public java.lang.String getPropertiesEntitat() {
		return(propertiesEntitat);
	};
	public void setPropertiesEntitat(java.lang.String _propertiesEntitat_) {
		this.propertiesEntitat = _propertiesEntitat_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof ModulDeFirma) {
      ModulDeFirma __instance = (ModulDeFirma)__obj;
      __result = true;
      __result = __result && (this.getModulDeFirmaID() == __instance.getModulDeFirmaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:moduldefirmaid | Table: pfi_modulfirmapertipusdoc | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "modulDeFirma")
	private Set<ModulDeFirmaPerTipusDeDocumentJPA> modulDeFirmaPerTipusDeDocuments = new HashSet<ModulDeFirmaPerTipusDeDocumentJPA>(0);
	public  Set<ModulDeFirmaPerTipusDeDocumentJPA> getModulDeFirmaPerTipusDeDocuments() {
    return this.modulDeFirmaPerTipusDeDocuments;
  }

	public void setModulDeFirmaPerTipusDeDocuments(Set<ModulDeFirmaPerTipusDeDocumentJPA> modulDeFirmaPerTipusDeDocuments) {
	  this.modulDeFirmaPerTipusDeDocuments = modulDeFirmaPerTipusDeDocuments;
	}


// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="pfi_modfirm_traduccio_nom_fk")
	@JoinColumn(name = "nomid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
	private TraduccioJPA nom;

	public TraduccioJPA getNom() {
    return this.nom;
  }

	public  void setNom(TraduccioJPA nom) {
    this.nom = nom;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> getNomTraduccions() {
    return this.nom.getTraduccions();
  }

  public void setNomTraduccions(java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> __traduccions__) {
    this.nom.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: pfi_traduccio | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@ForeignKey(name="pfi_modfirm_traduccio_desc_fk")
	@JoinColumn(name = "descripciocurtaid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false)
	private TraduccioJPA descripcioCurta;

	public TraduccioJPA getDescripcioCurta() {
    return this.descripcioCurta;
  }

	public  void setDescripcioCurta(TraduccioJPA descripcioCurta) {
    this.descripcioCurta = descripcioCurta;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> getDescripcioCurtaTraduccions() {
    return this.descripcioCurta.getTraduccions();
  }

  public void setDescripcioCurtaTraduccions(java.util.Map<String, es.caib.portafib.jpa.TraduccioMapJPA> __traduccions__) {
    this.descripcioCurta.setTraduccions(__traduccions__);
  }


// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_modfirm_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static ModulDeFirmaJPA toJPA(ModulDeFirma __bean) {
    if (__bean == null) { return null;}
    ModulDeFirmaJPA __tmp = new ModulDeFirmaJPA();
    __tmp.setModulDeFirmaID(__bean.getModulDeFirmaID());
    __tmp.setNomID(__bean.getNomID());
    __tmp.setDescripcioCurtaID(__bean.getDescripcioCurtaID());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setPropertiesAdmin(__bean.getPropertiesAdmin());
    __tmp.setPropertiesEntitat(__bean.getPropertiesEntitat());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}


  public static ModulDeFirmaJPA copyJPA(ModulDeFirmaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ModulDeFirmaJPA> copyJPA(java.util.Set<ModulDeFirmaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ModulDeFirmaJPA> __tmpSet = (java.util.Set<ModulDeFirmaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ModulDeFirmaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ModulDeFirmaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ModulDeFirmaJPA copyJPA(ModulDeFirmaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ModulDeFirmaJPA __tmp = (ModulDeFirmaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"ModulDeFirmaPerTipusDeDocumentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modulDeFirmaPerTipusDeDocuments) || org.hibernate.Hibernate.isInitialized(__jpa.getModulDeFirmaPerTipusDeDocuments())) ) {
      __tmp.setModulDeFirmaPerTipusDeDocuments(ModulDeFirmaPerTipusDeDocumentJPA.copyJPA(__jpa.getModulDeFirmaPerTipusDeDocuments(), __alreadyCopied,"ModulDeFirmaJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"ModulDeFirmaJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.descripcioCurta) || org.hibernate.Hibernate.isInitialized(__jpa.getDescripcioCurta()) ) ) {
      __tmp.setDescripcioCurta(TraduccioJPA.copyJPA(__jpa.getDescripcioCurta(), __alreadyCopied,"ModulDeFirmaJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.nom) || org.hibernate.Hibernate.isInitialized(__jpa.getNom()) ) ) {
      __tmp.setNom(TraduccioJPA.copyJPA(__jpa.getNom(), __alreadyCopied,"ModulDeFirmaJPA"));
    }

    return __tmp;
  }




}
