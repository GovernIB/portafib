
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;


@Entity
@Table(name = "pfi_usuaripersona" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class UsuariPersonaJPA implements UsuariPersona {



private static final long serialVersionUID = -80349968L;

  /** Identificador en el sistema de seguretat */
	@Id
	@Index(name="pfi_usuaripersona_pk_i")
	@Column(name="usuaripersonaid",nullable = false,length = 50)
	java.lang.String usuariPersonaID;

	@Column(name="nom",nullable = false,length = 50)
	java.lang.String nom;

	@Column(name="llinatges",nullable = false,length = 100)
	java.lang.String llinatges;

	@Column(name="email",nullable = false,length = 100)
	java.lang.String email;

	@Index(name="pfi_usuaripersona_nif_i")
	@Column(name="nif",nullable = false,unique = true,length = 9)
	java.lang.String nif;

	@Index(name="pfi_persona_idiomaid_fk_i")
	@Column(name="idiomaid",nullable = false,length = 5)
	java.lang.String idiomaID;

  /** és la firma gràfica de la persona */
	@Index(name="pfi_persona_rubricaid_fk_i")
	@Column(name="rubricaid",length = 19)
	java.lang.Long rubricaID;



  /** Constructor Buit */
  public UsuariPersonaJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariPersonaJPA(java.lang.String usuariPersonaID , java.lang.String nom , java.lang.String llinatges , java.lang.String email , java.lang.String nif , java.lang.String idiomaID , java.lang.Long rubricaID) {
    this.usuariPersonaID=usuariPersonaID;
    this.nom=nom;
    this.llinatges=llinatges;
    this.email=email;
    this.nif=nif;
    this.idiomaID=idiomaID;
    this.rubricaID=rubricaID;
}
  /** Constructor dels valors Not Null */
  public UsuariPersonaJPA(java.lang.String usuariPersonaID , java.lang.String nom , java.lang.String llinatges , java.lang.String email , java.lang.String nif , java.lang.String idiomaID) {
    this.usuariPersonaID=usuariPersonaID;
    this.nom=nom;
    this.llinatges=llinatges;
    this.email=email;
    this.nif=nif;
    this.idiomaID=idiomaID;
}
  public UsuariPersonaJPA(UsuariPersona __bean) {
    this.setUsuariPersonaID(__bean.getUsuariPersonaID());
    this.setNom(__bean.getNom());
    this.setLlinatges(__bean.getLlinatges());
    this.setEmail(__bean.getEmail());
    this.setNif(__bean.getNif());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setRubricaID(__bean.getRubricaID());
    // Fitxer
    this.setRubrica(FitxerJPA.toJPA(__bean.getRubrica()));
	}

  public static UsuariPersonaJPA toJPA(UsuariPersona __bean) {
    if (__bean == null) { return null;}
    UsuariPersonaJPA __tmp = new UsuariPersonaJPA();
    __tmp.setUsuariPersonaID(__bean.getUsuariPersonaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatges(__bean.getLlinatges());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setNif(__bean.getNif());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setRubricaID(__bean.getRubricaID());
    // Fitxer
    __tmp.setRubrica(FitxerJPA.toJPA(__bean.getRubrica()));
		return __tmp;
	}

	public java.lang.String getUsuariPersonaID() {
		return(usuariPersonaID);
	};
	public void setUsuariPersonaID(java.lang.String _usuariPersonaID_) {
		this.usuariPersonaID = _usuariPersonaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatges() {
		return(llinatges);
	};
	public void setLlinatges(java.lang.String _llinatges_) {
		this.llinatges = _llinatges_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.Long getRubricaID() {
		return(rubricaID);
	};
	public void setRubricaID(java.lang.Long _rubricaID_) {
		this.rubricaID = _rubricaID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof UsuariPersona) {
      UsuariPersona __instance = (UsuariPersona)__obj;
      __result = true;
      if (this.getUsuariPersonaID() == null) {
        __result = __result && (__instance.getUsuariPersonaID() == null);
      } else {
        __result = __result && this.getUsuariPersonaID().equals(__instance.getUsuariPersonaID()) ;
      }

    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:usuaripersonaid | Table: pfi_usuarientitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariPersona")
	private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
	public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

	public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
	  this.usuariEntitats = usuariEntitats;
	}


// IMP Field:idiomaid | Table: pfi_idioma | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_persona_idioma_fk")
	@JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false)
	private IdiomaJPA idioma;

	public IdiomaJPA getIdioma() {
    return this.idioma;
  }

	public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pfi_persona_fitxer_fk")
	@JoinColumn(name = "rubricaid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA rubrica;

	public FitxerJPA getRubrica() {
    return this.rubrica;
  }

	public  void setRubrica(FitxerJPA rubrica) {
    this.rubrica = rubrica;
  }



}
