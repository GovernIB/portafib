
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
@Table(name = "pfi_posiciopagina" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class PosicioPaginaJPA implements PosicioPagina {



private static final long serialVersionUID = 1764665569L;

	@Id
	@Index(name="pfi_posiciopagina_pk_i")
	@Column(name="posiciopaginaid",nullable = false,length = 19)
	long posicioPaginaID;

  /** Contindrà el codi de traducció */
	@Column(name="nom",nullable = false,length = 255)
	java.lang.String nom;



  /** Constructor Buit */
  public PosicioPaginaJPA() {
  }

  /** Constructor amb tots els camps  */
  public PosicioPaginaJPA(long posicioPaginaID , java.lang.String nom) {
    this.posicioPaginaID=posicioPaginaID;
    this.nom=nom;
}
  public PosicioPaginaJPA(PosicioPagina __bean) {
    this.setPosicioPaginaID(__bean.getPosicioPaginaID());
    this.setNom(__bean.getNom());
	}

  public static PosicioPaginaJPA toJPA(PosicioPagina __bean) {
    if (__bean == null) { return null;}
    PosicioPaginaJPA __tmp = new PosicioPaginaJPA();
    __tmp.setPosicioPaginaID(__bean.getPosicioPaginaID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}

	public long getPosicioPaginaID() {
		return(posicioPaginaID);
	};
	public void setPosicioPaginaID(long _posicioPaginaID_) {
		this.posicioPaginaID = _posicioPaginaID_;
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
    if (__obj != null && __obj instanceof PosicioPagina) {
      PosicioPagina __instance = (PosicioPagina)__obj;
      __result = true;
      __result = __result && (this.getPosicioPaginaID() == __instance.getPosicioPaginaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:codibarresposiciopaginaid | Table: pfi_custodiainfo | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "codiBarresPosicioPaginaID")
	private Set<CustodiaInfoJPA> custodiaInfo_codibarresposiciopaginaids = new HashSet<CustodiaInfoJPA>(0);
	public  Set<CustodiaInfoJPA> getCustodiaInfo_codibarresposiciopaginaids() {
    return this.custodiaInfo_codibarresposiciopaginaids;
  }

	public void setCustodiaInfo_codibarresposiciopaginaids(Set<CustodiaInfoJPA> custodiaInfo_codibarresposiciopaginaids) {
	  this.custodiaInfo_codibarresposiciopaginaids = custodiaInfo_codibarresposiciopaginaids;
	}


// EXP  Field:missatgeposiciopaginaid | Table: pfi_custodiainfo | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "missatgePosicioPaginaID")
	private Set<CustodiaInfoJPA> custodiaInfo_missatgeposiciopaginaids = new HashSet<CustodiaInfoJPA>(0);
	public  Set<CustodiaInfoJPA> getCustodiaInfo_missatgeposiciopaginaids() {
    return this.custodiaInfo_missatgeposiciopaginaids;
  }

	public void setCustodiaInfo_missatgeposiciopaginaids(Set<CustodiaInfoJPA> custodiaInfo_missatgeposiciopaginaids) {
	  this.custodiaInfo_missatgeposiciopaginaids = custodiaInfo_missatgeposiciopaginaids;
	}




}
