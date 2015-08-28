
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pfi_roleusuariaplicacio"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"usuariaplicacioid","roleid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class RoleUsuariAplicacioJPA implements RoleUsuariAplicacio {



private static final long serialVersionUID = 1411867987L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_roleusuariaplicacio_pk_i")
	@Column(name="id",nullable = false,length = 19)
	long id;

	@Index(name="pfi_roleusrapp_roleid_fk_i")
	@Column(name="roleid",nullable = false,length = 50)
	java.lang.String roleID;

	@Index(name="pfi_roleusrapp_usrappid_fk_i")
	@Column(name="usuariaplicacioid",nullable = false,length = 50)
	java.lang.String usuariAplicacioID;



  /** Constructor Buit */
  public RoleUsuariAplicacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public RoleUsuariAplicacioJPA(long id , java.lang.String roleID , java.lang.String usuariAplicacioID) {
    this.id=id;
    this.roleID=roleID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  /** Constructor sense valors autoincrementals */
  public RoleUsuariAplicacioJPA(java.lang.String roleID , java.lang.String usuariAplicacioID) {
    this.roleID=roleID;
    this.usuariAplicacioID=usuariAplicacioID;
}
  public RoleUsuariAplicacioJPA(RoleUsuariAplicacio __bean) {
    this.setId(__bean.getId());
    this.setRoleID(__bean.getRoleID());
    this.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
	}

	public long getId() {
		return(id);
	};
	public void setId(long _id_) {
		this.id = _id_;
	};

	public java.lang.String getRoleID() {
		return(roleID);
	};
	public void setRoleID(java.lang.String _roleID_) {
		this.roleID = _roleID_;
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
    if (__obj != null && __obj instanceof RoleUsuariAplicacio) {
      RoleUsuariAplicacio __instance = (RoleUsuariAplicacio)__obj;
      __result = true;
      __result = __result && (this.getId() == __instance.getId()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:roleid | Table: pfi_role | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_roleusrapp_role_fk")
	@JoinColumn(name = "roleid", referencedColumnName ="roleID", nullable = false, insertable=false, updatable=false)
	private RoleJPA role;

	public RoleJPA getRole() {
    return this.role;
  }

	public  void setRole(RoleJPA role) {
    this.role = role;
  }

// IMP Field:usuariaplicacioid | Table: pfi_usuariaplicacio | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_roleusrapp_usrapp_fk")
	@JoinColumn(name = "usuariaplicacioid", referencedColumnName ="usuariAplicacioID", nullable = false, insertable=false, updatable=false)
	private UsuariAplicacioJPA usuariAplicacio;

	public UsuariAplicacioJPA getUsuariAplicacio() {
    return this.usuariAplicacio;
  }

	public  void setUsuariAplicacio(UsuariAplicacioJPA usuariAplicacio) {
    this.usuariAplicacio = usuariAplicacio;
  }


 // ---------------  STATIC METHODS ------------------
  public static RoleUsuariAplicacioJPA toJPA(RoleUsuariAplicacio __bean) {
    if (__bean == null) { return null;}
    RoleUsuariAplicacioJPA __tmp = new RoleUsuariAplicacioJPA();
    __tmp.setId(__bean.getId());
    __tmp.setRoleID(__bean.getRoleID());
    __tmp.setUsuariAplicacioID(__bean.getUsuariAplicacioID());
		return __tmp;
	}


  public static RoleUsuariAplicacioJPA copyJPA(RoleUsuariAplicacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<RoleUsuariAplicacioJPA> copyJPA(java.util.Set<RoleUsuariAplicacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<RoleUsuariAplicacioJPA> __tmpSet = (java.util.Set<RoleUsuariAplicacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<RoleUsuariAplicacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (RoleUsuariAplicacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static RoleUsuariAplicacioJPA copyJPA(RoleUsuariAplicacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    RoleUsuariAplicacioJPA __tmp = (RoleUsuariAplicacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"RoleJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.role) || org.hibernate.Hibernate.isInitialized(__jpa.getRole()) ) ) {
      __tmp.setRole(RoleJPA.copyJPA(__jpa.getRole(), __alreadyCopied,"RoleUsuariAplicacioJPA"));
    }
    if(!"UsuariAplicacioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariAplicacio) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariAplicacio()) ) ) {
      __tmp.setUsuariAplicacio(UsuariAplicacioJPA.copyJPA(__jpa.getUsuariAplicacio(), __alreadyCopied,"RoleUsuariAplicacioJPA"));
    }

    return __tmp;
  }




}
