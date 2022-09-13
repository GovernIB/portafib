
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "UsuariPersonaJPA")
@Table(name = "pfi_usuaripersona" , indexes = { 
        @Index(name="pfi_usuaripersona_pk_i", columnList = "usuaripersonaid"),
        @Index(name="pfi_usuaripersona_nif_i", columnList = "nif"),
        @Index(name="pfi_persona_idiomaid_fk_i", columnList = "idiomaid"),
        @Index(name="pfi_persona_rubricaid_fk_i", columnList = "rubricaid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pfi_persona_nif_extern_uk", columnNames={"nif","usuariintern"}) } )
@SequenceGenerator(name="USUARIPERSONA_SEQ", sequenceName="pfi_usuaripersona_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariPersonaJPA implements UsuariPersona {

  /** Identificador en el sistema de seguretat */
    @Id
    @Column(name="usuaripersonaid",nullable = false,length = 50)
    java.lang.String usuariPersonaID;

    @Column(name="nom",nullable = false,length = 50)
    java.lang.String nom;

    @Column(name="llinatges",nullable = false,length = 100)
    java.lang.String llinatges;

    @Column(name="email",nullable = false,length = 100)
    java.lang.String email;

    @Column(name="nif",nullable = false,length = 9)
    java.lang.String nif;

    @Column(name="idiomaid",nullable = false,length = 5)
    java.lang.String idiomaID;

  /** és la firma gràfica de la persona */
    @Column(name="rubricaid",length = 19)
    java.lang.Long rubricaID;

    @Column(name="usuariintern",nullable = false,length = 1)
    boolean usuariIntern = true;

    @Column(name="contrasenya",length = 255)
    java.lang.String contrasenya;



  /** Constructor Buit */
  public UsuariPersonaJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariPersonaJPA(java.lang.String usuariPersonaID , java.lang.String nom , java.lang.String llinatges , java.lang.String email , java.lang.String nif , java.lang.String idiomaID , java.lang.Long rubricaID , boolean usuariIntern , java.lang.String contrasenya) {
    this.usuariPersonaID=usuariPersonaID;
    this.nom=nom;
    this.llinatges=llinatges;
    this.email=email;
    this.nif=nif;
    this.idiomaID=idiomaID;
    this.rubricaID=rubricaID;
    this.usuariIntern=usuariIntern;
    this.contrasenya=contrasenya;
}
  /** Constructor dels valors Not Null */
  public UsuariPersonaJPA(java.lang.String usuariPersonaID , java.lang.String nom , java.lang.String llinatges , java.lang.String email , java.lang.String nif , java.lang.String idiomaID , boolean usuariIntern) {
    this.usuariPersonaID=usuariPersonaID;
    this.nom=nom;
    this.llinatges=llinatges;
    this.email=email;
    this.nif=nif;
    this.idiomaID=idiomaID;
    this.usuariIntern=usuariIntern;
}
  public UsuariPersonaJPA(UsuariPersona __bean) {
    this.setUsuariPersonaID(__bean.getUsuariPersonaID());
    this.setNom(__bean.getNom());
    this.setLlinatges(__bean.getLlinatges());
    this.setEmail(__bean.getEmail());
    this.setNif(__bean.getNif());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setRubricaID(__bean.getRubricaID());
    this.setUsuariIntern(__bean.isUsuariIntern());
    this.setContrasenya(__bean.getContrasenya());
    // Fitxer
    this.setRubrica(FitxerJPA.toJPA(__bean.getRubrica()));
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

	public boolean isUsuariIntern() {
		return(usuariIntern);
	};
	public void setUsuariIntern(boolean _usuariIntern_) {
		this.usuariIntern = _usuariIntern_;
	};

	public java.lang.String getContrasenya() {
		return(contrasenya);
	};
	public void setContrasenya(java.lang.String _contrasenya_) {
		this.contrasenya = _contrasenya_;
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
    @JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_persona_idioma_fk"))
    private IdiomaJPA idioma;

    public IdiomaJPA getIdioma() {
    return this.idioma;
  }

    public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }

// IMP Field:fitxerid | Table: pfi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rubricaid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_persona_fitxer_fk"))
    private FitxerJPA rubrica;

    public FitxerJPA getRubrica() {
    return this.rubrica;
  }

    public  void setRubrica(FitxerJPA rubrica) {
    this.rubrica = rubrica;
  }


 // ---------------  STATIC METHODS ------------------
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
    __tmp.setUsuariIntern(__bean.isUsuariIntern());
    __tmp.setContrasenya(__bean.getContrasenya());
    // Fitxer
    __tmp.setRubrica(FitxerJPA.toJPA(__bean.getRubrica()));
		return __tmp;
	}


  public static UsuariPersonaJPA copyJPA(UsuariPersonaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariPersonaJPA> copyJPA(java.util.Set<UsuariPersonaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariPersonaJPA> __tmpSet = (java.util.Set<UsuariPersonaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariPersonaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariPersonaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariPersonaJPA copyJPA(UsuariPersonaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariPersonaJPA __tmp = (UsuariPersonaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"UsuariPersonaJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"IdiomaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.idioma) || org.hibernate.Hibernate.isInitialized(__jpa.getIdioma()) ) ) {
      __tmp.setIdioma(IdiomaJPA.copyJPA(__jpa.getIdioma(), __alreadyCopied,"UsuariPersonaJPA"));
    }

    return __tmp;
  }




}
