
package es.caib.portafib.jpa;
import es.caib.portafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.Index;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity
@Table(name = "pfi_roleusuarientitat"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"roleid","usuarientitatid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class RoleUsuariEntitatJPA implements RoleUsuariEntitat {



private static final long serialVersionUID = -1738883575L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_roleusuarientitat_pk_i")
	@Column(name="id",nullable = false,length = 19)
	long id;

	@Index(name="pfi_roleusrent_roleid_fk_i")
	@Column(name="roleid",nullable = false,length = 50)
	java.lang.String roleID;

	@Index(name="pfi_roleusrent_usrentid_fk_i")
	@Column(name="usuarientitatid",nullable = false,length = 101)
	java.lang.String usuariEntitatID;



  /** Constructor Buit */
  public RoleUsuariEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public RoleUsuariEntitatJPA(long id , java.lang.String roleID , java.lang.String usuariEntitatID) {
    this.id=id;
    this.roleID=roleID;
    this.usuariEntitatID=usuariEntitatID;
}
  /** Constructor sense valors autoincrementals */
  public RoleUsuariEntitatJPA(java.lang.String roleID , java.lang.String usuariEntitatID) {
    this.roleID=roleID;
    this.usuariEntitatID=usuariEntitatID;
}
  public RoleUsuariEntitatJPA(RoleUsuariEntitat __bean) {
    this.setId(__bean.getId());
    this.setRoleID(__bean.getRoleID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
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

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof RoleUsuariEntitat) {
      RoleUsuariEntitat __instance = (RoleUsuariEntitat)__obj;
      __result = true;
      __result = __result && (this.getId() == __instance.getId()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:roleid | Table: pfi_role | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_roleusrent_role_fk")
	@JoinColumn(name = "roleid", referencedColumnName ="roleID", nullable = false, insertable=false, updatable=false)
	private RoleJPA role;

	public RoleJPA getRole() {
    return this.role;
  }

	public  void setRole(RoleJPA role) {
    this.role = role;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_roleusrent_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static RoleUsuariEntitatJPA toJPA(RoleUsuariEntitat __bean) {
    if (__bean == null) { return null;}
    RoleUsuariEntitatJPA __tmp = new RoleUsuariEntitatJPA();
    __tmp.setId(__bean.getId());
    __tmp.setRoleID(__bean.getRoleID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
		return __tmp;
	}


  public static RoleUsuariEntitatJPA copyJPA(RoleUsuariEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<RoleUsuariEntitatJPA> copyJPA(java.util.Set<RoleUsuariEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<RoleUsuariEntitatJPA> __tmpSet = (java.util.Set<RoleUsuariEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<RoleUsuariEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (RoleUsuariEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static RoleUsuariEntitatJPA copyJPA(RoleUsuariEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    RoleUsuariEntitatJPA __tmp = (RoleUsuariEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"RoleJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.role) || org.hibernate.Hibernate.isInitialized(__jpa.getRole()) ) ) {
      __tmp.setRole(RoleJPA.copyJPA(__jpa.getRole(), __alreadyCopied,"RoleUsuariEntitatJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitat()) ) ) {
      __tmp.setUsuariEntitat(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitat(), __alreadyCopied,"RoleUsuariEntitatJPA"));
    }

    return __tmp;
  }




}
