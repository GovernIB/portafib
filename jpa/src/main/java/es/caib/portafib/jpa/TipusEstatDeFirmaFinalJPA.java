
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
@Table(name = "pfi_tipusestatdefirmafinal" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class TipusEstatDeFirmaFinalJPA implements TipusEstatDeFirmaFinal {



private static final long serialVersionUID = -1249884409L;

	@Id
	@Index(name="pfi_estfirmafi_pk_i")
	@Column(name="tipusestatdefirmafinalid",nullable = false,length = 19)
	long tipusEstatDeFirmaFinalID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;



  /** Constructor Buit */
  public TipusEstatDeFirmaFinalJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusEstatDeFirmaFinalJPA(long tipusEstatDeFirmaFinalID , java.lang.String nom , java.lang.String descripcio) {
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusEstatDeFirmaFinalJPA(long tipusEstatDeFirmaFinalID , java.lang.String nom) {
    this.tipusEstatDeFirmaFinalID=tipusEstatDeFirmaFinalID;
    this.nom=nom;
}
  public TipusEstatDeFirmaFinalJPA(TipusEstatDeFirmaFinal __bean) {
    this.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

  public static TipusEstatDeFirmaFinalJPA toJPA(TipusEstatDeFirmaFinal __bean) {
    if (__bean == null) { return null;}
    TipusEstatDeFirmaFinalJPA __tmp = new TipusEstatDeFirmaFinalJPA();
    __tmp.setTipusEstatDeFirmaFinalID(__bean.getTipusEstatDeFirmaFinalID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}

	public long getTipusEstatDeFirmaFinalID() {
		return(tipusEstatDeFirmaFinalID);
	};
	public void setTipusEstatDeFirmaFinalID(long _tipusEstatDeFirmaFinalID_) {
		this.tipusEstatDeFirmaFinalID = _tipusEstatDeFirmaFinalID_;
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
    if (__obj != null && __obj instanceof TipusEstatDeFirmaFinal) {
      TipusEstatDeFirmaFinal __instance = (TipusEstatDeFirmaFinal)__obj;
      __result = true;
      __result = __result && (this.getTipusEstatDeFirmaFinalID() == __instance.getTipusEstatDeFirmaFinalID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tipusestatdefirmafinalid | Table: pfi_estatdefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusEstatDeFirmaFinal")
	private Set<EstatDeFirmaJPA> estatDeFirmas = new HashSet<EstatDeFirmaJPA>(0);
	public  Set<EstatDeFirmaJPA> getEstatDeFirmas() {
    return this.estatDeFirmas;
  }

	public void setEstatDeFirmas(Set<EstatDeFirmaJPA> estatDeFirmas) {
	  this.estatDeFirmas = estatDeFirmas;
	}


// EXP  Field:tipusestatdefirmafinalid | Table: pfi_firma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusEstatDeFirmaFinal")
	private Set<FirmaJPA> firmas = new HashSet<FirmaJPA>(0);
	public  Set<FirmaJPA> getFirmas() {
    return this.firmas;
  }

	public void setFirmas(Set<FirmaJPA> firmas) {
	  this.firmas = firmas;
	}




}
