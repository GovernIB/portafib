
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
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
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


// EXP  Field:tipusfirmaid | Table: pfi_usuariaplicacioconfig | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusFirma")
	private Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracios = new HashSet<UsuariAplicacioConfiguracioJPA>(0);
	public  Set<UsuariAplicacioConfiguracioJPA> getUsuariAplicacioConfiguracios() {
    return this.usuariAplicacioConfiguracios;
  }

	public void setUsuariAplicacioConfiguracios(Set<UsuariAplicacioConfiguracioJPA> usuariAplicacioConfiguracios) {
	  this.usuariAplicacioConfiguracios = usuariAplicacioConfiguracios;
	}



 // ---------------  STATIC METHODS ------------------
  public static TipusFirmaJPA toJPA(TipusFirma __bean) {
    if (__bean == null) { return null;}
    TipusFirmaJPA __tmp = new TipusFirmaJPA();
    __tmp.setTipusFirmaID(__bean.getTipusFirmaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setSuportada(__bean.isSuportada());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static TipusFirmaJPA copyJPA(TipusFirmaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TipusFirmaJPA> copyJPA(java.util.Set<TipusFirmaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TipusFirmaJPA> __tmpSet = (java.util.Set<TipusFirmaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TipusFirmaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TipusFirmaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TipusFirmaJPA copyJPA(TipusFirmaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TipusFirmaJPA __tmp = (TipusFirmaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirmas())) ) {
      __tmp.setPeticioDeFirmas(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirmas(), __alreadyCopied,"TipusFirmaJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacioConfiguracios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacioConfiguracios())) ) {
      __tmp.setUsuariAplicacioConfiguracios(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getUsuariAplicacioConfiguracios(), __alreadyCopied,"TipusFirmaJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
