
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
@Table(name = "pfi_codibarres" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class CodiBarresJPA implements CodiBarres {



private static final long serialVersionUID = 177259997L;

  /** Contindrà la classe que gestiona aquest codi de barres */
	@Id
	@Index(name="pfi_codibarres_pk_i")
	@Column(name="codibarresid",nullable = false,length = 255)
	java.lang.String codiBarresID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;



  /** Constructor Buit */
  public CodiBarresJPA() {
  }

  /** Constructor amb tots els camps  */
  public CodiBarresJPA(java.lang.String codiBarresID , java.lang.String nom , java.lang.String descripcio) {
    this.codiBarresID=codiBarresID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public CodiBarresJPA(java.lang.String codiBarresID , java.lang.String nom) {
    this.codiBarresID=codiBarresID;
    this.nom=nom;
}
  public CodiBarresJPA(CodiBarres __bean) {
    this.setCodiBarresID(__bean.getCodiBarresID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public java.lang.String getCodiBarresID() {
		return(codiBarresID);
	};
	public void setCodiBarresID(java.lang.String _codiBarresID_) {
		this.codiBarresID = _codiBarresID_;
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
    if (__obj != null && __obj instanceof CodiBarres) {
      CodiBarres __instance = (CodiBarres)__obj;
      __result = true;
      if (this.getCodiBarresID() == null) {
        __result = __result && (__instance.getCodiBarresID() == null);
      } else {
        __result = __result && this.getCodiBarresID().equals(__instance.getCodiBarresID()) ;
      }

    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:codibarresid | Table: pfi_custodiainfo | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "codiBarres")
	private Set<CustodiaInfoJPA> custodiaInfos = new HashSet<CustodiaInfoJPA>(0);
	public  Set<CustodiaInfoJPA> getCustodiaInfos() {
    return this.custodiaInfos;
  }

	public void setCustodiaInfos(Set<CustodiaInfoJPA> custodiaInfos) {
	  this.custodiaInfos = custodiaInfos;
	}



 // ---------------  STATIC METHODS ------------------
  public static CodiBarresJPA toJPA(CodiBarres __bean) {
    if (__bean == null) { return null;}
    CodiBarresJPA __tmp = new CodiBarresJPA();
    __tmp.setCodiBarresID(__bean.getCodiBarresID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static CodiBarresJPA copyJPA(CodiBarresJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<CodiBarresJPA> copyJPA(java.util.Set<CodiBarresJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<CodiBarresJPA> __tmpSet = (java.util.Set<CodiBarresJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<CodiBarresJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (CodiBarresJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static CodiBarresJPA copyJPA(CodiBarresJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    CodiBarresJPA __tmp = (CodiBarresJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"CustodiaInfoJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.custodiaInfos) || org.hibernate.Hibernate.isInitialized(__jpa.getCustodiaInfos())) ) {
      __tmp.setCustodiaInfos(CustodiaInfoJPA.copyJPA(__jpa.getCustodiaInfos(), __alreadyCopied,"CodiBarresJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
