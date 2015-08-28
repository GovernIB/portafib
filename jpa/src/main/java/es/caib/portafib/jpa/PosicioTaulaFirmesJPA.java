
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
@Table(name = "pfi_posiciotaulafirmes" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class PosicioTaulaFirmesJPA implements PosicioTaulaFirmes {



private static final long serialVersionUID = -464992442L;

	@Id
	@Index(name="pfi_posiciotaulafirmes_pk_i")
	@Column(name="posiciotaulafirmesid",nullable = false,length = 19)
	int posicioTaulaFirmesID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;

	@Column(name="suportada",nullable = false,length = 1)
	boolean suportada;



  /** Constructor Buit */
  public PosicioTaulaFirmesJPA() {
  }

  /** Constructor amb tots els camps  */
  public PosicioTaulaFirmesJPA(int posicioTaulaFirmesID , java.lang.String nom , java.lang.String descripcio , boolean suportada) {
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.suportada=suportada;
}
  /** Constructor dels valors Not Null */
  public PosicioTaulaFirmesJPA(int posicioTaulaFirmesID , java.lang.String nom , boolean suportada) {
    this.posicioTaulaFirmesID=posicioTaulaFirmesID;
    this.nom=nom;
    this.suportada=suportada;
}
  public PosicioTaulaFirmesJPA(PosicioTaulaFirmes __bean) {
    this.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setSuportada(__bean.isSuportada());
	}

	public int getPosicioTaulaFirmesID() {
		return(posicioTaulaFirmesID);
	};
	public void setPosicioTaulaFirmesID(int _posicioTaulaFirmesID_) {
		this.posicioTaulaFirmesID = _posicioTaulaFirmesID_;
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

	public boolean isSuportada() {
		return(suportada);
	};
	public void setSuportada(boolean _suportada_) {
		this.suportada = _suportada_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PosicioTaulaFirmes) {
      PosicioTaulaFirmes __instance = (PosicioTaulaFirmes)__obj;
      __result = true;
      __result = __result && (this.getPosicioTaulaFirmesID() == __instance.getPosicioTaulaFirmesID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:posiciotaulafirmesid | Table: pfi_peticiodefirma | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "posicioTaulaFirmes")
	private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
	public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

	public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
	  this.peticioDeFirmas = peticioDeFirmas;
	}



 // ---------------  STATIC METHODS ------------------
  public static PosicioTaulaFirmesJPA toJPA(PosicioTaulaFirmes __bean) {
    if (__bean == null) { return null;}
    PosicioTaulaFirmesJPA __tmp = new PosicioTaulaFirmesJPA();
    __tmp.setPosicioTaulaFirmesID(__bean.getPosicioTaulaFirmesID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setSuportada(__bean.isSuportada());
		return __tmp;
	}


  public static PosicioTaulaFirmesJPA copyJPA(PosicioTaulaFirmesJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PosicioTaulaFirmesJPA> copyJPA(java.util.Set<PosicioTaulaFirmesJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PosicioTaulaFirmesJPA> __tmpSet = (java.util.Set<PosicioTaulaFirmesJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PosicioTaulaFirmesJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PosicioTaulaFirmesJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PosicioTaulaFirmesJPA copyJPA(PosicioTaulaFirmesJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PosicioTaulaFirmesJPA __tmp = (PosicioTaulaFirmesJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirmas())) ) {
      __tmp.setPeticioDeFirmas(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirmas(), __alreadyCopied,"PosicioTaulaFirmesJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
