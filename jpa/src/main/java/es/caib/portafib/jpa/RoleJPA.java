
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
@Table(name = "pfi_role" )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class RoleJPA implements Role {



private static final long serialVersionUID = -1253450907L;

	@Id
	@Index(name="pfi_role_pk_i")
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

  public static RoleJPA toJPA(Role __bean) {
    if (__bean == null) { return null;}
    RoleJPA __tmp = new RoleJPA();
    __tmp.setRoleID(__bean.getRoleID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
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

// EXP  Field:roleid | Table: pfi_roleusuariaplicacio | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private Set<RoleUsuariAplicacioJPA> roleUsuariAplicacios = new HashSet<RoleUsuariAplicacioJPA>(0);
	public  Set<RoleUsuariAplicacioJPA> getRoleUsuariAplicacios() {
    return this.roleUsuariAplicacios;
  }

	public void setRoleUsuariAplicacios(Set<RoleUsuariAplicacioJPA> roleUsuariAplicacios) {
	  this.roleUsuariAplicacios = roleUsuariAplicacios;
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




}
