
package es.caib.portafib.persistence;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_role" , indexes = { 
        @Index(name="pfi_role_pk_i", columnList = "roleid")})
@SequenceGenerator(name="ROLE_SEQ", sequenceName="pfi_role_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class RoleJPA implements Role {



private static final long serialVersionUID = -1253450907L;

    @Id
    @Column(name="roleid",nullable = false,length = 50)
    java.lang.String roleID;

    @Column(name="nom",nullable = false,length = 50)
    java.lang.String nom;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;



  /** Constructor Buit */
  public RoleJPA() {
  }

  /** Constructor amb tots els camps  */
  public RoleJPA(java.lang.String roleID , java.lang.String nom , java.lang.String descripcio) {
    this.roleID=roleID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public RoleJPA(java.lang.String roleID , java.lang.String nom) {
    this.roleID=roleID;
    this.nom=nom;
}
  public RoleJPA(Role __bean) {
    this.setRoleID(__bean.getRoleID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public java.lang.String getRoleID() {
		return(roleID);
	};
	public void setRoleID(java.lang.String _roleID_) {
		this.roleID = _roleID_;
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Role) {
      Role __instance = (Role)__obj;
      __result = true;
      if (this.getRoleID() == null) {
        __result = __result && (__instance.getRoleID() == null);
      } else {
        __result = __result && this.getRoleID().equals(__instance.getRoleID()) ;
      }

    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:roleid | Table: pfi_roleusuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<RoleUsuariEntitatJPA> roleUsuariEntitats = new HashSet<RoleUsuariEntitatJPA>(0);
    public  Set<RoleUsuariEntitatJPA> getRoleUsuariEntitats() {
    return this.roleUsuariEntitats;
  }

    public void setRoleUsuariEntitats(Set<RoleUsuariEntitatJPA> roleUsuariEntitats) {
      this.roleUsuariEntitats = roleUsuariEntitats;
    }



 // ---------------  STATIC METHODS ------------------
  public static RoleJPA toJPA(Role __bean) {
    if (__bean == null) { return null;}
    RoleJPA __tmp = new RoleJPA();
    __tmp.setRoleID(__bean.getRoleID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static RoleJPA copyJPA(RoleJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<RoleJPA> copyJPA(java.util.Set<RoleJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<RoleJPA> __tmpSet = (java.util.Set<RoleJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<RoleJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (RoleJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static RoleJPA copyJPA(RoleJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    RoleJPA __tmp = (RoleJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"RoleUsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.roleUsuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getRoleUsuariEntitats())) ) {
      __tmp.setRoleUsuariEntitats(RoleUsuariEntitatJPA.copyJPA(__jpa.getRoleUsuariEntitats(), __alreadyCopied,"RoleJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
