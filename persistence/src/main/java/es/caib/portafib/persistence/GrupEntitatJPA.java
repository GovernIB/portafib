
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "GrupEntitatJPA")
@Table(name = "pfi_grupentitat" , indexes = { 
        @Index(name="pfi_grupentitat_pk_i", columnList = "grupentitatid"),
        @Index(name="pfi_grupentitat_entitatid_fk_i", columnList = "entitatid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pfi_grupentita_nomentitat_uk", columnNames={"nom","entitatid"}) } )
@SequenceGenerator(name="GRUPENTITAT_SEQ", sequenceName="pfi_grupentitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class GrupEntitatJPA implements GrupEntitat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GRUPENTITAT_SEQ")
    @Column(name="grupentitatid",nullable = false,length = 19)
    long grupEntitatID;

    @Column(name="nom",nullable = false,length = 100)
    java.lang.String nom;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

    @Column(name="entitatid",nullable = false,length = 50)
    java.lang.String entitatID;



  /** Constructor Buit */
  public GrupEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public GrupEntitatJPA(long grupEntitatID , java.lang.String nom , java.lang.String descripcio , java.lang.String entitatID) {
    this.grupEntitatID=grupEntitatID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public GrupEntitatJPA(java.lang.String nom , java.lang.String descripcio , java.lang.String entitatID) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
}
  public GrupEntitatJPA(GrupEntitat __bean) {
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
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

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof GrupEntitat) {
      GrupEntitat __instance = (GrupEntitat)__obj;
      __result = true;
      __result = __result && (this.getGrupEntitatID() == __instance.getGrupEntitatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:grupentitatid | Table: pfi_grupentitatusuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grupEntitat")
    private Set<GrupEntitatUsuariEntitatJPA> grupEntitatUsuariEntitats = new HashSet<GrupEntitatUsuariEntitatJPA>(0);
    public  Set<GrupEntitatUsuariEntitatJPA> getGrupEntitatUsuariEntitats() {
    return this.grupEntitatUsuariEntitats;
  }

    public void setGrupEntitatUsuariEntitats(Set<GrupEntitatUsuariEntitatJPA> grupEntitatUsuariEntitats) {
      this.grupEntitatUsuariEntitats = grupEntitatUsuariEntitats;
    }


// EXP  Field:grupentitatid | Table: pfi_permisgrupplantilla | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grupEntitat")
    private Set<PermisGrupPlantillaJPA> permisGrupPlantillas = new HashSet<PermisGrupPlantillaJPA>(0);
    public  Set<PermisGrupPlantillaJPA> getPermisGrupPlantillas() {
    return this.permisGrupPlantillas;
  }

    public void setPermisGrupPlantillas(Set<PermisGrupPlantillaJPA> permisGrupPlantillas) {
      this.permisGrupPlantillas = permisGrupPlantillas;
    }


// IMP Field:entitatid | Table: pfi_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_grupentita_entitat_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static GrupEntitatJPA toJPA(GrupEntitat __bean) {
    if (__bean == null) { return null;}
    GrupEntitatJPA __tmp = new GrupEntitatJPA();
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static GrupEntitatJPA copyJPA(GrupEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<GrupEntitatJPA> copyJPA(java.util.Set<GrupEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<GrupEntitatJPA> __tmpSet = (java.util.Set<GrupEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<GrupEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (GrupEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static GrupEntitatJPA copyJPA(GrupEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    GrupEntitatJPA __tmp = (GrupEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PermisGrupPlantillaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.permisGrupPlantillas) || org.hibernate.Hibernate.isInitialized(__jpa.getPermisGrupPlantillas())) ) {
      __tmp.setPermisGrupPlantillas(PermisGrupPlantillaJPA.copyJPA(__jpa.getPermisGrupPlantillas(), __alreadyCopied,"GrupEntitatJPA"));
    }
    if(!"GrupEntitatUsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitatUsuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitatUsuariEntitats())) ) {
      __tmp.setGrupEntitatUsuariEntitats(GrupEntitatUsuariEntitatJPA.copyJPA(__jpa.getGrupEntitatUsuariEntitats(), __alreadyCopied,"GrupEntitatJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"GrupEntitatJPA"));
    }

    return __tmp;
  }




}
