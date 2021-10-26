
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@SuppressWarnings("deprecation")
@Entity
@Table(name = "pfi_usuariaplicacioperfil" )
@SequenceGenerator(name="PERFILDEFIRMA_SEQ", sequenceName="pfi_usuariaplicacioperfil_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PerfilDeFirmaJPA implements PerfilDeFirma {



private static final long serialVersionUID = -877725275L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PERFILDEFIRMA_SEQ")
    @Index(name="pfi_usuariaplicacioperfil_pk_i")
    @Column(name="usuariaplicacioperfilid",nullable = false,length = 19)
    long usuariAplicacioPerfilID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="codi",nullable = false,unique = true,length = 100)
    java.lang.String codi;

    @Column(name="descripcio",length = 500)
    java.lang.String descripcio;

    @Column(name="condicio",length = 4000)
    java.lang.String condicio;

    @Index(name="pfi_perfilapp_appconf1id_fk_i")
    @Column(name="usrappconfiguracio1id",nullable = false,length = 19)
    long configuracioDeFirma1ID;

    @Index(name="pfi_perfilapp_appconf2id_fk_i")
    @Column(name="usrappconfiguracio2id",length = 19)
    java.lang.Long configuracioDeFirma2ID;

    @Index(name="pfi_perfilapp_appconf3id_fk_i")
    @Column(name="usrappconfiguracio3id",length = 19)
    java.lang.Long configuracioDeFirma3ID;

    @Index(name="pfi_perfilapp_appconf4id_fk_i")
    @Column(name="usrappconfiguracio4id",length = 19)
    java.lang.Long configuracioDeFirma4ID;

    @Index(name="pfi_perfilapp_appconf5id_fk_i")
    @Column(name="usrappconfiguracio5id",length = 19)
    java.lang.Long configuracioDeFirma5ID;

    @Column(name="urlbase",length = 255)
    java.lang.String urlBase;



  /** Constructor Buit */
  public PerfilDeFirmaJPA() {
  }

  /** Constructor amb tots els camps  */
  public PerfilDeFirmaJPA(long usuariAplicacioPerfilID , java.lang.String nom , java.lang.String codi , java.lang.String descripcio , java.lang.String condicio , long configuracioDeFirma1ID , java.lang.Long configuracioDeFirma2ID , java.lang.Long configuracioDeFirma3ID , java.lang.Long configuracioDeFirma4ID , java.lang.Long configuracioDeFirma5ID , java.lang.String urlBase) {
    this.usuariAplicacioPerfilID=usuariAplicacioPerfilID;
    this.nom=nom;
    this.codi=codi;
    this.descripcio=descripcio;
    this.condicio=condicio;
    this.configuracioDeFirma1ID=configuracioDeFirma1ID;
    this.configuracioDeFirma2ID=configuracioDeFirma2ID;
    this.configuracioDeFirma3ID=configuracioDeFirma3ID;
    this.configuracioDeFirma4ID=configuracioDeFirma4ID;
    this.configuracioDeFirma5ID=configuracioDeFirma5ID;
    this.urlBase=urlBase;
}
  /** Constructor sense valors autoincrementals */
  public PerfilDeFirmaJPA(java.lang.String nom , java.lang.String codi , java.lang.String descripcio , java.lang.String condicio , long configuracioDeFirma1ID , java.lang.Long configuracioDeFirma2ID , java.lang.Long configuracioDeFirma3ID , java.lang.Long configuracioDeFirma4ID , java.lang.Long configuracioDeFirma5ID , java.lang.String urlBase) {
    this.nom=nom;
    this.codi=codi;
    this.descripcio=descripcio;
    this.condicio=condicio;
    this.configuracioDeFirma1ID=configuracioDeFirma1ID;
    this.configuracioDeFirma2ID=configuracioDeFirma2ID;
    this.configuracioDeFirma3ID=configuracioDeFirma3ID;
    this.configuracioDeFirma4ID=configuracioDeFirma4ID;
    this.configuracioDeFirma5ID=configuracioDeFirma5ID;
    this.urlBase=urlBase;
}
  /** Constructor dels valors Not Null */
  public PerfilDeFirmaJPA(long usuariAplicacioPerfilID , java.lang.String nom , java.lang.String codi , long configuracioDeFirma1ID) {
    this.usuariAplicacioPerfilID=usuariAplicacioPerfilID;
    this.nom=nom;
    this.codi=codi;
    this.configuracioDeFirma1ID=configuracioDeFirma1ID;
}
  public PerfilDeFirmaJPA(PerfilDeFirma __bean) {
    this.setUsuariAplicacioPerfilID(__bean.getUsuariAplicacioPerfilID());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setDescripcio(__bean.getDescripcio());
    this.setCondicio(__bean.getCondicio());
    this.setConfiguracioDeFirma1ID(__bean.getConfiguracioDeFirma1ID());
    this.setConfiguracioDeFirma2ID(__bean.getConfiguracioDeFirma2ID());
    this.setConfiguracioDeFirma3ID(__bean.getConfiguracioDeFirma3ID());
    this.setConfiguracioDeFirma4ID(__bean.getConfiguracioDeFirma4ID());
    this.setConfiguracioDeFirma5ID(__bean.getConfiguracioDeFirma5ID());
    this.setUrlBase(__bean.getUrlBase());
	}

	public long getUsuariAplicacioPerfilID() {
		return(usuariAplicacioPerfilID);
	};
	public void setUsuariAplicacioPerfilID(long _usuariAplicacioPerfilID_) {
		this.usuariAplicacioPerfilID = _usuariAplicacioPerfilID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getCondicio() {
		return(condicio);
	};
	public void setCondicio(java.lang.String _condicio_) {
		this.condicio = _condicio_;
	};

	public long getConfiguracioDeFirma1ID() {
		return(configuracioDeFirma1ID);
	};
	public void setConfiguracioDeFirma1ID(long _configuracioDeFirma1ID_) {
		this.configuracioDeFirma1ID = _configuracioDeFirma1ID_;
	};

	public java.lang.Long getConfiguracioDeFirma2ID() {
		return(configuracioDeFirma2ID);
	};
	public void setConfiguracioDeFirma2ID(java.lang.Long _configuracioDeFirma2ID_) {
		this.configuracioDeFirma2ID = _configuracioDeFirma2ID_;
	};

	public java.lang.Long getConfiguracioDeFirma3ID() {
		return(configuracioDeFirma3ID);
	};
	public void setConfiguracioDeFirma3ID(java.lang.Long _configuracioDeFirma3ID_) {
		this.configuracioDeFirma3ID = _configuracioDeFirma3ID_;
	};

	public java.lang.Long getConfiguracioDeFirma4ID() {
		return(configuracioDeFirma4ID);
	};
	public void setConfiguracioDeFirma4ID(java.lang.Long _configuracioDeFirma4ID_) {
		this.configuracioDeFirma4ID = _configuracioDeFirma4ID_;
	};

	public java.lang.Long getConfiguracioDeFirma5ID() {
		return(configuracioDeFirma5ID);
	};
	public void setConfiguracioDeFirma5ID(java.lang.Long _configuracioDeFirma5ID_) {
		this.configuracioDeFirma5ID = _configuracioDeFirma5ID_;
	};

	public java.lang.String getUrlBase() {
		return(urlBase);
	};
	public void setUrlBase(java.lang.String _urlBase_) {
		this.urlBase = _urlBase_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PerfilDeFirma) {
      PerfilDeFirma __instance = (PerfilDeFirma)__obj;
      __result = true;
      __result = __result && (this.getUsuariAplicacioPerfilID() == __instance.getUsuariAplicacioPerfilID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:usuariaplicacioperfilid | Table: pfi_perfilsperusrapp | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfilDeFirma")
    private Set<PerfilsPerUsuariAplicacioJPA> perfilsPerUsuariAplicacios = new HashSet<PerfilsPerUsuariAplicacioJPA>(0);
    public  Set<PerfilsPerUsuariAplicacioJPA> getPerfilsPerUsuariAplicacios() {
    return this.perfilsPerUsuariAplicacios;
  }

    public void setPerfilsPerUsuariAplicacios(Set<PerfilsPerUsuariAplicacioJPA> perfilsPerUsuariAplicacios) {
      this.perfilsPerUsuariAplicacios = perfilsPerUsuariAplicacios;
    }


// IMP Field:usuariaplicacioconfigid | Table: pfi_usuariaplicacioconfig | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_perfilapp_confapp_1_fk")
    @JoinColumn(name = "usrappconfiguracio1id", referencedColumnName ="usuariAplicacioConfigID", nullable = false, insertable=false, updatable=false)
    private UsuariAplicacioConfiguracioJPA configuracioDeFirma1;

    public UsuariAplicacioConfiguracioJPA getConfiguracioDeFirma1() {
    return this.configuracioDeFirma1;
  }

    public  void setConfiguracioDeFirma1(UsuariAplicacioConfiguracioJPA configuracioDeFirma1) {
    this.configuracioDeFirma1 = configuracioDeFirma1;
  }

// IMP Field:usuariaplicacioconfigid | Table: pfi_usuariaplicacioconfig | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_perfilapp_confapp_2_fk")
    @JoinColumn(name = "usrappconfiguracio2id", referencedColumnName ="usuariAplicacioConfigID", nullable = true, insertable=false, updatable=false)
    private UsuariAplicacioConfiguracioJPA configuracioDeFirma2;

    public UsuariAplicacioConfiguracioJPA getConfiguracioDeFirma2() {
    return this.configuracioDeFirma2;
  }

    public  void setConfiguracioDeFirma2(UsuariAplicacioConfiguracioJPA configuracioDeFirma2) {
    this.configuracioDeFirma2 = configuracioDeFirma2;
  }

// IMP Field:usuariaplicacioconfigid | Table: pfi_usuariaplicacioconfig | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_perfilapp_confapp_3_fk")
    @JoinColumn(name = "usrappconfiguracio3id", referencedColumnName ="usuariAplicacioConfigID", nullable = true, insertable=false, updatable=false)
    private UsuariAplicacioConfiguracioJPA configuracioDeFirma3;

    public UsuariAplicacioConfiguracioJPA getConfiguracioDeFirma3() {
    return this.configuracioDeFirma3;
  }

    public  void setConfiguracioDeFirma3(UsuariAplicacioConfiguracioJPA configuracioDeFirma3) {
    this.configuracioDeFirma3 = configuracioDeFirma3;
  }

// IMP Field:usuariaplicacioconfigid | Table: pfi_usuariaplicacioconfig | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_perfilapp_confapp_4_fk")
    @JoinColumn(name = "usrappconfiguracio4id", referencedColumnName ="usuariAplicacioConfigID", nullable = true, insertable=false, updatable=false)
    private UsuariAplicacioConfiguracioJPA configuracioDeFirma4;

    public UsuariAplicacioConfiguracioJPA getConfiguracioDeFirma4() {
    return this.configuracioDeFirma4;
  }

    public  void setConfiguracioDeFirma4(UsuariAplicacioConfiguracioJPA configuracioDeFirma4) {
    this.configuracioDeFirma4 = configuracioDeFirma4;
  }

// IMP Field:usuariaplicacioconfigid | Table: pfi_usuariaplicacioconfig | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name="pfi_perfilapp_confapp_5_fk")
    @JoinColumn(name = "usrappconfiguracio5id", referencedColumnName ="usuariAplicacioConfigID", nullable = true, insertable=false, updatable=false)
    private UsuariAplicacioConfiguracioJPA configuracioDeFirma5;

    public UsuariAplicacioConfiguracioJPA getConfiguracioDeFirma5() {
    return this.configuracioDeFirma5;
  }

    public  void setConfiguracioDeFirma5(UsuariAplicacioConfiguracioJPA configuracioDeFirma5) {
    this.configuracioDeFirma5 = configuracioDeFirma5;
  }


 // ---------------  STATIC METHODS ------------------
  public static PerfilDeFirmaJPA toJPA(PerfilDeFirma __bean) {
    if (__bean == null) { return null;}
    PerfilDeFirmaJPA __tmp = new PerfilDeFirmaJPA();
    __tmp.setUsuariAplicacioPerfilID(__bean.getUsuariAplicacioPerfilID());
    __tmp.setNom(__bean.getNom());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setCondicio(__bean.getCondicio());
    __tmp.setConfiguracioDeFirma1ID(__bean.getConfiguracioDeFirma1ID());
    __tmp.setConfiguracioDeFirma2ID(__bean.getConfiguracioDeFirma2ID());
    __tmp.setConfiguracioDeFirma3ID(__bean.getConfiguracioDeFirma3ID());
    __tmp.setConfiguracioDeFirma4ID(__bean.getConfiguracioDeFirma4ID());
    __tmp.setConfiguracioDeFirma5ID(__bean.getConfiguracioDeFirma5ID());
    __tmp.setUrlBase(__bean.getUrlBase());
		return __tmp;
	}


  public static PerfilDeFirmaJPA copyJPA(PerfilDeFirmaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PerfilDeFirmaJPA> copyJPA(java.util.Set<PerfilDeFirmaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PerfilDeFirmaJPA> __tmpSet = (java.util.Set<PerfilDeFirmaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PerfilDeFirmaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PerfilDeFirmaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PerfilDeFirmaJPA copyJPA(PerfilDeFirmaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PerfilDeFirmaJPA __tmp = (PerfilDeFirmaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PerfilsPerUsuariAplicacioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilsPerUsuariAplicacios) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilsPerUsuariAplicacios())) ) {
      __tmp.setPerfilsPerUsuariAplicacios(PerfilsPerUsuariAplicacioJPA.copyJPA(__jpa.getPerfilsPerUsuariAplicacios(), __alreadyCopied,"PerfilDeFirmaJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.configuracioDeFirma2) || org.hibernate.Hibernate.isInitialized(__jpa.getConfiguracioDeFirma2()) ) ) {
      __tmp.setConfiguracioDeFirma2(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getConfiguracioDeFirma2(), __alreadyCopied,"PerfilDeFirmaJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.configuracioDeFirma1) || org.hibernate.Hibernate.isInitialized(__jpa.getConfiguracioDeFirma1()) ) ) {
      __tmp.setConfiguracioDeFirma1(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getConfiguracioDeFirma1(), __alreadyCopied,"PerfilDeFirmaJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.configuracioDeFirma5) || org.hibernate.Hibernate.isInitialized(__jpa.getConfiguracioDeFirma5()) ) ) {
      __tmp.setConfiguracioDeFirma5(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getConfiguracioDeFirma5(), __alreadyCopied,"PerfilDeFirmaJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.configuracioDeFirma4) || org.hibernate.Hibernate.isInitialized(__jpa.getConfiguracioDeFirma4()) ) ) {
      __tmp.setConfiguracioDeFirma4(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getConfiguracioDeFirma4(), __alreadyCopied,"PerfilDeFirmaJPA"));
    }
    if(!"UsuariAplicacioConfiguracioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.configuracioDeFirma3) || org.hibernate.Hibernate.isInitialized(__jpa.getConfiguracioDeFirma3()) ) ) {
      __tmp.setConfiguracioDeFirma3(UsuariAplicacioConfiguracioJPA.copyJPA(__jpa.getConfiguracioDeFirma3(), __alreadyCopied,"PerfilDeFirmaJPA"));
    }

    return __tmp;
  }




}
