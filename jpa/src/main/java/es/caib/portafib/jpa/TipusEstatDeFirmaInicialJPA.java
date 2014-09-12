
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
@Table(name = "pfi_tipusestatdefirmainicial" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class TipusEstatDeFirmaInicialJPA implements TipusEstatDeFirmaInicial {



private static final long serialVersionUID = 2002136744L;

	@Id
	@Index(name="pfi_estfirmini_pk_i")
	@Column(name="tipusestatdefirmainicialid",nullable = false,length = 19)
	long tipusEstatDeFirmaInicialID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;



  /** Constructor Buit */
  public TipusEstatDeFirmaInicialJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusEstatDeFirmaInicialJPA(long tipusEstatDeFirmaInicialID , java.lang.String nom , java.lang.String descripcio) {
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public TipusEstatDeFirmaInicialJPA(long tipusEstatDeFirmaInicialID , java.lang.String nom) {
    this.tipusEstatDeFirmaInicialID=tipusEstatDeFirmaInicialID;
    this.nom=nom;
}
  public TipusEstatDeFirmaInicialJPA(TipusEstatDeFirmaInicial __bean) {
    this.setTipusEstatDeFirmaInicialID(__bean.getTipusEstatDeFirmaInicialID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

  public static TipusEstatDeFirmaInicialJPA toJPA(TipusEstatDeFirmaInicial __bean) {
    if (__bean == null) { return null;}
    TipusEstatDeFirmaInicialJPA __tmp = new TipusEstatDeFirmaInicialJPA();
    __tmp.setTipusEstatDeFirmaInicialID(__bean.getTipusEstatDeFirmaInicialID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}

	public long getTipusEstatDeFirmaInicialID() {
		return(tipusEstatDeFirmaInicialID);
	};
	public void setTipusEstatDeFirmaInicialID(long _tipusEstatDeFirmaInicialID_) {
		this.tipusEstatDeFirmaInicialID = _tipusEstatDeFirmaInicialID_;
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
    if (__obj != null && __obj instanceof TipusEstatDeFirmaInicial) {
      TipusEstatDeFirmaInicial __instance = (TipusEstatDeFirmaInicial)__obj;
      __result = true;
      __result = __result && (this.getTipusEstatDeFirmaInicialID() == __instance.getTipusEstatDeFirmaInicialID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tipusestatdefirmainicialid | Table: pfi_estatdefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusEstatDeFirmaInicial")
	private Set<EstatDeFirmaJPA> estatDeFirmas = new HashSet<EstatDeFirmaJPA>(0);
	public  Set<EstatDeFirmaJPA> getEstatDeFirmas() {
    return this.estatDeFirmas;
  }

	public void setEstatDeFirmas(Set<EstatDeFirmaJPA> estatDeFirmas) {
	  this.estatDeFirmas = estatDeFirmas;
	}




}
