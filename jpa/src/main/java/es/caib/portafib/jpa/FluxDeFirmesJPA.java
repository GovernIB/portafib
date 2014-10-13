
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_fluxdefirmes" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class FluxDeFirmesJPA implements FluxDeFirmes {



private static final long serialVersionUID = -624049275L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_fluxdefirmes_pk_i")
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

  public static FluxDeFirmesJPA toJPA(FluxDeFirmes __bean) {
    if (__bean == null) { return null;}
    FluxDeFirmesJPA __tmp = new FluxDeFirmesJPA();
    __tmp.setFluxDeFirmesID(__bean.getFluxDeFirmesID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
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
	public PeticioDeFirmaJPA getPeticioDeFirma() {
	  return this.peticioDeFirma;
	}

	public  void setPeticioDeFirma(PeticioDeFirmaJPA peticioDeFirma) {
	  this.peticioDeFirma = peticioDeFirma;
	}


// EXP  Field:fluxdefirmesid | Table: pfi_plantillafluxdefirmes | Type: 0  

	@OneToOne(mappedBy="fluxDeFirmes")
	private PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes;
	public PlantillaFluxDeFirmesJPA getPlantillaFluxDeFirmes() {
	  return this.plantillaFluxDeFirmes;
	}

	public  void setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes) {
	  this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
	}




}
