
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
@Table(name = "pfi_tipusfirma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class TipusFirmaJPA implements TipusFirma {



private static final long serialVersionUID = -1309802895L;

	@Id
	@Index(name="pfi_tipusfirma_pk_i")
	@Column(name="tipusfirmaid",nullable = false,length = 19)
	int tipusFirmaID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;

	@Column(name="suportada",nullable = false,length = 1)
	boolean suportada;

	@Column(name="descripcio",length = 1000)
	java.lang.String descripcio;



  /** Constructor Buit */
  public TipusFirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusFirmaJPA(int tipusFirmaID , java.lang.String nom , boolean suportada , java.lang.String descripcio) {
    this.tipusFirmaID=tipusFirmaID;
    this.nom=nom;
    this.suportada=suportada;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusFirmaJPA(int tipusFirmaID , java.lang.String nom , boolean suportada) {
    this.tipusFirmaID=tipusFirmaID;
    this.nom=nom;
    this.suportada=suportada;
}
  public TipusFirmaJPA(TipusFirma __bean) {
    this.setTipusFirmaID(__bean.getTipusFirmaID());
    this.setNom(__bean.getNom());
    this.setSuportada(__bean.isSuportada());
    this.setDescripcio(__bean.getDescripcio());
	}

  public static TipusFirmaJPA toJPA(TipusFirma __bean) {
    if (__bean == null) { return null;}
    TipusFirmaJPA __tmp = new TipusFirmaJPA();
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setSuportada(__bean.isSuportada());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}

	public int getTipusFirmaID() {
		return(tipusFirmaID);
	};
	public void setTipusFirmaID(int _tipusFirmaID_) {
		this.tipusFirmaID = _tipusFirmaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public boolean isSuportada() {
		return(suportada);
	};
	public void setSuportada(boolean _suportada_) {
		this.suportada = _suportada_;
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
    if (__obj != null && __obj instanceof TipusFirma) {
      TipusFirma __instance = (TipusFirma)__obj;
      __result = true;
      __result = __result && (this.getTipusFirmaID() == __instance.getTipusFirmaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tipusfirmaid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusFirma")
	private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

	public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
	  this.peticioDeFirmas = peticioDeFirmas;
	}




}
