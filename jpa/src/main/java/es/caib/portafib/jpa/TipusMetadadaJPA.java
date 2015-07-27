
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
@Table(name = "pfi_tipusmetadada" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class TipusMetadadaJPA implements TipusMetadada {



private static final long serialVersionUID = 818818051L;

	@Id
	@Index(name="pfi_tipusmetadada_pk_i")
	@Column(name="tipusmetadadaid",nullable = false,length = 10)
	int tipusMetadadaID;

	@Column(name="nom",nullable = false,unique = true,length = 100)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;



  /** Constructor Buit */
  public TipusMetadadaJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusMetadadaJPA(int tipusMetadadaID , java.lang.String nom , java.lang.String descripcio) {
    this.tipusMetadadaID=tipusMetadadaID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusMetadadaJPA(int tipusMetadadaID , java.lang.String nom) {
    this.tipusMetadadaID=tipusMetadadaID;
    this.nom=nom;
}
  public TipusMetadadaJPA(TipusMetadada __bean) {
    this.setTipusMetadadaID(__bean.getTipusMetadadaID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

  public static TipusMetadadaJPA toJPA(TipusMetadada __bean) {
    if (__bean == null) { return null;}
    TipusMetadadaJPA __tmp = new TipusMetadadaJPA();
    __tmp.setTipusMetadadaID(__bean.getTipusMetadadaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}

	public int getTipusMetadadaID() {
		return(tipusMetadadaID);
	};
	public void setTipusMetadadaID(int _tipusMetadadaID_) {
		this.tipusMetadadaID = _tipusMetadadaID_;
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
    if (__obj != null && __obj instanceof TipusMetadada) {
      TipusMetadada __instance = (TipusMetadada)__obj;
      __result = true;
      __result = __result && (this.getTipusMetadadaID() == __instance.getTipusMetadadaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tipusmetadadaid | Table: pfi_metadada | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusMetadada")
	private Set<MetadadaJPA> metadadas = new HashSet<MetadadaJPA>(0);
	public  Set<MetadadaJPA> getMetadadas() {
    return this.metadadas;
  }

	public void setMetadadas(Set<MetadadaJPA> metadadas) {
	  this.metadadas = metadadas;
	}




}
