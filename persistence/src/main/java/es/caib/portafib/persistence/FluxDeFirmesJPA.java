
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "FluxDeFirmesJPA")
@Table(name = "pfi_fluxdefirmes" , indexes = { 
        @Index(name="pfi_fluxdefirmes_pk_i", columnList = "fluxdefirmesid")})
@SequenceGenerator(name="FLUXDEFIRMES_SEQ", sequenceName="pfi_fluxdefirmes_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class FluxDeFirmesJPA implements FluxDeFirmes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLUXDEFIRMES_SEQ")
    @Column(name="fluxdefirmesid",nullable = false,length = 19)
    long fluxDeFirmesID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;



  /** Constructor Buit */
  public FluxDeFirmesJPA() {
  }

  /** Constructor amb tots els camps  */
  public FluxDeFirmesJPA(long fluxDeFirmesID , java.lang.String nom) {
    this.fluxDeFirmesID=fluxDeFirmesID;
    this.nom=nom;
}
  /** Constructor sense valors autoincrementals */
  public FluxDeFirmesJPA(java.lang.String nom) {
    this.nom=nom;
}
  public FluxDeFirmesJPA(FluxDeFirmes __bean) {
    this.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    this.setNom(__bean.getNom());
	}

	public long getFluxDeFirmesID() {
		return(fluxDeFirmesID);
	};
	public void setFluxDeFirmesID(long _fluxDeFirmesID_) {
		this.fluxDeFirmesID = _fluxDeFirmesID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof FluxDeFirmes) {
      FluxDeFirmes __instance = (FluxDeFirmes)__obj;
      __result = true;
      __result = __result && (this.getFluxDeFirmesID() == __instance.getFluxDeFirmesID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:fluxdefirmesid | Table: pfi_blocdefirmes | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fluxDeFirmes")
    private Set<BlocDeFirmesJPA> blocDeFirmess = new HashSet<BlocDeFirmesJPA>(0);
    public  Set<BlocDeFirmesJPA> getBlocDeFirmess() {
    return this.blocDeFirmess;
  }

    public void setBlocDeFirmess(Set<BlocDeFirmesJPA> blocDeFirmess) {
      this.blocDeFirmess = blocDeFirmess;
    }


// EXP  Field:fluxdefirmesid | Table: pfi_peticiodefirma | Type: 0  

    @OneToOne(mappedBy="fluxDeFirmes")
    private PeticioDeFirmaJPA peticioDeFirma;
    public PeticioDeFirmaJPA getPeticioDeFirma()    {
      return this.peticioDeFirma;
    }

    public  void setPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) {
      this.peticioDeFirma = peticioDeFirma;
    }


// EXP  Field:fluxdefirmesid | Table: pfi_plantillafluxdefirmes | Type: 0  

    @OneToOne(mappedBy="fluxDeFirmes")
    private PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes;
    public PlantillaFluxDeFirmesJPA getPlantillaFluxDeFirmes()    {
      return this.plantillaFluxDeFirmes;
    }

    public  void setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes) {
      this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
    }



 // ---------------  STATIC METHODS ------------------
  public static FluxDeFirmesJPA toJPA(FluxDeFirmes __bean) {
    if (__bean == null) { return null;}
    FluxDeFirmesJPA __tmp = new FluxDeFirmesJPA();
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}


  public static FluxDeFirmesJPA copyJPA(FluxDeFirmesJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FluxDeFirmesJPA> copyJPA(java.util.Set<FluxDeFirmesJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FluxDeFirmesJPA> __tmpSet = (java.util.Set<FluxDeFirmesJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FluxDeFirmesJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FluxDeFirmesJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FluxDeFirmesJPA copyJPA(FluxDeFirmesJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FluxDeFirmesJPA __tmp = (FluxDeFirmesJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"BlocDeFirmesJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.blocDeFirmess) || org.hibernate.Hibernate.isInitialized(__jpa.getBlocDeFirmess())) ) {
      __tmp.setBlocDeFirmess(BlocDeFirmesJPA.copyJPA(__jpa.getBlocDeFirmess(), __alreadyCopied,"FluxDeFirmesJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirma())) ) {
      __tmp.setPeticioDeFirma(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirma(), __alreadyCopied,"FluxDeFirmesJPA"));
    }
    if(!"PlantillaFluxDeFirmesJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.plantillaFluxDeFirmes) || org.hibernate.Hibernate.isInitialized(__jpa.getPlantillaFluxDeFirmes())) ) {
      __tmp.setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA.copyJPA(__jpa.getPlantillaFluxDeFirmes(), __alreadyCopied,"FluxDeFirmesJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
