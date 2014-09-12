
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
@Table(name = "pfi_algorismedefirma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class AlgorismeDeFirmaJPA implements AlgorismeDeFirma {



private static final long serialVersionUID = 2131015322L;

	@Id
	@Index(name="pfi_algorismedefirma_pk_i")
	@Column(name="algorismedefirmaid",nullable = false,length = 19)
	long algorismeDeFirmaID;

	@Column(name="nom",nullable = false,unique = true,length = 100)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;

	@Column(name="suportat",length = 1)
	boolean suportat;



  /** Constructor Buit */
  public AlgorismeDeFirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public AlgorismeDeFirmaJPA(long algorismeDeFirmaID , java.lang.String nom , java.lang.String descripcio , boolean suportat) {
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.suportat=suportat;
}
  /** Constructor dels valors Not Null */
  public AlgorismeDeFirmaJPA(long algorismeDeFirmaID , java.lang.String nom) {
    this.algorismeDeFirmaID=algorismeDeFirmaID;
    this.nom=nom;
}
  public AlgorismeDeFirmaJPA(AlgorismeDeFirma __bean) {
    this.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setSuportat(__bean.isSuportat());
	}

  public static AlgorismeDeFirmaJPA toJPA(AlgorismeDeFirma __bean) {
    if (__bean == null) { return null;}
    AlgorismeDeFirmaJPA __tmp = new AlgorismeDeFirmaJPA();
    __tmp.setAlgorismeDeFirmaID(__bean.getAlgorismeDeFirmaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setSuportat(__bean.isSuportat());
		return __tmp;
	}

	public long getAlgorismeDeFirmaID() {
		return(algorismeDeFirmaID);
	};
	public void setAlgorismeDeFirmaID(long _algorismeDeFirmaID_) {
		this.algorismeDeFirmaID = _algorismeDeFirmaID_;
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

	public boolean isSuportat() {
		return(suportat);
	};
	public void setSuportat(boolean _suportat_) {
		this.suportat = _suportat_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof AlgorismeDeFirma) {
      AlgorismeDeFirma __instance = (AlgorismeDeFirma)__obj;
      __result = true;
      __result = __result && (this.getAlgorismeDeFirmaID() == __instance.getAlgorismeDeFirmaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:algorismedefirmaid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "algorismeDeFirma")
	private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

	public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
	  this.peticioDeFirmas = peticioDeFirmas;
	}




}
