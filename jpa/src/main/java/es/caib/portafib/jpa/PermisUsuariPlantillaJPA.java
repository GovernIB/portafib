
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
@Table(name = "pfi_permisusuariplantilla"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"usuarientitatid","fluxdefirmesid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq", allocationSize=1)
public class PermisUsuariPlantillaJPA implements PermisUsuariPlantilla {



private static final long serialVersionUID = -1127198733L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_permisusuariplantilla_pk_i")
	@Column(name="permisusuariplantillaid",nullable = false,length = 19)
	long permisUsuariPlantillaID;

	@Index(name="pfi_permisuspl_usrentid_fk_i")
	@Column(name="usuarientitatid",nullable = false,length = 101)
	java.lang.String usuariEntitatID;

	@Index(name="pfi_permisuspl_fluxid_fk_i")
	@Column(name="fluxdefirmesid",nullable = false,length = 19)
	long plantillaFluxDeFirmesID;



  /** Constructor Buit */
  public PermisUsuariPlantillaJPA() {
  }

  /** Constructor amb tots els camps  */
  public PermisUsuariPlantillaJPA(long permisUsuariPlantillaID , java.lang.String usuariEntitatID , long plantillaFluxDeFirmesID) {
    this.permisUsuariPlantillaID=permisUsuariPlantillaID;
    this.usuariEntitatID=usuariEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  /** Constructor sense valors autoincrementals */
  public PermisUsuariPlantillaJPA(java.lang.String usuariEntitatID , long plantillaFluxDeFirmesID) {
    this.usuariEntitatID=usuariEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  public PermisUsuariPlantillaJPA(PermisUsuariPlantilla __bean) {
    this.setPermisUsuariPlantillaID(__bean.getPermisUsuariPlantillaID());
    this.setUsuariEntitatID(__bean.getUsuariEntitatID());
    this.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
	}

  public static PermisUsuariPlantillaJPA toJPA(PermisUsuariPlantilla __bean) {
    if (__bean == null) { return null;}
    PermisUsuariPlantillaJPA __tmp = new PermisUsuariPlantillaJPA();
    __tmp.setPermisUsuariPlantillaID(__bean.getPermisUsuariPlantillaID());
    __tmp.setUsuariEntitatID(__bean.getUsuariEntitatID());
    __tmp.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
		return __tmp;
	}

	public long getPermisUsuariPlantillaID() {
		return(permisUsuariPlantillaID);
	};
	public void setPermisUsuariPlantillaID(long _permisUsuariPlantillaID_) {
		this.permisUsuariPlantillaID = _permisUsuariPlantillaID_;
	};

	public java.lang.String getUsuariEntitatID() {
		return(usuariEntitatID);
	};
	public void setUsuariEntitatID(java.lang.String _usuariEntitatID_) {
		this.usuariEntitatID = _usuariEntitatID_;
	};

	public long getPlantillaFluxDeFirmesID() {
		return(plantillaFluxDeFirmesID);
	};
	public void setPlantillaFluxDeFirmesID(long _plantillaFluxDeFirmesID_) {
		this.plantillaFluxDeFirmesID = _plantillaFluxDeFirmesID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PermisUsuariPlantilla) {
      PermisUsuariPlantilla __instance = (PermisUsuariPlantilla)__obj;
      __result = true;
      __result = __result && (this.getPermisUsuariPlantillaID() == __instance.getPermisUsuariPlantillaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:usuarientitatid | Table: pfi_usuarientitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_permisuspl_usrentitat_fk")
	@JoinColumn(name = "usuarientitatid", referencedColumnName ="usuariEntitatID", nullable = false, insertable=false, updatable=false)
	private UsuariEntitatJPA usuariEntitat;

	public UsuariEntitatJPA getUsuariEntitat() {
    return this.usuariEntitat;
  }

	public  void setUsuariEntitat(UsuariEntitatJPA usuariEntitat) {
    this.usuariEntitat = usuariEntitat;
  }

// IMP Field:fluxdefirmesid | Table: pfi_plantillafluxdefirmes | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_permisuspl_plantiflfi_fk")
	@JoinColumn(name = "fluxdefirmesid", referencedColumnName ="fluxDeFirmesID", nullable = false, insertable=false, updatable=false)
	private PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes;

	public PlantillaFluxDeFirmesJPA getPlantillaFluxDeFirmes() {
    return this.plantillaFluxDeFirmes;
  }

	public  void setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes) {
    this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
  }



}
