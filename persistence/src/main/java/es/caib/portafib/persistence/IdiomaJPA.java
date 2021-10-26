
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "pfi_idioma" )
@SequenceGenerator(name="IDIOMA_SEQ", sequenceName="pfi_idioma_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class IdiomaJPA implements Idioma {



private static final long serialVersionUID = 1367253670L;

    @Id
    @Index(name="pfi_idioma_pk_i")
    @Column(name="idiomaid",nullable = false,length = 5)
    java.lang.String idiomaID;

    @Column(name="nom",nullable = false,length = 50)
    java.lang.String nom;

    @Column(name="suportat",nullable = false,length = 1)
    boolean suportat;

    @Column(name="ordre",nullable = false,length = 10)
    int ordre;



  /** Constructor Buit */
  public IdiomaJPA() {
  }

  /** Constructor amb tots els camps  */
  public IdiomaJPA(java.lang.String idiomaID , java.lang.String nom , boolean suportat , int ordre) {
    this.idiomaID=idiomaID;
    this.nom=nom;
    this.suportat=suportat;
    this.ordre=ordre;
}
  public IdiomaJPA(Idioma __bean) {
    this.setIdiomaID(__bean.getIdiomaID());
    this.setNom(__bean.getNom());
    this.setSuportat(__bean.isSuportat());
    this.setOrdre(__bean.getOrdre());
	}

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public boolean isSuportat() {
		return(suportat);
	};
	public void setSuportat(boolean _suportat_) {
		this.suportat = _suportat_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Idioma) {
      Idioma __instance = (Idioma)__obj;
      __result = true;
      if (this.getIdiomaID() == null) {
        __result = __result && (__instance.getIdiomaID() == null);
      } else {
        __result = __result && this.getIdiomaID().equals(__instance.getIdiomaID()) ;
      }

    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:idiomaid | Table: pfi_peticiodefirma | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idioma")
    private Set<PeticioDeFirmaJPA> peticioDeFirmas = new HashSet<PeticioDeFirmaJPA>(0);
    public  Set<PeticioDeFirmaJPA> getPeticioDeFirmas() {
    return this.peticioDeFirmas;
  }

    public void setPeticioDeFirmas(Set<PeticioDeFirmaJPA> peticioDeFirmas) {
      this.peticioDeFirmas = peticioDeFirmas;
    }


// EXP  Field:idiomaid | Table: pfi_usuariaplicacio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idioma")
    private Set<UsuariAplicacioJPA> usuariAplicacios = new HashSet<UsuariAplicacioJPA>(0);
    public  Set<UsuariAplicacioJPA> getUsuariAplicacios() {
    return this.usuariAplicacios;
  }

    public void setUsuariAplicacios(Set<UsuariAplicacioJPA> usuariAplicacios) {
      this.usuariAplicacios = usuariAplicacios;
    }


// EXP  Field:idiomaid | Table: pfi_usuaripersona | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idioma")
    private Set<UsuariPersonaJPA> usuariPersonas = new HashSet<UsuariPersonaJPA>(0);
    public  Set<UsuariPersonaJPA> getUsuariPersonas() {
    return this.usuariPersonas;
  }

    public void setUsuariPersonas(Set<UsuariPersonaJPA> usuariPersonas) {
      this.usuariPersonas = usuariPersonas;
    }



 // ---------------  STATIC METHODS ------------------
  public static IdiomaJPA toJPA(Idioma __bean) {
    if (__bean == null) { return null;}
    IdiomaJPA __tmp = new IdiomaJPA();
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setSuportat(__bean.isSuportat());
    __tmp.setOrdre(__bean.getOrdre());
		return __tmp;
	}


  public static IdiomaJPA copyJPA(IdiomaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<IdiomaJPA> copyJPA(java.util.Set<IdiomaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<IdiomaJPA> __tmpSet = (java.util.Set<IdiomaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<IdiomaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (IdiomaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static IdiomaJPA copyJPA(IdiomaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    IdiomaJPA __tmp = (IdiomaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"UsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacios())) ) {
      __tmp.setUsuariAplicacios(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacios(), __alreadyCopied,"IdiomaJPA"));
    }
    if(!"UsuariPersonaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariPersonas) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariPersonas())) ) {
      __tmp.setUsuariPersonas(UsuariPersonaJPA.copyJPA(__jpa.getUsuariPersonas(), __alreadyCopied,"IdiomaJPA"));
    }
    if(!"PeticioDeFirmaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticioDeFirmas) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticioDeFirmas())) ) {
      __tmp.setPeticioDeFirmas(PeticioDeFirmaJPA.copyJPA(__jpa.getPeticioDeFirmas(), __alreadyCopied,"IdiomaJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
