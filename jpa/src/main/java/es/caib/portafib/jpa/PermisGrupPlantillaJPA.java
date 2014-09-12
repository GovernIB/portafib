
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
@Table(name = "pfi_permisgrupplantilla"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"grupentitatid","fluxdefirmesid"}) } )
@SequenceGenerator(name="PORTAFIB_SEQ", sequenceName="pfi_portafib_seq")
public class PermisGrupPlantillaJPA implements PermisGrupPlantilla {



private static final long serialVersionUID = -1126538664L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PORTAFIB_SEQ")
	@Index(name="pfi_permisgrupplantilla_pk_i")
	@Column(name="permisgrupplantillaid",nullable = false,length = 19)
	long permisGrupPlantillaID;

	@Index(name="pfi_permisgrpl_grupentid_fk_i")
	@Column(name="grupentitatid",nullable = false,length = 19)
	long grupEntitatID;

	@Index(name="pfi_permisgrpl_fluxid_fk_i")
	@Column(name="fluxdefirmesid",nullable = false,length = 19)
	long plantillaFluxDeFirmesID;



  /** Constructor Buit */
  public PermisGrupPlantillaJPA() {
  }

  /** Constructor amb tots els camps  */
  public PermisGrupPlantillaJPA(long permisGrupPlantillaID , long grupEntitatID , long plantillaFluxDeFirmesID) {
    this.permisGrupPlantillaID=permisGrupPlantillaID;
    this.grupEntitatID=grupEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  /** Constructor sense valors autoincrementals */
  public PermisGrupPlantillaJPA(long grupEntitatID , long plantillaFluxDeFirmesID) {
    this.grupEntitatID=grupEntitatID;
    this.plantillaFluxDeFirmesID=plantillaFluxDeFirmesID;
}
  public PermisGrupPlantillaJPA(PermisGrupPlantilla __bean) {
    this.setPermisGrupPlantillaID(__bean.getPermisGrupPlantillaID());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
	}

  public static PermisGrupPlantillaJPA toJPA(PermisGrupPlantilla __bean) {
    if (__bean == null) { return null;}
    PermisGrupPlantillaJPA __tmp = new PermisGrupPlantillaJPA();
    __tmp.setPermisGrupPlantillaID(__bean.getPermisGrupPlantillaID());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setPlantillaFluxDeFirmesID(__bean.getPlantillaFluxDeFirmesID());
		return __tmp;
	}

	public long getPermisGrupPlantillaID() {
		return(permisGrupPlantillaID);
	};
	public void setPermisGrupPlantillaID(long _permisGrupPlantillaID_) {
		this.permisGrupPlantillaID = _permisGrupPlantillaID_;
	};

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
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
    if (__obj != null && __obj instanceof PermisGrupPlantilla) {
      PermisGrupPlantilla __instance = (PermisGrupPlantilla)__obj;
      __result = true;
      __result = __result && (this.getPermisGrupPlantillaID() == __instance.getPermisGrupPlantillaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:grupentitatid | Table: pfi_grupentitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_permisgrpl_grupentita_fk")
	@JoinColumn(name = "grupentitatid", referencedColumnName ="grupEntitatID", nullable = false, insertable=false, updatable=false)
	private GrupEntitatJPA grupEntitat;

	public GrupEntitatJPA getGrupEntitat() {
    return this.grupEntitat;
  }

	public  void setGrupEntitat(GrupEntitatJPA grupEntitat) {
    this.grupEntitat = grupEntitat;
  }

// IMP Field:fluxdefirmesid | Table: pfi_plantillafluxdefirmes | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pfi_permisgrpl_plantiflfi_fk")
	@JoinColumn(name = "fluxdefirmesid", referencedColumnName ="fluxDeFirmesID", nullable = false, insertable=false, updatable=false)
	private PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes;

	public PlantillaFluxDeFirmesJPA getPlantillaFluxDeFirmes() {
    return this.plantillaFluxDeFirmes;
  }

	public  void setPlantillaFluxDeFirmes(PlantillaFluxDeFirmesJPA plantillaFluxDeFirmes) {
    this.plantillaFluxDeFirmes = plantillaFluxDeFirmes;
  }



}
