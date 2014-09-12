
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;


@Entity
@Table(name = "pfi_prioritat" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class PrioritatJPA implements Prioritat {



private static final long serialVersionUID = -245759373L;

	@Id
	@Index(name="pfi_prioritat_pk_i")
	@Column(name="prioritatid",nullable = false,length = 10)
	int prioritatID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;



  /** Constructor Buit */
  public PrioritatJPA() {
  }

  /** Constructor amb tots els camps  */
  public PrioritatJPA(int prioritatID , java.lang.String nom) {
    this.prioritatID=prioritatID;
    this.nom=nom;
}
  public PrioritatJPA(Prioritat __bean) {
    this.setPrioritatID(__bean.getPrioritatID());
    this.setNom(__bean.getNom());
	}

  public static PrioritatJPA toJPA(Prioritat __bean) {
    if (__bean == null) { return null;}
    PrioritatJPA __tmp = new PrioritatJPA();
    __tmp.setPrioritatID(__bean.getPrioritatID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}

	public int getPrioritatID() {
		return(prioritatID);
	};
	public void setPrioritatID(int _prioritatID_) {
		this.prioritatID = _prioritatID_;
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
    if (__obj != null && __obj instanceof Prioritat) {
      Prioritat __instance = (Prioritat)__obj;
      __result = true;
      __result = __result && (this.getPrioritatID() == __instance.getPrioritatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:prioritatid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prioritat")
	private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

	public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
	  this.peticioDeFirmas = peticioDeFirmas;
	}




}
