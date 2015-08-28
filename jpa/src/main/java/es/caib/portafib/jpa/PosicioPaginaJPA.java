
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
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
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



 // ---------------  STATIC METHODS ------------------
  public static PosicioPaginaJPA toJPA(PosicioPagina __bean) {
    if (__bean == null) { return null;}
    PosicioPaginaJPA __tmp = new PosicioPaginaJPA();
    __tmp.setPosicioPaginaID(__bean.getPosicioPaginaID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}


  public static PosicioPaginaJPA copyJPA(PosicioPaginaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PosicioPaginaJPA> copyJPA(java.util.Set<PosicioPaginaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PosicioPaginaJPA> __tmpSet = (java.util.Set<PosicioPaginaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PosicioPaginaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PosicioPaginaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PosicioPaginaJPA copyJPA(PosicioPaginaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PosicioPaginaJPA __tmp = (PosicioPaginaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfo_missatgeposiciopaginaids) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfo_missatgeposiciopaginaids())) ) {
      __tmp.setCustodiaInfo_missatgeposiciopaginaids(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfo_missatgeposiciopaginaids(), __alreadyCopied,"PosicioPaginaJPA"));
    }
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfo_codibarresposiciopaginaids) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfo_codibarresposiciopaginaids())) ) {
      __tmp.setCustodiaInfo_codibarresposiciopaginaids(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfo_codibarresposiciopaginaids(), __alreadyCopied,"PosicioPaginaJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
