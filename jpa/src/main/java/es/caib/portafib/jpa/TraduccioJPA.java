
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import org.hibernate.annotations.Cascade;
import javax.persistence.SequenceGenerator;
import java.util.Map;
import javax.persistence.Id;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyCollection;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.JoinTable;
import org.hibernate.annotations.ForeignKey;
import java.util.HashMap;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import org.hibernate.annotations.CollectionOfElements;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_traduccio" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class TraduccioJPA implements Traduccio {



private static final long serialVersionUID = -326205279L;

  /**  */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_traduccio_pk_i")
	@Column(name="traduccioid",nullable = false,length = 19)
	long traduccioID;



  /** Constructor Buit */
  public TraduccioJPA() {
  }

  /** Constructor amb tots els camps  */
  public TraduccioJPA(long traduccioID) {
    this.traduccioID=traduccioID;
}
  public TraduccioJPA(Traduccio __bean) {
    this.setTraduccioID(__bean.getTraduccioID());
	}

	public long getTraduccioID() {
		return(traduccioID);
	};
	public void setTraduccioID(long _traduccioID_) {
		this.traduccioID = _traduccioID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Traduccio) {
      Traduccio __instance = (Traduccio)__obj;
      __result = true;
      __result = __result && (this.getTraduccioID() == __instance.getTraduccioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:firmatperformatid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "firmatPerFormatID")
	private Set<EntitatJPA> entitat_firmatperformatids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_firmatperformatids() {
    return this.entitat_firmatperformatids;
  }

	public void setEntitat_firmatperformatids(Set<EntitatJPA> entitat_firmatperformatids) {
	  this.entitat_firmatperformatids = entitat_firmatperformatids;
	}


// EXP  Field:motiudelegacioid | Table: pfi_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "motiuDelegacioID")
	private Set<EntitatJPA> entitat_motiudelegacioids = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitat_motiudelegacioids() {
    return this.entitat_motiudelegacioids;
  }

	public void setEntitat_motiudelegacioids(Set<EntitatJPA> entitat_motiudelegacioids) {
	  this.entitat_motiudelegacioids = entitat_motiudelegacioids;
	}


// EXP  Field:nomid | Table: pfi_moduldefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<ModulDeFirmaJPA> modulDeFirma_nomids = new HashSet<ModulDeFirmaJPA>(0);
	public  Set<ModulDeFirmaJPA> getModulDeFirma_nomids() {
    return this.modulDeFirma_nomids;
  }

	public void setModulDeFirma_nomids(Set<ModulDeFirmaJPA> modulDeFirma_nomids) {
	  this.modulDeFirma_nomids = modulDeFirma_nomids;
	}


// EXP  Field:descripciocurtaid | Table: pfi_moduldefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "descripcioCurtaID")
	private Set<ModulDeFirmaJPA> modulDeFirma_descripciocurtaids = new HashSet<ModulDeFirmaJPA>(0);
	public  Set<ModulDeFirmaJPA> getModulDeFirma_descripciocurtaids() {
    return this.modulDeFirma_descripciocurtaids;
  }

	public void setModulDeFirma_descripciocurtaids(Set<ModulDeFirmaJPA> modulDeFirma_descripciocurtaids) {
	  this.modulDeFirma_descripciocurtaids = modulDeFirma_descripciocurtaids;
	}


// EXP  Field:nom | Table: pfi_tipusdocument | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nomID")
	private Set<TipusDocumentJPA> tipusDocuments = new HashSet<TipusDocumentJPA>(0);
	public  Set<TipusDocumentJPA> getTipusDocuments() {
    return this.tipusDocuments;
  }

	public void setTipusDocuments(Set<TipusDocumentJPA> tipusDocuments) {
	  this.tipusDocuments = tipusDocuments;
	}


  @CollectionOfElements(fetch= FetchType.EAGER,targetElement = es.caib.portafib.jpa.TraduccioMapJPA.class)
  @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
  @LazyCollection(value= LazyCollectionOption.FALSE)
  @JoinTable(name="pfi_traducciomap",joinColumns={@JoinColumn(name="traducciomapid")})
  @org.hibernate.annotations.MapKey(columns={@Column(name="idiomaid")})
  @ForeignKey(name="pfi_traducmap_traduccio_fk") 
  private Map<String, es.caib.portafib.jpa.TraduccioMapJPA> traduccions =  new HashMap<String, es.caib.portafib.jpa.TraduccioMapJPA>();

  public Map<String, es.caib.portafib.jpa.TraduccioMapJPA> getTraduccions() {
    return this.traduccions;
  }

  public void setTraduccions(Map<String, es.caib.portafib.jpa.TraduccioMapJPA> _traduccions_) {
    this.traduccions = _traduccions_;
  }

  public java.util.Set<String> getIdiomes() {
    return this.traduccions.keySet();
  }
  
  public es.caib.portafib.jpa.TraduccioMapJPA getTraduccio(String idioma) {
    return (es.caib.portafib.jpa.TraduccioMapJPA) traduccions.get(idioma);
  }
  
  public void addTraduccio(String idioma, es.caib.portafib.jpa.TraduccioMapJPA traduccio) {
    if (traduccio == null) {
      traduccions.remove(idioma);
    } else {
      traduccions.put(idioma, traduccio);
    }
  }

 // ---------------  STATIC METHODS ------------------
  public static TraduccioJPA toJPA(Traduccio __bean) {
    if (__bean == null) { return null;}
    TraduccioJPA __tmp = new TraduccioJPA();
    __tmp.setTraduccioID(__bean.getTraduccioID());
		return __tmp;
	}


  public static TraduccioJPA copyJPA(TraduccioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TraduccioJPA> copyJPA(java.util.Set<TraduccioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TraduccioJPA> __tmpSet = (java.util.Set<TraduccioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TraduccioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TraduccioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TraduccioJPA copyJPA(TraduccioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TraduccioJPA __tmp = (TraduccioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"ModulDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modulDeFirma_descripciocurtaids) || org.hibernate.Hibernate.isInitialized(__jpa.getModulDeFirma_descripciocurtaids())) ) {
      __tmp.setModulDeFirma_descripciocurtaids(ModulDeFirmaJPA.copyJPA(__jpa.getModulDeFirma_descripciocurtaids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"ModulDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modulDeFirma_nomids) || org.hibernate.Hibernate.isInitialized(__jpa.getModulDeFirma_nomids())) ) {
      __tmp.setModulDeFirma_nomids(ModulDeFirmaJPA.copyJPA(__jpa.getModulDeFirma_nomids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"TipusDocumentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusDocuments) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusDocuments())) ) {
      __tmp.setTipusDocuments(TipusDocumentJPA.copyJPA(__jpa.getTipusDocuments(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_motiudelegacioids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_motiudelegacioids())) ) {
      __tmp.setEntitat_motiudelegacioids(EntitatJPA.copyJPA(__jpa.getEntitat_motiudelegacioids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat_firmatperformatids) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat_firmatperformatids())) ) {
      __tmp.setEntitat_firmatperformatids(EntitatJPA.copyJPA(__jpa.getEntitat_firmatperformatids(), __alreadyCopied,"TraduccioJPA"));
    }
    // Copia de beans complexes (IMP)
    // Aquesta linia s'afeix de forma manual
    __tmp.setTraduccions(new HashMap<String, TraduccioMapJPA>(__jpa.getTraduccions()));

    return __tmp;
  }




}
