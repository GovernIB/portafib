
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
@Table(name = "pfi_idioma" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
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

  public static IdiomaJPA toJPA(Idioma __bean) {
    if (__bean == null) { return null;}
    IdiomaJPA __tmp = new IdiomaJPA();
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setSuportat(__bean.isSuportat());
    __tmp.setOrdre(__bean.getOrdre());
		return __tmp;
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




}
