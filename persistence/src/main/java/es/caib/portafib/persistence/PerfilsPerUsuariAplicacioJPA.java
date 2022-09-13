
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "PerfilsPerUsuariAplicacioJPA")
@Table(name = "pfi_perfilsperusrapp" , indexes = { 
        @Index(name="pfi_perfilsperusrapp_pk_i", columnList = "perfilsperusrappid"),
        @Index(name="pfi_perfilsua_perfilid_fk_i", columnList = "usuariaplicacioperfilid"),
        @Index(name="pfi_perfilsua_usuappid_fk_i", columnList = "usuariaplicacioid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pfi_perfilsua_uaid_perf_uk", columnNames={"usuariaplicacioperfilid","usuariaplicacioid"}) } )
@SequenceGenerator(name="PERFILSPERUSUARIAPLICACIO_SEQ", sequenceName="pfi_perfilsperusrapp_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PerfilsPerUsuariAplicacioJPA implements PerfilsPerUsuariAplicacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PERFILSPERUSUARIAPLICACIO_SEQ")
    @Column(name="perfilsperusrappid",nullable = false,length = 19)
    long perfilsPerUsrAppID;

    @Column(name="usuariaplicacioperfilid",nullable = false,length = 19)
    long perfilDeFirmaID;

    @Column(name="usuariaplicacioid",nullable = false,length = 50)
    java.lang.String usuariAplicacioID;



  /** Constructor Buit */
  public PerfilsPerUsuariAplicacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public PerfilsPerUsuariAplicacioJPA(long perfilsPerUsrAppID , long perfilDeFirmaID , java.lang.String usuariAplicacioID) {
    this.perfilsPerUsrAppID=perfilsPerUsrAppID;
    this.perfilDeFirmaID=perfilDeFirmaID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor sense valors autoincrementals */
  public PerfilsPerUsuariAplicacioJPA(long perfilDeFirmaID , java.lang.String usuariAplicacioID) {
    this.perfilDeFirmaID=perfilDeFirmaID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  public PerfilsPerUsuariAplicacioJPA(PerfilsPerUsuariAplicacio __bean) {
    this.setPerfilsPerUsrAppID(__bean.getPerfilsPerUsrAppID());
    this.setPerfilDeFirmaID(__bean.getPerfilDeFirmaID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
	}

	public long getPerfilsPerUsrAppID() {
		return(perfilsPerUsrAppID);
	};
	public void setPerfilsPerUsrAppID(long _perfilsPerUsrAppID_) {
		this.perfilsPerUsrAppID = _perfilsPerUsrAppID_;
	};

	public long getPerfilDeFirmaID() {
		return(perfilDeFirmaID);
	};
	public void setPerfilDeFirmaID(long _perfilDeFirmaID_) {
		this.perfilDeFirmaID = _perfilDeFirmaID_;
	};

	public java.lang.String getUsuariAplicacioID() {
		return(usuariAplicacioID);
	};
	public void setUsuariAplicacioID(java.lang.String _usuariAplicacioID_) {
		this.usuariAplicacioID = _usuariAplicacioID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PerfilsPerUsuariAplicacio) {
      PerfilsPerUsuariAplicacio __instance = (PerfilsPerUsuariAplicacio)__obj;
      __result = true;
      __result = __result && (this.getPerfilsPerUsrAppID() == __instance.getPerfilsPerUsrAppID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuariaplicacioperfilid | Table: pfi_usuariaplicacioperfil | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariaplicacioperfilid", referencedColumnName ="usuariAplicacioPerfilID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_perfilsua_perfilapp_p_fk"))
    private PerfilDeFirmaJPA perfilDeFirma;

    public PerfilDeFirmaJPA getPerfilDeFirma() {
    return this.perfilDeFirma;
  }

    public  void setPerfilDeFirma(PerfilDeFirmaJPA perfilDeFirma) {
    this.perfilDeFirma = perfilDeFirma;
  }

// IMP Field:usuariaplicacioid | Table: pfi_usuariaplicacio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariaplicacioid", referencedColumnName ="usuariAplicacioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pfi_perfilsua_usrapp_usr_fk"))
    private UsuariAplicacioJPA usuariAplicacio;

    public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

    public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }


 // ---------------  STATIC METHODS ------------------
  public static PerfilsPerUsuariAplicacioJPA toJPA(PerfilsPerUsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    PerfilsPerUsuariAplicacioJPA __tmp = new PerfilsPerUsuariAplicacioJPA();
    __tmp.setPerfilsPerUsrAppID(__bean.getPerfilsPerUsrAppID());
    __tmp.setPerfilDeFirmaID(__bean.getPerfilDeFirmaID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}


  public static PerfilsPerUsuariAplicacioJPA copyJPA(PerfilsPerUsuariAplicacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PerfilsPerUsuariAplicacioJPA> copyJPA(java.util.Set<PerfilsPerUsuariAplicacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PerfilsPerUsuariAplicacioJPA> __tmpSet = (java.util.Set<PerfilsPerUsuariAplicacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PerfilsPerUsuariAplicacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PerfilsPerUsuariAplicacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PerfilsPerUsuariAplicacioJPA copyJPA(PerfilsPerUsuariAplicacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PerfilsPerUsuariAplicacioJPA __tmp = (PerfilsPerUsuariAplicacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"PerfilDeFirmaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.perfilDeFirma) || org.hibernate.Hibernate.isInitialized(__jpa.getPerfilDeFirma()) ) ) {
      __tmp.setPerfilDeFirma(PerfilDeFirmaJPA.copyJPA(__jpa.getPerfilDeFirma(), __alreadyCopied,"PerfilsPerUsuariAplicacioJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"PerfilsPerUsuariAplicacioJPA"));
    }

    return __tmp;
  }




}
