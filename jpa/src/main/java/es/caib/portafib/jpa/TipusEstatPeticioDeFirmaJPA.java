
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
@Table(name = "pfi_tipusestatpeticiodefirma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class TipusEstatPeticioDeFirmaJPA implements TipusEstatPeticioDeFirma {



private static final long serialVersionUID = -1903404584L;

	@Id
	@Index(name="pfi_estpetfirm_pk_i")
	@Column(name="tipusestatpeticiodefirmaid",nullable = false,length = 19)
	int tipusEstatPeticioDeFirmaID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;

	@Column(name="descripcio",length = 1000)
	java.lang.String descripcio;



  /** Constructor Buit */
  public TipusEstatPeticioDeFirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusEstatPeticioDeFirmaJPA(int tipusEstatPeticioDeFirmaID , java.lang.String nom , java.lang.String descripcio) {
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusEstatPeticioDeFirmaJPA(int tipusEstatPeticioDeFirmaID , java.lang.String nom) {
    this.tipusEstatPeticioDeFirmaID=tipusEstatPeticioDeFirmaID;
    this.nom=nom;
}
  public TipusEstatPeticioDeFirmaJPA(TipusEstatPeticioDeFirma __bean) {
    this.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

  public static TipusEstatPeticioDeFirmaJPA toJPA(TipusEstatPeticioDeFirma __bean) {
    if (__bean == null) { return null;}
    TipusEstatPeticioDeFirmaJPA __tmp = new TipusEstatPeticioDeFirmaJPA();
    __tmp.setTipusEstatPeticioDeFirmaID(__bean.getTipusEstatPeticioDeFirmaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}

	public int getTipusEstatPeticioDeFirmaID() {
		return(tipusEstatPeticioDeFirmaID);
	};
	public void setTipusEstatPeticioDeFirmaID(int _tipusEstatPeticioDeFirmaID_) {
		this.tipusEstatPeticioDeFirmaID = _tipusEstatPeticioDeFirmaID_;
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TipusEstatPeticioDeFirma) {
      TipusEstatPeticioDeFirma __instance = (TipusEstatPeticioDeFirma)__obj;
      __result = true;
      __result = __result && (this.getTipusEstatPeticioDeFirmaID() == __instance.getTipusEstatPeticioDeFirmaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tipusestatpeticiodefirmaid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusEstatPeticioDeFirma")
	private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

	public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
	  this.peticioDeFirmas = peticioDeFirmas;
	}




}
